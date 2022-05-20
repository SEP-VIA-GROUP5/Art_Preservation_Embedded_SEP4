package dk.via.sep4.measurements;

import dk.via.sep4.models.Sensor.Sensor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Ravneet
 */
@Entity(name="CO2")
@Table(name="co2")
public class CO2Measurement {

  @Id
  @GeneratedValue(generator = "sequence-generator")
  @GenericGenerator(
      name = "sequence-generator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @org.hibernate.annotations.Parameter(name = "sequence_name", value = "co2_sequence"),
          @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
          @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
      }
  )
  private Long CO2Id;
  @Column
  private int CO2Level;

  //@OneToOne(mappedBy = "CO2")
  @ManyToOne
  @JoinColumn(name = "sensors_sensor_id")
  private Sensor sensors;

  public Sensor getSensors() {
    return sensors;
  }

  public void setSensors(Sensor sensors) {
    this.sensors = sensors;
  }

  public CO2Measurement(int CO2Level) {
    this.CO2Level = CO2Level;

  }

  public CO2Measurement() {
  }

  public java.lang.Long getCO2Id()
  {
    return CO2Id;
  }

  public void setCO2Id(java.lang.Long CO2Id)
  {
    this.CO2Id = CO2Id;
  }

  public int getCO2Level()
  {
    return CO2Level;
  }

  public void setCO2Level(int CO2Level)
  {
    this.CO2Level = CO2Level;
  }


}
