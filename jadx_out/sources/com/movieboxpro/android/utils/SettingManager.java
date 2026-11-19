package com.movieboxpro.android.utils;

import android.content.Context;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.tool.SPStaticUtils;
import com.movieboxpro.androidtv.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import kotlinx.coroutines.DebugKt;

/* JADX INFO: compiled from: SettingManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050!2\u0006\u0010\"\u001a\u00020#J\u0014\u0010$\u001a\u00020%2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00050!J\u000e\u0010'\u001a\u00020%2\u0006\u0010(\u001a\u00020\u0019J\u0006\u0010)\u001a\u00020\u0019J\u000e\u0010*\u001a\u00020%2\u0006\u0010+\u001a\u00020\u0019J\u0006\u0010,\u001a\u00020\u0019J\u000e\u0010-\u001a\u00020%2\u0006\u0010.\u001a\u00020\u0005J\u0006\u0010/\u001a\u00020\u0005J\u0006\u00100\u001a\u00020\u0005J\u000e\u00101\u001a\u00020%2\u0006\u0010(\u001a\u00020\u0019J\u0006\u00102\u001a\u00020\u0019J\u0006\u00103\u001a\u00020\u0019J\u000e\u00104\u001a\u00020%2\u0006\u00105\u001a\u00020\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R+\u0010\u000e\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R+\u0010\u0015\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0014\u001a\u0004\b\u0016\u0010\u0010\"\u0004\b\u0017\u0010\u0012R+\u0010\u001a\u001a\u00020\u00192\u0006\u0010\r\u001a\u00020\u00198F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001f\u0010\u0014\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001e¨\u00066"}, d2 = {"Lcom/movieboxpro/android/utils/SettingManager;", "", "<init>", "()V", "SMART_DOWNLOAD", "", "SMART_DOWNLOAD_AUTO_DELETE_WATCHED", "DOWNLOAD_QUALITY_RULE", "AUTO_SELECT_DOWNLOAD_QUALITY", "LAST_SELECT_OPEN_SUBTITLE_LANGUAGE", "TRANSLATE_LANGUAGE", "DOWNLOAD_AUDIO_LANGUAGE", "DEFAULT_AAC_AUDIO", "<set-?>", "selectOpenSubtitleLanguage", "getSelectOpenSubtitleLanguage", "()Ljava/lang/String;", "setSelectOpenSubtitleLanguage", "(Ljava/lang/String;)V", "selectOpenSubtitleLanguage$delegate", "Lkotlin/properties/ReadWriteProperty;", "translateLanguage", "getTranslateLanguage", "setTranslateLanguage", "translateLanguage$delegate", "", "bestAudio", "getBestAudio", "()Z", "setBestAudio", "(Z)V", "bestAudio$delegate", "getDownloadAudioLanguage", "", "context", "Landroid/content/Context;", "saveDownloadAudioLanguage", "", "languages", "saveSmartDownload", DebugKt.DEBUG_PROPERTY_VALUE_ON, "getSmartDownload", "saveAutoDeleteWatched", "auto", "getAutoDeleteWatched", "saveDownloadRule", "rule", "getDownloadRule", "getCurrDownloadRule", "saveAutoDownloadSelectQuality", "isAutoDownloadSelectQuality", "isHwDecoder", "setHwDecoder", "enabled", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SettingManager {
    private static final String AUTO_SELECT_DOWNLOAD_QUALITY = "auto_select_download_quality";
    private static final String DEFAULT_AAC_AUDIO = "default_aac_audio";
    private static final String DOWNLOAD_AUDIO_LANGUAGE = "download_audio_language";
    private static final String DOWNLOAD_QUALITY_RULE = "download_quality_rule";
    private static final String SMART_DOWNLOAD = "smart_download";
    private static final String SMART_DOWNLOAD_AUTO_DELETE_WATCHED = "smart_download_auto_delete_watched";
    private static final String TRANSLATE_LANGUAGE = "translate_language";

    /* JADX INFO: renamed from: bestAudio$delegate, reason: from kotlin metadata */
    private static final ReadWriteProperty bestAudio;

    /* JADX INFO: renamed from: translateLanguage$delegate, reason: from kotlin metadata */
    private static final ReadWriteProperty translateLanguage;
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(SettingManager.class, "selectOpenSubtitleLanguage", "getSelectOpenSubtitleLanguage()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SettingManager.class, "translateLanguage", "getTranslateLanguage()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(SettingManager.class, "bestAudio", "getBestAudio()Z", 0))};
    public static final SettingManager INSTANCE = new SettingManager();
    private static final String LAST_SELECT_OPEN_SUBTITLE_LANGUAGE = "last_select_open_subtitle_language";

    /* JADX INFO: renamed from: selectOpenSubtitleLanguage$delegate, reason: from kotlin metadata */
    private static final ReadWriteProperty selectOpenSubtitleLanguage = SPDelegateKt.stringSP(LAST_SELECT_OPEN_SUBTITLE_LANGUAGE, "");

    static {
        String deviceLang = App.deviceLang;
        Intrinsics.checkNotNullExpressionValue(deviceLang, "deviceLang");
        translateLanguage = SPDelegateKt.stringSP(TRANSLATE_LANGUAGE, deviceLang);
        bestAudio = SPDelegateKt.boolSP(true, new Function1() { // from class: com.movieboxpro.android.utils.SettingManager$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SettingManager.bestAudio_delegate$lambda$0((KProperty) obj);
            }
        });
    }

    private SettingManager() {
    }

    public final String getSelectOpenSubtitleLanguage() {
        return (String) selectOpenSubtitleLanguage.getValue(this, $$delegatedProperties[0]);
    }

    public final void setSelectOpenSubtitleLanguage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        selectOpenSubtitleLanguage.setValue(this, $$delegatedProperties[0], str);
    }

    public final String getTranslateLanguage() {
        return (String) translateLanguage.getValue(this, $$delegatedProperties[1]);
    }

    public final void setTranslateLanguage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        translateLanguage.setValue(this, $$delegatedProperties[1], str);
    }

    public final boolean getBestAudio() {
        return ((Boolean) bestAudio.getValue(this, $$delegatedProperties[2])).booleanValue();
    }

    public final void setBestAudio(boolean z) {
        bestAudio.setValue(this, $$delegatedProperties[2], Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String bestAudio_delegate$lambda$0(KProperty it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return DEFAULT_AAC_AUDIO;
    }

    public final List<String> getDownloadAudioLanguage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = SPStaticUtils.getString(DOWNLOAD_AUDIO_LANGUAGE, "");
        Intrinsics.checkNotNull(string);
        if (string.length() == 0) {
            String[] stringArray = context.getResources().getStringArray(R.array.languages);
            Intrinsics.checkNotNullExpressionValue(stringArray, "getStringArray(...)");
            String[] stringArray2 = context.getResources().getStringArray(R.array.usual_languages);
            Intrinsics.checkNotNullExpressionValue(stringArray2, "getStringArray(...)");
            List list = ArraysKt.toList(stringArray2);
            ArrayList arrayList = new ArrayList();
            for (String str : stringArray) {
                if (!ArraysKt.contains(stringArray2, str)) {
                    arrayList.add(str);
                }
            }
            return CollectionsKt.plus((Collection) list, (Iterable) arrayList);
        }
        Object objFromJson = GsonExtensionsKt.getGSON().fromJson(string, new ParameterizedTypeImpl(String.class));
        List<String> list2 = objFromJson instanceof List ? (List) objFromJson : null;
        return list2 == null ? new ArrayList() : list2;
    }

    public final void saveDownloadAudioLanguage(List<String> languages) {
        Intrinsics.checkNotNullParameter(languages, "languages");
        SPStaticUtils.put(DOWNLOAD_AUDIO_LANGUAGE, GsonExtensionsKt.getGSON().toJson(languages));
    }

    public final void saveSmartDownload(boolean on) {
        PrefsUtils.getInstance().putBoolean(SMART_DOWNLOAD, on);
    }

    public final boolean getSmartDownload() {
        return PrefsUtils.getInstance().getBoolean(SMART_DOWNLOAD, false);
    }

    public final void saveAutoDeleteWatched(boolean auto) {
        PrefsUtils.getInstance().putBoolean(SMART_DOWNLOAD_AUTO_DELETE_WATCHED, auto);
    }

    public final boolean getAutoDeleteWatched() {
        return PrefsUtils.getInstance().getBoolean(SMART_DOWNLOAD_AUTO_DELETE_WATCHED, true);
    }

    public final void saveDownloadRule(String rule) {
        Intrinsics.checkNotNullParameter(rule, "rule");
        SPStaticUtils.put(DOWNLOAD_QUALITY_RULE, rule);
    }

    public final String getDownloadRule() {
        String string = SPStaticUtils.getString(DOWNLOAD_QUALITY_RULE, "1080P,720P,360P,4K,8K,ORG");
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        return string;
    }

    public final String getCurrDownloadRule() {
        return (String) StringsKt.split$default((CharSequence) getDownloadRule(), new String[]{","}, false, 0, 6, (Object) null).get(0);
    }

    public final void saveAutoDownloadSelectQuality(boolean on) {
        SPStaticUtils.put(AUTO_SELECT_DOWNLOAD_QUALITY, on);
    }

    public final boolean isAutoDownloadSelectQuality() {
        return SPStaticUtils.getBoolean(AUTO_SELECT_DOWNLOAD_QUALITY, false);
    }

    public final boolean isHwDecoder() {
        return PrefsUtils.getInstance().getBoolean(Constant.Prefs.HW_DECODER, true);
    }

    public final void setHwDecoder(boolean enabled) {
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.HW_DECODER, enabled);
    }
}
