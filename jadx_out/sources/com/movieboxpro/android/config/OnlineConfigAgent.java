package com.movieboxpro.android.config;

import android.content.Context;
import android.content.SharedPreferences;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpUtils;
import com.movieboxpro.android.http.RequestCallback;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.MiniInstallKeys;

/* JADX INFO: loaded from: classes5.dex */
public class OnlineConfigAgent {
    private static final String TAG = "OnlineConfigAgent";
    private static OnlineConfigAgent agent;
    private OnlineConfigListener listener;

    public interface OnlineConfigListener {
        void onDataReceived(String data);
    }

    public static synchronized OnlineConfigAgent getInstance() {
        if (agent == null) {
            agent = new OnlineConfigAgent();
        }
        return agent;
    }

    private OnlineConfigAgent() {
    }

    public void updateOnlineConfig() {
        Http.get("https://www.mini-install.com/api/index/get_param_encryption_id.html?id=43", null, new RequestCallback<String>() { // from class: com.movieboxpro.android.config.OnlineConfigAgent.1
            @Override // com.movieboxpro.android.http.RequestCallback
            public void onSuccess(String s) {
                super.onSuccess(s);
                MLog.d(OnlineConfigAgent.TAG, "onSuccess: " + s);
                String jSONString = JSONObject.parseObject(HttpUtils.decodeBody(JSONObject.parseObject(s).getString("data"), new MiniInstallKeys())).getJSONArray("data").toJSONString();
                OnlineConfigAgent.this.saveConfigParams(App.getContext(), jSONString);
                if (OnlineConfigAgent.this.listener != null) {
                    OnlineConfigAgent.this.listener.onDataReceived(jSONString);
                }
            }

            @Override // com.movieboxpro.android.http.RequestCallback
            public void onFailure(int code, String error) {
                super.onFailure(code, error);
                if (OnlineConfigAgent.this.listener != null) {
                    OnlineConfigAgent.this.listener.onDataReceived(null);
                }
            }
        });
    }

    public void setOnlineConfigListener(OnlineConfigListener listener) {
        this.listener = listener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveConfigParams(Context context, String data) {
        clearAllConfig(context);
        JSONArray array = JSONArray.parseArray(data);
        if (array.isEmpty()) {
            return;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("online_config", 0).edit();
        editorEdit.clear();
        int size = array.size();
        for (int i = 0; i < size; i++) {
            JSONObject jSONObject = array.getJSONObject(i);
            String strTrim = jSONObject.getString("param_name").trim();
            String strTrim2 = jSONObject.getString("param_value").trim();
            editorEdit.putString(strTrim, strTrim2);
            String strTrim3 = jSONObject.getString("remark").trim();
            editorEdit.putString("remark_" + strTrim, strTrim3);
            MLog.d(TAG, "saveConfigParams: key: " + strTrim + " value: " + strTrim2);
            StringBuilder sb = new StringBuilder("saveConfigParams: remark: ");
            sb.append(strTrim3);
            MLog.d(TAG, sb.toString());
        }
        editorEdit.commit();
    }

    String getConfigParam(Context context, String key) {
        String string = context.getSharedPreferences("online_config", 0).getString(key, "");
        MLog.d(TAG, "getConfigParam: key: " + key + " value: " + string);
        return string;
    }

    void setConfigParam(Context context, String key, String value) {
        context.getSharedPreferences("online_config", 0).edit().putString(key, value).commit();
    }

    boolean containsKey(Context context, String key) {
        return context.getSharedPreferences("online_config", 0).contains(key);
    }

    private void clearAllConfig(Context context) {
        context.getSharedPreferences("online_config", 0).edit().clear().commit();
    }
}
