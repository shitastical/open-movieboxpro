package com.movieboxpro.android.view.fragment.account;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.v2.ApiEncryptV2;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.QRCodeUtil;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentVipChargeGuideBinding;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: VipChargeGuideDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J&\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u001a\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00112\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\u0018\u001a\u00020\tH\u0002J\b\u0010\u0019\u001a\u00020\tH\u0002J\b\u0010\u001a\u001a\u00020\tH\u0002J\b\u0010\u001b\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/VipChargeGuideDialog;", "Landroidx/appcompat/app/AppCompatDialogFragment;", "<init>", "()V", "payUrl", "", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentVipChargeGuideBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "getContextRect", "", "activity", "Landroid/app/Activity;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "requestUrl", "hideLoadingView", "showLoadingView", "buildData", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VipChargeGuideDialog extends AppCompatDialogFragment {
    private FragmentVipChargeGuideBinding binding;
    private String payUrl = "https://www.xuedingcongming.com/index/pay";

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.BottomSheetFullScreenDialog);
    }

    private final int getContextRect(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.height();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Dialog dialog = getDialog();
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBinding = null;
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            window.requestFeature(1);
            window.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams attributes = window.getAttributes();
            Context context = getContext();
            Intrinsics.checkNotNull(context);
            window.setBackgroundDrawable(ContextCompat.getDrawable(context, android.R.color.transparent));
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity);
            int contextRect = getContextRect(activity);
            if (contextRect == 0) {
                contextRect = -1;
            }
            window.setLayout(-1, contextRect);
            attributes.dimAmount = 0.0f;
            attributes.gravity = 80;
            window.setAttributes(attributes);
        }
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBindingInflate = FragmentVipChargeGuideBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentVipChargeGuideBindingInflate, "inflate(...)");
        this.binding = fragmentVipChargeGuideBindingInflate;
        if (fragmentVipChargeGuideBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentVipChargeGuideBinding = fragmentVipChargeGuideBindingInflate;
        }
        return fragmentVipChargeGuideBinding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        String stringConfig = ConfigUtils.readStringConfig(ConfigKey.PAY_URL);
        String str = stringConfig;
        if (str != null && !StringsKt.isBlank(str)) {
            this.payUrl = stringConfig;
        }
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBinding = this.binding;
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBinding2 = null;
        if (fragmentVipChargeGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVipChargeGuideBinding = null;
        }
        fragmentVipChargeGuideBinding.textView11.getPaint().setFlags(17);
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBinding3 = this.binding;
        if (fragmentVipChargeGuideBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVipChargeGuideBinding3 = null;
        }
        fragmentVipChargeGuideBinding3.textView11.getPaint().setAntiAlias(true);
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBinding4 = this.binding;
        if (fragmentVipChargeGuideBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVipChargeGuideBinding4 = null;
        }
        fragmentVipChargeGuideBinding4.textView14.getPaint().setFlags(17);
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBinding5 = this.binding;
        if (fragmentVipChargeGuideBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVipChargeGuideBinding5 = null;
        }
        fragmentVipChargeGuideBinding5.textView14.getPaint().setAntiAlias(true);
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBinding6 = this.binding;
        if (fragmentVipChargeGuideBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentVipChargeGuideBinding2 = fragmentVipChargeGuideBinding6;
        }
        fragmentVipChargeGuideBinding2.tvTryAgain.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.requestUrl();
            }
        });
        requestUrl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestUrl() {
        RxSubscribersKt.subscribeTo$default(HttpRequest.INSTANCE.post(this, "Pay_url").param("uid", App.getUserData().uid_v2).asBean(HashMap.class), new Function1() { // from class: com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VipChargeGuideDialog.requestUrl$lambda$1(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VipChargeGuideDialog.requestUrl$lambda$2(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VipChargeGuideDialog.requestUrl$lambda$7(this.f$0, (HashMap) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestUrl$lambda$2(VipChargeGuideDialog vipChargeGuideDialog, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        vipChargeGuideDialog.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestUrl$lambda$7(final VipChargeGuideDialog vipChargeGuideDialog, HashMap it) {
        Intrinsics.checkNotNullParameter(it, "it");
        vipChargeGuideDialog.hideLoadingView();
        Object obj = it.get(ConfigKey.PAY_URL);
        String str = obj instanceof String ? (String) obj : null;
        String str2 = str;
        if (str2 != null && !StringsKt.isBlank(str2)) {
            vipChargeGuideDialog.payUrl = str;
        }
        Observable observableJust = Observable.just(vipChargeGuideDialog.payUrl + "&auth=" + vipChargeGuideDialog.buildData());
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return VipChargeGuideDialog.requestUrl$lambda$7$lambda$3((String) obj2);
            }
        };
        ObservableSubscribeProxy observableSubscribeProxy = (ObservableSubscribeProxy) observableJust.map(new Function() { // from class: com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj2) {
                return VipChargeGuideDialog.requestUrl$lambda$7$lambda$4(function1, obj2);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(vipChargeGuideDialog));
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj2) {
                return VipChargeGuideDialog.requestUrl$lambda$7$lambda$5(this.f$0, (Bitmap) obj2);
            }
        };
        observableSubscribeProxy.subscribe(new Consumer() { // from class: com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj2) {
                function2.invoke(obj2);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bitmap requestUrl$lambda$7$lambda$4(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Bitmap) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bitmap requestUrl$lambda$7$lambda$3(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return QRCodeUtil.createQRCodeBitmap(it, DensityUtils.dp2px(500.0f), DensityUtils.dp2px(500.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestUrl$lambda$7$lambda$5(VipChargeGuideDialog vipChargeGuideDialog, Bitmap bitmap) {
        FragmentActivity activity = vipChargeGuideDialog.getActivity();
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBinding = vipChargeGuideDialog.binding;
        if (fragmentVipChargeGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVipChargeGuideBinding = null;
        }
        GlideUtils.load((Activity) activity, bitmap, fragmentVipChargeGuideBinding.ivQrCode);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestUrl$lambda$1(VipChargeGuideDialog vipChargeGuideDialog, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        vipChargeGuideDialog.hideLoadingView();
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBinding = vipChargeGuideDialog.binding;
        if (fragmentVipChargeGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVipChargeGuideBinding = null;
        }
        TextView tvTryAgain = fragmentVipChargeGuideBinding.tvTryAgain;
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.visible(tvTryAgain);
        ToastUtils.showShort("Load failed：" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void hideLoadingView() {
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBinding = this.binding;
        if (fragmentVipChargeGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVipChargeGuideBinding = null;
        }
        ProgressBar progressBar = fragmentVipChargeGuideBinding.progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
    }

    private final void showLoadingView() {
        FragmentVipChargeGuideBinding fragmentVipChargeGuideBinding = this.binding;
        if (fragmentVipChargeGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentVipChargeGuideBinding = null;
        }
        ProgressBar progressBar = fragmentVipChargeGuideBinding.progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.visible(progressBar);
    }

    private final String buildData() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = jSONObject;
        jSONObject2.put("uid", App.isLogin() ? App.getUserData().uid_v2 : "");
        jSONObject2.put(ApiEncryptV2.EXPIRED_DATE, Long.valueOf((TimeUtils.getCurrentTime() / ((long) 1000)) + ((long) 43200)));
        String strEncodeBody = HttpUtils.encodeBody(jSONObject.toJSONString());
        Intrinsics.checkNotNullExpressionValue(strEncodeBody, "encodeBody(...)");
        return strEncodeBody;
    }
}
