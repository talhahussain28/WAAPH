package waaph.gb.com.network;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import waaph.gb.com.utils.Constants;
import waaph.gb.com.utils.SaveInSharedPreference;

public class ServiceUtils {
    private static OkHttpClient.Builder okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();
//    private static final Dispatcher dispatcher = new Dispatcher();

    public static ApiInterface createService(){

       /* HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(logging);*/
//        okHttpClient.dispatcher(dispatcher);

        Retrofit builder = new Retrofit.Builder()
                .baseUrl("http://110.93.250.125/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();

        return builder.create(ApiInterface.class);
    }

    public static ApiInterface createServiceWithHeader(final Context context){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(logging);
//        okHttpClient.dispatcher(dispatcher);
        Log.d("Authentication: ", new SaveInSharedPreference(context).getString(Constants.Companion.getARG_TOKEN()));

        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("token", new SaveInSharedPreference(context).getString(Constants.Companion.getARG_TOKEN()))
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        Retrofit builder = new Retrofit.Builder()
                .baseUrl("http://110.93.250.125/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();

        return builder.create(ApiInterface.class);
    }

    public static ApiInterface createServiceStagingV3WithHeader(final Context context){

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient.addInterceptor(logging);
//        okHttpClient.dispatcher(dispatcher);
        Log.d("Authentication: ", new SaveInSharedPreference(context).getString(Constants.Companion.getARG_TOKEN()));

        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request original = chain.request();

                Request request = original.newBuilder()
                        .header("token", new SaveInSharedPreference(context).getString(Constants.Companion.getARG_TOKEN()))
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        });

        Retrofit builder = new Retrofit.Builder()
                .baseUrl("http://110.93.250.125/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient.build())
                .build();

        return builder.create(ApiInterface.class);
    }

}
