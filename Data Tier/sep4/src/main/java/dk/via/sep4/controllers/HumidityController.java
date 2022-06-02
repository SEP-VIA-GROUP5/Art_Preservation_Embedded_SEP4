package dk.via.sep4.controllers;

import dk.via.sep4.exceptions.HumidityNotFoundException;
import dk.via.sep4.models.Humidity;
import dk.via.sep4.repo.HumidityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HumidityController {
    private final HumidityRepository repo;

    public HumidityController(HumidityRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/humidities")
    List<Humidity> all() {
        return repo.findAll();
    }

    @PostMapping("/humidity")
    Humidity create(@RequestBody Humidity newHumidity) {
        return repo.save(newHumidity);
    }

    @GetMapping("/humidity/{id}")
    Humidity get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new HumidityNotFoundException(id)
        );
    }

    @GetMapping("/humidity")
    double getMax(){
        List<Humidity> all = repo.findAll();
        Humidity max = repo.findById((long) all.size()).orElseThrow(
                () -> new HumidityNotFoundException((long) all.size())
        );
        return max.getMax();
    }

    @DeleteMapping("/humidity/{id}")
    void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
