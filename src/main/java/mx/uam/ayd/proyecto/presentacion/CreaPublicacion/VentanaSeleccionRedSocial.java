package mx.uam.ayd.proyecto.presentacion.CreaPublicacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;
import java.awt.Dialog.ModalExclusionType;

@SuppressWarnings("serial")
@Component
public class VentanaSeleccionRedSocial extends JFrame {
	
	private ControlCrearPublicacion controlPublicacion;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSeleccionRedSocial frame = new VentanaSeleccionRedSocial();
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
	public VentanaSeleccionRedSocial() {
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		getContentPane().setBackground(Color.WHITE);
		setType(Type.POPUP);
		setResizable(false);
		
		setTitle("Eleccion Red Social");
		setBounds(100, 100, 239, 210);
		getContentPane().setLayout(null);
		
		JButton btnFacebook = new JButton("");
		btnFacebook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPublicacion.PublicacionFacebook();
			}
		});
		btnFacebook.setForeground(Color.WHITE);
		btnFacebook.setBackground(Color.WHITE);
		btnFacebook.setBounds(21, 42, 79, 86);
		ImageIcon imgFace=new ImageIcon("imagenes/facebook.png");
		Icon iconoFacebook = new ImageIcon(imgFace.getImage().getScaledInstance( btnFacebook.getWidth(),btnFacebook.getHeight(),Image.SCALE_DEFAULT));
		btnFacebook.setIcon(iconoFacebook);
		getContentPane().add(btnFacebook);
		
		JButton btnInstagram = new JButton("");
		btnInstagram.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPublicacion.PublicacionInstagram();
			}
		});
		btnInstagram.setForeground(Color.WHITE);
		btnInstagram.setBackground(Color.WHITE);
		btnInstagram.setBounds(122, 42, 79, 86);
		ImageIcon imgInsta=new ImageIcon("imagenes/instagram.png");
		Icon iconoInstagram = new ImageIcon(imgInsta.getImage().getScaledInstance( btnInstagram.getWidth(),btnInstagram.getHeight(),Image.SCALE_DEFAULT));
		btnInstagram.setIcon(iconoInstagram);
		getContentPane().add(btnInstagram);
	}
	public void muestraVentanaSeleccionRedSocial(ControlCrearPublicacion controlPublicacion) {
		this.controlPublicacion=controlPublicacion;
		setVisible(true);
	}
}
