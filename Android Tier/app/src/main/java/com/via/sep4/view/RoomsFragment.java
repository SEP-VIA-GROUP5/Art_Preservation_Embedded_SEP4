package com.via.sep4.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.R;
import com.via.sep4.model.Metrics;
import com.via.sep4.viewModel.DataViewModel;

public class RoomsFragment extends Fragment {
    private TextView temperature, humidity, CO2;
    private Button toMetricsBtn;

    private DataViewModel mViewModel;

    public static RoomsFragment newInstance() {
        return new RoomsFragment();
    }


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rooms_fragment, container, false);

        return v;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
        initView(view);
    }

    private void initView(View view) {
        temperature = view.findViewById(R.id.dbtemperature);
        humidity = view.findViewById(R.id.dbhumidity);
        CO2 = view.findViewById(R.id.dbCO2);
        toMetricsBtn = view.findViewById(R.id.toMetricsDetails);

        FirebaseUser user;
        setupViews();
    }

    private void setupViews() {
        Metrics metrics = mViewModel.getMetricsSingleRoom(2);
        temperature.setText(metrics.getTemperature().getValue() + "C");
        humidity.setText(metrics.getHumidity().getValue() + "%rh");
        CO2.setText(metrics.getCO2().getValue() + "ppm");
    }

}



