package banos.ms.beer.ws;

import javax.servlet.ServletException;
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
 */
public class Service {
	/**
	 * Get the serialized version of a list of objects.
	 * @param query The query to retrieve the instances.
	 * @return The serialized objects.
	 * @throws JsonProcessingException
	 */
	protected static String getListJson(final String query) throws JsonProcessingException {
		final StandardServiceRegistry registry = getRegistry();
		final Session session = getSession(registry);
		
		String json;
		try {
			json = JSONUtils.toJson(session.createQuery(query).list());
		} finally {
			session.close();
			StandardServiceRegistryBuilder.destroy(registry);	
		}
		
		return json;
	}
	
	/**
	 * Get the serialized version of a single object.
	 * @param query The query to retrieve an instance.
	 * @return The serialized object.
	 * @throws JsonProcessingException
	 */
	protected static String getSingletonJson(final String query) throws JsonProcessingException {
		final StandardServiceRegistry registry = getRegistry();
		final Session session = getSession(registry);
		
		String json;
		try {
			json = JSONUtils.toJson(session.createQuery(query).getSingleResult());
		} finally {
			session.close();
			StandardServiceRegistryBuilder.destroy(registry);
		}
		
		return json;
	}
	
	/**
	 * Save a single instance.
	 * @param item The item instance to save.
	 * @throws Exception 
	 */
	protected static<T> void saveSingleton(final T item) throws Exception {
		final StandardServiceRegistry registry = getRegistry();
		final Session session = getSession(registry);
		
		try {
			session.beginTransaction();
			session.save(item);
			session.getTransaction().commit();
		} finally {
			session.close();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	/**
	 * Update a single instance.
	 * @param item The item instance to save.
	 * @throws Exception 
	 */
	protected static<T> void updateSingleton(final T item) throws Exception {
		final StandardServiceRegistry registry = getRegistry();
		final Session session = getSession(registry);
		
		try {
			session.beginTransaction();
			session.update(item);
			session.getTransaction().commit();
		} finally {
			session.close();
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
	
	/**
	 * Create a new database instance.
	 * @param item The instance to create in the database.
	 * @return The post response.
	 * @throws ServletException
	 */
	protected static<T> Response create(final T item) throws ServletException {
		try {
			saveSingleton(item);
			
			return Response.status(Response.Status.CREATED.getStatusCode()).build();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
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
