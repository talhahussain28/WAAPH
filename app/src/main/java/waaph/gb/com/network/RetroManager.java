package waaph.gb.com.network;

import android.content.Context;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroManager {

    public Retrofit retrofit;
    static RetroManager retroManager = null;
    private static Context mContext;

    public static RetroManager getInstance(Context context){
        mContext = context;
        if(retroManager == null){
            retroManager = new RetroManager();
        }
        return retroManager;
    }

    private RetroManager() {
        // create interceptor...
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();
                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder().header("Authorization", "auth-value"); // <-- put your auth here...
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
      //  httpClient.addNetworkInterceptor(new NetworkInterceptor(mContext));
        httpClient.readTimeout(30, TimeUnit.SECONDS); // default readTimeOut is 10s, change it to 30 seconds...
        httpClient.connectTimeout(15, TimeUnit.SECONDS);// default readTimeOut is 10s, change it to 30 seconds...
        OkHttpClient client = httpClient.build();
        retrofit = new Retrofit
                .Builder()
                .baseUrl("http://110.93.250.125/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

}