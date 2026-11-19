package com.movieboxpro.android.service;

import android.net.Uri;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser;
import com.jeffmony.downloader.m3u8.M3U8Constants;
import com.jeffmony.downloader.model.VideoTaskItem;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.dao.HlsDownloadDao;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.db.entity.HlsDownload;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.M3u8Utils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ShellUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: DownloadService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.movieboxpro.android.service.DownloadService$onCreate$2$onDownloadSuccess$1", f = "DownloadService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
final class DownloadService$onCreate$2$onDownloadSuccess$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VideoTaskItem $item;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DownloadService$onCreate$2$onDownloadSuccess$1(VideoTaskItem videoTaskItem, Continuation<? super DownloadService$onCreate$2$onDownloadSuccess$1> continuation) {
        super(2, continuation);
        this.$item = videoTaskItem;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DownloadService$onCreate$2$onDownloadSuccess$1(this.$item, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DownloadService$onCreate$2$onDownloadSuccess$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws Throwable {
        File file;
        String str;
        String string;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        try {
            DownloadFile downloadFileFindDownloadFileByDownloadId = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByDownloadId(this.$item.getGroupName());
            if (downloadFileFindDownloadFileByDownloadId != null) {
                HlsDownloadDao hlsDownloadDao = App.getDB().hlsDownloadDao();
                String downloadId = downloadFileFindDownloadFileByDownloadId.getDownloadId();
                Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
                HlsDownload hlsDownloadFindByDownloadId = hlsDownloadDao.findByDownloadId(downloadId);
                if (hlsDownloadFindByDownloadId != null) {
                    if (Intrinsics.areEqual(this.$item.getUrl(), hlsDownloadFindByDownloadId.getVideoUrl())) {
                        hlsDownloadFindByDownloadId.setVideoSize(this.$item.getDownloadSize());
                        hlsDownloadFindByDownloadId.setVideoPercent(this.$item.getPercent());
                        String filePath = this.$item.getFilePath();
                        Intrinsics.checkNotNullExpressionValue(filePath, "getFilePath(...)");
                        hlsDownloadFindByDownloadId.setVideoLocalPath(filePath);
                    } else if (Intrinsics.areEqual(this.$item.getUrl(), hlsDownloadFindByDownloadId.getAudioUrl())) {
                        hlsDownloadFindByDownloadId.setAudioSize(this.$item.getDownloadSize());
                        hlsDownloadFindByDownloadId.setAudioPercent(this.$item.getPercent());
                        String filePath2 = this.$item.getFilePath();
                        Intrinsics.checkNotNullExpressionValue(filePath2, "getFilePath(...)");
                        hlsDownloadFindByDownloadId.setAudioLocalPath(filePath2);
                    } else if (Intrinsics.areEqual(this.$item.getUrl(), hlsDownloadFindByDownloadId.getAudioEacUrl())) {
                        hlsDownloadFindByDownloadId.setAudioEacSize(this.$item.getDownloadSize());
                        hlsDownloadFindByDownloadId.setAudioEacPercent(this.$item.getPercent());
                        String filePath3 = this.$item.getFilePath();
                        Intrinsics.checkNotNullExpressionValue(filePath3, "getFilePath(...)");
                        hlsDownloadFindByDownloadId.setAudioEacLocalPath(filePath3);
                    }
                    downloadFileFindDownloadFileByDownloadId.setDownloadSize(hlsDownloadFindByDownloadId.getVideoSize() + hlsDownloadFindByDownloadId.getAudioSize() + hlsDownloadFindByDownloadId.getAudioEacSize());
                    downloadFileFindDownloadFileByDownloadId.setSize(0L);
                    downloadFileFindDownloadFileByDownloadId.setPercent((((hlsDownloadFindByDownloadId.getVideoPercent() + hlsDownloadFindByDownloadId.getAudioPercent()) + hlsDownloadFindByDownloadId.getAudioEacPercent()) / 3) / 100.0f);
                    boolean z = hlsDownloadFindByDownloadId.getVideoPercent() == 100.0f;
                    boolean z2 = hlsDownloadFindByDownloadId.getAudioUrl().length() == 0 || (hlsDownloadFindByDownloadId.getAudioUrl().length() > 0 && hlsDownloadFindByDownloadId.getAudioPercent() == 100.0f);
                    boolean z3 = hlsDownloadFindByDownloadId.getAudioEacUrl().length() == 0 || (hlsDownloadFindByDownloadId.getAudioEacUrl().length() > 0 && hlsDownloadFindByDownloadId.getAudioEacPercent() == 100.0f);
                    if (z && z2 && z3) {
                        HlsPlaylistParser hlsPlaylistParser = new HlsPlaylistParser();
                        Uri uri = Uri.parse(hlsDownloadFindByDownloadId.getM3u8LocalPath());
                        Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
                        HlsPlaylist hlsPlaylist = hlsPlaylistParser.parse(uri, (InputStream) new FileInputStream(new File(hlsDownloadFindByDownloadId.getM3u8LocalPath())));
                        Intrinsics.checkNotNullExpressionValue(hlsPlaylist, "parse(...)");
                        StringBuilder sb = new StringBuilder();
                        sb.append(M3U8Constants.PLAYLIST_HEADER);
                        sb.append(ShellUtil.COMMAND_LINE_END);
                        List<String> tags = hlsPlaylist.tags;
                        Intrinsics.checkNotNullExpressionValue(tags, "tags");
                        for (String str2 : tags) {
                            M3u8Utils m3u8Utils = M3u8Utils.INSTANCE;
                            Intrinsics.checkNotNull(str2);
                            Map<String, String> extXMedia = m3u8Utils.parseExtXMedia(str2);
                            String str3 = extXMedia.get("TYPE");
                            String str4 = extXMedia.get("VIDEO-RANGE");
                            if (str3 == null && str4 == null) {
                                sb.append(str2);
                                sb.append(ShellUtil.COMMAND_LINE_END);
                            } else if (Intrinsics.areEqual(str3, "AUDIO")) {
                                String str5 = extXMedia.get("URI");
                                String strRemoveSurrounding = (str5 == null || (string = StringsKt.trim((CharSequence) str5).toString()) == null) ? null : StringsKt.removeSurrounding(string, (CharSequence) "\"");
                                if (hlsDownloadFindByDownloadId.getAudioUrl().length() <= 0 || hlsDownloadFindByDownloadId.getAudioLocalPath().length() <= 0 || !Intrinsics.areEqual(strRemoveSurrounding, hlsDownloadFindByDownloadId.getAudioUrl())) {
                                    str = "\n\n";
                                } else {
                                    str = "\n\n";
                                    sb.append(StringsKt.replace$default(str2, strRemoveSurrounding, hlsDownloadFindByDownloadId.getAudioLocalPath(), false, 4, (Object) null));
                                    sb.append(str);
                                }
                                if (hlsDownloadFindByDownloadId.getAudioEacUrl().length() > 0 && hlsDownloadFindByDownloadId.getAudioEacLocalPath().length() > 0 && Intrinsics.areEqual(strRemoveSurrounding, hlsDownloadFindByDownloadId.getAudioEacUrl())) {
                                    sb.append(StringsKt.replace$default(str2, strRemoveSurrounding, hlsDownloadFindByDownloadId.getAudioEacLocalPath(), false, 4, (Object) null));
                                    sb.append(str);
                                }
                            } else if (str4 != null) {
                                sb.append(str2);
                                sb.append(ShellUtil.COMMAND_LINE_END);
                                sb.append(hlsDownloadFindByDownloadId.getVideoLocalPath());
                                sb.append("\n\n");
                            }
                        }
                        String str6 = FileUtils.getFileNameNoExtension(hlsDownloadFindByDownloadId.getM3u8LocalPath()) + "_playback.m3u8";
                        String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.DOWNLOAD_DEVICE_PATH, "");
                        Intrinsics.checkNotNull(string2);
                        if (string2.length() == 0) {
                            file = new File(Constant.DIR, Constant.Prefs.M3U8_CACHE_DIR);
                        } else {
                            file = new File(string2, Constant.Prefs.M3U8_CACHE_DIR);
                        }
                        if (!file.exists()) {
                            FileUtils.createOrExistsDir(file);
                        }
                        File file2 = new File(file, str6);
                        FileUtils.write(file2, sb.toString(), false);
                        downloadFileFindDownloadFileByDownloadId.setPath(file2.getPath());
                        String path = file2.getPath();
                        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
                        hlsDownloadFindByDownloadId.setPlaybackM3u8LocalPath(path);
                        downloadFileFindDownloadFileByDownloadId.setSize(hlsDownloadFindByDownloadId.getVideoSize() + hlsDownloadFindByDownloadId.getAudioSize() + hlsDownloadFindByDownloadId.getAudioEacSize());
                        downloadFileFindDownloadFileByDownloadId.setStatus(1);
                        DownloadFileHelper.INSTANCE.getInstance().updateDownloadFile(downloadFileFindDownloadFileByDownloadId);
                    } else {
                        downloadFileFindDownloadFileByDownloadId.setStatus(6);
                        DownloadFileHelper.INSTANCE.getInstance().updateDownloadFile(downloadFileFindDownloadFileByDownloadId);
                    }
                    App.getDB().hlsDownloadDao().insertOrReplace(hlsDownloadFindByDownloadId);
                }
            }
        } catch (Exception e) {
            DownloadFile downloadFileFindDownloadFileByDownloadId2 = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByDownloadId(this.$item.getGroupName());
            if (downloadFileFindDownloadFileByDownloadId2 != null) {
                downloadFileFindDownloadFileByDownloadId2.setStatus(7);
                String message = e.getMessage();
                downloadFileFindDownloadFileByDownloadId2.setErrorMsg(message != null ? message : "");
                DownloadFileHelper.INSTANCE.getInstance().updateDownloadFile(downloadFileFindDownloadFileByDownloadId2);
            }
        }
        return Unit.INSTANCE;
    }
}
