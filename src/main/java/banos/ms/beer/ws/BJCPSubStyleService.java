package banos.ms.beer.ws;

import javax.ws.rs.Path;

import banos.ms.beer.BJCPSubStyle;

/**
 * [Author]			Mike Banos
 * [Date]			10/10/2018
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access BJCP sub-style information.
 */
@Path("/substyles")
public class BJCPSubStyleService extends Service<BJCPSubStyle> {
	/**
	 * Get the managed type's class.
	 * @return The managed type's class.
	 */
	@Override
	protected Class<BJCPSubStyle> getEntityClass() {
		return BJCPSubStyle.class;
	}
}
