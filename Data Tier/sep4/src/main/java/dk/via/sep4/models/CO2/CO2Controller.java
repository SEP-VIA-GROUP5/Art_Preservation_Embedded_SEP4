package dk.via.sep4.models.CO2;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CO2Controller {
    private final CO2Repository repo;

    public CO2Controller(CO2Repository repo) {
        this.repo = repo;
    }

    @GetMapping("/co2s")
    List<CO2> all() {
        return repo.findAll();
    }

    @PostMapping("/co2")
    CO2 newCO2Sensor(@RequestBody CO2 newCO2) {
        return repo.save(newCO2);
    }

    @GetMapping("/co2/{id}")
    CO2 one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new CO2NotFoundException(id)
        );
    }

    @PutMapping("/co2/{id}")
    CO2 updateCO2Level(@RequestBody CO2 newCO2, @PathVariable Long id) {
        return repo.findById(id)
                .map(co2 -> {
                    co2.setValue(newCO2.getValue());
                    return co2;
                })
                .orElseGet(() -> {
                    newCO2.setId(id);
                    return repo.save(newCO2);
                });
    }

    @DeleteMapping("/co2/{id}")
    void deleteSO2Sensor(@PathVariable java.lang.Long id) {
        repo.deleteById(id);
    }
}
