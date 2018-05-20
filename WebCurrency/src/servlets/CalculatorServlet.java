package servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import calculator.Currency;
import calculator.Table;
import dao.TableDao;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		TableDao tableDao = new TableDao();
		Table currenciesTable = tableDao.getRates(LocalDate.now().toString());

		Collection<Currency> currencies = currenciesTable.getAllCurrencies();
		
		request.setAttribute("currencies", currencies);
		request.getRequestDispatcher("WEB-INF/view/calculator.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		
		//TODO
		// Exceptions
		
		TableDao tableDao = new TableDao();
		Table currenciesTable = tableDao.getRates(LocalDate.now().toString());
		Currency currencyFrom = currenciesTable.findCurrency(request.getParameter("setCurrencyFrom"));
		Currency currencyTo = currenciesTable.findCurrency(request.getParameter("setCurrencyTo"));
		BigDecimal amount = new BigDecimal(request.getParameter("setAmount"))
				.multiply(currencyFrom.getCurrencyRate()
						.divide(currencyTo.getCurrencyRate(), 4, RoundingMode.HALF_UP));
		
		request.setAttribute("amount", amount);
		doGet(request, response);
	}

}
