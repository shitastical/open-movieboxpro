package com.google.android.exoplayer.text;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.MediaFormatHolder;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.SampleSource;
import com.google.android.exoplayer.SampleSourceTrackRenderer;
import com.google.android.exoplayer.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class TextTrackRenderer extends SampleSourceTrackRenderer implements Handler.Callback {
    private static final List<Class<? extends SubtitleParser>> DEFAULT_PARSER_CLASSES;
    private static final int MSG_UPDATE_OVERLAY = 0;
    private final MediaFormatHolder formatHolder;
    private boolean inputStreamEnded;
    private PlayableSubtitle nextSubtitle;
    private int nextSubtitleEventIndex;
    private SubtitleParserHelper parserHelper;
    private int parserIndex;
    private HandlerThread parserThread;
    private PlayableSubtitle subtitle;
    private final SubtitleParser[] subtitleParsers;
    private final TextRenderer textRenderer;
    private final Handler textRendererHandler;

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected long getBufferedPositionUs() {
        return -3L;
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isReady() {
        return true;
    }

    static {
        ArrayList arrayList = new ArrayList();
        DEFAULT_PARSER_CLASSES = arrayList;
        try {
            arrayList.add(Class.forName("com.google.android.exoplayer.text.webvtt.WebvttParser").asSubclass(SubtitleParser.class));
        } catch (ClassNotFoundException unused) {
        }
        try {
            DEFAULT_PARSER_CLASSES.add(Class.forName("com.google.android.exoplayer.text.ttml.TtmlParser").asSubclass(SubtitleParser.class));
        } catch (ClassNotFoundException unused2) {
        }
        try {
            DEFAULT_PARSER_CLASSES.add(Class.forName("com.google.android.exoplayer.text.webvtt.Mp4WebvttParser").asSubclass(SubtitleParser.class));
        } catch (ClassNotFoundException unused3) {
        }
        try {
            DEFAULT_PARSER_CLASSES.add(Class.forName("com.google.android.exoplayer.text.subrip.SubripParser").asSubclass(SubtitleParser.class));
        } catch (ClassNotFoundException unused4) {
        }
        try {
            DEFAULT_PARSER_CLASSES.add(Class.forName("com.google.android.exoplayer.text.tx3g.Tx3gParser").asSubclass(SubtitleParser.class));
        } catch (ClassNotFoundException unused5) {
        }
    }

    public TextTrackRenderer(SampleSource sampleSource, TextRenderer textRenderer, Looper looper, SubtitleParser... subtitleParserArr) {
        this(new SampleSource[]{sampleSource}, textRenderer, looper, subtitleParserArr);
    }

    public TextTrackRenderer(SampleSource[] sampleSourceArr, TextRenderer textRenderer, Looper looper, SubtitleParser... subtitleParserArr) {
        super(sampleSourceArr);
        this.textRenderer = (TextRenderer) Assertions.checkNotNull(textRenderer);
        this.textRendererHandler = looper == null ? null : new Handler(looper, this);
        if (subtitleParserArr == null || subtitleParserArr.length == 0) {
            int size = DEFAULT_PARSER_CLASSES.size();
            subtitleParserArr = new SubtitleParser[size];
            for (int i = 0; i < size; i++) {
                try {
                    subtitleParserArr[i] = DEFAULT_PARSER_CLASSES.get(i).newInstance();
                } catch (IllegalAccessException e) {
                    throw new IllegalStateException("Unexpected error creating default parser", e);
                } catch (InstantiationException e2) {
                    throw new IllegalStateException("Unexpected error creating default parser", e2);
                }
            }
        }
        this.subtitleParsers = subtitleParserArr;
        this.formatHolder = new MediaFormatHolder();
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected boolean handlesTrack(MediaFormat mediaFormat) {
        return getParserIndex(mediaFormat) != -1;
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onEnabled(int i, long j, boolean z) throws ExoPlaybackException {
        super.onEnabled(i, j, z);
        this.parserIndex = getParserIndex(getFormat(i));
        HandlerThread handlerThread = new HandlerThread("textParser");
        this.parserThread = handlerThread;
        handlerThread.start();
        this.parserHelper = new SubtitleParserHelper(this.parserThread.getLooper(), this.subtitleParsers[this.parserIndex]);
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void onDiscontinuity(long j) {
        this.inputStreamEnded = false;
        this.subtitle = null;
        this.nextSubtitle = null;
        clearTextRenderer();
        SubtitleParserHelper subtitleParserHelper = this.parserHelper;
        if (subtitleParserHelper != null) {
            subtitleParserHelper.flush();
        }
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer
    protected void doSomeWork(long j, long j2, boolean z) throws ExoPlaybackException {
        if (this.nextSubtitle == null) {
            try {
                this.nextSubtitle = this.parserHelper.getAndClearResult();
            } catch (IOException e) {
                throw new ExoPlaybackException(e);
            }
        }
        if (getState() != 3) {
            return;
        }
        boolean z2 = false;
        if (this.subtitle != null) {
            long nextEventTime = getNextEventTime();
            while (nextEventTime <= j) {
                this.nextSubtitleEventIndex++;
                nextEventTime = getNextEventTime();
                z2 = true;
            }
        }
        PlayableSubtitle playableSubtitle = this.nextSubtitle;
        if (playableSubtitle != null && playableSubtitle.startTimeUs <= j) {
            PlayableSubtitle playableSubtitle2 = this.nextSubtitle;
            this.subtitle = playableSubtitle2;
            this.nextSubtitle = null;
            this.nextSubtitleEventIndex = playableSubtitle2.getNextEventTimeIndex(j);
            z2 = true;
        }
        if (z2) {
            updateTextRenderer(this.subtitle.getCues(j));
        }
        if (this.inputStreamEnded || this.nextSubtitle != null || this.parserHelper.isParsing()) {
            return;
        }
        SampleHolder sampleHolder = this.parserHelper.getSampleHolder();
        sampleHolder.clearData();
        int source = readSource(j, this.formatHolder, sampleHolder);
        if (source == -4) {
            this.parserHelper.setFormat(this.formatHolder.format);
        } else if (source == -3) {
            this.parserHelper.startParseOperation();
        } else if (source == -1) {
            this.inputStreamEnded = true;
        }
    }

    @Override // com.google.android.exoplayer.SampleSourceTrackRenderer, com.google.android.exoplayer.TrackRenderer
    protected void onDisabled() throws ExoPlaybackException {
        this.subtitle = null;
        this.nextSubtitle = null;
        this.parserThread.quit();
        this.parserThread = null;
        this.parserHelper = null;
        clearTextRenderer();
        super.onDisabled();
    }

    @Override // com.google.android.exoplayer.TrackRenderer
    protected boolean isEnded() {
        return this.inputStreamEnded && (this.subtitle == null || getNextEventTime() == Long.MAX_VALUE);
    }

    private long getNextEventTime() {
        int i = this.nextSubtitleEventIndex;
        if (i == -1 || i >= this.subtitle.getEventTimeCount()) {
            return Long.MAX_VALUE;
        }
        return this.subtitle.getEventTime(this.nextSubtitleEventIndex);
    }

    private void updateTextRenderer(List<Cue> list) {
        Handler handler = this.textRendererHandler;
        if (handler != null) {
            handler.obtainMessage(0, list).sendToTarget();
        } else {
            invokeRendererInternalCues(list);
        }
    }

    private void clearTextRenderer() {
        updateTextRenderer(Collections.emptyList());
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 0) {
            return false;
        }
        invokeRendererInternalCues((List) message.obj);
        return true;
    }

    private void invokeRendererInternalCues(List<Cue> list) {
        this.textRenderer.onCues(list);
    }

    private int getParserIndex(MediaFormat mediaFormat) {
        int i = 0;
        while (true) {
            SubtitleParser[] subtitleParserArr = this.subtitleParsers;
            if (i >= subtitleParserArr.length) {
                return -1;
            }
            if (subtitleParserArr[i].canParse(mediaFormat.mimeType)) {
                return i;
            }
            i++;
        }
    }
}
