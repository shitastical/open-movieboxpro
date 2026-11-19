package com.movieboxpro.android.timroes.axmlrpc.xmlcreator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class XmlElement {
    private List<XmlElement> children = new ArrayList();
    private String content;
    private String name;

    public XmlElement(String name) {
        this.name = name;
    }

    public void addChildren(XmlElement element) {
        this.children.add(element);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String str = this.content;
        if (str != null && str.length() > 0) {
            sb.append("\n<");
            sb.append(this.name);
            sb.append(">");
            sb.append(this.content);
            sb.append("</");
            sb.append(this.name);
            sb.append(">\n");
            return sb.toString();
        }
        if (this.children.size() > 0) {
            sb.append("\n<");
            sb.append(this.name);
            sb.append(">");
            Iterator<XmlElement> it = this.children.iterator();
            while (it.hasNext()) {
                sb.append(it.next().toString());
            }
            sb.append("</");
            sb.append(this.name);
            sb.append(">\n");
            return sb.toString();
        }
        sb.append("\n<");
        sb.append(this.name);
        sb.append("/>\n");
        return sb.toString();
    }
}
