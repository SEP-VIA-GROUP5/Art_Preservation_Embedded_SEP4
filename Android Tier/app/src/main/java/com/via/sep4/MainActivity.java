package com.via.sep4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.via.sep4.viewModel.DataViewModel;

public class MainActivity extends AppCompatActivity {
private TextView temperature;
private TextView CO2;
private TextView humidity;
private Button toDataBtn;
private DataViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView temperature = findViewById(R.id.temperature);
        TextView CO2 = findViewById(R.id.CO2);
        TextView humidity = findViewById(R.id.humidity);
        Button toDataBtn = findViewById(R.id.toData);

        viewModel = new ViewModelProvider(this).get(DataViewModel.class);

// should i make a onclick listern on the button that is gonna update the values?







    }
}