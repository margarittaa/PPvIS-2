package model;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXparser extends DefaultHandler{
	
	static File file = new File("src/model/list.xml");
	
	public static void mainSAXParser() throws ParserConfigurationException, SAXException, IOException{
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser = factory.newSAXParser();
		SAXparser saxp = new SAXparser();

		parser.parse(file, saxp);				
	}
	
	Sportsman man;
	String thisElement = ""; 

	@Override
	public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
	  if(qName.equals("sportsman"))
		  man = new Sportsman(); 
	  thisElement = qName;
	}

	@Override
	public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
	  if(qName.equals("sportsman"))
		  Data.sportsmenList.add(man);
	  thisElement = "";
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
	  if (thisElement.equals("fio")) {
		  man.setFIO(new String(new String(ch, start, length)));
	  }
	  if (thisElement.equals("composition")) {
		  man.setComposition(new String(ch, start, length));
	  }
	  if (thisElement.equals("position")) {
		  man.setPosition(new String(ch, start, length));
	  }
	  if (thisElement.equals("titles")) {
		  man.setTitles(Integer.parseInt(new String(ch, start, length)));
	  }
	  if (thisElement.equals("sport")) {
		  man.setSport(new String(ch, start, length));
	  }
	  if (thisElement.equals("category")) {
		  man.setCategory(new String(ch, start, length));
	  }
	  
	}

	@Override
	public void startDocument() throws SAXException {
	  System.out.println("Start parsing XML");
	}
	
	@Override
	public void endDocument() {
	  System.out.println("Stop parsing");
	}
}


