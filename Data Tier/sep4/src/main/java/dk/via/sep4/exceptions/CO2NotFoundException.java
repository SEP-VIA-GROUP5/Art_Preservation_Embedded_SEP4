package dk.via.sep4.exceptions;

public class CO2NotFoundException extends RuntimeException {
    public CO2NotFoundException(Long id) {
        super("Could not find CO2 with id: " + id);
    }
}
