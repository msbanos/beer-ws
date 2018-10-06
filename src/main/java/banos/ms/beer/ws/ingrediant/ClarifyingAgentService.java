package banos.ms.beer.ws.ingrediant;

import javax.ws.rs.Path;

import banos.ms.beer.ingrediant.ClarifyingAgent;
import banos.ms.beer.ws.Service;

/**
 * [Author]			Mike Banos
 * [Date]			12/14/2017
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access wort clarifying agent information.
 */
@Path("/clarifiers")
public class ClarifyingAgentService extends Service<ClarifyingAgent> {
	/**
	 * Get the managed type's class.
	 * @return The managed type's class.
	 */
	@Override
	protected Class<ClarifyingAgent> getEntityClass() {
		return ClarifyingAgent.class;
	}
}
