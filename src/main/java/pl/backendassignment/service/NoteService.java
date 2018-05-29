package pl.backendassignment.service;

import java.util.List;

import pl.backendassignment.entity.Note;

public interface NoteService {

	public List<Note> getNotes();

	public void saveNote(Note theNote);

	public Note getNote(int theId);

	public void deleteNote(int theId);
	
}
