package Forms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Gerenciador {

	private JFrame frmGerenciador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gerenciador window = new Gerenciador();
					window.frmGerenciador.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gerenciador() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGerenciador = new JFrame();
		frmGerenciador.setTitle("Ger\u00EAnciador de Satisfa\u00E7\u00E3o de Clientes");
		frmGerenciador.setBounds(100, 100, 604, 328);
		frmGerenciador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGerenciador.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bem Vindo ao Ger\u00EAnciador de Satisfa\u00E7\u00E3o de Clientes");
		lblNewLabel.setFont(new Font("Georgia", Font.PLAIN, 22));
		lblNewLabel.setBounds(36, 21, 513, 26);
		frmGerenciador.getContentPane().add(lblNewLabel);
		
		JLabel lblPorFavorEscolha = new JLabel("Por Favor Escolha uma Op\u00E7\u00E3o Abaixo");
		lblPorFavorEscolha.setFont(new Font("Georgia", Font.PLAIN, 22));
		lblPorFavorEscolha.setBounds(106, 58, 370, 26);
		frmGerenciador.getContentPane().add(lblPorFavorEscolha);
		
		JButton btnGerenCad = new JButton("Ger\u00EAnciador de Cadastros");
		btnGerenCad.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					Cadastros cad = new Cadastros();
					cad.main(null);
				} catch (Exception e) {JOptionPane.showMessageDialog(null, e.getMessage());}
			}
		});
		btnGerenCad.setFont(new Font("Georgia", Font.PLAIN, 20));
		btnGerenCad.setBounds(140, 139, 308, 33);
		frmGerenciador.getContentPane().add(btnGerenCad);
		
		JButton btnGerenAtend = new JButton("Ger\u00EAnciador de Atendimento");
		btnGerenAtend.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					Atendimentos ats = new Atendimentos();
					ats.main(null);
				} catch (Exception e) {JOptionPane.showMessageDialog(null, e.getMessage());}
			}
		});
		btnGerenAtend.setFont(new Font("Georgia", Font.PLAIN, 20));
		btnGerenAtend.setBounds(140, 95, 308, 33);
		frmGerenciador.getContentPane().add(btnGerenAtend);
		
		JButton btnGerenRel = new JButton("Ger\u00EAnciador de Relat\u00F3rio");
		btnGerenRel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGerenRel.setFont(new Font("Georgia", Font.PLAIN, 20));
		btnGerenRel.setBounds(140, 183, 308, 33);
		frmGerenciador.getContentPane().add(btnGerenRel);
		
		JButton btnSair = new JButton("Sair do Programa");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSair.setFont(new Font("Georgia", Font.PLAIN, 20));
		btnSair.setBounds(140, 227, 308, 33);
		frmGerenciador.getContentPane().add(btnSair);
	}
}
