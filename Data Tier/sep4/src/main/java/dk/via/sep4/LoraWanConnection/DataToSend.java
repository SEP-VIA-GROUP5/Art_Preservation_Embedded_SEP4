package dk.via.sep4.LoraWanConnection;

import dk.via.sep4.SpringConfiguration;
import dk.via.sep4.repo.CO2Repository;
import dk.via.sep4.repo.HumidityRepository;
import dk.via.sep4.repo.TemperatureRepository;

public class DataToSend {
    private final CO2Repository co2Rep;
    private final HumidityRepository humidityRep;
    private final TemperatureRepository temperatureRep;

    int co2Max;
    int humidity;
    int tempMax;

    public DataToSend() {
        co2Rep = (CO2Repository) SpringConfiguration.contextProvider().getApplicationContext().getBean("CO2Repository");
        humidityRep = (HumidityRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("humidityRepository");
        temperatureRep = (TemperatureRepository) SpringConfiguration.contextProvider().getApplicationContext().getBean("temperatureRepository");
    }

    public String getNorms() {
        co2Max = (int) Math.round(co2Rep.findTopByOrderByIdDesc().getMax());
        humidity = (int) Math.round(humidityRep.findTopByOrderByIdDesc().getMax());
        tempMax = (int) Math.round(temperatureRep.findTopByOrderByIdDesc().getMax());

        return String.format("%04x%02x%04x", co2Max, humidity, tempMax);
    }
}
