package banos.ms.beer.ws;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import banos.ms.utils.JSONUtils;

/**
 * [Author]			Mike Banos
 * [Date]			12/14/2017
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Base web service.
 * [Type T]			The type the service can handle.
 */
public abstract class Service<T> {
	/**
	 * GET call handler to retrieve list of all items.
	 * @return The serialized item list.
	 * @throws JsonProcessingException
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAll() throws JsonProcessingException {
		final StandardServiceRegistry registry = getRegistry();
		final Session session = getSession(registry);
	
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(getEntityClass());
		query.select(query.from(getEntityClass()));
		
		try {
			return JSONUtils.toJson(session.createQuery(query).getResultList());
		} finally {
			session.close();
			StandardServiceRegistryBuilder.destroy(registry);	
		}
	}
	/**
	 * GET call handler to retrieve an individual item.
	 * @param id The id of the item to retrieve.
	 * @return The serialized item.
	 * @throws JsonProcessingException
	 */
	@GET
	@Path("{id : \\d+}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSingleton(@PathParam("id") int id) throws JsonProcessingException {
		final StandardServiceRegistry registry = getRegistry();
		final Session session = getSession(registry);
		
		try {
			return JSONUtils.toJson(session.find(getEntityClass(), id));
		} finally {
			session.close();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	/**
	 * Post request handler.
	 * @param message The form values.
	 * @return The post response. 
	 * @throws ServletException 
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response post(final String message) throws ServletException {
		final StandardServiceRegistry registry = getRegistry();
		final Session session = getSession(registry);
		
		try {
			final T item = JSONUtils.fromJson(message, getEntityClass());
				
			session.beginTransaction();
			session.save(item);
			session.getTransaction().commit();
			
			return Response.status(Response.Status.CREATED.getStatusCode()).build();
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			session.close();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	/**
	 * Delete an item.
	 * @param id The id of the item to delete.
	 * @return The status response.
	 * @throws ServletException 
	 * @throws NumberFormatException 
	 */
	@DELETE
	@Path("{id : \\d+}")
	public Response delete(@PathParam("id") int id) throws NumberFormatException, ServletException {
		final StandardServiceRegistry registry = getRegistry();
		final Session session = getSession(registry);
		
		try {
			session.beginTransaction();
			session.remove(session.find(getEntityClass(), id));
			session.getTransaction().commit();
			
			return Response.status(Response.Status.OK.getStatusCode()).build();
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			session.close();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	/**
	 * Update an item.
	 * @param message The serialized item instance to update.
	 * @throws Exception 
	 */
	@PUT
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("{id : \\d+}")
	public Response put(final String message) throws ServletException {
		final StandardServiceRegistry registry = getRegistry();
		final Session session = getSession(registry);
		
		try {
			T item = JSONUtils.fromJson(message, getEntityClass());
			
			session.beginTransaction();
			session.update(item);
			session.getTransaction().commit();
			
			return Response.status(Response.Status.OK.getStatusCode()).build();
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			session.close();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	/**
	 * Get the entity class.
	 * @return The entity class.
	 */
	protected abstract Class<T> getEntityClass();
	
	/**
	 * Get a new service registry instance.
	 * @return The registry.
	 */
	private static StandardServiceRegistry getRegistry() {
		return new StandardServiceRegistryBuilder().configure().build();
	}
	
	/**
	 * Get a new Hibernate session.
	 * @return The session.
	 */
	private static Session getSession(final StandardServiceRegistry registry) {
		return new MetadataSources(registry).buildMetadata().buildSessionFactory().openSession();
	}
}
