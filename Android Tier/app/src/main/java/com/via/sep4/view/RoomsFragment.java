package com.via.sep4.view;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.via.sep4.MainActivity;
import com.via.sep4.NavigationBlock;
import com.via.sep4.R;
import com.via.sep4.viewModel.LogInViewModel;
import com.via.sep4.viewModel.RoomsViewModel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RoomsFragment extends Fragment {
    private TextView temperature, humidity, CO2;
    private Button toMetricsBtn;

    private int minTemp, maxTemp, minHumidity, maxHumidity;
    private boolean notificationsEnabled;


    private RoomsViewModel mViewModel;

    public static RoomsFragment newInstance()
    {
        return new RoomsFragment();
    }

    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rooms_fragment, container, false);

        loadSettings();

            if(notificationsEnabled) {
                addNotification();
            }

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

    private void addNotification() {

        NotificationCompat.Builder builder = null;
        
        
            //set the norms correctly !!!!!!!
            if(Integer.parseInt(temperature.toString())>= maxTemp){
                builder = new NotificationCompat.Builder(getContext(), "temperatureRising")
                                .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                                .setContentTitle("Warning Temperature Levels Rising")
                                .setContentText("The temperature is currently "+temperature.toString())
                                .setPriority(NotificationCompat.PRIORITY_HIGH);
            } else if(Integer.parseInt(temperature.toString())<= minTemp){
                builder = new NotificationCompat.Builder(getContext(), "temperatureLowering")
                        .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                        .setContentTitle("Warning Temperature Levels Lowering")
                        .setContentText("The temperature is currently "+temperature.toString())
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
            }else if(Integer.parseInt(humidity.toString())>= maxHumidity){
                builder = new NotificationCompat.Builder(getContext(), "humidityRising")
                        .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                        .setContentTitle("Warning Humidity Levels Rising")
                        .setContentText("The humidity is currently "+humidity.toString())
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
            }else if(Integer.parseInt(humidity.toString())<= minHumidity){
                builder = new NotificationCompat.Builder(getContext(), "humidityLowering")
                        .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                        .setContentTitle("Warning Humidity Levels Lowering")
                        .setContentText("The humidity is currently "+humidity.toString())
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
            }


        Intent notificationIntent = new Intent(getContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    private void loadSettings(){
        /*sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        notificationsEnabled = sharedPreferences.getBoolean("enableNotifications", false);
        minTemp = sharedPreferences.getInt("minTemp", 0);
        maxTemp = sharedPreferences.getInt("maxTemp", 0);
        minHumidity = sharedPreferences.getInt("minHumidity", 0);
        maxHumidity = sharedPreferences.getInt("maxHumidity", 0);*/
        notificationsEnabled=true;
        minTemp=0;
        maxTemp=20;
        minHumidity=0;
        maxHumidity=20;
    }


}



