package mx.uam.ayd.proyecto.presentacion.GestionarClientes;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color ;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioContrato;
import mx.uam.ayd.proyecto.negocio.modelo.Contrato;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDateTime;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

@SuppressWarnings("serial")
@Component
public class VistaGestionDeClientes extends JFrame {

	private JPanel contentPane ;
	
	private ControlGestionarClientes control ;

	// Campos necesarios para los datos solicitados
	
	private ArrayList <Usuario> clientes ;
	private ArrayList <Contrato> contratos ;
	
	// Campos necesarios para el funcionamiento de los elementos gráficos
	
	private DefaultListModel listaDeClientes = new DefaultListModel () ;
	private DefaultListModel listaDeContratos = new DefaultListModel () ;
	
	private Usuario usuarioSeleccionado ;
	
	private final JFileChooser fc = new JFileChooser ();
	
	private JLabel lblINombre = new JLabel ();
	private JLabel lblIApellido = new JLabel ();
	private JLabel lblITelefono = new JLabel ();
	private JLabel lblIMail = new JLabel ();
	private JLabel lblIFechaDeRegistro = new JLabel ();
	
	// Dimensiones de los elementos gráficos
	
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
		
			JList listaClientes = new JList( listaDeClientes );
			listaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			JScrollPane clientesScrollPane = new JScrollPane ( listaClientes );
			clientesScrollPane.setBounds (margen,margen,anchuraClientes,alturaMarco-margen*2) ;
			panelClientes.add(clientesScrollPane);

			listaClientes.addListSelectionListener(new ListSelectionListener () {
				public void valueChanged (ListSelectionEvent e) {
					if (!e.getValueIsAdjusting()) {
						if (listaClientes.getSelectedIndex() != -1) {
							usuarioSeleccionado = clientes.get(listaClientes.getSelectedIndex()) ;

							setEtiquetas(usuarioSeleccionado);
							listaDeContratos.clear();

							obtenerContrato( usuarioSeleccionado ) ;
						}
					}
				}
			});
			
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

			// Etiquetas interactivas
			
			int margenInteractivo = margenEtiqueta + anchuraEtiqueta + margen ;
			
			lblINombre.setBounds(margenInteractivo, margen, anchuraEtiqueta, alturaEtiqueta);
			panelClientes.add(lblINombre);

			lblIApellido.setBounds(margenInteractivo, alturaEtiqueta + margen + margenInterior, anchuraEtiqueta, alturaEtiqueta);
			panelClientes.add(lblIApellido);

			lblITelefono.setBounds(margenInteractivo, alturaEtiqueta*2+margen+margenInterior*2, anchuraEtiqueta, alturaEtiqueta);
			panelClientes.add(lblITelefono);

			lblIMail.setBounds(margenInteractivo, alturaEtiqueta*3+margen+margenInterior*3, anchuraEtiqueta, alturaEtiqueta);
			panelClientes.add(lblIMail);
			
			lblIFechaDeRegistro.setBounds(margenInteractivo, alturaEtiqueta*4+margen+margenInterior*4, anchuraEtiqueta, alturaEtiqueta);
			panelClientes.add(lblIFechaDeRegistro);
			
			// Botones
			
			JButton btnEliminarCliente = new JButton("Eliminar");
			btnEliminarCliente.setBounds (anchuraMarco - anchuraBtn*2 - margen*2, alturaMarco - alturaBtn - margen ,anchuraBtn, alturaBtn);
			panelClientes.add(btnEliminarCliente);
			
