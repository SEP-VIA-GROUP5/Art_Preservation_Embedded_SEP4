package com.via.sep4.model;

public class Humidity {
    private final int id;
    private final int max;
    private final int humidity;

    public Humidity(int id, int max, int humidity) {
        this.id = id;
        this.max = max;
        this.humidity = humidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getMax() {
        return max;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Humidity{" +
                "id=" + id +
                ", max=" + max +
                ", humidity=" + humidity +
                '}';
    }
}
