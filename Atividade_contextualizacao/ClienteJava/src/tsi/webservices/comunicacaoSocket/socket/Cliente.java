package tsi.webservices.comunicacaoSocket.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import tsi.webservices.comunicacaoSocket.modelos.Aluno;
import tsi.webservices.comunicacaoSocket.modelos.Turma;

public class Cliente {

	private static Socket client;
	private int port;
	private String adress;
	private List<Turma> turmas;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public List<Turma> getTurmas() {
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public Cliente(List<Turma> turmas) {
		this.port = 12345;
		this.adress = "127.0.0.1";
		this.turmas = turmas;
	}

	public void initConnection() throws UnknownHostException, IOException {

		client = new Socket("127.0.0.1", 12345);
		OutputStream saida = client.getOutputStream();
		BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
	
		
		for (Turma t : turmas) {
			System.out.println(t);
			
			saida.write(String.format("%d!!%d!!%s!!%d!!%d", 1, t.getId(), t.getCurso(), t.getAno(), t.getAlunos().size()).getBytes("UTF-8"));
			saida.flush();
			input.readLine();
			
			for (Aluno a : t.getAlunos()) {
				saida.write(String.format("%d!!%d!!%s!!%d", 2, a.getId(), a.getNome(), a.isMatriculado()).getBytes("UTF-8"));
				saida.flush();
				input.readLine();
			}
		
			

		}
		
		saida.write("".getBytes("UTF-8"));

		saida.close();
		client.close();

	}

}
