package com.via.sep4.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import android.content.SharedPreferences;

import android.graphics.BitmapFactory;
import android.os.Build;

import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.DataHandler;
import com.via.sep4.NotificationHandler;
import com.via.sep4.R;
import com.via.sep4.model.CO2;
import com.via.sep4.model.Humidity;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;
import com.via.sep4.model.Temperature;
import com.via.sep4.viewModel.DataViewModel;

import java.util.List;

public class HomeFragment extends Fragment {

    private FirebaseAuth auth;
    private DataViewModel viewModel;
    private Button toNormsSettings, toDashboard;
    private TextView temperatureTextView, humidityTextView, CO2TextView;
    private Switch temperatureSwitch, notificationSwitch;
    private TextView tempSettingText;
    private Room room;
    private Temperature temperature = null;
    private NotificationManager notificationManager;

    public static final String CHANNEL_1_ID = "tempChannel";
    public static final String CHANNEL_2_ID = "humChannel";
    public static final String CHANNEL_3_ID = "CO2Channel";

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
        createNotificationChannels();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        temperatureTextView = v.findViewById(R.id.dbtemperature);
        humidityTextView = v.findViewById(R.id.dbhumidity);
        CO2TextView = v.findViewById(R.id.dbCO2);
        toNormsSettings = v.findViewById(R.id.toNorms);
        toDashboard = v.findViewById(R.id.toDashboradGraph);
        temperatureSwitch = v.findViewById(R.id.CtoFSw);
        tempSettingText = v.findViewById(R.id.home_cToF_Text);
        sharedPreferences = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        notificationSwitch = v.findViewById(R.id.notSw);


        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void run() {
                loadDataForMain();
                handler.postDelayed(this, 120000);//set timer
            }
        };
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable, 1000);

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

        notificationSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean onOff) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("notification", onOff);
                editor.commit();
            }
        });

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadDataForMain() {
        Log.d("home loading", "load data");
        room = viewModel.getSingleRoom(1);
        String humS = "N/A";
        String co2S = "N/A";
        if (room.getMetrics().length == 0) {
            toNormsSettings.setVisibility(View.GONE);
            toDashboard.setVisibility(View.GONE);
            Toast.makeText(getContext(), R.string.home_no_values, Toast.LENGTH_LONG).show();
        } else {
            Metrics[] metrics = room.getMetrics();
            if (metrics.length != 0) {
                Humidity humidity = metrics[0].getHumidity();
                com.via.sep4.model.CO2 co2 = metrics[0].getCO2();

                temperature = metrics[0].getTemperature();
                humS = String.valueOf(humidity.getHumidity());
                co2S = String.valueOf(co2.getCo2());
                boolean settingTemp = sharedPreferences.getBoolean("temperature", true);
                if (temperature != null) {
                    setTempSetting(settingTemp, temperature);
                }
                boolean settingNotification = sharedPreferences.getBoolean("notification", true);
                notificationSwitch.setChecked(settingNotification);

                setNotificationChannel(temperature, humidity, co2);
            }
        }
        humidityTextView.setText(humS);
        CO2TextView.setText(co2S);
        Snackbar.make(getView(), getString(R.string.home_load_ok), Snackbar.LENGTH_SHORT).show();
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

    private void loadTemp(boolean setting, Temperature temperature) {
        if (setting) {
            temperatureTextView.setText(String.valueOf(temperature.getTemperature()));
        } else {
            temperatureTextView
                    .setText(String.valueOf(DataHandler.CelsiusToFahrenheit(temperature.getTemperature())));
        }
    }

    private void createNotificationChannels() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = getContext().getSystemService(NotificationManager.class);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setNotificationChannel(Temperature temperature, Humidity humidity, CO2 co2) {
        NotificationHandler handler = new NotificationHandler(getContext());
        List<NotificationChannel> channels = handler.getChannels();
        boolean settingNotification = sharedPreferences.getBoolean("notification", true);

        if (temperature.getTemperature() > temperature.getMax()) {
            temperatureTextView.setTextColor(getResources().getColor(R.color.r_red, getContext().getTheme()));
            notificationManager.createNotificationChannel(channels.get(0));
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_1_ID)
                    .setContentTitle(getText(R.string.notification_channel1))
                    .setContentText(getText(R.string.notification_channel_display_temp))
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_notification)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.thermometer))
                    .setAutoCancel(true);
            if (settingNotification) {
                enableNotification(builder, 1);
            }
        }
        if (humidity.getHumidity() > humidity.getMax()) {
            humidityTextView.setTextColor(getResources().getColor(R.color.r_red, getContext().getTheme()));
            notificationManager.createNotificationChannel(channels.get(1));
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_2_ID)
                    .setContentTitle(getText(R.string.notification_channel2))
                    .setContentText(getText(R.string.notification_channel_display_hum))
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_notification)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.thermometer))
                    .setAutoCancel(true);
            if (settingNotification) {
                enableNotification(builder, 2);
            }
        }
        if (co2.getCo2() > co2.getMax()) {
            CO2TextView.setTextColor(getResources().getColor(R.color.r_red, getContext().getTheme()));
            notificationManager.createNotificationChannel(channels.get(2));
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_3_ID)
                    .setContentTitle(getText(R.string.notification_channel3))
                    .setContentText(getText(R.string.notification_channel_display_co2))
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.ic_notification)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.thermometer))
                    .setAutoCancel(true);
            if (settingNotification) {
                enableNotification(builder, 3);
            }
        }
    }

    private void enableNotification(NotificationCompat.Builder builder, int id) {
        notificationManager.notify(id, builder.build());
    }
}


