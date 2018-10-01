package banos.ms.beer.ws.ingrediant;

import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;

import banos.ms.beer.ingrediant.Grain;
import banos.ms.beer.ws.Service;
import banos.ms.utils.JSONUtils;

/**
 * [Author]			Mike Banos
 * [Date]			12/14/2017
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access list of grains.
 */
@Path("/grains")
public class GrainService extends Service {
	/**
	 * GET call handler to retrieve list of all grains.
	 * @return The serialized grain list.
	 * @throws JsonProcessingException
	 */
	@GET
    @Produces("text/plain")
	public String getGrains() throws JsonProcessingException {
		return getListJson(getAllQuery);
	}
	
	/**
	 * GET call handler to retrieve an individual grain.
	 * @return The serialized grain.
	 * @throws JsonProcessingException
	 */
	@GET
	@Path("{id : \\d+}")
    @Produces("text/plain")
	public String getGrain(@PathParam("id") String id) throws JsonProcessingException {
		return getSingletonJson(getSingletonTemplate + id);
	}
	
	/**
	 * Post request handler.
	 * @param message The form values.
	 * @return The post response. 
	 * @throws ServletException 
	 */
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response doPost(final String message) throws ServletException {
		try {
			Grain grain = JSONUtils.fromJson(message, Grain.class);
			
			saveSingleton(grain);
			
			return Response.status(Response.Status.CREATED.getStatusCode()).build();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
	private static final String getAllQuery = "from Grain ORDER BY name";
	private static final String getSingletonTemplate = "from Grain WHERE id = ";
}
