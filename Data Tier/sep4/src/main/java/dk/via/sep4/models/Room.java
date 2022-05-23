package dk.via.sep4.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(updatable = false)
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "room_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})
    private  long id;
//    @OneToOne(mappedBy = "room")
//    private Set<Sensor> sensors;
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

//    public void setSensors(Set<Sensor> sensors) {
//        this.sensors = sensors;
//    }

//    public Set<Sensor> getSensors() {
//        return sensors;
//    }


    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Room))
            return false;
        Room room = (Room) o;
        return Objects.equals(this.id, room.id) && Objects.equals(this.name, room.name)
            && Objects.equals(this.location, room.location)
            //&& Objects.equals(this.sensors, room.sensors)
            && Objects.equals(this.number, room.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.location, this.number/*, this.sensors*/);
    }

    @Override public String toString()
    {
        return "Room{" + "id=" + id + /*", sensors=" + sensors +*/ ", name='" + name
            + '\'' + ", location='" + location + '\'' + ", number=" + number
            + '}';
    }
}
