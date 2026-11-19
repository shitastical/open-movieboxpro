package com.movieboxpro.android.view.activity.videoplayer;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.movieboxpro.android.timroes.axmlrpc.serializer.SerializerHandler;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TranslateSubtitleStreamParser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0007H\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u001a\u0010\u0010\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0007H\u0002J\u001f\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\u0015J\u001f\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0007H\u0002¢\u0006\u0002\u0010\u0018¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamParser;", "", "<init>", "()V", "parseLine", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent;", "raw", "", "isKnownType", "", "type", "parseCues", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Cues;", "root", "Lcom/google/gson/JsonObject;", "parseObject", "string", "obj", "key", SerializerHandler.TYPE_INT, "", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/Integer;", "long", "", "(Lcom/google/gson/JsonObject;Ljava/lang/String;)Ljava/lang/Long;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TranslateSubtitleStreamParser {
    public static final TranslateSubtitleStreamParser INSTANCE = new TranslateSubtitleStreamParser();

    private TranslateSubtitleStreamParser() {
    }

    @JvmStatic
    public static final TranslateSubtitleStreamEvent parseLine(String raw) {
        String strString;
        String str = raw;
        if (str == null || StringsKt.isBlank(str)) {
            return TranslateSubtitleStreamEvent.Ignore.INSTANCE;
        }
        String strRemovePrefix = StringsKt.removePrefix(StringsKt.trim((CharSequence) str).toString(), (CharSequence) "\ufeff");
        TranslateSubtitleStreamParser translateSubtitleStreamParser = INSTANCE;
        JsonObject object = translateSubtitleStreamParser.parseObject(strRemovePrefix);
        if (object != null && (strString = translateSubtitleStreamParser.string(object, "type")) != null) {
            Integer numM1622int = translateSubtitleStreamParser.m1622int(object, "code");
            String strString2 = translateSubtitleStreamParser.string(object, NotificationCompat.CATEGORY_MESSAGE);
            if (Intrinsics.areEqual(strString, "error") || (translateSubtitleStreamParser.isKnownType(strString) && (numM1622int == null || numM1622int.intValue() != 1))) {
                return new TranslateSubtitleStreamEvent.Error(numM1622int != null ? numM1622int.intValue() : -1, strString2);
            }
            int iHashCode = strString.hashCode();
            if (iHashCode != 3064992) {
                if (iHashCode != 3089282) {
                    if (iHashCode == 3347973 && strString.equals("meta")) {
                        return new TranslateSubtitleStreamEvent.Meta(translateSubtitleStreamParser.m1622int(object, "total"), translateSubtitleStreamParser.string(object, TypedValues.TransitionType.S_FROM), translateSubtitleStreamParser.string(object, TypedValues.TransitionType.S_TO));
                    }
                } else if (strString.equals("done")) {
                    return new TranslateSubtitleStreamEvent.Done(translateSubtitleStreamParser.m1622int(object, "total"), translateSubtitleStreamParser.m1622int(object, "done_count"), translateSubtitleStreamParser.m1623long(object, "srt_id"), translateSubtitleStreamParser.string(object, "srt_url"));
                }
            } else if (strString.equals("cues")) {
                return translateSubtitleStreamParser.parseCues(object);
            }
            return TranslateSubtitleStreamEvent.Ignore.INSTANCE;
        }
        return TranslateSubtitleStreamEvent.Ignore.INSTANCE;
    }

    private final boolean isKnownType(String type) {
        return Intrinsics.areEqual(type, "meta") || Intrinsics.areEqual(type, "cues") || Intrinsics.areEqual(type, "done");
    }

    private final TranslateSubtitleStreamEvent.Cues parseCues(JsonObject root) {
        ArrayList arrayList = new ArrayList();
        JsonElement jsonElement = root.get("cues");
        if (jsonElement != null && jsonElement.isJsonArray()) {
            JsonArray asJsonArray = jsonElement.getAsJsonArray();
            Intrinsics.checkNotNullExpressionValue(asJsonArray, "getAsJsonArray(...)");
            for (JsonElement jsonElement2 : asJsonArray) {
                if (jsonElement2.isJsonObject()) {
                    JsonObject asJsonObject = jsonElement2.getAsJsonObject();
                    TranslateSubtitleStreamParser translateSubtitleStreamParser = INSTANCE;
                    Intrinsics.checkNotNull(asJsonObject);
                    arrayList.add(new TranslateSubtitleCue(translateSubtitleStreamParser.string(asJsonObject, "num"), translateSubtitleStreamParser.string(asJsonObject, "time"), translateSubtitleStreamParser.string(asJsonObject, FirebaseAnalytics.Param.CONTENT)));
                }
            }
        }
        Integer numM1622int = m1622int(root, "offset");
        int iIntValue = numM1622int != null ? numM1622int.intValue() : 0;
        Integer numM1622int2 = m1622int(root, "count");
        return new TranslateSubtitleStreamEvent.Cues(iIntValue, numM1622int2 != null ? numM1622int2.intValue() : arrayList.size(), m1622int(root, "done_count"), arrayList);
    }

    private final JsonObject parseObject(String raw) {
        try {
            JsonElement string = JsonParser.parseString(raw);
            if (string.isJsonObject()) {
                return string.getAsJsonObject();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private final String string(JsonObject obj, String key) {
        JsonElement jsonElement = obj.get(key);
        if (jsonElement == null || !jsonElement.isJsonPrimitive() || jsonElement.isJsonNull()) {
            return null;
        }
        try {
            return jsonElement.getAsString();
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: int, reason: not valid java name */
    private final Integer m1622int(JsonObject obj, String key) {
        JsonElement jsonElement = obj.get(key);
        if (jsonElement == null || !jsonElement.isJsonPrimitive() || jsonElement.isJsonNull()) {
            return null;
        }
        try {
            return Integer.valueOf(jsonElement.getAsInt());
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: long, reason: not valid java name */
    private final Long m1623long(JsonObject obj, String key) {
        JsonElement jsonElement = obj.get(key);
        if (jsonElement == null || !jsonElement.isJsonPrimitive() || jsonElement.isJsonNull()) {
            return null;
        }
        try {
            return Long.valueOf(jsonElement.getAsLong());
        } catch (Exception unused) {
            return null;
        }
    }
}
