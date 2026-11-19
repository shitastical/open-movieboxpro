package androidx.media3.extractor.mp4;

import android.util.Pair;
import androidx.core.view.ViewCompat;
import androidx.media3.common.C;
import androidx.media3.common.ColorInfo;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.Metadata;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.ParserException;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.Util;
import androidx.media3.container.DolbyVisionConfig;
import androidx.media3.container.MdtaMetadataEntry;
import androidx.media3.container.Mp4AlternateGroupData;
import androidx.media3.container.Mp4Box;
import androidx.media3.container.Mp4LocationData;
import androidx.media3.container.Mp4TimestampData;
import androidx.media3.container.NalUnitUtil;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.Ac3Util;
import androidx.media3.extractor.Ac4Util;
import androidx.media3.extractor.AvcConfig;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.GaplessInfoHolder;
import androidx.media3.extractor.HevcConfig;
import androidx.media3.extractor.OpusUtil;
import androidx.media3.extractor.VorbisUtil;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;
import com.movieboxpro.android.utils.ShellUtil;
import j$.util.Objects;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.videolan.libvlc.MediaPlayer;

/* JADX INFO: loaded from: classes.dex */
public final class BoxParser {
    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 4;
    private static final int SAMPLE_RATE_AMR_NB = 8000;
    private static final int SAMPLE_RATE_AMR_WB = 16000;
    private static final String TAG = "BoxParsers";
    private static final int TYPE_clcp = 1668047728;
    private static final int TYPE_mdta = 1835299937;
    private static final int TYPE_meta = 1835365473;
    private static final int TYPE_nclc = 1852009571;
    private static final int TYPE_nclx = 1852009592;
    private static final int TYPE_sbtl = 1935832172;
    private static final int TYPE_soun = 1936684398;
    private static final int TYPE_subp = 1937072752;
    private static final int TYPE_subt = 1937072756;
    private static final int TYPE_text = 1952807028;
    private static final int TYPE_vide = 1986618469;
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    private interface SampleSizeBox {
        int getFixedSampleSize();

        int getSampleCount();

        int readNextSampleSize();
    }

    private static int getTrackTypeForHdlr(int i) {
        if (i == TYPE_soun) {
            return 1;
        }
        if (i == TYPE_vide) {
            return 2;
        }
        if (i == TYPE_text || i == TYPE_sbtl || i == TYPE_subt || i == TYPE_clcp || i == TYPE_subp) {
            return 3;
        }
        return i == 1835365473 ? 5 : -1;
    }

    public static int parseFullBoxFlags(int i) {
        return i & ViewCompat.MEASURED_SIZE_MASK;
    }

    public static int parseFullBoxVersion(int i) {
        return (i >> 24) & 255;
    }

