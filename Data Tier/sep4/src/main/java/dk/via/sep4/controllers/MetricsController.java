package dk.via.sep4.controllers;

import dk.via.sep4.models.Metrics;
import dk.via.sep4.exceptions.SensorNotFoundException;
import dk.via.sep4.repo.MetricsRepository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/metrics")
    Metrics newValue(@RequestBody Metrics newMetric) {
        return repo.save(newMetric);
    }
    @GetMapping("/metrics/{id}")
    Metrics one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new SensorNotFoundException(id)
        );
    }

    @DeleteMapping("metrics/{id}")
    void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
