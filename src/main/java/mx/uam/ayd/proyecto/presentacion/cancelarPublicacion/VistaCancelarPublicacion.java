package mx.uam.ayd.proyecto.presentacion.cancelarPublicacion;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Publicacion;
//import mx.uam.ayd.proyecto.presentacion.publicacionProgramada.ControlProgramarPublicacion;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dimension;
import javax.swing.DropMode;
import java.awt.ComponentOrientation;
@SuppressWarnings("serial")
@Component

public class VistaCancelarPublicacion extends JFrame {
	private ControlCancelarPublicacion controlcanc;
	private JPanel contentPane;
	JTextArea txtPub = new 	JTextArea();
	private JTextField idAeliminar;
	
	public VistaCancelarPublicacion() {
		setSize(new Dimension(1366, 768));
		setType(Type.UTILITY);
		setTitle("Cancelar Publicacion");
		setBounds(100, 100, 638, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelVista = new JPanel();
		panelVista.setAutoscrolls(true);
		panelVista.setBackground(Color.WHITE);
		panelVista.setBounds(10, 11, 611, 352);
		contentPane.add(panelVista);
		panelVista.setLayout(null);
		txtPub.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		txtPub.setTabSize(10);
		txtPub.setDropMode(DropMode.INSERT);
		txtPub.setDragEnabled(true);
		
		txtPub.setToolTipText("Pubs");
		txtPub.setEditable(false);
		txtPub.setBounds(47, 58, 487, 225);
		panelVista.add(txtPub);
		
		JLabel lblNewLabel = new JLabel("Publicaciones posibles de cancelar:");
		lblNewLabel.setBounds(195, 11, 200, 50);
		panelVista.add(lblNewLabel);
		
		JLabel lbl2 = new JLabel("Selecciona el id de la Publicacion que quieras cancelar:");
		lbl2.setBounds(60, 302, 282, 50);
		panelVista.add(lbl2);
		
		idAeliminar = new JTextField();
		idAeliminar.setToolTipText("Mataaalaaaa");
		idAeliminar.setBounds(331, 317, 86, 20);
		panelVista.add(idAeliminar);
		idAeliminar.setColumns(10);
		
		JButton btnEliminaPid = new JButton("Eliminar");
		btnEliminaPid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idAeliminar.getText();
				controlcanc.eliminaprev(id);
			}
		});
		btnEliminaPid.setBounds(444, 316, 89, 23);
		panelVista.add(btnEliminaPid);
		
		JButton btnsalir = new JButton("Ya no quiero");
		btnsalir.setBounds(0, 291, 89, 23);
		btnsalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlcanc.salir();
			}
		});
		panelVista.add(btnsalir);
				
	}
	public void MostrarPublicaciones(ControlCancelarPublicacion control, List<Publicacion> Pubs)
	{
		this.controlcanc = control;
		Integer numpublicaciones = Pubs.size();
		txtPub.setText("");
		txtPub.append("Total: " + numpublicaciones.toString() + "\n");
		for(Publicacion hecha: Pubs)
		txtPub.append(hecha.toString() + "\n");
		
		setVisible(true);
	}
	
	public void termina()
	{
		setVisible(false);
	}
}

