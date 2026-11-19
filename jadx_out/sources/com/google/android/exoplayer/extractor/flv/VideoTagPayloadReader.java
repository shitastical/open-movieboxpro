package com.google.android.exoplayer.extractor.flv;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class VideoTagPayloadReader extends TagPayloadReader {
    private static final int AVC_PACKET_TYPE_AVC_NALU = 1;
    private static final int AVC_PACKET_TYPE_SEQUENCE_HEADER = 0;
    private static final int VIDEO_CODEC_AVC = 7;
    private static final int VIDEO_FRAME_KEYFRAME = 1;
    private static final int VIDEO_FRAME_VIDEO_INFO = 5;
    private int frameType;
    private boolean hasOutputFormat;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private int nalUnitLengthFieldLength;

    @Override // com.google.android.exoplayer.extractor.flv.TagPayloadReader
    public void seek() {
    }

    public VideoTagPayloadReader(TrackOutput trackOutput) {
        super(trackOutput);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
    }

    @Override // com.google.android.exoplayer.extractor.flv.TagPayloadReader
    protected boolean parseHeader(ParsableByteArray parsableByteArray) throws TagPayloadReader.UnsupportedFormatException {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i = (unsignedByte >> 4) & 15;
        int i2 = unsignedByte & 15;
        if (i2 != 7) {
            throw new TagPayloadReader.UnsupportedFormatException("Video format not supported: " + i2);
        }
        this.frameType = i;
        return i != 5;
    }

    @Override // com.google.android.exoplayer.extractor.flv.TagPayloadReader
    protected void parsePayload(ParsableByteArray parsableByteArray, long j) throws ParserException {
        int unsignedByte = parsableByteArray.readUnsignedByte();
        long unsignedInt24 = j + (((long) parsableByteArray.readUnsignedInt24()) * 1000);
        if (unsignedByte == 0 && !this.hasOutputFormat) {
            ParsableByteArray parsableByteArray2 = new ParsableByteArray(new byte[parsableByteArray.bytesLeft()]);
            parsableByteArray.readBytes(parsableByteArray2.data, 0, parsableByteArray.bytesLeft());
            AvcSequenceHeaderData avcCodecPrivate = parseAvcCodecPrivate(parsableByteArray2);
            this.nalUnitLengthFieldLength = avcCodecPrivate.nalUnitLengthFieldLength;
            this.output.format(MediaFormat.createVideoFormat(null, "video/avc", -1, -1, getDurationUs(), avcCodecPrivate.width, avcCodecPrivate.height, avcCodecPrivate.initializationData, -1, avcCodecPrivate.pixelWidthAspectRatio));
            this.hasOutputFormat = true;
            return;
        }
        if (unsignedByte == 1) {
            byte[] bArr = this.nalLength.data;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i = 4 - this.nalUnitLengthFieldLength;
            int i2 = 0;
            while (parsableByteArray.bytesLeft() > 0) {
                parsableByteArray.readBytes(this.nalLength.data, i, this.nalUnitLengthFieldLength);
                this.nalLength.setPosition(0);
                int unsignedIntToInt = this.nalLength.readUnsignedIntToInt();
                this.nalStartCode.setPosition(0);
                this.output.sampleData(this.nalStartCode, 4);
                this.output.sampleData(parsableByteArray, unsignedIntToInt);
                i2 = i2 + 4 + unsignedIntToInt;
            }
            this.output.sampleMetadata(unsignedInt24, this.frameType == 1 ? 1 : 0, i2, 0, null);
        }
    }

    private AvcSequenceHeaderData parseAvcCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
        int i;
        int i2;
        float f;
        parsableByteArray.setPosition(4);
        int unsignedByte = parsableByteArray.readUnsignedByte() & 3;
        int i3 = unsignedByte + 1;
        Assertions.checkState(i3 != 3);
        ArrayList arrayList = new ArrayList();
        int unsignedByte2 = parsableByteArray.readUnsignedByte() & 31;
        for (int i4 = 0; i4 < unsignedByte2; i4++) {
            arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
        }
        int unsignedByte3 = parsableByteArray.readUnsignedByte();
        for (int i5 = 0; i5 < unsignedByte3; i5++) {
            arrayList.add(NalUnitUtil.parseChildNalUnit(parsableByteArray));
        }
        if (unsignedByte2 > 0) {
            ParsableBitArray parsableBitArray = new ParsableBitArray((byte[]) arrayList.get(0));
            parsableBitArray.setPosition((unsignedByte + 2) * 8);
            NalUnitUtil.SpsData spsNalUnit = NalUnitUtil.parseSpsNalUnit(parsableBitArray);
            int i6 = spsNalUnit.width;
            int i7 = spsNalUnit.height;
            f = spsNalUnit.pixelWidthAspectRatio;
            i = i6;
            i2 = i7;
        } else {
            i = -1;
            i2 = -1;
            f = 1.0f;
        }
        return new AvcSequenceHeaderData(arrayList, i3, i, i2, f);
    }

    private static final class AvcSequenceHeaderData {
        public final int height;
        public final List<byte[]> initializationData;
        public final int nalUnitLengthFieldLength;
        public final float pixelWidthAspectRatio;
        public final int width;

        public AvcSequenceHeaderData(List<byte[]> list, int i, int i2, int i3, float f) {
            this.initializationData = list;
            this.nalUnitLengthFieldLength = i;
            this.pixelWidthAspectRatio = f;
            this.width = i2;
            this.height = i3;
        }
    }
}
