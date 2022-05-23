package com.via.sep4.model;

import java.util.Arrays;

public class Metrics {
    private final int id;
    private final Temperature temperature;
    private final Humidity humidity;
    private final CO2 co2;

    public Metrics(int id, Temperature t, CO2 co2, Humidity h){
        this.id = id;
        this.temperature = t;
        this.co2 = co2;
        this.humidity = h;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public int getId() {
        return id;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public CO2 getCO2() {
        return co2;
    }

    @Override
    public String toString() {
        return "Metrics{" +
                "id=" + id +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", co2=" + co2 +
                '}';
    }
}
