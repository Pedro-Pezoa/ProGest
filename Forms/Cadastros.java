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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JToggleButton;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cadastros extends Utils
{
	private JFrame frmCadastro;
	private JButton btnCliente, btnAtendimento, btnCancelar, btnInserir, btnBuscar,
	                btnFim, btnPrim, btnAnt, btnProx, btnExcluir, btnAlterar, btnConcluir; 
	private JTextField txtNomeClien, txtTelefone, txtEmail;
	private JLabel lblCodigoCliente;
	private JPanel pnlCliente;
	
	private boolean ehClien, podeConcluirIn, podeConcluirAlt, podeConcluirBusc;
	private Cliente_DBO clienteAux;
	private JLabel lblOrdenarPor;
	private JRadioButton rdbtnNome, rdbtnEmail, rdbtnTelefone, rdbtnCodigo;
	private String qualOrdena;
	
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
				podeConcluirIn = podeConcluirAlt = podeConcluirBusc = false;
				mudaBotaoPrint(false, false, true, true);
				qualOrdena = "order by codCliente";
				try 
				{
					Cliente_DBO cliente = Utils.clien.getFirst(qualOrdena);
					printInfo(cliente);
					clienteAux = cliente;
				} catch (Exception e) {System.err.println(e.getMessage());}
			}
		});
		frmCadastro.setTitle("Ger\u00EAnciamento de Cadastros");
		frmCadastro.setBounds(100, 100, 649, 369);
		frmCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastro.getContentPane().setLayout(null);
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					if (ehClien)
					{
						clienteAux.setCodClien(Integer.parseInt(lblCodigoCliente.getText()));
						printInfo(Utils.clien.getLast(qualOrdena));
						
						limparCampos();
						lblCodigoCliente.setText((Utils.clien.getLast(qualOrdena).getCodClien()+1)+"");
						
						mudaBotaoTxt(false);
						mudaBotaoPrint(false, false, false, false);
						
						podeConcluirIn = true;
					}
				}catch (Exception e1) {e1.getMessage();}
		}});
		btnInserir.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnInserir.setBounds(87, 45, 106, 23);
		frmCadastro.getContentPane().add(btnInserir);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (ehClien)
				{
					try
					{
						clienteAux.setCodClien(Integer.parseInt(lblCodigoCliente.getText()));
						int result = JOptionPane.showConfirmDialog(null, "Deseja excluir este Cliente:");
						   
						if (result == 0) 
						{
							Utils.clien.excluirClien(new Cliente_DBO(Integer.parseInt(lblCodigoCliente.getText()), txtNomeClien.getText(),
									                                 txtEmail.getText(), txtTelefone.getText()));
							clienteAux.setCodClien(clienteAux.getCodClien()-1);
							JOptionPane.showMessageDialog(null, "Exclusão com Sucesso");
						}
						else JOptionPane.showMessageDialog(null, "Exclusão Cancelada");
						printInfo(Utils.clien.getCliente_DBO(clienteAux));
					} catch (Exception e1) {System.err.println(e1.getMessage());}
				}
			}
		});
		btnExcluir.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnExcluir.setBounds(203, 45, 106, 23);
		frmCadastro.getContentPane().add(btnExcluir);
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					if (ehClien)
					{
						clienteAux.setCodClien(Integer.parseInt(lblCodigoCliente.getText()));
						podeConcluirAlt = true;
						
						mudaBotaoTxt(false);
						mudaBotaoPrint(false, false, false, false);
					}
				}catch (Exception e1) {e1.getMessage();}
			}
		});
		btnAlterar.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnAlterar.setBounds(319, 45, 106, 23);
		frmCadastro.getContentPane().add(btnAlterar);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ehClien)
				{
					try 
					{
						clienteAux.setCodClien(Integer.parseInt(lblCodigoCliente.getText()));
						
						limparCampos();
						lblCodigoCliente.setText("---------------------------------------------------");
						
						mudaBotaoTxt(false);
						mudaBotaoPrint(false, false, false, false);
						
						podeConcluirBusc = true;
					} catch (Exception e) {JOptionPane.showMessageDialog(null, e.getMessage());}
				}
			}
		});
		btnBuscar.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnBuscar.setBounds(435, 45, 106, 23);
		frmCadastro.getContentPane().add(btnBuscar);
		
		btnPrim = new JButton("Primeiro");
		btnPrim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mudaBotaoPrint(false, false, true, true);
				try 
				{
					printInfo(Utils.clien.getFirst(qualOrdena));
				} catch (Exception e) {System.err.println(e.getMessage());}
			}
		});
		btnPrim.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnPrim.setBounds(87, 79, 106, 23);
		frmCadastro.getContentPane().add(btnPrim);
		
		btnAnt = new JButton("Anterior");
		btnAnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Cliente_DBO clien = Utils.clien.getAnt(Integer.parseInt(lblCodigoCliente.getText()), qualOrdena);
					if (clien != null)
					{
						printInfo(clien);
						mudaBotaoPrint(true, true, true, true);
						if (clien.equals(Utils.clien.getFirst(qualOrdena)))
							mudaBotaoPrint(false, false, true, true);
					}
				} catch (Exception e) {System.err.println(e.getMessage());}
			}
		});
		btnAnt.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnAnt.setBounds(203, 79, 106, 23);
		frmCadastro.getContentPane().add(btnAnt);
		
		btnProx = new JButton("Pr\u00F3ximo");
		btnProx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Cliente_DBO clien = Utils.clien.getProx(Integer.parseInt(lblCodigoCliente.getText()), qualOrdena);
					if (clien != null)
					{
						printInfo(clien);
						mudaBotaoPrint(true, true, true, true);
						if (clien.equals(Utils.clien.getLast(qualOrdena)))
							mudaBotaoPrint(true, true, false, false);
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
				mudaBotaoPrint(true, true, false, false);
				try 
				{
					printInfo(Utils.clien.getLast(qualOrdena));
				} catch (Exception e) {System.err.println(e.getMessage());}
			}
		});
		btnFim.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnFim.setBounds(435, 79, 106, 23);
		frmCadastro.getContentPane().add(btnFim);
		
		btnCliente = new JButton("Cliente");
		btnCliente.setEnabled(false);
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ehQualTabela(true);
			}
		});
		btnCliente.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnCliente.setBounds(87, 11, 222, 23);
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
		btnAtendimento.setBounds(319, 11, 222, 23);
		frmCadastro.getContentPane().add(btnAtendimento);
		
		pnlCliente = new JPanel();
		pnlCliente.setBounds(87, 177, 454, 138);
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
		
		lblCodigoCliente = new JLabel("---------------------------------------------------");
		lblCodigoCliente.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblCodigoCliente.setBounds(181, 11, 263, 14);
		pnlCliente.add(lblCodigoCliente);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					if (podeConcluirIn) JOptionPane.showMessageDialog(null, "Inclusão Cancelado");
					else if (podeConcluirAlt) JOptionPane.showMessageDialog(null, "Alteração Cancelada");
					
					printInfo(Utils.clien.getCliente_DBO(clienteAux));
					podeConcluirAlt = podeConcluirIn = false;
					
					mudaBotaoTxt(true);
					mudaBotaoPrint(true, true, true, true);
				} catch (Exception e1) {System.err.println(e1.getMessage());}
			}
		});
		btnCancelar.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(319, 113, 222, 23);
		frmCadastro.getContentPane().add(btnCancelar);
		
		lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblOrdenarPor.setBounds(87, 143, 106, 23);
		frmCadastro.getContentPane().add(lblOrdenarPor);
		
		btnConcluir = new JButton("Concluir");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (podeConcluirIn)
				{
					int result = JOptionPane.showConfirmDialog(null, "Deseja inserir este Cliente:");	
					
					if (result == 0)
					{
						try
						{
							Utils.clien.incluirClien(new Cliente_DBO(Utils.clien.getLast(qualOrdena).getCodClien()+1, txtNomeClien.getText(),
									                                 txtEmail.getText(), txtTelefone.getText()));
							printInfo(Utils.clien.getLast(qualOrdena));
							
							mudaBotaoTxt(true);
							mudaBotaoPrint(true, true, false, false);
							
							btnInserir.setText("Inserir");
							podeConcluirIn = false;
							
							JOptionPane.showMessageDialog(null, "Inclusão com Sucesso");
						}catch(Exception e2){JOptionPane.showMessageDialog(null, e2.getMessage());}
					}
				}
				
				else if (podeConcluirAlt)
				{
					int result = JOptionPane.showConfirmDialog(null, "Deseja alterar este Cliente:");	
					
					if (result == 0)
					{
						try
						{
							Utils.clien.alterarClien(new Cliente_DBO(clienteAux.getCodClien(), txtNomeClien.getText(),
									                                 txtEmail.getText(), txtTelefone.getText()));
							printInfo(Utils.clien.getCliente_DBO(clienteAux));
							
							mudaBotaoTxt(true);
							mudaBotaoPrint(true, true, true, true);
							
							btnAlterar.setText("Alterar");
							podeConcluirAlt = false;
							
							JOptionPane.showMessageDialog(null, "Alteração com Sucesso");
						}catch(Exception e2){JOptionPane.showMessageDialog(null, e2.getMessage());}
					}
				}
				
				else if (podeConcluirBusc)
				{
					try
					{
						Cliente_DBO[] vet = Utils.clien.getClientes(txtNomeClien.getText(), txtEmail.getText(), txtTelefone.getText());
						printInfo(Utils.clien.getCliente_DBO(new Cliente_DBO(clienteAux.getCodClien(), txtNomeClien.getText(),
                                  txtEmail.getText(), txtTelefone.getText())));
						
						//mudaBotaoTxt(true);
						//mudaBotaoPrint(true, true, true, true);
						
						
						JOptionPane.showMessageDialog(null, "Alteração com Sucesso");
					}catch(Exception e2){JOptionPane.showMessageDialog(null, e2.getMessage());}
				}
			}
		});
		btnConcluir.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnConcluir.setEnabled(false);
		btnConcluir.setBounds(87, 113, 222, 23);
		frmCadastro.getContentPane().add(btnConcluir);
		
		rdbtnNome = new JRadioButton("Nome");
		rdbtnNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudaBotaoOrdena('n');
				qualOrdena = "order by nomeCliente";
				try 
				{
					printInfo(Utils.clien.getFirst(qualOrdena));
					mudaBotaoPrint(false, false, true, true);
				} catch (Exception e1) {System.err.println(e1.getMessage());}
			}
		});
		rdbtnNome.setFont(new Font("Consolas", Font.PLAIN, 16));
		rdbtnNome.setBounds(284, 146, 61, 23);
		frmCadastro.getContentPane().add(rdbtnNome);
		
		rdbtnEmail = new JRadioButton("Email");
		rdbtnEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudaBotaoOrdena('e');
				qualOrdena = "order by emailCliente";
				try 
				{
					printInfo(Utils.clien.getFirst(qualOrdena));
					mudaBotaoPrint(false, false, true, true);
				} catch (Exception e1) {System.err.println(e1.getMessage());}
			}
		});
		rdbtnEmail.setFont(new Font("Consolas", Font.PLAIN, 16));
		rdbtnEmail.setBounds(347, 146, 71, 23);
		frmCadastro.getContentPane().add(rdbtnEmail);
		
		rdbtnTelefone = new JRadioButton("Telefone");
		rdbtnTelefone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mudaBotaoOrdena('t');
				qualOrdena = "order by telefone";
				try 
				{
					printInfo(Utils.clien.getFirst(qualOrdena));
					mudaBotaoPrint(false, false, true, true);
				} catch (Exception e1) {System.err.println(e1.getMessage());}
			}
		});
		rdbtnTelefone.setFont(new Font("Consolas", Font.PLAIN, 16));
		rdbtnTelefone.setBounds(420, 146, 97, 23);
		frmCadastro.getContentPane().add(rdbtnTelefone);
		
		rdbtnCodigo = new JRadioButton("C\u00F3digo");
		rdbtnCodigo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mudaBotaoOrdena('c');
				qualOrdena = "order by codCliente";
				try 
				{
					printInfo(Utils.clien.getFirst(qualOrdena));
					mudaBotaoPrint(false, false, true, true);
				} catch (Exception e1) {System.err.println(e1.getMessage());}
			}
		});
		rdbtnCodigo.setSelected(true);
		rdbtnCodigo.setFont(new Font("Consolas", Font.PLAIN, 16));
		rdbtnCodigo.setBounds(203, 146, 79, 23);
		frmCadastro.getContentPane().add(rdbtnCodigo);
	}

	private void printInfo(Object _obj) 
	{
		if (_obj == null) limparCampos();
		else
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
	}
	
	private void ehQualTabela(boolean _qual)
	{
		ehClien = _qual;
		btnCliente.setEnabled(!_qual);
		btnAtendimento.setEnabled(_qual);
		
		if (_qual)
		{
			pnlCliente.setVisible(_qual);
			try 
			{
				Cliente_DBO cliente = Utils.clien.getFirst(qualOrdena);
				printInfo(cliente);
			} catch (Exception e) {e.getMessage();}
		}
	}
	
	private void mudaBotaoTxt(boolean _qual)
	{
		
		txtNomeClien.setEditable(!_qual);
		txtEmail.setEditable(!_qual);
		txtTelefone.setEditable(!_qual);
		
		btnInserir.setEnabled(_qual);
		btnExcluir.setEnabled(_qual);
		btnAlterar.setEnabled(_qual);
		btnBuscar.setEnabled(_qual);
		
		btnCancelar.setEnabled(!_qual);
		btnConcluir.setEnabled(!_qual);

		btnAtendimento.setEnabled(_qual);
	}
	
	private void limparCampos() 
	{
		txtNomeClien.setText("");
		txtEmail.setText("");
		txtTelefone.setText("");
	}

	private void mudaBotaoPrint(boolean _qualPrim, boolean _qualAnt, boolean _qualProx, boolean _qualUlt)
	{
		btnPrim.setEnabled(_qualPrim);
		btnAnt.setEnabled(_qualAnt);
		btnProx.setEnabled(_qualProx);
		btnFim.setEnabled(_qualUlt);
	}
	
	private void mudaBotaoOrdena(char _qual)
	{
		if (_qual == 'c') rdbtnCodigo.setSelected(true);
		else rdbtnCodigo.setSelected(false);
		
		if (_qual == 'n') rdbtnNome.setSelected(true);
		else rdbtnNome.setSelected(false);
		
		if (_qual == 'e') rdbtnEmail.setSelected(true);
		else rdbtnEmail.setSelected(false);
		
		if (_qual == 't') rdbtnTelefone.setSelected(true);
		else rdbtnTelefone.setSelected(false);
	}
}
