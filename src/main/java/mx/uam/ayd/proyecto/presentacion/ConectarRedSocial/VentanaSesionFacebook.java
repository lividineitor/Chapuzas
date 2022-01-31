package mx.uam.ayd.proyecto.presentacion.ConectarRedSocial;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;

@SuppressWarnings("serial")
@Component
public class VentanaSesionFacebook extends JFrame {

	private JPanel contentPane;
	private ControlConectarRedSocial controlConectarRedSocial;
	private JTextField textUser;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSesionFacebook frame = new VentanaSesionFacebook();
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
	public VentanaSesionFacebook() {
		setResizable(false);
		setType(Type.POPUP);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\leonh\\Downloads\\AnalysisYDiseno-master\\AnalysisYDiseno-master\\imagenes\\facebook.png"));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
					controlConectarRedSocial.MuestraMensajeErrorVacio("No se pueden dejar campos vacios");
					}else {controlConectarRedSocial.conectarRedSocial("Facebook",textUser.getText(), passwordField.getText());}
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
	
	public void muestraVentanaSesionFacebook(ControlConectarRedSocial controlConectarRedSocial) {
		this.controlConectarRedSocial=controlConectarRedSocial;
		textUser.setText("");
		passwordField.setText("");
		setVisible(true);
	}
	
}
