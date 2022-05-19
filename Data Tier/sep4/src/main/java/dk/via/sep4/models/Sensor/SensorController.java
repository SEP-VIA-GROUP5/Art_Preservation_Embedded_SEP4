package dk.via.sep4.models.Sensor;

import org.hibernate.EntityMode;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/SEP4")
@RestController
public class SensorController {
    private final SensorRepository repo;

    SensorController(SensorRepository repo) {
        this.repo = repo;
    }

    @PutMapping("/sensor/{id}")
    public String update(@PathVariable long id, @RequestParam double min, @RequestParam double max) {
        //repo.update(id, min, max);
        return "The values has been updated max : " + max + " and min: " + min + "";
    }

    
}
