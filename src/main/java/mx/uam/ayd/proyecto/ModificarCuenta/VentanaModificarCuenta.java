package mx.uam.ayd.proyecto.ModificarCuenta;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
@Component
public class VentanaModificarCuenta extends JFrame {
	
	private ControlModificarCuenta control;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldEmail;
	private JTextField textFieldTelefono;
	private JPasswordField passwordFieldContraseña;
	
	public VentanaModificarCuenta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(229, 54, 86, 20);
		getContentPane().add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JButton btnNewButton = new JButton("Mensajes");
		btnNewButton.setBounds(0, 11, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Agenda");
		btnNewButton_1.setBounds(0, 31, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Citas");
		btnNewButton_2.setBounds(0, 53, 89, 23);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Configuración");
		btnNewButton_3.setForeground(Color.DARK_GRAY);
		btnNewButton_3.setBackground(SystemColor.textHighlight);
		btnNewButton_3.setBounds(0, 76, 89, 23);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Cerrar Sesion");
		btnNewButton_4.setBounds(0, 98, 89, 23);
		getContentPane().add(btnNewButton_4);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(143, 57, 46, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(143, 80, 46, 14);
		getContentPane().add(lblApellido);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(229, 77, 86, 20);
		getContentPane().add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(143, 105, 46, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(143, 130, 46, 14);
		getContentPane().add(lblTelefono);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setEditable(false);
		textFieldEmail.setBounds(229, 99, 86, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		textFieldTelefono = new JTextField();
		textFieldTelefono.setBounds(229, 127, 86, 20);
		getContentPane().add(textFieldTelefono);
		textFieldTelefono.setColumns(10);
		
		JLabel lblContraseña = new JLabel("Contraseña");
		lblContraseña.setBounds(143, 168, 46, 14);
		getContentPane().add(lblContraseña);
		
		JButton btnGuardar = new JButton("Guardar Cambios");
		btnGuardar.setBounds(107, 210, 172, 23);
		getContentPane().add(btnGuardar);
		
		passwordFieldContraseña = new JPasswordField();
		passwordFieldContraseña.setBounds(229, 165, 86, 20);
		getContentPane().add(passwordFieldContraseña);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(304, 210, 89, 23);
		getContentPane().add(btnCancelar);
		
		
		//Listeners
		
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldNombre.getText().equals("") || textFieldApellido.getText().equals("")) {
					//muestraDialogoConMensaje("El nombre y el apellido no deben estar vacios");
				} else {
					//control.ModificaCuenta(usuario);
					control.ModificaCuenta(textFieldNombre.getText(), textFieldApellido.getText(), textFieldEmail.getText(),textFieldTelefono.getText(),passwordFieldContraseña.getText());
					//control.agregaUsuario(textFieldNombre.getText(), textFieldApellido.getText(), (String) comboBoxGrupo.getSelectedItem());
				} 
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.termina();
			}
		});
	}

	public void muestra(ControlModificarCuenta control, Usuario usuario) {
		// TODO Auto-generated method stub
		this.control = control;
		textFieldNombre.setText(usuario.getNombre());
		textFieldApellido.setText(usuario.getApellido());
		textFieldEmail.setText(usuario.getEmail());
		textFieldTelefono.setText(usuario.getTelefono());
		passwordFieldContraseña.setText(usuario.getContraseña());
		setVisible(true);
		
	}

}
