package com.movieboxpro.android.view.fragment.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentManager;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseLazyFragment;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: AccountFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001a\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u001bH\u0007J\b\u0010\u001c\u001a\u00020\u0015H\u0002J\b\u0010\u001d\u001a\u00020\u0015H\u0002J\b\u0010\u001e\u001a\u00020\u0015H\u0016J\b\u0010\u001f\u001a\u00020\u0015H\u0016J\b\u0010 \u001a\u00020\u0015H\u0016J\b\u0010!\u001a\u00020\u0015H\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/AccountFragment;", "Lcom/movieboxpro/android/base/BaseLazyFragment;", "<init>", "()V", "loginFragment", "Lcom/movieboxpro/android/view/fragment/account/LoginFragment;", "codeLoginFragment", "Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentFragment;", "userInfoFragment", "Lcom/movieboxpro/android/view/fragment/account/UserInfoFragment;", "chooseLoginFragment", "Lcom/movieboxpro/android/view/fragment/account/ChooseLoginWayFragment;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "onLogin", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/LoginEvent;", "onSignOut", "Lcom/movieboxpro/android/event/LogoutEvent;", "initFragment", "initChooseLoginFragment", "onFragmentResume", "onFragmentPause", "onStart", "onDestroy", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AccountFragment extends BaseLazyFragment {
    private ChooseLoginWayFragment chooseLoginFragment;
    private CodeLoginFragmentFragment codeLoginFragment;
    private LoginFragment loginFragment;
    private UserInfoFragment userInfoFragment;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.account_fragment, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initFragment();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLogin(LoginEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.userInfoFragment == null) {
            this.userInfoFragment = new UserInfoFragment();
        }
        FragmentManager childFragmentManager = getChildFragmentManager();
        UserInfoFragment userInfoFragment = this.userInfoFragment;
        Intrinsics.checkNotNull(userInfoFragment);
        FragmentUtils.replace(childFragmentManager, userInfoFragment, R.id.frameLayout);
        this.loginFragment = null;
        this.codeLoginFragment = null;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSignOut(LogoutEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.ACCOUNT_LIST, "");
        Intrinsics.checkNotNull(string);
        if (AccountSignOutCoordinatorKt.isMultiAccount(string)) {
            AccountManagerFragment.INSTANCE.newInstance(true).show(getChildFragmentManager(), "AccountManagerFragment");
            this.userInfoFragment = null;
            return;
        }
        initChooseLoginFragment();
        FragmentManager childFragmentManager = getChildFragmentManager();
        ChooseLoginWayFragment chooseLoginWayFragment = this.chooseLoginFragment;
        Intrinsics.checkNotNull(chooseLoginWayFragment);
        FragmentUtils.replace(childFragmentManager, chooseLoginWayFragment, R.id.frameLayout);
        this.userInfoFragment = null;
    }

    private final void initFragment() {
        if (!App.isLogin()) {
            initChooseLoginFragment();
            FragmentManager childFragmentManager = getChildFragmentManager();
            ChooseLoginWayFragment chooseLoginWayFragment = this.chooseLoginFragment;
            Intrinsics.checkNotNull(chooseLoginWayFragment);
            FragmentUtils.add(childFragmentManager, chooseLoginWayFragment, R.id.frameLayout);
            return;
        }
        if (this.userInfoFragment == null) {
            this.userInfoFragment = new UserInfoFragment();
        }
        FragmentManager childFragmentManager2 = getChildFragmentManager();
        UserInfoFragment userInfoFragment = this.userInfoFragment;
        Intrinsics.checkNotNull(userInfoFragment);
        FragmentUtils.add(childFragmentManager2, userInfoFragment, R.id.frameLayout);
    }

    private final void initChooseLoginFragment() {
        if (this.chooseLoginFragment == null) {
            this.chooseLoginFragment = new ChooseLoginWayFragment();
        }
        ChooseLoginWayFragment chooseLoginWayFragment = this.chooseLoginFragment;
        Intrinsics.checkNotNull(chooseLoginWayFragment);
        chooseLoginWayFragment.setListener(new ChooseLoginWayFragment.ChooseLoginListener() { // from class: com.movieboxpro.android.view.fragment.account.AccountFragment.initChooseLoginFragment.1
            @Override // com.movieboxpro.android.view.fragment.account.ChooseLoginWayFragment.ChooseLoginListener
            public void chooseLogin(int type) {
                if (type == 1) {
                    if (AccountFragment.this.loginFragment == null) {
                        AccountFragment.this.loginFragment = LoginFragment.INSTANCE.newInstance(false);
                    }
                    FragmentManager childFragmentManager = AccountFragment.this.getChildFragmentManager();
                    LoginFragment loginFragment = AccountFragment.this.loginFragment;
                    Intrinsics.checkNotNull(loginFragment);
                    FragmentUtils.replace(childFragmentManager, loginFragment, R.id.frameLayout);
                    return;
                }
                if (type != 2) {
                    return;
                }
                if (AccountFragment.this.codeLoginFragment == null) {
                    AccountFragment.this.codeLoginFragment = CodeLoginFragmentFragment.Companion.newInstance$default(CodeLoginFragmentFragment.INSTANCE, false, 1, null);
                }
                FragmentManager childFragmentManager2 = AccountFragment.this.getChildFragmentManager();
                CodeLoginFragmentFragment codeLoginFragmentFragment = AccountFragment.this.codeLoginFragment;
                Intrinsics.checkNotNull(codeLoginFragmentFragment);
                FragmentUtils.replace(childFragmentManager2, codeLoginFragmentFragment, R.id.frameLayout);
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResume() {
        LoginFragment loginFragment = this.loginFragment;
        if (loginFragment != null) {
            loginFragment.onStartTimer();
        }
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentPause() {
        LoginFragment loginFragment = this.loginFragment;
        if (loginFragment != null) {
            loginFragment.onStopTimer();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
