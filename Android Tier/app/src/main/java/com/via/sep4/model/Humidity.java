package com.via.sep4.model;

public class Humidity {
    private final int id;
    private final int humidity;

    public Humidity(int id, int humidity){
        this.id = id;
        this.humidity = humidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Humidity{" +
                "id=" + id +
                ", value=" + humidity +
                '}';
    }
}
