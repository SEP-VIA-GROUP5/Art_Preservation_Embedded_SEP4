package dk.via.sep4.LoraWanConnection;

import dk.via.sep4.models.CO2;
import dk.via.sep4.models.Humidity;
import dk.via.sep4.models.Metrics;
import dk.via.sep4.models.Temperature;

/**
 * Ravneet
 */

public class HexaConverter {

  private String data;



  public HexaConverter() {
  }

  public Metrics convertFromHexaToInt(DataReceivedMessage data)
  {
    int Co2;
    int temperature;
    int humidity;

    String hexValCo2 = data.getData().substring(0, 4);
    Co2 = Integer.parseInt(hexValCo2, 16);
    CO2 co2Measurement = new CO2((double) Co2/10);

    String hexValHum = data.getData().substring(4,9);
    humidity = Integer.parseInt(hexValHum, 16);
    Humidity humidityMeasurement = new Humidity((double) humidity/10);

    String hexValTemp = data.getData().substring(9);
    temperature = Integer.parseInt(hexValTemp, 16);
    Temperature temperatureMeasurement = new Temperature((double) temperature/10);

    return new Metrics(co2Measurement, humidityMeasurement, temperatureMeasurement);
  }
}

