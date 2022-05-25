package dk.via.sep4;

import dk.via.sep4.models.*;
import dk.via.sep4.models.Metrics;
import dk.via.sep4.repo.BuildingRepository;
import dk.via.sep4.repo.MetricsRepository;
import dk.via.sep4.repo.RoomRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
 private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

 @Bean
 CommandLineRunner initDatabase( RoomRepository roomRepository, MetricsRepository metricsRepository, BuildingRepository repo) {
  return args -> {
   Humidity humidity = new Humidity(60);
   CO2 co2 = new CO2(400);
   Temperature temperature = new Temperature(22);

   Metrics sensor = new Metrics(co2, humidity, temperature);
   sensor.setTime();

   Humidity humidityv1 = new Humidity(6);
   CO2 co2v1 = new CO2(40);
   Temperature temperaturev1 = new Temperature(12);

   Metrics sensorV1 = new Metrics(co2v1, humidityv1, temperaturev1);
   sensorV1.setTime();

   Humidity humidityv2 = new Humidity(6);
   CO2 co2v2 = new CO2(4);
   Temperature temperaturev2 = new Temperature(2);

   Metrics sensorV2 = new Metrics(co2v2, humidityv2, temperaturev2);
   sensorV2.setTime();

   Room r1 = new Room("Baroque", 1);
   r1.addMetrics(sensor);
   Room r2 = new Room("Cubism", 2);
   r2.addMetrics(sensorV2);
   Room r3 = new Room("Fauvism", 3);
   r3.addMetrics(sensorV1);

   Building b1 = new Building("Somewhere");

   b1.addRoom(r1);
   b1.addRoom(r2);
   b1.addRoom(r3);

   log.info("Preloading " + repo.save(b1));

  };

 }
}
