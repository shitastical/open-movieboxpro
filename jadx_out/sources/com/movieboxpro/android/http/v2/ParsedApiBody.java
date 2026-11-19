package com.movieboxpro.android.http.v2;

import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MultipartBody;

/* JADX INFO: compiled from: ApiV2RequestBodyReader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012&\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ)\u0010\u0013\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\t\u0010\u0015\u001a\u00020\nHÆ\u0003JM\u0010\u0016\u001a\u00020\u00002(\b\u0002\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u0017\u001a\u00020\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0004HÖ\u0001R1\u0010\u0002\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0003j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u0001`\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, d2 = {"Lcom/movieboxpro/android/http/v2/ParsedApiBody;", "", "fields", "Ljava/util/LinkedHashMap;", "", "Lkotlin/collections/LinkedHashMap;", "fileParts", "", "Lokhttp3/MultipartBody$Part;", "multipart", "", "<init>", "(Ljava/util/LinkedHashMap;Ljava/util/List;Z)V", "getFields", "()Ljava/util/LinkedHashMap;", "getFileParts", "()Ljava/util/List;", "getMultipart", "()Z", "component1", "component2", "component3", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ParsedApiBody {
    private final LinkedHashMap<String, Object> fields;
    private final List<MultipartBody.Part> fileParts;
    private final boolean multipart;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ParsedApiBody copy$default(ParsedApiBody parsedApiBody, LinkedHashMap linkedHashMap, List list, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            linkedHashMap = parsedApiBody.fields;
        }
        if ((i & 2) != 0) {
            list = parsedApiBody.fileParts;
        }
        if ((i & 4) != 0) {
            z = parsedApiBody.multipart;
        }
        return parsedApiBody.copy(linkedHashMap, list, z);
    }

    public final LinkedHashMap<String, Object> component1() {
        return this.fields;
    }

    public final List<MultipartBody.Part> component2() {
        return this.fileParts;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final boolean getMultipart() {
        return this.multipart;
    }

    public final ParsedApiBody copy(LinkedHashMap<String, Object> fields, List<MultipartBody.Part> fileParts, boolean multipart) {
        Intrinsics.checkNotNullParameter(fields, "fields");
        Intrinsics.checkNotNullParameter(fileParts, "fileParts");
        return new ParsedApiBody(fields, fileParts, multipart);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ParsedApiBody)) {
            return false;
        }
        ParsedApiBody parsedApiBody = (ParsedApiBody) other;
        return Intrinsics.areEqual(this.fields, parsedApiBody.fields) && Intrinsics.areEqual(this.fileParts, parsedApiBody.fileParts) && this.multipart == parsedApiBody.multipart;
    }

    public int hashCode() {
        return (((this.fields.hashCode() * 31) + this.fileParts.hashCode()) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.multipart);
    }

    public String toString() {
        return "ParsedApiBody(fields=" + this.fields + ", fileParts=" + this.fileParts + ", multipart=" + this.multipart + ")";
    }

    public ParsedApiBody(LinkedHashMap<String, Object> fields, List<MultipartBody.Part> fileParts, boolean z) {
        Intrinsics.checkNotNullParameter(fields, "fields");
        Intrinsics.checkNotNullParameter(fileParts, "fileParts");
        this.fields = fields;
        this.fileParts = fileParts;
        this.multipart = z;
    }

    public final LinkedHashMap<String, Object> getFields() {
        return this.fields;
    }

    public final List<MultipartBody.Part> getFileParts() {
        return this.fileParts;
    }

    public final boolean getMultipart() {
        return this.multipart;
    }
}
