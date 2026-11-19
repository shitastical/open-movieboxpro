package com.google.android.exoplayer.hls;

import j$.util.DesugarCollections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class HlsMasterPlaylist extends HlsPlaylist {
    public final List<Variant> audios;
    public final String muxedAudioLanguage;
    public final String muxedCaptionLanguage;
    public final List<Variant> subtitles;
    public final List<Variant> variants;

    public HlsMasterPlaylist(String str, List<Variant> list, List<Variant> list2, List<Variant> list3, String str2, String str3) {
        super(str, 0);
        this.variants = DesugarCollections.unmodifiableList(list);
        this.audios = DesugarCollections.unmodifiableList(list2);
        this.subtitles = DesugarCollections.unmodifiableList(list3);
        this.muxedAudioLanguage = str2;
        this.muxedCaptionLanguage = str3;
    }
}
