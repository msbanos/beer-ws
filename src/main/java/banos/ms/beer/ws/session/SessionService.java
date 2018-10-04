package banos.ms.beer.ws.session;

import javax.ws.rs.Path;

import banos.ms.beer.session.BrewSession;
import banos.ms.beer.ws.Service;

/**
 * [Author]			Mike Banos
 * [Date]			12/14/2017
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer for brew sessions.
 */
@Path("/brew_sessions")
public class SessionService extends Service<BrewSession> {
	/**
	 * Get the entity class.
	 * @return The entity class.
	 */
	protected Class<BrewSession> getEntityClass() {
		return BrewSession.class;
	}
}
