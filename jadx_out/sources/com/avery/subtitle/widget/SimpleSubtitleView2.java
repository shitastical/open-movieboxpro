package com.avery.subtitle.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;
import com.avery.subtitle.DefaultSubtitleEngine2;
import com.avery.subtitle.OnMediaStatusListener;
import com.avery.subtitle.SubtitleEngine;
import com.avery.subtitle.model.Subtitle;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SimpleSubtitleView2 extends TextView implements SubtitleEngine, SubtitleEngine.OnSubtitleChangeListener, SubtitleEngine.OnSubtitlePreparedListener {
    private static final String EMPTY_TEXT = "";
    private static final int SHADOW_COLOR = Color.parseColor("#80000000");
    private static final float SHADOW_DX = 0.0f;
    private static final float SHADOW_DY = 4.0f;
    private static final float SHADOW_RADIUS = 3.0f;
    private int color;
    private SubtitleEngine mSubtitleEngine;
    private int textSize;
    private Typeface typeface;

    public SimpleSubtitleView2(Context context) {
        super(context);
        this.textSize = 18;
        this.color = -1;
        this.typeface = Typeface.DEFAULT_BOLD;
        init();
    }

    public SimpleSubtitleView2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.textSize = 18;
        this.color = -1;
        this.typeface = Typeface.DEFAULT_BOLD;
        init();
    }

    public SimpleSubtitleView2(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.textSize = 18;
        this.color = -1;
        this.typeface = Typeface.DEFAULT_BOLD;
        init();
    }

    private void init() {
        DefaultSubtitleEngine2 defaultSubtitleEngine2 = new DefaultSubtitleEngine2();
        this.mSubtitleEngine = defaultSubtitleEngine2;
        defaultSubtitleEngine2.setOnSubtitlePreparedListener(this);
        this.mSubtitleEngine.setOnSubtitleChangeListener(this);
        setLayerType(1, null);
        applyTextStyle();
    }

    private void applyTextStyle() {
        setTextColor(this.color);
        setTextSize(2, this.textSize);
        setTypeface(this.typeface);
        setShadowLayer(SHADOW_RADIUS, 0.0f, SHADOW_DY, SHADOW_COLOR);
    }

    @Override // com.avery.subtitle.SubtitleEngine.OnSubtitlePreparedListener
    public void onSubtitlePrepared(List<Subtitle> list) {
        start();
    }

    public void changeFont(Typeface typeface) {
        this.typeface = typeface;
        applyTextStyle();
    }

    public void changeSize(int i) {
        this.textSize = i;
        applyTextStyle();
    }

    public void changeColor(int i) {
        this.color = i;
        applyTextStyle();
    }

    @Override // com.avery.subtitle.SubtitleEngine.OnSubtitleChangeListener
    public void onSubtitleChanged(Subtitle subtitle) {
        if (subtitle == null || subtitle.content == null) {
            setText("");
            setVisibility(4);
        } else {
            setVisibility(0);
            applyTextStyle();
            setText(Html.fromHtml(subtitle.content));
        }
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void setSubtitlePath(String str) {
        setVisibility(0);
        this.mSubtitleEngine.setSubtitlePath(str);
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void reset() {
        this.mSubtitleEngine.reset();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void start() {
        this.mSubtitleEngine.start();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void pause() {
        this.mSubtitleEngine.pause();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void resume() {
        this.mSubtitleEngine.resume();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void stop() {
        setText("");
        setVisibility(8);
        this.mSubtitleEngine.stop();
        this.mSubtitleEngine.reset();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void destroy() {
        this.mSubtitleEngine.destroy();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void bindOnMediaStatusListener(OnMediaStatusListener onMediaStatusListener) {
        this.mSubtitleEngine.bindOnMediaStatusListener(onMediaStatusListener);
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void setDelay(int i) {
        this.mSubtitleEngine.setDelay(i);
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public int getCurrSubtitlePos() {
        return this.mSubtitleEngine.getCurrSubtitlePos();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public long setCurrSubtitle(Subtitle subtitle) {
        return this.mSubtitleEngine.setCurrSubtitle(subtitle);
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void bindToMediaPlayer(MediaPlayer mediaPlayer) {
        this.mSubtitleEngine.bindToMediaPlayer(mediaPlayer);
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public List<Subtitle> getSubtitles() {
        return this.mSubtitleEngine.getSubtitles();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void clearSubtitles() {
        this.mSubtitleEngine.clearSubtitles();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void setOnSubtitlePreparedListener(SubtitleEngine.OnSubtitlePreparedListener onSubtitlePreparedListener) {
        this.mSubtitleEngine.setOnSubtitlePreparedListener(onSubtitlePreparedListener);
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void setOnSubtitleChangeListener(SubtitleEngine.OnSubtitleChangeListener onSubtitleChangeListener) {
        this.mSubtitleEngine.setOnSubtitleChangeListener(onSubtitleChangeListener);
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void setSubtitles(List<Subtitle> list) {
        this.mSubtitleEngine.setSubtitles(list);
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void refreshSubtitle() {
        if (getVisibility() == 0) {
            this.mSubtitleEngine.refreshSubtitle();
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        destroy();
        super.onDetachedFromWindow();
    }
}
