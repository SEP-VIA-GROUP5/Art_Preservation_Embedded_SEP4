package com.via.sep4.model;

import java.util.Arrays;

public class Notification {
    private Room room;
    private int id;

    public Notification(int id, Room room) {
        this.id = id;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Notification{" +
                '\'' +
                ", id=" + id +
                ", room=" + room +
                '}';
    }
}


