package dk.via.sep4.controllers;

import dk.via.sep4.exceptions.*;
import dk.via.sep4.models.*;
import dk.via.sep4.repo.RoomRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController
{
    private final RoomRepository repo;

    public RoomController(RoomRepository roomRepository)
    {
        this.repo = roomRepository;
    }

    @GetMapping("/rooms") List<Room> all()
    {
        return repo.findAll();
    }

    @PostMapping("/room")
    Room create(@RequestBody Room room){
        return repo.save(room);
    }

    @PostMapping("/addMetrics/{id}")
    Room addMetrics(@RequestBody Metrics metrics, @PathVariable Long id){
        return repo.findById(id)
                .map(room -> {
                    metrics.setTime();
                    room.addMetrics(metrics);
                    return repo.save(room);
                })
                .orElseThrow(() -> new MetricsNotFoundException(id));
    }

    @GetMapping("/room/{id}")
    Room get(@PathVariable Long id)
    {
        return repo.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }

    @PutMapping("/rooms/{id}")
    Room update(@RequestBody Room room, @PathVariable Long id){
        return repo.findById(id).map(r -> {
            r.setName(room.getName());
            r.setNumber(room.getNumber());
            return repo.save(room);
        }).orElseGet(() -> {
            room.setId(id);
            return repo.save(room);
        });
    }

    @DeleteMapping("/room/{id}")
    void deleteRoom(@PathVariable Long id){
        repo.deleteById(id);
    }
}