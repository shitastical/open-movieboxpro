package com.movieboxpro.android.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.media3.exoplayer.ExoPlayer;
import com.hjq.toast.Toaster;
import com.movieboxpro.android.utils.tool.UtilsBridge;
import com.movieboxpro.androidtv.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes5.dex */
public final class ToastUtils {
    private static final int COLOR_DEFAULT = -16777217;
    private static final ToastUtils DEFAULT_MAKER = make();
    private static final String NOTHING = "toast nothing";
    private static final String NULL = "toast null";
    private static final String TAG_TOAST = "TAG_TOAST";
    private static WeakReference<IToast> sWeakToast;
    private String mMode;
    private int mGravity = -1;
    private int mXOffset = -1;
    private int mYOffset = -1;
    private int mBgColor = COLOR_DEFAULT;
    private int mBgResource = -1;
    private int mTextColor = COLOR_DEFAULT;
    private int mTextSize = -1;
    private boolean isLong = false;
    private Drawable[] mIcons = new Drawable[4];
    private boolean isNotUseSystemToast = false;

    interface IToast {
        void cancel();

        void setToastView(View view);

        void setToastView(CharSequence text);

        void show(int duration);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MODE {
        public static final String DARK = "dark";
        public static final String LIGHT = "light";
    }

    public static ToastUtils make() {
        return new ToastUtils();
    }

    public final ToastUtils setMode(String mode) {
        this.mMode = mode;
        return this;
    }

    public final ToastUtils setGravity(final int gravity, final int xOffset, final int yOffset) {
        this.mGravity = gravity;
        this.mXOffset = xOffset;
        this.mYOffset = yOffset;
        return this;
    }

    public final ToastUtils setBgColor(final int backgroundColor) {
        this.mBgColor = backgroundColor;
        return this;
    }

    public final ToastUtils setBgResource(final int bgResource) {
        this.mBgResource = bgResource;
        return this;
    }

    public final ToastUtils setTextColor(final int msgColor) {
        this.mTextColor = msgColor;
        return this;
    }

    public final ToastUtils setTextSize(final int textSize) {
        this.mTextSize = textSize;
        return this;
    }

    public final ToastUtils setDurationIsLong(boolean isLong) {
        this.isLong = isLong;
        return this;
    }

    public final ToastUtils setLeftIcon(int resId) {
        return setLeftIcon(ContextCompat.getDrawable(Utils.getApp(), resId));
    }

    public final ToastUtils setLeftIcon(Drawable drawable) {
        this.mIcons[0] = drawable;
        return this;
    }

    public final ToastUtils setTopIcon(int resId) {
        return setTopIcon(ContextCompat.getDrawable(Utils.getApp(), resId));
    }

    public final ToastUtils setTopIcon(Drawable drawable) {
        this.mIcons[1] = drawable;
        return this;
    }

    public final ToastUtils setRightIcon(int resId) {
        return setRightIcon(ContextCompat.getDrawable(Utils.getApp(), resId));
    }

    public final ToastUtils setRightIcon(Drawable drawable) {
        this.mIcons[2] = drawable;
        return this;
    }

    public final ToastUtils setBottomIcon(int resId) {
        return setBottomIcon(ContextCompat.getDrawable(Utils.getApp(), resId));
    }

    public final ToastUtils setBottomIcon(Drawable drawable) {
        this.mIcons[3] = drawable;
        return this;
    }

    public final ToastUtils setNotUseSystemToast() {
        this.isNotUseSystemToast = true;
        return this;
    }

    public static ToastUtils getDefaultMaker() {
        return DEFAULT_MAKER;
    }

    public final void show(final CharSequence text) {
        show(text, getDuration(), this);
    }

    public final void show(final int resId) {
        show(UtilsBridge.getString(resId), getDuration(), this);
    }

    public final void show(final int resId, final Object... args) {
        show(UtilsBridge.getString(resId, args), getDuration(), this);
    }

    public final void show(final String format, final Object... args) {
        show(UtilsBridge.format(format, args), getDuration(), this);
    }

    public final void show(final View view) {
        show(view, getDuration(), this);
    }

