package dk.via.sep4.models.room;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dk.via.sep4.models.Sensor.Sensor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomid")
    private long roomid;
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sensor> sensors;

    public Room() {
    }

    public Long getRoomid() {
        return roomid;
    }

    public void setRoomid(Long roomid) {
        this.roomid = roomid;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }

    @JsonManagedReference
    public Set<Sensor> getSensors() {
        return sensors;
    }
}