			btnEliminarCliente.addActionListener(new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					
					int respuesta ;
					
					if (listaClientes.getSelectedIndex() != -1 ) {
						usuarioSeleccionado = clientes.get(listaClientes.getSelectedIndex()) ;

						respuesta = control.confirmacion ( "¿Quiere eliminar al cliente?" ) ;

						if ( respuesta == JOptionPane.YES_OPTION) {
							respuesta = control.confirmacion ("¿Realmente quiere eliminar al cliente?") ;
							if (respuesta == JOptionPane.YES_OPTION) {
								if ( control.eliminarCliente(usuarioSeleccionado) ) {
									control.resultadoDeOperacion(1, "Se eliminó al cliente");
									setEtiquetas (null) ;
									obtenerClientes () ;
								}
								else
									control.resultadoDeOperacion ( 0 , "No se pudo eliminar el cliente" ) ;
							
							}
						}
						
					}
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
		
			JList listaContratos = new JList( listaDeContratos );
			listaContratos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listaContratos.setBounds (margen,margen,anchuraMarco-margen*2,alturaMarco-margen*3-alturaBtn);
			panelContratos.add(listaContratos);
						
			JButton btnEliminarContrato = new JButton("Eliminar");
			btnEliminarContrato.setBounds (anchuraMarco - anchuraBtn*2 - margen*2, alturaMarco - alturaBtn - margen ,anchuraBtn, alturaBtn);
			panelContratos.add(btnEliminarContrato);

			btnEliminarContrato.addActionListener(new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					Contrato contratoSeleccionado ;
					
					if (listaContratos.getSelectedIndex() != -1 ) {
						contratoSeleccionado = contratos.get(listaContratos.getSelectedIndex());
						
						if (control.confirmacion("¿Quire eliminar el contrato?") == JOptionPane.YES_OPTION) {
							if (control.eliminarContrato(contratoSeleccionado) ) {
								obtenerContrato(usuarioSeleccionado);
								control.resultadoDeOperacion( 1 , "Se eliminó el contrato" );
							}
							else {
								control.resultadoDeOperacion(0, "No se pudo eliminar el contrato");
							}
							
						}
						
					}
				}
			});
			
			JButton btnAniadir = new JButton("Añadir");
			btnAniadir.setBounds (anchuraMarco - anchuraBtn - margen, alturaMarco - alturaBtn - margen ,anchuraBtn, alturaBtn);
			panelContratos.add(btnAniadir);
			
			btnAniadir.addActionListener(new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					int rVal = fc.showOpenDialog ( btnAniadir );
					File archivo = fc.getSelectedFile() ;
					
					Contrato contratoTemporal = new Contrato () ;
					
					contratoTemporal.setFechaDeCreacion(LocalDateTime.now());
					contratoTemporal.setIdUsuario(usuarioSeleccionado.getIdUsuario());
					contratoTemporal.setRutaContrato(archivo.getAbsolutePath());
					
					if (control.guardarContrato(contratoTemporal) ) {
						control.resultadoDeOperacion(1, "El contrato se guardó con éxtio") ;
						obtenerContrato(usuarioSeleccionado);
					}
					
					else
						control.resultadoDeOperacion(0, "El contrato no se guardó") ;
				}
			});
		
		
		//Botones de la ventana
		
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds (ancho - anchuraBtn*2 - margen*2, alto - alturaBtn - margen ,anchuraBtn, alturaBtn);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
//				listaDeClientes.clear();
//				listaDeContratos.clear();
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
	
	/**
	 * Define los valores iniciales de la vista
	 * @param ControlGestionarClientes , ArrayList <Usiario>
	 *
	 */
	
	public void inicio ( ControlGestionarClientes control ) {
		
		this.control = control ;

		obtenerClientes () ;
		
		setVisible ( true ) ;
	}
	
	/**
	 * Método accesorio para solicitar los clientes existentes
	 * 
	 */

	public void obtenerClientes () {
		
		String nombreCliente ;
		
		this.clientes = control.obtenerClientes() ;
		
		if (!listaDeClientes.isEmpty())
			listaDeClientes.clear();
		
		for (Usuario usuario: clientes ) {
			nombreCliente = usuario.getApellido() + " " + usuario.getNombre() ;
			listaDeClientes.addElement(nombreCliente);
		}
	}
	
	/**
	 * Método accesorio para solicitar los contratos de un cliente, si los tiene
	 * @param Usuario
	 * 
	 */
	
	public void obtenerContrato (Usuario usuario) {

		String mensajeLista ;
		
		this.contratos = control.obtenerContrato(usuario) ;
		
		if (!listaDeContratos.isEmpty())
			listaDeContratos.clear();
		
		for (Contrato contrato : contratos ) {
			
			mensajeLista = contrato.getFechaDeCreacion().toString();
			
			listaDeContratos.addElement(mensajeLista);
		}
		
	}
	
	/**
	 * Fija los valores de las etiquedas interactivas
	 * @param Usuario o null
	 * 
	 */

	public void setEtiquetas (Usuario usuario ) {
		if (usuario != null ) {
			lblINombre.setText(usuario.getNombre());
			lblIApellido.setText(usuario.getApellido());
			lblITelefono.setText(usuario.getTelefono());
			lblIMail.setText(usuario.getEmail());
			lblIFechaDeRegistro.setText(usuario.getFechaderegistro().toString() );
		}
		
		else {
			lblINombre.setText("");
			lblIApellido.setText("");
			lblITelefono.setText("");
			lblIMail.setText("");
			lblIFechaDeRegistro.setText("");

		}

	}

}
