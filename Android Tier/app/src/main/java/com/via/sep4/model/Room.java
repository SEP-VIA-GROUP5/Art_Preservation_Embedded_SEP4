package com.via.sep4.model;

import java.util.Arrays;

public class Room {
    private String name;
    private final int id;
    private Metrics[] metrics;
    private final int number;

    public Room(int id, String name,  int number, Metrics[] metrics) {
        this.id = id;
        if (name.equals(null)) {
            this.name = "New room";
        } else {
            this.name = name;
        }
        this.metrics = metrics;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Metrics[] getMetrics() {
        return metrics;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", metrics=" + Arrays.toString(metrics) +
                ", number=" + number +
                '}';
    }
}
