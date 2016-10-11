package Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import BD_Basicos.Utils;
import BD_DBOs.Atendimento_DBO;
import BD_DBOs.Cliente_DBO;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Cadastros extends Utils
{
	private JFrame frmCadastro;
	private JButton btnCliente, btnAtendimento, btnConcluir, btnInserir, btnFim, btnPrim, btnAnt; 
	private JTextField txtNomeClien, txtTelefone, txtEmail;
	private JLabel lblCodigoCliente;
	private JPanel pnlCliente;
	private boolean ehClien;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastros window = new Cadastros();
					window.frmCadastro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Cadastros() throws Exception
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frmCadastro = new JFrame();
		frmCadastro.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				ehClien = true;
				btnPrim.setEnabled(false);
				btnAnt.setEnabled(false);
				try 
				{
					Cliente_DBO cliente = Utils.clien.getFirst();
					printInfo(cliente);
				} catch (Exception e) {System.err.println(e.getMessage());}
			}
		});
		frmCadastro.setTitle("Ger\u00EAnciamento de Cadastros");
		frmCadastro.setBounds(100, 100, 664, 301);
		frmCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastro.getContentPane().setLayout(null);
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ehClien)
				{
					try 
					{
						lblCodigoCliente.setText((Utils.clien.getLast().getCodClien()+1)+"");
						mudaBotao(false);
					} catch (Exception e1) {System.err.println(e1.getMessage());}
				}
			}
		});
		btnInserir.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnInserir.setBounds(87, 45, 106, 23);
		frmCadastro.getContentPane().add(btnInserir);
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Deseja excluir este Cliente:");	
				try 
				{
					if (result == 0) 
					{
						Utils.clien.excluirClien(new Cliente_DBO(Integer.parseInt(lblCodigoCliente.getText()), txtNomeClien.getText(),
								                                 txtEmail.getText(), txtTelefone.getText()));
						JOptionPane.showMessageDialog(null, "Exclusão feita com sucesso");
					}
					
					else JOptionPane.showMessageDialog(null, "Exclusão Cancelada");
					printInfo(Utils.clien.getFirst());
				} catch (Exception e1) {System.err.println(e1.getMessage());}
			}
		});
		btnExcluir.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnExcluir.setBounds(203, 45, 106, 23);
		frmCadastro.getContentPane().add(btnExcluir);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnAlterar.setBounds(319, 45, 106, 23);
		frmCadastro.getContentPane().add(btnAlterar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnBuscar.setBounds(435, 45, 106, 23);
		frmCadastro.getContentPane().add(btnBuscar);
		
		btnPrim = new JButton("Primeiro");
		btnPrim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnPrim.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnPrim.setBounds(87, 79, 106, 23);
		frmCadastro.getContentPane().add(btnPrim);
		
		btnAnt = new JButton("Anterior");
		btnAnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAnt.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnAnt.setBounds(203, 79, 106, 23);
		frmCadastro.getContentPane().add(btnAnt);
		
		JButton btnProx = new JButton("Pr\u00F3ximo");
		btnProx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Cliente_DBO clien = Utils.clien.getProx(Integer.parseInt(lblCodigoCliente.getText()));
					if (clien != null)
					{
						printInfo(clien);
						btnPrim.setEnabled(true);
						btnAnt.setEnabled(true);
					}
					else
					{
						btnProx.setEnabled(false);
						btnFim.setEnabled(false);
					}
				} catch (Exception e) {System.err.println(e.getMessage());}
			}
		});
		btnProx.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnProx.setBounds(319, 79, 106, 23);
		frmCadastro.getContentPane().add(btnProx);
		
		btnFim = new JButton("\u00DAltimo");
		btnFim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnFim.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnFim.setBounds(435, 79, 106, 23);
		frmCadastro.getContentPane().add(btnFim);
		
		btnCliente = new JButton("Cliente");
		btnCliente.setEnabled(false);
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ehCliente(true);
			}
		});
		btnCliente.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnCliente.setBounds(87, 11, 146, 23);
		frmCadastro.getContentPane().add(btnCliente);
		
		btnAtendimento = new JButton("Atendimento");
		btnAtendimento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ehClien = false;
				btnCliente.setEnabled(true);
				pnlCliente.setVisible(false);
				btnAtendimento.setEnabled(false);
			}
		});
		btnAtendimento.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnAtendimento.setBounds(243, 11, 146, 23);
		frmCadastro.getContentPane().add(btnAtendimento);
		
		pnlCliente = new JPanel();
		pnlCliente.setBounds(87, 113, 454, 138);
		frmCadastro.getContentPane().add(pnlCliente);;
		pnlCliente.setLayout(null);
		
		JLabel lblCodClien = new JLabel("C\u00F3digo Cliente:");
		lblCodClien.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblCodClien.setBounds(48, 11, 123, 14);
		pnlCliente.add(lblCodClien);
		
		JLabel lblNomeClien = new JLabel("Nome do Cliente:");
		lblNomeClien.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNomeClien.setBounds(31, 41, 140, 14);
		pnlCliente.add(lblNomeClien);
		
		JLabel lblEmail = new JLabel("Email do Cliente:");
		lblEmail.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblEmail.setBounds(31, 76, 140, 14);
		pnlCliente.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone do Cliente:");
		lblTelefone.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblTelefone.setBounds(10, 108, 161, 14);
		pnlCliente.add(lblTelefone);
		
		txtNomeClien = new JTextField();
		txtNomeClien.setEditable(false);
		txtNomeClien.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtNomeClien.setBounds(181, 41, 263, 20);
		pnlCliente.add(txtNomeClien);
		txtNomeClien.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setEditable(false);
		txtTelefone.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(181, 106, 263, 20);
		pnlCliente.add(txtTelefone);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(181, 76, 263, 20);
		pnlCliente.add(txtEmail);
		
		lblCodigoCliente = new JLabel("-------------------------------------");
		lblCodigoCliente.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblCodigoCliente.setBounds(181, 11, 263, 14);
		pnlCliente.add(lblCodigoCliente);
		
		btnConcluir = new JButton("Concluir");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Deseja inserir este Cliente:");	
				try 
				{
					if (result == 0)
					{
						Utils.clien.incluirClien(new Cliente_DBO(Utils.clien.getLast().getCodClien()+1, txtNomeClien.getText(),
								                                 txtEmail.getText(), txtTelefone.getText()));
						JOptionPane.showMessageDialog(null, "Inclusão feita com sucesso");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Inclusão Cancelado");
						printInfo(Utils.clien.getFirst());
					}
				} catch (Exception e1) {e1.getMessage();}
				mudaBotao(true);
			}
		});
		btnConcluir.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnConcluir.setEnabled(false);
		btnConcluir.setBounds(399, 11, 142, 23);
		frmCadastro.getContentPane().add(btnConcluir);
	}

	private void printInfo(Object _obj) 
	{
		Cliente_DBO clien = null;
		Atendimento_DBO aten = null;
		
		if (ehClien) clien = (Cliente_DBO)_obj;
		else aten = (Atendimento_DBO)_obj;
		
		if (clien != null)
		{
			lblCodigoCliente.setText(clien.getCodClien()+"");
			txtNomeClien.setText(clien.getNomeClien());
			txtEmail.setText(clien.getEmailClien());
			txtTelefone.setText(clien.getTeleClien());
		}
	}
	
	private void ehCliente(boolean _qual)
	{
		ehClien = _qual;
		btnCliente.setEnabled(!_qual);
		btnAtendimento.setEnabled(_qual);
		
		if (_qual)
		{
			pnlCliente.setVisible(_qual);
			try 
			{
				Cliente_DBO cliente = Utils.clien.getFirst();
				printInfo(cliente);
			} catch (Exception e) {e.getMessage();}
		}
	}
	
	private void mudaBotao(boolean _qual)
	{
		txtNomeClien.setEditable(!_qual);
		txtEmail.setEditable(!_qual);
		txtTelefone.setEditable(!_qual);
		
		btnConcluir.setEnabled(!_qual);
		btnInserir.setEnabled(_qual);
		btnAtendimento.setEnabled(_qual);
	}
}
