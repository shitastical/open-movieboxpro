package com.google.android.exoplayer.extractor.ts;

import android.util.Pair;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.common.base.Ascii;
import java.util.Arrays;
import java.util.Collections;

/* JADX INFO: loaded from: classes4.dex */
final class H262Reader extends ElementaryStreamReader {
    private static final double[] FRAME_RATE_VALUES = {23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    private static final int START_EXTENSION = 181;
    private static final int START_GROUP = 184;
    private static final int START_PICTURE = 0;
    private static final int START_SEQUENCE_HEADER = 179;
    private final CsdBuffer csdBuffer;
    private boolean foundFirstFrameInGroup;
    private long frameDurationUs;
    private long framePosition;
    private long frameTimeUs;
    private boolean hasOutputFormat;
    private boolean isKeyframe;
    private boolean pesPtsUsAvailable;
    private long pesTimeUs;
    private final boolean[] prefixFlags;
    private long totalBytesWritten;

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void packetFinished() {
    }

    public H262Reader(TrackOutput trackOutput) {
        super(trackOutput);
        this.prefixFlags = new boolean[4];
        this.csdBuffer = new CsdBuffer(128);
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        this.pesPtsUsAvailable = false;
        this.foundFirstFrameInGroup = false;
        this.totalBytesWritten = 0L;
    }

    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void packetStarted(long j, boolean z) {
        boolean z2 = j != -1;
        this.pesPtsUsAvailable = z2;
        if (z2) {
            this.pesTimeUs = j;
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.google.android.exoplayer.extractor.ts.ElementaryStreamReader
    public void consume(ParsableByteArray parsableByteArray) {
        int i;
        if (parsableByteArray.bytesLeft() > 0) {
            int position = parsableByteArray.getPosition();
            int iLimit = parsableByteArray.limit();
            byte[] bArr = parsableByteArray.data;
            this.totalBytesWritten += (long) parsableByteArray.bytesLeft();
            this.output.sampleData(parsableByteArray, parsableByteArray.bytesLeft());
            int i2 = position;
            while (true) {
                int iFindNalUnit = NalUnitUtil.findNalUnit(bArr, position, iLimit, this.prefixFlags);
                if (iFindNalUnit == iLimit) {
                    break;
                }
                int i3 = iFindNalUnit + 3;
                int i4 = parsableByteArray.data[i3] & 255;
                if (!this.hasOutputFormat) {
                    int i5 = iFindNalUnit - i2;
                    if (i5 > 0) {
                        this.csdBuffer.onData(bArr, i2, iFindNalUnit);
                    }
                    if (this.csdBuffer.onStartCode(i4, i5 < 0 ? -i5 : 0)) {
                        Pair<MediaFormat, Long> csdBuffer = parseCsdBuffer(this.csdBuffer);
                        this.output.format((MediaFormat) csdBuffer.first);
                        this.frameDurationUs = ((Long) csdBuffer.second).longValue();
                        this.hasOutputFormat = true;
                    }
                }
                if (this.hasOutputFormat && (i4 == 184 || i4 == 0)) {
                    int i6 = iLimit - iFindNalUnit;
                    if (this.foundFirstFrameInGroup) {
                        this.output.sampleMetadata(this.frameTimeUs, this.isKeyframe ? 1 : 0, ((int) (this.totalBytesWritten - this.framePosition)) - i6, i6, null);
                        this.isKeyframe = false;
                        i = i4;
                    } else {
                        i = i4;
                    }
                    if (i == 184) {
                        this.foundFirstFrameInGroup = false;
                        this.isKeyframe = true;
                    } else {
                        this.frameTimeUs = this.pesPtsUsAvailable ? this.pesTimeUs : this.frameTimeUs + this.frameDurationUs;
                        this.framePosition = this.totalBytesWritten - ((long) i6);
                        this.pesPtsUsAvailable = false;
                        this.foundFirstFrameInGroup = true;
                    }
                }
                i2 = iFindNalUnit;
                position = i3;
            }
            if (this.hasOutputFormat) {
                return;
            }
            this.csdBuffer.onData(bArr, i2, iLimit);
        }
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0064  */
    /* JADX WARN: Code duplicated, block: B:16:0x0069  */
    /* JADX WARN: Code duplicated, block: B:18:0x0078  */
    /* JADX WARN: Code duplicated, block: B:20:0x008a  */
    private static Pair<MediaFormat, Long> parseCsdBuffer(CsdBuffer csdBuffer) {
        float f;
        int i;
        float f2;
        int i2;
        long j;
        double[] dArr;
        double d;
        int i3;
        int i4;
        byte[] bArrCopyOf = Arrays.copyOf(csdBuffer.data, csdBuffer.length);
        int i5 = bArrCopyOf[4] & 255;
        byte b = bArrCopyOf[5];
        int i6 = (i5 << 4) | ((b & 255) >> 4);
        int i7 = ((b & Ascii.SI) << 8) | (bArrCopyOf[6] & 255);
        int i8 = (bArrCopyOf[7] & 240) >> 4;
        if (i8 == 2) {
            f = i7 * 4;
            i = i6 * 3;
        } else {
            if (i8 != 3) {
                if (i8 != 4) {
                    f2 = 1.0f;
                } else {
                    f = i7 * 121;
                    i = i6 * 100;
                }
                MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(null, "video/mpeg2", -1, -1, -1L, i6, i7, Collections.singletonList(bArrCopyOf), -1, f2);
                i2 = (bArrCopyOf[7] & Ascii.SI) - 1;
                if (i2 >= 0) {
                    dArr = FRAME_RATE_VALUES;
                    if (i2 < dArr.length) {
                        d = dArr[i2];
                        byte b2 = bArrCopyOf[csdBuffer.sequenceExtensionPosition + 9];
                        i3 = (b2 & 96) >> 5;
                        i4 = b2 & 31;
                        if (i3 != i4) {
                            d *= (((double) i3) + 1.0d) / ((double) (i4 + 1));
                        }
                        j = (long) (1000000.0d / d);
                    } else {
                        j = 0;
                    }
                } else {
                    j = 0;
                }
                return Pair.create(mediaFormatCreateVideoFormat, Long.valueOf(j));
            }
            f = i7 * 16;
            i = i6 * 9;
        }
        f2 = f / i;
        MediaFormat mediaFormatCreateVideoFormat2 = MediaFormat.createVideoFormat(null, "video/mpeg2", -1, -1, -1L, i6, i7, Collections.singletonList(bArrCopyOf), -1, f2);
        i2 = (bArrCopyOf[7] & Ascii.SI) - 1;
        if (i2 >= 0) {
            dArr = FRAME_RATE_VALUES;
            if (i2 < dArr.length) {
                d = dArr[i2];
                byte b3 = bArrCopyOf[csdBuffer.sequenceExtensionPosition + 9];
                i3 = (b3 & 96) >> 5;
                i4 = b3 & 31;
                if (i3 != i4) {
                    d *= (((double) i3) + 1.0d) / ((double) (i4 + 1));
                }
                j = (long) (1000000.0d / d);
            } else {
                j = 0;
            }
        } else {
            j = 0;
        }
        return Pair.create(mediaFormatCreateVideoFormat2, Long.valueOf(j));
    }

    private static final class CsdBuffer {
        public byte[] data;
        private boolean isFilling;
        public int length;
        public int sequenceExtensionPosition;

        public CsdBuffer(int i) {
            this.data = new byte[i];
        }

        public void reset() {
            this.isFilling = false;
            this.length = 0;
            this.sequenceExtensionPosition = 0;
        }

        public boolean onStartCode(int i, int i2) {
            if (this.isFilling) {
                if (this.sequenceExtensionPosition == 0 && i == 181) {
                    this.sequenceExtensionPosition = this.length;
                } else {
                    this.length -= i2;
                    this.isFilling = false;
                    return true;
                }
            } else if (i == H262Reader.START_SEQUENCE_HEADER) {
                this.isFilling = true;
            }
            return false;
        }

        public void onData(byte[] bArr, int i, int i2) {
            if (this.isFilling) {
                int i3 = i2 - i;
                byte[] bArr2 = this.data;
                int length = bArr2.length;
                int i4 = this.length;
                if (length < i4 + i3) {
                    this.data = Arrays.copyOf(bArr2, (i4 + i3) * 2);
                }
                System.arraycopy(bArr, i, this.data, this.length, i3);
                this.length += i3;
            }
        }
    }
}
