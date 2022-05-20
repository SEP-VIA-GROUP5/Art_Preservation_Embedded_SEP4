package Measurements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="CO2")
@Table(name="co2")
public class CO2Measurement {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public CO2Measurement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CO2Measurement(int co2) {
    }
}
