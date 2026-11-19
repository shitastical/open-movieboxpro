package com.movieboxpro.android.view.tvview;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.androidtv.R;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: DownloadTvCardView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016J\b\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/tvview/DownloadTvCardView;", "Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "edit", "", "setEdit", "", "updateUI", "item", "getLayoutId", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadTvCardView extends BaseVideoCardView<DownloadFile> {
    private boolean edit;

    public DownloadTvCardView(Context context) {
        super(context);
    }

    public final void setEdit(boolean edit) {
        this.edit = edit;
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public void updateUI(DownloadFile item) {
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) findViewById(R.id.tvTitle);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        TextView textView2 = (TextView) findViewById(R.id.tvSize);
        findViewById(R.id.view);
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox.setChecked(item.isCheck());
        if (this.edit) {
            Intrinsics.checkNotNull(checkBox);
            CommonExtKt.visible(checkBox);
        } else {
            Intrinsics.checkNotNull(checkBox);
            CommonExtKt.gone(checkBox);
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("%s.%s", Arrays.copyOf(new Object[]{Integer.valueOf(item.getEpisode()), item.getEpisodeName()}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
        GlideUtils.loadCornerLandHolder(getContext(), item.getThumb(), imageView, 8);
        textView2.setText(FileUtils.byte2FitMemorySize(item.getSize()));
    }

    @Override // com.movieboxpro.android.view.tvview.BaseVideoCardView
    public int getLayoutId() {
        return R.layout.adapter_downloaded_tv_card_view;
    }
}
