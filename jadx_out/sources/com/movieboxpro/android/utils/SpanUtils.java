package com.movieboxpro.android.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes5.dex */
public final class SpanUtils {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_TOP = 3;
    private static final int COLOR_DEFAULT = -16777217;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private int alignImage;
    private int alignLine;
    private Layout.Alignment alignment;
    private int backgroundColor;
    private float blurRadius;
    private int bulletColor;
    private int bulletGapWidth;
    private int bulletRadius;
    private ClickableSpan clickSpan;
    private int first;
    private int flag;
    private String fontFamily;
    private int fontSize;
    private boolean fontSizeIsDp;
    private int foregroundColor;
    private Bitmap imageBitmap;
    private Drawable imageDrawable;
    private int imageResourceId;
    private Uri imageUri;
    private boolean isBold;
    private boolean isBoldItalic;
    private boolean isItalic;
    private boolean isStrikethrough;
    private boolean isSubscript;
    private boolean isSuperscript;
    private boolean isUnderline;
    private int lineHeight;
    private SerializableSpannableStringBuilder mBuilder;
    private CharSequence mText;
    private TextView mTextView;
    private int mType;
    private final int mTypeCharSequence;
    private final int mTypeImage;
    private final int mTypeSpace;
    private float proportion;
    private int quoteColor;
    private int quoteGapWidth;
    private int rest;
    private Shader shader;
    private int shadowColor;
    private float shadowDx;
    private float shadowDy;
    private float shadowRadius;
    private int spaceColor;
    private int spaceSize;
    private Object[] spans;
    private int stripeWidth;
    private BlurMaskFilter.Blur style;
    private Typeface typeface;
    private String url;
    private int verticalAlign;
    private float xProportion;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Align {
    }

    private SpanUtils(TextView textView) {
        this();
        this.mTextView = textView;
    }

    public SpanUtils() {
        this.mTypeCharSequence = 0;
        this.mTypeImage = 1;
        this.mTypeSpace = 2;
        this.mBuilder = new SerializableSpannableStringBuilder();
        this.mText = "";
        this.mType = -1;
        setDefault();
    }

    private void setDefault() {
        this.flag = 33;
        this.foregroundColor = COLOR_DEFAULT;
        this.backgroundColor = COLOR_DEFAULT;
        this.lineHeight = -1;
        this.quoteColor = COLOR_DEFAULT;
        this.first = -1;
        this.bulletColor = COLOR_DEFAULT;
        this.fontSize = -1;
        this.proportion = -1.0f;
        this.xProportion = -1.0f;
        this.isStrikethrough = false;
        this.isUnderline = false;
        this.isSuperscript = false;
        this.isSubscript = false;
        this.isBold = false;
        this.isItalic = false;
        this.isBoldItalic = false;
        this.fontFamily = null;
        this.typeface = null;
        this.alignment = null;
        this.verticalAlign = -1;
        this.clickSpan = null;
        this.url = null;
        this.blurRadius = -1.0f;
        this.shader = null;
        this.shadowRadius = -1.0f;
        this.spans = null;
        this.imageBitmap = null;
        this.imageDrawable = null;
        this.imageUri = null;
        this.imageResourceId = -1;
        this.spaceSize = -1;
    }

    public SpanUtils setFlag(final int flag) {
        this.flag = flag;
        return this;
    }

    public SpanUtils setForegroundColor(final int color) {
        this.foregroundColor = color;
        return this;
    }

    public SpanUtils setBackgroundColor(final int color) {
        this.backgroundColor = color;
        return this;
    }

    public SpanUtils setLineHeight(final int lineHeight) {
        return setLineHeight(lineHeight, 2);
    }

    public SpanUtils setLineHeight(final int lineHeight, final int align) {
        this.lineHeight = lineHeight;
        this.alignLine = align;
        return this;
    }

    public SpanUtils setQuoteColor(final int color) {
        return setQuoteColor(color, 2, 2);
    }

    public SpanUtils setQuoteColor(final int color, final int stripeWidth, final int gapWidth) {
        this.quoteColor = color;
        this.stripeWidth = stripeWidth;
        this.quoteGapWidth = gapWidth;
        return this;
    }

