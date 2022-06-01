package com.via.sep4.model;

public class Temperature {
    private final int id;
    private int max;
    private final int temperature;

    public Temperature(int id, int max, int temperature){
        this.id = id;
        this.max = max;
        this.temperature = temperature;
    }

    public int getTemperature() {
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
