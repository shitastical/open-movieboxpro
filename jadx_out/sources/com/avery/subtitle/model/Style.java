package com.avery.subtitle.model;

/* JADX INFO: loaded from: classes4.dex */
public class Style {
    private static int styleCounter;
    public String backgroundColor;
    public boolean bold;
    public String color;
    public String font;
    public String fontSize;
    public String iD;
    public boolean italic;
    public String textAlign;
    public boolean underline;

    public Style(String str) {
        this.textAlign = "";
        this.iD = str;
    }

    public Style(String str, Style style) {
        this.textAlign = "";
        this.iD = str;
        this.font = style.font;
        this.fontSize = style.fontSize;
        this.color = style.color;
        this.backgroundColor = style.backgroundColor;
        this.textAlign = style.textAlign;
        this.italic = style.italic;
        this.underline = style.underline;
        this.bold = style.bold;
    }

    public static String getRGBValue(String str, String str2) {
        if (str.equalsIgnoreCase("name")) {
            if (str2.equals("transparent")) {
                return "00000000";
            }
            if (str2.equals("black")) {
                return "000000ff";
            }
            if (str2.equals("silver")) {
                return "c0c0c0ff";
            }
            if (str2.equals("gray")) {
                return "808080ff";
            }
            if (str2.equals("white")) {
                return "ffffffff";
            }
            if (str2.equals("maroon")) {
                return "800000ff";
            }
            if (str2.equals("red")) {
                return "ff0000ff";
            }
            if (str2.equals("purple")) {
                return "800080ff";
            }
            if (str2.equals("fuchsia")) {
                return "ff00ffff";
            }
            if (str2.equals("magenta")) {
                return "ff00ffff ";
            }
            if (str2.equals("green")) {
                return "008000ff";
            }
            if (str2.equals("lime")) {
                return "00ff00ff";
            }
            if (str2.equals("olive")) {
                return "808000ff";
            }
            if (str2.equals("yellow")) {
                return "ffff00ff";
            }
            if (str2.equals("navy")) {
                return "000080ff";
            }
            if (str2.equals("blue")) {
                return "0000ffff";
            }
            if (str2.equals("teal")) {
                return "008080ff";
            }
            if (str2.equals("aqua")) {
                return "00ffffff";
            }
            if (str2.equals("cyan")) {
                return "00ffffff ";
            }
        } else {
            if (str.equalsIgnoreCase("&HBBGGRR")) {
                return str2.substring(6) + str2.substring(4, 5) + str2.substring(2, 3) + "ff";
            }
            if (str.equalsIgnoreCase("&HAABBGGRR")) {
                return str2.substring(8) + str2.substring(6, 7) + str2.substring(4, 5) + str2.substring(2, 3);
            }
            if (str.equalsIgnoreCase("decimalCodedBBGGRR")) {
                String hexString = Integer.toHexString(Integer.parseInt(str2));
                while (hexString.length() < 6) {
                    hexString = "0" + hexString;
                }
                return hexString.substring(4) + hexString.substring(2, 4) + hexString.substring(0, 2) + "ff";
            }
            if (str.equalsIgnoreCase("decimalCodedAABBGGRR")) {
                String hexString2 = Long.toHexString(Long.parseLong(str2));
                while (hexString2.length() < 8) {
                    hexString2 = "0" + hexString2;
                }
                return hexString2.substring(6) + hexString2.substring(4, 6) + hexString2.substring(2, 4) + hexString2.substring(0, 2);
            }
        }
        return null;
    }

    public static String defaultID() {
        StringBuilder sb = new StringBuilder("default");
        int i = styleCounter;
        styleCounter = i + 1;
        sb.append(i);
        return sb.toString();
    }
}