    public SpanUtils setLeadingMargin(final int first, final int rest) {
        this.first = first;
        this.rest = rest;
        return this;
    }

    public SpanUtils setBullet(final int gapWidth) {
        return setBullet(0, 3, gapWidth);
    }

    public SpanUtils setBullet(final int color, final int radius, final int gapWidth) {
        this.bulletColor = color;
        this.bulletRadius = radius;
        this.bulletGapWidth = gapWidth;
        return this;
    }

    public SpanUtils setFontSize(final int size) {
        return setFontSize(size, false);
    }

    public SpanUtils setFontSize(final int size, final boolean isSp) {
        this.fontSize = size;
        this.fontSizeIsDp = isSp;
        return this;
    }

    public SpanUtils setFontProportion(final float proportion) {
        this.proportion = proportion;
        return this;
    }

    public SpanUtils setFontXProportion(final float proportion) {
        this.xProportion = proportion;
        return this;
    }

    public SpanUtils setStrikethrough() {
        this.isStrikethrough = true;
        return this;
    }

    public SpanUtils setUnderline() {
        this.isUnderline = true;
        return this;
    }

    public SpanUtils setSuperscript() {
        this.isSuperscript = true;
        return this;
    }

    public SpanUtils setSubscript() {
        this.isSubscript = true;
        return this;
    }

    public SpanUtils setBold() {
        this.isBold = true;
        return this;
    }

    public SpanUtils setItalic() {
        this.isItalic = true;
        return this;
    }

    public SpanUtils setBoldItalic() {
        this.isBoldItalic = true;
        return this;
    }

