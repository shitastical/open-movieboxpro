package com.movieboxpro.android.view.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.event.ConfigReceiveEvent;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.tv.LoginActivity;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.view.fragment.account.AccountManagerFragment;
import com.movieboxpro.androidtv.R;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes5.dex */
public class SplashActivity extends BaseActivity {
    private Animation operatingAnim;
    ImageView splashBottom;
    ImageView splashTop;

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isRequireNetWork() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isShowWarning() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isStatusBarTint() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isTitleBarShow() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean shouldLoadSplashAd() {
        return false;
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.activity_splash, container, false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        this.splashTop = (ImageView) findViewById(R.id.splash_top);
        this.splashBottom = (ImageView) findViewById(R.id.splash_bottom);
        setTitleBar(false);
        this.operatingAnim = AnimationUtils.loadAnimation(this, R.anim.rotate_anim);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        if (!isTaskRoot()) {
            Intent intent = getIntent();
            String action = intent.getAction();
            if (intent.hasCategory("android.intent.category.LAUNCHER") && "android.intent.action.MAIN".equals(action)) {
                finish();
                return;
            }
        }
        checkOnlineParams();
    }

    private void checkOnlineParams() {
        if (!Network.isConnected(this.context)) {
            MLog.d(this.TAG, "onResume: no network");
            if (!ConfigUtils.containsConfig(ConfigKey.SHOW_NEWS_MODULE)) {
                String str = String.format("%s_%s_%s", ConfigKey.SHOW_NEWS_MODULE, App.channel, App.versionName);
                if (!App.channel.equals("reader")) {
                    ConfigUtils.writeIntgerConfig(str, 0);
                } else {
                    ConfigUtils.writeIntgerConfig(str, 1);
                }
            }
            onNext();
            return;
        }
        if (ConfigUtils.containsConfig("url")) {
            MLog.d(this.TAG, "onResume: next");
            onNext();
        } else {
            MLog.d(this.TAG, "onResume: update online");
            this.app.updateOnline();
        }
    }

    public void onNext() {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.IS_FIRST_OPEN, true)) {
            ((ObservableSubscribeProxy) Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.view.activity.SplashActivity.1
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable e) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                }

                @Override // io.reactivex.Observer
                public void onNext(Long aLong) {
                    SplashActivity.this.showAnimal();
                }
            });
            return;
        }
        int intBoolConfig = ConfigUtils.readIntBoolConfig(ConfigKey.SHOW_SPLASH_ADVERT);
        int i = App.isLogin() ? App.getUserData().isvip : 0;
        if (App.isLogin() && i == 0 && intBoolConfig == 1) {
            loadsplash();
        } else {
            ((ObservableSubscribeProxy) Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.view.activity.SplashActivity.2
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable e) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                }

                @Override // io.reactivex.Observer
                public void onNext(Long aLong) {
                    SplashActivity.this.showAnimal();
                }
            });
        }
    }

    private void loadsplash() {
        ((ObservableSubscribeProxy) Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.view.activity.SplashActivity.3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
            }

            @Override // io.reactivex.Observer
            public void onNext(Long aLong) throws Throwable {
                SplashActivity.this.onAccepted();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAccepted() throws Throwable {
        this.app.readOnline();
        printSystemInfo();
        toMainOrLogin();
    }

    private void toMainOrLogin() {
        EventBus.getDefault().unregister(this);
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.ACCOUNT_LIST, "");
        if (string.isEmpty()) {
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
                startActivity(new Intent(this, (Class<?>) SuperChildModeActivity.class));
                finish();
                return;
            } else if (App.isLogin()) {
                route(MainActivity.class, getIntent().getExtras());
                finish();
                return;
            } else {
                route(LoginActivity.class);
                finish();
                return;
            }
        }
        try {
            List array = JSONObject.parseArray(string, UserModel.UserData.class);
            if (App.isLogin()) {
                array.add(App.getUserData());
            }
            if (array.size() > 1) {
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.QUICK_SWITCH_ACCOUNT, true) && App.isLogin()) {
                    AccountManagerFragment.INSTANCE.newInstance(true).show(getSupportFragmentManager(), "AccountManagerFragment");
                    return;
                }
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
                    startActivity(new Intent(this, (Class<?>) SuperChildModeActivity.class));
                    finish();
                    return;
                } else if (App.isLogin()) {
                    route(MainActivity.class, getIntent().getExtras());
                    finish();
                    return;
                } else {
                    route(LoginActivity.class);
                    finish();
                    return;
                }
            }
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
                startActivity(new Intent(this, (Class<?>) SuperChildModeActivity.class));
                finish();
            } else if (App.isLogin()) {
                route(MainActivity.class, getIntent().getExtras());
                finish();
            } else {
                route(LoginActivity.class);
                finish();
            }
        } catch (Exception unused) {
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
                startActivity(new Intent(this, (Class<?>) SuperChildModeActivity.class));
                finish();
            } else if (App.isLogin()) {
                route(MainActivity.class, getIntent().getExtras());
                finish();
            } else {
                route(LoginActivity.class);
                finish();
            }
        }
    }

    private void printSystemInfo() throws Throwable {
        String strCheckSystem = SystemUtils.checkSystem(this.activity);
        MLog.d(this.TAG, "info: " + strCheckSystem);
        if (App.isDebug) {
            FileUtils.write(new File(Constant.DIR_LOG + File.separator + "phone.info"), strCheckSystem, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAnimal() {
        if (App.tempCL) {
            this.operatingAnim.setInterpolator(new LinearInterpolator());
            Animation animation = this.operatingAnim;
            if (animation != null) {
                this.splashTop.startAnimation(animation);
            } else {
                this.splashTop.setAnimation(animation);
                this.splashTop.startAnimation(this.operatingAnim);
            }
            this.operatingAnim.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.view.activity.SplashActivity.4
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation2) {
                    ((ObservableSubscribeProxy) Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(SplashActivity.this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.view.activity.SplashActivity.4.1
                        @Override // io.reactivex.Observer
                        public void onComplete() {
                        }

                        @Override // io.reactivex.Observer
                        public void onError(Throwable e) {
                        }

                        @Override // io.reactivex.Observer
                        public void onSubscribe(Disposable d) {
                        }

                        @Override // io.reactivex.Observer
                        public void onNext(Long aLong) throws Throwable {
                            SplashActivity.this.onAccepted();
                        }
                    });
                }
            });
            return;
        }
        ((ObservableSubscribeProxy) Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Consumer<Long>() { // from class: com.movieboxpro.android.view.activity.SplashActivity.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Long aLong) throws Exception {
                SplashActivity.this.onAccepted();
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.operatingAnim == null) {
            ((ObservableSubscribeProxy) Observable.timer(200L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.view.activity.SplashActivity.6
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable e) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                }

                @Override // io.reactivex.Observer
                public void onNext(Long aLong) throws Throwable {
                    SplashActivity.this.onAccepted();
                }
            });
        }
    }

    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        this.splashTop.clearAnimation();
        super.onPause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return keyCode == 4 || super.onKeyDown(keyCode, event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onConfigReceive(ConfigReceiveEvent event) {
        MLog.d(this.TAG, "onConfigReceive");
        onNext();
    }
}
