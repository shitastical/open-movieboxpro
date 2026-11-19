package com.movieboxpro.android.view.tvpresenter;

import android.content.Context;
import android.view.ViewGroup;
import androidx.leanback.R;
import androidx.leanback.widget.BaseCardView;
import androidx.leanback.widget.HorizontalGridView;
import androidx.leanback.widget.Presenter;
import com.movieboxpro.android.utils.DensityUtils;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractCardPresenter<T extends BaseCardView, M> extends Presenter {
    private static final String TAG = "AbstractCardPresenter";
    private final Context mContext;

    public abstract void onBindViewHolder(M card, T cardView);

    protected abstract T onCreateView();

    public void onUnbindViewHolder(T cardView) {
    }

    public AbstractCardPresenter(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return this.mContext;
    }

    @Override // androidx.leanback.widget.Presenter
    public final Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        ((HorizontalGridView) parent.findViewById(R.id.row_content)).setItemSpacing(DensityUtils.dp2px(this.mContext, 15.0f));
        return new Presenter.ViewHolder(onCreateView());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.leanback.widget.Presenter
    public final void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        onBindViewHolder(item, (BaseCardView) viewHolder.view);
    }

    @Override // androidx.leanback.widget.Presenter
    public final void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        onUnbindViewHolder((BaseCardView) viewHolder.view);
    }
}
