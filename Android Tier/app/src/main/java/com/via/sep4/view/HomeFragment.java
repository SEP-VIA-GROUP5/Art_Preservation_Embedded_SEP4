package com.via.sep4.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.DataHandler;
import com.via.sep4.R;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;
import com.via.sep4.model.Temperature;
import com.via.sep4.viewModel.DataViewModel;

public class HomeFragment extends Fragment {

    private FirebaseAuth auth;
    private DataViewModel viewModel;
    private Button toNormsSettings, toDashboard;
    private TextView temperatureTextView, humidity, CO2;
    private Switch notification;
    private Switch temperatureSwitch;
    private TextView tempSettingText;
    private Room room;

    private SharedPreferences sharedPreferences;

    public HomeFragment() {
    }

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
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        temperatureTextView = v.findViewById(R.id.dbtemperature);
        humidity = v.findViewById(R.id.dbhumidity);
        CO2 = v.findViewById(R.id.dbCO2);
        toNormsSettings = v.findViewById(R.id.toNorms);
        toDashboard = v.findViewById(R.id.toDashboradGraph);
        notification = v.findViewById(R.id.notSw);
        temperatureSwitch = v.findViewById(R.id.CtoFSw);
        tempSettingText = v.findViewById(R.id.home_cToF_Text);
        sharedPreferences = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);

        // here i am selecting that the object room to be sent
        String humS = "N/A";
        String co2S = "N/A";
        if (viewModel.getSingleRoom(2) == null) {
            Toast.makeText(getContext(), R.string.fail_connectServer, Toast.LENGTH_LONG).show();
            toNormsSettings.setVisibility(View.GONE);
            toDashboard.setVisibility(View.GONE);
        } else {
            room = viewModel.getSingleRoom(2);
            Metrics[] metrics = room.getMetrics();
            Temperature temperature = null;
            if (metrics.length != 0) {
                temperature = metrics[0].getTemperature();
                humS = String.valueOf(metrics[0].getHumidity().getHumidity());
                co2S = String.valueOf(metrics[0].getCO2().getCo2());
            }
            boolean settingTemp = sharedPreferences.getBoolean("temperature", true);
            setTempSetting(settingTemp, temperature);

            toNormsSettings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle id = new Bundle();
                    id.putInt("roomId", room.getId());
                    NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_nav_home_to_settingsFragment, id);
                }
            });
            Temperature finalTemperature = temperature;
            temperatureSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        setTempSetting(b, finalTemperature);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("temperature", true);
                        editor.commit();
                    } else {
                        setTempSetting(b, finalTemperature);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("temperature", false);
                        editor.commit();
                    }
                }
            });
        }


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

    private void setTempSetting(boolean setting, Temperature temperature) {
        Log.d("setting", String.valueOf(setting));
        temperatureSwitch.setChecked(setting);
        if (setting) {
            tempSettingText.setText(R.string.home_tC);
        } else {
            tempSettingText.setText(R.string.home_tF);
        }
        loadTemp(setting, temperature);
    }

    private void loadTemp(boolean setting, Temperature temperature){
        if (setting){
            temperatureTextView.setText(String.valueOf(temperature.getTemperature()));
        } else {
            temperatureTextView
                    .setText(String.valueOf(DataHandler.CelsiusToFahrenheit(temperature.getTemperature())));
        }
    }
}
