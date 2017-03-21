package www.melody.chat.http;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 *  Retrofit的接口管理类
 *
 * Created by ZhengSheng on 2017/3/20.
 */

public class RetrofitServiceManager {

    private final static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .create();

    public static final boolean isDebug = true;
    private static final int DEFAULT_CONNECT_TIME_OUT = 5; // 超时时间 5s
    private static final int DEFAULT_READ_TIME_OUT = 10;

    private Retrofit mRetrofit;

    private RetrofitServiceManager(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        if (RetrofitServiceManager.isDebug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(logging)
                    .addNetworkInterceptor(new StethoInterceptor());
        }
        // builder.writeTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//写操作 超时时间
        httpClientBuilder.connectTimeout(DEFAULT_CONNECT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        httpClientBuilder.readTimeout(DEFAULT_READ_TIME_OUT,TimeUnit.SECONDS);//读操作超时时间
        OkHttpClient client = httpClientBuilder.build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://gank.io/api/")
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    private static class SingletonHolder{
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }

    /**
     * 获取RetrofitServiceManager
     * @return
     */
    public static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service){
        return mRetrofit.create(service);
    }
}
