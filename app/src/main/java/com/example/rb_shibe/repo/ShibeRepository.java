package com.example.rb_shibe.repo;

import com.example.rb_shibe.repo.remote.RetrofitInstance;

import java.util.List;
import java.util.logging.SocketHandler;

import retrofit2.Call;

public class ShibeRepository {

    private static ShibeRepository INSTANCE = null;

    private ShibeRepository() {

    }

    public Call<List<String>> getShibes(String type, int count) {
        return RetrofitInstance.getInstance().getShibes(type, count);
    }

    public static ShibeRepository getInstance() {
        if(INSTANCE == null)
            INSTANCE = new ShibeRepository();
        return INSTANCE;
    }
    
}
