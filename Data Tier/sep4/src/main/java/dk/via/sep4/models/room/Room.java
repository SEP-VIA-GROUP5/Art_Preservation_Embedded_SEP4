package dk.via.sep4.models.room;

import dk.via.sep4.models.Sensor.Sensor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private  long id;
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sensor> sensors;
    private String name;
    private String location;
   private int number;

    public Room()
    {
    }

    public Room(String name, String location, int number)
    {
        this.name = name;
        this.location = location;
        this.number = number;
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


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Room))
            return false;
        Room room = (Room) o;
        return Objects.equals(this.id, room.id) && Objects.equals(this.name, room.name)
            && Objects.equals(this.location, room.location)
            && Objects.equals(this.sensors, room.sensors)
            && Objects.equals(this.number, room.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.location, this.number, this.sensors);
    }

    @Override public String toString()
    {
        return "Room{" + "id=" + id + ", sensors=" + sensors + ", name='" + name
            + '\'' + ", location='" + location + '\'' + ", number=" + number
            + '}';
    }
}
