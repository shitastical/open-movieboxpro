package com.movieboxpro.android.service;

import android.app.IntentService;
import android.content.Intent;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.tvchannel.SampleTvProvider;
import com.movieboxpro.android.tvchannel.scheduler.ClipData;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: DealWatchNextService.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/service/DealWatchNextService;", "Landroid/app/IntentService;", "<init>", "()V", "onHandleIntent", "", "intent", "Landroid/content/Intent;", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DealWatchNextService extends IntentService {
    public static final String ADD_ACTION = "add";
    public static final String DELETE_ACTION = "delete";
    public static final String DURATION = "duration";
    public static final String EPISODE = "episode";
    public static final String ID = "id";
    public static final String MEDIA_DATA = "media_data";
    public static final String PROGRESS = "progress";
    public static final String SEASON = "season";

    public DealWatchNextService() {
        super("DealWatchNextService");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) throws Throwable {
        String action;
        ClipData clipDataBuild;
        if (intent == null || (action = intent.getAction()) == null) {
            return;
        }
        int iHashCode = action.hashCode();
        if (iHashCode == -1335458389) {
            if (action.equals("delete")) {
                try {
                    SampleTvProvider.deleteWatchNextContinue(getApplicationContext(), intent.getStringExtra("id"));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
        if (iHashCode == 96417 && action.equals(ADD_ACTION)) {
            Serializable serializableExtra = intent.getSerializableExtra(MEDIA_DATA);
            int intExtra = intent.getIntExtra("duration", 0);
            int intExtra2 = intent.getIntExtra("progress", 0);
            if (serializableExtra instanceof TvDetail) {
                int intExtra3 = intent.getIntExtra(SEASON, 0);
                int intExtra4 = intent.getIntExtra(EPISODE, 0);
                TvDetail tvDetail = (TvDetail) serializableExtra;
                ClipData.Builder description = new ClipData.Builder().setClipId(tvDetail.id).setContentId(tvDetail.id).setDuration(intExtra).setProgress(intExtra2).setDescription("Resuming watch");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%s S%s E%s", Arrays.copyOf(new Object[]{tvDetail.title, Integer.valueOf(intExtra3), Integer.valueOf(intExtra4)}, 3));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                clipDataBuild = description.setTitle(str).setCardImageUrl(tvDetail.banner_mini).setBaseMediaModel((BaseMediaModel) serializableExtra).setBoxType(2).build();
                Intrinsics.checkNotNullExpressionValue(clipDataBuild, "build(...)");
            } else {
                Intrinsics.checkNotNull(serializableExtra, "null cannot be cast to non-null type com.movieboxpro.android.model.BaseMediaModel");
                BaseMediaModel baseMediaModel = (BaseMediaModel) serializableExtra;
                clipDataBuild = new ClipData.Builder().setClipId(baseMediaModel.id).setContentId(baseMediaModel.id).setDuration(intExtra).setProgress(intExtra2).setDescription("Resuming watch").setTitle(baseMediaModel.title).setCardImageUrl(baseMediaModel.poster).setBaseMediaModel(baseMediaModel).setBoxType(1).build();
                Intrinsics.checkNotNullExpressionValue(clipDataBuild, "build(...)");
            }
            try {
                SampleTvProvider.addWatchNextContinue(getApplicationContext(), clipDataBuild);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
