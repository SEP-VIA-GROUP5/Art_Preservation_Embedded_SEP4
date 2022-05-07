package dk.via.sep4.models.Sensor;

import dk.via.sep4.models.room.Room;

import javax.persistence.*;

@Entity
@Table(name= "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="roomid")
    private Room room;
    public Sensor(SensorModel co2, String ppm, Room r, int i, int i1) {
    }

    public SensorModel getSensorModel() {
        return sensorModel;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public void setSensorModel(SensorModel sensorModel) {
        this.sensorModel = sensorModel;
    }

    private SensorModel sensorModel;
    private String unitType;

    private double currentvalue;

    public Sensor(SensorModel sensormodel, String unitType, Room room) {
        this.sensorModel = sensormodel;
        this.unitType = unitType;
        this.currentvalue = 0;
        this.room = room;
    }

    /**
     * Instantiates a new Sensor.
     */
    public Sensor() {
    }

    public double getCurrentvalue() {
        return currentvalue;
    }

    public void setCurrentvalue(double currentvalue) {
        this.currentvalue = currentvalue;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
