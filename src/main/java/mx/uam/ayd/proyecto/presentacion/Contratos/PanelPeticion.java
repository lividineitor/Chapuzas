package mx.uam.ayd.proyecto.presentacion.Contratos;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class PanelPeticion extends JPanel {
	private JTextField Nombre;
	private JTextField Apellido;
	private JTextField Correo;
	private JTextField textField;
	private JTextField Mensaje;

	/**
	 * Create the panel.
	 */
	public PanelPeticion(PetcionesOnline peticion) {
		setBackground(Color.WHITE);
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(45, 52, 46, 14);
		add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("apellido");
		lblApellido.setBounds(45, 89, 46, 14);
		add(lblApellido);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(45, 131, 46, 14);
		add(lblDireccion);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(45, 218, 46, 14);
		add(lblCorreo);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(45, 259, 46, 14);
		add(lblTelefono);
		
		JLabel lblNewLabel_1 = new JLabel("Mensaje Adicional");
		lblNewLabel_1.setBounds(45, 302, 101, 14);
		add(lblNewLabel_1);
		
		Nombre = new JTextField();
		Nombre.setEditable(false);
		Nombre.setBackground(Color.LIGHT_GRAY);
		Nombre.setBounds(190, 49, 86, 20);
		Nombre.setText(peticion.NombreDelCliente);
		add(Nombre);
		Nombre.setColumns(10);
		
		Apellido = new JTextField();
		Apellido.setEditable(false);
		Apellido.setText(peticion.Apellido);
		Apellido.setBackground(Color.LIGHT_GRAY);
		Apellido.setColumns(10);
		Apellido.setBounds(190, 86, 86, 20);
		add(Apellido);
		
		JTextPane Direccion = new JTextPane();
		Direccion.setEditable(false);
		Direccion.setBackground(Color.LIGHT_GRAY);
		Direccion.setText(peticion.Direccion);
		Direccion.setBounds(190, 131, 134, 59);
		add(Direccion);
		
		Correo = new JTextField();
		Correo.setEditable(false);
		Correo.setBackground(Color.LIGHT_GRAY);
		Correo.setColumns(10);
		Correo.setText(peticion.email);
		Correo.setBounds(190, 215, 134, 20);
		add(Correo);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBackground(Color.LIGHT_GRAY);
		textField.setColumns(10);
		textField.setBounds(190, 256, 134, 20);
		textField.setText(peticion.MensajeAdional);
		add(textField);
		
		JTextPane Mensaje = new JTextPane();
 		Mensaje.setEditable(false);
		Mensaje.setBackground(Color.LIGHT_GRAY);
 		Mensaje.setBounds(190, 299, 170, 104);
		Mensaje.setText(peticion.MensajeAdional);
		add(Mensaje);

	}
}
