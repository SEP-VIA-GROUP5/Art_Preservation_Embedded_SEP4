package com.via.sep4.repository;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.via.sep4.MainActivity;
import com.via.sep4.R;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;


public class SettingsRepository {
    private int temperature, minTemp, maxTemp;
    private int humidity, minHum, maxHum;
    private int CO2;
    private Room room;


    private static SettingsRepository instance;

    public static SettingsRepository getInstance() {
        if (instance == null) {
            instance = new SettingsRepository();
        }
        return instance;
    }




    public void setNorms(Room room, int maxTemp, int maxHum, int maxC02) {
        NotificationCompat.Builder builder = null;

        //TODO send norms to Db team

        Metrics[] metrics = room.getMetrics();
        temperature = metrics[0].getTemperature().getTemperature();
        humidity = metrics[0].getHumidity().getHumidity();
        CO2 = metrics[0].getCO2().getCo2();

        Log.d("values", String.valueOf(temperature));
        Log.d("value set", String.valueOf(maxTemp));



    }


}
