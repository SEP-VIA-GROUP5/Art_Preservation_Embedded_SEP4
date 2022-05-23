package com.via.sep4.view;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.NavigationBlock;
import com.via.sep4.R;
import com.via.sep4.viewModel.LogInViewModel;
import com.via.sep4.viewModel.RoomsViewModel;

public class RoomsFragment extends Fragment {
    private TextView temperature, humidity, CO2;
    private Button toMetricsBtn;

    private RoomsViewModel mViewModel;

    public static RoomsFragment newInstance()
    {
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

        mViewModel = new ViewModelProvider(getActivity()).get(RoomsViewModel.class);
        initView(view);
    }

    private void initView(View view) {
        temperature = view.findViewById(R.id.dbtemperature);
        humidity = view.findViewById(R.id.dbhumidity);
        CO2 = view.findViewById(R.id.dbCO2);
        toMetricsBtn = view.findViewById(R.id.toMetrics);




        FirebaseUser user;
        setupViews();
    }

 private void setupViews()
 {
     /* to make the details button go to another fragment ( that needs to be created to go in the detailed metrics of the room)


     temperature.setText();
     humidity.setText();
     CO2.setText();


     toMetricsBtn.setOnClickListener();
     {



     }*/


 }



}



