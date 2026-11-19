package com.movieboxpro.android.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.base.BaseBottomFullScreenDialogFragment;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.livedata.DownloadLanguageChangedLiveData;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.view.widget.LinearItemDecoration;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogChooseDownloadLanguageBinding;
import com.owen.tvrecyclerview.widget.V7LinearLayoutManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ChooseDownloadLanguageDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\u0006H\u0002¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseDownloadLanguageDialog;", "Lcom/movieboxpro/android/base/BaseBottomFullScreenDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/DialogChooseDownloadLanguageBinding;", "<init>", "()V", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initData", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseDownloadLanguageDialog extends BaseBottomFullScreenDialogFragment<DialogChooseDownloadLanguageBinding> {
    public ChooseDownloadLanguageDialog() {
        super(R.layout.dialog_choose_download_language);
    }

    @Override // com.movieboxpro.android.base.BaseBottomFullScreenDialogFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [com.movieboxpro.android.view.dialog.ChooseDownloadLanguageDialog$initData$adapter$1] */
    private final void initData() {
        SettingManager settingManager = SettingManager.INSTANCE;
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext(...)");
        List<String> downloadAudioLanguage = settingManager.getDownloadAudioLanguage(contextRequireContext);
        final int i = R.layout.adapter_download_language_item;
        final ArrayList arrayList = new ArrayList(downloadAudioLanguage);
        final ?? r0 = new CommonBaseAdapter<String>(i, arrayList) { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadLanguageDialog$initData$adapter$1
            {
                ArrayList<String> arrayList2 = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, String item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                LinearLayout linearLayout = (LinearLayout) holder.getView(R.id.linearLayout);
                FrameLayout frameLayout = (FrameLayout) holder.getView(R.id.flUpArrow);
                if (holder.getBindingAdapterPosition() == 0) {
                    CommonExtKt.gone(frameLayout);
                    linearLayout.setFocusable(false);
                } else {
                    linearLayout.setFocusable(true);
                    CommonExtKt.visible(frameLayout);
                }
                holder.setText(R.id.tvLanguage, (CharSequence) StringsKt.split$default((CharSequence) item, new String[]{"->"}, false, 0, 6, (Object) null).get(1));
            }
        };
        getBinding().recyclerView.setLayoutManager(new V7LinearLayoutManager(getContext()));
        getBinding().recyclerView.addItemDecoration(new LinearItemDecoration(12));
        r0.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.ChooseDownloadLanguageDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                ChooseDownloadLanguageDialog.initData$lambda$0(r0, baseQuickAdapter, view, i2);
            }
        });
        getBinding().recyclerView.setAdapter((RecyclerView.Adapter) r0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$0(ChooseDownloadLanguageDialog$initData$adapter$1 chooseDownloadLanguageDialog$initData$adapter$1, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        List mutableList = CollectionsKt.toMutableList((Collection) chooseDownloadLanguageDialog$initData$adapter$1.getData());
        if (i > 0) {
            Collections.swap(mutableList, i, i - 1);
            chooseDownloadLanguageDialog$initData$adapter$1.setList(mutableList);
        }
        SettingManager.INSTANCE.saveDownloadAudioLanguage(chooseDownloadLanguageDialog$initData$adapter$1.getData());
        DownloadLanguageChangedLiveData.INSTANCE.get().postValue(true);
    }
}
