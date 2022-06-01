package dk.via.sep4.models;

import dk.via.sep4.SpringConfiguration;
import dk.via.sep4.repo.CO2Repository;
import dk.via.sep4.repo.HumidityRepository;
import dk.via.sep4.repo.TemperatureRepository;

public class Norms {
    int co2Max;
    int humidity;
    int tempMax;

    public Norms(int co2Max, int humidity, int tempMax) {
        this.co2Max = co2Max;
        this.humidity = humidity;
        this.tempMax = tempMax;
    }

    public Norms() {
    }

    public int getCo2Max() {
        return co2Max;
    }

    public void setCo2Max(int co2Max) {
        this.co2Max = co2Max;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }

    public String getNorms() {
        return String.format("%04x%02x%04x", co2Max, humidity, tempMax);
    }
}
