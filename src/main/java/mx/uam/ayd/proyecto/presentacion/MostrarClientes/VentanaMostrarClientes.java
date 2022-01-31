package mx.uam.ayd.proyecto.presentacion.MostrarClientes;

import javax.swing.JFrame;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.presentacion.crearCuenta.VentanaFormularioCrearCuenta;

import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

@SuppressWarnings("serial")
@Component
public class VentanaMostrarClientes extends JFrame {
	private final JButton btnRespaldar = new JButton("Respaldar");
	private JTextField textFieldEmail;
	private ControlMostrarClientes control;
	
	public VentanaMostrarClientes() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnMensajes = new JButton("Mensajes");
		btnMensajes.setBounds(0, 11, 89, 23);
		getContentPane().add(btnMensajes);
		
		JButton Publicar = new JButton("Publicar");
		Publicar.setBounds(0, 36, 89, 23);
		getContentPane().add(Publicar);
		btnRespaldar.setBounds(0, 57, 89, 23);
		getContentPane().add(btnRespaldar);
		
		JButton btnAgenda = new JButton("Agenda");
		btnAgenda.setBounds(0, 80, 89, 23);
		getContentPane().add(btnAgenda);
		
		JButton btnContrato = new JButton("Contrato");
		btnContrato.setBounds(0, 103, 89, 23);
		getContentPane().add(btnContrato);
		
		JButton btnClientes = new JButton("Clientes");
		btnClientes.setForeground(Color.DARK_GRAY);
		btnClientes.setBackground(SystemColor.textHighlight);
		btnClientes.setBounds(0, 129, 89, 23);
		getContentPane().add(btnClientes);
		
		JButton btnConfiguracion = new JButton("Configuracion");
		btnConfiguracion.setBounds(0, 153, 89, 23);
		getContentPane().add(btnConfiguracion);
		
		JButton btnCerrarSesion = new JButton("Cerrar Sesion");
		btnCerrarSesion.setBounds(0, 174, 89, 23);
		getContentPane().add(btnCerrarSesion);
		
		JLabel lblNewLabel = new JLabel("¿En que periodo quieres conocer el registro de clientes?");
		lblNewLabel.setBounds(134, 36, 266, 40);
		getContentPane().add(lblNewLabel);
		
		JComboBox comboBoxPeriodo = new JComboBox();
		comboBoxPeriodo.setToolTipText("Hoy\r\nAyer\r\nEste mes\r\nEl mes pasado\r\nEste año\r\nEl año pasado\r\nDesde el Inicio");
		comboBoxPeriodo.setModel(new DefaultComboBoxModel(new String[] {"Hoy", "Ayer", "Este mes", "El mes pasado", "Este año", "El año pasado", "Desde el Inicio"}));
		comboBoxPeriodo.setBounds(202, 80, 65, 22);
		getContentPane().add(comboBoxPeriodo);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(293, 80, 89, 23);
		getContentPane().add(btnAceptar);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar por email");
		lblNewLabel_1.setBounds(161, 153, 133, 23);
		getContentPane().add(lblNewLabel_1);
		
		textFieldEmail = new JTextField();
		textFieldEmail.setToolTipText("email");
		textFieldEmail.setBounds(161, 194, 86, 20);
		getContentPane().add(textFieldEmail);
		textFieldEmail.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(293, 193, 89, 23);
		getContentPane().add(btnBuscar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(335, 227, 89, 23);
		getContentPane().add(btnCancelar);
		
		//Listeners
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.termina();
			}
		});
		
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String periodo = (String) comboBoxPeriodo.getSelectedItem();
				Date fecha = new Date();
				if(periodo=="Hoy")
				{
					control.BuscaClientesDia(fecha);
				}
				if(periodo=="Ayer")
				{
					fecha.setDate(fecha.getDate()-1);
					control.BuscaClientesDia(fecha);
				}
				if(periodo=="Este mes")
				{
					control.BuscaClientesMes(fecha);
				}
				if(periodo=="El mes pasado")
				{
					fecha.setMonth(fecha.getMonth()-1);
					control.BuscaClientesMes(fecha);
				}
				if(periodo=="Este año")
				{
					control.BuscaClientesAnnio(fecha);
				}
				if(periodo=="El año pasado")
				{
					fecha.setYear(fecha.getYear()-1);
					control.BuscaClientesAnnio(fecha);
				}
				if(periodo=="Desde el Inicio")
				{
					control.recuperaUsuarios();
				}
			}
		});
		
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textFieldEmail.getText().equals(""))
				{
					control.MuestraMensajeErrorVacio("No ingresaste nada.");
				}
				else {
			control.BuscarporEmail(textFieldEmail.getText());
				}
			}
	  });
	}
	
     public void muestra(ControlMostrarClientes control) {
		
		this.control = control;
		setVisible(true);
     }
     
}
