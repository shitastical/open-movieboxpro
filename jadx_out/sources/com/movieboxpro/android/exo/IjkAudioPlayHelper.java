package com.movieboxpro.android.exo;

import android.content.Context;
import android.util.Log;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ToastUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: compiled from: IjkAudioPlayHelper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u000b*\u0001\r\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0011\u001a\u00020\u00122\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tJ\"\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0019\u001a\u00020\u0014H\u0002J\b\u0010!\u001a\u00020\u0012H\u0002J\u0018\u0010\"\u001a\u00020\u00122\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010$\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\tJ\u0018\u0010%\u001a\u00020\u00122\u0006\u0010&\u001a\u00020'2\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0006\u0010(\u001a\u00020\u0014J\u0016\u0010)\u001a\u00020\u00122\u0006\u0010*\u001a\u00020\u00142\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010+\u001a\u00020\u00122\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0006\u0010,\u001a\u00020\u0012J\u000e\u0010-\u001a\u00020\u00122\u0006\u0010\b\u001a\u00020\tJ\u0006\u0010.\u001a\u00020\u0012J\u000e\u0010/\u001a\u00020\u00122\u0006\u00100\u001a\u00020\u0014J\u0006\u00101\u001a\u00020\u0012R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u001dj\b\u0012\u0004\u0012\u00020\u0018`\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/movieboxpro/android/exo/IjkAudioPlayHelper;", "", "<init>", "()V", "timerDisposable", "Lio/reactivex/disposables/Disposable;", "url", "", "player", "Ltv/danmaku/ijk/media/player/IMediaPlayer;", "exoPlayer", "Lcom/movieboxpro/android/exo/CustomExoPlayer;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "com/movieboxpro/android/exo/IjkAudioPlayHelper$listener$1", "Lcom/movieboxpro/android/exo/IjkAudioPlayHelper$listener$1;", "firstPlay", "", "play", "", "over400Count", "", "adjustDelayCount", "startTimer", "delay", "", "count", "delayDisposable", "anotherDelayDisposable", "timeList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "isNeedAddTime", "startDelay", "startTimerDelay", "changeUrl", "customDelay", "setAudioDelay", "setPlaySpeed", "speed", "", "getAudioDelay", "seekTo", "position", "start", DownloadService.PAUSE, "pauseOrPlay", "resume", "setVolume", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "release", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class IjkAudioPlayHelper {
    private int adjustDelayCount;
    private Disposable anotherDelayDisposable;
    private int customDelay;
    private Disposable delayDisposable;
    private final CustomExoPlayer exoPlayer;
    private boolean firstPlay;
    private boolean isNeedAddTime;
    private final IjkAudioPlayHelper$listener$1 listener;
    private int over400Count;
    private IMediaPlayer player;
    private boolean startDelay;
    private ArrayList<Long> timeList;
    private Disposable timerDisposable;
    private String url = "";

    /* JADX WARN: Type inference failed for: r0v2, types: [com.movieboxpro.android.exo.IjkAudioPlayHelper$listener$1] */
    public IjkAudioPlayHelper() {
        Context context = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        this.exoPlayer = new CustomExoPlayer(context);
        this.listener = new PlayListener() { // from class: com.movieboxpro.android.exo.IjkAudioPlayHelper$listener$1
            @Override // com.movieboxpro.android.exo.PlayListener
            public void onComplete() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPause() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPlay() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPositionChanged() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onStart() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onVideoSizeChanged(int width, int height) {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onBuffer(float percent) {
                IMediaPlayer iMediaPlayer;
                Disposable disposable;
                if (percent != 100.0f || (iMediaPlayer = this.this$0.player) == null) {
                    return;
                }
                IjkAudioPlayHelper ijkAudioPlayHelper = this.this$0;
                iMediaPlayer.setVolume(0.0f, 0.0f);
                if (!iMediaPlayer.isPlaying()) {
                    iMediaPlayer.start();
                }
                if (ijkAudioPlayHelper.timerDisposable == null || ((disposable = ijkAudioPlayHelper.timerDisposable) != null && disposable.isDisposed())) {
                    ijkAudioPlayHelper.startTimer(1000L, ijkAudioPlayHelper.player, 5);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onError(String msg) {
                ToastUtils.showShort("Load audio failed:" + msg, new Object[0]);
                try {
                    IMediaPlayer iMediaPlayer = this.this$0.player;
                    if (iMediaPlayer != null) {
                        iMediaPlayer.start();
                    }
                } catch (Exception unused) {
                }
            }
        };
        this.firstPlay = true;
        this.timeList = new ArrayList<>();
        this.isNeedAddTime = true;
    }

    public final void play(String url, IMediaPlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        this.player = player;
        this.url = url;
        this.customDelay = 0;
        Disposable disposable = this.timerDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.delayDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        try {
            player.pause();
            if (this.firstPlay) {
                this.exoPlayer.initPlayer();
                this.exoPlayer.registerListener(this.listener);
                this.exoPlayer.setVolume(1.0f, 1.0f);
                this.firstPlay = false;
            }
            this.exoPlayer.setDataSource(url, player.getCurrentPosition());
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startTimer(long delay, final IMediaPlayer player, int count) {
        Log.d("AudioPlayHelper", "startTimer:delay-" + delay);
        this.timeList.clear();
        Observable<Long> observableInterval = Observable.interval(delay, 600L, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread());
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.exo.IjkAudioPlayHelper$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return IjkAudioPlayHelper.startTimer$lambda$0(player, (Long) obj);
            }
        };
        Observable observableObserveOn = observableInterval.map(new Function() { // from class: com.movieboxpro.android.exo.IjkAudioPlayHelper$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return IjkAudioPlayHelper.startTimer$lambda$1(function1, obj);
            }
        }).observeOn(AndroidSchedulers.mainThread());
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.exo.IjkAudioPlayHelper$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return IjkAudioPlayHelper.startTimer$lambda$2(player, this, (Long) obj);
            }
        };
        observableObserveOn.map(new Function() { // from class: com.movieboxpro.android.exo.IjkAudioPlayHelper$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return IjkAudioPlayHelper.startTimer$lambda$3(function2, obj);
            }
        }).subscribeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.exo.IjkAudioPlayHelper.startTimer.3
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                Intrinsics.checkNotNullParameter(e, "e");
            }

            @Override // io.reactivex.Observer
            public void onNext(String t) {
                Intrinsics.checkNotNullParameter(t, "t");
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                IjkAudioPlayHelper.this.timerDisposable = d;
                IjkAudioPlayHelper.this.adjustDelayCount = 0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long startTimer$lambda$1(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Long) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Long startTimer$lambda$0(IMediaPlayer iMediaPlayer, Long it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Long.valueOf(iMediaPlayer != null ? iMediaPlayer.getCurrentPosition() : 0L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String startTimer$lambda$3(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (String) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String startTimer$lambda$2(IMediaPlayer iMediaPlayer, IjkAudioPlayHelper ijkAudioPlayHelper, Long it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (iMediaPlayer != null && iMediaPlayer.isPlaying()) {
            long currentPosition = (ijkAudioPlayHelper.exoPlayer.getCurrentPosition() + ((long) ijkAudioPlayHelper.customDelay)) - ((long) ((int) it.longValue()));
            Log.d("AudioPlayHelper", "offset-" + currentPosition);
            if (ijkAudioPlayHelper.adjustDelayCount >= 20) {
                Disposable disposable = ijkAudioPlayHelper.timerDisposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                Disposable disposable2 = ijkAudioPlayHelper.delayDisposable;
                if (disposable2 != null) {
                    disposable2.dispose();
                    return "";
                }
                return "";
            }
            if (Math.abs(currentPosition) > PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_SYN_TIME, 700L) + ((long) Math.abs(ijkAudioPlayHelper.customDelay))) {
                ijkAudioPlayHelper.exoPlayer.seekTo(it.longValue() - ((long) ijkAudioPlayHelper.customDelay));
                ijkAudioPlayHelper.adjustDelayCount++;
                ijkAudioPlayHelper.timeList.clear();
                Disposable disposable3 = ijkAudioPlayHelper.delayDisposable;
                if (disposable3 != null) {
                    disposable3.dispose();
                }
                ijkAudioPlayHelper.isNeedAddTime = true;
                ijkAudioPlayHelper.startDelay = false;
                return "";
            }
            if (ijkAudioPlayHelper.isNeedAddTime) {
                ijkAudioPlayHelper.timeList.add(Long.valueOf(currentPosition));
            }
            if (!ijkAudioPlayHelper.startDelay) {
                ijkAudioPlayHelper.startDelay = true;
                return "";
            }
            Disposable disposable4 = ijkAudioPlayHelper.delayDisposable;
            if (disposable4 != null && disposable4.isDisposed() && Math.abs(currentPosition) > 400) {
                int i = ijkAudioPlayHelper.over400Count + 1;
                ijkAudioPlayHelper.over400Count = i;
                if (i > 5) {
                    ijkAudioPlayHelper.over400Count = 0;
                    ijkAudioPlayHelper.startDelay = false;
                    return "";
                }
                return "";
            }
            return "";
        }
        return "";
    }

    private final void startTimerDelay() {
        Log.d("AudioPlayHelper", "Delay Timer");
        Disposable disposable = this.delayDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.anotherDelayDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
    }

    public final void changeUrl(String url, IMediaPlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        play(url, player);
    }

    public final void setAudioDelay(int delay, IMediaPlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        this.customDelay += delay;
        this.exoPlayer.seekTo(player.getCurrentPosition() - ((long) this.customDelay));
    }

    public final void setPlaySpeed(float speed, IMediaPlayer player) {
        this.exoPlayer.setSpeed(speed);
        Disposable disposable = this.timerDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    /* JADX INFO: renamed from: getAudioDelay, reason: from getter */
    public final int getCustomDelay() {
        return this.customDelay;
    }

    public final synchronized void seekTo(int position, IMediaPlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        this.exoPlayer.seekTo(position);
        this.startDelay = false;
    }

    public final void start(IMediaPlayer player) {
        if (this.exoPlayer.isPlaying()) {
            return;
        }
        this.exoPlayer.start();
        this.isNeedAddTime = true;
        this.startDelay = false;
        Disposable disposable = this.timerDisposable;
        if (disposable == null || (disposable != null && disposable.isDisposed())) {
            startTimer(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, player, 5);
        }
    }

    public final void pause() {
        if (this.exoPlayer.isPlaying()) {
            this.exoPlayer.pause();
        }
    }

    public final void pauseOrPlay(IMediaPlayer player) {
        Intrinsics.checkNotNullParameter(player, "player");
        if (this.exoPlayer.isPlaying()) {
            this.exoPlayer.pause();
            return;
        }
        this.exoPlayer.start();
        this.isNeedAddTime = true;
        this.startDelay = false;
        Disposable disposable = this.timerDisposable;
        if (disposable == null || (disposable != null && disposable.isDisposed())) {
            startTimer(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, player, 5);
        }
    }

    public final void resume() {
        this.exoPlayer.start();
    }

    public final void setVolume(int volume) {
        this.exoPlayer.setVolume(volume);
    }

    public final void release() {
        try {
            this.player = null;
            this.exoPlayer.unRegisterListener(this.listener);
            Disposable disposable = this.timerDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            Disposable disposable2 = this.delayDisposable;
            if (disposable2 != null) {
                disposable2.dispose();
            }
            Disposable disposable3 = this.anotherDelayDisposable;
            if (disposable3 != null) {
                disposable3.dispose();
            }
            this.exoPlayer.stop();
            this.exoPlayer.release();
        } catch (Exception unused) {
        }
    }
}
