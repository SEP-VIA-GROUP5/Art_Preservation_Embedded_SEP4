package com.via.sep4.repository;

import android.os.Handler;
import android.os.Message;
import android.util.JsonWriter;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.via.sep4.DataHandler;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Response;
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
                    } else {
                        msg = "Code: " + code + ", Error";
                    }
                    //Log.d("message ", msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return "null";
    }

    //TODO change HttpURLConnection to okhttp
    public Room getSingleRoom(int id) {
        Room[] room = new Room[1];
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
                        StringBuffer buffer = new StringBuffer();
                        while ((line = reader.readLine()) != null) { // 循环从流中读取
                            buffer.append(line);
                            buffer.append("\r\n");
                        }
                        String string = buffer.toString();
                        if (string != null && string.startsWith("\ufeff")) {
                            string = string.substring(1);
                        }
                        Gson gson = new Gson();
                        room[0] = gson.fromJson(string, Room.class);
                        msg = room[0].toString();
                        Log.d("room data", String.valueOf(room[0].getNumber()));
                        Message message = Message.obtain();
                        message.what = room[0].getId();
                        message.arg1 = room[0].getNumber();
                        message.obj = room[0].getName();

                        reader.close();
                    } else {
                        msg = "Code: " + code + ", Error";
                    }
                    Log.d("message ", msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (room[0] == null) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Log.d("room data", String.valueOf(room[0].getNumber()));
        return room[0];
    }

    public Metrics getMetricsSingleRoom(int number) {
        final Metrics[] metrics = new Metrics[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg = "";
                    URL url = new URL("http://sep4data-env.eba-hxyfmrv6.us-west-1.elasticbeanstalk.com/api/metrics/" + number);
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
                        StringBuffer buffer = new StringBuffer();
                        while ((line = reader.readLine()) != null) { // 循环从流中读取
                            buffer.append(line);
                            buffer.append("\r\n");
                        }
                        String string = buffer.toString();
                        if (string != null && string.startsWith("\ufeff")) {
                            string = string.substring(1);
                        }
                        Gson gson = new Gson();
                        metrics[0] = gson.fromJson(string, Metrics.class);
                        msg = metrics[0].toString();
                        reader.close();
                    } else {
                        msg = "Code: " + code + ", Error";
                    }
                    Log.d("message ", msg);
                    Log.d("humidity value", String.valueOf(metrics[0].getHumidity().getValue()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (metrics[0] == null) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return metrics[0];
    }

}
