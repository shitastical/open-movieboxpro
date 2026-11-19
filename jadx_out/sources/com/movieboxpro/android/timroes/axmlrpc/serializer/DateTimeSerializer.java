package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import java.text.SimpleDateFormat;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes5.dex */
public class DateTimeSerializer implements Serializer {
    private static final String DATETIME_FORMAT = "yyyyMMdd'T'HHmmss";
    private final SimpleDateFormat DATE_FORMATER = new SimpleDateFormat(DATETIME_FORMAT);
    private final boolean accepts_null_input;

    public DateTimeSerializer(boolean accepts_null_input) {
        this.accepts_null_input = accepts_null_input;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element content) throws XMLRPCException {
        return deserialize(XMLUtil.getOnlyTextContent(content.getChildNodes()));
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element content) throws XMLRPCException {
        return deserialize(XMLUtil.getOnlyTextContent(content.getChildNodes())) + "";
    }

    public Object deserialize(String dateStr) throws XMLRPCException {
        if (!this.accepts_null_input) {
            return "";
        }
        if (dateStr == null || dateStr.trim().length() == 0) {
            return null;
        }
        return "";
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object object) {
        return XMLUtil.makeXmlTag(SerializerHandler.TYPE_DATETIME, this.DATE_FORMATER.format(object));
    }
}
