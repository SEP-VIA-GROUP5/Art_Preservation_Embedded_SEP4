package dk.via.sep4.models.CO2;

public class CO2NotFoundException extends RuntimeException {
    public CO2NotFoundException(Long id) {
        super("Could not find CO2 sensor with id " + id);
    }
}
