package com.google.android.exoplayer.extractor.mp4;

import android.util.Pair;
import androidx.core.location.LocationRequestCompat;
import androidx.media3.extractor.ts.TsExtractor;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.GaplessInfo;
import com.google.android.exoplayer.util.Ac3Util;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.CodecSpecificDataUtil;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.util.Util;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import okhttp3.internal.http.HttpStatusCodesKt;
import org.videolan.libvlc.MediaPlayer;

/* JADX INFO: loaded from: classes4.dex */
final class AtomParsers {

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    public static Track parseTrak(Atom.ContainerAtom containerAtom, Atom.LeafAtom leafAtom, long j, boolean z) {
        Atom.ContainerAtom containerAtomOfType = containerAtom.getContainerAtomOfType(Atom.TYPE_mdia);
        int hdlr = parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data);
        if (hdlr != Track.TYPE_soun && hdlr != Track.TYPE_vide && hdlr != Track.TYPE_text && hdlr != Track.TYPE_sbtl && hdlr != Track.TYPE_subt) {
            return null;
        }
        TkhdData tkhd = parseTkhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        long j2 = j == -1 ? tkhd.duration : j;
        long mvhd = parseMvhd(leafAtom.data);
        long jScaleLargeTimestamp = j2 != -1 ? Util.scaleLargeTimestamp(j2, 1000000L, mvhd) : -1L;
        Atom.ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair<Long, String> mdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData stsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, tkhd.id, jScaleLargeTimestamp, tkhd.rotationDegrees, (String) mdhd.second, z);
        Pair<long[], long[]> edts = parseEdts(containerAtom.getContainerAtomOfType(Atom.TYPE_edts));
        if (stsd.mediaFormat == null) {
            return null;
        }
        return new Track(tkhd.id, hdlr, ((Long) mdhd.first).longValue(), mvhd, jScaleLargeTimestamp, stsd.mediaFormat, stsd.trackEncryptionBoxes, stsd.nalUnitLengthFieldLength, (long[]) edts.first, (long[]) edts.second);
    }

    public static TrackSampleTable parseStbl(Track track, Atom.ContainerAtom containerAtom) throws ParserException {
        SampleSizeBox stz2SampleSizeBox;
        boolean z;
        int unsignedIntToInt;
        int unsignedIntToInt2;
        int i;
        long[] jArr;
        int[] iArr;
        int i2;
        long[] jArr2;
        int[] iArr2;
        long[] jArr3;
        long[] jArr4;
        int[] iArr3;
        long[] jArr5;
        int[] iArr4;
        int unsignedIntToInt3;
        int unsignedIntToInt4;
        Atom.LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_stsz);
        if (leafAtomOfType != null) {
            stz2SampleSizeBox = new StszSampleSizeBox(leafAtomOfType);
        } else {
            Atom.LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stz2);
            if (leafAtomOfType2 == null) {
                throw new ParserException("Track has no sample table size information");
            }
            stz2SampleSizeBox = new Stz2SampleSizeBox(leafAtomOfType2);
        }
        int sampleCount = stz2SampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            return new TrackSampleTable(new long[0], new int[0], 0, new long[0], new int[0]);
        }
        Atom.LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stco);
        if (leafAtomOfType3 == null) {
            leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_co64);
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType3.data;
        ParsableByteArray parsableByteArray2 = containerAtom.getLeafAtomOfType(Atom.TYPE_stsc).data;
        ParsableByteArray parsableByteArray3 = containerAtom.getLeafAtomOfType(Atom.TYPE_stts).data;
        Atom.LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray parsableByteArray4 = null;
        ParsableByteArray parsableByteArray5 = leafAtomOfType4 != null ? leafAtomOfType4.data : null;
        Atom.LeafAtom leafAtomOfType5 = containerAtom.getLeafAtomOfType(Atom.TYPE_ctts);
        ParsableByteArray parsableByteArray6 = leafAtomOfType5 != null ? leafAtomOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z);
        parsableByteArray3.setPosition(12);
        int unsignedIntToInt5 = parsableByteArray3.readUnsignedIntToInt() - 1;
        int unsignedIntToInt6 = parsableByteArray3.readUnsignedIntToInt();
        int unsignedIntToInt7 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray6 != null) {
            parsableByteArray6.setPosition(12);
            unsignedIntToInt = parsableByteArray6.readUnsignedIntToInt();
        } else {
            unsignedIntToInt = 0;
        }
        int unsignedIntToInt8 = -1;
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            unsignedIntToInt2 = parsableByteArray5.readUnsignedIntToInt();
            if (unsignedIntToInt2 > 0) {
                unsignedIntToInt8 = parsableByteArray5.readUnsignedIntToInt() - 1;
                parsableByteArray4 = parsableByteArray5;
            }
        } else {
            parsableByteArray4 = parsableByteArray5;
            unsignedIntToInt2 = 0;
        }
        long j = 0;
        if (!(stz2SampleSizeBox.isFixedSampleSize() && "audio/raw".equals(track.mediaFormat.mimeType) && unsignedIntToInt5 == 0 && unsignedIntToInt == 0 && unsignedIntToInt2 == 0)) {
            long[] jArr6 = new long[sampleCount];
            iArr = new int[sampleCount];
            long[] jArr7 = new long[sampleCount];
            int i3 = unsignedIntToInt2;
            int[] iArr5 = new int[sampleCount];
            long j2 = 0;
            long j3 = 0;
            int i4 = 0;
            i2 = 0;
            int unsignedIntToInt9 = 0;
            int i5 = 0;
            int i6 = 0;
            int i7 = i3;
            int i8 = unsignedIntToInt;
            int i9 = unsignedIntToInt7;
            int i10 = unsignedIntToInt6;
            int i11 = unsignedIntToInt5;
            while (i4 < sampleCount) {
                while (i5 == 0) {
                    Assertions.checkState(chunkIterator.moveNext());
                    j2 = chunkIterator.offset;
                    i5 = chunkIterator.numSamples;
                    i10 = i10;
                    i9 = i9;
                }
                int i12 = i10;
                int i13 = i9;
                int i14 = i8;
                if (parsableByteArray6 != null) {
                    while (unsignedIntToInt9 == 0 && i14 > 0) {
                        unsignedIntToInt9 = parsableByteArray6.readUnsignedIntToInt();
                        i6 = parsableByteArray6.readInt();
                        i14--;
                    }
                    unsignedIntToInt9--;
                }
                int i15 = i6;
                jArr6[i4] = j2;
                long[] jArr8 = jArr6;
                int nextSampleSize = stz2SampleSizeBox.readNextSampleSize();
                iArr[i4] = nextSampleSize;
                int i16 = sampleCount;
                if (nextSampleSize > i2) {
                    i2 = nextSampleSize;
                }
                SampleSizeBox sampleSizeBox = stz2SampleSizeBox;
                jArr7[i4] = j3 + ((long) i15);
                iArr5[i4] = parsableByteArray4 == null ? 1 : 0;
                if (i4 == unsignedIntToInt8) {
                    iArr5[i4] = 1;
                    i7--;
                    if (i7 > 0) {
                        unsignedIntToInt8 = parsableByteArray4.readUnsignedIntToInt() - 1;
                    }
                }
                long[] jArr9 = jArr7;
                int[] iArr6 = iArr5;
                j3 += (long) i13;
                int i17 = i12 - 1;
                if (i17 != 0 || i11 <= 0) {
                    unsignedIntToInt3 = i13;
                    unsignedIntToInt4 = i17;
                } else {
                    unsignedIntToInt4 = parsableByteArray3.readUnsignedIntToInt();
                    unsignedIntToInt3 = parsableByteArray3.readUnsignedIntToInt();
                    i11--;
                }
                int i18 = unsignedIntToInt4;
                j2 += (long) iArr[i4];
                i5--;
                i4++;
                stz2SampleSizeBox = sampleSizeBox;
                jArr6 = jArr8;
                sampleCount = i16;
                jArr7 = jArr9;
                i6 = i15;
                i9 = unsignedIntToInt3;
                iArr5 = iArr6;
                int i19 = i14;
                i10 = i18;
                i8 = i19;
            }
            i = sampleCount;
            long[] jArr10 = jArr6;
            int[] iArr7 = iArr5;
            long[] jArr11 = jArr7;
            int i20 = i10;
            Assertions.checkArgument(unsignedIntToInt9 == 0);
            for (int i21 = i8; i21 > 0; i21--) {
                Assertions.checkArgument(parsableByteArray6.readUnsignedIntToInt() == 0);
                parsableByteArray6.readInt();
            }
            Assertions.checkArgument(i7 == 0);
            Assertions.checkArgument(i20 == 0);
            Assertions.checkArgument(i5 == 0);
            Assertions.checkArgument(i11 == 0);
            jArr = jArr10;
            iArr2 = iArr7;
            jArr2 = jArr11;
        } else {
            i = sampleCount;
            SampleSizeBox sampleSizeBox2 = stz2SampleSizeBox;
            long[] jArr12 = new long[chunkIterator.length];
            int[] iArr8 = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr12[chunkIterator.index] = chunkIterator.offset;
                iArr8[chunkIterator.index] = chunkIterator.numSamples;
            }
            FixedSampleSizeRechunker.Results resultsRechunk = FixedSampleSizeRechunker.rechunk(sampleSizeBox2.readNextSampleSize(), jArr12, iArr8, unsignedIntToInt7);
            jArr = resultsRechunk.offsets;
            iArr = resultsRechunk.sizes;
            i2 = resultsRechunk.maximumSize;
            jArr2 = resultsRechunk.timestamps;
            iArr2 = resultsRechunk.flags;
        }
        int i22 = i2;
        if (track.editListDurations == null) {
            Util.scaleLargeTimestampsInPlace(jArr2, 1000000L, track.timescale);
            return new TrackSampleTable(jArr, iArr, i22, jArr2, iArr2);
        }
        if (track.editListDurations.length == 1) {
            char c = 0;
            if (track.editListDurations[0] == 0) {
                int i23 = 0;
                while (i23 < jArr2.length) {
                    jArr2[i23] = Util.scaleLargeTimestamp(jArr2[i23] - track.editListMediaTimes[c], 1000000L, track.timescale);
                    i23++;
                    c = 0;
                }
                return new TrackSampleTable(jArr, iArr, i22, jArr2, iArr2);
            }
        }
        boolean z2 = false;
        int i24 = 0;
        int i25 = 0;
        for (int i26 = 0; i26 < track.editListDurations.length; i26++) {
            long j4 = track.editListMediaTimes[i26];
            if (j4 != -1) {
                long jScaleLargeTimestamp = Util.scaleLargeTimestamp(track.editListDurations[i26], track.timescale, track.movieTimescale);
                int iBinarySearchCeil = Util.binarySearchCeil(jArr2, j4, true, true);
                int iBinarySearchCeil2 = Util.binarySearchCeil(jArr2, j4 + jScaleLargeTimestamp, true, false);
                i24 += iBinarySearchCeil2 - iBinarySearchCeil;
                z2 |= i25 != iBinarySearchCeil;
                i25 = iBinarySearchCeil2;
            }
        }
        boolean z3 = (i24 != i) | z2;
        long[] jArr13 = z3 ? new long[i24] : jArr;
        int[] iArr9 = z3 ? new int[i24] : iArr;
        if (z3) {
            i22 = 0;
        }
        int[] iArr10 = z3 ? new int[i24] : iArr2;
        long[] jArr14 = new long[i24];
        int i27 = i22;
        int i28 = 0;
        int i29 = 0;
        while (i28 < track.editListDurations.length) {
            long j5 = track.editListMediaTimes[i28];
            long j6 = track.editListDurations[i28];
            if (j5 != -1) {
                int[] iArr11 = iArr10;
                long jScaleLargeTimestamp2 = Util.scaleLargeTimestamp(j6, track.timescale, track.movieTimescale) + j5;
                int iBinarySearchCeil3 = Util.binarySearchCeil(jArr2, j5, true, true);
                jArr3 = jArr14;
                int iBinarySearchCeil4 = Util.binarySearchCeil(jArr2, jScaleLargeTimestamp2, true, false);
                if (z3) {
                    int i30 = iBinarySearchCeil4 - iBinarySearchCeil3;
                    System.arraycopy(jArr, iBinarySearchCeil3, jArr13, i29, i30);
                    System.arraycopy(iArr, iBinarySearchCeil3, iArr9, i29, i30);
                    iArr4 = iArr11;
                    System.arraycopy(iArr2, iBinarySearchCeil3, iArr4, i29, i30);
                } else {
                    iArr4 = iArr11;
                }
                int i31 = i27;
                while (iBinarySearchCeil3 < iBinarySearchCeil4) {
                    long[] jArr15 = jArr;
                    int[] iArr12 = iArr2;
                    long[] jArr16 = jArr2;
                    jArr3[i29] = Util.scaleLargeTimestamp(j, 1000000L, track.movieTimescale) + Util.scaleLargeTimestamp(jArr2[iBinarySearchCeil3] - j5, 1000000L, track.timescale);
                    if (z3 && iArr9[i29] > i31) {
                        i31 = iArr[iBinarySearchCeil3];
                    }
                    i29++;
                    iBinarySearchCeil3++;
                    jArr = jArr15;
                    jArr2 = jArr16;
                    iArr2 = iArr12;
                }
                jArr4 = jArr;
                iArr3 = iArr2;
                jArr5 = jArr2;
                i27 = i31;
            } else {
                jArr3 = jArr14;
                jArr4 = jArr;
                iArr3 = iArr2;
                jArr5 = jArr2;
                iArr4 = iArr10;
            }
            j += j6;
            i28++;
            jArr = jArr4;
            iArr10 = iArr4;
            jArr2 = jArr5;
            iArr2 = iArr3;
            jArr14 = jArr3;
        }
        long[] jArr17 = jArr14;
        int[] iArr13 = iArr10;
        boolean z4 = false;
        for (int i32 = 0; i32 < iArr13.length && !z4; i32++) {
            z4 |= (iArr13[i32] & 1) != 0;
        }
        if (!z4) {
            throw new ParserException("The edited sample sequence does not contain a sync sample.");
        }
        return new TrackSampleTable(jArr13, iArr9, i27, jArr17, iArr13);
    }

    public static GaplessInfo parseUdta(Atom.LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int i = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(parsableByteArray.getPosition() - 8);
                parsableByteArray.setLimit(parsableByteArray.getPosition() + i);
                return parseMetaAtom(parsableByteArray);
            }
            parsableByteArray.skipBytes(i - 8);
        }
        return null;
    }

    private static GaplessInfo parseMetaAtom(ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(12);
        ParsableByteArray parsableByteArray2 = new ParsableByteArray();
        while (parsableByteArray.bytesLeft() >= 8) {
            int i = parsableByteArray.readInt() - 8;
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray2.reset(parsableByteArray.data, parsableByteArray.getPosition() + i);
                parsableByteArray2.setPosition(parsableByteArray.getPosition());
                GaplessInfo ilst = parseIlst(parsableByteArray2);
                if (ilst != null) {
                    return ilst;
                }
            }
            parsableByteArray.skipBytes(i);
        }
        return null;
    }

    private static GaplessInfo parseIlst(ParsableByteArray parsableByteArray) {
        while (true) {
            String string = null;
            if (parsableByteArray.bytesLeft() <= 0) {
                return null;
            }
            int position = parsableByteArray.getPosition() + parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_DASHES) {
                String string2 = null;
                String string3 = null;
                while (parsableByteArray.getPosition() < position) {
                    int i = parsableByteArray.readInt();
                    int i2 = i - 12;
                    int i3 = parsableByteArray.readInt();
                    parsableByteArray.skipBytes(4);
                    if (i3 == Atom.TYPE_mean) {
                        string3 = parsableByteArray.readString(i2);
                    } else if (i3 == Atom.TYPE_name) {
                        string = parsableByteArray.readString(i2);
                    } else if (i3 == Atom.TYPE_data) {
                        parsableByteArray.skipBytes(4);
                        string2 = parsableByteArray.readString(i - 16);
                    } else {
                        parsableByteArray.skipBytes(i2);
                    }
                }
                if (string != null && string2 != null && "com.apple.iTunes".equals(string3)) {
                    return GaplessInfo.createFromComment(string, string2);
                }
            } else {
                parsableByteArray.setPosition(position);
            }
        }
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        parsableByteArray.skipBytes(Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0 ? 16 : 8);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        long j;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        int i = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        int i2 = fullAtomVersion == 0 ? 4 : 8;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            j = -1;
            if (i4 < i2) {
                if (parsableByteArray.data[position + i4] != -1) {
                    long unsignedInt = fullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
                    if (unsignedInt == 0) {
                        break;
                    }
                    j = unsignedInt;
                    break;
                }
                i4++;
            } else {
                parsableByteArray.skipBytes(i2);
                break;
            }
        }
        parsableByteArray.skipBytes(16);
        int i5 = parsableByteArray.readInt();
        int i6 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int i7 = parsableByteArray.readInt();
        int i8 = parsableByteArray.readInt();
        if (i5 == 0 && i6 == 65536 && i7 == -65536 && i8 == 0) {
            i3 = 90;
        } else if (i5 == 0 && i6 == -65536 && i7 == 65536 && i8 == 0) {
            i3 = MediaPlayer.Event.PausableChanged;
        } else if (i5 == -65536 && i6 == 0 && i7 == 0 && i8 == -65536) {
            i3 = Opcodes.GETFIELD;
        }
        return new TkhdData(i, j, i3);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 8 : 16);
        long unsignedInt = parsableByteArray.readUnsignedInt();
        parsableByteArray.skipBytes(fullAtomVersion == 0 ? 4 : 8);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        return Pair.create(Long.valueOf(unsignedInt), "" + ((char) (((unsignedShort >> 10) & 31) + 96)) + ((char) (((unsignedShort >> 5) & 31) + 96)) + ((char) ((unsignedShort & 31) + 96)));
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, long j, int i2, String str, boolean z) {
        parsableByteArray.setPosition(12);
        int i3 = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(i3);
        for (int i4 = 0; i4 < i3; i4++) {
            int position = parsableByteArray.getPosition();
            int i5 = parsableByteArray.readInt();
            Assertions.checkArgument(i5 > 0, "childAtomSize should be positive");
            int i6 = parsableByteArray.readInt();
            if (i6 == Atom.TYPE_avc1 || i6 == Atom.TYPE_avc3 || i6 == Atom.TYPE_encv || i6 == Atom.TYPE_mp4v || i6 == Atom.TYPE_hvc1 || i6 == Atom.TYPE_hev1 || i6 == Atom.TYPE_s263 || i6 == Atom.TYPE_vp08 || i6 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray, i6, position, i5, i, j, i2, stsdData, i4);
            } else if (i6 == Atom.TYPE_mp4a || i6 == Atom.TYPE_enca || i6 == Atom.TYPE_ac_3 || i6 == Atom.TYPE_ec_3 || i6 == Atom.TYPE_dtsc || i6 == Atom.TYPE_dtse || i6 == Atom.TYPE_dtsh || i6 == Atom.TYPE_dtsl || i6 == Atom.TYPE_samr || i6 == Atom.TYPE_sawb || i6 == Atom.TYPE_lpcm || i6 == Atom.TYPE_sowt) {
                parseAudioSampleEntry(parsableByteArray, i6, position, i5, i, j, str, z, stsdData, i4);
            } else if (i6 == Atom.TYPE_TTML) {
                stsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(i), "application/ttml+xml", -1, j, str);
            } else if (i6 == Atom.TYPE_tx3g) {
                stsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(i), "application/x-quicktime-tx3g", -1, j, str);
            } else if (i6 == Atom.TYPE_wvtt) {
                stsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(i), MimeTypes.APPLICATION_MP4VTT, -1, j, str);
            } else if (i6 == Atom.TYPE_stpp) {
                stsdData.mediaFormat = MediaFormat.createTextFormat(Integer.toString(i), "application/ttml+xml", -1, j, str, 0L);
            }
            parsableByteArray.setPosition(position + i5);
        }
        return stsdData;
    }

    private static void parseVideoSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, long j, int i5, StsdData stsdData, int i6) {
        parsableByteArray.setPosition(i2 + 8);
        parsableByteArray.skipBytes(24);
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int unsignedShort2 = parsableByteArray.readUnsignedShort();
        parsableByteArray.skipBytes(50);
        int position = parsableByteArray.getPosition();
        if (i == Atom.TYPE_encv) {
            parseSampleEntryEncryptionData(parsableByteArray, i2, i3, stsdData, i6);
            parsableByteArray.setPosition(position);
        }
        String str = null;
        List<byte[]> listSingletonList = null;
        byte[] projFromParent = null;
        boolean z = false;
        float paspFromParent = 1.0f;
        int i7 = -1;
        while (position - i2 < i3) {
            parsableByteArray.setPosition(position);
            int position2 = parsableByteArray.getPosition();
            int i8 = parsableByteArray.readInt();
            if (i8 == 0 && parsableByteArray.getPosition() - i2 == i3) {
                break;
            }
            Assertions.checkArgument(i8 > 0, "childAtomSize should be positive");
            int i9 = parsableByteArray.readInt();
            if (i9 == Atom.TYPE_avcC) {
                Assertions.checkState(str == null);
                AvcCData avcCFromParent = parseAvcCFromParent(parsableByteArray, position2);
                listSingletonList = avcCFromParent.initializationData;
                stsdData.nalUnitLengthFieldLength = avcCFromParent.nalUnitLengthFieldLength;
                if (!z) {
                    paspFromParent = avcCFromParent.pixelWidthAspectRatio;
                }
                str = "video/avc";
            } else if (i9 == Atom.TYPE_hvcC) {
                Assertions.checkState(str == null);
                Pair<List<byte[]>, Integer> hvcCFromParent = parseHvcCFromParent(parsableByteArray, position2);
                listSingletonList = (List) hvcCFromParent.first;
                stsdData.nalUnitLengthFieldLength = ((Integer) hvcCFromParent.second).intValue();
                str = "video/hevc";
            } else if (i9 == Atom.TYPE_d263) {
                Assertions.checkState(str == null);
                str = "video/3gpp";
            } else if (i9 == Atom.TYPE_esds) {
                Assertions.checkState(str == null);
                Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, position2);
                str = (String) esdsFromParent.first;
                listSingletonList = Collections.singletonList(esdsFromParent.second);
            } else if (i9 == Atom.TYPE_pasp) {
                paspFromParent = parsePaspFromParent(parsableByteArray, position2);
                z = true;
            } else if (i9 == Atom.TYPE_vpcC) {
                Assertions.checkState(str == null);
                str = i == Atom.TYPE_vp08 ? "video/x-vnd.on2.vp8" : "video/x-vnd.on2.vp9";
            } else if (i9 == Atom.TYPE_sv3d) {
                projFromParent = parseProjFromParent(parsableByteArray, position2, i8);
            } else if (i9 == Atom.TYPE_st3d) {
                int unsignedByte = parsableByteArray.readUnsignedByte();
                parsableByteArray.skipBytes(3);
                if (unsignedByte == 0) {
                    int unsignedByte2 = parsableByteArray.readUnsignedByte();
                    if (unsignedByte2 == 0) {
                        i7 = 0;
                    } else if (unsignedByte2 == 1) {
                        i7 = 1;
                    } else if (unsignedByte2 == 2) {
                        i7 = 2;
                    }
                }
            }
            position += i8;
        }
        if (str == null) {
            return;
        }
        stsdData.mediaFormat = MediaFormat.createVideoFormat(Integer.toString(i4), str, -1, -1, j, unsignedShort, unsignedShort2, listSingletonList, i5, paspFromParent, projFromParent, i7);
    }

    private static AvcCData parseAvcCFromParent(ParsableByteArray parsableByteArray, int i) {
        float f;
        parsableByteArray.setPosition(i + 12);
        int unsignedByte = parsableByteArray.readUnsignedByte() & 3;
        int i2 = unsignedByte + 1;
        if (i2 == 3) {
            throw new IllegalStateException();
        }
        ArrayList arrayList = new ArrayList();
        int unsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
        for (int i3 = 0; i3 < unsignedByte2; i3++) {
            arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
        }
        int unsignedByte3 = parsableByteArray.readUnsignedByte();
        for (int i4 = 0; i4 < unsignedByte3; i4++) {
            arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
        }
        if (unsignedByte2 > 0) {
            ParsableBitArray parsableBitArray = new ParsableBitArray((byte[]) arrayList.get(0));
            parsableBitArray.setPosition((unsignedByte + 2) * 8);
            f = NalUnitUtil.parseSpsNalUnit(parsableBitArray).pixelWidthAspectRatio;
        } else {
            f = 1.0f;
        }
        return new AvcCData(arrayList, i2, f);
    }

    private static Pair<List<byte[]>, Integer> parseHvcCFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 29);
        int unsignedByte = parsableByteArray.readUnsignedByte() & 3;
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        int position = parsableByteArray.getPosition();
        int i2 = 0;
        for (int i3 = 0; i3 < unsignedByte2; i3++) {
            parsableByteArray.skipBytes(1);
            int unsignedShort = parsableByteArray.readUnsignedShort();
            for (int i4 = 0; i4 < unsignedShort; i4++) {
                int unsignedShort2 = parsableByteArray.readUnsignedShort();
                i2 += unsignedShort2 + 4;
                parsableByteArray.skipBytes(unsignedShort2);
            }
        }
        parsableByteArray.setPosition(position);
        byte[] bArr = new byte[i2];
        int i5 = 0;
        for (int i6 = 0; i6 < unsignedByte2; i6++) {
            parsableByteArray.skipBytes(1);
            int unsignedShort3 = parsableByteArray.readUnsignedShort();
            for (int i7 = 0; i7 < unsignedShort3; i7++) {
                int unsignedShort4 = parsableByteArray.readUnsignedShort();
                System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, bArr, i5, NalUnitUtil.NAL_START_CODE.length);
                int length = i5 + NalUnitUtil.NAL_START_CODE.length;
                System.arraycopy(parsableByteArray.data, parsableByteArray.getPosition(), bArr, length, unsignedShort4);
                i5 = length + unsignedShort4;
                parsableByteArray.skipBytes(unsignedShort4);
            }
        }
        return Pair.create(i2 == 0 ? null : Collections.singletonList(bArr), Integer.valueOf(unsignedByte + 1));
    }

    private static Pair<long[], long[]> parseEdts(Atom.ContainerAtom containerAtom) {
        Atom.LeafAtom leafAtomOfType;
        if (containerAtom == null || (leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst)) == null) {
            return Pair.create(null, null);
        }
        ParsableByteArray parsableByteArray = leafAtomOfType.data;
        parsableByteArray.setPosition(8);
        int fullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        int unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        long[] jArr = new long[unsignedIntToInt];
        long[] jArr2 = new long[unsignedIntToInt];
        for (int i = 0; i < unsignedIntToInt; i++) {
            jArr[i] = fullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
            jArr2[i] = fullAtomVersion == 1 ? parsableByteArray.readLong() : parsableByteArray.readInt();
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

    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, long j, String str, boolean z, StsdData stsdData, int i5) {
        int unsignedShort;
        int iRound;
        int unsignedIntToInt;
        int sampleEntryEncryptionData;
        String str2;
        StsdData stsdData2;
        int i6;
        int i7;
        int iFindEsdsPosition;
        int i8 = i3;
        StsdData stsdData3 = stsdData;
        parsableByteArray.setPosition(i2 + 8);
        if (z) {
            parsableByteArray.skipBytes(8);
            unsignedShort = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
        } else {
            parsableByteArray.skipBytes(16);
            unsignedShort = 0;
        }
        if (unsignedShort == 0 || unsignedShort == 1) {
            int unsignedShort2 = parsableByteArray.readUnsignedShort();
            parsableByteArray.skipBytes(6);
            int unsignedFixedPoint1616 = parsableByteArray.readUnsignedFixedPoint1616();
            if (unsignedShort == 1) {
                parsableByteArray.skipBytes(16);
            }
            iRound = unsignedFixedPoint1616;
            unsignedIntToInt = unsignedShort2;
        } else {
            if (unsignedShort != 2) {
                return;
            }
            parsableByteArray.skipBytes(16);
            iRound = (int) Math.round(parsableByteArray.readDouble());
            unsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray.skipBytes(20);
        }
        int position = parsableByteArray.getPosition();
        if (i == Atom.TYPE_enca) {
            sampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray, i2, i8, stsdData3, i5);
            parsableByteArray.setPosition(position);
        } else {
            sampleEntryEncryptionData = i;
        }
        String str3 = "audio/raw";
        if (sampleEntryEncryptionData == Atom.TYPE_ac_3) {
            str2 = "audio/ac3";
        } else if (sampleEntryEncryptionData == Atom.TYPE_ec_3) {
            str2 = "audio/eac3";
        } else if (sampleEntryEncryptionData == Atom.TYPE_dtsc) {
            str2 = "audio/vnd.dts";
        } else if (sampleEntryEncryptionData == Atom.TYPE_dtsh || sampleEntryEncryptionData == Atom.TYPE_dtsl) {
            str2 = "audio/vnd.dts.hd";
        } else if (sampleEntryEncryptionData == Atom.TYPE_dtse) {
            str2 = "audio/vnd.dts.hd;profile=lbr";
        } else if (sampleEntryEncryptionData == Atom.TYPE_samr) {
            str2 = "audio/3gpp";
        } else if (sampleEntryEncryptionData == Atom.TYPE_sawb) {
            str2 = "audio/amr-wb";
        } else {
            str2 = (sampleEntryEncryptionData == Atom.TYPE_lpcm || sampleEntryEncryptionData == Atom.TYPE_sowt) ? "audio/raw" : null;
        }
        int iIntValue = unsignedIntToInt;
        int iIntValue2 = iRound;
        int i9 = position;
        String str4 = str2;
        byte[] bArr = null;
        while (i9 - i2 < i8) {
            parsableByteArray.setPosition(i9);
            int i10 = parsableByteArray.readInt();
            Assertions.checkArgument(i10 > 0, "childAtomSize should be positive");
            int i11 = parsableByteArray.readInt();
            if (i11 == Atom.TYPE_esds || (z && i11 == Atom.TYPE_wave)) {
                String str5 = str4;
                int i12 = i9;
                str3 = str3;
                stsdData2 = stsdData3;
                if (i11 == Atom.TYPE_esds) {
                    i6 = i10;
                    i7 = i12;
                    iFindEsdsPosition = i7;
                } else {
                    i6 = i10;
                    i7 = i12;
                    iFindEsdsPosition = findEsdsPosition(parsableByteArray, i7, i6);
                }
                if (iFindEsdsPosition != -1) {
                    Pair<String, byte[]> esdsFromParent = parseEsdsFromParent(parsableByteArray, iFindEsdsPosition);
                    str4 = (String) esdsFromParent.first;
                    bArr = (byte[]) esdsFromParent.second;
                    if ("audio/mp4a-latm".equals(str4)) {
                        Pair<Integer, Integer> aacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
                        iIntValue2 = ((Integer) aacAudioSpecificConfig.first).intValue();
                        iIntValue = ((Integer) aacAudioSpecificConfig.second).intValue();
                    }
                } else {
                    str4 = str5;
                }
            } else {
                if (i11 == Atom.TYPE_dac3) {
                    parsableByteArray.setPosition(i9 + 8);
                    stsdData3.mediaFormat = Ac3Util.parseAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), j, str);
                } else if (i11 == Atom.TYPE_dec3) {
                    parsableByteArray.setPosition(i9 + 8);
                    stsdData3.mediaFormat = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray, Integer.toString(i4), j, str);
                } else {
                    if (i11 == Atom.TYPE_ddts) {
                        stsdData2 = stsdData3;
                        stsdData2.mediaFormat = MediaFormat.createAudioFormat(Integer.toString(i4), str4, -1, -1, j, iIntValue, iIntValue2, null, str);
                    }
                    i6 = i10;
                    str4 = str4;
                    i7 = i9;
                }
                stsdData2 = stsdData3;
                i6 = i10;
                str4 = str4;
                i7 = i9;
            }
            i9 = i7 + i6;
            stsdData3 = stsdData2;
            str3 = str3;
            i8 = i3;
        }
        String str6 = str4;
        String str7 = str3;
        StsdData stsdData4 = stsdData3;
        if (stsdData4.mediaFormat != null || str6 == null) {
            return;
        }
        stsdData4.mediaFormat = MediaFormat.createAudioFormat(Integer.toString(i4), str6, -1, -1, j, iIntValue, iIntValue2, bArr == null ? null : Collections.singletonList(bArr), str, str7.equals(str6) ? 2 : -1);
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int i3 = parsableByteArray.readInt();
            Assertions.checkArgument(i3 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += i3;
        }
        return -1;
    }

    /* JADX WARN: Code duplicated, block: B:36:0x0076  */
    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        String str;
        parsableByteArray.setPosition(i + 12);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int unsignedByte = parsableByteArray.readUnsignedByte();
        if ((unsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((unsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((unsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        if (unsignedByte2 == 32) {
            str = "video/mp4v-es";
        } else if (unsignedByte2 == 33) {
            str = "video/avc";
        } else if (unsignedByte2 == 35) {
            str = "video/hevc";
        } else if (unsignedByte2 != 64) {
            str = null;
            if (unsignedByte2 == 107) {
                return Pair.create("audio/mpeg", null);
            }
            if (unsignedByte2 == 165) {
                str = "audio/ac3";
            } else if (unsignedByte2 != 166) {
                switch (unsignedByte2) {
                    case 102:
                    case HttpStatusCodesKt.HTTP_EARLY_HINTS /* 103 */:
                    case LocationRequestCompat.QUALITY_LOW_POWER /* 104 */:
                        str = "audio/mp4a-latm";
                        break;
                    default:
                        switch (unsignedByte2) {
                            case Opcodes.RET /* 169 */:
                            case TsExtractor.TS_STREAM_TYPE_AC4 /* 172 */:
                                return Pair.create("audio/vnd.dts", null);
                            case 170:
                            case 171:
                                return Pair.create("audio/vnd.dts.hd", null);
                        }
                }
            } else {
                str = "audio/eac3";
            }
        } else {
            str = "audio/mp4a-latm";
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int expandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[expandableClassSize];
        parsableByteArray.readBytes(bArr, 0, expandableClassSize);
        return Pair.create(str, bArr);
    }

    private static int parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2, StsdData stsdData, int i3) {
        int position = parsableByteArray.getPosition();
        while (true) {
            if (position - i >= i2) {
                return 0;
            }
            parsableByteArray.setPosition(position);
            int i4 = parsableByteArray.readInt();
            Assertions.checkArgument(i4 > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf) {
                Pair<Integer, TrackEncryptionBox> sinfFromParent = parseSinfFromParent(parsableByteArray, position, i4);
                Integer num = (Integer) sinfFromParent.first;
                Assertions.checkArgument(num != null, "frma atom is mandatory");
                stsdData.trackEncryptionBoxes[i3] = (TrackEncryptionBox) sinfFromParent.second;
                return num.intValue();
            }
            position += i4;
        }
    }

    private static Pair<Integer, TrackEncryptionBox> parseSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        Integer numValueOf = null;
        TrackEncryptionBox schiFromParent = null;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            int i5 = parsableByteArray.readInt();
            if (i5 == Atom.TYPE_frma) {
                numValueOf = Integer.valueOf(parsableByteArray.readInt());
            } else if (i5 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                parsableByteArray.readInt();
                parsableByteArray.readInt();
            } else if (i5 == Atom.TYPE_schi) {
                schiFromParent = parseSchiFromParent(parsableByteArray, i3, i4);
            }
            i3 += i4;
        }
        return Pair.create(numValueOf, schiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                parsableByteArray.skipBytes(6);
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int unsignedByte = parsableByteArray.readUnsignedByte();
                byte[] bArr = new byte[16];
                parsableByteArray.readBytes(bArr, 0, 16);
                return new TrackEncryptionBox(z, unsignedByte, bArr);
            }
            i3 += i4;
        }
        return null;
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int i4 = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, i4 + i3);
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

    private AtomParsers() {
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

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            Assertions.checkState(parsableByteArray.readInt() == 1, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            this.offset = this.chunkOffsetsAreLongs ? this.chunkOffsets.readUnsignedLongToLong() : this.chunkOffsets.readUnsignedInt();
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
        private final long duration;
        private final int id;
        private final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    private static final class StsdData {
        public MediaFormat mediaFormat;
        public int nalUnitLengthFieldLength = -1;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    private static final class AvcCData {
        public final List<byte[]> initializationData;
        public final int nalUnitLengthFieldLength;
        public final float pixelWidthAspectRatio;

        public AvcCData(List<byte[]> list, int i, float f) {
            this.initializationData = list;
            this.nalUnitLengthFieldLength = i;
            this.pixelWidthAspectRatio = f;
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize;
        private final int sampleCount;

        public StszSampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fixedSampleSize = parsableByteArray.readUnsignedIntToInt();
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == 0 ? this.data.readUnsignedIntToInt() : i;
        }

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize;
        private final int sampleCount;
        private int sampleIndex;

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(Atom.LeafAtom leafAtom) {
            ParsableByteArray parsableByteArray = leafAtom.data;
            this.data = parsableByteArray;
            parsableByteArray.setPosition(12);
            this.fieldSize = parsableByteArray.readUnsignedIntToInt() & 255;
            this.sampleCount = parsableByteArray.readUnsignedIntToInt();
        }

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
        public int getSampleCount() {
            return this.sampleCount;
        }

        @Override // com.google.android.exoplayer.extractor.mp4.AtomParsers.SampleSizeBox
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
