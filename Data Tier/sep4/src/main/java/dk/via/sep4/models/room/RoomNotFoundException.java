package dk.via.sep4.models.room;

/**
 * @author $(Alina Chelmus)
 */
public class RoomNotFoundException extends RuntimeException
{
  public RoomNotFoundException(Long id)
  {
    super("Could not find the room with that id");
  }
}
