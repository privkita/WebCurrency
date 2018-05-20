package calculator;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import calculator.Currency;

	//TODO
	// Change getters ands setters for Map

public class Table {

	private String tableNumber;
	private LocalDate tableDate;
	private Map<String, Currency> currencies = new TreeMap<String, Currency>();

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public LocalDate getTableDate() {
		return tableDate;
	}

	public void setTableDate(LocalDate tableDate) {
		this.tableDate = tableDate;
	}

	public Map<String, Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Map<String, Currency> currencies) {
		this.currencies = currencies;
	}
	
	public void addCurrency(Currency currency) {
		currencies.put(currency.getCurrencyCode(), currency);
	}
	
	public Currency findCurrency(String code) {
		return currencies.get(code);
	}
	
	public Collection<Currency> getAllCurrencies() {
		return currencies.values();
	}

}
