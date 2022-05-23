package dk.via.sep4;

import dk.via.sep4.models.CO2;
import dk.via.sep4.models.Humidity;
import dk.via.sep4.models.Temperature;
import dk.via.sep4.models.Sensor;
import dk.via.sep4.repo.SensorRepository;
import dk.via.sep4.models.Room;
import dk.via.sep4.repo.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

@Configuration
public class LoadDatabase {
 private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

 @Bean
 CommandLineRunner initDatabase( RoomRepository roomRepository, SensorRepository sensorRepository) {
  return args -> {
   Humidity humidity = new Humidity(60);
   CO2 co2 = new CO2(400);
   Temperature temperature = new Temperature(22);

   Sensor sensor = new Sensor(co2, humidity, temperature,new Timestamp(2022,12,11,12,45,30,11));

   Humidity humidityv1 = new Humidity(6);
   CO2 co2v1 = new CO2(40);
   Temperature temperaturev1 = new Temperature(12);

   Sensor sensorV2 = new Sensor(co2v1, humidityv1, temperaturev1, new Timestamp(2002,11,10,11,40,25,10));

   Humidity humidityv2 = new Humidity(6);
   CO2 co2v2 = new CO2(4);
   Temperature temperaturev2 = new Temperature(2);

   Sensor sensorV3 = new Sensor(co2v2, humidityv2, temperaturev2, new Timestamp(2021,10,9,10,35,20,9));


   Room r1 = new Room("Baroque", "Second Floor", 1);
   Room r2 = new Room("Cubism", "First Floor", 2);
   Room r3 = new Room("Fauvism", "First Floor", 3);

   log.info("Preloading " + roomRepository.save(r1));
   log.info("Preloading " + roomRepository.save(r2));
   log.info("Preloading " + roomRepository.save(r3));

   log.info("Preloading " + sensorRepository.save(sensor));
   log.info("Preloading " + sensorRepository.save(sensorV2));
   log.info("Preloading " + sensorRepository.save(sensorV3));

  };

 }
}
