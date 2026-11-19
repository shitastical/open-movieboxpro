package com.google.android.exoplayer.text.webvtt;

import android.text.TextUtils;
import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public final class WebvttParser implements SubtitleParser {
    private final WebvttCueParser cueParser = new WebvttCueParser();
    private final ParsableByteArray parsableWebvttData = new ParsableByteArray();
    private final WebvttCue.Builder webvttCueBuilder = new WebvttCue.Builder();

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public final boolean canParse(String str) {
        return "text/vtt".equals(str);
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public final WebvttSubtitle parse(byte[] bArr, int i, int i2) throws ParserException {
        this.parsableWebvttData.reset(bArr, i2 + i);
        this.parsableWebvttData.setPosition(i);
        this.webvttCueBuilder.reset();
        WebvttParserUtil.validateWebvttHeaderLine(this.parsableWebvttData);
        while (!TextUtils.isEmpty(this.parsableWebvttData.readLine())) {
        }
        ArrayList arrayList = new ArrayList();
        while (this.cueParser.parseNextValidCue(this.parsableWebvttData, this.webvttCueBuilder)) {
            arrayList.add(this.webvttCueBuilder.build());
            this.webvttCueBuilder.reset();
        }
        return new WebvttSubtitle(arrayList);
    }
}
