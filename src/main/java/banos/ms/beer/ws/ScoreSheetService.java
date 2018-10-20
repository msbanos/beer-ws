package banos.ms.beer.ws;

import javax.ws.rs.Path;

import banos.ms.beer.ScoreSheet;

/**
 * [Author]			Mike Banos
 * [Date]			10/20/2018
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access score sheets.
 */
@Path("/scoresheets")
public class ScoreSheetService extends Service<ScoreSheet> {
	/**
	 * Get the entity class.
	 * @return The entity class.
	 */
	@Override
	protected Class<ScoreSheet> getEntityClass() {
		return ScoreSheet.class;
	}
}
