package model;

import java.io.File; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.ParserConfigurationException; 
import javax.xml.transform.Transformer; 
import javax.xml.transform.TransformerException; 
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult; 
import org.w3c.dom.Attr; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element;

public class DOMwriter{
	
	private static String fileName = "list.xml";
	
	public static void mainDOMwriter() throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		
		Element list = document.createElement("list");
		document.appendChild(list);
		
		 Data.sportsmenList.forEach((s)->{
			 
		 
		Element sportsman = document.createElement("sportsman");
		list.appendChild(sportsman);
		
		Attr id = document.createAttribute("id");
		id.setTextContent("1");
		sportsman.setAttributeNode(id);
		
		Element fio = document.createElement("fio"); 
		fio.setTextContent(s.getFIO());
		sportsman.appendChild(fio);
		
		Element titles = document.createElement("titles");
		titles.setTextContent(String.valueOf(s.getTitles()));
		sportsman.appendChild(titles);
		
		Element sport = document.createElement("sport");
		sport.setTextContent(s.getSport());
		sportsman.appendChild(sport);
		
		 });
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer(); 
		DOMSource source = new DOMSource(document); 
		StreamResult result = new StreamResult(
				new File(System.getProperty("user.dir") + File.separator + fileName));
				transformer.transform(source, result);
				System.out.println("Document " + fileName +  " saved");
		
	}
	
}
