package mx.uam.ayd.proyecto.presentacion.publicacionProgramada;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Publicacion;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Window.Type;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;

@SuppressWarnings("serial")
@Component
public class VentanaListaPublicaciones extends JFrame {

	private JPanel contentPane;
	
	private ControlProgramarPublicacion controlPub;
	JTextPane textPanelPubs ;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaPublicaciones frame = new VentanaListaPublicaciones();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	 */
	/**
	 * Create the frame.
	 */
	public VentanaListaPublicaciones() {
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
		
		JPanel panelVista = new JPanel();
		panelVista.setBackground(Color.WHITE);
		panelVista.setBounds(10, 11, 611, 352);
		contentPane.add(panelVista);
		panelVista.setLayout(null);
				
		JButton btnNuevaPub = new JButton("New+");
		btnNuevaPub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPub.nuevaPub();
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
		
		textPanelPubs = new JTextPane();
		textPanelPubs.setEditable(false);
		textPanelPubs.setBounds(21, 40, 575, 267);
		panelVista.add(textPanelPubs);
	}
	
	public void sWlistPub(ControlProgramarPublicacion controlPub,List<Publicacion> pubs) {
		this.controlPub = controlPub;
		if(pubs.isEmpty()) {
			textPanelPubs.setText("Sin publicaciones que mostrar");
		}else {
			for(Publicacion hecha: pubs)
			textPanelPubs.setText(hecha.toString());
		}
		
		setVisible(true);
	}
}
