package banos.ms.beer.ws;

import javax.ws.rs.Path;

import banos.ms.beer.BJCPStyle;

/**
 * [Author]			Mike Banos
 * [Date]			12/14/2017
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access BJCP style information.
 */
@Path("/styles")
public class BJCPStyleService extends Service<BJCPStyle> {
	/**
	 * Get the managed type's class.
	 * @return The managed type's class.
	 */
	@Override
	protected Class<BJCPStyle> getEntityClass() {
		return BJCPStyle.class;
	}
}
