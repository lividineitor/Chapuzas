package mx.uam.ayd.proyecto.presentacion.MostrarClientes;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

import javax.swing.JEditorPane;
import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JTextPane;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
@SuppressWarnings("serial")
@Component
public class VentanaMostrarClientes2 extends JFrame {
	private ControlMostrarClientes control;
	JTextPane textPaneClientes = new JTextPane();
	
	public VentanaMostrarClientes2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		textPaneClientes.setBackground(Color.LIGHT_GRAY);
		textPaneClientes.setText("ayayay");
		textPaneClientes.setBounds(10, 11, 414, 222);
		getContentPane().add(textPaneClientes);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(335, 227, 89, 23);
		getContentPane().add(btnSalir);
		
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.termina2();
			}
		});
	}
	
	public void muestra(ArrayList<Usuario> usuarios,ControlMostrarClientes control)
	{
		this.control = control;
		setVisible(true);
		if (usuarios.size() > 0)
		{
			String cadena = usuarios.toString();
			//textPaneClientes.setText(cadena);
		} else
			textPaneClientes.setText("No hay clientes, intenta con otro!");
		
	}
}