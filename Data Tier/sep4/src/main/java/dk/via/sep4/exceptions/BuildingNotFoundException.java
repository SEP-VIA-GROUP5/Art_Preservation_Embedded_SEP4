package dk.via.sep4.exceptions;

public class BuildingNotFoundException extends RuntimeException {
    public BuildingNotFoundException(Long id) {
        super("Could not find a building with id: " + id);
    }
}
