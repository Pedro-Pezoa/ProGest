package Forms;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import BD_Basicos.Utils;
import BD_DBOs.Atendimento_DBO;
import BD_DBOs.Cliente_DBO;
import Tipos.ListaDupla;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.JTextArea;

public class Cadastros extends Utils
{
	private JFrame frmCadastro;
	private JButton btnCliente, btnAtendimento, btnCancelar, btnInserir, btnBuscar,
	                btnFim, btnPrim, btnAnt, btnProx, btnExcluir, btnAlterar, btnConcluir; 
	private JTextField txtNomeClien, txtTelefone, txtEmail;
	private JLabel lblCodigoCliente;
	private JPanel pnlCliente, pnlAtend;
	
	private boolean ehClien, ehAten, podeConcluirIn, podeConcluirAlt, podeConcluirBusc, estaBuscando;
	private Cliente_DBO clienteAux;
	private Atendimento_DBO atendenteAux;
	private JRadioButton rdbtnNome, rdbtnEmail, rdbtnTelefone, rdbtnCodigo;
	private String qualOrdena;
	
	private ListaDupla<Cliente_DBO> lista;
	private JTextField txtTipo;
	private JTextField txtData;
	private JTextField txtNomeAtend;
	private JTextField txtStatus;
	private JLabel lblStatusDoAtendimento;
	private JLabel lblObservaoDoCliente;
	
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
				podeConcluirIn = podeConcluirAlt = podeConcluirBusc = estaBuscando = ehAten = false;
				
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
		frmCadastro.setBounds(100, 100, 649, 363);
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
				if (ehClien)
				{
					try 
					{
						if (estaBuscando)
						{
							lista.setAtual(lista.getInicio());
							printInfo(lista.getAtual().getInfo());
						}
						else printInfo(Utils.clien.getFirst(qualOrdena));
					} catch (Exception e) {System.err.println(e.getMessage());}
				}
			}
		});
		btnPrim.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnPrim.setBounds(87, 79, 106, 23);
		frmCadastro.getContentPane().add(btnPrim);
		
		btnAnt = new JButton("Anterior");
		btnAnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					if (ehClien)
					{
						if (estaBuscando)
						{
							lista.setAtual(lista.getAtual().getAnt());
							printInfo(lista.getAtual().getInfo());
							if (lista.getAtual().equals(lista.getInicio())) mudaBotaoPrint(false, false, true, true);
							else mudaBotaoPrint(true, true, true, true);
						}
						else
						{
							Cliente_DBO clien = Utils.clien.getAnt(Integer.parseInt(lblCodigoCliente.getText()), qualOrdena);
							if (clien != null)
							{
								printInfo(clien);
								mudaBotaoPrint(true, true, true, true);
								if (clien.equals(Utils.clien.getFirst(qualOrdena)))
									mudaBotaoPrint(false, false, true, true);
							}
						}	
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
				try 
				{
					if (ehClien)
					{
						if (estaBuscando)
						{
							lista.setAtual(lista.getAtual().getProx());
							printInfo(lista.getAtual().getInfo());
							if (lista.getAtual().equals(lista.getFim())) mudaBotaoPrint(true, true, false, false);
							else mudaBotaoPrint(true, true, true, true);
						}
						else
						{
							Cliente_DBO clien = Utils.clien.getProx(Integer.parseInt(lblCodigoCliente.getText()), qualOrdena);
							if (clien != null)
							{
								printInfo(clien);
								mudaBotaoPrint(true, true, true, true);
								if (clien.equals(Utils.clien.getLast(qualOrdena)))
									mudaBotaoPrint(true, true, false, false);
							}
						}
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
					if (ehClien)
					{
						if (estaBuscando)
						{
							lista.setAtual(lista.getFim());
							printInfo(lista.getAtual().getInfo());
						}
						else printInfo(Utils.clien.getLast(qualOrdena));
					}
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
				ehQualTabela(false);
			}
		});
		btnAtendimento.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnAtendimento.setBounds(319, 11, 222, 23);
		frmCadastro.getContentPane().add(btnAtendimento);
		
		pnlAtend = new JPanel();
		pnlAtend.setBounds(62, 147, 504, 385);
		pnlAtend.setVisible(false);
		frmCadastro.getContentPane().add(pnlAtend);
		pnlAtend.setLayout(null);
		
		JLabel label = new JLabel("Ordenar por:");
		label.setFont(new Font("Georgia", Font.PLAIN, 18));
		label.setBounds(40, 0, 106, 23);
		pnlAtend.add(label);
		
		JRadioButton rdbtnCodAtend = new JRadioButton("C\u00F3digo");
		rdbtnCodAtend.setSelected(true);
		rdbtnCodAtend.setFont(new Font("Consolas", Font.PLAIN, 16));
		rdbtnCodAtend.setBounds(156, 3, 79, 23);
		pnlAtend.add(rdbtnCodAtend);
		
		JRadioButton rdbtnNomeAtend = new JRadioButton("Nome");
		rdbtnNomeAtend.setFont(new Font("Consolas", Font.PLAIN, 16));
		rdbtnNomeAtend.setBounds(237, 3, 61, 23);
		pnlAtend.add(rdbtnNomeAtend);
		
		JRadioButton rdbtnData = new JRadioButton("Data");
		rdbtnData.setFont(new Font("Consolas", Font.PLAIN, 16));
		rdbtnData.setBounds(300, 3, 71, 23);
		pnlAtend.add(rdbtnData);
		
		JRadioButton rdbtnStatus = new JRadioButton("Status");
		rdbtnStatus.setFont(new Font("Consolas", Font.PLAIN, 16));
		rdbtnStatus.setBounds(373, 3, 97, 23);
		pnlAtend.add(rdbtnStatus);
		
		JLabel lblTipoDoAtendimento = new JLabel("Tipo do Atendimento:");
		lblTipoDoAtendimento.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblTipoDoAtendimento.setBounds(21, 140, 176, 14);
		pnlAtend.add(lblTipoDoAtendimento);
		
		txtTipo = new JTextField();
		txtTipo.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtTipo.setEditable(false);
		txtTipo.setColumns(10);
		txtTipo.setBounds(207, 139, 263, 20);
		pnlAtend.add(txtTipo);
		
		txtData = new JTextField();
		txtData.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtData.setEditable(false);
		txtData.setColumns(10);
		txtData.setBounds(207, 113, 263, 20);
		pnlAtend.add(txtData);
		
		JLabel lblDataDoAtendimento = new JLabel("Data do Atendimento:");
		lblDataDoAtendimento.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblDataDoAtendimento.setBounds(21, 114, 176, 14);
		pnlAtend.add(lblDataDoAtendimento);
		
		JLabel lblNomeDoAtendimento = new JLabel("Nome do Atendimento:");
		lblNomeDoAtendimento.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNomeDoAtendimento.setBounds(10, 88, 188, 14);
		pnlAtend.add(lblNomeDoAtendimento);
		
		txtNomeAtend = new JTextField();
		txtNomeAtend.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtNomeAtend.setEditable(false);
		txtNomeAtend.setColumns(10);
		txtNomeAtend.setBounds(207, 87, 263, 20);
		pnlAtend.add(txtNomeAtend);
		
		JLabel lblCodAtend = new JLabel("---------------------------------------------------");
		lblCodAtend.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblCodAtend.setBounds(207, 33, 263, 14);
		pnlAtend.add(lblCodAtend);
		
		JLabel lblCodClien2 = new JLabel("C\u00F3digo Cliente:");
		lblCodClien2.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblCodClien2.setBounds(74, 63, 123, 14);
		pnlAtend.add(lblCodClien2);
		
		JLabel lblCdigoAtendimento = new JLabel("C\u00F3digo Atendimento:");
		lblCdigoAtendimento.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblCdigoAtendimento.setBounds(26, 34, 171, 14);
		pnlAtend.add(lblCdigoAtendimento);
		
		JLabel lblCodClienAux = new JLabel("---------------------------------------------------");
		lblCodClienAux.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblCodClienAux.setBounds(207, 58, 263, 14);
		pnlAtend.add(lblCodClienAux);
		
		txtStatus = new JTextField();
		txtStatus.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtStatus.setEditable(false);
		txtStatus.setColumns(10);
		txtStatus.setBounds(207, 165, 263, 20);
		pnlAtend.add(txtStatus);
		
		lblStatusDoAtendimento = new JLabel("Status do Atendimento:");
		lblStatusDoAtendimento.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblStatusDoAtendimento.setBounds(8, 166, 189, 14);
		pnlAtend.add(lblStatusDoAtendimento);
		
		lblObservaoDoCliente = new JLabel("Observa\u00E7\u00E3o do Cliente:");
		lblObservaoDoCliente.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblObservaoDoCliente.setBounds(14, 197, 183, 14);
		pnlAtend.add(lblObservaoDoCliente);
		
		JTextArea txtObservacao = new JTextArea();
		txtObservacao.setBounds(207, 196, 263, 189);
		txtObservacao.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		pnlAtend.add(txtObservacao);
		
		pnlCliente = new JPanel();
		pnlCliente.setBounds(87, 147, 454, 168);
		frmCadastro.getContentPane().add(pnlCliente);;
		pnlCliente.setLayout(null);
		
		JLabel lblCodClien = new JLabel("C\u00F3digo Cliente:");
		lblCodClien.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblCodClien.setBounds(48, 42, 123, 14);
		pnlCliente.add(lblCodClien);
		
		JLabel lblNomeClien = new JLabel("Nome do Cliente:");
		lblNomeClien.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNomeClien.setBounds(31, 72, 140, 14);
		pnlCliente.add(lblNomeClien);
		
		JLabel lblEmail = new JLabel("Email do Cliente:");
		lblEmail.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblEmail.setBounds(31, 107, 140, 14);
		pnlCliente.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Telefone do Cliente:");
		lblTelefone.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblTelefone.setBounds(10, 139, 161, 14);
		pnlCliente.add(lblTelefone);
		
		txtNomeClien = new JTextField();
		txtNomeClien.setEditable(false);
		txtNomeClien.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtNomeClien.setBounds(181, 72, 263, 20);
		pnlCliente.add(txtNomeClien);
		txtNomeClien.setColumns(10);
		
		txtTelefone = new JTextField();
		txtTelefone.setEditable(false);
		txtTelefone.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(181, 137, 263, 20);
		pnlCliente.add(txtTelefone);
		
		txtEmail = new JTextField();
		txtEmail.setEditable(false);
		txtEmail.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtEmail.setColumns(10);
		txtEmail.setBounds(181, 107, 263, 20);
		pnlCliente.add(txtEmail);
		
		lblCodigoCliente = new JLabel("---------------------------------------------------");
		lblCodigoCliente.setFont(new Font("Georgia", Font.PLAIN, 14));
		lblCodigoCliente.setBounds(181, 42, 263, 14);
		pnlCliente.add(lblCodigoCliente);
		
		rdbtnTelefone = new JRadioButton("Telefone");
		rdbtnTelefone.setBounds(343, 3, 97, 23);
		pnlCliente.add(rdbtnTelefone);
		rdbtnTelefone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ehClien)
				{
					if (!podeConcluirAlt && !podeConcluirIn)
					{
						mudaBotaoOrdena('t');
						qualOrdena = "order by telefone";
						try 
						{
							printInfo(Utils.clien.getFirst(qualOrdena));
							mudaBotaoPrint(false, false, true, true);
						} catch (Exception e1) {System.err.println(e1.getMessage());}
					}
				}
			}
		});
		rdbtnTelefone.setFont(new Font("Consolas", Font.PLAIN, 16));
		
		rdbtnEmail = new JRadioButton("Email");
		rdbtnEmail.setBounds(270, 3, 71, 23);
		pnlCliente.add(rdbtnEmail);
		rdbtnEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ehClien)
				{
					if (!podeConcluirAlt && !podeConcluirIn)
					{
						mudaBotaoOrdena('e');
						qualOrdena = "order by emailCliente";
						try 
						{
							printInfo(Utils.clien.getFirst(qualOrdena));
							mudaBotaoPrint(false, false, true, true);
						} catch (Exception e1) {System.err.println(e1.getMessage());}
					}
				}
			}
		});
		rdbtnEmail.setFont(new Font("Consolas", Font.PLAIN, 16));
		
		rdbtnNome = new JRadioButton("Nome");
		rdbtnNome.setBounds(207, 3, 61, 23);
		pnlCliente.add(rdbtnNome);
		rdbtnNome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ehClien)
				{
					if (!podeConcluirAlt && !podeConcluirIn)
					{
						mudaBotaoOrdena('n');
						qualOrdena = "order by nomeCliente";
						try 
						{
							printInfo(Utils.clien.getFirst(qualOrdena));
							mudaBotaoPrint(false, false, true, true);
						} catch (Exception e1) {System.err.println(e1.getMessage());}
					}
				}
			}
		});
		rdbtnNome.setFont(new Font("Consolas", Font.PLAIN, 16));
		
		rdbtnCodigo = new JRadioButton("C\u00F3digo");
		rdbtnCodigo.setBounds(126, 3, 79, 23);
		pnlCliente.add(rdbtnCodigo);
		rdbtnCodigo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (ehClien)
				{
					if (!podeConcluirAlt && !podeConcluirIn)
					{
						mudaBotaoOrdena('c');
						qualOrdena = "order by codCliente";
						try 
						{
							printInfo(Utils.clien.getFirst(qualOrdena));
							mudaBotaoPrint(false, false, true, true);
						} catch (Exception e1) {System.err.println(e1.getMessage());}
					}
				}
			}
		});
		rdbtnCodigo.setSelected(true);
		rdbtnCodigo.setFont(new Font("Consolas", Font.PLAIN, 16));
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setBounds(10, 0, 106, 23);
		pnlCliente.add(lblOrdenarPor);
		lblOrdenarPor.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					if (ehClien)
					{
						if (!estaBuscando)
						{
							if (podeConcluirIn) JOptionPane.showMessageDialog(null, "Inclusão Cancelado");
							else if (podeConcluirAlt) JOptionPane.showMessageDialog(null, "Alteração Cancelada");
							
							printInfo(Utils.clien.getCliente_DBO(clienteAux));
							podeConcluirAlt = podeConcluirIn = false;
							
							mudaBotaoTxt(true);
							mudaBotaoPrint(true, true, true, true);
						}
						else
						{
							estaBuscando = false;
							Cliente_DBO cliente = Utils.clien.getFirst(qualOrdena);
							printInfo(cliente);
							clienteAux = cliente;
							
							podeConcluirIn = podeConcluirAlt = podeConcluirBusc = estaBuscando = false;
							mudaBotaoPrint(false, false, true, true);
							mudaBotaoTxt(true);
						}
					}
				} catch (Exception e1) {System.err.println(e1.getMessage());}
			}
		});
		btnCancelar.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnCancelar.setEnabled(false);
		btnCancelar.setBounds(319, 113, 222, 23);
		frmCadastro.getContentPane().add(btnCancelar);
		
		btnConcluir = new JButton("Concluir");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try
				{
					if (ehClien)
					{
						if (podeConcluirBusc)
						{
							lista = Utils.clien.getClientes(txtNomeClien.getText(), txtEmail.getText(), txtTelefone.getText());
							if (lista != null)
							{
								lista.setAtual(lista.getInicio());
								printInfo(lista.getAtual().getInfo());
								
								if (lista.getFim().equals(lista.getInicio())) mudaBotaoPrint(false, false, false, false);
								else mudaBotaoPrint(false, false, true, true);
								
								estaBuscando = true;
								mudaBotaoTxt(true);
								podeConcluirBusc = false;
								JOptionPane.showMessageDialog(null, "Busca com Sucesso");
							}
							else JOptionPane.showMessageDialog(null, "Não houve resultados na busca feita");
						}
						
						else if (JOptionPane.showConfirmDialog(null, "Deseja fazer esta ação:") == 0)
						{
							if (podeConcluirIn)
							{
								Utils.clien.incluirClien(new Cliente_DBO(Utils.clien.getLast(qualOrdena).getCodClien()+1, txtNomeClien.getText(),
		                                 txtEmail.getText(), txtTelefone.getText()));
								printInfo(Utils.clien.getLast(qualOrdena));
								
								mudaBotaoTxt(true);
								mudaBotaoPrint(true, true, false, false);
								podeConcluirIn = false;
							}
							
							else
							{
								Utils.clien.alterarClien(new Cliente_DBO(clienteAux.getCodClien(), txtNomeClien.getText(),
		                                 txtEmail.getText(), txtTelefone.getText()));
								printInfo(Utils.clien.getCliente_DBO(clienteAux));
								
								mudaBotaoTxt(true);
								mudaBotaoPrint(true, true, true, true);
								podeConcluirAlt = false;
							}
							JOptionPane.showMessageDialog(null, "Ação feita com sucesso");
						}
						else JOptionPane.showMessageDialog(null, "Ação cancelada");
					}
				}catch(Exception e2){JOptionPane.showMessageDialog(null, e2.getMessage());}
			}
		});
		btnConcluir.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnConcluir.setEnabled(false);
		btnConcluir.setBounds(87, 113, 222, 23);
		frmCadastro.getContentPane().add(btnConcluir);
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
		try 
		{
			if (_qual)
			{
				ehClien = true;
				ehAten = false;
				
				btnCliente.setEnabled(false);
				btnAtendimento.setEnabled(true);
				
				qualOrdena = "order by codCliente";
				pnlCliente.setVisible(true);
				pnlAtend.setVisible(false);
				
				Cliente_DBO cliente = Utils.clien.getFirst(qualOrdena);
				printInfo(cliente);
				clienteAux = cliente;
				frmCadastro.setBounds(100, 100, 649, 363);
			}
			else
			{
				ehClien = false;
				ehAten = true;
				
				btnCliente.setEnabled(true);
				btnAtendimento.setEnabled(false);
				
				qualOrdena = "order by codAtendimento";
				pnlCliente.setVisible(false);
				pnlAtend.setVisible(true);
				
				Atendimento_DBO atend = Utils.aten.getFirst(qualOrdena);
				printInfo(atend);
				atendenteAux = atend;
				frmCadastro.setBounds(100, 100, 649, 581);
			}
		}catch (Exception e) {e.getMessage();}
	}
	
	private void mudaBotaoTxt(boolean _qual)
	{
		if (ehClien)
		{
			txtNomeClien.setEditable(!_qual);
			txtEmail.setEditable(!_qual);
			txtTelefone.setEditable(!_qual);
			
			btnInserir.setEnabled(_qual && !estaBuscando);
			btnExcluir.setEnabled(_qual && !estaBuscando);
			btnAlterar.setEnabled(_qual && !estaBuscando);
			btnBuscar.setEnabled(_qual);
			
			btnCancelar.setEnabled(!_qual || estaBuscando);
			btnConcluir.setEnabled(!_qual);
	
			btnAtendimento.setEnabled(_qual);
			
			rdbtnCodigo.setEnabled(_qual);
			rdbtnEmail.setEnabled(_qual);
			rdbtnNome.setEnabled(_qual);
			rdbtnTelefone.setEnabled(_qual);
		}
	}
	
	private void limparCampos() 
	{
		if (ehClien)
		{
			txtNomeClien.setText("");
			txtEmail.setText("");
			txtTelefone.setText("");
		}
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
		if (ehClien)
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
}
