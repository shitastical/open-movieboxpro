package com.movieboxpro.android.tv.search;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivitySearchVideoBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchVideoActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 #2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004:\u0001#B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0012\u001a\u00020\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\tH\u0002J\b\u0010\u0016\u001a\u00020\u0013H\u0014J\b\u0010\u0017\u001a\u00020\u0013H\u0014J\u0018\u0010\u0018\u001a\u00020\u00132\u000e\u0010\u0019\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u001aH\u0016J(\u0010\u001b\u001a\u00020\u00132\u000e\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u001a2\u000e\u0010\u001d\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u0013H\u0014J\b\u0010\u001f\u001a\u00020\u0013H\u0016J\b\u0010 \u001a\u00020\u0002H\u0014J\b\u0010!\u001a\u00020\"H\u0014R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/movieboxpro/android/tv/search/SearchVideoActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/tv/search/SearchVideoPresenter;", "Lcom/movieboxpro/androidtv/databinding/ActivitySearchVideoBinding;", "Lcom/movieboxpro/android/tv/search/SearchVideoContract$View;", "<init>", "()V", "historyAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "hotSearchAdapter", "hintKeywordAdapter", "searResultFragment", "Lcom/movieboxpro/android/tv/search/SearchVideoResultFragment;", "currKeyword", "lastChangedTime", "", "initListener", "", "searchVideo", "keyword", "initView", "initData", "showHintKeyword", "keywords", "", "showKeyWord", "history", "hotSearch", "requestData", "onBackPressed", "bindPresenter", "getLayoutResId", "", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SearchVideoActivity extends BaseMvpActivity<SearchVideoPresenter, ActivitySearchVideoBinding> implements SearchVideoContract.View {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String currKeyword = "";
    private BaseQuickAdapter<String, BaseViewHolder> hintKeywordAdapter;
    private BaseQuickAdapter<String, BaseViewHolder> historyAdapter;
    private BaseQuickAdapter<String, BaseViewHolder> hotSearchAdapter;
    private long lastChangedTime;
    private SearchVideoResultFragment searResultFragment;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$1(int i, View view) {
        return i == 130;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ((ActivitySearchVideoBinding) this.binding).etSearch.addTextChangedListener(new TextWatcher() { // from class: com.movieboxpro.android.tv.search.SearchVideoActivity.initListener.1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String string = s != null ? s.toString() : null;
                String str = string;
                if (str == null || str.length() == 0) {
                    TvRecyclerView rvKeyWord = ((ActivitySearchVideoBinding) SearchVideoActivity.this.binding).rvKeyWord;
                    Intrinsics.checkNotNullExpressionValue(rvKeyWord, "rvKeyWord");
                    CommonExtKt.gone(rvKeyWord);
                    SearchVideoActivity.this.lastChangedTime = System.currentTimeMillis();
                    return;
                }
                if (System.currentTimeMillis() - SearchVideoActivity.this.lastChangedTime < 500) {
                    SearchVideoActivity.this.lastChangedTime = System.currentTimeMillis();
                } else {
                    SearchVideoActivity.this.lastChangedTime = System.currentTimeMillis();
                    ((SearchVideoPresenter) SearchVideoActivity.this.mPresenter).requestHintKeyword(string);
                }
            }
        });
        ((ActivitySearchVideoBinding) this.binding).etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.movieboxpro.android.tv.search.SearchVideoActivity$$ExternalSyntheticLambda0
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return SearchVideoActivity.initListener$lambda$0(this.f$0, textView, i, keyEvent);
            }
        });
        ((ActivitySearchVideoBinding) this.binding).rvKeyWord.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() { // from class: com.movieboxpro.android.tv.search.SearchVideoActivity$$ExternalSyntheticLambda1
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnInBorderKeyEventListener
            public final boolean onInBorderKeyEvent(int i, View view) {
                return SearchVideoActivity.initListener$lambda$1(i, view);
            }
        });
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = this.hintKeywordAdapter;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hintKeywordAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.tv.search.SearchVideoActivity$$ExternalSyntheticLambda2
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                SearchVideoActivity.initListener$lambda$2(this.f$0, baseQuickAdapter3, view, i);
            }
        });
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter3 = this.historyAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.tv.search.SearchVideoActivity$$ExternalSyntheticLambda3
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter4, View view, int i) {
                SearchVideoActivity.initListener$lambda$3(this.f$0, baseQuickAdapter4, view, i);
            }
        });
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter4 = this.hotSearchAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hotSearchAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter4;
        }
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.tv.search.SearchVideoActivity$$ExternalSyntheticLambda4
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter5, View view, int i) {
                SearchVideoActivity.initListener$lambda$4(this.f$0, baseQuickAdapter5, view, i);
            }
        });
        ((ActivitySearchVideoBinding) this.binding).tvDoSearch.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.search.SearchVideoActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchVideoActivity.initListener$lambda$5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$0(SearchVideoActivity searchVideoActivity, TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        InputMethodUtils.hideSoftInput(((ActivitySearchVideoBinding) searchVideoActivity.binding).etSearch);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(SearchVideoActivity searchVideoActivity, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        TvRecyclerView rvKeyWord = ((ActivitySearchVideoBinding) searchVideoActivity.binding).rvKeyWord;
        Intrinsics.checkNotNullExpressionValue(rvKeyWord, "rvKeyWord");
        CommonExtKt.gone(rvKeyWord);
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = searchVideoActivity.hintKeywordAdapter;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hintKeywordAdapter");
            baseQuickAdapter = null;
        }
        String item = baseQuickAdapter.getItem(i);
        if (item == null) {
            item = "";
        }
        searchVideoActivity.currKeyword = item;
        ((ActivitySearchVideoBinding) searchVideoActivity.binding).etSearch.setText(searchVideoActivity.currKeyword);
        ((ActivitySearchVideoBinding) searchVideoActivity.binding).etSearch.setSelection(searchVideoActivity.currKeyword.length());
        searchVideoActivity.searchVideo(searchVideoActivity.currKeyword);
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter3 = searchVideoActivity.hintKeywordAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hintKeywordAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.setList(new ArrayList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(SearchVideoActivity searchVideoActivity, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        ((ActivitySearchVideoBinding) searchVideoActivity.binding).etSearch.setText(searchVideoActivity.currKeyword);
        ((ActivitySearchVideoBinding) searchVideoActivity.binding).etSearch.setSelection(searchVideoActivity.currKeyword.length());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$4(SearchVideoActivity searchVideoActivity, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = searchVideoActivity.hotSearchAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hotSearchAdapter");
            baseQuickAdapter = null;
        }
        String item = baseQuickAdapter.getItem(i);
        if (item == null) {
            item = "";
        }
        searchVideoActivity.currKeyword = item;
        ((ActivitySearchVideoBinding) searchVideoActivity.binding).etSearch.setText(searchVideoActivity.currKeyword);
        ((ActivitySearchVideoBinding) searchVideoActivity.binding).etSearch.setSelection(searchVideoActivity.currKeyword.length());
        searchVideoActivity.searchVideo(searchVideoActivity.currKeyword);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5(SearchVideoActivity searchVideoActivity, View view) {
        if (((ActivitySearchVideoBinding) searchVideoActivity.binding).etSearch.getText().toString().length() == 0) {
            return;
        }
        String string = ((ActivitySearchVideoBinding) searchVideoActivity.binding).etSearch.getText().toString();
        searchVideoActivity.currKeyword = string;
        searchVideoActivity.searchVideo(string);
    }

    private final void searchVideo(String keyword) {
        ((ActivitySearchVideoBinding) this.binding).etSearch.clearFocus();
        FrameLayout frameLayout = ((ActivitySearchVideoBinding) this.binding).frameLayout;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "frameLayout");
        CommonExtKt.visible(frameLayout);
        ((ActivitySearchVideoBinding) this.binding).tvDoSearch.requestFocus();
        SearchVideoResultFragment searchVideoResultFragment = this.searResultFragment;
        if (searchVideoResultFragment != null) {
            if (searchVideoResultFragment != null) {
                searchVideoResultFragment.refreshData(keyword);
            }
        } else {
            this.searResultFragment = SearchVideoResultFragment.INSTANCE.newInstance(keyword);
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            SearchVideoResultFragment searchVideoResultFragment2 = this.searResultFragment;
            Intrinsics.checkNotNull(searchVideoResultFragment2);
            FragmentUtils.add(supportFragmentManager, searchVideoResultFragment2, R.id.frameLayout);
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        this.historyAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_search_history_item) { // from class: com.movieboxpro.android.tv.search.SearchVideoActivity.initData.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, String item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.tvKeyword, item);
            }
        };
        this.hotSearchAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_hot_search_item) { // from class: com.movieboxpro.android.tv.search.SearchVideoActivity.initData.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, String item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.tvKeyword, item);
            }
        };
        this.hintKeywordAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_search_hint_item) { // from class: com.movieboxpro.android.tv.search.SearchVideoActivity.initData.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, String item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.tvKeyword, item);
            }
        };
        TvRecyclerView tvRecyclerView = ((ActivitySearchVideoBinding) this.binding).rvHistory;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = this.historyAdapter;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            baseQuickAdapter = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        TvRecyclerView tvRecyclerView2 = ((ActivitySearchVideoBinding) this.binding).rvHotSearch;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter3 = this.hotSearchAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hotSearchAdapter");
            baseQuickAdapter3 = null;
        }
        tvRecyclerView2.setAdapter(baseQuickAdapter3);
        TvRecyclerView tvRecyclerView3 = ((ActivitySearchVideoBinding) this.binding).rvKeyWord;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter4 = this.hintKeywordAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hintKeywordAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter4;
        }
        tvRecyclerView3.setAdapter(baseQuickAdapter2);
    }

    @Override // com.movieboxpro.android.tv.search.SearchVideoContract.View
    public void showHintKeyword(List<String> keywords) {
        if (Intrinsics.areEqual(((ActivitySearchVideoBinding) this.binding).etSearch.getText().toString(), this.currKeyword) || ((ActivitySearchVideoBinding) this.binding).etSearch.getText().toString().length() == 0) {
            return;
        }
        TvRecyclerView rvKeyWord = ((ActivitySearchVideoBinding) this.binding).rvKeyWord;
        Intrinsics.checkNotNullExpressionValue(rvKeyWord, "rvKeyWord");
        CommonExtKt.visible(rvKeyWord);
        FrameLayout frameLayout = ((ActivitySearchVideoBinding) this.binding).frameLayout;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "frameLayout");
        CommonExtKt.gone(frameLayout);
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = this.hintKeywordAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hintKeywordAdapter");
            baseQuickAdapter = null;
        }
        if (keywords == null) {
            keywords = new ArrayList<>();
        }
        baseQuickAdapter.setList(new ArrayList(keywords));
    }

    @Override // com.movieboxpro.android.tv.search.SearchVideoContract.View
    public void showKeyWord(List<String> history, List<String> hotSearch) {
        List<String> list = history;
        if (list == null || list.isEmpty()) {
            TvRecyclerView rvHistory = ((ActivitySearchVideoBinding) this.binding).rvHistory;
            Intrinsics.checkNotNullExpressionValue(rvHistory, "rvHistory");
            CommonExtKt.gone(rvHistory);
        }
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = null;
        if ((history != null ? history.size() : 0) > 6) {
            BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter2 = this.historyAdapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
                baseQuickAdapter2 = null;
            }
            baseQuickAdapter2.setList(history != null ? history.subList(0, 6) : null);
        } else {
            BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter3 = this.historyAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
                baseQuickAdapter3 = null;
            }
            baseQuickAdapter3.setList(list);
        }
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter4 = this.hotSearchAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hotSearchAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter4;
        }
        baseQuickAdapter.setList(hotSearch);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        ((SearchVideoPresenter) this.mPresenter).requestHistoryHot();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        TvRecyclerView rvKeyWord = ((ActivitySearchVideoBinding) this.binding).rvKeyWord;
        Intrinsics.checkNotNullExpressionValue(rvKeyWord, "rvKeyWord");
        if (CommonExtKt.isVisible(rvKeyWord)) {
            TvRecyclerView rvKeyWord2 = ((ActivitySearchVideoBinding) this.binding).rvKeyWord;
            Intrinsics.checkNotNullExpressionValue(rvKeyWord2, "rvKeyWord");
            CommonExtKt.gone(rvKeyWord2);
            ((ActivitySearchVideoBinding) this.binding).etSearch.setText("");
            return;
        }
        FrameLayout frameLayout = ((ActivitySearchVideoBinding) this.binding).frameLayout;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "frameLayout");
        if (CommonExtKt.isVisible(frameLayout)) {
            FrameLayout frameLayout2 = ((ActivitySearchVideoBinding) this.binding).frameLayout;
            Intrinsics.checkNotNullExpressionValue(frameLayout2, "frameLayout");
            CommonExtKt.gone(frameLayout2);
            ((ActivitySearchVideoBinding) this.binding).etSearch.setText("");
            return;
        }
        super.onBackPressed();
    }

    /* JADX INFO: compiled from: SearchVideoActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/tv/search/SearchVideoActivity$Companion;", "", "<init>", "()V", "start", "", "context", "Landroid/content/Context;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, (Class<?>) SearchVideoActivity.class));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public SearchVideoPresenter bindPresenter() {
        return new SearchVideoPresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_search_video;
    }
}
