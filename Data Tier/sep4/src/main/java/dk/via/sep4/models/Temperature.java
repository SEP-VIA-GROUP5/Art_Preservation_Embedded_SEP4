package dk.via.sep4.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Ravneet
 */
@Entity
@Table
public class Temperature {
  @Id
  @GeneratedValue(generator = "sequence-generator")
  @GenericGenerator(
          name = "sequence-generator",
          strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
          parameters = {
                  @org.hibernate.annotations.Parameter(name = "sequence_name", value = "temperature_sequence"),
                  @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                  @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
          }
  )
  private Long id;
  @Column(nullable = false)
  private double temperature;

  @Column
  private double max;

  @OneToOne(mappedBy = "temperature")
  private Metrics metrics;

  public Temperature() {
  }

  public Temperature(double temperature) {
    this.temperature = temperature;
    max = 0;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getTemperature() {
    return temperature;
  }

  public void setTemperature(double temperature) {
    this.temperature = temperature;
  }

  public double getMax() {
    return max;
  }

  public void setMax(double max) {
    this.max = max;
  }

  @Override
  public String toString() {
    return "Temperature{" +
            "id: " + id +
            ", temperature: " + temperature +
            ", max: " + max +
            '}';
  }
}
