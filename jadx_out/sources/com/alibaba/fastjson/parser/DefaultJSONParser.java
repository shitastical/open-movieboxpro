package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.JSONPathException;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.MapDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.PropertyProcessable;
import com.alibaba.fastjson.parser.deserializer.ResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ThrowableDeserializer;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.IntegerCodec;
import com.alibaba.fastjson.serializer.LongCodec;
import com.alibaba.fastjson.serializer.StringCodec;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultJSONParser implements Closeable {
    public static final int NONE = 0;
    public static final int NeedToResolve = 1;
    public static final int TypeNameRedirect = 2;
    private static final Set<Class<?>> primitiveClasses = new HashSet();
    private String[] autoTypeAccept;
    private boolean autoTypeEnable;
    protected ParserConfig config;
    protected ParseContext context;
    private ParseContext[] contextArray;
    private int contextArrayIndex;
    private DateFormat dateFormat;
    private String dateFormatPattern;
    private List<ExtraProcessor> extraProcessors;
    private List<ExtraTypeProvider> extraTypeProviders;
    protected FieldTypeResolver fieldTypeResolver;
    public final Object input;
    protected transient BeanContext lastBeanContext;
    public final JSONLexer lexer;
    public int resolveStatus;
    private List<ResolveTask> resolveTaskList;
    public final SymbolTable symbolTable;

    static {
        Class<?>[] clsArr = {Boolean.TYPE, Byte.TYPE, Short.TYPE, Integer.TYPE, Long.TYPE, Float.TYPE, Double.TYPE, Boolean.class, Byte.class, Short.class, Integer.class, Long.class, Float.class, Double.class, BigInteger.class, BigDecimal.class, String.class};
        for (int i = 0; i < 17; i++) {
            primitiveClasses.add(clsArr[i]);
        }
    }

    public String getDateFomartPattern() {
        return this.dateFormatPattern;
    }

    public DateFormat getDateFormat() {
        if (this.dateFormat == null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormatPattern, this.lexer.getLocale());
            this.dateFormat = simpleDateFormat;
            simpleDateFormat.setTimeZone(this.lexer.getTimeZone());
        }
        return this.dateFormat;
    }

    public void setDateFormat(String str) {
        this.dateFormatPattern = str;
        this.dateFormat = null;
    }

    public void setDateFomrat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public DefaultJSONParser(String str) {
        this(str, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig) {
        this(str, new JSONScanner(str, JSON.DEFAULT_PARSER_FEATURE), parserConfig);
    }

    public DefaultJSONParser(String str, ParserConfig parserConfig, int i) {
        this(str, new JSONScanner(str, i), parserConfig);
    }

    public DefaultJSONParser(char[] cArr, int i, ParserConfig parserConfig, int i2) {
        this(cArr, new JSONScanner(cArr, i, i2), parserConfig);
    }

    public DefaultJSONParser(JSONLexer jSONLexer) {
        this(jSONLexer, ParserConfig.getGlobalInstance());
    }

    public DefaultJSONParser(JSONLexer jSONLexer, ParserConfig parserConfig) {
        this((Object) null, jSONLexer, parserConfig);
    }

    public DefaultJSONParser(Object obj, JSONLexer jSONLexer, ParserConfig parserConfig) {
        this.dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
        this.contextArrayIndex = 0;
        this.resolveStatus = 0;
        this.extraTypeProviders = null;
        this.extraProcessors = null;
        this.fieldTypeResolver = null;
        this.autoTypeAccept = null;
        this.lexer = jSONLexer;
        this.input = obj;
        this.config = parserConfig;
        this.symbolTable = parserConfig.symbolTable;
        char current = jSONLexer.getCurrent();
        if (current == '{') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 12;
        } else if (current == '[') {
            jSONLexer.next();
            ((JSONLexerBase) jSONLexer).token = 14;
        } else {
            jSONLexer.nextToken();
        }
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public String getInput() {
        Object obj = this.input;
        if (obj instanceof char[]) {
            return new String((char[]) obj);
        }
        return obj.toString();
    }

    /* JADX WARN: Code duplicated, block: B:109:0x0203 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:213:0x03b3  */
    /* JADX WARN: Code duplicated, block: B:225:0x03ed A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:232:0x03fe A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:234:0x0404  */
    /* JADX WARN: Code duplicated, block: B:242:0x0418 A[Catch: all -> 0x0608, TRY_ENTER, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:244:0x0427 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:246:0x0432 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:249:0x0441  */
    /* JADX WARN: Code duplicated, block: B:262:0x0474 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:265:0x047f A[Catch: all -> 0x0608, TRY_LEAVE, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:268:0x048f A[Catch: all -> 0x0608, TRY_ENTER, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:272:0x04b2 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:274:0x04bc A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:276:0x04c4 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:279:0x04d2 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:285:0x04e8 A[Catch: all -> 0x0608, TRY_ENTER, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:287:0x04f0  */
    /* JADX WARN: Code duplicated, block: B:290:0x04f7  */
    /* JADX WARN: Code duplicated, block: B:292:0x04fb A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:294:0x0500 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:297:0x050a  */
    /* JADX WARN: Code duplicated, block: B:300:0x0513 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:301:0x0524 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:303:0x0531 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:304:0x0536  */
    /* JADX WARN: Code duplicated, block: B:307:0x053b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:308:0x053d A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:309:0x0542  */
    /* JADX WARN: Code duplicated, block: B:312:0x054b A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:313:0x0556  */
    /* JADX WARN: Code duplicated, block: B:315:0x055a A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:317:0x0560 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:320:0x0566 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:323:0x0572 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:329:0x0587 A[Catch: all -> 0x0608, TRY_ENTER, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:331:0x058f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:332:0x0591 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:333:0x0596 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:336:0x05b6 A[Catch: all -> 0x0608, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:341:0x05cf A[Catch: all -> 0x0608, TRY_ENTER, TryCatch #2 {all -> 0x0608, blocks: (B:24:0x006f, B:26:0x0073, B:29:0x007d, B:32:0x0090, B:36:0x00aa, B:109:0x0203, B:110:0x0209, B:112:0x0214, B:114:0x021c, B:118:0x0230, B:120:0x023e, B:123:0x0251, B:125:0x025e, B:127:0x026b, B:128:0x026e, B:130:0x0278, B:131:0x0286, B:133:0x028c, B:135:0x029a, B:137:0x02a2, B:142:0x02b1, B:143:0x02b7, B:145:0x02bf, B:146:0x02c4, B:150:0x02cd, B:151:0x02d4, B:152:0x02d5, B:155:0x02df, B:157:0x02e3, B:159:0x02e9, B:160:0x02ec, B:162:0x02f2, B:165:0x02ff, B:171:0x0319, B:172:0x031d, B:121:0x0244, B:179:0x032c, B:181:0x0334, B:183:0x033e, B:185:0x034f, B:187:0x0353, B:189:0x0359, B:192:0x035e, B:194:0x0362, B:214:0x03b4, B:216:0x03bc, B:219:0x03c5, B:220:0x03ca, B:196:0x0369, B:198:0x0371, B:200:0x0375, B:201:0x0378, B:202:0x0384, B:205:0x038d, B:207:0x0391, B:208:0x0394, B:210:0x0398, B:211:0x039c, B:212:0x03a8, B:221:0x03cb, B:222:0x03e9, B:225:0x03ed, B:227:0x03f1, B:229:0x03f5, B:231:0x03fb, B:232:0x03fe, B:236:0x0406, B:242:0x0418, B:244:0x0427, B:246:0x0432, B:247:0x043a, B:248:0x043d, B:260:0x0469, B:262:0x0474, B:265:0x047f, B:268:0x048f, B:269:0x04ad, B:255:0x044d, B:257:0x0457, B:259:0x0466, B:258:0x045c, B:272:0x04b2, B:274:0x04bc, B:276:0x04c4, B:277:0x04c7, B:279:0x04d2, B:280:0x04d6, B:282:0x04e1, B:285:0x04e8, B:288:0x04f1, B:289:0x04f6, B:292:0x04fb, B:294:0x0500, B:298:0x050b, B:300:0x0513, B:303:0x0531, B:305:0x0537, B:308:0x053d, B:310:0x0543, B:312:0x054b, B:315:0x055a, B:318:0x0562, B:320:0x0566, B:321:0x056d, B:323:0x0572, B:324:0x0575, B:326:0x057d, B:329:0x0587, B:332:0x0591, B:333:0x0596, B:334:0x059b, B:335:0x05b5, B:301:0x0524, B:336:0x05b6, B:338:0x05c8, B:341:0x05cf, B:344:0x05dd, B:345:0x05fb, B:39:0x00bc, B:40:0x00da, B:42:0x00dd, B:44:0x00e8, B:46:0x00ec, B:48:0x00f0, B:50:0x00f6, B:51:0x00f9, B:58:0x0108, B:60:0x0110, B:63:0x0120, B:64:0x0138, B:65:0x0139, B:66:0x013e, B:77:0x0153, B:78:0x0159, B:80:0x0160, B:82:0x0169, B:84:0x0171, B:86:0x0176, B:89:0x017e, B:90:0x0196, B:81:0x0165, B:91:0x0197, B:92:0x01af, B:98:0x01b9, B:100:0x01c1, B:103:0x01d2, B:104:0x01f2, B:105:0x01f3, B:106:0x01f8, B:107:0x01f9, B:346:0x05fc, B:347:0x0601, B:348:0x0602, B:349:0x0607), top: B:356:0x006f, inners: #0, #1 }] */
    /* JADX WARN: Code duplicated, block: B:369:0x047d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:370:0x04e1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:371:0x04f1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:372:0x057d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:373:0x059b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:374:0x05c8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:375:0x05dd A[SYNTHETIC] */
    /* JADX WARN: Instruction removed from duplicated block: B:268:0x048f, please report this as an issue */
    public final Object parseObject(Map map, Object obj) {
        Object objScanSymbolUnQuoted;
        boolean z;
        char current;
        boolean z2;
        Map jSONObject;
        ParseContext context;
        Object object;
        String string;
        Type typeResolve;
        JSONArray jSONArray;
        JSONArray array;
        Object obj2;
        char current2;
        String strStringVal;
        Object obj3;
        JSONScanner jSONScanner;
        Object time;
        ParseContext parseContext;
        ParseContext context2;
        Object obj4;
        Object obj5;
        Class<?> clsCheckAutoType;
        Object map2;
        FieldDeserializer fieldDeserializer;
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
            return null;
        }
        if (jSONLexer.token() == 13) {
            jSONLexer.nextToken();
            return map;
        }
        if (jSONLexer.token() == 4 && jSONLexer.stringVal().length() == 0) {
            jSONLexer.nextToken();
            return map;
        }
        if (jSONLexer.token() != 12 && jSONLexer.token() != 16) {
            throw new JSONException("syntax error, expect {, actual " + jSONLexer.tokenName() + ", " + jSONLexer.info());
        }
        ParseContext parseContext2 = this.context;
        try {
            Map innerMap = map instanceof JSONObject ? ((JSONObject) map).getInnerMap() : map;
            boolean z3 = false;
            while (true) {
                jSONLexer.skipWhitespace();
                char current3 = jSONLexer.getCurrent();
                if (jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (current3 == ',') {
                        jSONLexer.next();
                        jSONLexer.skipWhitespace();
                        current3 = jSONLexer.getCurrent();
                    }
                }
                boolean z4 = true;
                if (current3 == '\"') {
                    objScanSymbolUnQuoted = jSONLexer.scanSymbol(this.symbolTable, Typography.quote);
                    jSONLexer.skipWhitespace();
                    if (jSONLexer.getCurrent() != ':') {
                        throw new JSONException("expect ':' at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                    }
                } else {
                    if (current3 == '}') {
                        jSONLexer.next();
                        jSONLexer.resetStringPosition();
                        jSONLexer.nextToken();
                        if (!z3) {
                            ParseContext parseContext3 = this.context;
                            if (parseContext3 != null && obj == parseContext3.fieldName && map == this.context.object) {
                                parseContext2 = this.context;
                            } else {
                                ParseContext context3 = setContext(map, obj);
                                if (parseContext2 == null) {
                                    parseContext2 = context3;
                                }
                            }
                        }
                        setContext(parseContext2);
                        return map;
                    }
                    if (current3 == '\'') {
                        if (!jSONLexer.isEnabled(Feature.AllowSingleQuotes)) {
                            throw new JSONException("syntax error");
                        }
                        objScanSymbolUnQuoted = jSONLexer.scanSymbol(this.symbolTable, '\'');
                        jSONLexer.skipWhitespace();
                        if (jSONLexer.getCurrent() != ':') {
                            throw new JSONException("expect ':' at " + jSONLexer.pos());
                        }
                    } else {
                        if (current3 == 26) {
                            throw new JSONException("syntax error");
                        }
                        if (current3 == ',') {
                            throw new JSONException("syntax error");
                        }
                        if ((current3 < '0' || current3 > '9') && current3 != '-') {
                            if (current3 == '{' || current3 == '[') {
                                jSONLexer.nextToken();
                                objScanSymbolUnQuoted = parse();
                                z = true;
                            } else {
                                if (!jSONLexer.isEnabled(Feature.AllowUnQuotedFieldNames)) {
                                    throw new JSONException("syntax error");
                                }
                                objScanSymbolUnQuoted = jSONLexer.scanSymbolUnQuoted(this.symbolTable);
                                jSONLexer.skipWhitespace();
                                char current4 = jSONLexer.getCurrent();
                                if (current4 != ':') {
                                    throw new JSONException("expect ':' at " + jSONLexer.pos() + ", actual " + current4);
                                }
                            }
                            if (!z) {
                                jSONLexer.next();
                                jSONLexer.skipWhitespace();
                            }
                            current = jSONLexer.getCurrent();
                            jSONLexer.resetStringPosition();
                            if (objScanSymbolUnQuoted == JSON.DEFAULT_TYPE_KEY || jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                                if (objScanSymbolUnQuoted != "$ref" && parseContext2 != null && !jSONLexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                                    jSONLexer.nextToken(4);
                                    if (jSONLexer.token() != 4) {
                                        throw new JSONException("illegal ref, " + JSONToken.name(jSONLexer.token()));
                                    }
                                    String strStringVal2 = jSONLexer.stringVal();
                                    jSONLexer.nextToken(13);
                                    if ("@".equals(strStringVal2)) {
                                        ParseContext parseContext4 = this.context;
                                        if (parseContext4 != null) {
                                            Object obj6 = parseContext4.object;
                                            if ((obj6 instanceof Object[]) || (obj6 instanceof Collection)) {
                                                obj4 = obj6;
                                            } else if (parseContext4.parent != null) {
                                                obj4 = parseContext4.parent.object;
                                            } else {
                                                obj4 = null;
                                            }
                                        } else {
                                            obj4 = null;
                                        }
                                    } else if (!"..".equals(strStringVal2)) {
                                        if ("$".equals(strStringVal2)) {
                                            ParseContext parseContext5 = parseContext2;
                                            while (parseContext5.parent != null) {
                                                parseContext5 = parseContext5.parent;
                                            }
                                            if (parseContext5.object != null) {
                                                obj4 = parseContext5.object;
                                            } else {
                                                addResolveTask(new ResolveTask(parseContext5, strStringVal2));
                                                setResolveStatus(1);
                                            }
                                        } else {
                                            addResolveTask(new ResolveTask(parseContext2, strStringVal2));
                                            setResolveStatus(1);
                                        }
                                        obj4 = null;
                                    } else if (parseContext2.object != null) {
                                        obj4 = parseContext2.object;
                                    } else {
                                        addResolveTask(new ResolveTask(parseContext2, strStringVal2));
                                        setResolveStatus(1);
                                        obj4 = null;
                                    }
                                    if (jSONLexer.token() != 13) {
                                        throw new JSONException("syntax error");
                                    }
                                    jSONLexer.nextToken(16);
                                    setContext(parseContext2);
                                    return obj4;
                                }
                                if (!z3) {
                                    parseContext = this.context;
                                    if (parseContext == null && obj == parseContext.fieldName && map == this.context.object) {
                                        parseContext2 = this.context;
                                    } else {
                                        context2 = setContext(map, obj);
                                        if (parseContext2 == null) {
                                            parseContext2 = context2;
                                        }
                                        z3 = true;
                                    }
                                }
                                if (map.getClass() == JSONObject.class && objScanSymbolUnQuoted == null) {
                                    objScanSymbolUnQuoted = "null";
                                }
                                if (current == '\"') {
                                    jSONLexer.scanString();
                                    strStringVal = jSONLexer.stringVal();
                                    obj3 = strStringVal;
                                    if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                                        jSONScanner = new JSONScanner(strStringVal);
                                        time = strStringVal;
                                        if (jSONScanner.scanISO8601DateIfMatch()) {
                                            time = jSONScanner.getCalendar().getTime();
                                        }
                                        jSONScanner.close();
                                        obj3 = time;
                                    }
                                    innerMap.put(objScanSymbolUnQuoted, obj3);
                                    obj2 = obj3;
                                } else if ((current < '0' && current <= '9') || current == '-') {
                                    jSONLexer.scanNumber();
                                    Number numberIntegerValue = jSONLexer.token() == 2 ? jSONLexer.integerValue() : jSONLexer.decimalValue(jSONLexer.isEnabled(Feature.UseBigDecimal));
                                    innerMap.put(objScanSymbolUnQuoted, numberIntegerValue);
                                    obj2 = numberIntegerValue;
                                } else if (current == '[') {
                                    jSONLexer.nextToken();
                                    jSONArray = new JSONArray();
                                    if (obj != null) {
                                        obj.getClass();
                                    }
                                    if (obj == null) {
                                        setContext(parseContext2);
                                    }
                                    parseArray(jSONArray, objScanSymbolUnQuoted);
                                    array = jSONArray;
                                    if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                                        array = jSONArray.toArray();
                                    }
                                    innerMap.put(objScanSymbolUnQuoted, array);
                                    if (jSONLexer.token() == 13) {
                                        jSONLexer.nextToken();
                                        setContext(parseContext2);
                                        return map;
                                    }
                                    if (jSONLexer.token() != 16) {
                                        throw new JSONException("syntax error");
                                    }
                                } else if (current == '{') {
                                    jSONLexer.nextToken();
                                    if (obj == null && obj.getClass() == Integer.class) {
                                        z2 = true;
                                    } else {
                                        z2 = false;
                                    }
                                    if (jSONLexer.isEnabled(Feature.CustomMapDeserializer)) {
                                        jSONObject = ((MapDeserializer) this.config.getDeserializer(Map.class)).createMap(Map.class);
                                    } else {
                                        jSONObject = new JSONObject(jSONLexer.isEnabled(Feature.OrderedField));
                                    }
                                    if (z2) {
                                        context = null;
                                    } else {
                                        context = setContext(parseContext2, jSONObject, objScanSymbolUnQuoted);
                                    }
                                    if (this.fieldTypeResolver == null) {
                                        object = null;
                                        z4 = false;
                                    } else {
                                        if (objScanSymbolUnQuoted != null) {
                                            string = objScanSymbolUnQuoted.toString();
                                        } else {
                                            string = null;
                                        }
                                        typeResolve = this.fieldTypeResolver.resolve(map, string);
                                        if (typeResolve != null) {
                                            object = this.config.getDeserializer(typeResolve).deserialze(this, typeResolve, objScanSymbolUnQuoted);
                                        } else {
                                            object = null;
                                            z4 = false;
                                        }
                                    }
                                    if (!z4) {
                                        object = parseObject(jSONObject, objScanSymbolUnQuoted);
                                    }
                                    if (context != null && jSONObject != object) {
                                        context.object = map;
                                    }
                                    if (objScanSymbolUnQuoted != null) {
                                        checkMapResolve(map, objScanSymbolUnQuoted.toString());
                                    }
                                    innerMap.put(objScanSymbolUnQuoted, object);
                                    if (z2) {
                                        setContext(object, objScanSymbolUnQuoted);
                                    }
                                    if (jSONLexer.token() == 13) {
                                        jSONLexer.nextToken();
                                        setContext(parseContext2);
                                        setContext(parseContext2);
                                        return map;
                                    }
                                    if (jSONLexer.token() != 16) {
                                        throw new JSONException("syntax error, " + jSONLexer.tokenName());
                                    }
                                    if (z2) {
                                        popContext();
                                    } else {
                                        setContext(parseContext2);
                                    }
                                } else {
                                    jSONLexer.nextToken();
                                    innerMap.put(objScanSymbolUnQuoted, parse());
                                    if (jSONLexer.token() == 13) {
                                        jSONLexer.nextToken();
                                        setContext(parseContext2);
                                        return map;
                                    }
                                    if (jSONLexer.token() != 16) {
                                        throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                                    }
                                }
                                jSONLexer.skipWhitespace();
                                current2 = jSONLexer.getCurrent();
                                if (current2 == ',') {
                                    if (current2 == '}') {
                                        jSONLexer.next();
                                        jSONLexer.resetStringPosition();
                                        jSONLexer.nextToken();
                                        setContext(obj2, objScanSymbolUnQuoted);
                                        setContext(parseContext2);
                                        return map;
                                    }
                                    throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                                }
                                jSONLexer.next();
                            } else {
                                String strScanSymbol = jSONLexer.scanSymbol(this.symbolTable, Typography.quote);
                                if (jSONLexer.isEnabled(Feature.IgnoreAutoType)) {
                                    obj5 = null;
                                } else {
                                    if (map == null || !map.getClass().getName().equals(strScanSymbol)) {
                                        obj5 = null;
                                        clsCheckAutoType = this.config.checkAutoType(strScanSymbol, null, jSONLexer.getFeatures());
                                    } else {
                                        clsCheckAutoType = map.getClass();
                                        obj5 = null;
                                    }
                                    if (clsCheckAutoType != null) {
                                        jSONLexer.nextToken(16);
                                        if (jSONLexer.token() == 13) {
                                            jSONLexer.nextToken(16);
                                            try {
                                                ObjectDeserializer deserializer = this.config.getDeserializer(clsCheckAutoType);
                                                if (deserializer instanceof JavaBeanDeserializer) {
                                                    JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
                                                    map2 = javaBeanDeserializer.createInstance(this, clsCheckAutoType);
                                                    for (Map.Entry entry : innerMap.entrySet()) {
                                                        Object key = entry.getKey();
                                                        if ((key instanceof String) && (fieldDeserializer = javaBeanDeserializer.getFieldDeserializer((String) key)) != null) {
                                                            fieldDeserializer.setValue(map2, entry.getValue());
                                                        }
                                                    }
                                                } else {
                                                    map2 = obj5;
                                                }
                                                if (map2 == null) {
                                                    map2 = clsCheckAutoType == Cloneable.class ? new HashMap() : "java.util.Collections$EmptyMap".equals(strScanSymbol) ? Collections.emptyMap() : clsCheckAutoType.newInstance();
                                                }
                                                setContext(parseContext2);
                                                return map2;
                                            } catch (Exception e) {
                                                throw new JSONException("create instance error", e);
                                            }
                                        }
                                        setResolveStatus(2);
                                        ParseContext parseContext6 = this.context;
                                        if (parseContext6 != null && obj != null && !(obj instanceof Integer) && !(parseContext6.fieldName instanceof Integer)) {
                                            popContext();
                                        }
                                        if (map.size() > 0) {
                                            Object objCast = TypeUtils.cast((Object) map, (Class<Object>) clsCheckAutoType, this.config);
                                            parseObject(objCast);
                                            setContext(parseContext2);
                                            return objCast;
                                        }
                                        ObjectDeserializer deserializer2 = this.config.getDeserializer(clsCheckAutoType);
                                        Class<?> cls = deserializer2.getClass();
                                        if (JavaBeanDeserializer.class.isAssignableFrom(cls) && cls != JavaBeanDeserializer.class && cls != ThrowableDeserializer.class) {
                                            setResolveStatus(0);
                                        }
                                        Object objDeserialze = deserializer2.deserialze(this, clsCheckAutoType, obj);
                                        setContext(parseContext2);
                                        return objDeserialze;
                                    }
                                    innerMap.put(JSON.DEFAULT_TYPE_KEY, strScanSymbol);
                                }
                            }
                        } else {
                            jSONLexer.resetStringPosition();
                            jSONLexer.scanNumber();
                            try {
                                Object objIntegerValue = jSONLexer.token() == 2 ? jSONLexer.integerValue() : jSONLexer.decimalValue(true);
                                Object string2 = objIntegerValue;
                                if (jSONLexer.isEnabled(Feature.NonStringKeyAsString)) {
                                    string2 = objIntegerValue.toString();
                                }
                                objScanSymbolUnQuoted = string2;
                                if (jSONLexer.getCurrent() != ':') {
                                    throw new JSONException("parse number key error" + jSONLexer.info());
                                }
                            } catch (NumberFormatException unused) {
                                throw new JSONException("parse number key error" + jSONLexer.info());
                            }
                        }
                    }
                }
                z = false;
                if (!z) {
                    jSONLexer.next();
                    jSONLexer.skipWhitespace();
                }
                current = jSONLexer.getCurrent();
                jSONLexer.resetStringPosition();
                if (objScanSymbolUnQuoted == JSON.DEFAULT_TYPE_KEY) {
                }
                if (objScanSymbolUnQuoted != "$ref") {
                }
                if (!z3) {
                    parseContext = this.context;
                    if (parseContext == null) {
                        context2 = setContext(map, obj);
                        if (parseContext2 == null) {
                            parseContext2 = context2;
                        }
                        z3 = true;
                    } else {
                        context2 = setContext(map, obj);
                        if (parseContext2 == null) {
                            parseContext2 = context2;
                        }
                        z3 = true;
                    }
                }
                if (map.getClass() == JSONObject.class) {
                    objScanSymbolUnQuoted = "null";
                }
                if (current == '\"') {
                    jSONLexer.scanString();
                    strStringVal = jSONLexer.stringVal();
                    obj3 = strStringVal;
                    if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                        jSONScanner = new JSONScanner(strStringVal);
                        time = strStringVal;
                        if (jSONScanner.scanISO8601DateIfMatch()) {
                            time = jSONScanner.getCalendar().getTime();
                        }
                        jSONScanner.close();
                        obj3 = time;
                    }
                    innerMap.put(objScanSymbolUnQuoted, obj3);
                    obj2 = obj3;
                } else if (current < '0') {
                    if (current == '[') {
                        jSONLexer.nextToken();
                        jSONArray = new JSONArray();
                        if (obj != null) {
                            obj.getClass();
                        }
                        if (obj == null) {
                            setContext(parseContext2);
                        }
                        parseArray(jSONArray, objScanSymbolUnQuoted);
                        array = jSONArray;
                        if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                            array = jSONArray.toArray();
                        }
                        innerMap.put(objScanSymbolUnQuoted, array);
                        if (jSONLexer.token() == 13) {
                            jSONLexer.nextToken();
                            setContext(parseContext2);
                            return map;
                        }
                        if (jSONLexer.token() != 16) {
                            throw new JSONException("syntax error");
                        }
                    } else if (current == '{') {
                        jSONLexer.nextToken();
                        if (obj == null) {
                            z2 = false;
                        } else {
                            z2 = false;
                        }
                        if (jSONLexer.isEnabled(Feature.CustomMapDeserializer)) {
                            jSONObject = ((MapDeserializer) this.config.getDeserializer(Map.class)).createMap(Map.class);
                        } else {
                            jSONObject = new JSONObject(jSONLexer.isEnabled(Feature.OrderedField));
                        }
                        if (z2) {
                            context = setContext(parseContext2, jSONObject, objScanSymbolUnQuoted);
                        } else {
                            context = null;
                        }
                        if (this.fieldTypeResolver == null) {
                            object = null;
                            z4 = false;
                        } else {
                            if (objScanSymbolUnQuoted != null) {
                                string = objScanSymbolUnQuoted.toString();
                            } else {
                                string = null;
                            }
                            typeResolve = this.fieldTypeResolver.resolve(map, string);
                            if (typeResolve != null) {
                                object = this.config.getDeserializer(typeResolve).deserialze(this, typeResolve, objScanSymbolUnQuoted);
                            } else {
                                object = null;
                                z4 = false;
                            }
                        }
                        if (!z4) {
                            object = parseObject(jSONObject, objScanSymbolUnQuoted);
                        }
                        if (context != null) {
                            context.object = map;
                        }
                        if (objScanSymbolUnQuoted != null) {
                            checkMapResolve(map, objScanSymbolUnQuoted.toString());
                        }
                        innerMap.put(objScanSymbolUnQuoted, object);
                        if (z2) {
                            setContext(object, objScanSymbolUnQuoted);
                        }
                        if (jSONLexer.token() == 13) {
                            jSONLexer.nextToken();
                            setContext(parseContext2);
                            setContext(parseContext2);
                            return map;
                        }
                        if (jSONLexer.token() != 16) {
                            throw new JSONException("syntax error, " + jSONLexer.tokenName());
                        }
                        if (z2) {
                            popContext();
                        } else {
                            setContext(parseContext2);
                        }
                    } else {
                        jSONLexer.nextToken();
                        innerMap.put(objScanSymbolUnQuoted, parse());
                        if (jSONLexer.token() == 13) {
                            jSONLexer.nextToken();
                            setContext(parseContext2);
                            return map;
                        }
                        if (jSONLexer.token() != 16) {
                            throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                        }
                    }
                } else if (current == '[') {
                    jSONLexer.nextToken();
                    jSONArray = new JSONArray();
                    if (obj != null) {
                        obj.getClass();
                    }
                    if (obj == null) {
                        setContext(parseContext2);
                    }
                    parseArray(jSONArray, objScanSymbolUnQuoted);
                    array = jSONArray;
                    if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                        array = jSONArray.toArray();
                    }
                    innerMap.put(objScanSymbolUnQuoted, array);
                    if (jSONLexer.token() == 13) {
                        jSONLexer.nextToken();
                        setContext(parseContext2);
                        return map;
                    }
                    if (jSONLexer.token() != 16) {
                        throw new JSONException("syntax error");
                    }
                } else if (current == '{') {
                    jSONLexer.nextToken();
                    if (obj == null) {
                        z2 = false;
                    } else {
                        z2 = false;
                    }
                    if (jSONLexer.isEnabled(Feature.CustomMapDeserializer)) {
                        jSONObject = ((MapDeserializer) this.config.getDeserializer(Map.class)).createMap(Map.class);
                    } else {
                        jSONObject = new JSONObject(jSONLexer.isEnabled(Feature.OrderedField));
                    }
                    if (z2) {
                        context = setContext(parseContext2, jSONObject, objScanSymbolUnQuoted);
                    } else {
                        context = null;
                    }
                    if (this.fieldTypeResolver == null) {
                        object = null;
                        z4 = false;
                    } else {
                        if (objScanSymbolUnQuoted != null) {
                            string = objScanSymbolUnQuoted.toString();
                        } else {
                            string = null;
                        }
                        typeResolve = this.fieldTypeResolver.resolve(map, string);
                        if (typeResolve != null) {
                            object = this.config.getDeserializer(typeResolve).deserialze(this, typeResolve, objScanSymbolUnQuoted);
                        } else {
                            object = null;
                            z4 = false;
                        }
                    }
                    if (!z4) {
                        object = parseObject(jSONObject, objScanSymbolUnQuoted);
                    }
                    if (context != null) {
                        context.object = map;
                    }
                    if (objScanSymbolUnQuoted != null) {
                        checkMapResolve(map, objScanSymbolUnQuoted.toString());
                    }
                    innerMap.put(objScanSymbolUnQuoted, object);
                    if (z2) {
                        setContext(object, objScanSymbolUnQuoted);
                    }
                    if (jSONLexer.token() == 13) {
                        jSONLexer.nextToken();
                        setContext(parseContext2);
                        setContext(parseContext2);
                        return map;
                    }
                    if (jSONLexer.token() != 16) {
                        throw new JSONException("syntax error, " + jSONLexer.tokenName());
                    }
                    if (z2) {
                        popContext();
                    } else {
                        setContext(parseContext2);
                    }
                } else {
                    jSONLexer.nextToken();
                    innerMap.put(objScanSymbolUnQuoted, parse());
                    if (jSONLexer.token() == 13) {
                        jSONLexer.nextToken();
                        setContext(parseContext2);
                        return map;
                    }
                    if (jSONLexer.token() != 16) {
                        throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                    }
                }
                jSONLexer.skipWhitespace();
                current2 = jSONLexer.getCurrent();
                if (current2 == ',') {
                    if (current2 == '}') {
                        jSONLexer.next();
                        jSONLexer.resetStringPosition();
                        jSONLexer.nextToken();
                        setContext(obj2, objScanSymbolUnQuoted);
                        setContext(parseContext2);
                        return map;
                    }
                    throw new JSONException("syntax error, position at " + jSONLexer.pos() + ", name " + objScanSymbolUnQuoted);
                }
                jSONLexer.next();
            }
        } catch (Throwable th) {
            setContext(parseContext2);
            throw th;
        }
    }

    public ParserConfig getConfig() {
        return this.config;
    }

    public void setConfig(ParserConfig parserConfig) {
        this.config = parserConfig;
    }

    public <T> T parseObject(Class<T> cls) {
        return (T) parseObject(cls, (Object) null);
    }

    public <T> T parseObject(Type type) {
        return (T) parseObject(type, (Object) null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T parseObject(Type type, Object obj) {
        int i = this.lexer.token();
        if (i == 8) {
            this.lexer.nextToken();
            return null;
        }
        if (i == 4) {
            if (type == byte[].class) {
                T t = (T) this.lexer.bytesValue();
                this.lexer.nextToken();
                return t;
            }
            if (type == char[].class) {
                String strStringVal = this.lexer.stringVal();
                this.lexer.nextToken();
                return (T) strStringVal.toCharArray();
            }
        }
        try {
            return (T) this.config.getDeserializer(type).deserialze(this, type, obj);
        } catch (JSONException e) {
            throw e;
        } catch (Throwable th) {
            throw new JSONException(th.getMessage(), th);
        }
    }

    public <T> List<T> parseArray(Class<T> cls) {
        ArrayList arrayList = new ArrayList();
        parseArray((Class<?>) cls, (Collection) arrayList);
        return arrayList;
    }

    public void parseArray(Class<?> cls, Collection collection) {
        parseArray((Type) cls, collection);
    }

    public void parseArray(Type type, Collection collection) {
        parseArray(type, collection, null);
    }

    public void parseArray(Type type, Collection collection, Object obj) {
        ObjectDeserializer deserializer;
        int i = this.lexer.token();
        if (i == 21 || i == 22) {
            this.lexer.nextToken();
            i = this.lexer.token();
        }
        if (i != 14) {
            throw new JSONException("exepct '[', but " + JSONToken.name(i) + ", " + this.lexer.info());
        }
        if (Integer.TYPE == type) {
            deserializer = IntegerCodec.instance;
            this.lexer.nextToken(2);
        } else if (String.class == type) {
            deserializer = StringCodec.instance;
            this.lexer.nextToken(4);
        } else {
            deserializer = this.config.getDeserializer(type);
            this.lexer.nextToken(deserializer.getFastMatchToken());
        }
        ParseContext parseContext = this.context;
        setContext(collection, obj);
        int i2 = 0;
        while (true) {
            try {
                if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (this.lexer.token() == 16) {
                        this.lexer.nextToken();
                    }
                }
                if (this.lexer.token() != 15) {
                    Object objDeserialze = null;
                    if (Integer.TYPE == type) {
                        collection.add(IntegerCodec.instance.deserialze(this, null, null));
                    } else if (String.class == type) {
                        if (this.lexer.token() == 4) {
                            objDeserialze = this.lexer.stringVal();
                            this.lexer.nextToken(16);
                        } else {
                            Object obj2 = parse();
                            if (obj2 != null) {
                                objDeserialze = obj2.toString();
                            }
                        }
                        collection.add(objDeserialze);
                    } else {
                        if (this.lexer.token() == 8) {
                            this.lexer.nextToken();
                        } else {
                            objDeserialze = deserializer.deserialze(this, type, Integer.valueOf(i2));
                        }
                        collection.add(objDeserialze);
                        checkListResolve(collection);
                    }
                    if (this.lexer.token() == 16) {
                        this.lexer.nextToken(deserializer.getFastMatchToken());
                    }
                    i2++;
                } else {
                    setContext(parseContext);
                    this.lexer.nextToken(16);
                    return;
                }
            } catch (Throwable th) {
                setContext(parseContext);
                throw th;
            }
        }
    }

    public Object[] parseArray(Type[] typeArr) {
        Object objCast;
        Class<?> componentType;
        boolean zIsArray;
        int i = 8;
        if (this.lexer.token() == 8) {
            this.lexer.nextToken(16);
            return null;
        }
        int i2 = 14;
        if (this.lexer.token() != 14) {
            throw new JSONException("syntax error : " + this.lexer.tokenName());
        }
        Object[] objArr = new Object[typeArr.length];
        if (typeArr.length == 0) {
            this.lexer.nextToken(15);
            if (this.lexer.token() != 15) {
                throw new JSONException("syntax error");
            }
            this.lexer.nextToken(16);
            return new Object[0];
        }
        this.lexer.nextToken(2);
        int i3 = 0;
        while (i3 < typeArr.length) {
            if (this.lexer.token() == i) {
                this.lexer.nextToken(16);
                objCast = null;
            } else {
                Type type = typeArr[i3];
                if (type == Integer.TYPE || type == Integer.class) {
                    if (this.lexer.token() == 2) {
                        objCast = Integer.valueOf(this.lexer.intValue());
                        this.lexer.nextToken(16);
                    } else {
                        objCast = TypeUtils.cast(parse(), type, this.config);
                    }
                } else if (type == String.class) {
                    if (this.lexer.token() == 4) {
                        objCast = this.lexer.stringVal();
                        this.lexer.nextToken(16);
                    } else {
                        objCast = TypeUtils.cast(parse(), type, this.config);
                    }
                } else {
                    if (i3 == typeArr.length - 1 && (type instanceof Class)) {
                        Class cls = (Class) type;
                        zIsArray = cls.isArray();
                        componentType = cls.getComponentType();
                    } else {
                        componentType = null;
                        zIsArray = false;
                    }
                    if (zIsArray && this.lexer.token() != i2) {
                        ArrayList arrayList = new ArrayList();
                        ObjectDeserializer deserializer = this.config.getDeserializer(componentType);
                        int fastMatchToken = deserializer.getFastMatchToken();
                        if (this.lexer.token() != 15) {
                            while (true) {
                                arrayList.add(deserializer.deserialze(this, type, null));
                                if (this.lexer.token() != 16) {
                                    break;
                                }
                                this.lexer.nextToken(fastMatchToken);
                            }
                            if (this.lexer.token() != 15) {
                                throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
                            }
                        }
                        objCast = TypeUtils.cast(arrayList, type, this.config);
                    } else {
                        objCast = this.config.getDeserializer(type).deserialze(this, type, Integer.valueOf(i3));
                    }
                }
            }
            objArr[i3] = objCast;
            if (this.lexer.token() == 15) {
                break;
            }
            if (this.lexer.token() != 16) {
                throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
            }
            if (i3 == typeArr.length - 1) {
                this.lexer.nextToken(15);
            } else {
                this.lexer.nextToken(2);
            }
            i3++;
            i = 8;
            i2 = 14;
        }
        if (this.lexer.token() != 15) {
            throw new JSONException("syntax error");
        }
        this.lexer.nextToken(16);
        return objArr;
    }

    public void parseObject(Object obj) {
        Object objDeserialze;
        Class<?> cls = obj.getClass();
        ObjectDeserializer deserializer = this.config.getDeserializer(cls);
        JavaBeanDeserializer javaBeanDeserializer = deserializer instanceof JavaBeanDeserializer ? (JavaBeanDeserializer) deserializer : null;
        if (this.lexer.token() != 12 && this.lexer.token() != 16) {
            throw new JSONException("syntax error, expect {, actual " + this.lexer.tokenName());
        }
        while (true) {
            String strScanSymbol = this.lexer.scanSymbol(this.symbolTable);
            if (strScanSymbol == null) {
                if (this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                } else if (this.lexer.token() != 16 || !this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                }
            }
            FieldDeserializer fieldDeserializer = javaBeanDeserializer != null ? javaBeanDeserializer.getFieldDeserializer(strScanSymbol) : null;
            if (fieldDeserializer == null) {
                if (!this.lexer.isEnabled(Feature.IgnoreNotMatch)) {
                    throw new JSONException("setter not found, class " + cls.getName() + ", property " + strScanSymbol);
                }
                this.lexer.nextTokenWithColon();
                parse();
                if (this.lexer.token() == 13) {
                    this.lexer.nextToken();
                    return;
                }
            } else {
                Class<?> cls2 = fieldDeserializer.fieldInfo.fieldClass;
                Type type = fieldDeserializer.fieldInfo.fieldType;
                if (cls2 == Integer.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    objDeserialze = IntegerCodec.instance.deserialze(this, type, null);
                } else if (cls2 == String.class) {
                    this.lexer.nextTokenWithColon(4);
                    objDeserialze = StringCodec.deserialze(this);
                } else if (cls2 == Long.TYPE) {
                    this.lexer.nextTokenWithColon(2);
                    objDeserialze = LongCodec.instance.deserialze(this, type, null);
                } else {
                    ObjectDeserializer deserializer2 = this.config.getDeserializer(cls2, type);
                    this.lexer.nextTokenWithColon(deserializer2.getFastMatchToken());
                    objDeserialze = deserializer2.deserialze(this, type, null);
                }
                fieldDeserializer.setValue(obj, objDeserialze);
                if (this.lexer.token() != 16 && this.lexer.token() == 13) {
                    this.lexer.nextToken(16);
                    return;
                }
            }
        }
    }

    public Object parseArrayWithType(Type type) {
        if (this.lexer.token() == 8) {
            this.lexer.nextToken();
            return null;
        }
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        if (actualTypeArguments.length != 1) {
            throw new JSONException("not support type " + type);
        }
        Type type2 = actualTypeArguments[0];
        if (type2 instanceof Class) {
            ArrayList arrayList = new ArrayList();
            parseArray((Class<?>) type2, (Collection) arrayList);
            return arrayList;
        }
        if (type2 instanceof WildcardType) {
            WildcardType wildcardType = (WildcardType) type2;
            Type type3 = wildcardType.getUpperBounds()[0];
            if (Object.class.equals(type3)) {
                if (wildcardType.getLowerBounds().length == 0) {
                    return parse();
                }
                throw new JSONException("not support type : " + type);
            }
            ArrayList arrayList2 = new ArrayList();
            parseArray((Class<?>) type3, (Collection) arrayList2);
            return arrayList2;
        }
        if (type2 instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) type2;
            Type[] bounds = typeVariable.getBounds();
            if (bounds.length != 1) {
                throw new JSONException("not support : " + typeVariable);
            }
            Type type4 = bounds[0];
            if (type4 instanceof Class) {
                ArrayList arrayList3 = new ArrayList();
                parseArray((Class<?>) type4, (Collection) arrayList3);
                return arrayList3;
            }
        }
        if (type2 instanceof ParameterizedType) {
            ArrayList arrayList4 = new ArrayList();
            parseArray((ParameterizedType) type2, arrayList4);
            return arrayList4;
        }
        throw new JSONException("TODO : " + type);
    }

    public void acceptType(String str) {
        JSONLexer jSONLexer = this.lexer;
        jSONLexer.nextTokenWithColon();
        if (jSONLexer.token() != 4) {
            throw new JSONException("type not match error");
        }
        if (str.equals(jSONLexer.stringVal())) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 16) {
                jSONLexer.nextToken();
                return;
            }
            return;
        }
        throw new JSONException("type not match error");
    }

    public int getResolveStatus() {
        return this.resolveStatus;
    }

    public void setResolveStatus(int i) {
        this.resolveStatus = i;
    }

    public Object getObject(String str) {
        for (int i = 0; i < this.contextArrayIndex; i++) {
            if (str.equals(this.contextArray[i].toString())) {
                return this.contextArray[i].object;
            }
        }
        return null;
    }

    public void checkListResolve(Collection collection) {
        if (this.resolveStatus == 1) {
            if (collection instanceof List) {
                int size = collection.size() - 1;
                ResolveTask lastResolveTask = getLastResolveTask();
                lastResolveTask.fieldDeserializer = new ResolveFieldDeserializer(this, (List) collection, size);
                lastResolveTask.ownerContext = this.context;
                setResolveStatus(0);
                return;
            }
            ResolveTask lastResolveTask2 = getLastResolveTask();
            lastResolveTask2.fieldDeserializer = new ResolveFieldDeserializer(collection);
            lastResolveTask2.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public void checkMapResolve(Map map, Object obj) {
        if (this.resolveStatus == 1) {
            ResolveFieldDeserializer resolveFieldDeserializer = new ResolveFieldDeserializer(map, obj);
            ResolveTask lastResolveTask = getLastResolveTask();
            lastResolveTask.fieldDeserializer = resolveFieldDeserializer;
            lastResolveTask.ownerContext = this.context;
            setResolveStatus(0);
        }
    }

    public Object parseObject(Map map) {
        return parseObject(map, (Object) null);
    }

    public JSONObject parseObject() {
        return (JSONObject) parseObject((Map) new JSONObject(this.lexer.isEnabled(Feature.OrderedField)));
    }

    public final void parseArray(Collection collection) {
        parseArray(collection, (Object) null);
    }

    public final void parseArray(Collection collection, Object obj) {
        Object object;
        Number numberDecimalValue;
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == 21 || jSONLexer.token() == 22) {
            jSONLexer.nextToken();
        }
        if (jSONLexer.token() != 14) {
            throw new JSONException("syntax error, expect [, actual " + JSONToken.name(jSONLexer.token()) + ", pos " + jSONLexer.pos() + ", fieldName " + obj);
        }
        jSONLexer.nextToken(4);
        ParseContext parseContext = this.context;
        setContext(collection, obj);
        int i = 0;
        while (true) {
            try {
                if (jSONLexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (jSONLexer.token() == 16) {
                        jSONLexer.nextToken();
                    }
                }
                int i2 = jSONLexer.token();
                if (i2 == 2) {
                    Number numberIntegerValue = jSONLexer.integerValue();
                    jSONLexer.nextToken(16);
                    object = numberIntegerValue;
                } else if (i2 == 3) {
                    if (jSONLexer.isEnabled(Feature.UseBigDecimal)) {
                        numberDecimalValue = jSONLexer.decimalValue(true);
                    } else {
                        numberDecimalValue = jSONLexer.decimalValue(false);
                    }
                    object = numberDecimalValue;
                    jSONLexer.nextToken(16);
                } else if (i2 == 4) {
                    String strStringVal = jSONLexer.stringVal();
                    jSONLexer.nextToken(16);
                    object = strStringVal;
                    if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                        JSONScanner jSONScanner = new JSONScanner(strStringVal);
                        Object time = strStringVal;
                        if (jSONScanner.scanISO8601DateIfMatch()) {
                            time = jSONScanner.getCalendar().getTime();
                        }
                        jSONScanner.close();
                        object = time;
                    }
                } else if (i2 == 6) {
                    Boolean bool = Boolean.TRUE;
                    jSONLexer.nextToken(16);
                    object = bool;
                } else if (i2 != 7) {
                    object = null;
                    object = null;
                    if (i2 == 8) {
                        jSONLexer.nextToken(4);
                    } else if (i2 == 12) {
                        object = parseObject(new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), Integer.valueOf(i));
                    } else {
                        if (i2 == 20) {
                            throw new JSONException("unclosed jsonArray");
                        }
                        if (i2 == 23) {
                            jSONLexer.nextToken(4);
                        } else if (i2 == 14) {
                            JSONArray jSONArray = new JSONArray();
                            parseArray(jSONArray, Integer.valueOf(i));
                            object = jSONArray;
                            if (jSONLexer.isEnabled(Feature.UseObjectArray)) {
                                object = jSONArray.toArray();
                            }
                        } else {
                            if (i2 == 15) {
                                jSONLexer.nextToken(16);
                                setContext(parseContext);
                                return;
                            }
                            object = parse();
                        }
                    }
                } else {
                    Boolean bool2 = Boolean.FALSE;
                    jSONLexer.nextToken(16);
                    object = bool2;
                }
                collection.add(object);
                checkListResolve(collection);
                if (jSONLexer.token() == 16) {
                    jSONLexer.nextToken(4);
                }
                i++;
            } catch (Throwable th) {
                setContext(parseContext);
                throw th;
            }
        }
    }

    public ParseContext getContext() {
        return this.context;
    }

    public List<ResolveTask> getResolveTaskList() {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        return this.resolveTaskList;
    }

    public void addResolveTask(ResolveTask resolveTask) {
        if (this.resolveTaskList == null) {
            this.resolveTaskList = new ArrayList(2);
        }
        this.resolveTaskList.add(resolveTask);
    }

    public ResolveTask getLastResolveTask() {
        List<ResolveTask> list = this.resolveTaskList;
        return list.get(list.size() - 1);
    }

    public List<ExtraProcessor> getExtraProcessors() {
        if (this.extraProcessors == null) {
            this.extraProcessors = new ArrayList(2);
        }
        return this.extraProcessors;
    }

    public List<ExtraTypeProvider> getExtraTypeProviders() {
        if (this.extraTypeProviders == null) {
            this.extraTypeProviders = new ArrayList(2);
        }
        return this.extraTypeProviders;
    }

    public FieldTypeResolver getFieldTypeResolver() {
        return this.fieldTypeResolver;
    }

    public void setFieldTypeResolver(FieldTypeResolver fieldTypeResolver) {
        this.fieldTypeResolver = fieldTypeResolver;
    }

    public void setContext(ParseContext parseContext) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = parseContext;
    }

    public void popContext() {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return;
        }
        this.context = this.context.parent;
        int i = this.contextArrayIndex;
        if (i <= 0) {
            return;
        }
        int i2 = i - 1;
        this.contextArrayIndex = i2;
        this.contextArray[i2] = null;
    }

    public ParseContext setContext(Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        return setContext(this.context, obj, obj2);
    }

    public ParseContext setContext(ParseContext parseContext, Object obj, Object obj2) {
        if (this.lexer.isEnabled(Feature.DisableCircularReferenceDetect)) {
            return null;
        }
        ParseContext parseContext2 = new ParseContext(parseContext, obj, obj2);
        this.context = parseContext2;
        addContext(parseContext2);
        return this.context;
    }

    private void addContext(ParseContext parseContext) {
        int i = this.contextArrayIndex;
        this.contextArrayIndex = i + 1;
        ParseContext[] parseContextArr = this.contextArray;
        if (parseContextArr == null) {
            this.contextArray = new ParseContext[8];
        } else if (i >= parseContextArr.length) {
            ParseContext[] parseContextArr2 = new ParseContext[(parseContextArr.length * 3) / 2];
            System.arraycopy(parseContextArr, 0, parseContextArr2, 0, parseContextArr.length);
            this.contextArray = parseContextArr2;
        }
        this.contextArray[i] = parseContext;
    }

    public Object parse() {
        return parse(null);
    }

    public Object parseKey() {
        if (this.lexer.token() == 18) {
            String strStringVal = this.lexer.stringVal();
            this.lexer.nextToken(16);
            return strStringVal;
        }
        return parse(null);
    }

    public Object parse(Object obj) {
        JSONLexer jSONLexer = this.lexer;
        int i = jSONLexer.token();
        if (i == 2) {
            Number numberIntegerValue = jSONLexer.integerValue();
            jSONLexer.nextToken();
            return numberIntegerValue;
        }
        if (i == 3) {
            Number numberDecimalValue = jSONLexer.decimalValue(jSONLexer.isEnabled(Feature.UseBigDecimal));
            jSONLexer.nextToken();
            return numberDecimalValue;
        }
        if (i == 4) {
            String strStringVal = jSONLexer.stringVal();
            jSONLexer.nextToken(16);
            if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                JSONScanner jSONScanner = new JSONScanner(strStringVal);
                try {
                    if (jSONScanner.scanISO8601DateIfMatch()) {
                        return jSONScanner.getCalendar().getTime();
                    }
                } finally {
                    jSONScanner.close();
                }
            }
            return strStringVal;
        }
        if (i == 12) {
            return parseObject(new JSONObject(jSONLexer.isEnabled(Feature.OrderedField)), obj);
        }
        if (i == 14) {
            JSONArray jSONArray = new JSONArray();
            parseArray(jSONArray, obj);
            return jSONLexer.isEnabled(Feature.UseObjectArray) ? jSONArray.toArray() : jSONArray;
        }
        if (i == 18) {
            if ("NaN".equals(jSONLexer.stringVal())) {
                jSONLexer.nextToken();
                return null;
            }
            throw new JSONException("syntax error, " + jSONLexer.info());
        }
        if (i != 26) {
            switch (i) {
                case 6:
                    jSONLexer.nextToken();
                    return Boolean.TRUE;
                case 7:
                    jSONLexer.nextToken();
                    return Boolean.FALSE;
                case 8:
                    jSONLexer.nextToken();
                    return null;
                case 9:
                    jSONLexer.nextToken(18);
                    if (jSONLexer.token() != 18) {
                        throw new JSONException("syntax error");
                    }
                    jSONLexer.nextToken(10);
                    accept(10);
                    long jLongValue = jSONLexer.integerValue().longValue();
                    accept(2);
                    accept(11);
                    return new Date(jLongValue);
                default:
                    switch (i) {
                        case 20:
                            if (jSONLexer.isBlankInput()) {
                                return null;
                            }
                            throw new JSONException("unterminated json string, " + jSONLexer.info());
                        case 21:
                            jSONLexer.nextToken();
                            HashSet hashSet = new HashSet();
                            parseArray(hashSet, obj);
                            return hashSet;
                        case 22:
                            jSONLexer.nextToken();
                            TreeSet treeSet = new TreeSet();
                            parseArray(treeSet, obj);
                            return treeSet;
                        case 23:
                            jSONLexer.nextToken();
                            return null;
                        default:
                            throw new JSONException("syntax error, " + jSONLexer.info());
                    }
            }
        }
        byte[] bArrBytesValue = jSONLexer.bytesValue();
        jSONLexer.nextToken();
        return bArrBytesValue;
    }

    public void config(Feature feature, boolean z) {
        this.lexer.config(feature, z);
    }

    public boolean isEnabled(Feature feature) {
        return this.lexer.isEnabled(feature);
    }

    public JSONLexer getLexer() {
        return this.lexer;
    }

    public final void accept(int i) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken();
            return;
        }
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(jSONLexer.token()));
    }

    public final void accept(int i, int i2) {
        JSONLexer jSONLexer = this.lexer;
        if (jSONLexer.token() == i) {
            jSONLexer.nextToken(i2);
        } else {
            throwException(i);
        }
    }

    public void throwException(int i) {
        throw new JSONException("syntax error, expect " + JSONToken.name(i) + ", actual " + JSONToken.name(this.lexer.token()));
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        JSONLexer jSONLexer = this.lexer;
        try {
            if (jSONLexer.isEnabled(Feature.AutoCloseSource) && jSONLexer.token() != 20) {
                throw new JSONException("not close json text, token : " + JSONToken.name(jSONLexer.token()));
            }
            jSONLexer.close();
        } catch (Throwable th) {
            jSONLexer.close();
            throw th;
        }
    }

    public Object resolveReference(String str) {
        if (this.contextArray == null) {
            return null;
        }
        int i = 0;
        while (true) {
            ParseContext[] parseContextArr = this.contextArray;
            if (i >= parseContextArr.length || i >= this.contextArrayIndex) {
                break;
            }
            ParseContext parseContext = parseContextArr[i];
            if (parseContext.toString().equals(str)) {
                return parseContext.object;
            }
            i++;
        }
        return null;
    }

    public void handleResovleTask(Object obj) {
        Object objEval;
        List<ResolveTask> list = this.resolveTaskList;
        if (list == null) {
            return;
        }
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ResolveTask resolveTask = this.resolveTaskList.get(i);
            String str = resolveTask.referenceValue;
            Object obj2 = resolveTask.ownerContext != null ? resolveTask.ownerContext.object : null;
            if (str.startsWith("$")) {
                objEval = getObject(str);
                if (objEval == null) {
                    try {
                        objEval = JSONPath.eval(obj, str);
                    } catch (JSONPathException unused) {
                    }
                }
            } else {
                objEval = resolveTask.context.object;
            }
            FieldDeserializer fieldDeserializer = resolveTask.fieldDeserializer;
            if (fieldDeserializer != null) {
                if (objEval != null && objEval.getClass() == JSONObject.class && fieldDeserializer.fieldInfo != null && !Map.class.isAssignableFrom(fieldDeserializer.fieldInfo.fieldClass)) {
                    objEval = JSONPath.eval(this.contextArray[0].object, str);
                }
                fieldDeserializer.setValue(obj2, objEval);
            }
        }
    }

    public static class ResolveTask {
        public final ParseContext context;
        public FieldDeserializer fieldDeserializer;
        public ParseContext ownerContext;
        public final String referenceValue;

        public ResolveTask(ParseContext parseContext, String str) {
            this.context = parseContext;
            this.referenceValue = str;
        }
    }

    public void parseExtra(Object obj, String str) {
        Object object;
        this.lexer.nextTokenWithColon();
        List<ExtraTypeProvider> list = this.extraTypeProviders;
        Type extraType = null;
        if (list != null) {
            Iterator<ExtraTypeProvider> it = list.iterator();
            while (it.hasNext()) {
                extraType = it.next().getExtraType(obj, str);
            }
        }
        if (extraType == null) {
            object = parse();
        } else {
            object = parseObject(extraType);
        }
        if (obj instanceof ExtraProcessable) {
            ((ExtraProcessable) obj).processExtra(str, object);
            return;
        }
        List<ExtraProcessor> list2 = this.extraProcessors;
        if (list2 != null) {
            Iterator<ExtraProcessor> it2 = list2.iterator();
            while (it2.hasNext()) {
                it2.next().processExtra(obj, str, object);
            }
        }
        if (this.resolveStatus == 1) {
            this.resolveStatus = 0;
        }
    }

    public Object parse(PropertyProcessable propertyProcessable, Object obj) {
        String strScanSymbolUnQuoted;
        int i = 0;
        if (this.lexer.token() != 12) {
            String str = "syntax error, expect {, actual " + this.lexer.tokenName();
            if (obj instanceof String) {
                str = (str + ", fieldName ") + obj;
            }
            String str2 = (str + ", ") + this.lexer.info();
            JSONArray jSONArray = new JSONArray();
            parseArray(jSONArray, obj);
            if (jSONArray.size() == 1) {
                Object obj2 = jSONArray.get(0);
                if (obj2 instanceof JSONObject) {
                    return (JSONObject) obj2;
                }
            }
            throw new JSONException(str2);
        }
        ParseContext parseContext = this.context;
        while (true) {
            try {
                this.lexer.skipWhitespace();
                char current = this.lexer.getCurrent();
                if (this.lexer.isEnabled(Feature.AllowArbitraryCommas)) {
                    while (current == ',') {
                        this.lexer.next();
                        this.lexer.skipWhitespace();
                        current = this.lexer.getCurrent();
                    }
                }
                if (current == '\"') {
                    strScanSymbolUnQuoted = this.lexer.scanSymbol(this.symbolTable, Typography.quote);
                    this.lexer.skipWhitespace();
                    if (this.lexer.getCurrent() != ':') {
                        throw new JSONException("expect ':' at " + this.lexer.pos());
                    }
                } else {
                    if (current == '}') {
                        this.lexer.next();
                        this.lexer.resetStringPosition();
                        this.lexer.nextToken(16);
                        setContext(parseContext);
                        return propertyProcessable;
                    }
                    if (current == '\'') {
                        if (!this.lexer.isEnabled(Feature.AllowSingleQuotes)) {
                            throw new JSONException("syntax error");
                        }
                        strScanSymbolUnQuoted = this.lexer.scanSymbol(this.symbolTable, '\'');
                        this.lexer.skipWhitespace();
                        if (this.lexer.getCurrent() != ':') {
                            throw new JSONException("expect ':' at " + this.lexer.pos());
                        }
                    } else {
                        if (!this.lexer.isEnabled(Feature.AllowUnQuotedFieldNames)) {
                            throw new JSONException("syntax error");
                        }
                        strScanSymbolUnQuoted = this.lexer.scanSymbolUnQuoted(this.symbolTable);
                        this.lexer.skipWhitespace();
                        char current2 = this.lexer.getCurrent();
                        if (current2 != ':') {
                            throw new JSONException("expect ':' at " + this.lexer.pos() + ", actual " + current2);
                        }
                    }
                }
                this.lexer.next();
                this.lexer.skipWhitespace();
                this.lexer.getCurrent();
                this.lexer.resetStringPosition();
                Object object = null;
                if (strScanSymbolUnQuoted != JSON.DEFAULT_TYPE_KEY || this.lexer.isEnabled(Feature.DisableSpecialKeyDetect)) {
                    this.lexer.nextToken();
                    if (i != 0) {
                        setContext(parseContext);
                    }
                    Type type = propertyProcessable.getType(strScanSymbolUnQuoted);
                    if (this.lexer.token() == 8) {
                        this.lexer.nextToken();
                    } else {
                        object = parseObject(type, strScanSymbolUnQuoted);
                    }
                    propertyProcessable.apply(strScanSymbolUnQuoted, object);
                    setContext(parseContext, object, strScanSymbolUnQuoted);
                    setContext(parseContext);
                    int i2 = this.lexer.token();
                    if (i2 == 20 || i2 == 15) {
                        break;
                        break;
                    }
                    if (i2 == 13) {
                        this.lexer.nextToken();
                        setContext(parseContext);
                        return propertyProcessable;
                    }
                } else {
                    Class<?> clsCheckAutoType = this.config.checkAutoType(this.lexer.scanSymbol(this.symbolTable, Typography.quote), null, this.lexer.getFeatures());
                    if (!Map.class.isAssignableFrom(clsCheckAutoType)) {
                        ObjectDeserializer deserializer = this.config.getDeserializer(clsCheckAutoType);
                        this.lexer.nextToken(16);
                        setResolveStatus(2);
                        if (parseContext != null && !(obj instanceof Integer)) {
                            popContext();
                        }
                        Map map = (Map) deserializer.deserialze(this, clsCheckAutoType, obj);
                        setContext(parseContext);
                        return map;
                    }
                    this.lexer.nextToken(16);
                    if (this.lexer.token() == 13) {
                        this.lexer.nextToken(16);
                        setContext(parseContext);
                        return propertyProcessable;
                    }
                }
                i++;
            } catch (Throwable th) {
                setContext(parseContext);
                throw th;
            }
        }
        setContext(parseContext);
        return propertyProcessable;
    }
}
