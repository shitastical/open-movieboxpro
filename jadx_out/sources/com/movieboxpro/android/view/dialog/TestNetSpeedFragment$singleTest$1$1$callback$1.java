package com.movieboxpro.android.view.dialog;

import androidx.core.app.NotificationCompat;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.TestNetMultiModel;
import com.movieboxpro.android.utils.FileUtils;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import io.reactivex.disposables.Disposable;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: TestNetSpeedFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/movieboxpro/android/view/dialog/TestNetSpeedFragment$singleTest$1$1$callback$1", "Lokhttp3/Callback;", "onFailure", "", NotificationCompat.CATEGORY_CALL, "Lokhttp3/Call;", "e", "Ljava/io/IOException;", "onResponse", "response", "Lokhttp3/Response;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TestNetSpeedFragment$singleTest$1$1$callback$1 implements Callback {
    final /* synthetic */ Ref.ObjectRef<Disposable> $disposable;
    final /* synthetic */ File $file;
    final /* synthetic */ TestNetSpeedFragment.OnTestSpeedListener $listener;
    final /* synthetic */ TestNetMultiModel $model;
    final /* synthetic */ int $position;
    final /* synthetic */ Ref.BooleanRef $success;
    final /* synthetic */ TestNetSpeedFragment this$0;

    TestNetSpeedFragment$singleTest$1$1$callback$1(TestNetSpeedFragment testNetSpeedFragment, Ref.ObjectRef<Disposable> objectRef, TestNetMultiModel testNetMultiModel, int i, TestNetSpeedFragment.OnTestSpeedListener onTestSpeedListener, Ref.BooleanRef booleanRef, File file) {
        this.this$0 = testNetSpeedFragment;
        this.$disposable = objectRef;
        this.$model = testNetMultiModel;
        this.$position = i;
        this.$listener = onTestSpeedListener;
        this.$success = booleanRef;
        this.$file = file;
    }

    @Override // okhttp3.Callback
    public void onFailure(Call call, IOException e) {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(e, "e");
        TvRecyclerView tvRecyclerView = this.this$0.getBinding().rvTestOne;
        if (tvRecyclerView != null) {
            final Ref.ObjectRef<Disposable> objectRef = this.$disposable;
            final TestNetMultiModel testNetMultiModel = this.$model;
            final TestNetSpeedFragment testNetSpeedFragment = this.this$0;
            final int i = this.$position;
            final TestNetSpeedFragment.OnTestSpeedListener onTestSpeedListener = this.$listener;
            tvRecyclerView.post(new Runnable() { // from class: com.movieboxpro.android.view.dialog.TestNetSpeedFragment$singleTest$1$1$callback$1$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    TestNetSpeedFragment$singleTest$1$1$callback$1.onFailure$lambda$0(objectRef, testNetMultiModel, testNetSpeedFragment, i, onTestSpeedListener);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onFailure$lambda$0(Ref.ObjectRef objectRef, TestNetMultiModel testNetMultiModel, TestNetSpeedFragment testNetSpeedFragment, int i, TestNetSpeedFragment.OnTestSpeedListener onTestSpeedListener) {
        Disposable disposable = (Disposable) objectRef.element;
        if (disposable != null) {
            disposable.dispose();
        }
        testNetMultiModel.setStatus(2);
        testNetSpeedFragment.getTestNetAdapter(i).notifyItemChanged(testNetSpeedFragment.getTestNetPosition(i));
        testNetSpeedFragment.getTestLoadingAdapter(i).getData().get(testNetSpeedFragment.getTestLoadingPosition(i)).setStatus(2);
        testNetSpeedFragment.getTestLoadingAdapter(i).notifyItemChanged(testNetSpeedFragment.getTestLoadingPosition(i));
        testNetSpeedFragment.deleteCacheFile(testNetMultiModel.getId());
        if (onTestSpeedListener != null) {
            onTestSpeedListener.onFailed(i);
        }
    }

    @Override // okhttp3.Callback
    public void onResponse(Call call, Response response) throws Throwable {
        Intrinsics.checkNotNullParameter(call, "call");
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.getIsSuccessful()) {
            this.$success.element = true;
            TestNetMultiModel testNetMultiModel = this.$model;
            ResponseBody responseBodyBody = response.body();
            testNetMultiModel.setContentLength(responseBodyBody != null ? responseBodyBody.getContentLength() : 0L);
            this.$model.setStartTime(System.currentTimeMillis());
            FileUtils.createOrExistsDir(new File(Constant.DIR_DOWNLOAD_TEST_SPEED));
            File file = this.$file;
            ResponseBody responseBodyBody2 = response.body();
            FileUtils.writeFileFromIS(file, responseBodyBody2 != null ? responseBodyBody2.byteStream() : null);
            return;
        }
        this.$model.setStatus(2);
        this.this$0.getTestNetAdapter(this.$position).notifyItemChanged(this.this$0.getTestNetPosition(this.$position));
        this.this$0.getTestLoadingAdapter(this.$position).getData().get(this.this$0.getTestLoadingPosition(this.$position)).setStatus(2);
        this.this$0.getTestLoadingAdapter(this.$position).notifyItemChanged(this.this$0.getTestLoadingPosition(this.$position));
        Disposable disposable = this.$disposable.element;
        if (disposable != null) {
            disposable.dispose();
        }
        this.this$0.deleteCacheFile(this.$model.getId());
        TestNetSpeedFragment.OnTestSpeedListener onTestSpeedListener = this.$listener;
        if (onTestSpeedListener != null) {
            onTestSpeedListener.onFailed(this.$position);
        }
    }
}
