package tsi.webservices.consumodeapi.cep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.google.gson.Gson;

public class ConsumoCEP {
	
	public static void main (String[] args) {
		
		
		try {
			
			Scanner entrada = new Scanner(System.in);			
			
			while(true) {
				
				System.out.println("\nDigite um cep ou \"exit\" para sair:");
				
				String cep = entrada.nextLine();
				
				if(cep.equalsIgnoreCase("exit")) {
					entrada.close();
					break;
				}
				
				if(cep.matches("\\d{8}")) {
					
					URL url = new URL("https://viacep.com.br/ws/"+cep+"/json");
					URLConnection connection = url.openConnection();
					BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					
					Gson gson = new Gson();
					
					CEP teste = gson.fromJson(input, CEP.class);
					
					if(teste.cep==null)
						System.out.println("Cep inválido");
					else
						System.out.println("\n"+teste);	
				}
				else
					System.out.println("Digite apenas 8 números!!");
				
			}
			
		}catch(IOException e) {
			System.out.println("Deu ruim");
		}
	
	
	}
		
	public class CEP {
		private String cep;
		private String lagradouro;
		private String complemento;
		private String bairro;
		private String localidade;
		private String uf;
		private String ibge;
		private String gia;
		private String ddd;
		private String siafi;
		
		public CEP(String cep, String lagradouro, String complemento, String bairro, String localidade, String uf,
				String ibge, String gia, String ddd, String siafi) {
			this.cep = cep;
			this.lagradouro = lagradouro;
			this.complemento = complemento;
			this.bairro = bairro;
			this.localidade = localidade;
			this.uf = uf;
			this.ibge = ibge;
			this.gia = gia;
			this.ddd = ddd;
			this.siafi = siafi;
		}

		@Override
		public String toString() {
			return "Cep: " + cep + "\nlagradouro: " + lagradouro + "\ncomplemento: " + complemento + "\nbairro: "
					+ bairro + "\nlocalidade: " + localidade + "\nuf: " + uf + "\nibge: " + ibge + "\ngia: " + gia
					+ "\nddd: " + ddd + "\nsiafi: " + siafi;
		}
		
	}
}