    public SpanUtils setFontFamily(final String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public SpanUtils setTypeface(final Typeface typeface) {
        this.typeface = typeface;
        return this;
    }

    public SpanUtils setHorizontalAlign(final Layout.Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public SpanUtils setVerticalAlign(final int align) {
        this.verticalAlign = align;
        return this;
    }

    public SpanUtils setClickSpan(final ClickableSpan clickSpan) {
        TextView textView = this.mTextView;
        if (textView != null && textView.getMovementMethod() == null) {
            this.mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        this.clickSpan = clickSpan;
        return this;
    }

    public SpanUtils setUrl(final String url) {
        TextView textView = this.mTextView;
        if (textView != null && textView.getMovementMethod() == null) {
            this.mTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
        this.url = url;
        return this;
    }

    public SpanUtils setBlur(final float radius, final BlurMaskFilter.Blur style) {
        this.blurRadius = radius;
        this.style = style;
        return this;
    }

    public SpanUtils setShader(final Shader shader) {
        this.shader = shader;
        return this;
    }

    public SpanUtils setShadow(final float radius, final float dx, final float dy, final int shadowColor) {
        this.shadowRadius = radius;
        this.shadowDx = dx;
        this.shadowDy = dy;
        this.shadowColor = shadowColor;
        return this;
    }

    public SpanUtils setSpans(final Object... spans) {
        if (spans.length > 0) {
            this.spans = spans;
        }
        return this;
    }

    public SpanUtils append(final CharSequence text) {
        apply(0);
        this.mText = text;
        return this;
    }

    public SpanUtils appendLine() {
        apply(0);
        this.mText = LINE_SEPARATOR;
        return this;
    }

    public SpanUtils appendLine(final CharSequence text) {
        apply(0);
        this.mText = ((Object) text) + LINE_SEPARATOR;
        return this;
    }

    public SpanUtils appendImage(final Bitmap bitmap) {
        return appendImage(bitmap, 0);
    }

    public SpanUtils appendImage(final Bitmap bitmap, final int align) {
        apply(1);
        this.imageBitmap = bitmap;
        this.alignImage = align;
        return this;
    }

    public SpanUtils appendImage(final Drawable drawable) {
        return appendImage(drawable, 0);
    }

    public SpanUtils appendImage(final Drawable drawable, final int align) {
        apply(1);
        this.imageDrawable = drawable;
        this.alignImage = align;
        return this;
    }

    public SpanUtils appendImage(final Uri uri) {
        return appendImage(uri, 0);
    }

    public SpanUtils appendImage(final Uri uri, final int align) {
        apply(1);
        this.imageUri = uri;
        this.alignImage = align;
        return this;
    }

    public SpanUtils appendImage(final int resourceId) {
        return appendImage(resourceId, 0);
    }

    public SpanUtils appendImage(final int resourceId, final int align) {
        apply(1);
        this.imageResourceId = resourceId;
        this.alignImage = align;
        return this;
    }

    public SpanUtils appendSpace(final int size) {
        return appendSpace(size, 0);
    }

    public SpanUtils appendSpace(final int size, final int color) {
        apply(2);
        this.spaceSize = size;
        this.spaceColor = color;
        return this;
    }

    private void apply(final int type) {
        applyLast();
        this.mType = type;
    }

    public SpannableStringBuilder create() {
        applyLast();
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setText(this.mBuilder);
        }
        return this.mBuilder;
    }

    private void applyLast() {
        int i = this.mType;
        if (i == 0) {
            updateCharCharSequence();
        } else if (i == 1) {
            updateImage();
        } else if (i == 2) {
            updateSpace();
        }
        setDefault();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateCharCharSequence() {
        if (this.mText.length() == 0) {
            return;
        }
        int length = this.mBuilder.length();
        if (length == 0 && this.lineHeight != -1) {
            this.mBuilder.append((CharSequence) Character.toString((char) 2)).append((CharSequence) ShellUtil.COMMAND_LINE_END).setSpan(new AbsoluteSizeSpan(0), 0, 2, 33);
            length = 2;
        }
        this.mBuilder.append(this.mText);
        int length2 = this.mBuilder.length();
        if (this.verticalAlign != -1) {
            this.mBuilder.setSpan(new VerticalAlignSpan(this.verticalAlign), length, length2, this.flag);
        }
        if (this.foregroundColor != COLOR_DEFAULT) {
            this.mBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), length, length2, this.flag);
        }
        if (this.backgroundColor != COLOR_DEFAULT) {
            this.mBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), length, length2, this.flag);
        }
        if (this.first != -1) {
            this.mBuilder.setSpan(new LeadingMarginSpan.Standard(this.first, this.rest), length, length2, this.flag);
        }
        int i = this.quoteColor;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        if (i != COLOR_DEFAULT) {
            this.mBuilder.setSpan(new CustomQuoteSpan(i, this.stripeWidth, this.quoteGapWidth), length, length2, this.flag);
        }
        int i2 = this.bulletColor;
        if (i2 != COLOR_DEFAULT) {
            this.mBuilder.setSpan(new CustomBulletSpan(i2, this.bulletRadius, this.bulletGapWidth), length, length2, this.flag);
        }
        if (this.fontSize != -1) {
            this.mBuilder.setSpan(new AbsoluteSizeSpan(this.fontSize, this.fontSizeIsDp), length, length2, this.flag);
        }
        if (this.proportion != -1.0f) {
            this.mBuilder.setSpan(new RelativeSizeSpan(this.proportion), length, length2, this.flag);
        }
        if (this.xProportion != -1.0f) {
            this.mBuilder.setSpan(new ScaleXSpan(this.xProportion), length, length2, this.flag);
        }
        if (this.lineHeight != -1) {
            this.mBuilder.setSpan(new CustomLineHeightSpan(this.lineHeight, this.alignLine), length, length2, this.flag);
        }
        if (this.isStrikethrough) {
            this.mBuilder.setSpan(new StrikethroughSpan(), length, length2, this.flag);
        }
        if (this.isUnderline) {
            this.mBuilder.setSpan(new UnderlineSpan(), length, length2, this.flag);
        }
        if (this.isSuperscript) {
            this.mBuilder.setSpan(new SuperscriptSpan(), length, length2, this.flag);
        }
        if (this.isSubscript) {
            this.mBuilder.setSpan(new SubscriptSpan(), length, length2, this.flag);
        }
        if (this.isBold) {
            this.mBuilder.setSpan(new StyleSpan(1), length, length2, this.flag);
        }
        if (this.isItalic) {
            this.mBuilder.setSpan(new StyleSpan(2), length, length2, this.flag);
        }
        if (this.isBoldItalic) {
            this.mBuilder.setSpan(new StyleSpan(3), length, length2, this.flag);
        }
        if (this.fontFamily != null) {
            this.mBuilder.setSpan(new TypefaceSpan(this.fontFamily), length, length2, this.flag);
        }
        if (this.typeface != null) {
            this.mBuilder.setSpan(new CustomTypefaceSpan(this.typeface), length, length2, this.flag);
        }
        if (this.alignment != null) {
            this.mBuilder.setSpan(new AlignmentSpan.Standard(this.alignment), length, length2, this.flag);
        }
        ClickableSpan clickableSpan = this.clickSpan;
        if (clickableSpan != null) {
            this.mBuilder.setSpan(clickableSpan, length, length2, this.flag);
        }
        if (this.url != null) {
            this.mBuilder.setSpan(new URLSpan(this.url), length, length2, this.flag);
        }
        if (this.blurRadius != -1.0f) {
            this.mBuilder.setSpan(new MaskFilterSpan(new BlurMaskFilter(this.blurRadius, this.style)), length, length2, this.flag);
        }
        if (this.shader != null) {
            this.mBuilder.setSpan(new ShaderSpan(this.shader), length, length2, this.flag);
        }
        if (this.shadowRadius != -1.0f) {
            this.mBuilder.setSpan(new ShadowSpan(this.shadowRadius, this.shadowDx, this.shadowDy, this.shadowColor), length, length2, this.flag);
        }
        Object[] objArr4 = this.spans;
        if (objArr4 != null) {
            for (Object obj : objArr4) {
                this.mBuilder.setSpan(obj, length, length2, this.flag);
            }
        }
    }

