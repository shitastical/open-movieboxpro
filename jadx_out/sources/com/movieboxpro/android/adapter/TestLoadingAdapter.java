package com.movieboxpro.android.adapter;

import android.widget.ImageView;
import android.widget.ProgressBar;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.model.TestLoadingModel;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TestLoadingAdapter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/adapter/TestLoadingAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/TestLoadingModel;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "<init>", "()V", "convert", "", "holder", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TestLoadingAdapter extends BaseQuickAdapter<TestLoadingModel, BaseViewHolder> {
    public TestLoadingAdapter() {
        super(R.layout.adapter_retry_speed_item, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, TestLoadingModel item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ProgressBar progressBar = (ProgressBar) holder.getView(R.id.loading);
        ImageView imageView = (ImageView) holder.getView(R.id.ivRetry);
        int status = item.getStatus();
        if (status == 0) {
            CommonExtKt.visible(progressBar);
            CommonExtKt.gone(imageView);
            return;
        }
        if (status == 1) {
            CommonExtKt.gone(progressBar);
            CommonExtKt.visible(imageView);
            imageView.setImageResource(R.mipmap.ic_stop_test_speed);
            return;
        }
        if (status == 2) {
            CommonExtKt.gone(progressBar);
            CommonExtKt.visible(imageView);
            imageView.setImageResource(R.mipmap.ic_test_speed_refresh);
        } else if (status == 3) {
            CommonExtKt.gone(progressBar);
            CommonExtKt.visible(imageView);
            imageView.setImageResource(R.mipmap.ic_test_speed_refresh);
        } else if (status == 4) {
            CommonExtKt.visible(progressBar);
            CommonExtKt.gone(imageView);
        } else {
            if (status != 5) {
                return;
            }
            CommonExtKt.gone(progressBar);
            CommonExtKt.visible(imageView);
            imageView.setImageResource(R.mipmap.ic_test_speed_refresh);
        }
    }
}
