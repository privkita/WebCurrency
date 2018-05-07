package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import calculator.Table;
import dao.NBPHandler;

class NBPHandlerTest {

	@Test
	void test() {		
		String data = "2018-03-20";
		String numberExp = "056/A/NBP/2018";
		LocalDate dateExp = LocalDate.parse("2018-03-20");
		int sizeExp = 35;
		
		Table table = NBPHandler.getTable(data);
		
		assertEquals(numberExp, table.getTableNumber());
		assertEquals(dateExp, table.getTableDate());
		assertEquals(sizeExp, table.getAllCurrencies().size());
	}

}
