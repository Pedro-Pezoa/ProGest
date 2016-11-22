package Forms;

import java.awt.Color;
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
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import java.awt.TextArea;
import java.awt.Label;
import javax.swing.JTextArea;

public class Atendimentos extends Utils
{
	private JFrame frmCadastro;
	private JButton btnCancelar, btnConcluir; 
	private JTextField txtNomeClien, txtData, txtNomeAt;
	private JComboBox cbxOpcao;
	
	private boolean ehClien, podeConcluirIn, podeConcluirAlt, podeConcluirBusc;
	private Cliente_DBO clienteAux;
	private JLabel lblOrdenarPor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Atendimentos window = new Atendimentos();
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
	public Atendimentos() throws Exception
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
				try 
				{
					Cliente_DBO cliente = Utils.clien.getFirst("");
					printInfo(cliente);
					clienteAux = cliente;
				} catch (Exception e) {System.err.println(e.getMessage());}
			}
		});
		frmCadastro.setTitle("Registro de Atendimento");
		frmCadastro.setBounds(100, 100, 588, 439);
		frmCadastro.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmCadastro.getContentPane().setLayout(null);;
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				try 
//				{
//					limparCampos();
//					lblStatus.setText("Sem Status");
//					cbxObs.setText("");
//					cbxOpcao.setSelectedIndex(0);
//					
//				} catch (Exception e1) {System.err.println(e1.getMessage());}
			}
		});
		btnCancelar.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnCancelar.setBounds(37, 350, 149, 31);
		frmCadastro.getContentPane().add(btnCancelar);
		
		btnConcluir = new JButton("Concluir");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				if (podeConcluir())
