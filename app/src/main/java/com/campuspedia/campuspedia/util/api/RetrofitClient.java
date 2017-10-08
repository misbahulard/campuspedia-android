package com.campuspedia.campuspedia.util.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class ini digunakan untuk membuat instance dari Retrofit
 *
 * @author misbahulard
 * @version 20171008
 */
public class RetrofitClient {
    private static Retrofit retrofit = null;

    /**
     * Method ini berfungsi untuk mendapatkan instance client dari API
     *
     * @param baseUrl base url API yang dituju
     * @return Retrofit instance Retrofit
     */
    public static Retrofit getClient(String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        return retrofit;
    }
}
