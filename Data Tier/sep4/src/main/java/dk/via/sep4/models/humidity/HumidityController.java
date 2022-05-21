package dk.via.sep4.models.humidity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
    Humidity newValue(@RequestBody Humidity newHumidity) {
        return repo.save(newHumidity);
    }

    @GetMapping("/humidity/{id}")
    Humidity one(@PathVariable java.lang.Long id) {
        return repo.findById(id).orElseThrow(
                () -> new HumidityNotFoundException(id)
        );
    }

    @PutMapping("/humidity/{id}")
    Humidity update(@RequestBody Humidity newHumidity, @PathVariable Long id) {
        return repo.findById(id)
                .map(humidity -> {
                    humidity.setValue(newHumidity.getValue());
                    return humidity;
                })
                .orElseGet(() -> {
                    newHumidity.setId(id);
                    return repo.save(newHumidity);
                });
    }

    @DeleteMapping("/humidity/{id}")
    void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
