package mx.uam.ayd.proyecto.presentacion.cancelarPublicacion;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Frame;
@SuppressWarnings("serial")
@Component
public class VentanaConfirmar extends JFrame {
	ControlCancelarPublicacion control;
	String Id;
	public VentanaConfirmar() {
		setSize(new Dimension(412, 253));
		setTitle("Ultima oportunidad");
		setPreferredSize(new Dimension(10, 10));
		setMinimumSize(new Dimension(10, 10));
		setExtendedState(Frame.MAXIMIZED_HORIZ);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().setPreferredSize(new Dimension(100, 100));
		getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		getContentPane().setMinimumSize(new Dimension(50, 50));
		getContentPane().setMaximumSize(new Dimension(500, 500));
		getContentPane().setBounds(new Rectangle(5, 5, 5, 5));
		getContentPane().setSize(new Dimension(50, 50));
		setType(Type.POPUP);
		getContentPane().setLayout(null);
		
		JLabel lbl1 = new JLabel("Estas seguro de cancelar esa publicacion?");
		lbl1.setBounds(104, 25, 200, 50);
		getContentPane().add(lbl1);
		
		JButton btnNo = new JButton("No");
		btnNo.setBounds(82, 147, 89, 23);
		getContentPane().add(btnNo);
		
		JButton btnSi = new JButton("Sí");
		btnSi.setBounds(259, 147, 89, 23);
		getContentPane().add(btnSi);
		
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					control.elimina(Id);
					setVisible(false);
				}
				catch (Exception r){//Error mandado por el reposotory al tratar de realizar un delete
					String mensaje = ("Algo debe estar mal! Probablemente el id es incorrecto");
					muestraDialogoConMensaje(mensaje);
				}
				
			}
		});
	}
	
	public void muestra(ControlCancelarPublicacion control, String id)
	{
		Id = id;
		this.control = control;
		setVisible(true);
		
	}
	public void muestraDialogoConMensaje(String mensaje ) {
		JOptionPane.showMessageDialog(this , mensaje);
		setVisible(false);
	}

}
