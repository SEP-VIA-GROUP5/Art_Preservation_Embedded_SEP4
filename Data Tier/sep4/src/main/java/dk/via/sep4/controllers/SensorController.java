package dk.via.sep4.controllers;

import dk.via.sep4.models.Sensor;
import dk.via.sep4.exceptions.SensorNotFoundException;
import dk.via.sep4.repo.SensorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SensorController {
    private final SensorRepository repo;

    SensorController(SensorRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/sensors")
    List<Sensor> all() {
        return repo.findAll();
    }

    @PostMapping("/sensor")
    Sensor newValue(@RequestBody Sensor newSensor) {
        return repo.save(newSensor);
    }
    @GetMapping("/sensor/{id}")
    Sensor one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new SensorNotFoundException(id)
        );
    }

    @DeleteMapping("sensor/{id}")
    void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
