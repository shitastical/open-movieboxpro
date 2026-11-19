package com.movieboxpro.android.tv.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseLazyFragment;
import com.movieboxpro.android.event.SearchFinishEvent;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentSearchVideoResultBinding;
import com.owen.tab.TvTabLayout;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: SearchVideoResultFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 32\u00020\u0001:\u00013B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J\u001a\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0016J>\u0010\u001f\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010'\u001a\u00020\u0006J\u0006\u0010(\u001a\u00020!J\b\u0010)\u001a\u00020\u001dH\u0002J\u0010\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020,H\u0007J\b\u0010-\u001a\u00020\u001dH\u0002J\b\u0010.\u001a\u00020\u001dH\u0016J\b\u0010/\u001a\u00020\u001dH\u0016J\u0010\u00100\u001a\u00020\u001d2\b\u0010\t\u001a\u0004\u0018\u00010\u0006J\b\u00101\u001a\u00020\u001dH\u0002J\b\u00102\u001a\u00020\u001dH\u0002R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/movieboxpro/android/tv/search/SearchVideoResultFragment;", "Lcom/movieboxpro/android/base/BaseLazyFragment;", "<init>", "()V", "tabTitles", "", "", "[Ljava/lang/String;", "types", "keyword", "hud", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "allFragment", "Lcom/movieboxpro/android/tv/search/SearchVideoListFragment;", "movieFragment", "tvFragment", "playListFragment", "Lcom/movieboxpro/android/tv/search/SearchPlayListFragment;", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentSearchVideoResultBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setFilter", "type", "", "year", TvContractCompat.PreviewProgramColumns.COLUMN_GENRE, "sort", "rating", "quality", "country", "getTabSelectPos", "initView", "onSearchFinish", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/SearchFinishEvent;", "hideAllFragment", "onStart", "onDestroy", "refreshData", "showLoading", "hideLoading", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SearchVideoResultFragment extends BaseLazyFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private SearchVideoListFragment allFragment;
    private FragmentSearchVideoResultBinding binding;
    private KProgressHUD hud;
    private String keyword;
    private SearchVideoListFragment movieFragment;
    private SearchPlayListFragment playListFragment;
    private final String[] tabTitles;
    private SearchVideoListFragment tvFragment;
    private final String[] types;

    public SearchVideoResultFragment() {
        this.tabTitles = PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIE_LIST, false) ? new String[]{"All", "Movies", "TV Shows"} : new String[]{"All", "Movies", "TV Shows", "MovieLists"};
        this.types = new String[]{TtmlNode.COMBINE_ALL, BuildConfig.CHANNEL, "tv"};
        this.keyword = "";
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentSearchVideoResultBinding fragmentSearchVideoResultBindingInflate = FragmentSearchVideoResultBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentSearchVideoResultBindingInflate, "inflate(...)");
        this.binding = fragmentSearchVideoResultBindingInflate;
        if (fragmentSearchVideoResultBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSearchVideoResultBindingInflate = null;
        }
        return fragmentSearchVideoResultBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    public final void setFilter(int type, String year, String genre, String sort, String rating, String quality, String country) {
        SearchVideoListFragment searchVideoListFragment;
        Intrinsics.checkNotNullParameter(year, "year");
        Intrinsics.checkNotNullParameter(genre, "genre");
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(rating, "rating");
        Intrinsics.checkNotNullParameter(quality, "quality");
        Intrinsics.checkNotNullParameter(country, "country");
        if (type == 0) {
            SearchVideoListFragment searchVideoListFragment2 = this.allFragment;
            if (searchVideoListFragment2 != null) {
                searchVideoListFragment2.setFilter(year, genre, sort, rating, quality, country);
                return;
            }
            return;
        }
        if (type != 1) {
            if (type == 2 && (searchVideoListFragment = this.tvFragment) != null) {
                searchVideoListFragment.setFilter(year, genre, sort, rating, quality, country);
                return;
            }
            return;
        }
        SearchVideoListFragment searchVideoListFragment3 = this.movieFragment;
        if (searchVideoListFragment3 != null) {
            searchVideoListFragment3.setFilter(year, genre, sort, rating, quality, country);
        }
    }

    public final int getTabSelectPos() {
        FragmentSearchVideoResultBinding fragmentSearchVideoResultBinding = this.binding;
        if (fragmentSearchVideoResultBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSearchVideoResultBinding = null;
        }
        return fragmentSearchVideoResultBinding.tvTabLayout.getSelectedTabPosition();
    }

    private final void initView() {
        Bundle arguments = getArguments();
        FragmentSearchVideoResultBinding fragmentSearchVideoResultBinding = null;
        this.keyword = arguments != null ? arguments.getString("keyword") : null;
        FragmentSearchVideoResultBinding fragmentSearchVideoResultBinding2 = this.binding;
        if (fragmentSearchVideoResultBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSearchVideoResultBinding2 = null;
        }
        fragmentSearchVideoResultBinding2.tvTabLayout.addOnTabSelectedListener(new TvTabLayout.OnTabSelectedListener() { // from class: com.movieboxpro.android.tv.search.SearchVideoResultFragment.initView.1
            @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
            public void onTabReselected(TvTabLayout.Tab tab) {
            }

            @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
            public void onTabUnselected(TvTabLayout.Tab tab) {
            }

            @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
            public void onTabSelected(TvTabLayout.Tab tab) {
                FragmentTransaction fragmentTransactionBeginTransaction = SearchVideoResultFragment.this.getChildFragmentManager().beginTransaction();
                Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
                SearchVideoResultFragment.this.hideAllFragment();
                Integer numValueOf = tab != null ? Integer.valueOf(tab.getPosition()) : null;
                if (numValueOf != null && numValueOf.intValue() == 0) {
                    if (SearchVideoResultFragment.this.allFragment == null) {
                        SearchVideoResultFragment.this.allFragment = SearchVideoListFragment.INSTANCE.newInstance(SearchVideoResultFragment.this.types[0], SearchVideoResultFragment.this.keyword);
                        int i = R.id.frameLayout;
                        SearchVideoListFragment searchVideoListFragment = SearchVideoResultFragment.this.allFragment;
                        Intrinsics.checkNotNull(searchVideoListFragment);
                        fragmentTransactionBeginTransaction.add(i, searchVideoListFragment);
                    }
                    SearchVideoListFragment searchVideoListFragment2 = SearchVideoResultFragment.this.allFragment;
                    Intrinsics.checkNotNull(searchVideoListFragment2);
                    fragmentTransactionBeginTransaction.show(searchVideoListFragment2);
                } else if (numValueOf != null && numValueOf.intValue() == 1) {
                    if (SearchVideoResultFragment.this.movieFragment == null) {
                        SearchVideoResultFragment.this.movieFragment = SearchVideoListFragment.INSTANCE.newInstance(SearchVideoResultFragment.this.types[1], SearchVideoResultFragment.this.keyword);
                        int i2 = R.id.frameLayout;
                        SearchVideoListFragment searchVideoListFragment3 = SearchVideoResultFragment.this.movieFragment;
                        Intrinsics.checkNotNull(searchVideoListFragment3);
                        fragmentTransactionBeginTransaction.add(i2, searchVideoListFragment3);
                    }
                    SearchVideoListFragment searchVideoListFragment4 = SearchVideoResultFragment.this.movieFragment;
                    Intrinsics.checkNotNull(searchVideoListFragment4);
                    fragmentTransactionBeginTransaction.show(searchVideoListFragment4);
                } else if (numValueOf != null && numValueOf.intValue() == 2) {
                    if (SearchVideoResultFragment.this.tvFragment == null) {
                        SearchVideoResultFragment.this.tvFragment = SearchVideoListFragment.INSTANCE.newInstance(SearchVideoResultFragment.this.types[2], SearchVideoResultFragment.this.keyword);
                        int i3 = R.id.frameLayout;
                        SearchVideoListFragment searchVideoListFragment5 = SearchVideoResultFragment.this.tvFragment;
                        Intrinsics.checkNotNull(searchVideoListFragment5);
                        fragmentTransactionBeginTransaction.add(i3, searchVideoListFragment5);
                    }
                    SearchVideoListFragment searchVideoListFragment6 = SearchVideoResultFragment.this.tvFragment;
                    Intrinsics.checkNotNull(searchVideoListFragment6);
                    fragmentTransactionBeginTransaction.show(searchVideoListFragment6);
                } else if (numValueOf != null && numValueOf.intValue() == 3) {
                    if (SearchVideoResultFragment.this.playListFragment == null) {
                        SearchVideoResultFragment.this.playListFragment = SearchPlayListFragment.INSTANCE.newInstance(SearchVideoResultFragment.this.keyword);
                        int i4 = R.id.frameLayout;
                        SearchPlayListFragment searchPlayListFragment = SearchVideoResultFragment.this.playListFragment;
                        Intrinsics.checkNotNull(searchPlayListFragment);
                        fragmentTransactionBeginTransaction.add(i4, searchPlayListFragment);
                    }
                    SearchPlayListFragment searchPlayListFragment2 = SearchVideoResultFragment.this.playListFragment;
                    Intrinsics.checkNotNull(searchPlayListFragment2);
                    fragmentTransactionBeginTransaction.show(searchPlayListFragment2);
                }
                fragmentTransactionBeginTransaction.commitAllowingStateLoss();
            }
        });
        for (String str : this.tabTitles) {
            FragmentSearchVideoResultBinding fragmentSearchVideoResultBinding3 = this.binding;
            if (fragmentSearchVideoResultBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentSearchVideoResultBinding3 = null;
            }
            if (fragmentSearchVideoResultBinding3.tvTabLayout != null) {
                FragmentSearchVideoResultBinding fragmentSearchVideoResultBinding4 = this.binding;
                if (fragmentSearchVideoResultBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentSearchVideoResultBinding4 = null;
                }
                TvTabLayout tvTabLayout = fragmentSearchVideoResultBinding4.tvTabLayout;
                FragmentSearchVideoResultBinding fragmentSearchVideoResultBinding5 = this.binding;
                if (fragmentSearchVideoResultBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentSearchVideoResultBinding5 = null;
                }
                tvTabLayout.addTab(fragmentSearchVideoResultBinding5.tvTabLayout.newTab().setText(str));
            }
        }
        FragmentSearchVideoResultBinding fragmentSearchVideoResultBinding6 = this.binding;
        if (fragmentSearchVideoResultBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentSearchVideoResultBinding = fragmentSearchVideoResultBinding6;
        }
        fragmentSearchVideoResultBinding.frameLayout.requestFocus();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSearchFinish(SearchFinishEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        FragmentSearchVideoResultBinding fragmentSearchVideoResultBinding = this.binding;
        if (fragmentSearchVideoResultBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSearchVideoResultBinding = null;
        }
        fragmentSearchVideoResultBinding.frameLayout.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideAllFragment() {
        SearchVideoListFragment searchVideoListFragment = this.allFragment;
        if (searchVideoListFragment != null) {
            FragmentUtils.hide(searchVideoListFragment);
        }
        SearchVideoListFragment searchVideoListFragment2 = this.movieFragment;
        if (searchVideoListFragment2 != null) {
            FragmentUtils.hide(searchVideoListFragment2);
        }
        SearchVideoListFragment searchVideoListFragment3 = this.tvFragment;
        if (searchVideoListFragment3 != null) {
            FragmentUtils.hide(searchVideoListFragment3);
        }
        SearchPlayListFragment searchPlayListFragment = this.playListFragment;
        if (searchPlayListFragment != null) {
            FragmentUtils.hide(searchPlayListFragment);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    public final void refreshData(String keyword) {
        this.keyword = keyword;
        SearchVideoListFragment searchVideoListFragment = this.allFragment;
        if (searchVideoListFragment != null) {
            searchVideoListFragment.refreshData(keyword);
        }
        SearchVideoListFragment searchVideoListFragment2 = this.movieFragment;
        if (searchVideoListFragment2 != null) {
            searchVideoListFragment2.refreshData(keyword);
        }
        SearchVideoListFragment searchVideoListFragment3 = this.tvFragment;
        if (searchVideoListFragment3 != null) {
            searchVideoListFragment3.refreshData(keyword);
        }
        SearchPlayListFragment searchPlayListFragment = this.playListFragment;
        if (searchPlayListFragment != null) {
            searchPlayListFragment.refreshData(keyword);
        }
        FragmentSearchVideoResultBinding fragmentSearchVideoResultBinding = this.binding;
        if (fragmentSearchVideoResultBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentSearchVideoResultBinding = null;
        }
        fragmentSearchVideoResultBinding.frameLayout.requestFocus();
    }

    private final void showLoading() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD != null) {
            Intrinsics.checkNotNull(kProgressHUD);
            if (kProgressHUD.isShowing()) {
                return;
            }
        }
        KProgressHUD kProgressHUD2 = this.hud;
        if (kProgressHUD2 != null) {
            Intrinsics.checkNotNull(kProgressHUD2);
            if (kProgressHUD2.isShowing()) {
                return;
            }
        }
        this.hud = KProgressHUD.create(getContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f).show();
    }

    private final void hideLoading() {
        KProgressHUD kProgressHUD = this.hud;
        if (kProgressHUD != null) {
            Intrinsics.checkNotNull(kProgressHUD);
            if (kProgressHUD.isShowing()) {
                KProgressHUD kProgressHUD2 = this.hud;
                Intrinsics.checkNotNull(kProgressHUD2);
                kProgressHUD2.dismiss();
            }
        }
    }

    /* JADX INFO: compiled from: SearchVideoResultFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/tv/search/SearchVideoResultFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/tv/search/SearchVideoResultFragment;", "keyword", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SearchVideoResultFragment newInstance(String keyword) {
            Intrinsics.checkNotNullParameter(keyword, "keyword");
            SearchVideoResultFragment searchVideoResultFragment = new SearchVideoResultFragment();
            Bundle bundle = new Bundle();
            bundle.putString("keyword", keyword);
            searchVideoResultFragment.setArguments(bundle);
            return searchVideoResultFragment;
        }
    }
}
