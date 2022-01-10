package mx.uam.ayd.proyecto.presentacion.configuración ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane ;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cita;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;



@SuppressWarnings("serial")
@Component
public class VistaAgenda extends JFrame {
	
	private JPanel contentPane ;

	private ControlConfiguracion control ;
	
	// Elementos accesorios para mantener el estado que mostraran las distintas listas
	
	private DefaultListModel listaCitas = new DefaultListModel () ;
	private String [] cadenaFiltro = {"Todos" , "Pendiente" , "Confirmado" , "Realizado" , "Expirado"} ;
	
	/**
	 * Muestra la ventana para agendar cita
	 * @param
	 * @return
	 * */
	
	public VistaAgenda () {
				
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
						JLabel lblCitas = new JLabel("Citas recibidas");
						lblCitas.setBounds(10, 10, 150, 30);
						contentPane.add(lblCitas);
						
						JComboBox cbFiltro = new JComboBox();
						cbFiltro.setBounds(160, 10, 150, 30);
						contentPane.add(cbFiltro);
						
						for (String elemento:cadenaFiltro)
							cbFiltro.addItem(elemento);
						
						
						JButton btnFiltrar = new JButton("Filtrar");
						btnFiltrar.setBounds(320, 10, 150, 30);
						contentPane.add(btnFiltrar);
						
						btnFiltrar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								String elemento = ( String )cbFiltro.getSelectedItem();
								
								if (elemento.equals("Todos"))
									mostrarCitas ( control.obtenerCitas() ) ;
								else
									filtrarCitas (control.obtenerCitas() , elemento ) ;
							}
						});
						
						JList list = new JList( listaCitas );
						list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
						list.setSelectedIndex(0);
						
						
						JScrollPane listScrollPane = new JScrollPane ( list ) ;
						listScrollPane.setBounds(10, 50, 460, 250);
						getContentPane().add(listScrollPane);
						
						JButton btnConfirmar = new JButton("Confirmar");
						btnConfirmar.setBounds(10, 310, 150, 30);
						contentPane.add(btnConfirmar);
						
						btnConfirmar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Cita cita = ( Cita ) list.getSelectedValue() ;
								
								if (cita.getEstado().equals("Pendiente"))
								{
									cita.setEstado("Confirmado");
								
									boolean estado = false ;
								
									estado = control.confirmarCita( cita );
									mostrarMensajeExito(estado);
								}
								else
									mostrarMensajeErrorDeEstado();
								
								mostrarCitas ( control.obtenerCitas() ) ;
									
							}
						});
						
						JButton btnEliminar = new JButton("Eliminar");
						btnEliminar.setBounds(320, 310, 150, 30);
						contentPane.add(btnEliminar);
						
						btnEliminar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								Cita cita = ( Cita ) list.getSelectedValue() ;
								
								boolean estado = false ;
								
								estado = control.eliminarCita(cita);
								
								mostrarMensajeExito(estado);
								
								mostrarCitas ( control.obtenerCitas() ) ;
									
							}
						});
						
						JButton btnCancelar = new JButton("Cancelar");
						btnCancelar.setBounds(10, 370, 150, 30);
						contentPane.add(btnCancelar);
						
						btnCancelar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								listaCitas.clear ();
								control.terminaAgenda();
							}
						});
						
						JButton btnCrearCita = new JButton("Crear cita");
						btnCrearCita.setBounds(320, 370, 150, 30);
						contentPane.add(btnCrearCita);
						
						btnCrearCita.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								control.inicioCalendario();
							}
						});

		
	}

	
	/**
	 * Inicializa la variable de control y permite mostrar la ventana para agendar citas
	 * @param ControlAgenarCita
	 * @return
	 * */
	
	public void inicio ( ControlConfiguracion control ) {
		this.control = control ;
		setVisible (true);
		
		mostrarCitas ( control.obtenerCitas() ) ;
	}
	
	/**
	 * Muestra los horarios disponibles para una fecha determinada
	 * @param List <Cita>
	 * @return
	 * */
	
	public void mostrarCitas ( List <Cita> citas ) {

		listaCitas.clear();
		
		for (Cita cita:citas)
			listaCitas.addElement( cita );
			
	}
	
	public void filtrarCitas ( List <Cita> citas , String filtro )
	{
		listaCitas.clear();

		for ( Cita cita:citas )
		{
			if (cita.getEstado().equals(filtro))
				listaCitas.addElement(cita);
		}
		
	}

	public void mostrarMensajeExito ( boolean estado ) {
		
		String mensaje ;
		
		if ( estado )
			mensaje = "Operación realizada con éxito." ;
		
		else
			mensaje = "Operación fallida." ;
		
		JOptionPane.showMessageDialog ( null , mensaje ) ;
	}

	public void mostrarMensajeErrorDeEstado ()
	{
		JOptionPane.showMessageDialog ( null , "Sólo se pueden confirmar citas PENDIENTES." ) ;
	}
}
