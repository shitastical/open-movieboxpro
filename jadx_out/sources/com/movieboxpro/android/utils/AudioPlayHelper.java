package com.movieboxpro.android.utils;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.dl7.player.media.MediaPlayerCompat;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.service.DownloadService;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.Media;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.AbstractVLCEvent;
import org.videolan.libvlc.interfaces.IMedia;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: compiled from: AudioPlayHelper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\rJ\"\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0014\u001a\u00020\u000fH\u0002J\b\u0010\u001d\u001a\u00020\u000bH\u0002J\u001a\u0010\u001e\u001a\u00020\u000b2\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0018\u0010 \u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0018\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020#2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0006\u0010$\u001a\u00020\u000fJ\u0018\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010'\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010(\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0010\u0010)\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rJ\u0006\u0010*\u001a\u00020\u000bJ\u000e\u0010+\u001a\u00020\u000b2\u0006\u0010,\u001a\u00020\u000fJ\u0006\u0010-\u001a\u00020\u000bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0017\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0018j\b\u0012\u0004\u0012\u00020\u0013`\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/movieboxpro/android/utils/AudioPlayHelper;", "", "<init>", "()V", "timerDisposable", "Lio/reactivex/disposables/Disposable;", "url", "", "vlcPlayer", "Lorg/videolan/libvlc/MediaPlayer;", "play", "", "player", "Ltv/danmaku/ijk/media/player/IMediaPlayer;", "over400Count", "", "adjustDelayCount", "startTimer", "delay", "", "count", "delayDisposable", "anotherDelayDisposable", "timeList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "isNeedAddTime", "", "startDelay", "startTimerDelay", "changeUrl", "customDelay", "setAudioDelay", "setPlaySpeed", "speed", "", "getAudioDelay", "seekTo", "position", "start", DownloadService.PAUSE, "pauseOrPlay", "resume", "setVolume", MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, "release", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AudioPlayHelper {
    private int adjustDelayCount;
    private Disposable anotherDelayDisposable;
    private int customDelay;
    private Disposable delayDisposable;
    private boolean isNeedAddTime;
    private int over400Count;
    private boolean startDelay;
    private ArrayList<Long> timeList;
    private Disposable timerDisposable;
    private String url = "";
    private final MediaPlayer vlcPlayer;

    public AudioPlayHelper() {
        VLCInstance vLCInstance = VLCInstance.INSTANCE;
        Context context = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        this.vlcPlayer = new MediaPlayer(vLCInstance.getInstance(context));
        this.timeList = new ArrayList<>();
        this.isNeedAddTime = true;
    }

    public final void play(final String url, final IMediaPlayer player) {
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
        Observable observableJust = Observable.just(url);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.utils.AudioPlayHelper$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AudioPlayHelper.play$lambda$1(player, url, this, (String) obj);
            }
        };
        Observable observableCompose = observableJust.map(new Function() { // from class: com.movieboxpro.android.utils.AudioPlayHelper$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return AudioPlayHelper.play$lambda$2(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper());
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(observableCompose, null, null, null, new Function1() { // from class: com.movieboxpro.android.utils.AudioPlayHelper$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AudioPlayHelper.play$lambda$3((Disposable) obj);
            }
        }, 7, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit play$lambda$2(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit play$lambda$1(final IMediaPlayer iMediaPlayer, String str, final AudioPlayHelper audioPlayHelper, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        MediaPlayerCompat.selectTrack(iMediaPlayer, -1);
        if (iMediaPlayer != null) {
            iMediaPlayer.pause();
        }
        Media media = new Media(audioPlayHelper.vlcPlayer.getLibVLC(), Uri.parse(str));
        media.addOption(":start-time=" + ((iMediaPlayer != null ? iMediaPlayer.getCurrentPosition() : 0L) / 1000));
        media.addOption(":no-video");
        IMedia media2 = audioPlayHelper.vlcPlayer.getMedia();
        if (media2 != null) {
            media2.release();
        }
        audioPlayHelper.vlcPlayer.setMedia(media);
        audioPlayHelper.vlcPlayer.play();
        audioPlayHelper.vlcPlayer.setEventListener(new MediaPlayer.EventListener() { // from class: com.movieboxpro.android.utils.AudioPlayHelper$$ExternalSyntheticLambda0
            @Override // org.videolan.libvlc.interfaces.AbstractVLCEvent.Listener
            public final void onEvent(AbstractVLCEvent abstractVLCEvent) {
                AudioPlayHelper.play$lambda$1$lambda$0(iMediaPlayer, audioPlayHelper, (MediaPlayer.Event) abstractVLCEvent);
            }
        });
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void play$lambda$1$lambda$0(IMediaPlayer iMediaPlayer, AudioPlayHelper audioPlayHelper, MediaPlayer.Event event) {
        if (event.type != 259 || event.getBuffering() < 100.0f) {
            return;
        }
        if (iMediaPlayer != null && !iMediaPlayer.isPlaying() && iMediaPlayer != null) {
            iMediaPlayer.start();
        }
        Disposable disposable = audioPlayHelper.timerDisposable;
        if (disposable == null || (disposable != null && disposable.isDisposed())) {
            audioPlayHelper.startTimer(1000L, iMediaPlayer, 5);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit play$lambda$3(Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Unit.INSTANCE;
    }

    private final void startTimer(long delay, final IMediaPlayer player, final int count) {
        Log.d("AudioPlayHelper", "startTimer:delay-" + delay);
        Disposable disposable = this.timerDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.timeList.clear();
        Observable<Long> observableInterval = Observable.interval(delay, 600L, TimeUnit.MILLISECONDS);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.utils.AudioPlayHelper$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AudioPlayHelper.startTimer$lambda$4(this.f$0, player, count, (Long) obj);
            }
        };
        Observable<R> map = observableInterval.map(new Function() { // from class: com.movieboxpro.android.utils.AudioPlayHelper$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return AudioPlayHelper.startTimer$lambda$5(function1, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        RxSubscribersKt.subscribeTo$default(map, null, null, null, new Function1() { // from class: com.movieboxpro.android.utils.AudioPlayHelper$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AudioPlayHelper.startTimer$lambda$6(this.f$0, (Disposable) obj);
            }
        }, 7, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTimer$lambda$5(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTimer$lambda$4(AudioPlayHelper audioPlayHelper, IMediaPlayer iMediaPlayer, int i, Long it) {
        Intrinsics.checkNotNullParameter(it, "it");
        if (!audioPlayHelper.vlcPlayer.isPlaying() || audioPlayHelper.vlcPlayer.getRate() != 1.0f) {
            return Unit.INSTANCE;
        }
        long time = ((audioPlayHelper.vlcPlayer.getTime() - audioPlayHelper.vlcPlayer.getAudioDelay()) + ((long) audioPlayHelper.customDelay)) - ((long) ((int) (iMediaPlayer != null ? iMediaPlayer.getCurrentPosition() : 0L)));
        if (audioPlayHelper.adjustDelayCount <= i) {
            Log.d("AudioPlayHelper", time + "delay:" + audioPlayHelper.vlcPlayer.getAudioDelay() + " adjustCount:" + audioPlayHelper.adjustDelayCount);
            if (Math.abs(time) > Math.abs(audioPlayHelper.customDelay) + 1000) {
                audioPlayHelper.vlcPlayer.setAudioDelay(0L);
                Log.d("AudioPlayHelper", "set time");
                audioPlayHelper.vlcPlayer.setTime((iMediaPlayer != null ? iMediaPlayer.getCurrentPosition() : 0L) - ((long) audioPlayHelper.customDelay));
                audioPlayHelper.adjustDelayCount++;
                audioPlayHelper.timeList.clear();
                Disposable disposable = audioPlayHelper.delayDisposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                audioPlayHelper.isNeedAddTime = true;
                audioPlayHelper.startDelay = false;
            } else {
                if (audioPlayHelper.isNeedAddTime) {
                    audioPlayHelper.timeList.add(Long.valueOf(time));
                }
                if (!audioPlayHelper.startDelay) {
                    audioPlayHelper.startDelay = true;
                    audioPlayHelper.startTimerDelay();
                } else {
                    Disposable disposable2 = audioPlayHelper.delayDisposable;
                    if (disposable2 != null && disposable2.isDisposed() && Math.abs(time) > 400) {
                        int i2 = audioPlayHelper.over400Count + 1;
                        audioPlayHelper.over400Count = i2;
                        if (i2 > 5) {
                            audioPlayHelper.over400Count = 0;
                            audioPlayHelper.startDelay = false;
                        }
                    }
                }
            }
        } else {
            Disposable disposable3 = audioPlayHelper.timerDisposable;
            if (disposable3 != null) {
                disposable3.dispose();
            }
            Disposable disposable4 = audioPlayHelper.delayDisposable;
            if (disposable4 != null) {
                disposable4.dispose();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTimer$lambda$6(AudioPlayHelper audioPlayHelper, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        audioPlayHelper.timerDisposable = it;
        audioPlayHelper.adjustDelayCount = 0;
        return Unit.INSTANCE;
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
        Observable<Long> observableTimer = Observable.timer(3L, TimeUnit.SECONDS);
        Intrinsics.checkNotNullExpressionValue(observableTimer, "timer(...)");
        RxSubscribersKt.subscribeTo$default(observableTimer, null, null, new Function1() { // from class: com.movieboxpro.android.utils.AudioPlayHelper$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AudioPlayHelper.startTimerDelay$lambda$7(this.f$0, (Long) obj);
            }
        }, new Function1() { // from class: com.movieboxpro.android.utils.AudioPlayHelper$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AudioPlayHelper.startTimerDelay$lambda$8(this.f$0, (Disposable) obj);
            }
        }, 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTimerDelay$lambda$8(AudioPlayHelper audioPlayHelper, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        audioPlayHelper.delayDisposable = it;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit startTimerDelay$lambda$7(AudioPlayHelper audioPlayHelper, Long l) {
        audioPlayHelper.over400Count = 0;
        long jAverageOfLong = (long) CollectionsKt.averageOfLong(audioPlayHelper.timeList);
        Log.d("AudioPlayHelper", "setDelay:" + jAverageOfLong);
        audioPlayHelper.vlcPlayer.setAudioDelay(jAverageOfLong);
        audioPlayHelper.timeList.clear();
        return Unit.INSTANCE;
    }

    public final void changeUrl(String url, IMediaPlayer player) {
        play(url, player);
    }

    public final void setAudioDelay(int delay, IMediaPlayer player) {
        this.customDelay += delay;
        this.vlcPlayer.setTime((player != null ? player.getCurrentPosition() : 0L) - ((long) this.customDelay));
    }

    public final void setPlaySpeed(float speed, IMediaPlayer player) {
        this.vlcPlayer.setRate(speed);
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
        this.vlcPlayer.setTime(position);
        this.startDelay = false;
    }

    public final void start(IMediaPlayer player) {
        if (this.vlcPlayer.isPlaying()) {
            return;
        }
        this.vlcPlayer.play();
        this.isNeedAddTime = true;
        this.startDelay = false;
        Disposable disposable = this.timerDisposable;
        if (disposable == null || (disposable != null && disposable.isDisposed())) {
            startTimer(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, player, 5);
        }
    }

    public final void pause(IMediaPlayer player) {
        if (this.vlcPlayer.isPlaying()) {
            this.vlcPlayer.pause();
        }
    }

    public final void pauseOrPlay(IMediaPlayer player) {
        if (this.vlcPlayer.isPlaying()) {
            this.vlcPlayer.pause();
            return;
        }
        this.vlcPlayer.play();
        this.isNeedAddTime = true;
        this.startDelay = false;
        Disposable disposable = this.timerDisposable;
        if (disposable == null || (disposable != null && disposable.isDisposed())) {
            startTimer(ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS, player, 5);
        }
    }

    public final void resume() {
        this.vlcPlayer.play();
    }

    public final void setVolume(int volume) {
        this.vlcPlayer.setVolume(volume);
    }

    public final void release() {
        try {
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
            this.vlcPlayer.stop();
            this.vlcPlayer.setEventListener((MediaPlayer.EventListener) null);
            IMedia media = this.vlcPlayer.getMedia();
            if (media != null) {
                media.release();
            }
        } catch (Exception unused) {
        }
    }
}
