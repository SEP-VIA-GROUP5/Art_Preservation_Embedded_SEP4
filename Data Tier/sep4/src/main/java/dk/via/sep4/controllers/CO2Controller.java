package dk.via.sep4.controllers;

import dk.via.sep4.exceptions.CO2NotFoundException;
import dk.via.sep4.models.CO2;
import dk.via.sep4.repo.CO2Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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
    CO2 create(@RequestBody CO2 newCO2) {
        return repo.save(newCO2);
    }

    @PostMapping("/co2s")
    CO2 setNorm(@RequestBody CO2 newCO2, @RequestParam double min, @RequestParam double max) {
        newCO2.setNorm(min, max);
        return repo.save(newCO2);
    }

    @GetMapping("/co2/{id}")
    CO2 get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new CO2NotFoundException(id)
        );
    }

//    @PutMapping("/co2/{id}")
//    CO2 update(@RequestBody CO2 newCO2, @PathVariable Long id) {
//        return repo.findById(id)
//                .map(co2 -> {
//                    co2.setValue(newCO2.getValue());
//                    return repo.save(newCO2);
//                })
//                .orElseGet(() -> {
//                    newCO2.setId(id);
//                    return repo.save(newCO2);
//                });
//    }

    @DeleteMapping("/co2/{id}")
    void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
