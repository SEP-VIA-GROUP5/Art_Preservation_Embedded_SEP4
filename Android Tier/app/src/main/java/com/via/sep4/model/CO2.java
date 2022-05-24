package com.via.sep4.model;

public class CO2 {
    private final int id;
    private final int value;

    public CO2(int id, int value){
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
        return "CO2{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
