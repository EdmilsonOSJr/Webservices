package webservices.xml.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CEP {

	public static String consultaCEP(String cep) throws Exception {

		Map<Object, Object> cepMap = new HashMap<Object, Object>();
		URL url;
		URLConnection connection;
		BufferedReader input;
		Gson gson;

		if (cep.matches("\\d{8}")) {
			url = new URL("https://viacep.com.br/ws/" + cep + "/json");
			connection = url.openConnection();
			input = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

			gson = new Gson();

			cepMap = gson.fromJson(input, new TypeToken<Map<Object, Object>>() {
			}.getType());

			if (cepMap.containsKey("erro"))
				System.out.println("CEP fornecido não existe!!!");
			else
				for (Map.Entry<Object, Object> pair : cepMap.entrySet()) {
					System.out.println(String.format("%s : %s", pair.getKey(),
							pair.getValue().toString().isEmpty() ? "Não fornecido" : pair.getValue()));
				}

			return String.format("Rua: %s\nNúmero: %s\nComplemento: %s\nBairro: %s\nCidade: %s\nEstado: %s",
					cepMap.get("rua"), cepMap.get("complemento"), cepMap.get("bairro"), cepMap.get("cidadde"),
					cepMap.get("estado"));
		} else
			return "cep inválido";

	}

}
