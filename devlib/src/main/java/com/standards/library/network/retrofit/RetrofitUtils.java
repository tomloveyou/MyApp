package com.standards.library.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.standards.library.network.NetworkConfig;


import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author huangnan
 */
public class RetrofitUtils {
    /**
     * 默认超时时间
     */
    private static final long DEFAULT_TIME_OUT = 15;
    private static volatile RetrofitUtils retrofitUtils;
    private Retrofit retrofit;
    private HashMap<String, Object> mServices;

    private RetrofitUtils() {
        //添加gson
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .create();

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        //加入log信息
        client.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        // 方便手动查看string日志
//        client.addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Response proceed = chain.proceed(chain.request());
//                String string = proceed.body().string();
//                return chain.proceed(chain.request());
//            }
//        });

        retrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl(NetworkConfig.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client.build())
                .build();
        mServices = new HashMap<>();
    }

    public void resetBaseUrl(String url) {
        retrofit = retrofit.newBuilder().baseUrl(url).build();
    }

    public static RetrofitUtils getInstance() {

        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public <T> T getService(Class<T> clazz) {
        T service = (T) mServices.get(clazz.getSimpleName());
        if (service != null) {
            return service;
        }
        mServices.put(clazz.getCanonicalName(), clazz);
        return retrofit.create(clazz);
    }
}
