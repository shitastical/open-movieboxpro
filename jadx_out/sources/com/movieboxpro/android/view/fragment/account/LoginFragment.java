package com.movieboxpro.android.view.fragment.account;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.movieboxpro.android.base.mvp.BaseMvpFragment;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.livedata.AddAccountLiveData;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.tool.ActivityUtils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentLoginBinding;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: LoginFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 #2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0001#B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u000b\u001a\u00020\fH\u0014J\b\u0010\r\u001a\u00020\fH\u0014J\b\u0010\u000e\u001a\u00020\fH\u0014J\b\u0010\u000f\u001a\u00020\fH\u0014J\b\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016J\u0012\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\b\u0010\u0015\u001a\u00020\fH\u0016J\b\u0010\u0016\u001a\u00020\fH\u0002J\u0006\u0010\u0017\u001a\u00020\fJ\u0006\u0010\u0018\u001a\u00020\fJ\b\u0010\u0019\u001a\u00020\fH\u0016J\u0012\u0010\u001a\u001a\u00020\f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\b\u0010\u001d\u001a\u00020\fH\u0016J\b\u0010\u001e\u001a\u00020\fH\u0016J\b\u0010\u001f\u001a\u00020\fH\u0016J\b\u0010 \u001a\u00020\u0002H\u0014J\b\u0010!\u001a\u00020\"H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/LoginFragment;", "Lcom/movieboxpro/android/base/mvp/BaseMvpFragment;", "Lcom/movieboxpro/android/view/fragment/account/LoginPresenter;", "Lcom/movieboxpro/androidtv/databinding/FragmentLoginBinding;", "Lcom/movieboxpro/android/view/fragment/account/LoginContract$View;", "<init>", "()V", "id", "", "dispose", "Lio/reactivex/disposables/Disposable;", "initListener", "", "initData", "initView", "loadData", "showLoadingView", "hideLoadingView", "addIdSuccess", "bitmap", "Landroid/graphics/Bitmap;", "addIdFailed", "startTimer", "onStartTimer", "onStopTimer", "stopPolling", "loginSuccess", "model", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "addAccountFailed", "onDuplicateAccountLogin", "onDestroy", "bindPresenter", "bindLayout", "", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoginFragment extends BaseMvpFragment<LoginPresenter, FragmentLoginBinding> implements LoginContract.View {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Disposable dispose;
    private String id;

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initListener() {
        ((FragmentLoginBinding) this.binding).tvTryAgain.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.LoginFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginFragment.initListener$lambda$0(this.f$0, view);
            }
        });
        ((FragmentLoginBinding) this.binding).tvTryAgain.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.account.LoginFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return LoginFragment.initListener$lambda$1(view, i, keyEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(LoginFragment loginFragment, View view) {
        TextView tvTryAgain = ((FragmentLoginBinding) loginFragment.binding).tvTryAgain;
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.gone(tvTryAgain);
        LoginPresenter loginPresenter = (LoginPresenter) loginFragment.mPresenter;
        String str = loginFragment.id;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("id");
            str = null;
        }
        loginPresenter.addId(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$1(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 || i != 19) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initData() {
        String uniqueId = SystemUtils.getUniqueId(getContext());
        Intrinsics.checkNotNullExpressionValue(uniqueId, "getUniqueId(...)");
        this.id = uniqueId;
        ((FragmentLoginBinding) this.binding).tvVersionName.setText("v13.4");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initView() {
        GlideUtils.load(getContext(), R.drawable.login_hint_bg, ((FragmentLoginBinding) this.binding).imageView);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void loadData() {
        LoginPresenter loginPresenter = (LoginPresenter) this.mPresenter;
        String str = this.id;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("id");
            str = null;
        }
        loginPresenter.addId(str);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoadingView() {
        ProgressBar progressBar = ((FragmentLoginBinding) this.binding).progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.visible(progressBar);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoadingView() {
        ProgressBar progressBar = ((FragmentLoginBinding) this.binding).progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
    }

    @Override // com.movieboxpro.android.view.fragment.account.LoginContract.View
    public void addIdSuccess(Bitmap bitmap) {
        if (bitmap != null) {
            GlideUtils.load((Activity) getActivity(), bitmap, (ImageView) ((FragmentLoginBinding) this.binding).ivQrCode);
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        startTimer();
    }

    @Override // com.movieboxpro.android.view.fragment.account.LoginContract.View
    public void addIdFailed() {
        TextView tvTryAgain = ((FragmentLoginBinding) this.binding).tvTryAgain;
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.visible(tvTryAgain);
    }

    private final void startTimer() {
        ObservableSubscribeProxy observableSubscribeProxy = (ObservableSubscribeProxy) Observable.interval(0L, 5L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.account.LoginFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LoginFragment.startTimer$lambda$3(this.f$0, (Long) obj);
            }
        };
        this.dispose = observableSubscribeProxy.subscribe(new Consumer() { // from class: com.movieboxpro.android.view.fragment.account.LoginFragment$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                function1.invoke(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTimer$lambda$3(LoginFragment loginFragment, Long l) {
        Log.d("LoginFragment", "查看是否登录");
        LoginPresenter loginPresenter = (LoginPresenter) loginFragment.mPresenter;
        String str = loginFragment.id;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("id");
            str = null;
        }
        Bundle arguments = loginFragment.getArguments();
        loginPresenter.checkScanResult(str, arguments != null ? arguments.getBoolean("addAccount", false) : false);
        return Unit.INSTANCE;
    }

    public final void onStartTimer() {
        if (this.dispose != null) {
            startTimer();
        }
    }

    public final void onStopTimer() {
        Disposable disposable = this.dispose;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override // com.movieboxpro.android.view.fragment.account.LoginContract.View
    public void stopPolling() {
        Disposable disposable = this.dispose;
        if (disposable != null) {
            disposable.dispose();
        }
        this.dispose = null;
    }

    @Override // com.movieboxpro.android.view.fragment.account.LoginContract.View
    public void loginSuccess(UserModel.UserData model) {
        Bundle arguments = getArguments();
        if (arguments != null ? arguments.getBoolean("addAccount", false) : false) {
            ToastUtils.showShort("Login successfully", new Object[0]);
            AddAccountLiveData.INSTANCE.get().setValue(true);
            return;
        }
        ToastUtils.showShort("Login successfully", new Object[0]);
        List<Activity> activityList = ActivityUtils.getActivityList();
        if (activityList.size() == 1) {
            Activity activity = activityList.get(0);
            if (Intrinsics.areEqual(activity, MainActivity.class)) {
                return;
            }
            MainActivity.Companion companion = MainActivity.INSTANCE;
            Intrinsics.checkNotNull(activity);
            companion.start(activity);
        }
    }

    @Override // com.movieboxpro.android.view.fragment.account.LoginContract.View
    public void addAccountFailed() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override // com.movieboxpro.android.view.fragment.account.LoginContract.View
    public void onDuplicateAccountLogin() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        Disposable disposable = this.dispose;
        if (disposable != null) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    /* JADX INFO: compiled from: LoginFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/LoginFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/account/LoginFragment;", "addAccount", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ LoginFragment newInstance$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.newInstance(z);
        }

        public final LoginFragment newInstance(boolean addAccount) {
            LoginFragment loginFragment = new LoginFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("addAccount", addAccount);
            loginFragment.setArguments(bundle);
            return loginFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    public LoginPresenter bindPresenter() {
        return new LoginPresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected int bindLayout() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            arguments.getBoolean("childMode", false);
        }
        return R.layout.fragment_login;
    }
}
