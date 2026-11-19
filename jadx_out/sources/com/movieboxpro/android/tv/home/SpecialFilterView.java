package com.movieboxpro.android.tv.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.model.SpecialFilterModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.view.widget.LayoutManagerItemDecoration;
import com.movieboxpro.androidtv.R;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SpecialFilterView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u0018\u00002\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0014\u0010\u001e\u001a\u00020\u00182\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\b\u0010 \u001a\u00020\u0018H\u0002J\b\u0010!\u001a\u00020\u0018H\u0002R\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\fR\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00120\u0011X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/movieboxpro/android/tv/home/SpecialFilterView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "bgList", "", "[Ljava/lang/Integer;", "data", "", "Lcom/movieboxpro/android/model/SpecialFilterModel;", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "lastSelectPos", "mContext", "initView", "", "focusScale", "v", "Landroid/view/View;", "scale", "", "setData", "list", "initListener", "initData", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SpecialFilterView extends FrameLayout {
    private BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> adapter;
    private Integer[] bgList;
    private List<? extends SpecialFilterModel> data;
    private int lastSelectPos;
    private final Context mContext;
    private FocusBorder mFocusBorder;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SpecialFilterView(Context context) {
        this(context, null, 0, 6, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SpecialFilterView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SpecialFilterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        this.bgList = new Integer[]{Integer.valueOf(R.drawable.special_filter_bg_one_shape), Integer.valueOf(R.drawable.special_filter_bg_two_shape), Integer.valueOf(R.drawable.special_filter_bg_three_shape), Integer.valueOf(R.drawable.special_filter_bg_four_shape)};
        this.lastSelectPos = -1;
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.fragment_special_filter, this);
        initView();
        initData();
        initListener();
    }

    public /* synthetic */ SpecialFilterView(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
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
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.tv.home.SpecialFilterView$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                SpecialFilterView.initListener$lambda$1(this.f$0, baseQuickAdapter2, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(SpecialFilterView specialFilterView, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = specialFilterView.lastSelectPos;
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter3 = specialFilterView.adapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter3 = null;
            }
            SpecialFilterModel item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter4 = specialFilterView.adapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            specialFilterView.lastSelectPos = -1;
            return;
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter5 = specialFilterView.adapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter6 = specialFilterView.adapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter6 = null;
            }
            SpecialFilterModel itemOrNull = baseQuickAdapter6.getItemOrNull(specialFilterView.lastSelectPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter7 = specialFilterView.adapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(specialFilterView.lastSelectPos);
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter8 = specialFilterView.adapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter8 = null;
        }
        SpecialFilterModel item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter9 = specialFilterView.adapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        specialFilterView.lastSelectPos = i;
    }

    private final void initData() {
        int i = R.layout.adapter_special_filter_item;
        Collection arrayList = this.data;
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        this.adapter = new BaseQuickAdapter<SpecialFilterModel, BaseViewHolder>(i, new ArrayList(arrayList)) { // from class: com.movieboxpro.android.tv.home.SpecialFilterView.initData.1
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
        TvRecyclerView tvRecyclerView = (TvRecyclerView) findViewById(R.id.recyclerView);
        tvRecyclerView.setSelectedItemAtCentered(true);
        tvRecyclerView.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        BaseQuickAdapter<SpecialFilterModel, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        tvRecyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.tv.home.SpecialFilterView.initData.2
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
                SpecialFilterView.this.focusScale(itemView, 1.2f);
            }
        });
    }
}
