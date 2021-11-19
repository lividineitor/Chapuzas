package mx.uam.ayd.proyecto.presentacion.principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
@Component
public class VentanaSesionRedSocial extends JFrame {

	private JPanel contentPane;
	private ControlPrincipal control;
	private JTextField textUser;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSesionRedSocial frame = new VentanaSesionRedSocial();
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
	public VentanaSesionRedSocial() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\leonh\\Downloads\\AnalysisYDiseno-master\\AnalysisYDiseno-master\\imagenes\\facebook.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 279, 354);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 243, 293);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblLogoFace = new JLabel("");
		lblLogoFace.setBounds(94, 24, 50, 50);
		ImageIcon imgLogo=new ImageIcon("imagenes/facebook.png");
		Icon icono = new ImageIcon(imgLogo.getImage().getScaledInstance( lblLogoFace.getWidth(),lblLogoFace.getHeight(),Image.SCALE_DEFAULT));
		lblLogoFace.setIcon(icono);
		
		panel.add(lblLogoFace);
		
		textUser = new JTextField();
		textUser.setBounds(24, 133, 209, 20);
		panel.add(textUser);
		textUser.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(24, 112, 46, 14);
		panel.add(lblUsuario);
		
		JLabel lblPass = new JLabel("Password:");
		lblPass.setBounds(24, 164, 63, 14);
		panel.add(lblPass);
		
		JButton btnContinuar = new JButton("Continuar");
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textUser.getText().equals("") || passwordField.getText().equals("")) {
					muestraDialogoConMensaje("No puede enviar campos vacios");
					}else {control.redSocial(textUser.getText(), passwordField.getText());}
			}
		});
		btnContinuar.setForeground(Color.WHITE);
		btnContinuar.setBackground(new Color(102, 153, 255));
		btnContinuar.setBounds(83, 224, 89, 23);
		panel.add(btnContinuar);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(24, 189, 209, 20);
		panel.add(passwordField);
	}
	public void sWsesionReds(ControlPrincipal control) {
		
		this.control = control;
		
		setVisible(true);
	}
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
}
