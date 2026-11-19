package com.movieboxpro.android.utils;

import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.service.DealWatchNextService;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.videolan.libvlc.util.AndroidUtil;

/* JADX INFO: compiled from: VLCOptions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rR!\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\r0\u000fj\b\u0012\u0004\u0012\u00020\r`\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/utils/VLCOptions;", "", "<init>", "()V", "uploadError2", "", "isVlc", "", "boxType", "", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "id", "", "libOptions", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getLibOptions", "()Ljava/util/ArrayList;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VLCOptions {
    public static final VLCOptions INSTANCE = new VLCOptions();

    private VLCOptions() {
    }

    public final void uploadError2(final boolean isVlc, final int boxType, final int season, final int episode, final String id) {
        Observable observableJust = Observable.just("");
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.utils.VLCOptions$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VLCOptions.uploadError2$lambda$1(isVlc, (String) obj);
            }
        };
        Observable map = observableJust.map(new Function() { // from class: com.movieboxpro.android.utils.VLCOptions$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return VLCOptions.uploadError2$lambda$2(function1, obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.utils.VLCOptions$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VLCOptions.uploadError2$lambda$3(boxType, id, season, episode, (String) obj);
            }
        };
        Observable observableCompose = map.flatMap(new Function() { // from class: com.movieboxpro.android.utils.VLCOptions$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return VLCOptions.uploadError2$lambda$4(function2, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(observableCompose, null, null, null, new Function1() { // from class: com.movieboxpro.android.utils.VLCOptions$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return VLCOptions.uploadError2$lambda$5((Disposable) obj);
            }
        }, 7, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String uploadError2$lambda$2(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (String) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String uploadError2$lambda$1(boolean z, String it) throws IOException {
        String str;
        Intrinsics.checkNotNullParameter(it, "it");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("logcat -d").getInputStream()));
        StringBuilder sb = new StringBuilder();
        if (z) {
            str = "vlc";
        } else {
            str = "IJKMEDIA";
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                if (line != null && StringsKt.contains$default((CharSequence) line, (CharSequence) str, false, 2, (Object) null)) {
                    sb.append(line);
                    sb.append(ShellUtil.COMMAND_LINE_END);
                }
            } else {
                return sb.toString();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource uploadError2$lambda$4(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource uploadError2$lambda$3(int i, String str, int i2, int i3, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Http.getService().Movie_feedback2(API.Common.MOVIE_FEEDBACK, App.isLogin() ? App.getUserData().getMaster_uid() : "", i, str, -1, "", "14", SystemUtils.getMsg() + ShellUtil.COMMAND_LINE_END + it, i2, i3, "").compose(RxUtils.rxTranslateMsg());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit uploadError2$lambda$5(Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    public final ArrayList<String> getLibOptions() {
        ArrayList<String> arrayList = new ArrayList<>(50);
        if (AndroidUtil.isLolliPopOrLater) {
            arrayList.add("--audio-time-stretch");
        } else {
            arrayList.add("--no-audio-time-stretch");
        }
        arrayList.add("--avcodec-skiploopfilter");
        arrayList.add("-1");
        arrayList.add("--avcodec-skip-frame");
        arrayList.add("0");
        arrayList.add("--stats");
        arrayList.add("--audio-resampler");
        arrayList.add("soxr");
        arrayList.add("file_crypt,none");
        arrayList.add("--stats");
        arrayList.add("--tone-mapping=1");
        arrayList.add("--android-display-chroma");
        arrayList.add("RV16");
        arrayList.add("--sout-chromecast-conversion-quality=0");
        arrayList.add("--sout-keep");
        arrayList.add("--smb-force-v1");
        arrayList.add(AndroidUtil.isMarshMallowOrLater ? "file_crypt,none" : "file_plaintext,none");
        arrayList.add("--keystore-file");
        arrayList.add(new File(App.getContext().getDir("keystore", 0), IDataSource.SCHEME_FILE_TAG).getAbsolutePath());
        arrayList.add(":file-caching=1000");
        arrayList.add(":live-caching=1000");
        arrayList.add(":sout-mux-caching=500");
        arrayList.add("-vv");
        return arrayList;
    }
}
