package mx.uam.ayd.proyecto.presentacion.CreaPublicacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Image;


import org.springframework.stereotype.Component;
import java.awt.Window.Type;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dialog.ModalExclusionType;

@SuppressWarnings("serial")
@Component
public class VentanaFormularioFacebook extends JFrame {
	
	private ControlCrearPublicacion controlPublicacion;
	private JPanel contentPane;
	private JTextField campoTitulo;
	private JTextField campoDia;
	private JTextField campoMes;
	private JTextField campoAnio;
	private JTextArea CampoContenido;
	private JButton btnVid;
	private JFileChooser archivo;	
	private JLabel label_usr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaFormularioFacebook frame = new VentanaFormularioFacebook();
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
	public VentanaFormularioFacebook() {
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
		campoTitulo.setBounds(10, 38, 195, 20);
		contentPane.add(campoTitulo);
		campoTitulo.setColumns(10);
		
		CampoContenido = new JTextArea();
		CampoContenido.setToolTipText("Contenido publicacion");
		CampoContenido.setBounds(10, 69, 465, 176);
		contentPane.add(CampoContenido);
		
		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.setBackground(new Color(51, 102, 204));
		btnPublicar.setForeground(Color.WHITE);
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(campoTitulo.getText().equals("") || CampoContenido.getText().equals(""))
					controlPublicacion.MuestraMensajeErrorVacio("No pueden enviar los campos de titulo y contenido, vacios");
				else if (campoAnio.getText().equals("")|| campoDia.getText().equals("")||campoMes.getText().equals("")) {
					controlPublicacion.MuestraMensajeInformativo("Fecha de publicacion la actual");
					controlPublicacion.creaPublicacionNoProgramadaFacebook(campoTitulo.getText(), CampoContenido.getText());
				}else {
					//muestraDialogoStatus("Fecha de publicacion: "+campoDia.getText()+"/"+campoMes.getText()+"/"+campoAnio.getText());
					controlPublicacion.MuestraMensajeInformativo("Fecha de publicacion: "+campoDia.getText()+"/"+campoMes.getText()+"/"+campoAnio.getText());
					controlPublicacion.creaPublicacionProgramadaFacebook(campoTitulo.getText(), CampoContenido.getText(),LocalDate.parse(campoAnio.getText()+"-"+campoMes.getText()+"-"+campoDia.getText()));
				}
			}
		});
		btnPublicar.setBounds(116, 309, 89, 23);
		contentPane.add(btnPublicar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlPublicacion.cerrarFormulario("Facebook");
			}
		});
		btnCancelar.setForeground(Color.WHITE);
		btnCancelar.setBackground(new Color(153, 0, 102));
		btnCancelar.setBounds(266, 309, 89, 23);
		contentPane.add(btnCancelar);
		
		JButton btnImg = new JButton("");
		btnImg.setBackground(Color.WHITE);
		btnImg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				File imagen = muestraVentanaFiles();
				if(controlPublicacion.nuevaImagen(imagen))
					muestraDialogoStatus("Imagen "+imagen+"aceptada");
				else
					muestraDialogoStatus("img "+imagen+" no aceptada");
			}
		});
		btnImg.setBounds(10, 270, 49, 28);
		ImageIcon imgCamara=new ImageIcon("imagenes/camara.png");
		Icon iconoCam = new ImageIcon(imgCamara.getImage().getScaledInstance( btnImg.getWidth(),btnImg.getHeight(),Image.SCALE_DEFAULT));
		btnImg.setIcon(iconoCam);
		contentPane.add(btnImg);
		
		btnVid = new JButton("");
		btnVid.setBackground(Color.WHITE);
		btnVid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File video = muestraVentanaFiles();
				if(controlPublicacion.nuevoVideo(video))
					muestraDialogoStatus("Imagen "+video+"aceptada");
				else
					muestraDialogoStatus("img "+video+" no aceptada");
			}
		});
		btnVid.setBounds(69, 270, 49, 28);
		ImageIcon imgVideo=new ImageIcon("imagenes/video.png");
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
		btnProgramar.setBounds(306, 270, 49, 28);
		ImageIcon imgCalendario=new ImageIcon("imagenes/calendario.png");
		Icon iconoCal = new ImageIcon(imgCalendario.getImage().getScaledInstance( btnProgramar.getWidth(),btnProgramar.getHeight(),Image.SCALE_DEFAULT));
		btnProgramar.setIcon(iconoCal);
		contentPane.add(btnProgramar);
		
		campoDia = new JTextField();
		campoDia.setEnabled(false);
		campoDia.setBounds(369, 271, 20, 20);
		contentPane.add(campoDia);
		campoDia.setColumns(10);
		
		campoMes = new JTextField();
		campoMes.setEnabled(false);
		campoMes.setBounds(399, 271, 25, 20);
		contentPane.add(campoMes);
		campoMes.setColumns(10);
		
		campoAnio = new JTextField();
		campoAnio.setEnabled(false);
		campoAnio.setBounds(435, 271, 40, 20);
		contentPane.add(campoAnio);
		campoAnio.setColumns(10);
		
		JLabel label_redSocial = new JLabel("");
		label_redSocial.setBounds(435, 0, 40, 39);
		ImageIcon imgLogo=new ImageIcon("imagenes/facebook.png");
		Icon icono = new ImageIcon(imgLogo.getImage().getScaledInstance( label_redSocial.getWidth(),label_redSocial.getHeight(),Image.SCALE_DEFAULT));
		label_redSocial.setIcon(icono);
		
		contentPane.add(label_redSocial);
		
		label_usr = new JLabel("");
		label_usr.setBounds(0, 0, 31, 27);
		ImageIcon imgusr=new ImageIcon("imagenes/userN.png");
		Icon iconoUsr = new ImageIcon(imgusr.getImage().getScaledInstance( label_usr.getWidth(),label_usr.getHeight(),Image.SCALE_DEFAULT));
		label_usr.setIcon(iconoUsr);
		contentPane.add(label_usr);
	}

	
	//Dialogo para el estatus de la publicacion
	public void muestraDialogoStatus(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
	public File muestraVentanaFiles() {
		archivo = new JFileChooser();
		archivo.showOpenDialog(archivo);
		return archivo.getSelectedFile();
	}
	
	public void muestraVentanaFormularioFacebook(ControlCrearPublicacion controlPublicacion) {
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
