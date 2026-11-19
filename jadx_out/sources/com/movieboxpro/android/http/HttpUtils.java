package com.movieboxpro.android.http;

import android.text.TextUtils;
import android.util.Base64;
import androidx.core.app.NotificationCompat;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.utils.CipherUtils;
import com.movieboxpro.android.utils.HexDump;
import com.movieboxpro.android.utils.MD5Util;
import com.movieboxpro.android.utils.MLog;
import java.net.URLDecoder;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes5.dex */
public class HttpUtils {
    private static CipherKeys DEFAULT_KEYS = CipherKeys.getDefaultKeys();
    private static final String TAG = "HttpUtils";

    public static String md5(String str) {
        return HexDump.toHexString(MD5Util.md5(str)).toLowerCase();
    }

    private static String getVerify(String data, String appKey, String key) {
        if (data == null) {
            return null;
        }
        return md5(md5(appKey) + key + data);
    }

    static String formToJson(String body) {
        if (body == null) {
            return null;
        }
        String[] strArrSplit = body.split("&");
        if (strArrSplit.length <= 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        for (String str : strArrSplit) {
            String[] strArrSplit2 = str.split("=");
            if (strArrSplit2.length == 2) {
                if (strArrSplit2[0].equals("appid")) {
                    strArrSplit2[1] = App.packageName;
                    z = true;
                } else if (strArrSplit2[0].equals("app_version")) {
                    strArrSplit2[1] = App.versionName;
                    z2 = true;
                } else if (strArrSplit2[0].equals("mids")) {
                    jSONObject.put(strArrSplit2[0], (Object) ((JSONObject) JSONObject.parse(strArrSplit2[1])).getJSONArray("mid"));
                } else if (strArrSplit2[0].equals("tids")) {
                    jSONObject.put(strArrSplit2[0], (Object) ((JSONObject) JSONObject.parse(strArrSplit2[1])).getJSONArray("tid"));
                } else if (strArrSplit2[0].equals("lang")) {
                    if (TextUtils.equals("default", strArrSplit2[1])) {
                        strArrSplit2[1] = "";
                    }
                    z3 = true;
                }
                if (!strArrSplit2[0].equals("mids") && !strArrSplit2[0].equals("tids")) {
                    jSONObject.put(strArrSplit2[0], (Object) strArrSplit2[1]);
                }
            } else if (strArrSplit2.length == 1) {
                jSONObject.put(strArrSplit2[0], (Object) "");
            }
        }
        if (!z) {
            jSONObject.put("appid", (Object) App.packageName);
        }
        if (!z2) {
            jSONObject.put("app_version", (Object) App.versionName);
        }
        if (!z3) {
            jSONObject.put("lang", (Object) App.deviceLang);
        }
        return jSONObject.toJSONString();
    }

    public static String encodeBody(String json) {
        return encodeBody(json, DEFAULT_KEYS);
    }

    public static String encodeBody(String json, CipherKeys keys) {
        if (json == null) {
            return null;
        }
        MLog.d(TAG, "before encrypt:" + json);
        String strEncrypt = CipherUtils.encrypt(json, keys);
        MLog.d(TAG, "after encrypt:" + strEncrypt);
        String verify = getVerify(strEncrypt, keys.getAppKey(), keys.getKey());
        String strMd5 = md5(keys.getAppKey());
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("app_key", (Object) strMd5);
        jSONObject.put("encrypt_data", (Object) strEncrypt);
        jSONObject.put("verify", (Object) verify);
        String jSONString = jSONObject.toJSONString();
        MLog.d(TAG, "new body json:" + jSONString);
        String str = new String(Base64.encode(jSONString.getBytes(), 2));
        MLog.d(TAG, "after encoded:".concat(str));
        return str;
    }

    public static String decodeBody(String encoded, CipherKeys keys) {
        if (encoded != null) {
            try {
                String str = new String(Base64.decode(URLDecoder.decode(encoded, "utf-8"), 2), "utf-8");
                MLog.d(TAG, "decodeBody:".concat(str));
                JSONObject object = JSONObject.parseObject(str);
                boolean zContainsKey = object.containsKey("app_key");
                boolean zContainsKey2 = object.containsKey("encrypt_data");
                boolean zContainsKey3 = object.containsKey("verify");
                if (zContainsKey && zContainsKey2 && zContainsKey3) {
                    String string = object.getString("app_key");
                    String string2 = object.getString("encrypt_data");
                    if (!md5(string + keys.getKey() + string2).equalsIgnoreCase(object.getString("verify"))) {
                        MLog.d(TAG, "decodeBody: 校验失败！");
                        return null;
                    }
                    String strDecrypt = CipherUtils.decrypt(string2, keys);
                    MLog.d(TAG, "decodeBody: " + strDecrypt);
                    return strDecrypt;
                }
                MLog.d(TAG, "decodeBody: 数据错误！");
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static <T> BaseResponse<T> parse(String str, Class<T> cls) {
        if (str != null) {
            try {
                System.out.println("哇卡卡卡" + str);
                JSONObject object = JSONObject.parseObject(str);
                BaseResponse<T> baseResponse = new BaseResponse<>();
                int intValue = object.getIntValue("code");
                System.out.println("哇卡卡卡" + intValue);
                baseResponse.success = intValue;
                if (intValue == 0) {
                    Object obj = object.get(NotificationCompat.CATEGORY_MESSAGE);
                    if (obj != null) {
                        if (obj instanceof String) {
                            baseResponse.msg = object.getString(NotificationCompat.CATEGORY_MESSAGE);
                        } else if (obj instanceof JSONObject) {
                            baseResponse.msg = ((JSONObject) obj).getJSONObject("Message").getString("messagestr");
                        } else {
                            baseResponse.msg = "未知错误";
                        }
                    } else {
                        baseResponse.msg = "未知错误";
                    }
                } else if (intValue == 1) {
                    Object obj2 = object.get(NotificationCompat.CATEGORY_MESSAGE);
                    if (obj2 != null) {
                        if (obj2 instanceof String) {
                            baseResponse.msg = object.getString(NotificationCompat.CATEGORY_MESSAGE);
                        } else if (obj2 instanceof JSONObject) {
                            baseResponse.msg = object.getJSONObject(NotificationCompat.CATEGORY_MESSAGE).getJSONObject("Message").getString("messagestr");
                        } else {
                            baseResponse.msg = "请求成功";
                        }
                    } else {
                        baseResponse.msg = "请求成功";
                    }
                } else {
                    baseResponse.msg = object.getString(NotificationCompat.CATEGORY_MESSAGE);
                    if (baseResponse.msg == null) {
                        baseResponse.msg = "未知错误";
                    }
                }
                T t = (T) object.get("data");
                if (t == null) {
                    t = (T) object.get("dataList");
                }
                if (t != null) {
                    if (t instanceof JSONObject) {
                        if (!t.isEmpty()) {
                            baseResponse.code = 1000;
                            baseResponse.data = (T) JSONObject.toJavaObject((JSONObject) t, cls);
                        } else if (intValue == 1) {
                            baseResponse.code = 1002;
                            baseResponse.data = null;
                        } else {
                            baseResponse.code = 2002;
                            baseResponse.data = null;
                        }
                        baseResponse.list = null;
                    } else if (t instanceof JSONArray) {
                        JSONArray jSONArray = (JSONArray) t;
                        if (jSONArray.isEmpty()) {
                            if (intValue == 1) {
                                baseResponse.code = 1002;
                                baseResponse.data = null;
                                baseResponse.list = null;
                            } else {
                                baseResponse.code = 2002;
                                baseResponse.data = null;
                                baseResponse.list = null;
                            }
                        } else if (intValue == 1) {
                            baseResponse.code = 1001;
                            baseResponse.data = null;
                            baseResponse.list = jSONArray.toJavaList(cls);
                        } else {
                            baseResponse.code = 2002;
                            baseResponse.data = null;
                            baseResponse.list = null;
                        }
                    } else if (t instanceof String) {
                        baseResponse.code = 1000;
                        baseResponse.data = t;
                        baseResponse.list = null;
                    } else {
                        baseResponse.code = 2000;
                        baseResponse.data = null;
                        baseResponse.list = null;
                    }
                } else {
                    if (intValue != 1) {
                        baseResponse.code = 2001;
                    } else {
                        baseResponse.code = 1000;
                    }
                    baseResponse.data = null;
                    baseResponse.list = null;
                }
                return baseResponse;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String toURLEncoded(String paramString) {
        if (paramString != null && !paramString.equals("")) {
            try {
                return URLEncoder.encode(new String(paramString.getBytes(), "UTF-8"), "UTF-8");
            } catch (Exception unused) {
            }
        }
        return "";
    }
}
