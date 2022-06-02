package dk.via.sep4;

import dk.via.sep4.models.*;
import dk.via.sep4.repo.BuildingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LoadDatabase {
 @Bean
 CommandLineRunner initDatabase(BuildingRepository repo) {
  return args -> {
   CO2 co2_2 = new CO2(64);
   Humidity humidity2 = new Humidity(96);
   Temperature temperature2 = new Temperature(21);
   Metrics metrics2 = new Metrics(co2_2, humidity2, temperature2);

   CO2 co2_3 = new CO2(66);
   Humidity humidity3 = new Humidity(96);
   Temperature temperature3 = new Temperature(24);
   Metrics metrics3 = new Metrics(co2_3, humidity3, temperature3);

   CO2 co2 = new CO2(67);
   Humidity humidity = new Humidity(97);
   Temperature temperature = new Temperature(22);
   Metrics metrics = new Metrics(co2, humidity, temperature);

   Room r2 = new Room("Cubism", 2);

   r2.addMetrics(metrics);
   r2.addMetrics(metrics3);
   r2.addMetrics(metrics2);

   Building b1 = new Building("Somewhere");

   b1.addRoom(r2);

   repo.save(b1);
  };
 }
}
