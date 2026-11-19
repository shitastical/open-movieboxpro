package com.movieboxpro.android.service;

import android.app.IntentService;
import android.content.Intent;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.tvchannel.Clip;
import com.movieboxpro.android.tvchannel.Playlist;
import com.movieboxpro.android.tvchannel.SampleTvProvider;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.PrefsUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: CreateChannelService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0015¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/service/CreateChannelService;", "Landroid/app/IntentService;", "<init>", "()V", "onHandleIntent", "", "intent", "Landroid/content/Intent;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CreateChannelService extends IntentService {
    public CreateChannelService() {
        super("CreateChannelService");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.PREVIEWPROGRAM_IDS);
                Playlist playlist = (Playlist) intent.getParcelableExtra(DealWatchNextService.MEDIA_DATA);
                List<Clip> list = playlist != null ? playlist.getmClips() : null;
                if (list != null) {
                    String str = string;
                    if (str != null && str.length() != 0) {
                        int i = 0;
                        for (Object obj : StringsKt.split$default((CharSequence) string, new String[]{"|"}, false, 0, 6, (Object) null)) {
                            int i2 = i + 1;
                            if (i < 0) {
                                CollectionsKt.throwIndexOverflow();
                            }
                            String str2 = (String) obj;
                            if (CommonExtKt.checkIndexLegal(i, list)) {
                                list.get(i).setProgramId(Long.parseLong(str2));
                                SampleTvProvider.updateProgramClip(this, list.get(i));
                            }
                            i = i2;
                        }
                        return;
                    }
                    PrefsUtils.getInstance().putLong(Constant.Prefs.CREATE_CHANNEL, SampleTvProvider.addChannel(this, playlist));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
