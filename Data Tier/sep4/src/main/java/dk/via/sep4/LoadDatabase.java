package dk.via.sep4;

import dk.via.sep4.models.Sensor.Sensor;
import dk.via.sep4.models.Sensor.SensorModel;
import dk.via.sep4.models.room.Room;
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
   Room r = new Room();
   roomRepository.save(r);

   Sensor s1 = new Sensor(SensorModel.CO2, "PPM", r, 0, 0);
   Sensor s2 = new Sensor(SensorModel.Humidity, "%", r, 0, 0);
   Sensor s3 = new Sensor(SensorModel.Temperature, "°C", r, 0, 20);

  };

 }
}
