package dk.via.sep4.LoraWanConnection;

import dk.via.sep4.SpringConfiguration;
import dk.via.sep4.models.*;
import dk.via.sep4.repo.CO2Repository;
import dk.via.sep4.repo.HumidityRepository;
import dk.via.sep4.repo.TemperatureRepository;

/**
 * Ravneet
 */

public class HexaConverter {

  private final CO2Repository co2Rep;
  private final HumidityRepository humidityRep;
  private final TemperatureRepository temperatureRep;
  Room r1 = new Room("Baroque", 1);
  private Metrics metrics;

  public HexaConverter() {
    co2Rep = (CO2Repository) SpringConfiguration.contextProvider().getApplicationContext().getBean("CO2Repository");
    humidityRep = (HumidityRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("humidityRepository");
    temperatureRep = (TemperatureRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("temperatureRepository");
  }

  public Metrics convertFromHexaToInt(DataReceivedMessage data)
  {
    int Co2;
    int temperature;
    int humidity;

    String hexValCo2 = data.getData().substring(0, 4);
    Co2 = Integer.parseInt(hexValCo2, 16);
    CO2 co2Measurement = new CO2((double) Co2/10);
    co2Measurement.setMax(co2Rep.findTopByOrderByIdDesc().getMax());

    String hexValHum = data.getData().substring(4,9);
    humidity = Integer.parseInt(hexValHum, 16);
    Humidity humidityMeasurement = new Humidity((double) humidity/10);
    humidityMeasurement.setMax(humidityRep.findTopByOrderByIdDesc().getMax());

    String hexValTemp = data.getData().substring(9);
    temperature = Integer.parseInt(hexValTemp, 16);
    Temperature temperatureMeasurement = new Temperature((double) temperature/10);
    temperatureMeasurement.setMax(temperatureRep.findTopByOrderByIdDesc().getMax());

    metrics = new Metrics(co2Measurement, humidityMeasurement, temperatureMeasurement);
    r1.addMetrics(metrics);

    return metrics;
  }
}

