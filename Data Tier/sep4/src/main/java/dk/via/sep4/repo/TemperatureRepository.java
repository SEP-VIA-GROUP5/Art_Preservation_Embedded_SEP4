package dk.via.sep4.repo;

import dk.via.sep4.models.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureRepository extends JpaRepository<Temperature, Long> {
    Temperature findTopByOrderByIdDesc();
}
