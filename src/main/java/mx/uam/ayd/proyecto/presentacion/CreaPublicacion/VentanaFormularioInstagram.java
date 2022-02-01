package mx.uam.ayd.proyecto.presentacion.CreaPublicacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;


@SuppressWarnings("serial")
@Component
public class VentanaFormularioInstagram extends JFrame {

	private ControlCrearPublicacion controlPublicacion;
	private JPanel contentPane;
	private JTextField campoTitulo;
	private JTextField campoDia;
	private JTextField campoMes;
	private JTextField campoAnio;
	private JTextArea CampoContenido;
	private JButton btnVid;
	JLabel label_redSocial ;
	private JFileChooser archivo = new JFileChooser();	
	private JLabel label_usr;
	private JLabel lblTitulo;
	private JLabel lblContenido;
	private JLabel lblCalendario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaFormularioInstagram frame = new VentanaFormularioInstagram();
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
	public VentanaFormularioInstagram() {
		setTitle("Formulario Publicacion Instagram");
		setResizable(false);
		setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
		setType(Type.POPUP);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		campoTitulo = new JTextField();
		campoTitulo.setToolTipText("Titulo Publicacion");
		campoTitulo.setBounds(53, 8, 195, 20);
		contentPane.add(campoTitulo);
		campoTitulo.setColumns(10);
		
		CampoContenido = new JTextArea();
		CampoContenido.setToolTipText("Contenido publicacion");
		CampoContenido.setBounds(10, 50, 465, 195);
		contentPane.add(CampoContenido);
		
		JButton btnPublicar = new JButton("Publicar");
		
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(campoTitulo.getText().equals("") || CampoContenido.getText().equals(""))
					controlPublicacion.MuestraMensajeErrorVacio("No pueden enviar los campos de titulo y contenido, vacios");
				else if (campoAnio.getText().equals("")|| campoDia.getText().equals("")||campoMes.getText().equals("")) {
					controlPublicacion.MuestraMensajeInformativo("Fecha de publicacion la actual");
					controlPublicacion.creaPublicacionNoProgramadaInstagram(campoTitulo.getText(), CampoContenido.getText());
				}else {
					muestraDialogoStatus("Fecha de publicacion: "+campoDia.getText()+"/"+campoMes.getText()+"/"+campoAnio.getText());
					controlPublicacion.creaPublicacionProgramadaInstagram(campoTitulo.getText(), CampoContenido.getText(),LocalDate.parse(campoAnio.getText()+"-"+campoMes.getText()+"-"+campoDia.getText()));
				}
			}
		});
		btnPublicar.setBounds(379, 309, 89, 23);
		contentPane.add(btnPublicar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPublicacion.cerrarFormulario("Instagram");
			}
		});
		
		btnCancelar.setBounds(10, 309, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnImg = new JButton("");
		btnImg.setBackground(Color.WHITE);
		btnImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File imagen = muestraVentanaFiles();
				if(controlPublicacion.nuevaImagen(imagen))
					muestraDialogoStatus("Imagen "+imagen+" aceptada");
				else
					muestraDialogoStatus("Imagen "+imagen+" no aceptada");
			}
		});
		btnImg.setBounds(10, 270, 33, 28);
		ImageIcon imgCamara=new ImageIcon("imagenes/ImagenIconoFolder.png");
		Icon iconoCam = new ImageIcon(imgCamara.getImage().getScaledInstance( btnImg.getWidth(),btnImg.getHeight(),Image.SCALE_DEFAULT));
		btnImg.setIcon(iconoCam);
		contentPane.add(btnImg);
		
		btnVid = new JButton("");
		btnVid.setBackground(Color.WHITE);
		btnVid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File video = muestraVentanaFiles();
				if(controlPublicacion.nuevoVideo(video))
					muestraDialogoStatus("Video "+video+" aceptado");
				else
					muestraDialogoStatus("Video "+video+" no aceptado");
			}
		});
		btnVid.setBounds(53, 270, 33, 28);
		ImageIcon imgVideo=new ImageIcon("imagenes/videoIconoFolder.png");
		Icon iconoVi = new ImageIcon(imgVideo.getImage().getScaledInstance( btnVid.getWidth(),btnVid.getHeight(),Image.SCALE_DEFAULT));
		btnVid.setIcon(iconoVi);
		contentPane.add(btnVid);
		
		JButton btnProgramar = new JButton("");
		btnProgramar.setBackground(Color.WHITE);
		btnProgramar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campoDia.setEnabled(true);
				campoMes.setEnabled(true);
				campoAnio.setEnabled(true);
			}
		});
		btnProgramar.setBounds(322, 270, 33, 28);
		ImageIcon imgCalendario=new ImageIcon("imagenes/calendarioIcono.png");
		Icon iconoCal = new ImageIcon(imgCalendario.getImage().getScaledInstance( btnProgramar.getWidth(),btnProgramar.getHeight(),Image.SCALE_DEFAULT));
		btnProgramar.setIcon(iconoCal);
		contentPane.add(btnProgramar);
		
		campoDia = new JTextField();
		campoDia.setEnabled(false);
		campoDia.setBounds(369, 271, 20, 27);
		contentPane.add(campoDia);
		campoDia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(campoDia.getText().length()>=2) {
					e.consume();
				}
			}
		});
		campoDia.setColumns(10);
		
		campoMes = new JTextField();
		campoMes.setEnabled(false);
		campoMes.setBounds(399, 271, 25, 27);
		contentPane.add(campoMes);
		campoMes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(campoMes.getText().length()>=2) {
					e.consume();
				}
			}
		});
		campoMes.setColumns(10);
		
		campoAnio = new JTextField();
		campoAnio.setEnabled(false);
		campoAnio.setBounds(435, 271, 40, 27);
		contentPane.add(campoAnio);
		campoAnio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(campoAnio.getText().length()>=4) {
					e.consume();
				}
			}
		});
		campoAnio.setColumns(10);
		
		label_redSocial = new JLabel("");
		label_redSocial.setBounds(435, 0, 40, 39);
		ImageIcon imgLogo=new ImageIcon("imagenes/InstagramIcono.png");
		Icon icono = new ImageIcon(imgLogo.getImage().getScaledInstance( label_redSocial.getWidth(),label_redSocial.getHeight(),Image.SCALE_DEFAULT));
		label_redSocial.setIcon(icono);
		
		contentPane.add(label_redSocial);
		
		lblTitulo = new JLabel("Titulo: ");
		lblTitulo.setBounds(10, 11, 46, 14);
		contentPane.add(lblTitulo);
		
		lblContenido = new JLabel("Contenido:");
		lblContenido.setBounds(10, 36, 65, 14);
		contentPane.add(lblContenido);
		
		lblCalendario = new JLabel("Dia/Mes/Anio");
		lblCalendario.setBounds(369, 256, 89, 14);
		contentPane.add(lblCalendario);
		
		
	}
	//Dialogo para el estatus de la publicacion
		public void muestraDialogoStatus(String mensaje ) {
			JOptionPane.showMessageDialog(this , mensaje);
		}
		public File muestraVentanaFiles() {
			archivo.showOpenDialog(archivo);
			return archivo.getSelectedFile();
		}
		
		public void muestraVentanaFormularioInstagram(ControlCrearPublicacion controlPublicacion) {
			this.controlPublicacion=controlPublicacion;
			
			campoAnio.setText("");
			campoDia.setText("");
			campoMes.setText("");
			campoTitulo.setText("");
			CampoContenido.setText("");
			campoAnio.setText("");
			setVisible(true);
		}

}
