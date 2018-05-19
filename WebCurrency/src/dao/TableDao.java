package dao;

import java.time.LocalDate;

import calculator.Table;

public class TableDao {
	
	private Table table;
	
	public Table getRates(String date) {
	    	
		LocalDate givenDate = LocalDate.parse(date);
		table = NBPHandler.getTable(givenDate.toString());
		while (table == null) {
			table = NBPHandler.getTable(givenDate.toString());
			givenDate = givenDate.minusDays(1);
		}
		return table;
	}

}

