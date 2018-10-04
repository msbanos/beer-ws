package banos.ms.beer.ws.ingrediant;

import javax.ws.rs.Path;

import banos.ms.beer.ingrediant.Grain;
import banos.ms.beer.ws.Service;

/**
 * [Author]			Mike Banos
 * [Date]			12/14/2017
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access list of grains.
 */
@Path("/grains")
public class GrainService extends Service<Grain> {
	/**
	 * Get the entity class.
	 * @return The entity class.
	 */
	@Override
	protected Class<Grain> getEntityClass() {
		return Grain.class;
	}
}
