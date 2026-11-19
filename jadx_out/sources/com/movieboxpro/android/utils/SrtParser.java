package com.movieboxpro.android.utils;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.dl7.player.utils.SRT;
import com.dueeeke.model.SrtPraseModel;
import com.dueeeke.videocontroller.R;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.player.IjkVideoView;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTimeConstants;

/* JADX INFO: loaded from: classes5.dex */
public class SrtParser {
    private static String content;
    private static ArrayList<SrtPraseModel> enSrtList;
    public static int lastEndTime;

    public static ArrayList<SRT> parseSrt(String name, ArrayList<SRT> srtList) {
        int subtitleTime;
        int subtitleTime2;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(name), 8192);
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
                        Matcher matcher = Pattern.compile("\\d+:\\d+:\\d+,\\d+").matcher(com.dl7.player.utils.StringUtils.formatContent(strArrSplit[1]));
                        if (matcher.find()) {
                            subtitleTime2 = parseSubtitleTime(matcher.group());
                            subtitleTime = matcher.find() ? parseSubtitleTime(matcher.group()) : 0;
                        } else {
                            subtitleTime = 0;
                            subtitleTime2 = 0;
                        }
                        String str = "";
                        for (int i = 2; i < strArrSplit.length; i++) {
                            str = str + strArrSplit[i] + "<br />";
                        }
                        String strSubstring = str.substring(0, str.length() - 1);
                        srt.setBeginTime(subtitleTime2);
                        srt.setEndTime(subtitleTime);
                        srt.setSrtBody(strSubstring);
                        srtList.add(srt);
                        stringBuffer.delete(0, stringBuffer.length());
                    }
                }
            }
            if (srtList.size() > 0) {
                lastEndTime = srtList.get(srtList.size() - 1).getEndTime();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return srtList;
    }

    public static List<SrtPraseModel> parseSrt(String name, List<SrtPraseModel> srtList) {
        int subtitleTime;
        int subtitleTime2;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(name), 8192);
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
                        SrtPraseModel srtPraseModel = new SrtPraseModel();
                        Matcher matcher = Pattern.compile("\\d+:\\d+:\\d+,\\d+").matcher(com.dueeeke.utils.StringUtils.formatContent(strArrSplit[1]));
                        if (matcher.find()) {
                            subtitleTime2 = parseSubtitleTime(matcher.group());
                            subtitleTime = matcher.find() ? parseSubtitleTime(matcher.group()) : 0;
                        } else {
                            subtitleTime = 0;
                            subtitleTime2 = 0;
                        }
                        String str = "";
                        for (int i = 2; i < strArrSplit.length; i++) {
                            str = str + strArrSplit[i] + "<br />";
                        }
                        String strSubstring = str.substring(0, str.length() - 1);
                        srtPraseModel.setBeginTime(subtitleTime2);
                        srtPraseModel.setEndTime(subtitleTime);
                        srtPraseModel.setSrtBody(strSubstring);
                        srtList.add(srtPraseModel);
                        stringBuffer.delete(0, stringBuffer.length());
                    }
                }
            }
            if (srtList.size() > 0) {
                lastEndTime = srtList.get(srtList.size() - 1).getEndTime();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return srtList;
    }

    public static List<SRT> parseContentSrt(String content2, List<SRT> srtList) {
        int subtitleTime;
        int subtitleTime2;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content2.getBytes())), 8192);
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
                        Matcher matcher = Pattern.compile("\\d+:\\d+:\\d+,\\d+").matcher(com.dueeeke.utils.StringUtils.formatContent(strArrSplit[1]));
                        if (matcher.find()) {
                            subtitleTime2 = parseSubtitleTime(matcher.group());
                            subtitleTime = matcher.find() ? parseSubtitleTime(matcher.group()) : 0;
                        } else {
                            subtitleTime = 0;
                            subtitleTime2 = 0;
                        }
                        String str = "";
                        for (int i = 2; i < strArrSplit.length; i++) {
                            str = str + strArrSplit[i] + "<br />";
                        }
                        String strSubstring = str.substring(0, str.length() - 1);
                        srt.setBeginTime(subtitleTime2);
                        srt.setEndTime(subtitleTime);
                        srt.setSrtBody(strSubstring);
                        srtList.add(srt);
                        stringBuffer.delete(0, stringBuffer.length());
                    }
                }
            }
            if (srtList.size() > 0) {
                lastEndTime = srtList.get(srtList.size() - 1).getEndTime();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return srtList;
    }

    public static List<SRT> parseSRTContentSrt(String content2, List<SRT> srtList) {
        int subtitleTime;
        int subtitleTime2;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(content2.getBytes())), 8192);
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
                        Matcher matcher = Pattern.compile("\\d+:\\d+:\\d+,\\d+").matcher(com.dueeeke.utils.StringUtils.formatContent(strArrSplit[1]));
                        if (matcher.find()) {
                            subtitleTime2 = parseSubtitleTime(matcher.group());
                            subtitleTime = matcher.find() ? parseSubtitleTime(matcher.group()) : 0;
                        } else {
                            subtitleTime = 0;
                            subtitleTime2 = 0;
                        }
                        String str = "";
                        for (int i = 2; i < strArrSplit.length; i++) {
                            str = str + strArrSplit[i] + "<br />";
                        }
                        String strSubstring = str.substring(0, str.length() - 1);
                        srt.setBeginTime(subtitleTime2);
                        srt.setEndTime(subtitleTime);
                        srt.setSrtBody(strSubstring);
                        srtList.add(srt);
                        stringBuffer.delete(0, stringBuffer.length());
                    }
                }
            }
            if (srtList.size() > 0) {
                lastEndTime = srtList.get(srtList.size() - 1).getEndTime();
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return srtList;
    }

    public static int parseSubtitleTime(String timeString) {
        int i;
        String[] strArrSplit = timeString.replace(",", ":").replace(".", ":").split(":");
        if (strArrSplit[3].length() == 2) {
            i = Integer.parseInt(strArrSplit[3]) * 10;
        } else {
            i = Integer.parseInt(strArrSplit[3]);
        }
        return (Integer.parseInt(strArrSplit[0]) * DateTimeConstants.MILLIS_PER_HOUR) + (Integer.parseInt(strArrSplit[1]) * DateTimeConstants.MILLIS_PER_MINUTE) + (Integer.parseInt(strArrSplit[2]) * 1000) + i;
    }

    public static int showSRT2(IjkVideoView videoView, TextView tvSrtCN, int size, int speed, ArrayList<SRT> srtList) {
        int currentPosition = videoView.getCurrentPosition() + (speed * 1000);
        if (currentPosition > lastEndTime) {
            tvSrtCN.setVisibility(8);
        }
        int i = 0;
        for (int i2 = 0; i2 < srtList.size(); i2++) {
            SRT srt = srtList.get(i2);
            if (currentPosition >= srt.getBeginTime() && currentPosition <= srt.getEndTime()) {
                String srtBody = srt.getSrtBody();
                tvSrtCN.setVisibility(0);
                if (srtBody != null) {
                    SpanUtils.with(tvSrtCN).append(Html.fromHtml(srtBody)).setFontSize(size, true).setForegroundColor(ContextCompat.getColor(App.getContext(), R.color.srt_text)).setTypeface(Typeface.DEFAULT_BOLD).setShadow(3.0f, 0.0f, 4.0f, Color.parseColor("#80000000")).create();
                }
                i = i2;
            } else if (i2 < srtList.size() - 2 && currentPosition > srt.getEndTime() && currentPosition < srtList.get(i2 + 1).getBeginTime()) {
                tvSrtCN.setVisibility(8);
            }
        }
        return i;
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
