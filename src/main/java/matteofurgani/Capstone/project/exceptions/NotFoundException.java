package matteofurgani.Capstone.project.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(int id) {
        super("L'id n." + id + " was not found!");
    }
}
