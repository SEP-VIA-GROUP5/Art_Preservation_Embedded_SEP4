package com.via.sep4;

public class Data {
    private final int temperature;
    private final int CO2;
    private final int humidity;

    public Data(int temperature, int CO2, int humidity) {
        this.temperature = temperature;
        this.CO2 = CO2;
        this.humidity = humidity;


    }

    public int getTemperature() {
        return temperature;
    }

    public int getCO2() {
        return CO2;
    }

    public int getHumidity() {
        return humidity;
    }


}
