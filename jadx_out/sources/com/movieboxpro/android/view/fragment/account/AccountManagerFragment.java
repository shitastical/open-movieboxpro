package com.movieboxpro.android.view.fragment.account;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.adorkable.iosdialog.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.livedata.AddAccountLiveData;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.tv.LoginActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.MsgHintDialog;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: AccountManagerFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 )2\u00020\u0001:\u0001)B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0015H\u0016J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\u001a\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u001a2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010!\u001a\u00020\u0015H\u0002J\b\u0010\"\u001a\u00020\u0015H\u0002J\b\u0010#\u001a\u00020\u0015H\u0002J\u0010\u0010$\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0006H\u0002J\u0010\u0010&\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u001aH\u0002J\b\u0010'\u001a\u00020\u0015H\u0002J\b\u0010(\u001a\u00020\u0015H\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/AccountManagerFragment;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "adapter", "Lcom/movieboxpro/android/base/CommonBaseAdapter;", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "hud", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "<set-?>", "", "chooseAccount", "getChooseAccount", "()Z", "setChooseAccount", "(Z)V", "chooseAccount$delegate", "Lkotlin/properties/ReadWriteProperty;", "mRecyclerView", "Lcom/owen/tvrecyclerview/widget/TvRecyclerView;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "getAccountList", "initListener", "signOut", "switchAccount", "model", "initData", "showLoadingView", "hideLoadingView", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AccountManagerFragment extends DialogFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(AccountManagerFragment.class, "chooseAccount", "getChooseAccount()Z", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CommonBaseAdapter<UserModel.UserData> adapter;

    /* JADX INFO: renamed from: chooseAccount$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty chooseAccount = FragmentArgsKt.argOrDefault(this, false);
    private KProgressHUD hud;
    private TvRecyclerView mRecyclerView;

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getChooseAccount() {
        return ((Boolean) this.chooseAccount.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setChooseAccount(boolean z) {
        this.chooseAccount.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    /* JADX INFO: compiled from: AccountManagerFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/AccountManagerFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/fragment/account/AccountManagerFragment;", "chooseAccount", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final AccountManagerFragment newInstance(boolean chooseAccount) {
            AccountManagerFragment accountManagerFragment = new AccountManagerFragment();
            accountManagerFragment.setChooseAccount(chooseAccount);
            return accountManagerFragment;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.DialogStyle);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            getResources().getDisplayMetrics();
            attributes.width = -1;
            attributes.height = -1;
            attributes.gravity = 17;
            attributes.dimAmount = 0.8f;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(com.movieboxpro.androidtv.R.layout.fragment_account_manager, container, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData(view);
        initListener();
        getAccountList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getAccountList() {
        Observable observableJust = Observable.just("");
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountManagerFragment.getAccountList$lambda$1((String) obj);
            }
        };
        Object objAs = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return AccountManagerFragment.getAccountList$lambda$2(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountManagerFragment.getAccountList$lambda$3((ApiException) obj);
            }
        }, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountManagerFragment.getAccountList$lambda$4(this.f$0, (List) obj);
            }
        }, 14, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getAccountList$lambda$2(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List getAccountList$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.ACCOUNT_LIST, "");
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData(...)");
        Intrinsics.checkNotNull(string);
        return AccountListHelperKt.mergeDisplayAccounts(userData, string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getAccountList$lambda$4(AccountManagerFragment accountManagerFragment, List list) {
        TvRecyclerView tvRecyclerView;
        Intrinsics.checkNotNull(list);
        List mutableList = CollectionsKt.toMutableList((Collection) list);
        mutableList.add(new UserModel.UserData());
        CommonBaseAdapter<UserModel.UserData> commonBaseAdapter = accountManagerFragment.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setList(mutableList);
        if (accountManagerFragment.getChooseAccount() && (tvRecyclerView = accountManagerFragment.mRecyclerView) != null) {
            tvRecyclerView.requestFocus();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getAccountList$lambda$3(ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void initListener() {
        CommonBaseAdapter<UserModel.UserData> commonBaseAdapter = this.adapter;
        CommonBaseAdapter<UserModel.UserData> commonBaseAdapter2 = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                AccountManagerFragment.initListener$lambda$6(this.f$0, baseQuickAdapter, view, i);
            }
        });
        CommonBaseAdapter<UserModel.UserData> commonBaseAdapter3 = this.adapter;
        if (commonBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter2 = commonBaseAdapter3;
        }
        commonBaseAdapter2.setOnItemLongClickListener(new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
            public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                return AccountManagerFragment.initListener$lambda$8(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6(AccountManagerFragment accountManagerFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommonBaseAdapter<UserModel.UserData> commonBaseAdapter = accountManagerFragment.adapter;
        CommonBaseAdapter<UserModel.UserData> commonBaseAdapter2 = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        UserModel.UserData item = commonBaseAdapter.getItem(i);
        if (Intrinsics.areEqual(App.getUserData().uid, item.uid)) {
            if (accountManagerFragment.getChooseAccount()) {
                FragmentActivity activity = accountManagerFragment.getActivity();
                if (activity != null) {
                    activity.finish();
                }
                accountManagerFragment.dismiss();
                Context context = accountManagerFragment.getContext();
                if (context != null) {
                    MainActivity.INSTANCE.start(context);
                    return;
                }
                return;
            }
            return;
        }
        CommonBaseAdapter<UserModel.UserData> commonBaseAdapter3 = accountManagerFragment.adapter;
        if (commonBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter2 = commonBaseAdapter3;
        }
        if (i == commonBaseAdapter2.getData().size() - 1) {
            LoginActivity.INSTANCE.start(accountManagerFragment.getContext(), true);
        } else {
            accountManagerFragment.switchAccount(item);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$8(final AccountManagerFragment accountManagerFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommonBaseAdapter<UserModel.UserData> commonBaseAdapter = accountManagerFragment.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        final UserModel.UserData item = commonBaseAdapter.getItem(i);
        new MsgHintDialog.Builder(accountManagerFragment.getContext()).setContent("Are you sure to delete it").setPositiveText("Delete").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda8
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                AccountManagerFragment.initListener$lambda$8$lambda$7(item, accountManagerFragment);
            }
        }).create().show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$8$lambda$7(UserModel.UserData userData, AccountManagerFragment accountManagerFragment) {
        CommonBaseAdapter<UserModel.UserData> commonBaseAdapter = null;
        if (!Intrinsics.areEqual(userData.uid, App.getUserData().uid)) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(accountManagerFragment), Dispatchers.getIO(), null, new AccountManagerFragment$initListener$2$1$1(userData, accountManagerFragment, null), 2, null);
            return;
        }
        CommonBaseAdapter<UserModel.UserData> commonBaseAdapter2 = accountManagerFragment.adapter;
        if (commonBaseAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            commonBaseAdapter = commonBaseAdapter2;
        }
        if (commonBaseAdapter.getData().size() == 2) {
            boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false);
            App.logout();
            accountManagerFragment.signOut();
            EventBus.getDefault().post(new ListFocusUpEvent());
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.CHILD_MODE, z);
            return;
        }
        ToastUtils.showShort("Can't delete the selected account", new Object[0]);
    }

    private final void signOut() {
        AccountSwitchHelperKt.requestLoginOut(getContext());
    }

    private final void switchAccount(UserModel.UserData model) {
        UserModel.UserData userData;
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.ACCOUNT_LIST, "");
        Intrinsics.checkNotNull(string);
        if (StringsKt.isBlank(string)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = AccountListHelperKt.parseSavedAccountList(string).iterator();
        UserModel.UserData userData2 = null;
        loop0: while (true) {
            userData = userData2;
            while (true) {
                if (!it.hasNext()) {
                    break loop0;
                }
                userData2 = (UserModel.UserData) it.next();
                if (!AccountListHelperKt.isSameAccount(userData2, model)) {
                    arrayList.add(userData2);
                }
            }
        }
        if (userData == null) {
            return;
        }
        UserModel.UserData userData3 = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData3, "getUserData(...)");
        arrayList.add(AccountListHelperKt.captureAccountWithSession(userData3));
        AccountSwitchHelperKt.performAccountSwitch(this, getContext(), userData, arrayList, new Function0() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AccountManagerFragment.switchAccount$lambda$10(this.f$0);
            }
        }, new Function0() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return AccountManagerFragment.switchAccount$lambda$12(this.f$0);
            }
        }, new Function2() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return AccountManagerFragment.switchAccount$lambda$13(this.f$0, (Throwable) obj, ((Boolean) obj2).booleanValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit switchAccount$lambda$10(AccountManagerFragment accountManagerFragment) {
        accountManagerFragment.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit switchAccount$lambda$12(AccountManagerFragment accountManagerFragment) {
        accountManagerFragment.hideLoadingView();
        ToastUtils.showShort("Login successfully", new Object[0]);
        accountManagerFragment.getAccountList();
        if (accountManagerFragment.getChooseAccount()) {
            FragmentActivity activity = accountManagerFragment.getActivity();
            if (activity != null) {
                activity.finish();
            }
            accountManagerFragment.dismiss();
            Context context = accountManagerFragment.getContext();
            if (context != null) {
                MainActivity.INSTANCE.start(context);
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit switchAccount$lambda$13(AccountManagerFragment accountManagerFragment, Throwable error, boolean z) {
        Intrinsics.checkNotNullParameter(error, "error");
        accountManagerFragment.hideLoadingView();
        ToastUtils.showShort(AccountSwitchHelperKt.switchAccountErrorMessage(error), new Object[0]);
        return Unit.INSTANCE;
    }

    private final void initData(View view) {
        TvRecyclerView tvRecyclerView = (TvRecyclerView) view.findViewById(com.movieboxpro.androidtv.R.id.recyclerView);
        this.mRecyclerView = tvRecyclerView;
        CommonBaseAdapter<UserModel.UserData> commonBaseAdapter = new CommonBaseAdapter<UserModel.UserData>(com.movieboxpro.androidtv.R.layout.adapter_account_item, new ArrayList()) { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment.initData.1
            {
                ArrayList<UserModel.UserData> arrayList = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, UserModel.UserData item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                ImageView imageView = (ImageView) holder.getView(com.movieboxpro.androidtv.R.id.ivVip);
                ImageView imageView2 = (ImageView) holder.getView(com.movieboxpro.androidtv.R.id.ivSelect);
                TextView textView = (TextView) holder.getView(com.movieboxpro.androidtv.R.id.tvDate);
                String str = item.uid;
                if (str == null || StringsKt.isBlank(str)) {
                    CommonExtKt.gone(imageView);
                    holder.setText(com.movieboxpro.androidtv.R.id.tvName, "Add New Account");
                    GlideUtils.load(getContext(), com.movieboxpro.androidtv.R.mipmap.ic_add_account, (ImageView) holder.getView(com.movieboxpro.androidtv.R.id.ivAvatar));
                    CommonExtKt.gone(imageView2);
                    CommonExtKt.gone(textView);
                    Unit unit = Unit.INSTANCE;
                    return;
                }
                CommonExtKt.visible(textView);
                holder.setText(com.movieboxpro.androidtv.R.id.tvName, item.username);
                GlideUtils.load(getContext(), item.avatar, (ImageView) holder.getView(com.movieboxpro.androidtv.R.id.ivAvatar), com.movieboxpro.androidtv.R.drawable.ic_default_avatar);
                if (!AccountManagerFragment.this.getChooseAccount() && Intrinsics.areEqual(App.getUserData().uid, item.uid)) {
                    CommonExtKt.visible(imageView2);
                } else {
                    CommonExtKt.gone(imageView2);
                }
                if (item.isvip == 1) {
                    CommonExtKt.visible(imageView);
                    int i = com.movieboxpro.androidtv.R.id.tvDate;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str2 = String.format("Expires: %s", Arrays.copyOf(new Object[]{TimeUtils.formatTime4(item.dead_time * ((long) 1000))}, 1));
                    Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                    holder.setText(i, str2);
                    return;
                }
                CommonExtKt.gone(imageView);
                holder.setText(com.movieboxpro.androidtv.R.id.tvDate, "Not a VIP");
            }
        };
        this.adapter = commonBaseAdapter;
        tvRecyclerView.setAdapter(commonBaseAdapter);
        tvRecyclerView.setSpacingWithMargins(ScreenUtils.dpToPxInt(20.0f), 0);
        AddAccountLiveData.INSTANCE.get().observe(this, new AccountManagerFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountManagerFragment.initData$lambda$14(this.f$0, (Boolean) obj);
            }
        }));
        TextView textView = (TextView) view.findViewById(com.movieboxpro.androidtv.R.id.tvTitle);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(com.movieboxpro.androidtv.R.id.flQuickSwitch);
        TextView textView2 = (TextView) view.findViewById(com.movieboxpro.androidtv.R.id.tvDesc);
        final TextView textView3 = (TextView) view.findViewById(com.movieboxpro.androidtv.R.id.tvQuickSwitch);
        if (getChooseAccount()) {
            Dialog dialog = getDialog();
            if (dialog != null) {
                dialog.setCancelable(false);
            }
            Dialog dialog2 = getDialog();
            if (dialog2 != null) {
                dialog2.setCanceledOnTouchOutside(false);
            }
            textView.setText("Select an Account");
            Intrinsics.checkNotNull(frameLayout);
            CommonExtKt.gone(frameLayout);
            Intrinsics.checkNotNull(textView2);
            CommonExtKt.gone(textView2);
        } else {
            tvRecyclerView.requestFocus();
            textView.setText("Manage Accounts");
        }
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.account.AccountManagerFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AccountManagerFragment.initData$lambda$15(textView3, view2);
            }
        });
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.QUICK_SWITCH_ACCOUNT, true)) {
            textView3.setText("ON");
        } else {
            textView3.setText("OFF");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$14(AccountManagerFragment accountManagerFragment, Boolean bool) {
        accountManagerFragment.getAccountList();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$15(TextView textView, View view) {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.QUICK_SWITCH_ACCOUNT, true)) {
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.QUICK_SWITCH_ACCOUNT, false);
        } else {
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.QUICK_SWITCH_ACCOUNT, true);
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.QUICK_SWITCH_ACCOUNT, true)) {
            textView.setText("ON");
        } else {
            textView.setText("OFF");
        }
    }

    private final void showLoadingView() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD != null) {
            Intrinsics.checkNotNull(kProgressHUD);
            if (kProgressHUD.isShowing()) {
                return;
            }
        }
        KProgressHUD kProgressHUD2 = this.hud;
        if (kProgressHUD2 != null) {
            Intrinsics.checkNotNull(kProgressHUD2);
            if (kProgressHUD2.isShowing()) {
                return;
            }
        }
        this.hud = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f).show();
    }

    private final void hideLoadingView() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD != null) {
            Intrinsics.checkNotNull(kProgressHUD);
            if (kProgressHUD.isShowing()) {
                KProgressHUD kProgressHUD2 = this.hud;
                Intrinsics.checkNotNull(kProgressHUD2);
                kProgressHUD2.dismiss();
            }
        }
    }
}
