package com.movieboxpro.android.timroes.axmlrpc;

import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* JADX INFO: loaded from: classes5.dex */
public class XMLUtil {
    private XMLUtil() {
    }

    public static Element getOnlyChildElement(NodeList list) throws XMLRPCException {
        Element element = null;
        for (int i = 0; i < list.getLength(); i++) {
            Node nodeItem = list.item(i);
            if ((nodeItem.getNodeType() != 3 || nodeItem.getNodeValue().trim().length() > 0) && nodeItem.getNodeType() != 8) {
                if (nodeItem.getNodeType() != 1) {
                    throw new XMLRPCException("Only element nodes allowed.");
                }
                if (element != null) {
                    throw new XMLRPCException("Element has more than one children.");
                }
                element = (Element) nodeItem;
            }
        }
        return element;
    }

    public static String getOnlyTextContent(NodeList list) throws XMLRPCException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.getLength(); i++) {
            Node nodeItem = list.item(i);
            if (nodeItem.getNodeType() != 8) {
                if (nodeItem.getNodeType() != 3) {
                    throw new XMLRPCException("Element must contain only text elements.");
                }
                sb.append(nodeItem.getNodeValue());
            }
        }
        return sb.toString();
    }

    public static boolean hasChildElement(NodeList list) {
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeType() == 1) {
                return true;
            }
        }
        return false;
    }

    public static XmlElement makeXmlTag(String type, String content) {
        XmlElement xmlElement = new XmlElement(type);
        xmlElement.setContent(content);
        return xmlElement;
    }
}
