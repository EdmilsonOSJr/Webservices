package tsi.webservices.consumodeapi.git;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ConsumoGit {

	private Gson gson = new Gson();
	private URL url;
	private URLConnection connection;
	private String urlUsuario, urlFollowers, urlRepos;
	
	Map <Object,Map<String, String>> usuarios = new HashMap<Object, Map<String, String>>();// map usado para guardar os usuario
	Map <Object,List<Map<Object, Object>>> dados = new HashMap<Object, List<Map<Object, Object>>>();// map usado para guardar os followers e seus repositorios
	
	public static void main(String[] args) {
		new ConsumoGit();
	}

	public ConsumoGit() {
		inicia();
	}
	
	public void inicia() {
		try(Scanner input = new Scanner(System.in)) {
			
			while(true) {
				
				System.out.println("\nDigite o nome de um usuário do GitHub ou \"exit\" para sair:");
				String usuario = input.nextLine();
				
				if(usuario.equalsIgnoreCase("exit")) {
					input.close();
					break;
				}
				
				urlUsuario = "https://api.github.com/users/"+usuario;
				confereBuffer(urlUsuario, usuarios); 
				
				urlFollowers = usuarios.get(urlUsuario).get("followers_url");
				confereBuffer(urlFollowers, dados);
				
				System.out.println("\nUsuário da consulta: "+usuarios.get(urlUsuario).get("name"));
				System.out.println("\nNúmero de seguidores: "+dados.get(urlFollowers).size());
				for(Map<Object, Object> follower : dados.get(urlFollowers)) {
					
					urlUsuario = "https://api.github.com/users/"+follower.get("login");
					confereBuffer(urlUsuario,usuarios);
					
					urlRepos = "https://api.github.com/users/"+follower.get("login")+"/repos?type=owner\r\n";
					confereBuffer(urlRepos,dados);
					
					System.out.println("\nNome do seguidor: "+usuarios.get(urlUsuario).get("name"));
					for(Map<Object, Object> repos : dados.get(urlRepos)) {
						System.out.println("\tRepositório: "+repos.get("name").toString());
					}
				}
			}// while(true)
		} catch (FileNotFoundException e) {
			System.out.println("\nUsuário não existe.");
		}catch (IOException e) {
			System.err.println("\nNúmero de tentativas excedidas.");
		}
	}
	
	
	// Confere se a url fornecida já foi procurada. Caso não tenha sido ela e seu retorno são adicionados ao seu respectivo Map.
	private <E> void confereBuffer(String chave, Map<Object,E> dados) throws IOException {
		
		if(!dados.containsKey(chave)) {
			url = new URL(chave);
			connection = url.openConnection();
			try(BufferedReader entrada = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
				
				if(chave.contains("repos") || chave.contains("followers")) 
					dados.put(chave, gson.fromJson(entrada, new TypeToken<List<Map<Object, Object>>>() {}.getType()));					
				else 
					dados.put(chave,gson.fromJson(entrada, new TypeToken<Map<String, String>>() {}.getType()));
			}
		}
		
	}
	
}
