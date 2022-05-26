package com.via.sep4.view;

import android.app.Notification;
import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.via.sep4.R;

public class RoomSettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.room_preferences, "room_prefs");
    }





}