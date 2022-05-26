package com.via.sep4.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Room implements Parcelable {
    private String name;
    private final int id;
    private Metrics[] metrics;
    private final int number;

    public Room(int id, String name,  int number, Metrics[] metrics) {
        this.id = id;
        if (name.equals(null)) {
            this.name = "New room";
        } else {
            this.name = name;
        }
        this.metrics = metrics;
        this.number = number;
    }


    protected Room(Parcel in) {
        name = in.readString();
        id = in.readInt();
        number = in.readInt();
    }

    public static final Creator<Room> CREATOR = new Creator<Room>() {
        @Override
        public Room createFromParcel(Parcel in) {
            return new Room(in);
        }

        @Override
        public Room[] newArray(int size) {
            return new Room[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Metrics[] getMetrics() {
        return metrics;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", metrics=" + Arrays.toString(metrics) +
                ", number=" + number +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(id);
        parcel.writeInt(number);
    }


}
