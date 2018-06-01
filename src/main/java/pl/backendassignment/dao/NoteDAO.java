package pl.backendassignment.dao;

import java.util.List;

import pl.backendassignment.entity.Note;

public interface NoteDAO {

	public List<Note> getNotes();

	public void saveNote(Note theNote);

	public Note getNote(int theId);

	public void deleteNote(int theId);

	public List<Note> getHistoryOfChangesForNote(int theId);
	
}