//				{
//					int result = JOptionPane.showConfirmDialog(null, "Deseja inserir este Cliente:");	
//					
//					if (result == 0)
//					{
//						try
//						{
//							Utils.clien.incluirClien(new Cliente_DBO(Utils.clien.getLast("").getCodClien()+1, txtNomeClien.getText(), txtNomeAt.getText(), txtData.getText()));
//
//							printInfo(Utils.clien.getLast(""));
//							
//							mudaBotaoTxt(true);
//							mudaBotaoPrint(true, true, false, false);
//							podeConcluirIn = false;
//							
//							JOptionPane.showMessageDialog(null, "Inclusão com Sucesso");
//						}catch(Exception e2){JOptionPane.showMessageDialog(null, e2.getMessage());}
//					}
//				}
//				
//				else if (podeConcluirAlt)
//				{
//					int result = JOptionPane.showConfirmDialog(null, "Deseja alterar este Cliente:");	
//					
//					if (result == 0)
//					{
//						try
//						{
//							Utils.clien.alterarClien(new Cliente_DBO(clienteAux.getCodClien(), txtNomeClien.getText(),
//									                                 txtNomeAt.getText(), txtData.getText()));
//							printInfo(Utils.clien.getCliente_DBO(clienteAux));
//							
//							mudaBotaoTxt(true);
//							mudaBotaoPrint(true, true, true, true);
//							
//							podeConcluirAlt = false;
//							
//							JOptionPane.showMessageDialog(null, "Alteração com Sucesso");
//						}catch(Exception e2){JOptionPane.showMessageDialog(null, e2.getMessage());}
//					}
//				}
//				
//				else if (podeConcluirBusc)
//				{
//					try
//					{
//						Utils.clien.getCliente_DBO(new Cliente_DBO(clienteAux.getCodClien(), txtNomeClien.getText(),
//								                                   txtNomeAt.getText(), txtData.getText()));
//						printInfo(Utils.clien.getCliente_DBO(clienteAux));
//						
//						mudaBotaoTxt(true);
//						mudaBotaoPrint(true, true, true, true);
//						
//						podeConcluirAlt = false;
//						
//						JOptionPane.showMessageDialog(null, "Alteração com Sucesso");
//					}catch(Exception e2){JOptionPane.showMessageDialog(null, e2.getMessage());}
//				}
//			}
//
//			private boolean podeConcluir() {			
//				if(txtNomeClien.getText() != "" && txtNomeAt.getText() != "" && txtData.getText() != "" && cbxObs.getText() != "")
//					return true;
//				return false;
				}
		});
		btnConcluir.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnConcluir.setBounds(37, 308, 149, 31);
		frmCadastro.getContentPane().add(btnConcluir);
		
		JLabel lblRegistroDeAtendimento = new JLabel("Fa\u00E7a o registro de seu atendimento aqui");
		lblRegistroDeAtendimento.setFont(new Font("Georgia", Font.PLAIN, 23));
		lblRegistroDeAtendimento.setBounds(83, 11, 424, 42);
		frmCadastro.getContentPane().add(lblRegistroDeAtendimento);
		
		JLabel lblNomeClien = new JLabel("Seu nome :");
		lblNomeClien.setBounds(127, 65, 90, 14);
		frmCadastro.getContentPane().add(lblNomeClien);
		lblNomeClien.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		txtNomeClien = new JTextField();
		txtNomeClien.setBounds(226, 64, 305, 20);
		frmCadastro.getContentPane().add(txtNomeClien);
		txtNomeClien.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtNomeClien.setColumns(10);
		
		txtNomeAt = new JTextField();
		txtNomeAt.setBounds(226, 90, 305, 20);
		frmCadastro.getContentPane().add(txtNomeAt);
		txtNomeAt.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtNomeAt.setColumns(10);
		
		JLabel lblEmail = new JLabel("Nome do atendente :");
		lblEmail.setBounds(50, 91, 167, 14);
		frmCadastro.getContentPane().add(lblEmail);
		lblEmail.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		txtData = new JTextField();
		txtData.setBounds(226, 116, 305, 20);
		frmCadastro.getContentPane().add(txtData);
		txtData.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtData.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Data do Atendimento :");
		lblTelefone.setBounds(37, 117, 180, 14);
		frmCadastro.getContentPane().add(lblTelefone);
		lblTelefone.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		cbxOpcao = new JComboBox();
		cbxOpcao.setBounds(226, 144, 305, 23);
		frmCadastro.getContentPane().add(cbxOpcao);
		cbxOpcao.setFont(new Font("Georgia", Font.PLAIN, 15));
		cbxOpcao.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma Op\u00E7\u00E3o", "Reclama\u00E7\u00E3o", "D\u00FAvida", "Solicita\u00E7\u00E3o de servi\u00E7o"}));
		
		lblOrdenarPor = new JLabel("Tipo de atendimento:");
		lblOrdenarPor.setBounds(47, 142, 171, 23);
		frmCadastro.getContentPane().add(lblOrdenarPor);
		lblOrdenarPor.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es :");
		lblObservaes.setBounds(101, 178, 115, 23);
		frmCadastro.getContentPane().add(lblObservaes);
		lblObservaes.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		final JTextArea cbxObs = new JTextArea();
		cbxObs.setBounds(226, 181, 305, 200);
		frmCadastro.getContentPane().add(cbxObs);
		cbxObs.setBorder(border);
	}

	private void printInfo(Object _obj) 
	{
//		if (_obj == null) limparCampos();
//		else
//		{
//			Cliente_DBO clien = null;
//			Atendimento_DBO aten = null;
//			
//			if (ehClien) clien = (Cliente_DBO)_obj;
//			else aten = (Atendimento_DBO)_obj;
//			
//			if (clien != null)
//			{
////				lblCodigoCliente.setText(clien.getCodClien()+"");
//				txtNomeClien.setText(clien.getNomeClien());
//				txtEmail.setText(clien.getEmailClien());
//				txtTelefone.setText(clien.getTeleClien());
//			}
//		}
	}
	
	private void mudaBotaoTxt(boolean _qual)
	{
		
		txtNomeClien.setEditable(!_qual);
		txtNomeAt.setEditable(!_qual);
		txtData.setEditable(!_qual);
		
//		btnInserir.setEnabled(_qual);
//		btnExcluir.setEnabled(_qual);
//		btnAlterar.setEnabled(_qual);
//		btnBuscar.setEnabled(_qual);
		
		btnCancelar.setEnabled(!_qual);
		btnConcluir.setEnabled(!_qual);
		cbxOpcao.setEnabled(_qual);

//		btnAtendimento.setEnabled(_qual);
	}
	
	private void limparCampos() 
	{
		txtNomeClien.setText("");
		txtNomeAt.setText("");
		txtData.setText("");
	}

	private void mudaBotaoPrint(boolean _qualPrim, boolean _qualAnt, boolean _qualProx, boolean _qualUlt)
	{
//		btnPrim.setEnabled(_qualPrim);
//		btnAnt.setEnabled(_qualAnt);
//		btnProx.setEnabled(_qualProx);
//		btnFim.setEnabled(_qualUlt);
	}
}
