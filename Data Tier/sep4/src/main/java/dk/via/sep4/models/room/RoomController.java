package dk.via.sep4.models.room;

import org.apache.catalina.LifecycleState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/SEP4")
@RestController
public class RoomController {
    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository)
    {
        this.roomRepository = roomRepository;
    }

    @GetMapping("/rooms")
    List<Room> all(){
        return roomRepository.findAll();
    }

    @GetMapping("/rooms/{id}")
    Room one(@PathVariable Long id){
    return roomRepository.findById(id).orElseThrow( () -> new RoomNotFoundException(id));
    }
}
