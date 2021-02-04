package com.example.rb_shibe.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rb_shibe.repo.ShibeRepository;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<List<String>> _urls = new MutableLiveData<>();
    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();

    public LiveData<List<String>> getUrls() {
        return _urls;
    }

    public LiveData<String> getError() {
        return _errorMessage;
    }

    private final ShibeRepository shibeRepo = ShibeRepository.getInstance();

    public void fetchShibes(String type, int count) {
        shibeRepo.getShibes(type, count).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NotNull Call<List<String>> call, @NotNull Response<List<String>> response) {
                List<String> urls = response.body();
                _urls.setValue(urls);
            }

            @Override
            public void onFailure(@NotNull Call<List<String>> call, @NotNull Throwable t) {
                _errorMessage.setValue(t.getMessage());
            }
        });
    }


}
