package mx.uam.ayd.proyecto.presentacion.preferencia ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane ;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Preferencia;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;

import java.awt.Color ;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;



@SuppressWarnings("serial")
@Component
public class VistaPreferencia extends JFrame {
	
	private JPanel contentPane ;

	private ControlPreferencia control ;

	private int ancho ;
	private final int margenx = 10 ;
	private final int margeny = 10 ;
	private String [] semana = new String [7] ;

	private int horaApertura = 0 ;
	private int horaCierre = 0 ;
	private int minutosApertura = 0 ;
	private int minutosCierre = 0 ;
	
	private JTextField textFieldRuta;
	private JTextField textFieldUsuarioF;
	private JPasswordField passwordFieldF;
	private JTextField textFieldI;
	private JPasswordField passwordFieldI;
	private final JFileChooser fc = new JFileChooser () ;
	private DefaultListModel listaFechas = new DefaultListModel ();
	private DefaultListModel listaFechasRegistradas = new DefaultListModel ();

	private ArrayList <LocalDate> temporalFecha = new ArrayList ();
	
	
	/**
	 * Muestra la ventana para Configurar el sistema
	 * @param
	 * @return
	 * */
	
	public VistaPreferencia () {
		
		// Valores de las preferencias nuevas que se guardarán
		
		Preferencia preferenciaTemporal = new Preferencia () ; // Mantiene los datos temporalmente hasta que se guarden o descarten 
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ancho = getWidth() - margenx * 2 ;
		
		// Contenido del panel
		
		marcoLogotipo ( preferenciaTemporal ) ;
		marcoHorario ( preferenciaTemporal ) ;
		marcoDescanso ( preferenciaTemporal ) ;
		marcoFacebook ( preferenciaTemporal ) ;
		marcoInstagram ( preferenciaTemporal ) ;
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(120, 660, 100, 25);
		contentPane.add(btnGuardar);
		
		btnGuardar.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				control.guardarPreferencia(preferenciaTemporal);
				mostrarMensajeExito(true);
			}

		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(10, 660, 100, 25);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				control.terminaPreferencia();
			}
		});
		
	}

	
	/**
	 * Inicializa la variable de control y permite mostrar la ventana para configurar el sistema
	 * @param ControlPreferencia
	 * @return
	 * */
	
	public void inicio ( ControlPreferencia control ) {
		this.control = control ;
		setVisible (true);	
	}

	/**
	 * Muestra el marco para elegir la ruta de la imagen para el logotipo de la empresa. Soporta imágenes en JPG y PNG.
	 * @return String: ruta de la imagen del logotipo
	 */
	
	private void marcoLogotipo (Preferencia preferencia ) {
				
		JPanel panelLogotipo = new JPanel();
		panelLogotipo.setBounds(margenx, margeny, 780 , 100);
		panelLogotipo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Logotipo" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));
		contentPane.add(panelLogotipo);
		
		JLabel lblRuta = new JLabel("Archivo");
		panelLogotipo.add(lblRuta);
		
		JButton btnSelectImage = new JButton("Seleccionar Imagen");
		btnSelectImage.setHorizontalAlignment(SwingConstants.LEFT);
		panelLogotipo.add(btnSelectImage);
		
		btnSelectImage.addActionListener(new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				int rVal = fc.showOpenDialog(btnSelectImage) ;
				
				if ( rVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					
					preferencia.setRutaLogotipo(file.getAbsolutePath()) ;
					
					lblRuta.setText(file.getAbsolutePath()) ;
				}
			}
		});
		
		JPanel panelPrevisualizar = new JPanel();
