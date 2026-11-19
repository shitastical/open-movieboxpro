package androidx.media3.extractor;

import androidx.media3.common.ParserException;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.NalUnitUtil;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class HevcConfig {
    public final int bitdepthChroma;
    public final int bitdepthLuma;
    public final String codecs;
    public final int colorRange;
    public final int colorSpace;
    public final int colorTransfer;
    public final int decodedHeight;
    public final int decodedWidth;
    public final int height;
    public final List<byte[]> initializationData;
    public final int maxNumReorderPics;
    public final int maxSubLayers;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthHeightRatio;
    public final int stereoMode;
    public final NalUnitUtil.H265VpsData vpsData;
    public final int width;

    public static HevcConfig parse(ParsableByteArray parsableByteArray) throws ParserException {
        return parseImpl(parsableByteArray, false, null);
    }

    public static HevcConfig parseLayered(ParsableByteArray parsableByteArray, NalUnitUtil.H265VpsData h265VpsData) throws ParserException {
        return parseImpl(parsableByteArray, true, h265VpsData);
    }

    private static HevcConfig parseImpl(ParsableByteArray parsableByteArray, boolean z, NalUnitUtil.H265VpsData h265VpsData) throws ParserException {
        NalUnitUtil.H265Sei3dRefDisplayInfoData h265Sei3dRefDisplayInfo;
        try {
            if (z) {
                parsableByteArray.skipBytes(4);
            } else {
                parsableByteArray.skipBytes(21);
            }
            int unsignedByte = parsableByteArray.readUnsignedByte() & 3;
            int unsignedByte2 = parsableByteArray.readUnsignedByte();
            int position = parsableByteArray.getPosition();
            int i = 0;
            for (int i2 = 0; i2 < unsignedByte2; i2++) {
                parsableByteArray.skipBytes(1);
                int unsignedShort = parsableByteArray.readUnsignedShort();
                for (int i3 = 0; i3 < unsignedShort; i3++) {
                    int unsignedShort2 = parsableByteArray.readUnsignedShort();
                    i += unsignedShort2 + 4;
                    parsableByteArray.skipBytes(unsignedShort2);
                }
            }
            parsableByteArray.setPosition(position);
            byte[] bArr = new byte[i];
            NalUnitUtil.H265VpsData h265VpsData2 = h265VpsData;
            String strBuildHevcCodecString = null;
            int i4 = 0;
            int i5 = 0;
            int i6 = -1;
            int i7 = -1;
            int i8 = -1;
            int i9 = -1;
            int i10 = -1;
            int i11 = -1;
            int i12 = -1;
            int i13 = -1;
            int i14 = -1;
            int i15 = -1;
            int i16 = -1;
            float f = 1.0f;
            int i17 = -1;
            while (i4 < unsignedByte2) {
                int unsignedByte3 = parsableByteArray.readUnsignedByte() & 63;
                int unsignedShort3 = parsableByteArray.readUnsignedShort();
                NalUnitUtil.H265VpsData h265VpsNalUnit = h265VpsData2;
                int i18 = 0;
                while (i18 < unsignedShort3) {
                    int unsignedShort4 = parsableByteArray.readUnsignedShort();
                    int i19 = unsignedByte2;
                    System.arraycopy(NalUnitUtil.NAL_START_CODE, 0, bArr, i5, NalUnitUtil.NAL_START_CODE.length);
                    int length = i5 + NalUnitUtil.NAL_START_CODE.length;
                    System.arraycopy(parsableByteArray.getData(), parsableByteArray.getPosition(), bArr, length, unsignedShort4);
                    if (unsignedByte3 == 32 && i18 == 0) {
                        h265VpsNalUnit = NalUnitUtil.parseH265VpsNalUnit(bArr, length, length + unsignedShort4);
                        unsignedShort3 = unsignedShort3;
                    } else {
                        if (unsignedByte3 == 33 && i18 == 0) {
                            NalUnitUtil.H265SpsData h265SpsNalUnit = NalUnitUtil.parseH265SpsNalUnit(bArr, length, length + unsignedShort4, h265VpsNalUnit);
                            int i20 = h265SpsNalUnit.maxSubLayersMinus1 + 1;
                            int i21 = h265SpsNalUnit.width;
                            int i22 = h265SpsNalUnit.height;
                            int i23 = h265SpsNalUnit.decodedWidth;
                            i8 = i22;
                            int i24 = h265SpsNalUnit.decodedHeight;
                            i11 = h265SpsNalUnit.bitDepthLumaMinus8 + 8;
                            i12 = h265SpsNalUnit.bitDepthChromaMinus8 + 8;
                            int i25 = h265SpsNalUnit.colorSpace;
                            int i26 = h265SpsNalUnit.colorRange;
                            int i27 = h265SpsNalUnit.colorTransfer;
                            float f2 = h265SpsNalUnit.pixelWidthHeightRatio;
                            int i28 = h265SpsNalUnit.maxNumReorderPics;
                            if (h265SpsNalUnit.profileTierLevel != null) {
                                strBuildHevcCodecString = CodecSpecificDataUtil.buildHevcCodecString(h265SpsNalUnit.profileTierLevel.generalProfileSpace, h265SpsNalUnit.profileTierLevel.generalTierFlag, h265SpsNalUnit.profileTierLevel.generalProfileIdc, h265SpsNalUnit.profileTierLevel.generalProfileCompatibilityFlags, h265SpsNalUnit.profileTierLevel.constraintBytes, h265SpsNalUnit.profileTierLevel.generalLevelIdc);
                            }
                            i6 = i20;
                            i7 = i21;
                            i10 = i24;
                            i9 = i23;
                            i17 = i28;
                            f = f2;
                            i15 = i27;
                            i14 = i26;
                            i13 = i25;
                        } else {
                            unsignedShort3 = unsignedShort3;
                            if (unsignedByte3 == 39 && i18 == 0 && (h265Sei3dRefDisplayInfo = NalUnitUtil.parseH265Sei3dRefDisplayInfo(bArr, length, length + unsignedShort4)) != null && h265VpsNalUnit != null) {
                                i16 = h265Sei3dRefDisplayInfo.leftViewId == h265VpsNalUnit.layerInfos.get(0).viewId ? 4 : 5;
                            }
                        }
                        i5 = length + unsignedShort4;
                        parsableByteArray.skipBytes(unsignedShort4);
                        i18++;
                        unsignedShort3 = unsignedShort3;
                        unsignedByte2 = i19;
                    }
                    i5 = length + unsignedShort4;
                    parsableByteArray.skipBytes(unsignedShort4);
                    i18++;
                    unsignedShort3 = unsignedShort3;
                    unsignedByte2 = i19;
                }
                i4++;
                h265VpsData2 = h265VpsNalUnit;
            }
            return new HevcConfig(i == 0 ? Collections.emptyList() : Collections.singletonList(bArr), unsignedByte + 1, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, f, i17, strBuildHevcCodecString, h265VpsData2);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw ParserException.createForMalformedContainer("Error parsing".concat(z ? "L-HEVC config" : "HEVC config"), e);
        }
    }

    private HevcConfig(List<byte[]> list, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, float f, int i13, String str, NalUnitUtil.H265VpsData h265VpsData) {
        this.initializationData = list;
        this.nalUnitLengthFieldLength = i;
        this.maxSubLayers = i2;
        this.width = i3;
        this.height = i4;
        this.decodedWidth = i5;
        this.decodedHeight = i6;
        this.bitdepthLuma = i7;
        this.bitdepthChroma = i8;
        this.colorSpace = i9;
        this.colorRange = i10;
        this.colorTransfer = i11;
        this.stereoMode = i12;
        this.pixelWidthHeightRatio = f;
        this.maxNumReorderPics = i13;
        this.codecs = str;
        this.vpsData = h265VpsData;
    }
}
