package com.google.android.exoplayer.text.tx3g;

import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.Subtitle;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.ParsableByteArray;

/* JADX INFO: loaded from: classes4.dex */
public final class Tx3gParser implements SubtitleParser {
    private final ParsableByteArray parsableByteArray = new ParsableByteArray();

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        return "application/x-quicktime-tx3g".equals(str);
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public Subtitle parse(byte[] bArr, int i, int i2) {
        this.parsableByteArray.reset(bArr, i2);
        int unsignedShort = this.parsableByteArray.readUnsignedShort();
        if (unsignedShort == 0) {
            return Tx3gSubtitle.EMPTY;
        }
        return new Tx3gSubtitle(new Cue(this.parsableByteArray.readString(unsignedShort)));
    }
}
