package com.movieboxpro.android.utils;

import com.google.firebase.crashlytics.internal.common.IdManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DownloadUrl;
import com.movieboxpro.android.model.HlsItem;
import com.movieboxpro.android.model.HlsResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DownloadUrlTransformer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005H\u0007J\u0014\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\n0\u0005H\u0007J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/utils/DownloadUrlTransformer;", "", "<init>", "()V", "applyDownloadUrlsParsing", "Lio/reactivex/ObservableTransformer;", "", "", "Lcom/movieboxpro/android/model/DownloadUrl;", "applyPlayUrlsParsing", "Lcom/movieboxpro/android/model/BaseMediaModel;", "parseTvDownloadUrls", "it", "Lcom/google/gson/JsonObject;", "needOrgFile", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadUrlTransformer {
    public static final DownloadUrlTransformer INSTANCE = new DownloadUrlTransformer();

    private DownloadUrlTransformer() {
    }

    @JvmStatic
    public static final ObservableTransformer<String, List<DownloadUrl>> applyDownloadUrlsParsing() {
        return new ObservableTransformer() { // from class: com.movieboxpro.android.utils.DownloadUrlTransformer$$ExternalSyntheticLambda6
            @Override // io.reactivex.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return DownloadUrlTransformer.applyDownloadUrlsParsing$lambda$5(observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource applyDownloadUrlsParsing$lambda$5(Observable upstream) {
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        Observable observableCompose = upstream.compose(RxUtils.rxTranslate2Bean(JsonObject.class));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.utils.DownloadUrlTransformer$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadUrlTransformer.applyDownloadUrlsParsing$lambda$5$lambda$0((JsonObject) obj);
            }
        };
        Observable map = observableCompose.map(new Function() { // from class: com.movieboxpro.android.utils.DownloadUrlTransformer$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadUrlTransformer.applyDownloadUrlsParsing$lambda$5$lambda$1(function1, obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.utils.DownloadUrlTransformer$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadUrlTransformer.applyDownloadUrlsParsing$lambda$5$lambda$3((BaseMediaModel) obj);
            }
        };
        return map.map(new Function() { // from class: com.movieboxpro.android.utils.DownloadUrlTransformer$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadUrlTransformer.applyDownloadUrlsParsing$lambda$5$lambda$4(function2, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel applyDownloadUrlsParsing$lambda$5$lambda$1(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (BaseMediaModel) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel applyDownloadUrlsParsing$lambda$5$lambda$0(JsonObject it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.parseTvDownloadUrls(it, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List applyDownloadUrlsParsing$lambda$5$lambda$4(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List applyDownloadUrlsParsing$lambda$5$lambda$3(BaseMediaModel it) {
        Integer intOrNull;
        Intrinsics.checkNotNullParameter(it, "it");
        List<BaseMediaModel.DownloadFile> list = it.list;
        Intrinsics.checkNotNullExpressionValue(list, "list");
        List<BaseMediaModel.DownloadFile> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (Iterator it2 = list2.iterator(); it2.hasNext(); it2 = it2) {
            BaseMediaModel.DownloadFile downloadFile = (BaseMediaModel.DownloadFile) it2.next();
            String str = downloadFile.path;
            String str2 = downloadFile.real_quality;
            String str3 = downloadFile.format;
            String str4 = downloadFile.size;
            int i = downloadFile.vip_only;
            String str5 = downloadFile.count;
            int iIntValue = (str5 == null || (intOrNull = StringsKt.toIntOrNull(str5)) == null) ? 0 : intOrNull.intValue();
            long j = downloadFile.dateline;
            arrayList.add(new DownloadUrl(str, str2, str3, str4, Integer.valueOf(i), Integer.valueOf(iIntValue), Long.valueOf(j), String.valueOf(downloadFile.fid), String.valueOf(downloadFile.tmfid), String.valueOf(downloadFile.mmfid), downloadFile.filename, null, null, null, 0, 0, downloadFile.h265, downloadFile.bitstream, Integer.valueOf(downloadFile.original), Integer.valueOf(downloadFile.hdr), Integer.valueOf(downloadFile.colorbit), downloadFile.size_bytes, downloadFile.hls, 63488, null));
        }
        return arrayList;
    }

    @JvmStatic
    public static final ObservableTransformer<String, BaseMediaModel> applyPlayUrlsParsing() {
        return new ObservableTransformer() { // from class: com.movieboxpro.android.utils.DownloadUrlTransformer$$ExternalSyntheticLambda7
            @Override // io.reactivex.ObservableTransformer
            public final ObservableSource apply(Observable observable) {
                return DownloadUrlTransformer.applyPlayUrlsParsing$lambda$8(observable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource applyPlayUrlsParsing$lambda$8(Observable upstream) {
        Intrinsics.checkNotNullParameter(upstream, "upstream");
        Observable observableCompose = upstream.compose(RxUtils.rxTranslate2Bean(JsonObject.class));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.utils.DownloadUrlTransformer$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return DownloadUrlTransformer.applyPlayUrlsParsing$lambda$8$lambda$6((JsonObject) obj);
            }
        };
        return observableCompose.map(new Function() { // from class: com.movieboxpro.android.utils.DownloadUrlTransformer$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return DownloadUrlTransformer.applyPlayUrlsParsing$lambda$8$lambda$7(function1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel applyPlayUrlsParsing$lambda$8$lambda$7(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (BaseMediaModel) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel applyPlayUrlsParsing$lambda$8$lambda$6(JsonObject it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return INSTANCE.parseTvDownloadUrls(it, true);
    }

    private final BaseMediaModel parseTvDownloadUrls(JsonObject it, boolean needOrgFile) {
        Object objM1917constructorimpl;
        List<BaseMediaModel.DownloadFile> listEmptyList;
        Object objM1917constructorimpl2;
        ArrayList arrayListEmptyList;
        Long file_size;
        JsonElement jsonElement = it.get("list");
        Intrinsics.checkNotNull(jsonElement, "null cannot be cast to non-null type com.google.gson.JsonArray");
        JsonArray jsonArray = (JsonArray) jsonElement;
        if (!jsonArray.isEmpty()) {
            JsonElement jsonElement2 = jsonArray.get(0);
            Intrinsics.checkNotNull(jsonElement2, "null cannot be cast to non-null type com.google.gson.JsonObject");
            if (((JsonObject) jsonElement2).get("hls_quality_list") != null) {
                JsonElement jsonElement3 = it.get(new String[]{"list"}[0]);
                Intrinsics.checkNotNullExpressionValue(jsonElement3, "get(...)");
                String json = GsonExtensionsKt.getGSON().toJson(jsonElement3);
                Gson gson = GsonExtensionsKt.getGSON();
                try {
                    Result.Companion companion = Result.INSTANCE;
                    Object objFromJson = gson.fromJson(json, new ParameterizedTypeImpl(HlsResponse.class));
                    objM1917constructorimpl2 = Result.m1917constructorimpl(objFromJson instanceof List ? (List) objFromJson : null);
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM1917constructorimpl2 = Result.m1917constructorimpl(ResultKt.createFailure(th));
                }
                List<HlsResponse> arrayList = (List) (Result.m1923isFailureimpl(objM1917constructorimpl2) ? null : objM1917constructorimpl2);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                ArrayList arrayList2 = new ArrayList();
                for (HlsResponse hlsResponse : arrayList) {
                    if (needOrgFile) {
                        hlsResponse.getOrg_file().real_quality = hlsResponse.getOrg_file().quality;
                        String str = hlsResponse.getOrg_file().bitstream;
                        int i = str != null ? Integer.parseInt(str) : 0;
                        if (i != 0) {
                            if (i < 1014) {
                                hlsResponse.getOrg_file().bitstream = i + "K";
                            } else {
                                String str2 = new DecimalFormat(IdManager.DEFAULT_VERSION_NAME).format(Float.valueOf(i / 1024.0f));
                                hlsResponse.getOrg_file().bitstream = str2 + "M";
                            }
                        }
                        arrayList2.add(hlsResponse.getOrg_file());
                    }
                    List<HlsItem> hls_quality_list = hlsResponse.getHls_quality_list();
                    if (hls_quality_list == null) {
                        arrayListEmptyList = CollectionsKt.emptyList();
                    } else {
                        List<HlsItem> list = hls_quality_list;
                        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                        for (HlsItem hlsItem : list) {
                            BaseMediaModel.DownloadFile downloadFile = new BaseMediaModel.DownloadFile();
                            String download_url = hlsItem.getDownload_url();
                            downloadFile.path = (download_url == null || StringsKt.isBlank(download_url)) ? hlsItem.getM3u8_url() : hlsItem.getDownload_url();
                            downloadFile.quality = hlsItem.getQuality();
                            downloadFile.real_quality = hlsItem.getQuality();
                            downloadFile.h265 = Intrinsics.areEqual(hlsItem.getFormat(), "h265") ? 1 : 0;
                            downloadFile.hdr = hlsItem.getHdr();
                            downloadFile.fid = hlsResponse.getOrg_file().fid;
                            downloadFile.vip_only = hlsItem.getVip_only();
                            downloadFile.channel_layout = hlsItem.getChannel_layout();
                            downloadFile.type = hlsItem.getType();
                            downloadFile.sample_rate = hlsItem.getSample_rate();
                            Long file_size2 = hlsItem.getFile_size();
                            downloadFile.size_bytes = file_size2 != null ? file_size2.longValue() : 0L;
                            if (hlsItem.getFile_size() != null && ((file_size = hlsItem.getFile_size()) == null || file_size.longValue() != 0)) {
                                downloadFile.size = FileUtils.byte2FitMemorySize(hlsItem.getFile_size().longValue());
                            }
                            downloadFile.dateline = hlsResponse.getOrg_file().dateline;
                            downloadFile.filename = hlsResponse.getOrg_file().filename;
                            downloadFile.tmfid = hlsResponse.getOrg_file().tmfid;
                            downloadFile.mmfid = hlsResponse.getOrg_file().mmfid;
                            downloadFile.lang = hlsItem.getLang();
                            downloadFile.hls = true;
                            downloadFile.bitstream = hlsItem.getBitrate();
                            downloadFile.bitrate = hlsItem.getBitrate();
                            downloadFile.format = hlsItem.getFormat();
                            downloadFile.name = hlsItem.getName();
                            arrayList3.add(downloadFile);
                        }
                        arrayListEmptyList = arrayList3;
                    }
                    arrayList2.addAll(arrayListEmptyList);
                }
                BaseMediaModel baseMediaModel = new BaseMediaModel();
                baseMediaModel.list = arrayList2;
                return baseMediaModel;
            }
            JsonElement jsonElement4 = it.get(new String[]{"list"}[0]);
            Intrinsics.checkNotNullExpressionValue(jsonElement4, "get(...)");
            String json2 = GsonExtensionsKt.getGSON().toJson(jsonElement4);
            Gson gson2 = GsonExtensionsKt.getGSON();
            try {
                Result.Companion companion3 = Result.INSTANCE;
                Object objFromJson2 = gson2.fromJson(json2, new ParameterizedTypeImpl(HlsResponse.class));
                objM1917constructorimpl = Result.m1917constructorimpl(objFromJson2 instanceof List ? (List) objFromJson2 : null);
            } catch (Throwable th2) {
                Result.Companion companion4 = Result.INSTANCE;
                objM1917constructorimpl = Result.m1917constructorimpl(ResultKt.createFailure(th2));
            }
            ArrayList arrayList4 = (List) (Result.m1923isFailureimpl(objM1917constructorimpl) ? null : objM1917constructorimpl);
            if (arrayList4 == null) {
                arrayList4 = new ArrayList();
            }
            BaseMediaModel baseMediaModel2 = new BaseMediaModel();
            HlsResponse hlsResponse2 = (HlsResponse) CollectionsKt.firstOrNull(arrayList4);
            if (hlsResponse2 == null || (listEmptyList = hlsResponse2.getQuality_list()) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            baseMediaModel2.list = listEmptyList;
            return baseMediaModel2;
        }
        Object objFromJson3 = GsonExtensionsKt.getGSON().fromJson((JsonElement) it, (Class<Object>) BaseMediaModel.class);
        Intrinsics.checkNotNullExpressionValue(objFromJson3, "fromJson(...)");
        return (BaseMediaModel) objFromJson3;
    }
}
