package com.via.sep4.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.http.GET;
import retrofit2.http.Path;

public class DataRepository {

    private static DataRepository instance;
    private final MutableLiveData<String> errors = new MutableLiveData<>("");

    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public String connectHttpRooms() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg = "";
                    URL url = new URL("http://sep4data-env.eba-hxyfmrv6.us-west-1.elasticbeanstalk.com/api/rooms");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(false);
                    connection.setDoInput(true);
                    connection.setRequestMethod("GET");
                    connection.setUseCaches(true);
                    connection.setInstanceFollowRedirects(true);
                    connection.setConnectTimeout(3000);
                    connection.connect();
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) { // 循环从流中读取
                            msg += line + "\n";
                        }
                        reader.close();
                    } else if (code == 500){
                        msg = "500 error";
                    }
                    Log.d("message ", msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return "null";
    }

    public String getSingleRoom(int id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg = "";
                    URL url = new URL("http://sep4data-env.eba-hxyfmrv6.us-west-1.elasticbeanstalk.com/api/room/" + id);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(false);
                    connection.setDoInput(true);
                    connection.setRequestMethod("GET");
                    connection.setUseCaches(true);
                    connection.setInstanceFollowRedirects(true);
                    connection.setConnectTimeout(3000);
                    connection.connect();
                    int code = connection.getResponseCode();
                    if (code == 200) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line = null;
                        while ((line = reader.readLine()) != null) { // 循环从流中读取
                            msg += line + "\n";
                        }
                        reader.close();
                    } else if (code == 500){
                        msg = "500 error";
                    }
                    Log.d("message ", msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return "null";
    }


}
