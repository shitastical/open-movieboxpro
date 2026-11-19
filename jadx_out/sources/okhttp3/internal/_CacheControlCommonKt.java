package okhttp3.internal;

import com.google.common.net.HttpHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import okhttp3.CacheControl;
import okhttp3.Headers;

/* JADX INFO: compiled from: -CacheControlCommon.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0004*\u00020\u0005H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0002*\u00020\u0007H\u0000\u001a\f\u0010\b\u001a\u00020\u0002*\u00020\u0007H\u0000\u001a\f\u0010\t\u001a\u00020\u0002*\u00020\nH\u0000\u001a\f\u0010\u000b\u001a\u00020\n*\u00020\nH\u0000\u001a\f\u0010\f\u001a\u00020\n*\u00020\nH\u0000\u001a\f\u0010\r\u001a\u00020\n*\u00020\nH\u0000\u001a\f\u0010\u000e\u001a\u00020\n*\u00020\nH\u0000\u001a\f\u0010\u000f\u001a\u00020\n*\u00020\nH\u0000\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\u001a\u001e\u0010\u0013\u001a\u00020\u0004*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\u0004H\u0002¨\u0006\u0016"}, d2 = {"commonToString", "", "Lokhttp3/CacheControl;", "commonClampToInt", "", "", "commonForceNetwork", "Lokhttp3/CacheControl$Companion;", "commonForceCache", "commonBuild", "Lokhttp3/CacheControl$Builder;", "commonNoCache", "commonNoStore", "commonOnlyIfCached", "commonNoTransform", "commonImmutable", "commonParse", "headers", "Lokhttp3/Headers;", "indexOfElement", "characters", "startIndex", "okhttp"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class _CacheControlCommonKt {
    public static final int commonClampToInt(long j) {
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    public static final String commonToString(CacheControl cacheControl) {
        Intrinsics.checkNotNullParameter(cacheControl, "<this>");
        String headerValue = cacheControl.getHeaderValue();
        if (headerValue != null) {
            return headerValue;
        }
        StringBuilder sb = new StringBuilder();
        if (cacheControl.noCache()) {
            sb.append("no-cache, ");
        }
        if (cacheControl.noStore()) {
            sb.append("no-store, ");
        }
        if (cacheControl.maxAgeSeconds() != -1) {
            sb.append("max-age=");
            sb.append(cacheControl.maxAgeSeconds());
            sb.append(", ");
        }
        if (cacheControl.sMaxAgeSeconds() != -1) {
            sb.append("s-maxage=");
            sb.append(cacheControl.sMaxAgeSeconds());
            sb.append(", ");
        }
        if (cacheControl.getIsPrivate()) {
            sb.append("private, ");
        }
        if (cacheControl.getIsPublic()) {
            sb.append("public, ");
        }
        if (cacheControl.mustRevalidate()) {
            sb.append("must-revalidate, ");
        }
        if (cacheControl.maxStaleSeconds() != -1) {
            sb.append("max-stale=");
            sb.append(cacheControl.maxStaleSeconds());
            sb.append(", ");
        }
        if (cacheControl.minFreshSeconds() != -1) {
            sb.append("min-fresh=");
            sb.append(cacheControl.minFreshSeconds());
            sb.append(", ");
        }
        if (cacheControl.onlyIfCached()) {
            sb.append("only-if-cached, ");
        }
        if (cacheControl.noTransform()) {
            sb.append("no-transform, ");
        }
        if (cacheControl.immutable()) {
            sb.append("immutable, ");
        }
        if (sb.length() == 0) {
            return "";
        }
        Intrinsics.checkNotNullExpressionValue(sb.delete(sb.length() - 2, sb.length()), "delete(...)");
        String string = sb.toString();
        cacheControl.setHeaderValue$okhttp(string);
        return string;
    }

    public static final CacheControl commonForceNetwork(CacheControl.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        return new CacheControl.Builder().noCache().build();
    }

    public static final CacheControl commonForceCache(CacheControl.Companion companion) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        CacheControl.Builder builderOnlyIfCached = new CacheControl.Builder().onlyIfCached();
        Duration.Companion companion2 = Duration.INSTANCE;
        return builderOnlyIfCached.m3537maxStaleLRDsOJo(DurationKt.toDuration(Integer.MAX_VALUE, DurationUnit.SECONDS)).build();
    }

    public static final CacheControl commonBuild(CacheControl.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        return new CacheControl(builder.getNoCache(), builder.getNoStore(), builder.getMaxAgeSeconds(), -1, false, false, false, builder.getMaxStaleSeconds(), builder.getMinFreshSeconds(), builder.getOnlyIfCached(), builder.getNoTransform(), builder.getImmutable(), null);
    }

    public static final CacheControl.Builder commonNoCache(CacheControl.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.setNoCache$okhttp(true);
        return builder;
    }

    public static final CacheControl.Builder commonNoStore(CacheControl.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.setNoStore$okhttp(true);
        return builder;
    }

    public static final CacheControl.Builder commonOnlyIfCached(CacheControl.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.setOnlyIfCached$okhttp(true);
        return builder;
    }

    public static final CacheControl.Builder commonNoTransform(CacheControl.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.setNoTransform$okhttp(true);
        return builder;
    }

    public static final CacheControl.Builder commonImmutable(CacheControl.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        builder.setImmutable$okhttp(true);
        return builder;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x004e  */
    /* JADX WARN: Code duplicated, block: B:17:0x006d  */
    /* JADX WARN: Code duplicated, block: B:28:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:40:0x0105  */
    /* JADX WARN: Code duplicated, block: B:43:0x0113  */
    /* JADX WARN: Code duplicated, block: B:55:0x0153  */
    /* JADX WARN: Code duplicated, block: B:58:0x0161  */
    /* JADX WARN: Code duplicated, block: B:76:0x00e1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x00f1 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x00d9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x018c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x00e9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x011b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x012b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x013b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x016a A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x017b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x00fe A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:0x00f9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:88:0x010d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:89:0x014b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x015b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:91:0x0184 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x0173 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:93:0x0143 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x0133 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:95:0x0123 A[SYNTHETIC] */
    public static final CacheControl commonParse(CacheControl.Companion companion, Headers headers) {
        int iIndexOfElement;
        int iIndexOfElement2;
        String string;
        String string2;
        Headers headers2 = headers;
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(headers2, "headers");
        int size = headers.size();
        boolean z = true;
        int i = 0;
        boolean z2 = true;
        String str = null;
        boolean z3 = false;
        boolean z4 = false;
        int nonNegativeInt = -1;
        int nonNegativeInt2 = -1;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        int nonNegativeInt3 = -1;
        int nonNegativeInt4 = -1;
        boolean z8 = false;
        boolean z9 = false;
        boolean z10 = false;
        while (i < size) {
            String strName = headers2.name(i);
            String strValue = headers2.value(i);
            if (!StringsKt.equals(strName, HttpHeaders.CACHE_CONTROL, z)) {
                if (StringsKt.equals(strName, HttpHeaders.PRAGMA, z)) {
                }
                i++;
                headers2 = headers;
                size = size;
            } else {
                if (str == null) {
                    str = strValue;
                }
                iIndexOfElement = 0;
                while (iIndexOfElement < strValue.length()) {
                    iIndexOfElement2 = indexOfElement(strValue, "=,;", iIndexOfElement);
                    String strSubstring = strValue.substring(iIndexOfElement, iIndexOfElement2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "substring(...)");
                    string = StringsKt.trim((CharSequence) strSubstring).toString();
                    if (iIndexOfElement2 != strValue.length()) {
                        size = size;
                        if (strValue.charAt(iIndexOfElement2) == ',' && strValue.charAt(iIndexOfElement2) != ';') {
                            int iIndexOfNonWhitespace = _UtilCommonKt.indexOfNonWhitespace(strValue, iIndexOfElement2 + 1);
                            if (iIndexOfNonWhitespace < strValue.length() && strValue.charAt(iIndexOfNonWhitespace) == '\"') {
                                int i2 = iIndexOfNonWhitespace + 1;
                                int iIndexOf$default = StringsKt.indexOf$default((CharSequence) strValue, Typography.quote, i2, false, 4, (Object) null);
                                string2 = strValue.substring(i2, iIndexOf$default);
                                Intrinsics.checkNotNullExpressionValue(string2, "substring(...)");
                                iIndexOfElement = iIndexOf$default + 1;
                            } else {
                                iIndexOfElement = indexOfElement(strValue, ",;", iIndexOfNonWhitespace);
                                String strSubstring2 = strValue.substring(iIndexOfNonWhitespace, iIndexOfElement);
                                Intrinsics.checkNotNullExpressionValue(strSubstring2, "substring(...)");
                                string2 = StringsKt.trim((CharSequence) strSubstring2).toString();
                            }
                        }
                        z = true;
                        if (StringsKt.equals("no-cache", string, true)) {
                            z3 = true;
                        } else if (StringsKt.equals("no-store", string, true)) {
                            z4 = true;
                        } else if (StringsKt.equals("max-age", string, true)) {
                            nonNegativeInt = _UtilCommonKt.toNonNegativeInt(string2, -1);
                        } else if (StringsKt.equals("s-maxage", string, true)) {
                            nonNegativeInt2 = _UtilCommonKt.toNonNegativeInt(string2, -1);
                        } else if (StringsKt.equals("private", string, true)) {
                            z5 = true;
                        } else if (StringsKt.equals("public", string, true)) {
                            z6 = true;
                        } else if (StringsKt.equals("must-revalidate", string, true)) {
                            z7 = true;
                        } else if (StringsKt.equals("max-stale", string, true)) {
                            nonNegativeInt3 = _UtilCommonKt.toNonNegativeInt(string2, Integer.MAX_VALUE);
                        } else if (StringsKt.equals("min-fresh", string, true)) {
                            nonNegativeInt4 = _UtilCommonKt.toNonNegativeInt(string2, -1);
                        } else if (StringsKt.equals("only-if-cached", string, true)) {
                            z8 = true;
                        } else if (StringsKt.equals("no-transform", string, true)) {
                            z9 = true;
                        } else if (StringsKt.equals("immutable", string, true)) {
                            z10 = true;
                        }
                    } else {
                        size = size;
                    }
                    iIndexOfElement = iIndexOfElement2 + 1;
                    string2 = null;
                    z = true;
                    if (StringsKt.equals("no-cache", string, true)) {
                        z3 = true;
                    } else if (StringsKt.equals("no-store", string, true)) {
                        z4 = true;
                    } else if (StringsKt.equals("max-age", string, true)) {
                        nonNegativeInt = _UtilCommonKt.toNonNegativeInt(string2, -1);
                    } else if (StringsKt.equals("s-maxage", string, true)) {
                        nonNegativeInt2 = _UtilCommonKt.toNonNegativeInt(string2, -1);
                    } else if (StringsKt.equals("private", string, true)) {
                        z5 = true;
                    } else if (StringsKt.equals("public", string, true)) {
                        z6 = true;
                    } else if (StringsKt.equals("must-revalidate", string, true)) {
                        z7 = true;
                    } else if (StringsKt.equals("max-stale", string, true)) {
                        nonNegativeInt3 = _UtilCommonKt.toNonNegativeInt(string2, Integer.MAX_VALUE);
                    } else if (StringsKt.equals("min-fresh", string, true)) {
                        nonNegativeInt4 = _UtilCommonKt.toNonNegativeInt(string2, -1);
                    } else if (StringsKt.equals("only-if-cached", string, true)) {
                        z8 = true;
                    } else if (StringsKt.equals("no-transform", string, true)) {
                        z9 = true;
                    } else if (StringsKt.equals("immutable", string, true)) {
                        z10 = true;
                    }
                }
                i++;
                headers2 = headers;
                size = size;
            }
            z2 = false;
            iIndexOfElement = 0;
            while (iIndexOfElement < strValue.length()) {
                iIndexOfElement2 = indexOfElement(strValue, "=,;", iIndexOfElement);
                String strSubstring3 = strValue.substring(iIndexOfElement, iIndexOfElement2);
                Intrinsics.checkNotNullExpressionValue(strSubstring3, "substring(...)");
                string = StringsKt.trim((CharSequence) strSubstring3).toString();
                if (iIndexOfElement2 != strValue.length()) {
                    size = size;
                    if (strValue.charAt(iIndexOfElement2) == ',') {
                    }
                    z = true;
                    if (StringsKt.equals("no-cache", string, true)) {
                        z3 = true;
                    } else if (StringsKt.equals("no-store", string, true)) {
                        z4 = true;
                    } else if (StringsKt.equals("max-age", string, true)) {
                        nonNegativeInt = _UtilCommonKt.toNonNegativeInt(string2, -1);
                    } else if (StringsKt.equals("s-maxage", string, true)) {
                        nonNegativeInt2 = _UtilCommonKt.toNonNegativeInt(string2, -1);
                    } else if (StringsKt.equals("private", string, true)) {
                        z5 = true;
                    } else if (StringsKt.equals("public", string, true)) {
                        z6 = true;
                    } else if (StringsKt.equals("must-revalidate", string, true)) {
                        z7 = true;
                    } else if (StringsKt.equals("max-stale", string, true)) {
                        nonNegativeInt3 = _UtilCommonKt.toNonNegativeInt(string2, Integer.MAX_VALUE);
                    } else if (StringsKt.equals("min-fresh", string, true)) {
                        nonNegativeInt4 = _UtilCommonKt.toNonNegativeInt(string2, -1);
                    } else if (StringsKt.equals("only-if-cached", string, true)) {
                        z8 = true;
                    } else if (StringsKt.equals("no-transform", string, true)) {
                        z9 = true;
                    } else if (StringsKt.equals("immutable", string, true)) {
                        z10 = true;
                    }
                } else {
                    size = size;
                }
                iIndexOfElement = iIndexOfElement2 + 1;
                string2 = null;
                z = true;
                if (StringsKt.equals("no-cache", string, true)) {
                    z3 = true;
                } else if (StringsKt.equals("no-store", string, true)) {
                    z4 = true;
                } else if (StringsKt.equals("max-age", string, true)) {
                    nonNegativeInt = _UtilCommonKt.toNonNegativeInt(string2, -1);
                } else if (StringsKt.equals("s-maxage", string, true)) {
                    nonNegativeInt2 = _UtilCommonKt.toNonNegativeInt(string2, -1);
                } else if (StringsKt.equals("private", string, true)) {
                    z5 = true;
                } else if (StringsKt.equals("public", string, true)) {
                    z6 = true;
                } else if (StringsKt.equals("must-revalidate", string, true)) {
                    z7 = true;
                } else if (StringsKt.equals("max-stale", string, true)) {
                    nonNegativeInt3 = _UtilCommonKt.toNonNegativeInt(string2, Integer.MAX_VALUE);
                } else if (StringsKt.equals("min-fresh", string, true)) {
                    nonNegativeInt4 = _UtilCommonKt.toNonNegativeInt(string2, -1);
                } else if (StringsKt.equals("only-if-cached", string, true)) {
                    z8 = true;
                } else if (StringsKt.equals("no-transform", string, true)) {
                    z9 = true;
                } else if (StringsKt.equals("immutable", string, true)) {
                    z10 = true;
                }
            }
            i++;
            headers2 = headers;
            size = size;
        }
        return new CacheControl(z3, z4, nonNegativeInt, nonNegativeInt2, z5, z6, z7, nonNegativeInt3, nonNegativeInt4, z8, z9, z10, !z2 ? null : str);
    }

    static /* synthetic */ int indexOfElement$default(String str, String str2, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return indexOfElement(str, str2, i);
    }

    private static final int indexOfElement(String str, String str2, int i) {
        int length = str.length();
        while (i < length) {
            if (StringsKt.contains$default((CharSequence) str2, str.charAt(i), false, 2, (Object) null)) {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
