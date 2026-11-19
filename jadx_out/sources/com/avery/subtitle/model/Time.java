package com.avery.subtitle.model;

import org.joda.time.DateTimeConstants;

/* JADX INFO: loaded from: classes4.dex */
public class Time {
    public int mseconds;

    public Time(String str, String str2) {
        if (str.equalsIgnoreCase("hh:mm:ss,ms")) {
            int i = Integer.parseInt(str2.substring(0, 2));
            this.mseconds = Integer.parseInt(str2.substring(9, 12)) + (Integer.parseInt(str2.substring(6, 8)) * 1000) + (Integer.parseInt(str2.substring(3, 5)) * DateTimeConstants.MILLIS_PER_MINUTE) + (i * DateTimeConstants.MILLIS_PER_HOUR);
            return;
        }
        if (str.equalsIgnoreCase("h:mm:ss.cs")) {
            int i2 = Integer.parseInt(str2.substring(0, 1));
            this.mseconds = (Integer.parseInt(str2.substring(8, 10)) * 10) + (Integer.parseInt(str2.substring(5, 7)) * 1000) + (Integer.parseInt(str2.substring(2, 4)) * DateTimeConstants.MILLIS_PER_MINUTE) + (i2 * DateTimeConstants.MILLIS_PER_HOUR);
            return;
        }
        if (str.equalsIgnoreCase("h:m:s:f/fps")) {
            String[] strArrSplit = str2.split("/");
            float f = Float.parseFloat(strArrSplit[1]);
            String[] strArrSplit2 = strArrSplit[0].split(":");
            int i3 = Integer.parseInt(strArrSplit2[0]);
            this.mseconds = ((int) ((Integer.parseInt(strArrSplit2[3]) * 1000) / f)) + (Integer.parseInt(strArrSplit2[2]) * 1000) + (Integer.parseInt(strArrSplit2[1]) * DateTimeConstants.MILLIS_PER_MINUTE) + (i3 * DateTimeConstants.MILLIS_PER_HOUR);
        }
    }

    public Time(int i) {
        this.mseconds = i;
    }

    public String getTime(String str) {
        StringBuilder sb = new StringBuilder();
        if (str.equalsIgnoreCase("hh:mm:ss,ms")) {
            String strValueOf = String.valueOf(this.mseconds / DateTimeConstants.MILLIS_PER_HOUR);
            if (strValueOf.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf);
            sb.append(':');
            String strValueOf2 = String.valueOf((this.mseconds / DateTimeConstants.MILLIS_PER_MINUTE) % 60);
            if (strValueOf2.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf2);
            sb.append(':');
            String strValueOf3 = String.valueOf((this.mseconds / 1000) % 60);
            if (strValueOf3.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf3);
            sb.append(',');
            String strValueOf4 = String.valueOf(this.mseconds % 1000);
            if (strValueOf4.length() == 1) {
                sb.append("00");
            } else if (strValueOf4.length() == 2) {
                sb.append('0');
            }
            sb.append(strValueOf4);
        } else if (str.equalsIgnoreCase("h:mm:ss.cs")) {
            String strValueOf5 = String.valueOf(this.mseconds / DateTimeConstants.MILLIS_PER_HOUR);
            if (strValueOf5.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf5);
            sb.append(':');
            String strValueOf6 = String.valueOf((this.mseconds / DateTimeConstants.MILLIS_PER_MINUTE) % 60);
            if (strValueOf6.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf6);
            sb.append(':');
            String strValueOf7 = String.valueOf((this.mseconds / 1000) % 60);
            if (strValueOf7.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf7);
            sb.append('.');
            String strValueOf8 = String.valueOf((this.mseconds / 10) % 100);
            if (strValueOf8.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf8);
        } else if (str.startsWith("hhmmssff/")) {
            float f = Float.parseFloat(str.split("/")[1]);
            String strValueOf9 = String.valueOf(this.mseconds / DateTimeConstants.MILLIS_PER_HOUR);
            if (strValueOf9.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf9);
            String strValueOf10 = String.valueOf((this.mseconds / DateTimeConstants.MILLIS_PER_MINUTE) % 60);
            if (strValueOf10.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf10);
            String strValueOf11 = String.valueOf((this.mseconds / 1000) % 60);
            if (strValueOf11.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf11);
            String strValueOf12 = String.valueOf(((this.mseconds % 1000) * ((int) f)) / 1000);
            if (strValueOf12.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf12);
        } else if (str.startsWith("h:m:s:f/")) {
            float f2 = Float.parseFloat(str.split("/")[1]);
            sb.append(String.valueOf(this.mseconds / DateTimeConstants.MILLIS_PER_HOUR));
            sb.append(':');
            sb.append(String.valueOf((this.mseconds / DateTimeConstants.MILLIS_PER_MINUTE) % 60));
            sb.append(':');
            sb.append(String.valueOf((this.mseconds / 1000) % 60));
            sb.append(':');
            sb.append(String.valueOf(((this.mseconds % 1000) * ((int) f2)) / 1000));
        } else if (str.startsWith("hh:mm:ss:ff/")) {
            float f3 = Float.parseFloat(str.split("/")[1]);
            String strValueOf13 = String.valueOf(this.mseconds / DateTimeConstants.MILLIS_PER_HOUR);
            if (strValueOf13.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf13);
            sb.append(':');
            String strValueOf14 = String.valueOf((this.mseconds / DateTimeConstants.MILLIS_PER_MINUTE) % 60);
            if (strValueOf14.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf14);
            sb.append(':');
            String strValueOf15 = String.valueOf((this.mseconds / 1000) % 60);
            if (strValueOf15.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf15);
            sb.append(':');
            String strValueOf16 = String.valueOf(((this.mseconds % 1000) * ((int) f3)) / 1000);
            if (strValueOf16.length() == 1) {
                sb.append('0');
            }
            sb.append(strValueOf16);
        }
        return sb.toString();
    }
}
