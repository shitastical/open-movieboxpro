package com.movieboxpro.android.view.widget;

import android.text.Layout;
import android.text.NoCopySpan;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.TextView;

/* JADX INFO: loaded from: classes5.dex */
public class MyLinkMovementMethod extends ScrollingMovementMethod {
    private static final int CLICK = 1;
    private static final int DOWN = 3;
    private static Object FROM_BELOW = new NoCopySpan.Concrete();
    private static final int UP = 2;
    private static MyLinkMovementMethod sInstance;

    @Override // android.text.method.BaseMovementMethod, android.text.method.MovementMethod
    public boolean canSelectArbitrarily() {
        return true;
    }

    @Override // android.text.method.BaseMovementMethod
    protected boolean handleMovementKey(TextView widget, Spannable buffer, int keyCode, int movementMetaState, KeyEvent event) {
        if ((keyCode == 23 || keyCode == 66) && KeyEvent.metaStateHasNoModifiers(movementMetaState) && event.getAction() == 0 && event.getRepeatCount() == 0 && action(1, widget, buffer)) {
            return true;
        }
        return super.handleMovementKey(widget, buffer, keyCode, movementMetaState, event);
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod
    protected boolean up(TextView widget, Spannable buffer) {
        if (action(2, widget, buffer)) {
            return true;
        }
        return super.up(widget, buffer);
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod
    protected boolean down(TextView widget, Spannable buffer) {
        if (action(3, widget, buffer)) {
            return true;
        }
        return super.down(widget, buffer);
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod
    protected boolean left(TextView widget, Spannable buffer) {
        if (action(2, widget, buffer)) {
            return true;
        }
        return super.left(widget, buffer);
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod
    protected boolean right(TextView widget, Spannable buffer) {
        if (action(3, widget, buffer)) {
            return true;
        }
        return super.right(widget, buffer);
    }

    private boolean action(int what, TextView widget, Spannable buffer) {
        Layout layout = widget.getLayout();
        int totalPaddingTop = widget.getTotalPaddingTop() + widget.getTotalPaddingBottom();
        int scrollY = widget.getScrollY();
        int height = (widget.getHeight() + scrollY) - totalPaddingTop;
        int lineForVertical = layout.getLineForVertical(scrollY);
        int lineForVertical2 = layout.getLineForVertical(height);
        int lineStart = layout.getLineStart(lineForVertical);
        int lineEnd = layout.getLineEnd(lineForVertical2);
        ClickableSpan[] clickableSpanArr = (ClickableSpan[]) buffer.getSpans(lineStart, lineEnd, ClickableSpan.class);
        int selectionStart = Selection.getSelectionStart(buffer);
        int selectionEnd = Selection.getSelectionEnd(buffer);
        int iMin = Math.min(selectionStart, selectionEnd);
        int iMax = Math.max(selectionStart, selectionEnd);
        if (iMin < 0 && buffer.getSpanStart(FROM_BELOW) >= 0) {
            iMin = buffer.length();
            iMax = iMin;
        }
        if (iMin > lineEnd) {
            iMax = Integer.MAX_VALUE;
            iMin = Integer.MAX_VALUE;
        }
        int spanStart = -1;
        if (iMax < lineStart) {
            iMax = -1;
            iMin = -1;
        }
        if (what == 1) {
            if (iMin == iMax) {
                return false;
            }
            ClickableSpan[] clickableSpanArr2 = (ClickableSpan[]) buffer.getSpans(iMin, iMax, ClickableSpan.class);
            if (clickableSpanArr2.length != 1) {
                return false;
            }
            clickableSpanArr2[0].onClick(widget);
        } else if (what == 2) {
            int i = -1;
            for (int i2 = 0; i2 < clickableSpanArr.length; i2++) {
                int spanEnd = buffer.getSpanEnd(clickableSpanArr[i2]);
                if ((spanEnd < iMax || iMin == iMax) && spanEnd > i) {
                    spanStart = buffer.getSpanStart(clickableSpanArr[i2]);
                    i = spanEnd;
                }
            }
            if (spanStart >= 0) {
                Selection.setSelection(buffer, i, spanStart);
                return true;
            }
        } else if (what == 3) {
            int spanEnd2 = Integer.MAX_VALUE;
            int i3 = Integer.MAX_VALUE;
            for (int i4 = 0; i4 < clickableSpanArr.length; i4++) {
                int spanStart2 = buffer.getSpanStart(clickableSpanArr[i4]);
                if ((spanStart2 > iMin || iMin == iMax) && spanStart2 < i3) {
                    spanEnd2 = buffer.getSpanEnd(clickableSpanArr[i4]);
                    i3 = spanStart2;
                }
            }
            if (spanEnd2 < Integer.MAX_VALUE) {
                Selection.setSelection(buffer, i3, spanEnd2);
                return true;
            }
        }
        return false;
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        int action = event.getAction();
        if (action == 1 || action == 0) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            int totalPaddingLeft = x - widget.getTotalPaddingLeft();
            int totalPaddingTop = y - widget.getTotalPaddingTop();
            int scrollX = totalPaddingLeft + widget.getScrollX();
            int scrollY = totalPaddingTop + widget.getScrollY();
            Layout layout = widget.getLayout();
            int offsetForHorizontal = layout.getOffsetForHorizontal(layout.getLineForVertical(scrollY), scrollX);
            ClickableSpan[] clickableSpanArr = (ClickableSpan[]) buffer.getSpans(offsetForHorizontal, offsetForHorizontal, ClickableSpan.class);
            if (clickableSpanArr.length == 0) {
                Selection.removeSelection(buffer);
                return false;
            }
            if (action == 1) {
                clickableSpanArr[0].onClick(widget);
            } else if (action == 0) {
                Selection.setSelection(buffer, buffer.getSpanStart(clickableSpanArr[0]), buffer.getSpanEnd(clickableSpanArr[0]));
            }
            return true;
        }
        return super.onTouchEvent(widget, buffer, event);
    }

    @Override // android.text.method.BaseMovementMethod, android.text.method.MovementMethod
    public void initialize(TextView widget, Spannable text) {
        Selection.removeSelection(text);
        text.removeSpan(FROM_BELOW);
    }

    @Override // android.text.method.ScrollingMovementMethod, android.text.method.BaseMovementMethod, android.text.method.MovementMethod
    public void onTakeFocus(TextView view, Spannable text, int dir) {
        Selection.removeSelection(text);
        if ((dir & 1) != 0) {
            text.setSpan(FROM_BELOW, 0, 0, 34);
        } else {
            text.removeSpan(FROM_BELOW);
        }
    }

    public static MovementMethod getInstance() {
        if (sInstance == null) {
            sInstance = new MyLinkMovementMethod();
        }
        return sInstance;
    }
}
