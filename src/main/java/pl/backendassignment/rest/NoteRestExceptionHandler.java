package pl.backendassignment.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoteRestExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<NoteErrorResponse> handleException(NoteNotFoundException exc){

        NoteErrorResponse noteErrorResponse = new NoteErrorResponse();
        noteErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        noteErrorResponse.setMessage(exc.getMessage());
        noteErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(noteErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<NoteErrorResponse> handleException(NoteWrongInputException exc){

        NoteErrorResponse noteErrorResponse = new NoteErrorResponse();
        noteErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        noteErrorResponse.setMessage(exc.getMessage());
        noteErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(noteErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<NoteErrorResponse> handleException(Exception exc){

        NoteErrorResponse noteErrorResponse = new NoteErrorResponse();
        noteErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        noteErrorResponse.setMessage(exc.getMessage());
        noteErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(noteErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
