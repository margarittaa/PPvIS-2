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
		
	public static void mainDOMwriter(File file) throws ParserConfigurationException, TransformerFactoryConfigurationError, TransformerException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		
		Element list = document.createElement("list");
		document.appendChild(list);
		
		Data.sportsmenList.forEach((s)->{			 
		 
			Element sportsman = document.createElement("sportsman");
			list.appendChild(sportsman);
		
			Element fio = document.createElement("fio"); 
			fio.setTextContent(s.getFIO());
			sportsman.appendChild(fio);
			
			Element composition = document.createElement("composition"); 
			composition.setTextContent(s.getComposition());
			sportsman.appendChild(composition);
			
			Element position = document.createElement("position"); 
			position.setTextContent(s.getPosition());
			sportsman.appendChild(position);
		
			Element titles = document.createElement("titles");
			titles.setTextContent(String.valueOf(s.getTitles()));
			sportsman.appendChild(titles);
					
			Element sport = document.createElement("sport");
			sport.setTextContent(s.getSport());
			sportsman.appendChild(sport);

			Element category = document.createElement("category"); 
			category.setTextContent(s.getCategory());
			sportsman.appendChild(category);
		
		 });
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer(); 
		DOMSource source = new DOMSource(document); 
		StreamResult result = new StreamResult(file);
		transformer.transform(source, result);
		System.out.println("Document " + file.getName() +  " saved");
		
	}
	
}

