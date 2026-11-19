package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
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
import androidx.media3.extractor.ts.TsExtractor;
import com.adorkable.iosdialog.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.crashlytics.internal.common.IdManager;
import com.movieboxpro.android.listener.CommonItemClickListener;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.FilterModel;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.view.widget.LayoutManagerItemDecoration;
import com.movieboxpro.androidtv.databinding.FragmentFilterBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FilterDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u0000 L2\u00020\u0001:\u0002KLB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u000e\u0010+\u001a\u00020(2\u0006\u0010%\u001a\u00020&J\b\u0010,\u001a\u00020(H\u0016J&\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\u001a\u00103\u001a\u00020(2\u0006\u00104\u001a\u00020.2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010;\u001a\u00020(H\u0002J\u0014\u0010<\u001a\u00020(2\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00100>J\u001e\u0010?\u001a\u00020(2\u0016\u0010=\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0005j\b\u0012\u0004\u0012\u00020\u000e`\u0007J\b\u0010@\u001a\u00020(H\u0002J!\u0010A\u001a\u00020(2\u0012\u0010B\u001a\n\u0012\u0006\b\u0001\u0012\u00020D0C\"\u00020DH\u0002¢\u0006\u0002\u0010EJ\b\u0010F\u001a\u00020(H\u0003J\u0018\u0010G\u001a\u00020(2\u0006\u0010H\u001a\u00020.2\u0006\u0010I\u001a\u00020JH\u0002R\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082.¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\u0005j\b\u0012\u0004\u0012\u00020\u000e`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0005j\b\u0012\u0004\u0012\u00020\u0010`\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u001bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001d0\u001bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u001d0\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010%\u001a\u0004\u0018\u00010&X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00105\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00107\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006M"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "sortTitles", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "sort", "ratingTitles", "rating", "resolutionTitles", "resolution", "countryList", "Lcom/movieboxpro/android/model/FilterCountry;", "geners", "Lcom/movieboxpro/android/model/common/Gener;", "country", "selectMorePos", "", "imdbRatingStart", "imdbRatingEnd", "tomatoRatingStart", "tomatoRatingEnd", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "sortAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/FilterModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "yearAdapter", "ratingAdapter", "resolutionAdapter", "countryAdapter", "generAdapter", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentFilterBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/FilterDialogFragment$OnFilterDoneListener;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setOnListener", "onStart", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "lastSortPos", "lastYearPos", "lastGenerPos", "lastRatingPos", "lastResolutionPos", "lastCountryPos", "initListener", "setGener", "list", "", "setCountry", "initData", "scaleListener", "recyclerView", "", "Lcom/owen/tvrecyclerview/widget/TvRecyclerView;", "([Lcom/owen/tvrecyclerview/widget/TvRecyclerView;)V", "initView", "focusScale", "v", "scale", "", "OnFilterDoneListener", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FilterDialogFragment extends DialogFragment {

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
    private ArrayList<FilterCountry> countryList = new ArrayList<>();
    private ArrayList<Gener> geners = new ArrayList<>();
    private String country = "";
    private int selectMorePos = -1;
    private String imdbRatingStart = IdManager.DEFAULT_VERSION_NAME;
    private String imdbRatingEnd = "10.0";
    private String tomatoRatingStart = "0";
    private String tomatoRatingEnd = "100";
    private int lastYearPos = -1;
    private int lastGenerPos = -1;
    private int lastRatingPos = -1;
    private int lastResolutionPos = -1;

    /* JADX INFO: compiled from: FilterDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\bf\u0018\u00002\u00020\u0001JH\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0005H&¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterDialogFragment$OnFilterDoneListener;", "", "onFilterDone", "", "sort", "", "year", "gener", "rating", "quality", "country", "imdbRating", "tomatoMeter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface OnFilterDoneListener {
        void onFilterDone(String sort, String year, String gener, String rating, String quality, String country, String imdbRating, String tomatoMeter);
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
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda7
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    return FilterDialogFragment.onViewCreated$lambda$1(this.f$0, dialogInterface, i, keyEvent);
                }
            });
        }
        initView();
        initData();
        initListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$1(FilterDialogFragment filterDialogFragment, DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        View currentFocus;
        View currentFocus2;
        View currentFocus3;
        View currentFocus4;
        if (keyEvent.getAction() != 0) {
            return false;
        }
        View viewFocusSearch = null;
        switch (i) {
            case 37:
                Dialog dialog = filterDialogFragment.getDialog();
                if (dialog != null && (currentFocus = dialog.getCurrentFocus()) != null) {
                    viewFocusSearch = currentFocus.focusSearch(33);
                }
                if (viewFocusSearch != null) {
                    viewFocusSearch.requestFocus();
                }
                return true;
            case 38:
                Dialog dialog2 = filterDialogFragment.getDialog();
                if (dialog2 != null && (currentFocus2 = dialog2.getCurrentFocus()) != null) {
                    viewFocusSearch = currentFocus2.focusSearch(17);
                }
                if (viewFocusSearch != null) {
                    viewFocusSearch.requestFocus();
                }
                return true;
            case 39:
                Dialog dialog3 = filterDialogFragment.getDialog();
                if (dialog3 != null && (currentFocus3 = dialog3.getCurrentFocus()) != null) {
                    viewFocusSearch = currentFocus3.focusSearch(TsExtractor.TS_STREAM_TYPE_HDMV_DTS);
                }
                if (viewFocusSearch != null) {
                    viewFocusSearch.requestFocus();
                }
                return true;
            case 40:
                Dialog dialog4 = filterDialogFragment.getDialog();
                if (dialog4 != null && (currentFocus4 = dialog4.getCurrentFocus()) != null) {
                    viewFocusSearch = currentFocus4.focusSearch(66);
                }
                if (viewFocusSearch != null) {
                    viewFocusSearch.requestFocus();
                }
                return true;
            default:
                return false;
        }
    }

    private final void initListener() {
        FragmentFilterBinding fragmentFilterBinding = this.binding;
        FragmentFilterBinding fragmentFilterBinding2 = null;
        if (fragmentFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding = null;
        }
        fragmentFilterBinding.tvImdbStart.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterDialogFragment.initListener$lambda$4(this.f$0, view);
            }
        });
        FragmentFilterBinding fragmentFilterBinding3 = this.binding;
        if (fragmentFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding3 = null;
        }
        fragmentFilterBinding3.tvImdbEnd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterDialogFragment.initListener$lambda$7(this.f$0, view);
            }
        });
        FragmentFilterBinding fragmentFilterBinding4 = this.binding;
        if (fragmentFilterBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding4 = null;
        }
        fragmentFilterBinding4.tvTomatoStart.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterDialogFragment.initListener$lambda$10(this.f$0, view);
            }
        });
        FragmentFilterBinding fragmentFilterBinding5 = this.binding;
        if (fragmentFilterBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding5 = null;
        }
        fragmentFilterBinding5.tvTomatoEnd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterDialogFragment.initListener$lambda$13(this.f$0, view);
            }
        });
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter = this.sortAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda14
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                FilterDialogFragment.initListener$lambda$14(this.f$0, baseQuickAdapter2, view, i);
            }
        });
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = this.ratingAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter2 = null;
        }
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda15
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                FilterDialogFragment.initListener$lambda$15(this.f$0, baseQuickAdapter3, view, i);
            }
        });
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = this.resolutionAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda16
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter4, View view, int i) {
                FilterDialogFragment.initListener$lambda$16(this.f$0, baseQuickAdapter4, view, i);
            }
        });
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = this.yearAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter4 = null;
        }
        baseQuickAdapter4.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter5, View view, int i) {
                FilterDialogFragment.initListener$lambda$17(this.f$0, baseQuickAdapter5, view, i);
            }
        });
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter5 = this.generAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            baseQuickAdapter5 = null;
        }
        baseQuickAdapter5.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter6, View view, int i) {
                FilterDialogFragment.initListener$lambda$18(this.f$0, baseQuickAdapter6, view, i);
            }
        });
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter6 = this.countryAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter6 = null;
        }
        baseQuickAdapter6.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter7, View view, int i) {
                FilterDialogFragment.initListener$lambda$19(this.f$0, baseQuickAdapter7, view, i);
            }
        });
        FragmentFilterBinding fragmentFilterBinding6 = this.binding;
        if (fragmentFilterBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding6 = null;
        }
        fragmentFilterBinding6.tvDone.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterDialogFragment.initListener$lambda$20(this.f$0, view);
            }
        });
        FragmentFilterBinding fragmentFilterBinding7 = this.binding;
        if (fragmentFilterBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentFilterBinding2 = fragmentFilterBinding7;
        }
        fragmentFilterBinding2.tvReset.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FilterDialogFragment.initListener$lambda$21(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(final FilterDialogFragment filterDialogFragment, View view) {
        final ArrayList arrayList = new ArrayList();
        int i = (int) (Float.parseFloat(filterDialogFragment.imdbRatingEnd) * 10);
        int i2 = 0;
        if (i >= 0) {
            int i3 = 0;
            while (true) {
                arrayList.add(String.valueOf(((double) i3) / 10.0d));
                if (i3 == i) {
                    break;
                } else {
                    i3++;
                }
            }
        }
        ArrayList<String> arrayList2 = arrayList;
        for (String str : arrayList2) {
            FragmentFilterBinding fragmentFilterBinding = filterDialogFragment.binding;
            if (fragmentFilterBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterBinding = null;
            }
            if (Intrinsics.areEqual(str, fragmentFilterBinding.tvImdbStart.getText().toString())) {
                ChooseFilterRatingDialogFragment chooseFilterRatingDialogFragmentNewInstance = ChooseFilterRatingDialogFragment.INSTANCE.newInstance(i2);
                chooseFilterRatingDialogFragmentNewInstance.setData(arrayList2);
                chooseFilterRatingDialogFragmentNewInstance.setListener(new CommonItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda4
                    @Override // com.movieboxpro.android.listener.CommonItemClickListener
                    public final void onItemClicked(int i4, String str2) {
                        FilterDialogFragment.initListener$lambda$4$lambda$3(this.f$0, arrayList, i4, str2);
                    }
                });
                chooseFilterRatingDialogFragmentNewInstance.show(filterDialogFragment.getChildFragmentManager(), "ChooseFilterRatingDialogFragment");
            }
            i2++;
        }
        i2 = -1;
        ChooseFilterRatingDialogFragment chooseFilterRatingDialogFragmentNewInstance2 = ChooseFilterRatingDialogFragment.INSTANCE.newInstance(i2);
        chooseFilterRatingDialogFragmentNewInstance2.setData(arrayList2);
        chooseFilterRatingDialogFragmentNewInstance2.setListener(new CommonItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda4
            @Override // com.movieboxpro.android.listener.CommonItemClickListener
            public final void onItemClicked(int i4, String str2) {
                FilterDialogFragment.initListener$lambda$4$lambda$3(this.f$0, arrayList, i4, str2);
            }
        });
        chooseFilterRatingDialogFragmentNewInstance2.show(filterDialogFragment.getChildFragmentManager(), "ChooseFilterRatingDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4$lambda$3(FilterDialogFragment filterDialogFragment, ArrayList arrayList, int i, String str) {
        Object obj = arrayList.get(i);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        filterDialogFragment.imdbRatingStart = (String) obj;
        FragmentFilterBinding fragmentFilterBinding = filterDialogFragment.binding;
        if (fragmentFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding = null;
        }
        fragmentFilterBinding.tvImdbStart.setText((CharSequence) arrayList.get(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7(final FilterDialogFragment filterDialogFragment, View view) {
        final ArrayList arrayList = new ArrayList();
        for (int i = (int) (Float.parseFloat(filterDialogFragment.imdbRatingStart) * 10); i < 101; i++) {
            arrayList.add(String.valueOf(((double) i) / 10.0d));
        }
        ArrayList<String> arrayList2 = arrayList;
        int i2 = 0;
        for (String str : arrayList2) {
            FragmentFilterBinding fragmentFilterBinding = filterDialogFragment.binding;
            if (fragmentFilterBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterBinding = null;
            }
            if (Intrinsics.areEqual(str, fragmentFilterBinding.tvImdbEnd.getText().toString())) {
                ChooseFilterRatingDialogFragment chooseFilterRatingDialogFragmentNewInstance = ChooseFilterRatingDialogFragment.INSTANCE.newInstance(i2);
                chooseFilterRatingDialogFragmentNewInstance.setData(arrayList2);
                chooseFilterRatingDialogFragmentNewInstance.setListener(new CommonItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda6
                    @Override // com.movieboxpro.android.listener.CommonItemClickListener
                    public final void onItemClicked(int i3, String str2) {
                        FilterDialogFragment.initListener$lambda$7$lambda$6(this.f$0, arrayList, i3, str2);
                    }
                });
                chooseFilterRatingDialogFragmentNewInstance.show(filterDialogFragment.getChildFragmentManager(), "ChooseFilterRatingDialogFragment");
            }
            i2++;
        }
        i2 = -1;
        ChooseFilterRatingDialogFragment chooseFilterRatingDialogFragmentNewInstance2 = ChooseFilterRatingDialogFragment.INSTANCE.newInstance(i2);
        chooseFilterRatingDialogFragmentNewInstance2.setData(arrayList2);
        chooseFilterRatingDialogFragmentNewInstance2.setListener(new CommonItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda6
            @Override // com.movieboxpro.android.listener.CommonItemClickListener
            public final void onItemClicked(int i3, String str2) {
                FilterDialogFragment.initListener$lambda$7$lambda$6(this.f$0, arrayList, i3, str2);
            }
        });
        chooseFilterRatingDialogFragmentNewInstance2.show(filterDialogFragment.getChildFragmentManager(), "ChooseFilterRatingDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7$lambda$6(FilterDialogFragment filterDialogFragment, ArrayList arrayList, int i, String str) {
        Object obj = arrayList.get(i);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        filterDialogFragment.imdbRatingEnd = (String) obj;
        FragmentFilterBinding fragmentFilterBinding = filterDialogFragment.binding;
        if (fragmentFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding = null;
        }
        fragmentFilterBinding.tvImdbEnd.setText((CharSequence) arrayList.get(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$10(final FilterDialogFragment filterDialogFragment, View view) {
        final ArrayList arrayList = new ArrayList();
        int i = Integer.parseInt(filterDialogFragment.tomatoRatingEnd);
        int i2 = 0;
        if (i >= 0) {
            int i3 = 0;
            while (true) {
                arrayList.add(String.valueOf(i3));
                if (i3 == i) {
                    break;
                } else {
                    i3++;
                }
            }
        }
        ArrayList<String> arrayList2 = arrayList;
        for (String str : arrayList2) {
            FragmentFilterBinding fragmentFilterBinding = filterDialogFragment.binding;
            if (fragmentFilterBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterBinding = null;
            }
            if (Intrinsics.areEqual(str, fragmentFilterBinding.tvTomatoStart.getText().toString())) {
                ChooseFilterRatingDialogFragment chooseFilterRatingDialogFragmentNewInstance = ChooseFilterRatingDialogFragment.INSTANCE.newInstance(i2);
                chooseFilterRatingDialogFragmentNewInstance.setData(arrayList2);
                chooseFilterRatingDialogFragmentNewInstance.setListener(new CommonItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda5
                    @Override // com.movieboxpro.android.listener.CommonItemClickListener
                    public final void onItemClicked(int i4, String str2) {
                        FilterDialogFragment.initListener$lambda$10$lambda$9(this.f$0, arrayList, i4, str2);
                    }
                });
                chooseFilterRatingDialogFragmentNewInstance.show(filterDialogFragment.getChildFragmentManager(), "ChooseFilterRatingDialogFragment");
            }
            i2++;
        }
        i2 = -1;
        ChooseFilterRatingDialogFragment chooseFilterRatingDialogFragmentNewInstance2 = ChooseFilterRatingDialogFragment.INSTANCE.newInstance(i2);
        chooseFilterRatingDialogFragmentNewInstance2.setData(arrayList2);
        chooseFilterRatingDialogFragmentNewInstance2.setListener(new CommonItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda5
            @Override // com.movieboxpro.android.listener.CommonItemClickListener
            public final void onItemClicked(int i4, String str2) {
                FilterDialogFragment.initListener$lambda$10$lambda$9(this.f$0, arrayList, i4, str2);
            }
        });
        chooseFilterRatingDialogFragmentNewInstance2.show(filterDialogFragment.getChildFragmentManager(), "ChooseFilterRatingDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$10$lambda$9(FilterDialogFragment filterDialogFragment, ArrayList arrayList, int i, String str) {
        Object obj = arrayList.get(i);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        filterDialogFragment.tomatoRatingStart = (String) obj;
        FragmentFilterBinding fragmentFilterBinding = filterDialogFragment.binding;
        if (fragmentFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding = null;
        }
        fragmentFilterBinding.tvTomatoStart.setText((CharSequence) arrayList.get(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$13(final FilterDialogFragment filterDialogFragment, View view) {
        final ArrayList arrayList = new ArrayList();
        for (int i = Integer.parseInt(filterDialogFragment.tomatoRatingStart); i < 101; i++) {
            arrayList.add(String.valueOf(i));
        }
        ArrayList<String> arrayList2 = arrayList;
        int i2 = 0;
        for (String str : arrayList2) {
            FragmentFilterBinding fragmentFilterBinding = filterDialogFragment.binding;
            if (fragmentFilterBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentFilterBinding = null;
            }
            if (Intrinsics.areEqual(str, fragmentFilterBinding.tvTomatoEnd.getText().toString())) {
                ChooseFilterRatingDialogFragment chooseFilterRatingDialogFragmentNewInstance = ChooseFilterRatingDialogFragment.INSTANCE.newInstance(i2);
                chooseFilterRatingDialogFragmentNewInstance.setData(arrayList2);
                chooseFilterRatingDialogFragmentNewInstance.setListener(new CommonItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda0
                    @Override // com.movieboxpro.android.listener.CommonItemClickListener
                    public final void onItemClicked(int i3, String str2) {
                        FilterDialogFragment.initListener$lambda$13$lambda$12(this.f$0, arrayList, i3, str2);
                    }
                });
                chooseFilterRatingDialogFragmentNewInstance.show(filterDialogFragment.getChildFragmentManager(), "ChooseFilterRatingDialogFragment");
            }
            i2++;
        }
        i2 = -1;
        ChooseFilterRatingDialogFragment chooseFilterRatingDialogFragmentNewInstance2 = ChooseFilterRatingDialogFragment.INSTANCE.newInstance(i2);
        chooseFilterRatingDialogFragmentNewInstance2.setData(arrayList2);
        chooseFilterRatingDialogFragmentNewInstance2.setListener(new CommonItemClickListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$$ExternalSyntheticLambda0
            @Override // com.movieboxpro.android.listener.CommonItemClickListener
            public final void onItemClicked(int i3, String str2) {
                FilterDialogFragment.initListener$lambda$13$lambda$12(this.f$0, arrayList, i3, str2);
            }
        });
        chooseFilterRatingDialogFragmentNewInstance2.show(filterDialogFragment.getChildFragmentManager(), "ChooseFilterRatingDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$13$lambda$12(FilterDialogFragment filterDialogFragment, ArrayList arrayList, int i, String str) {
        Object obj = arrayList.get(i);
        Intrinsics.checkNotNullExpressionValue(obj, "get(...)");
        filterDialogFragment.tomatoRatingEnd = (String) obj;
        FragmentFilterBinding fragmentFilterBinding = filterDialogFragment.binding;
        if (fragmentFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding = null;
        }
        fragmentFilterBinding.tvTomatoEnd.setText((CharSequence) arrayList.get(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$14(FilterDialogFragment filterDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        if (filterDialogFragment.lastSortPos == i) {
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = filterDialogFragment.sortAdapter;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = null;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter2 = null;
        }
        FilterModel itemOrNull = baseQuickAdapter2.getItemOrNull(filterDialogFragment.lastSortPos);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterDialogFragment.sortAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter4 = null;
        }
        FilterModel item = baseQuickAdapter4.getItem(i);
        if (item != null) {
            item.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterDialogFragment.sortAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter5 = null;
        }
        baseQuickAdapter5.notifyItemChanged(i);
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterDialogFragment.sortAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
        } else {
            baseQuickAdapter3 = baseQuickAdapter6;
        }
        baseQuickAdapter3.notifyItemChanged(filterDialogFragment.lastSortPos);
        filterDialogFragment.lastSortPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$15(FilterDialogFragment filterDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterDialogFragment.lastRatingPos;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            filterDialogFragment.lastRatingPos = -1;
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterDialogFragment.ratingAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter3 = null;
            }
            FilterModel item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterDialogFragment.ratingAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterDialogFragment.ratingAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterDialogFragment.ratingAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter6.getItemOrNull(filterDialogFragment.lastRatingPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterDialogFragment.ratingAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterDialogFragment.lastRatingPos);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterDialogFragment.ratingAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter8 = null;
        }
        FilterModel item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterDialogFragment.ratingAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterDialogFragment.lastRatingPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$16(FilterDialogFragment filterDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterDialogFragment.lastResolutionPos;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            filterDialogFragment.lastResolutionPos = -1;
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterDialogFragment.resolutionAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter3 = null;
            }
            FilterModel item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterDialogFragment.resolutionAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterDialogFragment.resolutionAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterDialogFragment.resolutionAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter6.getItemOrNull(filterDialogFragment.lastResolutionPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterDialogFragment.resolutionAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterDialogFragment.lastResolutionPos);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterDialogFragment.resolutionAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter8 = null;
        }
        FilterModel item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterDialogFragment.resolutionAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterDialogFragment.lastResolutionPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$17(FilterDialogFragment filterDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterDialogFragment.lastYearPos;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            filterDialogFragment.lastYearPos = -1;
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterDialogFragment.yearAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter3 = null;
            }
            FilterModel item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterDialogFragment.yearAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            return;
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterDialogFragment.yearAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterDialogFragment.yearAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter6.getItemOrNull(filterDialogFragment.lastYearPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterDialogFragment.yearAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterDialogFragment.lastYearPos);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterDialogFragment.yearAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter8 = null;
        }
        FilterModel item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterDialogFragment.yearAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterDialogFragment.lastYearPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$18(FilterDialogFragment filterDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int i2 = filterDialogFragment.lastGenerPos;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter2 = null;
        if (i2 == i) {
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter3 = filterDialogFragment.generAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter3 = null;
            }
            Gener item = baseQuickAdapter3.getItem(i);
            if (item != null) {
                item.setSelect(false);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter4 = filterDialogFragment.generAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(i);
            filterDialogFragment.lastGenerPos = -1;
            return;
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter5 = filterDialogFragment.generAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            baseQuickAdapter5 = null;
        }
        if (CommonExtKt.checkIndexLegal(i2, baseQuickAdapter5.getData())) {
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter6 = filterDialogFragment.generAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter6 = null;
            }
            Gener itemOrNull = baseQuickAdapter6.getItemOrNull(filterDialogFragment.lastGenerPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter7 = filterDialogFragment.generAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterDialogFragment.lastGenerPos);
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter8 = filterDialogFragment.generAdapter;
        if (baseQuickAdapter8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
            baseQuickAdapter8 = null;
        }
        Gener item2 = baseQuickAdapter8.getItem(i);
        if (item2 != null) {
            item2.setSelect(true);
        }
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter9 = filterDialogFragment.generAdapter;
        if (baseQuickAdapter9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter9;
        }
        baseQuickAdapter2.notifyItemChanged(i);
        filterDialogFragment.lastGenerPos = i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$19(final FilterDialogFragment filterDialogFragment, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter2 = filterDialogFragment.countryAdapter;
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter3 = null;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter2 = null;
        }
        FilterCountry item = baseQuickAdapter2.getItem(i);
        String country = item.getCountry();
        Intrinsics.checkNotNull(country);
        if (StringsKt.contains$default((CharSequence) country, (CharSequence) "More", false, 2, (Object) null)) {
            ChooseCountryDialog chooseCountryDialogNewInstance = ChooseCountryDialog.INSTANCE.newInstance(filterDialogFragment.country);
            chooseCountryDialogNewInstance.setListener(new ArrayList<>(filterDialogFragment.countryList), new ChooseCountryDialog.OnLanguageListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$initListener$10$1
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
            chooseCountryDialogNewInstance.show(filterDialogFragment.getChildFragmentManager(), "ChooseCountryDialog");
            return;
        }
        if (filterDialogFragment.lastCountryPos == i) {
            return;
        }
        item.setSelect(true);
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter4 = filterDialogFragment.countryAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter4 = null;
        }
        FilterCountry itemOrNull = baseQuickAdapter4.getItemOrNull(filterDialogFragment.lastCountryPos);
        if (itemOrNull != null) {
            itemOrNull.setSelect(false);
        }
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter5 = filterDialogFragment.countryAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter5 = null;
        }
        baseQuickAdapter5.notifyItemChanged(i);
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter6 = filterDialogFragment.countryAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
        } else {
            baseQuickAdapter3 = baseQuickAdapter6;
        }
        baseQuickAdapter3.notifyItemChanged(filterDialogFragment.lastCountryPos);
        filterDialogFragment.lastCountryPos = i;
        if (Intrinsics.areEqual(item.getCountry(), "All")) {
            filterDialogFragment.country = "";
        } else {
            String country2 = item.getCountry();
            Intrinsics.checkNotNullExpressionValue(country2, "getCountry(...)");
            filterDialogFragment.country = country2;
        }
        filterDialogFragment.selectMorePos = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$20(FilterDialogFragment filterDialogFragment, View view) {
        String str;
        String str2;
        String title;
        String str3;
        String str4 = "";
        if (Intrinsics.areEqual(filterDialogFragment.imdbRatingStart, "0") && Intrinsics.areEqual(filterDialogFragment.imdbRatingEnd, "10.0")) {
            str = "";
        } else {
            str = filterDialogFragment.imdbRatingStart + "-" + filterDialogFragment.imdbRatingEnd;
        }
        if (Intrinsics.areEqual(filterDialogFragment.tomatoRatingStart, "0") && Intrinsics.areEqual(filterDialogFragment.tomatoRatingEnd, "100")) {
            str2 = "";
        } else {
            str2 = filterDialogFragment.tomatoRatingStart + "-" + filterDialogFragment.tomatoRatingEnd;
        }
        OnFilterDoneListener onFilterDoneListener = filterDialogFragment.listener;
        if (onFilterDoneListener != null) {
            ArrayList<String> arrayList = filterDialogFragment.sort;
            ArrayList<String> arrayList2 = null;
            if (arrayList == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sort");
                arrayList = null;
            }
            String str5 = arrayList.get(filterDialogFragment.lastSortPos);
            Intrinsics.checkNotNullExpressionValue(str5, "get(...)");
            String str6 = str5;
            if (filterDialogFragment.lastYearPos == -1) {
                title = "";
            } else {
                BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter = filterDialogFragment.yearAdapter;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                    baseQuickAdapter = null;
                }
                title = baseQuickAdapter.getData().get(filterDialogFragment.lastYearPos).getTitle();
            }
            Intrinsics.checkNotNull(title);
            int i = filterDialogFragment.lastGenerPos;
            String id = i == -1 ? "" : filterDialogFragment.geners.get(i).getId();
            Intrinsics.checkNotNull(id);
            if (filterDialogFragment.lastRatingPos == -1) {
                str3 = "";
            } else {
                ArrayList<String> arrayList3 = filterDialogFragment.rating;
                if (arrayList3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rating");
                    arrayList3 = null;
                }
                String str7 = arrayList3.get(filterDialogFragment.lastRatingPos);
                Intrinsics.checkNotNullExpressionValue(str7, "get(...)");
                str3 = str7;
            }
            if (filterDialogFragment.lastResolutionPos != -1) {
                ArrayList<String> arrayList4 = filterDialogFragment.resolution;
                if (arrayList4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("resolution");
                } else {
                    arrayList2 = arrayList4;
                }
                String str8 = arrayList2.get(filterDialogFragment.lastResolutionPos);
                Intrinsics.checkNotNullExpressionValue(str8, "get(...)");
                str4 = str8;
            }
            onFilterDoneListener.onFilterDone(str6, title, id, str3, str4, filterDialogFragment.country, str, str2);
        }
        filterDialogFragment.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$21(FilterDialogFragment filterDialogFragment, View view) {
        filterDialogFragment.imdbRatingStart = IdManager.DEFAULT_VERSION_NAME;
        filterDialogFragment.imdbRatingEnd = "10.0";
        filterDialogFragment.tomatoRatingStart = "0";
        filterDialogFragment.tomatoRatingEnd = "100";
        FragmentFilterBinding fragmentFilterBinding = filterDialogFragment.binding;
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter = null;
        if (fragmentFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding = null;
        }
        fragmentFilterBinding.tvImdbStart.setText(filterDialogFragment.imdbRatingStart);
        FragmentFilterBinding fragmentFilterBinding2 = filterDialogFragment.binding;
        if (fragmentFilterBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding2 = null;
        }
        fragmentFilterBinding2.tvImdbEnd.setText(filterDialogFragment.imdbRatingEnd);
        FragmentFilterBinding fragmentFilterBinding3 = filterDialogFragment.binding;
        if (fragmentFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding3 = null;
        }
        fragmentFilterBinding3.tvTomatoStart.setText(filterDialogFragment.tomatoRatingStart);
        FragmentFilterBinding fragmentFilterBinding4 = filterDialogFragment.binding;
        if (fragmentFilterBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding4 = null;
        }
        fragmentFilterBinding4.tvTomatoEnd.setText(filterDialogFragment.tomatoRatingEnd);
        if (filterDialogFragment.lastSortPos != 0) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = filterDialogFragment.sortAdapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter2 = null;
            }
            FilterModel item = baseQuickAdapter2.getItem(0);
            if (item != null) {
                item.setSelect(true);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = filterDialogFragment.sortAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter3 = null;
            }
            baseQuickAdapter3.notifyItemChanged(0);
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = filterDialogFragment.sortAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter4 = null;
            }
            FilterModel itemOrNull = baseQuickAdapter4.getItemOrNull(filterDialogFragment.lastSortPos);
            if (itemOrNull != null) {
                itemOrNull.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter5 = filterDialogFragment.sortAdapter;
            if (baseQuickAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
                baseQuickAdapter5 = null;
            }
            baseQuickAdapter5.notifyItemChanged(filterDialogFragment.lastSortPos);
            filterDialogFragment.lastSortPos = 0;
        }
        if (filterDialogFragment.lastRatingPos != -1) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = filterDialogFragment.ratingAdapter;
            if (baseQuickAdapter6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter6 = null;
            }
            FilterModel itemOrNull2 = baseQuickAdapter6.getItemOrNull(filterDialogFragment.lastRatingPos);
            if (itemOrNull2 != null) {
                itemOrNull2.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter7 = filterDialogFragment.ratingAdapter;
            if (baseQuickAdapter7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
                baseQuickAdapter7 = null;
            }
            baseQuickAdapter7.notifyItemChanged(filterDialogFragment.lastRatingPos);
            filterDialogFragment.lastRatingPos = -1;
        }
        if (filterDialogFragment.lastResolutionPos != -1) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter8 = filterDialogFragment.resolutionAdapter;
            if (baseQuickAdapter8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter8 = null;
            }
            FilterModel itemOrNull3 = baseQuickAdapter8.getItemOrNull(filterDialogFragment.lastResolutionPos);
            if (itemOrNull3 != null) {
                itemOrNull3.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter9 = filterDialogFragment.resolutionAdapter;
            if (baseQuickAdapter9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
                baseQuickAdapter9 = null;
            }
            baseQuickAdapter9.notifyItemChanged(filterDialogFragment.lastResolutionPos);
            filterDialogFragment.lastResolutionPos = -1;
        }
        if (filterDialogFragment.lastYearPos != -1) {
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter10 = filterDialogFragment.yearAdapter;
            if (baseQuickAdapter10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter10 = null;
            }
            FilterModel itemOrNull4 = baseQuickAdapter10.getItemOrNull(filterDialogFragment.lastYearPos);
            if (itemOrNull4 != null) {
                itemOrNull4.setSelect(false);
            }
            BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter11 = filterDialogFragment.yearAdapter;
            if (baseQuickAdapter11 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
                baseQuickAdapter11 = null;
            }
            baseQuickAdapter11.notifyItemChanged(filterDialogFragment.lastYearPos);
            filterDialogFragment.lastYearPos = -1;
        }
        if (filterDialogFragment.lastGenerPos != -1) {
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter12 = filterDialogFragment.generAdapter;
            if (baseQuickAdapter12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter12 = null;
            }
            Gener itemOrNull5 = baseQuickAdapter12.getItemOrNull(filterDialogFragment.lastGenerPos);
            if (itemOrNull5 != null) {
                itemOrNull5.setSelect(false);
            }
            BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter13 = filterDialogFragment.generAdapter;
            if (baseQuickAdapter13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
                baseQuickAdapter13 = null;
            }
            baseQuickAdapter13.notifyItemChanged(filterDialogFragment.lastGenerPos);
            filterDialogFragment.lastGenerPos = -1;
        }
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter14 = filterDialogFragment.countryAdapter;
        if (baseQuickAdapter14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter14 = null;
        }
        FilterCountry itemOrNull6 = baseQuickAdapter14.getItemOrNull(filterDialogFragment.lastCountryPos);
        if (itemOrNull6 != null) {
            itemOrNull6.setSelect(false);
        }
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter15 = filterDialogFragment.countryAdapter;
        if (baseQuickAdapter15 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter15 = null;
        }
        baseQuickAdapter15.notifyItemChanged(filterDialogFragment.lastCountryPos);
        filterDialogFragment.lastCountryPos = 0;
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter16 = filterDialogFragment.countryAdapter;
        if (baseQuickAdapter16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter16 = null;
        }
        baseQuickAdapter16.getItem(filterDialogFragment.lastCountryPos).setSelect(true);
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter17 = filterDialogFragment.countryAdapter;
        if (baseQuickAdapter17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter17;
        }
        baseQuickAdapter.notifyItemChanged(filterDialogFragment.lastCountryPos);
        filterDialogFragment.country = "";
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
        FragmentFilterBinding fragmentFilterBinding = this.binding;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter = null;
        if (fragmentFilterBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding = null;
        }
        fragmentFilterBinding.tvTomatoStart.setText(this.tomatoRatingStart);
        FragmentFilterBinding fragmentFilterBinding2 = this.binding;
        if (fragmentFilterBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding2 = null;
        }
        fragmentFilterBinding2.tvTomatoEnd.setText(this.tomatoRatingEnd);
        FragmentFilterBinding fragmentFilterBinding3 = this.binding;
        if (fragmentFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding3 = null;
        }
        fragmentFilterBinding3.tvImdbStart.setText(this.imdbRatingStart);
        FragmentFilterBinding fragmentFilterBinding4 = this.binding;
        if (fragmentFilterBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding4 = null;
        }
        fragmentFilterBinding4.tvImdbEnd.setText(this.imdbRatingEnd);
        ArrayList arrayList = new ArrayList();
        ArrayList<String> arrayList2 = this.sortTitles;
        if (arrayList2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortTitles");
            arrayList2 = null;
        }
        Iterator<T> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(new FilterModel((String) it.next(), false));
        }
        ((FilterModel) arrayList.get(this.lastSortPos)).setSelect(true);
        this.sortAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment.initData.2
            {
                super(i, arrayList);
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
        ArrayList arrayList3 = new ArrayList();
        ArrayList<String> arrayList4 = this.resolutionTitles;
        if (arrayList4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionTitles");
            arrayList4 = null;
        }
        Iterator<T> it2 = arrayList4.iterator();
        while (it2.hasNext()) {
            arrayList3.add(new FilterModel((String) it2.next(), false));
        }
        if (CommonExtKt.checkIndexLegal(this.lastResolutionPos, arrayList3)) {
            ((FilterModel) arrayList3.get(this.lastResolutionPos)).setSelect(true);
        }
        this.resolutionAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList3, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment.initData.4
            {
                super(i, arrayList3);
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
        ArrayList arrayList5 = new ArrayList();
        arrayList5.add(new FilterCountry("All", -1));
        if (this.countryList.size() >= 12) {
            arrayList5.addAll(this.countryList.subList(0, 10));
        }
        if (!StringsKt.isBlank(this.country) && this.selectMorePos != -1) {
            arrayList5.add(new FilterCountry("More(" + this.country + ")", -1));
        } else {
            arrayList5.add(new FilterCountry("More", -1));
        }
        FilterCountry filterCountry = (FilterCountry) CollectionsKt.getOrNull(arrayList5, this.lastCountryPos);
        if (filterCountry != null) {
            filterCountry.setSelect(true);
        }
        this.countryAdapter = new BaseQuickAdapter<FilterCountry, BaseViewHolder>(arrayList5, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment.initData.5
            {
                super(i, arrayList5);
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
        ArrayList arrayList6 = new ArrayList();
        ArrayList<String> arrayList7 = this.ratingTitles;
        if (arrayList7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingTitles");
            arrayList7 = null;
        }
        Iterator<T> it3 = arrayList7.iterator();
        while (it3.hasNext()) {
            arrayList6.add(new FilterModel((String) it3.next(), false));
        }
        if (CommonExtKt.checkIndexLegal(this.lastRatingPos, arrayList6)) {
            ((FilterModel) arrayList6.get(this.lastRatingPos)).setSelect(true);
        }
        this.ratingAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList6, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment.initData.7
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
        for (int i = Calendar.getInstance().get(1); 2009 < i; i--) {
            arrayList8.add(new FilterModel(String.valueOf(i)));
        }
        for (int i2 = 1; i2 < 12; i2++) {
            arrayList8.add(new FilterModel((2010 - (i2 * 10)) + "-" + (2010 - ((i2 - 1) * 10))));
        }
        if (CommonExtKt.checkIndexLegal(this.lastYearPos, arrayList8)) {
            ((FilterModel) arrayList8.get(this.lastYearPos)).setSelect(true);
        }
        this.yearAdapter = new BaseQuickAdapter<FilterModel, BaseViewHolder>(arrayList8, com.movieboxpro.androidtv.R.layout.adapter_filter_item) { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment.initData.8
            {
                super(i, arrayList8);
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
        this.generAdapter = new BaseQuickAdapter<Gener, BaseViewHolder>(com.movieboxpro.androidtv.R.layout.adapter_genre_item, this.geners) { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment.initData.9
            {
                ArrayList<Gener> arrayList9 = arrayList;
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
        FragmentFilterBinding fragmentFilterBinding5 = this.binding;
        if (fragmentFilterBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding5 = null;
        }
        fragmentFilterBinding5.rvSort.setSelectedItemAtCentered(true);
        fragmentFilterBinding5.rvSort.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView = fragmentFilterBinding5.rvSort;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter2 = this.sortAdapter;
        if (baseQuickAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sortAdapter");
            baseQuickAdapter2 = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter2);
        fragmentFilterBinding5.rvRating.setSelectedItemAtCentered(true);
        fragmentFilterBinding5.rvRating.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView2 = fragmentFilterBinding5.rvRating;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter3 = this.ratingAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ratingAdapter");
            baseQuickAdapter3 = null;
        }
        tvRecyclerView2.setAdapter(baseQuickAdapter3);
        fragmentFilterBinding5.rvResolution.setSelectedItemAtCentered(true);
        fragmentFilterBinding5.rvResolution.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView3 = fragmentFilterBinding5.rvResolution;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter4 = this.resolutionAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resolutionAdapter");
            baseQuickAdapter4 = null;
        }
        tvRecyclerView3.setAdapter(baseQuickAdapter4);
        fragmentFilterBinding5.rvCountry.setSelectedItemAtCentered(true);
        fragmentFilterBinding5.rvCountry.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView4 = fragmentFilterBinding5.rvCountry;
        BaseQuickAdapter<FilterCountry, BaseViewHolder> baseQuickAdapter5 = this.countryAdapter;
        if (baseQuickAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("countryAdapter");
            baseQuickAdapter5 = null;
        }
        tvRecyclerView4.setAdapter(baseQuickAdapter5);
        fragmentFilterBinding5.rvYear.setSelectedItemAtCentered(true);
        fragmentFilterBinding5.rvYear.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView5 = fragmentFilterBinding5.rvYear;
        BaseQuickAdapter<FilterModel, BaseViewHolder> baseQuickAdapter6 = this.yearAdapter;
        if (baseQuickAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("yearAdapter");
            baseQuickAdapter6 = null;
        }
        tvRecyclerView5.setAdapter(baseQuickAdapter6);
        fragmentFilterBinding5.rvGener.setSelectedItemAtCentered(true);
        fragmentFilterBinding5.rvGener.addItemDecoration(new LayoutManagerItemDecoration(0, 28));
        TvRecyclerView tvRecyclerView6 = fragmentFilterBinding5.rvGener;
        BaseQuickAdapter<Gener, BaseViewHolder> baseQuickAdapter7 = this.generAdapter;
        if (baseQuickAdapter7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("generAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter7;
        }
        tvRecyclerView6.setAdapter(baseQuickAdapter);
        TvRecyclerView rvSort = fragmentFilterBinding5.rvSort;
        Intrinsics.checkNotNullExpressionValue(rvSort, "rvSort");
        TvRecyclerView rvRating = fragmentFilterBinding5.rvRating;
        Intrinsics.checkNotNullExpressionValue(rvRating, "rvRating");
        TvRecyclerView rvResolution = fragmentFilterBinding5.rvResolution;
        Intrinsics.checkNotNullExpressionValue(rvResolution, "rvResolution");
        TvRecyclerView rvYear = fragmentFilterBinding5.rvYear;
        Intrinsics.checkNotNullExpressionValue(rvYear, "rvYear");
        TvRecyclerView rvGener = fragmentFilterBinding5.rvGener;
        Intrinsics.checkNotNullExpressionValue(rvGener, "rvGener");
        scaleListener(rvSort, rvRating, rvResolution, rvYear, rvGener);
    }

    private final void initView() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(com.movieboxpro.androidtv.R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(com.movieboxpro.androidtv.R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(getActivity());
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
        Bundle arguments = getArguments();
        FragmentFilterBinding fragmentFilterBinding = null;
        if (Intrinsics.areEqual((Object) (arguments != null ? Boolean.valueOf(arguments.getBoolean("isMovie")) : null), (Object) true)) {
            this.sortTitles = CollectionsKt.arrayListOf("Released", "ADDED", "IMDB", "ROTTEN TOMATOES", "VIEW");
            this.sort = CollectionsKt.arrayListOf("released", "adder", "rating", "tomato_meter", "view");
            this.ratingTitles = CollectionsKt.arrayListOf("G", "PG", "PG-13", "R", "NC-17");
            this.rating = CollectionsKt.arrayListOf("G", "PG", "PG-13", "R", "NC-17");
            this.resolutionTitles = CollectionsKt.arrayListOf("1080P", "4K", "8K", "BLU-RAY");
            this.resolution = CollectionsKt.arrayListOf("1080p", "4k", "8k", "blu-ray");
            return;
        }
        this.sortTitles = CollectionsKt.arrayListOf("ADDED", "IMDB", "ROTTEN TOMATOES", "VIEW");
        this.sort = CollectionsKt.arrayListOf("adder", "rating", "tomato_meter", "view");
        this.ratingTitles = CollectionsKt.arrayListOf("TV-Y", "TV-Y7", "TV-G", "TV-PG", "TV-14", "TV-MA");
        this.rating = CollectionsKt.arrayListOf("TV-Y", "TV-Y7", "TV-G", "TV-PG", "TV-14", "TV-MA");
        this.resolutionTitles = CollectionsKt.arrayListOf("");
        this.resolution = CollectionsKt.arrayListOf("");
        FragmentFilterBinding fragmentFilterBinding2 = this.binding;
        if (fragmentFilterBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding2 = null;
        }
        TextView tvResolution = fragmentFilterBinding2.tvResolution;
        Intrinsics.checkNotNullExpressionValue(tvResolution, "tvResolution");
        CommonExtKt.gone(tvResolution);
        FragmentFilterBinding fragmentFilterBinding3 = this.binding;
        if (fragmentFilterBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentFilterBinding3 = null;
        }
        TvRecyclerView rvResolution = fragmentFilterBinding3.rvResolution;
        Intrinsics.checkNotNullExpressionValue(rvResolution, "rvResolution");
        CommonExtKt.gone(rvResolution);
        FragmentFilterBinding fragmentFilterBinding4 = this.binding;
        if (fragmentFilterBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentFilterBinding = fragmentFilterBinding4;
        }
        fragmentFilterBinding.tvRating.setText("TVSHOW RATINGS");
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

    /* JADX INFO: compiled from: FilterDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/dialog/FilterDialogFragment$Companion;", "", "<init>", "()V", "DEFAULT_SORT", "", "newInstance", "Lcom/movieboxpro/android/view/dialog/FilterDialogFragment;", "isMovie", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final FilterDialogFragment newInstance(boolean isMovie) {
            FilterDialogFragment filterDialogFragment = new FilterDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putBoolean("isMovie", isMovie);
            filterDialogFragment.setArguments(bundle);
            return filterDialogFragment;
        }
    }

    private final void scaleListener(TvRecyclerView... recyclerView) {
        for (TvRecyclerView tvRecyclerView : recyclerView) {
            tvRecyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.dialog.FilterDialogFragment$scaleListener$1$1
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
