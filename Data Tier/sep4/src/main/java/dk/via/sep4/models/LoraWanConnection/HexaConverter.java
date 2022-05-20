package dk.via.sep4.models.LoraWanConnection;

import dk.via.sep4.measurements.CO2Measurement;
import dk.via.sep4.measurements.HumidityMeasurement;
import dk.via.sep4.measurements.TempMeasurement;
import dk.via.sep4.models.Sensor.Sensor;

/**
 * Ravneet
 */
public class HexaConverter {

  private String data;

  public HexaConverter(String data)
  {
    this.data = data;
  }



  public Sensor convertFromHexaToInt(DataReceivedMessage data)
  {
    int Co2 ;
    int temperature;
    int humidity;


    String hexValCo2 = data.getData().substring(0, 4);
    Co2 = Integer.parseInt(hexValCo2, 16);
    CO2Measurement co2Measurement = new CO2Measurement(Co2);

    String hexValTemp = data.getData().substring(4,8);
    temperature = Integer.parseInt(hexValTemp, 16);
    TempMeasurement temperatureMeasurement = new TempMeasurement(temperature);

    String hexValHum = data.getData().substring(8,12);
    humidity = Integer.parseInt(hexValHum, 16);
    HumidityMeasurement humidityMeasurement = new HumidityMeasurement(humidity);

    Sensor sensor = new Sensor();

    return sensor;
  }
}