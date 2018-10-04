package banos.ms.beer.ws.ingrediant;

import javax.ws.rs.Path;

import banos.ms.beer.ingrediant.YeastBrand;
import banos.ms.beer.ws.Service;

/**
 * [Author]			Mike Banos
 * [Date]			10/04/2018
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access yeast brand information.
 */
@Path("/yeast_brands")
public class YeastBrandService extends Service<YeastBrand> {
	/**
	 * Get the entity class.
	 * @return The entity class.
	 */
	@Override
	protected Class<YeastBrand> getEntityClass() {
		return YeastBrand.class;
	}
}
