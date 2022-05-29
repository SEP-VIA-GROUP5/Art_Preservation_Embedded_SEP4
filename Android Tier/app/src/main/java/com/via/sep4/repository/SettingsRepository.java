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


    public void setNormsAndNotification(Room room, int minTemp, int maxTemp, int minHum, int maxHum, int minCO2, int maxC02, Context context) {

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                CharSequence name = "temperatureRisingChannel";
                String description = "Channel for rising temp notifications";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel channel = new NotificationChannel("temperatureRising", name, importance);
                channel.setDescription(description);

                NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
                notificationManager.createNotificationChannel(channel);

            }

        NotificationCompat.Builder builder = null;


        Metrics[] metrics = room.getMetrics();
        temperature = metrics[0].getTemperature().getTemperature();
        humidity = metrics[0].getHumidity().getHumidity();
        CO2 = metrics[0].getCO2().getCo2();

        Log.d("values", String.valueOf(temperature));
        Log.d("value set", String.valueOf(maxTemp));

        if (temperature >= maxTemp) {
            builder = new NotificationCompat.Builder(context, "temperatureRising")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Temperature Levels Rising")
                    .setContentText("The temperature is currently " + temperature)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        } else if (temperature <= minTemp) {
            builder = new NotificationCompat.Builder(context, "temperatureLowering")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Temperature Levels Lowering")
                    .setContentText("The temperature is currently " + temperature)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        } else if (humidity >= maxHum) {
            builder = new NotificationCompat.Builder(context, "humidityRising")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Humidity Levels Rising")
                    .setContentText("The humidity is currently " + humidity)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        } else if (humidity <= minHum) {
            builder = new NotificationCompat.Builder(context, "humidityLowering")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Humidity Levels Lowering")
                    .setContentText("The humidity is currently " + humidity)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        } else if (CO2 <= minCO2) {
            builder = new NotificationCompat.Builder(context, "CO2Lowering")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Humidity Levels Lowering")
                    .setContentText("The humidity is currently " + humidity)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        } else if (CO2 <= maxC02) {
            builder = new NotificationCompat.Builder(context, "CO2Rising")
                    .setSmallIcon(R.drawable.ic_baseline_notification_important_24)
                    .setContentTitle("Warning Humidity Levels Lowering")
                    .setContentText("The humidity is currently " + humidity)
                    .setPriority(NotificationCompat.PRIORITY_HIGH);
        }


        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                PendingIntent.FLAG_IMMUTABLE);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());

    }


}
