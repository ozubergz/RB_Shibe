package com.example.rb_shibe.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.rb_shibe.R;
import com.example.rb_shibe.viewmodel.MainViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    private AppCompatEditText inputText;
    private MaterialTextView tvDisplay;
    private MaterialButton btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initObservers();
        initView();
    }

    private void initObservers() {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getUrls().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> urls) {
                tvDisplay.setText(urls.toString());
            }
        });

        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvDisplay.setText(s);
            }
        });

    }

    public void initView() {
        inputText = findViewById(R.id.et_input);
        btnClick = findViewById(R.id.btn_click);
        tvDisplay = findViewById(R.id.tv_display);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String count = inputText.getText().toString();
                if(!count.isEmpty())
                    viewModel.fetchShibes(Integer.parseInt(count));
            }
        });
    }
}