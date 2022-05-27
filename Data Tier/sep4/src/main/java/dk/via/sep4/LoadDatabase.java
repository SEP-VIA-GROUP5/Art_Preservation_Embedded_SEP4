package dk.via.sep4;

import dk.via.sep4.models.*;
import dk.via.sep4.models.Metrics;
import dk.via.sep4.repo.BuildingRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
 private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

 @Bean
 CommandLineRunner initDatabase(BuildingRepository repo) {
  return args -> {
   CO2 co2 = new CO2(400);
   Humidity humidity = new Humidity(60);
   Temperature temperature = new Temperature(22);

   Metrics metrics = new Metrics(co2, humidity, temperature);

   CO2 co2_2 = new CO2(40);
   Humidity humidity2 = new Humidity(6);
   Temperature temperature2 = new Temperature(12);

   Metrics metrics2 = new Metrics(co2_2, humidity2, temperature2);

   CO2 co2_3 = new CO2(4);
   Humidity humidity3 = new Humidity(6);
   Temperature temperature3 = new Temperature(2);

   Metrics metrics3 = new Metrics(co2_3, humidity3, temperature3);

   Room r1 = new Room("Baroque", 1);
   Room r2 = new Room("Cubism", 2);
   r2.addMetrics(metrics);
   r2.addMetrics(metrics3);
   r2.addMetrics(metrics2);

   Building b1 = new Building("Somewhere");

   b1.addRoom(r1);
   b1.addRoom(r2);

   log.info("Preloading " + repo.save(b1));
  };

 }
}
