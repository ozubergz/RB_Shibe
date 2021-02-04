package com.example.rb_shibe.repo.remote;

import java.util.List;

import kotlin.reflect.KType;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ShibeService {

    // http://shibe.online/api/shibes?count=50

    @GET("/api/{type}")
    Call<List<String>> getShibes(@Path(value = "type", encoded = true) String type, @Query("count") int count);
}


