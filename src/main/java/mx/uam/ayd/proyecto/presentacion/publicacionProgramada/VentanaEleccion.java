package mx.uam.ayd.proyecto.presentacion.publicacionProgramada;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import org.springframework.stereotype.Component;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;

@SuppressWarnings("serial")
@Component
public class VentanaEleccion extends JFrame {

	private ControlProgramarPublicacion controlPub;
	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaEleccion frame = new VentanaEleccion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public VentanaEleccion() {
		setType(Type.UTILITY);
		setResizable(false);
		
		setTitle("Eleccion Red Social");
		setBounds(100, 100, 239, 210);
		getContentPane().setLayout(null);
		
		JButton btnFacebook = new JButton("Facebook");
		btnFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPub.iValidacio("Facebook");
			}
		});
		btnFacebook.setForeground(Color.WHITE);
		btnFacebook.setBackground(new Color(51, 102, 204));
		btnFacebook.setBounds(24, 87, 79, 23);
		getContentPane().add(btnFacebook);
		
		JButton btnInstagram = new JButton("Instagram");
		btnInstagram.setEnabled(false);
		btnInstagram.setForeground(Color.WHITE);
		btnInstagram.setBackground(new Color(153, 102, 153));
		btnInstagram.setBounds(113, 87, 89, 23);
		getContentPane().add(btnInstagram);

	}
	public void sWselectRedS(ControlProgramarPublicacion controlPub) {
		this.controlPub = controlPub;
		
		setVisible(true);
	}
}
