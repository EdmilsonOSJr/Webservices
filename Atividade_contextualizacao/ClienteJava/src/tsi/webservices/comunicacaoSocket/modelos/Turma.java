package tsi.webservices.comunicacaoSocket.modelos;

import java.util.ArrayList;
import java.util.List;

public class Turma {

	private static int id = 0;

	private int idTurma;
	private int ano;
	private String curso;
	private List<Aluno> alunos;

	public Turma(int ano, String curso) {
		id++;
		this.idTurma = id;
		this.ano = ano;
		this.curso = curso;
		this.alunos = new ArrayList<Aluno>();
	}

	public int getId() {
		return idTurma;
	}

	public void setId(int idTurma) {
		this.idTurma = idTurma;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public void adicionarAluno(Aluno aluno) {
		alunos.add(aluno);
	}

	@Override
	public String toString() {
		return "Turma: id= " + idTurma + " ano= " + ano + ", curso= " + curso + ", alunos= " + alunos;
	}

}
