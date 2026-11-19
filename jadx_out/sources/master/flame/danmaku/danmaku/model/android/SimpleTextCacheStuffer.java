package master.flame.danmaku.danmaku.model.android;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import java.util.HashMap;
import java.util.Map;
import master.flame.danmaku.danmaku.model.BaseDanmaku;

/* JADX INFO: loaded from: classes6.dex */
public class SimpleTextCacheStuffer extends BaseCacheStuffer {
    private static final Map<Float, Float> sTextHeightCache = new HashMap();

    protected void drawBackground(BaseDanmaku baseDanmaku, Canvas canvas, float f, float f2) {
    }

    protected Float getCacheHeight(BaseDanmaku baseDanmaku, Paint paint) {
        Float fValueOf = Float.valueOf(paint.getTextSize());
        Map<Float, Float> map = sTextHeightCache;
        Float f = map.get(fValueOf);
        if (f != null) {
            return f;
        }
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        Float fValueOf2 = Float.valueOf((fontMetrics.descent - fontMetrics.ascent) + fontMetrics.leading);
        map.put(fValueOf, fValueOf2);
        return fValueOf2;
    }

    @Override // master.flame.danmaku.danmaku.model.android.BaseCacheStuffer
    public void measure(BaseDanmaku baseDanmaku, TextPaint textPaint, boolean z) {
        float fMax = 0.0f;
        Float fValueOf = Float.valueOf(0.0f);
        if (baseDanmaku.lines == null) {
            if (baseDanmaku.text != null) {
                fMax = textPaint.measureText(baseDanmaku.text.toString());
                fValueOf = getCacheHeight(baseDanmaku, textPaint);
            }
            baseDanmaku.paintWidth = fMax;
            baseDanmaku.paintHeight = fValueOf.floatValue();
            return;
        }
        Float cacheHeight = getCacheHeight(baseDanmaku, textPaint);
        for (String str : baseDanmaku.lines) {
            if (str.length() > 0) {
                fMax = Math.max(textPaint.measureText(str), fMax);
            }
        }
        baseDanmaku.paintWidth = fMax;
        baseDanmaku.paintHeight = baseDanmaku.lines.length * cacheHeight.floatValue();
    }

    protected void drawStroke(BaseDanmaku baseDanmaku, String str, Canvas canvas, float f, float f2, Paint paint) {
        if (str != null) {
            canvas.drawText(str, f, f2, paint);
        } else {
            canvas.drawText(baseDanmaku.text.toString(), f, f2, paint);
        }
    }

    protected void drawText(BaseDanmaku baseDanmaku, String str, Canvas canvas, float f, float f2, TextPaint textPaint, boolean z) {
        if (str != null) {
            canvas.drawText(str, f, f2, textPaint);
        } else {
            canvas.drawText(baseDanmaku.text.toString(), f, f2, textPaint);
        }
    }

    @Override // master.flame.danmaku.danmaku.model.android.BaseCacheStuffer
    public void clearCaches() {
        sTextHeightCache.clear();
    }

    @Override // master.flame.danmaku.danmaku.model.android.BaseCacheStuffer
    public void drawDanmaku(BaseDanmaku baseDanmaku, Canvas canvas, float f, float f2, boolean z, AndroidDisplayer.DisplayerConfig displayerConfig) {
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9 = f + baseDanmaku.padding;
        float f10 = f2 + baseDanmaku.padding;
        if (baseDanmaku.borderColor != 0) {
            f9 += 4.0f;
            f10 += 4.0f;
        }
        float f11 = f9;
        float f12 = f10;
        displayerConfig.definePaintParams(z);
        TextPaint paint = displayerConfig.getPaint(baseDanmaku, z);
        drawBackground(baseDanmaku, canvas, f, f2);
        boolean z2 = true;
        if (baseDanmaku.lines != null) {
            String[] strArr = baseDanmaku.lines;
            if (strArr.length == 1) {
                if (displayerConfig.hasStroke(baseDanmaku)) {
                    displayerConfig.applyPaintConfig(baseDanmaku, paint, true);
                    float fAscent = f12 - paint.ascent();
                    if (displayerConfig.HAS_PROJECTION) {
                        float f13 = displayerConfig.sProjectionOffsetX + f11;
                        f7 = fAscent + displayerConfig.sProjectionOffsetY;
                        f8 = f13;
                    } else {
                        f7 = fAscent;
                        f8 = f11;
                    }
                    drawStroke(baseDanmaku, strArr[0], canvas, f8, f7, paint);
                }
                displayerConfig.applyPaintConfig(baseDanmaku, paint, false);
                drawText(baseDanmaku, strArr[0], canvas, f11, f12 - paint.ascent(), paint, z);
            } else {
                float length = (baseDanmaku.paintHeight - (baseDanmaku.padding * 2)) / strArr.length;
                int i = 0;
                while (i < strArr.length) {
                    String str = strArr[i];
                    if (str == null || str.length() == 0) {
                        i = i;
                    } else {
                        if (displayerConfig.hasStroke(baseDanmaku)) {
                            displayerConfig.applyPaintConfig(baseDanmaku, paint, z2);
                            float fAscent2 = ((i * length) + f12) - paint.ascent();
                            if (displayerConfig.HAS_PROJECTION) {
                                float f14 = displayerConfig.sProjectionOffsetX + f11;
                                f5 = fAscent2 + displayerConfig.sProjectionOffsetY;
                                f6 = f14;
                            } else {
                                f5 = fAscent2;
                                f6 = f11;
                            }
                            drawStroke(baseDanmaku, strArr[i], canvas, f6, f5, paint);
                        }
                        displayerConfig.applyPaintConfig(baseDanmaku, paint, false);
                        drawText(baseDanmaku, strArr[i], canvas, f11, ((i * length) + f12) - paint.ascent(), paint, z);
                    }
                    i++;
                    strArr = strArr;
                    z2 = true;
                }
            }
        } else {
            if (displayerConfig.hasStroke(baseDanmaku)) {
                displayerConfig.applyPaintConfig(baseDanmaku, paint, true);
                float fAscent3 = f12 - paint.ascent();
                if (displayerConfig.HAS_PROJECTION) {
                    float f15 = displayerConfig.sProjectionOffsetX + f11;
                    f3 = fAscent3 + displayerConfig.sProjectionOffsetY;
                    f4 = f15;
                } else {
                    f3 = fAscent3;
                    f4 = f11;
                }
                drawStroke(baseDanmaku, null, canvas, f4, f3, paint);
            }
            displayerConfig.applyPaintConfig(baseDanmaku, paint, false);
            drawText(baseDanmaku, null, canvas, f11, f12 - paint.ascent(), paint, z);
        }
        if (baseDanmaku.underlineColor != 0) {
            Paint underlinePaint = displayerConfig.getUnderlinePaint(baseDanmaku);
            float f16 = (f2 + baseDanmaku.paintHeight) - displayerConfig.UNDERLINE_HEIGHT;
            canvas.drawLine(f, f16, f + baseDanmaku.paintWidth, f16, underlinePaint);
        }
        if (baseDanmaku.borderColor != 0) {
            canvas.drawRect(f, f2, f + baseDanmaku.paintWidth, f2 + baseDanmaku.paintHeight, displayerConfig.getBorderPaint(baseDanmaku));
        }
    }
}
