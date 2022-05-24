package com.via.sep4.model;

public class Room {
    private String name;
    private final int id;
    private  int temperature;
    private  int CO2;
    private  int humidity;
    private final int number;

    public Room(int id, String name,  int number) {
        this.id = id;
        if (name.equals(null)) {
            this.name = "New room";
        } else {
            this.name = name;
        }
        //this.temperature = t;
        //this.CO2 = co2;
        //this.humidity = h;
        this.number = number;
    }

    public int getCO2() {
        return CO2;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getId() {
        return id;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", number=" + number +
                '}';
    }
}