    public static List<TrackSampleTable> parseTraks(Mp4Box.ContainerBox containerBox, GaplessInfoHolder gaplessInfoHolder, long j, DrmInitData drmInitData, boolean z, boolean z2, Function<Track, Track> function) throws ParserException {
        Track trackApply;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < containerBox.containerChildren.size(); i++) {
            Mp4Box.ContainerBox containerBox2 = containerBox.containerChildren.get(i);
            if (containerBox2.type == 1953653099 && (trackApply = function.apply(parseTrak(containerBox2, (Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_mvhd)), j, drmInitData, z, z2))) != null) {
                arrayList.add(parseStbl(trackApply, (Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox2.getContainerBoxOfType(Mp4Box.TYPE_mdia))).getContainerBoxOfType(Mp4Box.TYPE_minf))).getContainerBoxOfType(Mp4Box.TYPE_stbl)), gaplessInfoHolder));
            }
        }
        return arrayList;
    }

    public static Metadata parseUdta(Mp4Box.LeafBox leafBox) {
        ParsableByteArray parsableByteArray = leafBox.data;
        parsableByteArray.setPosition(8);
        Metadata metadata = new Metadata(new Metadata.Entry[0]);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int i = parsableByteArray.readInt();
            int i2 = parsableByteArray.readInt();
            if (i2 == 1835365473) {
                parsableByteArray.setPosition(position);
                metadata = metadata.copyWithAppendedEntriesFrom(parseUdtaMeta(parsableByteArray, position + i));
            } else if (i2 == 1936553057) {
                parsableByteArray.setPosition(position);
                metadata = metadata.copyWithAppendedEntriesFrom(SmtaAtomUtil.parseSmta(parsableByteArray, position + i));
            } else if (i2 == -1451722374) {
                metadata = metadata.copyWithAppendedEntriesFrom(parseXyz(parsableByteArray));
            }
            parsableByteArray.setPosition(position + i);
        }
        return metadata;
    }

    public static Mp4TimestampData parseMvhd(ParsableByteArray parsableByteArray) {
        long unsignedInt;
        long unsignedInt2;
        parsableByteArray.setPosition(8);
        if (parseFullBoxVersion(parsableByteArray.readInt()) == 0) {
            unsignedInt = parsableByteArray.readUnsignedInt();
            unsignedInt2 = parsableByteArray.readUnsignedInt();
        } else {
            unsignedInt = parsableByteArray.readLong();
            unsignedInt2 = parsableByteArray.readLong();
        }
        return new Mp4TimestampData(unsignedInt, unsignedInt2, parsableByteArray.readUnsignedInt());
    }

    public static Metadata parseMdtaFromMeta(Mp4Box.ContainerBox containerBox) {
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_hdlr);
        Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_keys);
        Mp4Box.LeafBox leafBoxOfType3 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_ilst);
        if (leafBoxOfType == null || leafBoxOfType2 == null || leafBoxOfType3 == null || parseHdlr(leafBoxOfType.data) != TYPE_mdta) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType2.data;
        parsableByteArray.setPosition(12);
        int i = parsableByteArray.readInt();
        String[] strArr = new String[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i2] = parsableByteArray.readString(i3 - 8);
        }
        ParsableByteArray parsableByteArray2 = leafBoxOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int position = parsableByteArray2.getPosition();
            int i4 = parsableByteArray2.readInt();
            int i5 = parsableByteArray2.readInt() - 1;
            if (i5 >= 0 && i5 < i) {
                MdtaMetadataEntry mdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, position + i4, strArr[i5]);
                if (mdtaMetadataEntryFromIlst != null) {
                    arrayList.add(mdtaMetadataEntryFromIlst);
                }
            } else {
                Log.w(TAG, "Skipped metadata with unknown key index: " + i5);
            }
            parsableByteArray2.setPosition(position + i4);
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    public static void maybeSkipRemainingMetaBoxHeaderBytes(ParsableByteArray parsableByteArray) {
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(4);
        if (parsableByteArray.readInt() != 1751411826) {
            position += 4;
        }
        parsableByteArray.setPosition(position);
    }

    public static Track parseTrak(Mp4Box.ContainerBox containerBox, Mp4Box.LeafBox leafBox, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long[] jArr;
        long[] jArr2;
        Format formatBuild;
        Metadata metadata;
        Mp4Box.ContainerBox containerBoxOfType;
        Pair<long[], long[]> edts;
        Mp4Box.ContainerBox containerBox2 = (Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox.getContainerBoxOfType(Mp4Box.TYPE_mdia));
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(Mp4Box.TYPE_hdlr))).data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        TkhdData tkhd = parseTkhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_tkhd))).data);
        long jScaleLargeTimestamp = C.TIME_UNSET;
        long j2 = j == C.TIME_UNSET ? tkhd.duration : j;
        long j3 = parseMvhd(leafBox.data).timescale;
        if (j2 != C.TIME_UNSET) {
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(j2, 1000000L, j3);
        }
        long j4 = jScaleLargeTimestamp;
        Mp4Box.ContainerBox containerBox3 = (Mp4Box.ContainerBox) Assertions.checkNotNull(((Mp4Box.ContainerBox) Assertions.checkNotNull(containerBox2.getContainerBoxOfType(Mp4Box.TYPE_minf))).getContainerBoxOfType(Mp4Box.TYPE_stbl));
        MdhdData mdhd = parseMdhd(((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox2.getLeafBoxOfType(Mp4Box.TYPE_mdhd))).data);
        Mp4Box.LeafBox leafBoxOfType = containerBox3.getLeafBoxOfType(Mp4Box.TYPE_stsd);
        if (leafBoxOfType == null) {
            throw ParserException.createForMalformedContainer("Malformed sample table (stbl) missing sample description (stsd)", null);
        }
        StsdData stsd = parseStsd(leafBoxOfType.data, tkhd, mdhd.language, drmInitData, z2);
        if (z || (containerBoxOfType = containerBox.getContainerBoxOfType(Mp4Box.TYPE_edts)) == null || (edts = parseEdts(containerBoxOfType)) == null) {
            jArr = null;
            jArr2 = null;
        } else {
            long[] jArr3 = (long[]) edts.first;
            jArr2 = (long[]) edts.second;
            jArr = jArr3;
        }
        if (stsd.format == null) {
            return null;
        }
        if (tkhd.alternateGroup != 0) {
            Mp4AlternateGroupData mp4AlternateGroupData = new Mp4AlternateGroupData(tkhd.alternateGroup);
            Format.Builder builderBuildUpon = stsd.format.buildUpon();
            if (stsd.format.metadata != null) {
                metadata = stsd.format.metadata.copyWithAppendedEntries(mp4AlternateGroupData);
            } else {
                metadata = new Metadata(mp4AlternateGroupData);
            }
            formatBuild = builderBuildUpon.setMetadata(metadata).build();
        } else {
            formatBuild = stsd.format;
        }
        return new Track(tkhd.id, trackTypeForHdlr, mdhd.timescale, j3, j4, mdhd.mediaDurationUs, formatBuild, stsd.requiredSampleTransformation, stsd.trackEncryptionBoxes, stsd.nalUnitLengthFieldLength, jArr, jArr2);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0264 A[DONT_INVERT, LOOP:11: B:100:0x0264->B:104:0x026e, LOOP_START, PHI: r25
      0x0264: PHI (r25v2 int) = (r25v1 int), (r25v3 int) binds: [B:99:0x0262, B:104:0x026e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:101:0x0266  */
    /* JADX WARN: Code duplicated, block: B:104:0x026e A[LOOP:11: B:100:0x0264->B:104:0x026e, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:105:0x0274 A[EDGE_INSN: B:105:0x0274->B:106:0x0275 BREAK  A[LOOP:11: B:100:0x0264->B:104:0x026e]] */
    /* JADX WARN: Code duplicated, block: B:115:0x0287  */
    /* JADX WARN: Code duplicated, block: B:118:0x02c5  */
    /* JADX WARN: Code duplicated, block: B:119:0x02c8  */
    /* JADX WARN: Code duplicated, block: B:124:0x02e6  */
    /* JADX WARN: Code duplicated, block: B:131:0x0329  */
    /* JADX WARN: Code duplicated, block: B:133:0x0344  */
    /* JADX WARN: Code duplicated, block: B:141:0x037c  */
    /* JADX WARN: Code duplicated, block: B:163:0x043e  */
    /* JADX WARN: Code duplicated, block: B:164:0x0440  */
    /* JADX WARN: Code duplicated, block: B:168:0x045c  */
    /* JADX WARN: Code duplicated, block: B:170:0x0464  */
    /* JADX WARN: Code duplicated, block: B:177:0x04a5  */
    /* JADX WARN: Code duplicated, block: B:194:0x04e3  */
    /* JADX WARN: Code duplicated, block: B:195:0x04e5  */
    /* JADX WARN: Code duplicated, block: B:197:0x04ed  */
    /* JADX WARN: Code duplicated, block: B:201:0x050d  */
    /* JADX WARN: Code duplicated, block: B:202:0x050f  */
    /* JADX WARN: Code duplicated, block: B:205:0x0514  */
    /* JADX WARN: Code duplicated, block: B:206:0x0517  */
    /* JADX WARN: Code duplicated, block: B:208:0x051a  */
    /* JADX WARN: Code duplicated, block: B:209:0x051d  */
    /* JADX WARN: Code duplicated, block: B:211:0x0520  */
    /* JADX WARN: Code duplicated, block: B:213:0x0523  */
    /* JADX WARN: Code duplicated, block: B:214:0x0526  */
    /* JADX WARN: Code duplicated, block: B:218:0x0538  */
    /* JADX WARN: Code duplicated, block: B:220:0x0546  */
    /* JADX WARN: Code duplicated, block: B:223:0x0555  */
    /* JADX WARN: Code duplicated, block: B:225:0x057f  */
    /* JADX WARN: Code duplicated, block: B:235:0x05c6  */
    /* JADX WARN: Code duplicated, block: B:256:0x01a9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:257:0x0253 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:261:0x01a1 A[EDGE_INSN: B:261:0x01a1->B:69:0x01a1 BREAK  A[LOOP:9: B:65:0x0184->B:68:0x018c], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:264:0x0274 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:265:0x026c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:53:0x0121  */
    /* JADX WARN: Code duplicated, block: B:55:0x0124  */
    /* JADX WARN: Code duplicated, block: B:58:0x0132 A[LOOP:0: B:56:0x012c->B:58:0x0132, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:61:0x015a  */
    /* JADX WARN: Code duplicated, block: B:64:0x017e  */
    /* JADX WARN: Code duplicated, block: B:66:0x0186  */
    /* JADX WARN: Code duplicated, block: B:68:0x018c A[LOOP:9: B:65:0x0184->B:68:0x018c, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:72:0x01c9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x01cb A[ADDED_TO_REGION, LOOP:10: B:73:0x01cb->B:75:0x01cf, LOOP_START, PHI: r22 r23 r25
      0x01cb: PHI (r22v4 int) = (r22v1 int), (r22v5 int) binds: [B:72:0x01c9, B:75:0x01cf] A[DONT_GENERATE, DONT_INLINE]
      0x01cb: PHI (r23v3 int) = (r23v1 int), (r23v5 int) binds: [B:72:0x01c9, B:75:0x01cf] A[DONT_GENERATE, DONT_INLINE]
      0x01cb: PHI (r25v5 int) = (r25v1 int), (r25v6 int) binds: [B:72:0x01c9, B:75:0x01cf] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:79:0x01ee  */
    /* JADX WARN: Code duplicated, block: B:82:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:83:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:86:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:88:0x0204  */
    /* JADX WARN: Code duplicated, block: B:90:0x0214  */
    /* JADX WARN: Code duplicated, block: B:95:0x022f  */
    public static TrackSampleTable parseStbl(Track track, Mp4Box.ContainerBox containerBox, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox stz2SampleSizeBox;
        boolean z;
        int unsignedIntToInt;
        int unsignedIntToInt2;
        int unsignedIntToInt3;
        int fixedSampleSize;
        boolean z2;
        long[] jArr;
        int[] iArr;
        long[] jArr2;
        int[] iArr2;
        int i;
        int unsignedIntToInt4;
        int i2;
        int i3;
        int i4;
        int i5;
        int unsignedIntToInt5;
        long j;
        long j2;
        long j3;
        int i6;
        int i7;
        long[] jArr3;
        int[] iArr3;
        long[] jArrCopyOf;
        int[] iArrCopyOf;
        int i8;
        boolean z3;
        int i9;
        Track trackCopyWithFormat;
        String str;
        int[] iArr4;
        long[] jArr4;
        long j4;
        long j5;
        long j6;
        int i10;
        boolean zMoveNext;
        int i11;
        int i12;
        int nextSampleSize;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int[] iArr5;
        long jScaleLargeTimestamp;
        boolean z4;
        int[] iArr6;
        int[] iArr7;
        long[] jArr5;
        int i18;
        boolean z5;
        int i19;
        int i20;
        int[] iArr8;
        int[] iArr9;
        boolean z6;
        boolean z7;
        long[] jArr6;
        int[] iArr10;
        int[] iArr11;
        long[] jArr7;
        int i21;
        int i22;
        int i23;
        long j7;
        boolean z8;
        long j8;
        int i24;
        int i25;
        long jScaleLargeTimestamp2;
        long j9;
        boolean z9;
        int[] iArr12;
        int i26;
        int i27;
        int i28;
        boolean z10;
        int i29;
        int i30;
        long j10;
        long jScaleLargeTimestamp3;
        long jScaleLargeTimestamp4;
        long jScaleLargeTimestamp5;
        long jScaleLargeValue;
        long[] jArr8;
        int[] iArr13;
        Track trackCopyWithFormat2 = track;
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_stsz);
        if (leafBoxOfType != null) {
            stz2SampleSizeBox = new StszSampleSizeBox(leafBoxOfType, trackCopyWithFormat2.format);
        } else {
            Mp4Box.LeafBox leafBoxOfType2 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_stz2);
            if (leafBoxOfType2 == null) {
                throw ParserException.createForMalformedContainer("Track has no sample table size information", null);
            }
            stz2SampleSizeBox = new Stz2SampleSizeBox(leafBoxOfType2);
        }
        int sampleCount = stz2SampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(track, new long[0], new int[0], 0, new long[0], new int[0], 0L);
        }
        if (trackCopyWithFormat2.type == 2 && trackCopyWithFormat2.mediaDurationUs > 0) {
            trackCopyWithFormat2 = trackCopyWithFormat2.copyWithFormat(trackCopyWithFormat2.format.buildUpon().setFrameRate(sampleCount / (trackCopyWithFormat2.mediaDurationUs / 1000000.0f)).build());
        }
        Mp4Box.LeafBox leafBoxOfType3 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_stco);
        if (leafBoxOfType3 == null) {
            leafBoxOfType3 = (Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_co64));
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType3.data;
        ParsableByteArray parsableByteArray2 = ((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_stsc))).data;
        ParsableByteArray parsableByteArray3 = ((Mp4Box.LeafBox) Assertions.checkNotNull(containerBox.getLeafBoxOfType(Mp4Box.TYPE_stts))).data;
        Mp4Box.LeafBox leafBoxOfType4 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_stss);
        ParsableByteArray parsableByteArray4 = leafBoxOfType4 != null ? leafBoxOfType4.data : null;
        Mp4Box.LeafBox leafBoxOfType5 = containerBox.getLeafBoxOfType(Mp4Box.TYPE_ctts);
        ParsableByteArray parsableByteArray5 = leafBoxOfType5 != null ? leafBoxOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z);
        parsableByteArray3.setPosition(12);
        int unsignedIntToInt6 = parsableByteArray3.readUnsignedIntToInt() - 1;
        int unsignedIntToInt7 = parsableByteArray3.readUnsignedIntToInt();
        int unsignedIntToInt8 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            unsignedIntToInt = parsableByteArray5.readUnsignedIntToInt();
        } else {
            unsignedIntToInt = 0;
        }
        if (parsableByteArray4 != null) {
            parsableByteArray4.setPosition(12);
            unsignedIntToInt2 = parsableByteArray4.readUnsignedIntToInt();
            if (unsignedIntToInt2 > 0) {
                unsignedIntToInt3 = parsableByteArray4.readUnsignedIntToInt() - 1;
            } else {
                parsableByteArray4 = null;
            }
            fixedSampleSize = stz2SampleSizeBox.getFixedSampleSize();
            String str2 = trackCopyWithFormat2.format.sampleMimeType;
            if (fixedSampleSize == -1 && (("audio/raw".equals(str2) || MimeTypes.AUDIO_MLAW.equals(str2) || MimeTypes.AUDIO_ALAW.equals(str2)) && unsignedIntToInt6 == 0 && unsignedIntToInt == 0 && unsignedIntToInt2 == 0)) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                jArr8 = new long[chunkIterator.length];
                iArr13 = new int[chunkIterator.length];
                while (chunkIterator.moveNext()) {
                    jArr8[chunkIterator.index] = chunkIterator.offset;
                    iArr13[chunkIterator.index] = chunkIterator.numSamples;
                }
                FixedSampleSizeRechunker.Results resultsRechunk = FixedSampleSizeRechunker.rechunk(fixedSampleSize, jArr8, iArr13, unsignedIntToInt8);
                jArr3 = resultsRechunk.offsets;
                int[] iArr14 = resultsRechunk.sizes;
                int i31 = resultsRechunk.maximumSize;
                long[] jArr9 = resultsRechunk.timestamps;
                iArrCopyOf = resultsRechunk.flags;
                long j11 = resultsRechunk.duration;
                j5 = resultsRechunk.totalSize;
                trackCopyWithFormat = trackCopyWithFormat2;
                iArr4 = iArr14;
                i3 = i31;
                jArr4 = jArr9;
                j4 = j11;
            } else {
                jArr = new long[sampleCount];
                iArr = new int[sampleCount];
                jArr2 = new long[sampleCount];
                iArr2 = new int[sampleCount];
                i = unsignedIntToInt6;
                unsignedIntToInt4 = unsignedIntToInt3;
                i2 = unsignedIntToInt;
                i3 = 0;
                i4 = 0;
                i5 = 0;
                unsignedIntToInt5 = 0;
                j = 0;
                j2 = 0;
                j3 = 0;
                Track track2 = trackCopyWithFormat2;
                i6 = 0;
                while (true) {
                    if (i6 < sampleCount) {
                        i7 = unsignedIntToInt2;
                        jArr3 = jArr;
                        iArr3 = iArr;
                        jArrCopyOf = jArr2;
                        iArrCopyOf = iArr2;
                        i8 = i4;
                        break;
                    }
                    j6 = j3;
                    i10 = i4;
                    zMoveNext = true;
                    while (i10 == 0) {
                        zMoveNext = chunkIterator.moveNext();
                        if (zMoveNext) {
                            break;
                        }
                        int i32 = unsignedIntToInt8;
                        long j12 = chunkIterator.offset;
                        i10 = chunkIterator.numSamples;
                        j6 = j12;
                        unsignedIntToInt8 = i32;
                        unsignedIntToInt2 = unsignedIntToInt2;
                        sampleCount = sampleCount;
                    }
                    i11 = sampleCount;
                    i12 = unsignedIntToInt8;
                    i7 = unsignedIntToInt2;
                    if (!zMoveNext) {
                        Log.w(TAG, "Unexpected end of chunk data");
                        long[] jArrCopyOf2 = Arrays.copyOf(jArr, i6);
                        int[] iArrCopyOf2 = Arrays.copyOf(iArr, i6);
                        jArrCopyOf = Arrays.copyOf(jArr2, i6);
                        iArrCopyOf = Arrays.copyOf(iArr2, i6);
                        jArr3 = jArrCopyOf2;
                        iArr3 = iArrCopyOf2;
                        sampleCount = i6;
                        i8 = i10;
                        break;
                    }
                    if (parsableByteArray5 != null) {
                        while (unsignedIntToInt5 == 0 && i2 > 0) {
                            unsignedIntToInt5 = parsableByteArray5.readUnsignedIntToInt();
                            i5 = parsableByteArray5.readInt();
                            i2--;
                        }
                        unsignedIntToInt5--;
                    }
                    int i33 = i5;
                    jArr[i6] = j6;
                    nextSampleSize = stz2SampleSizeBox.readNextSampleSize();
                    iArr[i6] = nextSampleSize;
                    ChunkIterator chunkIterator2 = chunkIterator;
                    SampleSizeBox sampleSizeBox = stz2SampleSizeBox;
                    j2 += (long) nextSampleSize;
                    if (nextSampleSize > i3) {
                        i3 = nextSampleSize;
                    }
                    jArr2[i6] = j + ((long) i33);
                    if (parsableByteArray4 == null) {
                        i13 = 1;
                    } else {
                        i13 = 0;
                    }
                    iArr2[i6] = i13;
                    if (i6 == unsignedIntToInt4) {
                        iArr2[i6] = 1;
                        i15 = i7 - 1;
                        if (i15 > 0) {
                            unsignedIntToInt4 = ((ParsableByteArray) Assertions.checkNotNull(parsableByteArray4)).readUnsignedIntToInt() - 1;
                        }
                        i14 = unsignedIntToInt4;
                    } else {
                        i14 = unsignedIntToInt4;
                        i15 = i7;
                    }
                    j += (long) i12;
                    unsignedIntToInt7--;
                    if (unsignedIntToInt7 == 0 || i <= 0) {
                        i16 = i12;
                        i17 = i;
                    } else {
                        int unsignedIntToInt9 = parsableByteArray3.readUnsignedIntToInt();
                        i16 = parsableByteArray3.readInt();
                        i17 = i - 1;
                        unsignedIntToInt7 = unsignedIntToInt9;
                    }
                    int i34 = i16;
                    long j13 = j6 + ((long) iArr[i6]);
                    i4 = i10 - 1;
                    i6++;
                    j3 = j13;
                    i5 = i33;
                    unsignedIntToInt4 = i14;
                    sampleCount = i11;
                    chunkIterator = chunkIterator2;
                    int i35 = i17;
                    unsignedIntToInt8 = i34;
                    i = i35;
                    unsignedIntToInt2 = i15;
                    stz2SampleSizeBox = sampleSizeBox;
                }
                long j14 = j + ((long) i5);
                if (parsableByteArray5 != null) {
                    z3 = true;
                    break;
                }
                while (true) {
                    if (i2 > 0) {
                        z3 = true;
                        break;
                    }
                    if (parsableByteArray5.readUnsignedIntToInt() != 0) {
                        z3 = false;
                        break;
                    }
                    parsableByteArray5.readInt();
                    i2--;
                }
                if (i7 != 0 && unsignedIntToInt7 == 0 && i8 == 0 && i == 0) {
                    i9 = unsignedIntToInt5;
                    if (i9 == 0 && z3) {
                        trackCopyWithFormat = track2;
                    }
                    iArr4 = iArr3;
                    jArr4 = jArrCopyOf;
                    j4 = j14;
                    j5 = j2;
                } else {
                    i9 = unsignedIntToInt5;
                }
                StringBuilder sb = new StringBuilder("Inconsistent stbl box for track ");
                trackCopyWithFormat = track2;
                sb.append(trackCopyWithFormat.id);
                sb.append(": remainingSynchronizationSamples ");
                sb.append(i7);
                sb.append(", remainingSamplesAtTimestampDelta ");
                sb.append(unsignedIntToInt7);
                sb.append(", remainingSamplesInChunk ");
                sb.append(i8);
                sb.append(", remainingTimestampDeltaChanges ");
                sb.append(i);
                sb.append(", remainingSamplesAtTimestampOffset ");
                sb.append(i9);
                if (z3) {
                    str = "";
                } else {
                    str = ", ctts invalid";
                }
                sb.append(str);
                Log.w(TAG, sb.toString());
                iArr4 = iArr3;
                jArr4 = jArrCopyOf;
                j4 = j14;
                j5 = j2;
            }
            int[] iArr15 = iArrCopyOf;
            if (trackCopyWithFormat.mediaDurationUs > 0) {
                jScaleLargeValue = Util.scaleLargeValue(j5 * 8, 1000000L, trackCopyWithFormat.mediaDurationUs, RoundingMode.HALF_DOWN);
                if (jScaleLargeValue > 0 && jScaleLargeValue < 2147483647L) {
                    trackCopyWithFormat = trackCopyWithFormat.copyWithFormat(trackCopyWithFormat.format.buildUpon().setAverageBitrate((int) jScaleLargeValue).build());
                }
            }
            iArr5 = iArr15;
            jScaleLargeTimestamp = Util.scaleLargeTimestamp(j4, 1000000L, trackCopyWithFormat.timescale);
            if (trackCopyWithFormat.editListDurations == null) {
                Util.scaleLargeTimestampsInPlace(jArr4, 1000000L, trackCopyWithFormat.timescale);
                return new TrackSampleTable(trackCopyWithFormat, jArr3, iArr4, i3, jArr4, iArr5, jScaleLargeTimestamp);
            }
            if (trackCopyWithFormat.editListDurations.length == 1 && trackCopyWithFormat.type == 1 && jArr4.length >= 2) {
                j10 = ((long[]) Assertions.checkNotNull(trackCopyWithFormat.editListMediaTimes))[0];
                jScaleLargeTimestamp3 = Util.scaleLargeTimestamp(trackCopyWithFormat.editListDurations[0], trackCopyWithFormat.timescale, trackCopyWithFormat.movieTimescale) + j10;
                if (canApplyEditWithGaplessInfo(jArr4, j4, j10, jScaleLargeTimestamp3)) {
                    long j15 = j4 - jScaleLargeTimestamp3;
                    jScaleLargeTimestamp4 = Util.scaleLargeTimestamp(j10 - jArr4[0], trackCopyWithFormat.format.sampleRate, trackCopyWithFormat.timescale);
                    jScaleLargeTimestamp5 = Util.scaleLargeTimestamp(j15, trackCopyWithFormat.format.sampleRate, trackCopyWithFormat.timescale);
                    if ((jScaleLargeTimestamp4 == 0 || jScaleLargeTimestamp5 != 0) && jScaleLargeTimestamp4 <= 2147483647L && jScaleLargeTimestamp5 <= 2147483647L) {
                        gaplessInfoHolder.encoderDelay = (int) jScaleLargeTimestamp4;
                        gaplessInfoHolder.encoderPadding = (int) jScaleLargeTimestamp5;
                        Util.scaleLargeTimestampsInPlace(jArr4, 1000000L, trackCopyWithFormat.timescale);
                        return new TrackSampleTable(trackCopyWithFormat, jArr3, iArr4, i3, jArr4, iArr5, Util.scaleLargeTimestamp(trackCopyWithFormat.editListDurations[0], 1000000L, trackCopyWithFormat.movieTimescale));
                    }
                }
            }
            if (trackCopyWithFormat.editListDurations.length != 1 && trackCopyWithFormat.editListDurations[0] == 0) {
                long j16 = ((long[]) Assertions.checkNotNull(trackCopyWithFormat.editListMediaTimes))[0];
                for (int i36 = 0; i36 < jArr4.length; i36++) {
                    jArr4[i36] = Util.scaleLargeTimestamp(jArr4[i36] - j16, 1000000L, trackCopyWithFormat.timescale);
                }
                return new TrackSampleTable(trackCopyWithFormat, jArr3, iArr4, i3, jArr4, iArr5, Util.scaleLargeTimestamp(j4 - j16, 1000000L, trackCopyWithFormat.timescale));
            }
            if (trackCopyWithFormat.type == 1) {
                z4 = true;
            } else {
                z4 = false;
            }
            iArr6 = new int[trackCopyWithFormat.editListDurations.length];
            iArr7 = new int[trackCopyWithFormat.editListDurations.length];
            jArr5 = (long[]) Assertions.checkNotNull(trackCopyWithFormat.editListMediaTimes);
            i18 = 0;
            z5 = false;
            i19 = 0;
            i20 = 0;
            while (i18 < trackCopyWithFormat.editListDurations.length) {
                j9 = jArr5[i18];
                if (j9 != -1) {
                    boolean z11 = z5;
                    int i37 = i19;
                    long jScaleLargeTimestamp6 = Util.scaleLargeTimestamp(trackCopyWithFormat.editListDurations[i18], trackCopyWithFormat.timescale, trackCopyWithFormat.movieTimescale);
                    iArr6[i18] = Util.binarySearchFloor(jArr4, j9, true, true);
                    long j17 = j9 + jScaleLargeTimestamp6;
                    iArr7[i18] = Util.binarySearchCeil(jArr4, j17, z4, false);
                    i26 = iArr6[i18];
                    while (true) {
                        i27 = iArr6[i18];
                        iArr12 = iArr5;
                        if (i27 < 0 || (iArr12[i27] & 1) != 0) {
                            break;
                        }
                        iArr6[i18] = i27 - 1;
                        iArr5 = iArr12;
                    }
                    if (i27 < 0) {
                        iArr6[i18] = i26;
                        while (true) {
                            i30 = iArr6[i18];
                            if (i30 >= iArr7[i18] || (iArr12[i30] & 1) != 0) {
                                break;
                            }
                            iArr6[i18] = i30 + 1;
                        }
                    }
                    if (trackCopyWithFormat.type == 2 && iArr6[i18] != iArr7[i18]) {
                        while (true) {
                            i29 = iArr7[i18];
                            if (i29 >= jArr4.length - 1 || jArr4[i29 + 1] > j17) {
                                break;
                            }
                            iArr7[i18] = i29 + 1;
                        }
                    }
                    int i38 = iArr7[i18];
                    i28 = iArr6[i18];
                    int i39 = i37 + (i38 - i28);
                    if (i20 != i28) {
                        z10 = true;
                    } else {
                        z10 = false;
                    }
                    i20 = i38;
                    z9 = z11 | z10;
                    i19 = i39;
                } else {
                    z9 = z5;
                    iArr12 = iArr5;
                }
                i18++;
                iArr5 = iArr12;
                iArr4 = iArr4;
                sampleCount = sampleCount;
                z5 = z9;
                jArr5 = jArr5;
            }
            iArr8 = iArr4;
            boolean z12 = z5;
            iArr9 = iArr5;
            if (i19 != sampleCount) {
                z6 = true;
            } else {
                z6 = false;
            }
            z7 = z12 | z6;
            if (z7) {
                jArr6 = new long[i19];
            } else {
                jArr6 = jArr3;
            }
            if (z7) {
                iArr10 = new int[i19];
            } else {
                iArr10 = iArr8;
            }
            if (z7) {
                i3 = 0;
            }
            if (z7) {
                iArr11 = new int[i19];
            } else {
                iArr11 = iArr9;
            }
            jArr7 = new long[i19];
            i21 = i3;
            i22 = 0;
            i23 = 0;
            j7 = 0;
            z8 = false;
            while (i22 < trackCopyWithFormat.editListDurations.length) {
                j8 = trackCopyWithFormat.editListMediaTimes[i22];
                i24 = iArr6[i22];
                int[] iArr16 = iArr6;
                i25 = iArr7[i22];
                int[] iArr17 = iArr7;
                if (z7) {
                    int i40 = i25 - i24;
                    System.arraycopy(jArr3, i24, jArr6, i23, i40);
                    System.arraycopy(iArr8, i24, iArr10, i23, i40);
                    System.arraycopy(iArr9, i24, iArr11, i23, i40);
                }
                int i41 = i21;
                while (i24 < i25) {
                    int i42 = i25;
                    int[] iArr18 = iArr9;
                    long jScaleLargeTimestamp7 = Util.scaleLargeTimestamp(j7, 1000000L, trackCopyWithFormat.movieTimescale);
                    long[] jArr10 = jArr6;
                    long[] jArr11 = jArr3;
                    jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(jArr4[i24] - j8, 1000000L, trackCopyWithFormat.timescale);
                    if (jScaleLargeTimestamp2 < 0) {
                        z8 = true;
                    }
                    jArr7[i23] = jScaleLargeTimestamp7 + jScaleLargeTimestamp2;
                    if (!z7 && iArr10[i23] > i41) {
                        i41 = iArr8[i24];
                    }
                    i23++;
                    i24++;
                    iArr9 = iArr18;
                    i25 = i42;
                    jArr3 = jArr11;
                    jArr6 = jArr10;
                }
                j7 += trackCopyWithFormat.editListDurations[i22];
                i22++;
                iArr9 = iArr9;
                i21 = i41;
                iArr6 = iArr16;
                iArr7 = iArr17;
                jArr6 = jArr6;
            }
            long[] jArr12 = jArr6;
            long jScaleLargeTimestamp8 = Util.scaleLargeTimestamp(j7, 1000000L, trackCopyWithFormat.movieTimescale);
            if (z8) {
                trackCopyWithFormat = trackCopyWithFormat.copyWithFormat(trackCopyWithFormat.format.buildUpon().setHasPrerollSamples(true).build());
            }
            return new TrackSampleTable(trackCopyWithFormat, jArr12, iArr10, i21, jArr7, iArr11, jScaleLargeTimestamp8);
        }
        unsignedIntToInt2 = 0;
        unsignedIntToInt3 = -1;
        fixedSampleSize = stz2SampleSizeBox.getFixedSampleSize();
        String str3 = trackCopyWithFormat2.format.sampleMimeType;
        if (fixedSampleSize == -1) {
            z2 = false;
        } else {
            z2 = false;
        }
        if (z2) {
            jArr8 = new long[chunkIterator.length];
            iArr13 = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr8[chunkIterator.index] = chunkIterator.offset;
                iArr13[chunkIterator.index] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results resultsRechunk2 = FixedSampleSizeRechunker.rechunk(fixedSampleSize, jArr8, iArr13, unsignedIntToInt8);
            jArr3 = resultsRechunk2.offsets;
            int[] iArr19 = resultsRechunk2.sizes;
            int i310 = resultsRechunk2.maximumSize;
            long[] jArr13 = resultsRechunk2.timestamps;
            iArrCopyOf = resultsRechunk2.flags;
            long j18 = resultsRechunk2.duration;
            j5 = resultsRechunk2.totalSize;
            trackCopyWithFormat = trackCopyWithFormat2;
            iArr4 = iArr19;
            i3 = i310;
            jArr4 = jArr13;
            j4 = j18;
        } else {
            jArr = new long[sampleCount];
            iArr = new int[sampleCount];
            jArr2 = new long[sampleCount];
            iArr2 = new int[sampleCount];
            i = unsignedIntToInt6;
            unsignedIntToInt4 = unsignedIntToInt3;
            i2 = unsignedIntToInt;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            unsignedIntToInt5 = 0;
            j = 0;
            j2 = 0;
            j3 = 0;
            Track track3 = trackCopyWithFormat2;
            i6 = 0;
            while (true) {
                if (i6 < sampleCount) {
                    i7 = unsignedIntToInt2;
                    jArr3 = jArr;
                    iArr3 = iArr;
                    jArrCopyOf = jArr2;
                    iArrCopyOf = iArr2;
                    i8 = i4;
                    break;
                }
                j6 = j3;
                i10 = i4;
                zMoveNext = true;
                while (i10 == 0) {
                    zMoveNext = chunkIterator.moveNext();
                    if (zMoveNext) {
                        break;
                        break;
                    }
                    int i311 = unsignedIntToInt8;
                    long j19 = chunkIterator.offset;
                    i10 = chunkIterator.numSamples;
                    j6 = j19;
                    unsignedIntToInt8 = i311;
                    unsignedIntToInt2 = unsignedIntToInt2;
                    sampleCount = sampleCount;
                }
                i11 = sampleCount;
                i12 = unsignedIntToInt8;
                i7 = unsignedIntToInt2;
                if (!zMoveNext) {
                    Log.w(TAG, "Unexpected end of chunk data");
                    long[] jArrCopyOf3 = Arrays.copyOf(jArr, i6);
                    int[] iArrCopyOf3 = Arrays.copyOf(iArr, i6);
                    jArrCopyOf = Arrays.copyOf(jArr2, i6);
                    iArrCopyOf = Arrays.copyOf(iArr2, i6);
                    jArr3 = jArrCopyOf3;
                    iArr3 = iArrCopyOf3;
                    sampleCount = i6;
                    i8 = i10;
                    break;
                }
                if (parsableByteArray5 != null) {
                    while (unsignedIntToInt5 == 0) {
                        unsignedIntToInt5 = parsableByteArray5.readUnsignedIntToInt();
                        i5 = parsableByteArray5.readInt();
                        i2--;
                    }
                    unsignedIntToInt5--;
                }
                int i312 = i5;
                jArr[i6] = j6;
                nextSampleSize = stz2SampleSizeBox.readNextSampleSize();
                iArr[i6] = nextSampleSize;
                ChunkIterator chunkIterator3 = chunkIterator;
                SampleSizeBox sampleSizeBox2 = stz2SampleSizeBox;
                j2 += (long) nextSampleSize;
                if (nextSampleSize > i3) {
                    i3 = nextSampleSize;
                }
                jArr2[i6] = j + ((long) i312);
                if (parsableByteArray4 == null) {
                    i13 = 1;
                } else {
                    i13 = 0;
                }
                iArr2[i6] = i13;
                if (i6 == unsignedIntToInt4) {
                    iArr2[i6] = 1;
                    i15 = i7 - 1;
                    if (i15 > 0) {
                        unsignedIntToInt4 = ((ParsableByteArray) Assertions.checkNotNull(parsableByteArray4)).readUnsignedIntToInt() - 1;
                    }
                    i14 = unsignedIntToInt4;
                } else {
                    i14 = unsignedIntToInt4;
                    i15 = i7;
                }
                j += (long) i12;
                unsignedIntToInt7--;
                if (unsignedIntToInt7 == 0) {
                    i16 = i12;
                    i17 = i;
                } else {
                    i16 = i12;
                    i17 = i;
                }
                int i313 = i16;
                long j110 = j6 + ((long) iArr[i6]);
                i4 = i10 - 1;
                i6++;
                j3 = j110;
                i5 = i312;
                unsignedIntToInt4 = i14;
                sampleCount = i11;
                chunkIterator = chunkIterator3;
                int i314 = i17;
                unsignedIntToInt8 = i313;
                i = i314;
                unsignedIntToInt2 = i15;
                stz2SampleSizeBox = sampleSizeBox2;
            }
            long j111 = j + ((long) i5);
            if (parsableByteArray5 != null) {
                z3 = true;
                break;
            }
            while (true) {
                if (i2 > 0) {
                    z3 = true;
                    break;
                }
                if (parsableByteArray5.readUnsignedIntToInt() != 0) {
                    z3 = false;
                    break;
                }
                parsableByteArray5.readInt();
                i2--;
            }
            if (i7 != 0) {
                i9 = unsignedIntToInt5;
                StringBuilder sb2 = new StringBuilder("Inconsistent stbl box for track ");
                trackCopyWithFormat = track3;
                sb2.append(trackCopyWithFormat.id);
                sb2.append(": remainingSynchronizationSamples ");
                sb2.append(i7);
                sb2.append(", remainingSamplesAtTimestampDelta ");
                sb2.append(unsignedIntToInt7);
                sb2.append(", remainingSamplesInChunk ");
                sb2.append(i8);
                sb2.append(", remainingTimestampDeltaChanges ");
                sb2.append(i);
                sb2.append(", remainingSamplesAtTimestampOffset ");
                sb2.append(i9);
                if (z3) {
                    str = ", ctts invalid";
                } else {
                    str = "";
                }
                sb2.append(str);
                Log.w(TAG, sb2.toString());
            } else {
                i9 = unsignedIntToInt5;
                StringBuilder sb3 = new StringBuilder("Inconsistent stbl box for track ");
                trackCopyWithFormat = track3;
                sb3.append(trackCopyWithFormat.id);
                sb3.append(": remainingSynchronizationSamples ");
                sb3.append(i7);
                sb3.append(", remainingSamplesAtTimestampDelta ");
                sb3.append(unsignedIntToInt7);
                sb3.append(", remainingSamplesInChunk ");
                sb3.append(i8);
                sb3.append(", remainingTimestampDeltaChanges ");
                sb3.append(i);
                sb3.append(", remainingSamplesAtTimestampOffset ");
                sb3.append(i9);
                if (z3) {
                    str = ", ctts invalid";
                } else {
                    str = "";
                }
                sb3.append(str);
                Log.w(TAG, sb3.toString());
            }
            iArr4 = iArr3;
            jArr4 = jArrCopyOf;
            j4 = j111;
            j5 = j2;
        }
        int[] iArr110 = iArrCopyOf;
        if (trackCopyWithFormat.mediaDurationUs > 0) {
            jScaleLargeValue = Util.scaleLargeValue(j5 * 8, 1000000L, trackCopyWithFormat.mediaDurationUs, RoundingMode.HALF_DOWN);
            if (jScaleLargeValue > 0) {
                trackCopyWithFormat = trackCopyWithFormat.copyWithFormat(trackCopyWithFormat.format.buildUpon().setAverageBitrate((int) jScaleLargeValue).build());
            }
        }
        iArr5 = iArr110;
        jScaleLargeTimestamp = Util.scaleLargeTimestamp(j4, 1000000L, trackCopyWithFormat.timescale);
        if (trackCopyWithFormat.editListDurations == null) {
            Util.scaleLargeTimestampsInPlace(jArr4, 1000000L, trackCopyWithFormat.timescale);
            return new TrackSampleTable(trackCopyWithFormat, jArr3, iArr4, i3, jArr4, iArr5, jScaleLargeTimestamp);
        }
        if (trackCopyWithFormat.editListDurations.length == 1) {
            j10 = ((long[]) Assertions.checkNotNull(trackCopyWithFormat.editListMediaTimes))[0];
            jScaleLargeTimestamp3 = Util.scaleLargeTimestamp(trackCopyWithFormat.editListDurations[0], trackCopyWithFormat.timescale, trackCopyWithFormat.movieTimescale) + j10;
            if (canApplyEditWithGaplessInfo(jArr4, j4, j10, jScaleLargeTimestamp3)) {
                long j112 = j4 - jScaleLargeTimestamp3;
                jScaleLargeTimestamp4 = Util.scaleLargeTimestamp(j10 - jArr4[0], trackCopyWithFormat.format.sampleRate, trackCopyWithFormat.timescale);
                jScaleLargeTimestamp5 = Util.scaleLargeTimestamp(j112, trackCopyWithFormat.format.sampleRate, trackCopyWithFormat.timescale);
                if (jScaleLargeTimestamp4 == 0) {
                    gaplessInfoHolder.encoderDelay = (int) jScaleLargeTimestamp4;
                    gaplessInfoHolder.encoderPadding = (int) jScaleLargeTimestamp5;
                    Util.scaleLargeTimestampsInPlace(jArr4, 1000000L, trackCopyWithFormat.timescale);
                    return new TrackSampleTable(trackCopyWithFormat, jArr3, iArr4, i3, jArr4, iArr5, Util.scaleLargeTimestamp(trackCopyWithFormat.editListDurations[0], 1000000L, trackCopyWithFormat.movieTimescale));
                }
                gaplessInfoHolder.encoderDelay = (int) jScaleLargeTimestamp4;
                gaplessInfoHolder.encoderPadding = (int) jScaleLargeTimestamp5;
                Util.scaleLargeTimestampsInPlace(jArr4, 1000000L, trackCopyWithFormat.timescale);
                return new TrackSampleTable(trackCopyWithFormat, jArr3, iArr4, i3, jArr4, iArr5, Util.scaleLargeTimestamp(trackCopyWithFormat.editListDurations[0], 1000000L, trackCopyWithFormat.movieTimescale));
            }
        }
        if (trackCopyWithFormat.editListDurations.length != 1) {
        }
        if (trackCopyWithFormat.type == 1) {
            z4 = true;
        } else {
            z4 = false;
        }
        iArr6 = new int[trackCopyWithFormat.editListDurations.length];
        iArr7 = new int[trackCopyWithFormat.editListDurations.length];
        jArr5 = (long[]) Assertions.checkNotNull(trackCopyWithFormat.editListMediaTimes);
        i18 = 0;
        z5 = false;
        i19 = 0;
        i20 = 0;
        while (i18 < trackCopyWithFormat.editListDurations.length) {
            j9 = jArr5[i18];
            if (j9 != -1) {
                boolean z13 = z5;
                int i315 = i19;
                long jScaleLargeTimestamp9 = Util.scaleLargeTimestamp(trackCopyWithFormat.editListDurations[i18], trackCopyWithFormat.timescale, trackCopyWithFormat.movieTimescale);
                iArr6[i18] = Util.binarySearchFloor(jArr4, j9, true, true);
                long j113 = j9 + jScaleLargeTimestamp9;
                iArr7[i18] = Util.binarySearchCeil(jArr4, j113, z4, false);
                i26 = iArr6[i18];
                while (true) {
                    i27 = iArr6[i18];
                    iArr12 = iArr5;
                    if (i27 < 0) {
                        break;
                    }
                    break;
                    break;
                    iArr6[i18] = i27 - 1;
                    iArr5 = iArr12;
                }
                if (i27 < 0) {
                    iArr6[i18] = i26;
                    while (true) {
                        i30 = iArr6[i18];
                        if (i30 >= iArr7[i18]) {
                            break;
                        }
                        break;
                        break;
                        iArr6[i18] = i30 + 1;
                    }
                }
                if (trackCopyWithFormat.type == 2) {
                    while (true) {
                        i29 = iArr7[i18];
                        if (i29 >= jArr4.length - 1) {
                            break;
                        }
                        break;
                        break;
                        iArr7[i18] = i29 + 1;
                    }
                }
                int i316 = iArr7[i18];
                i28 = iArr6[i18];
                int i317 = i315 + (i316 - i28);
                if (i20 != i28) {
                    z10 = true;
                } else {
                    z10 = false;
                }
                i20 = i316;
                z9 = z13 | z10;
                i19 = i317;
            } else {
                z9 = z5;
                iArr12 = iArr5;
            }
            i18++;
            iArr5 = iArr12;
            iArr4 = iArr4;
            sampleCount = sampleCount;
            z5 = z9;
            jArr5 = jArr5;
        }
        iArr8 = iArr4;
        boolean z14 = z5;
        iArr9 = iArr5;
        if (i19 != sampleCount) {
            z6 = true;
        } else {
            z6 = false;
        }
        z7 = z14 | z6;
        if (z7) {
            jArr6 = new long[i19];
        } else {
            jArr6 = jArr3;
        }
        if (z7) {
            iArr10 = new int[i19];
        } else {
            iArr10 = iArr8;
        }
        if (z7) {
            i3 = 0;
        }
        if (z7) {
            iArr11 = new int[i19];
        } else {
            iArr11 = iArr9;
        }
        jArr7 = new long[i19];
        i21 = i3;
        i22 = 0;
        i23 = 0;
        j7 = 0;
        z8 = false;
        while (i22 < trackCopyWithFormat.editListDurations.length) {
            j8 = trackCopyWithFormat.editListMediaTimes[i22];
            i24 = iArr6[i22];
            int[] iArr111 = iArr6;
            i25 = iArr7[i22];
            int[] iArr112 = iArr7;
            if (z7) {
                int i43 = i25 - i24;
                System.arraycopy(jArr3, i24, jArr6, i23, i43);
                System.arraycopy(iArr8, i24, iArr10, i23, i43);
                System.arraycopy(iArr9, i24, iArr11, i23, i43);
            }
            int i44 = i21;
            while (i24 < i25) {
                int i45 = i25;
                int[] iArr113 = iArr9;
                long jScaleLargeTimestamp10 = Util.scaleLargeTimestamp(j7, 1000000L, trackCopyWithFormat.movieTimescale);
                long[] jArr14 = jArr6;
                long[] jArr15 = jArr3;
                jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(jArr4[i24] - j8, 1000000L, trackCopyWithFormat.timescale);
                if (jScaleLargeTimestamp2 < 0) {
                    z8 = true;
                }
                jArr7[i23] = jScaleLargeTimestamp10 + jScaleLargeTimestamp2;
                if (!z7) {
                }
                i23++;
                i24++;
                iArr9 = iArr113;
                i25 = i45;
                jArr3 = jArr15;
                jArr6 = jArr14;
            }
            j7 += trackCopyWithFormat.editListDurations[i22];
            i22++;
            iArr9 = iArr9;
            i21 = i44;
            iArr6 = iArr111;
            iArr7 = iArr112;
            jArr6 = jArr6;
        }
        long[] jArr16 = jArr6;
        long jScaleLargeTimestamp11 = Util.scaleLargeTimestamp(j7, 1000000L, trackCopyWithFormat.movieTimescale);
        if (z8) {
            trackCopyWithFormat = trackCopyWithFormat.copyWithFormat(trackCopyWithFormat.format.buildUpon().setHasPrerollSamples(true).build());
        }
        return new TrackSampleTable(trackCopyWithFormat, jArr16, iArr10, i21, jArr7, iArr11, jScaleLargeTimestamp11);
    }

    private static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        maybeSkipRemainingMetaBoxHeaderBytes(parsableByteArray);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int i2 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1768715124) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + i2);
            }
            parsableByteArray.setPosition(position + i2);
        }
        return null;
    }

    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Metadata.Entry ilstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (ilstElement != null) {
                arrayList.add(ilstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata(arrayList);
    }

    private static Metadata parseXyz(ParsableByteArray parsableByteArray) {
        short s = parsableByteArray.readShort();
        parsableByteArray.skipBytes(2);
        String string = parsableByteArray.readString(s);
        int iMax = Math.max(string.lastIndexOf(43), string.lastIndexOf(45));
        try {
            return new Metadata(new Mp4LocationData(Float.parseFloat(string.substring(0, iMax)), Float.parseFloat(string.substring(iMax, string.length() - 1))));
        } catch (IndexOutOfBoundsException | NumberFormatException unused) {
            return null;
        }
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        long j;
        int i;
        parsableByteArray.setPosition(8);
        int fullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullBoxVersion == 0 ? 8 : 16);
        int i2 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        int i3 = fullBoxVersion == 0 ? 4 : 8;
        int i4 = 0;
        while (true) {
            j = C.TIME_UNSET;
            if (i4 < i3) {
                if (parsableByteArray.getData()[position + i4] != -1) {
                    long unsignedInt = fullBoxVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
                    if (unsignedInt == 0) {
                        break;
                    }
                    j = unsignedInt;
                    break;
                }
                i4++;
            } else {
                parsableByteArray.skipBytes(i3);
                break;
            }
        }
        parsableByteArray.skipBytes(10);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(4);
        int i5 = parsableByteArray.readInt();
        int i6 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int i7 = parsableByteArray.readInt();
        int i8 = parsableByteArray.readInt();
        if (i5 == 0 && i6 == 65536 && ((i7 == -65536 || i7 == 65536) && i8 == 0)) {
            i = 90;
        } else if (i5 == 0 && i6 == -65536 && ((i7 == 65536 || i7 == -65536) && i8 == 0)) {
            i = MediaPlayer.Event.PausableChanged;
        } else {
            i = ((i5 == -65536 || i5 == 65536) && i6 == 0 && i7 == 0 && i8 == -65536) ? Opcodes.GETFIELD : 0;
        }
        parsableByteArray.skipBytes(16);
        short s = parsableByteArray.readShort();
        parsableByteArray.skipBytes(2);
        return new TkhdData(i2, j, unsignedShort, i, s, parsableByteArray.readShort());
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static MdhdData parseMdhd(ParsableByteArray parsableByteArray) {
        long jScaleLargeTimestamp;
        parsableByteArray.setPosition(8);
        int fullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullBoxVersion == 0 ? 8 : 16);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        int position = parsableByteArray.getPosition();
        int i = fullBoxVersion == 0 ? 4 : 8;
        int i2 = 0;
        while (true) {
            if (i2 < i) {
                if (parsableByteArray.getData()[position + i2] != -1) {
                    long unsignedInt2 = fullBoxVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
                    if (unsignedInt2 == 0) {
                        break;
                    }
                    jScaleLargeTimestamp = Util.scaleLargeTimestamp(unsignedInt2, 1000000L, unsignedInt);
                    return new MdhdData(unsignedInt, jScaleLargeTimestamp, getLanguageFromCode(parsableByteArray.readUnsignedShort()));
                }
                i2++;
            } else {
                parsableByteArray.skipBytes(i);
                break;
            }
        }
        jScaleLargeTimestamp = -9223372036854775807L;
        return new MdhdData(unsignedInt, jScaleLargeTimestamp, getLanguageFromCode(parsableByteArray.readUnsignedShort()));
    }

    private static String getLanguageFromCode(int i) {
        char[] cArr = {(char) (((i >> 10) & 31) + 96), (char) (((i >> 5) & 31) + 96), (char) ((i & 31) + 96)};
        for (int i2 = 0; i2 < 3; i2++) {
            char c = cArr[i2];
            if (c < 'a' || c > 'z') {
                return null;
            }
        }
        return new String(cArr);
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, TkhdData tkhdData, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        parsableByteArray.setPosition(12);
        int i = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(i);
        for (int i2 = 0; i2 < i; i2++) {
            int position = parsableByteArray.getPosition();
            int i3 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i3 > 0, "childAtomSize must be positive");
            int i4 = parsableByteArray.readInt();
            if (i4 == 1635148593 || i4 == 1635148595 || i4 == 1701733238 || i4 == 1831958048 || i4 == 1836070006 || i4 == 1752589105 || i4 == 1751479857 || i4 == 1932670515 || i4 == 1211250227 || i4 == 1748121139 || i4 == 1987063864 || i4 == 1987063865 || i4 == 1635135537 || i4 == 1685479798 || i4 == 1685479729 || i4 == 1685481573 || i4 == 1685481521 || i4 == 1634760241) {
                parseVideoSampleEntry(parsableByteArray, i4, position, i3, tkhdData.id, str, tkhdData.rotationDegrees, drmInitData, stsdData, i2);
            } else if (i4 == 1836069985 || i4 == 1701733217 || i4 == 1633889587 || i4 == 1700998451 || i4 == 1633889588 || i4 == 1835823201 || i4 == 1685353315 || i4 == 1685353317 || i4 == 1685353320 || i4 == 1685353324 || i4 == 1685353336 || i4 == 1935764850 || i4 == 1935767394 || i4 == 1819304813 || i4 == 1936684916 || i4 == 1953984371 || i4 == 778924082 || i4 == 778924083 || i4 == 1835557169 || i4 == 1835560241 || i4 == 1634492771 || i4 == 1634492791 || i4 == 1970037111 || i4 == 1332770163 || i4 == 1716281667 || i4 == 1767992678 || i4 == 1768973165 || i4 == 1718641517) {
                parseAudioSampleEntry(parsableByteArray, i4, position, i3, tkhdData.id, str, z, drmInitData, stsdData, i2);
            } else if (i4 == 1414810956 || i4 == 1954034535 || i4 == 2004251764 || i4 == 1937010800 || i4 == 1664495672 || i4 == 1836070003) {
                parseTextSampleEntry(parsableByteArray, i4, position, i3, tkhdData, str, stsdData);
            } else if (i4 == 1835365492) {
                parseMetaDataSampleEntry(parsableByteArray, i4, position, tkhdData.id, stsdData);
            } else if (i4 == 1667329389) {
                stsdData.format = new Format.Builder().setId(tkhdData.id).setSampleMimeType(MimeTypes.APPLICATION_CAMERA_MOTION).build();
            }
            parsableByteArray.setPosition(position + i3);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, TkhdData tkhdData, String str, StsdData stsdData) {
        parsableByteArray.setPosition(i2 + 16);
        String str2 = "application/ttml+xml";
        ImmutableList immutableListOf = null;
        long j = Long.MAX_VALUE;
        if (i != 1414810956) {
            if (i == 1954034535) {
                int i4 = i3 - 16;
                byte[] bArr = new byte[i4];
                parsableByteArray.readBytes(bArr, 0, i4);
                immutableListOf = ImmutableList.of(bArr);
                str2 = "application/x-quicktime-tx3g";
            } else if (i == 2004251764) {
                str2 = MimeTypes.APPLICATION_MP4VTT;
            } else if (i == 1937010800) {
                j = 0;
            } else if (i == 1664495672) {
                stsdData.requiredSampleTransformation = 1;
                str2 = MimeTypes.APPLICATION_MP4CEA608;
            } else if (i == 1836070003) {
                int position = parsableByteArray.getPosition();
                parsableByteArray.skipBytes(4);
                if (parsableByteArray.readInt() == 1702061171) {
                    EsdsData esdsFromParent = parseEsdsFromParent(parsableByteArray, position);
                    if (esdsFromParent.initializationData == null || esdsFromParent.initializationData.length != 64) {
                        return;
                    }
                    immutableListOf = ImmutableList.of(Util.getUtf8Bytes(formatVobsubIdx(esdsFromParent.initializationData, tkhdData.width, tkhdData.height)));
                    str2 = "application/vobsub";
                } else {
                    str2 = null;
                }
            } else {
                throw new IllegalStateException();
            }
        }
        if (str2 != null) {
            stsdData.format = new Format.Builder().setId(tkhdData.id).setSampleMimeType(str2).setLanguage(str).setSubsampleOffsetUs(j).setInitializationData(immutableListOf).build();
        }
    }

    private static String formatVobsubIdx(byte[] bArr, int i, int i2) {
        Assertions.checkState(bArr.length == 64);
        ArrayList arrayList = new ArrayList(16);
        for (int i3 = 0; i3 < bArr.length - 3; i3 += 4) {
            arrayList.add(String.format("%06x", Integer.valueOf(vobsubYuvToRgb(Ints.fromBytes(bArr[i3], bArr[i3 + 1], bArr[i3 + 2], bArr[i3 + 3])))));
        }
        return "size: " + i + "x" + i2 + "\npalette: " + Joiner.on(", ").join(arrayList) + ShellUtil.COMMAND_LINE_END;
    }

    private static int vobsubYuvToRgb(int i) {
        int i2 = (i >> 16) & 255;
        int i3 = ((i >> 8) & 255) - 128;
        int i4 = (i & 255) - 128;
        return Util.constrainValue(i2 + ((i4 * 17790) / 10000), 0, 255) | (Util.constrainValue(((i3 * 14075) / 10000) + i2, 0, 255) << 16) | (Util.constrainValue((i2 - ((i4 * 3455) / 10000)) - ((i3 * 7169) / 10000), 0, 255) << 8);
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, int i5, DrmInitData drmInitData, StsdData stsdData, int i6) throws ParserException {
        String str2;
        DrmInitData drmInitData2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13 = i2;
        int i14 = i3;
        DrmInitData drmInitDataCopyWithSchemeType = drmInitData;
        StsdData stsdData2 = stsdData;
        parsableByteArray.setPosition(i13 + 16);
        parsableByteArray.skipBytes(16);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        int iIntValue = i;
        if (iIntValue == 1701733238) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i13, i14);
            if (sampleEntryEncryptionData != null) {
                iIntValue = ((Integer) sampleEntryEncryptionData.first).intValue();
                drmInitDataCopyWithSchemeType = drmInitDataCopyWithSchemeType == null ? null : drmInitDataCopyWithSchemeType.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                stsdData2.trackEncryptionBoxes[i6] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        String str3 = "video/3gpp";
        if (iIntValue != 1831958048) {
            str2 = iIntValue == 1211250227 ? "video/3gpp" : null;
        } else {
            str2 = MimeTypes.VIDEO_MPEG;
        }
        float paspFromParent = 1.0f;
        int i15 = 8;
        int i16 = 8;
        ByteBuffer byteBufferAllocateHdrStaticInfo = null;
        List<byte[]> listBuild = null;
        String str4 = null;
        byte[] projFromParent = null;
        int i17 = -1;
        int i18 = -1;
        int i19 = -1;
        int i20 = -1;
        int i21 = -1;
        int i22 = -1;
        int i23 = -1;
        int iIsoTransferCharacteristicsToColorTransfer = -1;
        BtrtData btrtFromParent = null;
        EsdsData esdsFromParent = null;
        NalUnitUtil.H265VpsData h265VpsData = null;
        boolean z = false;
        while (position - i13 < i14) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int i24 = parsableByteArray.readInt();
            if (i24 == 0 && parsableByteArray.getPosition() - i13 == i14) {
                break;
            }
            ExtractorUtil.checkContainerInput(i24 > 0, "childAtomSize must be positive");
            int i25 = parsableByteArray.readInt();
            if (i25 == 1635148611) {
                ExtractorUtil.checkContainerInput(str2 == null, null);
                parsableByteArray.setPosition(position2 + 8);
                AvcConfig avcConfig = AvcConfig.parse(parsableByteArray);
                List<byte[]> list = avcConfig.initializationData;
                stsdData2.nalUnitLengthFieldLength = avcConfig.nalUnitLengthFieldLength;
                if (!z) {
                    paspFromParent = avcConfig.pixelWidthHeightRatio;
                }
                String str5 = avcConfig.codecs;
                int i26 = avcConfig.maxNumReorderFrames;
                int i27 = avcConfig.colorSpace;
                drmInitData2 = drmInitDataCopyWithSchemeType;
                i18 = i26;
                i23 = avcConfig.colorRange;
                iIsoTransferCharacteristicsToColorTransfer = avcConfig.colorTransfer;
                i15 = avcConfig.bitdepthLuma;
                listBuild = list;
                str2 = "video/avc";
                str4 = str5;
                i7 = i27;
                i16 = avcConfig.bitdepthChroma;
            } else {
                if (i25 == 1752589123) {
                    ExtractorUtil.checkContainerInput(str2 == null, null);
                    parsableByteArray.setPosition(position2 + 8);
                    HevcConfig hevcConfig = HevcConfig.parse(parsableByteArray);
                    List<byte[]> list2 = hevcConfig.initializationData;
                    stsdData2.nalUnitLengthFieldLength = hevcConfig.nalUnitLengthFieldLength;
                    if (!z) {
                        paspFromParent = hevcConfig.pixelWidthHeightRatio;
                    }
                    int i28 = hevcConfig.maxNumReorderPics;
                    int i29 = hevcConfig.maxSubLayers;
                    String str6 = hevcConfig.codecs;
                    listBuild = list2;
                    if (hevcConfig.stereoMode != -1) {
                        i17 = hevcConfig.stereoMode;
                    }
                    int i30 = hevcConfig.decodedWidth;
                    int i31 = hevcConfig.decodedHeight;
                    int i32 = hevcConfig.colorSpace;
                    int i33 = hevcConfig.colorRange;
                    int i34 = hevcConfig.colorTransfer;
                    int i35 = hevcConfig.bitdepthLuma;
                    int i36 = hevcConfig.bitdepthChroma;
                    h265VpsData = hevcConfig.vpsData;
                    drmInitData2 = drmInitDataCopyWithSchemeType;
                    unsignedShort2 = unsignedShort2;
                    iIntValue = iIntValue;
                    i23 = i33;
                    iIsoTransferCharacteristicsToColorTransfer = i34;
                    i19 = i29;
                    i20 = i30;
                    str4 = str6;
                    i16 = i36;
                    i18 = i28;
                    str2 = "video/hevc";
                    i7 = i32;
                    i21 = i31;
                    i15 = i35;
                } else {
                    drmInitData2 = drmInitDataCopyWithSchemeType;
                    if (i25 == 1818785347) {
                        ExtractorUtil.checkContainerInput("video/hevc".equals(str2), "lhvC must follow hvcC atom");
                        NalUnitUtil.H265VpsData h265VpsData2 = h265VpsData;
                        ExtractorUtil.checkContainerInput(h265VpsData2 != null && h265VpsData2.layerInfos.size() >= 2, "must have at least two layers");
                        parsableByteArray.setPosition(position2 + 8);
                        HevcConfig layered = HevcConfig.parseLayered(parsableByteArray, (NalUnitUtil.H265VpsData) Assertions.checkNotNull(h265VpsData2));
                        ExtractorUtil.checkContainerInput(stsdData2.nalUnitLengthFieldLength == layered.nalUnitLengthFieldLength, "nalUnitLengthFieldLength must be same for both hvcC and lhvC atoms");
                        if (layered.colorSpace != -1) {
                            i7 = i22;
                            ExtractorUtil.checkContainerInput(i7 == layered.colorSpace, "colorSpace must be the same for both views");
                        } else {
                            i7 = i22;
                        }
                        if (layered.colorRange != -1) {
                            i11 = i23;
                            ExtractorUtil.checkContainerInput(i11 == layered.colorRange, "colorRange must be the same for both views");
                        } else {
                            i11 = i23;
                        }
                        if (layered.colorTransfer != -1) {
                            int i37 = iIsoTransferCharacteristicsToColorTransfer;
                            i12 = i37;
                            ExtractorUtil.checkContainerInput(i37 == layered.colorTransfer, "colorTransfer must be the same for both views");
                        } else {
                            i12 = iIsoTransferCharacteristicsToColorTransfer;
                        }
                        ExtractorUtil.checkContainerInput(i15 == layered.bitdepthLuma, "bitdepthLuma must be the same for both views");
                        ExtractorUtil.checkContainerInput(i16 == layered.bitdepthChroma, "bitdepthChroma must be the same for both views");
                        List<byte[]> list3 = listBuild;
                        if (list3 != null) {
                            listBuild = ImmutableList.builder().addAll((Iterable) list3).addAll((Iterable) layered.initializationData).build();
                        } else {
                            listBuild = list3;
                            ExtractorUtil.checkContainerInput(false, "initializationData must be already set from hvcC atom");
                        }
                        String str7 = layered.codecs;
                        h265VpsData = h265VpsData2;
                        str2 = MimeTypes.VIDEO_MV_HEVC;
                        i23 = i11;
                        iIsoTransferCharacteristicsToColorTransfer = i12;
                        str4 = str7;
                    } else {
                        List<byte[]> list4 = listBuild;
                        i7 = i22;
                        i23 = i23;
                        int i38 = iIsoTransferCharacteristicsToColorTransfer;
                        NalUnitUtil.H265VpsData h265VpsData3 = h265VpsData;
                        if (i25 == 1986361461) {
                            VexuData videoExtendedUsageBox = parseVideoExtendedUsageBox(parsableByteArray, position2, i24);
                            if (videoExtendedUsageBox == null || videoExtendedUsageBox.eyesData == null) {
                                i10 = i17;
                                i17 = i10;
                            } else if (h265VpsData3 == null || h265VpsData3.layerInfos.size() < 2) {
                                i10 = i17;
                                if (i10 == -1) {
                                    i17 = videoExtendedUsageBox.eyesData.striData.eyeViewsReversed ? 5 : 4;
                                } else {
                                    i17 = i10;
                                }
                            } else {
                                ExtractorUtil.checkContainerInput(videoExtendedUsageBox.hasBothEyeViews(), "both eye views must be marked as available");
                                ExtractorUtil.checkContainerInput(!videoExtendedUsageBox.eyesData.striData.eyeViewsReversed, "for MV-HEVC, eye_views_reversed must be set to false");
                                i10 = i17;
                                i17 = i10;
                            }
                            h265VpsData = h265VpsData3;
                            listBuild = list4;
                            i23 = i23;
                            iIsoTransferCharacteristicsToColorTransfer = i38;
                        } else {
                            int i39 = i17;
                            if (i25 == 1685480259 || i25 == 1685485123 || i25 == 1685485379) {
                                i8 = i39;
                                unsignedShort2 = unsignedShort2;
                                iIntValue = iIntValue;
                                int i40 = i16;
                                float f = paspFromParent;
                                int i41 = i15;
                                i9 = i38;
                                int i42 = i24 - 8;
                                byte[] bArr = new byte[i42];
                                parsableByteArray.readBytes(bArr, 0, i42);
                                if (list4 != null) {
                                    listBuild = ImmutableList.builder().addAll((Iterable) list4).add(bArr).build();
                                } else {
                                    ExtractorUtil.checkContainerInput(false, "initializationData must already be set from hvcC or avcC atom");
                                    listBuild = list4;
                                }
                                parsableByteArray.setPosition(position2 + 8);
                                DolbyVisionConfig dolbyVisionConfig = DolbyVisionConfig.parse(parsableByteArray);
                                if (dolbyVisionConfig != null) {
                                    String str8 = dolbyVisionConfig.codecs;
                                    str2 = MimeTypes.VIDEO_DOLBY_VISION;
                                    str4 = str8;
                                }
                                i16 = i40;
                                i15 = i41;
                                paspFromParent = f;
                            } else if (i25 == 1987076931) {
                                ExtractorUtil.checkContainerInput(str2 == null, null);
                                String str9 = iIntValue == 1987063864 ? "video/x-vnd.on2.vp8" : "video/x-vnd.on2.vp9";
                                parsableByteArray.setPosition(position2 + 12);
                                byte unsignedByte = (byte) parsableByteArray.readUnsignedByte();
                                byte unsignedByte2 = (byte) parsableByteArray.readUnsignedByte();
                                int unsignedByte3 = parsableByteArray.readUnsignedByte();
                                i16 = unsignedByte3 >> 4;
                                List<byte[]> listBuildVp9CodecPrivateInitializationData = str9.equals("video/x-vnd.on2.vp9") ? CodecSpecificDataUtil.buildVp9CodecPrivateInitializationData(unsignedByte, unsignedByte2, (byte) i16, (byte) ((unsignedByte3 >> 1) & 7)) : list4;
                                boolean z2 = (unsignedByte3 & 1) != 0;
                                int unsignedByte4 = parsableByteArray.readUnsignedByte();
                                int unsignedByte5 = parsableByteArray.readUnsignedByte();
                                int iIsoColorPrimariesToColorSpace = ColorInfo.isoColorPrimariesToColorSpace(unsignedByte4);
                                i23 = z2 ? 1 : 2;
                                iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(unsignedByte5);
                                str2 = str9;
                                listBuild = listBuildVp9CodecPrivateInitializationData;
                                i15 = i16;
                                h265VpsData = h265VpsData3;
                                i7 = iIsoColorPrimariesToColorSpace;
                                i17 = i39;
                            } else if (i25 == 1635135811) {
                                int i43 = i24 - 8;
                                byte[] bArr2 = new byte[i43];
                                parsableByteArray.readBytes(bArr2, 0, i43);
                                listBuild = ImmutableList.of(bArr2);
                                parsableByteArray.setPosition(position2 + 8);
                                ColorInfo av1c = parseAv1c(parsableByteArray);
                                int i44 = av1c.lumaBitdepth;
                                int i45 = av1c.chromaBitdepth;
                                i7 = av1c.colorSpace;
                                int i46 = av1c.colorRange;
                                iIsoTransferCharacteristicsToColorTransfer = av1c.colorTransfer;
                                i15 = i44;
                                unsignedShort2 = unsignedShort2;
                                iIntValue = iIntValue;
                                i23 = i46;
                                h265VpsData = h265VpsData3;
                                i17 = i39;
                                i16 = i45;
                                str2 = MimeTypes.VIDEO_AV1;
                            } else if (i25 == 1668050025) {
                                if (byteBufferAllocateHdrStaticInfo == null) {
                                    byteBufferAllocateHdrStaticInfo = allocateHdrStaticInfo();
                                }
                                ByteBuffer byteBuffer = byteBufferAllocateHdrStaticInfo;
                                byteBuffer.position(21);
                                byteBuffer.putShort(parsableByteArray.readShort());
                                byteBuffer.putShort(parsableByteArray.readShort());
                                byteBufferAllocateHdrStaticInfo = byteBuffer;
                                unsignedShort2 = unsignedShort2;
                                iIntValue = iIntValue;
                                h265VpsData = h265VpsData3;
                                listBuild = list4;
                                i23 = i23;
                                iIsoTransferCharacteristicsToColorTransfer = i38;
                                i17 = i39;
                            } else {
                                if (i25 == 1835295606) {
                                    if (byteBufferAllocateHdrStaticInfo == null) {
                                        byteBufferAllocateHdrStaticInfo = allocateHdrStaticInfo();
                                    }
                                    ByteBuffer byteBuffer2 = byteBufferAllocateHdrStaticInfo;
                                    short s = parsableByteArray.readShort();
                                    short s2 = parsableByteArray.readShort();
                                    iIntValue = iIntValue;
                                    short s3 = parsableByteArray.readShort();
                                    short s4 = parsableByteArray.readShort();
                                    int i47 = i16;
                                    short s5 = parsableByteArray.readShort();
                                    int i48 = i15;
                                    short s6 = parsableByteArray.readShort();
                                    i8 = i39;
                                    short s7 = parsableByteArray.readShort();
                                    float f2 = paspFromParent;
                                    short s8 = parsableByteArray.readShort();
                                    long unsignedInt = parsableByteArray.readUnsignedInt();
                                    long unsignedInt2 = parsableByteArray.readUnsignedInt();
                                    unsignedShort2 = unsignedShort2;
                                    byteBuffer2.position(1);
                                    byteBuffer2.putShort(s5);
                                    byteBuffer2.putShort(s6);
                                    byteBuffer2.putShort(s);
                                    byteBuffer2.putShort(s2);
                                    byteBuffer2.putShort(s3);
                                    byteBuffer2.putShort(s4);
                                    byteBuffer2.putShort(s7);
                                    byteBuffer2.putShort(s8);
                                    byteBuffer2.putShort((short) (unsignedInt / 10000));
                                    byteBuffer2.putShort((short) (unsignedInt2 / 10000));
                                    byteBufferAllocateHdrStaticInfo = byteBuffer2;
                                    i16 = i47;
                                    i15 = i48;
                                    listBuild = list4;
                                    i23 = i23;
                                    iIsoTransferCharacteristicsToColorTransfer = i38;
                                    paspFromParent = f2;
                                } else {
                                    i8 = i39;
                                    unsignedShort2 = unsignedShort2;
                                    iIntValue = iIntValue;
                                    i16 = i16;
                                    float f3 = paspFromParent;
                                    i15 = i15;
                                    if (i25 == 1681012275) {
                                        ExtractorUtil.checkContainerInput(str2 == null, null);
                                        str2 = str3;
                                    } else {
                                        if (i25 == 1702061171) {
                                            ExtractorUtil.checkContainerInput(str2 == null, null);
                                            esdsFromParent = parseEsdsFromParent(parsableByteArray, position2);
                                            String str10 = esdsFromParent.mimeType;
                                            byte[] bArr3 = esdsFromParent.initializationData;
                                            listBuild = bArr3 != null ? ImmutableList.of(bArr3) : list4;
                                            str2 = str10;
                                            i23 = i23;
                                            iIsoTransferCharacteristicsToColorTransfer = i38;
                                            paspFromParent = f3;
                                        } else if (i25 == 1651798644) {
                                            btrtFromParent = parseBtrtFromParent(parsableByteArray, position2);
                                        } else if (i25 == 1885434736) {
                                            paspFromParent = parsePaspFromParent(parsableByteArray, position2);
                                            i16 = i16;
                                            i15 = i15;
                                            listBuild = list4;
                                            i23 = i23;
                                            iIsoTransferCharacteristicsToColorTransfer = i38;
                                            z = true;
                                        } else if (i25 == 1937126244) {
                                            projFromParent = parseProjFromParent(parsableByteArray, position2, i24);
                                        } else if (i25 == 1936995172) {
                                            int unsignedByte6 = parsableByteArray.readUnsignedByte();
                                            parsableByteArray.skipBytes(3);
                                            if (unsignedByte6 == 0) {
                                                int unsignedByte7 = parsableByteArray.readUnsignedByte();
                                                if (unsignedByte7 == 0) {
                                                    i8 = 0;
                                                } else if (unsignedByte7 == 1) {
                                                    i8 = 1;
                                                } else if (unsignedByte7 == 2) {
                                                    i8 = 2;
                                                } else if (unsignedByte7 == 3) {
                                                    i8 = 3;
                                                }
                                            }
                                        } else if (i25 == 1634760259) {
                                            int i49 = i24 - 12;
                                            byte[] bArr4 = new byte[i49];
                                            parsableByteArray.setPosition(position2 + 12);
                                            parsableByteArray.readBytes(bArr4, 0, i49);
                                            listBuild = ImmutableList.of(bArr4);
                                            ColorInfo apvc = parseApvc(new ParsableByteArray(bArr4));
                                            int i50 = apvc.lumaBitdepth;
                                            int i51 = apvc.chromaBitdepth;
                                            int i52 = apvc.colorSpace;
                                            int i53 = apvc.colorRange;
                                            iIsoTransferCharacteristicsToColorTransfer = apvc.colorTransfer;
                                            i15 = i50;
                                            i16 = i51;
                                            i7 = i52;
                                            i23 = i53;
                                            str2 = MimeTypes.VIDEO_APV;
                                            paspFromParent = f3;
                                        } else {
                                            if (i25 == 1668246642) {
                                                i9 = i38;
                                                if (i7 == -1 && i9 == -1) {
                                                    int i54 = parsableByteArray.readInt();
                                                    if (i54 == TYPE_nclx || i54 == TYPE_nclc) {
                                                        int unsignedShort3 = parsableByteArray.readUnsignedShort();
                                                        int unsignedShort4 = parsableByteArray.readUnsignedShort();
                                                        parsableByteArray.skipBytes(2);
                                                        boolean z3 = i24 == 19 && (parsableByteArray.readUnsignedByte() & 128) != 0;
                                                        int iIsoColorPrimariesToColorSpace2 = ColorInfo.isoColorPrimariesToColorSpace(unsignedShort3);
                                                        int i55 = z3 ? 1 : 2;
                                                        i7 = iIsoColorPrimariesToColorSpace2;
                                                        i16 = i16;
                                                        i15 = i15;
                                                        listBuild = list4;
                                                        paspFromParent = f3;
                                                        iIsoTransferCharacteristicsToColorTransfer = ColorInfo.isoTransferCharacteristicsToColorTransfer(unsignedShort4);
                                                        i23 = i55;
                                                    } else {
                                                        Log.w(TAG, "Unsupported color type: " + Mp4Box.getBoxTypeString(i54));
                                                    }
                                                }
                                            } else {
                                                i9 = i38;
                                            }
                                            i16 = i16;
                                            i15 = i15;
                                            listBuild = list4;
                                            paspFromParent = f3;
                                        }
                                        int i56 = i8;
                                        h265VpsData = h265VpsData3;
                                        i17 = i56;
                                    }
                                    listBuild = list4;
                                    i23 = i23;
                                    iIsoTransferCharacteristicsToColorTransfer = i38;
                                    paspFromParent = f3;
                                }
                                int i57 = i8;
                                h265VpsData = h265VpsData3;
                                i17 = i57;
                            }
                            iIsoTransferCharacteristicsToColorTransfer = i9;
                            int i58 = i8;
                            h265VpsData = h265VpsData3;
                            i17 = i58;
                        }
                    }
                }
                position += i24;
                i13 = i2;
                i14 = i3;
                stsdData2 = stsdData;
                iIntValue = iIntValue;
                drmInitDataCopyWithSchemeType = drmInitData2;
                unsignedShort2 = unsignedShort2;
                i22 = i7;
                str3 = str3;
            }
            position += i24;
            i13 = i2;
            i14 = i3;
            stsdData2 = stsdData;
            iIntValue = iIntValue;
            drmInitDataCopyWithSchemeType = drmInitData2;
            unsignedShort2 = unsignedShort2;
            i22 = i7;
            str3 = str3;
        }
        DrmInitData drmInitData3 = drmInitDataCopyWithSchemeType;
        int i59 = unsignedShort2;
        float f4 = paspFromParent;
        List<byte[]> list5 = listBuild;
        int i60 = i17;
        int i61 = i22;
        int i62 = i23;
        int i63 = iIsoTransferCharacteristicsToColorTransfer;
        int i64 = i16;
        int i65 = i15;
        if (str2 == null) {
            return;
        }
        Format.Builder colorInfo = new Format.Builder().setId(i4).setSampleMimeType(str2).setCodecs(str4).setWidth(unsignedShort).setHeight(i59).setDecodedWidth(i20).setDecodedHeight(i21).setPixelWidthHeightRatio(f4).setRotationDegrees(i5).setProjectionData(projFromParent).setStereoMode(i60).setInitializationData(list5).setMaxNumReorderSamples(i18).setMaxSubLayers(i19).setDrmInitData(drmInitData3).setLanguage(str).setColorInfo(new ColorInfo.Builder().setColorSpace(i61).setColorRange(i62).setColorTransfer(i63).setHdrStaticInfo(byteBufferAllocateHdrStaticInfo != null ? byteBufferAllocateHdrStaticInfo.array() : null).setLumaBitdepth(i65).setChromaBitdepth(i64).build());
        if (btrtFromParent != null) {
            colorInfo.setAverageBitrate(Ints.saturatedCast(btrtFromParent.avgBitrate)).setPeakBitrate(Ints.saturatedCast(btrtFromParent.maxBitrate));
        } else if (esdsFromParent != null) {
            colorInfo.setAverageBitrate(Ints.saturatedCast(esdsFromParent.bitrate)).setPeakBitrate(Ints.saturatedCast(esdsFromParent.peakBitrate));
        }
        stsdData.format = colorInfo.build();
    }

    private static ColorInfo parseAv1c(ParsableByteArray parsableByteArray) {
        ColorInfo.Builder builder = new ColorInfo.Builder();
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.getData());
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        parsableBitArray.skipBytes(1);
        int bits = parsableBitArray.readBits(3);
        parsableBitArray.skipBits(6);
        boolean bit = parsableBitArray.readBit();
        boolean bit2 = parsableBitArray.readBit();
        if (bits == 2 && bit) {
            builder.setLumaBitdepth(bit2 ? 12 : 10);
            builder.setChromaBitdepth(bit2 ? 12 : 10);
        } else if (bits <= 2) {
            builder.setLumaBitdepth(bit ? 10 : 8);
            builder.setChromaBitdepth(bit ? 10 : 8);
        }
        parsableBitArray.skipBits(13);
        parsableBitArray.skipBit();
        int bits2 = parsableBitArray.readBits(4);
        if (bits2 != 1) {
            Log.i(TAG, "Unsupported obu_type: " + bits2);
            return builder.build();
        }
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported obu_extension_flag");
            return builder.build();
        }
        boolean bit3 = parsableBitArray.readBit();
        parsableBitArray.skipBit();
        if (bit3 && parsableBitArray.readBits(8) > 127) {
            Log.i(TAG, "Excessive obu_size");
            return builder.build();
        }
        int bits3 = parsableBitArray.readBits(3);
        parsableBitArray.skipBit();
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported reduced_still_picture_header");
            return builder.build();
        }
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported timing_info_present_flag");
            return builder.build();
        }
        if (parsableBitArray.readBit()) {
            Log.i(TAG, "Unsupported initial_display_delay_present_flag");
            return builder.build();
        }
        int bits4 = parsableBitArray.readBits(5);
        boolean z = false;
        for (int i = 0; i <= bits4; i++) {
            parsableBitArray.skipBits(12);
            if (parsableBitArray.readBits(5) > 7) {
                parsableBitArray.skipBit();
            }
        }
        int bits5 = parsableBitArray.readBits(4);
        int bits6 = parsableBitArray.readBits(4);
        parsableBitArray.skipBits(bits5 + 1);
        parsableBitArray.skipBits(bits6 + 1);
        if (parsableBitArray.readBit()) {
            parsableBitArray.skipBits(7);
        }
        parsableBitArray.skipBits(7);
        boolean bit4 = parsableBitArray.readBit();
        if (bit4) {
            parsableBitArray.skipBits(2);
        }
        if ((parsableBitArray.readBit() ? 2 : parsableBitArray.readBits(1)) > 0 && !parsableBitArray.readBit()) {
            parsableBitArray.skipBits(1);
        }
        if (bit4) {
            parsableBitArray.skipBits(3);
        }
        parsableBitArray.skipBits(3);
        boolean bit5 = parsableBitArray.readBit();
        if (bits3 == 2 && bit5) {
            parsableBitArray.skipBit();
        }
        if (bits3 != 1 && parsableBitArray.readBit()) {
            z = true;
        }
        if (parsableBitArray.readBit()) {
            int bits7 = parsableBitArray.readBits(8);
            int bits8 = parsableBitArray.readBits(8);
            builder.setColorSpace(ColorInfo.isoColorPrimariesToColorSpace(bits7)).setColorRange(((z || bits7 != 1 || bits8 != 13 || parsableBitArray.readBits(8) != 0) ? parsableBitArray.readBits(1) : 1) != 1 ? 2 : 1).setColorTransfer(ColorInfo.isoTransferCharacteristicsToColorTransfer(bits8));
        }
        return builder.build();
    }

    private static ColorInfo parseApvc(ParsableByteArray parsableByteArray) {
        ColorInfo.Builder builder = new ColorInfo.Builder();
        ParsableBitArray parsableBitArray = new ParsableBitArray(parsableByteArray.getData());
        parsableBitArray.setPosition(parsableByteArray.getPosition() * 8);
        parsableBitArray.skipBytes(1);
        int bits = parsableBitArray.readBits(8);
        for (int i = 0; i < bits; i++) {
            parsableBitArray.skipBytes(1);
            int bits2 = parsableBitArray.readBits(8);
            for (int i2 = 0; i2 < bits2; i2++) {
                parsableBitArray.skipBits(6);
                boolean bit = parsableBitArray.readBit();
                parsableBitArray.skipBit();
                parsableBitArray.skipBytes(11);
                parsableBitArray.skipBits(4);
                int bits3 = parsableBitArray.readBits(4) + 8;
                builder.setLumaBitdepth(bits3);
                builder.setChromaBitdepth(bits3);
                parsableBitArray.skipBytes(1);
                if (bit) {
                    int bits4 = parsableBitArray.readBits(8);
                    int bits5 = parsableBitArray.readBits(8);
                    parsableBitArray.skipBytes(1);
                    builder.setColorSpace(ColorInfo.isoColorPrimariesToColorSpace(bits4)).setColorRange(parsableBitArray.readBit() ? 1 : 2).setColorTransfer(ColorInfo.isoTransferCharacteristicsToColorTransfer(bits5));
                }
            }
        }
        return builder.build();
    }

    private static ByteBuffer allocateHdrStaticInfo() {
        return ByteBuffer.allocate(25).order(ByteOrder.LITTLE_ENDIAN);
    }

    private static void parseMetaDataSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, StsdData stsdData) {
        parsableByteArray.setPosition(i2 + 16);
        if (i == 1835365492) {
            parsableByteArray.readNullTerminatedString();
            String nullTerminatedString = parsableByteArray.readNullTerminatedString();
            if (nullTerminatedString != null) {
                stsdData.format = new Format.Builder().setId(i3).setSampleMimeType(nullTerminatedString).build();
            }
        }
    }

    private static Pair<long[], long[]> parseEdts(Mp4Box.ContainerBox containerBox) {
        Mp4Box.LeafBox leafBoxOfType = containerBox.getLeafBoxOfType(Mp4Box.TYPE_elst);
        if (leafBoxOfType == null) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafBoxOfType.data;
        parsableByteArray.setPosition(8);
        int fullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[unsignedIntToInt];
        long[] jArr2 = new long[unsignedIntToInt];
        for (int i = 0; i < unsignedIntToInt; i++) {
            jArr[i] = fullBoxVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = fullBoxVersion == 1 ? parsableByteArray.readLong() : parsableByteArray.readInt();
            if (parsableByteArray.readShort() != 1) {
                throw new IllegalArgumentException("Unsupported media rate.");
            }
            parsableByteArray.skipBytes(2);
        }
        return Pair.create(jArr, jArr2);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return parsableByteArray.readUnsignedIntToInt() / parsableByteArray.readUnsignedIntToInt();
    }

    /* JADX WARN: Code duplicated, block: B:141:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:143:0x01eb  */
    /* JADX WARN: Code duplicated, block: B:144:0x01ed  */
    /* JADX WARN: Code duplicated, block: B:147:0x01fc  */
    /* JADX WARN: Code duplicated, block: B:149:0x0212  */
    /* JADX WARN: Code duplicated, block: B:150:0x0226  */
    /* JADX WARN: Code duplicated, block: B:153:0x0245  */
    /* JADX WARN: Code duplicated, block: B:154:0x024b  */
    /* JADX WARN: Code duplicated, block: B:156:0x0258  */
    /* JADX WARN: Code duplicated, block: B:158:0x0261  */
    /* JADX WARN: Code duplicated, block: B:160:0x026c  */
    /* JADX WARN: Code duplicated, block: B:162:0x0274  */
    /* JADX WARN: Code duplicated, block: B:163:0x0279  */
    /* JADX WARN: Code duplicated, block: B:169:0x0296  */
    /* JADX WARN: Code duplicated, block: B:206:0x0381 A[PHI: r8 r9 r11 r12 r16 r23
      0x0381: PHI (r8v14 int) = (r8v9 int), (r8v9 int), (r8v9 int), (r8v25 int), (r26v0 int) binds: [B:244:0x0486, B:246:0x0494, B:249:0x049e, B:209:0x038f, B:205:0x037e] A[DONT_GENERATE, DONT_INLINE]
      0x0381: PHI (r9v9 int) = (r9v5 int), (r9v5 int), (r9v5 int), (r9v12 int), (r9v5 int) binds: [B:244:0x0486, B:246:0x0494, B:249:0x049e, B:209:0x038f, B:205:0x037e] A[DONT_GENERATE, DONT_INLINE]
      0x0381: PHI (r11v12 java.lang.String) = 
      (r11v10 java.lang.String)
      (r11v11 java.lang.String)
      (r11v11 java.lang.String)
      (r11v10 java.lang.String)
      (r11v15 java.lang.String)
     binds: [B:244:0x0486, B:246:0x0494, B:249:0x049e, B:209:0x038f, B:205:0x037e] A[DONT_GENERATE, DONT_INLINE]
      0x0381: PHI (r12v61 java.util.List<byte[]>) = 
      (r12v50 java.util.List<byte[]>)
      (r12v50 java.util.List<byte[]>)
      (r12v60 java.util.List<byte[]>)
      (r12v69 java.util.List<byte[]>)
      (r12v70 java.util.List<byte[]>)
     binds: [B:244:0x0486, B:246:0x0494, B:249:0x049e, B:209:0x038f, B:205:0x037e] A[DONT_GENERATE, DONT_INLINE]
      0x0381: PHI (r16v2 int) = (r16v1 int), (r16v1 int), (r16v1 int), (r16v1 int), (r16v4 int) binds: [B:244:0x0486, B:246:0x0494, B:249:0x049e, B:209:0x038f, B:205:0x037e] A[DONT_GENERATE, DONT_INLINE]
      0x0381: PHI (r23v3 androidx.media3.extractor.mp4.BoxParser$EsdsData) = 
      (r23v1 androidx.media3.extractor.mp4.BoxParser$EsdsData)
      (r23v2 androidx.media3.extractor.mp4.BoxParser$EsdsData)
      (r23v2 androidx.media3.extractor.mp4.BoxParser$EsdsData)
      (r23v1 androidx.media3.extractor.mp4.BoxParser$EsdsData)
      (r23v1 androidx.media3.extractor.mp4.BoxParser$EsdsData)
     binds: [B:244:0x0486, B:246:0x0494, B:249:0x049e, B:209:0x038f, B:205:0x037e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:239:0x046f  */
    /* JADX WARN: Code duplicated, block: B:241:0x047f  */
    /* JADX WARN: Code duplicated, block: B:242:0x0481  */
    /* JADX WARN: Code duplicated, block: B:245:0x0488  */
    /* JADX WARN: Code duplicated, block: B:247:0x0496  */
    /* JADX WARN: Code duplicated, block: B:249:0x049e  */
    /* JADX WARN: Code duplicated, block: B:250:0x04a4  */
    /* JADX WARN: Code duplicated, block: B:252:0x04ac  */
    /* JADX WARN: Code duplicated, block: B:253:0x04bd  */
    /* JADX WARN: Code duplicated, block: B:37:0x008b  */
    /* JADX WARN: Code duplicated, block: B:93:0x0152  */
    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, boolean z, DrmInitData drmInitData, StsdData stsdData, int i5) throws ParserException {
        int unsignedShort;
        int unsignedShort2;
        int unsignedFixedPoint1616;
        int i6;
        int i7;
        String str2;
        int i8;
        String str3;
        String str4;
        List<byte[]> listOf;
        EsdsData esdsFromParent;
        int i9;
        boolean z2;
        int i10;
        int iFindBoxPosition;
        byte[] bArr;
        String str5;
        String strBuildIamfCodecString;
        int i11;
        int pcmEncoding;
        int i12;
        int unsignedByte;
        byte[] bArr2;
        int unsignedByte2;
        int i13;
        String str6;
        byte[] bArr3;
        int iIntValue = i;
        int i14 = i2;
        int i15 = i3;
        DrmInitData drmInitDataCopyWithSchemeType = drmInitData;
        parsableByteArray.setPosition(i14 + 16);
        if (z) {
            unsignedShort = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        } else {
            parsableByteArray.skipBytes(8);
            unsignedShort = 0;
        }
        if (unsignedShort == 0 || unsignedShort == 1) {
            unsignedShort2 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
            unsignedFixedPoint1616 = parsableByteArray.readUnsignedFixedPoint1616();
            parsableByteArray.setPosition(parsableByteArray.getPosition() - 4);
            i6 = parsableByteArray.readInt();
            if (unsignedShort == 1) {
                parsableByteArray.skipBytes(16);
            }
            i7 = -1;
        } else {
            if (unsignedShort != 2) {
                return;
            }
            parsableByteArray.skipBytes(16);
            unsignedFixedPoint1616 = (int) Math.round(parsableByteArray.readDouble());
            int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray.skipBytes(4);
            int unsignedIntToInt2 = parsableByteArray.readUnsignedIntToInt();
            int unsignedIntToInt3 = parsableByteArray.readUnsignedIntToInt();
            boolean z3 = (unsignedIntToInt3 & 1) != 0;
            boolean z4 = (unsignedIntToInt3 & 2) != 0;
            if (z3) {
                if (unsignedIntToInt2 == 32) {
                    i7 = 4;
                } else {
                    i7 = -1;
                }
            } else if (unsignedIntToInt2 == 8) {
                i7 = 3;
            } else if (unsignedIntToInt2 == 16) {
                i7 = z4 ? 268435456 : 2;
            } else if (unsignedIntToInt2 == 24) {
                i7 = z4 ? C.ENCODING_PCM_24BIT_BIG_ENDIAN : 21;
            } else if (unsignedIntToInt2 == 32) {
                i7 = z4 ? C.ENCODING_PCM_32BIT_BIG_ENDIAN : 22;
            } else {
                i7 = -1;
            }
            parsableByteArray.skipBytes(8);
            unsignedShort2 = unsignedIntToInt;
            i6 = 0;
        }
        if (iIntValue == 1767992678) {
            unsignedFixedPoint1616 = -1;
            unsignedShort2 = -1;
        } else {
            if (iIntValue != 1935764850) {
                unsignedFixedPoint1616 = iIntValue == 1935767394 ? 16000 : 8000;
            }
            unsignedShort2 = 1;
        }
        int position = parsableByteArray.getPosition();
        if (iIntValue == 1701733217) {
            Pair<Integer, TrackEncryptionBox> sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i14, i15);
            if (sampleEntryEncryptionData != null) {
                iIntValue = ((Integer) sampleEntryEncryptionData.first).intValue();
                drmInitDataCopyWithSchemeType = drmInitDataCopyWithSchemeType == null ? null : drmInitDataCopyWithSchemeType.copyWithSchemeType(((TrackEncryptionBox) sampleEntryEncryptionData.second).schemeType);
                stsdData.trackEncryptionBoxes[i5] = (TrackEncryptionBox) sampleEntryEncryptionData.second;
            }
            parsableByteArray.setPosition(position);
        }
        String str7 = MimeTypes.AUDIO_MPEGH_MHM1;
        if (iIntValue == 1633889587) {
            str2 = "audio/ac3";
        } else if (iIntValue == 1700998451) {
            str2 = "audio/eac3";
        } else if (iIntValue == 1633889588) {
            str2 = MimeTypes.AUDIO_AC4;
        } else if (iIntValue == 1685353315) {
            str2 = "audio/vnd.dts";
        } else if (iIntValue == 1685353320 || iIntValue == 1685353324) {
            str2 = "audio/vnd.dts.hd";
        } else if (iIntValue == 1685353317) {
            str2 = "audio/vnd.dts.hd;profile=lbr";
        } else if (iIntValue == 1685353336) {
            str2 = MimeTypes.AUDIO_DTS_X;
        } else if (iIntValue == 1935764850) {
            str2 = "audio/3gpp";
        } else if (iIntValue == 1935767394) {
            str2 = "audio/amr-wb";
        } else {
            if (iIntValue == 1936684916) {
                str3 = "audio/raw";
                i8 = 2;
            } else if (iIntValue == 1953984371) {
                str3 = "audio/raw";
                i8 = 268435456;
            } else if (iIntValue == 1819304813) {
                if (i7 == -1) {
                    str3 = "audio/raw";
                    i8 = 2;
                } else {
                    i8 = i7;
                    str3 = "audio/raw";
                }
            } else if (iIntValue == 778924082 || iIntValue == 778924083) {
                str2 = "audio/mpeg";
            } else if (iIntValue == 1835557169) {
                str2 = MimeTypes.AUDIO_MPEGH_MHA1;
            } else if (iIntValue == 1835560241) {
                i8 = i7;
                str3 = MimeTypes.AUDIO_MPEGH_MHM1;
            } else if (iIntValue == 1634492771) {
                str2 = MimeTypes.AUDIO_ALAC;
            } else if (iIntValue == 1634492791) {
                str2 = MimeTypes.AUDIO_ALAW;
            } else if (iIntValue == 1970037111) {
                str2 = MimeTypes.AUDIO_MLAW;
            } else if (iIntValue == 1332770163) {
                str2 = "audio/opus";
            } else if (iIntValue == 1716281667) {
                str2 = MimeTypes.AUDIO_FLAC;
            } else if (iIntValue == 1835823201) {
                str2 = "audio/true-hd";
            } else if (iIntValue == 1767992678) {
                str2 = MimeTypes.AUDIO_IAMF;
            } else {
                i8 = i7;
                str3 = null;
            }
            int i16 = i8;
            str4 = null;
            listOf = null;
            esdsFromParent = null;
            BtrtData btrtFromParent = null;
            while (position - i14 < i15) {
                parsableByteArray.setPosition(position);
                i9 = parsableByteArray.readInt();
                if (i9 > 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                ExtractorUtil.checkContainerInput(z2, "childAtomSize must be positive");
                i10 = parsableByteArray.readInt();
                if (i10 == 1835557187) {
                    parsableByteArray.setPosition(position + 8);
                    parsableByteArray.skipBytes(1);
                    unsignedByte2 = parsableByteArray.readUnsignedByte();
                    parsableByteArray.skipBytes(1);
                    if (Objects.equals(str3, str7)) {
                        i13 = 0;
                        str6 = String.format("mhm1.%02X", Integer.valueOf(unsignedByte2));
                    } else {
                        i13 = 0;
                        str6 = String.format("mha1.%02X", Integer.valueOf(unsignedByte2));
                    }
                    int unsignedShort3 = parsableByteArray.readUnsignedShort();
                    bArr3 = new byte[unsignedShort3];
                    parsableByteArray.readBytes(bArr3, i13, unsignedShort3);
                    if (listOf == null) {
                        listOf = ImmutableList.of(bArr3);
                    } else {
                        listOf = ImmutableList.of(bArr3, listOf.get(i13));
                    }
                    strBuildIamfCodecString = str6;
                } else {
                    unsignedFixedPoint1616 = unsignedFixedPoint1616;
                    str7 = str7;
                    if (i10 == 1835557200) {
                        parsableByteArray.setPosition(position + 8);
                        unsignedByte = parsableByteArray.readUnsignedByte();
                        if (unsignedByte > 0) {
                            bArr2 = new byte[unsignedByte];
                            parsableByteArray.readBytes(bArr2, 0, unsignedByte);
                            if (listOf == null) {
                                listOf = ImmutableList.of(bArr2);
                            } else {
                                listOf = ImmutableList.of(listOf.get(0), bArr2);
                            }
                        }
                    } else {
                        if (i10 != 1702061171 || (z && i10 == 2002876005)) {
                            unsignedFixedPoint1616 = unsignedFixedPoint1616;
                            if (i10 == 1702061171) {
                                iFindBoxPosition = position;
                            } else {
                                iFindBoxPosition = findBoxPosition(parsableByteArray, Mp4Box.TYPE_esds, position, i9);
                            }
                            if (iFindBoxPosition != -1) {
                                esdsFromParent = parseEsdsFromParent(parsableByteArray, iFindBoxPosition);
                                str3 = esdsFromParent.mimeType;
                                bArr = esdsFromParent.initializationData;
                                if (bArr != null) {
                                    if ("audio/vorbis".equals(str3)) {
                                        listOf = VorbisUtil.parseVorbisCsdFromEsdsInitializationData(bArr);
                                    } else {
                                        if ("audio/mp4a-latm".equals(str3)) {
                                            AacUtil.Config audioSpecificConfig = AacUtil.parseAudioSpecificConfig(bArr);
                                            int i17 = audioSpecificConfig.sampleRateHz;
                                            int i18 = audioSpecificConfig.channelCount;
                                            str5 = audioSpecificConfig.codecs;
                                            unsignedFixedPoint1616 = i17;
                                            unsignedShort2 = i18;
                                        } else {
                                            str5 = str4;
                                        }
                                        String str8 = str5;
                                        listOf = ImmutableList.of(bArr);
                                        strBuildIamfCodecString = str8;
                                    }
                                }
                            }
                            position += i9;
                            i15 = i3;
                            str7 = str7;
                            str4 = strBuildIamfCodecString;
                            i14 = i2;
                        } else if (i10 == 1651798644) {
                            btrtFromParent = parseBtrtFromParent(parsableByteArray, position);
                        } else {
                            if (i10 == 1684103987) {
                                parsableByteArray.setPosition(position + 8);
                                stsdData.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitDataCopyWithSchemeType);
                            } else if (i10 == 1684366131) {
                                parsableByteArray.setPosition(position + 8);
                                stsdData.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), str, drmInitDataCopyWithSchemeType);
                            } else {
                                if (i10 == 1684103988) {
                                    parsableByteArray.setPosition(position + 8);
                                    stsdData.format = Ac4Util.parseAc4AnnexEFormat(parsableByteArray, Integer.toString(i4), str, drmInitDataCopyWithSchemeType);
                                } else {
                                    if (i10 == 1684892784) {
                                        if (i6 <= 0) {
                                            throw ParserException.createForMalformedContainer("Invalid sample rate for Dolby TrueHD MLP stream: " + i6, null);
                                        }
                                        strBuildIamfCodecString = str4;
                                        unsignedFixedPoint1616 = i6;
                                        unsignedShort2 = 2;
                                    } else if (i10 == 1684305011 || i10 == 1969517683) {
                                        i11 = unsignedFixedPoint1616;
                                        stsdData.format = new Format.Builder().setId(i4).setSampleMimeType(str3).setChannelCount(unsignedShort2).setSampleRate(i11).setDrmInitData(drmInitDataCopyWithSchemeType).setLanguage(str).build();
                                    } else if (i10 == 1682927731) {
                                        int i19 = i9 - 8;
                                        byte[] bArr4 = opusMagic;
                                        byte[] bArrCopyOf = Arrays.copyOf(bArr4, bArr4.length + i19);
                                        parsableByteArray.setPosition(position + 8);
                                        parsableByteArray.readBytes(bArrCopyOf, bArr4.length, i19);
                                        listOf = OpusUtil.buildInitializationData(bArrCopyOf);
                                    } else if (i10 == 1684425825) {
                                        byte[] bArr5 = new byte[i9 - 8];
                                        bArr5[0] = 102;
                                        bArr5[1] = 76;
                                        bArr5[2] = 97;
                                        bArr5[3] = 67;
                                        parsableByteArray.setPosition(position + 12);
                                        parsableByteArray.readBytes(bArr5, 4, i9 - 12);
                                        listOf = ImmutableList.of(bArr5);
                                    } else if (i10 == 1634492771) {
                                        int i20 = i9 - 12;
                                        byte[] bArr6 = new byte[i20];
                                        parsableByteArray.setPosition(position + 12);
                                        parsableByteArray.readBytes(bArr6, 0, i20);
                                        Pair<Integer, Integer> alacAudioSpecificConfig = CodecSpecificDataUtil.parseAlacAudioSpecificConfig(bArr6);
                                        int iIntValue2 = ((Integer) alacAudioSpecificConfig.first).intValue();
                                        int iIntValue3 = ((Integer) alacAudioSpecificConfig.second).intValue();
                                        unsignedFixedPoint1616 = iIntValue2;
                                        listOf = ImmutableList.of(bArr6);
                                        unsignedShort2 = iIntValue3;
                                    } else if (i10 == 1767990114) {
                                        parsableByteArray.setPosition(position + 9);
                                        int unsignedLeb128ToInt = parsableByteArray.readUnsignedLeb128ToInt();
                                        byte[] bArr7 = new byte[unsignedLeb128ToInt];
                                        parsableByteArray.readBytes(bArr7, 0, unsignedLeb128ToInt);
                                        strBuildIamfCodecString = CodecSpecificDataUtil.buildIamfCodecString(bArr7);
                                        listOf = ImmutableList.of(bArr7);
                                        unsignedFixedPoint1616 = unsignedFixedPoint1616;
                                    } else if (i10 == 1885564227) {
                                        parsableByteArray.setPosition(position + 12);
                                        ByteOrder byteOrder = (parsableByteArray.readUnsignedByte() & 1) != 0 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN;
                                        int unsignedByte3 = parsableByteArray.readUnsignedByte();
                                        if (iIntValue == 1768973165) {
                                            pcmEncoding = Util.getPcmEncoding(unsignedByte3, byteOrder);
                                            i12 = -1;
                                        } else {
                                            pcmEncoding = (iIntValue == 1718641517 && unsignedByte3 == 32 && byteOrder.equals(ByteOrder.LITTLE_ENDIAN)) ? 4 : i16;
                                            i12 = -1;
                                        }
                                        i16 = pcmEncoding;
                                        if (pcmEncoding != i12) {
                                            str3 = "audio/raw";
                                        }
                                    } else {
                                        i11 = unsignedFixedPoint1616;
                                    }
                                    position += i9;
                                    i15 = i3;
                                    str7 = str7;
                                    str4 = strBuildIamfCodecString;
                                    i14 = i2;
                                }
                                strBuildIamfCodecString = str4;
                                unsignedFixedPoint1616 = i11;
                                position += i9;
                                i15 = i3;
                                str7 = str7;
                                str4 = strBuildIamfCodecString;
                                i14 = i2;
                            }
                            i11 = unsignedFixedPoint1616;
                            strBuildIamfCodecString = str4;
                            unsignedFixedPoint1616 = i11;
                            position += i9;
                            i15 = i3;
                            str7 = str7;
                            str4 = strBuildIamfCodecString;
                            i14 = i2;
                        }
                        strBuildIamfCodecString = str4;
                        position += i9;
                        i15 = i3;
                        str7 = str7;
                        str4 = strBuildIamfCodecString;
                        i14 = i2;
                    }
                    strBuildIamfCodecString = str4;
                }
                unsignedFixedPoint1616 = unsignedFixedPoint1616;
                position += i9;
                i15 = i3;
                str7 = str7;
                str4 = strBuildIamfCodecString;
                i14 = i2;
            }
            if (stsdData.format == null || str3 == null) {
            }
            Format.Builder language = new Format.Builder().setId(i4).setSampleMimeType(str3).setCodecs(str4).setChannelCount(unsignedShort2).setSampleRate(unsignedFixedPoint1616).setPcmEncoding(i16).setInitializationData(listOf).setDrmInitData(drmInitDataCopyWithSchemeType).setLanguage(str);
            if (esdsFromParent != null) {
                language.setAverageBitrate(Ints.saturatedCast(esdsFromParent.bitrate)).setPeakBitrate(Ints.saturatedCast(esdsFromParent.peakBitrate));
            } else if (btrtFromParent != null) {
                language.setAverageBitrate(Ints.saturatedCast(btrtFromParent.avgBitrate)).setPeakBitrate(Ints.saturatedCast(btrtFromParent.maxBitrate));
            }
            stsdData.format = language.build();
            return;
        }
        String str9 = str2;
        i8 = i7;
        str3 = str9;
        int i110 = i8;
        str4 = null;
        listOf = null;
        esdsFromParent = null;
        BtrtData btrtFromParent2 = null;
        while (position - i14 < i15) {
            parsableByteArray.setPosition(position);
            i9 = parsableByteArray.readInt();
            if (i9 > 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            ExtractorUtil.checkContainerInput(z2, "childAtomSize must be positive");
            i10 = parsableByteArray.readInt();
            if (i10 == 1835557187) {
                parsableByteArray.setPosition(position + 8);
                parsableByteArray.skipBytes(1);
                unsignedByte2 = parsableByteArray.readUnsignedByte();
                parsableByteArray.skipBytes(1);
                if (Objects.equals(str3, str7)) {
                    i13 = 0;
                    str6 = String.format("mhm1.%02X", Integer.valueOf(unsignedByte2));
                } else {
                    i13 = 0;
                    str6 = String.format("mha1.%02X", Integer.valueOf(unsignedByte2));
                }
                int unsignedShort4 = parsableByteArray.readUnsignedShort();
                bArr3 = new byte[unsignedShort4];
                parsableByteArray.readBytes(bArr3, i13, unsignedShort4);
                if (listOf == null) {
                    listOf = ImmutableList.of(bArr3);
                } else {
                    listOf = ImmutableList.of(bArr3, listOf.get(i13));
                }
                strBuildIamfCodecString = str6;
            } else {
                unsignedFixedPoint1616 = unsignedFixedPoint1616;
                str7 = str7;
                if (i10 == 1835557200) {
                    parsableByteArray.setPosition(position + 8);
                    unsignedByte = parsableByteArray.readUnsignedByte();
                    if (unsignedByte > 0) {
                        bArr2 = new byte[unsignedByte];
                        parsableByteArray.readBytes(bArr2, 0, unsignedByte);
                        if (listOf == null) {
                            listOf = ImmutableList.of(bArr2);
                        } else {
                            listOf = ImmutableList.of(listOf.get(0), bArr2);
                        }
                    }
                } else {
                    if (i10 != 1702061171) {
                        unsignedFixedPoint1616 = unsignedFixedPoint1616;
                        if (i10 == 1702061171) {
                            iFindBoxPosition = position;
                        } else {
                            iFindBoxPosition = findBoxPosition(parsableByteArray, Mp4Box.TYPE_esds, position, i9);
                        }
                        if (iFindBoxPosition != -1) {
                            esdsFromParent = parseEsdsFromParent(parsableByteArray, iFindBoxPosition);
                            str3 = esdsFromParent.mimeType;
                            bArr = esdsFromParent.initializationData;
                            if (bArr != null) {
                                strBuildIamfCodecString = str4;
                            } else if ("audio/vorbis".equals(str3)) {
                                listOf = VorbisUtil.parseVorbisCsdFromEsdsInitializationData(bArr);
                                strBuildIamfCodecString = str4;
                            } else {
                                if ("audio/mp4a-latm".equals(str3)) {
                                    AacUtil.Config audioSpecificConfig2 = AacUtil.parseAudioSpecificConfig(bArr);
                                    int i111 = audioSpecificConfig2.sampleRateHz;
                                    int i112 = audioSpecificConfig2.channelCount;
                                    str5 = audioSpecificConfig2.codecs;
                                    unsignedFixedPoint1616 = i111;
                                    unsignedShort2 = i112;
                                } else {
                                    str5 = str4;
                                }
                                String str10 = str5;
                                listOf = ImmutableList.of(bArr);
                                strBuildIamfCodecString = str10;
                            }
                        } else {
                            strBuildIamfCodecString = str4;
                        }
                    } else {
                        unsignedFixedPoint1616 = unsignedFixedPoint1616;
                        if (i10 == 1702061171) {
                            iFindBoxPosition = position;
                        } else {
                            iFindBoxPosition = findBoxPosition(parsableByteArray, Mp4Box.TYPE_esds, position, i9);
                        }
                        if (iFindBoxPosition != -1) {
                            esdsFromParent = parseEsdsFromParent(parsableByteArray, iFindBoxPosition);
                            str3 = esdsFromParent.mimeType;
                            bArr = esdsFromParent.initializationData;
                            if (bArr != null) {
                                strBuildIamfCodecString = str4;
                            } else if ("audio/vorbis".equals(str3)) {
                                listOf = VorbisUtil.parseVorbisCsdFromEsdsInitializationData(bArr);
                                strBuildIamfCodecString = str4;
                            } else {
                                if ("audio/mp4a-latm".equals(str3)) {
                                    AacUtil.Config audioSpecificConfig3 = AacUtil.parseAudioSpecificConfig(bArr);
                                    int i113 = audioSpecificConfig3.sampleRateHz;
                                    int i114 = audioSpecificConfig3.channelCount;
                                    str5 = audioSpecificConfig3.codecs;
                                    unsignedFixedPoint1616 = i113;
                                    unsignedShort2 = i114;
                                } else {
                                    str5 = str4;
                                }
                                String str11 = str5;
                                listOf = ImmutableList.of(bArr);
                                strBuildIamfCodecString = str11;
                            }
                        } else {
                            strBuildIamfCodecString = str4;
                        }
                    }
                    position += i9;
                    i15 = i3;
                    str7 = str7;
                    str4 = strBuildIamfCodecString;
                    i14 = i2;
                }
                strBuildIamfCodecString = str4;
            }
            unsignedFixedPoint1616 = unsignedFixedPoint1616;
            position += i9;
            i15 = i3;
            str7 = str7;
            str4 = strBuildIamfCodecString;
            i14 = i2;
        }
        if (stsdData.format == null) {
        }
    }

    private static int findBoxPosition(ParsableByteArray parsableByteArray, int i, int i2, int i3) throws ParserException {
        int position = parsableByteArray.getPosition();
        ExtractorUtil.checkContainerInput(position >= i2, null);
        while (position - i2 < i3) {
            parsableByteArray.setPosition(position);
            int i4 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i4 > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == i) {
                return position;
            }
            position += i4;
        }
        return -1;
    }

    private static EsdsData parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 12);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if ((unsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((unsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedByte());
        }
        if ((unsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if ("audio/mpeg".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts".equals(mimeTypeFromMp4ObjectType) || "audio/vnd.dts.hd".equals(mimeTypeFromMp4ObjectType)) {
            return new EsdsData(mimeTypeFromMp4ObjectType, null, -1L, -1L);
        }
        parsableByteArray.skipBytes(4);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        long unsignedInt2 = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(1);
        int expandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[expandableClassSize];
        parsableByteArray.readBytes(bArr, 0, expandableClassSize);
        return new EsdsData(mimeTypeFromMp4ObjectType, bArr, unsignedInt2 > 0 ? unsignedInt2 : -1L, unsignedInt > 0 ? unsignedInt : -1L);
    }

    private static BtrtData parseBtrtFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        parsableByteArray.skipBytes(4);
        return new BtrtData(parsableByteArray.readUnsignedInt(), parsableByteArray.readUnsignedInt());
    }

    static VexuData parseVideoExtendedUsageBox(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        parsableByteArray.setPosition(i + 8);
        int position = parsableByteArray.getPosition();
        EyesData stereoViewBox = null;
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int i3 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i3 > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1702454643) {
                stereoViewBox = parseStereoViewBox(parsableByteArray, position, i3);
            }
            position += i3;
        }
        if (stereoViewBox == null) {
            return null;
        }
        return new VexuData(stereoViewBox);
    }

    private static EyesData parseStereoViewBox(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        parsableByteArray.setPosition(i + 8);
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int i3 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i3 > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1937011305) {
                parsableByteArray.skipBytes(4);
                int unsignedByte = parsableByteArray.readUnsignedByte();
                return new EyesData(new StriData((unsignedByte & 1) == 1, (unsignedByte & 2) == 2, (unsignedByte & 8) == 8));
            }
            position += i3;
        }
        return null;
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        Pair<Integer, TrackEncryptionBox> commonEncryptionSinfFromParent;
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int i3 = parsableByteArray.readInt();
            ExtractorUtil.checkContainerInput(i3 > 0, "childAtomSize must be positive");
            if (parsableByteArray.readInt() == 1936289382 && (commonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, i3)) != null) {
                return commonEncryptionSinfFromParent;
            }
            position += i3;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) throws ParserException {
        int i3 = i + 8;
        String string = null;
        Integer numValueOf = null;
        int i4 = -1;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i6 = parsableByteArray.readInt();
            int i7 = parsableByteArray.readInt();
            if (i7 == 1718775137) {
                numValueOf = Integer.valueOf(parsableByteArray.readInt());
            } else if (i7 == 1935894637) {
                parsableByteArray.skipBytes(4);
                string = parsableByteArray.readString(4);
            } else if (i7 == 1935894633) {
                i4 = i3;
                i5 = i6;
            }
            i3 += i6;
        }
        if (!C.CENC_TYPE_cenc.equals(string) && !C.CENC_TYPE_cbc1.equals(string) && !C.CENC_TYPE_cens.equals(string) && !C.CENC_TYPE_cbcs.equals(string)) {
            return null;
        }
        ExtractorUtil.checkContainerInput(numValueOf != null, "frma atom is mandatory");
        ExtractorUtil.checkContainerInput(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox schiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, string);
        ExtractorUtil.checkContainerInput(schiFromParent != null, "tenc atom is mandatory");
        return Pair.create(numValueOf, (TrackEncryptionBox) Util.castNonNull(schiFromParent));
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        int i5 = i + 8;
        while (true) {
            byte[] bArr = null;
            if (i5 - i >= i2) {
                return null;
            }
            parsableByteArray.setPosition(i5);
            int i6 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1952804451) {
                int fullBoxVersion = parseFullBoxVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (fullBoxVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int unsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = unsignedByte & 15;
                    i4 = (unsignedByte & 240) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int unsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, 16);
                if (z && unsignedByte2 == 0) {
                    int unsignedByte3 = parsableByteArray.readUnsignedByte();
                    bArr = new byte[unsignedByte3];
                    parsableByteArray.readBytes(bArr, 0, unsignedByte3);
                }
                return new TrackEncryptionBox(z, str, unsignedByte2, bArr2, i4, i3, bArr);
            }
            i5 += i6;
        }
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == 1886547818) {
                return Arrays.copyOfRange(parsableByteArray.getData(), i3, i4 + i3);
            }
            i3 += i4;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i = unsignedByte & 127;
        while ((unsignedByte & 128) == 128) {
            unsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (unsignedByte & 127);
        }
        return i;
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length - 1;
        return jArr[0] <= j2 && j2 < jArr[Util.constrainValue(4, 0, length)] && jArr[Util.constrainValue(jArr.length - 4, 0, length)] < j3 && j3 <= j;
    }

    private BoxParser() {
    }

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) throws ParserException {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            ExtractorUtil.checkContainerInput(parsableByteArray.readInt() == 1, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long unsignedInt;
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                unsignedInt = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                unsignedInt = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = unsignedInt;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private static final class TkhdData {
        private final int alternateGroup;
        private final long duration;
        private final int height;
        private final int id;
        private final int rotationDegrees;
        private final int width;

        public TkhdData(int i, long j, int i2, int i3, int i4, int i5) {
            this.id = i;
            this.duration = j;
            this.alternateGroup = i2;
            this.rotationDegrees = i3;
            this.width = i4;
            this.height = i5;
        }
    }

    private static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    private static final class EsdsData {
        private final long bitrate;
        private final byte[] initializationData;
        private final String mimeType;
        private final long peakBitrate;

        public EsdsData(String str, byte[] bArr, long j, long j2) {
            this.mimeType = str;
            this.initializationData = bArr;
            this.bitrate = j;
            this.peakBitrate = j2;
        }
    }

    private static final class BtrtData {
        private final long avgBitrate;
        private final long maxBitrate;

        public BtrtData(long j, long j2) {
            this.avgBitrate = j;
            this.maxBitrate = j2;
        }
    }

    private static final class StriData {
        private final boolean eyeViewsReversed;
        private final boolean hasLeftEyeView;
        private final boolean hasRightEyeView;

        public StriData(boolean z, boolean z2, boolean z3) {
            this.hasLeftEyeView = z;
            this.hasRightEyeView = z2;
            this.eyeViewsReversed = z3;
        }
    }

    private static final class EyesData {
        private final StriData striData;

        public EyesData(StriData striData) {
            this.striData = striData;
        }
    }

    private static final class MdhdData {
        private final String language;
        private final long mediaDurationUs;
        private final long timescale;

        public MdhdData(long j, long j2, String str) {
            this.timescale = j;
            this.mediaDurationUs = j2;
            this.language = str;
        }
    }

    static final class VexuData {
        private final EyesData eyesData;

        public VexuData(EyesData eyesData) {
            this.eyesData = eyesData;
        }

        public boolean hasBothEyeViews() {
            EyesData eyesData = this.eyesData;
            return eyesData != null && eyesData.striData.hasLeftEyeView && this.eyesData.striData.hasRightEyeView;
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Mp4Box.LeafBox leafBox, Format format) {
            ParsableByteArray parsableByteArray = leafBox.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if ("audio/raw".equals(format.sampleMimeType)) {
                int pcmFrameSize = Util.getPcmFrameSize(format.pcmEncoding, format.channelCount);
                if (unsignedIntToInt == 0 || unsignedIntToInt % pcmFrameSize != 0) {
                    Log.w(BoxParser.TAG, "Audio sample size mismatch. stsd sample size: " + pcmFrameSize + ", stsz sample size: " + unsignedIntToInt);
                    unsignedIntToInt = pcmFrameSize;
                }
            }
            this.fixedSampleSize = unsignedIntToInt == 0 ? -1 : unsignedIntToInt;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getFixedSampleSize() {
            return this.fixedSampleSize;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == -1 ? this.data.readUnsignedIntToInt() : i;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getFixedSampleSize() {
            return -1;
        }

        public Stz2SampleSizeBox(Mp4Box.LeafBox leafBox) {
            ParsableByteArray parsableByteArray = leafBox.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = parsableByteArray.readUnsignedIntToInt() & 255;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // androidx.media3.extractor.mp4.BoxParser.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 == 0) {
                int unsignedByte = this.data.readUnsignedByte();
                this.currentByte = unsignedByte;
                return (unsignedByte & 240) >> 4;
            }
            return this.currentByte & 15;
        }
    }
}
