package com.movieboxpro.android.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import butterknife.ButterKnife;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.event.UrlChangeEvent;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.presenter.IPresenter;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.view.dialog.NoInternetDialog;
import com.movieboxpro.android.view.listener.ITabListener;
import com.movieboxpro.android.view.listener.ITitleController;
import com.movieboxpro.android.view.listener.IViewController;
import com.movieboxpro.androidtv.R;
import java.io.Serializable;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseFragment extends Fragment implements IViewController, ITitleController {
    protected Activity activity;
    protected App app;
    protected Context appContext;
    protected Context context;
    public ITabListener listener;
    protected FrameLayout mContent;
    protected View mTitleBar;
    protected ImageView mTitleLeftIconImg;
    protected TextView mTitleLeftText;
    protected RelativeLayout mTitleRightIcon;
    protected ImageView mTitleRightIconDot;
    protected ImageView mTitleRightIconImg;
    protected TextView mTitleRightText;
    protected TextView mTitleText;
    protected String name;
    protected IPresenter presenter;
    protected APIService service;
    protected boolean isViewInited = false;
    private boolean isAddIn = false;
    protected boolean loaded = false;
    private boolean isWarningShowed = false;
    protected String TAG = getClass().getSimpleName();

    protected boolean isRequireNetWork() {
        return true;
    }

    protected boolean isShowWarning() {
        return true;
    }

    protected boolean isSupportBack() {
        return false;
    }

    protected boolean isTitleBarShow() {
        return false;
    }

    public boolean onBackPressed() {
        return false;
    }

    protected void onTryAgainClick() {
    }

    public void setListener(ITabListener listener) {
        this.listener = listener;
    }

    public String getName() {
        if (this.name == null) {
            this.name = getClass().getSimpleName();
        }
        return this.name;
    }

    public void setAddIn(boolean addIn) {
        this.isAddIn = addIn;
    }

    public void setName(String name) {
        if (this.name == null) {
            this.name = name;
            this.TAG += ":" + name;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MLog.d(this.TAG, "onCreate");
        this.service = Http.getService();
        initContext();
    }

    protected void initContext() {
        this.app = App.getInstance();
        this.appContext = App.getContext();
        this.context = getActivity();
        this.activity = getActivity();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MLog.d(this.TAG, "onCreateView");
        View viewInflate = inflater.inflate(R.layout.fragment_base, container, false);
        this.mContent = (FrameLayout) viewInflate.findViewById(R.id.fragment_base_content);
        EventBus.getDefault().register(this);
        View viewLoadView = loadView(inflater, this.mContent);
        initRootView(viewInflate, viewLoadView);
        initTitleBar();
        ButterKnife.bind(this, viewLoadView);
        initView();
        initPresenter();
        this.isViewInited = true;
        if (this.isAddIn) {
            if (isVisible()) {
                onVisible();
            }
        } else if (getUserVisibleHint()) {
            MLog.d(this.TAG, "getUserVisibleHint:" + getUserVisibleHint());
            onVisible();
        }
        return viewInflate;
    }

    private void initRootView(View rootView, View contentView) {
        this.mTitleBar = rootView.findViewById(R.id.fragment_base_title);
        this.mTitleLeftIconImg = (ImageView) rootView.findViewById(R.id.titlebar_left_icon_img);
        this.mTitleLeftText = (TextView) rootView.findViewById(R.id.titlebar_left_text);
        this.mTitleText = (TextView) rootView.findViewById(R.id.titlebar_title_text);
        this.mTitleRightText = (TextView) rootView.findViewById(R.id.titlebar_right_text);
        this.mTitleRightIcon = (RelativeLayout) rootView.findViewById(R.id.titlebar_right_icon);
        this.mTitleRightIconImg = (ImageView) rootView.findViewById(R.id.titlebar_right_icon_img);
        this.mTitleRightIconDot = (ImageView) rootView.findViewById(R.id.titlebar_right_icon_dot);
        this.mContent.removeAllViews();
        this.mContent.addView(contentView);
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

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleBar(boolean isShow) {
        if (isShow) {
            this.mTitleBar.setVisibility(0);
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

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleBack(boolean isShow) {
        if (isShow) {
            this.mTitleLeftText.setVisibility(8);
            this.mTitleLeftIconImg.setVisibility(0);
            this.mTitleLeftIconImg.setImageResource(R.drawable.selector_titlebar_back);
            this.mTitleLeftIconImg.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.base.BaseFragment.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    BaseFragment.this.getActivity().finish();
                }
            });
            return;
        }
        this.mTitleLeftIconImg.setVisibility(8);
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

    @Override // com.movieboxpro.android.view.listener.ITitleController
    public void setTitleRightText(String right, boolean hasBorder, View.OnClickListener listener) {
        this.mTitleRightIcon.setVisibility(8);
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

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        MLog.d(this.TAG, "onStart");
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MLog.d(this.TAG, "onResume");
        if (this.isViewInited) {
            if (this.isAddIn) {
                if (isVisible()) {
                    onVisible();
                }
            } else if (getUserVisibleHint()) {
                MLog.d(this.TAG, "getUserVisibleHint:" + getUserVisibleHint());
                onVisible();
            }
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initPresenter() {
        MLog.d(this.TAG, "initPresenter");
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public <T> void updateView(T t) {
        MLog.d(this.TAG, "updateView");
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showToast(String msg) {
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showToast(msg);
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showNoInternetDialog() {
        new NoInternetDialog(getContext(), new NoInternetDialog.OnButtonClickListener() { // from class: com.movieboxpro.android.base.BaseFragment.2
            @Override // com.movieboxpro.android.view.dialog.NoInternetDialog.OnButtonClickListener
            public void onClick() {
                BaseFragment.this.onTryAgainClick();
            }
        }).show();
    }

    public void showToast(int id) {
        showToast(getString(id));
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showAlert(String msg) {
        FragmentActivity activity = getActivity();
        if (activity instanceof BaseActivity) {
            ((BaseActivity) activity).showAlert(msg);
        }
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void showLoading() {
        MLog.d(this.TAG, "showLoading");
        KeyEventDispatcher.Component activity = getActivity();
        if (activity == null || !(activity instanceof IViewController)) {
            return;
        }
        ((IViewController) activity).showLoading();
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void hideLoading() {
        hideLoading(true);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void hideLoading(boolean isDelay) {
        MLog.d(this.TAG, "hideLoading");
        KeyEventDispatcher.Component activity = getActivity();
        if (activity instanceof IViewController) {
            ((IViewController) activity).hideLoading(isDelay);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        MLog.d(this.TAG, "onDownloadPause");
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        MLog.d(this.TAG, "onStop");
        super.onStop();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        MLog.d(this.TAG, "onDestroyView");
        EventBus.getDefault().unregister(this);
        this.loaded = false;
        this.isViewInited = false;
        IPresenter iPresenter = this.presenter;
        if (iPresenter != null) {
            iPresenter.detachView();
        }
        hideLoading();
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        MLog.d(this.TAG, "onDestroy");
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        MLog.d(this.TAG, "setUserVisibleHint:" + isVisibleToUser);
        if (this.isViewInited) {
            if (getUserVisibleHint()) {
                onVisible();
            } else {
                onInVisible();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        MLog.d(this.TAG, "onHiddenChanged:" + hidden);
        if (this.isViewInited) {
            if (!isHidden()) {
                onVisible();
            } else {
                onInVisible();
            }
        }
    }

    protected void onVisible() {
        MLog.d(this.TAG, "onVisible");
        if (Network.isConnected(this.context) || !isRequireNetWork() || !isShowWarning() || this.isWarningShowed) {
            return;
        }
        MLog.d(this.TAG, "onVisible: showWarning");
        showToast("NetWork Fail");
        this.isWarningShowed = true;
    }

    protected void onInVisible() {
        MLog.d(this.TAG, "onInVisible");
        this.isWarningShowed = false;
    }

    protected void route(Class<? extends Activity> claz) {
        route(claz, (Bundle) null);
    }

    protected void route(Class<? extends Activity> claz, Bundle data) {
        Intent intent = new Intent(getContext(), claz);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivity(intent);
    }

    protected void route(Class<? extends Activity> claz, int requestCode) {
        route(claz, null, requestCode);
    }

    protected void route(Class<? extends Activity> claz, Bundle data, int requestCode) {
        Intent intent = new Intent(getContext(), claz);
        if (data != null) {
            intent.putExtras(data);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void route(Class<? extends Activity> claz, Bundle data, View v, String transtion) {
        Intent intent = new Intent(getContext(), claz);
        if (data != null) {
            intent.putExtras(data);
        }
        if (Build.VERSION.SDK_INT > 21) {
            ActivityCompat.startActivity(this.activity, intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity, Pair.create(v, transtion)).toBundle());
        } else {
            startActivity(intent);
        }
    }

    protected boolean getBooleanParam(String key, boolean defaultValue) {
        Bundle arguments = getArguments();
        return arguments != null ? arguments.getBoolean(key, defaultValue) : defaultValue;
    }

    protected String getStringParam(String key, String defaultValue) {
        Bundle arguments = getArguments();
        return arguments != null ? arguments.getString(key, defaultValue) : defaultValue;
    }

    protected int getIntParam(String key, int defaultValue) {
        Bundle arguments = getArguments();
        return arguments != null ? arguments.getInt(key, defaultValue) : defaultValue;
    }

    protected <T extends Serializable> T getSerializable(String str, T t) {
        T t2;
        Bundle arguments = getArguments();
        return (arguments == null || (t2 = (T) arguments.getSerializable(str)) == null) ? t : t2;
    }

    protected <T extends Parcelable> T getParcelable(String str, T t) {
        T t2;
        Bundle arguments = getArguments();
        return (arguments == null || (t2 = (T) arguments.getParcelable(str)) == null) ? t : t2;
    }

    protected boolean isScreenPortrait() {
        MLog.d(this.TAG, "isScreenPortrait");
        return getResources().getConfiguration().orientation == 1;
    }

    protected boolean isScreenLandscape() {
        MLog.d(this.TAG, "isScreenLandscape");
        return getResources().getConfiguration().orientation == 2;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UrlChangeEvent event) {
        this.service = Http.getService();
    }
}
