package com.movieboxpro.android.view.dialog;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.ExternalStorage;
import com.movieboxpro.android.receiver.UsbConnectReceiver;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.fragment.OtgFragment;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogChooseDownloadLocationBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ChooseDownloadLocationFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0017H\u0016J\u0010\u0010\u0019\u001a\u00020\u00172\u0006\u0010\u001a\u001a\u00020\u000bH\u0003J\b\u0010\u001b\u001a\u00020\u0017H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseDownloadLocationFragment;", "Lcom/movieboxpro/android/view/dialog/BaseBindingCenterDialogFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/DialogChooseDownloadLocationBinding;", "deviceAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/ExternalStorage;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "lastSelectPos", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "dialogDimAmount", "", "initListener", "", "initData", "requestStoragePermission", "position", "initView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseDownloadLocationFragment extends BaseBindingCenterDialogFragment {
    private DialogChooseDownloadLocationBinding binding;
    private BaseQuickAdapter<ExternalStorage, BaseViewHolder> deviceAdapter;
    private int lastSelectPos = -1;

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    protected float dialogDimAmount() {
        return 0.5f;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initView() {
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        ViewDataBinding viewDataBindingInflate = DataBindingUtil.inflate(inflater, R.layout.dialog_choose_download_location, container, false);
        Intrinsics.checkNotNullExpressionValue(viewDataBindingInflate, "inflate(...)");
        DialogChooseDownloadLocationBinding dialogChooseDownloadLocationBinding = (DialogChooseDownloadLocationBinding) viewDataBindingInflate;
        this.binding = dialogChooseDownloadLocationBinding;
        if (dialogChooseDownloadLocationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogChooseDownloadLocationBinding = null;
        }
        View root = dialogChooseDownloadLocationBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initListener() {
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter = this.deviceAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadLocationFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                ChooseDownloadLocationFragment.initListener$lambda$1(this.f$0, baseQuickAdapter2, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(final ChooseDownloadLocationFragment chooseDownloadLocationFragment, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        if (i == chooseDownloadLocationFragment.lastSelectPos) {
            return;
        }
        ConfirmSwitchDownloadPathDialog confirmSwitchDownloadPathDialog = new ConfirmSwitchDownloadPathDialog();
        confirmSwitchDownloadPathDialog.setListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadLocationFragment$$ExternalSyntheticLambda1
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                ChooseDownloadLocationFragment.initListener$lambda$1$lambda$0(this.f$0, i);
            }
        });
        confirmSwitchDownloadPathDialog.show(chooseDownloadLocationFragment.getChildFragmentManager(), "ConfirmSwitchDownloadPathDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1$lambda$0(ChooseDownloadLocationFragment chooseDownloadLocationFragment, int i) {
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter = chooseDownloadLocationFragment.deviceAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
            baseQuickAdapter = null;
        }
        ExternalStorage item = baseQuickAdapter.getItem(i);
        if (new File(item.getPath()).exists() && new File(item.getPath()).canWrite()) {
            chooseDownloadLocationFragment.requestStoragePermission(i);
        } else {
            ToastUtils.showShort("Device is unavailable", new Object[0]);
        }
        UsbConnectReceiver.INSTANCE.getStorages().setValue(UsbConnectReceiver.INSTANCE.getStorages().getValue());
    }

    @Override // com.movieboxpro.android.view.dialog.BaseBindingCenterDialogFragment
    public void initData() {
        this.deviceAdapter = new BaseQuickAdapter<ExternalStorage, BaseViewHolder>(R.layout.adapter_usb_device) { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadLocationFragment.initData.1
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
        DialogChooseDownloadLocationBinding dialogChooseDownloadLocationBinding = this.binding;
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter = null;
        if (dialogChooseDownloadLocationBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            dialogChooseDownloadLocationBinding = null;
        }
        TvRecyclerView tvRecyclerView = dialogChooseDownloadLocationBinding.recyclerView;
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter2 = this.deviceAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter2;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        UsbConnectReceiver.INSTANCE.getStorages().observe(this, new ChooseDownloadLocationFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadLocationFragment$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ChooseDownloadLocationFragment.initData$lambda$4(this.f$0, (List) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$4(ChooseDownloadLocationFragment chooseDownloadLocationFragment, List list) {
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
                chooseDownloadLocationFragment.lastSelectPos = i;
            }
            i = i2;
        }
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter2 = chooseDownloadLocationFragment.deviceAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter2;
        }
        baseQuickAdapter.setList(list);
        return Unit.INSTANCE;
    }

    private final void requestStoragePermission(int position) {
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter = this.deviceAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
            baseQuickAdapter = null;
        }
        ExternalStorage item = baseQuickAdapter.getItem(position);
        item.setSelect(true);
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter2 = this.deviceAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
            baseQuickAdapter2 = null;
        }
        baseQuickAdapter2.notifyItemChanged(position);
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter3 = this.deviceAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.getItem(this.lastSelectPos).setSelect(false);
        BaseQuickAdapter<ExternalStorage, BaseViewHolder> baseQuickAdapter4 = this.deviceAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceAdapter");
            baseQuickAdapter4 = null;
        }
        baseQuickAdapter4.notifyItemChanged(this.lastSelectPos);
        this.lastSelectPos = position;
        OtgFragment.INSTANCE.setOtgRoot(null);
        PrefsUtils.getInstance().putString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, item.getPath());
    }
}
