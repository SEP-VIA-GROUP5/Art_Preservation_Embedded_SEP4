package dk.via.sep4.measurements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ravneet
 */
@Entity
@Table(name = "humidity")
public class HumidityMeasurement {
  @Id
  @Column(name = "id", nullable = false)
  private Long id;

  public HumidityMeasurement() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public HumidityMeasurement(int humidity) {


  }
}
