package webservices.xml.testeXML;

import webservices.xml.validandoXML.ValidandoXML;

public class TesteValidacao {
	
	
	public static void main(String[] args) {
		
		System.out.println("Valida��o com DTD: "+ValidandoXML.validaComDTD("src/../documentos/biblioteca.xml"));
	
		System.out.println("Valida��o com XSD: "+ValidandoXML.validaComXSD("src/../documentos/biblioteca.xml", "src/../documentos/biblioteca.xsd"));
		
		
	}
	
	
}
