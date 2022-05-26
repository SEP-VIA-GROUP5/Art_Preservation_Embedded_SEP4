package dk.via.sep4.controllers;

import dk.via.sep4.models.Humidity;
import dk.via.sep4.exceptions.HumidityNotFoundException;
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

    @PostMapping("/humidities")
    Humidity setNorm(@RequestBody Humidity newHumidity, @RequestParam double min, @RequestParam double max) {
        newHumidity.setNorm(min, max);
        return repo.save(newHumidity);
    }

    @GetMapping("/humidity/{id}")
    Humidity get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new HumidityNotFoundException(id)
        );
    }

//    @PutMapping("/humidity/{id}")
//    Humidity update(@RequestBody Humidity newHumidity, @PathVariable Long id) {
//        return repo.findById(id)
//                .map(humidity -> {
//                    humidity.setValue(newHumidity.getValue());
//                    return humidity;
//                })
//                .orElseGet(() -> {
//                    newHumidity.setId(id);
//                    return repo.save(newHumidity);
//                });
//    }

    @DeleteMapping("/humidity/{id}")
    void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
