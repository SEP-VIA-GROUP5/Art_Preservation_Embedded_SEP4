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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.DataHandler;
import com.via.sep4.R;
import com.via.sep4.model.CO2;
import com.via.sep4.model.Humidity;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;
import com.via.sep4.model.Temperature;
import com.via.sep4.viewModel.DataViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FirebaseAuth auth;
    private DataViewModel viewModel;
    private Button toNormsSettings, toDashboard;
    private TextView temperatureTextView, humidity, CO2;
    private Switch notification;
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
        humidity = v.findViewById(R.id.dbhumidity);
        CO2 = v.findViewById(R.id.dbCO2);
        toNormsSettings = v.findViewById(R.id.toNorms);
        toDashboard = v.findViewById(R.id.toDashboradGraph);
        notification = v.findViewById(R.id.notSw);
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
                if (onOff) {
                    createNotificationChannels();
                }
            }
        });

        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loadDataForMain() {
        Log.d("home loading", "load data");
        room = viewModel.getSingleRoom(2);
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

                List<NotificationChannel> channels = createAllChannels();
                if (temperature.getTemperature() > temperature.getMax()) {
                    notificationManager.createNotificationChannel(channels.get(1));
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_1_ID)
                            .setContentTitle(getText(R.string.notification_channel1))
                            .setContentText(getText(R.string.notification_channel_display_temp))
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.drawable.ic_notification)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.thermometer));
                    notificationManager.notify(1, builder.build());
                } else if (humidity.getHumidity() > humidity.getMax()) {
                    notificationManager.createNotificationChannel(channels.get(2));
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_2_ID)
                            .setContentTitle(getText(R.string.notification_channel2))
                            .setContentText(getText(R.string.notification_channel_display_hum))
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.drawable.ic_notification)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.thermometer));
                    notificationManager.notify(2, builder.build());
                } else if (co2.getCo2() > co2.getMax()) {
                    notificationManager.createNotificationChannel(channels.get(3));
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), CHANNEL_3_ID)
                            .setContentTitle(getText(R.string.notification_channel3))
                            .setContentText(getText(R.string.notification_channel_display_co2))
                            .setWhen(System.currentTimeMillis())
                            .setSmallIcon(R.drawable.ic_notification)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.thermometer));
                    notificationManager.notify(3, builder.build());
                }
            }
        }
        humidity.setText(humS);
        CO2.setText(co2S);
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
    private List<NotificationChannel> createAllChannels() {
        List<NotificationChannel> channels = new ArrayList<>();

        CharSequence name1 = getString(R.string.notification_channel1);
        String description1 = getString(R.string.notification_channel1_desc);
        int importance1 = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID, name1, importance1);
        channel1.setDescription(description1);

        CharSequence name2 = getString(R.string.notification_channel2);
        String description2 = getString(R.string.notification_channel2_desc);
        int importance2 = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID, name2, importance2);
        channel2.setDescription(description2);

        CharSequence name3 = getString(R.string.notification_channel3);
        String description3 = getString(R.string.notification_channel3_desc);
        int importance3 = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel3 = new NotificationChannel(CHANNEL_3_ID, name3, importance3);
        channel3.setDescription(description3);

        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel3);
        return channels;
    }

   /* private void setNotification(boolean setting) {

        Log.d("setting", String.valueOf(setting));
        notificationSwitch.setChecked(setting);
        if (setting) {
            createNotificationChannels();
        }
*/
}


