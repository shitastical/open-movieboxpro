package com.movieboxpro.android.base.mvp;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.databinding.DataBindingUtil;
import androidx.viewbinding.ViewBinding;
import butterknife.ButterKnife;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BaseContract.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.tv.LoginActivity;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.VipExpireDialog;
import com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog;
import com.movieboxpro.android.view.widget.loadcallback.EmptyCallbackView;
import com.movieboxpro.android.view.widget.loadcallback.ErrorCallbackView;
import com.movieboxpro.android.view.widget.loadcallback.LoadingCallbackView;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.disposables.Disposable;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseMvpActivity<T extends BaseContract.BasePresenter, VB extends ViewBinding> extends AppCompatActivity implements BaseContract.BaseView {
    protected VB binding;
    private KProgressHUD kProgressHUD;
    private LoadService loadService;
    protected T mPresenter;

    private void initStatusBar() {
    }

    protected abstract T bindPresenter();

    protected boolean enableEventBus() {
        return false;
    }

    protected abstract int getLayoutResId();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract void initView();

    protected boolean isErrorPageEnable() {
        return false;
    }

    protected boolean isNeedLoadData() {
        return true;
    }

    protected abstract void requestData();

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showError(String message) {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showFinish(String msg) {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View viewInflate = View.inflate(this, getLayoutResId(), null);
        this.binding = DataBindingUtil.bind(viewInflate);
        setContentView(viewInflate);
        ButterKnife.bind(this);
        initStatusBar();
        this.mPresenter = (T) bindPresenter();
        attachView();
        initData();
        initView();
        initListener();
        initLoadSir(viewInflate);
        if (isNeedLoadData()) {
            if (Network.isConnected(this)) {
                requestData();
                return;
            } else {
                this.loadService.showCallback(ErrorCallbackView.class);
                return;
            }
        }
        this.loadService.showSuccess();
    }

    private void initLoadSir(View view) {
        this.loadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() { // from class: com.movieboxpro.android.base.mvp.BaseMvpActivity.1
            @Override // com.kingja.loadsir.callback.Callback.OnReloadListener
            public void onReload(View view2) {
                BaseMvpActivity.this.requestData();
            }
        });
    }

    protected int getStatusColor() {
        return R.color.color_main_back;
    }

    private void attachView() {
        T t = this.mPresenter;
        if (t != null) {
            t.attachView(this);
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoading() {
        this.loadService.showCallback(LoadingCallbackView.class);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoadingView() {
        if (this.kProgressHUD == null && !isFinishing()) {
            this.kProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f).show();
        }
        this.kProgressHUD.show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isLogin() {
        if (App.isLogin()) {
            return true;
        }
        LoginActivity.INSTANCE.start(this);
        return false;
    }

    protected boolean isVipExpired() {
        if (App.getUserData().isvip == 1) {
            return false;
        }
        new VipExpireDialog.Builder(this).setCancelable(false).setTitle("Your VIP has expired.").setContent(String.format("Expire: %s", TimeUtils.formatTime4(App.getUserData().dead_time * 1000))).setNegativeText("Close").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.base.mvp.BaseMvpActivity.3
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public void onClick() {
                new VipChargeGuideDialog().show(BaseMvpActivity.this.getSupportFragmentManager(), "VipChargeGuideDialog");
            }
        }).setCancelListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.base.mvp.BaseMvpActivity.2
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public void onClick() {
                BaseMvpActivity.this.getUserInfo();
            }
        }).create().show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        ((ObservableSubscribeProxy) Http.getService().Userinfo2(API.USER.USERINFO, App.getUserData().uid_v2, SystemUtils.getUniqueId(this), BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(UserModel.UserData.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<UserModel.UserData>(this) { // from class: com.movieboxpro.android.base.mvp.BaseMvpActivity.4
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(UserModel.UserData model) throws Throwable {
                App.updateUser(model);
            }
        });
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoading() {
        this.loadService.showSuccess();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoadingView() {
        if (this.kProgressHUD == null) {
            this.kProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.0f).show();
        }
        this.kProgressHUD.dismiss();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showErrorPage() {
        this.loadService.showCallback(ErrorCallbackView.class);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showEmptyState() {
        this.loadService.showCallback(EmptyCallbackView.class);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (!enableEventBus() || EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        if (enableEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        detachView();
        super.onDestroy();
    }

    private void detachView() {
        T t = this.mPresenter;
        if (t != null) {
            t.detachView();
        }
    }

    protected void route(Class<? extends Activity> claz, Bundle data, View v, String transtion) {
        Intent intent = new Intent(this, claz);
        if (data != null) {
            intent.putExtras(data);
        }
        if (Build.VERSION.SDK_INT > 21) {
            ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, Pair.create(v, transtion)).toBundle());
        } else {
            startActivity(intent);
        }
    }
}
