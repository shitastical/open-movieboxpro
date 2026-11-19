package com.movieboxpro.android.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.media3.common.PlaybackException;
import butterknife.ButterKnife;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.event.UrlChangeEvent;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.presenter.IPresenter;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.Toastor;
import com.movieboxpro.android.view.dialog.NoInternetDialog;
import com.movieboxpro.android.view.listener.IFragmentController;
import com.movieboxpro.android.view.listener.ITitleController;
import com.movieboxpro.android.view.listener.IViewController;
import com.movieboxpro.androidtv.R;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseActivity extends AppCompatActivity implements IViewController, ITitleController, IFragmentController {
    public String TAG;
    protected Activity activity;
    protected App app;
    protected Context appContext;
    protected Context context;
    protected AlertDialog dialog;
    private KProgressHUD hud;
    protected FrameLayout mAdContainer;
    protected FrameLayout mContent;
    protected FrameLayout mFragment;
    protected View mRoot;
    protected View mTitleBar;
    protected ImageView mTitleLeftIconImg;
    protected TextView mTitleLeftText;
    protected RelativeLayout mTitleRightIcon;
    protected ImageView mTitleRightIconDot;
    protected ImageView mTitleRightIconImg;
    protected TextView mTitleRightText;
    protected TextView mTitleText;
    private Toastor mToastor;
    protected IPresenter presenter;
    protected Bundle savedInstanceState;
    protected APIService service;
    protected Stack<String> tags = new Stack<>();
    protected Boolean isStatusBarTint = true;
    private boolean hasLoadingHide = false;

    protected boolean isDialogStyle() {
        return false;
    }

    protected boolean isRequireNetWork() {
        return true;
    }

    protected boolean isShowWarning() {
        return true;
    }

    protected boolean isStatusBarTint() {
        return true;
    }

    protected boolean isSupportBack() {
        return true;
    }

    protected boolean isTitleBarShow() {
        return true;
    }

    protected void onTryAgainClick() {
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        String simpleName = getClass().getSimpleName();
        this.TAG = simpleName;
        MLog.d(simpleName, "onCreate");
        initContext();
        if (!Network.isConnected(this.context)) {
            if (isShowWarning()) {
                MLog.d(this.TAG, "onCreate: showWarning");
                showToast("NetWork Fail");
            }
            if (isRequireNetWork()) {
                finish();
                return;
            }
        }
        if (!isDialogStyle()) {
            initBaseLayout();
            initTitleBar();
            View viewLoadView = loadView(getLayoutInflater(), this.mContent);
            if (viewLoadView != null) {
                this.mContent.addView(viewLoadView);
            }
        } else {
            MLog.d(this.TAG, "wuqi!!!");
            View viewLoadView2 = loadView(getLayoutInflater(), null);
            if (viewLoadView2 != null) {
                setContentView(viewLoadView2);
            }
        }
        ButterKnife.bind(this);
        initPresenter();
        EventBus.getDefault().register(this);
        this.service = Http.getService();
        initView();
        initData();
    }

    private void initContext() {
        this.app = App.getInstance();
        this.appContext = App.getContext();
        this.context = this;
        this.activity = this;
    }

    private void initBaseLayout() {
        setContentView(R.layout.activity_base);
        this.mRoot = findViewById(R.id.base_root);
        this.mTitleBar = findViewById(R.id.activity_base_title);
        this.mTitleLeftText = (TextView) findViewById(R.id.titlebar_left_text);
        this.mTitleLeftIconImg = (ImageView) findViewById(R.id.titlebar_left_icon_img);
        this.mTitleText = (TextView) findViewById(R.id.titlebar_title_text);
        this.mTitleRightText = (TextView) findViewById(R.id.titlebar_right_text);
        this.mTitleRightIcon = (RelativeLayout) findViewById(R.id.titlebar_right_icon);
        this.mTitleRightIconImg = (ImageView) findViewById(R.id.titlebar_right_icon_img);
        this.mTitleRightIconDot = (ImageView) findViewById(R.id.titlebar_right_icon_dot);
        this.mContent = (FrameLayout) findViewById(R.id.activity_base_content);
        this.mFragment = (FrameLayout) findViewById(R.id.activity_base_fragment);
        this.mAdContainer = (FrameLayout) findViewById(R.id.splash_ad_container);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleBar(boolean isShow) {
        if (isShow) {
            this.mTitleBar.setVisibility(0);
            getWindow().setFeatureInt(7, R.layout.layout_title_bar);
        } else {
            this.mTitleBar.setVisibility(8);
        }
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitle(String title, View.OnClickListener listener) {
        this.mTitleText.setVisibility(0);
        this.mTitleText.setText(title);
        if (listener != null) {
            this.mTitleText.setClickable(true);
            this.mTitleText.setOnClickListener(listener);
        } else {
            this.mTitleText.setClickable(false);
        }
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitle(String title) {
        setTitle(title, null);
    }

    public void setTitleBack(View.OnClickListener listener, boolean isShow) {
        if (isShow) {
            this.mTitleLeftText.setVisibility(8);
            this.mTitleLeftIconImg.setVisibility(0);
            this.mTitleLeftIconImg.setImageResource(R.drawable.selector_titlebar_back);
            if (listener != null) {
                this.mTitleLeftIconImg.setOnClickListener(listener);
                return;
            } else {
                this.mTitleLeftIconImg.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseActivity.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        BaseActivity.this.finish();
                    }
                });
                return;
            }
        }
        this.mTitleLeftIconImg.setVisibility(8);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleBack(boolean isShow) {
        setTitleBack(null, isShow);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleBackground(int color) {
        this.mTitleBar.setBackgroundColor(color);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleLeftText(String left, View.OnClickListener listener) {
        if (isTitleBarShow()) {
            if (left != null) {
                this.mTitleLeftIconImg.setVisibility(8);
                this.mTitleLeftText.setVisibility(0);
                this.mTitleLeftText.setText(left);
                if (listener != null) {
                    this.mTitleLeftText.setClickable(true);
                    this.mTitleLeftText.setOnClickListener(listener);
                    return;
                }
                return;
            }
            this.mTitleLeftText.setVisibility(8);
        }
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleLeftIcon(int res, View.OnClickListener listener) {
        this.mTitleLeftIconImg.setVisibility(8);
        this.mTitleLeftIconImg.setVisibility(0);
        this.mTitleLeftIconImg.setImageResource(res);
        if (listener != null) {
            this.mTitleLeftIconImg.setClickable(true);
            this.mTitleLeftIconImg.setOnClickListener(listener);
        } else {
            this.mTitleLeftIconImg.setClickable(false);
        }
    }

    public void setTitleRightText(String title) {
        setTitleRightText(title, false, null);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightText(String right, boolean hasBorder, View.OnClickListener listener) {
        this.mTitleRightIcon.setVisibility(8);
        if (TextUtils.isEmpty(right)) {
            this.mTitleRightText.setVisibility(8);
            return;
        }
        this.mTitleRightText.setVisibility(0);
        this.mTitleRightText.setText(right);
        if (hasBorder) {
            this.mTitleRightText.setBackgroundResource(R.drawable.bg_border_btn_white);
        } else {
            this.mTitleRightText.setBackgroundResource(R.drawable.bg_null);
        }
        if (listener != null) {
            this.mTitleRightText.setClickable(true);
            this.mTitleRightText.setOnClickListener(listener);
        } else {
            this.mTitleRightText.setClickable(false);
        }
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightText(String right, View.OnClickListener listener) {
        setTitleRightText(right, false, listener);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightIcon(int res, boolean hasNew, View.OnClickListener listener) {
        this.mTitleRightText.setVisibility(8);
        this.mTitleRightIcon.setVisibility(0);
        this.mTitleRightIconImg.setVisibility(0);
        this.mTitleRightIconImg.setImageResource(res);
        if (hasNew) {
            this.mTitleRightIconDot.setVisibility(0);
        } else {
            this.mTitleRightIconDot.setVisibility(8);
        }
        if (listener != null) {
            this.mTitleRightIcon.setClickable(true);
            this.mTitleRightIcon.setOnClickListener(listener);
        } else {
            this.mTitleRightIcon.setClickable(false);
        }
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightIcon(int res, View.OnClickListener listener) {
        setTitleRightIcon(res, false, listener);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void clearTitleRightNew() {
        this.mTitleRightIconDot.setVisibility(8);
    }

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void clearTitleRight() {
        this.mTitleRightText.setVisibility(8);
        this.mTitleRightIcon.setVisibility(8);
    }

    private void initTitleBar() {
        if (isTitleBarShow()) {
            setTitleBar(true);
            if (isSupportBack()) {
                setTitleBack(true);
                return;
            } else {
                setTitleBack(false);
                return;
            }
        }
        setTitleBar(false);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initPresenter() {
        MLog.d(this.TAG, "initPresenter");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        MLog.d(this.TAG, "onStart");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MLog.d(this.TAG, "onNewIntent");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        MLog.d(this.TAG, "onResume");
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        MLog.d(this.TAG, "onDownloadPause");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        MLog.d(this.TAG, "onStop");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        MLog.d(this.TAG, "onDestroy");
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD != null && kProgressHUD.isShowing()) {
            this.hud.dismiss();
            this.hud = null;
        }
        AlertDialog alertDialog = this.dialog;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.dialog.dismiss();
        }
        hideLoading(false);
        EventBus.getDefault().unregister(this);
        IPresenter iPresenter = this.presenter;
        if (iPresenter != null) {
            iPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.view.listener.IFragmentController
    public void onFragmentStart(String tag) {
        MLog.d(this.TAG, "onFragmentStart: " + tag);
    }

    @Override // com.movieboxpro.android.view.listener.IFragmentController
    public void onFragmentFinish(String tag) {
        MLog.d(this.TAG, "onFragmentFinish: " + tag);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public <T> void updateView(T t) {
        MLog.d(this.TAG, "updateView");
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showLoading() {
        MLog.d(this.TAG, "showLoading");
        KProgressHUD kProgressHUD = this.hud;
        if ((kProgressHUD == null || !kProgressHUD.isShowing()) && !isFinishing()) {
            KProgressHUD kProgressHUD2 = this.hud;
            if (kProgressHUD2 == null || !kProgressHUD2.isShowing()) {
                this.hud = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f).show();
            }
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void hideLoading() {
        hideLoading(true);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void hideLoading(boolean isDelay) {
        MLog.d(this.TAG, "hideLoading: delay: " + isDelay + ", hasHide: " + this.hasLoadingHide);
        if (isFinishing() || this.hasLoadingHide || this.hud == null) {
            return;
        }
        this.hasLoadingHide = true;
        getWindow().getDecorView().postDelayed(new Runnable() { // from class: com.movieboxpro.android.base.BaseActivity.2
            @Override // java.lang.Runnable
            public void run() {
                MLog.d(BaseActivity.this.TAG, "hideLoading: run");
                if (!BaseActivity.this.isFinishing() && BaseActivity.this.hud != null && BaseActivity.this.hud.isShowing()) {
                    BaseActivity.this.hud.dismiss();
                    MLog.d(BaseActivity.this.TAG, "hideLoading: hud dismiss");
                }
                BaseActivity.this.hasLoadingHide = false;
            }
        }, isDelay ? 300L : 10L);
    }

    public void videHideloading() {
        String str = this.TAG;
        StringBuilder sb = new StringBuilder("hideLoading: delay: ");
        sb.append(this.hud != null);
        sb.append(", hasHide: ");
        sb.append(this.hasLoadingHide);
        MLog.d(str, sb.toString());
        if (isFinishing() || this.hasLoadingHide || this.hud == null) {
            return;
        }
        this.hasLoadingHide = true;
        runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.base.BaseActivity.3
            @Override // java.lang.Runnable
            public void run() {
                MLog.d(BaseActivity.this.TAG, "hideLoading: run");
                if (!BaseActivity.this.isFinishing() && BaseActivity.this.hud != null && BaseActivity.this.hud.isShowing()) {
                    BaseActivity.this.hud.dismiss();
                    MLog.d(BaseActivity.this.TAG, "hideLoading: hud dismiss");
                }
                BaseActivity.this.hasLoadingHide = false;
            }
        });
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showNoInternetDialog() {
        new NoInternetDialog(this, new NoInternetDialog.OnButtonClickListener() { // from class: com.movieboxpro.android.base.BaseActivity.4
            @Override // com.movieboxpro.android.view.dialog.NoInternetDialog.OnButtonClickListener
            public void onClick() {
                BaseActivity.this.onTryAgainClick();
            }
        }).show();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showToast(final String msg) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        ToastUtils.showShort(msg);
    }

    public void showToast(int id) {
        showToast(getString(id));
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showAlert(String msg) {
        new com.adorkable.iosdialog.AlertDialog(this.context).builder().setTitle("Notes").setMsg(msg).setNegativeButton("OK", new View.OnClickListener(this) { // from class: com.movieboxpro.android.base.BaseActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
            }
        }).setCancelable(false).show();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        MLog.d(this.TAG, "onActivityResult");
    }

    protected boolean shouldLoadSplashAd() {
        if (!ConfigUtils.readBooleanConfig(ConfigKey.SHOW_SPLASH_AD, true)) {
            MLog.d(this.TAG, "shouldLoadSplashAd: not show ad");
            return false;
        }
        int iMinutesSinceLastBack = TimeUtils.minutesSinceLastBack(PrefsUtils.getInstance().getLong(Constant.Prefs.ENTER_BACK_TIME, 0L));
        int integerConfig = ConfigUtils.readIntegerConfig(ConfigKey.SPLASH_AD_INTERVAL, 10) * PlaybackException.ERROR_CODE_DRM_UNSPECIFIED;
        if (App.isDebug) {
            integerConfig = 1;
        }
        MLog.d(this.TAG, String.format(Locale.CHINA, "shouldLoadSplashAd: minutes: %d,  interva: %d", Integer.valueOf(iMinutesSinceLastBack), Integer.valueOf(integerConfig)));
        return iMinutesSinceLastBack >= integerConfig;
    }

    protected int getStatusTintColor() {
        return R.color.transparent;
    }

    protected boolean isScreenPortrait() {
        return getResources().getConfiguration().orientation == 1;
    }

    protected boolean isScreenLandscape() {
        return getResources().getConfiguration().orientation == 2;
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.tags.isEmpty()) {
            super.finish();
        } else {
            popFragment();
        }
        if (InputMethodUtils.isActive(this.context)) {
            InputMethodUtils.hideSoftInput(this);
        }
    }

    protected void popFragment() {
        if (this.tags.isEmpty()) {
            return;
        }
        String strPop = this.tags.pop();
        getSupportFragmentManager().beginTransaction().remove(getSupportFragmentManager().findFragmentByTag(strPop)).commit();
        onFragmentFinish(strPop);
    }

    protected void setResult(int resultCode, String key, int value) {
        if (key == null) {
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putInt(key, value);
        intent.putExtras(bundle);
        setResult(resultCode, intent);
        finish();
    }

    protected void setResult(int resultCode, String key, String value) {
        if (key == null || value == null) {
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        intent.putExtras(bundle);
        setResult(resultCode, intent);
        finish();
    }

    protected void setResult(int resultCode, String key, Serializable value) {
        if (key == null || value == null) {
            return;
        }
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, value);
        intent.putExtras(bundle);
        setResult(resultCode, intent);
        finish();
    }

    protected int getIntResult(Intent intent, String key) {
        Bundle extras;
        if (intent == null || key == null || (extras = intent.getExtras()) == null) {
            return 0;
        }
        return extras.getInt(key);
    }

    protected String getStringResult(Intent intent, String key) {
        Bundle extras;
        if (intent == null || key == null || (extras = intent.getExtras()) == null) {
            return null;
        }
        return extras.getString(key);
    }

    protected Serializable getResult(Intent intent, String key) {
        Bundle extras;
        if (intent == null || key == null || (extras = intent.getExtras()) == null) {
            return null;
        }
        return extras.getSerializable(key);
    }

    public void route(Class<? extends Activity> claz) {
        route(claz, (Bundle) null);
    }

    public void route(Class<? extends Activity> claz, Bundle data) {
        Intent intent = new Intent(this, claz);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivity(intent);
    }

    public void route(Class<? extends Activity> claz, int requestCode) {
        route(claz, null, requestCode);
    }

    public void route(Class<? extends Activity> claz, Bundle data, int requestCode) {
        Intent intent = new Intent(this, claz);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivityForResult(intent, requestCode);
    }

    public void route(BaseFragment fragment) {
        if (fragment != null) {
            String name = fragment.getName();
            this.tags.push(name);
            getSupportFragmentManager().beginTransaction().add(R.id.activity_base_fragment, fragment, name).addToBackStack(name).commit();
            onFragmentStart(name);
        }
    }

    protected void route(Class<? extends Activity> claz, Bundle data, View v, String transtion) {
        Intent intent = new Intent(this, claz);
        if (data != null) {
            intent.putExtras(data);
        }
        if (Build.VERSION.SDK_INT > 21) {
            ActivityCompat.startActivity(this, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity, Pair.create(v, transtion)).toBundle());
        } else {
            startActivity(intent);
        }
    }

    protected boolean getBooleanParam(String key, boolean defaultValue) {
        Bundle extras = getIntent().getExtras();
        return extras != null ? extras.getBoolean(key, defaultValue) : defaultValue;
    }

    protected String getStringParam(String key, String defaultValue) {
        Bundle extras = getIntent().getExtras();
        return extras != null ? extras.getString(key, defaultValue) : defaultValue;
    }

    protected int getIntParam(String key, int defaultValue) {
        Bundle extras = getIntent().getExtras();
        return extras != null ? extras.getInt(key, defaultValue) : defaultValue;
    }

    protected double getDoubleParam(String key, double defaultValue) {
        Bundle extras = getIntent().getExtras();
        return extras != null ? extras.getDouble(key, defaultValue) : defaultValue;
    }

    protected <T extends Serializable> T getSerializable(String str, T t) {
        T t2;
        Bundle extras = getIntent().getExtras();
        return (extras == null || (t2 = (T) extras.getSerializable(str)) == null) ? t : t2;
    }

    protected <T extends Parcelable> T getParcelable(String str, T t) {
        T t2;
        Bundle extras = getIntent().getExtras();
        return (extras == null || (t2 = (T) extras.getParcelable(str)) == null) ? t : t2;
    }

    protected List<? extends Serializable> getListParam(String key) {
        Serializable serializable;
        Bundle extras = getIntent().getExtras();
        if (extras == null || (serializable = extras.getSerializable(key)) == null) {
            return null;
        }
        return (List) serializable;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UrlChangeEvent event) {
        this.service = Http.getService();
    }
}
