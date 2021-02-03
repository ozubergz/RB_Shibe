package com.example.rb_shibe.repo.remote;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitInstance {

    private static final String BASE_URL = "https://shibe.online";

    // Step 1: Declare instance
    private static ShibeService INSTANCE = null;

    // Step 2: Make the constructor private
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .client(getClient())
                .build();
    }

    // Step 3: Public method to access the new instance
    public static ShibeService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = getRetrofit().create(ShibeService.class);
        }
        return INSTANCE;
    }

    private static OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(logging).build();
    }
}
