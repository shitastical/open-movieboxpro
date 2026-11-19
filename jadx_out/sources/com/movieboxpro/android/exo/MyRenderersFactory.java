package com.movieboxpro.android.exo;

import android.content.Context;
import android.os.Handler;
import androidx.media3.common.AudioAttributes;
import androidx.media3.decoder.ffmpeg.FfmpegAudioRenderer;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.Renderer;
import androidx.media3.exoplayer.audio.AudioCapabilities;
import androidx.media3.exoplayer.audio.AudioRendererEventListener;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.mediacodec.MediaCodecInfo;
import androidx.media3.exoplayer.mediacodec.MediaCodecSelector;
import androidx.media3.exoplayer.mediacodec.MediaCodecUtil;
import com.movieboxpro.android.utils.SettingManager;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyRenderersFactory.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JN\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015H\u0014¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/exo/MyRenderersFactory;", "Landroidx/media3/exoplayer/DefaultRenderersFactory;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "buildAudioRenderers", "", "extensionRendererMode", "", "mediaCodecSelector", "Landroidx/media3/exoplayer/mediacodec/MediaCodecSelector;", "enableDecoderFallback", "", "audioSink", "Landroidx/media3/exoplayer/audio/AudioSink;", "eventHandler", "Landroid/os/Handler;", "eventListener", "Landroidx/media3/exoplayer/audio/AudioRendererEventListener;", "out", "Ljava/util/ArrayList;", "Landroidx/media3/exoplayer/Renderer;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MyRenderersFactory extends DefaultRenderersFactory {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MyRenderersFactory(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setExtensionRendererMode(1);
        setEnableDecoderFallback(true);
        setMediaCodecSelector(new MediaCodecSelector() { // from class: com.movieboxpro.android.exo.MyRenderersFactory$$ExternalSyntheticLambda0
            @Override // androidx.media3.exoplayer.mediacodec.MediaCodecSelector
            public final List getDecoderInfos(String str, boolean z, boolean z2) {
                return MyRenderersFactory._init_$lambda$4(str, z, z2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List _init_$lambda$4(String mimeType, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        Intrinsics.checkNotNullParameter(mimeType, "mimeType");
        List<MediaCodecInfo> decoderInfos = MediaCodecSelector.DEFAULT.getDecoderInfos(mimeType, z, z2);
        Intrinsics.checkNotNullExpressionValue(decoderInfos, "getDecoderInfos(...)");
        if (SettingManager.INSTANCE.isHwDecoder()) {
            ArrayList arrayList = new ArrayList();
            for (Object obj : decoderInfos) {
                if (((MediaCodecInfo) obj).hardwareAccelerated) {
                    arrayList.add(obj);
                }
            }
            ArrayList arrayList2 = arrayList;
            if (!arrayList2.isEmpty()) {
                decoderInfos = arrayList2;
            }
            return decoderInfos;
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : decoderInfos) {
            if (!((MediaCodecInfo) obj2).hardwareAccelerated) {
                arrayList3.add(obj2);
            }
        }
        ArrayList arrayList4 = arrayList3;
        if (!arrayList4.isEmpty()) {
            decoderInfos = arrayList4;
        }
        return decoderInfos;
    }

    @Override // androidx.media3.exoplayer.DefaultRenderersFactory
    protected void buildAudioRenderers(Context context, int extensionRendererMode, MediaCodecSelector mediaCodecSelector, boolean enableDecoderFallback, AudioSink audioSink, Handler eventHandler, AudioRendererEventListener eventListener, ArrayList<Renderer> out) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaCodecSelector, "mediaCodecSelector");
        Intrinsics.checkNotNullParameter(audioSink, "audioSink");
        Intrinsics.checkNotNullParameter(eventHandler, "eventHandler");
        Intrinsics.checkNotNullParameter(eventListener, "eventListener");
        Intrinsics.checkNotNullParameter(out, "out");
        AudioAttributes audioAttributesBuild = new AudioAttributes.Builder().setUsage(1).setContentType(3).build();
        Intrinsics.checkNotNullExpressionValue(audioAttributesBuild, "build(...)");
        AudioCapabilities capabilities = AudioCapabilities.getCapabilities(context, audioAttributesBuild, null);
        Intrinsics.checkNotNullExpressionValue(capabilities, "getCapabilities(...)");
        if (!capabilities.supportsEncoding(6)) {
            out.add(new FfmpegAudioRenderer(eventHandler, eventListener, audioSink));
        }
        super.buildAudioRenderers(context, extensionRendererMode, mediaCodecSelector, enableDecoderFallback, audioSink, eventHandler, eventListener, out);
    }
}
