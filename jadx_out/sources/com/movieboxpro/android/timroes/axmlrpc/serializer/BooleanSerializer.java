package com.movieboxpro.android.timroes.axmlrpc.serializer;

import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes5.dex */
public class BooleanSerializer implements Serializer {
    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element content) throws XMLRPCException {
        return XMLUtil.getOnlyTextContent(content.getChildNodes()).equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element content) throws XMLRPCException {
        return XMLUtil.getOnlyTextContent(content.getChildNodes()).equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE) ? "true" : "false";
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object object) {
        return XMLUtil.makeXmlTag("boolean", ((Boolean) object).booleanValue() ? IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE : "0");
    }
}
