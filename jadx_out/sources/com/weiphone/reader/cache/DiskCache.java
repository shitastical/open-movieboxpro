package com.weiphone.reader.cache;

import android.text.TextUtils;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.MLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DiskCache.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0016J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u0005H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \u0007*\u0004\u0018\u00010\u00050\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/weiphone/reader/cache/DiskCache;", "Lcom/weiphone/reader/cache/ICache;", "<init>", "()V", "tag", "", FileDownloadService.PARAMS_KEY_PATH, "kotlin.jvm.PlatformType", "get", "Lio/reactivex/Observable;", "key", "put", "", "value", "clear", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DiskCache implements ICache {
    private final String tag = "DiskCache";
    private final String path = Constant.DIR_CACHE;

    @Override // com.weiphone.reader.cache.ICache
    public Observable<String> get(final String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Observable<String> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.weiphone.reader.cache.DiskCache$$ExternalSyntheticLambda0
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                DiskCache.get$lambda$0(this.f$0, key, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create(...)");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void get$lambda$0(DiskCache diskCache, String str, ObservableEmitter it) {
        Intrinsics.checkNotNullParameter(it, "it");
        MLog.d(diskCache.tag, "load for: " + str + " from disk");
        File file = new File(diskCache.path, str);
        if (it.isDisposed()) {
            return;
        }
        if (file.isFile()) {
            Charset charsetForName = Charset.forName("UTF-8");
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(...)");
            String text = FilesKt.readText(file, charsetForName);
            if (!TextUtils.isEmpty(text)) {
                MLog.d(diskCache.tag, "cache found for: " + str + " from disk");
                it.onNext(text);
            } else {
                MLog.d(diskCache.tag, "no cache found for: " + str + " from disk");
                it.onNext("");
            }
        } else {
            MLog.d(diskCache.tag, "no cache found for: " + str + " from disk");
            it.onNext("");
        }
        it.onComplete();
    }

    @Override // com.weiphone.reader.cache.ICache
    public void put(final String key, final String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        MLog.d(this.tag, "save to disk for: " + key);
        Observable.create(new ObservableOnSubscribe() { // from class: com.weiphone.reader.cache.DiskCache$$ExternalSyntheticLambda1
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                DiskCache.put$lambda$1(this.f$0, key, value, observableEmitter);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void put$lambda$1(DiskCache diskCache, String str, String str2, ObservableEmitter it) {
        Intrinsics.checkNotNullParameter(it, "it");
        File file = new File(diskCache.path, str);
        if (file.isFile()) {
            file.delete();
        }
        Charset charsetForName = Charset.forName("UTF-8");
        Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(...)");
        FilesKt.writeText(file, str2, charsetForName);
        if (it.isDisposed()) {
            return;
        }
        it.onComplete();
    }

    @Override // com.weiphone.reader.cache.ICache
    public void clear(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        MLog.d(this.tag, "clear cache for " + key + " from disk");
        File file = new File(this.path, key);
        if (file.isFile()) {
            file.delete();
        }
    }
}