    private int getDuration() {
        return this.isLong ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View tryApplyUtilsToastView(final CharSequence text) {
        if (!MODE.DARK.equals(this.mMode) && !MODE.LIGHT.equals(this.mMode)) {
            Drawable[] drawableArr = this.mIcons;
            if (drawableArr[0] == null && drawableArr[1] == null && drawableArr[2] == null && drawableArr[3] == null) {
                return null;
            }
        }
        View viewLayoutId2View = UtilsBridge.layoutId2View(R.layout.utils_toast_view);
        TextView textView = (TextView) viewLayoutId2View.findViewById(android.R.id.message);
        if (MODE.DARK.equals(this.mMode)) {
            ((GradientDrawable) viewLayoutId2View.getBackground().mutate()).setColor(Color.parseColor("#BB000000"));
            textView.setTextColor(-1);
        }
        textView.setText(text);
        if (this.mIcons[0] != null) {
            View viewFindViewById = viewLayoutId2View.findViewById(R.id.utvLeftIconView);
            ViewCompat.setBackground(viewFindViewById, this.mIcons[0]);
            viewFindViewById.setVisibility(0);
        }
        if (this.mIcons[1] != null) {
            View viewFindViewById2 = viewLayoutId2View.findViewById(R.id.utvTopIconView);
            ViewCompat.setBackground(viewFindViewById2, this.mIcons[1]);
            viewFindViewById2.setVisibility(0);
        }
        if (this.mIcons[2] != null) {
            View viewFindViewById3 = viewLayoutId2View.findViewById(R.id.utvRightIconView);
            ViewCompat.setBackground(viewFindViewById3, this.mIcons[2]);
            viewFindViewById3.setVisibility(0);
        }
        if (this.mIcons[3] != null) {
            View viewFindViewById4 = viewLayoutId2View.findViewById(R.id.utvBottomIconView);
            ViewCompat.setBackground(viewFindViewById4, this.mIcons[3]);
            viewFindViewById4.setVisibility(0);
        }
        return viewLayoutId2View;
    }

    public static void showShort(final CharSequence text) {
        Toaster.show(text);
    }

    public static void showShort(final int resId) {
        show(UtilsBridge.getString(resId), 0, DEFAULT_MAKER);
    }

    public static void showShort(final int resId, final Object... args) {
        show(UtilsBridge.getString(resId, args), 0, DEFAULT_MAKER);
        Toaster.show(resId);
    }

    public static void showShort(final String format, final Object... args) {
        Toaster.show((CharSequence) UtilsBridge.format(format, args));
    }

    public static void showLong(final CharSequence text) {
        show(text, 1, DEFAULT_MAKER);
    }

    public static void showLong(final int resId) {
        show(UtilsBridge.getString(resId), 1, DEFAULT_MAKER);
    }

    public static void showLong(final int resId, final Object... args) {
        show(UtilsBridge.getString(resId), 1, DEFAULT_MAKER);
    }

    public static void showLong(final String format, final Object... args) {
        show(UtilsBridge.format(format, args), 1, DEFAULT_MAKER);
    }

    public static void cancel() {
        UtilsBridge.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.utils.ToastUtils.1
            @Override // java.lang.Runnable
            public void run() {
                if (ToastUtils.sWeakToast != null) {
                    IToast iToast = (IToast) ToastUtils.sWeakToast.get();
                    if (iToast != null) {
                        iToast.cancel();
                    }
                    ToastUtils.sWeakToast = null;
                }
            }
        });
    }

    private static void show(final CharSequence text, final int duration, final ToastUtils utils) {
        show(null, getToastFriendlyText(text), duration, utils);
    }

    private static void show(final View view, final int duration, final ToastUtils utils) {
        show(view, null, duration, utils);
    }