    private void updateImage() {
        int length = this.mBuilder.length();
        if (length == 0) {
            this.mBuilder.append((CharSequence) Character.toString((char) 2));
            length = 1;
        }
        this.mBuilder.append((CharSequence) "<img>");
        int i = length + 5;
        if (this.imageBitmap != null) {
            this.mBuilder.setSpan(new CustomImageSpan(this.imageBitmap, this.alignImage), length, i, this.flag);
            return;
        }
        if (this.imageDrawable != null) {
            this.mBuilder.setSpan(new CustomImageSpan(this.imageDrawable, this.alignImage), length, i, this.flag);
        } else if (this.imageUri != null) {
            this.mBuilder.setSpan(new CustomImageSpan(this.imageUri, this.alignImage), length, i, this.flag);
        } else if (this.imageResourceId != -1) {
            this.mBuilder.setSpan(new CustomImageSpan(this.imageResourceId, this.alignImage), length, i, this.flag);
        }
    }

    private void updateSpace() {
        int length = this.mBuilder.length();
        this.mBuilder.append((CharSequence) "< >");
        this.mBuilder.setSpan(new SpaceSpan(this.spaceSize, this.spaceColor), length, length + 3, this.flag);
    }

    static class VerticalAlignSpan extends ReplacementSpan {
        static final int ALIGN_CENTER = 2;
        static final int ALIGN_TOP = 3;
        final int mVerticalAlignment;

        VerticalAlignSpan(int verticalAlignment) {
            this.mVerticalAlignment = verticalAlignment;
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
            return (int) paint.measureText(text.subSequence(start, end).toString());
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            CharSequence charSequenceSubSequence = text.subSequence(start, end);
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            canvas.drawText(charSequenceSubSequence.toString(), x, y - (((((fontMetricsInt.descent + y) + y) + fontMetricsInt.ascent) / 2) - ((bottom + top) / 2)), paint);
        }
    }

    static class CustomLineHeightSpan implements LineHeightSpan {
        static final int ALIGN_CENTER = 2;
        static final int ALIGN_TOP = 3;
        static Paint.FontMetricsInt sfm;
        private final int height;
        final int mVerticalAlignment;

        CustomLineHeightSpan(int height, int verticalAlignment) {
            this.height = height;
            this.mVerticalAlignment = verticalAlignment;
        }

