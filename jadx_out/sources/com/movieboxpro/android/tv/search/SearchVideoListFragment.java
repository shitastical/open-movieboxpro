package com.movieboxpro.android.tv.search;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.event.SearchFinishEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.livedata.SearchFilterLiveData;
import com.movieboxpro.android.model.movie.NormalFilmModel;
import com.movieboxpro.android.tv.movie.MovieDetailActivity;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.tool.SPStaticUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import io.reactivex.Observable;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: SearchVideoListFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 -2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001-B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u0012H\u0014J6\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0003J\b\u0010\u0016\u001a\u00020\u0014H\u0016J\u0006\u0010\u0017\u001a\u00020\u0010J\u0014\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0014J\u0012\u0010\u001c\u001a\u00020\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020 H\u0014J\u0010\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u001bH\u0014J\u0010\u0010#\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020\u001bH\u0014J\b\u0010$\u001a\u00020\u0010H\u0014J\u0010\u0010%\u001a\u00020\u00142\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003J\u0018\u0010&\u001a\u00020\u00142\u000e\u0010'\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010(H\u0014J\u001c\u0010)\u001a\u00020\u00142\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010,\u001a\u0004\u0018\u00010\u0002H\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/movieboxpro/android/tv/search/SearchVideoListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/movie/NormalFilmModel;", "", "<init>", "()V", "type", "keyword", "uid", "sort", "gener", "year", "rating", "country", "quality", "allDefault", "", "getServiceData", "Lio/reactivex/Observable;", "setFilter", "", TvContractCompat.PreviewProgramColumns.COLUMN_GENRE, "onFragmentResume", "haveFilter", "getFilterData", "Ljava/util/HashMap;", "initItemLayout", "", "getBundle", "arguments", "Landroid/os/Bundle;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onFocus", "position", "onLastFocus", "isOpenLoadMore", "refreshData", "doSomethingWithData", "list", "", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SearchVideoListFragment extends BaseListFragment<NormalFilmModel, String> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String type = "";
    private String keyword = "";
    private String uid = "";
    private String sort = "";
    private String gener = "";
    private String year = "";
    private String rating = "";
    private String country = "";
    private String quality = "";
    private boolean allDefault = true;

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected boolean isOpenLoadMore() {
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected Observable<String> getServiceData() {
        Observable<String> observableSearchFilter = Http.getService().SearchFilter(API.Search.SEARCH, this.type, this.keyword, this.uid, String.valueOf(this.mCurrentPage), String.valueOf(this.mPageSize), this.sort, this.year, this.rating, this.quality, this.country, this.gener, PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false) ? 1 : 0);
        Intrinsics.checkNotNullExpressionValue(observableSearchFilter, "SearchFilter(...)");
        return observableSearchFilter;
    }

    public final void setFilter(String year, String genre, String sort, String rating, String quality, String country) {
        Intrinsics.checkNotNullParameter(year, "year");
        Intrinsics.checkNotNullParameter(genre, "genre");
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(rating, "rating");
        Intrinsics.checkNotNullParameter(quality, "quality");
        Intrinsics.checkNotNullParameter(country, "country");
        this.mCurrentPage = 1;
        this.year = year;
        this.gener = genre;
        this.sort = sort;
        this.rating = rating;
        this.quality = quality;
        this.country = country;
        this.allDefault = Intrinsics.areEqual(year, "") && Intrinsics.areEqual(genre, "") && (Intrinsics.areEqual(sort, "") || Intrinsics.areEqual(sort, "adder") || Intrinsics.areEqual(sort, "released")) && StringsKt.isBlank(country) && StringsKt.isBlank(rating);
        SearchFilterLiveData.INSTANCE.get().setValue(Boolean.valueOf(this.allDefault));
        startRefresh();
    }

    @Override // com.movieboxpro.android.base.BaseLazyFragment
    public void onFragmentResume() {
        super.onFragmentResume();
        SearchFilterLiveData.INSTANCE.get().setValue(Boolean.valueOf(this.allDefault));
    }

    public final boolean haveFilter() {
        if (!Intrinsics.areEqual(this.year, "") || !Intrinsics.areEqual(this.gener, "")) {
            return true;
        }
        if (!Intrinsics.areEqual(this.sort, "") && !Intrinsics.areEqual(this.sort, "adder") && !Intrinsics.areEqual(this.sort, "released")) {
            return true;
        }
        String str = this.country;
        if (str != null && !StringsKt.isBlank(str)) {
            return true;
        }
        String str2 = this.rating;
        if (str2 != null && !StringsKt.isBlank(str2)) {
            return true;
        }
        String str3 = this.quality;
        return (str3 == null || StringsKt.isBlank(str3)) ? false : true;
    }

    public final HashMap<String, String> getFilterData() {
        return MapsKt.hashMapOf(TuplesKt.to("year", this.year), TuplesKt.to("gener", this.gener), TuplesKt.to("sort", this.sort), TuplesKt.to("rating", this.rating), TuplesKt.to("quality", this.quality), TuplesKt.to("country", this.country));
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected int initItemLayout() {
        return R.layout.layout_movielist_item2;
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void getBundle(Bundle arguments) {
        String string;
        String str;
        String str2;
        this.mClass = NormalFilmModel.class;
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        this.uid = uid_v2;
        this.type = arguments != null ? arguments.getString("type") : null;
        this.keyword = arguments != null ? arguments.getString("keyword") : null;
        if (Intrinsics.areEqual(this.type, TtmlNode.COMBINE_ALL)) {
            string = SPStaticUtils.getString(Constant.Prefs.SEARCH_FILTER_ALL);
        } else if (Intrinsics.areEqual(this.type, BuildConfig.CHANNEL)) {
            string = SPStaticUtils.getString(Constant.Prefs.SEARCH_FILTER_MOVIE);
        } else {
            string = Intrinsics.areEqual(this.type, "tv") ? SPStaticUtils.getString(Constant.Prefs.SEARCH_FILTER_TV) : "";
        }
        String str3 = string;
        if (str3 != null && !StringsKt.isBlank(str3)) {
            Object object = JSONObject.parseObject(string, (Class<Object>) HashMap.class);
            Intrinsics.checkNotNull(object, "null cannot be cast to non-null type java.util.HashMap<kotlin.String, kotlin.String>");
            HashMap map = (HashMap) object;
            String str4 = (String) map.get("year");
            if (str4 == null) {
                str4 = "";
            }
            this.year = str4;
            String str5 = (String) map.get("gener");
            if (str5 == null) {
                str5 = "";
            }
            this.gener = str5;
            String str6 = (String) map.get("sort");
            if (str6 == null) {
                str6 = "";
            }
            this.sort = str6;
            String str7 = (String) map.get("rating");
            if (str7 == null) {
                str7 = "";
            }
            this.rating = str7;
            String str8 = (String) map.get("quality");
            if (str8 == null) {
                str8 = "";
            }
            this.quality = str8;
            String str9 = (String) map.get("country");
            if (str9 == null) {
                str9 = "";
            }
            this.country = str9;
        }
        this.allDefault = Intrinsics.areEqual(this.year, "") && Intrinsics.areEqual(this.gener, "") && (Intrinsics.areEqual(this.sort, "") || Intrinsics.areEqual(this.sort, "adder") || Intrinsics.areEqual(this.sort, "released")) && (((str = this.country) == null || StringsKt.isBlank(str)) && ((str2 = this.rating) == null || StringsKt.isBlank(str2)));
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected OnItemClickListener onItemClick() {
        return new OnItemClickListener() { // from class: com.movieboxpro.android.tv.search.SearchVideoListFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SearchVideoListFragment.onItemClick$lambda$2(this.f$0, baseQuickAdapter, view, i);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onItemClick$lambda$2(SearchVideoListFragment searchVideoListFragment, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        Object item = adapter.getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.movieboxpro.android.model.movie.NormalFilmModel");
        NormalFilmModel normalFilmModel = (NormalFilmModel) item;
        if (normalFilmModel.getBox_type() == 1) {
            Context context = searchVideoListFragment.getContext();
            if (context != null) {
                MovieDetailActivity.INSTANCE.starter(context, String.valueOf(normalFilmModel.getId()), normalFilmModel.getPoster());
                return;
            }
            return;
        }
        Context context2 = searchVideoListFragment.getContext();
        if (context2 != null) {
            TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
            String strValueOf = String.valueOf(normalFilmModel.getId());
            String poster = normalFilmModel.getPoster();
            Intrinsics.checkNotNullExpressionValue(poster, "getPoster(...)");
            companion.starter(context2, strValueOf, poster);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void onFocus(int position) {
        View viewByPosition = this.mAdapter.getViewByPosition(position, R.id.tvTitle);
        if (viewByPosition != null) {
            CommonExtKt.visible(viewByPosition);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void onLastFocus(int position) {
        View viewByPosition = this.mAdapter.getViewByPosition(position, R.id.tvTitle);
        if (viewByPosition != null) {
            CommonExtKt.invisible(viewByPosition);
        }
    }

    public final void refreshData(String keyword) {
        this.keyword = keyword;
        startRefresh();
    }

    @Override // com.movieboxpro.android.base.BaseListFragment
    protected void doSomethingWithData(List<NormalFilmModel> list) {
        EventBus.getDefault().post(new SearchFinishEvent());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:51:0x00cb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:52:0x00cd  */
    /*  JADX ERROR: UnsupportedOperationException in pass: SwitchBreakVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1092)
        	at jadx.core.dex.visitors.regions.SwitchBreakVisitor$BaseSwitchRegionVisitor.leaveRegion(SwitchBreakVisitor.java:210)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:31)
        	at jadx.core.dex.visitors.regions.SwitchBreakVisitor$IterativeSwitchRegionVisitor.leaveRegion(SwitchBreakVisitor.java:177)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:91)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.SwitchBreakVisitor.runSwitchTraverse(SwitchBreakVisitor.java:52)
        	at jadx.core.dex.visitors.regions.SwitchBreakVisitor.visit(SwitchBreakVisitor.java:45)
        */
    @Override // com.movieboxpro.android.base.BaseListFragment
    public void initHolder(com.chad.library.adapter.base.viewholder.BaseViewHolder r6, com.movieboxpro.android.model.movie.NormalFilmModel r7) {
        /*
            Method dump skipped, instruction units count: 282
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.tv.search.SearchVideoListFragment.initHolder(com.chad.library.adapter.base.viewholder.BaseViewHolder, com.movieboxpro.android.model.movie.NormalFilmModel):void");
    }

    /* JADX INFO: compiled from: SearchVideoListFragment.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0007¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/tv/search/SearchVideoListFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/tv/search/SearchVideoListFragment;", "type", "", "keyword", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SearchVideoListFragment newInstance(String type, String keyword) {
            Intrinsics.checkNotNullParameter(type, "type");
            SearchVideoListFragment searchVideoListFragment = new SearchVideoListFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", type);
            bundle.putString("keyword", keyword);
            searchVideoListFragment.setArguments(bundle);
            return searchVideoListFragment;
        }
    }
}
