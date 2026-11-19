package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.androidtv.databinding.UserAvatarViewBinding;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: UserAvatarView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\rJ\u001a\u0010\u0014\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\rR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/widget/UserAvatarView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "binding", "Lcom/movieboxpro/androidtv/databinding/UserAvatarViewBinding;", "getNameFirstLetter", "", "name", "clearView", "", "setAvatarNoCache", "avatar", "userName", "setAvatar", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UserAvatarView extends FrameLayout {
    private final UserAvatarViewBinding binding;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UserAvatarView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UserAvatarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ UserAvatarView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UserAvatarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        UserAvatarViewBinding userAvatarViewBindingInflate = UserAvatarViewBinding.inflate(LayoutInflater.from(context), this, true);
        Intrinsics.checkNotNullExpressionValue(userAvatarViewBindingInflate, "inflate(...)");
        this.binding = userAvatarViewBindingInflate;
    }

    private final String getNameFirstLetter(String name) {
        String str = name;
        String[] strArr = (String[]) new Regex("\\s+").split(str, 0).toArray(new String[0]);
        StringBuilder sb = new StringBuilder();
        for (String str2 : strArr) {
            if (str2.length() > 0) {
                sb.append(str2.charAt(0));
            }
        }
        if (sb.length() <= 1) {
            List listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"_"}, false, 0, 6, (Object) null);
            if (listSplit$default.size() >= 2) {
                Object objFirstOrNull = StringsKt.firstOrNull((CharSequence) listSplit$default.get(0));
                if (objFirstOrNull == null) {
                    objFirstOrNull = "";
                }
                Character chFirstOrNull = StringsKt.firstOrNull((CharSequence) listSplit$default.get(1));
                Object obj = chFirstOrNull != null ? chFirstOrNull : "";
                StringBuilder sb2 = new StringBuilder();
                sb2.append(objFirstOrNull);
                sb2.append(obj);
                String string = sb2.toString();
                Locale locale = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
                String upperCase = string.toUpperCase(locale);
                Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
                if (upperCase.length() >= 2) {
                    Locale locale2 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
                    String upperCase2 = upperCase.toUpperCase(locale2);
                    Intrinsics.checkNotNullExpressionValue(upperCase2, "toUpperCase(...)");
                    return upperCase2;
                }
                if (name.length() >= 2) {
                    char cCharAt = name.charAt(0);
                    char cCharAt2 = name.charAt(1);
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(cCharAt);
                    sb3.append(cCharAt2);
                    String string2 = sb3.toString();
                    Locale locale3 = Locale.getDefault();
                    Intrinsics.checkNotNullExpressionValue(locale3, "getDefault(...)");
                    String upperCase3 = string2.toUpperCase(locale3);
                    Intrinsics.checkNotNullExpressionValue(upperCase3, "toUpperCase(...)");
                    return upperCase3;
                }
                Locale locale4 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale4, "getDefault(...)");
                String upperCase4 = name.toUpperCase(locale4);
                Intrinsics.checkNotNullExpressionValue(upperCase4, "toUpperCase(...)");
                return upperCase4;
            }
            if (name.length() >= 2) {
                char cCharAt3 = name.charAt(0);
                char cCharAt4 = name.charAt(1);
                StringBuilder sb4 = new StringBuilder();
                sb4.append(cCharAt3);
                sb4.append(cCharAt4);
                String string3 = sb4.toString();
                Locale locale5 = Locale.getDefault();
                Intrinsics.checkNotNullExpressionValue(locale5, "getDefault(...)");
                String upperCase5 = string3.toUpperCase(locale5);
                Intrinsics.checkNotNullExpressionValue(upperCase5, "toUpperCase(...)");
                return upperCase5;
            }
            Locale locale6 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale6, "getDefault(...)");
            String upperCase6 = name.toUpperCase(locale6);
            Intrinsics.checkNotNullExpressionValue(upperCase6, "toUpperCase(...)");
            return upperCase6;
        }
        String string4 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string4, "toString(...)");
        Locale locale7 = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale7, "getDefault(...)");
        String upperCase7 = string4.toUpperCase(locale7);
        Intrinsics.checkNotNullExpressionValue(upperCase7, "toUpperCase(...)");
        return upperCase7;
    }

    public final void clearView() {
        this.binding.tvNameAvatar.setText("");
        this.binding.ivAvatars.setImageDrawable(null);
    }

    public final void setAvatarNoCache(String avatar, String userName) {
        TextView textView = this.binding.tvNameAvatar;
        if (userName == null) {
            userName = "";
        }
        textView.setText(getNameFirstLetter(userName));
        Glide.with(this).load(avatar + "?t=" + System.currentTimeMillis()).onlyRetrieveFromCache(false).diskCacheStrategy(DiskCacheStrategy.NONE).listener(new RequestListener<Drawable>() { // from class: com.movieboxpro.android.view.widget.UserAvatarView.setAvatarNoCache.1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                TextView tvNameAvatar = UserAvatarView.this.binding.tvNameAvatar;
                Intrinsics.checkNotNullExpressionValue(tvNameAvatar, "tvNameAvatar");
                CommonExtKt.visible(tvNameAvatar);
                return true;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                TextView tvNameAvatar = UserAvatarView.this.binding.tvNameAvatar;
                Intrinsics.checkNotNullExpressionValue(tvNameAvatar, "tvNameAvatar");
                CommonExtKt.gone(tvNameAvatar);
                UserAvatarView.this.binding.ivAvatars.setImageDrawable(resource);
                return true;
            }
        }).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(this.binding.ivAvatars);
    }

    public final void setAvatar(String avatar, String userName) {
        TextView textView = this.binding.tvNameAvatar;
        if (userName == null) {
            userName = "";
        }
        textView.setText(getNameFirstLetter(userName));
        Glide.with(this).load(avatar).diskCacheStrategy(DiskCacheStrategy.NONE).listener(new RequestListener<Drawable>() { // from class: com.movieboxpro.android.view.widget.UserAvatarView.setAvatar.1
            @Override // com.bumptech.glide.request.RequestListener
            public boolean onLoadFailed(GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                TextView tvNameAvatar = UserAvatarView.this.binding.tvNameAvatar;
                Intrinsics.checkNotNullExpressionValue(tvNameAvatar, "tvNameAvatar");
                CommonExtKt.visible(tvNameAvatar);
                return true;
            }

            @Override // com.bumptech.glide.request.RequestListener
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                TextView tvNameAvatar = UserAvatarView.this.binding.tvNameAvatar;
                Intrinsics.checkNotNullExpressionValue(tvNameAvatar, "tvNameAvatar");
                CommonExtKt.gone(tvNameAvatar);
                UserAvatarView.this.binding.ivAvatars.setImageDrawable(resource);
                return true;
            }
        }).apply((BaseRequestOptions<?>) RequestOptions.circleCropTransform()).into(this.binding.ivAvatars);
    }
}
