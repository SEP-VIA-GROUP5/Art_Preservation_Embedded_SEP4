package com.via.sep4;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataHandler {
    public static String changeDotToComaEmail(String email) {
        return email.replace('.', ',');
    }

    public static String convertStringArray(String[] strings) {
        StringBuilder sb = new StringBuilder();
        if (strings != null && strings.length > 0) {
            for (int i = 0; i < strings.length; i++) {
                if (i < strings.length - 1) {
                    sb.append(strings[i] + ",");
                } else {
                    sb.append(strings[i]);
                }
            }
        }
        String string = sb.toString();
        return string;
    }

    public static JSONObject streamToJson(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        String temp = "";
        StringBuilder stringBuilder = new StringBuilder();
        while ((temp = bufferedReader.readLine()) != null) {
            stringBuilder.append(temp);
        }
        JSONObject json = new JSONObject(stringBuilder.toString().trim());
        return json;
    }

    public static JSONObject transferTimeStamp(JSONObject object) {
        try {
            object.put("time", System.currentTimeMillis());
        } catch (JSONException e) {
            Log.d("json e", e.toString());
        }
        return object;
    }

    public static float CelsiusToFahrenheit(float c){
        return (float) (c * 1.8 + 32);
    }
}
