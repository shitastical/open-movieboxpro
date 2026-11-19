package com.dueeeke.videoplayer.widget.SrtView;

import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes4.dex */
public class LrcEntry implements Comparable<LrcEntry> {
    public static final int GRAVITY_CENTER = 2;
    public static final int GRAVITY_LEFT = 1;
    public static final int GRAVITY_RIGHT = 3;
    private float offset = Float.MIN_VALUE;
    private StaticLayout staticLayout;
    private String text;
    private long time;

    private LrcEntry(long j, String str) {
        this.time = j;
        this.text = str;
    }

    void init(TextPaint textPaint, int i, int i2) {
        Layout.Alignment alignment;
        if (i2 == 1) {
            alignment = Layout.Alignment.ALIGN_NORMAL;
        } else if (i2 != 3) {
            alignment = Layout.Alignment.ALIGN_CENTER;
        } else {
            alignment = Layout.Alignment.ALIGN_OPPOSITE;
        }
        this.staticLayout = new StaticLayout(this.text, textPaint, i, alignment, 1.0f, 0.0f, false);
    }

    long getTime() {
        return this.time;
    }

    StaticLayout getStaticLayout() {
        return this.staticLayout;
    }

    int getHeight() {
        StaticLayout staticLayout = this.staticLayout;
        if (staticLayout == null) {
            return 0;
        }
        return staticLayout.getHeight();
    }

    public float getOffset() {
        return this.offset;
    }

    public void setOffset(float f) {
        this.offset = f;
    }

    @Override // java.lang.Comparable
    public int compareTo(LrcEntry lrcEntry) {
        if (lrcEntry == null) {
            return -1;
        }
        return (int) (this.time - lrcEntry.getTime());
    }

    static List<LrcEntry> parseLrc(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                List<LrcEntry> line2 = parseLine(line);
                if (line2 != null && !line2.isEmpty()) {
                    arrayList.addAll(line2);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    static List<LrcEntry> parseLrc(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split("\\n")) {
            List<LrcEntry> line = parseLine(str2);
            if (line != null && !line.isEmpty()) {
                arrayList.addAll(line);
            }
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static List<LrcEntry> parseLine(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = Pattern.compile("((\\[\\d\\d:\\d\\d\\.\\d{2,3}\\])+)([\\s\\S]*)").matcher(str.trim());
        if (!matcher.matches()) {
            return null;
        }
        String strGroup = matcher.group(1);
        String strGroup2 = matcher.group(3);
        ArrayList arrayList = new ArrayList();
        Matcher matcher2 = Pattern.compile("\\[(\\d\\d):(\\d\\d)\\.(\\d){2,3}\\]").matcher(strGroup);
        while (matcher2.find()) {
            long j = Long.parseLong(matcher2.group(1));
            long j2 = Long.parseLong(matcher2.group(2));
            long j3 = Long.parseLong(matcher2.group(3));
            long j4 = (j * 60000) + (j2 * 1000);
            if (j3 < 100) {
                j3 *= 10;
            }
            arrayList.add(new LrcEntry(j4 + j3, strGroup2));
        }
        return arrayList;
    }
}
