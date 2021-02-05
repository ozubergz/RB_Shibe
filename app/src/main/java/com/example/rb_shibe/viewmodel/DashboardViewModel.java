package com.example.rb_shibe.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.rb_shibe.R;
import com.example.rb_shibe.util.Constants;

public class DashboardViewModel extends AndroidViewModel {

    /****************** Constructor *******************/
    public DashboardViewModel(@NonNull Application application) {
        super(application);
    }

    /****************** Shared Preferences *******************/
    private SharedPreferences sharedPref = getApplication().getSharedPreferences(
            getApplication().getString(R.string.preference_key), Context.MODE_PRIVATE);

    /************ Save Data Getters *************/
    public String getTypeData() {
        return sharedPref.getString(Constants.STRING_TYPE_DATA, "shibes");
    }

    public int getCountData() {
        return sharedPref.getInt(Constants.INT_COUNT_DATA, 0);
    }

    /************ Save Data Setters *************/
    public void saveTypeData(String data) {
        sharedPref.edit().putString(Constants.STRING_TYPE_DATA, data).apply();
    }

    public void saveCountData(int data) {
        sharedPref.edit().putInt(Constants.INT_COUNT_DATA, data).apply();
    }

}
