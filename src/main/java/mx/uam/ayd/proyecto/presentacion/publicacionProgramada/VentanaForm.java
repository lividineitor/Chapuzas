package mx.uam.ayd.proyecto.presentacion.publicacionProgramada;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
@Component
public class VentanaForm extends JFrame {

	private JPanel contentPane;
	private ControlProgramarPublicacion controlPub;
	private JTextField txtTituloDeLa;
	private JTextField textAnio;
	private JTextField textMes;
	private JTextField textDia;
	private JTextField textHora;
	private JTextField textMinuto;
	private ArrayList<File> imagenes = new ArrayList<>();

	private ArrayList<File> videos = new ArrayList<>();

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaForm frame = new VentanaForm();
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
	public VentanaForm() {
		setType(Type.UTILITY);
		setTitle("Formulario");
		setResizable(false);
		
		setBounds(100, 100, 560, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 524, 323);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtTituloDeLa = new JTextField();
		txtTituloDeLa.setText("Titulo de la publicacion");
		txtTituloDeLa.setBounds(45, 28, 255, 20);
		panel.add(txtTituloDeLa);
		txtTituloDeLa.setColumns(10);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(10, 31, 46, 14);
		panel.add(lblTitulo);
		
		JTextArea txtrDescripcionDeLa = new JTextArea();
		txtrDescripcionDeLa.setText("Descripcion de la publicacion");
		txtrDescripcionDeLa.setBounds(10, 74, 504, 122);
		panel.add(txtrDescripcionDeLa);
		
		JLabel lblDescripcion = new JLabel("Descripcion de la publicacion:");
		lblDescripcion.setBounds(10, 59, 147, 14);
		panel.add(lblDescripcion);
		
		JButton btnImagen = new JButton("Imagen");
		btnImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser imagen = new JFileChooser();	
				imagen.showOpenDialog(imagen);
				imagenes.add(imagen.getSelectedFile());
			}
		});
		btnImagen.setForeground(Color.WHITE);
		btnImagen.setBackground(new Color(102, 153, 255));
		btnImagen.setBounds(10, 207, 69, 23);
		panel.add(btnImagen);
		
		JButton btnVideo = new JButton("Video");
		btnVideo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser video = new JFileChooser();	
				video.showOpenDialog(video);
				videos.add(video.getSelectedFile());
				}
		});
		btnVideo.setForeground(Color.WHITE);
		btnVideo.setBackground(new Color(102, 153, 255));
		btnVideo.setBounds(84, 207, 69, 23);
		panel.add(btnVideo);
		
		JButton btnProgramar = new JButton("Programar");
		btnProgramar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textDia.setEnabled(true);
				textMes.setEnabled(true);
				textAnio.setEnabled(true);
				textHora.setEnabled(true);
				textMinuto.setEnabled(true);
			}
		});
		btnProgramar.setBackground(new Color(102, 153, 255));
		btnProgramar.setForeground(Color.WHITE);
		btnProgramar.setBounds(410, 207, 89, 23);
		panel.add(btnProgramar);
		
		textDia = new JTextField();
		textDia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textDia.getText().length()>=2) {
					e.consume();
				}
			}
		});
		textDia.setEnabled(false);
		textDia.setBounds(388, 260, 34, 20);
		panel.add(textDia);
		textDia.setColumns(10);
		
		textMes = new JTextField();
		textMes.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textMes.getText().length()>=2) {
					e.consume();
				}
			}
		});
		textMes.setEnabled(false);
		textMes.setBounds(425, 260, 39, 20);
		panel.add(textMes);
		textMes.setColumns(10);
		
		textAnio = new JTextField();
		textAnio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textAnio.getText().length()>=4) {
					e.consume();
				}
			}
		});
		textAnio.setEnabled(false);
		textAnio.setBounds(474, 260, 39, 20);
		panel.add(textAnio);
		textAnio.setColumns(10);
		
		textHora = new JTextField();
		textHora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textHora.getText().length()>=2) {
					e.consume();
				}
			}
		});
		textHora.setEnabled(false);
		textHora.setBounds(412, 303, 40, 20);
		panel.add(textHora);
		textHora.setColumns(10);
		
		textMinuto = new JTextField();
		textMinuto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(textMinuto.getText().length()>=2) {
					e.consume();
				}
			}
		});
		textMinuto.setEnabled(false);
		textMinuto.setBounds(462, 303, 37, 20);
		panel.add(textMinuto);
		textMinuto.setColumns(10);
		
		JLabel lblFecha = new JLabel("Dia/Mes/Anio");
		lblFecha.setBounds(412, 241, 89, 14);
		panel.add(lblFecha);
		
		JLabel lblNewLabel = new JLabel("Hora:Minuto");
		lblNewLabel.setBounds(425, 280, 69, 14);
		panel.add(lblNewLabel);
		
		JLabel lblFacebook = new JLabel("Facebook");
		lblFacebook.setBounds(437, 11, 62, 14);
		panel.add(lblFacebook);
		
		JButton btnPublicar = new JButton("Publicar");
		btnPublicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtrDescripcionDeLa.getText().equals("") || txtTituloDeLa.getText().equals("")) {
					muestraDialogoConMensaje("El titulo y la descripcion no deben estar vacios");
				}else if(imagenes.isEmpty()&&videos.isEmpty()) {
					muestraDialogoConMensaje("Caso 1");
					controlPub.dPublicacion(txtrDescripcionDeLa.getText(), txtTituloDeLa.getText(), "Facebook");
				}else if(imagenes.size()>0 && videos.isEmpty()) {
					muestraDialogoConMensaje("Caso 2");
					controlPub.dPublicacion(txtrDescripcionDeLa.getText(), txtTituloDeLa.getText(), "Facebook", imagenes);
				}else if(imagenes.isEmpty() && videos.size()>0) {
					muestraDialogoConMensaje("Caso 3");
					controlPub.dPublicacion(txtrDescripcionDeLa.getText(), txtTituloDeLa.getText() , videos, "Facebook");
				}else {
					muestraDialogoConMensaje("Caso 4");
					controlPub.dPublicacion(txtrDescripcionDeLa.getText(), txtTituloDeLa.getText(), "Facebook", imagenes, videos);
				}
				
				
				// para la programacion
				
				if((textDia.getText().equals("")&& textMes.getText().equals("")&& textAnio.getText().equals(""))) {
					muestraDialogoConMensaje("Se tomara la fecha de hoy y la hora sera la actual");
					controlPub.pPublicacion();
				}else{
					LocalDate cuando =LocalDate.of(Integer.parseInt(textAnio.getText()), Integer.parseInt(textMes.getText()),Integer.parseInt(textDia.getText()));
					LocalTime hora = LocalTime.of(Integer.parseInt(textHora.getText()), Integer.parseInt(textMinuto.getText()), 0);
					controlPub.pPublicacion(cuando,hora);
				}
				
				
				
				/*else if((textDia.getText().equals("")&& textMes.getText().equals("")&& textAnio.getText().equals(""))) {
					muestraDialogoConMensaje("Se tomara la fecha de hoy y la hora sera la actual");
				}*/
				//
			}
		});
		btnPublicar.setBackground(new Color(51, 102, 255));
		btnPublicar.setForeground(Color.WHITE);
		btnPublicar.setBounds(229, 339, 89, 23);
		contentPane.add(btnPublicar);
		
		
	}
	public void sWformPub(ControlProgramarPublicacion controlPub) {
		this.controlPub = controlPub;
		
		textAnio.setText("");
		textMes.setText("");
		textDia.setText("");
		textHora.setText("");
		textMinuto.setText("");
		imagenes.clear();
		videos.clear();
		
		setVisible(true);
	}
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
	}
}
