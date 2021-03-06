package webservices.xml.testeXML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import webservices.xml.manipulandoXML.ManipulandoXML;
import webservices.xml.util.CEP;

public class TesteManipulacao {

	public static void main(String[] args) {

		Document documento = ManipulandoXML.recuperaConteudo("src/../documentos/biblioteca.xml");
		
		Element pai;
		NodeList livros;
		NodeList filhos;
		

		System.out.println("# Obtendo a raiz");
		pai = documento.getDocumentElement();
		System.out.println(pai.getNodeName()+"\n");

		System.out.println("# Tag da raiz");
		System.out.println(pai.getTagName() + "\n");

		System.out.println("# Conteúdo textual da raiz");
		System.out.println(pai.getTextContent() + "\n");

		System.out.println("# Obtém os elementos filhos da raiz");
		filhos = pai.getChildNodes();
		System.out.println("Número de filhos: " + filhos.getLength() + "\n");


		System.out.println("# Busca um elemento filho específico");
		filhos = pai.getElementsByTagName("endereco");
		System.out.println(filhos.item(0).getTextContent() + "\n");

		System.out.println("# Obtém o índice de um elemento filho");
		System.out.println(pai.compareDocumentPosition(filhos.item(0)) + "\n");

		
		System.out.println("# Obtendo os atributos de um elemento");
		NamedNodeMap atributos = filhos.item(0).getAttributes();
		System.out.println(atributos.getNamedItem("cep")+"\n");
		
		System.out.println("# Contando o total de livros da biblioteca");
		livros = documento.getElementsByTagName("livro");
		Element livroE;
		
		int qtd = 0;
		for (int i = 0; i < livros.getLength();i++) {
			livroE = (Element)livros.item(i);
			qtd+=Integer.parseInt(livroE.getAttribute("quantidade"));
		}
		System.out.println(qtd+"\n");
		
		
		System.out.println("# Listando o nome dos livros");
		livros = documento.getElementsByTagName("titulo");
		for (int i = 0; i < livros.getLength();i++) {
			livroE = (Element)livros.item(i);
			System.out.println(livroE.getTextContent());
		}
		
		
		System.out.println("\n# Consultando o endereço completo da biblioteca a partir do CEP");
		try {
			CEP.consultaCEP(atributos.getNamedItem("cep").getTextContent());
		} catch (Exception e) {
		}
	}
	

}
