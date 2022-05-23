package dk.via.sep4.exceptions;

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
