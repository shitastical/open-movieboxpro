package com.movieboxpro.android.tv.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.model.SpecialFilterModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.view.widget.LayoutManagerItemDecoration;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentSpecialFilterBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SpecialFilterFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u00010B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u001a\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u001a2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0006\u0010$\u001a\u00020\"J\u0006\u0010%\u001a\u00020\"J\u000e\u0010&\u001a\u00020\"2\u0006\u0010\u0011\u001a\u00020\u0012J\b\u0010'\u001a\u00020\"H\u0002J\u0018\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020+H\u0002J\u0014\u0010,\u001a\u00020\"2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\b\u0010.\u001a\u00020\"H\u0002J\b\u0010/\u001a\u00020\"H\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\r0\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u00061"}, d2 = {"Lcom/movieboxpro/android/tv/home/SpecialFilterFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "bgList", "", "", "[Ljava/lang/Integer;", "data", "", "Lcom/movieboxpro/android/model/SpecialFilterModel;", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "lastSelectPos", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/tv/home/SpecialFilterFragment$OnTopFilterClickListener;", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentSpecialFilterBinding;", "getBinding", "()Lcom/movieboxpro/androidtv/databinding/FragmentSpecialFilterBinding;", "setBinding", "(Lcom/movieboxpro/androidtv/databinding/FragmentSpecialFilterBinding;)V", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "resetFilter", "requestFocus", "setTopFilterClickListener", "initView", "focusScale", "v", "scale", "", "setData", "list", "initListener", "initData", "OnTopFilterClickListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SpecialFilterFragment extends Fragment {
    private BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> adapter;
    protected FragmentSpecialFilterBinding binding;
    private List<? extends SpecialFilterModel> data;
    private OnTopFilterClickListener listener;
    private FocusBorder mFocusBorder;
    private Integer[] bgList = {Integer.valueOf(R.drawable.special_filter_bg_one_shape), Integer.valueOf(R.drawable.special_filter_bg_two_shape), Integer.valueOf(R.drawable.special_filter_bg_three_shape), Integer.valueOf(R.drawable.special_filter_bg_four_shape)};
    private int lastSelectPos = -1;

    /* JADX INFO: compiled from: SpecialFilterFragment.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/tv/home/SpecialFilterFragment$OnTopFilterClickListener;", "", "onFilterClicked", "", "selected", "", "id", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnTopFilterClickListener {
        void onFilterClicked(boolean selected, String id);
    }

    protected final FragmentSpecialFilterBinding getBinding() {
        FragmentSpecialFilterBinding fragmentSpecialFilterBinding = this.binding;
        if (fragmentSpecialFilterBinding != null) {
            return fragmentSpecialFilterBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    protected final void setBinding(FragmentSpecialFilterBinding fragmentSpecialFilterBinding) {
        Intrinsics.checkNotNullParameter(fragmentSpecialFilterBinding, "<set-?>");
        this.binding = fragmentSpecialFilterBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSpecialFilterBinding fragmentSpecialFilterBindingInflate = FragmentSpecialFilterBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentSpecialFilterBindingInflate, "inflate(...)");
        setBinding(fragmentSpecialFilterBindingInflate);
        return getBinding().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    public final void resetFilter() {
        int i = this.lastSelectPos;
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        if (CommonExtKt.checkIndexLegal(i, baseQuickAdapter.getData())) {
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter3 = this.adapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter3 = null;
            }
            SpecialFilterModel itemOrNull = baseQuickAdapter3.getItemOrNull(this.lastSelectPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter4 = this.adapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(this.lastSelectPos);
            this.lastSelectPos = -1;
        }
    }

    public final void requestFocus() {
        getBinding().recyclerView.requestFocus();
    }

    public final void setTopFilterClickListener(OnTopFilterClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    private final void initView() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
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

    public final void setData(List<? extends SpecialFilterModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.data = list;
        if (list != null) {
            int i = 0;
            for (Object obj : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Integer[] numArr = this.bgList;
                ((SpecialFilterModel) obj).setBgId(numArr[i % numArr.length].intValue());
                i = i2;
            }
        }
    }

    private final void initListener() {
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.tv.home.SpecialFilterFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                SpecialFilterFragment.initListener$lambda$1(this.f$0, baseQuickAdapter2, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(SpecialFilterFragment specialFilterFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        String id;
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = specialFilterFragment.lastSelectPos;
        String str = "";
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter3 = specialFilterFragment.adapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter3 = null;
            }
            SpecialFilterModel item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter4 = specialFilterFragment.adapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            specialFilterFragment.lastSelectPos = -1;
            OnTopFilterClickListener onTopFilterClickListener = specialFilterFragment.listener;
            if (onTopFilterClickListener != null) {
                onTopFilterClickListener.onFilterClicked(false, "");
                return;
            }
            return;
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter5 = specialFilterFragment.adapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter6 = specialFilterFragment.adapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter6 = null;
            }
            SpecialFilterModel itemOrNull = baseQuickAdapter6.getItemOrNull(specialFilterFragment.lastSelectPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter7 = specialFilterFragment.adapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(specialFilterFragment.lastSelectPos);
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter8 = specialFilterFragment.adapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter8 = null;
        }
        SpecialFilterModel item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter9 = specialFilterFragment.adapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter9 = null;
        }
        baseQuickAdapter9.notifyItemChanged(i);
        specialFilterFragment.lastSelectPos = i;
        OnTopFilterClickListener onTopFilterClickListener2 = specialFilterFragment.listener;
        if (onTopFilterClickListener2 != null) {
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter10 = specialFilterFragment.adapter;
            if (baseQuickAdapter10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter10;
            }
            SpecialFilterModel item3 = baseQuickAdapter2.getItem(i);
            if (item3 != null && (id = item3.getId()) != null) {
                str = id;
            }
            onTopFilterClickListener2.onFilterClicked(true, str);
        }
    }

    private final void initData() {
        int i = R.layout.adapter_special_filter_item;
        Collection arrayList = this.data;
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        this.adapter = new BaseQuickAdapter<SpecialFilterModel, BaseViewHolder>(i, new ArrayList(arrayList)) { // from class: com.movieboxpro.android.tv.home.SpecialFilterFragment.initData.1
            {
                ArrayList<SpecialFilterModel> arrayList2 = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, SpecialFilterModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(R.id.textView);
                View view = helper.getView(R.id.view);
                textView.setText(item.getDisplay_name());
                if (item.isSelect()) {
                    CommonExtKt.visible(view);
                } else {
                    CommonExtKt.gone(view);
                }
                textView.setBackgroundResource(item.getBgId());
            }
        };
        getBinding().recyclerView.setSelectedItemAtCentered(true);
        getBinding().recyclerView.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView = getBinding().recyclerView;
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        getBinding().recyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.tv.home.SpecialFilterFragment.initData.2
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                SpecialFilterFragment.this.focusScale(itemView, 1.1f);
            }
        });
    }
}
