package com.avery.subtitle;

import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.runtime.AppTaskExecutor;

/* JADX INFO: loaded from: classes4.dex */
public class UIRenderTask implements Runnable {
    private SubtitleEngine.OnSubtitleChangeListener mOnSubtitleChangeListener;
    private Subtitle mSubtitle;

    public UIRenderTask(SubtitleEngine.OnSubtitleChangeListener onSubtitleChangeListener) {
        this.mOnSubtitleChangeListener = onSubtitleChangeListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        SubtitleEngine.OnSubtitleChangeListener onSubtitleChangeListener = this.mOnSubtitleChangeListener;
        if (onSubtitleChangeListener != null) {
            onSubtitleChangeListener.onSubtitleChanged(this.mSubtitle);
        }
    }

    public void execute(Subtitle subtitle) {
        this.mSubtitle = subtitle;
        AppTaskExecutor.mainThread().execute(this);
    }
}