//		panelPrevisualizar.setSize ( panelLogotipo.getHeight() , panelLogotipo.getHeight() ) ;
		panelLogotipo.add(panelPrevisualizar);
				
	}
	
	/**
	 * Muestra el marco para elegir los días laborales y el horario de apertura y cierra
	 * @param
	 * @return
	 */
	 
	 
	
	private void marcoHorario (Preferencia preferencia) {
	
		JPanel panelHorario = new JPanel();
		panelHorario.setBounds(10, 120, 780, 150);
		panelHorario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Horario laboral" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));
		contentPane.add(panelHorario);
		
		JCheckBox lunes = new JCheckBox("Lunes");
		panelHorario.add(lunes);
		lunes.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED)
					semana [0] = "Monday" ;
				else
					semana [0] = null ; 
				preferencia.setDiasDescanso(actualizarSemana());
			}		
		});
		
		JCheckBox martes = new JCheckBox("Martes");
		panelHorario.add(martes);
		martes.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED)
					semana [1] = "Tuesday" ;
				else
					semana [1] = null ; 
				preferencia.setDiasDescanso(actualizarSemana());
			}		
		});

		
		JCheckBox miercoles = new JCheckBox("Miércoles");
		panelHorario.add(miercoles);
		miercoles.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED)
					semana [2] = "Wednesday" ;
				else
					semana [2] = null ; 
				preferencia.setDiasDescanso(actualizarSemana());
			}		
		});

		
		JCheckBox jueves = new JCheckBox("Jueves");
		panelHorario.add(jueves);
		jueves.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED)
					semana [3] = "Thursday" ;
				else
					semana [3] = null ; 
				preferencia.setDiasDescanso(actualizarSemana());
			}		
		});

		
		JCheckBox viernes = new JCheckBox("Viernes");
		panelHorario.add(viernes);
		viernes.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED)
					semana [4] = "Friday" ;
				else
					semana [4] = null ; 
				preferencia.setDiasDescanso(actualizarSemana());
			}		
		});

		
		JCheckBox sabado = new JCheckBox("Sábado");
		panelHorario.add(sabado);
		sabado.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED)
					semana [5] = "Saturday" ;
				else
					semana [5] = null ; 
				preferencia.setDiasDescanso(actualizarSemana());
			}		
		});


		JCheckBox domingo = new JCheckBox("Domingo");
		panelHorario.add(domingo);
		domingo.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED)
					semana [6] = "Sunday" ;
				else
					semana [6] = null ; 
				preferencia.setDiasDescanso(actualizarSemana());
			}		
		});
		
		JLabel lblApertura = new JLabel("Apertura");
		panelHorario.add(lblApertura);
				
		JComboBox aperturaHora = new JComboBox();
		panelHorario.add(aperturaHora);
		
		for (int i = 0 ; i < 24 ; i ++ )
			aperturaHora.addItem ( i ) ;
		
		aperturaHora.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED) {
					horaApertura = (int) aperturaHora.getSelectedItem();
					
					if (minutosApertura >= 0 )
						preferencia.setHoraDeApertura(LocalTime.of(horaApertura, minutosApertura));
				}
			}		
		});

		
		JComboBox aperturaMinutos = new JComboBox();
		panelHorario.add(aperturaMinutos);
		
		for (int i = 0 ; i < 60 ; i ++ )
			aperturaMinutos.addItem ( i ) ;

		aperturaMinutos.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED) {
					minutosApertura = (int) aperturaMinutos.getSelectedItem();
					
					if (horaApertura >= 0 )
						preferencia.setHoraDeApertura(LocalTime.of(horaApertura, minutosApertura));
				}
			}		
		});
		
		
		JLabel lblCierre = new JLabel("Cierre");
		panelHorario.add(lblCierre);
		
		JComboBox cierreHora = new JComboBox ();
		panelHorario.add(cierreHora);
		
		for (int i = 0 ; i < 24 ; i ++ )
			cierreHora.addItem ( i ) ;
		
		cierreHora.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED) {
					horaCierre = (int) cierreHora.getSelectedItem();
					
					if (minutosCierre >= 0 )
						preferencia.setHoraDeCierre(LocalTime.of(horaCierre, minutosCierre));
				}
			}		
		});
		
		
		JComboBox cierreMinutos = new JComboBox();
		panelHorario.add(cierreMinutos);
		
		for (int i = 0 ; i < 60 ; i ++ )
			cierreMinutos.addItem ( i ) ;
		
		cierreMinutos.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED) {
					minutosCierre = (int) cierreMinutos.getSelectedItem();
					
					if (horaCierre >= 0 )
						preferencia.setHoraDeCierre(LocalTime.of(horaCierre, minutosCierre));
				}
			}		
		});

		
		JLabel lblIntervaloCitas = new JLabel("Intervalo para citas (meses)");
		panelHorario.add(lblIntervaloCitas);
				
		JComboBox intervalo = new JComboBox();
		panelHorario.add(intervalo);
		
		for (int i = 0 ; i < 12 ; i ++ )
			intervalo.addItem ( i ) ;
		
		intervalo.addItemListener(new ItemListener () {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange () ==ItemEvent.SELECTED) {
					preferencia.setPeriodoParaCitas( (int) intervalo.getSelectedItem() );
				}
			}		
		});
		
	}
	
	

	/**
	 * Muestra el marco para elegir los días de descanso obligatorio
	 * @return
	 */
	
	private void marcoDescanso (Preferencia preferencia) {
		JPanel panelDescansos = new JPanel();
		panelDescansos.setBounds(10, 280, 780, 150);
		panelDescansos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Días de descanso" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));

		contentPane.add(panelDescansos);

		JList listDias = new JList( listaFechas );
		listDias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDias.setSelectedIndex ( 0 ) ;
		
		JScrollPane listScrollPane = new JScrollPane (listDias) ;
		listScrollPane.setBounds ( 10 , 290 , 150 , 50 ) ;
		panelDescansos.add(listScrollPane);
		
		mostrarFechas ();
		
		JButton btnAdd = new JButton("Agregar");
		panelDescansos.add(btnAdd);
		
		btnAdd.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e ) {
				listaFechasRegistradas.addElement(listDias.getSelectedValue());
				
				for (int i = 0 ; i < listaFechasRegistradas.getSize() ; i ++)
					temporalFecha.add( (LocalDate ) listaFechasRegistradas.get(i)) ;

				if (temporalFecha.size() != 0 )				
					preferencia.setDiasFeriados(temporalFecha);
			}
		});
		
		JList listDiasRegistrados = new JList( listaFechasRegistradas );
		listDiasRegistrados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listDiasRegistrados.setSelectedIndex ( 0 ) ;
		
		JScrollPane listScrollPaneRegistrados = new JScrollPane (listDiasRegistrados) ;
		listScrollPaneRegistrados.setBounds ( 10 , 290 , 150 , 50 ) ;
		panelDescansos.add(listScrollPaneRegistrados);
		
		JButton btnDelete = new JButton("Eliminar");
		panelDescansos.add(btnDelete);
		
		btnDelete.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e ) {
				listaFechasRegistradas.removeElement(listDiasRegistrados.getSelectedValue());
				
				for (int i = 0 ; i < listaFechasRegistradas.getSize() ; i ++)
					temporalFecha.add( (LocalDate) listaFechasRegistradas.get(i)) ;
				
				if (temporalFecha.size() != 0 )				
					preferencia.setDiasFeriados(temporalFecha);

			}
		});
		


	}
	
	/**
	 * 
	 */
	
	private void marcoFacebook (Preferencia preferencia) {
		JPanel panelFacebook = new JPanel();
		panelFacebook.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Facebook" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));
		panelFacebook.setBounds(10, 440, ancho, 100);
		contentPane.add(panelFacebook);
		
		JLabel lblUsuarioF = new JLabel("Usuario");
		panelFacebook.add(lblUsuarioF);
		
		textFieldUsuarioF = new JTextField();
		panelFacebook.add(textFieldUsuarioF);
		textFieldUsuarioF.setColumns(10);
		
		
		
		JLabel lblPassF = new JLabel("Contraseña");
		panelFacebook.add(lblPassF);
		
		passwordFieldF = new JPasswordField();
		passwordFieldF.setText("");
		panelFacebook.add(passwordFieldF);
		
		JList list_1 = new JList();
		panelFacebook.add(list_1);

	}
	
	private void marcoInstagram (Preferencia preferencia) {
		JPanel panelInstagram = new JPanel();
		panelInstagram.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Instagram" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));
		panelInstagram.setBounds(10, 550, ancho, 100);
		contentPane.add(panelInstagram);
		
		JLabel lblUsuarioI = new JLabel("Usuario");
		panelInstagram.add(lblUsuarioI);
		
		textFieldI = new JTextField();
		panelInstagram.add(textFieldI);
		textFieldI.setColumns(10);
		
		JLabel lblPassI = new JLabel("Contraseña");
		panelInstagram.add(lblPassI);
		
		passwordFieldI = new JPasswordField();
		passwordFieldI.setText("");
		panelInstagram.add(passwordFieldI);
		
		JList list_1_1 = new JList();
		panelInstagram.add(list_1_1);

	}
	
	// TEMPORAL: Sección que define los diálogos de éxito o error al momento de guardar las configuraciones
	
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
	
	private String [] actualizarSemana ()
	{
		int contador = 0 ;
		int indice = 0 ;
		
		for (String dia : semana )
			if (dia != null)
				contador ++ ;
		
		String [] semanaCompleta = new String [contador] ;
		
		for ( String dia : semana )
			if (dia != null)
			{
				semanaCompleta [indice] = dia ;
				indice ++ ;
			}
		return semanaCompleta ;
	}

	private void mostrarFechas () {
		listaFechas.clear();
		
		long dias = 0 ;
		
		LocalDate hoy = LocalDate.now () ;
		LocalDate siguienteAno = LocalDate.of( hoy.getYear() + 1 , 1 , 1) ;
		
		LocalDate fechaARecorrer = hoy ;
		
		while ( fechaARecorrer.isBefore(siguienteAno) ) {
			fechaARecorrer = hoy.plusDays(dias);
			listaFechas.addElement(fechaARecorrer );
			dias ++ ;
		}
	}

	
	private void mostrarFechasRegistradas ( ArrayList <LocalDate> fechas ) {
		listaFechasRegistradas.clear();
		
		for (LocalDate fecha : fechas )
			listaFechasRegistradas.addElement(fecha);
	}
	
}
