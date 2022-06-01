package com.via.sep4.model;

public class Humidity {
    private final int id;
    private int max;
    private final float humidity;

    public Humidity(int id, int max, float humidity) {
        this.id = id;
        this.max = max;
        this.humidity = humidity;
    }

    public float getHumidity() {
        return humidity;
    }

    public int getMax() {
        return max;
    }

    public int getId() {
        return id;
    }

    public void setMax(int max) {
        this.max = max;
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
