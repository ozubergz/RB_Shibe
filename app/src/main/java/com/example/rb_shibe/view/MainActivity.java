package com.example.rb_shibe.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.rb_shibe.adapter.ShibeAdapter;
import com.example.rb_shibe.databinding.ActivityMainBinding;
import com.example.rb_shibe.util.Constants;
import com.example.rb_shibe.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private ActivityMainBinding binding;
    private String typeData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        // setContentView takes in binding.getRoot() whenever using binding
        setContentView(binding.getRoot());

        int intData = getIntent().getIntExtra(Constants.DASHBOARD_ACTIVITY_PARAM_INT, 0);
        typeData = getIntent().getStringExtra(Constants.DASHBOARD_ACTIVITY_PARAM_TYPE);

        initObservers();
        initView();

        viewModel.fetchShibes(typeData, intData);

    }

    private void initObservers() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getUrls().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> urls) {
                ShibeAdapter adapter = new ShibeAdapter(urls);
                binding.rvList.setAdapter(adapter);
            }
        });

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
//                binding.tvDisplay.setText(s);
            }
        });

    }

    private void initView() {
        binding.btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("click");
                String count = binding.etInput.getText().toString();
                if(!count.isEmpty())
                    viewModel.fetchShibes(typeData, Integer.parseInt(count));
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvList.setLayoutManager(linearLayoutManager);
    }


}