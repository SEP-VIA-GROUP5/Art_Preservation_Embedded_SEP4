package com.via.sep4.repository;

import android.util.Log;

import com.via.sep4.DataHandler;
import com.via.sep4.model.Metrics;
import com.via.sep4.model.Room;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class DataRepositoryTest {

    private DataRepository repository;

    @Before
    public void setUp() throws Exception {
        repository = DataRepository.getInstance();
        Log.i("test start", "start");
    }

    @After
    public void tearDown() throws Exception {
        Log.i("test end", "end");
    }

    //norms
    @Test
    public void setMinusNorm() {
        int code = repository.setAllNorms(-1, 100, 100, 100);
        Assert.assertEquals(500, code);
    }

    @Test
    public void setZeroNorm() {
        int code = repository.setAllNorms(0, 100, 100, 100);
        Assert.assertEquals(500, code);
    }

    @Test
    public void setOneNorm() {
        //set correct norms
        int code = repository.setAllNorms(1, 100, 100, 100);
        Assert.assertEquals(200, code);
    }

    @Test
    public void setTwoNorm() {
        //set correct norms
        int code = repository.setAllNorms(4, 100, 100, 100);
        Assert.assertEquals(200, code);
    }

    //rooms
    @Test
    public void getInValidRoomMinus() {
        Room room = repository.getSingleRoom(-1);
        Assert.assertEquals(null, room.getMetrics());
    }

    @Test
    public void getInValidRoomZero() {
        //this room isn't exist
        Room room = repository.getSingleRoom(0);
        Assert.assertEquals(null, room.getMetrics());
    }

    @Test
    public void getValidRoom() {
        Room room = repository.getSingleRoom(1);
        Metrics[] metrics = room.getMetrics();
        Assert.assertEquals(24, metrics[0].getTemperature().getTemperature(), 0.5);
        Assert.assertEquals(96, metrics[0].getHumidity().getHumidity(), 0.5);
        Assert.assertEquals(66, metrics[0].getCO2().getCo2(), 0.5);
    }

    @Test
    public void getValidRoom2() {
        Room room = repository.getSingleRoom(2);
        Metrics[] metrics = room.getMetrics();
        Assert.assertEquals(21, metrics[0].getTemperature().getTemperature(), 0.5);
        Assert.assertEquals(96, metrics[0].getHumidity().getHumidity(), 0.5);
        Assert.assertEquals(64, metrics[0].getCO2().getCo2(), 0.5);
    }

    @Test
    public void getInValidRoom() {
        //this room isn't exist
        Room room = repository.getSingleRoom(50);
        Assert.assertEquals(null, room.getMetrics());
    }

    @Test
    public void addValidRoom() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "");
            jsonObject.put("number", 0);
            int id = repository.addSingleRoom(jsonObject);

            Room room = repository.getSingleRoom(id);
            Assert.assertEquals(0, room.getNumber());
        } catch (JSONException e) {
            return;
        }
    }

    @Test
    public void addValidRoom2() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "room");
            jsonObject.put("number", 1);
            int id = repository.addSingleRoom(jsonObject);

            Room room = repository.getSingleRoom(id);
            Assert.assertEquals(1, room.getNumber());
        } catch (JSONException e) {
            return;
        }
    }

    @Test
    public void addValidRoom3() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "room");
            jsonObject.put("number", -1);
            int id = repository.addSingleRoom(jsonObject);

            Room room = repository.getSingleRoom(id);
            Assert.assertEquals(-1, room.getNumber());
        } catch (JSONException e) {
            return;
        }
    }

    @Test
    public void deleteRoom1() {
        int code = repository.deleteARoom(-1);
        Assert.assertEquals(500, code);
    }

    @Test
    public void deleteRoom2() {
        int code = repository.deleteARoom(0);
        Assert.assertEquals(500, code);
    }

    @Test
    public void deleteRoom3() {
        int code = repository.deleteARoom(5);
        Assert.assertEquals(200, code);
    }
}