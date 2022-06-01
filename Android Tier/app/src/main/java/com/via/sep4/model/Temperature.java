package com.via.sep4.model;

public class Temperature {
    private final int id;
    private int max;
    private final float temperature;

    public Temperature(int id, int max, float temperature){
        this.id = id;
        this.max = max;
        this.temperature = temperature;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getId() {
        return id;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", max=" + max +
                ", temperature=" + temperature +
                '}';
    }
}
