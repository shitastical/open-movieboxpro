package com.google.android.exoplayer.text.subrip;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer.text.Cue;
import com.google.android.exoplayer.text.SubtitleParser;
import com.google.android.exoplayer.util.LongArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public final class SubripParser implements SubtitleParser {
    private static final String TAG = "SubripParser";
    private final StringBuilder textBuilder = new StringBuilder();
    private static final Pattern SUBRIP_TIMING_LINE = Pattern.compile("(\\S*)\\s*-->\\s*(\\S*)");
    private static final Pattern SUBRIP_TIMESTAMP = Pattern.compile("(?:(\\d+):)?(\\d+):(\\d+),(\\d+)");

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public boolean canParse(String str) {
        return "application/x-subrip".equals(str);
    }

    @Override // com.google.android.exoplayer.text.SubtitleParser
    public SubripSubtitle parse(byte[] bArr, int i, int i2) {
        ArrayList arrayList = new ArrayList();
        LongArray longArray = new LongArray();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i2 + i);
        parsableByteArray.setPosition(i);
        while (true) {
            String line = parsableByteArray.readLine();
            if (line != null) {
                if (line.length() != 0) {
                    try {
                        Integer.parseInt(line);
                        String line2 = parsableByteArray.readLine();
                        Matcher matcher = SUBRIP_TIMING_LINE.matcher(line2);
                        if (matcher.find()) {
                            boolean z = true;
                            longArray.add(parseTimecode(matcher.group(1)));
                            if (TextUtils.isEmpty(matcher.group(2))) {
                                z = false;
                            } else {
                                longArray.add(parseTimecode(matcher.group(2)));
                            }
                            this.textBuilder.setLength(0);
                            while (true) {
                                String line3 = parsableByteArray.readLine();
                                if (TextUtils.isEmpty(line3)) {
                                    break;
                                }
                                if (this.textBuilder.length() > 0) {
                                    this.textBuilder.append("<br>");
                                }
                                this.textBuilder.append(line3.trim());
                            }
                            arrayList.add(new Cue(Html.fromHtml(this.textBuilder.toString())));
                            if (z) {
                                arrayList.add(null);
                            }
                        } else {
                            Log.w(TAG, "Skipping invalid timing: " + line2);
                        }
                    } catch (NumberFormatException unused) {
                        Log.w(TAG, "Skipping invalid index: " + line);
                    }
                }
            } else {
                Cue[] cueArr = new Cue[arrayList.size()];
                arrayList.toArray(cueArr);
                return new SubripSubtitle(cueArr, longArray.toArray());
            }
        }
    }

    private static long parseTimecode(String str) throws NumberFormatException {
        Matcher matcher = SUBRIP_TIMESTAMP.matcher(str);
        if (!matcher.matches()) {
            throw new NumberFormatException("has invalid format");
        }
        return ((Long.parseLong(matcher.group(1)) * 3600000) + (Long.parseLong(matcher.group(2)) * 60000) + (Long.parseLong(matcher.group(3)) * 1000) + Long.parseLong(matcher.group(4))) * 1000;
    }
}
