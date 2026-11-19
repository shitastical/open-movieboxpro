package com.movieboxpro.android.timroes.axmlrpc;

import com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.SimpleXMLCreator;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;

/* JADX INFO: loaded from: classes5.dex */
public class Call {
    private String method;
    private Object[] params;
    private final SerializerHandler serializerHandler;

    public Call(SerializerHandler serializerHandler, String method) {
        this(serializerHandler, method, null);
    }

    public Call(SerializerHandler serializerHandler, String method, Object[] params) {
        this.method = method;
        this.params = params;
        this.serializerHandler = serializerHandler;
    }

    public String getXML(boolean debugMode) throws XMLRPCException, NullPointerException {
        SimpleXMLCreator simpleXMLCreator = new SimpleXMLCreator();
        XmlElement xmlElement = new XmlElement("methodCall");
        simpleXMLCreator.setRootElement(xmlElement);
        XmlElement xmlElement2 = new XmlElement("methodName");
        xmlElement2.setContent(this.method);
        xmlElement.addChildren(xmlElement2);
        Object[] objArr = this.params;
        if (objArr != null && objArr.length > 0) {
            XmlElement xmlElement3 = new XmlElement("params");
            xmlElement.addChildren(xmlElement3);
            for (Object obj : this.params) {
                xmlElement3.addChildren(getXMLParam(obj));
            }
        }
        return simpleXMLCreator.toString();
    }

    private XmlElement getXMLParam(Object o) throws XMLRPCException, NullPointerException {
        XmlElement xmlElement = new XmlElement("param");
        XmlElement xmlElement2 = new XmlElement("value");
        xmlElement.addChildren(xmlElement2);
        xmlElement2.addChildren(this.serializerHandler.serialize(o));
        return xmlElement;
    }
}
