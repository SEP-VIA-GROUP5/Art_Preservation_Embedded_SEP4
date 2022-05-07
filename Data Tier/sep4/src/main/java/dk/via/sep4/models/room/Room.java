package dk.via.sep4.models.room;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dk.via.sep4.models.Sensor.Sensor;

import java.util.Set;

public class Room {
    private long roomid;
    private Set<Sensor> sensors;



    public Long getRoomid() {
        return roomid;
    }
    public Room() {
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
