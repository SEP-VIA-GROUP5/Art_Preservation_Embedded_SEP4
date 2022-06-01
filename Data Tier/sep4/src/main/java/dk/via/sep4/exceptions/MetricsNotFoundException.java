package dk.via.sep4.exceptions;

public class MetricsNotFoundException extends RuntimeException {
    public MetricsNotFoundException(Long id) {
        super("Could not find metrics with id: " + id);
    }
}
