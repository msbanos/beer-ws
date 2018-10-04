package banos.ms.beer.ws.ingrediant;

import javax.ws.rs.Path;

import banos.ms.beer.ingrediant.Hop;
import banos.ms.beer.ws.Service;

/**
 * [Author]			Mike Banos
 * [Date]			12/14/2017
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access hop information.
 */
@Path("/hops")
public class HopService extends Service<Hop> {
	/**
	 * Get the entity class.
	 * @return The entity class.
	 */
	protected Class<Hop> getEntityClass() {
		return Hop.class;
	}
}
