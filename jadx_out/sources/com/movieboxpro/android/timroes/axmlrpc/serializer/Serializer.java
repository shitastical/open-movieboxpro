package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes5.dex */
public interface Serializer {
    Object deserialize(Element content) throws XMLRPCException;

    String deserialize2(Element content) throws XMLRPCException;

    XmlElement serialize(Object object);
}
