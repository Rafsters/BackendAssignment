package pl.backendassignment.dao;

import java.util.List;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import pl.backendassignment.entity.Note;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class NoteDAOImpl implements NoteDAO {

	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Note> getNotes() {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<Note> theQuery =
				currentSession.createQuery("from Note order by title",
						Note.class);

		List<Note> notes = theQuery.getResultList();

		return notes;
	}

	@Override
	public void saveNote(Note theNote) {

		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(theNote);

	}

	@Override
	public Note getNote(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();

		Note theNote = currentSession.get(Note.class, theId);

		return theNote;
	}

	@Override
	public void deleteNote(int theId) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query theQuery = 
				currentSession.createQuery("delete from Note where id=:noteId");
		theQuery.setParameter("noteId", theId);
		
		theQuery.executeUpdate();		
	}

	@Override
	public List<Note> getHistoryOfChangesForNote(int theId) {
		Session currentSession = sessionFactory.getCurrentSession();

		AuditReader auditReader = AuditReaderFactory.get(currentSession);
		AuditQuery q = auditReader.createQuery().forRevisionsOfEntity(Note.class, true, true);
		q.add(AuditEntity.id().eq(theId));
		List<Note> audit = q.getResultList();
		for (Note n :
				audit) {
			System.out.println(n);
		}

		return audit;
	}
}











