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
  private double value;

  @Column
  private double norm;

  @OneToOne(mappedBy = "co2")
  private Metrics metrics;

  public CO2(double level) {
    this.value = level;
    norm = 0;
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

  public double getValue()
  {
    return value;
  }

  public void setValue(double level)
  {
    this.value = level;
  }

  public double getNorm() {
    return norm;
  }

  public void setNorm(double norm) {
    this.norm = norm;
  }
}
