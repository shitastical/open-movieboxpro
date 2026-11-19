package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.adorkable.iosdialog.R;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.FilterModel;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.tool.SPStaticUtils;
import com.movieboxpro.android.view.widget.LayoutManagerItemDecoration;
import com.movieboxpro.androidtv.databinding.FragmentFilterBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FilterSearchDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u0000 I2\u00020\u0001:\u0002HIB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u000e\u0010(\u001a\u00020%2\u0006\u0010 \u001a\u00020!J\b\u0010)\u001a\u00020%H\u0016J&\u0010*\u001a\u0004\u0018\u00010+2\u0006\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u001a\u00100\u001a\u00020%2\u0006\u00101\u001a\u00020+2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\b\u00108\u001a\u00020%H\u0002J\u0014\u00109\u001a\u00020%2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00120;J\u001e\u0010<\u001a\u00020%2\u0016\u0010:\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0005j\b\u0012\u0004\u0012\u00020\u0010`\u0007J\b\u0010=\u001a\u00020%H\u0002J!\u0010>\u001a\u00020%2\u0012\u0010?\u001a\n\u0012\u0006\b\u0001\u0012\u00020A0@\"\u00020AH\u0002¢\u0006\u0002\u0010BJ\b\u0010C\u001a\u00020%H\u0003J\u0018\u0010D\u001a\u00020%2\u0006\u0010E\u001a\u00020+2\u0006\u0010F\u001a\u00020GH\u0002R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0005j\b\u0012\u0004\u0012\u00020\u000e`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0005j\b\u0012\u0004\u0012\u00020\u0010`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00120\u0005j\b\u0012\u0004\u0012\u00020\u0012`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006J"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterSearchDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "sortTitles", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "sort", "ratingTitles", "rating", "resolutionTitles", "resolution", "yearList", "Lcom/movieboxpro/android/model/FilterModel;", "countryList", "Lcom/movieboxpro/android/model/FilterCountry;", "geners", "Lcom/movieboxpro/android/model/common/Gener;", "country", "selectMorePos", "", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "sortAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "yearAdapter", "ratingAdapter", "resolutionAdapter", "countryAdapter", "generAdapter", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/FilterSearchDialogFragment$OnFilterDoneListener;", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentFilterBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setOnListener", "onStart", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "lastSortPos", "lastYearPos", "lastGenerPos", "lastRatingPos", "lastResolutionPos", "lastCountryPos", "initListener", "setGener", "list", "", "setCountry", "initData", "scaleListener", "recyclerView", "", "Lcom/owen/tvrecyclerview/widget/TvRecyclerView;", "([Lcom/owen/tvrecyclerview/widget/TvRecyclerView;)V", "initView", "focusScale", "v", "scale", "", "OnFilterDoneListener", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FilterSearchDialogFragment extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final String DEFAULT_SORT = "released";
    private FragmentFilterBinding binding;
    private BaseQuickAdapter<FilterCountry, BaseViewHolder> countryAdapter;
    private BaseQuickAdapter<Gener, BaseViewHolder> generAdapter;
    private int lastCountryPos;
    private int lastSortPos;
    private OnFilterDoneListener listener;
    private FocusBorder mFocusBorder;
    private ArrayList<String> rating;
    private BaseQuickAdapter<FilterModel, BaseViewHolder> ratingAdapter;
    private ArrayList<String> ratingTitles;
    private ArrayList<String> resolution;
    private BaseQuickAdapter<FilterModel, BaseViewHolder> resolutionAdapter;
    private ArrayList<String> resolutionTitles;
    private ArrayList<String> sort;
    private BaseQuickAdapter<FilterModel, BaseViewHolder> sortAdapter;
    private ArrayList<String> sortTitles;
    private BaseQuickAdapter<FilterModel, BaseViewHolder> yearAdapter;
    private final ArrayList<FilterModel> yearList = new ArrayList<>();
    private ArrayList<FilterCountry> countryList = new ArrayList<>();
    private ArrayList<Gener> geners = new ArrayList<>();
    private String country = "";
    private int selectMorePos = -1;
    private int lastYearPos = -1;
    private int lastGenerPos = -1;
    private int lastRatingPos = -1;
    private int lastResolutionPos = -1;

    /* JADX INFO: compiled from: FilterSearchDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J8\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H&¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterSearchDialogFragment$OnFilterDoneListener;", "", "onFilterDone", "", "sort", "", "year", "gener", "rating", "quality", "country", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnFilterDoneListener {
        void onFilterDone(String sort, String year, String gener, String rating, String quality, String country);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.DialogTopShowStyle);
    }

    public final void setOnListener(OnFilterDoneListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            Context context = getContext();
            window.setBackgroundDrawable(context != null ? ContextCompat.getDrawable(context, android.R.color.transparent) : null);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 48;
            attributes.dimAmount = 0.0f;
            attributes.width = -1;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentFilterBinding fragmentFilterBindingInflate = FragmentFilterBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentFilterBindingInflate, "inflate(...)");
        this.binding = fragmentFilterBindingInflate;
        if (fragmentFilterBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBindingInflate = null;
        }
        return fragmentFilterBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private final void initListener() {
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter = this.sortAdapter;
        FragmentFilterBinding fragmentFilterBinding = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterSearchDialogFragment.initListener$lambda$1(this.f$0, baseQuickAdapter2, view, i);
            }
        });
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = this.ratingAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter2 = null;
        }
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                FilterSearchDialogFragment.initListener$lambda$2(this.f$0, baseQuickAdapter3, view, i);
            }
        });
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = this.resolutionAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter4, View view, int i) {
                FilterSearchDialogFragment.initListener$lambda$3(this.f$0, baseQuickAdapter4, view, i);
            }
        });
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = this.yearAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter4 = null;
        }
        baseQuickAdapter4.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter5, View view, int i) {
                FilterSearchDialogFragment.initListener$lambda$4(this.f$0, baseQuickAdapter5, view, i);
            }
        });
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter5 = this.generAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            baseQuickAdapter5 = null;
        }
        baseQuickAdapter5.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter6, View view, int i) {
                FilterSearchDialogFragment.initListener$lambda$5(this.f$0, baseQuickAdapter6, view, i);
            }
        });
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter6 = this.countryAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter6 = null;
        }
        baseQuickAdapter6.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter7, View view, int i) {
                FilterSearchDialogFragment.initListener$lambda$6(this.f$0, baseQuickAdapter7, view, i);
            }
        });
        FragmentFilterBinding fragmentFilterBinding2 = this.binding;
        if (fragmentFilterBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding2 = null;
        }
        fragmentFilterBinding2.tvDone.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterSearchDialogFragment.initListener$lambda$7(this.f$0, view);
            }
        });
        FragmentFilterBinding fragmentFilterBinding3 = this.binding;
        if (fragmentFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentFilterBinding = fragmentFilterBinding3;
        }
        fragmentFilterBinding.tvReset.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterSearchDialogFragment.initListener$lambda$8(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(FilterSearchDialogFragment filterSearchDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        if (filterSearchDialogFragment.lastSortPos == i) {
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = filterSearchDialogFragment.sortAdapter;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = null;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter2 = null;
        }
        FilterModel itemOrNull = baseQuickAdapter2.getItemOrNull(filterSearchDialogFragment.lastSortPos);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterSearchDialogFragment.sortAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter4 = null;
        }
        FilterModel item = baseQuickAdapter4.getItem(i);
        if (item != null) {
            item.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterSearchDialogFragment.sortAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter5 = null;
        }
        baseQuickAdapter5.notifyItemChanged(i);
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterSearchDialogFragment.sortAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
        } else {
            baseQuickAdapter3 = baseQuickAdapter6;
        }
        baseQuickAdapter3.notifyItemChanged(filterSearchDialogFragment.lastSortPos);
        filterSearchDialogFragment.lastSortPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(FilterSearchDialogFragment filterSearchDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterSearchDialogFragment.lastRatingPos;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            filterSearchDialogFragment.lastRatingPos = -1;
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterSearchDialogFragment.ratingAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter3 = null;
            }
            FilterModel item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterSearchDialogFragment.ratingAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterSearchDialogFragment.ratingAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterSearchDialogFragment.ratingAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter6.getItemOrNull(filterSearchDialogFragment.lastRatingPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterSearchDialogFragment.ratingAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterSearchDialogFragment.lastRatingPos);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterSearchDialogFragment.ratingAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter8 = null;
        }
        FilterModel item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterSearchDialogFragment.ratingAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterSearchDialogFragment.lastRatingPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(FilterSearchDialogFragment filterSearchDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterSearchDialogFragment.lastResolutionPos;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            filterSearchDialogFragment.lastResolutionPos = -1;
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterSearchDialogFragment.resolutionAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter3 = null;
            }
            FilterModel item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterSearchDialogFragment.resolutionAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterSearchDialogFragment.resolutionAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterSearchDialogFragment.resolutionAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter6.getItemOrNull(filterSearchDialogFragment.lastResolutionPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterSearchDialogFragment.resolutionAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterSearchDialogFragment.lastResolutionPos);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterSearchDialogFragment.resolutionAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter8 = null;
        }
        FilterModel item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterSearchDialogFragment.resolutionAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterSearchDialogFragment.lastResolutionPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(FilterSearchDialogFragment filterSearchDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterSearchDialogFragment.lastYearPos;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            filterSearchDialogFragment.lastYearPos = -1;
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterSearchDialogFragment.yearAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter3 = null;
            }
            FilterModel item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterSearchDialogFragment.yearAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterSearchDialogFragment.yearAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterSearchDialogFragment.yearAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter6.getItemOrNull(filterSearchDialogFragment.lastYearPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterSearchDialogFragment.yearAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterSearchDialogFragment.lastYearPos);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterSearchDialogFragment.yearAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter8 = null;
        }
        FilterModel item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterSearchDialogFragment.yearAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterSearchDialogFragment.lastYearPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5(FilterSearchDialogFragment filterSearchDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterSearchDialogFragment.lastGenerPos;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter3 = filterSearchDialogFragment.generAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter3 = null;
            }
            Gener item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter4 = filterSearchDialogFragment.generAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            filterSearchDialogFragment.lastGenerPos = -1;
            return;
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter5 = filterSearchDialogFragment.generAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter6 = filterSearchDialogFragment.generAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter6 = null;
            }
            Gener itemOrNull = baseQuickAdapter6.getItemOrNull(filterSearchDialogFragment.lastGenerPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter7 = filterSearchDialogFragment.generAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterSearchDialogFragment.lastGenerPos);
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter8 = filterSearchDialogFragment.generAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            baseQuickAdapter8 = null;
        }
        Gener item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter9 = filterSearchDialogFragment.generAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterSearchDialogFragment.lastGenerPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6(final FilterSearchDialogFragment filterSearchDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter2 = filterSearchDialogFragment.countryAdapter;
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter3 = null;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter2 = null;
        }
        FilterCountry item = baseQuickAdapter2.getItem(i);
        String country = item.getCountry();
        Intrinsics.checkNotNull(country);
        if (StringsKt.contains$default((CharSequence) country, (CharSequence) "More", false, 2, (Object) null)) {
            ChooseCountryDialog chooseCountryDialogNewInstance = ChooseCountryDialog.INSTANCE.newInstance(filterSearchDialogFragment.country);
            chooseCountryDialogNewInstance.setListener(new ArrayList<>(filterSearchDialogFragment.countryList), new ChooseCountryDialog.OnLanguageListener() { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment$initListener$6$1
                @Override // com.movieboxpro.android.view.dialog.ChooseCountryDialog.OnLanguageListener
                public void onLanguageSelected(String language) {
                    Intrinsics.checkNotNullParameter(language, "language");
                    this.this$0.selectMorePos = i;
                    this.this$0.country = language;
                    int i2 = this.this$0.lastCountryPos;
                    BaseQuickAdapter baseQuickAdapter4 = this.this$0.countryAdapter;
                    BaseQuickAdapter baseQuickAdapter5 = null;
                    if (baseQuickAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter4 = null;
                    }
                    if (i2 != baseQuickAdapter4.getData().size() - 1) {
                        BaseQuickAdapter baseQuickAdapter6 = this.this$0.countryAdapter;
                        if (baseQuickAdapter6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            baseQuickAdapter6 = null;
                        }
                        FilterCountry filterCountry = (FilterCountry) baseQuickAdapter6.getItemOrNull(this.this$0.lastCountryPos);
                        if (filterCountry != null) {
                            filterCountry.setSelect(false);
                        }
                        BaseQuickAdapter baseQuickAdapter7 = this.this$0.countryAdapter;
                        if (baseQuickAdapter7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                            baseQuickAdapter7 = null;
                        }
                        baseQuickAdapter7.notifyItemChanged(this.this$0.lastCountryPos);
                    }
                    BaseQuickAdapter baseQuickAdapter8 = this.this$0.countryAdapter;
                    if (baseQuickAdapter8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter8 = null;
                    }
                    BaseQuickAdapter baseQuickAdapter9 = this.this$0.countryAdapter;
                    if (baseQuickAdapter9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter9 = null;
                    }
                    ((FilterCountry) baseQuickAdapter8.getItem(baseQuickAdapter9.getData().size() - 1)).setCountry("More(" + this.this$0.country + ")");
                    BaseQuickAdapter baseQuickAdapter10 = this.this$0.countryAdapter;
                    if (baseQuickAdapter10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter10 = null;
                    }
                    BaseQuickAdapter baseQuickAdapter11 = this.this$0.countryAdapter;
                    if (baseQuickAdapter11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter11 = null;
                    }
                    ((FilterCountry) baseQuickAdapter10.getItem(baseQuickAdapter11.getData().size() - 1)).setSelect(true);
                    BaseQuickAdapter baseQuickAdapter12 = this.this$0.countryAdapter;
                    if (baseQuickAdapter12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                        baseQuickAdapter12 = null;
                    }
                    BaseQuickAdapter baseQuickAdapter13 = this.this$0.countryAdapter;
                    if (baseQuickAdapter13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
                    } else {
                        baseQuickAdapter5 = baseQuickAdapter13;
                    }
                    baseQuickAdapter12.notifyItemChanged(baseQuickAdapter5.getData().size() - 1);
                    this.this$0.lastCountryPos = i;
                }
            });
            chooseCountryDialogNewInstance.show(filterSearchDialogFragment.getChildFragmentManager(), "ChooseCountryDialog");
            return;
        }
        if (filterSearchDialogFragment.lastCountryPos == i) {
            return;
        }
        item.setSelect(true);
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter4 = filterSearchDialogFragment.countryAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter4 = null;
        }
        FilterCountry itemOrNull = baseQuickAdapter4.getItemOrNull(filterSearchDialogFragment.lastCountryPos);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter5 = filterSearchDialogFragment.countryAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter5 = null;
        }
        baseQuickAdapter5.notifyItemChanged(i);
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter6 = filterSearchDialogFragment.countryAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
        } else {
            baseQuickAdapter3 = baseQuickAdapter6;
        }
        baseQuickAdapter3.notifyItemChanged(filterSearchDialogFragment.lastCountryPos);
        filterSearchDialogFragment.lastCountryPos = i;
        if (Intrinsics.areEqual(item.getCountry(), "All")) {
            filterSearchDialogFragment.country = "";
        } else {
            String country2 = item.getCountry();
            Intrinsics.checkNotNullExpressionValue(country2, "getCountry(...)");
            filterSearchDialogFragment.country = country2;
        }
        filterSearchDialogFragment.selectMorePos = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7(FilterSearchDialogFragment filterSearchDialogFragment, View view) {
        String title;
        String str;
        String str2;
        OnFilterDoneListener onFilterDoneListener = filterSearchDialogFragment.listener;
        if (onFilterDoneListener != null) {
            ArrayList<String> arrayList = filterSearchDialogFragment.sort;
            ArrayList<String> arrayList2 = null;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sort");
                arrayList = null;
            }
            String str3 = arrayList.get(filterSearchDialogFragment.lastSortPos);
            Intrinsics.checkNotNullExpressionValue(str3, "get(...)");
            String str4 = str3;
            if (filterSearchDialogFragment.lastYearPos == -1) {
                title = "";
            } else {
                BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter = filterSearchDialogFragment.yearAdapter;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                    baseQuickAdapter = null;
                }
                title = baseQuickAdapter.getData().get(filterSearchDialogFragment.lastYearPos).getTitle();
            }
            Intrinsics.checkNotNull(title);
            int i = filterSearchDialogFragment.lastGenerPos;
            String id = i == -1 ? "" : filterSearchDialogFragment.geners.get(i).getId();
            Intrinsics.checkNotNull(id);
            if (filterSearchDialogFragment.lastRatingPos == -1) {
                str = "";
            } else {
                ArrayList<String> arrayList3 = filterSearchDialogFragment.rating;
                if (arrayList3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rating");
                    arrayList3 = null;
                }
                String str5 = arrayList3.get(filterSearchDialogFragment.lastRatingPos);
                Intrinsics.checkNotNullExpressionValue(str5, "get(...)");
                str = str5;
            }
            if (filterSearchDialogFragment.lastResolutionPos == -1) {
                str2 = "";
            } else {
                ArrayList<String> arrayList4 = filterSearchDialogFragment.resolution;
                if (arrayList4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("resolution");
                } else {
                    arrayList2 = arrayList4;
                }
                String str6 = arrayList2.get(filterSearchDialogFragment.lastResolutionPos);
                Intrinsics.checkNotNullExpressionValue(str6, "get(...)");
                str2 = str6;
            }
            onFilterDoneListener.onFilterDone(str4, title, id, str, str2, filterSearchDialogFragment.country);
        }
        filterSearchDialogFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$8(FilterSearchDialogFragment filterSearchDialogFragment, View view) {
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter = null;
        if (filterSearchDialogFragment.lastSortPos != 0) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = filterSearchDialogFragment.sortAdapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter2 = null;
            }
            FilterModel item = baseQuickAdapter2.getItem(0);
            if (item != null) {
                item.setSelect(true);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterSearchDialogFragment.sortAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter3 = null;
            }
            baseQuickAdapter3.notifyItemChanged(0);
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterSearchDialogFragment.sortAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter4 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter4.getItemOrNull(filterSearchDialogFragment.lastSortPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterSearchDialogFragment.sortAdapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter5 = null;
            }
            baseQuickAdapter5.notifyItemChanged(filterSearchDialogFragment.lastSortPos);
            filterSearchDialogFragment.lastSortPos = 0;
        }
        if (filterSearchDialogFragment.lastRatingPos != -1) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterSearchDialogFragment.ratingAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull2 = baseQuickAdapter6.getItemOrNull(filterSearchDialogFragment.lastRatingPos);
            if (itemOrNull2 != null) {
                itemOrNull2.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterSearchDialogFragment.ratingAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterSearchDialogFragment.lastRatingPos);
            filterSearchDialogFragment.lastRatingPos = -1;
        }
        if (filterSearchDialogFragment.lastResolutionPos != -1) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterSearchDialogFragment.resolutionAdapter;
            if (baseQuickAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter8 = null;
            }
            FilterModel itemOrNull3 = baseQuickAdapter8.getItemOrNull(filterSearchDialogFragment.lastResolutionPos);
            if (itemOrNull3 != null) {
                itemOrNull3.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterSearchDialogFragment.resolutionAdapter;
            if (baseQuickAdapter9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter9 = null;
            }
            baseQuickAdapter9.notifyItemChanged(filterSearchDialogFragment.lastResolutionPos);
            filterSearchDialogFragment.lastResolutionPos = -1;
        }
        if (filterSearchDialogFragment.lastYearPos != -1) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter10 = filterSearchDialogFragment.yearAdapter;
            if (baseQuickAdapter10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter10 = null;
            }
            FilterModel itemOrNull4 = baseQuickAdapter10.getItemOrNull(filterSearchDialogFragment.lastYearPos);
            if (itemOrNull4 != null) {
                itemOrNull4.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter11 = filterSearchDialogFragment.yearAdapter;
            if (baseQuickAdapter11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter11 = null;
            }
            baseQuickAdapter11.notifyItemChanged(filterSearchDialogFragment.lastYearPos);
            filterSearchDialogFragment.lastYearPos = -1;
        }
        if (filterSearchDialogFragment.lastGenerPos != -1) {
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter12 = filterSearchDialogFragment.generAdapter;
            if (baseQuickAdapter12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter12 = null;
            }
            Gener itemOrNull5 = baseQuickAdapter12.getItemOrNull(filterSearchDialogFragment.lastGenerPos);
            if (itemOrNull5 != null) {
                itemOrNull5.setSelect(false);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter13 = filterSearchDialogFragment.generAdapter;
            if (baseQuickAdapter13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter13 = null;
            }
            baseQuickAdapter13.notifyItemChanged(filterSearchDialogFragment.lastGenerPos);
            filterSearchDialogFragment.lastGenerPos = -1;
        }
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter14 = filterSearchDialogFragment.countryAdapter;
        if (baseQuickAdapter14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter14 = null;
        }
        FilterCountry itemOrNull6 = baseQuickAdapter14.getItemOrNull(filterSearchDialogFragment.lastCountryPos);
        if (itemOrNull6 != null) {
            itemOrNull6.setSelect(false);
        }
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter15 = filterSearchDialogFragment.countryAdapter;
        if (baseQuickAdapter15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter15 = null;
        }
        baseQuickAdapter15.notifyItemChanged(filterSearchDialogFragment.lastCountryPos);
        filterSearchDialogFragment.lastCountryPos = 0;
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter16 = filterSearchDialogFragment.countryAdapter;
        if (baseQuickAdapter16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter16 = null;
        }
        baseQuickAdapter16.getItem(filterSearchDialogFragment.lastCountryPos).setSelect(true);
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter17 = filterSearchDialogFragment.countryAdapter;
        if (baseQuickAdapter17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter17;
        }
        baseQuickAdapter.notifyItemChanged(filterSearchDialogFragment.lastCountryPos);
        filterSearchDialogFragment.country = "";
    }

    public final void setGener(List<Gener> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        List<Gener> list2 = list;
        if (!list2.isEmpty()) {
            list.remove(0);
        }
        this.geners.addAll(list2);
    }

    public final void setCountry(ArrayList<FilterCountry> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.countryList.clear();
        this.countryList.addAll(list);
    }

    private final void initData() {
        String string;
        Bundle arguments = getArguments();
        boolean z = arguments != null ? arguments.getBoolean("isMovie") : false;
        Bundle arguments2 = getArguments();
        if (arguments2 != null ? arguments2.getBoolean("isAll") : false) {
            string = SPStaticUtils.getString(Constant.Prefs.SEARCH_FILTER_ALL);
        } else if (z) {
            string = SPStaticUtils.getString(Constant.Prefs.SEARCH_FILTER_MOVIE);
        } else {
            string = SPStaticUtils.getString(Constant.Prefs.SEARCH_FILTER_TV);
        }
        String str = string;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = null;
        if (!(str == null || str.length() == 0)) {
            Object object = JSONObject.parseObject(string, (Class<Object>) HashMap.class);
            Intrinsics.checkNotNull(object, "null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.String?>");
            HashMap map = (HashMap) object;
            String str2 = (String) map.get("year");
            String str3 = (String) map.get(TvContractCompat.PreviewProgramColumns.COLUMN_GENRE);
            String str4 = (String) map.get("sort");
            String str5 = (String) map.get("rating");
            String str6 = (String) map.get("quality");
            String str7 = (String) map.get("country");
            ArrayList<String> arrayList = this.sort;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sort");
                arrayList = null;
            }
            int iIndexOf = CollectionsKt.indexOf((List<? extends String>) arrayList, str4);
            if (iIndexOf != -1) {
                this.lastSortPos = iIndexOf;
            }
            Iterator<Gener> it = this.geners.iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                } else if (Intrinsics.areEqual(it.next().getId(), str3)) {
                    break;
                } else {
                    i++;
                }
            }
            if (i != -1) {
                this.lastGenerPos = i;
            }
            ArrayList<String> arrayList2 = this.rating;
            if (arrayList2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rating");
                arrayList2 = null;
            }
            int iIndexOf2 = CollectionsKt.indexOf((List<? extends String>) arrayList2, str5);
            if (iIndexOf2 != -1) {
                this.lastRatingPos = iIndexOf2;
            }
            ArrayList<String> arrayList3 = this.resolution;
            if (arrayList3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolution");
                arrayList3 = null;
            }
            int iIndexOf3 = CollectionsKt.indexOf((List<? extends String>) arrayList3, str6);
            if (iIndexOf3 != -1) {
                this.lastResolutionPos = iIndexOf3;
            }
            Iterator<FilterCountry> it2 = this.countryList.iterator();
            int i2 = 0;
            while (true) {
                if (!it2.hasNext()) {
                    i2 = -1;
                    break;
                } else if (Intrinsics.areEqual(it2.next().getCountry(), str7)) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 != -1) {
                this.lastCountryPos = i2;
            }
            Iterator<FilterModel> it3 = this.yearList.iterator();
            int i3 = 0;
            while (true) {
                if (!it3.hasNext()) {
                    i3 = -1;
                    break;
                } else if (Intrinsics.areEqual(it3.next().getTitle(), str2)) {
                    break;
                } else {
                    i3++;
                }
            }
            if (i3 != -1) {
                this.lastYearPos = i3;
            }
        }
        ArrayList arrayList4 = new ArrayList();
        ArrayList<String> arrayList5 = this.sortTitles;
        if (arrayList5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortTitles");
            arrayList5 = null;
        }
        Iterator<T> it4 = arrayList5.iterator();
        while (it4.hasNext()) {
            arrayList4.add(new FilterModel((String) it4.next(), false));
        }
        ((FilterModel) arrayList4.get(this.lastSortPos)).setSelect(true);
        this.sortAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList4, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment.initData.2
            {
                super(i, arrayList4);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, FilterModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.textView);
                if (item.isSelect()) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.color_main_yellow));
                } else {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.white));
                }
                textView.setText(item.getTitle());
            }
        };
        ArrayList arrayList6 = new ArrayList();
        ArrayList<String> arrayList7 = this.resolutionTitles;
        if (arrayList7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionTitles");
            arrayList7 = null;
        }
        Iterator<T> it5 = arrayList7.iterator();
        while (it5.hasNext()) {
            arrayList6.add(new FilterModel((String) it5.next(), false));
        }
        if (CommonExtKt.checkIndexLegal(this.lastResolutionPos, arrayList6)) {
            ((FilterModel) arrayList6.get(this.lastResolutionPos)).setSelect(true);
        }
        this.resolutionAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList6, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment.initData.4
            {
                super(i, arrayList6);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, FilterModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.textView);
                if (item.isSelect()) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.color_main_yellow));
                } else {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.white));
                }
                textView.setText(item.getTitle());
            }
        };
        ArrayList arrayList8 = new ArrayList();
        arrayList8.add(new FilterCountry("All", -1));
        if (this.countryList.size() >= 12) {
            arrayList8.addAll(this.countryList.subList(0, 10));
        }
        if (!StringsKt.isBlank(this.country) && this.selectMorePos != -1) {
            arrayList8.add(new FilterCountry("More(" + this.country + ")", -1));
        } else {
            arrayList8.add(new FilterCountry("More", -1));
        }
        FilterCountry filterCountry = (FilterCountry) CollectionsKt.getOrNull(arrayList8, this.lastCountryPos);
        if (filterCountry != null) {
            filterCountry.setSelect(true);
        }
        this.countryAdapter = new BaseQuickAdapter<FilterCountry, BaseViewHolder>(arrayList8, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment.initData.5
            {
                super(i, arrayList8);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, FilterCountry item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.textView);
                if (item.isSelect()) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.color_main_yellow));
                } else {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.white));
                }
                textView.setText(item.getCountry());
            }
        };
        ArrayList arrayList9 = new ArrayList();
        ArrayList<String> arrayList10 = this.ratingTitles;
        if (arrayList10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingTitles");
            arrayList10 = null;
        }
        Iterator<T> it6 = arrayList10.iterator();
        while (it6.hasNext()) {
            arrayList9.add(new FilterModel((String) it6.next(), false));
        }
        if (CommonExtKt.checkIndexLegal(this.lastRatingPos, arrayList9)) {
            ((FilterModel) arrayList9.get(this.lastRatingPos)).setSelect(true);
        }
        this.ratingAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList9, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment.initData.7
            {
                super(i, arrayList9);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, FilterModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.textView);
                if (item.isSelect()) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.color_main_yellow));
                } else {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.white));
                }
                textView.setText(item.getTitle());
            }
        };
        if (CommonExtKt.checkIndexLegal(this.lastYearPos, this.yearList)) {
            this.yearList.get(this.lastYearPos).setSelect(true);
        }
        this.yearAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(com.movieboxpro.androidtv.R.layout.adapter_filter_item, this.yearList) { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment.initData.8
            {
                ArrayList<FilterModel> arrayList11 = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, FilterModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TextView textView = (TextView) helper.getView(com.movieboxpro.androidtv.R.id.textView);
                if (item.isSelect()) {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.color_main_yellow));
                } else {
                    textView.setTextColor(ContextCompat.getColor(getContext(), com.movieboxpro.androidtv.R.color.white));
                }
                textView.setText(item.getTitle());
            }
        };
        this.generAdapter = new BaseQuickAdapter<Gener, BaseViewHolder>(com.movieboxpro.androidtv.R.layout.adapter_genre_item, this.geners) { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment.initData.9
            {
                ArrayList<Gener> arrayList11 = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, Gener item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                GlideUtils.load(getContext(), item.getImg(), (ImageView) helper.getView(com.movieboxpro.androidtv.R.id.imageView));
                View view = helper.getView(com.movieboxpro.androidtv.R.id.view);
                if (item.isSelect()) {
                    CommonExtKt.visible(view);
                } else {
                    CommonExtKt.gone(view);
                }
            }
        };
        FragmentFilterBinding fragmentFilterBinding = this.binding;
        if (fragmentFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding = null;
        }
        fragmentFilterBinding.rvSort.setSelectedItemAtCentered(true);
        fragmentFilterBinding.rvSort.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView = fragmentFilterBinding.rvSort;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = this.sortAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter2 = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter2);
        fragmentFilterBinding.rvRating.setSelectedItemAtCentered(true);
        fragmentFilterBinding.rvRating.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView2 = fragmentFilterBinding.rvRating;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = this.ratingAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter3 = null;
        }
        tvRecyclerView2.setAdapter(baseQuickAdapter3);
        fragmentFilterBinding.rvResolution.setSelectedItemAtCentered(true);
        fragmentFilterBinding.rvResolution.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView3 = fragmentFilterBinding.rvResolution;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = this.resolutionAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter4 = null;
        }
        tvRecyclerView3.setAdapter(baseQuickAdapter4);
        fragmentFilterBinding.rvCountry.setSelectedItemAtCentered(true);
        fragmentFilterBinding.rvCountry.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView4 = fragmentFilterBinding.rvCountry;
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter5 = this.countryAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter5 = null;
        }
        tvRecyclerView4.setAdapter(baseQuickAdapter5);
        fragmentFilterBinding.rvYear.setSelectedItemAtCentered(true);
        fragmentFilterBinding.rvYear.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView5 = fragmentFilterBinding.rvYear;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = this.yearAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter6 = null;
        }
        tvRecyclerView5.setAdapter(baseQuickAdapter6);
        fragmentFilterBinding.rvGener.setSelectedItemAtCentered(true);
        fragmentFilterBinding.rvGener.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView6 = fragmentFilterBinding.rvGener;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter7 = this.generAdapter;
        if (baseQuickAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter7;
        }
        tvRecyclerView6.setAdapter(baseQuickAdapter);
        TvRecyclerView rvSort = fragmentFilterBinding.rvSort;
        Intrinsics.checkNotNullExpressionValue(rvSort, "rvSort");
        TvRecyclerView rvRating = fragmentFilterBinding.rvRating;
        Intrinsics.checkNotNullExpressionValue(rvRating, "rvRating");
        TvRecyclerView rvResolution = fragmentFilterBinding.rvResolution;
        Intrinsics.checkNotNullExpressionValue(rvResolution, "rvResolution");
        TvRecyclerView rvYear = fragmentFilterBinding.rvYear;
        Intrinsics.checkNotNullExpressionValue(rvYear, "rvYear");
        TvRecyclerView rvGener = fragmentFilterBinding.rvGener;
        Intrinsics.checkNotNullExpressionValue(rvGener, "rvGener");
        scaleListener(rvSort, rvRating, rvResolution, rvYear, rvGener);
    }

    private final void initView() {
        FragmentFilterBinding fragmentFilterBinding;
        FragmentFilterBinding fragmentFilterBinding2;
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(com.movieboxpro.androidtv.R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(com.movieboxpro.androidtv.R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(getActivity());
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
        Bundle arguments = getArguments();
        Boolean boolValueOf = arguments != null ? Boolean.valueOf(arguments.getBoolean("isMovie")) : null;
        Bundle arguments2 = getArguments();
        Boolean bool = boolValueOf;
        if (arguments2 != null ? arguments2.getBoolean("isAll") : false) {
            this.sortTitles = CollectionsKt.arrayListOf("Default", "View", "Added", "Released", "Name");
            this.sort = CollectionsKt.arrayListOf("", "view desc", "last_index_time desc", "solr_get_time desc", "solr_name asc");
            this.ratingTitles = CollectionsKt.arrayListOf("G", "PG", "PG-13", "R", "NC-17");
            this.rating = CollectionsKt.arrayListOf("G", "PG", "PG-13", "R", "NC-17");
            this.resolutionTitles = CollectionsKt.arrayListOf("1080P", "4K", "8K", "BLU-RAY");
            this.resolution = CollectionsKt.arrayListOf("1080p", "4k", "8k", "blu-ray");
            FragmentFilterBinding fragmentFilterBinding3 = this.binding;
            if (fragmentFilterBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterBinding3 = null;
            }
            TextView tvResolution = fragmentFilterBinding3.tvResolution;
            Intrinsics.checkNotNullExpressionValue(tvResolution, "tvResolution");
            CommonExtKt.gone(tvResolution);
            FragmentFilterBinding fragmentFilterBinding4 = this.binding;
            if (fragmentFilterBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterBinding4 = null;
            }
            TvRecyclerView rvResolution = fragmentFilterBinding4.rvResolution;
            Intrinsics.checkNotNullExpressionValue(rvResolution, "rvResolution");
            CommonExtKt.gone(rvResolution);
            FragmentFilterBinding fragmentFilterBinding5 = this.binding;
            if (fragmentFilterBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterBinding5 = null;
            }
            TextView tvRating = fragmentFilterBinding5.tvRating;
            Intrinsics.checkNotNullExpressionValue(tvRating, "tvRating");
            CommonExtKt.gone(tvRating);
            FragmentFilterBinding fragmentFilterBinding6 = this.binding;
            if (fragmentFilterBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterBinding2 = null;
            } else {
                fragmentFilterBinding2 = fragmentFilterBinding6;
            }
            TvRecyclerView rvRating = fragmentFilterBinding2.rvRating;
            Intrinsics.checkNotNullExpressionValue(rvRating, "rvRating");
            CommonExtKt.gone(rvRating);
        } else if (Intrinsics.areEqual((Object) bool, (Object) true)) {
            this.sortTitles = CollectionsKt.arrayListOf("Default", "View", "Added", "Released", "Name");
            this.sort = CollectionsKt.arrayListOf("", "view desc", "last_index_time desc", "solr_get_time desc", "solr_name asc");
            this.ratingTitles = CollectionsKt.arrayListOf("G", "PG", "PG-13", "R", "NC-17");
            this.rating = CollectionsKt.arrayListOf("G", "PG", "PG-13", "R", "NC-17");
            this.resolutionTitles = CollectionsKt.arrayListOf("1080P", "4K", "8K", "BLU-RAY");
            this.resolution = CollectionsKt.arrayListOf("1080p", "4k", "8k", "blu-ray");
        } else {
            this.sortTitles = CollectionsKt.arrayListOf("Default", "View", "Added", "Released", "Name");
            this.sort = CollectionsKt.arrayListOf("", "view desc", "last_index_time desc", "solr_get_time desc", "solr_name asc");
            this.ratingTitles = CollectionsKt.arrayListOf("TV-Y", "TV-Y7", "TV-G", "TV-PG", "TV-14", "TV-MA");
            this.rating = CollectionsKt.arrayListOf("TV-Y", "TV-Y7", "TV-G", "TV-PG", "TV-14", "TV-MA");
            this.resolutionTitles = CollectionsKt.arrayListOf("");
            this.resolution = CollectionsKt.arrayListOf("");
            FragmentFilterBinding fragmentFilterBinding7 = this.binding;
            if (fragmentFilterBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterBinding7 = null;
            }
            TextView tvResolution2 = fragmentFilterBinding7.tvResolution;
            Intrinsics.checkNotNullExpressionValue(tvResolution2, "tvResolution");
            CommonExtKt.gone(tvResolution2);
            FragmentFilterBinding fragmentFilterBinding8 = this.binding;
            if (fragmentFilterBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterBinding8 = null;
            }
            TvRecyclerView rvResolution2 = fragmentFilterBinding8.rvResolution;
            Intrinsics.checkNotNullExpressionValue(rvResolution2, "rvResolution");
            CommonExtKt.gone(rvResolution2);
            FragmentFilterBinding fragmentFilterBinding9 = this.binding;
            if (fragmentFilterBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterBinding = null;
            } else {
                fragmentFilterBinding = fragmentFilterBinding9;
            }
            fragmentFilterBinding.tvRating.setText("TVSHOW RATINGS");
        }
        for (int i = Calendar.getInstance().get(1); 2009 < i; i--) {
            this.yearList.add(new FilterModel(String.valueOf(i)));
        }
        for (int i2 = 1; i2 < 12; i2++) {
            this.yearList.add(new FilterModel((2010 - (i2 * 10)) + "-" + (2010 - ((i2 - 1) * 10))));
        }
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

    /* JADX INFO: compiled from: FilterSearchDialogFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterSearchDialogFragment$Companion;", "", "<init>", "()V", "DEFAULT_SORT", "", "newInstance", "Lcom/movieboxpro/android/view/dialog/FilterSearchDialogFragment;", "isMovie", "", "isAll", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FilterSearchDialogFragment newInstance(boolean isMovie, boolean isAll) {
            FilterSearchDialogFragment filterSearchDialogFragment = new FilterSearchDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isMovie", isMovie);
            bundle.putBoolean("isAll", isAll);
            filterSearchDialogFragment.setArguments(bundle);
            return filterSearchDialogFragment;
        }
    }

    private final void scaleListener(TvRecyclerView... recyclerView) {
        for (TvRecyclerView tvRecyclerView : recyclerView) {
            tvRecyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.FilterSearchDialogFragment$scaleListener$1$1
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
                    this.this$0.focusScale(itemView, 1.2f);
                }
            });
        }
    }
}
