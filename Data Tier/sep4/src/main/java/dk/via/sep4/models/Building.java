package dk.via.sep4.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Building {
    @Id
    @Column(updatable = false)
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "building_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")})
    private  long id;
    @Column
    private String address;
    @OneToMany(targetEntity = Room.class,
            mappedBy="building",
            cascade= CascadeType.ALL,
            orphanRemoval = true)
    private List<Room> rooms;

    public Building(String address) {
        this.address = address;
        rooms = new ArrayList<>();
    }

    public Building() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Room[] getRooms() {
        return rooms.toArray(new Room[rooms.size()]);
    }

    @Override
    public String toString() {
        return "Building{" +
                "id: " + id +
                ", address: " + address + '\n' +
                ", rooms: " + Arrays.toString(getRooms()) +
                '}';
    }
}
