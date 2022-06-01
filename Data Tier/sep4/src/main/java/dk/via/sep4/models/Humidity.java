package dk.via.sep4.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Ravneet
 */
@Entity
@Table
public class Humidity {
  @Id
  @GeneratedValue(generator = "sequence-generator")
  @GenericGenerator(
          name = "sequence-generator",
          strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
          parameters = {
                  @org.hibernate.annotations.Parameter(name = "sequence_name", value = "humidity_sequence"),
                  @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                  @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
          }
  )
  private Long id;
  @Column(nullable = false)
  private double humidity;

  @Column
  private double max;

  @OneToOne(mappedBy = "humidity")
  private Metrics metrics;

  public Humidity(double humidity) {
    this.humidity = humidity;
    max = 0;
  }

  public Humidity() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public double getHumidity() {
    return humidity;
  }

  public void setHumidity(double humidity) {
    this.humidity = humidity;
  }

  public double getMax() {
    return max;
  }

  public void setMax(double max) {
    this.max = max;
  }

  @Override
  public String toString() {
    return "Humidity{" +
            "id: " + id +
            ", humidity: " + humidity +
            ", max: " + max +
            '}';
  }
}
