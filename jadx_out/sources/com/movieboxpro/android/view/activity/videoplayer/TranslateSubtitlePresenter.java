package com.movieboxpro.android.view.activity.videoplayer;

import androidx.lifecycle.LifecycleOwner;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import com.avery.subtitle.format.FormatSRT;
import com.avery.subtitle.model.Subtitle;
import com.dl7.player.utils.SRT;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.utils.HttpHelperKt;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SrtParser;
import com.movieboxpro.android.utils.ToastUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
import okio.BufferedSource;

/* JADX INFO: compiled from: TranslateSubtitlePresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J8\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000bH\u0016J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u000fH\u0016J$\u0010\u0017\u001a\u00020\r2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitlePresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleContract$View;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "translateDisposable", "Lio/reactivex/disposables/Disposable;", "appendedCount", "", "translate", "", "fromLanguage", "", "toLanguage", "toLanguageName", CmcdConfiguration.KEY_SESSION_ID, "boxType", "originalCount", "parseContent", FirebaseAnalytics.Param.CONTENT, "startTranslate", "subtitle", "", "Lcom/avery/subtitle/model/Subtitle;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TranslateSubtitlePresenter extends BasePresenter<TranslateSubtitleContract.View> implements TranslateSubtitleContract.Presenter {
    private int appendedCount;
    private Disposable translateDisposable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TranslateSubtitlePresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleContract.Presenter
    public void translate(String fromLanguage, String toLanguage, String toLanguageName, String sid, int boxType, final int originalCount) {
        Intrinsics.checkNotNullParameter(fromLanguage, "fromLanguage");
        Intrinsics.checkNotNullParameter(toLanguage, "toLanguage");
        Intrinsics.checkNotNullParameter(toLanguageName, "toLanguageName");
        Intrinsics.checkNotNullParameter(sid, "sid");
        Disposable disposable = this.translateDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.appendedCount = 0;
        TranslateSubtitleContract.View view = getView();
        if (view != null) {
            view.onTranslateStarted(originalCount);
        }
        Observable<ResponseBody> observableTranslateSubtitleStream = HttpHelperKt.getTranslateStreamApi().translateSubtitleStream(TranslateSubtitleRequest.url(), TranslateSubtitleRequest.INSTANCE.requestBody(boxType, sid, fromLanguage, toLanguage, toLanguageName));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TranslateSubtitlePresenter.translate$lambda$2((ResponseBody) obj);
            }
        };
        ((ObservableSubscribeProxy) observableTranslateSubtitleStream.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TranslateSubtitlePresenter.translate$lambda$3(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<TranslateSubtitleUiUpdate>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter.translate.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                TranslateSubtitlePresenter.this.translateDisposable = d;
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(TranslateSubtitleUiUpdate model) {
                Intrinsics.checkNotNullParameter(model, "model");
                if (model instanceof TranslateSubtitleUiUpdate.Append) {
                    TranslateSubtitleUiUpdate.Append append = (TranslateSubtitleUiUpdate.Append) model;
                    TranslateSubtitlePresenter.this.appendedCount += append.getModels().size();
                    TranslateSubtitleContract.View view2 = TranslateSubtitlePresenter.this.getView();
                    if (view2 != null) {
                        view2.appendTranslatedCues(CollectionsKt.toMutableList((Collection) append.getModels()), append.getDoneCount(), originalCount);
                        return;
                    }
                    return;
                }
                if (Intrinsics.areEqual(model, TranslateSubtitleUiUpdate.Finished.INSTANCE)) {
                    if (TranslateSubtitlePresenter.this.appendedCount == 0) {
                        TranslateSubtitleContract.View view3 = TranslateSubtitlePresenter.this.getView();
                        if (view3 != null) {
                            view3.onTranslateFailed("subtitle is empty");
                            return;
                        }
                        return;
                    }
                    TranslateSubtitleContract.View view4 = TranslateSubtitlePresenter.this.getView();
                    if (view4 != null) {
                        view4.onTranslateFinished();
                        return;
                    }
                    return;
                }
                if (!(model instanceof TranslateSubtitleUiUpdate.Failed)) {
                    throw new NoWhenBranchMatchedException();
                }
                TranslateSubtitleUiUpdate.Failed failed = (TranslateSubtitleUiUpdate.Failed) model;
                if (failed.getCode() == -10001 || failed.getCode() == -10022) {
                    Http.getLoginInvalidHandler().handle();
                }
                TranslateSubtitleContract.View view5 = TranslateSubtitlePresenter.this.getView();
                if (view5 != null) {
                    view5.onTranslateFailed(failed.getMessage());
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Intrinsics.checkNotNullParameter(e, "e");
                TranslateSubtitleContract.View view2 = TranslateSubtitlePresenter.this.getView();
                if (view2 != null) {
                    String message = e.getMessage();
                    if (message == null) {
                        message = "Translate failed";
                    }
                    view2.onTranslateFailed(message);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource translate$lambda$3(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource translate$lambda$2(final ResponseBody body) {
        Intrinsics.checkNotNullParameter(body, "body");
        return Observable.create(new ObservableOnSubscribe() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda3
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                TranslateSubtitlePresenter.translate$lambda$2$lambda$1(body, observableEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void translate$lambda$2$lambda$1(ResponseBody responseBody, ObservableEmitter emitter) {
        String utf8Line;
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        try {
            BufferedSource bodySource = responseBody.getBodySource();
            try {
                BufferedSource bufferedSource = bodySource;
                boolean z = false;
                int iIntValue = 0;
                while (!emitter.isDisposed() && (utf8Line = bufferedSource.readUtf8Line()) != null) {
                    Pair<TranslateSubtitleUiUpdate, Integer> pairReduceLine = TranslateSubtitleStreamReducer.INSTANCE.reduceLine(utf8Line, iIntValue);
                    TranslateSubtitleUiUpdate translateSubtitleUiUpdateComponent1 = pairReduceLine.component1();
                    iIntValue = pairReduceLine.component2().intValue();
                    if (translateSubtitleUiUpdateComponent1 != null) {
                        emitter.onNext(translateSubtitleUiUpdateComponent1);
                        if ((translateSubtitleUiUpdateComponent1 instanceof TranslateSubtitleUiUpdate.Finished) || (translateSubtitleUiUpdateComponent1 instanceof TranslateSubtitleUiUpdate.Failed)) {
                            z = true;
                            break;
                        }
                    }
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(bodySource, null);
                if (emitter.isDisposed()) {
                    return;
                }
                if (!z) {
                    emitter.onNext(new TranslateSubtitleUiUpdate.Failed("Translate failed", -1));
                }
                emitter.onComplete();
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(bodySource, th);
                    throw th2;
                }
            }
        } catch (Exception e) {
            if (emitter.isDisposed()) {
                return;
            }
            emitter.onError(e);
        }
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleContract.Presenter
    public void parseContent(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        Observable observableJust = Observable.just(content);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TranslateSubtitlePresenter.parseContent$lambda$4((String) obj);
            }
        };
        ((ObservableSubscribeProxy) observableJust.map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TranslateSubtitlePresenter.parseContent$lambda$5(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends SRT>>() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter.parseContent.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                TranslateSubtitlePresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<? extends SRT> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                TranslateSubtitlePresenter.this.getView().hideLoadingView();
                TranslateSubtitlePresenter.this.getView().showContent(model);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                TranslateSubtitlePresenter.this.getView().hideLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList parseContent$lambda$5(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ArrayList) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList parseContent$lambda$4(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        SrtParser.parseContentSrt(it, arrayList);
        return arrayList;
    }

    public final void startTranslate(List<Subtitle> subtitle, final String fromLanguage, final String toLanguage) {
        Intrinsics.checkNotNullParameter(subtitle, "subtitle");
        Intrinsics.checkNotNullParameter(fromLanguage, "fromLanguage");
        Intrinsics.checkNotNullParameter(toLanguage, "toLanguage");
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final Ref.IntRef intRef = new Ref.IntRef();
        Observable observableJust = Observable.just(subtitle);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TranslateSubtitlePresenter.startTranslate$lambda$7(arrayList2, (List) obj);
            }
        };
        Observable observableSubscribeOn = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda10
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TranslateSubtitlePresenter.startTranslate$lambda$8(function1, obj);
            }
        }).subscribeOn(Schedulers.io());
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TranslateSubtitlePresenter.startTranslate$lambda$13(fromLanguage, toLanguage, arrayList2, (ArrayList) obj);
            }
        };
        Object objAs = observableSubscribeOn.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda12
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TranslateSubtitlePresenter.startTranslate$lambda$14(function2, obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TranslateSubtitlePresenter.startTranslate$lambda$15(this.f$0, (ApiException) obj);
            }
        }, new Function0() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TranslateSubtitlePresenter.startTranslate$lambda$18(arrayList2, this, arrayList);
            }
        }, new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TranslateSubtitlePresenter.startTranslate$lambda$19(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TranslateSubtitlePresenter.startTranslate$lambda$20(intRef, this, arrayList2, (Unit) obj);
            }
        }, 8, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList startTranslate$lambda$8(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ArrayList) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList startTranslate$lambda$7(ArrayList arrayList, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            Subtitle subtitle = (Subtitle) it2.next();
            if (sb.length() < 1500) {
                sb.append(subtitle.content);
                arrayList2.add(subtitle);
            } else {
                arrayList.add(new ArrayList(arrayList2));
                StringsKt.clear(sb);
                sb.append(subtitle.content);
                arrayList2.clear();
                arrayList2.add(subtitle);
            }
        }
        ArrayList arrayList3 = arrayList2;
        if (!arrayList3.isEmpty()) {
            arrayList.add(new ArrayList(arrayList3));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource startTranslate$lambda$14(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource startTranslate$lambda$13(String str, String str2, final ArrayList arrayList, ArrayList it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList2 = new ArrayList();
        final int i = 0;
        for (Object obj : it) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            StringBuilder sb = new StringBuilder();
            int i3 = 0;
            for (Object obj2 : (ArrayList) obj) {
                int i4 = i3 + 1;
                if (i3 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Subtitle subtitle = (Subtitle) obj2;
                sb.append(i4);
                sb.append(ShellUtil.COMMAND_LINE_END);
                sb.append(subtitle.start.getTime("hh:mm:ss,ms"));
                sb.append(" --> ");
                sb.append(subtitle.end.getTime("hh:mm:ss,ms"));
                sb.append(ShellUtil.COMMAND_LINE_END);
                sb.append(subtitle.content);
                sb.append("\n\n");
                i3 = i4;
            }
            Observable<String> observableTranslate = HttpHelperKt.getApiService().translate(API.TRANSLATE_URL, str, str2, sb.toString());
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return TranslateSubtitlePresenter.startTranslate$lambda$13$lambda$12$lambda$10(arrayList, i, (String) obj3);
                }
            };
            arrayList2.add(observableTranslate.map(new Function() { // from class: com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitlePresenter$$ExternalSyntheticLambda8
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj3) {
                    return TranslateSubtitlePresenter.startTranslate$lambda$13$lambda$12$lambda$11(function1, obj3);
                }
            }).subscribeOn(Schedulers.io()));
            i = i2;
        }
        return Observable.merge(arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTranslate$lambda$13$lambda$12$lambda$11(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTranslate$lambda$13$lambda$12$lambda$10(ArrayList arrayList, int i, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        FormatSRT formatSRT = new FormatSRT();
        byte[] bytes = it.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        arrayList.set(i, new ArrayList(formatSRT.parseFile("", new ByteArrayInputStream(bytes)).captions.values()));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTranslate$lambda$19(TranslateSubtitlePresenter translateSubtitlePresenter, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        translateSubtitlePresenter.getView().showProgress(0, 10);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTranslate$lambda$20(Ref.IntRef intRef, TranslateSubtitlePresenter translateSubtitlePresenter, ArrayList arrayList, Unit unit) {
        intRef.element++;
        translateSubtitlePresenter.getView().showProgress(intRef.element, arrayList.size());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTranslate$lambda$18(ArrayList arrayList, TranslateSubtitlePresenter translateSubtitlePresenter, ArrayList arrayList2) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            for (Subtitle subtitle : (ArrayList) it.next()) {
                SRT srt = new SRT();
                srt.setBeginTime(subtitle.start.mseconds);
                srt.setEndTime(subtitle.end.mseconds);
                srt.setSrtBody(subtitle.content);
                arrayList2.add(srt);
            }
        }
        translateSubtitlePresenter.getView().showSubtitles(arrayList2);
        translateSubtitlePresenter.getView().dismissProgress();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTranslate$lambda$15(TranslateSubtitlePresenter translateSubtitlePresenter, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        translateSubtitlePresenter.getView().dismissProgress();
        ToastUtils.showShort("Translate failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }
}
