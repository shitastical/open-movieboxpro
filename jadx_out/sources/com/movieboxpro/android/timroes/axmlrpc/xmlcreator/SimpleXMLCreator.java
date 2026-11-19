package com.movieboxpro.android.timroes.axmlrpc.xmlcreator;

/* JADX INFO: loaded from: classes5.dex */
public class SimpleXMLCreator {
    private XmlElement root;

    public void setRootElement(XmlElement element) {
        this.root = element;
    }

    public String toString() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + this.root.toString();
    }
}
