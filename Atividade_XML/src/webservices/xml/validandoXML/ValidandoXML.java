package webservices.xml.validandoXML;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class ValidandoXML {

	public static String validaComDTD(String nomeArq) {
		
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();

			builder.parse(new File(nomeArq));

			return "Válido";

		} catch (Exception e) {
			return "Inválido";
		}
	}

	
	public static String validaComXSD(String nomeArqXml, String nomeArqXSD) {
		try {
		
			File schemaFile = new File(nomeArqXSD) ;
			Source xmlFile = new StreamSource(new File(nomeArqXml));
		
	
	        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
	        Schema schema = schemaFactory.newSchema(schemaFile);
	        Validator validator = schema.newValidator();

            validator.validate(xmlFile);
            
            return "Válido";
           
        } catch (SAXException|IOException e) {
            return  "Inválido";
		}
	}
}
