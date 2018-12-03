package banos.ms.beer.ws;

import java.util.Arrays;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.Session;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import banos.ms.beer.Note;
import banos.ms.beer.NoteEntity;
import banos.ms.utils.JSONUtils;

/**
 * [Author]			Mike Banos
 * [Date]			11/24/2018
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access notes.
 */
@Path("/notes")
public class NoteService extends Service<Note> {
	/**
	 * GET call handler to retrieve an individual item.
	 * @param id The id of the item to retrieve.
	 * @return The serialized item.
	 * @throws JsonProcessingException
	 */
	@GET
	@Path("session/{id : \\d+}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSessionNotes(@PathParam("id") int id) throws JsonProcessingException {
		final StandardServiceRegistry registry = getRegistry();
		final Session session = getSession(registry);
	
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Note> query = builder.createQuery(getEntityClass());
		
		Root<Note> root = query.from(Note.class);
		query.select(root);
		
		Predicate p1 = builder.equal(root.get("entityId"), id);
		Predicate p2 = builder.equal(root.get("entityType"), NoteEntity.SESSION);
		
		List<Predicate> predicates = Arrays.asList(p1, p2);
		query.where(builder.and(predicates.toArray(new Predicate[0])));
		
		try {
			return JSONUtils.toJson(session.createQuery(query).getResultList());
		} finally {
			session.close();
			StandardServiceRegistryBuilder.destroy(registry);	
		}
	}
	
	/**
	 * Get the entity class.
	 * @return The entity class.
	 */
	@Override
	protected Class<Note> getEntityClass() {
		return Note.class;
	}
}
