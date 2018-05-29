package pl.backendassignment.rest;

import java.util.List;

import pl.backendassignment.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.backendassignment.service.NoteService;

@RestController
@RequestMapping("/api")
public class NoteRESTController {

    @Autowired
    private NoteService noteService;

    @GetMapping("/notes")
    public List<Note> getNotes() {

        return noteService.getNotes();
    }

    @GetMapping("/notes/{theNoteId}")
    public Note getNote(@PathVariable int theNoteId) {

        if(theNoteId > noteService.getNotes().size() || theNoteId < 0) {
            System.out.println("Error not found");
            throw new NoteNotFoundException("Note id not found: " + theNoteId);
        }

        return noteService.getNote(theNoteId);

    }

    @PostMapping("/notes")
    public Note addNote(@RequestBody Note theNote) {
        if(theNote.getTitle().isEmpty() || theNote.getTitle() == null){
            throw new NoteWrongInputException("Wrong input");
        }
        noteService.saveNote(theNote);

        return theNote;
    }

    @PutMapping("/notes/{theNoteId}")
    public Note updateNote(@PathVariable int theNoteId,
                           @RequestBody Note theNote) {

        theNote.setId(theNoteId);

        noteService.saveNote(theNote);

        return theNote;
    }

    @DeleteMapping("/notes/{theNoteId}")
    public String deleteNote(@PathVariable int theNoteId) {

        noteService.deleteNote(theNoteId);

        return "Deleted note id: " + theNoteId;
    }

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

}
