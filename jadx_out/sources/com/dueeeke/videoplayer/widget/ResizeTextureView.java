package com.dueeeke.videoplayer.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class ResizeTextureView extends TextureView {
    private int mVideoHeight;
    private int mVideoWidth;
    private int screenType;

    public ResizeTextureView(Context context) {
        super(context);
    }

    public ResizeTextureView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setVideoSize(int i, int i2) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
    }

    public void setScreenScale(int i) {
        this.screenType = i;
        requestLayout();
    }

    private int getMeasureHeight(int i, int i2) {
        int i3;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (getRotation() == 90.0f || getRotation() == 270.0f) {
            int i4 = i + i2;
            i2 = i4 - i2;
            i = i4 - i2;
        }
        getDefaultSize(this.mVideoWidth, i);
        getDefaultSize(this.mVideoHeight, i2);
        if (mode == 1073741824 && mode2 == 1073741824) {
            int i5 = this.mVideoWidth;
            int i6 = i5 * size2;
            int i7 = this.mVideoHeight;
            if (i6 >= size * i7) {
                return i5 * size2 > size * i7 ? (size * i7) / i5 : size2;
            }
            int i8 = (i5 * size2) / i7;
            return size2;
        }
        if (mode == 1073741824) {
            int i9 = (size * this.mVideoHeight) / this.mVideoWidth;
            return (mode2 != Integer.MIN_VALUE || i9 <= size2) ? i9 : size2;
        }
        if (mode2 == 1073741824) {
            int i10 = (this.mVideoWidth * size2) / this.mVideoHeight;
            return size2;
        }
        int i11 = this.mVideoWidth;
        int i12 = this.mVideoHeight;
        if (mode2 != Integer.MIN_VALUE || i12 <= size2) {
            i3 = i11;
            size2 = i12;
        } else {
            i3 = (size2 * i11) / i12;
        }
        return (mode != Integer.MIN_VALUE || i3 <= size) ? size2 : (size * i12) / i11;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x009d A[PHI: r7
      0x009d: PHI (r7v10 int) = (r7v4 int), (r7v5 int), (r7v9 int), (r7v13 int) binds: [B:41:0x009b, B:37:0x008f, B:34:0x0081, B:26:0x006a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:59:0x00cf A[PHI: r1 r7
      0x00cf: PHI (r1v8 int) = (r1v5 int), (r1v5 int), (r1v11 int), (r1v11 int) binds: [B:65:0x00df, B:66:0x00e1, B:56:0x00ca, B:57:0x00cc] A[DONT_GENERATE, DONT_INLINE]
      0x00cf: PHI (r7v19 int) = (r7v17 int), (r7v17 int), (r7v15 int), (r7v15 int) binds: [B:65:0x00df, B:66:0x00e1, B:56:0x00ca, B:57:0x00cc] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:68:0x00e8  */
    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        if (getRotation() == 90.0f || getRotation() == 270.0f) {
            int i6 = i + i2;
            i2 = i6 - i2;
            i = i6 - i2;
        }
        int defaultSize = getDefaultSize(this.mVideoWidth, i);
        int defaultSize2 = getDefaultSize(this.mVideoHeight, i2);
        switch (this.screenType) {
            case 1:
                i2 = (defaultSize / 16) * 9;
                if (defaultSize2 <= i2) {
                    i = (defaultSize2 / 9) * 16;
                    i2 = defaultSize2;
                } else {
                    i = defaultSize;
                }
                break;
            case 2:
                i2 = (defaultSize / 4) * 3;
                if (defaultSize2 <= i2) {
                    i = (defaultSize2 / 3) * 4;
                    i2 = defaultSize2;
                } else {
                    i = defaultSize;
                }
                break;
            case 3:
                break;
            case 4:
                i = this.mVideoWidth;
                i2 = this.mVideoHeight;
                break;
            case 5:
                int i7 = this.mVideoWidth;
                if (i7 <= 0 || (i3 = this.mVideoHeight) <= 0) {
                    i = defaultSize;
                } else if (i7 * defaultSize2 > defaultSize * i3) {
                    i = (i7 * defaultSize2) / i3;
                } else {
                    i2 = (i3 * defaultSize) / i7;
                    i = defaultSize;
                }
                i2 = defaultSize2;
                break;
            case 6:
                int i8 = this.mVideoWidth;
                if (i8 <= 0 || (i4 = this.mVideoHeight) <= 0) {
                    i = defaultSize;
                } else if (i8 * defaultSize2 > defaultSize * i4) {
                    i = (i8 * defaultSize2) / i4;
                } else {
                    i2 = (i4 * defaultSize) / i8;
                    i = defaultSize;
                }
                i2 = defaultSize2;
                break;
            default:
                if (this.mVideoWidth > 0 && this.mVideoHeight > 0) {
                    int mode = View.MeasureSpec.getMode(i);
                    i = View.MeasureSpec.getSize(i);
                    int mode2 = View.MeasureSpec.getMode(i2);
                    i2 = View.MeasureSpec.getSize(i2);
                    if (mode == 1073741824 && mode2 == 1073741824) {
                        int i9 = this.mVideoWidth;
                        int i10 = i9 * i2;
                        int i11 = this.mVideoHeight;
                        if (i10 < i * i11) {
                            i = (i9 * i2) / i11;
                        } else if (i9 * i2 > i * i11) {
                            i2 = (i11 * i) / i9;
                        }
                    } else if (mode == 1073741824) {
                        int i12 = (this.mVideoHeight * i) / this.mVideoWidth;
                        if (mode2 != Integer.MIN_VALUE || i12 <= i2) {
                            i2 = i12;
                        }
                    } else if (mode2 == 1073741824) {
                        i5 = (this.mVideoWidth * i2) / this.mVideoHeight;
                        if (mode != Integer.MIN_VALUE || i5 <= i) {
                            i = i5;
                        }
                    } else {
                        int i13 = this.mVideoWidth;
                        int i14 = this.mVideoHeight;
                        if (mode2 != Integer.MIN_VALUE || i14 <= i2) {
                            i5 = i13;
                            i2 = i14;
                        } else {
                            i5 = (i2 * i13) / i14;
                        }
                        if (mode == Integer.MIN_VALUE && i5 > i) {
                            i2 = (i14 * i) / i13;
                        } else {
                            i = i5;
                        }
                    }
                } else {
                    i = defaultSize;
                    i2 = defaultSize2;
                }
                break;
        }
        setMeasuredDimension(i, i2);
    }
}
