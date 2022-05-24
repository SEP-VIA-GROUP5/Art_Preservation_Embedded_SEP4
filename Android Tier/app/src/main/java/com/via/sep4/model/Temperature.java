package com.via.sep4.model;

public class Temperature {
    private final int id;
    private final int value;

    public Temperature(int id, int value){
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
        return "Temperature{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
