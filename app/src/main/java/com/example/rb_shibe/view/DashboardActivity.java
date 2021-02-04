package com.example.rb_shibe.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rb_shibe.databinding.ActivityDashboardBinding;
import com.example.rb_shibe.util.Constants;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
    }

    private void initView() {
        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getActivityData();

            }
        });
    }

    private void getActivityData() {
        String type = "shibes";
        int count = 0;

        if(!binding.etIntData.getText().toString().isEmpty()) {
            String string = binding.etIntData.getText().toString();
            count = Integer.parseInt(string);
        }

        if(!binding.etTypeData.getText().toString().isEmpty()) {
            type = binding.etTypeData.getText().toString();
        }

        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);

        intent.putExtra(Constants.DASHBOARD_ACTIVITY_PARAM_INT, count);
        intent.putExtra(Constants.DASHBOARD_ACTIVITY_PARAM_TYPE, type);

        startActivity(intent);
    }


}
