package com.via.sep4.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.DataHandler;
import com.via.sep4.R;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;
import com.via.sep4.model.User;
import com.via.sep4.view.rooms.RoomsFragment;
import com.via.sep4.viewModel.DataViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FirebaseAuth auth;
    private DataViewModel viewModel;
    private Button toNormsSettings, toDashboard;
    private TextView temperature, humidity, CO2;
    private Room room;

    public HomeFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        checkUser(user, getContext());
        viewModel = new ViewModelProvider(getActivity()).get(DataViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        room = viewModel.getSingleRoom(1);
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        temperature = v.findViewById(R.id.dbtemperature);
        humidity = v.findViewById(R.id.dbhumidity);
        CO2 = v.findViewById(R.id.dbCO2);
        toNormsSettings = v.findViewById(R.id.toNorms);
        toDashboard = v.findViewById(R.id.toDashboradGraph);

        toNormsSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle id = new Bundle();
                id.putInt("roomId", room.getId());
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_nav_home_to_settingsFragment, id);
            }
        });

// here i am selecting that the object room to be sent


        Metrics[] metrics = room.getMetrics();
        String idOfRoom = String.valueOf(room.getId());
        String tempS;
        String humS;
        String co2S;
        int idr = room.getId();

        if (metrics.length == 0) {
            tempS = "N/A";
            humS = "N/A";
            co2S = "N/A";
        } else {
            tempS = String.valueOf(metrics[0].getTemperature().getValue());
            humS = String.valueOf(metrics[0].getHumidity().getValue());
            co2S = String.valueOf(metrics[0].getCO2().getValue());
        }
        temperature.setText(tempS);
        humidity.setText(humS);
        CO2.setText(co2S);
        return v;

    }






    private void checkUser(FirebaseUser user, Context context) {
        if (user == null) {
            Toast.makeText(context, R.string.main_login_info, Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_nav_home_to_signIn_fragment);
        }
    }


}