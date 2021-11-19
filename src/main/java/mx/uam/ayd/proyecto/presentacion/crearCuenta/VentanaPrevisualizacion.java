package mx.uam.ayd.proyecto.presentacion.crearCuenta;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
@Component
public class VentanaPrevisualizacion extends JFrame {
	
	private ControlCrearCuenta controlCrearCuenta;
	private JPanel contentPane;
	private JLabel lblNombreValue,lblRolValue,lblCorreoValue,lblTelefonoValue,lblApellidoValue;
	
	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrevisualizacion frame = new VentanaPrevisualizacion();
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
	public VentanaPrevisualizacion() {
		setType(Type.UTILITY);
		setTitle("Previsualizacion");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 459, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(169, 58, 58, 14);
		contentPane.add(lblNombre);
		
		lblNombreValue = new JLabel("");
		lblNombreValue.setBounds(265, 58, 168, 14);
		contentPane.add(lblNombreValue);
		
		JLabel lblNewLabel = new JLabel("Apeliidos:");
		lblNewLabel.setBounds(169, 83, 58, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblRol = new JLabel("Rol:");
		lblRol.setBounds(10, 124, 46, 14);
		contentPane.add(lblRol);
		
		JLabel lblCorreo = new JLabel("Correo:");
		lblCorreo.setBounds(10, 149, 46, 14);
		contentPane.add(lblCorreo);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		lblTelefono.setBounds(10, 182, 46, 14);
		contentPane.add(lblTelefono);
		
		lblRolValue = new JLabel("");
		lblRolValue.setBounds(40, 124, 106, 14);
		contentPane.add(lblRolValue);
		
		JButton btnCrear = new JButton("Listo");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlCrearCuenta.nuevaCuenta();
			}
		});
		btnCrear.setBounds(335, 227, 89, 23);
		contentPane.add(btnCrear);
		
		lblCorreoValue = new JLabel("");
		lblCorreoValue.setBounds(63, 149, 159, 14);
		contentPane.add(lblCorreoValue);
		
		lblTelefonoValue = new JLabel("");
		lblTelefonoValue.setBounds(63, 182, 132, 14);
		contentPane.add(lblTelefonoValue);
		
		lblApellidoValue = new JLabel("");
		lblApellidoValue.setBounds(262, 83, 171, 14);
		contentPane.add(lblApellidoValue);
	}
	
	public void sWprevisualizacion(ControlCrearCuenta controlCrearCuenta,String nombre,String apellido,String rol, String correo, String telefono) {
		this.controlCrearCuenta=controlCrearCuenta;
		lblNombreValue.setText(nombre);
		lblApellidoValue.setText(apellido);
		lblCorreoValue.setText(correo);
		lblRolValue.setText(rol);
		lblTelefonoValue.setText(telefono);
		
		setVisible(true);
	}
}
