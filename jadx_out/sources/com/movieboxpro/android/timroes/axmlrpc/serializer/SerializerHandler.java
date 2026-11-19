package com.movieboxpro.android.timroes.axmlrpc.serializer;

import com.movieboxpro.android.timroes.axmlrpc.XMLRPCException;
import com.movieboxpro.android.timroes.axmlrpc.XMLUtil;
import com.movieboxpro.android.timroes.axmlrpc.xmlcreator.XmlElement;
import com.movieboxpro.android.utils.MLog;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import org.w3c.dom.Element;

/* JADX INFO: loaded from: classes5.dex */
public class SerializerHandler {
    public static final String TYPE_ARRAY = "array";
    public static final String TYPE_BASE64 = "base64";
    public static final String TYPE_BOOLEAN = "boolean";
    public static final String TYPE_DATETIME = "dateTime.iso8601";
    public static final String TYPE_DOUBLE = "double";
    public static final String TYPE_INT = "int";
    public static final String TYPE_INT2 = "i4";
    public static final String TYPE_LONG = "i8";
    public static final String TYPE_NULL = "nil";
    public static final String TYPE_STRING = "string";
    public static final String TYPE_STRUCT = "struct";
    private ArraySerializer array;
    private Base64Serializer base64;
    private BooleanSerializer bool;
    private DateTimeSerializer datetime;
    private int flags;
    private DoubleSerializer floating;
    private IntSerializer integer;
    private LongSerializer long8;
    private NullSerializer nil;
    private StringSerializer string;
    private StructSerializer struct;

    public SerializerHandler() {
        this(8192);
    }

    public SerializerHandler(int flags) {
        this.bool = new BooleanSerializer();
        this.integer = new IntSerializer();
        this.long8 = new LongSerializer();
        this.floating = new DoubleSerializer();
        this.base64 = new Base64Serializer();
        this.nil = new NullSerializer();
        this.flags = flags;
        this.string = new StringSerializer((flags & 4096) == 0, (flags & 2048) == 0);
        this.struct = new StructSerializer(this);
        this.array = new ArraySerializer(this);
        this.datetime = new DateTimeSerializer((flags & 16384) != 0);
    }

    public Object deserialize(Element element) throws XMLRPCException {
        Serializer serializer;
        if (!"value".equals(element.getNodeName())) {
            throw new XMLRPCException("Value tag is missing around value.");
        }
        if (!XMLUtil.hasChildElement(element.getChildNodes())) {
            if ((this.flags & 256) != 0) {
                return this.string.deserialize(element);
            }
            throw new XMLRPCException("Missing type element inside of value element.");
        }
        Element onlyChildElement = XMLUtil.getOnlyChildElement(element.getChildNodes());
        String nodeName = ((this.flags & 512) == 0 || onlyChildElement.getLocalName() == null) ? onlyChildElement.getNodeName() : onlyChildElement.getLocalName();
        if ((this.flags & 8) != 0 && TYPE_NULL.equals(nodeName)) {
            serializer = this.nil;
        } else if ("string".equals(nodeName)) {
            serializer = this.string;
        } else if ("boolean".equals(nodeName)) {
            serializer = this.bool;
        } else if (TYPE_DOUBLE.equals(nodeName)) {
            serializer = this.floating;
        } else if (TYPE_INT.equals(nodeName) || TYPE_INT2.equals(nodeName)) {
            serializer = this.integer;
        } else if (TYPE_DATETIME.equals(nodeName)) {
            serializer = this.datetime;
        } else if (TYPE_LONG.equals(nodeName)) {
            if ((this.flags & 2) != 0) {
                serializer = this.long8;
            } else {
                throw new XMLRPCException("8 byte integer is not in the specification. You must use FLAGS_8BYTE_INT to enable the i8 tag.");
            }
        } else if (TYPE_STRUCT.equals(nodeName)) {
            serializer = this.struct;
        } else if (TYPE_ARRAY.equals(nodeName)) {
            serializer = this.array;
        } else if (TYPE_BASE64.equals(nodeName)) {
            serializer = this.base64;
        } else {
            throw new XMLRPCException("No deserializer found for type '" + nodeName + "'.");
        }
        return serializer.deserialize(onlyChildElement).toString();
    }

