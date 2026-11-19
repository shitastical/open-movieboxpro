package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.core.os.BundleKt;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.model.MovieListFilter;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.androidtv.R;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ChooseListSortDialog.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u00132\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0002H\u0016J\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\bH\u0016J\b\u0010\u0012\u001a\u00020\bH\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseListSortDialog;", "Lcom/movieboxpro/android/view/dialog/BaseVerticalListDialogFragment;", "Lcom/movieboxpro/android/model/MovieListFilter;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$EditActionListener;", "setListener", "", "setItemLayout", "", "initItemView", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "onItemClicked", "position", "onStart", "initData", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseListSortDialog extends BaseVerticalListDialogFragment<MovieListFilter> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private DialogAction.EditActionListener listener;

    public final void setListener(DialogAction.EditActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public int setItemLayout() {
        return R.layout.adapter_choose_list_sort;
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void initItemView(BaseViewHolder helper, MovieListFilter item) {
        Intrinsics.checkNotNullParameter(helper, "helper");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) helper.getView(R.id.textView);
        if (item.isSelect()) {
            textView.setTextColor(CommonExtKt.colorInt(R.color.color_main_yellow));
        } else {
            textView.setTextColor(CommonExtKt.colorInt(R.color.white_70alpha));
        }
        textView.setText(item.getTitle());
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void onItemClicked(int position) {
        DialogAction.EditActionListener editActionListener = this.listener;
        if (editActionListener != null) {
            editActionListener.onClick(getAdapter().getItem(position).getTitle());
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment, com.movieboxpro.android.view.dialog.BaseTransparentDialogFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            getResources().getDisplayMetrics();
            attributes.width = DensityUtils.dp2px(App.getContext(), 460.0f);
            attributes.height = -2;
            attributes.gravity = 17;
            attributes.dimAmount = 0.95f;
            window.setAttributes(attributes);
        }
    }

    @Override // com.movieboxpro.android.view.dialog.BaseVerticalListDialogFragment
    public void initData() {
        Bundle arguments = getArguments();
        Object obj = null;
        String string = arguments != null ? arguments.getString("filter") : null;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MovieListFilter("Default"));
        arrayList.add(new MovieListFilter("Name from A-Z"));
        arrayList.add(new MovieListFilter("Name from Z-A"));
        arrayList.add(new MovieListFilter("Latest Added"));
        arrayList.add(new MovieListFilter("Earliest Added"));
        arrayList.add(new MovieListFilter("Latest Released"));
        arrayList.add(new MovieListFilter("Earliest Released"));
        for (Object obj2 : arrayList) {
            if (Intrinsics.areEqual(((MovieListFilter) obj2).getTitle(), string)) {
                obj = obj2;
                break;
            }
        }
        MovieListFilter movieListFilter = (MovieListFilter) obj;
        if (movieListFilter != null) {
            movieListFilter.setSelect(true);
        }
        getAdapter().setList(arrayList);
    }

    /* JADX INFO: compiled from: ChooseListSortDialog.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/ChooseListSortDialog$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/ChooseListSortDialog;", "filter", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ChooseListSortDialog newInstance(String filter) {
            Intrinsics.checkNotNullParameter(filter, "filter");
            ChooseListSortDialog chooseListSortDialog = new ChooseListSortDialog();
            chooseListSortDialog.setArguments(BundleKt.bundleOf(TuplesKt.to("filter", filter)));
            return chooseListSortDialog;
        }
    }
}
