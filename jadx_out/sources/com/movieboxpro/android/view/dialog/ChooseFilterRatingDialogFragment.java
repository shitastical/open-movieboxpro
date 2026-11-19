package com.movieboxpro.android.view.dialog;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.listener.CommonItemClickListener;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: ChooseFilterRatingDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\u0012\u001a\u00020\bH\u0016J\u0010\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\bH\u0016J\b\u0010\u0015\u001a\u00020\u0011H\u0016J\u0018\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0002H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseFilterRatingDialogFragment;", "Lcom/movieboxpro/android/view/dialog/BaseVerticalListDialogFragment;", "", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/CommonItemClickListener;", "<set-?>", "", "pos", "getPos", "()I", "setPos", "(I)V", "pos$delegate", "Lkotlin/properties/ReadWriteProperty;", "setListener", "", "setItemLayout", "onItemClicked", "position", "initData", "initItemView", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseFilterRatingDialogFragment extends BaseVerticalListDialogFragment<String> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(ChooseFilterRatingDialogFragment.class, "pos", "getPos()I", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private CommonItemClickListener listener;

    /* JADX INFO: renamed from: pos$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty pos = FragmentArgsKt.arg(this);

    private final int getPos() {
        return ((Number) this.pos.getValue(this, $$delegatedProperties[0])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setPos(int i) {
        this.pos.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    /* JADX INFO: compiled from: ChooseFilterRatingDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseFilterRatingDialogFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChooseFilterRatingDialogFragment;", "pos", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChooseFilterRatingDialogFragment newInstance(int pos) {
            ChooseFilterRatingDialogFragment chooseFilterRatingDialogFragment = new ChooseFilterRatingDialogFragment();
            chooseFilterRatingDialogFragment.setPos(pos);
            return chooseFilterRatingDialogFragment;
        }
    }

    public final void setListener(CommonItemClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public int setItemLayout() {
        return R.layout.adapter_filter_rating_item;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void onItemClicked(int position) {
        CommonItemClickListener commonItemClickListener = this.listener;
        if (commonItemClickListener != null) {
            commonItemClickListener.onItemClicked(position, ChooseLanguageDialogFragment.TYPE);
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void initData() {
        if (getPos() > 0) {
            getBinding().recyclerView.postDelayed(new Runnable() { // from class: com.movieboxpro.android.view.dialog.ChooseFilterRatingDialogFragment$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ChooseFilterRatingDialogFragment.initData$lambda$0(this.f$0);
                }
            }, 200L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$0(ChooseFilterRatingDialogFragment chooseFilterRatingDialogFragment) {
        chooseFilterRatingDialogFragment.getBinding().recyclerView.setSelectionWithSmooth(chooseFilterRatingDialogFragment.getPos());
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void initItemView(BaseViewHolder helper, String item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        helper.setText(R.id.tvTitle, item);
    }
}
