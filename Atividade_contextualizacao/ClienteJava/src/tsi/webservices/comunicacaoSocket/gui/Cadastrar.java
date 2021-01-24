package tsi.webservices.comunicacaoSocket.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import tsi.webservices.comunicacaoSocket.modelos.Aluno;
import tsi.webservices.comunicacaoSocket.modelos.AlunoTableModel;
import tsi.webservices.comunicacaoSocket.modelos.Turma;
import tsi.webservices.comunicacaoSocket.modelos.TurmaTableModel;
import tsi.webservices.comunicacaoSocket.socket.Cliente;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Cadastrar extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private List<Turma> turmas = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastrar frame = new Cadastrar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Cadastrar() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 746, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelTables = new JPanel();
		panelTables.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Tabelas",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTables.setBounds(10, 11, 558, 368);
		contentPane.add(panelTables);

		JScrollPane scrollPaneTurmas = new JScrollPane();
		scrollPaneTurmas.setBounds(16, 27, 526, 139);

		JScrollPane scrollPaneAlunos = new JScrollPane();
		scrollPaneAlunos.setBounds(16, 197, 526, 146);

		JPanel panelBotoes = new JPanel();
		panelBotoes.setBounds(578, 11, 142, 368);
		contentPane.add(panelBotoes);
		panelBotoes.setLayout(null);

		JButton buttonAdicionarTurma = new JButton("Nova Turma");
		buttonAdicionarTurma.setBounds(10, 48, 122, 53);
		panelBotoes.add(buttonAdicionarTurma);

		JButton buttonEnviar = new JButton("Enviar");
		buttonEnviar.setBounds(10, 145, 122, 53);
		buttonEnviar.setEnabled(false);
		panelBotoes.add(buttonEnviar);

		JButton buttonAdicionarAluno = new JButton("Novo Aluno");
		buttonAdicionarAluno.setEnabled(false);
		buttonAdicionarAluno.setBounds(10, 263, 122, 53);
		panelBotoes.add(buttonAdicionarAluno);

		panelTables.setLayout(null);
		AlunoTableModel modelAluno = new AlunoTableModel();
		JTable tableAlunos = new JTable(modelAluno);
		scrollPaneAlunos.setViewportView(tableAlunos);
		tableAlunos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelTables.add(scrollPaneAlunos);

		TurmaTableModel modelTurma = new TurmaTableModel();
		JTable tableTurmas = new JTable(modelTurma);
		scrollPaneTurmas.setViewportView(tableTurmas);
		tableTurmas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panelTables.add(scrollPaneTurmas);
		
		JLabel lblNewLabel = new JLabel("Aten\u00E7\u00E3o! O aluno com valor diferente de 1 no campo \"Matriculado\" n\u00E3o será matriculado.");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(20, 380, 700, 25);
		contentPane.add(lblNewLabel);

		tableTurmas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int linhaSelecionada = 0;

				modelAluno.limpar();

				buttonAdicionarAluno.setEnabled(true);

				linhaSelecionada = tableTurmas.getSelectedRow();

				modelAluno.addListaDeAlunos(turmas.get(linhaSelecionada).getAlunos());

			}
		});

		buttonAdicionarTurma.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Turma turma = new Turma(2022, "TSI");
					
				turmas.add(turma);

				modelTurma.addTurma(turma);
				
				buttonEnviar.setEnabled(true);
			}
		});

		buttonEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Cliente cliente = new Cliente(turmas);

				try {
					cliente.initConnection();
				} catch (IOException e1) {
					System.out.println("Deu ruim");
				}
			}
		});

		buttonAdicionarAluno.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				Aluno aluno = new Aluno("Edmilson", 1);

				int linhaSelec = tableTurmas.getSelectedRow();

				Turma turma = turmas.get(linhaSelec);

				turma.adicionarAluno(aluno);

				turmas.set(linhaSelec, turma);

				modelAluno.addAluno(aluno);
			}

		});

	}
}
