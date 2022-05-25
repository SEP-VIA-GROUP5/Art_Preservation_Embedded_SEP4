package dk.via.sep4.controllers;

import dk.via.sep4.exceptions.BuildingNotFoundException;
import dk.via.sep4.models.Building;
import dk.via.sep4.models.Room;
import dk.via.sep4.repo.BuildingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BuildingController {
    private final BuildingRepository repo;

    public BuildingController(BuildingRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/buildings")
    List<Building> all()
    {
        return repo.findAll();
    }

    @PostMapping("/building")
    Building create(@RequestBody Building newBuilding){
        return repo.save(newBuilding);
    }

    @GetMapping("/building/{id}")
    Building get(@PathVariable Long id)
    {
        return repo.findById(id).orElseThrow(() -> new BuildingNotFoundException(id));
    }

    @PostMapping("/addRoom/{id}")
    Building addRoom(@RequestBody Room room, @PathVariable Long id){
        return repo.findById(id)
                .map(building -> {
                    building.addRoom(room);
                    return repo.save(building);
                })
                .orElseThrow(() -> new BuildingNotFoundException(id));
    }

    @PutMapping("/building/{id}")
    Building update(@RequestBody Building building, @PathVariable Long id){
        return repo.findById(id)
                .map(b -> {
                    b.setAddress(building.getAddress());
                    return repo.save(b);
                })
                .orElseGet(() -> {
                    building.setId(id);
                    return repo.save(building);
                });
    }

    @DeleteMapping("/removeRoom/{id}")
    Building removeRoom(@RequestBody Room room, @PathVariable Long id){
        return repo.findById(id)
                .map(building -> {
                    building.removeRoom(room);
                    return repo.save(building);
                })
                .orElseThrow(() -> new BuildingNotFoundException(id));
    }

    @DeleteMapping("/building/{id}")
    void delete(@PathVariable Long id){
        repo.deleteById(id);
    }
}
