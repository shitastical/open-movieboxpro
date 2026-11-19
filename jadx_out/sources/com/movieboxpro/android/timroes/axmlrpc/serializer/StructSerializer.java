package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLRPCRuntimeException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import java.util.Map;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/* JADX INFO: loaded from: classes5.dex */
public class StructSerializer implements Serializer {
    private static final String STRUCT_MEMBER = "member";
    private static final String STRUCT_NAME = "name";
    private static final String STRUCT_VALUE = "value";
    private final SerializerHandler serializerHandler;

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public String deserialize2(Element content) throws XMLRPCException {
        return null;
    }

    public StructSerializer(SerializerHandler serializerHandler) {
        this.serializerHandler = serializerHandler;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public Object deserialize(Element content) throws XMLRPCException {
        JSONObject jSONObject = new JSONObject();
        for (int i = 0; i < content.getChildNodes().getLength(); i++) {
            Node nodeItem = content.getChildNodes().item(i);
            if ((nodeItem.getNodeType() != 3 || nodeItem.getNodeValue().trim().length() > 0) && nodeItem.getNodeType() != 8) {
                if (nodeItem.getNodeType() != 1 || !STRUCT_MEMBER.equals(nodeItem.getNodeName())) {
                    throw new XMLRPCException("Only struct members allowed within a struct.");
                }
                String onlyTextContent = null;
                Object objDeserialize = null;
                for (int i2 = 0; i2 < nodeItem.getChildNodes().getLength(); i2++) {
                    Node nodeItem2 = nodeItem.getChildNodes().item(i2);
                    if ((nodeItem2.getNodeType() != 3 || nodeItem2.getNodeValue().trim().length() > 0) && nodeItem2.getNodeType() != 8) {
                        if ("name".equals(nodeItem2.getNodeName())) {
                            if (onlyTextContent != null) {
                                throw new XMLRPCException("Name of a struct member cannot be set twice.");
                            }
                            onlyTextContent = XMLUtil.getOnlyTextContent(nodeItem2.getChildNodes());
                        } else {
                            if (nodeItem2.getNodeType() != 1 || !"value".equals(nodeItem2.getNodeName())) {
                                throw new XMLRPCException("A struct member must only contain one name and one value.");
                            }
                            if (objDeserialize != null) {
                                throw new XMLRPCException("Value of a struct member cannot be set twice.");
                            }
                            objDeserialize = this.serializerHandler.deserialize((Element) nodeItem2);
                        }
                    }
                }
                jSONObject.put(onlyTextContent, objDeserialize);
            }
        }
        return jSONObject;
    }

    @Override // com.movieboxpro.android.timroes.axmlrpc.serializer.Serializer
    public XmlElement serialize(Object object) {
        XmlElement xmlElement = new XmlElement(SerializerHandler.TYPE_STRUCT);
        try {
            for (Map.Entry entry : ((Map) object).entrySet()) {
                XmlElement xmlElement2 = new XmlElement(STRUCT_MEMBER);
                XmlElement xmlElement3 = new XmlElement("name");
                XmlElement xmlElement4 = new XmlElement("value");
                xmlElement3.setContent((String) entry.getKey());
                xmlElement4.addChildren(this.serializerHandler.serialize(entry.getValue()));
                xmlElement2.addChildren(xmlElement3);
                xmlElement2.addChildren(xmlElement4);
                xmlElement.addChildren(xmlElement2);
            }
            return xmlElement;
        } catch (XMLRPCException e) {
            throw new XMLRPCRuntimeException(e);
        }
    }
}
