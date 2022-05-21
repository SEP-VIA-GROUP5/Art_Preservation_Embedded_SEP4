package dk.via.sep4.models.sensor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SensorRepository extends JpaRepository<Sensor, Long> {

}
