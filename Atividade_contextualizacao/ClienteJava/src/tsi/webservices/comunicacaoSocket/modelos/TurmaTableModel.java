package tsi.webservices.comunicacaoSocket.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class TurmaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<Turma> turmas;

	private String[] colunas = new String[] { "Id", "ano", "Curso" };

	/** Creates a new instance of DevmediaTableModel */
	public TurmaTableModel(List<Turma> turmas) {
		this.turmas = turmas;
	}

	public TurmaTableModel() {
		this.turmas = new ArrayList<>();
	}

	@Override
	public int getRowCount() {
		return turmas.size();
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

	public void setValueAt(Turma aValue, int rowIndex) {
		Turma turma = turmas.get(rowIndex);

		turma.setId(aValue.getId());
		turma.setAno(aValue.getAno());
		turma.setCurso(aValue.getCurso());

		fireTableCellUpdated(rowIndex, 0);
		fireTableCellUpdated(rowIndex, 1);
		fireTableCellUpdated(rowIndex, 2);

	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Turma turma = turmas.get(rowIndex);

		try {
			switch (columnIndex) {
			case 0:
				turma.setId(Integer.parseInt(aValue.toString()));break;
			case 1:
				turma.setAno(Integer.parseInt(aValue.toString()));break;
			case 2:
				turma.setCurso(aValue.toString());break;
			}
			fireTableCellUpdated(rowIndex, columnIndex);

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Tipo de dado inconpatível com o campo", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Turma turmaSelecionada = turmas.get(rowIndex);

		String valueObject = null;

		switch (columnIndex) {
		case 0:
			valueObject = Integer.toString(turmaSelecionada.getId());
			break;
		case 1:
			valueObject = Integer.toString(turmaSelecionada.getAno());
			break;
		case 2:
			valueObject = turmaSelecionada.getCurso();
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

	public Turma getTurma(int indiceLinha) {
		return turmas.get(indiceLinha);
	}

	public void addTurma(Turma u) {
		turmas.add(u);

		int ultimoIndice = getRowCount() - 1;

		fireTableRowsInserted(ultimoIndice, ultimoIndice);
	}

	public void removeTurma(int indiceLinha) {
		turmas.remove(indiceLinha);

		fireTableRowsDeleted(indiceLinha, indiceLinha);
	}

	public void addListaDeTurmas(List<Turma> novosUsuarios) {

		int tamanhoAntigo = getRowCount();
		turmas.addAll(novosUsuarios);
		fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
	}

	public void limpar() {
		turmas.clear();
		fireTableDataChanged();
	}

	public boolean isEmpty() {
		return turmas.isEmpty();
	}

	/**
	 * 
	 */

}
