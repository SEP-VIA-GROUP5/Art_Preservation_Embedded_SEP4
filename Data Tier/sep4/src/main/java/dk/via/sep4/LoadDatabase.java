package dk.via.sep4;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

 @Bean
 CommandLineRunner initDatabase(final String data, RoomRepository roomRepository) {
  return args -> {

//   log.info("Preloading " + data.save());
   Room r = new Room("Main room");
   roomRepository.save(r);

   Sensor s1 = new Sensor(SensorType.CO2, "PPM", r, 0, 0);
   Sensor s2= new Sensor(SensorType.Humidity, "%", r, 0, 0);
   Sensor s3 = new Sensor(SensorType.Temperature, "°C", r, 0, 20);
//
//  };
// }
}
