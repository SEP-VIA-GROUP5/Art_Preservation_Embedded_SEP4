package com.via.sep4.repository;

import static com.via.sep4.DataHandler.streamToJson;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.via.sep4.DataHandler;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private static DataRepository instance;
    private final MutableLiveData<String> errors = new MutableLiveData<>("");

    public static DataRepository getInstance() {
        if (instance == null) {
            instance = new DataRepository();
        }
        return instance;
    }

    public ArrayList<Room> connectHttpRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        final String[] msg = {""};
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
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
                        StringBuffer buffer = new StringBuffer();
                        while ((line = reader.readLine()) != null) { // 循环从流中读取
                            buffer.append(line);
                            buffer.append("\r\n");
                        }
                        String string = buffer.toString();
                        Log.d("message 2 ", string);
                        msg[0] = string;
                        reader.close();
                    } else {
                        msg[0] = "Code: " + code + ", Error";
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (msg[0] == "") {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Gson gson = new Gson();
        rooms = gson.fromJson(msg[0], new TypeToken<List<Room>>() {
        }.getType());
        return rooms;
    }

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

    public String getMetricsByRoomString(int id) {
        final String[] strings = new String[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String msg = "";
                    URL url = new URL("http://sep4data-env.eba-hxyfmrv6.us-west-1.elasticbeanstalk.com/api/metrics/" + id);
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
                        strings[0] = string;
                        reader.close();
                        connection.disconnect();
                    } else {
                        msg = "Code: " + code + ", Error";
                    }
                    Log.d("message ", msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (strings[0] == null) {
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return strings[0];
    }

    public int deleteARoom(int id) {
        final int[] code = new int[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://sep4data-env.eba-hxyfmrv6.us-west-1.elasticbeanstalk.com/api/room/" + id);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoOutput(false);
                    connection.setDoInput(true);
                    connection.setRequestMethod("DELETE");
                    connection.setUseCaches(true);
                    connection.setInstanceFollowRedirects(true);
                    connection.setConnectTimeout(3000);
                    connection.connect();
                    code[0] = connection.getResponseCode();

                    connection.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
        while (code[0] == 0) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return code[0];
    }

    public int addSingleRoom(JSONObject jsonParam) {
        final int[] roomId = new int[1];
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuffer sb = new StringBuffer();
                try {
                    URL url = new URL("http://sep4data-env.eba-hxyfmrv6.us-west-1.elasticbeanstalk.com/api/room");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Charset", "UTF-8");
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    conn.setRequestProperty("accept", "application/json");
                    conn.connect();
                    OutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.write((jsonParam.toString()).getBytes("UTF-8"));
                    out.flush();
                    out.close();

                    System.out.println(conn.getResponseCode());
                    if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                        InputStream in1 = conn.getInputStream();
                        try {
                            String readLine = new String();
                            BufferedReader responseReader = new BufferedReader(new InputStreamReader(in1, "UTF-8"));
                            while ((readLine = responseReader.readLine()) != null) {
                                sb.append(readLine).append("\n");
                            }
                            responseReader.close();
                            System.out.println(sb.toString());
                            Gson gson = new Gson();
                            Room room = gson.fromJson(sb.toString(), Room.class);
                            roomId[0] = room.getId();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        System.out.println("error");
                    }

                } catch (Exception e) {
                    Log.d("post room e", e.getMessage());
                }
            }
        }).start();
        while (roomId[0] == 0) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return roomId[0];
    }

    public int addMetricsToRoom(int id) {
        //TODO only get metrics (id=3), change it in the future
        final int[] code = new int[1];
        String metricsGet = getMetricsByRoomString(3);
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringBuffer sb = new StringBuffer();
                try {
                    URL url = new URL("http://sep4data-env.eba-hxyfmrv6.us-west-1.elasticbeanstalk.com/api/addMetrics/" + id);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Charset", "UTF-8");
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    conn.setRequestProperty("accept", "application/json");
                    conn.connect();
                    JSONObject jsonObject = new JSONObject(new String(metricsGet));
                    DataHandler.transferTimeStamp(jsonObject);
                    OutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.write((jsonObject.toString()).getBytes("UTF-8"));
                    out.flush();
                    out.close();

                    System.out.println(conn.getResponseCode());
                    code[0] = conn.getResponseCode();
                    if (HttpURLConnection.HTTP_OK == conn.getResponseCode()) {
                        InputStream in1 = conn.getInputStream();
                        try {
                            String readLine = new String();
                            BufferedReader responseReader = new BufferedReader(new InputStreamReader(in1, "UTF-8"));
                            while ((readLine = responseReader.readLine()) != null) {
                                sb.append(readLine).append("\n");
                            }

                            responseReader.close();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    } else {
                        System.out.println("error");
                    }
                } catch (Exception e) {
                    Log.d("getString e", e.getMessage());
                }
            }
        }).start();
        return code[0];
    }
}
