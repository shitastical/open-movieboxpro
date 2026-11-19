package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TrailersSource;
import okhttp3.internal.UnreadableResponseBody;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.http2.ConnectionShutdownException;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

/* JADX INFO: compiled from: CallServerInterceptor.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "<init>", "()V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "shouldIgnoreAndWaitForRealResponse", "", "code", "", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CallServerInterceptor implements Interceptor {
    public static final CallServerInterceptor INSTANCE = new CallServerInterceptor();

    private final boolean shouldIgnoreAndWaitForRealResponse(int code) {
        if (code == 100) {
            return true;
        }
        return 102 <= code && code < 200;
    }

    private CallServerInterceptor() {
    }

    /* JADX WARN: Code duplicated, block: B:100:0x0214  */
    /* JADX WARN: Code duplicated, block: B:41:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:43:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:95:0x0207  */
    /* JADX WARN: Code duplicated, block: B:97:0x0210  */
    /* JADX WARN: Code duplicated, block: B:98:0x0211  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [okhttp3.Response$Builder] */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object, okhttp3.Response$Builder] */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17, types: [okhttp3.Response$Builder] */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v20 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v23 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v9 */
    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        boolean z;
        IOException iOException;
        ?? responseHeaders;
        Response responseBuild;
        ?? r6;
        Intrinsics.checkNotNullParameter(chain, "chain");
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        final Exchange exchange = realInterceptorChain.getExchange();
        Intrinsics.checkNotNull(exchange);
        Request request = realInterceptorChain.getRequest();
        RequestBody requestBodyBody = request.body();
        long jCurrentTimeMillis = System.currentTimeMillis();
        boolean z2 = false;
        ?? responseHeaders2 = (!HttpMethod.permitsRequestBody(request.method()) || requestBodyBody == null) ? 0 : 1;
        boolean zEquals = StringsKt.equals("upgrade", request.header(com.google.common.net.HttpHeaders.CONNECTION), true);
        try {
            exchange.writeRequestHeaders(request);
            try {
                if (responseHeaders2 != 0) {
                    if (StringsKt.equals("100-continue", request.header(com.google.common.net.HttpHeaders.EXPECT), true)) {
                        exchange.flushRequest();
                        responseHeaders2 = exchange.readResponseHeaders(true);
                        try {
                            exchange.responseHeadersStart();
                            z = false;
                            r6 = responseHeaders2;
                        } catch (IOException e) {
                            e = e;
                            z = true;
                            if (!(e instanceof ConnectionShutdownException)) {
                                throw e;
                            }
                            if (exchange.getHasFailure()) {
                                throw e;
                            }
                            ?? r17 = responseHeaders2;
                            iOException = e;
                            responseHeaders = r17;
                        }
                    } else {
                        r6 = 0;
                        z = true;
                    }
                    if (r6 == 0) {
                        if (requestBodyBody.isDuplex()) {
                            exchange.flushRequest();
                            requestBodyBody.writeTo(Okio.buffer(exchange.createRequestBody(request, true)));
                            responseHeaders2 = r6;
                        } else {
                            BufferedSink bufferedSinkBuffer = Okio.buffer(exchange.createRequestBody(request, false));
                            requestBodyBody.writeTo(bufferedSinkBuffer);
                            bufferedSinkBuffer.close();
                            responseHeaders2 = r6;
                        }
                    } else {
                        exchange.noRequestBody();
                        responseHeaders2 = r6;
                        if (!exchange.getConnection$okhttp().isMultiplexed$okhttp()) {
                            exchange.noNewExchangesOnConnection();
                            responseHeaders2 = r6;
                        }
                    }
                } else {
                    exchange.noRequestBody();
                    responseHeaders2 = 0;
                    z = true;
                }
                if (requestBodyBody == null || !requestBodyBody.isDuplex()) {
                    exchange.finishRequest();
                }
                responseHeaders = responseHeaders2;
                iOException = null;
            } catch (IOException e2) {
                e = e2;
                if (!(e instanceof ConnectionShutdownException)) {
                    throw e;
                }
                if (exchange.getHasFailure()) {
                    throw e;
                }
                ?? r18 = responseHeaders2;
                iOException = e;
                responseHeaders = r18;
            }
        } catch (IOException e3) {
            e = e3;
            responseHeaders2 = 0;
        }
        if (responseHeaders == 0) {
            try {
                responseHeaders = exchange.readResponseHeaders(false);
                Intrinsics.checkNotNull(responseHeaders);
                if (z) {
                    exchange.responseHeadersStart();
                    z = false;
                }
            } catch (IOException e4) {
                e = e4;
                if (iOException != null) {
                    ExceptionsKt.addSuppressed(iOException, e);
                    throw iOException;
                }
                throw e;
            }
        }
        Response responseBuild2 = responseHeaders.request(request).handshake(exchange.getConnection$okhttp().getHandshake()).sentRequestAtMillis(jCurrentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        int iCode = responseBuild2.code();
        while (shouldIgnoreAndWaitForRealResponse(iCode)) {
            try {
                Response.Builder responseHeaders3 = exchange.readResponseHeaders(z2);
                Intrinsics.checkNotNull(responseHeaders3);
                if (z) {
                    exchange.responseHeadersStart();
                }
                responseBuild2 = responseHeaders3.request(request).handshake(exchange.getConnection$okhttp().getHandshake()).sentRequestAtMillis(jCurrentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
                iCode = responseBuild2.code();
                z2 = false;
            } catch (IOException e5) {
                e = e5;
                if (iOException != null) {
                    ExceptionsKt.addSuppressed(iOException, e);
                    throw iOException;
                }
                throw e;
            }
        }
        exchange.responseHeadersEnd(responseBuild2);
        boolean z3 = iCode == 101;
        if (z3 && exchange.getConnection$okhttp().isMultiplexed$okhttp()) {
            throw new ProtocolException("Unexpected 101 code on HTTP/2 connection");
        }
        boolean z4 = z3 && StringsKt.equals("upgrade", Response.header$default(responseBuild2, com.google.common.net.HttpHeaders.CONNECTION, null, 2, null), true);
        if (zEquals && z4) {
            responseBuild = responseBuild2.newBuilder().body(new UnreadableResponseBody(responseBuild2.body().getMediaType(), responseBuild2.body().getContentLength())).socket(exchange.upgradeToSocket()).build();
        } else {
            final ResponseBody responseBodyOpenResponseBody = exchange.openResponseBody(responseBuild2);
            responseBuild = responseBuild2.newBuilder().body(responseBodyOpenResponseBody).trailers(new TrailersSource() { // from class: okhttp3.internal.http.CallServerInterceptor.intercept.1
                @Override // okhttp3.TrailersSource
                public Headers peek() {
                    return exchange.peekTrailers();
                }

                @Override // okhttp3.TrailersSource
                public Headers get() throws IOException {
                    BufferedSource bodySource = responseBodyOpenResponseBody.getBodySource();
                    if (bodySource.isOpen()) {
                        _UtilJvmKt.skipAll(bodySource);
                    }
                    Headers headersPeek = peek();
                    if (headersPeek != null) {
                        return headersPeek;
                    }
                    throw new IllegalStateException("null trailers after exhausting response body?!".toString());
                }
            }).build();
        }
        if (StringsKt.equals("close", responseBuild.request().header(com.google.common.net.HttpHeaders.CONNECTION), true) || StringsKt.equals("close", Response.header$default(responseBuild, com.google.common.net.HttpHeaders.CONNECTION, null, 2, null), true)) {
            exchange.noNewExchangesOnConnection();
        }
        if ((iCode != 204 && iCode != 205) || responseBuild.body().getContentLength() <= 0) {
            return responseBuild;
        }
        throw new ProtocolException("HTTP " + iCode + " had non-zero Content-Length: " + responseBuild.body().getContentLength());
    }
}
