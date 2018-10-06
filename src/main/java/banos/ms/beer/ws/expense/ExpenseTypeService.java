package banos.ms.beer.ws.expense;

import javax.ws.rs.Path;

import banos.ms.beer.expense.ExpenseType;
import banos.ms.beer.ws.Service;

/**
 * [Author]			Mike Banos
 * [Date]			12/14/2017
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access expense types.
 */
@Path("/expense_types")
public class ExpenseTypeService extends Service<ExpenseType> {
	/**
	 * Get the managed type's class.
	 * @return The managed type's class.
	 */
	@Override
	protected Class<ExpenseType> getEntityClass() {
		return ExpenseType.class;
	}
}
