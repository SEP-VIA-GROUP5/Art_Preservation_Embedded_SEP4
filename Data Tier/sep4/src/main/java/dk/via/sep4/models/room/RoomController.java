package dk.via.sep4.models.room;

import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController
{
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository)
    {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/rooms") List<Room> all()
    {
        return roomRepository.findAll();
    }

    //-----
    @PostMapping("/rooms")
    Room r1(@RequestBody Room r1){
        return roomRepository.save(r1);
    }

    @GetMapping("/rooms/{room_id}")
    Room one(@PathVariable Long id)
    {
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }
//    @PutMapping("/rooms/{id}")
//    Room replaceRoom(@RequestBody Room r1, @PathVariable Long id){
//        return roomRepository.findById(id).map(room -> {
//            room.setSensors(r1.getSensors());
//
//            return roomRepository.save(room);
//        }).orElseGet(() -> {
//            r1.setId(id);
//            return roomRepository.save(r1);
//        });
//    }
    @DeleteMapping("/rooms/{id}")
    void deleteRoom(@PathVariable Long id){
        roomRepository.deleteById(id);
    }

    // .......


}