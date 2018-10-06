package banos.ms.beer.ws.expense;

import javax.ws.rs.Path;

import banos.ms.beer.expense.Expense;
import banos.ms.beer.ws.Service;

/**
 * [Author]			Mike Banos
 * [Date]			12/14/2017
 * [Licensing ]		All works licensed by the Lesser GPL
 * 		@link http://www.gnu.org/licenses/lgpl.html
 * [Summary]		Service layer to access expenses.
 */
@Path("/expenses")
public class ExpenseService extends Service<Expense> {
	/**
	 * Get the class of the type being managed.
	 * @return The managed type class.
	 */
	@Override
	protected Class<Expense> getEntityClass() {
		return Expense.class;
	}
}
