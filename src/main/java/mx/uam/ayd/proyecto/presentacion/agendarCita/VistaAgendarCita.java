package mx.uam.ayd.proyecto.presentacion.agendarCita ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane ;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cita;

import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JLabel;



@SuppressWarnings("serial")
@Component
public class VistaAgendarCita extends JFrame {
	
	private JPanel contentPane ;

	private JPanel contentPane_agendarCita ;

	private ControlAgendarCita control ;
	
	private List <Cita> citas ;
	
	
	// Elementos accesorios para mantener el estado que mostraran las distintas listas
	
	private DefaultListModel listModel = new DefaultListModel () ;

	private DefaultListModel listaDisponibles = new DefaultListModel () ;

	
	/**
	 * Muestra la ventana para agendar cita
	 * @param
	 * @return
	 * */
	
	public VistaAgendarCita () {
				
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
						JLabel lblElijaUnaFecha = new JLabel("Elija una fecha");
						lblElijaUnaFecha.setBounds(10, 10, 133, 30);
						contentPane.add(lblElijaUnaFecha);
						
						JList list_1 = new JList( listaDisponibles );
						list_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						list_1.setSelectedIndex(0);
						
						
						JScrollPane listScrollPane_1 = new JScrollPane ( list_1 ) ;
						listScrollPane_1.setBounds(350, 50, 300, 250);
						getContentPane().add(listScrollPane_1);
						
						JList list = new JList( listModel );
						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						list.setSelectedIndex(0);
						
						
						JScrollPane listScrollPane = new JScrollPane ( list ) ;
						listScrollPane.setBounds(10, 50, 300, 250);
						getContentPane().add(listScrollPane);		
								
						JLabel lblHorariosDisponibles = new JLabel("Horarios Disponibles");
						lblHorariosDisponibles.setBounds(350, 10, 148, 30);
						contentPane.add(lblHorariosDisponibles);

						JLabel lblEscribaUnMensaje = new JLabel("Escriba un mensaje");
						lblEscribaUnMensaje.setBounds(690, 10, 150, 30);
						contentPane.add(lblEscribaUnMensaje);
						
						JTextArea textPane = new JTextArea();
						textPane.setLineWrap(true);
						
						JScrollPane textScrollPane = new JScrollPane ( textPane ) ;
						textScrollPane.setBounds(690, 50, 300, 250);
						textScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
						textScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
						getContentPane().add(textScrollPane);		
						
						JButton btnDisponibilidad = new JButton("Ver disponibilidad");
						btnDisponibilidad.setBounds(82, 329, 162, 25);
						contentPane.add(btnDisponibilidad);
						btnDisponibilidad.setToolTipText("Visualiza los horarios disponibles");
						
						btnDisponibilidad.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Cita cita = new Cita () ;
								LocalDate fecha = (LocalDate) list.getSelectedValue () ;
								cita.setFechaCita(fecha.atTime(0 ,0 ));
								cita.setIdUsuario (10) ;
								control.mostrarHorarios( cita );
							}
						});
						
						JButton btnAgendarCita = new JButton("Agendar cita");
						btnAgendarCita.setBounds(442, 329, 148, 25);
						contentPane.add(btnAgendarCita);
				
						btnAgendarCita.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								LocalDateTime fecha = ( LocalDateTime ) list_1.getSelectedValue() ;
								String mensaje = textPane.getText () ;
								control.mostrarAgendarCita ( fecha , mensaje ) ;
								
								Cita cita = new Cita () ;
								LocalDate fecha_1 = (LocalDate) list.getSelectedValue () ;
								cita.setFechaCita(fecha_1.atTime(0 ,0 ));
								cita.setIdUsuario (10) ;
								control.mostrarHorarios( cita );
							}
						});
						
						JButton btnCancelar = new JButton("Cancelar");
						btnCancelar.setBounds(285, 476, 117, 25);
						contentPane.add(btnCancelar);
						
						btnCancelar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								listModel.clear () ;
								listaDisponibles.clear ();
								control.termina();
							}
						});

		
	}

	
	/**
	 * Inicializa la variable de control y permite mostrar la ventana para agendar citas
	 * @param ControlAgenarCita
	 * @return
	 * */
	
	public void inicio ( ControlAgendarCita control , List <LocalDate> fechas ) {
		this.control = control ;
		setVisible (true);
		
		// debe llenar listModel
		
		for ( LocalDate cita:fechas )
			listModel.addElement(cita);
		
	}
	
	/**
	 * Muestra los horarios disponibles para una fecha determinada
	 * @param List <Cita>
	 * @return
	 * */
	
	public void mostrarHorarios ( List <LocalDateTime> horarios ) {

		listaDisponibles.clear();
		
		for (LocalDateTime hora:horarios)
			listaDisponibles.addElement( hora );
		
	}

	 /**
	  * Muestra un mensaje de éxito al crear una cita
	  * @param Cita
	  * @return
	  * */
	
	public void mostrarMensajeExito (Cita cita) {
		JOptionPane.showMessageDialog ( null , "Su cita para el " + cita.getFechaCita().toLocalDate() + " se realizó con éxito." ) ;
	}

	public void mostrarMensajeError () {
		JOptionPane.showMessageDialog ( null , "La cita ya está ocupada." ) ;
	}
	
}
