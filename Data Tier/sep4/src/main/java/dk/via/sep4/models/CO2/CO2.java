package dk.via.sep4.models.CO2;

import dk.via.sep4.models.sensor.Sensor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Ravneet
 */
@Entity
@Table(name="co2")
public class CO2 {

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
  private Long id;
  @Column
  private int value;

  @OneToOne(mappedBy = "co2")
  private Sensor sensor;

  public CO2(int level) {
    this.value = level;
  }
  public CO2() {
  }

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public int getValue()
  {
    return value;
  }

  public void setValue(int level)
  {
    this.value = level;
  }
}
