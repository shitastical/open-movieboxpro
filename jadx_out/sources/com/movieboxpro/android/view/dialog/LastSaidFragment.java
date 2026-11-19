package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.avery.subtitle.model.Subtitle;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.view.widget.LinearItemDecoration;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.SimpleListDialogBinding;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LastSaidFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001cB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u001a\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u000e\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u000eJ\u000e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u0006J\u0016\u0010\u001a\u001a\u00020\u00102\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/view/dialog/LastSaidFragment;", "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/SimpleListDialogBinding;", "<init>", "()V", "selectPos", "", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/avery/subtitle/model/Subtitle;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "list", "", "onItemClickListener", "Lcom/movieboxpro/android/view/dialog/LastSaidFragment$OnLastSaidListener;", "onStart", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "setOnItemClickListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setSelectPos", "position", "setSubtitleData", "data", "OnLastSaidListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LastSaidFragment extends BaseTransparentDialogFragment<SimpleListDialogBinding> {
    private BaseQuickAdapter<Subtitle, BaseViewHolder> adapter;
    private List<Subtitle> list;
    private OnLastSaidListener onItemClickListener;
    private int selectPos;

    /* JADX INFO: compiled from: LastSaidFragment.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/LastSaidFragment$OnLastSaidListener;", "", "onLastSaidClick", "", "subtitle", "Lcom/avery/subtitle/model/Subtitle;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnLastSaidListener {
        void onLastSaidClick(Subtitle subtitle);
    }

    public LastSaidFragment() {
        super(R.layout.simple_list_dialog);
        this.selectPos = -1;
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
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        this.adapter = new BaseQuickAdapter<Subtitle, BaseViewHolder>(R.layout.adapter_last_said_item, this.list) { // from class: com.movieboxpro.android.view.dialog.LastSaidFragment.onViewCreated.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, Subtitle item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(R.id.textView);
                textView.setText(Html.fromHtml(item.content));
                if (LastSaidFragment.this.selectPos == helper.getAdapterPosition()) {
                    textView.setTextColor(CommonExtKt.colorInt(R.color.color_main_blue));
                } else {
                    textView.setTextColor(CommonExtKt.colorInt(R.color.white));
                }
            }
        };
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getBinding().recyclerView.addItemDecoration(new LinearItemDecoration(1, 5));
        RecyclerView recyclerView = getBinding().recyclerView;
        BaseQuickAdapter<Subtitle, BaseViewHolder> baseQuickAdapter = this.adapter;
        BaseQuickAdapter<Subtitle, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        recyclerView.setAdapter(baseQuickAdapter);
        if (CommonExtKt.checkIndexLegal(this.selectPos, this.list)) {
            getBinding().recyclerView.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.LastSaidFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LastSaidFragment.onViewCreated$lambda$2(this.f$0);
                }
            }, 200L);
        }
        BaseQuickAdapter<Subtitle, BaseViewHolder> baseQuickAdapter3 = this.adapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.LastSaidFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter4, View view2, int i) {
                LastSaidFragment.onViewCreated$lambda$3(this.f$0, baseQuickAdapter4, view2, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2(final LastSaidFragment lastSaidFragment) {
        BaseQuickAdapter<Subtitle, BaseViewHolder> baseQuickAdapter = lastSaidFragment.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        View viewByPosition = baseQuickAdapter.getViewByPosition(lastSaidFragment.selectPos, R.id.textView);
        int screenHeight = DensityUtils.getScreenHeight();
        int height = viewByPosition != null ? viewByPosition.getHeight() : 0;
        RecyclerView.LayoutManager layoutManager = lastSaidFragment.getBinding().recyclerView.getLayoutManager();
        Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(lastSaidFragment.selectPos, (screenHeight + height) / 2);
        RecyclerView recyclerView = lastSaidFragment.getBinding().recyclerView;
        if (recyclerView != null) {
            recyclerView.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.LastSaidFragment$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    LastSaidFragment.onViewCreated$lambda$2$lambda$1(this.f$0);
                }
            }, 200L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$2$lambda$1(LastSaidFragment lastSaidFragment) {
        lastSaidFragment.getBinding().recyclerView.requestFocus();
        lastSaidFragment.getBinding().recyclerView.setFocusable(true);
        BaseQuickAdapter<Subtitle, BaseViewHolder> baseQuickAdapter = lastSaidFragment.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        View viewByPosition = baseQuickAdapter.getViewByPosition(lastSaidFragment.selectPos, R.id.textView);
        if (viewByPosition != null) {
            viewByPosition.requestFocus();
        }
        if (viewByPosition != null) {
            viewByPosition.setFocusable(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$3(LastSaidFragment lastSaidFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        OnLastSaidListener onLastSaidListener = lastSaidFragment.onItemClickListener;
        if (onLastSaidListener != null) {
            BaseQuickAdapter<Subtitle, BaseViewHolder> baseQuickAdapter2 = lastSaidFragment.adapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter2 = null;
            }
            onLastSaidListener.onLastSaidClick(baseQuickAdapter2.getData().get(i));
        }
        lastSaidFragment.dismiss();
    }

    public final void setOnItemClickListener(OnLastSaidListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.onItemClickListener = listener;
    }

    public final void setSelectPos(int position) {
        this.selectPos = position;
    }

    public final void setSubtitleData(List<Subtitle> data) {
        this.list = data;
    }
}
