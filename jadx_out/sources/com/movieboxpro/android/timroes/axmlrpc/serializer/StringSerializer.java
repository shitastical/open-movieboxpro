package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes5.dex */
public class StringSerializer implements Serializer {
    private boolean decodeStrings;
    private boolean encodeStrings;

    public StringSerializer(boolean encodeStrings, boolean decodeStrings) {
        this.decodeStrings = decodeStrings;
        this.encodeStrings = encodeStrings;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element content) throws XMLRPCException {
        String onlyTextContent = XMLUtil.getOnlyTextContent(content.getChildNodes());
        return this.decodeStrings ? onlyTextContent.replaceAll("&lt;", "<").replaceAll("&amp;", "&") : onlyTextContent;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element content) throws XMLRPCException {
        String onlyTextContent = XMLUtil.getOnlyTextContent(content.getChildNodes());
        return this.decodeStrings ? onlyTextContent.replaceAll("&lt;", "<").replaceAll("&amp;", "&") : onlyTextContent;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object object) {
        String string = object.toString();
        if (this.encodeStrings) {
            string = string.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll("]]>", "]]&gt;");
        }
        return XMLUtil.makeXmlTag("string", string);
    }
}
