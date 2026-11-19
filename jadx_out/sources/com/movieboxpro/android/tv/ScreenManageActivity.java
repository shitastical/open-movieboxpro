package com.movieboxpro.android.tv;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.mediarouter.media.MediaRouterJellybean;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.internal.ViewUtils;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.BaseSimpleActivity;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog;
import com.movieboxpro.android.view.widget.LinearItemDecoration;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityScreenManageBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScreenManageActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007H\u0002J\b\u0010\u000f\u001a\u00020\fH\u0016J\b\u0010\u0010\u001a\u00020\fH\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/tv/ScreenManageActivity;", "Lcom/movieboxpro/android/base/BaseSimpleActivity;", "Lcom/movieboxpro/androidtv/databinding/ActivityScreenManageBinding;", "<init>", "()V", "adapter", "Lcom/movieboxpro/android/base/CommonBaseAdapter;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "memberAdapter", "getLayoutResId", "", "initListener", "", "deleteDevice", "item", "initData", "initView", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ScreenManageActivity extends BaseSimpleActivity<ActivityScreenManageBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CommonBaseAdapter<DeviceModelResponse.DeviceModel> adapter;
    private CommonBaseAdapter<DeviceModelResponse.DeviceModel> memberAdapter;

    @JvmStatic
    public static final void start(Activity activity, ArrayList<DeviceModelResponse.DeviceModel> arrayList, int i, String str, int i2, String str2) {
        INSTANCE.start(activity, arrayList, i, str, i2, str2);
    }

    @JvmStatic
    public static final void start(Activity activity, ArrayList<DeviceModelResponse.DeviceModel> arrayList, int i, String str, int i2, String str2, int i3) {
        INSTANCE.start(activity, arrayList, i, str, i2, str2, i3);
    }

    @JvmStatic
    public static final void start(Activity activity, ArrayList<DeviceModelResponse.DeviceModel> arrayList, int i, String str, int i2, String str2, int i3, int i4) {
        INSTANCE.start(activity, arrayList, i, str, i2, str2, i3, i4);
    }

    @JvmStatic
    public static final void start(Activity activity, ArrayList<DeviceModelResponse.DeviceModel> arrayList, int i, String str, int i2, String str2, int i3, int i4, boolean z) {
        INSTANCE.start(activity, arrayList, i, str, i2, str2, i3, i4, z);
    }

    @JvmStatic
    public static final void start(Activity activity, ArrayList<DeviceModelResponse.DeviceModel> arrayList, int i, String str, int i2, String str2, int i3, int i4, boolean z, boolean z2) {
        INSTANCE.start(activity, arrayList, i, str, i2, str2, i3, i4, z, z2);
    }

    @JvmStatic
    public static final void start(Fragment fragment, ArrayList<DeviceModelResponse.DeviceModel> arrayList, int i, String str, int i2, String str2) {
        INSTANCE.start(fragment, arrayList, i, str, i2, str2);
    }

    @JvmStatic
    public static final void start(Fragment fragment, ArrayList<DeviceModelResponse.DeviceModel> arrayList, int i, String str, int i2, String str2, int i3) {
        INSTANCE.start(fragment, arrayList, i, str, i2, str2, i3);
    }

    @JvmStatic
    public static final void start(Fragment fragment, ArrayList<DeviceModelResponse.DeviceModel> arrayList, int i, String str, int i2, String str2, int i3, int i4) {
        INSTANCE.start(fragment, arrayList, i, str, i2, str2, i3, i4);
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public int getLayoutResId() {
        return R.layout.activity_screen_manage;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initListener() {
        CommonBaseAdapter<DeviceModelResponse.DeviceModel> commonBaseAdapter = this.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        commonBaseAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.tv.ScreenManageActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ScreenManageActivity.initListener$lambda$0(this.f$0, baseQuickAdapter, view, i);
            }
        });
        getBinding().tvUpgrade.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.ScreenManageActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScreenManageActivity.initListener$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$0(ScreenManageActivity screenManageActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        CommonBaseAdapter<DeviceModelResponse.DeviceModel> commonBaseAdapter = screenManageActivity.adapter;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        screenManageActivity.deleteDevice(commonBaseAdapter.getItem(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(ScreenManageActivity screenManageActivity, View view) {
        new VipChargeGuideDialog().show(screenManageActivity.getSupportFragmentManager(), "VipChargeGuideDialog");
    }

    private final void deleteDevice(DeviceModelResponse.DeviceModel item) {
        Observable<String> observableDeletePlayingStatus = Http.getService().deletePlayingStatus(API.Common.DELETE_PLAYING_STATUS, item != null ? item.getOpen_udid() : null, App.getUserData().uid_v2);
        Intrinsics.checkNotNullExpressionValue(observableDeletePlayingStatus, "deletePlayingStatus(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(observableDeletePlayingStatus, this), new Function1() { // from class: com.movieboxpro.android.tv.ScreenManageActivity$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ScreenManageActivity.deleteDevice$lambda$2(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.tv.ScreenManageActivity$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ScreenManageActivity.deleteDevice$lambda$3(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.tv.ScreenManageActivity$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ScreenManageActivity.deleteDevice$lambda$4(this.f$0, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDevice$lambda$3(ScreenManageActivity screenManageActivity, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        screenManageActivity.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDevice$lambda$4(ScreenManageActivity screenManageActivity, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        screenManageActivity.hideLoadingView();
        Intent intent = new Intent();
        Bundle extras = screenManageActivity.getIntent().getExtras();
        if (extras == null) {
            extras = new Bundle();
        }
        intent.putExtras(extras);
        screenManageActivity.setResult(-1, intent);
        screenManageActivity.finish();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteDevice$lambda$2(ScreenManageActivity screenManageActivity, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ToastUtils.showShort("Stop failed:" + it.getMessage(), new Object[0]);
        screenManageActivity.hideLoadingView();
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initData() {
        String username;
        Serializable serializableExtra = getIntent().getSerializableExtra("data");
        Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type java.util.ArrayList<com.movieboxpro.android.model.DeviceModelResponse.DeviceModel>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : (ArrayList) serializableExtra) {
            String uid = ((DeviceModelResponse.DeviceModel) obj).getUid();
            Object obj2 = linkedHashMap.get(uid);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(uid, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList arrayList = (List) linkedHashMap.get(App.getUserData().uid);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        if (arrayList.size() <= 1) {
            getBinding().tvMineScreenNum.setText("My Screen (" + arrayList.size() + ")");
        } else {
            getBinding().tvMineScreenNum.setText("My Screens (" + arrayList.size() + ")");
        }
        this.adapter = new CommonBaseAdapter<DeviceModelResponse.DeviceModel>(R.layout.adapter_online_screen, new ArrayList(arrayList)) { // from class: com.movieboxpro.android.tv.ScreenManageActivity.initData.1
            {
                ArrayList<DeviceModelResponse.DeviceModel> arrayList2 = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, DeviceModelResponse.DeviceModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.tvDeviceName, item.getDevice_name() + " " + item.getDevice_model());
            }
        };
        getBinding().rvMyScreen.setSpacingWithMargins(DensityUtils.dp2px(App.getContext(), 20.0f), 0);
        TvRecyclerView tvRecyclerView = getBinding().rvMyScreen;
        CommonBaseAdapter<DeviceModelResponse.DeviceModel> commonBaseAdapter = this.adapter;
        CommonBaseAdapter<DeviceModelResponse.DeviceModel> commonBaseAdapter2 = null;
        if (commonBaseAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            commonBaseAdapter = null;
        }
        tvRecyclerView.setAdapter(commonBaseAdapter);
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            if (!Intrinsics.areEqual(entry.getKey(), App.getUserData().uid)) {
                DeviceModelResponse.DeviceModel deviceModel = new DeviceModelResponse.DeviceModel();
                DeviceModelResponse.DeviceModel deviceModel2 = (DeviceModelResponse.DeviceModel) CollectionsKt.firstOrNull((List) entry.getValue());
                if (deviceModel2 == null || (username = deviceModel2.getUsername()) == null) {
                    username = "";
                }
                deviceModel.setUsername(username);
                deviceModel.setScreenNum(((List) entry.getValue()).size());
                arrayList2.add(deviceModel);
            }
        }
        this.memberAdapter = new CommonBaseAdapter<DeviceModelResponse.DeviceModel>(arrayList2, R.layout.adapter_screen_member) { // from class: com.movieboxpro.android.tv.ScreenManageActivity.initData.3
            {
                super(i, arrayList2);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, DeviceModelResponse.DeviceModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.tvName, item.getUsername());
                if (item.getScreenNum() <= 1) {
                    helper.setText(R.id.tvScreenNum, item.getScreenNum() + " screen playing");
                    return;
                }
                helper.setText(R.id.tvScreenNum, item.getScreenNum() + " screens playing");
            }
        };
        getBinding().rvMembers.setLayoutManager(new LinearLayoutManager(this, 0, false));
        getBinding().rvMembers.addItemDecoration(new LinearItemDecoration(0, 20));
        RecyclerView recyclerView = getBinding().rvMembers;
        CommonBaseAdapter<DeviceModelResponse.DeviceModel> commonBaseAdapter3 = this.memberAdapter;
        if (commonBaseAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("memberAdapter");
        } else {
            commonBaseAdapter2 = commonBaseAdapter3;
        }
        recyclerView.setAdapter(commonBaseAdapter2);
        if (arrayList2.isEmpty()) {
            RecyclerView rvMembers = getBinding().rvMembers;
            Intrinsics.checkNotNullExpressionValue(rvMembers, "rvMembers");
            CommonExtKt.gone(rvMembers);
            getBinding().rvMembers.setFocusable(false);
        }
    }

    @Override // com.movieboxpro.android.base.BaseSimpleActivity
    public void initView() {
        if (App.getUserData().getFamily() != 0) {
            getBinding().tvFamilyName.setText(App.getUserData().getMaster_username() + "'s Family");
            TextView tvUpgrade = getBinding().tvUpgrade;
            Intrinsics.checkNotNullExpressionValue(tvUpgrade, "tvUpgrade");
            CommonExtKt.gone(tvUpgrade);
        } else {
            TextView tvUpgrade2 = getBinding().tvUpgrade;
            Intrinsics.checkNotNullExpressionValue(tvUpgrade2, "tvUpgrade");
            CommonExtKt.visible(tvUpgrade2);
            getBinding().tvFamilyName.setText("Maximum Playing Screen!");
        }
        getBinding().tvErrMsg.setText(getIntent().getStringExtra("errMsg"));
    }

    /* JADX INFO: compiled from: ScreenManageActivity.kt */
    @Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J`\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\rH\u0007Jt\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\r2\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0017H\u0007J@\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0019\u001a\u00020\u001aJ@\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00152\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0019\u001a\u00020\u001aJP\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u00152\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/tv/ScreenManageActivity$Companion;", "", "<init>", "()V", "start", "", "fragment", "Landroidx/fragment/app/Fragment;", "list", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", "requestCode", "", "errMsg", "", "boxType", "id", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "activity", "Landroid/app/Activity;", "isNext", "", "isContinue", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final void start(Activity activity, ArrayList<DeviceModelResponse.DeviceModel> list, int i, String str, int i2, String str2) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(list, "list");
            start$default(this, activity, list, i, str, i2, str2, 0, 0, false, false, 960, null);
        }

        @JvmStatic
        public final void start(Activity activity, ArrayList<DeviceModelResponse.DeviceModel> list, int i, String str, int i2, String str2, int i3) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(list, "list");
            start$default(this, activity, list, i, str, i2, str2, i3, 0, false, false, MediaRouterJellybean.DEVICE_OUT_BLUETOOTH, null);
        }

        @JvmStatic
        public final void start(Activity activity, ArrayList<DeviceModelResponse.DeviceModel> list, int i, String str, int i2, String str2, int i3, int i4) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(list, "list");
            start$default(this, activity, list, i, str, i2, str2, i3, i4, false, false, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
        }

        @JvmStatic
        public final void start(Activity activity, ArrayList<DeviceModelResponse.DeviceModel> list, int i, String str, int i2, String str2, int i3, int i4, boolean z) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(list, "list");
            start$default(this, activity, list, i, str, i2, str2, i3, i4, z, false, 512, null);
        }

        @JvmStatic
        public final void start(Fragment fragment, ArrayList<DeviceModelResponse.DeviceModel> list, int i, String str, int i2, String str2) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intrinsics.checkNotNullParameter(list, "list");
            start$default(this, fragment, list, i, str, i2, str2, 0, 0, 192, null);
        }

        @JvmStatic
        public final void start(Fragment fragment, ArrayList<DeviceModelResponse.DeviceModel> list, int i, String str, int i2, String str2, int i3) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intrinsics.checkNotNullParameter(list, "list");
            start$default(this, fragment, list, i, str, i2, str2, i3, 0, 128, null);
        }

        private Companion() {
        }

        public static /* synthetic */ void start$default(Companion companion, Fragment fragment, ArrayList arrayList, int i, String str, int i2, String str2, int i3, int i4, int i5, Object obj) {
            companion.start(fragment, (ArrayList<DeviceModelResponse.DeviceModel>) arrayList, i, str, i2, str2, (i5 & 64) != 0 ? 0 : i3, (i5 & 128) != 0 ? 0 : i4);
        }

        @JvmStatic
        public final void start(Fragment fragment, ArrayList<DeviceModelResponse.DeviceModel> list, int requestCode, String errMsg, int boxType, String id, int season, int episode) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intrinsics.checkNotNullParameter(list, "list");
            Intent intent = new Intent(fragment.getContext(), (Class<?>) ScreenManageActivity.class);
            intent.putExtra("data", list);
            intent.putExtra("errMsg", errMsg);
            intent.putExtra("boxType", boxType);
            intent.putExtra("id", id);
            intent.putExtra(DealWatchNextService.SEASON, season);
            intent.putExtra(DealWatchNextService.EPISODE, episode);
            fragment.startActivityForResult(intent, requestCode);
        }

        public static /* synthetic */ void start$default(Companion companion, Activity activity, ArrayList arrayList, int i, String str, int i2, String str2, int i3, int i4, boolean z, boolean z2, int i5, Object obj) {
            companion.start(activity, arrayList, i, str, i2, str2, (i5 & 64) != 0 ? 0 : i3, (i5 & 128) != 0 ? 0 : i4, (i5 & 256) != 0 ? false : z, (i5 & 512) != 0 ? false : z2);
        }

        @JvmStatic
        public final void start(Activity activity, ArrayList<DeviceModelResponse.DeviceModel> list, int requestCode, String errMsg, int boxType, String id, int season, int episode, boolean isNext, boolean isContinue) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(list, "list");
            Intent intent = new Intent(activity, (Class<?>) ScreenManageActivity.class);
            intent.putExtra("data", list);
            intent.putExtra("errMsg", errMsg);
            intent.putExtra("boxType", boxType);
            intent.putExtra("id", id);
            intent.putExtra(DealWatchNextService.SEASON, season);
            intent.putExtra(DealWatchNextService.EPISODE, episode);
            intent.putExtra("isNext", isNext);
            intent.putExtra("isContinue", isContinue);
            activity.startActivityForResult(intent, requestCode);
        }

        public final void start(Fragment fragment, ArrayList<DeviceModelResponse.DeviceModel> list, int requestCode, String errMsg, MovieDetail movieDetail) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(movieDetail, "movieDetail");
            Intent intent = new Intent(fragment.getContext(), (Class<?>) ScreenManageActivity.class);
            intent.putExtra("data", list);
            intent.putExtra("detail", movieDetail);
            intent.putExtra("errMsg", errMsg);
            fragment.startActivityForResult(intent, requestCode);
        }

        public final void start(Activity fragment, ArrayList<DeviceModelResponse.DeviceModel> list, int requestCode, String errMsg, MovieDetail movieDetail) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(movieDetail, "movieDetail");
            Intent intent = new Intent(fragment, (Class<?>) ScreenManageActivity.class);
            intent.putExtra("data", list);
            intent.putExtra("detail", movieDetail);
            intent.putExtra("errMsg", errMsg);
            fragment.startActivityForResult(intent, requestCode);
        }

        public final void start(Activity activity, ArrayList<DeviceModelResponse.DeviceModel> list, int requestCode, String errMsg, TvDetail tvDetail, int season, int episode) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(list, "list");
            Intrinsics.checkNotNullParameter(tvDetail, "tvDetail");
            Intent intent = new Intent(activity, (Class<?>) ScreenManageActivity.class);
            intent.putExtra("data", list);
            intent.putExtra("detail", tvDetail);
            intent.putExtra("errMsg", errMsg);
            intent.putExtra(DealWatchNextService.SEASON, season);
            intent.putExtra(DealWatchNextService.EPISODE, episode);
            activity.startActivityForResult(intent, requestCode);
        }
    }
}
