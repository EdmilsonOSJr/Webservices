package tsi.webservices.comunicacaoSocket.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class AlunoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Aluno> alunos;

	private String[] colunas = new String[] { "Id", "nome", "Matriculado" };

	/** Creates a new instance of DevmediaTableModel */
	public AlunoTableModel(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public AlunoTableModel() {
		this.alunos = new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return alunos.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return colunas[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return String.class;
	}

	public void setValueAt(Aluno aValue, int rowIndex) {
		Aluno aluno = alunos.get(rowIndex);

		aluno.setId(aValue.getId());
		aluno.setNome(aValue.getNome());
		aluno.setMatriculado(aValue.isMatriculado());

		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Aluno aluno = alunos.get(rowIndex);

		try {
			switch (columnIndex) {
			case 0:
				aluno.setId(Integer.parseInt(aValue.toString()));break;
			case 1:
				aluno.setNome(aValue.toString());break;
			case 2:
				aluno.setMatriculado(Integer.parseInt(aValue.toString()));break;
			}
			fireTableCellUpdated(rowIndex, columnIndex);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Tipo de dado inconpatível com o campo", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Aluno alunoSelecionada = alunos.get(rowIndex);

		String valueObject = null;

		switch (columnIndex) {
		case 0:
			valueObject = Integer.toString(alunoSelecionada.getId());
			break;
		case 1:
			valueObject = (alunoSelecionada.getNome());
			break;
		case 2:
			valueObject = Integer.toString(alunoSelecionada.isMatriculado());
			break;
		default:
			System.err.println("Índice inválido para propriedade do bean Usuario.class");
		}

		return valueObject;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return columnIndex != 0;
	}

	public Aluno getAluno(int indiceLinha) {
		return alunos.get(indiceLinha);
	}

	public void addAluno(Aluno u) {
		alunos.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeAluno(int indiceLinha) {
		alunos.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeAlunos(List<Aluno> novosUsuarios) {

		int tamanhoAntigo = getRowCount();
		alunos.addAll(novosUsuarios);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		alunos.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return alunos.isEmpty();
	}

	/**
	 * 
	 */

}
