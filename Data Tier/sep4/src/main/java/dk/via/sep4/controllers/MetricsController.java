package dk.via.sep4.controllers;

import dk.via.sep4.exceptions.MetricsNotFoundException;
import dk.via.sep4.models.Metrics;
import dk.via.sep4.repo.MetricsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MetricsController {
    private final MetricsRepository repo;

    MetricsController(MetricsRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/metrics")
    List<Metrics> all() {
        return repo.findAll();
    }

    @GetMapping("/metrics/{roomId}")
    List<Metrics> history(@PathVariable Long roomId) {
        return repo.findMetricsByRoomIs(roomId);
    }

    @GetMapping("/metric/{id)")
    Metrics get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new MetricsNotFoundException(id)
        );
    }
}
