package com.movieboxpro.android.view.tvview;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import androidx.leanback.widget.BaseCardView;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;

/* JADX INFO: compiled from: BaseVideoCardView.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001a\u00020\u0012H&J\b\u0010\u0013\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\u000eH\u0016R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/tvview/BaseVideoCardView;", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/leanback/widget/BaseCardView;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", TvContractCompat.ProgramColumns.COLUMN_TITLE, "Landroid/widget/TextView;", "getTitle", "()Landroid/widget/TextView;", "setTitle", "(Landroid/widget/TextView;)V", "updateUI", "", "item", "(Ljava/lang/Object;)V", "getLayoutId", "", "showTitle", "hideTitle", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class BaseVideoCardView<T> extends BaseCardView {
    private TextView title;

    public abstract int getLayoutId();

    public abstract void updateUI(T item);

    public BaseVideoCardView(Context context) {
        super(context, null, R.style.DefaultCardStyle);
        LayoutInflater.from(context).inflate(getLayoutId(), this);
        setFocusable(true);
        setCardType(0);
        setBackground(null);
    }

    protected final TextView getTitle() {
        return this.title;
    }

    protected final void setTitle(TextView textView) {
        this.title = textView;
    }

    public void showTitle() {
        TextView textView = this.title;
        if (textView != null) {
            CommonExtKt.visible(textView);
        }
    }

    public void hideTitle() {
        TextView textView = this.title;
        if (textView != null) {
            CommonExtKt.gone(textView);
        }
    }
}
