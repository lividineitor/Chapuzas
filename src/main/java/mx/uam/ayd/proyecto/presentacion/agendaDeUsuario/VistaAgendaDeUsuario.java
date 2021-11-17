package mx.uam.ayd.proyecto.presentacion.agendaDeUsuario ;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Cita;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

@SuppressWarnings("serial")
@Component
public class VistaAgendaDeUsuario extends JFrame {

	private JPanel contentPane;
	
	private ControlAgendaDeUsuario control;

	// Elementos accesorios para mantener el estado que mostraran las distintas listas

	
	private DefaultListModel listModel = new DefaultListModel () ;

	
	public VistaAgendaDeUsuario() {
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList( listModel );
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		
		JScrollPane listScrollPane = new JScrollPane ( list ) ;
		listScrollPane.setBounds(10, 50, 300, 250);
		getContentPane().add(listScrollPane);		
		
		JLabel lblCitas = new JLabel("Citas");
		lblCitas.setBounds(10, 10, 70, 15);
		contentPane.add(lblCitas);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(20, 312, 117, 25);
		contentPane.add(btnBorrar);
		
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.borrarCita ( (Cita) list.getSelectedValue());
				recargarLista();
			}
		});
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(193, 312, 117, 25);
		contentPane.add(btnCancelar);
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.termina();
			}
		});

	}
	
	public void inicio(ControlAgendaDeUsuario control ) {
				
		this.control = control;
		recargarLista () ;
		setVisible(true);
	}
	
	public void recargarLista () {
		listModel.clear () ;
		for (Cita cita:control.recargarLista() )
			listModel.addElement(cita);
	}
}
