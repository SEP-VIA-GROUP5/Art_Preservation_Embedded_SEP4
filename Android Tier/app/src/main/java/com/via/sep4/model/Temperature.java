package com.via.sep4.model;

public class Temperature {
    private final int id;
    private final int temperature;

    public Temperature(int id, int temperature){
        this.id = id;
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "id=" + id +
                ", value=" + temperature +
                '}';
    }
}