        @Override // android.text.style.LineHeightSpan
        public void chooseHeight(final CharSequence text, final int start, final int end, final int spanstartv, final int v, final Paint.FontMetricsInt fm) {
            Paint.FontMetricsInt fontMetricsInt = sfm;
            if (fontMetricsInt == null) {
                Paint.FontMetricsInt fontMetricsInt2 = new Paint.FontMetricsInt();
                sfm = fontMetricsInt2;
                fontMetricsInt2.top = fm.top;
                sfm.ascent = fm.ascent;
                sfm.descent = fm.descent;
                sfm.bottom = fm.bottom;
                sfm.leading = fm.leading;
            } else {
                fm.top = fontMetricsInt.top;
                fm.ascent = sfm.ascent;
                fm.descent = sfm.descent;
                fm.bottom = sfm.bottom;
                fm.leading = sfm.leading;
            }
            int i = this.height - (((fm.descent + v) - fm.ascent) - spanstartv);
            if (i > 0) {
                int i2 = this.mVerticalAlignment;
                if (i2 == 3) {
                    fm.descent += i;
                } else if (i2 == 2) {
                    int i3 = i / 2;
                    fm.descent += i3;
                    fm.ascent -= i3;
                } else {
                    fm.ascent -= i;
                }
            }
            int i4 = this.height - (((v + fm.bottom) - fm.top) - spanstartv);
            if (i4 > 0) {
                int i5 = this.mVerticalAlignment;
                if (i5 == 3) {
                    fm.bottom += i4;
                } else if (i5 == 2) {
                    int i6 = i4 / 2;
                    fm.bottom += i6;
                    fm.top -= i6;
                } else {
                    fm.top -= i4;
                }
            }
            if (end == ((Spanned) text).getSpanEnd(this)) {
                sfm = null;
            }
        }
    }

    static class SpaceSpan extends ReplacementSpan {
        private final Paint paint;
        private final int width;

        private SpaceSpan(final int width) {
            this(width, 0);
        }

