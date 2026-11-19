package com.dl7.player.danmaku;

import androidx.core.view.ViewCompat;
import androidx.media3.exoplayer.upstream.CmcdData;
import ly.count.android.sdk.internal.RemoteConfigValueStore;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.android.JSONSource;
import master.flame.danmaku.danmaku.util.DanmakuUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class AcFunDanmakuParser extends BaseDanmakuParser {
    @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
    public Danmakus parse() {
        if (this.mDataSource != null && (this.mDataSource instanceof JSONSource)) {
            return doParse(((JSONSource) this.mDataSource).data());
        }
        return new Danmakus();
    }

    private Danmakus doParse(JSONArray jSONArray) {
        Danmakus danmakus = new Danmakus();
        if (jSONArray != null && jSONArray.length() != 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject != null) {
                        danmakus = _parse(jSONObject, danmakus);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return danmakus;
    }

    private Danmakus _parse(JSONObject jSONObject, Danmakus danmakus) {
        int i;
        if (danmakus == null) {
            danmakus = new Danmakus();
        }
        if (jSONObject != null && jSONObject.length() != 0) {
            for (int i2 = 0; i2 < jSONObject.length(); i2++) {
                try {
                    String[] strArrSplit = jSONObject.getString(RemoteConfigValueStore.keyCacheFlag).split(",");
                    if (strArrSplit.length > 0 && (i = Integer.parseInt(strArrSplit[2])) != 7) {
                        long j = (long) (Float.parseFloat(strArrSplit[0]) * 1000.0f);
                        int i3 = Integer.parseInt(strArrSplit[1]);
                        int i4 = ViewCompat.MEASURED_STATE_MASK;
                        int i5 = i3 | ViewCompat.MEASURED_STATE_MASK;
                        float f = Float.parseFloat(strArrSplit[3]);
                        BaseDanmaku baseDanmakuCreateDanmaku = this.mContext.mDanmakuFactory.createDanmaku(i, this.mContext);
                        if (baseDanmakuCreateDanmaku != null) {
                            baseDanmakuCreateDanmaku.setTime(j);
                            baseDanmakuCreateDanmaku.textSize = f * (this.mDispDensity - 0.6f);
                            baseDanmakuCreateDanmaku.textColor = i5;
                            if (i5 <= -16777216) {
                                i4 = -1;
                            }
                            baseDanmakuCreateDanmaku.textShadowColor = i4;
                            DanmakuUtils.fillText(baseDanmakuCreateDanmaku, jSONObject.optString(CmcdData.OBJECT_TYPE_MANIFEST, "...."));
                            baseDanmakuCreateDanmaku.index = i2;
                            baseDanmakuCreateDanmaku.setTimer(this.mTimer);
                            danmakus.addItem(baseDanmakuCreateDanmaku);
                        }
                    }
                } catch (NumberFormatException | JSONException unused) {
                }
            }
        }
        return danmakus;
    }
}
