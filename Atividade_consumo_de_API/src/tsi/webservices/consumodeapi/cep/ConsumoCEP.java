package tsi.webservices.consumodeapi.cep;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ConsumoCEP {
	
	public static void main (String[] args) {
		
		Map <Object,Object> cepMap = new HashMap <Object, Object>();
		URL url;
		URLConnection connection;
		BufferedReader input;
		Gson gson;
		String cep;
		
		try (Scanner entrada = new Scanner(System.in)){
			
			while(true) {
				
				System.out.println("\nDigite um cep ou \"exit\" para sair:");
				cep = entrada.nextLine();
				
				if(cep.equalsIgnoreCase("exit")) {
					entrada.close();
					break;
				}
				
				if(cep.matches("\\d{8}")) {
					url = new URL("https://viacep.com.br/ws/"+cep+"/json");
					connection = url.openConnection();
					input = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
					
					gson = new Gson();
					
					cepMap = gson.fromJson(input, new TypeToken<Map<Object, Object>>() {}.getType());
					
					if(cepMap.containsKey("erro"))
						System.out.println("CEP fornecido não existe!!!");
					else					
						for (Map.Entry<Object,Object> pair : cepMap.entrySet()) {
							System.out.println(String.format("%s : %s",pair.getKey(), pair.getValue().toString().isEmpty()?"Não fornecido":pair.getValue()));
						}
				}
				else
					System.out.println("Valor inválido!!");
			}
			
		}catch(Exception e) {
			System.out.println("Ocorreu um erro na consulta.");
		}
	
	
	}
}
