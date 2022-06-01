package dk.via.sep4.repo;

import dk.via.sep4.models.CO2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CO2Repository extends JpaRepository<CO2, Long> {
    CO2 findTopByOrderByIdDesc();
}
