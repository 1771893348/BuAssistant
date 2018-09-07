package com.wcjr.buassistant.data.remote;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.wcjr.buassistant.androidlibrary.common.Host;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 *
 * init all of the components used to access the net interface
 */
public class RetrofitUtil {
    private static final String TAG = "WCJR_Retrofit";

    private static APIService service = getRetrofit().create(APIService.class);
    private static Retrofit retrofit;
    private static OkHttpClient okHttpClient;
    protected static Context mContext;

    public static APIService getService(Context context) {
        mContext = context;
        return service;
    }

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
//            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.i(TAG, message));
//            OkHttpClient client = new OkHttpClient.Builder()
//                    .certificatePinner(new CertificatePinner.Builder()
//                            .add("down.jinrongweb.net", "sha256/goKoN3aAoFjmq4xG2DQuWnaJD2pqdiiMGDBZ9tFjUZo=")
//                            .add("www.jinrongweb.com", "sha256/b+QsNFiM0tD9dttEtGLTTV1Zchm67b6cgmeAHNJg5Es=")
//                            .add("jinrongweb.com", "sha256/StZKjqveasAYpNb20/fcDMLEKoBsm/dr1VpmkM8fTyg=")
//                            .build())
//                    .readTimeout(20, TimeUnit.SECONDS)
//                    .writeTimeout(20, TimeUnit.SECONDS)
//                    .connectTimeout(20, TimeUnit.SECONDS)
//                    .addInterceptor(interceptor)
//                    .build();
//            retrofit = new Retrofit.Builder()
//                    .client(client)
//                    .baseUrl(API_HOST)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .build();


            AssetManager assetManager = mContext.getAssets();
            OkHttpClient client = null;
            try {
                client = getOkHttpClient((Application) mContext,
                        assetManager.open("1_down.jinrongweb.net_bundle.crt"),
                        assetManager.open("1_jinrongweb.com_bundle.crt"),
                        assetManager.open("ServerCertificate.cer"));
                retrofit = new Retrofit.Builder()
                        .client(client)
                        .baseUrl(Host.API_HOST)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .build();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return retrofit;
    }

    /**
     * 获取oKHttpClient
     * certificates 证书信息 没有就传null
     * @return
     */
    public static OkHttpClient getOkHttpClient(Application appContext,InputStream... certificates) {
        if (okHttpClient == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> Log.i(TAG, message));
//            File sdcache = appContext.getExternalCacheDir();
            int cacheSize = 10 * 1024 * 1024;
//            assert sdcache != null;
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .addInterceptor(interceptor);
            if (certificates != null){
                try {
                    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                    keyStore.load(null);
                    int index = 0;
                    for (InputStream certificate : certificates) {
                        String certificateAlias = Integer.toString(index++);
                        keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                        try {
                            if (certificate != null)
                                certificate.close();
                        } catch (IOException ignored) {
                        }
                    }
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    trustManagerFactory.init(keyStore);
                    sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
                    builder.sslSocketFactory(sslContext.getSocketFactory());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            okHttpClient = builder.build();
        }
        return okHttpClient;
    }
    /**
     * 获取SSLSocketFactory
     *
     * @param certificates 证书流文件
     * @return
     */
    private static SSLSocketFactory getSSLSocketFactory(InputStream... certificates) {
        try {
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null);
            int index = 0;
            for (InputStream certificate : certificates) {
                String certificateAlias = Integer.toString(index++);
                keyStore.setCertificateEntry(certificateAlias, certificateFactory.generateCertificate(certificate));

                try {
                    if (certificate != null)
                        certificate.close();
                } catch (IOException e) {
                }
            }
            SSLContext sslContext = SSLContext.getInstance("TLS");
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    /**
//     * 对网络接口返回的Response进行分割操作
//     *
//     * @param response
//     * @param <T>
//     * @return
//     */
//    private  <T> Flowable<T> flatResponse(final Response<T> response) {
//        return Flowable.create(e -> {
//            if (response.code() == 200) {
//                e.onNext(response.body());
//                e.onComplete();
//            } else {
//                e.onError(new APIException(response.code(), response.message()));
//            }
//        }, BackpressureStrategy.BUFFER);
//    }


//    /**
//     * 自定义异常，当接口返回的{@link Response#code}不为200时，需要跑出此异常
//     * eg：登陆时验证码错误；参数为传递等
//     */
//    public static class APIException extends Exception {
//        public int code;
//        public String message;
//
//        private APIException(int code, String message) {
//            this.code = code;
//            this.message = message;
//        }
//
//        @Override
//        public String getMessage() {
//            return message;
//        }
//    }


//    protected <T> FlowableTransformer<Response<T>, T> applySchedulers() {
//        return (FlowableTransformer<Response<T>, T>) transformer;
//    }
//
//    final FlowableTransformer transformer = new FlowableTransformer() {
//        @Override
//        public Publisher apply(@NonNull Flowable upstream) {
//            return upstream.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .flatMap(o -> {
//                                return flatResponse((Response<Object>) o);
//                            }
//                    );
//        }
//
//    };


//    /**
//     * 当{@link APIService}中接口的注解为{@link retrofit2.http.Multipart}时，参数为{@link RequestBody}
//     * 生成对应的RequestBody
//     *
//     * @param param
//     * @return
//     */
//    protected RequestBody createRequestBody(int param) {
//        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
//    }
//
//    protected RequestBody createRequestBody(long param) {
//        return RequestBody.create(MediaType.parse("text/plain"), String.valueOf(param));
//    }
//
//    protected RequestBody createRequestBody(String param) {
//        return RequestBody.create(MediaType.parse("text/plain"), param);
//    }
//
//    protected RequestBody createRequestBody(File param) {
//        return RequestBody.create(MediaType.parse("image/*"), param);
//    }
//
//    /**
//     * 已二进制传递图片文件，对图片文件进行了压缩
//     *
//     * @param path 文件路径
//     * @return
//     */
//    protected RequestBody createPictureRequestBody(String path) {
//        Bitmap bitmap = ClippingPictureUtil.decodeResizeBitmapSd(path, 400, 800);
//        return RequestBody.create(MediaType.parse("image/*"), ClippingPictureUtil.bitmapToBytes(bitmap));
//    }
//
//    public static RequestBody createJsonRequestBody(String json) {
//        return RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),json);
//    }


}
