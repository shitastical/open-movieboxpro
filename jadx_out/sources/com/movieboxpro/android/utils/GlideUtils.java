package com.movieboxpro.android.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.GlideApp;
import com.movieboxpro.androidtv.R;
import java.io.File;
import java.math.BigDecimal;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/* JADX INFO: loaded from: classes5.dex */
public class GlideUtils {
    public static void loadWithBlur(final Activity context, final String url, final ImageView target) {
        if (context == null || target == null) {
            return;
        }
        target.post(new Runnable() { // from class: com.movieboxpro.android.utils.GlideUtils.1
            @Override // java.lang.Runnable
            public void run() {
                if (GlideUtils.isDestroy(context)) {
                    return;
                }
                if (target.getWidth() != 0 && target.getHeight() != 0) {
                    GlideApp.with(context).load(url).skipMemoryCache(true).priority(Priority.LOW).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new MultiTransformation(new BlurTransformation(30, 3)))).into(new SimpleTarget<Drawable>(target.getWidth() / 2, target.getHeight() / 2) { // from class: com.movieboxpro.android.utils.GlideUtils.1.1
                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(Object resource, Transition transition) {
                            onResourceReady((Drawable) resource, (Transition<? super Drawable>) transition);
                        }

                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            target.setImageDrawable(resource);
                        }
                    });
                } else {
                    GlideApp.with(context).load(url).skipMemoryCache(true).priority(Priority.LOW).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new MultiTransformation(new BlurTransformation(30, 3)))).into(new SimpleTarget<Drawable>() { // from class: com.movieboxpro.android.utils.GlideUtils.1.2
                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(Object resource, Transition transition) {
                            onResourceReady((Drawable) resource, (Transition<? super Drawable>) transition);
                        }

                        public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                            target.setImageDrawable(resource);
                        }
                    });
                }
            }
        });
    }

    public static boolean isDestroy(Activity mActivity) {
        return mActivity == null || mActivity.isFinishing() || mActivity.isDestroyed();
    }

    public static void loadLandHolder(Context context, String url, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && isDestroy((Activity) context)) {
            return;
        }
        if (TextUtils.isEmpty(url)) {
            GlideApp.with(context).load(Integer.valueOf(R.drawable.ic_empty_land_holder)).into(imageView);
        } else {
            GlideApp.with(context).load(url).format(DecodeFormat.PREFER_RGB_565).placeholder(R.drawable.ic_land_loading_holder).error(R.drawable.ic_land_img_load_failed).into(imageView);
        }
    }

    public static void loadLandSkipMemCache(Context context, String url, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        if (TextUtils.isEmpty(url)) {
            GlideApp.with(context).load(Integer.valueOf(R.drawable.ic_empty_land_holder)).into(imageView);
        } else {
            GlideApp.with(context).load(url).format(DecodeFormat.PREFER_RGB_565).placeholder(R.drawable.ic_land_loading_holder).error(R.drawable.ic_land_img_load_failed).into(imageView);
        }
    }

    public static void loadPortraitHolder(Context context, String url, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && isDestroy((Activity) context)) {
            return;
        }
        if (TextUtils.isEmpty(url)) {
            GlideApp.with(context).load(Integer.valueOf(R.drawable.ic_empty_portrait_holder)).into(imageView);
        } else {
            GlideApp.with(context).load(url).format(DecodeFormat.PREFER_RGB_565).placeholder(R.drawable.ic_portrait_loading_holder).error(R.drawable.ic_portrait_img_load_failed).into(imageView);
        }
    }

    public static void loadPortraitSkipMemCache(Context context, String url, ImageView imageView) {
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            return;
        }
        if (TextUtils.isEmpty(url)) {
            GlideApp.with(context).load(Integer.valueOf(R.drawable.ic_empty_portrait_holder)).into(imageView);
        } else {
            GlideApp.with(context).load(url).format(DecodeFormat.PREFER_RGB_565).placeholder(R.drawable.ic_portrait_loading_holder).skipMemoryCache(true).error(R.drawable.ic_portrait_img_load_failed).into(imageView);
        }
    }

    private static RequestBuilder<Drawable> loadTransform(Context context, int placeholderId, float radius) {
        return Glide.with(context).load(Integer.valueOf(placeholderId)).apply((BaseRequestOptions<?>) new RequestOptions().centerCrop().transform(new RoundedCornersTransformation((int) radius, 0)));
    }

    public static void loadCornerPortraitHolder(Context context, String url, ImageView imageView, int radius) {
        float f = radius;
        MultiTransformation multiTransformation = new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(DensityUtils.dp2px(App.getContext(), f), 0));
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && isDestroy((Activity) context)) {
            return;
        }
        if (TextUtils.isEmpty(url)) {
            GlideApp.with(context).load(Integer.valueOf(R.drawable.ic_empty_portrait_holder)).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(multiTransformation)).into(imageView);
        } else {
            GlideApp.with(context).load(url).format(DecodeFormat.PREFER_RGB_565).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(multiTransformation)).thumbnail(loadTransform(context, R.drawable.ic_portrait_loading_holder, DensityUtils.dp2px(App.getContext(), f))).error(R.drawable.ic_portrait_img_load_failed).into(imageView);
        }
    }

    public static void loadCornerLandHolder(Context context, String url, ImageView imageView, int radius) {
        float f = radius;
        MultiTransformation multiTransformation = new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(DensityUtils.dp2px(App.getContext(), f), 0));
        if (context == null || imageView == null) {
            return;
        }
        if ((context instanceof Activity) && isDestroy((Activity) context)) {
            return;
        }
        if (TextUtils.isEmpty(url)) {
            GlideApp.with(context).load(Integer.valueOf(R.drawable.ic_empty_land_holder)).into(imageView);
        } else {
            GlideApp.with(context).load(url).format(DecodeFormat.PREFER_RGB_565).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(multiTransformation)).thumbnail(loadTransform(context, R.drawable.ic_land_loading_holder, DensityUtils.dp2px(App.getContext(), f))).error(R.drawable.ic_portrait_img_load_failed).into(imageView);
        }
    }

    public static void load(Activity activity, String url, ImageView target) {
        if (TextUtils.isEmpty(url) || activity == null || activity.isFinishing() || target == null) {
            return;
        }
        GlideApp.with(activity).load(url).into(target);
    }

    public static void load(Activity activity, int resId, ImageView target) {
        if (activity == null || activity.isFinishing() || target == null) {
            return;
        }
        GlideApp.with(activity).load(Integer.valueOf(resId)).into(target);
    }

    public static void load(Activity activity, Object resId, ImageView target) {
        if (activity == null || activity.isFinishing() || target == null) {
            return;
        }
        GlideApp.with(activity).load(resId).into(target);
    }

    public static void load(Activity activity, Bitmap resId, ImageView target) {
        if (activity == null || activity.isFinishing() || target == null) {
            return;
        }
        GlideApp.with(activity).load(resId).into(target);
    }

    public static void load(Activity activity, String url, ImageView target, int placeHolder) {
        if (TextUtils.isEmpty(url) || activity == null || activity.isFinishing() || target == null) {
            return;
        }
        GlideApp.with(activity).load(url).placeholder(placeHolder).into(target);
    }

    public static void load(Context context, String url, ImageView target, int placeHolder) {
        if (context == null || target == null) {
            return;
        }
        GlideApp.with(context).load(url).placeholder(placeHolder).into(target);
    }

    public static void load(Context context, int resId, ImageView target) {
        if (context == null || target == null) {
            return;
        }
        GlideApp.with(context).load(Integer.valueOf(resId)).into(target);
    }

    public static void load(Context context, String url, ImageView target) {
        GlideApp.with(context).load(url).into(target);
    }

    public static void loads(Context context, String url, ImageView target) {
        if (TextUtils.isEmpty(url) || context == null || target == null) {
            return;
        }
        GlideApp.with(context).load(url).transform((Transformation<Bitmap>) new CropTransformation(target.getWidth(), target.getHeight(), CropTransformation.CropType.BOTTOM)).into(target);
    }

    public static void load(Activity activity, String url, ImageView target, int radius, int margin) {
        if (TextUtils.isEmpty(url) || activity == null || activity.isFinishing() || target == null) {
            return;
        }
        GlideApp.with(activity).load(url).transform((Transformation<Bitmap>) new RoundedCornersTransformation(radius, margin)).into(target);
    }

    public static void load(Activity activity, String url, ImageView target, int errRes, int radius, int margin) {
        if (TextUtils.isEmpty(url) || activity == null || activity.isFinishing() || target == null) {
            return;
        }
        GlideApp.with(activity).load(url).error(errRes).transform((Transformation<Bitmap>) new RoundedCornersTransformation(radius, margin)).into(target);
    }

    public static void load(Context context, String url, ImageView target, int errRes, int radius, int margin) {
        if (TextUtils.isEmpty(url) || context == null || target == null) {
            return;
        }
        GlideApp.with(context).load(url).error(errRes).transform((Transformation<Bitmap>) new RoundedCornersTransformation(radius, margin)).into(target);
    }

    public static void loadWithCorner(final Context context, String url, final ImageView imageView, int radius) {
        GlideApp.with(context).load(url).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(radius, 0)))).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void loadWithCorner(final Context context, Integer url, final ImageView imageView, int radius) {
        GlideApp.with(context).load(url).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(radius, 0)))).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
    }

    public static void loadWithCorner(final Context context, String url, final ImageView imageView, int radius, int placeHolder) {
        GlideApp.with(context).load(url).apply((BaseRequestOptions<?>) RequestOptions.bitmapTransform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(radius, 0)))).diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(placeHolder).into(imageView);
    }

    public static void clearImageDiskCache(final Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                new Thread(new Runnable() { // from class: com.movieboxpro.android.utils.GlideUtils.2
                    @Override // java.lang.Runnable
                    public void run() {
                        Glide.get(context).clearDiskCache();
                    }
                }).start();
            } else {
                Glide.get(context).clearDiskCache();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearImageMemoryCache(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                Glide.get(context).clearMemory();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clearImageAllCache(Context context) {
        clearImageDiskCache(context);
        clearImageMemoryCache(context);
        deleteFolderFile(context.getExternalCacheDir() + DiskCache.Factory.DEFAULT_DISK_CACHE_DIR, true);
    }

    public static String getCacheSize(Context context) {
        try {
            return getFormatSize(getFolderSize(new File(context.getCacheDir() + "/image_manager_disk_cache")));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static long getFolderSize(File file) throws Exception {
        long length;
        long j = 0;
        try {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    length = getFolderSize(file2);
                } else {
                    length = file2.length();
                }
                j += length;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (TextUtils.isEmpty(filePath)) {
            return;
        }
        try {
            File file = new File(filePath);
            if (file.isDirectory()) {
                for (File file2 : file.listFiles()) {
                    deleteFolderFile(file2.getAbsolutePath(), true);
                }
            }
            if (deleteThisPath) {
                if (!file.isDirectory()) {
                    file.delete();
                } else if (file.listFiles().length == 0) {
                    file.delete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFormatSize(double size) {
        double d = size / 1024.0d;
        if (d < 1.0d) {
            return size + "Byte";
        }
        double d2 = d / 1024.0d;
        if (d2 < 1.0d) {
            return new BigDecimal(Double.toString(d)).setScale(2, 4).toPlainString() + "KB";
        }
        double d3 = d2 / 1024.0d;
        if (d3 < 1.0d) {
            return new BigDecimal(Double.toString(d2)).setScale(2, 4).toPlainString() + "MB";
        }
        double d4 = d3 / 1024.0d;
        if (d4 < 1.0d) {
            return new BigDecimal(Double.toString(d3)).setScale(2, 4).toPlainString() + "GB";
        }
        return new BigDecimal(d4).setScale(2, 4).toPlainString() + "TB";
    }

    public static void loadWithCircle(Context context, String resId, ImageView target, int placeholder) {
        if (context == null || target == null) {
            return;
        }
        Glide.with(context).load(resId).placeholder(placeholder).transition(DrawableTransitionOptions.withCrossFade()).skipMemoryCache(true).priority(Priority.LOW).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(target);
    }
}
