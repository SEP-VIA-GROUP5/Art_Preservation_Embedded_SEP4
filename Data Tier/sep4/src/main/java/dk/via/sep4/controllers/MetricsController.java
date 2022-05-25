package dk.via.sep4.controllers;

import dk.via.sep4.models.Metrics;
import dk.via.sep4.exceptions.MetricsNotFoundException;
import dk.via.sep4.repo.MetricsRepository;
import org.jetbrains.annotations.NotNull;
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
    Metrics create(@RequestBody @NotNull Metrics newMetric) {
        newMetric.setTime();
        return repo.save(newMetric);
    }

    @GetMapping("/metrics/{id}")
    Metrics get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new MetricsNotFoundException(id)
        );
    }

    @DeleteMapping("metrics/{id}")
    void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
