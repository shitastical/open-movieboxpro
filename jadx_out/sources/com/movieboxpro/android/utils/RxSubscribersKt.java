package com.movieboxpro.android.utils;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleOwner;
import com.alibaba.fastjson.asm.Opcodes;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.ServerException;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.functions.Functions;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RxSubscribers.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\n\u001a(\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00100\u000f\"\b\b\u0000\u0010\u0010*\u00020\u0002*\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u0001H\u0002\u001a\u001e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f*\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u0001H\u0002\u001a\u0012\u0010\u0012\u001a\u00020\u0013*\b\u0012\u0004\u0012\u00020\u00030\tH\u0002\u001a\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000f*\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0001H\u0002\u001al\u0010\u0015\u001a\u00020\u000b\"\b\b\u0000\u0010\u0010*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00100\u00162\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0001\u001a\u0082\u0001\u0010\u0015\u001a\u00020\u000b\"\b\b\u0000\u0010\u0010*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00100\u001b2\u0014\b\u0002\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0002\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u0001\u001a)\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u00100\u001b\"\u0006\b\u0000\u0010\u0010\u0018\u0001*\b\u0012\u0004\u0012\u00020\u001e0\u00162\u0006\u0010\u001f\u001a\u00020 H\u0086\b\u001a\u001e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001b*\b\u0012\u0004\u0012\u00020\u001e0\u00162\u0006\u0010\u001f\u001a\u00020 \u001a/\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100#0\u001b\"\u0006\b\u0000\u0010\u0010\u0018\u0001*\b\u0012\u0004\u0012\u00020\u001e0\u00162\u0006\u0010\u001f\u001a\u00020 H\u0086\b\u001a$\u0010$\u001a\b\u0012\u0004\u0012\u0002H\u00100\u001b\"\u0004\b\u0000\u0010\u0010*\b\u0012\u0004\u0012\u0002H\u00100\u00162\u0006\u0010\u001f\u001a\u00020 \u001a!\u0010%\u001a\b\u0012\u0004\u0012\u0002H\u00100\u0016\"\u0006\b\u0000\u0010\u0010\u0018\u0001*\b\u0012\u0004\u0012\u00020\u001e0\u0016H\u0086\b\u001a'\u0010&\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100#0\u0016\"\u0006\b\u0000\u0010\u0010\u0018\u0001*\b\u0012\u0004\u0012\u00020\u001e0\u0016H\u0086\b\u001a\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001e0\u0016*\b\u0012\u0004\u0012\u00020\u001e0\u0016\u001a\u008a\u0001\u0010(\u001a\u00020\u0003\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00100\u001b2\u0014\b\u0006\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0006\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u0014\b\u0006\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000\u001a\u0092\u0001\u0010)\u001a\u00020\u0003\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u00020\u001e0\u00162\u0006\u0010\u001f\u001a\u00020 2\u0014\b\u0006\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0006\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u0014\b\u0006\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000\u001a\u0098\u0001\u0010*\u001a\u00020\u0003\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u00020\u001e0\u00162\u0006\u0010\u001f\u001a\u00020 2\u0014\b\u0006\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0006\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u001a\b\u0006\u0010\u0019\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00100#\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000\u001a\u0086\u0001\u0010+\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u001e0\u00162\u0006\u0010\u001f\u001a\u00020 2\u0014\b\u0006\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0006\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u0014\b\u0006\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000\u001a\u0092\u0001\u0010,\u001a\u00020\u0003\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00100\u00162\u0006\u0010\u001f\u001a\u00020 2\u0014\b\u0006\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u00012\u000e\b\u0006\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00030\t2\u0014\b\u0006\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0010\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0006\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001H\u0086\bø\u0001\u0000\"\u001a\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006-"}, d2 = {"onNextStub", "Lkotlin/Function1;", "", "", "onErrorStub", "", "onApiExceptionErrorStub", "Lcom/movieboxpro/android/http/ApiException;", "onCompleteStub", "Lkotlin/Function0;", "onSubscribeStub", "Lio/reactivex/disposables/Disposable;", "onServerErrorStub", "Lcom/movieboxpro/android/http/ServerException;", "asConsumer", "Lio/reactivex/functions/Consumer;", ExifInterface.GPS_DIRECTION_TRUE, "asOnErrorConsumer", "asOnCompleteAction", "Lio/reactivex/functions/Action;", "asOnSubscribe", "subscribeTo", "Lio/reactivex/Observable;", "onError", "onComplete", "onSuccess", "onStart", "Lcom/uber/autodispose/ObservableSubscribeProxy;", "onServerError", "transformBean", "", "owner", "Landroidx/lifecycle/LifecycleOwner;", "transformMsg", "transformList", "", "transform", "toBean", "toLists", "toMsg", "subscribeKt", "subscribeToBean", "subscribeToList", "subscribeToMsg", "transformSubscribe", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class RxSubscribersKt {
    private static final Function1<Object, Unit> onNextStub = new Function1() { // from class: com.movieboxpro.android.utils.RxSubscribersKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return RxSubscribersKt.onNextStub$lambda$0(obj);
        }
    };
    private static final Function1<Throwable, Unit> onErrorStub = new Function1() { // from class: com.movieboxpro.android.utils.RxSubscribersKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return RxSubscribersKt.onErrorStub$lambda$1((Throwable) obj);
        }
    };
    private static final Function1<ApiException, Unit> onApiExceptionErrorStub = new Function1() { // from class: com.movieboxpro.android.utils.RxSubscribersKt$$ExternalSyntheticLambda6
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return RxSubscribersKt.onApiExceptionErrorStub$lambda$2((ApiException) obj);
        }
    };
    private static final Function0<Unit> onCompleteStub = new Function0() { // from class: com.movieboxpro.android.utils.RxSubscribersKt$$ExternalSyntheticLambda7
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Unit.INSTANCE;
        }
    };
    private static final Function1<Disposable, Unit> onSubscribeStub = new Function1() { // from class: com.movieboxpro.android.utils.RxSubscribersKt$$ExternalSyntheticLambda8
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return RxSubscribersKt.onSubscribeStub$lambda$4((Disposable) obj);
        }
    };
    private static final Function1<ServerException, Unit> onServerErrorStub = new Function1() { // from class: com.movieboxpro.android.utils.RxSubscribersKt$$ExternalSyntheticLambda9
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return RxSubscribersKt.onServerErrorStub$lambda$5((ServerException) obj);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onNextStub$lambda$0(Object it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onErrorStub$lambda$1(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onApiExceptionErrorStub$lambda$2(ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onSubscribeStub$lambda$4(Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onServerErrorStub$lambda$5(ServerException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    private static final <T> Consumer<T> asConsumer(final Function1<? super T, Unit> function1) {
        if (function1 != onNextStub) {
            return new Consumer() { // from class: com.movieboxpro.android.utils.RxSubscribersKt$$ExternalSyntheticLambda2
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    function1.invoke(obj);
                }
            };
        }
        Consumer<T> consumerEmptyConsumer = Functions.emptyConsumer();
        Intrinsics.checkNotNullExpressionValue(consumerEmptyConsumer, "emptyConsumer(...)");
        return consumerEmptyConsumer;
    }

    private static final Consumer<Throwable> asOnErrorConsumer(final Function1<? super Throwable, Unit> function1) {
        return new Consumer() { // from class: com.movieboxpro.android.utils.RxSubscribersKt$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                function1.invoke((Throwable) obj);
            }
        };
    }

    private static final Action asOnCompleteAction(final Function0<Unit> function0) {
        if (function0 != onCompleteStub) {
            return new Action() { // from class: com.movieboxpro.android.utils.RxSubscribersKt$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Action
                public final void run() {
                    function0.invoke();
                }
            };
        }
        Action EMPTY_ACTION = Functions.EMPTY_ACTION;
        Intrinsics.checkNotNullExpressionValue(EMPTY_ACTION, "EMPTY_ACTION");
        return EMPTY_ACTION;
    }

    private static final Consumer<Disposable> asOnSubscribe(final Function1<? super Disposable, Unit> function1) {
        return new Consumer() { // from class: com.movieboxpro.android.utils.RxSubscribersKt$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                function1.invoke((Disposable) obj);
            }
        };
    }

    public static /* synthetic */ Disposable subscribeTo$default(Observable observable, Function1 function1, Function0 function0, Function1 function2, Function1 function3, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = onErrorStub;
        }
        if ((i & 2) != 0) {
            function0 = onCompleteStub;
        }
        if ((i & 4) != 0) {
            function2 = onNextStub;
        }
        if ((i & 8) != 0) {
            function3 = onSubscribeStub;
        }
        return subscribeTo(observable, function1, function0, function2, function3);
    }

    public static final <T> Disposable subscribeTo(Observable<T> observable, Function1<? super Throwable, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onSuccess, Function1<? super Disposable, Unit> onStart) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Disposable disposableSubscribe = observable.subscribe(asConsumer(onSuccess), asOnErrorConsumer(onError), asOnCompleteAction(onComplete), asOnSubscribe(onStart));
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        return disposableSubscribe;
    }

    public static /* synthetic */ Disposable subscribeTo$default(ObservableSubscribeProxy observableSubscribeProxy, Function1 function1, Function0 function0, Function1 function2, Function1 function3, Function1 function4, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = onApiExceptionErrorStub;
        }
        if ((i & 2) != 0) {
            function0 = onCompleteStub;
        }
        Function0 function5 = function0;
        if ((i & 4) != 0) {
            function2 = onSubscribeStub;
        }
        Function1 function6 = function2;
        if ((i & 8) != 0) {
            function3 = onServerErrorStub;
        }
        Function1 function7 = function3;
        if ((i & 16) != 0) {
            function4 = onNextStub;
        }
        return subscribeTo(observableSubscribeProxy, function1, function5, function6, function7, function4);
    }

    public static final <T> Disposable subscribeTo(ObservableSubscribeProxy<T> observableSubscribeProxy, final Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super Disposable, Unit> onStart, final Function1<? super ServerException, Unit> onServerError, Function1<? super T, Unit> onSuccess) {
        Intrinsics.checkNotNullParameter(observableSubscribeProxy, "<this>");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Disposable disposableSubscribe = observableSubscribeProxy.subscribe(asConsumer(onSuccess), new Consumer() { // from class: com.movieboxpro.android.utils.RxSubscribersKt$$ExternalSyntheticLambda10
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                RxSubscribersKt.subscribeTo$lambda$10(onError, onServerError, (Throwable) obj);
            }
        }, asOnCompleteAction(onComplete), asOnSubscribe(onStart));
        Intrinsics.checkNotNullExpressionValue(disposableSubscribe, "subscribe(...)");
        return disposableSubscribe;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void subscribeTo$lambda$10(Function1 function1, Function1 function2, Throwable th) {
        ApiException apiExceptionHandleException = ApiException.handleException(th);
        Intrinsics.checkNotNullExpressionValue(apiExceptionHandleException, "handleException(...)");
        function1.invoke(apiExceptionHandleException);
        if (th instanceof ServerException) {
            function2.invoke(th);
        }
    }

    public static final ObservableSubscribeProxy<String> transformMsg(Observable<String> observable, LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        return transform(toMsg(observable), owner);
    }

    public static final <T> ObservableSubscribeProxy<T> transform(Observable<T> observable, LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Object objAs = observable.compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(owner));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        return (ObservableSubscribeProxy) objAs;
    }

    public static final /* synthetic */ <T> Observable<T> toBean(Observable<String> observable) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCompose = observable.compose(RxUtils.rxTranslate2Bean(Object.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        return observableCompose;
    }

    public static final /* synthetic */ <T> Observable<List<T>> toLists(Observable<String> observable) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCompose = observable.compose(RxUtils.rxTranslate2List(Object.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        return observableCompose;
    }

    public static final Observable<String> toMsg(Observable<String> observable) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Observable observableCompose = observable.compose(RxUtils.rxTranslateMsg());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        return observableCompose;
    }

    public static /* synthetic */ void subscribeKt$default(ObservableSubscribeProxy observableSubscribeProxy, Function1 onError, Function0 onComplete, Function1 onSuccess, Function1 onStart, Function1 onServerError, int i, Object obj) {
        if ((i & 1) != 0) {
            onError = new Function1<ApiException, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeKt.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ApiException it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ApiException apiException) {
                    invoke2(apiException);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 2) != 0) {
            onComplete = new Function0<Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeKt.2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            Intrinsics.needClassReification();
            onSuccess = AnonymousClass3.INSTANCE;
        }
        if ((i & 8) != 0) {
            onStart = new Function1<Disposable, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeKt.4
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Disposable it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Disposable disposable) {
                    invoke2(disposable);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 16) != 0) {
            onServerError = new Function1<ServerException, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeKt.5
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ServerException it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ServerException serverException) {
                    invoke2(serverException);
                    return Unit.INSTANCE;
                }
            };
        }
        Intrinsics.checkNotNullParameter(observableSubscribeProxy, "<this>");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.needClassReification();
        observableSubscribeProxy.subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass6(onSuccess)), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass7(onError, onServerError)), new AnonymousClass8(onComplete), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass9(onStart)));
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.movieboxpro.android.utils.RxSubscribersKt$subscribeKt$6, reason: invalid class name */
    /* JADX INFO: compiled from: RxSubscribers.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = Opcodes.ARETURN)
    public static final class AnonymousClass6<T> implements Function1<T, Unit> {
        final /* synthetic */ Function1<T, Unit> $onSuccess;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(Function1<? super T, Unit> function1) {
            this.$onSuccess = function1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Object obj) {
            invoke2(obj);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(T t) {
            Function1<T, Unit> function1 = this.$onSuccess;
            Intrinsics.checkNotNull(t);
            function1.invoke(t);
        }
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.utils.RxSubscribersKt$subscribeKt$7, reason: invalid class name */
    /* JADX INFO: compiled from: RxSubscribers.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = Opcodes.ARETURN)
    public static final class AnonymousClass7 implements Function1<Throwable, Unit> {
        final /* synthetic */ Function1<ApiException, Unit> $onError;
        final /* synthetic */ Function1<ServerException, Unit> $onServerError;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass7(Function1<? super ApiException, Unit> function1, Function1<? super ServerException, Unit> function2) {
            this.$onError = function1;
            this.$onServerError = function2;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
            invoke2(th);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Throwable th) {
            Function1<ApiException, Unit> function1 = this.$onError;
            ApiException apiExceptionHandleException = ApiException.handleException(th);
            Intrinsics.checkNotNullExpressionValue(apiExceptionHandleException, "handleException(...)");
            function1.invoke(apiExceptionHandleException);
            if (th instanceof ServerException) {
                this.$onServerError.invoke(th);
            }
        }
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.utils.RxSubscribersKt$subscribeKt$9, reason: invalid class name */
    /* JADX INFO: compiled from: RxSubscribers.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = Opcodes.ARETURN)
    public static final class AnonymousClass9 implements Function1<Disposable, Unit> {
        final /* synthetic */ Function1<Disposable, Unit> $onStart;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass9(Function1<? super Disposable, Unit> function1) {
            this.$onStart = function1;
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Disposable disposable) {
            invoke2(disposable);
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(Disposable disposable) {
            Function1<Disposable, Unit> function1 = this.$onStart;
            Intrinsics.checkNotNull(disposable);
            function1.invoke(disposable);
        }
    }

    public static final /* synthetic */ <T> void subscribeKt(ObservableSubscribeProxy<T> observableSubscribeProxy, Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onSuccess, Function1<? super Disposable, Unit> onStart, Function1<? super ServerException, Unit> onServerError) {
        Intrinsics.checkNotNullParameter(observableSubscribeProxy, "<this>");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.needClassReification();
        observableSubscribeProxy.subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass6(onSuccess)), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass7(onError, onServerError)), new AnonymousClass8(onComplete), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass9(onStart)));
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.utils.RxSubscribersKt$subscribeKt$8, reason: invalid class name */
    /* JADX INFO: compiled from: RxSubscribers.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = Opcodes.ARETURN)
    public static final class AnonymousClass8 implements Action {
        final /* synthetic */ Function0<Unit> $onComplete;

        public AnonymousClass8(Function0<Unit> function0) {
            this.$onComplete = function0;
        }

        @Override // io.reactivex.functions.Action
        public final void run() {
            this.$onComplete.invoke();
        }
    }

    public static /* synthetic */ void subscribeToBean$default(Observable observable, LifecycleOwner owner, Function1 onError, Function0 onComplete, Function1 onSuccess, Function1 onStart, Function1 onServerError, int i, Object obj) {
        if ((i & 2) != 0) {
            onError = new Function1<ApiException, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToBean.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ApiException it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ApiException apiException) {
                    invoke2(apiException);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            onComplete = new Function0<Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToBean.2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 8) != 0) {
            Intrinsics.needClassReification();
            onSuccess = C01533.INSTANCE;
        }
        if ((i & 16) != 0) {
            onStart = new Function1<Disposable, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToBean.4
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Disposable it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Disposable disposable) {
                    invoke2(disposable);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 32) != 0) {
            onServerError = new Function1<ServerException, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToBean.5
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ServerException it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ServerException serverException) {
                    invoke2(serverException);
                    return Unit.INSTANCE;
                }
            };
        }
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCompose = observable.compose(RxUtils.rxTranslate2Bean(Object.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        ObservableSubscribeProxy observableSubscribeProxyTransform = transform(observableCompose, owner);
        Intrinsics.needClassReification();
        observableSubscribeProxyTransform.subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new RxSubscribersKt$subscribeToBean$$inlined$subscribeKt$1(onSuccess)), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass7(onError, onServerError)), new AnonymousClass8(onComplete), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass9(onStart)));
    }

    public static /* synthetic */ void subscribeToList$default(Observable observable, LifecycleOwner owner, Function1 onError, Function0 onComplete, Function1 onSuccess, Function1 onStart, Function1 onServerError, int i, Object obj) {
        if ((i & 2) != 0) {
            onError = new Function1<ApiException, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToList.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ApiException it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ApiException apiException) {
                    invoke2(apiException);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            onComplete = new Function0<Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToList.2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 8) != 0) {
            Intrinsics.needClassReification();
            onSuccess = C01583.INSTANCE;
        }
        if ((i & 16) != 0) {
            onStart = new Function1<Disposable, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToList.4
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Disposable it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Disposable disposable) {
                    invoke2(disposable);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 32) != 0) {
            onServerError = new Function1<ServerException, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToList.5
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ServerException it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ServerException serverException) {
                    invoke2(serverException);
                    return Unit.INSTANCE;
                }
            };
        }
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCompose = observable.compose(RxUtils.rxTranslate2List(Object.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        ObservableSubscribeProxy observableSubscribeProxyTransform = transform(observableCompose, owner);
        Intrinsics.needClassReification();
        observableSubscribeProxyTransform.subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new RxSubscribersKt$subscribeToList$$inlined$subscribeKt$1(onSuccess)), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass7(onError, onServerError)), new AnonymousClass8(onComplete), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass9(onStart)));
    }

    public static /* synthetic */ void subscribeToMsg$default(Observable observable, LifecycleOwner owner, Function1 onError, Function0 onComplete, Function1 onSuccess, Function1 onStart, Function1 onServerError, int i, Object obj) {
        if ((i & 2) != 0) {
            onError = new Function1<ApiException, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToMsg.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ApiException it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ApiException apiException) {
                    invoke2(apiException);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            onComplete = new Function0<Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToMsg.2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 8) != 0) {
            onSuccess = new Function1<String, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToMsg.3
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(String it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(String str) {
                    invoke2(str);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 16) != 0) {
            onStart = new Function1<Disposable, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToMsg.4
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Disposable it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Disposable disposable) {
                    invoke2(disposable);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 32) != 0) {
            onServerError = new Function1<ServerException, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.subscribeToMsg.5
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ServerException it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ServerException serverException) {
                    invoke2(serverException);
                    return Unit.INSTANCE;
                }
            };
        }
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        transformMsg(observable, owner).subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new RxSubscribersKt$subscribeToMsg$$inlined$subscribeKt$1(onSuccess)), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass7(onError, onServerError)), new AnonymousClass8(onComplete), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass9(onStart)));
    }

    public static final void subscribeToMsg(Observable<String> observable, LifecycleOwner owner, Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super String, Unit> onSuccess, Function1<? super Disposable, Unit> onStart, Function1<? super ServerException, Unit> onServerError) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        transformMsg(observable, owner).subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new RxSubscribersKt$subscribeToMsg$$inlined$subscribeKt$1(onSuccess)), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass7(onError, onServerError)), new AnonymousClass8(onComplete), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass9(onStart)));
    }

    public static /* synthetic */ void transformSubscribe$default(Observable observable, LifecycleOwner owner, Function1 onError, Function0 onComplete, Function1 onSuccess, Function1 onStart, Function1 onServerError, int i, Object obj) {
        if ((i & 2) != 0) {
            onError = new Function1<ApiException, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.transformSubscribe.1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ApiException it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ApiException apiException) {
                    invoke2(apiException);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 4) != 0) {
            onComplete = new Function0<Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.transformSubscribe.2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 8) != 0) {
            Intrinsics.needClassReification();
            onSuccess = C01683.INSTANCE;
        }
        if ((i & 16) != 0) {
            onStart = new Function1<Disposable, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.transformSubscribe.4
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Disposable it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Disposable disposable) {
                    invoke2(disposable);
                    return Unit.INSTANCE;
                }
            };
        }
        if ((i & 32) != 0) {
            onServerError = new Function1<ServerException, Unit>() { // from class: com.movieboxpro.android.utils.RxSubscribersKt.transformSubscribe.5
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ServerException it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ServerException serverException) {
                    invoke2(serverException);
                    return Unit.INSTANCE;
                }
            };
        }
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        ObservableSubscribeProxy observableSubscribeProxyTransform = transform(observable, owner);
        Intrinsics.needClassReification();
        observableSubscribeProxyTransform.subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new RxSubscribersKt$transformSubscribe$$inlined$subscribeKt$1(onSuccess)), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass7(onError, onServerError)), new AnonymousClass8(onComplete), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass9(onStart)));
    }

    public static final /* synthetic */ <T> void transformSubscribe(Observable<T> observable, LifecycleOwner owner, Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onSuccess, Function1<? super Disposable, Unit> onStart, Function1<? super ServerException, Unit> onServerError) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        ObservableSubscribeProxy observableSubscribeProxyTransform = transform(observable, owner);
        Intrinsics.needClassReification();
        observableSubscribeProxyTransform.subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new RxSubscribersKt$transformSubscribe$$inlined$subscribeKt$1(onSuccess)), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass7(onError, onServerError)), new AnonymousClass8(onComplete), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass9(onStart)));
    }

    public static final /* synthetic */ <T> ObservableSubscribeProxy<T> transformBean(Observable<String> observable, LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCompose = observable.compose(RxUtils.rxTranslate2Bean(Object.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        return transform(observableCompose, owner);
    }

    public static final /* synthetic */ <T> ObservableSubscribeProxy<List<T>> transformList(Observable<String> observable, LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCompose = observable.compose(RxUtils.rxTranslate2List(Object.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        return transform(observableCompose, owner);
    }

    public static final /* synthetic */ <T> void subscribeToBean(Observable<String> observable, LifecycleOwner owner, Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super T, Unit> onSuccess, Function1<? super Disposable, Unit> onStart, Function1<? super ServerException, Unit> onServerError) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCompose = observable.compose(RxUtils.rxTranslate2Bean(Object.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        ObservableSubscribeProxy observableSubscribeProxyTransform = transform(observableCompose, owner);
        Intrinsics.needClassReification();
        observableSubscribeProxyTransform.subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new RxSubscribersKt$subscribeToBean$$inlined$subscribeKt$1(onSuccess)), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass7(onError, onServerError)), new AnonymousClass8(onComplete), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass9(onStart)));
    }

    public static final /* synthetic */ <T> void subscribeToList(Observable<String> observable, LifecycleOwner owner, Function1<? super ApiException, Unit> onError, Function0<Unit> onComplete, Function1<? super List<? extends T>, Unit> onSuccess, Function1<? super Disposable, Unit> onStart, Function1<? super ServerException, Unit> onServerError) {
        Intrinsics.checkNotNullParameter(observable, "<this>");
        Intrinsics.checkNotNullParameter(owner, "owner");
        Intrinsics.checkNotNullParameter(onError, "onError");
        Intrinsics.checkNotNullParameter(onComplete, "onComplete");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onServerError, "onServerError");
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        Observable observableCompose = observable.compose(RxUtils.rxTranslate2List(Object.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        ObservableSubscribeProxy observableSubscribeProxyTransform = transform(observableCompose, owner);
        Intrinsics.needClassReification();
        observableSubscribeProxyTransform.subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new RxSubscribersKt$subscribeToList$$inlined$subscribeKt$1(onSuccess)), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass7(onError, onServerError)), new AnonymousClass8(onComplete), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new AnonymousClass9(onStart)));
    }
}
