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

        if(theNoteId < 0) {
            System.out.println("Error not found");
            throw new NoteNotFoundException("Note id not found: " + theNoteId);
        }

        return noteService.getNote(theNoteId);

    }

    @PostMapping("/notes")
    public Note addNote(@RequestBody Note theNote) {
        if(theNote.getTitle().isEmpty() || theNote.getTitle() == null || theNote.getContent().isEmpty() || theNote.getContent() == null){
            throw new NoteWrongInputException("Input data cannot be empty");
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

    @GetMapping("/notes/history/{theNoteId}")
    public List<Note> getHistoryOfChangesForNote(@PathVariable int theNoteId) {

        if(theNoteId < 0) {
            System.out.println("Error not found");
            throw new NoteNotFoundException("Note id not found: " + theNoteId);
        }

        return noteService.getHistoryOfChangesForNote(theNoteId);

    }

}
