package com.movieboxpro.android.view.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.base.BaseBottomFullScreenDialogFragment;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentArgsKt;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.DialogChooseSeasonBinding;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;

/* JADX INFO: compiled from: ChooseSeasonDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001d2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002\u001c\u001dB\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013J\u001a\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u0015H\u0002R+\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\f\u0010\r\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00068B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\r\u001a\u0004\b\u000f\u0010\t\"\u0004\b\u0010\u0010\u000bR\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialog;", "Lcom/movieboxpro/android/base/BaseBottomFullScreenDialogFragment;", "Lcom/movieboxpro/androidtv/databinding/DialogChooseSeasonBinding;", "<init>", "()V", "<set-?>", "", "totalSeason", "getTotalSeason", "()I", "setTotalSeason", "(I)V", "totalSeason$delegate", "Lkotlin/properties/ReadWriteProperty;", "currSeason", "getCurrSeason", "setCurrSeason", "currSeason$delegate", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialog$ChooseSeasonListener;", "setListener", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initData", "ChooseSeasonListener", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseSeasonDialog extends BaseBottomFullScreenDialogFragment<DialogChooseSeasonBinding> {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(ChooseSeasonDialog.class, "totalSeason", "getTotalSeason()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(ChooseSeasonDialog.class, "currSeason", "getCurrSeason()I", 0))};

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: renamed from: currSeason$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty currSeason;
    private ChooseSeasonListener listener;

    /* JADX INFO: renamed from: totalSeason$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty totalSeason;

    /* JADX INFO: compiled from: ChooseSeasonDialog.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialog$ChooseSeasonListener;", "", "onChooseSeason", "", DealWatchNextService.SEASON, "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface ChooseSeasonListener {
        void onChooseSeason(int season);
    }

    public ChooseSeasonDialog() {
        super(R.layout.dialog_choose_season);
        ChooseSeasonDialog chooseSeasonDialog = this;
        this.totalSeason = FragmentArgsKt.arg(chooseSeasonDialog);
        this.currSeason = FragmentArgsKt.arg(chooseSeasonDialog);
    }

    private final int getTotalSeason() {
        return ((Number) this.totalSeason.getValue(this, $$delegatedProperties[0])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setTotalSeason(int i) {
        this.totalSeason.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCurrSeason() {
        return ((Number) this.currSeason.getValue(this, $$delegatedProperties[1])).intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setCurrSeason(int i) {
        this.currSeason.setValue(this, $$delegatedProperties[1], Integer.valueOf(i));
    }

    public final void setListener(ChooseSeasonListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    /* JADX INFO: compiled from: ChooseSeasonDialog.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialog;", "totalSeason", "", "currSeason", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChooseSeasonDialog newInstance(int totalSeason, int currSeason) {
            ChooseSeasonDialog chooseSeasonDialog = new ChooseSeasonDialog();
            chooseSeasonDialog.setTotalSeason(totalSeason);
            chooseSeasonDialog.setCurrSeason(currSeason);
            return chooseSeasonDialog;
        }
    }

    @Override // com.movieboxpro.android.base.BaseBottomFullScreenDialogFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [com.movieboxpro.android.view.dialog.ChooseSeasonDialog$initData$adapter$1] */
    private final void initData() {
        final ArrayList arrayList = new ArrayList();
        int totalSeason = getTotalSeason();
        int i = 1;
        if (1 <= totalSeason) {
            while (true) {
                arrayList.add(Integer.valueOf(i));
                if (i == totalSeason) {
                    break;
                } else {
                    i++;
                }
            }
        }
        final int i2 = R.layout.adapter_season_item;
        final ?? r2 = new CommonBaseAdapter<Integer>(arrayList, this, i2) { // from class: com.movieboxpro.android.view.dialog.ChooseSeasonDialog$initData$adapter$1
            final /* synthetic */ ChooseSeasonDialog this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(i2, arrayList);
                this.this$0 = this;
            }

            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public /* bridge */ /* synthetic */ void convert(BaseViewHolder baseViewHolder, Object obj) {
                convert(baseViewHolder, ((Number) obj).intValue());
            }

            protected void convert(BaseViewHolder holder, int item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                TextView textView = (TextView) holder.getView(R.id.textView);
                textView.setText("SEASON " + item);
                if (this.this$0.getCurrSeason() == item) {
                    CommonExtKt.textColor(textView, R.color.color_main_yellow);
                } else {
                    CommonExtKt.textColor(textView, R.color.white_30alpha);
                }
            }
        };
        r2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.dialog.ChooseSeasonDialog$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i3) {
                ChooseSeasonDialog.initData$lambda$0(r2, this, baseQuickAdapter, view, i3);
            }
        });
        getBinding().recyclerView.setSpacingWithMargins(CommonExtKt.dp2Px(40), CommonExtKt.dp2Px(40));
        getBinding().recyclerView.setAdapter((RecyclerView.Adapter) r2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initData$lambda$0(ChooseSeasonDialog$initData$adapter$1 chooseSeasonDialog$initData$adapter$1, ChooseSeasonDialog chooseSeasonDialog, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        int iIntValue = chooseSeasonDialog$initData$adapter$1.getItem(i).intValue();
        ChooseSeasonListener chooseSeasonListener = chooseSeasonDialog.listener;
        if (chooseSeasonListener != null) {
            chooseSeasonListener.onChooseSeason(iIntValue);
        }
        chooseSeasonDialog.dismiss();
    }
}
