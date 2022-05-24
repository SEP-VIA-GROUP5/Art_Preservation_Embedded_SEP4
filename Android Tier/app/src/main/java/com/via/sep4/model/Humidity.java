package com.via.sep4.model;

public class Humidity {
    private final int id;
    private final int value;

    public Humidity(int id, int value){
        this.id = id;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Humidity{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
