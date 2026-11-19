package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.listener.PowerGroupListener;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.ConnectUsbDevice;
import com.movieboxpro.android.event.OtgAccessEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.DownloadUrl;
import com.movieboxpro.android.model.ExternalStorage;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.receiver.UsbConnectReceiver;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.fragment.OtgFragment;
import com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentDownloadVideoQualityBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.owen.tvrecyclerview.widget.V7GridLayoutManager;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: ChooseDownloadQualityFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 ,2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002,-B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0010H\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\"\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\b\u0010!\u001a\u00020\u0010H\u0003J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\fH\u0003J\u0018\u0010$\u001a\u00020\u00102\u0006\u0010%\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\fH\u0002J\b\u0010&\u001a\u00020\u0010H\u0003J\u0016\u0010'\u001a\u00020\u001a2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0(H\u0002J\u0010\u0010)\u001a\u00020\u00102\u0006\u0010*\u001a\u00020+H\u0007R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseDownloadQualityFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentDownloadVideoQualityBinding;", "<init>", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/DownloadUrl;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "deviceAdapter", "Lcom/movieboxpro/android/model/ExternalStorage;", "lastSelectPos", "", "callback", "Lcom/movieboxpro/android/view/dialog/ChooseDownloadQualityFragment$ChooseDownloadQualityListener;", "onStart", "", "onDestroy", "setCallback", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "getUserInfo", "checkChooseDeviceConnect", "", "clickDevice", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "initListener", "requestStoragePermission", "position", "doChooseDownload", "it", "initData", "checkChangeStorage", "", "onOtgAccess", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/OtgAccessEvent;", "Companion", "ChooseDownloadQualityListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseDownloadQualityFragment extends BaseTransparentDialogFragment<FragmentDownloadVideoQualityBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private BaseQuickAdapter<DownloadUrl, BaseViewHolder> adapter;
    private ChooseDownloadQualityListener callback;
    private ExternalStorage clickDevice;
    private BaseQuickAdapter<ExternalStorage, BaseViewHolder> deviceAdapter;
    private int lastSelectPos;

    /* JADX INFO: compiled from: ChooseDownloadQualityFragment.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseDownloadQualityFragment$ChooseDownloadQualityListener;", "", "onChooseDownload", "", "downloadUrl", "Lcom/movieboxpro/android/model/DownloadUrl;", "position", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ChooseDownloadQualityListener {
        void onChooseDownload(DownloadUrl downloadUrl, int position);
    }

    private final void requestStoragePermission(int position) {
    }

    public ChooseDownloadQualityFragment() {
        super(R.layout.fragment_download_video_quality);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (((double) getResources().getDisplayMetrics().widthPixels) * 0.8d);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
        }
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

    public final void setCallback(ChooseDownloadQualityListener callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.callback = callback;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
        initListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getUserInfo() {
        Object objAs = Http.getService().Userinfo2(API.USER.USERINFO, App.getUserData().uid_v2, SystemUtils.getUniqueId(getContext()), BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(UserModel.UserData.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ChooseDownloadQualityFragment.getUserInfo$lambda$1((UserModel.UserData) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getUserInfo$lambda$1(UserModel.UserData userData) throws Throwable {
        App.updateUser(userData);
        return Unit.INSTANCE;
    }

    private final boolean checkChooseDeviceConnect() {
        int i = PrefsUtils.getInstance().getInt(Constant.Prefs.DOWNLOAD_DEVICE_ID, 0);
        Iterator<T> it = UsbConnectReceiver.INSTANCE.getDevices().getList().iterator();
        while (it.hasNext()) {
            if (((UsbDevice) it.next()).getProductId() == i) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == -1) {
            OtgFragment.INSTANCE.setOtgRoot(data != null ? data.getData() : null);
            ExternalStorage externalStorage = this.clickDevice;
            if (externalStorage != null) {
                PrefsUtils.getInstance().putString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, externalStorage.getPath());
                App.getDB().connectUsbDeviceDao().insert(new ConnectUsbDevice(externalStorage.getPath(), String.valueOf(data != null ? data.getData() : null), externalStorage.getDesc()));
                BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter = this.deviceAdapter;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                    baseQuickAdapter = null;
                }
                int i = 0;
                for (Object obj : baseQuickAdapter.getData()) {
                    int i2 = i + 1;
                    if (i < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    ExternalStorage externalStorage2 = (ExternalStorage) obj;
                    if (Intrinsics.areEqual(externalStorage2.getPath(), externalStorage.getPath())) {
                        externalStorage2.setSelect(true);
                        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter2 = this.deviceAdapter;
                        if (baseQuickAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                            baseQuickAdapter2 = null;
                        }
                        baseQuickAdapter2.getItem(this.lastSelectPos).setSelect(false);
                        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter3 = this.deviceAdapter;
                        if (baseQuickAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                            baseQuickAdapter3 = null;
                        }
                        baseQuickAdapter3.notifyItemChanged(i);
                        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter4 = this.deviceAdapter;
                        if (baseQuickAdapter4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                            baseQuickAdapter4 = null;
                        }
                        baseQuickAdapter4.notifyItemChanged(this.lastSelectPos);
                        this.lastSelectPos = i;
                    }
                    i = i2;
                }
            }
        }
    }

    private final void initListener() {
        BaseQuickAdapter<DownloadUrl, BaseViewHolder> baseQuickAdapter = this.adapter;
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                ChooseDownloadQualityFragment.initListener$lambda$9(this.f$0, baseQuickAdapter3, view, i);
            }
        });
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter3 = this.deviceAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter4, View view, int i) {
                ChooseDownloadQualityFragment.initListener$lambda$11(this.f$0, baseQuickAdapter4, view, i);
            }
        });
        final int screenHeight = ScreenUtils.getScreenHeight();
        getBinding().linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, (int) (screenHeight * 0.8f)));
        getBinding().linearLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                ChooseDownloadQualityFragment.initListener$lambda$12(screenHeight, this, view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$9(final ChooseDownloadQualityFragment chooseDownloadQualityFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        BaseQuickAdapter<DownloadUrl, BaseViewHolder> baseQuickAdapter2 = chooseDownloadQualityFragment.adapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter2 = null;
        }
        DownloadUrl item = baseQuickAdapter2.getItem(i);
        Integer vip_only = item.getVip_only();
        if (vip_only != null && vip_only.intValue() == 1) {
            if (App.getUserData().getIsvip() == 1) {
                chooseDownloadQualityFragment.doChooseDownload(item, i);
                return;
            }
            Context context = chooseDownloadQualityFragment.getContext();
            if (context != null) {
                new VipExpireDialog.Builder(context).setCancelable(false).setTitle("You're not VIP yet").setContent("").setNegativeText("Close").setPositiveText("Subscribe").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment$$ExternalSyntheticLambda0
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        ChooseDownloadQualityFragment.initListener$lambda$9$lambda$8$lambda$7$lambda$5(this.f$0);
                    }
                }).setCancelListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment$$ExternalSyntheticLambda1
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        this.f$0.getUserInfo();
                    }
                }).create().show();
                return;
            }
            return;
        }
        chooseDownloadQualityFragment.doChooseDownload(item, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$9$lambda$8$lambda$7$lambda$5(ChooseDownloadQualityFragment chooseDownloadQualityFragment) {
        new VipChargeGuideDialog().show(chooseDownloadQualityFragment.getChildFragmentManager(), "VipChargeGuideDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11(final ChooseDownloadQualityFragment chooseDownloadQualityFragment, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        if (chooseDownloadQualityFragment.lastSelectPos == i) {
            return;
        }
        ConfirmSwitchDownloadPathDialog confirmSwitchDownloadPathDialog = new ConfirmSwitchDownloadPathDialog();
        confirmSwitchDownloadPathDialog.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment$$ExternalSyntheticLambda2
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                ChooseDownloadQualityFragment.initListener$lambda$11$lambda$10(this.f$0, i);
            }
        });
        confirmSwitchDownloadPathDialog.show(chooseDownloadQualityFragment.getChildFragmentManager(), "ConfirmSwitchDownloadPathDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11$lambda$10(ChooseDownloadQualityFragment chooseDownloadQualityFragment, int i) {
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter = chooseDownloadQualityFragment.deviceAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
            baseQuickAdapter = null;
        }
        ExternalStorage item = baseQuickAdapter.getItem(i);
        if (new File(item.getPath()).exists() && new File(item.getPath()).canWrite()) {
            BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter2 = chooseDownloadQualityFragment.deviceAdapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                baseQuickAdapter2 = null;
            }
            ExternalStorage item2 = baseQuickAdapter2.getItem(i);
            item2.setSelect(true);
            BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter3 = chooseDownloadQualityFragment.deviceAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                baseQuickAdapter3 = null;
            }
            baseQuickAdapter3.notifyItemChanged(i);
            BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter4 = chooseDownloadQualityFragment.deviceAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                baseQuickAdapter4 = null;
            }
            baseQuickAdapter4.getItem(chooseDownloadQualityFragment.lastSelectPos).setSelect(false);
            BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter5 = chooseDownloadQualityFragment.deviceAdapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                baseQuickAdapter5 = null;
            }
            baseQuickAdapter5.notifyItemChanged(chooseDownloadQualityFragment.lastSelectPos);
            chooseDownloadQualityFragment.lastSelectPos = i;
            OtgFragment.INSTANCE.setOtgRoot(null);
            PrefsUtils.getInstance().putString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, item2.getPath());
            return;
        }
        ToastUtils.showShort("Device is unavailable", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$12(int i, ChooseDownloadQualityFragment chooseDownloadQualityFragment, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        float f = i * 0.9f;
        if (view.getHeight() > f) {
            chooseDownloadQualityFragment.getBinding().linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, (int) f));
        }
    }

    private final void doChooseDownload(DownloadUrl it, int position) {
        ChooseDownloadQualityListener chooseDownloadQualityListener = this.callback;
        if (chooseDownloadQualityListener != null) {
            chooseDownloadQualityListener.onChooseDownload(it, position);
        }
        dismiss();
    }

    private final void initData() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            Observable<Boolean> observableRequest = new RxPermissions(activity).request("android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE");
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return ChooseDownloadQualityFragment.initData$lambda$15$lambda$13((Boolean) obj);
                }
            };
            observableRequest.subscribe(new Consumer() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment$$ExternalSyntheticLambda8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    function1.invoke(obj);
                }
            });
        }
        getBinding().tvPath.setText("Saved in:\nAndroid/data/com.movieboxpro.androidtv/files/Download");
        getBinding().tvNote.setText("Tips:\nThe usb device should be formatted as exFAT or NTFS");
        Bundle arguments = getArguments();
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter = null;
        ArrayList parcelableArrayList = arguments != null ? arguments.getParcelableArrayList("list") : null;
        Intrinsics.checkNotNull(parcelableArrayList, "null cannot be cast to non-null type java.util.ArrayList<com.movieboxpro.android.model.DownloadUrl>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : parcelableArrayList) {
            String filename = ((DownloadUrl) obj).getFilename();
            Object obj2 = linkedHashMap.get(filename);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(filename, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.addAll((Collection) ((Map.Entry) it.next()).getValue());
        }
        this.adapter = new BaseQuickAdapter<DownloadUrl, BaseViewHolder>(arrayList, R.layout.adapter_definition_item) { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment.initData.3
            {
                super(i, arrayList);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Code duplicated, block: B:42:0x0095  */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, DownloadUrl item) {
                String lowerCase;
                int i;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                ImageView imageView = (ImageView) holder.getView(R.id.ivCode);
                if (item.getH265() == 1) {
                    imageView.setImageResource(com.dueeeke.videocontroller.R.mipmap.ic_choose_fromat);
                } else {
                    imageView.setImageResource(com.dueeeke.videocontroller.R.mipmap.ic_choose_fromat2);
                }
                String real_quality = item.getReal_quality();
                if (real_quality != null) {
                    lowerCase = real_quality.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
                } else {
                    lowerCase = null;
                }
                if (lowerCase != null) {
                    switch (lowerCase) {
                        case "4k":
                            i = com.dueeeke.videocontroller.R.mipmap.ic_choose_4k;
                            break;
                        case "8k":
                            i = R.mipmap.ic_choose_8k;
                            break;
                        case "org":
                            i = R.mipmap.ic_origin_rate;
                            break;
                        case "360p":
                            i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                            break;
                        case "720p":
                            i = com.dueeeke.videocontroller.R.mipmap.ic_choose_hd;
                            break;
                        case "auto":
                            i = R.mipmap.ic_choose_auto;
                            break;
                        case "1080p":
                            i = com.dueeeke.videocontroller.R.mipmap.ic_choose_fullhd;
                            break;
                        default:
                            i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                            break;
                    }
                } else {
                    i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                }
                ImageView imageView2 = (ImageView) holder.getView(R.id.ivOrg);
                Integer original = item.getOriginal();
                if (original != null && original.intValue() == 1) {
                    CommonExtKt.visible(imageView2);
                } else {
                    CommonExtKt.gone(imageView2);
                }
                ((ImageView) holder.getView(R.id.ivDefinition)).setImageResource(i);
                TextView textView = (TextView) holder.getView(R.id.tv_rate);
                LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.ll_rate);
                String bitstream = item.getBitstream();
                if (bitstream == null || bitstream.length() == 0) {
                    CommonExtKt.gone(textView);
                    CommonExtKt.gone(linearLayout);
                } else {
                    CommonExtKt.visible(textView);
                    CommonExtKt.visible(linearLayout);
                    textView.setText(item.getBitstream());
                }
                ImageView imageView3 = (ImageView) holder.getView(R.id.ivVip);
                Integer vip_only = item.getVip_only();
                if (vip_only != null && vip_only.intValue() == 1) {
                    CommonExtKt.visible(imageView3);
                } else {
                    CommonExtKt.gone(imageView3);
                }
                ImageView imageView4 = (ImageView) holder.getView(R.id.ivHdr);
                Integer hdr = item.getHdr();
                if (hdr != null && hdr.intValue() == 1) {
                    CommonExtKt.visible(imageView4);
                } else {
                    CommonExtKt.gone(imageView4);
                }
                ImageView imageView5 = (ImageView) holder.getView(R.id.ivColorBit);
                Integer colorbit = item.getColorbit();
                if (colorbit != null && colorbit.intValue() == 10) {
                    CommonExtKt.visible(imageView5);
                    imageView5.setImageResource(R.mipmap.ic_color_bit_10);
                } else if (colorbit != null && colorbit.intValue() == 12) {
                    CommonExtKt.visible(imageView5);
                    imageView5.setImageResource(R.mipmap.ic_color_bit_12);
                } else {
                    CommonExtKt.gone(imageView5);
                }
                holder.setText(R.id.tv_size, item.getSize());
                CommonExtKt.gone((ImageView) holder.getView(R.id.iv_selected));
            }
        };
        getBinding().recyclerView.setSelectedItemAtCentered(true);
        TvRecyclerView tvRecyclerView = getBinding().recyclerView;
        BaseQuickAdapter<DownloadUrl, BaseViewHolder> baseQuickAdapter2 = this.adapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter2 = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter2);
        PowerfulStickyDecoration powerfulStickyDecorationBuild = PowerfulStickyDecoration.Builder.init(new PowerGroupListener() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment$initData$decoration$1
            @Override // com.gavin.com.library.listener.PowerGroupListener
            public boolean needDrawDecoration(int position) {
                return true;
            }

            @Override // com.gavin.com.library.listener.GroupListener
            public String getGroupName(int position) {
                String filename2;
                BaseQuickAdapter baseQuickAdapter3 = this.this$0.adapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter3 = null;
                }
                DownloadUrl downloadUrl = (DownloadUrl) baseQuickAdapter3.getItemOrNull(position);
                return (downloadUrl == null || (filename2 = downloadUrl.getFilename()) == null) ? "" : filename2;
            }

            @Override // com.gavin.com.library.listener.PowerGroupListener
            public View getGroupView(int position) {
                Long dateline;
                BaseQuickAdapter baseQuickAdapter3 = null;
                View viewInflate = LayoutInflater.from(this.this$0.getContext()).inflate(R.layout.video_quality_title, (ViewGroup) null, false);
                TextView textView = (TextView) viewInflate.findViewById(R.id.tvName);
                BaseQuickAdapter baseQuickAdapter4 = this.this$0.adapter;
                if (baseQuickAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter4 = null;
                }
                DownloadUrl downloadUrl = (DownloadUrl) baseQuickAdapter4.getItemOrNull(position);
                textView.setText(downloadUrl != null ? downloadUrl.getFilename() : null);
                TextView textView2 = (TextView) viewInflate.findViewById(R.id.tvTime);
                BaseQuickAdapter baseQuickAdapter5 = this.this$0.adapter;
                if (baseQuickAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    baseQuickAdapter3 = baseQuickAdapter5;
                }
                DownloadUrl downloadUrl2 = (DownloadUrl) baseQuickAdapter3.getItemOrNull(position);
                textView2.setText(TimeUtils.formatTime4(((downloadUrl2 == null || (dateline = downloadUrl2.getDateline()) == null) ? 0L : dateline.longValue()) * ((long) 1000)));
                Intrinsics.checkNotNull(viewInflate);
                return viewInflate;
            }
        }).setGroupBackground(CommonExtKt.colorInt(R.color.color_main_back)).setDivideHeight(0).setGroupHeight((int) ScreenUtils.dpToPx(70.0f)).setCacheEnable(true).setSticky(false).build();
        V7GridLayoutManager v7GridLayoutManager = new V7GridLayoutManager(getContext(), 1);
        if (powerfulStickyDecorationBuild != null) {
            powerfulStickyDecorationBuild.resetSpan(getBinding().recyclerView, v7GridLayoutManager);
        }
        getBinding().recyclerView.setLayoutManager(v7GridLayoutManager);
        TvRecyclerView tvRecyclerView2 = getBinding().recyclerView;
        Intrinsics.checkNotNull(powerfulStickyDecorationBuild);
        tvRecyclerView2.addItemDecoration(powerfulStickyDecorationBuild);
        this.deviceAdapter = new BaseQuickAdapter<ExternalStorage, BaseViewHolder>(R.layout.adapter_usb_device) { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment.initData.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, ExternalStorage item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                String name = item.getName();
                if (name == null || StringsKt.isBlank(name)) {
                    holder.setText(R.id.tvDeviceName, item.getDesc());
                } else {
                    holder.setText(R.id.tvDeviceName, item.getDesc() + " (" + item.getName() + ")");
                }
                holder.setText(R.id.tvSpace, item.getSpaceDesc());
                ImageView imageView = (ImageView) holder.getView(R.id.ivSelect);
                if (item.isSelect()) {
                    CommonExtKt.visible(imageView);
                } else {
                    CommonExtKt.gone(imageView);
                }
            }
        };
        TvRecyclerView tvRecyclerView3 = getBinding().rvDevice;
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter3 = this.deviceAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter3;
        }
        tvRecyclerView3.setAdapter(baseQuickAdapter);
        UsbConnectReceiver.INSTANCE.getStorages().observe(this, new ChooseDownloadQualityFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadQualityFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj3) {
                return ChooseDownloadQualityFragment.initData$lambda$20(this.f$0, (List) obj3);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$15$lambda$13(Boolean bool) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$20(ChooseDownloadQualityFragment chooseDownloadQualityFragment, List list) {
        int i;
        Intrinsics.checkNotNull(list);
        List list2 = list;
        Iterator it = list2.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            ((ExternalStorage) it.next()).setSelect(false);
        }
        List list3 = list;
        if (!list3.isEmpty()) {
            TvRecyclerView rvDevice = chooseDownloadQualityFragment.getBinding().rvDevice;
            Intrinsics.checkNotNullExpressionValue(rvDevice, "rvDevice");
            CommonExtKt.visible(rvDevice);
        } else {
            TvRecyclerView rvDevice2 = chooseDownloadQualityFragment.getBinding().rvDevice;
            Intrinsics.checkNotNullExpressionValue(rvDevice2, "rvDevice");
            CommonExtKt.gone(rvDevice2);
        }
        String path = Uri.parse(PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH())).getPath();
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter = null;
        if (path != null && !StringsKt.startsWith$default(path, "/storage/emulated/0", false, 2, (Object) null)) {
            if (!new File(path == null ? "" : path).exists()) {
                ToastUtils.showShort("Last selected usb device is unavailable", new Object[0]);
                path = UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH();
                PrefsUtils.getInstance().putString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, UsbConnectReceiver.INSTANCE.getINTERNAL_DOWNLOAD_PATH());
            }
        }
        for (Object obj : list2) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            ExternalStorage externalStorage = (ExternalStorage) obj;
            if (StringsKt.equals(externalStorage.getPath(), path, true)) {
                externalStorage.setSelect(true);
                chooseDownloadQualityFragment.lastSelectPos = i;
            }
            i = i2;
        }
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter2 = chooseDownloadQualityFragment.deviceAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter2;
        }
        baseQuickAdapter.setList(list3);
        return Unit.INSTANCE;
    }

    private final boolean checkChangeStorage(List<ExternalStorage> it) {
        try {
            for (IndexedValue indexedValue : CollectionsKt.withIndex(it)) {
                String path = ((ExternalStorage) indexedValue.getValue()).getPath();
                BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter = this.deviceAdapter;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
                    baseQuickAdapter = null;
                }
                if (!Intrinsics.areEqual(path, baseQuickAdapter.getData().get(indexedValue.getIndex()).getPath())) {
                    return false;
                }
            }
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onOtgAccess(OtgAccessEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter = this.deviceAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
            baseQuickAdapter = null;
        }
        int i = 0;
        for (Object obj : baseQuickAdapter.getData()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            i = i2;
        }
    }

    /* JADX INFO: compiled from: ChooseDownloadQualityFragment.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseDownloadQualityFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChooseDownloadQualityFragment;", "list", "", "Lcom/movieboxpro/android/model/DownloadUrl;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChooseDownloadQualityFragment newInstance(List<DownloadUrl> list) {
            Intrinsics.checkNotNullParameter(list, "list");
            ChooseDownloadQualityFragment chooseDownloadQualityFragment = new ChooseDownloadQualityFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("list", new ArrayList<>(list));
            chooseDownloadQualityFragment.setArguments(bundle);
            return chooseDownloadQualityFragment;
        }
    }
}
