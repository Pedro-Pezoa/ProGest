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

public class Atendimento extends Utils
{
	private JFrame frmCadastro;
	private JButton btnCancelar, btnConcluir; 
	private JTextField txtNomeClien, txtData, txtNomeAt;
	private JPanel pnlCliente;
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
					Atendimento window = new Atendimento();
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
	public Atendimento() throws Exception
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
		frmCadastro.setBounds(100, 100, 649, 685);
		frmCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastro.getContentPane().setLayout(null);
		
		pnlCliente = new JPanel();
		pnlCliente.setBounds(55, 75, 540, 482);
		frmCadastro.getContentPane().add(pnlCliente);;
		pnlCliente.setLayout(null);
		
		JLabel lblNomeClien = new JLabel("Seu nome :");
		lblNomeClien.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblNomeClien.setBounds(87, 36, 98, 14);
		pnlCliente.add(lblNomeClien);
		
		JLabel lblEmail = new JLabel("Nome do atendente :");
		lblEmail.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblEmail.setBounds(10, 72, 171, 14);
		pnlCliente.add(lblEmail);
		
		JLabel lblTelefone = new JLabel("Data :");
		lblTelefone.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblTelefone.setBounds(130, 108, 53, 14);
		pnlCliente.add(lblTelefone);
		
		txtNomeClien = new JTextField();
		txtNomeClien.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtNomeClien.setBounds(225, 36, 305, 20);
		pnlCliente.add(txtNomeClien);
		txtNomeClien.setColumns(10);
		
		txtData = new JTextField();
		txtData.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtData.setColumns(10);
		txtData.setBounds(225, 108, 305, 20);
		pnlCliente.add(txtData);
		
		txtNomeAt = new JTextField();
		txtNomeAt.setFont(new Font("Georgia", Font.PLAIN, 14));
		txtNomeAt.setColumns(10);
		txtNomeAt.setBounds(225, 72, 305, 20);
		pnlCliente.add(txtNomeAt);
		
		cbxOpcao = new JComboBox();
		cbxOpcao.setBounds(282, 146, 187, 23);
		pnlCliente.add(cbxOpcao);
		cbxOpcao.setFont(new Font("Georgia", Font.PLAIN, 15));
		cbxOpcao.setModel(new DefaultComboBoxModel(new String[] {"Reclama\u00E7\u00E3o", "D\u00FAvida", "Solicita\u00E7\u00E3o de servi\u00E7o"}));
		
		lblOrdenarPor = new JLabel("Tipo de atendimento:");
		lblOrdenarPor.setBounds(7, 144, 171, 23);
		pnlCliente.add(lblOrdenarPor);
		lblOrdenarPor.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es :");
		lblObservaes.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblObservaes.setBounds(70, 184, 125, 23);
		pnlCliente.add(lblObservaes);
		
		JLabel lblStatusDoAtendimento = new JLabel("Status do atendimento:");
		lblStatusDoAtendimento.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblStatusDoAtendimento.setBounds(0, 427, 194, 23);
		pnlCliente.add(lblStatusDoAtendimento);
		
		final Label lblStatus = new Label("Sem status");
		lblStatus.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblStatus.setBounds(225, 427, 331, 22);
		pnlCliente.add(lblStatus);
		
		final JTextArea cbxObs = new JTextArea();
		cbxObs.setBounds(225, 180, 305, 200);
		pnlCliente.add(cbxObs);
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		cbxObs.setBorder(border);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					limparCampos();
					lblStatus.setText("Sem Status");
					cbxObs.setText("");
					cbxOpcao.setSelectedIndex(0);
					
				} catch (Exception e1) {System.err.println(e1.getMessage());}
			}
		});
		btnCancelar.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnCancelar.setBounds(473, 599, 106, 23);
		frmCadastro.getContentPane().add(btnCancelar);
		
		btnConcluir = new JButton("Concluir");
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (podeConcluir())
				{
					int result = JOptionPane.showConfirmDialog(null, "Deseja inserir este Cliente:");	
					
					if (result == 0)
					{
						try
						{
							Utils.clien.incluirClien(new Cliente_DBO(Utils.clien.getLast("").getCodClien()+1, txtNomeClien.getText(), txtNomeAt.getText(), txtData.getText()));

							printInfo(Utils.clien.getLast(""));
							
							mudaBotaoTxt(true);
							mudaBotaoPrint(true, true, false, false);
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
									                                 txtNomeAt.getText(), txtData.getText()));
							printInfo(Utils.clien.getCliente_DBO(clienteAux));
							
							mudaBotaoTxt(true);
							mudaBotaoPrint(true, true, true, true);
							
							podeConcluirAlt = false;
							
							JOptionPane.showMessageDialog(null, "Alteração com Sucesso");
						}catch(Exception e2){JOptionPane.showMessageDialog(null, e2.getMessage());}
					}
				}
				
				else if (podeConcluirBusc)
				{
					try
					{
						Utils.clien.getCliente_DBO(new Cliente_DBO(clienteAux.getCodClien(), txtNomeClien.getText(),
								                                   txtNomeAt.getText(), txtData.getText()));
						printInfo(Utils.clien.getCliente_DBO(clienteAux));
						
						mudaBotaoTxt(true);
						mudaBotaoPrint(true, true, true, true);
						
						podeConcluirAlt = false;
						
						JOptionPane.showMessageDialog(null, "Alteração com Sucesso");
					}catch(Exception e2){JOptionPane.showMessageDialog(null, e2.getMessage());}
				}
			}

			private boolean podeConcluir() {			
				
				
				if(txtNomeClien.getText() != "" && txtNomeAt.getText() != "" && txtData.getText() != "" && cbxObs.getText() != "")
					return true;
				return false;
				
				
				
			}
			
			
		});
		btnConcluir.setFont(new Font("Consolas", Font.PLAIN, 16));
		btnConcluir.setBounds(252, 599, 106, 23);
		frmCadastro.getContentPane().add(btnConcluir);
		
		JLabel lblRegistroDeAtendimento = new JLabel("Fa\u00E7a o registro de seu atendimento aqui");
		lblRegistroDeAtendimento.setFont(new Font("Georgia", Font.PLAIN, 23));
		lblRegistroDeAtendimento.setBounds(118, 11, 424, 53);
		frmCadastro.getContentPane().add(lblRegistroDeAtendimento);
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
