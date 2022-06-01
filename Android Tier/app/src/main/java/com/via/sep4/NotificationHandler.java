package com.via.sep4;

import static com.via.sep4.view.HomeFragment.CHANNEL_1_ID;
import static com.via.sep4.view.HomeFragment.CHANNEL_2_ID;
import static com.via.sep4.view.HomeFragment.CHANNEL_3_ID;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class NotificationHandler{
    private final List<NotificationChannel> channels = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    public NotificationHandler(Context context) {
        CharSequence name1 = context.getString(R.string.notification_channel1);
        String description1 = context.getString(R.string.notification_channel1_desc);
        int importance1 = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel1 = new NotificationChannel(CHANNEL_1_ID, name1, importance1);
        channel1.setDescription(description1);

        CharSequence name2 = context.getString(R.string.notification_channel2);
        String description2 = context.getString(R.string.notification_channel2_desc);
        int importance2 = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel2 = new NotificationChannel(CHANNEL_2_ID, name2, importance2);
        channel2.setDescription(description2);

        CharSequence name3 = context.getString(R.string.notification_channel3);
        String description3 = context.getString(R.string.notification_channel3_desc);
        int importance3 = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel3 = new NotificationChannel(CHANNEL_3_ID, name3, importance3);
        channel3.setDescription(description3);

        channels.add(channel1);
        channels.add(channel2);
        channels.add(channel3);
    }

    public List<NotificationChannel> getChannels() {
        return channels;
    }

}
