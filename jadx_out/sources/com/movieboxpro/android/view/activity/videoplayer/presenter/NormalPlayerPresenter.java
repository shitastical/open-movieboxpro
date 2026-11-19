package com.movieboxpro.android.view.activity.videoplayer.presenter;

import com.dueeeke.model.MediaQualityInfo;
import com.dueeeke.videoplayer.controller.MediaPlayerControl;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface NormalPlayerPresenter extends MediaPlayerControl {
    int getDefinition();

    List<MediaQualityInfo> getDefinitionData();

    void switchDefaultDefinition(boolean open);

    void switchDefinition(int definition);
}
