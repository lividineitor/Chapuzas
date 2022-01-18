package mx.uam.ayd.proyecto.presentacion.CreaPublicacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Publicacion;

import javax.swing.border.CompoundBorder;

import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;
import mx.uam.ayd.proyecto.presentacion.publicacionProgramada.ControlProgramarPublicacion;
import mx.uam.ayd.proyecto.presentacion.cancelarPublicacion.ControlCancelarPublicacion;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
@Component
public class VentanaPublicaciones extends JFrame {
	
	private String [] encabezados= {"Titulo","Red social","Estado","Fecha Publicacion", "Accciones"};
	
	//datos para la tabla
	private Object [][] datosFila;
	
	//array para sacar datos de las publicaciones
	private ArrayList<Publicacion> misPublicaciones = new ArrayList<>();
	
	private ControlCrearPublicacion controlPublicacion;
	JPanel panelVista;
	private JPanel contentPane;
	private JTable tablePublicaciones;
	private ControlProgramarPublicacion controlPub;
	//private ControlCancelarPublicacion controlcancela;
	JTextPane textPanelPubs ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPublicaciones frame = new VentanaPublicaciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPublicaciones() {
		setType(Type.UTILITY);
		setTitle("Publicaciones");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\leonh\\OneDrive\\Imágenes\\Screenshots\\logo.png"));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		ImageIcon imgLogo=new ImageIcon("imagenes/sisgief.png");
		
		panelVista = new JPanel();
		panelVista.setBackground(Color.WHITE);
		panelVista.setBounds(10, 11, 611, 352);
		contentPane.add(panelVista);
		panelVista.setLayout(null);
				
		JButton btnNuevaPub = new JButton("New+");
		btnNuevaPub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPublicacion.nuevaPublicacion();
			}
		});
		btnNuevaPub.setForeground(Color.WHITE);
		btnNuevaPub.setBackground(new Color(0, 102, 102));
		btnNuevaPub.setBounds(507, 318, 89, 23);
		panelVista.add(btnNuevaPub);
		
		JLabel lblNewLabel = new JLabel("Mis publicaciones");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(185, 0, 188, 14);
		panelVista.add(lblNewLabel);
		
		tablePublicaciones = new JTable();
		tablePublicaciones.setBounds(26, 29, 554, 273);
		panelVista.add(tablePublicaciones);
		

		textPanelPubs = new JTextPane();
		textPanelPubs.setEditable(false);
		textPanelPubs.setBounds(21, 40, 575, 267);
		panelVista.add(textPanelPubs);
		
		JButton btnCancelar = new JButton("Cancelar una publicacion programada");
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPub.iniciacancelar();
				setVisible(false);
			}
		});
		btnCancelar.setBounds(76, 318, 217, 23);
		panelVista.add(btnCancelar);
	}
	
	
	public void muestraPublicaciones(ControlCrearPublicacion controlPublicacion, ArrayList<Publicacion> misPublicaciones) {
		this.controlPublicacion=controlPublicacion;
		this.misPublicaciones=misPublicaciones;
		

		if(!misPublicaciones.isEmpty())
			llenarTabla();
		
		setVisible(true);
	}
	
	//Dialogo para el estatus de la cancelacion de la publicacion
	public void muestraDialogoStatus(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
	
	//llenar tabla
	public void llenarTabla() {
		datosFila= new Object[misPublicaciones.size()][5];
		for (int i=0;i<misPublicaciones.size();i++) {
			datosFila[i][0] = misPublicaciones.get(i).getTitulo();
			datosFila[i][1] = misPublicaciones.get(i).getNombreRedSocial();
			datosFila[i][2] = (misPublicaciones.get(i).getFechaProgramada().isAfter(LocalDate.now()) ? "Por Publicar" :"Publicado");
			datosFila[i][3] = misPublicaciones.get(i).getFechaProgramada();
			datosFila[i][4]= "Cancelar";
		}
		tablePublicaciones.setModel(new DefaultTableModel(datosFila,encabezados));
		
	}
}
