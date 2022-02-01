package mx.uam.ayd.proyecto.presentacion.principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

@SuppressWarnings("serial")
@Component
public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	
	private ControlPrincipal control;
	private JLabel lblConectado;

	
	JButton btnPublicacion,btnContratos,btnListarUsuarios,btnInicioDeSesion,btnCrearCuenta,btnMostrarClientes,btnModifica,btnAgendarCita,btnAgenda,btnAgendaAdmin;

	private JButton btnLogOut;
	private JLabel lblNewLabel;
	private JButton btnNuevoCliente;
	private JButton btnGestionarClientes ;
	private JButton btnPreferencia;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\leonh\\OneDrive\\Imágenes\\Screenshots\\logo.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 839, 539);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setBounds(140, 0, 634, 31);
		contentPane.add(panelHeader);
		panelHeader.setLayout(null);
		
		JLabel lblEstudio = new JLabel("Estudio Reyes");
		lblEstudio.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstudio.setBounds(161, 11, 235, 14);
		panelHeader.add(lblEstudio);
		
		JPanel panelNav = new JPanel();


		panelNav.setBounds(0, 0, 141, 458);



		contentPane.add(panelNav);
		panelNav.setLayout(null);
		
		JLabel logo = new JLabel("");
		logo.setBounds(10, 11, 106, 105);
		ImageIcon imgLogo=new ImageIcon("imagenes/sisgief.png");
		Icon icono = new ImageIcon(imgLogo.getImage().getScaledInstance( logo.getWidth(),logo.getHeight(),Image.SCALE_DEFAULT));
		logo.setIcon(icono);
		panelNav.add(logo);
		
		btnPublicacion = new JButton("Publicacion");
		btnPublicacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.publicacion();
			}
		});

		btnPublicacion.setBounds(10, 195, 121, 23);
		panelNav.add(btnPublicacion);
		
		

	    btnContratos = new JButton("Contratos");
		btnContratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.contratos(control.getUsuario ());
			}
		});
		btnContratos.setBounds(10, 161, 121, 23);
		panelNav.add(btnContratos);
	
		
		btnListarUsuarios = new JButton("Listar usuarios");
		btnListarUsuarios.setBounds(10, 229, 121, 23);
		panelNav.add(btnListarUsuarios);
		
		btnLogOut = new JButton("Salir");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.logOut();
			}
		});
 

		btnLogOut.setBounds(23, 395, 89, 23);


		panelNav.add(btnLogOut);
		
		lblNewLabel = new JLabel("Chapuzas Company");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		lblNewLabel.setBounds(0, 433, 141, 14);

		panelNav.add(lblNewLabel);
		
		btnAgendarCita = new JButton("Agendar Cita");
		btnAgendarCita.setBounds(10, 263, 121, 23);
		panelNav.add(btnAgendarCita);
		
		btnAgenda = new JButton("Agenda");
		btnAgenda.setBounds(10, 297, 121, 23);
		panelNav.add(btnAgenda);
		
		btnAgendaAdmin = new JButton("Agenda Admin");
		btnAgendaAdmin.setBounds(10, 331, 121, 23);
		panelNav.add(btnAgendaAdmin);

		btnGestionarClientes = new JButton("Gestionar Clientes");
		btnGestionarClientes.setBounds(10, 127, 121, 23);
		panelNav.add(btnGestionarClientes);
		
		btnPreferencia = new JButton("Preferencias");

		btnPreferencia.setBounds(10, 365, 121, 23);

		panelNav.add(btnPreferencia);
		
		btnPreferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.preferencias( control.getUsuario () );
				}
		});
		
		btnGestionarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.gestionarClientes();
				}
		});
		
		btnAgendaAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.agenda( control.getUsuario () );
				}
		});
		btnAgenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.agendaDeUsuario( control.getUsuario () );
			}
		});
		btnAgendarCita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.agendarCita( control.getUsuario () );
			}
		});
		
		btnListarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.listarUsuarios();
			}
		});
	
		JPanel panelVista = new JPanel();

		panelVista.setBounds(150, 42, 624, 405);

		contentPane.add(panelVista);
		panelVista.setLayout(null);
		
		btnInicioDeSesion = new JButton("IniciaSesion");
		btnInicioDeSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.sesionSistema();
			}
		});
		btnInicioDeSesion.setBounds(350, 11, 113, 23);
		panelVista.add(btnInicioDeSesion);
		
		btnCrearCuenta = new JButton("Crear Cuenta");
		btnCrearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.addUser();
			}
		});
		btnCrearCuenta.setBounds(491, 11, 123, 23);
		panelVista.add(btnCrearCuenta);
		

		btnNuevoCliente = new JButton("Nuevo Cliente");
		btnNuevoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.addUser();
			}
		});
		btnNuevoCliente.setBounds(10, 371, 133, 23);
		//panelVista.add(btnNuevoCliente);
		
		
		btnModifica = new JButton("Modifica Cuenta");
		btnModifica.setBounds(153, 11, 109, 23);
		//panelVista.add(btnModifica);
		
		btnMostrarClientes = new JButton("Muestra Clientes");
		btnMostrarClientes.setBounds(10, 11, 133, 23);
		//panelVista.add(btnMostrarClientes);
		
		btnMostrarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				control.MostrarClientes();
			}
		});
		
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.ModificarCuenta();
			}
		});
		
	}
	
	public void muestra(ControlPrincipal control, boolean statusConect,String permiso) {
		
		this.control = control;
		
		btnCrearCuenta.setEnabled(!statusConect);
		btnInicioDeSesion.setEnabled(!statusConect);
		
		
		
		if(permiso.equals("Administrador")) {
			btnContratos.setVisible(statusConect);

			btnPublicacion.setEnabled(statusConect);
			btnListarUsuarios.setEnabled(statusConect);
			btnNuevoCliente.setVisible(statusConect);
			btnAgendaAdmin.setVisible(statusConect);
			btnAgendarCita.setVisible(!statusConect);
			btnAgenda.setVisible(!statusConect);
			btnMostrarClientes.setVisible(statusConect);
			btnGestionarClientes.setVisible(statusConect);
			btnPreferencia.setVisible(statusConect);
		}else if(permiso.equals("Cliente")) {
			btnAgendarCita.setVisible(statusConect);
			btnAgenda.setVisible(statusConect);
			
			
		}
		btnContratos.setVisible(statusConect);		
		btnPublicacion.setVisible(statusConect);
		btnListarUsuarios.setVisible(statusConect);
		btnAgendarCita.setVisible(statusConect);
		btnAgenda.setVisible(statusConect);
		btnCrearCuenta.setVisible(!statusConect);
		btnInicioDeSesion.setVisible(!statusConect);
		btnLogOut.setVisible(statusConect);
		btnModifica.setVisible(statusConect);
		
		
		
		
		
		setVisible(true);
	}
}
