package tsi.webservices.consumodeapi.git;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class ConsumoGit {

	public static void main(String[] args) {
		String usuario;
		try {
			
			Scanner input = new Scanner(System.in);			
			
			while(true) {
				
				System.out.println("\nDigite o nome de um usuário do GitHub ou \"exit\" para sair:");
				
				usuario = input.nextLine();
				
				if(usuario.equalsIgnoreCase("exit")) {
					input.close();
					break;
				}
				
				URL url = new URL("https://api.github.com/users/"+usuario+"/followers");
				URLConnection connection = url.openConnection();
				BufferedReader entrada = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				Gson gson = new Gson();
				
				
				List<Map<String, String>> map = gson.fromJson(entrada, new TypeToken<List<Map<String, String>>>() {}.getType());
				entrada.close();
				
				
				System.out.println("Número de seguidores: "+map.size());
				
				for(Map<String, String> follower : map) {
					
					System.out.println("Nome do seguidor: "+follower.get("login"));
					
					url = new URL(" https://api.github.com/users/"+follower.get("login")+"/repos?type=owner\r\n");
					connection = url.openConnection();
					entrada = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					
					List<Map<Object, Object>> map2 = gson.fromJson(entrada, new TypeToken<List<Map<Object, Object>>>() {}.getType());
					entrada.close();
					
					for(Map<Object, Object> repos : map2) {
						System.out.println("\tRepositório: "+repos.get("name").toString());
					}
				}
			}
			
			//entrada.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
