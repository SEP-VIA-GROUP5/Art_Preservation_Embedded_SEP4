package Measurements;

import javax.persistence.*;

@Entity(name="Temperature")
@Table(name="temperature")
public class TempMeasurement {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public TempMeasurement() {
    }


    public void HumidityMeasurement() {
    }

    public TempMeasurement(int temperature) {
    }
}
