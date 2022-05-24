package dk.via.sep4.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Metrics {
    @Id
    @Column(updatable = false)
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
            @org.hibernate.annotations.Parameter(name = "sequence_name", value = "sensor_sequence"),
            @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
            @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Temperature temperature;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Humidity humidity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private CO2 co2;

    @Column
    private Timestamp time;

    @ManyToOne(targetEntity = Room.class,
            cascade = CascadeType.ALL)
    @JoinColumn(name="room_id")
    private Room rooms;

    public Metrics(CO2 co2, Humidity humidity, Temperature temperature) {
        this.co2 = co2;
        this.humidity = humidity;
        this.temperature = temperature;
        time = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Instantiates a new Metrics.
     */
    public Metrics() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public Humidity getHumidity() {
        return humidity;
    }

    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    public CO2 getCo2() {
        return co2;
    }

    public void setCo2(CO2 co2) {
        this.co2 = co2;
    }

    public void setRoom(Room rooms) {
        this.rooms = rooms;
    }
}
