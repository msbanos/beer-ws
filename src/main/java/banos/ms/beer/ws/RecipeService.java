package banos.ms.beer.ws;

import javax.ws.rs.Path;

import banos.ms.beer.Recipe;

/**
 * [Author]			Mike Banos
 * [Date]			10/03/2018
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access recipe information.
 */
@Path("/recipes")
public class RecipeService extends Service<Recipe> {
	/**
	 * Get the entity class.
	 * @return The entity class.
	 */
	@Override
	protected Class<Recipe> getEntityClass() {
		return Recipe.class;
	}
}
