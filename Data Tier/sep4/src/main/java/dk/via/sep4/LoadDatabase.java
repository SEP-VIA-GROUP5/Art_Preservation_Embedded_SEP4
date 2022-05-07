package dk.via.sep4;

import dk.via.sep4.models.Sensor.Sensor;
import dk.via.sep4.models.Sensor.SensorModel;
import dk.via.sep4.models.Sensor.SensorRepository;
import dk.via.sep4.models.room.Room;
import dk.via.sep4.models.room.RoomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
 private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

 @Bean
 CommandLineRunner initDatabase( RoomRepository roomRepository, SensorRepository sensorRepository) {
  return args -> {


   Room r1 = new Room("Baroque", "Second Floor", 1);
   Room r2 = new Room("Cubism", "First Floor", 2);
   Room r3 = new Room("Fauvism", "First Floor", 3);

   log.info("Preloading " + roomRepository.save(r1));
   log.info("Preloading " + roomRepository.save(r2));
   log.info("Preloading " + roomRepository.save(r3));

   Sensor s1 = new Sensor(SensorModel.CO2, "PPM", r1, 0, 0);
   Sensor s2 = new Sensor(SensorModel.Humidity, "%", r2, 0, 0);
   Sensor s3 = new Sensor(SensorModel.Temperature, "°C", r3, 0, 20);

   log.info("Preloading " + sensorRepository.save(s1));
   log.info("Preloading " + sensorRepository.save(s2));
   log.info("Preloading " + sensorRepository.save(s3));

  };

 }
}
