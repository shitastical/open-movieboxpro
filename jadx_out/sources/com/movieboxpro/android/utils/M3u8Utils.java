package com.movieboxpro.android.utils;

import android.content.Context;
import android.net.Uri;
import androidx.media3.exoplayer.hls.playlist.HlsMultivariantPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylist;
import androidx.media3.exoplayer.hls.playlist.HlsPlaylistParser;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.service.FileDownloadService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlin.text.Typography;

/* JADX INFO: compiled from: M3u8Utils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u0006J\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\t2\u0006\u0010\n\u001a\u00020\u0006J \u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000fH\u0002J&\u0010\u0010\u001a\u0004\u0018\u00010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u0002J&\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0013j\b\u0012\u0004\u0012\u00020\u0006`\u00142\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u000fH\u0002¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/utils/M3u8Utils;", "", "<init>", "()V", "getDownloadUrl", "Lkotlin/Triple;", "", FileDownloadService.PARAMS_KEY_PATH, "parseExtXMedia", "", "line", "findEacAudio", "Landroidx/media3/exoplayer/hls/playlist/HlsMultivariantPlaylist$Rendition;", "trackName", "audios", "", "findAudio", "languages", "getStrings", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class M3u8Utils {
    public static final M3u8Utils INSTANCE = new M3u8Utils();

    private M3u8Utils() {
    }

    public final Triple<String, String, String> getDownloadUrl(String path) throws IOException {
        List<String> pathSegments;
        String str;
        List listSplit$default;
        Intrinsics.checkNotNullParameter(path, "path");
        HlsPlaylistParser hlsPlaylistParser = new HlsPlaylistParser();
        Uri uri = Uri.parse(path);
        Intrinsics.checkNotNullExpressionValue(uri, "parse(this)");
        HlsPlaylist hlsPlaylist = hlsPlaylistParser.parse(uri, (InputStream) new FileInputStream(new File(path)));
        Intrinsics.checkNotNullExpressionValue(hlsPlaylist, "parse(...)");
        SettingManager settingManager = SettingManager.INSTANCE;
        Context context = App.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        ArrayList<String> strings = getStrings(settingManager.getDownloadAudioLanguage(context));
        if (hlsPlaylist instanceof HlsMultivariantPlaylist) {
            HlsMultivariantPlaylist hlsMultivariantPlaylist = (HlsMultivariantPlaylist) hlsPlaylist;
            List<Uri> mediaPlaylistUrls = hlsMultivariantPlaylist.mediaPlaylistUrls;
            Intrinsics.checkNotNullExpressionValue(mediaPlaylistUrls, "mediaPlaylistUrls");
            Uri uri2 = (Uri) CollectionsKt.firstOrNull((List) mediaPlaylistUrls);
            String str2 = null;
            if (uri2 == null) {
                return null;
            }
            List<HlsMultivariantPlaylist.Rendition> audios = hlsMultivariantPlaylist.audios;
            Intrinsics.checkNotNullExpressionValue(audios, "audios");
            HlsMultivariantPlaylist.Rendition renditionFindAudio = findAudio(audios, strings);
            if (renditionFindAudio != null) {
                Uri uri3 = renditionFindAudio.url;
                if (uri3 != null && (pathSegments = uri3.getPathSegments()) != null && (str = (String) CollectionsKt.lastOrNull((List) pathSegments)) != null && (listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"."}, false, 0, 6, (Object) null)) != null) {
                    str2 = (String) CollectionsKt.firstOrNull(listSplit$default);
                }
                String str3 = str2;
                if (str3 != null && !StringsKt.isBlank(str3)) {
                    List<HlsMultivariantPlaylist.Rendition> audios2 = hlsMultivariantPlaylist.audios;
                    Intrinsics.checkNotNullExpressionValue(audios2, "audios");
                    HlsMultivariantPlaylist.Rendition renditionFindEacAudio = findEacAudio(str2, audios2);
                    if (renditionFindEacAudio != null) {
                        return new Triple<>(uri2.toString(), String.valueOf(renditionFindAudio.url), String.valueOf(renditionFindEacAudio.url));
                    }
                    return new Triple<>(uri2.toString(), String.valueOf(renditionFindAudio.url), "");
                }
            } else {
                return new Triple<>(uri2.toString(), "", "");
            }
        }
        return new Triple<>("", "", "");
    }

    public final Map<String, String> parseExtXMedia(String line) {
        Intrinsics.checkNotNullParameter(line, "line");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (MatchResult matchResult : Regex.findAll$default(new Regex("([A-Z-]+)=(\"([^\"]*)\"|[^,]*)"), StringsKt.substringAfter(line, ":", ""), 0, 2, null)) {
            String str = matchResult.getGroupValues().get(1);
            String str2 = matchResult.getGroupValues().get(3);
            if (str2.length() == 0) {
                str2 = matchResult.getGroupValues().get(2);
            }
            linkedHashMap.put(str, StringsKt.trim(str2, Typography.quote));
        }
        return linkedHashMap;
    }

    private final HlsMultivariantPlaylist.Rendition findEacAudio(String trackName, List<HlsMultivariantPlaylist.Rendition> audios) {
        List<String> pathSegments;
        String str;
        List listSplit$default;
        for (HlsMultivariantPlaylist.Rendition rendition : audios) {
            Uri uri = rendition.url;
            String str2 = (uri == null || (pathSegments = uri.getPathSegments()) == null || (str = (String) CollectionsKt.lastOrNull((List) pathSegments)) == null || (listSplit$default = StringsKt.split$default((CharSequence) str, new String[]{"."}, false, 0, 6, (Object) null)) == null) ? null : (String) CollectionsKt.firstOrNull(listSplit$default);
            if (!Intrinsics.areEqual(str2, trackName) && str2 != null && StringsKt.contains$default((CharSequence) str2, (CharSequence) trackName, false, 2, (Object) null)) {
                return rendition;
            }
        }
        return null;
    }

    private final HlsMultivariantPlaylist.Rendition findAudio(List<HlsMultivariantPlaylist.Rendition> audios, List<String> languages) {
        for (String str : languages) {
            for (HlsMultivariantPlaylist.Rendition rendition : audios) {
                if (Intrinsics.areEqual(str, rendition.format.language)) {
                    return rendition;
                }
            }
        }
        return (HlsMultivariantPlaylist.Rendition) CollectionsKt.firstOrNull((List) audios);
    }

    private final ArrayList<String> getStrings(List<String> languages) {
        List listEmptyList;
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = languages.iterator();
        while (it.hasNext()) {
            List<String> listSplit = new Regex("->").split(it.next(), 0);
            if (!listSplit.isEmpty()) {
                ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        if (listIterator.previous().length() != 0) {
                            listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                            break;
                        }
                    } else {
                        listEmptyList = CollectionsKt.emptyList();
                        break;
                    }
                }
            } else {
                listEmptyList = CollectionsKt.emptyList();
                break;
            }
            String str = ((String[]) listEmptyList.toArray(new String[0]))[0];
            if (Intrinsics.areEqual("zh-CN", str)) {
                arrayList.add("zh");
            } else {
                arrayList.add(str);
            }
        }
        return arrayList;
    }
}
