package dk.via.sep4.models.room;

import dk.via.sep4.models.Sensor.Sensor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private long id;
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sensor> sensors;

    public Room() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long roomid) {
        this.id = roomid;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }

    public Set<Sensor> getSensors() {
        return sensors;
    }
}
