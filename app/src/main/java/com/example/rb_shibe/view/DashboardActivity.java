package com.example.rb_shibe.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.rb_shibe.R;
import com.example.rb_shibe.databinding.ActivityDashboardBinding;
import com.example.rb_shibe.util.Constants;
import com.example.rb_shibe.viewmodel.DashboardViewModel;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private ArrayList<String> arrayList_options;
    private ArrayAdapter<String> arrayAdapter_options;
    private DashboardViewModel viewModel;

    private String type;
    private int count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /************ Create new instance of View Model *************/
        viewModel = new ViewModelProvider.AndroidViewModelFactory(
                getApplication()).create(DashboardViewModel.class);

        /************ Get Save Data *************/
        type = viewModel.getTypeData();
        count = viewModel.getCountData();

        binding.actOptions.setText(type,false);
        binding.etIntData.setText(String.valueOf(count));

        initView();
        createDropDownMenu();
    }

    private void initView() {

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivityData();
            }
        });

        binding.actOptions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                type = arrayAdapter_options.getItem(position);
                viewModel.saveTypeData(type);
            }
        });
    }

    private void getActivityData() {
        if(binding.etIntData.getText() != null) {
            String string = binding.etIntData.getText().toString();
            count = Integer.parseInt(string);
        }

        viewModel.saveCountData(count);

        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);

        intent.putExtra(Constants.DASHBOARD_ACTIVITY_PARAM_INT, count);
        intent.putExtra(Constants.DASHBOARD_ACTIVITY_PARAM_TYPE, type);

        startActivity(intent);
    }

    private void createDropDownMenu() {
        arrayList_options = new ArrayList<>();
        arrayList_options.add("shibes");
        arrayList_options.add("birds");
        arrayList_options.add("cats");

        arrayAdapter_options = new ArrayAdapter<>(
                getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, arrayList_options);

        binding.actOptions.setAdapter(arrayAdapter_options);
    }
}
