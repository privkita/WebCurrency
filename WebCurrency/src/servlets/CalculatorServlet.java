package servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
		// Map<String, Currency> currencies = table.getCurrencies();
		// ArrayList<Currency> currencies = (ArrayList<Currency>) table.getAllCurrencies();
		Collection<Currency> currencies = currenciesTable.getAllCurrencies();
		
		System.out.println("wypisujemy waluty");
		System.out.println("ilość walut: " + currencies.size());
		for (Currency currency : currencies) {
			System.out.println(currency.getCurrencyName());
		}
		
		request.setAttribute("currencies", currencies);

		
		request.getRequestDispatcher("WEB-INF/view/calculator.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
