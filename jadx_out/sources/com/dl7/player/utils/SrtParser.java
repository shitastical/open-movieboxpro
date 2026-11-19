package com.dl7.player.utils;

import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import com.dl7.player.media.IjkVideoView;
import com.movieboxpro.android.utils.ShellUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTimeConstants;

/* JADX INFO: loaded from: classes4.dex */
public class SrtParser {
    private static String content;
    private static ArrayList<SRT> enSrtList;
    public static int lastEndTime;

    public static ArrayList<SRT> parseSrt2(String str, ArrayList<SRT> arrayList) {
        int subtitleTime;
        int subtitleTime2;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str), 8192);
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                if (!line.equals("")) {
                    stringBuffer.append(line).append("@");
                } else {
                    String[] strArrSplit = stringBuffer.toString().trim().split("@");
                    if (strArrSplit.length < 3) {
                        stringBuffer.delete(0, stringBuffer.length());
                    } else {
                        SRT srt = new SRT();
                        Matcher matcher = Pattern.compile("\\d+:\\d+:\\d+,\\d+").matcher(StringUtils.formatContent(strArrSplit[1]));
                        if (matcher.find()) {
                            subtitleTime2 = parseSubtitleTime(matcher.group());
                            subtitleTime = matcher.find() ? parseSubtitleTime(matcher.group()) : 0;
                        } else {
                            subtitleTime = 0;
                            subtitleTime2 = 0;
                        }
                        String str2 = "";
                        for (int i = 2; i < strArrSplit.length; i++) {
                            str2 = str2 + strArrSplit[i] + "<br />";
                        }
                        String strSubstring = str2.substring(0, str2.length() - 1);
                        srt.setBeginTime(subtitleTime2);
                        srt.setEndTime(subtitleTime);
                        srt.setSrtBody(strSubstring);
                        arrayList.add(srt);
                        stringBuffer.delete(0, stringBuffer.length());
                    }
                }
            }
            if (arrayList.size() > 0) {
                lastEndTime = arrayList.get(arrayList.size() - 1).getEndTime();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static int parseSubtitleTime(String str) {
        int i;
        String[] strArrSplit = str.replace(",", ":").replace(".", ":").split(":");
        if (strArrSplit[3].length() == 2) {
            i = Integer.parseInt(strArrSplit[3]) * 10;
        } else {
            i = Integer.parseInt(strArrSplit[3]);
        }
        return (Integer.parseInt(strArrSplit[0]) * DateTimeConstants.MILLIS_PER_HOUR) + (Integer.parseInt(strArrSplit[1]) * DateTimeConstants.MILLIS_PER_MINUTE) + (Integer.parseInt(strArrSplit[2]) * 1000) + i;
    }

    public static void parseSrt(String str, ArrayList<SRT> arrayList) {
        int i;
        int i2;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(str), 8192);
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("jiaodafu : ");
                sb.append(!line.equals(""));
                Log.d("ssssss", sb.toString());
                if (!line.equals("")) {
                    stringBuffer.append(line).append("@");
                } else {
                    String[] strArrSplit = stringBuffer.toString().trim().split("@");
                    if (strArrSplit.length < 3) {
                        stringBuffer.delete(0, stringBuffer.length());
                    } else {
                        SRT srt = new SRT();
                        try {
                            String content2 = StringUtils.formatContent(strArrSplit[1]);
                            i = ((Integer.parseInt(content2.substring(0, 2)) * DateTimeConstants.SECONDS_PER_HOUR) + (Integer.parseInt(content2.substring(3, 5)) * 60) + Integer.parseInt(content2.substring(6, 8))) * 1000;
                            try {
                                i2 = ((Integer.parseInt(content2.substring(15, 17)) * DateTimeConstants.SECONDS_PER_HOUR) + (Integer.parseInt(content2.substring(18, 20)) * 60) + Integer.parseInt(content2.substring(21, 23))) * 1000;
                            } catch (Exception e) {
                                e = e;
                                e.printStackTrace();
                                i2 = 0;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            i = 0;
                        }
                        String str2 = "";
                        for (int i3 = 2; i3 < strArrSplit.length; i3++) {
                            str2 = str2 + strArrSplit[2] + ShellUtil.COMMAND_LINE_END;
                        }
                        String strSubstring = str2.substring(0, str2.length() - 1);
                        srt.setBeginTime(i);
                        srt.setEndTime(i2);
                        srt.setSrtBody(strSubstring);
                        Log.d("ssssss", "jiaodafu : " + i);
                        Log.d("ssssss", "jiaodafu : " + i2);
                        Log.d("ssssss", "jiaodafu : " + strSubstring);
                        arrayList.add(srt);
                        stringBuffer.delete(0, stringBuffer.length());
                    }
                }
            }
            if (arrayList.size() > 0) {
                lastEndTime = arrayList.get(arrayList.size() - 1).getEndTime();
            }
            bufferedReader.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public static int showSRT2(IjkVideoView ijkVideoView, TextView textView, TextView textView2, int i, ArrayList<SRT> arrayList) {
        int currentPosition = ijkVideoView.getCurrentPosition() + (i * 1000);
        if (currentPosition > lastEndTime) {
            textView.setVisibility(8);
        }
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            SRT srt = arrayList.get(i3);
            if (currentPosition >= srt.getBeginTime() && currentPosition <= srt.getEndTime()) {
                String srtBody = srt.getSrtBody();
                textView.setVisibility(0);
                textView.setText(Html.fromHtml(srtBody));
                i2 = i3;
            } else if (i3 < arrayList.size() - 2 && currentPosition > srt.getEndTime() && currentPosition < arrayList.get(i3 + 1).getBeginTime()) {
                textView.setVisibility(8);
                textView2.setVisibility(8);
            }
        }
        return i2;
    }

    public static void showSRT(IjkVideoView ijkVideoView, TextView textView, TextView textView2, int i, ArrayList<SRT> arrayList) {
        int currentPosition = ijkVideoView.getCurrentPosition() + (i * 1000);
        if (currentPosition > lastEndTime) {
            textView.setVisibility(8);
        }
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            SRT srt = arrayList.get(i2);
            if (currentPosition >= srt.getBeginTime() && currentPosition <= srt.getEndTime()) {
                String srtBody = srt.getSrtBody();
                textView.setVisibility(0);
                textView.setText(srtBody);
                Log.e("wwwwwwwwww", "中文");
            } else if (i2 < arrayList.size() - 2 && currentPosition > srt.getEndTime() && currentPosition < arrayList.get(i2 + 1).getBeginTime()) {
                textView.setVisibility(8);
                textView2.setVisibility(8);
            }
        }
    }

    public static Boolean isGB2312(String str) {
        int i = 0;
        while (i < str.length()) {
            int i2 = i + 1;
            boolean zMatches = Pattern.matches("[一-龥]", str.substring(i, i2));
            if (!zMatches) {
                return Boolean.valueOf(zMatches);
            }
            i = i2;
        }
        return true;
    }
}
