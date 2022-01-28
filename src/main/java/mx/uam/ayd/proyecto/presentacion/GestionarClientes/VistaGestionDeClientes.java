package mx.uam.ayd.proyecto.presentacion.GestionarClientes;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color ;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Contrato;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JLabel;

@SuppressWarnings("serial")
@Component
public class VistaGestionDeClientes extends JFrame {

	private JPanel contentPane ;
	
	private ControlGestionarClientes control ;
	
	private ArrayList <Usuario> clientes ;
	private ArrayList <Contrato> contratos ;
	
	private int ancho = 650 ;
	private int alto = 550 ;
	
	private int anchuraBtn = 150 ;
	private int alturaBtn = 30 ;
	private int margen = 20 ;
	
	public VistaGestionDeClientes () {
		
		setBounds(100,100,ancho,alto);
		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// Panel de clientes
		
		int alturaMarco = 200 ;
		
		int anchuraMarco = ancho - margen*2 ;
		
		JPanel panelClientes = new JPanel () ;
		panelClientes.setBounds (margen , margen, anchuraMarco , alturaMarco );
		panelClientes.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Clientes" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));
		panelClientes.setLayout (null);
		contentPane.add(panelClientes);
		
			int anchuraClientes = 200 ;
		
			JList listaClientes = new JList();
			listaClientes.setBounds (margen,margen,anchuraClientes,alturaMarco-margen*2) ;
			panelClientes.add(listaClientes);

			// Etiquetas
			
			int margenEtiqueta = anchuraClientes + margen * 2 ;
			
			int alturaEtiqueta = 15 ;
			int anchuraEtiqueta = 100 ;
			
			int margenInterior = 10 ;
			
			JLabel lblNombre = new JLabel("Nombre: ");
			lblNombre.setBounds(margenEtiqueta, margen, anchuraEtiqueta, alturaEtiqueta);
			panelClientes.add(lblNombre);
			
			JLabel lblApellido = new JLabel("Apellido: ");
			lblApellido.setBounds(margenEtiqueta, alturaEtiqueta+margen+margenInterior, anchuraEtiqueta, alturaEtiqueta);
			panelClientes.add(lblApellido);
			
			JLabel lblTelefono = new JLabel("Teléfono: ");
			lblTelefono.setBounds(margenEtiqueta, alturaEtiqueta*2+margen+margenInterior*2, anchuraEtiqueta, alturaEtiqueta);
			panelClientes.add(lblTelefono);
	
			JLabel lblMail = new JLabel("E-mail: ");
			lblMail.setBounds(margenEtiqueta, alturaEtiqueta*3+margen+margenInterior*3, anchuraEtiqueta, alturaEtiqueta);
			panelClientes.add(lblMail);
			
			JLabel lblFechaDeRegistro = new JLabel("F/registro: ");
			lblFechaDeRegistro.setBounds(margenEtiqueta, alturaEtiqueta*4+margen+margenInterior*4, anchuraEtiqueta, alturaEtiqueta);
			panelClientes.add(lblFechaDeRegistro);

			// Botones
			
			JButton btnEliminarCliente = new JButton("Eliminar");
			btnEliminarCliente.setBounds (anchuraMarco - anchuraBtn*2 - margen*2, alturaMarco - alturaBtn - margen ,anchuraBtn, alturaBtn);
			panelClientes.add(btnEliminarCliente);
			
			btnEliminarCliente.addActionListener(new ActionListener () {
				public void actionPerformed (ActionEvent e) {
				}
			});
		
			JButton btnEditar = new JButton("Editar");
			btnEditar.setBounds (anchuraMarco - anchuraBtn - margen, alturaMarco - alturaBtn - margen ,anchuraBtn, alturaBtn);
			panelClientes.add(btnEditar);
			
			btnEditar.addActionListener(new ActionListener () {
				public void actionPerformed (ActionEvent e) {
				}
			});
		
		
		// Panel de contratos
		
			
		JPanel panelContratos = new JPanel () ;
		panelContratos.setBounds (margen , margen*2+alturaMarco, anchuraMarco , alturaMarco );
		panelContratos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Contratos" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));
		panelContratos.setLayout(null) ;
		contentPane.add(panelContratos);
		
			JList listaContratos = new JList();
			listaContratos.setBounds (margen,margen,anchuraMarco-margen*2,alturaMarco-margen*3-alturaBtn);
			panelContratos.add(listaContratos);
		
			JButton btnEliminarContrato = new JButton("Eliminar");
			btnEliminarContrato.setBounds (anchuraMarco - anchuraBtn*2 - margen*2, alturaMarco - alturaBtn - margen ,anchuraBtn, alturaBtn);
			panelContratos.add(btnEliminarContrato);

			btnEliminarContrato.addActionListener(new ActionListener () {
				public void actionPerformed (ActionEvent e) {
				}
			});
			
			JButton btnAniadir = new JButton("Añadir");
			btnAniadir.setBounds (anchuraMarco - anchuraBtn - margen, alturaMarco - alturaBtn - margen ,anchuraBtn, alturaBtn);
			panelContratos.add(btnAniadir);
			
			btnAniadir.addActionListener(new ActionListener () {
				public void actionPerformed (ActionEvent e) {
				}
			});
		
		
		//Botones de la ventana
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds (ancho - anchuraBtn*2 - margen*2, alto - alturaBtn - margen ,anchuraBtn, alturaBtn);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				control.terminaGestionClientes () ;
			}
		});
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds (ancho - anchuraBtn - margen, alto - alturaBtn - margen ,anchuraBtn, alturaBtn);
		contentPane.add(btnGuardar);
		
		btnGuardar.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
			}
		});
		
	}
	
	public void inicio ( ControlGestionarClientes control , ArrayList <Usuario> usuarios ) {
		this.control = control ;
		this.clientes = usuarios ;
		setVisible ( true ) ;
	}
}
