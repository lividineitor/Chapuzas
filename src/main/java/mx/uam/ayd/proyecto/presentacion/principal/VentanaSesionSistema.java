package mx.uam.ayd.proyecto.presentacion.principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
@Component
public class VentanaSesionSistema extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JPasswordField passwordField;
	private ControlPrincipal control;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaSesionSistema frame = new VentanaSesionSistema();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public VentanaSesionSistema() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Inicio de sesion");
		setType(Type.UTILITY);
		setResizable(false);
		setBounds(100, 100, 237, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelSInicio = new JPanel();
		panelSInicio.setBackground(Color.WHITE);
		panelSInicio.setBounds(10, 11, 201, 256);
		contentPane.add(panelSInicio);
		panelSInicio.setLayout(null);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(10, 116, 181, 20);
		panelSInicio.add(textUsuario);
		textUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Correo:");
		lblUsuario.setBounds(10, 96, 70, 14);
		panelSInicio.add(lblUsuario);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 162, 181, 20);
		panelSInicio.add(passwordField);
		
		JLabel lblpass = new JLabel("Contraseña:");
		lblpass.setBounds(10, 147, 103, 14);
		panelSInicio.add(lblpass);
		
		JLabel lblimagen = new JLabel("");
		lblimagen.setBounds(50, 11, 103, 82);
		ImageIcon imgLogo=new ImageIcon("imagenes/user.png");
		Icon icono = new ImageIcon(imgLogo.getImage().getScaledInstance( lblimagen.getWidth(),lblimagen.getHeight(),Image.SCALE_DEFAULT));
		lblimagen.setIcon(icono);
		panelSInicio.add(lblimagen);
		
		JButton btnNewButton = new JButton("Iniciar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textUsuario.getText().equals("")||passwordField.getText().equals("")) {
					control.MuestraMensajeErrorVacio("No puede enviar campos vacios");
				}else {
					control.validaDatos(textUsuario.getText(), passwordField.getText());
				}
			}
		});
		btnNewButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(102, 102, 153));
		btnNewButton.setBounds(53, 209, 89, 23);
		panelSInicio.add(btnNewButton);
	}
	
	
	public void sWsesionSis(ControlPrincipal control) {
		
		this.control = control;
		
		textUsuario.setText("");
		passwordField.setText("");
		
		setVisible(true);
	}
	
}