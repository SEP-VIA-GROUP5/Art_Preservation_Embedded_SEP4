package dk.via.sep4.exceptions;

public class TemperatureNotFoundException extends RuntimeException {
    public TemperatureNotFoundException(Long id) {
        super("Could not find temperature sensor with id " + id);
    }
}
