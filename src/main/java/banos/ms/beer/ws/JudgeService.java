package banos.ms.beer.ws;

import javax.ws.rs.Path;

import banos.ms.beer.Judge;

/**
 * Service layer to access judge information.
 * @author mike
 *
 */
@Path("/judges")
public class JudgeService extends Service<Judge> {
	/**
	 * Get the managed type's class.
	 * @return The managed type's class.
	 */
	@Override
	protected Class<Judge> getEntityClass() {
		return Judge.class;
	}
}
