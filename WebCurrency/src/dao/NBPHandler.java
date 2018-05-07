package dao;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import calculator.Currency;
import calculator.Table;

public class NBPHandler {
	
	public static Table getTable(String data) {
		Document doc = readXML("http://api.nbp.pl/api/exchangerates/tables/A/" + data + "/?format=xml");
		return tableFromXml(doc);
	}
	
	public static Document readXML(String adress) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
		
			URL url = new URL(adress);
			try(InputStream in = url.openStream()) {
				return db.parse(in);
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
			return null;
		}		
	}
	private static Table tableFromXml(Document doc) {
		
		XPathFactory xpf = XPathFactory.newInstance();
		XPath xpath = xpf.newXPath();
		
		try {
			String tableNumber = xpath.evaluate("//No", doc);
			LocalDate tableDate = LocalDate.parse(xpath.evaluate("//EffectiveDate", doc));
			
			Table currenciesTable = new Table();
			currenciesTable.setTableNumber(tableNumber);
			currenciesTable.setTableDate(tableDate);
			
			NodeList rates = (NodeList) xpath.evaluate("//Rate", doc, XPathConstants.NODESET);
			for(int i = 0; i < rates.getLength(); i++) {
				Node rate = rates.item(i);
				Currency currency = new Currency();
				currency.setCurrencyName(xpath.evaluate("Currency", rate));
				currency.setCurrencyCode(xpath.evaluate("Code", rate));
				currency.setCurrencyRate(new BigDecimal(xpath.evaluate("Mid", rate)));
				currenciesTable.addCurrency(currency);				
			}
			return currenciesTable;
			
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
