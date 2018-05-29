package pl.backendassignment.rest;

public class NoteWrongInputException extends RuntimeException {
    public NoteWrongInputException(String message) {
        super(message);
    }

    public NoteWrongInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoteWrongInputException(Throwable cause) {
        super(cause);
    }
}