    private static void show(final View view, final CharSequence text, final int duration, final ToastUtils utils) {
        UtilsBridge.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.utils.ToastUtils.2
            @Override // java.lang.Runnable
            public void run() {
                ToastUtils.cancel();
                IToast iToastNewToast = ToastUtils.newToast(ToastUtils.this);
                ToastUtils.sWeakToast = new WeakReference(iToastNewToast);
                View view2 = view;
                if (view2 != null) {
                    iToastNewToast.setToastView(view2);
                } else {
                    iToastNewToast.setToastView(text);
                }
                iToastNewToast.show(duration);
            }
        });
    }

    private static CharSequence getToastFriendlyText(CharSequence src) {
        if (src == null) {
            return NULL;
        }
        return src.length() == 0 ? NOTHING : src;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static IToast newToast(ToastUtils toastUtils) {
        if (!toastUtils.isNotUseSystemToast && NotificationManagerCompat.from(Utils.getApp()).areNotificationsEnabled()) {
            if (Build.VERSION.SDK_INT < 23) {
                return new SystemToast(toastUtils);
            }
            if (!UtilsBridge.isGrantedDrawOverlays()) {
                return new SystemToast(toastUtils);
            }
        }
        if (Build.VERSION.SDK_INT < 25) {
            return new WindowManagerToast(toastUtils, 2005);
        }
        if (UtilsBridge.isGrantedDrawOverlays()) {
            if (Build.VERSION.SDK_INT >= 26) {
                return new WindowManagerToast(toastUtils, 2038);
            }
            return new WindowManagerToast(toastUtils, 2002);
        }
        return new ActivityToast(toastUtils);
    }

    static final class SystemToast extends AbsToast {
        SystemToast(ToastUtils toastUtils) {
            super(toastUtils);
            if (Build.VERSION.SDK_INT == 25) {
                try {
                    Field declaredField = Toast.class.getDeclaredField("mTN");
                    declaredField.setAccessible(true);
                    Object obj = declaredField.get(this.mToast);
                    Field declaredField2 = declaredField.getType().getDeclaredField("mHandler");
                    declaredField2.setAccessible(true);
                    declaredField2.set(obj, new SafeHandler((Handler) declaredField2.get(obj)));
                } catch (Exception unused) {
                }
            }
        }

        @Override // com.movieboxpro.android.utils.ToastUtils.IToast
        public void show(int duration) {
            if (this.mToast == null) {
                return;
            }
            this.mToast.setDuration(duration);
            this.mToast.show();
        }

        static class SafeHandler extends Handler {
            private Handler impl;

            SafeHandler(Handler impl) {
                this.impl = impl;
            }

            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                this.impl.handleMessage(msg);
            }

            @Override // android.os.Handler
            public void dispatchMessage(Message msg) {
                try {
                    this.impl.dispatchMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static final class WindowManagerToast extends AbsToast {
        private WindowManager.LayoutParams mParams;
        private WindowManager mWM;

        WindowManagerToast(ToastUtils toastUtils, int type) {
            super(toastUtils);
            this.mParams = new WindowManager.LayoutParams();
            this.mWM = (WindowManager) Utils.getApp().getSystemService("window");
            this.mParams.type = type;
        }

        WindowManagerToast(ToastUtils toastUtils, WindowManager wm, int type) {
            super(toastUtils);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.mParams = layoutParams;
            this.mWM = wm;
            layoutParams.type = type;
        }

        @Override // com.movieboxpro.android.utils.ToastUtils.IToast
        public void show(final int duration) {
            if (this.mToast == null) {
                return;
            }
            this.mParams.height = -2;
            this.mParams.width = -2;
            this.mParams.format = -3;
            this.mParams.windowAnimations = android.R.style.Animation.Toast;
            this.mParams.setTitle("ToastWithoutNotification");
            this.mParams.flags = 152;
            this.mParams.packageName = Utils.getApp().getPackageName();
            this.mParams.gravity = this.mToast.getGravity();
            if ((this.mParams.gravity & 7) == 7) {
                this.mParams.horizontalWeight = 1.0f;
            }
            if ((this.mParams.gravity & 112) == 112) {
                this.mParams.verticalWeight = 1.0f;
            }
            this.mParams.x = this.mToast.getXOffset();
            this.mParams.y = this.mToast.getYOffset();
            this.mParams.horizontalMargin = this.mToast.getHorizontalMargin();
            this.mParams.verticalMargin = this.mToast.getVerticalMargin();
            try {
                WindowManager windowManager = this.mWM;
                if (windowManager != null) {
                    windowManager.addView(this.mToastView, this.mParams);
                }
            } catch (Exception unused) {
            }
            UtilsBridge.runOnUiThreadDelayed(new Runnable() { // from class: com.movieboxpro.android.utils.ToastUtils.WindowManagerToast.1
                @Override // java.lang.Runnable
                public void run() {
                    WindowManagerToast.this.cancel();
                }
            }, duration == 0 ? ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS : 3500L);
        }

        @Override // com.movieboxpro.android.utils.ToastUtils.AbsToast, com.movieboxpro.android.utils.ToastUtils.IToast
        public void cancel() {
            try {
                WindowManager windowManager = this.mWM;
                if (windowManager != null) {
                    windowManager.removeViewImmediate(this.mToastView);
                    this.mWM = null;
                }
            } catch (Exception unused) {
            }
            super.cancel();
        }
    }

    static final class ActivityToast extends AbsToast {
        private static int sShowingIndex;
        private IToast iToast;
        private com.movieboxpro.android.utils.tool.Utils.ActivityLifecycleCallbacks mActivityLifecycleCallbacks;

        ActivityToast(ToastUtils toastUtils) {
            super(toastUtils);
        }

        @Override // com.movieboxpro.android.utils.ToastUtils.IToast
        public void show(int duration) {
            if (this.mToast == null) {
                return;
            }
            if (!UtilsBridge.isAppForeground()) {
                this.iToast = showSystemToast(duration);
                return;
            }
            boolean z = false;
            for (Activity activity : UtilsBridge.getActivityList()) {
                if (UtilsBridge.isActivityAlive(activity)) {
                    if (!z) {
                        this.iToast = showWithActivityWindow(activity, duration);
                        z = true;
                    } else {
                        showWithActivityView(activity, sShowingIndex, true);
                    }
                }
            }
            if (z) {
                registerLifecycleCallback();
                UtilsBridge.runOnUiThreadDelayed(new Runnable() { // from class: com.movieboxpro.android.utils.ToastUtils.ActivityToast.1
                    @Override // java.lang.Runnable
                    public void run() {
                        ActivityToast.this.cancel();
                    }
                }, duration == 0 ? ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS : 3500L);
                sShowingIndex++;
                return;
            }
            this.iToast = showSystemToast(duration);
        }

        @Override // com.movieboxpro.android.utils.ToastUtils.AbsToast, com.movieboxpro.android.utils.ToastUtils.IToast
        public void cancel() {
            Window window;
            if (isShowing()) {
                unregisterLifecycleCallback();
                for (Activity activity : UtilsBridge.getActivityList()) {
                    if (UtilsBridge.isActivityAlive(activity) && (window = activity.getWindow()) != null) {
                        ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                        StringBuilder sb = new StringBuilder(ToastUtils.TAG_TOAST);
                        sb.append(sShowingIndex - 1);
                        View viewFindViewWithTag = viewGroup.findViewWithTag(sb.toString());
                        if (viewFindViewWithTag != null) {
                            try {
                                viewGroup.removeView(viewFindViewWithTag);
                            } catch (Exception unused) {
                            }
                        }
                    }
                }
            }
            IToast iToast = this.iToast;
            if (iToast != null) {
                iToast.cancel();
                this.iToast = null;
            }
            super.cancel();
        }

        private IToast showSystemToast(int duration) {
            SystemToast systemToast = new SystemToast(this.mToastUtils);
            systemToast.mToast = this.mToast;
            systemToast.show(duration);
            return systemToast;
        }

        private IToast showWithActivityWindow(Activity activity, int duration) {
            WindowManagerToast windowManagerToast = new WindowManagerToast(this.mToastUtils, activity.getWindowManager(), 99);
            windowManagerToast.mToastView = getToastViewSnapshot(-1);
            windowManagerToast.mToast = this.mToast;
            windowManagerToast.show(duration);
            return windowManagerToast;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void showWithActivityView(final Activity activity, final int index, boolean useAnim) {
            Window window = activity.getWindow();
            if (window != null) {
                ViewGroup viewGroup = (ViewGroup) window.getDecorView();
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                layoutParams.gravity = this.mToast.getGravity();
                layoutParams.bottomMargin = this.mToast.getYOffset() + UtilsBridge.getNavBarHeight();
                layoutParams.topMargin = this.mToast.getYOffset() + UtilsBridge.getStatusBarHeight();
                layoutParams.leftMargin = this.mToast.getXOffset();
                View toastViewSnapshot = getToastViewSnapshot(index);
                if (useAnim) {
                    toastViewSnapshot.setAlpha(0.0f);
                    toastViewSnapshot.animate().alpha(1.0f).setDuration(200L).start();
                }
                viewGroup.addView(toastViewSnapshot, layoutParams);
            }
        }

        private void registerLifecycleCallback() {
            final int i = sShowingIndex;
            com.movieboxpro.android.utils.tool.Utils.ActivityLifecycleCallbacks activityLifecycleCallbacks = new com.movieboxpro.android.utils.tool.Utils.ActivityLifecycleCallbacks() { // from class: com.movieboxpro.android.utils.ToastUtils.ActivityToast.2
                @Override // com.movieboxpro.android.utils.tool.Utils.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity) {
                    if (ActivityToast.this.isShowing()) {
                        ActivityToast.this.showWithActivityView(activity, i, false);
                    }
                }
            };
            this.mActivityLifecycleCallbacks = activityLifecycleCallbacks;
            UtilsBridge.addActivityLifecycleCallbacks(activityLifecycleCallbacks);
        }

        private void unregisterLifecycleCallback() {
            UtilsBridge.removeActivityLifecycleCallbacks(this.mActivityLifecycleCallbacks);
            this.mActivityLifecycleCallbacks = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isShowing() {
            return this.mActivityLifecycleCallbacks != null;
        }
    }

    static abstract class AbsToast implements IToast {
        protected Toast mToast = new Toast(Utils.getApp());
        protected ToastUtils mToastUtils;
        protected View mToastView;

        private void processRtlIfNeed() {
        }

        AbsToast(ToastUtils toastUtils) {
            this.mToastUtils = toastUtils;
            if (toastUtils.mGravity == -1 && this.mToastUtils.mXOffset == -1 && this.mToastUtils.mYOffset == -1) {
                return;
            }
            this.mToast.setGravity(this.mToastUtils.mGravity, this.mToastUtils.mXOffset, this.mToastUtils.mYOffset);
        }

        @Override // com.movieboxpro.android.utils.ToastUtils.IToast
        public void setToastView(View view) {
            this.mToastView = view;
            this.mToast.setView(view);
        }

        @Override // com.movieboxpro.android.utils.ToastUtils.IToast
        public void setToastView(CharSequence text) {
            View viewTryApplyUtilsToastView = this.mToastUtils.tryApplyUtilsToastView(text);
            if (viewTryApplyUtilsToastView != null) {
                setToastView(viewTryApplyUtilsToastView);
                processRtlIfNeed();
                return;
            }
            View view = this.mToast.getView();
            this.mToastView = view;
            if (view == null || view.findViewById(android.R.id.message) == null) {
                setToastView(UtilsBridge.layoutId2View(R.layout.utils_toast_view));
            }
            TextView textView = (TextView) this.mToastView.findViewById(android.R.id.message);
            textView.setText(text);
            if (this.mToastUtils.mTextColor != ToastUtils.COLOR_DEFAULT) {
                textView.setTextColor(this.mToastUtils.mTextColor);
            }
            if (this.mToastUtils.mTextSize != -1) {
                textView.setTextSize(this.mToastUtils.mTextSize);
            }
            setBg(textView);
            processRtlIfNeed();
        }

        private void setBg(final TextView msgTv) {
            if (this.mToastUtils.mBgResource != -1) {
                this.mToastView.setBackgroundResource(this.mToastUtils.mBgResource);
                msgTv.setBackgroundColor(0);
                return;
            }
            if (this.mToastUtils.mBgColor != ToastUtils.COLOR_DEFAULT) {
                Drawable background = this.mToastView.getBackground();
                Drawable background2 = msgTv.getBackground();
                if (background != null && background2 != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.mToastUtils.mBgColor, PorterDuff.Mode.SRC_IN));
                    msgTv.setBackgroundColor(0);
                } else if (background != null) {
                    background.mutate().setColorFilter(new PorterDuffColorFilter(this.mToastUtils.mBgColor, PorterDuff.Mode.SRC_IN));
                } else if (background2 != null) {
                    background2.mutate().setColorFilter(new PorterDuffColorFilter(this.mToastUtils.mBgColor, PorterDuff.Mode.SRC_IN));
                } else {
                    this.mToastView.setBackgroundColor(this.mToastUtils.mBgColor);
                }
            }
        }

        @Override // com.movieboxpro.android.utils.ToastUtils.IToast
        public void cancel() {
            Toast toast = this.mToast;
            if (toast != null) {
                toast.cancel();
            }
            this.mToast = null;
            this.mToastView = null;
        }

        View getToastViewSnapshot(final int index) {
            Bitmap bitmapView2Bitmap = UtilsBridge.view2Bitmap(this.mToastView);
            ImageView imageView = new ImageView(Utils.getApp());
            imageView.setTag(ToastUtils.TAG_TOAST + index);
            imageView.setImageBitmap(bitmapView2Bitmap);
            return imageView;
        }
    }

    public static final class UtilsMaxWidthRelativeLayout extends RelativeLayout {
        private static final int SPACING = DensityUtils.dp2px(80.0f);

        public UtilsMaxWidthRelativeLayout(Context context) {
            super(context);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public UtilsMaxWidthRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }

        @Override // android.widget.RelativeLayout, android.view.View
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(DensityUtils.getScreenWidth() - SPACING, Integer.MIN_VALUE), heightMeasureSpec);
        }
    }
}
