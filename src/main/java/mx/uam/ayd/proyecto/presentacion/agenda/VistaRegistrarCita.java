package mx.uam.ayd.proyecto.presentacion.agenda ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane ;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;


import javax.swing.JLabel;

import javax.swing.JTextField;


@SuppressWarnings("serial")
@Component
public class VistaRegistrarCita extends JFrame {
	
	private JPanel contentPane ;

	private ControlAgenda control ;	
	
	// Elementos accesorios para mantener el estado que mostraran las distintas listas
	
	private DefaultListModel listModel = new DefaultListModel () ;
	private Long idUsuario ;
	private LocalDateTime fecha ;

	private DefaultListModel listaDisponibles = new DefaultListModel () ;
	private JTextField tfNombre;
	private JTextField tfApellido;
	private JTextField tfEdad;
	private JTextField tfTel;
	private JTextField tfEmail;

	
	/**
	 * Muestra la ventana para agendar cita de un usuario nuevo
	 * @param
	 * @return
	 * */
	
	public VistaRegistrarCita () {
				
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistarCitaNueva = new JLabel("Registrar cita");
		lblRegistarCitaNueva.setBounds(10, 10, 200, 30);
		contentPane.add(lblRegistarCitaNueva);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 50, 100, 30);
		contentPane.add(lblNombre);
		
		tfNombre = new JTextField();
		tfNombre.setBounds(110, 50, 200, 30);
		contentPane.add(tfNombre);
		tfNombre.setColumns(30);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(10, 100, 100, 30);
		contentPane.add(lblApellido);
		
		tfApellido = new JTextField();
		tfApellido.setBounds(110, 100, 200, 30);
		contentPane.add(tfApellido);
		tfApellido.setColumns(30);
		
		JLabel lblEdad = new JLabel("Edad");
		lblEdad.setBounds(10, 150, 100, 30);
		contentPane.add(lblEdad);
		
		tfEdad = new JTextField();
		tfEdad.setBounds(110, 150, 200, 30);
		contentPane.add(tfEdad);
		tfEdad.setColumns(10);
		
		JLabel lblTel = new JLabel("Teléfono");
		lblTel.setBounds(10, 200, 100, 30);
		contentPane.add(lblTel);
		
		tfTel = new JTextField();
		tfTel.setBounds(110, 200, 200, 30);
		contentPane.add(tfTel);
		tfTel.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail");
		lblEmail.setBounds(10, 250, 100, 30);
		contentPane.add(lblEmail);
		
		tfEmail = new JTextField();
		tfEmail.setBounds(110, 250, 200, 30);
		contentPane.add(tfEmail);
		tfEmail.setColumns(50);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 300, 150, 30);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear () ;
				listaDisponibles.clear ();
				control.terminaRegistrarCita();
			}
		});
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(200, 300, 150, 30);
		contentPane.add(btnConfirmar);
		
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Usuario usuario = new Usuario () ;
				
				usuario.setNombre(tfNombre.getText());
				usuario.setApellido(tfApellido.getText());
				//usuario.setEdad(Integer.parseInt(tfEdad.getText()));
				usuario.setTelefono(tfTel.getText());
				usuario.setEmail(tfEmail.getText());
				usuario.setPermisos("Cliente");
				usuario.setFechaderegistro(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));

				Usuario usuarioCreado = control.crearUsuario(usuario) ;
				Cita cita = new Cita () ;
				
				cita.setFechaCreacion(LocalDateTime.now());
				cita.setFechaCita(fecha);
				cita.setEstado("Confirmado");
				
				boolean estado = control.crearCita ( cita , usuarioCreado ) ;
				
				mostrarMensajeExito(estado);
				
			}
		});

	}

	
	/**
	 * Inicializa la variable de control y permite mostrar la ventana para agendar citas
	 * @param ControlAgenarCita
	 * @return
	 * */
	
	public void inicio ( ControlAgenda control , Long idUsuario , LocalDateTime fecha ) {
		this.control = control ;
		this.idUsuario = idUsuario ;
		this.fecha = fecha ;
		setVisible (true);
	}
	
	public void mostrarMensajeExito ( boolean estado ) {
		
		String mensaje ;
		
		if ( estado )
			control.MuestraMensajeExito("Operación realizada con éxito.");
		
		else
			control.MuestraMensajeErrorInfraccion("Operación fallida.");

	}

}
