package mx.uam.ayd.proyecto.presentacion.crearCuenta;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
@Component
public class VentanaNuevoCliente extends JFrame {

	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textCorreo;
	private JTextField textTelefono;
	private JPasswordField passwordField;
	private JPasswordField passwordConfirmacion;
	
	private ControlCrearCuenta controlCrearCuenta;


	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaNuevoCliente frame = new VentanaNuevoCliente();
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
	public VentanaNuevoCliente() {
		setResizable(false);
		setTitle("Crear cuenta cliente");
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 319, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 283, 296);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textNombre = new JTextField();
		textNombre.setBounds(130, 24, 143, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre(s):");
		lblNombre.setBounds(74, 27, 54, 14);
		panel.add(lblNombre);
		
		textApellidos = new JTextField();
		textApellidos.setBounds(130, 55, 143, 20);
		panel.add(textApellidos);
		textApellidos.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(74, 58, 46, 14);
		panel.add(lblApellidos);
		
		textCorreo = new JTextField();
		textCorreo.setBounds(74, 122, 199, 20);
		panel.add(textCorreo);
		textCorreo.setColumns(10);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(27, 125, 46, 14);
		panel.add(lblCorreo);
		
		textTelefono = new JTextField();
		textTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textTelefono.getText().length()>=10) {
					e.consume();
				}
			}
		});
		textTelefono.setBounds(74, 153, 199, 20);
		panel.add(textTelefono);
		textTelefono.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(74, 184, 199, 20);
		panel.add(passwordField);
		
		passwordConfirmacion = new JPasswordField();
		passwordConfirmacion.setToolTipText("");
		passwordConfirmacion.setBounds(74, 215, 199, 20);
		panel.add(passwordConfirmacion);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(18, 156, 46, 14);
		panel.add(lblTelefono);
		
		JLabel lblContrasenia = new JLabel("Contraseña:");
		lblContrasenia.setBounds(10, 187, 63, 14);
		panel.add(lblContrasenia);
		
		JLabel lblContrasConf = new JLabel("Contraseña:");
		lblContrasConf.setBounds(10, 218, 63, 14);
		panel.add(lblContrasConf);
		
		JButton btnCrear = new JButton("Crear cuenta");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textNombre.getText().equals("")||textApellidos.getText().equals("")||textCorreo.getText().equals("")||textTelefono.getText().equals("")||passwordField.getText().equals("")||passwordConfirmacion.getText().equals("")) {
					muestraDialogoConMensaje("No se permiten campos vacios");
				}else if(!(passwordField.getText().equals(passwordConfirmacion.getText()))) {
					muestraDialogoConMensaje("Error las contraseñas deben ser iguales");
				}else{
					controlCrearCuenta.setRol("cliente");
					controlCrearCuenta.dCuenta(textNombre.getText(), textApellidos.getText(), textCorreo.getText(), textTelefono.getText(), passwordField.getText());
				}
			}
		});
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setBackground(new Color(0, 102, 102));
		btnCrear.setBounds(92, 258, 113, 23);
		panel.add(btnCrear);
	}
	
	public void sWcrearCuentaCliente(ControlCrearCuenta controlCrearCuenta) {
		this.controlCrearCuenta=controlCrearCuenta;
		textApellidos.setText("");
		textNombre.setText("");
		textCorreo.setText("");
		textTelefono.setText("");
		
		passwordField.setText("");
		passwordConfirmacion.setText("");
		setVisible(true);
	}
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}

}
