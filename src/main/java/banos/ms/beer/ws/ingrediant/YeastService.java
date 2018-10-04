package banos.ms.beer.ws.ingrediant;

import javax.ws.rs.Path;

import banos.ms.beer.ingrediant.Yeast;
import banos.ms.beer.ws.Service;

/**
 * [Author]			Mike Banos
 * [Date]			10/04/2018
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access yeast strain information.
 */
@Path("/yeast")
public class YeastService extends Service<Yeast> {
	/**
	 * Get the entity class.
	 * @return The entity class.
	 */
	@Override
	protected Class<Yeast> getEntityClass() {
		return Yeast.class;
	}
}