    public Object deserialize2(Element element) throws XMLRPCException {
        Serializer serializer;
        if (!"value".equals(element.getNodeName())) {
            throw new XMLRPCException("Value tag is missing around value.");
        }
        if (!XMLUtil.hasChildElement(element.getChildNodes())) {
            if ((this.flags & 256) != 0) {
                return this.string.deserialize(element);
            }
            throw new XMLRPCException("Missing type element inside of value element.");
        }
        Element onlyChildElement = XMLUtil.getOnlyChildElement(element.getChildNodes());
        String nodeName = ((this.flags & 512) == 0 || onlyChildElement.getLocalName() == null) ? onlyChildElement.getNodeName() : onlyChildElement.getLocalName();
        if ((this.flags & 8) != 0 && TYPE_NULL.equals(nodeName)) {
            serializer = this.nil;
        } else if ("string".equals(nodeName)) {
            serializer = this.string;
        } else if ("boolean".equals(nodeName)) {
            serializer = this.bool;
        } else if (TYPE_DOUBLE.equals(nodeName)) {
            serializer = this.floating;
        } else if (TYPE_INT.equals(nodeName) || TYPE_INT2.equals(nodeName)) {
            serializer = this.integer;
        } else if (TYPE_DATETIME.equals(nodeName)) {
            serializer = this.datetime;
        } else if (TYPE_LONG.equals(nodeName)) {
            if ((this.flags & 2) != 0) {
                serializer = this.long8;
            } else {
                throw new XMLRPCException("8 byte integer is not in the specification. You must use FLAGS_8BYTE_INT to enable the i8 tag.");
            }
        } else if (TYPE_STRUCT.equals(nodeName)) {
            serializer = this.struct;
        } else if (TYPE_ARRAY.equals(nodeName)) {
            serializer = this.array;
        } else if (TYPE_BASE64.equals(nodeName)) {
            serializer = this.base64;
        } else {
            throw new XMLRPCException("No deserializer found for type '" + nodeName + "'.");
        }
        MLog.d("SSS", "SHIFOU 1 :" + serializer.deserialize(onlyChildElement).toString());
        return serializer.deserialize(onlyChildElement).toString();
    }

    public XmlElement serialize(Object object) throws XMLRPCException {
        Serializer serializer;
        int i = this.flags;
        if ((i & 8) != 0 && object == null) {
            serializer = this.nil;
        } else if (object instanceof String) {
            serializer = this.string;
        } else if (object instanceof Boolean) {
            serializer = this.bool;
        } else if ((object instanceof Double) || (object instanceof Float) || (object instanceof BigDecimal)) {
            serializer = this.floating;
        } else if ((object instanceof Integer) || (object instanceof Short) || (object instanceof Byte)) {
            serializer = this.integer;
        } else if (object instanceof Long) {
            if ((i & 2) != 0) {
                serializer = this.long8;
            } else {
                long jLongValue = ((Long) object).longValue();
                if (jLongValue > 2147483647L || jLongValue < -2147483648L) {
                    throw new XMLRPCException("FLAGS_8BYTE_INT must be set, if values outside the 4 byte integer range should be transfered.");
                }
                serializer = this.integer;
            }
        } else if (object instanceof Date) {
            serializer = this.datetime;
        } else if (object instanceof Calendar) {
            object = ((Calendar) object).getTime();
            serializer = this.datetime;
        } else if (object instanceof Map) {
            serializer = this.struct;
        } else if (object instanceof byte[]) {
            byte[] bArr = (byte[]) object;
            int length = bArr.length;
            Byte[] bArr2 = new Byte[length];
            for (int i2 = 0; i2 < length; i2++) {
                bArr2[i2] = new Byte(bArr[i2]);
            }
            serializer = this.base64;
            object = bArr2;
        } else if (object instanceof Byte[]) {
            serializer = this.base64;
        } else {
            if (!(object instanceof Iterable) && !(object instanceof Object[])) {
                if (object != null) {
                    throw new XMLRPCException("No serializer found for type '" + object.getClass().getName() + "'.");
                }
                throw new XMLRPCException("No serializer found for type ''.");
            }
            serializer = this.array;
        }
        return serializer.serialize(object);
    }
}
