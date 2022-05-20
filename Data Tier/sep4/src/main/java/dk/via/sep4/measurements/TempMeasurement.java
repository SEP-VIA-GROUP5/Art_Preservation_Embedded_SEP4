package dk.via.sep4.measurements;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Ravneet
 */
@Entity
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
