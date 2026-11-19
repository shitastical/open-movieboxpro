package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes4.dex */
public class MapSerializer extends SerializeFilterable implements ObjectSerializer {
    public static MapSerializer instance = new MapSerializer();
    private static final int NON_STRINGKEY_AS_STRING = SerializerFeature.of(new SerializerFeature[]{SerializerFeature.BrowserCompatible, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.BrowserSecure});

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        write(jSONSerializer, obj, obj2, type, i, false);
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0153 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:102:0x0157 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:105:0x015f A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:115:0x017e A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:11:0x0022 A[PHI: r1
      0x0022: PHI (r1v61 java.util.Map<java.lang.String, java.lang.Object>) = 
      (r1v2 java.util.Map<java.lang.String, java.lang.Object>)
      (r1v2 java.util.Map<java.lang.String, java.lang.Object>)
      (r1v2 java.util.Map<java.lang.String, java.lang.Object>)
      (r1v1 java.util.Map<java.lang.String, java.lang.Object>)
     binds: [B:16:0x0030, B:18:0x0034, B:21:0x003d, B:9:0x001f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:120:0x018d A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:123:0x0195 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:131:0x01b1 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:134:0x01bb A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:137:0x01c3 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:145:0x01df A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:148:0x01e8 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:158:0x0217 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:161:0x022f A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:166:0x0242 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:168:0x0247 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:171:0x0252 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:173:0x0259 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:174:0x025b A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:177:0x0266 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:180:0x0272 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:183:0x027c A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:185:0x0286 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:187:0x028e A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:188:0x0295  */
    /* JADX WARN: Code duplicated, block: B:191:0x02a1 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:198:0x02b8  */
    /* JADX WARN: Code duplicated, block: B:200:0x02c8 A[Catch: all -> 0x02f6, TRY_LEAVE, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:218:0x023c A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:227:0x0187 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:64:0x00eb A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:66:0x00ef A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:82:0x011f A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    /* JADX WARN: Code duplicated, block: B:84:0x0123 A[Catch: all -> 0x02f6, TryCatch #1 {all -> 0x02f6, blocks: (B:29:0x0055, B:30:0x0058, B:32:0x0064, B:41:0x007f, B:43:0x0090, B:44:0x00a0, B:46:0x00a6, B:48:0x00b8, B:51:0x00c0, B:54:0x00c5, B:56:0x00cf, B:58:0x00d3, B:61:0x00de, B:64:0x00eb, B:66:0x00ef, B:69:0x00f7, B:72:0x00fc, B:74:0x0106, B:76:0x010a, B:79:0x0115, B:82:0x011f, B:84:0x0123, B:87:0x012b, B:90:0x0130, B:92:0x013a, B:94:0x013e, B:97:0x0149, B:100:0x0153, B:102:0x0157, B:105:0x015f, B:108:0x0164, B:110:0x016e, B:112:0x0172, B:115:0x017e, B:118:0x0189, B:120:0x018d, B:123:0x0195, B:126:0x019a, B:128:0x01a4, B:130:0x01a8, B:131:0x01b1, B:132:0x01b7, B:134:0x01bb, B:137:0x01c3, B:140:0x01c8, B:142:0x01d2, B:144:0x01d6, B:145:0x01df, B:148:0x01e8, B:151:0x01ed, B:153:0x01f1, B:156:0x01f6, B:161:0x022f, B:164:0x023c, B:166:0x0242, B:168:0x0247, B:169:0x024a, B:171:0x0252, B:172:0x0255, B:183:0x027c, B:185:0x0286, B:187:0x028e, B:189:0x0297, B:191:0x02a1, B:193:0x02a5, B:195:0x02a9, B:197:0x02b4, B:199:0x02ba, B:200:0x02c8, B:174:0x025b, B:175:0x025e, B:177:0x0266, B:179:0x026a, B:181:0x0275, B:180:0x0272, B:158:0x0217, B:38:0x0078), top: B:215:0x0055 }] */
    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i, boolean z) throws IOException {
        Map<String, Object> treeMap;
        boolean z2;
        List<PropertyPreFilter> list;
        List<PropertyFilter> list2;
        List<PropertyFilter> list3;
        List<NameFilter> list4;
        List<NameFilter> list5;
        String str;
        String str2;
        Object objProcessValue;
        Object obj3;
        Class<?> cls;
        Class<?> cls2;
        Class<?> cls3;
        ObjectSerializer objectSerializer;
        Type type2;
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        Map<String, Object> innerMap = (Map) obj;
        int i2 = SerializerFeature.MapSortField.mask;
        if ((serializeWriter.features & i2) == 0 && (i2 & i) == 0) {
            treeMap = innerMap;
        } else {
            if (innerMap instanceof JSONObject) {
                innerMap = ((JSONObject) innerMap).getInnerMap();
            }
            if ((innerMap instanceof SortedMap) || (innerMap instanceof LinkedHashMap)) {
                treeMap = innerMap;
            } else {
                try {
                    treeMap = new TreeMap(innerMap);
                } catch (Exception unused) {
                    treeMap = innerMap;
                }
            }
        }
        if (jSONSerializer.containsReference(obj)) {
            jSONSerializer.writeReference(obj);
            return;
        }
        SerialContext serialContext = jSONSerializer.context;
        jSONSerializer.setContext(serialContext, obj, obj2, 0);
        if (!z) {
            try {
                serializeWriter.write(123);
            } catch (Throwable th) {
                jSONSerializer.context = serialContext;
                throw th;
            }
        }
        jSONSerializer.incrementIndent();
        if (serializeWriter.isEnabled(SerializerFeature.WriteClassName)) {
            String str3 = jSONSerializer.config.typeKey;
            Class<?> cls4 = treeMap.getClass();
            if ((cls4 == JSONObject.class || cls4 == HashMap.class || cls4 == LinkedHashMap.class) && treeMap.containsKey(str3)) {
                z2 = true;
            } else {
                serializeWriter.writeFieldName(str3);
                serializeWriter.writeString(obj.getClass().getName());
                z2 = false;
            }
        } else {
            z2 = true;
        }
        boolean z3 = z2;
        Class<?> cls5 = null;
        ObjectSerializer objectWriter = null;
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            Object value = entry.getValue();
            String key = entry.getKey();
            List<PropertyPreFilter> list6 = jSONSerializer.propertyPreFilters;
            if (list6 != null && list6.size() > 0) {
                if (key == null || (key instanceof String)) {
                    if (applyName(jSONSerializer, obj, key)) {
                        list = this.propertyPreFilters;
                        if (list == null) {
                            list2 = jSONSerializer.propertyFilters;
                            if (list2 == null) {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                }
                                if (obj3 == null) {
                                }
                                if (str2 instanceof String) {
                                    String str4 = str2;
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    serializeWriter.writeFieldName(str4, true);
                                } else {
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (obj3 == null) {
                                    serializeWriter.writeNull();
                                    cls5 = cls5;
                                } else {
                                    cls = obj3.getClass();
                                    cls2 = cls5;
                                    if (cls != cls2) {
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                        cls3 = cls;
                                    } else {
                                        cls3 = cls2;
                                    }
                                    objectSerializer = objectWriter;
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    } else {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    }
                                    cls5 = cls3;
                                }
                                z3 = false;
                            } else {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                }
                                if (obj3 == null) {
                                }
                                if (str2 instanceof String) {
                                    String str5 = str2;
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    serializeWriter.writeFieldName(str5, true);
                                } else {
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (obj3 == null) {
                                    serializeWriter.writeNull();
                                    cls5 = cls5;
                                } else {
                                    cls = obj3.getClass();
                                    cls2 = cls5;
                                    if (cls != cls2) {
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                        cls3 = cls;
                                    } else {
                                        cls3 = cls2;
                                    }
                                    objectSerializer = objectWriter;
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    } else {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    }
                                    cls5 = cls3;
                                }
                                z3 = false;
                            }
                        } else {
                            list2 = jSONSerializer.propertyFilters;
                            if (list2 == null) {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                }
                                if (obj3 == null) {
                                }
                                if (str2 instanceof String) {
                                    String str6 = str2;
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    serializeWriter.writeFieldName(str6, true);
                                } else {
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (obj3 == null) {
                                    serializeWriter.writeNull();
                                    cls5 = cls5;
                                } else {
                                    cls = obj3.getClass();
                                    cls2 = cls5;
                                    if (cls != cls2) {
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                        cls3 = cls;
                                    } else {
                                        cls3 = cls2;
                                    }
                                    objectSerializer = objectWriter;
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    } else {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    }
                                    cls5 = cls3;
                                }
                                z3 = false;
                            } else {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                }
                                if (obj3 == null) {
                                }
                                if (str2 instanceof String) {
                                    String str7 = str2;
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    serializeWriter.writeFieldName(str7, true);
                                } else {
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (obj3 == null) {
                                    serializeWriter.writeNull();
                                    cls5 = cls5;
                                } else {
                                    cls = obj3.getClass();
                                    cls2 = cls5;
                                    if (cls != cls2) {
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                        cls3 = cls;
                                    } else {
                                        cls3 = cls2;
                                    }
                                    objectSerializer = objectWriter;
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    } else {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    }
                                    cls5 = cls3;
                                }
                                z3 = false;
                            }
                        }
                    }
                } else if ((!key.getClass().isPrimitive() && !(key instanceof Number)) || applyName(jSONSerializer, obj, JSON.toJSONString(key))) {
                    list = this.propertyPreFilters;
                    if (list == null) {
                        list2 = jSONSerializer.propertyFilters;
                        if (list2 == null) {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            }
                            if (obj3 == null) {
                            }
                            if (str2 instanceof String) {
                                String str8 = str2;
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                serializeWriter.writeFieldName(str8, true);
                            } else {
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (obj3 == null) {
                                serializeWriter.writeNull();
                                cls5 = cls5;
                            } else {
                                cls = obj3.getClass();
                                cls2 = cls5;
                                if (cls != cls2) {
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                    cls3 = cls;
                                } else {
                                    cls3 = cls2;
                                }
                                objectSerializer = objectWriter;
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                } else {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                }
                                cls5 = cls3;
                            }
                            z3 = false;
                        } else {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            }
                            if (obj3 == null) {
                            }
                            if (str2 instanceof String) {
                                String str9 = str2;
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                serializeWriter.writeFieldName(str9, true);
                            } else {
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (obj3 == null) {
                                serializeWriter.writeNull();
                                cls5 = cls5;
                            } else {
                                cls = obj3.getClass();
                                cls2 = cls5;
                                if (cls != cls2) {
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                    cls3 = cls;
                                } else {
                                    cls3 = cls2;
                                }
                                objectSerializer = objectWriter;
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                } else {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                }
                                cls5 = cls3;
                            }
                            z3 = false;
                        }
                    } else {
                        list2 = jSONSerializer.propertyFilters;
                        if (list2 == null) {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            }
                            if (obj3 == null) {
                            }
                            if (str2 instanceof String) {
                                String str10 = str2;
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                serializeWriter.writeFieldName(str10, true);
                            } else {
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (obj3 == null) {
                                serializeWriter.writeNull();
                                cls5 = cls5;
                            } else {
                                cls = obj3.getClass();
                                cls2 = cls5;
                                if (cls != cls2) {
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                    cls3 = cls;
                                } else {
                                    cls3 = cls2;
                                }
                                objectSerializer = objectWriter;
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                } else {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                }
                                cls5 = cls3;
                            }
                            z3 = false;
                        } else {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            }
                            if (obj3 == null) {
                            }
                            if (str2 instanceof String) {
                                String str11 = str2;
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                serializeWriter.writeFieldName(str11, true);
                            } else {
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (obj3 == null) {
                                serializeWriter.writeNull();
                                cls5 = cls5;
                            } else {
                                cls = obj3.getClass();
                                cls2 = cls5;
                                if (cls != cls2) {
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                    cls3 = cls;
                                } else {
                                    cls3 = cls2;
                                }
                                objectSerializer = objectWriter;
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                } else {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                }
                                cls5 = cls3;
                            }
                            z3 = false;
                        }
                    }
                }
                cls5 = cls5;
                cls5 = cls5;
            } else {
                list = this.propertyPreFilters;
                if (list == null && list.size() > 0) {
                    if (key == null || (key instanceof String)) {
                        if (applyName(jSONSerializer, obj, key)) {
                            list2 = jSONSerializer.propertyFilters;
                            if (list2 == null) {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                }
                                if (obj3 == null) {
                                }
                                if (str2 instanceof String) {
                                    String str12 = str2;
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    serializeWriter.writeFieldName(str12, true);
                                } else {
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (obj3 == null) {
                                    serializeWriter.writeNull();
                                    cls5 = cls5;
                                } else {
                                    cls = obj3.getClass();
                                    cls2 = cls5;
                                    if (cls != cls2) {
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                        cls3 = cls;
                                    } else {
                                        cls3 = cls2;
                                    }
                                    objectSerializer = objectWriter;
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    } else {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    }
                                    cls5 = cls3;
                                }
                                z3 = false;
                            } else {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                }
                                if (obj3 == null) {
                                }
                                if (str2 instanceof String) {
                                    String str13 = str2;
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    serializeWriter.writeFieldName(str13, true);
                                } else {
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (obj3 == null) {
                                    serializeWriter.writeNull();
                                    cls5 = cls5;
                                } else {
                                    cls = obj3.getClass();
                                    cls2 = cls5;
                                    if (cls != cls2) {
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                        cls3 = cls;
                                    } else {
                                        cls3 = cls2;
                                    }
                                    objectSerializer = objectWriter;
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    } else {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    }
                                    cls5 = cls3;
                                }
                                z3 = false;
                            }
                        }
                    } else if ((!key.getClass().isPrimitive() && !(key instanceof Number)) || applyName(jSONSerializer, obj, JSON.toJSONString(key))) {
                        list2 = jSONSerializer.propertyFilters;
                        if (list2 == null) {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            }
                            if (obj3 == null) {
                            }
                            if (str2 instanceof String) {
                                String str14 = str2;
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                serializeWriter.writeFieldName(str14, true);
                            } else {
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (obj3 == null) {
                                serializeWriter.writeNull();
                                cls5 = cls5;
                            } else {
                                cls = obj3.getClass();
                                cls2 = cls5;
                                if (cls != cls2) {
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                    cls3 = cls;
                                } else {
                                    cls3 = cls2;
                                }
                                objectSerializer = objectWriter;
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                } else {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                }
                                cls5 = cls3;
                            }
                            z3 = false;
                        } else {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            }
                            if (obj3 == null) {
                            }
                            if (str2 instanceof String) {
                                String str15 = str2;
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                serializeWriter.writeFieldName(str15, true);
                            } else {
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (obj3 == null) {
                                serializeWriter.writeNull();
                                cls5 = cls5;
                            } else {
                                cls = obj3.getClass();
                                cls2 = cls5;
                                if (cls != cls2) {
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                    cls3 = cls;
                                } else {
                                    cls3 = cls2;
                                }
                                objectSerializer = objectWriter;
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                } else {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                }
                                cls5 = cls3;
                            }
                            z3 = false;
                        }
                    }
                    cls5 = cls5;
                    cls5 = cls5;
                } else {
                    list2 = jSONSerializer.propertyFilters;
                    if (list2 == null && list2.size() > 0) {
                        if (key == null || (key instanceof String)) {
                            if (apply(jSONSerializer, obj, key, value)) {
                                list3 = this.propertyFilters;
                                if (list3 != null) {
                                    if (key != null) {
                                        if (!apply(jSONSerializer, obj, key, value)) {
                                        }
                                    } else if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                }
                                list4 = jSONSerializer.nameFilters;
                                if (list4 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                list5 = this.nameFilters;
                                if (list5 != null) {
                                    if (key != null) {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    } else {
                                        key = processKey(jSONSerializer, obj, key, value);
                                    }
                                }
                                str = key;
                                if (str != null) {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                } else {
                                    str2 = str;
                                    objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                    obj3 = objProcessValue;
                                }
                                if (obj3 == null) {
                                }
                                if (str2 instanceof String) {
                                    String str16 = str2;
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    serializeWriter.writeFieldName(str16, true);
                                } else {
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                        jSONSerializer.write((Object) str2);
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (obj3 == null) {
                                    serializeWriter.writeNull();
                                    cls5 = cls5;
                                } else {
                                    cls = obj3.getClass();
                                    cls2 = cls5;
                                    if (cls != cls2) {
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                        cls3 = cls;
                                    } else {
                                        cls3 = cls2;
                                    }
                                    objectSerializer = objectWriter;
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    } else {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    }
                                    cls5 = cls3;
                                }
                                z3 = false;
                            }
                        } else if ((!key.getClass().isPrimitive() && !(key instanceof Number)) || apply(jSONSerializer, obj, JSON.toJSONString(key), value)) {
                            list3 = this.propertyFilters;
                            if (list3 != null) {
                                if (key != null) {
                                    if (!apply(jSONSerializer, obj, key, value)) {
                                    }
                                } else if (!apply(jSONSerializer, obj, key, value)) {
                                }
                            }
                            list4 = jSONSerializer.nameFilters;
                            if (list4 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            list5 = this.nameFilters;
                            if (list5 != null) {
                                if (key != null) {
                                    key = processKey(jSONSerializer, obj, key, value);
                                } else {
                                    key = processKey(jSONSerializer, obj, key, value);
                                }
                            }
                            str = key;
                            if (str != null) {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                                obj3 = objProcessValue;
                            }
                            if (obj3 == null) {
                            }
                            if (str2 instanceof String) {
                                String str17 = str2;
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                    jSONSerializer.println();
                                }
                                serializeWriter.writeFieldName(str17, true);
                            } else {
                                if (!z3) {
                                    serializeWriter.write(44);
                                }
                                if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                    jSONSerializer.write((Object) str2);
                                } else {
                                    jSONSerializer.write((Object) str2);
                                }
                                serializeWriter.write(58);
                            }
                            if (obj3 == null) {
                                serializeWriter.writeNull();
                                cls5 = cls5;
                            } else {
                                cls = obj3.getClass();
                                cls2 = cls5;
                                if (cls != cls2) {
                                    objectWriter = jSONSerializer.getObjectWriter(cls);
                                    cls3 = cls;
                                } else {
                                    cls3 = cls2;
                                }
                                objectSerializer = objectWriter;
                                if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                } else {
                                    objectWriter = objectSerializer;
                                    objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                }
                                cls5 = cls3;
                            }
                            z3 = false;
                        }
                        cls5 = cls5;
                        cls5 = cls5;
                    } else {
                        list3 = this.propertyFilters;
                        if (list3 != null && list3.size() > 0) {
                            if (key != null || (key instanceof String)) {
                                if (!apply(jSONSerializer, obj, key, value)) {
                                    cls5 = cls5;
                                    cls5 = cls5;
                                }
                            } else if ((key.getClass().isPrimitive() || (key instanceof Number)) && !apply(jSONSerializer, obj, JSON.toJSONString(key), value)) {
                                cls5 = cls5;
                                cls5 = cls5;
                            }
                        }
                        list4 = jSONSerializer.nameFilters;
                        if (list4 != null && list4.size() > 0) {
                            if (key != null || (key instanceof String)) {
                                key = processKey(jSONSerializer, obj, key, value);
                            } else if (key.getClass().isPrimitive() || (key instanceof Number)) {
                                key = processKey(jSONSerializer, obj, JSON.toJSONString(key), value);
                            }
                        }
                        list5 = this.nameFilters;
                        if (list5 != null && list5.size() > 0) {
                            if (key != null || (key instanceof String)) {
                                key = processKey(jSONSerializer, obj, key, value);
                            } else if (key.getClass().isPrimitive() || (key instanceof Number)) {
                                key = processKey(jSONSerializer, obj, JSON.toJSONString(key), value);
                            }
                        }
                        str = key;
                        if (str != null || (str instanceof String)) {
                            str2 = str;
                            objProcessValue = processValue(jSONSerializer, null, obj, str2, value);
                        } else {
                            if ((str instanceof Map) || (str instanceof Collection)) {
                                str2 = str;
                                cls5 = cls5;
                                obj3 = value;
                            } else {
                                str2 = str;
                                objProcessValue = processValue(jSONSerializer, null, obj, JSON.toJSONString(str), value);
                            }
                            if (obj3 == null || serializeWriter.isEnabled(SerializerFeature.WRITE_MAP_NULL_FEATURES)) {
                                if (str2 instanceof String) {
                                    String str18 = str2;
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                        jSONSerializer.println();
                                    }
                                    serializeWriter.writeFieldName(str18, true);
                                } else {
                                    if (!z3) {
                                        serializeWriter.write(44);
                                    }
                                    if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING) && !(str2 instanceof Enum)) {
                                        jSONSerializer.write(JSON.toJSONString(str2));
                                    } else {
                                        jSONSerializer.write((Object) str2);
                                    }
                                    serializeWriter.write(58);
                                }
                                if (obj3 == null) {
                                    serializeWriter.writeNull();
                                    cls5 = cls5;
                                } else {
                                    cls = obj3.getClass();
                                    cls2 = cls5;
                                    if (cls != cls2) {
                                        objectWriter = jSONSerializer.getObjectWriter(cls);
                                        cls3 = cls;
                                    } else {
                                        cls3 = cls2;
                                    }
                                    objectSerializer = objectWriter;
                                    if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName) && (objectSerializer instanceof JavaBeanSerializer)) {
                                        if (type instanceof ParameterizedType) {
                                            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                                            if (actualTypeArguments.length == 2) {
                                                type2 = actualTypeArguments[1];
                                            } else {
                                                type2 = null;
                                            }
                                        } else {
                                            type2 = null;
                                        }
                                        objectWriter = objectSerializer;
                                        ((JavaBeanSerializer) objectSerializer).writeNoneASM(jSONSerializer, obj3, str2, type2, i);
                                    } else {
                                        objectWriter = objectSerializer;
                                        objectWriter.write(jSONSerializer, obj3, str2, null, i);
                                    }
                                    cls5 = cls3;
                                }
                                z3 = false;
                            } else {
                                cls5 = cls5;
                            }
                        }
                        obj3 = objProcessValue;
                        if (obj3 == null) {
                        }
                        if (str2 instanceof String) {
                            String str19 = str2;
                            if (!z3) {
                                serializeWriter.write(44);
                            }
                            if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat)) {
                                jSONSerializer.println();
                            }
                            serializeWriter.writeFieldName(str19, true);
                        } else {
                            if (!z3) {
                                serializeWriter.write(44);
                            }
                            if (!serializeWriter.isEnabled(NON_STRINGKEY_AS_STRING)) {
                                jSONSerializer.write((Object) str2);
                            } else {
                                jSONSerializer.write((Object) str2);
                            }
                            serializeWriter.write(58);
                        }
                        if (obj3 == null) {
                            serializeWriter.writeNull();
                            cls5 = cls5;
                        } else {
                            cls = obj3.getClass();
                            cls2 = cls5;
                            if (cls != cls2) {
                                objectWriter = jSONSerializer.getObjectWriter(cls);
                                cls3 = cls;
                            } else {
                                cls3 = cls2;
                            }
                            objectSerializer = objectWriter;
                            if (!SerializerFeature.isEnabled(i, SerializerFeature.WriteClassName)) {
                                objectWriter = objectSerializer;
                                objectWriter.write(jSONSerializer, obj3, str2, null, i);
                            } else {
                                objectWriter = objectSerializer;
                                objectWriter.write(jSONSerializer, obj3, str2, null, i);
                            }
                            cls5 = cls3;
                        }
                        z3 = false;
                    }
                }
            }
        }
        jSONSerializer.context = serialContext;
        jSONSerializer.decrementIdent();
        if (serializeWriter.isEnabled(SerializerFeature.PrettyFormat) && treeMap.size() > 0) {
            jSONSerializer.println();
        }
        if (z) {
            return;
        }
        serializeWriter.write(125);
    }
}
