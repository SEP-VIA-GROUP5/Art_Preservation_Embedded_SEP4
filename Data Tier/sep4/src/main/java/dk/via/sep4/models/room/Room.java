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
    @Column(name = "roomId")
    private long roomId;
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sensor> sensors;

    public Room() {
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomid) {
        this.roomId = roomid;
    }

    public void setSensors(Set<Sensor> sensors) {
        this.sensors = sensors;
    }


    public Set<Sensor> getSensors() {
        return sensors;
    }
}
