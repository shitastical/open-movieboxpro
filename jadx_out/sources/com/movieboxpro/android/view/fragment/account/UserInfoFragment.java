package com.movieboxpro.android.view.fragment.account;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.livedata.AddAccountLiveData;
import com.movieboxpro.android.livedata.FocusSettingLiveData;
import com.movieboxpro.android.livedata.PrivateModeChangedLiveData;
import com.movieboxpro.android.livedata.SwitchAccountLiveData;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.tv.HistoryActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SpanUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentUserInfoBinding;
import com.owen.focus.FocusBorder;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: UserInfoFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u0017H\u0002J\b\u0010\u001a\u001a\u00020\u0017H\u0002J!\u0010\u001b\u001a\u00020\u00172\u0012\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u001d\"\u00020\u000fH\u0002¢\u0006\u0002\u0010\u001eJ!\u0010\u001f\u001a\u00020\u00172\u0012\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u000f0\u001d\"\u00020\u000fH\u0002¢\u0006\u0002\u0010\u001eJ\u0018\u0010 \u001a\u00020\u00172\u0006\u0010!\u001a\u00020\u000f2\u0006\u0010\"\u001a\u00020#H\u0002J\b\u0010$\u001a\u00020\u0017H\u0002J\b\u0010%\u001a\u00020\u0017H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/UserInfoFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "vipChargeGuideDialog", "Lcom/movieboxpro/android/view/fragment/account/VipChargeGuideDialog;", "signOutHud", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "settingFragment", "Lcom/movieboxpro/android/view/fragment/account/SettingFragment;", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentUserInfoBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "initUserInfo", "report", "setViewFocusUpListener", "args", "", "([Landroid/view/View;)V", "setViewFocusListener", "focusScale", "v", "scale", "", "showSignOutLoading", "hideSignOutLoading", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UserInfoFragment extends Fragment {
    private FragmentUserInfoBinding binding;
    private FocusBorder mFocusBorder;
    private SettingFragment settingFragment;
    private KProgressHUD signOutHud;
    private VipChargeGuideDialog vipChargeGuideDialog;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$6(View view) {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentUserInfoBinding fragmentUserInfoBindingInflate = FragmentUserInfoBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentUserInfoBindingInflate, "inflate(...)");
        this.binding = fragmentUserInfoBindingInflate;
        if (fragmentUserInfoBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserInfoBindingInflate = null;
        }
        return fragmentUserInfoBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        FragmentUserInfoBinding fragmentUserInfoBinding;
        FragmentUserInfoBinding fragmentUserInfoBinding2;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        UserInfoFragment userInfoFragment = this;
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(userInfoFragment);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
        FragmentUserInfoBinding fragmentUserInfoBinding3 = this.binding;
        if (fragmentUserInfoBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserInfoBinding3 = null;
        }
        fragmentUserInfoBinding3.ivAccount.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UserInfoFragment.onViewCreated$lambda$0(this.f$0, view2);
            }
        });
        FragmentUserInfoBinding fragmentUserInfoBinding4 = this.binding;
        if (fragmentUserInfoBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserInfoBinding4 = null;
        }
        fragmentUserInfoBinding4.tvSignOut.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UserInfoFragment.onViewCreated$lambda$5(this.f$0, view2);
            }
        });
        FragmentUserInfoBinding fragmentUserInfoBinding5 = this.binding;
        if (fragmentUserInfoBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserInfoBinding5 = null;
        }
        fragmentUserInfoBinding5.ivHistory.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UserInfoFragment.onViewCreated$lambda$6(view2);
            }
        });
        FragmentUserInfoBinding fragmentUserInfoBinding6 = this.binding;
        if (fragmentUserInfoBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserInfoBinding6 = null;
        }
        SpanUtils spanUtilsWith = SpanUtils.with(fragmentUserInfoBinding6.tvVersionName);
        Intrinsics.checkNotNullExpressionValue(spanUtilsWith, "with(...)");
        CommonExtKt.addText(CommonExtKt.addText(spanUtilsWith, "v13.4", 16, R.color.white_70alpha), "       Android " + Build.VERSION.RELEASE, 8, R.color.white_70alpha).create();
        FragmentUserInfoBinding fragmentUserInfoBinding7 = this.binding;
        if (fragmentUserInfoBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserInfoBinding7 = null;
        }
        fragmentUserInfoBinding7.ivCrown.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UserInfoFragment.onViewCreated$lambda$7(this.f$0, view2);
            }
        });
        FragmentUserInfoBinding fragmentUserInfoBinding8 = this.binding;
        if (fragmentUserInfoBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserInfoBinding8 = null;
        }
        fragmentUserInfoBinding8.ivSetting.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UserInfoFragment.onViewCreated$lambda$8(this.f$0, view2);
            }
        });
        FragmentUserInfoBinding fragmentUserInfoBinding9 = this.binding;
        if (fragmentUserInfoBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserInfoBinding9 = null;
        }
        fragmentUserInfoBinding9.ivHistory.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UserInfoFragment.onViewCreated$lambda$9(this.f$0, view2);
            }
        });
        FragmentUserInfoBinding fragmentUserInfoBinding10 = this.binding;
        if (fragmentUserInfoBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserInfoBinding10 = null;
        }
        ImageView ivHistory = fragmentUserInfoBinding10.ivHistory;
        Intrinsics.checkNotNullExpressionValue(ivHistory, "ivHistory");
        ImageView ivCrown = fragmentUserInfoBinding10.ivCrown;
        Intrinsics.checkNotNullExpressionValue(ivCrown, "ivCrown");
        TextView tvSignOut = fragmentUserInfoBinding10.tvSignOut;
        Intrinsics.checkNotNullExpressionValue(tvSignOut, "tvSignOut");
        ImageView ivSetting = fragmentUserInfoBinding10.ivSetting;
        Intrinsics.checkNotNullExpressionValue(ivSetting, "ivSetting");
        ImageView ivAccount = fragmentUserInfoBinding10.ivAccount;
        Intrinsics.checkNotNullExpressionValue(ivAccount, "ivAccount");
        setViewFocusListener(ivHistory, ivCrown, tvSignOut, ivSetting, ivAccount);
        ImageView ivHistory2 = fragmentUserInfoBinding10.ivHistory;
        Intrinsics.checkNotNullExpressionValue(ivHistory2, "ivHistory");
        ImageView ivCrown2 = fragmentUserInfoBinding10.ivCrown;
        Intrinsics.checkNotNullExpressionValue(ivCrown2, "ivCrown");
        TextView tvSignOut2 = fragmentUserInfoBinding10.tvSignOut;
        Intrinsics.checkNotNullExpressionValue(tvSignOut2, "tvSignOut");
        ImageView ivSetting2 = fragmentUserInfoBinding10.ivSetting;
        Intrinsics.checkNotNullExpressionValue(ivSetting2, "ivSetting");
        ImageView ivAccount2 = fragmentUserInfoBinding10.ivAccount;
        Intrinsics.checkNotNullExpressionValue(ivAccount2, "ivAccount");
        setViewFocusUpListener(ivHistory2, ivCrown2, tvSignOut2, ivSetting2, ivAccount2);
        initUserInfo();
        SwitchAccountLiveData.INSTANCE.get().observeInFragment(userInfoFragment, new UserInfoFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserInfoFragment.onViewCreated$lambda$11(this.f$0, (Boolean) obj);
            }
        }));
        AddAccountLiveData.INSTANCE.get().observe(getViewLifecycleOwner(), new UserInfoFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserInfoFragment.onViewCreated$lambda$12(this.f$0, (Boolean) obj);
            }
        }));
        FocusSettingLiveData.INSTANCE.get().observeInFragment(userInfoFragment, new UserInfoFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserInfoFragment.onViewCreated$lambda$13(this.f$0, (Boolean) obj);
            }
        }));
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false)) {
            FragmentUserInfoBinding fragmentUserInfoBinding11 = this.binding;
            if (fragmentUserInfoBinding11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentUserInfoBinding2 = null;
            } else {
                fragmentUserInfoBinding2 = fragmentUserInfoBinding11;
            }
            ImageView ivHistory3 = fragmentUserInfoBinding2.ivHistory;
            Intrinsics.checkNotNullExpressionValue(ivHistory3, "ivHistory");
            CommonExtKt.gone(ivHistory3);
        } else {
            FragmentUserInfoBinding fragmentUserInfoBinding12 = this.binding;
            if (fragmentUserInfoBinding12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentUserInfoBinding = null;
            } else {
                fragmentUserInfoBinding = fragmentUserInfoBinding12;
            }
            ImageView ivHistory4 = fragmentUserInfoBinding.ivHistory;
            Intrinsics.checkNotNullExpressionValue(ivHistory4, "ivHistory");
            CommonExtKt.visible(ivHistory4);
        }
        PrivateModeChangedLiveData.INSTANCE.get().observeInFragment(userInfoFragment, new UserInfoFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserInfoFragment.onViewCreated$lambda$14(this.f$0, (Boolean) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(UserInfoFragment userInfoFragment, View view) {
        AccountManagerFragment.INSTANCE.newInstance(false).show(userInfoFragment.getChildFragmentManager(), "AccountManagerFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$5(final UserInfoFragment userInfoFragment, View view) {
        new MsgHintDialog.Builder(userInfoFragment.getContext()).setContent("Are you sure to sign out?").setPositiveText("Sign Out").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda12
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                UserInfoFragment.onViewCreated$lambda$5$lambda$4(this.f$0);
            }
        }).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$5$lambda$4(final UserInfoFragment userInfoFragment) {
        AccountSignOutCoordinator accountSignOutCoordinator = AccountSignOutCoordinator.INSTANCE;
        Context context = userInfoFragment.getContext();
        LifecycleOwner viewLifecycleOwner = userInfoFragment.getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "getViewLifecycleOwner(...)");
        accountSignOutCoordinator.signOut(context, viewLifecycleOwner, new Function0() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UserInfoFragment.onViewCreated$lambda$5$lambda$4$lambda$1(this.f$0);
            }
        }, new Function0() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UserInfoFragment.onViewCreated$lambda$5$lambda$4$lambda$2(this.f$0);
            }
        }, new Function0() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return UserInfoFragment.onViewCreated$lambda$5$lambda$4$lambda$3(this.f$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$5$lambda$4$lambda$1(UserInfoFragment userInfoFragment) {
        userInfoFragment.showSignOutLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$5$lambda$4$lambda$2(UserInfoFragment userInfoFragment) {
        userInfoFragment.hideSignOutLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$5$lambda$4$lambda$3(UserInfoFragment userInfoFragment) {
        AccountManagerFragment.INSTANCE.newInstance(false).show(userInfoFragment.getChildFragmentManager(), "AccountManagerFragment");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$7(UserInfoFragment userInfoFragment, View view) {
        if (userInfoFragment.vipChargeGuideDialog == null) {
            userInfoFragment.vipChargeGuideDialog = new VipChargeGuideDialog();
        }
        VipChargeGuideDialog vipChargeGuideDialog = userInfoFragment.vipChargeGuideDialog;
        Intrinsics.checkNotNull(vipChargeGuideDialog);
        vipChargeGuideDialog.show(userInfoFragment.getChildFragmentManager(), "VipChargeGuideDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$8(UserInfoFragment userInfoFragment, View view) {
        if (userInfoFragment.settingFragment == null) {
            userInfoFragment.settingFragment = new SettingFragment();
        }
        SettingFragment settingFragment = userInfoFragment.settingFragment;
        Intrinsics.checkNotNull(settingFragment);
        settingFragment.show(userInfoFragment.getChildFragmentManager(), "SettingFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$9(UserInfoFragment userInfoFragment, View view) {
        Intent intent = new Intent(userInfoFragment.getContext(), (Class<?>) HistoryActivity.class);
        Context context = userInfoFragment.getContext();
        if (context != null) {
            context.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$11(UserInfoFragment userInfoFragment, Boolean bool) {
        userInfoFragment.initUserInfo();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$12(UserInfoFragment userInfoFragment, Boolean bool) {
        userInfoFragment.initUserInfo();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$13(UserInfoFragment userInfoFragment, Boolean bool) {
        FragmentUserInfoBinding fragmentUserInfoBinding = userInfoFragment.binding;
        if (fragmentUserInfoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserInfoBinding = null;
        }
        fragmentUserInfoBinding.ivSetting.requestFocus();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onViewCreated$lambda$14(UserInfoFragment userInfoFragment, Boolean bool) {
        FragmentUserInfoBinding fragmentUserInfoBinding = null;
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false)) {
            FragmentUserInfoBinding fragmentUserInfoBinding2 = userInfoFragment.binding;
            if (fragmentUserInfoBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentUserInfoBinding = fragmentUserInfoBinding2;
            }
            ImageView ivHistory = fragmentUserInfoBinding.ivHistory;
            Intrinsics.checkNotNullExpressionValue(ivHistory, "ivHistory");
            CommonExtKt.gone(ivHistory);
        } else {
            FragmentUserInfoBinding fragmentUserInfoBinding3 = userInfoFragment.binding;
            if (fragmentUserInfoBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentUserInfoBinding = fragmentUserInfoBinding3;
            }
            ImageView ivHistory2 = fragmentUserInfoBinding.ivHistory;
            Intrinsics.checkNotNullExpressionValue(ivHistory2, "ivHistory");
            CommonExtKt.visible(ivHistory2);
        }
        return Unit.INSTANCE;
    }

    private final void initUserInfo() {
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData(...)");
        FragmentUserInfoBinding fragmentUserInfoBinding = this.binding;
        FragmentUserInfoBinding fragmentUserInfoBinding2 = null;
        if (fragmentUserInfoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentUserInfoBinding = null;
        }
        fragmentUserInfoBinding.tvUsername.setText(userData.username);
        if (userData.isvip == 1) {
            FragmentUserInfoBinding fragmentUserInfoBinding3 = this.binding;
            if (fragmentUserInfoBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentUserInfoBinding3 = null;
            }
            ImageView ivVip = fragmentUserInfoBinding3.ivVip;
            Intrinsics.checkNotNullExpressionValue(ivVip, "ivVip");
            CommonExtKt.visible(ivVip);
            FragmentUserInfoBinding fragmentUserInfoBinding4 = this.binding;
            if (fragmentUserInfoBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentUserInfoBinding4 = null;
            }
            TextView tvVipDate = fragmentUserInfoBinding4.tvVipDate;
            Intrinsics.checkNotNullExpressionValue(tvVipDate, "tvVipDate");
            CommonExtKt.visible(tvVipDate);
            FragmentUserInfoBinding fragmentUserInfoBinding5 = this.binding;
            if (fragmentUserInfoBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentUserInfoBinding5 = null;
            }
            TextView textView = fragmentUserInfoBinding5.tvVipDate;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("Expire: %s", Arrays.copyOf(new Object[]{TimeUtils.formatTime4(App.getUserData().dead_time * ((long) 1000))}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            textView.setText(str);
        } else {
            FragmentUserInfoBinding fragmentUserInfoBinding6 = this.binding;
            if (fragmentUserInfoBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentUserInfoBinding6 = null;
            }
            ImageView ivVip2 = fragmentUserInfoBinding6.ivVip;
            Intrinsics.checkNotNullExpressionValue(ivVip2, "ivVip");
            CommonExtKt.gone(ivVip2);
            FragmentUserInfoBinding fragmentUserInfoBinding7 = this.binding;
            if (fragmentUserInfoBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentUserInfoBinding7 = null;
            }
            fragmentUserInfoBinding7.tvVipDate.setText("Not a VIP");
        }
        Context context = getContext();
        String str2 = userData.avatar;
        FragmentUserInfoBinding fragmentUserInfoBinding8 = this.binding;
        if (fragmentUserInfoBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentUserInfoBinding2 = fragmentUserInfoBinding8;
        }
        GlideUtils.load(context, str2, fragmentUserInfoBinding2.ivAvatar, R.drawable.ic_default_avatar);
    }

    private final void report() {
        Observable observableJust = Observable.just("");
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserInfoFragment.report$lambda$16((String) obj);
            }
        };
        Observable map = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda13
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return UserInfoFragment.report$lambda$17(function1, obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserInfoFragment.report$lambda$18((String) obj);
            }
        };
        Observable observableCompose = map.flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda15
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return UserInfoFragment.report$lambda$19(function2, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(observableCompose, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return UserInfoFragment.report$lambda$20((String) obj);
            }
        }, null, 11, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String report$lambda$17(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (String) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String report$lambda$16(String it) throws IOException {
        Intrinsics.checkNotNullParameter(it, "it");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -d").getInputStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                if (line != null && StringsKt.contains$default((CharSequence) line, (CharSequence) "IJKMEDIA", false, 2, (Object) null)) {
                    sb.append(line);
                    sb.append(ShellUtil.COMMAND_LINE_END);
                }
            } else {
                return sb.toString();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource report$lambda$19(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource report$lambda$18(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Http.getService().Movie_feedback2(API.Common.MOVIE_FEEDBACK, App.isLogin() ? App.getUserData().uid_v2 : "", 1, "", -1, "", "14", SystemUtils.getMsg() + ShellUtil.COMMAND_LINE_END + it, 0, 0, "").compose(RxUtils.rxTranslateMsg());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit report$lambda$20(String str) {
        ToastUtils.showShort("Report complete", new Object[0]);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean setViewFocusUpListener$lambda$22$lambda$21(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 || i != 19) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setViewFocusListener$lambda$24$lambda$23(UserInfoFragment userInfoFragment, View view, boolean z) {
        if (z) {
            Intrinsics.checkNotNull(view);
            userInfoFragment.focusScale(view, 1.1f);
        }
        FocusBorder focusBorder = userInfoFragment.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(z);
    }

    private final void focusScale(View v, float scale) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }

    private final void showSignOutLoading() {
        KProgressHUD kProgressHUD = this.signOutHud;
        if (kProgressHUD == null || !kProgressHUD.isShowing()) {
            this.signOutHud = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(false).setAnimationSpeed(1).setDimAmount(0.5f).show();
        }
    }

    private final void hideSignOutLoading() {
        KProgressHUD kProgressHUD;
        KProgressHUD kProgressHUD2 = this.signOutHud;
        if (kProgressHUD2 == null || !kProgressHUD2.isShowing() || (kProgressHUD = this.signOutHud) == null) {
            return;
        }
        kProgressHUD.dismiss();
    }

    private final void setViewFocusUpListener(View... args) {
        for (View view : args) {
            view.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda0
                @Override // android.view.View.OnKeyListener
                public final boolean onKey(View view2, int i, KeyEvent keyEvent) {
                    return UserInfoFragment.setViewFocusUpListener$lambda$22$lambda$21(view2, i, keyEvent);
                }
            });
        }
    }

    private final void setViewFocusListener(View... args) {
        for (View view : args) {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.fragment.account.UserInfoFragment$$ExternalSyntheticLambda17
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    UserInfoFragment.setViewFocusListener$lambda$24$lambda$23(this.f$0, view2, z);
                }
            });
        }
    }
}
