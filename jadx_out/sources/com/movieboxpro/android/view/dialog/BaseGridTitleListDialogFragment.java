package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.GridListDialogBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BaseGridTitleListDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0016\u0010\u0019\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016J\u0018\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u00162\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0004J\b\u0010\u001f\u001a\u00020\u0014H\u0004J\b\u0010 \u001a\u00020!H&J\u001d\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00028\u0000H&¢\u0006\u0002\u0010%J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010'\u001a\u00020!H&J\u000e\u0010(\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u0012J \u0010*\u001a\u00020\u00142\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R&\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\tX\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/movieboxpro/android/view/dialog/BaseGridTitleListDialogFragment;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/movieboxpro/android/view/dialog/BaseTransparentDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/GridListDialogBinding;", "<init>", "()V", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "getAdapter", "()Lcom/chad/library/adapter/base/BaseQuickAdapter;", "setAdapter", "(Lcom/chad/library/adapter/base/BaseQuickAdapter;)V", "list", "", "name", "", "onViewCreated", "", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initAdapter", "focusScale", "v", "scale", "", "showLoading", "hideLoading", "setItemLayout", "", "initItemView", "helper", "item", "(Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;Ljava/lang/Object;)V", "onItemClicked", "position", "setTitle", TvContractCompat.ProgramColumns.COLUMN_TITLE, "setData", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BaseGridTitleListDialogFragment<T> extends BaseTransparentDialogFragment<GridListDialogBinding> {
    protected BaseQuickAdapter<T, BaseViewHolder> adapter;
    private List<? extends T> list;
    private FocusBorder mFocusBorder;
    private String name;

    public BaseQuickAdapter<T, BaseViewHolder> initAdapter() {
        return null;
    }

    public abstract void initItemView(BaseViewHolder helper, T item);

    public abstract void onItemClicked(int position);

    public abstract int setItemLayout();

    public BaseGridTitleListDialogFragment() {
        super(R.layout.grid_list_dialog);
        this.name = "";
    }

    protected final BaseQuickAdapter<T, BaseViewHolder> getAdapter() {
        BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter != null) {
            return baseQuickAdapter;
        }
        Intrinsics.throwUninitializedPropertyAccessException("adapter");
        return null;
    }

    protected final void setAdapter(BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapter) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<set-?>");
        this.adapter = baseQuickAdapter;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(getActivity());
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
        if (initAdapter() != null) {
            BaseQuickAdapter<T, BaseViewHolder> baseQuickAdapterInitAdapter = initAdapter();
            Intrinsics.checkNotNull(baseQuickAdapterInitAdapter);
            setAdapter(baseQuickAdapterInitAdapter);
        } else {
            int itemLayout = setItemLayout();
            List<? extends T> list = this.list;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("list");
                list = null;
            }
            setAdapter(new BaseQuickAdapter<T, BaseViewHolder>(this, itemLayout, new ArrayList(list)) { // from class: com.movieboxpro.android.view.dialog.BaseGridTitleListDialogFragment.onViewCreated.1
                final /* synthetic */ BaseGridTitleListDialogFragment<T> this$0;

                {
                    this.this$0 = this;
                    ArrayList<T> arrayList = arrayList;
                }

                @Override // com.chad.library.adapter.base.BaseQuickAdapter
                protected void convert(BaseViewHolder helper, T item) {
                    Intrinsics.checkNotNullParameter(helper, "helper");
                    this.this$0.initItemView(helper, item);
                }
            });
        }
        getBinding().tvName.setText(this.name);
        getBinding().recyclerView.setSelectedItemAtCentered(true);
        getBinding().recyclerView.setSpacingWithMargins(DensityUtils.dp2px(getContext(), 15.0f), DensityUtils.dp2px(getContext(), 15.0f));
        getBinding().recyclerView.setAdapter(getAdapter());
        getBinding().recyclerView.setOnItemListener(new TvRecyclerView.OnItemListener(this) { // from class: com.movieboxpro.android.view.dialog.BaseGridTitleListDialogFragment.onViewCreated.2
            final /* synthetic */ BaseGridTitleListDialogFragment<T> this$0;

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            {
                this.this$0 = this;
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                this.this$0.focusScale(itemView, 1.15f);
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                this.this$0.onItemClicked(position);
                this.this$0.dismiss();
            }
        });
        getBinding().recyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.view.dialog.BaseGridTitleListDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view2, boolean z) {
                BaseGridTitleListDialogFragment.onViewCreated$lambda$0(this.f$0, view2, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(BaseGridTitleListDialogFragment baseGridTitleListDialogFragment, View view, boolean z) {
        if (z) {
            return;
        }
        FocusBorder focusBorder = baseGridTitleListDialogFragment.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void focusScale(View v, float scale) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }

    protected final void showLoading() {
        FrameLayout flLoading = getBinding().flLoading;
        Intrinsics.checkNotNullExpressionValue(flLoading, "flLoading");
        CommonExtKt.visible(flLoading);
    }

    protected final void hideLoading() {
        FrameLayout flLoading = getBinding().flLoading;
        Intrinsics.checkNotNullExpressionValue(flLoading, "flLoading");
        CommonExtKt.gone(flLoading);
    }

    public final void setTitle(String title) {
        Intrinsics.checkNotNullParameter(title, "title");
        getBinding().tvName.setText(title);
    }

    public final void setData(List<? extends T> list, String name) {
        if (list == null) {
            list = new ArrayList();
        }
        this.list = list;
        this.name = name;
    }
}
