package com.owen.focus;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class DrawableFocusBorder extends AbsFocusBorder {
    private View mBorderView;

    @Override // com.owen.focus.AbsFocusBorder
    public float getRoundRadius() {
        return 0.0f;
    }

    @Override // com.owen.focus.AbsFocusBorder
    List<Animator> getSequentiallyAnimators(float f, float f2, int i, int i2, AbsFocusBorder.Options options) {
        return null;
    }

    @Override // com.owen.focus.AbsFocusBorder
    List<Animator> getTogetherAnimators(float f, float f2, int i, int i2, AbsFocusBorder.Options options) {
        return null;
    }

    private DrawableFocusBorder(Context context, Builder builder) {
        super(context, builder);
        Drawable drawable = builder.mBorderDrawable;
        Rect rect = new Rect();
        drawable.getPadding(rect);
        this.mPaddingRectF.set(rect);
        View view = new View(context);
        this.mBorderView = view;
        view.setLayerType(1, null);
        this.mBorderView.setBackground(drawable);
        addView(this.mBorderView, new ViewGroup.LayoutParams(-1, -1));
    }

    @Override // com.owen.focus.AbsFocusBorder
    public View getBorderView() {
        return this.mBorderView;
    }

    @Override // com.owen.focus.AbsFocusBorder, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public static final class Builder extends AbsFocusBorder.Builder {
        private int mBorderResId = 0;
        private Drawable mBorderDrawable = null;

        public Builder borderDrawableRes(int i) {
            this.mBorderResId = i;
            return this;
        }

        public Builder borderDrawable(Drawable drawable) {
            this.mBorderDrawable = drawable;
            return this;
        }

        @Override // com.owen.focus.AbsFocusBorder.Builder
        public FocusBorder build(Activity activity) {
            if (activity == null) {
                throw new NullPointerException("The activity cannot be null");
            }
            if (this.mBorderDrawable == null && this.mBorderResId == 0) {
                throw new RuntimeException("The border Drawable or ResId cannot be null");
            }
            return build((ViewGroup) activity.findViewById(android.R.id.content));
        }

        @Override // com.owen.focus.AbsFocusBorder.Builder
        public FocusBorder build(ViewGroup viewGroup) {
            if (viewGroup == null) {
                throw new NullPointerException("The FocusBorder parent cannot be null");
            }
            Drawable drawable = this.mBorderDrawable;
            if (drawable == null) {
                drawable = viewGroup.getContext().getDrawable(this.mBorderResId);
            }
            this.mBorderDrawable = drawable;
            DrawableFocusBorder drawableFocusBorder = new DrawableFocusBorder(viewGroup.getContext().getApplicationContext(), this);
            viewGroup.addView(drawableFocusBorder, new ViewGroup.LayoutParams(1, 1));
            return drawableFocusBorder;
        }
    }
}
