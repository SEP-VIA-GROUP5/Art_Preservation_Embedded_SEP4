package dk.via.sep4.repo;

import dk.via.sep4.models.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoomRepository extends JpaRepository<Room, Long> {

       // RoomController findByRoomidIs(Long roomid);
}


