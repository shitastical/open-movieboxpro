package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatTextView;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.androidtv.R;

/* JADX INFO: loaded from: classes5.dex */
public class SpannableFoldTextView extends AppCompatTextView implements View.OnClickListener {
    private static final String ELLIPSIZE_END = "...";
    private static final int END = 0;
    private static final String EXPAND_TIP_TEXT = "UP ";
    private static final String FOLD_TIP_TEXT = "All ";
    private static final int MAX_LINE = 2;
    private static final String TAG = "SpannableFoldTextView";
    private static final int TIP_COLOR = -1;
    private boolean flag;
    private HttpSpan httpSpan;
    private boolean isExpand;
    private boolean isExpandSpanClick;
    private boolean isParentClick;
    private boolean isShowTipAfterExpand;
    private View.OnClickListener listener;
    private String mExpandText;
    private String mFoldText;
    private String mHttpSpan;
    private CharSequence mOriginalText;
    private int mShowMaxLine;
    private ExpandSpan mSpan;
    private boolean mTipClickable;
    private int mTipColor;
    private int mTipGravity;

    public SpannableFoldTextView(Context context) {
        this(context, null);
    }

    public SpannableFoldTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override // android.widget.TextView
    public void setTextColor(int color) {
        super.setTextColor(color);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SpannableFoldTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mShowMaxLine = 2;
        this.mSpan = new ExpandSpan();
        this.httpSpan = new HttpSpan();
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FoldTextView);
            this.mShowMaxLine = typedArrayObtainStyledAttributes.getInt(R.styleable.FoldTextView_showMaxLine, 2);
            this.mTipGravity = typedArrayObtainStyledAttributes.getInt(R.styleable.FoldTextView_tipGravity, 0);
            this.mTipColor = typedArrayObtainStyledAttributes.getColor(R.styleable.FoldTextView_tipColor, -1);
            this.mTipClickable = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FoldTextView_tipClickable, false);
            this.mFoldText = typedArrayObtainStyledAttributes.getString(R.styleable.FoldTextView_foldText);
            this.mExpandText = typedArrayObtainStyledAttributes.getString(R.styleable.FoldTextView_expandText);
            this.isShowTipAfterExpand = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FoldTextView_showTipAfterExpand, false);
            this.isParentClick = typedArrayObtainStyledAttributes.getBoolean(R.styleable.FoldTextView_isSetParentClick, false);
            typedArrayObtainStyledAttributes.recycle();
        }
        if (TextUtils.isEmpty(this.mExpandText)) {
            this.mExpandText = EXPAND_TIP_TEXT;
        }
        if (TextUtils.isEmpty(this.mFoldText)) {
            this.mFoldText = FOLD_TIP_TEXT;
        }
    }

    @Override // android.widget.TextView
    public void setText(final CharSequence text, final TextView.BufferType type) {
        if (TextUtils.isEmpty(text) || this.mShowMaxLine == 0) {
            super.setText(text, type);
            return;
        }
        if (this.isExpand) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(this.mOriginalText);
            if (this.mHttpSpan != null) {
                spannableStringBuilder.append("\n\ns");
                Drawable drawable = getResources().getDrawable(R.drawable.ic_imdb);
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                spannableStringBuilder.setSpan(new myImageSpain(this, drawable, 1), spannableStringBuilder.length() - 1, spannableStringBuilder.length(), 17);
                spannableStringBuilder.append((CharSequence) this.mHttpSpan);
                spannableStringBuilder.setSpan(this.httpSpan, spannableStringBuilder.length() - this.mHttpSpan.length(), spannableStringBuilder.length(), 17);
            }
            addTip(spannableStringBuilder, type);
            return;
        }
        if (!this.flag) {
            getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.movieboxpro.android.view.widget.SpannableFoldTextView.1
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    SpannableFoldTextView.this.getViewTreeObserver().removeOnPreDrawListener(this);
                    SpannableFoldTextView.this.flag = true;
                    SpannableFoldTextView.this.formatText(text, type);
                    return true;
                }
            });
        } else {
            formatText(text, type);
        }
    }

    private void addTip(SpannableStringBuilder span, TextView.BufferType type) {
        if (!this.isExpand || this.isShowTipAfterExpand) {
            if (this.mTipGravity == 0) {
                span.append("  ");
            } else {
                span.append(" ");
            }
            if (this.isExpand) {
                Drawable drawable = getResources().getDrawable(R.drawable.ic_expandtext_up);
                drawable.setBounds(0, 0, getLayout().getWidth(), drawable.getIntrinsicHeight());
                span.setSpan(new myImageSpain(this, drawable, 1), span.length() - 1, span.length(), 17);
            } else {
                Drawable drawable2 = getResources().getDrawable(R.drawable.ic_expandtext);
                drawable2.setBounds(0, 0, getLayout().getWidth(), drawable2.getIntrinsicHeight());
                span.setSpan(new myImageSpain(this, drawable2, 1), span.length() - 1, span.length(), 17);
            }
            if (this.mTipClickable) {
                span.setSpan(this.mSpan, span.length(), span.length(), 17);
                if (this.isParentClick) {
                    setMovementMethod(MyLinkMovementMethod.getInstance());
                    setClickable(false);
                    setFocusable(false);
                    setLongClickable(false);
                } else {
                    setMovementMethod(LinkMovementMethod.getInstance());
                }
            }
            span.setSpan(new ForegroundColorSpan(this.mTipColor), span.length(), span.length(), 17);
        }
        super.setText(span, type);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void formatText(CharSequence text, final TextView.BufferType type) {
        this.mOriginalText = text;
        Layout layout = getLayout();
        if (layout == null || !layout.getText().equals(this.mOriginalText)) {
            super.setText(this.mOriginalText, type);
            layout = getLayout();
        }
        if (layout == null) {
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.movieboxpro.android.view.widget.SpannableFoldTextView.2
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    SpannableFoldTextView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    SpannableFoldTextView spannableFoldTextView = SpannableFoldTextView.this;
                    spannableFoldTextView.translateText(spannableFoldTextView.getLayout(), type);
                }
            });
        } else {
            translateText(layout, type);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void translateText(Layout layout, TextView.BufferType type) {
        if (layout.getLineCount() > this.mShowMaxLine) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int lineStart = layout.getLineStart(this.mShowMaxLine - 1);
            int lineVisibleEnd = layout.getLineVisibleEnd(this.mShowMaxLine - 1);
            TextPaint paint = getPaint();
            StringBuilder sb = new StringBuilder(ELLIPSIZE_END);
            if (this.mTipGravity == 0) {
                sb.append("  ");
                sb.append(this.mFoldText);
            }
            spannableStringBuilder.append(this.mOriginalText.subSequence(0, lineVisibleEnd - (paint.breakText(this.mOriginalText, lineStart, lineVisibleEnd, false, paint.measureText(sb.toString()), null) + 1)));
            spannableStringBuilder.append(ELLIPSIZE_END);
            addTip(spannableStringBuilder, type);
        }
    }

    public void setShowMaxLine(int mShowMaxLine) {
        this.mShowMaxLine = mShowMaxLine;
    }

    public void setFoldText(String mFoldText) {
        this.mFoldText = mFoldText;
    }

    public void setExpandText(String mExpandText) {
        this.mExpandText = mExpandText;
    }

    public void setTipGravity(int mTipGravity) {
        this.mTipGravity = mTipGravity;
    }

    public void setTipColor(int mTipColor) {
        this.mTipColor = mTipColor;
    }

    public void setTipClickable(boolean mTipClickable) {
        this.mTipClickable = mTipClickable;
    }

    public void setHttpSpan(String mHttpText) {
        this.mHttpSpan = mHttpText;
    }

    public void setShowTipAfterExpand(boolean showTipAfterExpand) {
        this.isShowTipAfterExpand = showTipAfterExpand;
    }

    public void setParentClick(boolean parentClick) {
        this.isParentClick = parentClick;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (this.isExpandSpanClick) {
            this.isExpandSpanClick = false;
            return;
        }
        this.isExpand = !this.isExpand;
        Log.d("emmm", "onClick: parentclick click");
        setText(this.mOriginalText);
    }

    private class ExpandSpan extends ClickableSpan {
        private ExpandSpan() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View widget) {
            if (SpannableFoldTextView.this.mTipClickable) {
                SpannableFoldTextView spannableFoldTextView = SpannableFoldTextView.this;
                spannableFoldTextView.isExpand = !spannableFoldTextView.isExpand;
                SpannableFoldTextView.this.isExpandSpanClick = true;
                Log.d("emmm", "onClick: span click");
                SpannableFoldTextView spannableFoldTextView2 = SpannableFoldTextView.this;
                spannableFoldTextView2.setText(spannableFoldTextView2.mOriginalText);
            }
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint ds) {
            ds.setColor(SpannableFoldTextView.this.mTipColor);
            ds.setUnderlineText(false);
        }
    }

    private class HttpSpan extends ClickableSpan {
        private HttpSpan() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View widget) {
            if (SpannableFoldTextView.this.mTipClickable) {
                SpannableFoldTextView.this.isExpandSpanClick = true;
                Log.d("emmm", "onClick: span click");
                if (SpannableFoldTextView.this.mHttpSpan != null) {
                    SystemUtils.startBrowser(SpannableFoldTextView.this.getContext(), SpannableFoldTextView.this.mHttpSpan);
                }
            }
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint ds) {
            ds.setColor(SpannableFoldTextView.this.mTipColor);
            ds.setUnderlineText(false);
        }
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener l) {
        this.listener = l;
        super.setOnClickListener(this);
    }

    private class myImageSpain extends ImageSpan {
        public myImageSpain(final SpannableFoldTextView this$0, Bitmap b) {
            super(b);
        }

        public myImageSpain(final SpannableFoldTextView this$0, Drawable d, int verticalAlignment) {
            super(d, verticalAlignment);
        }

        @Override // android.text.style.DynamicDrawableSpan, android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
            Drawable drawable = getDrawable();
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            int i = ((((fontMetricsInt.descent + y) + y) + fontMetricsInt.ascent) / 2) - (drawable.getBounds().bottom / 2);
            canvas.save();
            canvas.translate(x, i);
            drawable.draw(canvas);
            canvas.restore();
        }
    }
}
