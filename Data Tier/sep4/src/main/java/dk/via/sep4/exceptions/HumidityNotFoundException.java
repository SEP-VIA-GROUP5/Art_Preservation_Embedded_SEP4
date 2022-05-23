package dk.via.sep4.exceptions;

public class HumidityNotFoundException extends RuntimeException {
    public HumidityNotFoundException(Long id) {
        super("Could not find humidity sensor with id " + id);
    }
}
