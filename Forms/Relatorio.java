package Forms;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BD_Basicos.Utils;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Relatorio extends Utils
{
	private JFrame frmRelatorio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Relatorio window = new Relatorio();
					window.frmRelatorio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Relatorio() throws Exception
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRelatorio = new JFrame();
		frmRelatorio.setTitle("Ger\u00EAnciamento de Relat\u00F3rio de Satisfa\u00E7\u00E3o e Reclama\u00E7\u00E3o");
		frmRelatorio.setBounds(100, 100, 1000, 595);
		frmRelatorio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRelatorio.getContentPane().setLayout(null);
		
		final JPanel panel = new JPanel();
		panel.setBounds(165, 45, 650, 500);
		frmRelatorio.getContentPane().add(panel);
		
		JButton btnSpawn = new JButton("Spawn");
		btnSpawn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Graphics g = panel.getGraphics();
				
				g.setColor(Color.green);
				g.fillRect(0, panel.getHeight()-300, 100, 300);// X Y Width Height
				g.setColor(Color.blue);
				g.fillRect(100, panel.getHeight()-250, 100, 250);
				g.setColor(Color.gray);
				g.fillRect(200, panel.getHeight()-200, 100, 200);// X Y Width Height
				g.setColor(Color.orange);
				g.fillRect(300, panel.getHeight()-150, 100, 150);
				g.setColor(Color.yellow);
				g.fillRect(400, panel.getHeight()-100, 100, 100);// X Y Width Height
				g.setColor(Color.red);
				g.fillRect(500, panel.getHeight()-50, 100, 50);
				
				g.setColor(Color.BLACK);
				//g.drawLine(0, panel.getHeight(), 100, panel.getHeight()-300);
				//g.drawLine(100, panel.getHeight()-300, 200, panel.getHeight()-400);
			}
		});
		btnSpawn.setBounds(450, 11, 89, 23);
		frmRelatorio.getContentPane().add(btnSpawn);
	}
}
