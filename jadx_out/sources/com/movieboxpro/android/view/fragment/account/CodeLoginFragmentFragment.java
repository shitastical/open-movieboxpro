package com.movieboxpro.android.view.fragment.account;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.movieboxpro.android.base.mvp.BaseMvpFragment;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.livedata.AddAccountLiveData;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.tool.ActivityUtils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentCodeLoginBinding;
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

/* JADX INFO: compiled from: CodeLoginFragmentFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 $2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0001$B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000e\u001a\u00020\rH\u0014J\b\u0010\u000f\u001a\u00020\rH\u0014J\b\u0010\u0010\u001a\u00020\rH\u0014J\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\rH\u0002J\u0006\u0010\u0013\u001a\u00020\rJ\u0006\u0010\u0014\u001a\u00020\rJ\b\u0010\u0015\u001a\u00020\rH\u0016J\b\u0010\u0016\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J\b\u0010\u0018\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0002H\u0014J\u0010\u0010\u001c\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u001d\u001a\u00020\rH\u0016J\b\u0010\u001e\u001a\u00020\rH\u0016J\u0012\u0010\u001f\u001a\u00020\r2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\b\u0010\"\u001a\u00020\rH\u0016J\b\u0010#\u001a\u00020\rH\u0016R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentFragment;", "Lcom/movieboxpro/android/base/mvp/BaseMvpFragment;", "Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentPresenter;", "Lcom/movieboxpro/androidtv/databinding/FragmentCodeLoginBinding;", "Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentContract$View;", "<init>", "()V", "dispose", "Lio/reactivex/disposables/Disposable;", "code", "", "id", "initView", "", "initData", "initListener", "loadData", "showLoadingView", "startTimer", "onStartTimer", "onStopTimer", "onDestroy", "hideLoadingView", "onFragmentResume", "onFragmentPause", "bindLayout", "", "bindPresenter", "showCode", "showTryView", "stopPolling", "loginSuccess", "model", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "addAccountFailed", "onDuplicateAccountLogin", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CodeLoginFragmentFragment extends BaseMvpFragment<CodeLoginFragmentPresenter, FragmentCodeLoginBinding> implements CodeLoginFragmentContract.View {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String code;
    private Disposable dispose;
    private String id;

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initView() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void loadData() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initData() {
        String uniqueId = SystemUtils.getUniqueId(getContext());
        if (uniqueId == null) {
            uniqueId = "";
        }
        this.id = uniqueId;
        ((CodeLoginFragmentPresenter) this.mPresenter).getCode();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initListener() {
        ((FragmentCodeLoginBinding) this.binding).tvTryAgain.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CodeLoginFragmentFragment.initListener$lambda$0(this.f$0, view);
            }
        });
        ((FragmentCodeLoginBinding) this.binding).tvTryAgain.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return CodeLoginFragmentFragment.initListener$lambda$1(view, i, keyEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(CodeLoginFragmentFragment codeLoginFragmentFragment, View view) {
        TextView tvTryAgain = ((FragmentCodeLoginBinding) codeLoginFragmentFragment.binding).tvTryAgain;
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.gone(tvTryAgain);
        ((CodeLoginFragmentPresenter) codeLoginFragmentFragment.mPresenter).getCode();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$1(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 || i != 19) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoadingView() {
        ProgressBar progressBar = ((FragmentCodeLoginBinding) this.binding).progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.visible(progressBar);
    }

    private final void startTimer() {
        ObservableSubscribeProxy observableSubscribeProxy = (ObservableSubscribeProxy) Observable.interval(0L, 3L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CodeLoginFragmentFragment.startTimer$lambda$2(this.f$0, (Long) obj);
            }
        };
        this.dispose = observableSubscribeProxy.subscribe(new Consumer() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentFragment$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                function1.invoke(obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTimer$lambda$2(CodeLoginFragmentFragment codeLoginFragmentFragment, Long l) {
        CodeLoginFragmentPresenter codeLoginFragmentPresenter = (CodeLoginFragmentPresenter) codeLoginFragmentFragment.mPresenter;
        String str = codeLoginFragmentFragment.code;
        if (str == null) {
            str = "";
        }
        String str2 = codeLoginFragmentFragment.id;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("id");
            str2 = null;
        }
        Bundle arguments = codeLoginFragmentFragment.getArguments();
        codeLoginFragmentPresenter.checkCode(str, str2, arguments != null ? arguments.getBoolean("addAccount", false) : false);
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

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        Disposable disposable = this.dispose;
        if (disposable != null) {
            disposable.dispose();
        }
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoadingView() {
        ProgressBar progressBar = ((FragmentCodeLoginBinding) this.binding).progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResume() {
        super.onFragmentResume();
        onStartTimer();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentPause() {
        super.onFragmentPause();
        onStopTimer();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected int bindLayout() {
        return R.layout.fragment_code_login;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    public CodeLoginFragmentPresenter bindPresenter() {
        return new CodeLoginFragmentPresenter(this);
    }

    /* JADX INFO: compiled from: CodeLoginFragmentFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentFragment;", "addAccount", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ CodeLoginFragmentFragment newInstance$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.newInstance(z);
        }

        public final CodeLoginFragmentFragment newInstance(boolean addAccount) {
            CodeLoginFragmentFragment codeLoginFragmentFragment = new CodeLoginFragmentFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("addAccount", addAccount);
            codeLoginFragmentFragment.setArguments(bundle);
            return codeLoginFragmentFragment;
        }
    }

    @Override // com.movieboxpro.android.view.fragment.account.CodeLoginFragmentContract.View
    public void showCode(String code) {
        Intrinsics.checkNotNullParameter(code, "code");
        this.code = code;
        ((FragmentCodeLoginBinding) this.binding).tvCode.setText(code);
        startTimer();
    }

    @Override // com.movieboxpro.android.view.fragment.account.CodeLoginFragmentContract.View
    public void showTryView() {
        TextView tvTryAgain = ((FragmentCodeLoginBinding) this.binding).tvTryAgain;
        Intrinsics.checkNotNullExpressionValue(tvTryAgain, "tvTryAgain");
        CommonExtKt.visible(tvTryAgain);
    }

    @Override // com.movieboxpro.android.view.fragment.account.CodeLoginFragmentContract.View
    public void stopPolling() {
        Disposable disposable = this.dispose;
        if (disposable != null) {
            disposable.dispose();
        }
        this.dispose = null;
    }

    @Override // com.movieboxpro.android.view.fragment.account.CodeLoginFragmentContract.View
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

    @Override // com.movieboxpro.android.view.fragment.account.CodeLoginFragmentContract.View
    public void addAccountFailed() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }

    @Override // com.movieboxpro.android.view.fragment.account.CodeLoginFragmentContract.View
    public void onDuplicateAccountLogin() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.finish();
        }
    }
}
