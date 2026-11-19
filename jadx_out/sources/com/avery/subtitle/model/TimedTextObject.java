package com.avery.subtitle.model;

import com.avery.subtitle.format.FormatASS;
import com.avery.subtitle.format.FormatSCC;
import com.avery.subtitle.format.FormatSRT;
import com.avery.subtitle.format.FormatSTL;
import com.avery.subtitle.format.FormatTTML;
import java.util.Hashtable;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes4.dex */
public class TimedTextObject {
    public String title = "";
    public String description = "";
    public String copyrigth = "";
    public String author = "";
    public String fileName = "";
    public String language = "";
    public boolean useASSInsteadOfSSA = true;
    public int offset = 0;
    public boolean built = false;
    public Hashtable<String, Style> styling = new Hashtable<>();
    public Hashtable<String, Region> layout = new Hashtable<>();
    public TreeMap<Integer, Subtitle> captions = new TreeMap<>();
    public String warnings = "List of non fatal errors produced during parsing:\n\n";

    public String[] toSRT() {
        return new FormatSRT().toFile(this);
    }

    public String[] toASS() {
        return new FormatASS().toFile(this);
    }

    public byte[] toSTL() {
        return new FormatSTL().toFile(this);
    }

    public String[] toSCC() {
        return new FormatSCC().toFile(this);
    }

    public String[] toTTML() {
        return new FormatTTML().toFile(this);
    }

    public void cleanUnusedStyles() {
        Hashtable<String, Style> hashtable = new Hashtable<>();
        for (Subtitle subtitle : this.captions.values()) {
            if (subtitle.style != null) {
                String str = subtitle.style.iD;
                if (!hashtable.containsKey(str)) {
                    hashtable.put(str, subtitle.style);
                }
            }
        }
        this.styling = hashtable;
    }
}
