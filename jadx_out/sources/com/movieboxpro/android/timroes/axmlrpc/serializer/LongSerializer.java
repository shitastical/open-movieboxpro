package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes5.dex */
class LongSerializer implements Serializer {
    LongSerializer() {
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element content) throws XMLRPCException {
        return Long.valueOf(Long.parseLong(XMLUtil.getOnlyTextContent(content.getChildNodes())));
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element content) throws XMLRPCException {
        return Long.parseLong(XMLUtil.getOnlyTextContent(content.getChildNodes())) + "";
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object object) {
        return XMLUtil.makeXmlTag(SerializerHandler.TYPE_LONG, ((Long) object).toString());
    }
}
