package dk.via.sep4.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Ravneet
 */
@Entity
@Table
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
  @Column(nullable = false)
  private double CO2;

  @Column
  private double min, max;

  @OneToOne(mappedBy = "co2")
  private Metrics metrics;

  public CO2(double CO2) {
    this.CO2 = CO2;
    min = 0;
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

  public double getCO2()
  {
    return CO2;
  }

  public void setCO2(double CO2)
  {
    this.CO2 = CO2;
  }

  public void setNorm(double min, double max) {
    this.min = min;
    this.max = max;
  }

  @Override
  public String toString() {
    return "CO2{" +
            "id: " + id +
            ", CO2: " + CO2 +
            ", min: " + min +
            ", max: " + max +
            '}';
  }
}
