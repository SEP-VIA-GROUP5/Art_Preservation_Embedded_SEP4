package dk.via.sep4.models;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table
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
    @OneToMany(mappedBy = "room")
    private Set<Metrics> metrics;
    @Column
    @NotNull
    private String name;
    @Column
    @NotNull
    private int number;

    public Room(String name, int number)
    {
        this.name = name;
        this.number = number;
    }

    public Room() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setMetrics(Set<Metrics> metrics) {
        this.metrics = metrics;
    }

    public Set<Metrics> getMetrics() {
        return metrics;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Room))
            return false;
        Room room = (Room) o;
        return Objects.equals(this.id, room.id) &&
                Objects.equals(this.name, room.name) &&
                Objects.equals(this.metrics, room.metrics) &&
                Objects.equals(this.number, room.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.number, this.metrics);
    }
}
