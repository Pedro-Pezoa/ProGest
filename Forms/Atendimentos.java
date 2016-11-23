package Forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

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
import java.awt.LayoutManager;

import javax.swing.JTextArea;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Atendimentos extends Utils
{
	private JFrame frmCadastro;
	private JButton btnCancelar, btnConcluir; 
	private JTextField txtNomeClien, txtData, txtNomeAt;
	private JComboBox cbxOpcao;
	
	private boolean ehClien, podeConcluirIn, podeConcluirAlt, podeConcluirBusc;
	private Cliente_DBO clienteAux;
	private JLabel lblOrdenarPor;
	private boolean jaPassou = false;
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
		frmCadastro.setBounds(100, 100, 590, 440);
		frmCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastro.getContentPane().setLayout(null);;
		
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		
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
		
		final JPanel pnlObs = new JPanel();
		pnlObs.setBounds(10, 178, 552, 212);
		frmCadastro.getContentPane().add(pnlObs);
		pnlObs.setLayout(null);
		
		final JComboBox cbxPblm = new JComboBox();
		cbxPblm.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma Op\u00E7\u00E3o", "Produto danificado", "Falha no pagamento", "Demora na entrega", "Atendimento de m\u00E1 qualidade", "N\u00E3o recebi o produto", "Outros"}));
		cbxPblm.setFont(new Font("Georgia", Font.PLAIN, 15));
		cbxPblm.setBounds(226, 180, 305, 23);
		frmCadastro.getContentPane().add(cbxPblm);
		
		
		
		final JTextArea cbxObs = new JTextArea();
		cbxObs.setBounds(237, 11, 305, 200);
		pnlObs.add(cbxObs);
		cbxObs.setBorder(border);
		
		JLabel lblObservaes = new JLabel("Observa\u00E7\u00F5es :");
		lblObservaes.setBounds(59, 8, 115, 23);
		pnlObs.add(lblObservaes);
		lblObservaes.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		btnConcluir = new JButton("Concluir");
		btnConcluir.setBounds(31, 131, 149, 31);
		pnlObs.add(btnConcluir);
		
		cbxOpcao = new JComboBox();
		cbxOpcao.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				int i = cbxOpcao.getSelectedIndex();

				if(i == 0 || i == -1)
				{
					if(jaPassou)
					{
						pnlObs.setLocation(pnlObs.getX(),pnlObs.getY()-40);
						frmCadastro.setBounds(100,100,590,440);
						jaPassou = false;
					}
					
				}
				else
				{
					if(!jaPassou)
					{
						frmCadastro.setBounds(100,100,590,480);
						pnlObs.setLocation(pnlObs.getX(),pnlObs.getY()+20);
						cbxPblm.setSelectedIndex(0);
					}
					
					jaPassou = true;
				}

			}
		});
		
		cbxOpcao.setBounds(226, 144, 305, 23);
		frmCadastro.getContentPane().add(cbxOpcao);
		cbxOpcao.setFont(new Font("Georgia", Font.PLAIN, 15));
		cbxOpcao.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma Op\u00E7\u00E3o", "Reclama\u00E7\u00E3o", "D\u00FAvida", "Solicita\u00E7\u00E3o de servi\u00E7o"}));
		
		lblOrdenarPor = new JLabel("Tipo de atendimento:");
		lblOrdenarPor.setBounds(50, 142, 171, 23);
		frmCadastro.getContentPane().add(lblOrdenarPor);
		lblOrdenarPor.setFont(new Font("Georgia", Font.PLAIN, 18));
		
		
		btnConcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (podeConcluir())
				{
					int result = JOptionPane.showConfirmDialog(null, "Deseja inserir este Cliente:");	
					
					if (result == 0)
					{
						try
						{
							Utils.aten.incluirAten(new Atendimento_DBO(aten.getLast("").getCodAten()+1
									, aten.getLast("").getCodClien()+1, txtNomeClien.getText(),
									txtData.getText(),cbxOpcao.getModel().getSelectedItem().toString(), cbxObs.getText(), "sem status", "outra"));
							printInfo(Utils.clien.getLast(""));
							
							JOptionPane.showMessageDialog(null, "Inclusão com Sucesso");
						}catch(Exception e2){JOptionPane.showMessageDialog(null, e2.getMessage());}
					}
				}
				
			}

			private boolean podeConcluir() {			
				if(txtNomeClien.getText() != "" && txtNomeAt.getText() != "" && txtData.getText() 
						!= "" && cbxOpcao.getSelectedIndex() > 0 && cbxObs.getText() != " ")
				{
					if(cbxOpcao.getSelectedIndex() == 1 && cbxPblm.getSelectedIndex() < 1)
						return false;
					return true;					
				}
				return false;
				}
		});
		btnConcluir.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(31, 173, 149, 31);
		pnlObs.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					limparCampos();
					cbxObs.setText("");
					cbxOpcao.setSelectedIndex(0);
					cbxPblm.setSelectedIndex(0);
				} catch (Exception e1) {System.err.println(e1.getMessage());}
			}
		});
		btnCancelar.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JLabel lblPblm = new JLabel("Problema principal:");
		lblPblm.setFont(new Font("Georgia", Font.PLAIN, 18));
		lblPblm.setBounds(46, 180, 171, 23);
		frmCadastro.getContentPane().add(lblPblm);
		
		
		
		
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
