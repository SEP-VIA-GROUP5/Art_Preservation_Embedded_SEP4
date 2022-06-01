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

    @GetMapping("/co2/{id}")
    CO2 get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(
                () -> new CO2NotFoundException(id)
        );
    }

    @GetMapping("/co2")
    double getMax() {
        List<CO2> all = repo.findAll();
        CO2 co2 = repo.findById((long) all.size()).orElseThrow(
                () -> new CO2NotFoundException((long) all.size())
        );
        return co2.getMax();
    }

    @DeleteMapping("/co2/{id}")
    void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
