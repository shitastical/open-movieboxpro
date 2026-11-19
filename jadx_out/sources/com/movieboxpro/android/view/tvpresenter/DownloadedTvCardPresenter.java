package com.movieboxpro.android.view.tvpresenter;

import android.content.Context;
import android.view.View;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.tvview.DownloadTvCardView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadedTvCardPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0011\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\r2\u0006\u0010\b\u001a\u00020\tJ\b\u0010\u000f\u001a\u00020\u0002H\u0014J\u001c\u0010\u0010\u001a\u00020\r2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/view/tvpresenter/DownloadedTvCardPresenter;", "Lcom/movieboxpro/android/view/tvpresenter/AbstractCardPresenter;", "Lcom/movieboxpro/android/view/tvview/DownloadTvCardView;", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/dialog/DialogAction$ActionListener;", "edit", "", "setEdit", "", "setLongClickListener", "onCreateView", "onBindViewHolder", "card", "cardView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadedTvCardPresenter extends AbstractCardPresenter<DownloadTvCardView, DownloadFile> {
    private boolean edit;
    private DialogAction.ActionListener listener;

    public DownloadedTvCardPresenter(Context context) {
        super(context);
    }

    public final void setEdit(boolean edit) {
        this.edit = edit;
    }

    public final void setLongClickListener(DialogAction.ActionListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.view.tvpresenter.AbstractCardPresenter
    public DownloadTvCardView onCreateView() {
        return new DownloadTvCardView(getContext());
    }

    @Override // com.movieboxpro.android.view.tvpresenter.AbstractCardPresenter
    public void onBindViewHolder(DownloadFile card, DownloadTvCardView cardView) {
        if (card != null) {
            if (cardView != null) {
                cardView.setEdit(this.edit);
            }
            if (cardView != null) {
                cardView.updateUI(card);
            }
            if (cardView != null) {
                cardView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.movieboxpro.android.view.tvpresenter.DownloadedTvCardPresenter$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnLongClickListener
                    public final boolean onLongClick(View view) {
                        return DownloadedTvCardPresenter.onBindViewHolder$lambda$1$lambda$0(this.f$0, view);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$1$lambda$0(DownloadedTvCardPresenter downloadedTvCardPresenter, View view) {
        DialogAction.ActionListener actionListener = downloadedTvCardPresenter.listener;
        if (actionListener == null) {
            return true;
        }
        actionListener.onClick();
        return true;
    }
}
