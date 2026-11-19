package com.movieboxpro.android.tv.home;

import android.view.View;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.base.BaseBindingFragment;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.listener.OnVideoListFocusListener;
import com.movieboxpro.android.model.TimeLineModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.view.widget.LayoutManagerItemDecoration;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentTvGuideBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.snowtop.diskpanda.utils.databinding.FragmentBindingDelegate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: TvGuideFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u0005\u0018\u0000 92\u00020\u00012\u00020\u0002:\u00019B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0006\u0010\u0014\u001a\u00020\u0015J\u0006\u0010\u0016\u001a\u00020\u0017J\b\u0010\u0018\u001a\u00020\u0015H\u0016J\b\u0010\u0019\u001a\u00020\u0015H\u0016J\b\u0010\u001a\u001a\u00020\u0015H\u0016J\b\u0010\u001b\u001a\u00020\u0015H\u0016J\b\u0010\u001c\u001a\u00020\u0015H\u0016J\u0018\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020\u0017H\u0002J$\u0010 \u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u00172\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\u0018\u0010$\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020#2\u0006\u0010%\u001a\u00020\rH\u0002J\u0018\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020\rH\u0002J\u0010\u0010(\u001a\u00020\u00172\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u0012H\u0002J\u001c\u0010.\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002000/2\u0006\u00101\u001a\u000200H\u0002J\u001c\u00102\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002000/2\u0006\u00101\u001a\u000200H\u0002J\u001c\u00103\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002000/2\u0006\u00101\u001a\u000200H\u0002J\"\u00104\u001a\b\u0012\u0004\u0012\u000200052\u0012\u00106\u001a\u000e\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u0002000/H\u0002J\u001e\u00104\u001a\b\u0012\u0004\u0012\u000200052\u0006\u00107\u001a\u0002002\u0006\u00108\u001a\u000200H\u0002R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/movieboxpro/android/tv/home/TvGuideFragment;", "Lcom/movieboxpro/android/base/BaseBindingFragment;", "Lcom/movieboxpro/android/listener/OnVideoListFocusListener;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;", "getBinding", "()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;", "binding$delegate", "Lcom/snowtop/diskpanda/utils/databinding/FragmentBindingDelegate;", "timeLineAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/TimeLineModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "listFragment", "Lcom/movieboxpro/android/tv/home/TvGuideListFragment;", "lastSelectedPosition", "", "focusedPosition", "refreshData", "", "onBackPressed", "", "initView", "initData", "initListener", "onFocusUp", "onFocus", "selectDay", "position", "moveToList", "setDayFocus", "focused", "itemView", "Landroid/view/View;", "bindFocusViews", "item", "bindTimeLine", "helper", "isToday", "calendar", "Ljava/util/Calendar;", "getWeekDayLetter", "", "week", "getLastTimeInterval", "Lkotlin/Pair;", "Ljava/util/Date;", "date", "getTimeInterval", "getTimeNextWeek", "findDates", "", "range", "dBegin", "dEnd", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TvGuideFragment extends BaseBindingFragment implements OnVideoListFocusListener {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(TvGuideFragment.class, "binding", "getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentTvGuideBinding;", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentBindingDelegate binding;
    private int focusedPosition;
    private int lastSelectedPosition;
    private TvGuideListFragment listFragment;
    private BaseQuickAdapter<TimeLineModel, BaseViewHolder> timeLineAdapter;

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initView() {
    }

    @Override // com.movieboxpro.android.listener.OnVideoListFocusListener
    public void onFocus() {
    }

    public TvGuideFragment() {
        super(R.layout.fragment_tv_guide);
        this.binding = new FragmentBindingDelegate(FragmentTvGuideBinding.class, this);
        this.lastSelectedPosition = -1;
    }

    private final FragmentTvGuideBinding getBinding() {
        return (FragmentTvGuideBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    public final void refreshData() {
        TvGuideListFragment tvGuideListFragment = this.listFragment;
        if (tvGuideListFragment != null) {
            if (tvGuideListFragment == null) {
                Intrinsics.throwUninitializedPropertyAccessException("listFragment");
                tvGuideListFragment = null;
            }
            tvGuideListFragment.startRefresh();
        }
    }

    public final boolean onBackPressed() {
        if (!isAdded() || getView() == null || this.listFragment == null || getBinding().rvTimeLine.hasFocus()) {
            return false;
        }
        TvGuideListFragment tvGuideListFragment = this.listFragment;
        if (tvGuideListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listFragment");
            tvGuideListFragment = null;
        }
        tvGuideListFragment.scrollToTop();
        onFocusUp();
        return true;
    }

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initData() {
        TimeLineModel timeLineModel;
        final Ref.IntRef intRef = new Ref.IntRef();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(findDates(getLastTimeInterval(new Date())));
        arrayList.addAll(findDates(getTimeInterval(new Date())));
        arrayList.addAll(findDates(getTimeNextWeek(new Date())));
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
        String str = "";
        int i = 0;
        for (Object obj : arrayList2) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((Date) obj);
            String time2 = TimeUtils.INSTANCE.formatTime2(calendar.getTimeInMillis());
            Intrinsics.checkNotNull(calendar);
            if (isToday(calendar)) {
                intRef.element = i;
                timeLineModel = new TimeLineModel(calendar.get(5), getWeekDayLetter(calendar.get(7)), time2, true, true, false, 32, null);
            } else {
                TimeLineModel timeLineModel2 = new TimeLineModel(calendar.get(5), getWeekDayLetter(calendar.get(7)), time2, false, false, false, 56, null);
                time2 = str;
                timeLineModel = timeLineModel2;
            }
            arrayList3.add(timeLineModel);
            str = time2;
            i = i2;
        }
        final ArrayList arrayList4 = arrayList3;
        this.lastSelectedPosition = intRef.element;
        this.focusedPosition = intRef.element;
        this.timeLineAdapter = new BaseQuickAdapter<TimeLineModel, BaseViewHolder>(R.layout.adapter_time_line_item, new ArrayList(arrayList4)) { // from class: com.movieboxpro.android.tv.home.TvGuideFragment.initData.1
            {
                ArrayList<TimeLineModel> arrayList5 = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, TimeLineModel item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                TvGuideFragment.this.bindTimeLine(helper, item);
            }
        };
        getBinding().rvTimeLine.setSelectedItemAtCentered(true);
        getBinding().rvTimeLine.addItemDecoration(new LayoutManagerItemDecoration(0, 16));
        TvRecyclerView tvRecyclerView = getBinding().rvTimeLine;
        BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter = this.timeLineAdapter;
        TvGuideListFragment tvGuideListFragment = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
            baseQuickAdapter = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        getBinding().rvTimeLine.post(new Runnable() { // from class: com.movieboxpro.android.tv.home.TvGuideFragment$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                TvGuideFragment.initData$lambda$1(intRef, arrayList4, this);
            }
        });
        TvGuideListFragment tvGuideListFragmentNewInstance = TvGuideListFragment.INSTANCE.newInstance(str);
        this.listFragment = tvGuideListFragmentNewInstance;
        if (tvGuideListFragmentNewInstance == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listFragment");
            tvGuideListFragmentNewInstance = null;
        }
        tvGuideListFragmentNewInstance.setFocusUpListener(this);
        FragmentManager childFragmentManager = getChildFragmentManager();
        TvGuideListFragment tvGuideListFragment2 = this.listFragment;
        if (tvGuideListFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("listFragment");
        } else {
            tvGuideListFragment = tvGuideListFragment2;
        }
        FragmentUtils.add(childFragmentManager, tvGuideListFragment, R.id.frameLayout);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$1(Ref.IntRef intRef, List list, TvGuideFragment tvGuideFragment) {
        if (CommonExtKt.checkIndexLegal(intRef.element, list)) {
            tvGuideFragment.getBinding().rvTimeLine.scrollToPosition(intRef.element);
        }
    }

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initListener() {
        BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter = this.timeLineAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.tv.home.TvGuideFragment$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                TvGuideFragment.initListener$lambda$2(this.f$0, baseQuickAdapter2, view, i);
            }
        });
        getBinding().rvTimeLine.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.tv.home.TvGuideFragment.initListener.2
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                TvGuideFragment.this.setDayFocus(position, false, itemView);
                itemView.animate().scaleX(1.0f).scaleY(1.0f).setDuration(120L).start();
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                TvGuideFragment.this.focusedPosition = position;
                TvGuideFragment.this.setDayFocus(position, true, itemView);
                itemView.animate().scaleX(1.18f).scaleY(1.18f).setDuration(120L).start();
            }
        });
        getBinding().rvTimeLine.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.tv.home.TvGuideFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                TvGuideFragment.initListener$lambda$3(this.f$0, view, z);
            }
        });
        getBinding().rvTimeLine.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() { // from class: com.movieboxpro.android.tv.home.TvGuideFragment$$ExternalSyntheticLambda2
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnInBorderKeyEventListener
            public final boolean onInBorderKeyEvent(int i, View view) {
                return TvGuideFragment.initListener$lambda$4(this.f$0, i, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(TvGuideFragment tvGuideFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        tvGuideFragment.selectDay(i, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(TvGuideFragment tvGuideFragment, View view, boolean z) {
        if (z) {
            return;
        }
        setDayFocus$default(tvGuideFragment, tvGuideFragment.focusedPosition, false, null, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$4(TvGuideFragment tvGuideFragment, int i, View view) {
        if (i == 33) {
            EventBus.getDefault().post(new ListFocusUpEvent());
            return true;
        }
        if (i != 130) {
            return false;
        }
        tvGuideFragment.selectDay(tvGuideFragment.focusedPosition, true);
        return true;
    }

    @Override // com.movieboxpro.android.listener.OnVideoListFocusListener
    public void onFocusUp() {
        getBinding().rvTimeLine.requestFocus();
    }

    private final void selectDay(int position, boolean moveToList) {
        BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter = this.timeLineAdapter;
        TvGuideListFragment tvGuideListFragment = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
            baseQuickAdapter = null;
        }
        if (CommonExtKt.checkIndexLegal(position, baseQuickAdapter.getData())) {
            BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter2 = this.timeLineAdapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
                baseQuickAdapter2 = null;
            }
            TimeLineModel itemOrNull = baseQuickAdapter2.getItemOrNull(position);
            if (itemOrNull == null) {
                return;
            }
            int i = this.lastSelectedPosition;
            if (i != position) {
                BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter3 = this.timeLineAdapter;
                if (baseQuickAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
                    baseQuickAdapter3 = null;
                }
                if (CommonExtKt.checkIndexLegal(i, baseQuickAdapter3.getData())) {
                    BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter4 = this.timeLineAdapter;
                    if (baseQuickAdapter4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
                        baseQuickAdapter4 = null;
                    }
                    TimeLineModel itemOrNull2 = baseQuickAdapter4.getItemOrNull(this.lastSelectedPosition);
                    if (itemOrNull2 != null) {
                        itemOrNull2.setSelect(false);
                    }
                    BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter5 = this.timeLineAdapter;
                    if (baseQuickAdapter5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
                        baseQuickAdapter5 = null;
                    }
                    baseQuickAdapter5.notifyItemChanged(this.lastSelectedPosition);
                }
                itemOrNull.setSelect(true);
                BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter6 = this.timeLineAdapter;
                if (baseQuickAdapter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
                    baseQuickAdapter6 = null;
                }
                baseQuickAdapter6.notifyItemChanged(position);
                this.lastSelectedPosition = position;
                TvGuideListFragment tvGuideListFragment2 = this.listFragment;
                if (tvGuideListFragment2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("listFragment");
                    tvGuideListFragment2 = null;
                }
                tvGuideListFragment2.refreshData(itemOrNull.getDay());
            }
            if (moveToList) {
                TvGuideListFragment tvGuideListFragment3 = this.listFragment;
                if (tvGuideListFragment3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("listFragment");
                } else {
                    tvGuideListFragment = tvGuideListFragment3;
                }
                tvGuideListFragment.requestListFocus();
            }
        }
    }

    static /* synthetic */ void setDayFocus$default(TvGuideFragment tvGuideFragment, int i, boolean z, View view, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            view = null;
        }
        tvGuideFragment.setDayFocus(i, z, view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setDayFocus(int position, boolean focused, View itemView) {
        BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter = this.timeLineAdapter;
        BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
            baseQuickAdapter = null;
        }
        if (CommonExtKt.checkIndexLegal(position, baseQuickAdapter.getData())) {
            BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter3 = this.timeLineAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
                baseQuickAdapter3 = null;
            }
            TimeLineModel itemOrNull = baseQuickAdapter3.getItemOrNull(position);
            if (itemOrNull == null) {
                return;
            }
            itemOrNull.setFocus(focused);
            if (itemView != null) {
                bindFocusViews(itemView, itemOrNull);
                return;
            }
            BaseQuickAdapter<TimeLineModel, BaseViewHolder> baseQuickAdapter4 = this.timeLineAdapter;
            if (baseQuickAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("timeLineAdapter");
            } else {
                baseQuickAdapter2 = baseQuickAdapter4;
            }
            baseQuickAdapter2.notifyItemChanged(position);
        }
    }

    private final void bindFocusViews(View itemView, TimeLineModel item) {
        TextView textView = (TextView) itemView.findViewById(R.id.tvWeek);
        View viewFindViewById = itemView.findViewById(R.id.viewFocusRing);
        if (item.isFocus()) {
            Intrinsics.checkNotNull(viewFindViewById);
            CommonExtKt.visible(viewFindViewById);
            Intrinsics.checkNotNull(textView);
            CommonExtKt.textColor(textView, R.color.color_main_yellow);
            return;
        }
        Intrinsics.checkNotNull(viewFindViewById);
        CommonExtKt.gone(viewFindViewById);
        Intrinsics.checkNotNull(textView);
        CommonExtKt.textColor(textView, item.isSelect() ? R.color.color_main_yellow : R.color.gray_999);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bindTimeLine(BaseViewHolder helper, TimeLineModel item) {
        TextView textView = (TextView) helper.getView(R.id.tvWeek);
        TextView textView2 = (TextView) helper.getView(R.id.tvDay);
        View view = helper.getView(R.id.viewFocusRing);
        textView.setText(item.getWeekCode());
        textView2.setText(String.valueOf(item.getDayNumber()));
        if (item.isSelect()) {
            textView2.setBackgroundResource(R.drawable.yelow_circle_shape);
            CommonExtKt.textColor(textView2, R.color.color_main);
            CommonExtKt.textColor(textView, R.color.color_main_yellow);
        } else {
            CommonExtKt.textColor(textView2, R.color.white);
            CommonExtKt.textColor(textView, item.isFocus() ? R.color.white : R.color.gray_999);
            if (item.isToday()) {
                textView2.setBackgroundResource(R.drawable.yelow_1border_circle_shape);
            } else {
                textView2.setBackgroundColor(CommonExtKt.colorInt(R.color.transparent));
            }
        }
        if (item.isFocus()) {
            CommonExtKt.visible(view);
            CommonExtKt.textColor(textView, R.color.color_main_yellow);
        } else {
            CommonExtKt.gone(view);
        }
    }

    private final boolean isToday(Calendar calendar) {
        Calendar calendar2 = Calendar.getInstance();
        return calendar2.get(1) == calendar.get(1) && calendar2.get(6) == calendar.get(6);
    }

    private final String getWeekDayLetter(int week) {
        switch (week) {
            case 1:
            case 7:
                return ExifInterface.LATITUDE_SOUTH;
            case 2:
                return "M";
            case 3:
            case 5:
                return ExifInterface.GPS_DIRECTION_TRUE;
            case 4:
                return ExifInterface.LONGITUDE_WEST;
            case 6:
                return "F";
            default:
                return "";
        }
    }

    private final Pair<Date, Date> getLastTimeInterval(Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.setTime(date);
        int i = calendar.get(7) - 1;
        int i2 = i > 0 ? i : 7;
        calendar.add(5, (-6) - i2);
        calendar2.add(5, 0 - i2);
        return new Pair<>(calendar.getTime(), calendar2.getTime());
    }

    private final Pair<Date, Date> getTimeInterval(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (1 == calendar.get(7)) {
            calendar.add(5, -1);
        }
        calendar.setFirstDayOfWeek(2);
        calendar.add(5, calendar.getFirstDayOfWeek() - calendar.get(7));
        Date time = calendar.getTime();
        calendar.add(5, 6);
        return new Pair<>(time, calendar.getTime());
    }

    private final Pair<Date, Date> getTimeNextWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.setTime(date);
        calendar2.setTime(date);
        int i = calendar.get(7) - 1;
        int i2 = i > 0 ? i : 7;
        calendar.add(5, 8 - i2);
        calendar2.add(5, 14 - i2);
        return new Pair<>(calendar.getTime(), calendar2.getTime());
    }

    private final List<Date> findDates(Pair<? extends Date, ? extends Date> range) {
        return findDates(range.getFirst(), range.getSecond());
    }

    private final List<Date> findDates(Date dBegin, Date dEnd) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(dBegin);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dBegin);
        while (dEnd.after(calendar.getTime())) {
            calendar.add(5, 1);
            arrayList.add(calendar.getTime());
        }
        return arrayList;
    }

    /* JADX INFO: compiled from: TvGuideFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/tv/home/TvGuideFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/tv/home/TvGuideFragment;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TvGuideFragment newInstance() {
            return new TvGuideFragment();
        }
    }
}
