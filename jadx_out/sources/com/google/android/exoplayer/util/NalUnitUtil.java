package com.google.android.exoplayer.util;

import android.util.Log;
import java.nio.ByteBuffer;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class NalUnitUtil {
    public static final int EXTENDED_SAR = 255;
    private static final int NAL_UNIT_TYPE_SPS = 7;
    private static final String TAG = "NalUnitUtil";
    public static final byte[] NAL_START_CODE = {0, 0, 0, 1};
    public static final float[] ASPECT_RATIO_IDC_VALUES = {1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f};
    private static final Object scratchEscapePositionsLock = new Object();
    private static int[] scratchEscapePositions = new int[10];

    public static final class SpsData {
        public final boolean deltaPicOrderAlwaysZeroFlag;
        public final boolean frameMbsOnlyFlag;
        public final int frameNumLength;
        public final int height;
        public final int picOrderCntLsbLength;
        public final int picOrderCountType;
        public final float pixelWidthAspectRatio;
        public final boolean separateColorPlaneFlag;
        public final int seqParameterSetId;
        public final int width;

        public SpsData(int i, int i2, int i3, float f, boolean z, boolean z2, int i4, int i5, int i6, boolean z3) {
            this.seqParameterSetId = i;
            this.width = i2;
            this.height = i3;
            this.pixelWidthAspectRatio = f;
            this.separateColorPlaneFlag = z;
            this.frameMbsOnlyFlag = z2;
            this.frameNumLength = i4;
            this.picOrderCountType = i5;
            this.picOrderCntLsbLength = i6;
            this.deltaPicOrderAlwaysZeroFlag = z3;
        }
    }

    public static final class PpsData {
        public final boolean bottomFieldPicOrderInFramePresentFlag;
        public final int picParameterSetId;
        public final int seqParameterSetId;

        public PpsData(int i, int i2, boolean z) {
            this.picParameterSetId = i;
            this.seqParameterSetId = i2;
            this.bottomFieldPicOrderInFramePresentFlag = z;
        }
    }

    public static int unescapeStream(byte[] bArr, int i) {
        int i2;
        synchronized (scratchEscapePositionsLock) {
            int iFindNextUnescapeIndex = 0;
            int i3 = 0;
            while (iFindNextUnescapeIndex < i) {
                try {
                    iFindNextUnescapeIndex = findNextUnescapeIndex(bArr, iFindNextUnescapeIndex, i);
                    if (iFindNextUnescapeIndex < i) {
                        int[] iArr = scratchEscapePositions;
                        if (iArr.length <= i3) {
                            scratchEscapePositions = Arrays.copyOf(iArr, iArr.length * 2);
                        }
                        scratchEscapePositions[i3] = iFindNextUnescapeIndex;
                        iFindNextUnescapeIndex += 3;
                        i3++;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
            i2 = i - i3;
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < i3; i6++) {
                int i7 = scratchEscapePositions[i6] - i5;
                System.arraycopy(bArr, i5, bArr, i4, i7);
                int i8 = i4 + i7;
                int i9 = i8 + 1;
                bArr[i8] = 0;
                i4 = i8 + 2;
                bArr[i9] = 0;
                i5 += i7 + 3;
            }
            System.arraycopy(bArr, i5, bArr, i4, i2 - i4);
        }
        return i2;
    }

    public static void discardToSps(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        int i = 0;
        int i2 = 0;
        while (true) {
            int i3 = i + 1;
            if (i3 < iPosition) {
                int i4 = byteBuffer.get(i) & 255;
                if (i2 == 3) {
                    if (i4 == 1 && (byteBuffer.get(i3) & 31) == 7) {
                        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
                        byteBufferDuplicate.position(i - 3);
                        byteBufferDuplicate.limit(iPosition);
                        byteBuffer.position(0);
                        byteBuffer.put(byteBufferDuplicate);
                        return;
                    }
                } else if (i4 == 0) {
                    i2++;
                }
                if (i4 != 0) {
                    i2 = 0;
                }
                i = i3;
            } else {
                byteBuffer.clear();
                return;
            }
        }
    }

    public static byte[] parseChildNalUnit(ParsableByteArray parsableByteArray) {
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(unsignedShort);
        return CodecSpecificDataUtil.buildNalUnit(parsableByteArray.data, position, unsignedShort);
    }

    public static int getNalUnitType(byte[] bArr, int i) {
        return bArr[i + 3] & 31;
    }

    public static int getH265NalUnitType(byte[] bArr, int i) {
        return (bArr[i + 3] & 126) >> 1;
    }

    /* JADX WARN: Code duplicated, block: B:57:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:60:0x00df  */
    /* JADX WARN: Code duplicated, block: B:62:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:63:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:65:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:66:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:68:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:86:0x0153  */
    public static SpsData parseSpsNalUnit(ParsableBitArray parsableBitArray) {
        int unsignedExpGolombCodedInt;
        boolean z;
        int unsignedExpGolombCodedInt2;
        boolean z2;
        boolean bit;
        int i;
        int i2;
        float f;
        int i3;
        int i4;
        int bits = parsableBitArray.readBits(8);
        parsableBitArray.skipBits(16);
        int unsignedExpGolombCodedInt3 = parsableBitArray.readUnsignedExpGolombCodedInt();
        int i5 = 1;
        if (bits == 100 || bits == 110 || bits == 122 || bits == 244 || bits == 44 || bits == 83 || bits == 86 || bits == 118 || bits == 128 || bits == 138) {
            unsignedExpGolombCodedInt = parsableBitArray.readUnsignedExpGolombCodedInt();
            boolean bit2 = unsignedExpGolombCodedInt == 3 ? parsableBitArray.readBit() : false;
            parsableBitArray.readUnsignedExpGolombCodedInt();
            parsableBitArray.readUnsignedExpGolombCodedInt();
            parsableBitArray.skipBits(1);
            if (parsableBitArray.readBit()) {
                int i6 = unsignedExpGolombCodedInt != 3 ? 8 : 12;
                int i7 = 0;
                while (i7 < i6) {
                    if (parsableBitArray.readBit()) {
                        skipScalingList(parsableBitArray, i7 < 6 ? 16 : 64);
                    }
                    i7++;
                }
            }
            z = bit2;
        } else {
            unsignedExpGolombCodedInt = 1;
            z = false;
        }
        int unsignedExpGolombCodedInt4 = parsableBitArray.readUnsignedExpGolombCodedInt() + 4;
        int unsignedExpGolombCodedInt5 = parsableBitArray.readUnsignedExpGolombCodedInt();
        if (unsignedExpGolombCodedInt5 == 0) {
            unsignedExpGolombCodedInt2 = parsableBitArray.readUnsignedExpGolombCodedInt() + 4;
        } else {
            if (unsignedExpGolombCodedInt5 == 1) {
                boolean bit3 = parsableBitArray.readBit();
                parsableBitArray.readSignedExpGolombCodedInt();
                parsableBitArray.readSignedExpGolombCodedInt();
                long unsignedExpGolombCodedInt6 = parsableBitArray.readUnsignedExpGolombCodedInt();
                z = z;
                for (int i8 = 0; i8 < unsignedExpGolombCodedInt6; i8++) {
                    parsableBitArray.readUnsignedExpGolombCodedInt();
                }
                z2 = bit3;
                unsignedExpGolombCodedInt2 = 0;
            } else {
                unsignedExpGolombCodedInt2 = 0;
            }
            parsableBitArray.readUnsignedExpGolombCodedInt();
            parsableBitArray.skipBits(1);
            int unsignedExpGolombCodedInt7 = parsableBitArray.readUnsignedExpGolombCodedInt() + 1;
            int unsignedExpGolombCodedInt8 = parsableBitArray.readUnsignedExpGolombCodedInt() + 1;
            bit = parsableBitArray.readBit();
            int i9 = (2 - (bit ? 1 : 0)) * unsignedExpGolombCodedInt8;
            if (!bit) {
                parsableBitArray.skipBits(1);
            }
            parsableBitArray.skipBits(1);
            i = unsignedExpGolombCodedInt7 * 16;
            i2 = i9 * 16;
            if (parsableBitArray.readBit()) {
                int unsignedExpGolombCodedInt9 = parsableBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt10 = parsableBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt11 = parsableBitArray.readUnsignedExpGolombCodedInt();
                int unsignedExpGolombCodedInt12 = parsableBitArray.readUnsignedExpGolombCodedInt();
                if (unsignedExpGolombCodedInt == 0) {
                    i4 = 2 - (bit ? 1 : 0);
                } else {
                    if (unsignedExpGolombCodedInt == 3) {
                        i3 = 1;
                    } else {
                        i3 = 2;
                    }
                    i4 = (2 - (bit ? 1 : 0)) * (unsignedExpGolombCodedInt == 1 ? 2 : 1);
                    i5 = i3;
                }
                i -= (unsignedExpGolombCodedInt9 + unsignedExpGolombCodedInt10) * i5;
                i2 -= (unsignedExpGolombCodedInt11 + unsignedExpGolombCodedInt12) * i4;
            }
            int i10 = i;
            int i11 = i2;
            float f2 = 1.0f;
            if (parsableBitArray.readBit() || !parsableBitArray.readBit()) {
                f = 1.0f;
            } else {
                int bits2 = parsableBitArray.readBits(8);
                if (bits2 == 255) {
                    int bits3 = parsableBitArray.readBits(16);
                    int bits4 = parsableBitArray.readBits(16);
                    if (bits3 != 0 && bits4 != 0) {
                        f2 = bits3 / bits4;
                    }
                    f = f2;
                } else {
                    float[] fArr = ASPECT_RATIO_IDC_VALUES;
                    if (bits2 < fArr.length) {
                        f = fArr[bits2];
                    } else {
                        Log.w(TAG, "Unexpected aspect_ratio_idc value: " + bits2);
                        f = 1.0f;
                    }
                }
            }
            return new SpsData(unsignedExpGolombCodedInt3, i10, i11, f, z, bit, unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt5, unsignedExpGolombCodedInt2, z2);
        }
        z2 = false;
        parsableBitArray.readUnsignedExpGolombCodedInt();
        parsableBitArray.skipBits(1);
        int unsignedExpGolombCodedInt13 = parsableBitArray.readUnsignedExpGolombCodedInt() + 1;
        int unsignedExpGolombCodedInt14 = parsableBitArray.readUnsignedExpGolombCodedInt() + 1;
        bit = parsableBitArray.readBit();
        int i12 = (2 - (bit ? 1 : 0)) * unsignedExpGolombCodedInt14;
        if (!bit) {
            parsableBitArray.skipBits(1);
        }
        parsableBitArray.skipBits(1);
        i = unsignedExpGolombCodedInt13 * 16;
        i2 = i12 * 16;
        if (parsableBitArray.readBit()) {
            int unsignedExpGolombCodedInt15 = parsableBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt16 = parsableBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt17 = parsableBitArray.readUnsignedExpGolombCodedInt();
            int unsignedExpGolombCodedInt18 = parsableBitArray.readUnsignedExpGolombCodedInt();
            if (unsignedExpGolombCodedInt == 0) {
                i4 = 2 - (bit ? 1 : 0);
            } else {
                if (unsignedExpGolombCodedInt == 3) {
                    i3 = 1;
                } else {
                    i3 = 2;
                }
                i4 = (2 - (bit ? 1 : 0)) * (unsignedExpGolombCodedInt == 1 ? 2 : 1);
                i5 = i3;
            }
            i -= (unsignedExpGolombCodedInt15 + unsignedExpGolombCodedInt16) * i5;
            i2 -= (unsignedExpGolombCodedInt17 + unsignedExpGolombCodedInt18) * i4;
        }
        int i13 = i;
        int i14 = i2;
        float f3 = 1.0f;
        if (parsableBitArray.readBit()) {
            f = 1.0f;
        } else {
            f = 1.0f;
        }
        return new SpsData(unsignedExpGolombCodedInt3, i13, i14, f, z, bit, unsignedExpGolombCodedInt4, unsignedExpGolombCodedInt5, unsignedExpGolombCodedInt2, z2);
    }

    public static PpsData parsePpsNalUnit(ParsableBitArray parsableBitArray) {
        int unsignedExpGolombCodedInt = parsableBitArray.readUnsignedExpGolombCodedInt();
        int unsignedExpGolombCodedInt2 = parsableBitArray.readUnsignedExpGolombCodedInt();
        parsableBitArray.skipBits(1);
        return new PpsData(unsignedExpGolombCodedInt, unsignedExpGolombCodedInt2, parsableBitArray.readBit());
    }

    /* JADX WARN: Code restructure failed: missing block: B:68:0x0095, code lost:
    
        r9 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int findNalUnit(byte[] r8, int r9, int r10, boolean[] r11) {
        /*
            int r0 = r10 - r9
            r1 = 0
            r2 = 1
            if (r0 < 0) goto L8
            r3 = 1
            goto L9
        L8:
            r3 = 0
        L9:
            com.google.android.exoplayer.util.Assertions.checkState(r3)
            if (r0 != 0) goto Lf
            return r10
        Lf:
            r3 = 2
            if (r11 == 0) goto L40
            boolean r4 = r11[r1]
            if (r4 == 0) goto L1c
            clearPrefixFlags(r11)
            int r9 = r9 + (-3)
            return r9
        L1c:
            if (r0 <= r2) goto L2b
            boolean r4 = r11[r2]
            if (r4 == 0) goto L2b
            r4 = r8[r9]
            if (r4 != r2) goto L2b
            clearPrefixFlags(r11)
            int r9 = r9 - r3
            return r9
        L2b:
            if (r0 <= r3) goto L40
            boolean r4 = r11[r3]
            if (r4 == 0) goto L40
            r4 = r8[r9]
            if (r4 != 0) goto L40
            int r4 = r9 + 1
            r4 = r8[r4]
            if (r4 != r2) goto L40
            clearPrefixFlags(r11)
            int r9 = r9 - r2
            return r9
        L40:
            int r4 = r10 + (-1)
            int r9 = r9 + r3
        L43:
            if (r9 >= r4) goto L65
            r5 = r8[r9]
            r6 = r5 & 254(0xfe, float:3.56E-43)
            if (r6 == 0) goto L4c
            goto L62
        L4c:
            int r6 = r9 + (-2)
            r7 = r8[r6]
            if (r7 != 0) goto L60
            int r7 = r9 + (-1)
            r7 = r8[r7]
            if (r7 != 0) goto L60
            if (r5 != r2) goto L60
            if (r11 == 0) goto L5f
            clearPrefixFlags(r11)
        L5f:
            return r6
        L60:
            int r9 = r9 + (-2)
        L62:
            int r9 = r9 + 3
            goto L43
        L65:
            if (r11 == 0) goto Lb9
            if (r0 <= r3) goto L7c
            int r9 = r10 + (-3)
            r9 = r8[r9]
            if (r9 != 0) goto L7a
            int r9 = r10 + (-2)
            r9 = r8[r9]
            if (r9 != 0) goto L7a
            r9 = r8[r4]
            if (r9 != r2) goto L7a
            goto L95
        L7a:
            r9 = 0
            goto L96
        L7c:
            if (r0 != r3) goto L8d
            boolean r9 = r11[r3]
            if (r9 == 0) goto L7a
            int r9 = r10 + (-2)
            r9 = r8[r9]
            if (r9 != 0) goto L7a
            r9 = r8[r4]
            if (r9 != r2) goto L7a
            goto L95
        L8d:
            boolean r9 = r11[r2]
            if (r9 == 0) goto L7a
            r9 = r8[r4]
            if (r9 != r2) goto L7a
        L95:
            r9 = 1
        L96:
            r11[r1] = r9
            if (r0 <= r2) goto La5
            int r9 = r10 + (-2)
            r9 = r8[r9]
            if (r9 != 0) goto Laf
            r9 = r8[r4]
            if (r9 != 0) goto Laf
            goto Lad
        La5:
            boolean r9 = r11[r3]
            if (r9 == 0) goto Laf
            r9 = r8[r4]
            if (r9 != 0) goto Laf
        Lad:
            r9 = 1
            goto Lb0
        Laf:
            r9 = 0
        Lb0:
            r11[r2] = r9
            r8 = r8[r4]
            if (r8 != 0) goto Lb7
            r1 = 1
        Lb7:
            r11[r3] = r1
        Lb9:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.util.NalUnitUtil.findNalUnit(byte[], int, int, boolean[]):int");
    }

    public static void clearPrefixFlags(boolean[] zArr) {
        zArr[0] = false;
        zArr[1] = false;
        zArr[2] = false;
    }

    private static int findNextUnescapeIndex(byte[] bArr, int i, int i2) {
        while (i < i2 - 2) {
            if (bArr[i] == 0 && bArr[i + 1] == 0 && bArr[i + 2] == 3) {
                return i;
            }
            i++;
        }
        return i2;
    }

    private static void skipScalingList(ParsableBitArray parsableBitArray, int i) {
        int signedExpGolombCodedInt = 8;
        int i2 = 8;
        for (int i3 = 0; i3 < i; i3++) {
            if (signedExpGolombCodedInt != 0) {
                signedExpGolombCodedInt = ((parsableBitArray.readSignedExpGolombCodedInt() + i2) + 256) % 256;
            }
            if (signedExpGolombCodedInt != 0) {
                i2 = signedExpGolombCodedInt;
            }
        }
    }

    private NalUnitUtil() {
    }
}
