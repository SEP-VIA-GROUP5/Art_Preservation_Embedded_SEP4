package dk.via.sep4.models.Sensor;

import dk.via.sep4.measurements.CO2Measurement;
import dk.via.sep4.measurements.HumidityMeasurement;
import dk.via.sep4.measurements.TempMeasurement;
import dk.via.sep4.models.room.Room;

import javax.persistence.*;

@Entity
@Table(name= "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensor_id")
    private long id;

    @ManyToOne
    @JoinColumn(name="room_id")
    private HumidityMeasurement room;
    public Sensor(SensorModel co2, String ppm, Room r, int i, int i1) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CO2Measurement getSensorModel() {
        return sensorModel;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public void setSensorModel(CO2Measurement sensorModel) {
        this.sensorModel = sensorModel;
    }

    @ManyToOne
    @JoinColumn(name = "sensor_model_id")
    private CO2Measurement sensorModel;
    private String unitType;

    private double currentvalue;

    public Sensor(CO2Measurement sensorModel, TempMeasurement unitType, HumidityMeasurement room) {
        this.sensorModel = sensorModel;
        this.unitType = String.valueOf(unitType);
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

    public HumidityMeasurement getRoom() {
        return room;
    }

    public void setRoom(HumidityMeasurement room) {
        this.room = room;
    }
}
