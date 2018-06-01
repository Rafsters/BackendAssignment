package pl.backendassignment.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import pl.backendassignment.dao.NoteDAO;
import pl.backendassignment.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	private NoteDAO noteDAO;
	
	@Override
	@Transactional
	public List<Note> getNotes() {
		return noteDAO.getNotes();
	}

	@Override
	@Transactional
	public void saveNote(Note theNote) {

		noteDAO.saveNote(theNote);
	}

	@Override
	@Transactional
	public Note getNote(int theId) {
		
		return noteDAO.getNote(theId);
	}

	@Override
	@Transactional
	public void deleteNote(int theId) {
		
		noteDAO.deleteNote(theId);
	}

	@Transactional
	@Override
	public List<Note> getHistoryOfChangesForNote(int theId) {
		return noteDAO.getHistoryOfChangesForNote(theId);
	}
}





