package dk.via.sep4.controllers;

import dk.via.sep4.exceptions.RoomNotFoundException;
import dk.via.sep4.models.Room;
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

    //-----
    @PostMapping("/room")
    Room create(@RequestBody Room r1){
        return repo.save(r1);
    }

    @GetMapping("/room/{id}")
    Room one(@PathVariable Long id)
    {
        return repo.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }
//    @PutMapping("/rooms/{id}")
//    RoomController replaceRoom(@RequestBody RoomController r1, @PathVariable Long id){
//        return roomRepository.findById(id).map(room -> {
//            room.setSensors(r1.getSensors());
//
//            return roomRepository.save(room);
//        }).orElseGet(() -> {
//            r1.setId(id);
//            return roomRepository.save(r1);
//        });
//    }
    @DeleteMapping("/room/{id}")
    void deleteRoom(@PathVariable Long id){
        repo.deleteById(id);
    }

    // .......


}