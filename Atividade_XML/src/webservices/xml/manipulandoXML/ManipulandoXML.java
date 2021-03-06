package webservices.xml.manipulandoXML;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ManipulandoXML {

	public static Document recuperaConteudo(String nomeArq){

		try {

			DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = fabrica.newDocumentBuilder();
			Document document = builder.parse(nomeArq);
			return document;
			
		} catch (ParserConfigurationException|IOException|SAXException e) {
			e.printStackTrace();

		} 
		
		return null;
	}

}
