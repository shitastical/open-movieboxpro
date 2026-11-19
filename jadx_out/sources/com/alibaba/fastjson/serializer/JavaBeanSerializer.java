package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class JavaBeanSerializer extends SerializeFilterable implements ObjectSerializer {
    protected SerializeBeanInfo beanInfo;
    protected final FieldSerializer[] getters;
    private volatile transient long[] hashArray;
    private volatile transient short[] hashArrayMapping;
    protected final FieldSerializer[] sortedGetters;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public JavaBeanSerializer(Class<?> cls) {
        this(cls, (Map<String, String>) null);
    }

    public JavaBeanSerializer(Class<?> cls, String... strArr) {
        this(cls, createAliasMap(strArr));
    }

    static Map<String, String> createAliasMap(String... strArr) {
        HashMap map = new HashMap();
        for (String str : strArr) {
            map.put(str, str);
        }
        return map;
    }

    public Class<?> getType() {
        return this.beanInfo.beanType;
    }

    public JavaBeanSerializer(Class<?> cls, Map<String, String> map) {
        this(TypeUtils.buildBeanInfo(cls, map, null));
    }

    public JavaBeanSerializer(SerializeBeanInfo serializeBeanInfo) {
        this.beanInfo = serializeBeanInfo;
        this.sortedGetters = new FieldSerializer[serializeBeanInfo.sortedFields.length];
        int i = 0;
        int i2 = 0;
        while (true) {
            FieldSerializer[] fieldSerializerArr = this.sortedGetters;
            if (i2 >= fieldSerializerArr.length) {
                break;
            }
            fieldSerializerArr[i2] = new FieldSerializer(serializeBeanInfo.beanType, serializeBeanInfo.sortedFields[i2]);
            i2++;
        }
        if (serializeBeanInfo.fields == serializeBeanInfo.sortedFields) {
            this.getters = this.sortedGetters;
            return;
        }
        this.getters = new FieldSerializer[serializeBeanInfo.fields.length];
        while (true) {
            FieldSerializer[] fieldSerializerArr2 = this.getters;
            if (i >= fieldSerializerArr2.length) {
                return;
            }
            fieldSerializerArr2[i] = getFieldSerializer(serializeBeanInfo.fields[i].name);
            i++;
        }
    }

    public void writeDirectNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArray(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    public void writeAsArrayNonContext(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i);
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    public void writeNoneASM(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws Throwable {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0141 A[Catch: Exception -> 0x00e5, all -> 0x03f3, TRY_ENTER, TryCatch #3 {all -> 0x03f3, blocks: (B:60:0x00d6, B:63:0x00e0, B:72:0x00f7, B:74:0x00fd, B:80:0x010b, B:82:0x0111, B:84:0x011b, B:90:0x0126, B:96:0x0136, B:101:0x0141, B:104:0x014d, B:106:0x0154, B:94:0x012e), top: B:362:0x00d6 }] */
    /* JADX WARN: Code duplicated, block: B:111:0x0175 A[Catch: all -> 0x03d6, Exception -> 0x03da, TRY_ENTER, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:113:0x017d A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:116:0x018e A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:118:0x0199 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:125:0x01a7 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:127:0x01ac A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:128:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:130:0x01b6 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:132:0x01c1 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:139:0x01cf A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:141:0x01d4  */
    /* JADX WARN: Code duplicated, block: B:142:0x01d7 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:144:0x01df A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:146:0x01ea A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:153:0x01f8 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:155:0x01fd A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:156:0x0202 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:158:0x020a A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:160:0x0215 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:167:0x0223 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:169:0x0228 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:170:0x022d  */
    /* JADX WARN: Code duplicated, block: B:171:0x022f A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:176:0x023f A[DONT_INVERT, PHI: r1
      0x023f: PHI (r1v34 java.lang.Object) = 
      (r1v33 java.lang.Object)
      (r1v33 java.lang.Object)
      (r1v33 java.lang.Object)
      (r1v33 java.lang.Object)
      (r1v38 java.lang.Object)
      (r1v33 java.lang.Object)
      (r1v39 java.lang.Object)
      (r1v33 java.lang.Object)
      (r1v40 java.lang.Object)
      (r1v33 java.lang.Object)
      (r1v41 java.lang.Object)
      (r1v33 java.lang.Object)
     binds: [B:110:0x0173, B:170:0x022d, B:172:0x0231, B:174:0x023b, B:169:0x0228, B:168:0x0226, B:155:0x01fd, B:154:0x01fb, B:141:0x01d4, B:140:0x01d2, B:127:0x01ac, B:126:0x01aa] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:177:0x0241 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:232:0x02e7  */
    /* JADX WARN: Code duplicated, block: B:233:0x02e9 A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:235:0x02ed A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:242:0x030c A[Catch: all -> 0x03d6, Exception -> 0x03da, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:244:0x0311 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:245:0x0313 A[Catch: all -> 0x03d6, Exception -> 0x03da, TRY_LEAVE, TryCatch #14 {Exception -> 0x03da, all -> 0x03d6, blocks: (B:108:0x016d, B:111:0x0175, B:113:0x017d, B:116:0x018e, B:118:0x0199, B:120:0x019d, B:123:0x01a4, B:125:0x01a7, B:127:0x01ac, B:130:0x01b6, B:132:0x01c1, B:134:0x01c5, B:137:0x01cc, B:139:0x01cf, B:142:0x01d7, B:144:0x01df, B:146:0x01ea, B:148:0x01ee, B:151:0x01f5, B:153:0x01f8, B:155:0x01fd, B:156:0x0202, B:158:0x020a, B:160:0x0215, B:162:0x0219, B:165:0x0220, B:167:0x0223, B:169:0x0228, B:171:0x022f, B:173:0x0233, B:177:0x0241, B:179:0x0245, B:181:0x024e, B:183:0x0259, B:185:0x025f, B:187:0x0263, B:190:0x026e, B:192:0x0272, B:194:0x0276, B:197:0x0281, B:199:0x0285, B:201:0x0289, B:204:0x0294, B:206:0x0298, B:208:0x029c, B:211:0x02aa, B:213:0x02ae, B:215:0x02b2, B:218:0x02bf, B:220:0x02c3, B:222:0x02c7, B:225:0x02d5, B:227:0x02d9, B:229:0x02dd, B:233:0x02e9, B:235:0x02ed, B:237:0x02f1, B:240:0x0301, B:242:0x030c, B:245:0x0313), top: B:372:0x016d }] */
    /* JADX WARN: Code duplicated, block: B:247:0x0318  */
    /* JADX WARN: Code duplicated, block: B:251:0x0323  */
    /* JADX WARN: Code duplicated, block: B:253:0x032a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:254:0x032c A[Catch: Exception -> 0x03e0, all -> 0x043a, TryCatch #2 {Exception -> 0x03e0, blocks: (B:249:0x031d, B:290:0x03a6, B:292:0x03aa, B:294:0x03ae, B:297:0x03b7, B:299:0x03bf, B:300:0x03c7, B:302:0x03cd, B:254:0x032c, B:255:0x032f, B:257:0x0335, B:260:0x033b, B:264:0x034d, B:267:0x0357, B:270:0x0361, B:272:0x036a, B:275:0x0374, B:276:0x0378, B:277:0x037c, B:279:0x0383, B:280:0x0387, B:281:0x038b, B:283:0x038f, B:285:0x0393, B:288:0x039f, B:289:0x03a3, B:261:0x0345, B:310:0x03df), top: B:364:0x031d }] */
    /* JADX WARN: Code duplicated, block: B:256:0x0333  */
    /* JADX WARN: Code duplicated, block: B:257:0x0335 A[Catch: Exception -> 0x03e0, all -> 0x043a, TryCatch #2 {Exception -> 0x03e0, blocks: (B:249:0x031d, B:290:0x03a6, B:292:0x03aa, B:294:0x03ae, B:297:0x03b7, B:299:0x03bf, B:300:0x03c7, B:302:0x03cd, B:254:0x032c, B:255:0x032f, B:257:0x0335, B:260:0x033b, B:264:0x034d, B:267:0x0357, B:270:0x0361, B:272:0x036a, B:275:0x0374, B:276:0x0378, B:277:0x037c, B:279:0x0383, B:280:0x0387, B:281:0x038b, B:283:0x038f, B:285:0x0393, B:288:0x039f, B:289:0x03a3, B:261:0x0345, B:310:0x03df), top: B:364:0x031d }] */
    /* JADX WARN: Code duplicated, block: B:262:0x034a  */
    /* JADX WARN: Code duplicated, block: B:264:0x034d A[Catch: Exception -> 0x03e0, all -> 0x043a, TryCatch #2 {Exception -> 0x03e0, blocks: (B:249:0x031d, B:290:0x03a6, B:292:0x03aa, B:294:0x03ae, B:297:0x03b7, B:299:0x03bf, B:300:0x03c7, B:302:0x03cd, B:254:0x032c, B:255:0x032f, B:257:0x0335, B:260:0x033b, B:264:0x034d, B:267:0x0357, B:270:0x0361, B:272:0x036a, B:275:0x0374, B:276:0x0378, B:277:0x037c, B:279:0x0383, B:280:0x0387, B:281:0x038b, B:283:0x038f, B:285:0x0393, B:288:0x039f, B:289:0x03a3, B:261:0x0345, B:310:0x03df), top: B:364:0x031d }] */
    /* JADX WARN: Code duplicated, block: B:266:0x0355 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:281:0x038b A[Catch: Exception -> 0x03e0, all -> 0x043a, TryCatch #2 {Exception -> 0x03e0, blocks: (B:249:0x031d, B:290:0x03a6, B:292:0x03aa, B:294:0x03ae, B:297:0x03b7, B:299:0x03bf, B:300:0x03c7, B:302:0x03cd, B:254:0x032c, B:255:0x032f, B:257:0x0335, B:260:0x033b, B:264:0x034d, B:267:0x0357, B:270:0x0361, B:272:0x036a, B:275:0x0374, B:276:0x0378, B:277:0x037c, B:279:0x0383, B:280:0x0387, B:281:0x038b, B:283:0x038f, B:285:0x0393, B:288:0x039f, B:289:0x03a3, B:261:0x0345, B:310:0x03df), top: B:364:0x031d }] */
    /* JADX WARN: Code duplicated, block: B:283:0x038f A[Catch: Exception -> 0x03e0, all -> 0x043a, TryCatch #2 {Exception -> 0x03e0, blocks: (B:249:0x031d, B:290:0x03a6, B:292:0x03aa, B:294:0x03ae, B:297:0x03b7, B:299:0x03bf, B:300:0x03c7, B:302:0x03cd, B:254:0x032c, B:255:0x032f, B:257:0x0335, B:260:0x033b, B:264:0x034d, B:267:0x0357, B:270:0x0361, B:272:0x036a, B:275:0x0374, B:276:0x0378, B:277:0x037c, B:279:0x0383, B:280:0x0387, B:281:0x038b, B:283:0x038f, B:285:0x0393, B:288:0x039f, B:289:0x03a3, B:261:0x0345, B:310:0x03df), top: B:364:0x031d }] */
    /* JADX WARN: Code duplicated, block: B:289:0x03a3 A[Catch: Exception -> 0x03e0, all -> 0x043a, TryCatch #2 {Exception -> 0x03e0, blocks: (B:249:0x031d, B:290:0x03a6, B:292:0x03aa, B:294:0x03ae, B:297:0x03b7, B:299:0x03bf, B:300:0x03c7, B:302:0x03cd, B:254:0x032c, B:255:0x032f, B:257:0x0335, B:260:0x033b, B:264:0x034d, B:267:0x0357, B:270:0x0361, B:272:0x036a, B:275:0x0374, B:276:0x0378, B:277:0x037c, B:279:0x0383, B:280:0x0387, B:281:0x038b, B:283:0x038f, B:285:0x0393, B:288:0x039f, B:289:0x03a3, B:261:0x0345, B:310:0x03df), top: B:364:0x031d }] */
    /* JADX WARN: Code duplicated, block: B:292:0x03aa A[Catch: Exception -> 0x03e0, all -> 0x043a, TryCatch #2 {Exception -> 0x03e0, blocks: (B:249:0x031d, B:290:0x03a6, B:292:0x03aa, B:294:0x03ae, B:297:0x03b7, B:299:0x03bf, B:300:0x03c7, B:302:0x03cd, B:254:0x032c, B:255:0x032f, B:257:0x0335, B:260:0x033b, B:264:0x034d, B:267:0x0357, B:270:0x0361, B:272:0x036a, B:275:0x0374, B:276:0x0378, B:277:0x037c, B:279:0x0383, B:280:0x0387, B:281:0x038b, B:283:0x038f, B:285:0x0393, B:288:0x039f, B:289:0x03a3, B:261:0x0345, B:310:0x03df), top: B:364:0x031d }] */
    /* JADX WARN: Code duplicated, block: B:304:0x03d3  */
    /* JADX WARN: Code duplicated, block: B:351:0x046a A[Catch: all -> 0x04a2, TryCatch #0 {all -> 0x04a2, blocks: (B:348:0x044a, B:351:0x046a, B:352:0x047e, B:354:0x0484, B:355:0x049c, B:356:0x04a1), top: B:360:0x044a }] */
    /* JADX WARN: Code duplicated, block: B:354:0x0484 A[Catch: all -> 0x04a2, TryCatch #0 {all -> 0x04a2, blocks: (B:348:0x044a, B:351:0x046a, B:352:0x047e, B:354:0x0484, B:355:0x049c, B:356:0x04a1), top: B:360:0x044a }] */
    /* JADX WARN: Code duplicated, block: B:360:0x044a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:368:0x0126 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:44:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:68:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:70:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:72:0x00f7 A[Catch: all -> 0x03f3, Exception -> 0x03f6, TRY_ENTER, TRY_LEAVE, TryCatch #1 {Exception -> 0x03f6, blocks: (B:60:0x00d6, B:72:0x00f7, B:80:0x010b, B:96:0x0136, B:106:0x0154, B:94:0x012e), top: B:362:0x00d6 }] */
    /* JADX WARN: Code duplicated, block: B:74:0x00fd A[Catch: Exception -> 0x00e5, all -> 0x03f3, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x03f3, blocks: (B:60:0x00d6, B:63:0x00e0, B:72:0x00f7, B:74:0x00fd, B:80:0x010b, B:82:0x0111, B:84:0x011b, B:90:0x0126, B:96:0x0136, B:101:0x0141, B:104:0x014d, B:106:0x0154, B:94:0x012e), top: B:362:0x00d6 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x0108 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:79:0x010a  */
    /* JADX WARN: Code duplicated, block: B:82:0x0111 A[Catch: Exception -> 0x00e5, all -> 0x03f3, TRY_ENTER, TryCatch #3 {all -> 0x03f3, blocks: (B:60:0x00d6, B:63:0x00e0, B:72:0x00f7, B:74:0x00fd, B:80:0x010b, B:82:0x0111, B:84:0x011b, B:90:0x0126, B:96:0x0136, B:101:0x0141, B:104:0x014d, B:106:0x0154, B:94:0x012e), top: B:362:0x00d6 }] */
    /* JADX WARN: Code duplicated, block: B:87:0x0122  */
    /* JADX WARN: Code duplicated, block: B:98:0x013c  */
    /* JADX WARN: Code duplicated, block: B:99:0x013d  */
    /* JADX WARN: Instruction removed from duplicated block: B:351:0x046a, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:354:0x0484, please report this as an issue */
    protected void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i, boolean z) throws Throwable {
        FieldSerializer[] fieldSerializerArr;
        JSONSerializer jSONSerializer2;
        SerialContext serialContext;
        Object obj3;
        String str;
        char c;
        SerialContext serialContext2;
        boolean z2;
        Object propertyValueDirect;
        Object obj4;
        String strProcessKey;
        int i2;
        char c2;
        Object objProcessValue;
        char c3;
        int iOf;
        int i3;
        int i4;
        int i5;
        int i6;
        JSONSerializer jSONSerializer3 = jSONSerializer;
        Object obj5 = obj;
        Type type2 = type;
        SerializeWriter serializeWriter = jSONSerializer3.out;
        if (obj5 == null) {
            serializeWriter.writeNull();
            return;
        }
        if (writeReference(jSONSerializer3, obj5, i)) {
            return;
        }
        if (serializeWriter.sortField) {
            fieldSerializerArr = this.sortedGetters;
        } else {
            fieldSerializerArr = this.getters;
        }
        FieldSerializer[] fieldSerializerArr2 = fieldSerializerArr;
        SerialContext serialContext3 = jSONSerializer3.context;
        if (!this.beanInfo.beanType.isEnum()) {
            jSONSerializer.setContext(serialContext3, obj, obj2, this.beanInfo.features, i);
        }
        boolean zIsWriteAsArray = isWriteAsArray(jSONSerializer3, i);
        char c4 = zIsWriteAsArray ? '[' : '{';
        char c5 = zIsWriteAsArray ? ']' : '}';
        if (!z) {
            try {
                serializeWriter.append(c4);
            } catch (Exception e) {
                e = e;
                jSONSerializer2 = jSONSerializer3;
                obj3 = obj5;
                serialContext = serialContext3;
                str = "write javaBean error, fastjson version 1.2.46";
                if (obj3 != null) {
                    try {
                        str = "write javaBean error, fastjson version 1.2.46, class " + obj.getClass().getName();
                    } catch (Throwable th) {
                        th = th;
                    }
                }
                if (obj2 != null) {
                    str = str + ", fieldName : " + obj2;
                }
                if (e.getMessage() != null) {
                    str = str + ", " + e.getMessage();
                }
                throw new JSONException(str, e);
            } catch (Throwable th2) {
                th = th2;
                jSONSerializer2 = jSONSerializer3;
                serialContext = serialContext3;
            }
        }
        if (fieldSerializerArr2.length > 0 && serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
            jSONSerializer.incrementIndent();
            jSONSerializer.println();
        }
        char c6 = ',';
        if ((this.beanInfo.features & SerializerFeature.WriteClassName.mask) == 0 && (i & SerializerFeature.WriteClassName.mask) == 0 && !jSONSerializer3.isWriteClassName(type2, obj5)) {
            c = 0;
        } else {
            Class<?> cls = obj.getClass();
            if (cls != ((cls == type2 || !(type2 instanceof WildcardType)) ? type2 : TypeUtils.getClass(type))) {
                writeClassName(jSONSerializer3, this.beanInfo.typeKey, obj5);
                c = ',';
            } else {
                c = 0;
            }
        }
        boolean z3 = serializeWriter.quoteFieldNames && !serializeWriter.useSingleQuotes;
        boolean z4 = writeBefore(jSONSerializer3, obj5, c) == ',';
        boolean zIsEnabled = serializeWriter.isEnabled(SerializerFeature.SkipTransientField);
        boolean zIsEnabled2 = serializeWriter.isEnabled(SerializerFeature.IgnoreNonFieldGetter);
        boolean z5 = z4;
        int i7 = 0;
        while (i7 < fieldSerializerArr2.length) {
            try {
                FieldSerializer fieldSerializer = fieldSerializerArr2[i7];
                Field field = fieldSerializer.fieldInfo.field;
                FieldInfo fieldInfo = fieldSerializer.fieldInfo;
                serialContext2 = serialContext3;
                try {
                    try {
                        String str2 = fieldInfo.name;
                        FieldSerializer[] fieldSerializerArr3 = fieldSerializerArr2;
                        Class<?> cls2 = fieldInfo.fieldClass;
                        if (!zIsEnabled || field == null) {
                            if (zIsEnabled2 || field != null) {
                                if (!applyName(jSONSerializer3, obj5, str2) && applyLabel(jSONSerializer3, fieldInfo.label)) {
                                    z2 = false;
                                } else if (zIsWriteAsArray) {
                                    z2 = true;
                                } else {
                                    i2 = i7;
                                    c2 = c5;
                                    jSONSerializer2 = jSONSerializer3;
                                }
                                if (this.beanInfo.typeKey == null && str2.equals(this.beanInfo.typeKey) && jSONSerializer3.isWriteClassName(type2, obj5)) {
                                    i2 = i7;
                                    c2 = c5;
                                    jSONSerializer2 = jSONSerializer3;
                                } else {
                                    propertyValueDirect = null;
                                    if (!z2) {
                                        try {
                                            propertyValueDirect = fieldSerializer.getPropertyValueDirect(obj5);
                                        } catch (InvocationTargetException e2) {
                                            if (!serializeWriter.isEnabled(SerializerFeature.IgnoreErrorGetter)) {
                                                jSONSerializer2 = jSONSerializer3;
                                                throw e2;
                                            }
                                            e = e;
                                            obj3 = obj;
                                            serialContext = serialContext2;
                                            str = "write javaBean error, fastjson version 1.2.46";
                                            if (obj3 != null) {
                                                str = "write javaBean error, fastjson version 1.2.46, class " + obj.getClass().getName();
                                            }
                                            if (obj2 != null) {
                                                str = str + ", fieldName : " + obj2;
                                            }
                                            if (e.getMessage() != null) {
                                                str = str + ", " + e.getMessage();
                                            }
                                            throw new JSONException(str, e);
                                        }
                                    }
                                    if (apply(jSONSerializer3, obj5, str2, propertyValueDirect)) {
                                        if (cls2 == String.class && "trim".equals(fieldInfo.format) && propertyValueDirect != null) {
                                            propertyValueDirect = ((String) propertyValueDirect).trim();
                                        }
                                        obj4 = propertyValueDirect;
                                        strProcessKey = processKey(jSONSerializer3, obj5, str2, obj4);
                                        i2 = i7;
                                        c2 = c5;
                                        try {
                                            objProcessValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str2, obj4);
                                            if (objProcessValue != null) {
                                                if (objProcessValue == null) {
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (z5) {
                                                    if (fieldInfo.unwrapped) {
                                                    }
                                                    serializeWriter.write(44);
                                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                        jSONSerializer.println();
                                                    }
                                                }
                                                if (strProcessKey == str2) {
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    if (obj4 != objProcessValue) {
                                                        if (!zIsWriteAsArray) {
                                                            fieldSerializer.writePrefix(jSONSerializer2);
                                                        }
                                                        jSONSerializer2.write(objProcessValue);
                                                    } else {
                                                        if (zIsWriteAsArray) {
                                                            c3 = 0;
                                                        } else {
                                                            c3 = 0;
                                                        }
                                                        if (!zIsWriteAsArray) {
                                                            JSONField annotation2 = fieldInfo.getAnnotation();
                                                            if (cls2 == String.class) {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            } else {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                        } else {
                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    if (!zIsWriteAsArray) {
                                                        serializeWriter.writeFieldName(strProcessKey, true);
                                                    }
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    jSONSerializer2.write(objProcessValue);
                                                }
                                                if (!fieldInfo.unwrapped) {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                } else {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                }
                                            }
                                            iOf = fieldInfo.serialzeFeatures;
                                            if (this.beanInfo.jsonType != null) {
                                                iOf |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                            }
                                            if (cls2 == Boolean.class) {
                                                i6 = SerializerFeature.WriteNullBooleanAsFalse.mask;
                                                int i8 = SerializerFeature.WriteMapNullValue.mask | i6;
                                                if (!zIsWriteAsArray || (iOf & i8) != 0 || (i8 & serializeWriter.features) != 0) {
                                                    if ((iOf & i6) == 0 || (serializeWriter.features & i6) != 0) {
                                                        objProcessValue = false;
                                                    }
                                                    if (objProcessValue == null && (serializeWriter.notWriteDefaultValue || (fieldInfo.serialzeFeatures & SerializerFeature.NotWriteDefaultValue.mask) != 0 || (this.beanInfo.features & SerializerFeature.NotWriteDefaultValue.mask) != 0)) {
                                                        Class<?> cls3 = fieldInfo.fieldClass;
                                                        if ((cls3 != Byte.TYPE || !(objProcessValue instanceof Byte) || ((Byte) objProcessValue).byteValue() != 0) && ((cls3 != Short.TYPE || !(objProcessValue instanceof Short) || ((Short) objProcessValue).shortValue() != 0) && (cls3 != Integer.TYPE || !(objProcessValue instanceof Integer) || ((Integer) objProcessValue).intValue() != 0))) {
                                                            if ((cls3 != Long.TYPE || !(objProcessValue instanceof Long) || ((Long) objProcessValue).longValue() != 0) && ((cls3 != Float.TYPE || !(objProcessValue instanceof Float) || ((Float) objProcessValue).floatValue() != 0.0f) && ((cls3 != Double.TYPE || !(objProcessValue instanceof Double) || ((Double) objProcessValue).doubleValue() != 0.0d) && (cls3 != Boolean.TYPE || !(objProcessValue instanceof Boolean) || ((Boolean) objProcessValue).booleanValue())))) {
                                                                if (z5) {
                                                                    if (fieldInfo.unwrapped) {
                                                                    }
                                                                    serializeWriter.write(44);
                                                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                        jSONSerializer.println();
                                                                    }
                                                                }
                                                                if (strProcessKey == str2) {
                                                                    jSONSerializer2 = jSONSerializer;
                                                                    c6 = ',';
                                                                    if (obj4 != objProcessValue) {
                                                                        if (!zIsWriteAsArray) {
                                                                            fieldSerializer.writePrefix(jSONSerializer2);
                                                                        }
                                                                        jSONSerializer2.write(objProcessValue);
                                                                    } else {
                                                                        if (zIsWriteAsArray) {
                                                                            c3 = 0;
                                                                        } else {
                                                                            c3 = 0;
                                                                        }
                                                                        if (!zIsWriteAsArray) {
                                                                            JSONField annotation3 = fieldInfo.getAnnotation();
                                                                            if (cls2 == String.class) {
                                                                                if (!fieldInfo.unwrapped) {
                                                                                }
                                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                            } else {
                                                                                if (!fieldInfo.unwrapped) {
                                                                                }
                                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                            }
                                                                        } else {
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                        if (!fieldInfo.unwrapped) {
                                                                            z5 = true;
                                                                            break;
                                                                            break;
                                                                        } else {
                                                                            z5 = true;
                                                                            break;
                                                                            break;
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (!zIsWriteAsArray) {
                                                                        serializeWriter.writeFieldName(strProcessKey, true);
                                                                    }
                                                                    jSONSerializer2 = jSONSerializer;
                                                                    c6 = ',';
                                                                    jSONSerializer2.write(objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped || !(objProcessValue instanceof Map) || ((Map) objProcessValue).size() != 0) {
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            try {
                                                                try {
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } catch (Exception e3) {
                                                                    e = e3;
                                                                    obj3 = obj;
                                                                    serialContext = serialContext2;
                                                                    str = "write javaBean error, fastjson version 1.2.46";
                                                                    if (obj3 != null) {
                                                                        str = "write javaBean error, fastjson version 1.2.46, class " + obj.getClass().getName();
                                                                    }
                                                                    if (obj2 != null) {
                                                                        str = str + ", fieldName : " + obj2;
                                                                    }
                                                                    if (e.getMessage() != null) {
                                                                        str = str + ", " + e.getMessage();
                                                                    }
                                                                    throw new JSONException(str, e);
                                                                }
                                                            } catch (Throwable th3) {
                                                                th = th3;
                                                                serialContext = serialContext2;
                                                                jSONSerializer2.context = serialContext;
                                                                throw th;
                                                            }
                                                        } else {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray || fieldInfo.unwrapped) {
                                                                    c3 = 0;
                                                                } else if (z3) {
                                                                    c3 = 0;
                                                                    serializeWriter.write(fieldInfo.name_chars, 0, fieldInfo.name_chars.length);
                                                                } else {
                                                                    c3 = 0;
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation4 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class || !(annotation4 == null || annotation4.serializeUsing() == Void.class)) {
                                                                        if (!fieldInfo.unwrapped && (objProcessValue instanceof Map) && ((Map) objProcessValue).size() == 0) {
                                                                            z5 = false;
                                                                        } else {
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else if (objProcessValue == null) {
                                                                        if ((serializeWriter.features & SerializerFeature.WriteNullStringAsEmpty.mask) != 0 || (fieldSerializer.features & SerializerFeature.WriteNullStringAsEmpty.mask) != 0) {
                                                                            serializeWriter.writeString("");
                                                                        } else {
                                                                            serializeWriter.writeNull();
                                                                        }
                                                                    } else {
                                                                        String str3 = (String) objProcessValue;
                                                                        if (serializeWriter.useSingleQuotes) {
                                                                            serializeWriter.writeStringWithSingleQuote(str3);
                                                                        } else {
                                                                            serializeWriter.writeStringWithDoubleQuote(str3, c3);
                                                                        }
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped || !(objProcessValue instanceof Map)) {
                                                                    z5 = true;
                                                                    break;
                                                                }
                                                                Map map = (Map) objProcessValue;
                                                                if (map.size() != 0) {
                                                                    if (jSONSerializer2.isEnabled(SerializerFeature.WriteMapNullValue)) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                    Iterator it = map.values().iterator();
                                                                    while (it.hasNext()) {
                                                                        if (it.next() != null) {
                                                                            z5 = true;
                                                                            break;
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                }
                                            } else if (cls2 == String.class) {
                                                i5 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                int i9 = SerializerFeature.WriteMapNullValue.mask | i5;
                                                if (!zIsWriteAsArray || (iOf & i9) != 0 || (i9 & serializeWriter.features) != 0) {
                                                    if ((iOf & i5) == 0 || (serializeWriter.features & i5) != 0) {
                                                        objProcessValue = "";
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation5 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation6 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                            } else {
                                                if (Number.class.isAssignableFrom(cls2)) {
                                                    i4 = SerializerFeature.WriteNullNumberAsZero.mask;
                                                    int i10 = SerializerFeature.WriteMapNullValue.mask | i4;
                                                    if (!zIsWriteAsArray || (iOf & i10) != 0 || (i10 & serializeWriter.features) != 0) {
                                                        if ((iOf & i4) == 0 || (serializeWriter.features & i4) != 0) {
                                                            objProcessValue = 0;
                                                        }
                                                    }
                                                } else if (Collection.class.isAssignableFrom(cls2)) {
                                                    i3 = SerializerFeature.WriteNullListAsEmpty.mask;
                                                    int i11 = SerializerFeature.WriteMapNullValue.mask | i3;
                                                    if (!zIsWriteAsArray || (iOf & i11) != 0 || (i11 & serializeWriter.features) != 0) {
                                                        if ((iOf & i3) == 0 || (serializeWriter.features & i3) != 0) {
                                                            objProcessValue = Collections.emptyList();
                                                        }
                                                    }
                                                } else if (!zIsWriteAsArray || fieldSerializer.writeNull || serializeWriter.isEnabled(SerializerFeature.WriteMapNullValue.mask)) {
                                                }
                                                if (objProcessValue == null) {
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation7 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (z5) {
                                                    if (fieldInfo.unwrapped) {
                                                    }
                                                    serializeWriter.write(44);
                                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                        jSONSerializer.println();
                                                    }
                                                }
                                                if (strProcessKey == str2) {
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    if (obj4 != objProcessValue) {
                                                        if (!zIsWriteAsArray) {
                                                            fieldSerializer.writePrefix(jSONSerializer2);
                                                        }
                                                        jSONSerializer2.write(objProcessValue);
                                                    } else {
                                                        if (zIsWriteAsArray) {
                                                            c3 = 0;
                                                        } else {
                                                            c3 = 0;
                                                        }
                                                        if (!zIsWriteAsArray) {
                                                            JSONField annotation8 = fieldInfo.getAnnotation();
                                                            if (cls2 == String.class) {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            } else {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                        } else {
                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    if (!zIsWriteAsArray) {
                                                        serializeWriter.writeFieldName(strProcessKey, true);
                                                    }
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    jSONSerializer2.write(objProcessValue);
                                                }
                                                if (!fieldInfo.unwrapped) {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                } else {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                }
                                            }
                                            jSONSerializer2 = jSONSerializer;
                                            c6 = ',';
                                        } catch (Exception e4) {
                                            e = e4;
                                            jSONSerializer2 = jSONSerializer;
                                            obj3 = obj;
                                            serialContext = serialContext2;
                                            str = "write javaBean error, fastjson version 1.2.46";
                                            if (obj3 != null) {
                                                str = "write javaBean error, fastjson version 1.2.46, class " + obj.getClass().getName();
                                            }
                                            if (obj2 != null) {
                                                str = str + ", fieldName : " + obj2;
                                            }
                                            if (e.getMessage() != null) {
                                                str = str + ", " + e.getMessage();
                                            }
                                            throw new JSONException(str, e);
                                        } catch (Throwable th4) {
                                            th = th4;
                                            jSONSerializer2 = jSONSerializer;
                                        }
                                    } else {
                                        i2 = i7;
                                        c2 = c5;
                                        jSONSerializer2 = jSONSerializer3;
                                    }
                                }
                            } else {
                                i2 = i7;
                                c2 = c5;
                                jSONSerializer2 = jSONSerializer3;
                            }
                            i7 = i2 + 1;
                            obj5 = obj;
                            type2 = type;
                            jSONSerializer3 = jSONSerializer2;
                            serialContext3 = serialContext2;
                            fieldSerializerArr2 = fieldSerializerArr3;
                            c5 = c2;
                        } else {
                            try {
                                if (fieldInfo.fieldTransient) {
                                    i2 = i7;
                                    c2 = c5;
                                    jSONSerializer2 = jSONSerializer3;
                                } else if (zIsEnabled2) {
                                    if (!applyName(jSONSerializer3, obj5, str2)) {
                                        if (zIsWriteAsArray) {
                                            z2 = true;
                                            if (this.beanInfo.typeKey == null) {
                                                propertyValueDirect = null;
                                                if (!z2) {
                                                    propertyValueDirect = fieldSerializer.getPropertyValueDirect(obj5);
                                                }
                                                if (apply(jSONSerializer3, obj5, str2, propertyValueDirect)) {
                                                    if (cls2 == String.class) {
                                                        propertyValueDirect = ((String) propertyValueDirect).trim();
                                                    }
                                                    obj4 = propertyValueDirect;
                                                    strProcessKey = processKey(jSONSerializer3, obj5, str2, obj4);
                                                    i2 = i7;
                                                    c2 = c5;
                                                    objProcessValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str2, obj4);
                                                    if (objProcessValue != null) {
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation9 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation10 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    iOf = fieldInfo.serialzeFeatures;
                                                    if (this.beanInfo.jsonType != null) {
                                                        iOf |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                                    }
                                                    if (cls2 == Boolean.class) {
                                                        i6 = SerializerFeature.WriteNullBooleanAsFalse.mask;
                                                        int i12 = SerializerFeature.WriteMapNullValue.mask | i6;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i6) == 0) {
                                                            objProcessValue = false;
                                                        } else {
                                                            objProcessValue = false;
                                                        }
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation11 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation12 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (cls2 == String.class) {
                                                        i5 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                        int i13 = SerializerFeature.WriteMapNullValue.mask | i5;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i5) == 0) {
                                                            objProcessValue = "";
                                                        } else {
                                                            objProcessValue = "";
                                                        }
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation13 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation14 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (Number.class.isAssignableFrom(cls2)) {
                                                        i4 = SerializerFeature.WriteNullNumberAsZero.mask;
                                                        int i14 = SerializerFeature.WriteMapNullValue.mask | i4;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i4) == 0) {
                                                            objProcessValue = 0;
                                                        } else {
                                                            objProcessValue = 0;
                                                        }
                                                    } else if (Collection.class.isAssignableFrom(cls2)) {
                                                        i3 = SerializerFeature.WriteNullListAsEmpty.mask;
                                                        int i15 = SerializerFeature.WriteMapNullValue.mask | i3;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i3) == 0) {
                                                            objProcessValue = Collections.emptyList();
                                                        } else {
                                                            objProcessValue = Collections.emptyList();
                                                        }
                                                    } else if (!zIsWriteAsArray) {
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation15 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation16 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                }
                                            } else {
                                                propertyValueDirect = null;
                                                if (!z2) {
                                                    propertyValueDirect = fieldSerializer.getPropertyValueDirect(obj5);
                                                }
                                                if (apply(jSONSerializer3, obj5, str2, propertyValueDirect)) {
                                                    if (cls2 == String.class) {
                                                        propertyValueDirect = ((String) propertyValueDirect).trim();
                                                    }
                                                    obj4 = propertyValueDirect;
                                                    strProcessKey = processKey(jSONSerializer3, obj5, str2, obj4);
                                                    i2 = i7;
                                                    c2 = c5;
                                                    objProcessValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str2, obj4);
                                                    if (objProcessValue != null) {
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation17 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation18 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    iOf = fieldInfo.serialzeFeatures;
                                                    if (this.beanInfo.jsonType != null) {
                                                        iOf |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                                    }
                                                    if (cls2 == Boolean.class) {
                                                        i6 = SerializerFeature.WriteNullBooleanAsFalse.mask;
                                                        int i16 = SerializerFeature.WriteMapNullValue.mask | i6;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i6) == 0) {
                                                            objProcessValue = false;
                                                        } else {
                                                            objProcessValue = false;
                                                        }
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation19 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation110 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (cls2 == String.class) {
                                                        i5 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                        int i17 = SerializerFeature.WriteMapNullValue.mask | i5;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i5) == 0) {
                                                            objProcessValue = "";
                                                        } else {
                                                            objProcessValue = "";
                                                        }
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation111 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation112 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (Number.class.isAssignableFrom(cls2)) {
                                                        i4 = SerializerFeature.WriteNullNumberAsZero.mask;
                                                        int i18 = SerializerFeature.WriteMapNullValue.mask | i4;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i4) == 0) {
                                                            objProcessValue = 0;
                                                        } else {
                                                            objProcessValue = 0;
                                                        }
                                                    } else if (Collection.class.isAssignableFrom(cls2)) {
                                                        i3 = SerializerFeature.WriteNullListAsEmpty.mask;
                                                        int i19 = SerializerFeature.WriteMapNullValue.mask | i3;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i3) == 0) {
                                                            objProcessValue = Collections.emptyList();
                                                        } else {
                                                            objProcessValue = Collections.emptyList();
                                                        }
                                                    } else if (!zIsWriteAsArray) {
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation113 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation114 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                }
                                            }
                                        }
                                    } else if (zIsWriteAsArray) {
                                        z2 = true;
                                        if (this.beanInfo.typeKey == null) {
                                            propertyValueDirect = null;
                                            if (!z2) {
                                                propertyValueDirect = fieldSerializer.getPropertyValueDirect(obj5);
                                            }
                                            if (apply(jSONSerializer3, obj5, str2, propertyValueDirect)) {
                                                if (cls2 == String.class) {
                                                    propertyValueDirect = ((String) propertyValueDirect).trim();
                                                }
                                                obj4 = propertyValueDirect;
                                                strProcessKey = processKey(jSONSerializer3, obj5, str2, obj4);
                                                i2 = i7;
                                                c2 = c5;
                                                objProcessValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str2, obj4);
                                                if (objProcessValue != null) {
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation115 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation116 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                iOf = fieldInfo.serialzeFeatures;
                                                if (this.beanInfo.jsonType != null) {
                                                    iOf |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                                }
                                                if (cls2 == Boolean.class) {
                                                    i6 = SerializerFeature.WriteNullBooleanAsFalse.mask;
                                                    int i110 = SerializerFeature.WriteMapNullValue.mask | i6;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i6) == 0) {
                                                        objProcessValue = false;
                                                    } else {
                                                        objProcessValue = false;
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation117 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation118 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (cls2 == String.class) {
                                                    i5 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                    int i111 = SerializerFeature.WriteMapNullValue.mask | i5;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i5) == 0) {
                                                        objProcessValue = "";
                                                    } else {
                                                        objProcessValue = "";
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation119 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation1110 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (Number.class.isAssignableFrom(cls2)) {
                                                    i4 = SerializerFeature.WriteNullNumberAsZero.mask;
                                                    int i112 = SerializerFeature.WriteMapNullValue.mask | i4;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i4) == 0) {
                                                        objProcessValue = 0;
                                                    } else {
                                                        objProcessValue = 0;
                                                    }
                                                } else if (Collection.class.isAssignableFrom(cls2)) {
                                                    i3 = SerializerFeature.WriteNullListAsEmpty.mask;
                                                    int i113 = SerializerFeature.WriteMapNullValue.mask | i3;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i3) == 0) {
                                                        objProcessValue = Collections.emptyList();
                                                    } else {
                                                        objProcessValue = Collections.emptyList();
                                                    }
                                                } else if (!zIsWriteAsArray) {
                                                }
                                                if (objProcessValue == null) {
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation1111 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (z5) {
                                                    if (fieldInfo.unwrapped) {
                                                    }
                                                    serializeWriter.write(44);
                                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                        jSONSerializer.println();
                                                    }
                                                }
                                                if (strProcessKey == str2) {
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    if (obj4 != objProcessValue) {
                                                        if (!zIsWriteAsArray) {
                                                            fieldSerializer.writePrefix(jSONSerializer2);
                                                        }
                                                        jSONSerializer2.write(objProcessValue);
                                                    } else {
                                                        if (zIsWriteAsArray) {
                                                            c3 = 0;
                                                        } else {
                                                            c3 = 0;
                                                        }
                                                        if (!zIsWriteAsArray) {
                                                            JSONField annotation1112 = fieldInfo.getAnnotation();
                                                            if (cls2 == String.class) {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            } else {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                        } else {
                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    if (!zIsWriteAsArray) {
                                                        serializeWriter.writeFieldName(strProcessKey, true);
                                                    }
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    jSONSerializer2.write(objProcessValue);
                                                }
                                                if (!fieldInfo.unwrapped) {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                } else {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                }
                                                jSONSerializer2 = jSONSerializer;
                                                c6 = ',';
                                            }
                                        } else {
                                            propertyValueDirect = null;
                                            if (!z2) {
                                                propertyValueDirect = fieldSerializer.getPropertyValueDirect(obj5);
                                            }
                                            if (apply(jSONSerializer3, obj5, str2, propertyValueDirect)) {
                                                if (cls2 == String.class) {
                                                    propertyValueDirect = ((String) propertyValueDirect).trim();
                                                }
                                                obj4 = propertyValueDirect;
                                                strProcessKey = processKey(jSONSerializer3, obj5, str2, obj4);
                                                i2 = i7;
                                                c2 = c5;
                                                objProcessValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str2, obj4);
                                                if (objProcessValue != null) {
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation1113 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation1114 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                iOf = fieldInfo.serialzeFeatures;
                                                if (this.beanInfo.jsonType != null) {
                                                    iOf |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                                }
                                                if (cls2 == Boolean.class) {
                                                    i6 = SerializerFeature.WriteNullBooleanAsFalse.mask;
                                                    int i114 = SerializerFeature.WriteMapNullValue.mask | i6;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i6) == 0) {
                                                        objProcessValue = false;
                                                    } else {
                                                        objProcessValue = false;
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation1115 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation1116 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (cls2 == String.class) {
                                                    i5 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                    int i115 = SerializerFeature.WriteMapNullValue.mask | i5;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i5) == 0) {
                                                        objProcessValue = "";
                                                    } else {
                                                        objProcessValue = "";
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation1117 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation1118 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (Number.class.isAssignableFrom(cls2)) {
                                                    i4 = SerializerFeature.WriteNullNumberAsZero.mask;
                                                    int i116 = SerializerFeature.WriteMapNullValue.mask | i4;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i4) == 0) {
                                                        objProcessValue = 0;
                                                    } else {
                                                        objProcessValue = 0;
                                                    }
                                                } else if (Collection.class.isAssignableFrom(cls2)) {
                                                    i3 = SerializerFeature.WriteNullListAsEmpty.mask;
                                                    int i117 = SerializerFeature.WriteMapNullValue.mask | i3;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i3) == 0) {
                                                        objProcessValue = Collections.emptyList();
                                                    } else {
                                                        objProcessValue = Collections.emptyList();
                                                    }
                                                } else if (!zIsWriteAsArray) {
                                                }
                                                if (objProcessValue == null) {
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation1119 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (z5) {
                                                    if (fieldInfo.unwrapped) {
                                                    }
                                                    serializeWriter.write(44);
                                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                        jSONSerializer.println();
                                                    }
                                                }
                                                if (strProcessKey == str2) {
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    if (obj4 != objProcessValue) {
                                                        if (!zIsWriteAsArray) {
                                                            fieldSerializer.writePrefix(jSONSerializer2);
                                                        }
                                                        jSONSerializer2.write(objProcessValue);
                                                    } else {
                                                        if (zIsWriteAsArray) {
                                                            c3 = 0;
                                                        } else {
                                                            c3 = 0;
                                                        }
                                                        if (!zIsWriteAsArray) {
                                                            JSONField annotation11110 = fieldInfo.getAnnotation();
                                                            if (cls2 == String.class) {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            } else {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                        } else {
                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    if (!zIsWriteAsArray) {
                                                        serializeWriter.writeFieldName(strProcessKey, true);
                                                    }
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    jSONSerializer2.write(objProcessValue);
                                                }
                                                if (!fieldInfo.unwrapped) {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                } else {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                }
                                                jSONSerializer2 = jSONSerializer;
                                                c6 = ',';
                                            }
                                        }
                                    }
                                    i2 = i7;
                                    c2 = c5;
                                    jSONSerializer2 = jSONSerializer3;
                                } else {
                                    if (!applyName(jSONSerializer3, obj5, str2)) {
                                        if (zIsWriteAsArray) {
                                            z2 = true;
                                            if (this.beanInfo.typeKey == null) {
                                                propertyValueDirect = null;
                                                if (!z2) {
                                                    propertyValueDirect = fieldSerializer.getPropertyValueDirect(obj5);
                                                }
                                                if (apply(jSONSerializer3, obj5, str2, propertyValueDirect)) {
                                                    if (cls2 == String.class) {
                                                        propertyValueDirect = ((String) propertyValueDirect).trim();
                                                    }
                                                    obj4 = propertyValueDirect;
                                                    strProcessKey = processKey(jSONSerializer3, obj5, str2, obj4);
                                                    i2 = i7;
                                                    c2 = c5;
                                                    objProcessValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str2, obj4);
                                                    if (objProcessValue != null) {
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation11111 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation11112 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    iOf = fieldInfo.serialzeFeatures;
                                                    if (this.beanInfo.jsonType != null) {
                                                        iOf |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                                    }
                                                    if (cls2 == Boolean.class) {
                                                        i6 = SerializerFeature.WriteNullBooleanAsFalse.mask;
                                                        int i118 = SerializerFeature.WriteMapNullValue.mask | i6;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i6) == 0) {
                                                            objProcessValue = false;
                                                        } else {
                                                            objProcessValue = false;
                                                        }
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation11113 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation11114 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (cls2 == String.class) {
                                                        i5 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                        int i119 = SerializerFeature.WriteMapNullValue.mask | i5;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i5) == 0) {
                                                            objProcessValue = "";
                                                        } else {
                                                            objProcessValue = "";
                                                        }
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation11115 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation11116 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (Number.class.isAssignableFrom(cls2)) {
                                                        i4 = SerializerFeature.WriteNullNumberAsZero.mask;
                                                        int i1110 = SerializerFeature.WriteMapNullValue.mask | i4;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i4) == 0) {
                                                            objProcessValue = 0;
                                                        } else {
                                                            objProcessValue = 0;
                                                        }
                                                    } else if (Collection.class.isAssignableFrom(cls2)) {
                                                        i3 = SerializerFeature.WriteNullListAsEmpty.mask;
                                                        int i1111 = SerializerFeature.WriteMapNullValue.mask | i3;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i3) == 0) {
                                                            objProcessValue = Collections.emptyList();
                                                        } else {
                                                            objProcessValue = Collections.emptyList();
                                                        }
                                                    } else if (!zIsWriteAsArray) {
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation11117 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation11118 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                }
                                            } else {
                                                propertyValueDirect = null;
                                                if (!z2) {
                                                    propertyValueDirect = fieldSerializer.getPropertyValueDirect(obj5);
                                                }
                                                if (apply(jSONSerializer3, obj5, str2, propertyValueDirect)) {
                                                    if (cls2 == String.class) {
                                                        propertyValueDirect = ((String) propertyValueDirect).trim();
                                                    }
                                                    obj4 = propertyValueDirect;
                                                    strProcessKey = processKey(jSONSerializer3, obj5, str2, obj4);
                                                    i2 = i7;
                                                    c2 = c5;
                                                    objProcessValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str2, obj4);
                                                    if (objProcessValue != null) {
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation11119 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation111110 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    iOf = fieldInfo.serialzeFeatures;
                                                    if (this.beanInfo.jsonType != null) {
                                                        iOf |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                                    }
                                                    if (cls2 == Boolean.class) {
                                                        i6 = SerializerFeature.WriteNullBooleanAsFalse.mask;
                                                        int i1112 = SerializerFeature.WriteMapNullValue.mask | i6;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i6) == 0) {
                                                            objProcessValue = false;
                                                        } else {
                                                            objProcessValue = false;
                                                        }
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation111111 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation111112 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (cls2 == String.class) {
                                                        i5 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                        int i1113 = SerializerFeature.WriteMapNullValue.mask | i5;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i5) == 0) {
                                                            objProcessValue = "";
                                                        } else {
                                                            objProcessValue = "";
                                                        }
                                                        if (objProcessValue == null) {
                                                            if (z5) {
                                                                if (fieldInfo.unwrapped) {
                                                                }
                                                                serializeWriter.write(44);
                                                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                    jSONSerializer.println();
                                                                }
                                                            }
                                                            if (strProcessKey == str2) {
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                if (obj4 != objProcessValue) {
                                                                    if (!zIsWriteAsArray) {
                                                                        fieldSerializer.writePrefix(jSONSerializer2);
                                                                    }
                                                                    jSONSerializer2.write(objProcessValue);
                                                                } else {
                                                                    if (zIsWriteAsArray) {
                                                                        c3 = 0;
                                                                    } else {
                                                                        c3 = 0;
                                                                    }
                                                                    if (!zIsWriteAsArray) {
                                                                        JSONField annotation111113 = fieldInfo.getAnnotation();
                                                                        if (cls2 == String.class) {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        } else {
                                                                            if (!fieldInfo.unwrapped) {
                                                                            }
                                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                        }
                                                                    } else {
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                    if (!fieldInfo.unwrapped) {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    } else {
                                                                        z5 = true;
                                                                        break;
                                                                        break;
                                                                    }
                                                                }
                                                            } else {
                                                                if (!zIsWriteAsArray) {
                                                                    serializeWriter.writeFieldName(strProcessKey, true);
                                                                }
                                                                jSONSerializer2 = jSONSerializer;
                                                                c6 = ',';
                                                                jSONSerializer2.write(objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation111114 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (Number.class.isAssignableFrom(cls2)) {
                                                        i4 = SerializerFeature.WriteNullNumberAsZero.mask;
                                                        int i1114 = SerializerFeature.WriteMapNullValue.mask | i4;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i4) == 0) {
                                                            objProcessValue = 0;
                                                        } else {
                                                            objProcessValue = 0;
                                                        }
                                                    } else if (Collection.class.isAssignableFrom(cls2)) {
                                                        i3 = SerializerFeature.WriteNullListAsEmpty.mask;
                                                        int i1115 = SerializerFeature.WriteMapNullValue.mask | i3;
                                                        if (!zIsWriteAsArray) {
                                                        }
                                                        if ((iOf & i3) == 0) {
                                                            objProcessValue = Collections.emptyList();
                                                        } else {
                                                            objProcessValue = Collections.emptyList();
                                                        }
                                                    } else if (!zIsWriteAsArray) {
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation111115 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation111116 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                }
                                            }
                                        }
                                    } else if (zIsWriteAsArray) {
                                        z2 = true;
                                        if (this.beanInfo.typeKey == null) {
                                            propertyValueDirect = null;
                                            if (!z2) {
                                                propertyValueDirect = fieldSerializer.getPropertyValueDirect(obj5);
                                            }
                                            if (apply(jSONSerializer3, obj5, str2, propertyValueDirect)) {
                                                if (cls2 == String.class) {
                                                    propertyValueDirect = ((String) propertyValueDirect).trim();
                                                }
                                                obj4 = propertyValueDirect;
                                                strProcessKey = processKey(jSONSerializer3, obj5, str2, obj4);
                                                i2 = i7;
                                                c2 = c5;
                                                objProcessValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str2, obj4);
                                                if (objProcessValue != null) {
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation111117 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation111118 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                iOf = fieldInfo.serialzeFeatures;
                                                if (this.beanInfo.jsonType != null) {
                                                    iOf |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                                }
                                                if (cls2 == Boolean.class) {
                                                    i6 = SerializerFeature.WriteNullBooleanAsFalse.mask;
                                                    int i1116 = SerializerFeature.WriteMapNullValue.mask | i6;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i6) == 0) {
                                                        objProcessValue = false;
                                                    } else {
                                                        objProcessValue = false;
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation111119 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation1111110 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (cls2 == String.class) {
                                                    i5 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                    int i1117 = SerializerFeature.WriteMapNullValue.mask | i5;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i5) == 0) {
                                                        objProcessValue = "";
                                                    } else {
                                                        objProcessValue = "";
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation1111111 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation1111112 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (Number.class.isAssignableFrom(cls2)) {
                                                    i4 = SerializerFeature.WriteNullNumberAsZero.mask;
                                                    int i1118 = SerializerFeature.WriteMapNullValue.mask | i4;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i4) == 0) {
                                                        objProcessValue = 0;
                                                    } else {
                                                        objProcessValue = 0;
                                                    }
                                                } else if (Collection.class.isAssignableFrom(cls2)) {
                                                    i3 = SerializerFeature.WriteNullListAsEmpty.mask;
                                                    int i1119 = SerializerFeature.WriteMapNullValue.mask | i3;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i3) == 0) {
                                                        objProcessValue = Collections.emptyList();
                                                    } else {
                                                        objProcessValue = Collections.emptyList();
                                                    }
                                                } else if (!zIsWriteAsArray) {
                                                }
                                                if (objProcessValue == null) {
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation1111113 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (z5) {
                                                    if (fieldInfo.unwrapped) {
                                                    }
                                                    serializeWriter.write(44);
                                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                        jSONSerializer.println();
                                                    }
                                                }
                                                if (strProcessKey == str2) {
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    if (obj4 != objProcessValue) {
                                                        if (!zIsWriteAsArray) {
                                                            fieldSerializer.writePrefix(jSONSerializer2);
                                                        }
                                                        jSONSerializer2.write(objProcessValue);
                                                    } else {
                                                        if (zIsWriteAsArray) {
                                                            c3 = 0;
                                                        } else {
                                                            c3 = 0;
                                                        }
                                                        if (!zIsWriteAsArray) {
                                                            JSONField annotation1111114 = fieldInfo.getAnnotation();
                                                            if (cls2 == String.class) {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            } else {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                        } else {
                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    if (!zIsWriteAsArray) {
                                                        serializeWriter.writeFieldName(strProcessKey, true);
                                                    }
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    jSONSerializer2.write(objProcessValue);
                                                }
                                                if (!fieldInfo.unwrapped) {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                } else {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                }
                                                jSONSerializer2 = jSONSerializer;
                                                c6 = ',';
                                            }
                                        } else {
                                            propertyValueDirect = null;
                                            if (!z2) {
                                                propertyValueDirect = fieldSerializer.getPropertyValueDirect(obj5);
                                            }
                                            if (apply(jSONSerializer3, obj5, str2, propertyValueDirect)) {
                                                if (cls2 == String.class) {
                                                    propertyValueDirect = ((String) propertyValueDirect).trim();
                                                }
                                                obj4 = propertyValueDirect;
                                                strProcessKey = processKey(jSONSerializer3, obj5, str2, obj4);
                                                i2 = i7;
                                                c2 = c5;
                                                objProcessValue = processValue(jSONSerializer, fieldSerializer.fieldContext, obj, str2, obj4);
                                                if (objProcessValue != null) {
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation1111115 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation1111116 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                iOf = fieldInfo.serialzeFeatures;
                                                if (this.beanInfo.jsonType != null) {
                                                    iOf |= SerializerFeature.of(this.beanInfo.jsonType.serialzeFeatures());
                                                }
                                                if (cls2 == Boolean.class) {
                                                    i6 = SerializerFeature.WriteNullBooleanAsFalse.mask;
                                                    int i11110 = SerializerFeature.WriteMapNullValue.mask | i6;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i6) == 0) {
                                                        objProcessValue = false;
                                                    } else {
                                                        objProcessValue = false;
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation1111117 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation1111118 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (cls2 == String.class) {
                                                    i5 = SerializerFeature.WriteNullStringAsEmpty.mask;
                                                    int i11111 = SerializerFeature.WriteMapNullValue.mask | i5;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i5) == 0) {
                                                        objProcessValue = "";
                                                    } else {
                                                        objProcessValue = "";
                                                    }
                                                    if (objProcessValue == null) {
                                                        if (z5) {
                                                            if (fieldInfo.unwrapped) {
                                                            }
                                                            serializeWriter.write(44);
                                                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                                jSONSerializer.println();
                                                            }
                                                        }
                                                        if (strProcessKey == str2) {
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            if (obj4 != objProcessValue) {
                                                                if (!zIsWriteAsArray) {
                                                                    fieldSerializer.writePrefix(jSONSerializer2);
                                                                }
                                                                jSONSerializer2.write(objProcessValue);
                                                            } else {
                                                                if (zIsWriteAsArray) {
                                                                    c3 = 0;
                                                                } else {
                                                                    c3 = 0;
                                                                }
                                                                if (!zIsWriteAsArray) {
                                                                    JSONField annotation1111119 = fieldInfo.getAnnotation();
                                                                    if (cls2 == String.class) {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    } else {
                                                                        if (!fieldInfo.unwrapped) {
                                                                        }
                                                                        fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                    }
                                                                } else {
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                                if (!fieldInfo.unwrapped) {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                } else {
                                                                    z5 = true;
                                                                    break;
                                                                    break;
                                                                }
                                                            }
                                                        } else {
                                                            if (!zIsWriteAsArray) {
                                                                serializeWriter.writeFieldName(strProcessKey, true);
                                                            }
                                                            jSONSerializer2 = jSONSerializer;
                                                            c6 = ',';
                                                            jSONSerializer2.write(objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation11111110 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (Number.class.isAssignableFrom(cls2)) {
                                                    i4 = SerializerFeature.WriteNullNumberAsZero.mask;
                                                    int i11112 = SerializerFeature.WriteMapNullValue.mask | i4;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i4) == 0) {
                                                        objProcessValue = 0;
                                                    } else {
                                                        objProcessValue = 0;
                                                    }
                                                } else if (Collection.class.isAssignableFrom(cls2)) {
                                                    i3 = SerializerFeature.WriteNullListAsEmpty.mask;
                                                    int i11113 = SerializerFeature.WriteMapNullValue.mask | i3;
                                                    if (!zIsWriteAsArray) {
                                                    }
                                                    if ((iOf & i3) == 0) {
                                                        objProcessValue = Collections.emptyList();
                                                    } else {
                                                        objProcessValue = Collections.emptyList();
                                                    }
                                                } else if (!zIsWriteAsArray) {
                                                }
                                                if (objProcessValue == null) {
                                                    if (z5) {
                                                        if (fieldInfo.unwrapped) {
                                                        }
                                                        serializeWriter.write(44);
                                                        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                            jSONSerializer.println();
                                                        }
                                                    }
                                                    if (strProcessKey == str2) {
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        if (obj4 != objProcessValue) {
                                                            if (!zIsWriteAsArray) {
                                                                fieldSerializer.writePrefix(jSONSerializer2);
                                                            }
                                                            jSONSerializer2.write(objProcessValue);
                                                        } else {
                                                            if (zIsWriteAsArray) {
                                                                c3 = 0;
                                                            } else {
                                                                c3 = 0;
                                                            }
                                                            if (!zIsWriteAsArray) {
                                                                JSONField annotation11111111 = fieldInfo.getAnnotation();
                                                                if (cls2 == String.class) {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                } else {
                                                                    if (!fieldInfo.unwrapped) {
                                                                    }
                                                                    fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                                }
                                                            } else {
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                            if (!fieldInfo.unwrapped) {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            } else {
                                                                z5 = true;
                                                                break;
                                                                break;
                                                            }
                                                        }
                                                    } else {
                                                        if (!zIsWriteAsArray) {
                                                            serializeWriter.writeFieldName(strProcessKey, true);
                                                        }
                                                        jSONSerializer2 = jSONSerializer;
                                                        c6 = ',';
                                                        jSONSerializer2.write(objProcessValue);
                                                    }
                                                    if (!fieldInfo.unwrapped) {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    } else {
                                                        z5 = true;
                                                        break;
                                                        break;
                                                    }
                                                }
                                                if (z5) {
                                                    if (fieldInfo.unwrapped) {
                                                    }
                                                    serializeWriter.write(44);
                                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                                        jSONSerializer.println();
                                                    }
                                                }
                                                if (strProcessKey == str2) {
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    if (obj4 != objProcessValue) {
                                                        if (!zIsWriteAsArray) {
                                                            fieldSerializer.writePrefix(jSONSerializer2);
                                                        }
                                                        jSONSerializer2.write(objProcessValue);
                                                    } else {
                                                        if (zIsWriteAsArray) {
                                                            c3 = 0;
                                                        } else {
                                                            c3 = 0;
                                                        }
                                                        if (!zIsWriteAsArray) {
                                                            JSONField annotation11111112 = fieldInfo.getAnnotation();
                                                            if (cls2 == String.class) {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            } else {
                                                                if (!fieldInfo.unwrapped) {
                                                                }
                                                                fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                            }
                                                        } else {
                                                            fieldSerializer.writeValue(jSONSerializer2, objProcessValue);
                                                        }
                                                        if (!fieldInfo.unwrapped) {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        } else {
                                                            z5 = true;
                                                            break;
                                                            break;
                                                        }
                                                    }
                                                } else {
                                                    if (!zIsWriteAsArray) {
                                                        serializeWriter.writeFieldName(strProcessKey, true);
                                                    }
                                                    jSONSerializer2 = jSONSerializer;
                                                    c6 = ',';
                                                    jSONSerializer2.write(objProcessValue);
                                                }
                                                if (!fieldInfo.unwrapped) {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                } else {
                                                    z5 = true;
                                                    break;
                                                    break;
                                                }
                                                jSONSerializer2 = jSONSerializer;
                                                c6 = ',';
                                            }
                                        }
                                    }
                                    i2 = i7;
                                    c2 = c5;
                                    jSONSerializer2 = jSONSerializer3;
                                }
                                i7 = i2 + 1;
                                obj5 = obj;
                                type2 = type;
                                jSONSerializer3 = jSONSerializer2;
                                serialContext3 = serialContext2;
                                fieldSerializerArr2 = fieldSerializerArr3;
                                c5 = c2;
                            } catch (Exception e5) {
                                e = e5;
                                jSONSerializer2 = jSONSerializer3;
                                obj3 = obj5;
                                serialContext = serialContext2;
                                str = "write javaBean error, fastjson version 1.2.46";
                                if (obj3 != null) {
                                    str = "write javaBean error, fastjson version 1.2.46, class " + obj.getClass().getName();
                                }
                                if (obj2 != null) {
                                    str = str + ", fieldName : " + obj2;
                                }
                                if (e.getMessage() != null) {
                                    str = str + ", " + e.getMessage();
                                }
                                throw new JSONException(str, e);
                            }
                        }
                    } catch (Exception e6) {
                        e = e6;
                        jSONSerializer2 = jSONSerializer3;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    jSONSerializer2 = jSONSerializer3;
                }
            } catch (Exception e7) {
                e = e7;
                jSONSerializer2 = jSONSerializer3;
                serialContext2 = serialContext3;
            } catch (Throwable th6) {
                th = th6;
                jSONSerializer2 = jSONSerializer3;
                serialContext2 = serialContext3;
            }
            jSONSerializer2.context = serialContext;
            throw th;
        }
        char c7 = c5;
        jSONSerializer2 = jSONSerializer3;
        FieldSerializer[] fieldSerializerArr4 = fieldSerializerArr2;
        serialContext2 = serialContext3;
        obj3 = obj;
        if (!z5) {
            c6 = 0;
        }
        try {
            writeAfter(jSONSerializer2, obj3, c6);
            if (fieldSerializerArr4.length > 0 && serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                jSONSerializer.decrementIdent();
                jSONSerializer.println();
            }
            if (!z) {
                serializeWriter.append(c7);
            }
            jSONSerializer2.context = serialContext2;
        } catch (Exception e8) {
            e = e8;
            serialContext = serialContext2;
            str = "write javaBean error, fastjson version 1.2.46";
            if (obj3 != null) {
                str = "write javaBean error, fastjson version 1.2.46, class " + obj.getClass().getName();
            }
            if (obj2 != null) {
                str = str + ", fieldName : " + obj2;
            }
            if (e.getMessage() != null) {
                str = str + ", " + e.getMessage();
            }
            throw new JSONException(str, e);
        }
    }

    protected void writeClassName(JSONSerializer jSONSerializer, String str, Object obj) {
        if (str == null) {
            str = jSONSerializer.config.typeKey;
        }
        jSONSerializer.out.writeFieldName(str, false);
        String name = this.beanInfo.typeName;
        if (name == null) {
            Class<?> superclass = obj.getClass();
            if (TypeUtils.isProxy(superclass)) {
                superclass = superclass.getSuperclass();
            }
            name = superclass.getName();
        }
        jSONSerializer.write(name);
    }

    public boolean writeReference(JSONSerializer jSONSerializer, Object obj, int i) {
        SerialContext serialContext = jSONSerializer.context;
        int i2 = SerializerFeature.DisableCircularReferenceDetect.mask;
        if (serialContext == null || (serialContext.features & i2) != 0 || (i & i2) != 0 || jSONSerializer.references == null || !jSONSerializer.references.containsKey(obj)) {
            return false;
        }
        jSONSerializer.writeReference(obj);
        return true;
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer) {
        return isWriteAsArray(jSONSerializer, 0);
    }

    protected boolean isWriteAsArray(JSONSerializer jSONSerializer, int i) {
        int i2 = SerializerFeature.BeanToArray.mask;
        return ((this.beanInfo.features & i2) == 0 && !jSONSerializer.out.beanToArray && (i & i2) == 0) ? false : true;
    }

    public Object getFieldValue(Object obj, String str) {
        FieldSerializer fieldSerializer = getFieldSerializer(str);
        if (fieldSerializer == null) {
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }

    public Object getFieldValue(Object obj, String str, long j, boolean z) {
        FieldSerializer fieldSerializer = getFieldSerializer(j);
        if (fieldSerializer == null) {
            if (!z) {
                return null;
            }
            throw new JSONException("field not found. " + str);
        }
        try {
            return fieldSerializer.getPropertyValue(obj);
        } catch (IllegalAccessException e) {
            throw new JSONException("getFieldValue error." + str, e);
        } catch (InvocationTargetException e2) {
            throw new JSONException("getFieldValue error." + str, e2);
        }
    }

    public FieldSerializer getFieldSerializer(String str) {
        if (str == null) {
            return null;
        }
        int length = this.sortedGetters.length - 1;
        int i = 0;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            int iCompareTo = this.sortedGetters[i2].fieldInfo.name.compareTo(str);
            if (iCompareTo < 0) {
                i = i2 + 1;
            } else {
                if (iCompareTo <= 0) {
                    return this.sortedGetters[i2];
                }
                length = i2 - 1;
            }
        }
        return null;
    }

    public FieldSerializer getFieldSerializer(long j) {
        PropertyNamingStrategy[] propertyNamingStrategyArrValues;
        int iBinarySearch;
        if (this.hashArray == null) {
            propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            long[] jArr = new long[this.sortedGetters.length * propertyNamingStrategyArrValues.length];
            int i = 0;
            int i2 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr = this.sortedGetters;
                if (i >= fieldSerializerArr.length) {
                    break;
                }
                String str = fieldSerializerArr[i].fieldInfo.name;
                jArr[i2] = TypeUtils.fnv1a_64(str);
                i2++;
                for (PropertyNamingStrategy propertyNamingStrategy : propertyNamingStrategyArrValues) {
                    String strTranslate = propertyNamingStrategy.translate(str);
                    if (!str.equals(strTranslate)) {
                        jArr[i2] = TypeUtils.fnv1a_64(strTranslate);
                        i2++;
                    }
                }
                i++;
            }
            Arrays.sort(jArr, 0, i2);
            this.hashArray = new long[i2];
            System.arraycopy(jArr, 0, this.hashArray, 0, i2);
        } else {
            propertyNamingStrategyArrValues = null;
        }
        int iBinarySearch2 = Arrays.binarySearch(this.hashArray, j);
        if (iBinarySearch2 < 0) {
            return null;
        }
        if (this.hashArrayMapping == null) {
            if (propertyNamingStrategyArrValues == null) {
                propertyNamingStrategyArrValues = PropertyNamingStrategy.values();
            }
            short[] sArr = new short[this.hashArray.length];
            Arrays.fill(sArr, (short) -1);
            int i3 = 0;
            while (true) {
                FieldSerializer[] fieldSerializerArr2 = this.sortedGetters;
                if (i3 >= fieldSerializerArr2.length) {
                    break;
                }
                String str2 = fieldSerializerArr2[i3].fieldInfo.name;
                int iBinarySearch3 = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(str2));
                if (iBinarySearch3 >= 0) {
                    sArr[iBinarySearch3] = (short) i3;
                }
                for (PropertyNamingStrategy propertyNamingStrategy2 : propertyNamingStrategyArrValues) {
                    String strTranslate2 = propertyNamingStrategy2.translate(str2);
                    if (!str2.equals(strTranslate2) && (iBinarySearch = Arrays.binarySearch(this.hashArray, TypeUtils.fnv1a_64(strTranslate2))) >= 0) {
                        sArr[iBinarySearch] = (short) i3;
                    }
                }
                i3++;
            }
            this.hashArrayMapping = sArr;
        }
        short s = this.hashArrayMapping[iBinarySearch2];
        if (s != -1) {
            return this.sortedGetters[s];
        }
        return null;
    }

    public List<Object> getFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            arrayList.add(fieldSerializer.getPropertyValue(obj));
        }
        return arrayList;
    }

    public List<Object> getObjectFieldValues(Object obj) throws Exception {
        ArrayList arrayList = new ArrayList(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            Class<?> cls = fieldSerializer.fieldInfo.fieldClass;
            if (!cls.isPrimitive() && !cls.getName().startsWith("java.lang.")) {
                arrayList.add(fieldSerializer.getPropertyValue(obj));
            }
        }
        return arrayList;
    }

    public int getSize(Object obj) throws Exception {
        int i = 0;
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            if (fieldSerializer.getPropertyValueDirect(obj) != null) {
                i++;
            }
        }
        return i;
    }

    public Map<String, Object> getFieldValuesMap(Object obj) throws Exception {
        LinkedHashMap linkedHashMap = new LinkedHashMap(this.sortedGetters.length);
        for (FieldSerializer fieldSerializer : this.sortedGetters) {
            linkedHashMap.put(fieldSerializer.fieldInfo.name, fieldSerializer.getPropertyValue(obj));
        }
        return linkedHashMap;
    }

    protected BeanContext getBeanContext(int i) {
        return this.sortedGetters[i].fieldContext;
    }

    protected Type getFieldType(int i) {
        return this.sortedGetters[i].fieldInfo.fieldType;
    }

    protected char writeBefore(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.beforeFilters != null) {
            Iterator<BeforeFilter> it = jSONSerializer.beforeFilters.iterator();
            while (it.hasNext()) {
                c = it.next().writeBefore(jSONSerializer, obj, c);
            }
        }
        if (this.beforeFilters != null) {
            Iterator<BeforeFilter> it2 = this.beforeFilters.iterator();
            while (it2.hasNext()) {
                c = it2.next().writeBefore(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    protected char writeAfter(JSONSerializer jSONSerializer, Object obj, char c) {
        if (jSONSerializer.afterFilters != null) {
            Iterator<AfterFilter> it = jSONSerializer.afterFilters.iterator();
            while (it.hasNext()) {
                c = it.next().writeAfter(jSONSerializer, obj, c);
            }
        }
        if (this.afterFilters != null) {
            Iterator<AfterFilter> it2 = this.afterFilters.iterator();
            while (it2.hasNext()) {
                c = it2.next().writeAfter(jSONSerializer, obj, c);
            }
        }
        return c;
    }

    protected boolean applyLabel(JSONSerializer jSONSerializer, String str) {
        if (jSONSerializer.labelFilters != null) {
            Iterator<LabelFilter> it = jSONSerializer.labelFilters.iterator();
            while (it.hasNext()) {
                if (!it.next().apply(str)) {
                    return false;
                }
            }
        }
        if (this.labelFilters == null) {
            return true;
        }
        Iterator<LabelFilter> it2 = this.labelFilters.iterator();
        while (it2.hasNext()) {
            if (!it2.next().apply(str)) {
                return false;
            }
        }
        return true;
    }
}
