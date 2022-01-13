package mx.uam.ayd.proyecto.presentacion.preferencia ;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import mx.uam.ayd.proyecto.negocio.modelo.Cita;

import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JLayeredPane;
import javax.swing.JToolBar;
import javax.swing.JTabbedPane;
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
	private JTextField textFieldRuta;

	private int ancho ;
	private final int margenx = 10 ;
	private final int margeny = 10 ;
	private JTextField textFieldUsuarioF;
	private JPasswordField passwordFieldF;
	private JTextField textFieldI;
	private JPasswordField passwordFieldI;
	/**
	 * Muestra la ventana para Configurar el sistema
	 * @param
	 * @return
	 * */
	
	public VistaPreferencia () {
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		ancho = getWidth() - margenx * 2 ;
		
		JPanel panelLogotipo = new JPanel();
		panelLogotipo.setBounds(margenx, margeny, ancho , 96);
		panelLogotipo.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Logotipo" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));
		contentPane.add(panelLogotipo);
		
		textFieldRuta = new JTextField();
		textFieldRuta.setHorizontalAlignment(SwingConstants.LEFT);
		panelLogotipo.add(textFieldRuta);
		textFieldRuta.setColumns(10);
		
		JButton btnSelectImage = new JButton("Seleccionar Imagen");
		btnSelectImage.setHorizontalAlignment(SwingConstants.LEFT);
		panelLogotipo.add(btnSelectImage);
		
		JPanel panelPrevisualizar = new JPanel();
//		panelPrevisualizar.setSize ( panelLogotipo.getHeight() , panelLogotipo.getHeight() ) ;
		panelLogotipo.add(panelPrevisualizar);
		
		JPanel panelHorario = new JPanel();
		panelHorario.setBounds(margenx, 118, ancho, 165);
		panelHorario.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Horario laboral" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));
		contentPane.add(panelHorario);
		
		JCheckBox lunes = new JCheckBox("Lunes");
		lunes.setVerticalAlignment(SwingConstants.TOP);
		lunes.setHorizontalAlignment(SwingConstants.TRAILING);
		panelHorario.add(lunes);
		
		JCheckBox martes = new JCheckBox("Martes");
		martes.setVerticalAlignment(SwingConstants.TOP);
		panelHorario.add(martes);
		
		JCheckBox miercoles = new JCheckBox("Miércoles");
		miercoles.setHorizontalAlignment(SwingConstants.LEFT);
		panelHorario.add(miercoles);
		
		JCheckBox jueves = new JCheckBox("Jueves");
		panelHorario.add(jueves);
		
		JCheckBox viernes = new JCheckBox("Viernes");
		panelHorario.add(viernes);
		
		JCheckBox sabado = new JCheckBox("Sábado");
		panelHorario.add(sabado);

		JCheckBox domingo = new JCheckBox("Domingo");
		panelHorario.add(domingo);
		
		JSpinner spinner = new JSpinner();
		panelHorario.add(spinner);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panelHorario.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panelHorario.add(lblNewLabel_2);
		
		JSpinner spinner_1 = new JSpinner();
		panelHorario.add(spinner_1);
		
		JSpinner spinner_3 = new JSpinner();
		panelHorario.add(spinner_3);
		

		
		JSpinner spinner_2 = new JSpinner();
		panelHorario.add(spinner_2);
		
		JPanel panelDescansos = new JPanel();
		panelDescansos.setBounds(margenx, 326, ancho, 100);
		panelDescansos.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Días de descanso" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));

		contentPane.add(panelDescansos);
		
		JButton btnCalendario = new JButton("Calendario");
		panelDescansos.add(btnCalendario);
		
		JButton btnAdd = new JButton("Agregar");
		panelDescansos.add(btnAdd);
		
		JButton btnDelete = new JButton("Eliminar");
		panelDescansos.add(btnDelete);
		
		JList listDias = new JList();
		panelDescansos.add(listDias);
		
		JPanel panelFacebook = new JPanel();
		panelFacebook.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Facebook" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));
		panelFacebook.setBounds(margenx, 463, ancho, 100);
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
		
		JPanel panelInstagram = new JPanel();
		panelInstagram.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 1) , "Instagram" , TitledBorder.LEFT , TitledBorder.DEFAULT_POSITION));
		panelInstagram.setBounds(10, 577, ancho, 100);
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

	
	/**
	 * Inicializa la variable de control y permite mostrar la ventana para configurar el sistema
	 * @param ControlPreferencia
	 * @return
	 * */
	
	public void inicio ( ControlPreferencia control ) {
		this.control = control ;
		setVisible (true);	
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
}
