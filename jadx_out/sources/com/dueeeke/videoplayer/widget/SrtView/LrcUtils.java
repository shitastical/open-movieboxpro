package com.dueeeke.videoplayer.widget.SrtView;

import com.google.android.material.timepicker.TimeModel;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class LrcUtils {
    public static String formatTime(long j) {
        int i = (int) ((j / 1000) % 60);
        return String.format(Locale.getDefault(), TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf((int) (j / 60000))) + ":" + String.format(Locale.getDefault(), TimeModel.ZERO_LEADING_NUMBER_FORMAT, Integer.valueOf(i));
    }
}
