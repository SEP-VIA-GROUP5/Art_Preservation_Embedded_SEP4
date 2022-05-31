package com.via.sep4.model;

public class CO2 {
    private final int id;
    private final int max;
    private final int co2;

    public CO2(int id, int max,  int co2){
        this.id = id;
        this.max = max;
        this.co2 = co2;
    }

    public int getMax() {
        return max;
    }

    public int getCo2() {
        return co2;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CO2{" +
                "id=" + id +
                ", max=" + max +
                ", co2=" + co2 +
                '}';
    }
}
