package com.weiphone.reader.cache;

import android.text.TextUtils;
import android.util.LruCache;
import com.movieboxpro.android.utils.MLog;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MemoryCache.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\f\u001a\u00020\u0005H\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0005H\u0016J\u0010\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u0005H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/weiphone/reader/cache/MemoryCache;", "Lcom/weiphone/reader/cache/ICache;", "<init>", "()V", "tag", "", "mCacheSize", "", "mCache", "Landroid/util/LruCache;", "get", "Lio/reactivex/Observable;", "key", "put", "", "value", "clear", "MyLruCache", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MemoryCache implements ICache {
    private final LruCache<String, String> mCache;
    private final int mCacheSize;
    private final String tag = "MemoryCache";

    public MemoryCache() {
        int iMaxMemory = (int) (Runtime.getRuntime().maxMemory() / ((long) 8));
        this.mCacheSize = iMaxMemory;
        this.mCache = new MyLruCache(iMaxMemory);
    }

    @Override // com.weiphone.reader.cache.ICache
    public Observable<String> get(final String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        Observable<String> observableCreate = Observable.create(new ObservableOnSubscribe() { // from class: com.weiphone.reader.cache.MemoryCache$$ExternalSyntheticLambda0
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                MemoryCache.get$lambda$0(this.f$0, key, observableEmitter);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create(...)");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void get$lambda$0(MemoryCache memoryCache, String str, ObservableEmitter it) {
        Intrinsics.checkNotNullParameter(it, "it");
        MLog.d(memoryCache.tag, "load for: " + str + " from memory");
        String str2 = memoryCache.mCache.get(str);
        if (it.isDisposed()) {
            return;
        }
        if (!TextUtils.isEmpty(str2)) {
            MLog.d(memoryCache.tag, "cache found for: " + str + " from memory");
            it.onNext(str2);
        } else {
            MLog.d(memoryCache.tag, "no cache found for: " + str + " from memory");
            it.onNext("");
        }
        it.onComplete();
    }

    @Override // com.weiphone.reader.cache.ICache
    public void put(String key, String value) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        MLog.d(this.tag, "save to memory for: " + key);
        this.mCache.put(key, value);
    }

    @Override // com.weiphone.reader.cache.ICache
    public void clear(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        MLog.d(this.tag, "clear cache for " + key + " from memory");
        this.mCache.remove(key);
    }

    /* JADX INFO: compiled from: MemoryCache.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0014¨\u0006\n"}, d2 = {"Lcom/weiphone/reader/cache/MemoryCache$MyLruCache;", "Landroid/util/LruCache;", "", "maxSize", "", "<init>", "(I)V", "sizeOf", "key", "value", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class MyLruCache extends LruCache<String, String> {
        public MyLruCache(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.util.LruCache
        public int sizeOf(String key, String value) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            Charset charsetForName = Charset.forName("utf-8");
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(...)");
            byte[] bytes = value.getBytes(charsetForName);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            return bytes.length;
        }
    }
}
