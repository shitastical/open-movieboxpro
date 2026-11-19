package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONScanner;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractDateDeserializer extends ContextObjectDeserializer implements ObjectDeserializer {
    protected abstract <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2);

    @Override // com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, null, 0);
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0073  */
    /* JADX WARN: Code duplicated, block: B:30:0x007e  */
    /* JADX WARN: Code duplicated, block: B:32:0x0089  */
    /* JADX WARN: Code duplicated, block: B:38:0x00a2 A[PHI: r6
      0x00a2: PHI (r6v1 java.lang.Object) = (r6v0 java.lang.Object), (r6v3 java.lang.Object) binds: [B:37:0x009f, B:27:0x0071] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.alibaba.fastjson.parser.deserializer.ContextObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, String str, int i) {
        Object obj2;
        SimpleDateFormat simpleDateFormat;
        Object obj3;
        Object obj4;
        JSONScanner jSONScanner;
        Object time;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 2) {
            Object objValueOf = Long.valueOf(jSONLexer.longValue());
            jSONLexer.nextToken(16);
            obj2 = objValueOf;
        } else {
            Object obj5 = null;
            if (jSONLexer.token() == 4) {
                String strStringVal = jSONLexer.stringVal();
                if (str != null) {
                    try {
                        simpleDateFormat = new SimpleDateFormat(str, JSON.defaultLocale);
                    } catch (IllegalArgumentException unused) {
                        if (str.equals("yyyy-MM-ddTHH:mm:ss.SSS")) {
                            str = "yyyy-MM-dd'T'HH:mm:ss.SSS";
                            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                        } else if (str.equals("yyyy-MM-ddTHH:mm:ss")) {
                            str = "yyyy-MM-dd'T'HH:mm:ss";
                            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        } else {
                            simpleDateFormat = null;
                        }
                    }
                    try {
                        obj3 = simpleDateFormat.parse(strStringVal);
                    } catch (ParseException unused2) {
                        if (str.equals("yyyy-MM-dd'T'HH:mm:ss.SSS") && strStringVal.length() == 19) {
                            try {
                                obj3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse(strStringVal);
                            } catch (ParseException unused3) {
                            }
                        }
                        if (obj5 == null) {
                            jSONLexer.nextToken(16);
                            obj4 = strStringVal;
                            if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                                jSONScanner = new JSONScanner(strStringVal);
                                time = strStringVal;
                                if (jSONScanner.scanISO8601DateIfMatch()) {
                                    time = jSONScanner.getCalendar().getTime();
                                }
                                jSONScanner.close();
                                obj4 = time;
                            }
                            obj2 = obj4;
                        } else {
                            obj2 = obj5;
                        }
                        return (T) cast(defaultJSONParser, type, obj, obj2);
                    }
                    obj5 = obj3;
                }
                if (obj5 == null) {
                    jSONLexer.nextToken(16);
                    obj4 = strStringVal;
                    if (jSONLexer.isEnabled(Feature.AllowISO8601DateFormat)) {
                        jSONScanner = new JSONScanner(strStringVal);
                        time = strStringVal;
                        if (jSONScanner.scanISO8601DateIfMatch()) {
                            time = jSONScanner.getCalendar().getTime();
                        }
                        jSONScanner.close();
                        obj4 = time;
                    }
                    obj2 = obj4;
                } else {
                    obj2 = obj5;
                }
            } else if (jSONLexer.token() == 8) {
                jSONLexer.nextToken();
                obj2 = obj5;
            } else if (jSONLexer.token() == 12) {
                jSONLexer.nextToken();
                if (jSONLexer.token() == 4) {
                    if (JSON.DEFAULT_TYPE_KEY.equals(jSONLexer.stringVal())) {
                        jSONLexer.nextToken();
                        defaultJSONParser.accept(17);
                        Type typeCheckAutoType = defaultJSONParser.getConfig().checkAutoType(jSONLexer.stringVal(), null, jSONLexer.getFeatures());
                        if (typeCheckAutoType != null) {
                            type = typeCheckAutoType;
                        }
                        defaultJSONParser.accept(4);
                        defaultJSONParser.accept(16);
                    }
                    jSONLexer.nextTokenWithColon(2);
                    if (jSONLexer.token() == 2) {
                        long jLongValue = jSONLexer.longValue();
                        jSONLexer.nextToken();
                        Object objValueOf2 = Long.valueOf(jLongValue);
                        defaultJSONParser.accept(13);
                        obj2 = objValueOf2;
                    } else {
                        throw new JSONException("syntax error : " + jSONLexer.tokenName());
                    }
                } else {
                    throw new JSONException("syntax error");
                }
            } else if (defaultJSONParser.getResolveStatus() == 2) {
                defaultJSONParser.setResolveStatus(0);
                defaultJSONParser.accept(16);
                if (jSONLexer.token() == 4) {
                    if (!"val".equals(jSONLexer.stringVal())) {
                        throw new JSONException("syntax error");
                    }
                    jSONLexer.nextToken();
                    defaultJSONParser.accept(17);
                    Object obj6 = defaultJSONParser.parse();
                    defaultJSONParser.accept(13);
                    obj2 = obj6;
                } else {
                    throw new JSONException("syntax error");
                }
            } else {
                obj2 = defaultJSONParser.parse();
            }
        }
        return (T) cast(defaultJSONParser, type, obj, obj2);
    }
}
