package com.via.sep4.repository;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.via.sep4.MainActivity;
import com.via.sep4.R;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;
import com.via.sep4.model.Humidity;
import com.via.sep4.model.Temperature;






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

    Metrics[] metrics = room.getMetrics();


    public void setNormsAndNotification(Room room, int minTemp, int maxTemp, int minHum, int maxHum, int minCO2, int maxC02) {
        NotificationCompat.Builder builder = null;

        Metrics[] metrics = room.getMetrics();
        temperature = metrics[0].getTemperature().getValue();
        humidity = metrics[0].getHumidity().getValue();
        CO2=metrics[0].getCO2().getValue();



        if(temperature>= maxTemp){
            builder = new NotificationCompat.Builder(getContext(), "temperatureRising")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Temperature Levels Rising")
                    .setContentText("The temperature is currently "+temperature)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);


        } else if(temperature<= minTemp){
            builder = new NotificationCompat.Builder(getContext(), "temperatureLowering")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Temperature Levels Lowering")
                    .setContentText("The temperature is currently "+temperature)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        }else if(humidity>= maxHum){
            builder = new NotificationCompat.Builder(getContext(), "humidityRising")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Humidity Levels Rising")
                    .setContentText("The humidity is currently "+humidity)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        }else if(humidity<= minHum){
            builder = new NotificationCompat.Builder(getContext(), "humidityLowering")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Humidity Levels Lowering")
                    .setContentText("The humidity is currently "+humidity)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        }
        else if(CO2<= minCO2){
            builder = new NotificationCompat.Builder(getContext(), "CO2Lowering")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Humidity Levels Lowering")
                    .setContentText("The humidity is currently "+humidity)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);}
        else if(CO2<= maxC02){
            builder = new NotificationCompat.Builder(getContext(), "CO2Rising")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Humidity Levels Lowering")
                    .setContentText("The humidity is currently "+humidity)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);}







        Intent notificationIntent = new Intent(getContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getContext(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());


    }

    private Context getContext() {
//TODO check it latter;
return null;
    }


}
