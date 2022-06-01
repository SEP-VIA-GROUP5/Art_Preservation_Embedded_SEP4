package dk.via.sep4.repo;

import dk.via.sep4.models.Metrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MetricsRepository extends JpaRepository<Metrics, Long> {
    List<Metrics> findMetricsByRoomIs(Long roomId);
}