        private SpaceSpan(final int width, final int color) {
            Paint paint = new Paint();
            this.paint = paint;
            this.width = width;
            paint.setColor(color);
            paint.setStyle(Paint.Style.FILL);
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(final Paint paint, final CharSequence text, final int start, final int end, final Paint.FontMetricsInt fm) {
            return this.width;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(final Canvas canvas, final CharSequence text, final int start, final int end, final float x, final int top, final int y, final int bottom, final Paint paint) {
            canvas.drawRect(x, top, x + this.width, bottom, this.paint);
        }
    }

    static class CustomQuoteSpan implements LeadingMarginSpan {
        private final int color;
        private final int gapWidth;
        private final int stripeWidth;

        private CustomQuoteSpan(final int color, final int stripeWidth, final int gapWidth) {
            this.color = color;
            this.stripeWidth = stripeWidth;
            this.gapWidth = gapWidth;
        }

        @Override // android.text.style.LeadingMarginSpan
        public int getLeadingMargin(final boolean first) {
            return this.stripeWidth + this.gapWidth;
        }

        @Override // android.text.style.LeadingMarginSpan
        public void drawLeadingMargin(final Canvas c, final Paint p, final int x, final int dir, final int top, final int baseline, final int bottom, final CharSequence text, final int start, final int end, final boolean first, final Layout layout) {
            Paint.Style style = p.getStyle();
            int color = p.getColor();
            p.setStyle(Paint.Style.FILL);
            p.setColor(this.color);
            c.drawRect(x, top, x + (this.stripeWidth * dir), bottom, p);
            p.setStyle(style);
            p.setColor(color);
        }
    }

    static class CustomBulletSpan implements LeadingMarginSpan {
        private final int color;
        private final int gapWidth;
        private final int radius;
        private Path sBulletPath;

        private CustomBulletSpan(final int color, final int radius, final int gapWidth) {
            this.sBulletPath = null;
            this.color = color;
            this.radius = radius;
            this.gapWidth = gapWidth;
        }

        @Override // android.text.style.LeadingMarginSpan
        public int getLeadingMargin(final boolean first) {
            return (this.radius * 2) + this.gapWidth;
        }

        @Override // android.text.style.LeadingMarginSpan
        public void drawLeadingMargin(final Canvas c, final Paint p, final int x, final int dir, final int top, final int baseline, final int bottom, final CharSequence text, final int start, final int end, final boolean first, final Layout l) {
            if (((Spanned) text).getSpanStart(this) == start) {
                Paint.Style style = p.getStyle();
                int color = p.getColor();
                p.setColor(this.color);
                p.setStyle(Paint.Style.FILL);
                if (c.isHardwareAccelerated()) {
                    if (this.sBulletPath == null) {
                        Path path = new Path();
                        this.sBulletPath = path;
                        path.addCircle(0.0f, 0.0f, this.radius, Path.Direction.CW);
                    }
                    c.save();
                    c.translate(x + (dir * this.radius), (top + bottom) / 2.0f);
                    c.drawPath(this.sBulletPath, p);
                    c.restore();
                } else {
                    int i = this.radius;
                    c.drawCircle(x + (dir * i), (top + bottom) / 2.0f, i, p);
                }
                p.setColor(color);
                p.setStyle(style);
            }
        }
    }

    static class CustomTypefaceSpan extends TypefaceSpan {
        private final Typeface newType;

        private CustomTypefaceSpan(final Typeface type) {
            super("");
            this.newType = type;
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.CharacterStyle
        public void updateDrawState(final TextPaint textPaint) {
            apply(textPaint, this.newType);
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.MetricAffectingSpan
        public void updateMeasureState(final TextPaint paint) {
            apply(paint, this.newType);
        }

        private void apply(final Paint paint, final Typeface tf) {
            Typeface typeface = paint.getTypeface();
            int style = (typeface == null ? 0 : typeface.getStyle()) & (~tf.getStyle());
            if ((style & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((style & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.getShader();
            paint.setTypeface(tf);
        }
    }

    static class CustomImageSpan extends CustomDynamicDrawableSpan {
        private Uri mContentUri;
        private Drawable mDrawable;
        private int mResourceId;

        private CustomImageSpan(final Bitmap b, final int verticalAlignment) {
            super(verticalAlignment);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(Utils.getApp().getResources(), b);
            this.mDrawable = bitmapDrawable;
            bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }

        private CustomImageSpan(final Drawable d, final int verticalAlignment) {
            super(verticalAlignment);
            this.mDrawable = d;
            d.setBounds(0, 0, d.getIntrinsicWidth(), this.mDrawable.getIntrinsicHeight());
        }

        private CustomImageSpan(final Uri uri, final int verticalAlignment) {
            super(verticalAlignment);
            this.mContentUri = uri;
        }

        private CustomImageSpan(final int resourceId, final int verticalAlignment) {
            super(verticalAlignment);
            this.mResourceId = resourceId;
        }

        @Override // com.movieboxpro.android.utils.SpanUtils.CustomDynamicDrawableSpan
        public Drawable getDrawable() {
            Drawable drawable;
            Drawable drawable2 = this.mDrawable;
            if (drawable2 != null) {
                return drawable2;
            }
            BitmapDrawable bitmapDrawable = null;
            if (this.mContentUri != null) {
                try {
                    InputStream inputStreamOpenInputStream = Utils.getApp().getContentResolver().openInputStream(this.mContentUri);
                    BitmapDrawable bitmapDrawable2 = new BitmapDrawable(Utils.getApp().getResources(), BitmapFactory.decodeStream(inputStreamOpenInputStream));
                    try {
                        bitmapDrawable2.setBounds(0, 0, bitmapDrawable2.getIntrinsicWidth(), bitmapDrawable2.getIntrinsicHeight());
                        if (inputStreamOpenInputStream != null) {
                            inputStreamOpenInputStream.close();
                        }
                        return bitmapDrawable2;
                    } catch (Exception e) {
                        e = e;
                        bitmapDrawable = bitmapDrawable2;
                        Log.e("sms", "Failed to loaded content " + this.mContentUri, e);
                        return bitmapDrawable;
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } else {
                try {
                    drawable = ContextCompat.getDrawable(Utils.getApp(), this.mResourceId);
                    try {
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    } catch (Exception unused) {
                        Log.e("sms", "Unable to find resource: " + this.mResourceId);
                        return drawable;
                    }
                } catch (Exception unused2) {
                    drawable = null;
                }
            }
        }
    }

    static abstract class CustomDynamicDrawableSpan extends ReplacementSpan {
        static final int ALIGN_BASELINE = 1;
        static final int ALIGN_BOTTOM = 0;
        static final int ALIGN_CENTER = 2;
        static final int ALIGN_TOP = 3;
        private WeakReference<Drawable> mDrawableRef;
        final int mVerticalAlignment;

        public abstract Drawable getDrawable();

        private CustomDynamicDrawableSpan() {
            this.mVerticalAlignment = 0;
        }

        private CustomDynamicDrawableSpan(final int verticalAlignment) {
            this.mVerticalAlignment = verticalAlignment;
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(final Paint paint, final CharSequence text, final int start, final int end, final Paint.FontMetricsInt fm) {
            int i;
            Rect bounds = getCachedDrawable().getBounds();
            if (fm != null && (i = fm.bottom - fm.top) < bounds.height()) {
                int i2 = this.mVerticalAlignment;
                if (i2 == 3) {
                    fm.top = fm.top;
                    fm.bottom = bounds.height() + fm.top;
                } else if (i2 == 2) {
                    int i3 = i / 4;
                    fm.top = ((-bounds.height()) / 2) - i3;
                    fm.bottom = (bounds.height() / 2) - i3;
                } else {
                    fm.top = (-bounds.height()) + fm.bottom;
                    fm.bottom = fm.bottom;
                }
                fm.ascent = fm.top;
                fm.descent = fm.bottom;
            }
            return bounds.right;
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(final Canvas canvas, final CharSequence text, final int start, final int end, final float x, final int top, final int y, final int bottom, final Paint paint) {
            int iHeight;
            float fHeight;
            Drawable cachedDrawable = getCachedDrawable();
            Rect bounds = cachedDrawable.getBounds();
            canvas.save();
            if (bounds.height() < bottom - top) {
                int i = this.mVerticalAlignment;
                if (i == 3) {
                    fHeight = top;
                } else {
                    if (i == 2) {
                        iHeight = ((bottom + top) - bounds.height()) / 2;
                    } else if (i == 1) {
                        fHeight = y - bounds.height();
                    } else {
                        iHeight = bottom - bounds.height();
                    }
                    fHeight = iHeight;
                }
                canvas.translate(x, fHeight);
            } else {
                canvas.translate(x, top);
            }
            cachedDrawable.draw(canvas);
            canvas.restore();
        }

        private Drawable getCachedDrawable() {
            WeakReference<Drawable> weakReference = this.mDrawableRef;
            Drawable drawable = weakReference != null ? weakReference.get() : null;
            if (drawable != null) {
                return drawable;
            }
            Drawable drawable2 = getDrawable();
            this.mDrawableRef = new WeakReference<>(drawable2);
            return drawable2;
        }
    }

    static class ShaderSpan extends CharacterStyle implements UpdateAppearance {
        private Shader mShader;

        private ShaderSpan(final Shader shader) {
            this.mShader = shader;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(final TextPaint tp) {
            tp.setShader(this.mShader);
        }
    }

    static class ShadowSpan extends CharacterStyle implements UpdateAppearance {
        private float dx;
        private float dy;
        private float radius;
        private int shadowColor;

        private ShadowSpan(final float radius, final float dx, final float dy, final int shadowColor) {
            this.radius = radius;
            this.dx = dx;
            this.dy = dy;
            this.shadowColor = shadowColor;
        }

        @Override // android.text.style.CharacterStyle
        public void updateDrawState(final TextPaint tp) {
            tp.setShadowLayer(this.radius, this.dx, this.dy, this.shadowColor);
        }
    }

    private static class SerializableSpannableStringBuilder extends SpannableStringBuilder implements Serializable {
        private static final long serialVersionUID = 4909567650765875771L;

        private SerializableSpannableStringBuilder() {
        }
    }

    public static SpanUtils with(final TextView textView) {
        return new SpanUtils(textView);
    }
}
