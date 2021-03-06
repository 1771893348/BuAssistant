package com.wcjr.buassistant.data.remote;


import android.support.annotation.NonNull;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/**
 *
 */
public class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    public enum Level {
        /**
         * No logs.
         */
        NONE,
//        /**
//         * Logs request and response lines.
//         * <p/>
//         * Example:
//         * <pre>{@code
//         * --> POST /greeting HTTP/1.1 (3-byte body)
//         * <p/>
//         * <-- HTTP/1.1 200 OK (22ms, 6-byte body)
//         * }</pre>
//         */
//        BASIC,
        /**
         * Logs request and response lines and their respective headers.
         * <p/>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * --> END POST
         * <p/>
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <-- END HTTP
         * }</pre>
         */
        HEADERS,
        /**
         * Logs request and response lines and their respective headers and bodies (if present).
         * <p/>
         * Example:
         * <pre>{@code
         * --> POST /greeting HTTP/1.1
         * Host: example.com
         * Content-Type: plain/text
         * Content-Length: 3
         * <p/>
         * Hi?
         * --> END GET
         * <p/>
         * <-- HTTP/1.1 200 OK (22ms)
         * Content-Type: plain/text
         * Content-Length: 6
         * <p/>
         * Hello!
         * <-- END HTTP
         * }</pre>
         */
        BODY
    }

    public interface Logger {
        void log(String message);

//        /**
//         * A {@link Logger} defaults output appropriate for the current platform.
//         */
//        Logger DEFAULT = message -> Platform.get().log(Platform.INFO, message, null);
    }

//    public HttpLoggingInterceptor() {
//        this(Logger.DEFAULT);
//    }

    HttpLoggingInterceptor(Logger logger) {
        this.logger = logger;
    }

    private final Logger logger;

    private volatile Level level = Level.BODY;

    /**
     * Change the level at which this interceptor logs.
     */
//    public HttpLoggingInterceptor setLevel(Level level) {
//        if (level == null) throw new NullPointerException("level == null. Use Level.NONE instead.");
//        this.level = level;
//        return this;
//    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Level level = this.level;

        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }

        boolean logBody = level == Level.BODY;
        boolean logHeaders = logBody || level == Level.HEADERS;

        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;

        String requestStartMessage = request.method() + ' ' + request.url();
        if (!logHeaders && hasRequestBody) {
            requestStartMessage += " (" + requestBody.contentLength() + "-byte body)";
        }
        logger.log(requestStartMessage);

        if (logHeaders) {

            if (!logBody || !hasRequestBody) {
                logger.log("--> END " + request.method());
            } else if (bodyEncoded(request.headers())) {
                logger.log("--> END " + request.method() + " (encoded body omitted)");
            } else if (request.body() instanceof MultipartBody) {
                //如果是MultipartBody，会log出一大推乱码的东东
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);

                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    contentType.charset(UTF8);
                }

                logger.log(buffer.readString(UTF8));

//                logger.log(request.method() + " (" + requestBody.contentLength() + "-byte body)");
            }
        }

        long startNs = System.nanoTime();
        Response response;

//        try {
            //发送网络请求
            response = chain.proceed(request);
            long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
            logger.log(response.code() + ' ' + response.message() + " (" + tookMs + "ms" + ')');
//        } catch (SocketTimeoutException e) {
            //此处不抛异常  连接超时会crash 没有找到其他好的方法
//            e.printStackTrace();
//            response = new Response.Builder().request(chain.request()).protocol(Protocol.HTTP_1_1).code(500).message("SocketTimeoutException").build();
//        }
        return response;
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }

//    private static String protocol(Protocol protocol) {
//        return protocol == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1";
//    }
}
