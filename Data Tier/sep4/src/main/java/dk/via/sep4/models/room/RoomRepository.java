package dk.via.sep4.models.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface RoomRepository
    extends JpaRepository<Room, Long> {

        Room findByRoomidIs(Long roomid);
}


