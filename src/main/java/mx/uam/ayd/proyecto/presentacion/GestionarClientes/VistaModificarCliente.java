package mx.uam.ayd.proyecto.presentacion.GestionarClientes;

import javax.swing.JFrame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent ;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
@Component
public class VistaModificarCliente extends JFrame {

	private Usuario cliente ;
	private ControlGestionarClientes control ;
	
	// Campos que definen las normas de los elementos gráficos
	
	private final int margen = 20 ;
	private final int margenInterno = 10 ;
	
	private int alto = 200 ;
	private int ancho = 360 ;
	
	private int alturaEtiqueta = 20 ;
	private int anchuraEtiqueta = 100 ;
	
	private int alturaCampo = alturaEtiqueta ;
	private int anchuraCampo = 200 ;
	
	private int columnasCampo = 30 ;
	
	private int alturaBoton = 30 ;
	private int anchuraBoton = 100 ;
	
	
	private final int sangrado = anchuraEtiqueta + margen * 2 ;

	
	
	// Elementos de la interfaz gráfica
	
	private JPanel panel ;
	
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfTelefono;
	private JTextField tfEmail;
	
	@Autowired
	private VistaGestionDeClientes vistaClientes ;
	
	public VistaModificarCliente () {
		
		cliente = new Usuario () ;
		
		panel = new JPanel();
	
		setBounds(100,100,ancho,alto);
		panel.setBorder( new EmptyBorder(5,5,5,5));
		setContentPane(panel);
		panel.setLayout(null);

		
		// Etiquetas de campos
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds( margen , margen , anchuraEtiqueta , alturaEtiqueta );
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds( margen , alturaEtiqueta + margenInterno + margen , anchuraEtiqueta , alturaEtiqueta );
		panel.add(lblApellido);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds( margen , ( alturaEtiqueta + margenInterno ) * 2 + margen , anchuraEtiqueta , alturaEtiqueta );
		panel.add(lblTelefono);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds( margen , ( alturaEtiqueta + margenInterno ) * 3 + margen , anchuraEtiqueta , alturaEtiqueta );
		panel.add(lblEmail);

		
		// Campos de selección
		
		tfNombre = new JTextField();
		tfNombre.setBounds( sangrado , margen , anchuraCampo , alturaCampo );
		panel.add(tfNombre);
		tfNombre.setColumns( columnasCampo );
		
		tfApellido = new JTextField();
		tfApellido.setBounds( sangrado , alturaCampo + margenInterno + margen , anchuraCampo , alturaCampo );
		panel.add(tfApellido);
		tfApellido.setColumns( columnasCampo );
		
		tfTelefono = new JTextField();
		tfTelefono.setBounds( sangrado , ( alturaCampo + margenInterno ) * 2 + margen , anchuraCampo , alturaCampo );
		panel.add(tfTelefono);
		tfTelefono.setColumns( columnasCampo );
		
		tfEmail = new JTextField();
		tfEmail.setBounds( sangrado , ( alturaCampo + margenInterno ) * 3 + margen , anchuraCampo , alturaCampo );
		panel.add(tfEmail);
		tfEmail.setColumns( columnasCampo );

		
		// Fijar elementos en los campos de texto
		

		
		// Botones de control
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds( ancho - ( anchuraBoton + margen ) * 2 , alto - ( margen + alturaBoton ) , anchuraBoton , alturaBoton );
		panel.add(btnCancelar);
		
		btnCancelar.addActionListener( new ActionListener () {
			public void actionPerformed ( ActionEvent e ) {
				control.terminarModificarCliente();
			}
		});
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds( ancho - ( anchuraBoton + margen ) , alto - ( margen + alturaBoton ) , anchuraBoton , alturaBoton );
		panel.add(btnGuardar);
		
		btnGuardar.addActionListener( new ActionListener () {
			public void actionPerformed ( ActionEvent e ) {

				// Revisa que no se tengan campos vacíos
				
				if ( tfNombre.getText().length() == 0 || tfApellido.getText().length() == 0 || tfTelefono.getText().length() == 0 || tfEmail.getText().length() == 0 )
					control.resultadoDeOperacion(0, "No puede haber campos vacios");

				
				else {
					cliente.setNombre(tfNombre.getText());
					cliente.setApellido(tfApellido.getText());
					cliente.setTelefono(tfTelefono.getText());
					cliente.setEmail(tfEmail.getText());
					
					if (control.actualizarDatos(cliente))
						control.resultadoDeOperacion(1, "Actualización exitosa");
					else
						control.resultadoDeOperacion(0, "No se pueden actualizar los datos");
					
					vistaClientes.obtenerClientes();
					
					control.terminarModificarCliente() ;
					
				}
			}
		});
		
	}
	
	public void inicio (Usuario usuario , ControlGestionarClientes control ) {
		this.cliente = usuario ;
		this.control = control ;
		tfNombre.setText(cliente.getNombre());
		tfApellido.setText(cliente.getApellido());
		tfTelefono.setText(cliente.getTelefono());
		tfEmail.setText(cliente.getEmail());
		setVisible(true) ;
	}
}
