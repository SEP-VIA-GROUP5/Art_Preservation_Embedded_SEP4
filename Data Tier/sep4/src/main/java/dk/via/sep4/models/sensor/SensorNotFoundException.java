package dk.via.sep4.models.sensor;

public class SensorNotFoundException extends RuntimeException {
    public SensorNotFoundException(Long id) {
        super("Could not find sensors with id " + id);
    }
}
