package com.via.sep4.repository;

import static org.junit.Assert.*;

import android.util.Log;

import com.via.sep4.model.Room;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

    @Test
    public void setAllNorms() {
        //set correct norms
        int code = repository.setAllNorms(3, 100, 100, 100);
        Assert.assertEquals(200, code);
    }

    @Test
    public void getValidRoom() {
        Room room = repository.getSingleRoom(1);
        Assert.assertEquals(1, room.getId());
    }

    @Test
    public void getInValidRoom() {
        //this room isn't exist
        Room room = repository.getSingleRoom(50);
        Assert.assertEquals(100, room.getId());
    }
}