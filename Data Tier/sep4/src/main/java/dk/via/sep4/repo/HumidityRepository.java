package dk.via.sep4.repo;

import dk.via.sep4.models.Humidity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HumidityRepository extends JpaRepository<Humidity, Long> {
    Humidity findTopByOrderByIdDesc();
}
