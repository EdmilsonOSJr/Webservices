package tsi.webservices.comunicacaoSocket.modelos;

public class Aluno {

	private static int id = 0;

	private int idAluno;
	private String nome;
	private int matriculado;

	public Aluno(String nome, int matriculado) {
		id++;
		this.idAluno = id;
		this.nome = nome;
		this.matriculado = matriculado;
	}

	public int getId() {
		return idAluno;
	}

	public void setId(int idAluno) {
		this.idAluno = idAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int isMatriculado() {
		return matriculado;
	}

	public void setMatriculado(int matriculado) {
		this.matriculado = matriculado;
	}

	@Override
	public String toString() {
		return idAluno + " " + nome + " " + matriculado;
	}
}
