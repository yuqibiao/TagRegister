package com.afrid.tag_register.net;

import com.afrid.tag_register.net.api.NetApi;
import com.afrid.tag_register.utils.gson.DoubleDefault0Adapter;
import com.afrid.tag_register.utils.gson.IntegerDefault0Adapter;
import com.afrid.tag_register.utils.gson.LongDefault0Adapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.yyyu.baselibrary.utils.MyLog;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 功能：retrofit 网络请求创建
 *
 * @author yu
 * @version 1.0
 * @date 2017/8/10
 */

public class APIFactory {

    //藤原
    public static final String BASE_URL = "http://47.100.100.217:8080/ty/";
    //盛业
    //public static final String BASE_URL = "http://47.100.100.217:8080/sy/";
    //public static final String BASE_URL = "http://192.168.56.2:8080/sy/";

    //惠州-兔
   // public static final String BASE_URL = "http://119.23.30.113:8080/dh/";
    //厦门库
    //public static final String BASE_URL = "http://119.23.30.113:8080/xiamen/";
    //郑州库
    //public static final String BASE_URL = "http://47.100.100.217:8080/zz/";
    //测试库(阿里云 香港)
    //public static final String BASE_URL = "http://101.132.125.217:8080/hongkong/";
    //本地测试库
    //public static final String BASE_URL = "http://192.168.33.110:8080/test/";
    //云端测试库
    //public static final String BASE_URL = "http://101.132.125.217:8080/test/";
    //昆明库
    //public static final String BASE_URL="http://39.108.109.86:8080/tag-manager-kunming/";

    private static final int DEFAULT_TIMEOUT = 1;

    private Retrofit.Builder builder;


    private APIFactory() {
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                MyLog.i("APIFactory: OkHttpClient", "OkHttpMessage:" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        OkHttpClient.Builder httpClientBuild = new OkHttpClient.Builder();
        httpClientBuild.addInterceptor(loggingInterceptor);
        httpClientBuild.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES);
        httpClientBuild.readTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES);
        httpClientBuild.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.MINUTES);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder
                .registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        return new Date(json.getAsJsonPrimitive().getAsLong());
                    }
                })
                .registerTypeAdapter(Integer.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(int.class, new IntegerDefault0Adapter())
                .registerTypeAdapter(Double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(double.class, new DoubleDefault0Adapter())
                .registerTypeAdapter(Long.class, new LongDefault0Adapter())
                .registerTypeAdapter(long.class, new LongDefault0Adapter());
        Gson gson = gsonBuilder.create();
        builder = new Retrofit.Builder()
                .client(httpClientBuild.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());
    }

    private static class SingletonHolder {
        private static final APIFactory INSTANCE = new APIFactory();
    }

    public static APIFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public NetApi createKunmingApi() {
        Retrofit retrofit = builder.baseUrl(BASE_URL).build();
        NetApi kunmingApi = retrofit.create(NetApi.class);
        return kunmingApi;
    }


}
