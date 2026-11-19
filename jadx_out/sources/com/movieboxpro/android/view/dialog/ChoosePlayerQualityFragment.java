package com.movieboxpro.android.view.dialog;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.gavin.com.library.PowerfulStickyDecoration;
import com.gavin.com.library.listener.PowerGroupListener;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentDownloadVideoQualityBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.owen.tvrecyclerview.widget.V7GridLayoutManager;
import com.uber.autodispose.ObservableSubscribeProxy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChoosePlayerQualityFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00162\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u0016\u0017B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nJ\u001a\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010\u0013\u001a\u00020\fH\u0002J\b\u0010\u0014\u001a\u00020\fH\u0002J\b\u0010\u0015\u001a\u00020\fH\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChoosePlayerQualityFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/FragmentDownloadVideoQualityBinding;", "<init>", "()V", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "callback", "Lcom/movieboxpro/android/view/dialog/ChoosePlayerQualityFragment$ChooseDownloadQualityListener;", "onStart", "", "setCallback", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "getUserInfo", "initListener", "initData", "Companion", "ChooseDownloadQualityListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChoosePlayerQualityFragment extends BaseTransparentDialogFragment<FragmentDownloadVideoQualityBinding> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private BaseQuickAdapter<BaseMediaModel.DownloadFile, BaseViewHolder> adapter;
    private ChooseDownloadQualityListener callback;

    /* JADX INFO: compiled from: ChoosePlayerQualityFragment.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChoosePlayerQualityFragment$ChooseDownloadQualityListener;", "", "onChooseDownload", "", "downloadUrl", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "position", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ChooseDownloadQualityListener {
        void onChooseDownload(BaseMediaModel.DownloadFile downloadUrl, int position);
    }

    public ChoosePlayerQualityFragment() {
        super(R.layout.fragment_download_video_quality);
    }

    @Override // com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.width = (int) (((double) getResources().getDisplayMetrics().widthPixels) * 0.5d);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.5f;
            window.setAttributes(attributes);
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
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ChoosePlayerQualityFragment.getUserInfo$lambda$1((UserModel.UserData) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getUserInfo$lambda$1(UserModel.UserData userData) throws Throwable {
        App.updateUser(userData);
        return Unit.INSTANCE;
    }

    private final void initListener() {
        BaseQuickAdapter<BaseMediaModel.DownloadFile, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                ChoosePlayerQualityFragment.initListener$lambda$6(this.f$0, baseQuickAdapter2, view, i);
            }
        });
        final int screenHeight = ScreenUtils.getScreenHeight();
        getBinding().linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, (int) (screenHeight * 0.8f)));
        getBinding().linearLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnLayoutChangeListener
            public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                ChoosePlayerQualityFragment.initListener$lambda$7(screenHeight, this, view, i, i2, i3, i4, i5, i6, i7, i8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6(final ChoosePlayerQualityFragment choosePlayerQualityFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        BaseQuickAdapter<BaseMediaModel.DownloadFile, BaseViewHolder> baseQuickAdapter2 = choosePlayerQualityFragment.adapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter2 = null;
        }
        BaseMediaModel.DownloadFile item = baseQuickAdapter2.getItem(i);
        if (item.vip_only == 1) {
            if (App.getUserData().getIsvip() == 1) {
                ChooseDownloadQualityListener chooseDownloadQualityListener = choosePlayerQualityFragment.callback;
                if (chooseDownloadQualityListener != null) {
                    chooseDownloadQualityListener.onChooseDownload(item, i);
                }
                choosePlayerQualityFragment.dismiss();
                return;
            }
            Context context = choosePlayerQualityFragment.getContext();
            if (context != null) {
                new VipExpireDialog.Builder(context).setCancelable(false).setTitle("You're not VIP yet").setContent("").setNegativeText("Close").setPositiveText("Subscribe").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment$$ExternalSyntheticLambda3
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        ChoosePlayerQualityFragment.initListener$lambda$6$lambda$5$lambda$4$lambda$2(this.f$0);
                    }
                }).setCancelListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment$$ExternalSyntheticLambda4
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public final void onClick() {
                        this.f$0.getUserInfo();
                    }
                }).create().show();
                return;
            }
            return;
        }
        ChooseDownloadQualityListener chooseDownloadQualityListener2 = choosePlayerQualityFragment.callback;
        if (chooseDownloadQualityListener2 != null) {
            chooseDownloadQualityListener2.onChooseDownload(item, i);
        }
        choosePlayerQualityFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6$lambda$5$lambda$4$lambda$2(ChoosePlayerQualityFragment choosePlayerQualityFragment) {
        new VipChargeGuideDialog().show(choosePlayerQualityFragment.getChildFragmentManager(), "VipChargeGuideDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7(int i, ChoosePlayerQualityFragment choosePlayerQualityFragment, View view, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        float f = i * 0.9f;
        if (view.getHeight() > f) {
            choosePlayerQualityFragment.getBinding().linearLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, (int) f));
        }
    }

    private final void initData() {
        TvRecyclerView rvDevice = getBinding().rvDevice;
        Intrinsics.checkNotNullExpressionValue(rvDevice, "rvDevice");
        CommonExtKt.gone(rvDevice);
        Bundle arguments = getArguments();
        BaseQuickAdapter<BaseMediaModel.DownloadFile, BaseViewHolder> baseQuickAdapter = null;
        Serializable serializable = arguments != null ? arguments.getSerializable("list") : null;
        Intrinsics.checkNotNull(serializable, "null cannot be cast to non-null type java.util.ArrayList<com.movieboxpro.android.model.BaseMediaModel.DownloadFile>");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : (ArrayList) serializable) {
            String str = ((BaseMediaModel.DownloadFile) obj).filename;
            Object obj2 = linkedHashMap.get(str);
            if (obj2 == null) {
                obj2 = (List) new ArrayList();
                linkedHashMap.put(str, obj2);
            }
            ((List) obj2).add(obj);
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.addAll((Collection) ((Map.Entry) it.next()).getValue());
        }
        this.adapter = new BaseQuickAdapter<BaseMediaModel.DownloadFile, BaseViewHolder>(arrayList, R.layout.adapter_definition_item) { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment.initData.2
            {
                super(i, arrayList);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Code duplicated, block: B:39:0x008e  */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, BaseMediaModel.DownloadFile item) {
                int i;
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                if (item.h265 == 1) {
                    GlideUtils.load(getContext(), com.dueeeke.videocontroller.R.mipmap.ic_choose_fromat, (ImageView) holder.getView(R.id.ivCode));
                } else {
                    GlideUtils.load(getContext(), com.dueeeke.videocontroller.R.mipmap.ic_choose_fromat2, (ImageView) holder.getView(R.id.ivCode));
                }
                String str2 = item.real_quality;
                if (str2 == null) {
                    i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                } else {
                    int iHashCode = str2.hashCode();
                    if (iHashCode != 1687) {
                        if (iHashCode != 1811) {
                            if (iHashCode != 1572835) {
                                if (iHashCode != 1688155) {
                                    if (iHashCode == 46737913 && str2.equals("1080p")) {
                                        i = com.dueeeke.videocontroller.R.mipmap.ic_choose_fullhd;
                                    } else {
                                        i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                                    }
                                } else if (str2.equals("720p")) {
                                    i = com.dueeeke.videocontroller.R.mipmap.ic_choose_hd;
                                } else {
                                    i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                                }
                            } else if (str2.equals("360p")) {
                                i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                            } else {
                                i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                            }
                        } else if (str2.equals("8K")) {
                            i = R.mipmap.ic_choose_8k;
                        } else {
                            i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                        }
                    } else if (str2.equals("4K")) {
                        i = com.dueeeke.videocontroller.R.mipmap.ic_choose_4k;
                    } else {
                        i = com.dueeeke.videocontroller.R.mipmap.ic_choose_sd;
                    }
                }
                ImageView imageView = (ImageView) holder.getView(R.id.ivOrg);
                if (item.original == 1) {
                    CommonExtKt.visible(imageView);
                } else {
                    CommonExtKt.gone(imageView);
                }
                GlideUtils.load(getContext(), i, (ImageView) holder.getView(R.id.ivDefinition));
                TextView textView = (TextView) holder.getView(R.id.tv_rate);
                LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.ll_rate);
                String str3 = item.bitstream;
                if (str3 == null || str3.length() == 0) {
                    CommonExtKt.gone(textView);
                    CommonExtKt.gone(linearLayout);
                } else {
                    CommonExtKt.visible(textView);
                    CommonExtKt.visible(linearLayout);
                    textView.setText(item.bitstream);
                }
                ImageView imageView2 = (ImageView) holder.getView(R.id.ivVip);
                if (item.vip_only == 1) {
                    CommonExtKt.visible(imageView2);
                } else {
                    CommonExtKt.gone(imageView2);
                }
                ImageView imageView3 = (ImageView) holder.getView(R.id.ivHdr);
                if (item.hdr == 1) {
                    CommonExtKt.visible(imageView3);
                } else {
                    CommonExtKt.gone(imageView3);
                }
                ImageView imageView4 = (ImageView) holder.getView(R.id.ivColorBit);
                int i2 = item.colorbit;
                if (i2 == 10) {
                    CommonExtKt.visible(imageView4);
                    imageView4.setImageResource(R.mipmap.ic_color_bit_10);
                } else if (i2 != 12) {
                    CommonExtKt.gone(imageView4);
                } else {
                    CommonExtKt.visible(imageView4);
                    imageView4.setImageResource(R.mipmap.ic_color_bit_12);
                }
                holder.setText(R.id.tv_size, item.size);
                CommonExtKt.gone((ImageView) holder.getView(R.id.iv_selected));
            }
        };
        getBinding().recyclerView.setSelectedItemAtCentered(true);
        TvRecyclerView tvRecyclerView = getBinding().recyclerView;
        BaseQuickAdapter<BaseMediaModel.DownloadFile, BaseViewHolder> baseQuickAdapter2 = this.adapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter = baseQuickAdapter2;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        PowerfulStickyDecoration powerfulStickyDecorationBuild = PowerfulStickyDecoration.Builder.init(new PowerGroupListener() { // from class: com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment$initData$decoration$1
            @Override // com.gavin.com.library.listener.PowerGroupListener
            public boolean needDrawDecoration(int position) {
                return true;
            }

            @Override // com.gavin.com.library.listener.GroupListener
            public String getGroupName(int position) {
                String str2;
                BaseQuickAdapter baseQuickAdapter3 = this.this$0.adapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter3 = null;
                }
                BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) baseQuickAdapter3.getItemOrNull(position);
                return (downloadFile == null || (str2 = downloadFile.filename) == null) ? "" : str2;
            }

            @Override // com.gavin.com.library.listener.PowerGroupListener
            public View getGroupView(int position) {
                BaseQuickAdapter baseQuickAdapter3 = null;
                View viewInflate = LayoutInflater.from(this.this$0.getContext()).inflate(R.layout.video_quality_title, (ViewGroup) null, false);
                TextView textView = (TextView) viewInflate.findViewById(R.id.tvName);
                BaseQuickAdapter baseQuickAdapter4 = this.this$0.adapter;
                if (baseQuickAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter4 = null;
                }
                BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) baseQuickAdapter4.getItemOrNull(position);
                textView.setText(downloadFile != null ? downloadFile.filename : null);
                TextView textView2 = (TextView) viewInflate.findViewById(R.id.tvTime);
                BaseQuickAdapter baseQuickAdapter5 = this.this$0.adapter;
                if (baseQuickAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    baseQuickAdapter3 = baseQuickAdapter5;
                }
                BaseMediaModel.DownloadFile downloadFile2 = (BaseMediaModel.DownloadFile) baseQuickAdapter3.getItemOrNull(position);
                textView2.setText(TimeUtils.formatTime4((downloadFile2 != null ? downloadFile2.dateline : 0L) * ((long) 1000)));
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
    }

    /* JADX INFO: compiled from: ChoosePlayerQualityFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0016\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChoosePlayerQualityFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChoosePlayerQualityFragment;", "list", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "Lkotlin/collections/ArrayList;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChoosePlayerQualityFragment newInstance(ArrayList<BaseMediaModel.DownloadFile> list) {
            Intrinsics.checkNotNullParameter(list, "list");
            ChoosePlayerQualityFragment choosePlayerQualityFragment = new ChoosePlayerQualityFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("list", list);
            choosePlayerQualityFragment.setArguments(bundle);
            return choosePlayerQualityFragment;
        }
    }
}
