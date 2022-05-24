package dk.via.sep4.exceptions;

public class BuildingNotFoundException extends RuntimeException {
    public BuildingNotFoundException(Long id) {
        super("Could not find the building with id: " + id);
    }
}
