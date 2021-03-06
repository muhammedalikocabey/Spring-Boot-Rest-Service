package com.makocabey.rest;



import org.springframework.stereotype.Component;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;



@Component
public class XMLService {
			
	public List<Parity> parseAndSaveParityData() 
			throws Exception { 
		
		
		List<Parity> listOfParity = new ArrayList<>();
		
		try {
			String URL = "https://www.tcmb.gov.tr/kurlar/today.xml";
					
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			Document doc = builder.parse(URL);
			
			
			doc.getDocumentElement().normalize();
			
			
			String sDate = doc.getElementsByTagName("Tarih_Date").item(0).getAttributes().getNamedItem("Date").getNodeValue();
			
			
			DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			
			LocalDate date = LocalDate.parse(sDate, format);
			
			
			NodeList parityNodeList = doc.getElementsByTagName("Currency");
			
			
			for(int i = 0; i < parityNodeList.getLength(); i++) {
				Node node = parityNodeList.item(i);
				
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element) node;
					
					
					String parityCode = (elem.getAttribute("CurrencyCode") + "TRY");
					
					if (parityCode.equalsIgnoreCase("XDRTRY")) {
						continue;
					}
					
					Integer unit = Integer.parseInt(elem.getElementsByTagName("Unit").item(0).getTextContent());
					
					Double purchasePrice = Double.parseDouble(elem.getElementsByTagName("ForexBuying").item(0).getTextContent());
					purchasePrice /= unit;
					
					Double salePrice = Double.parseDouble(elem.getElementsByTagName("ForexSelling").item(0).getTextContent());
					salePrice /= unit;
					
					Double averagePrice = (purchasePrice + salePrice) / 2;
					
					
					Parity parity = new Parity(parityCode, date, purchasePrice, salePrice, averagePrice);
					
					listOfParity.add(parity);
				}
				
			}
			
			
		}
		
		catch (Exception e) {
			throw e;
		}
		
		return listOfParity;
	}
	
}
