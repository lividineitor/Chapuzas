package mx.uam.ayd.proyecto.presentacion.Contratos;
import mx.uam.ayd.proyecto.presentacion.Contratos.ControlContratos;
import javax.swing.JFrame;
import org.springframework.stereotype.Component;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


//Iturbe Pineda

@SuppressWarnings("serial")
@Component
public class VentanaContratos extends JFrame {
	
	public VentanaContratos() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(335, 227, 89, 23);
		getContentPane().add(btnCancelar);
		
		//Listeners
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				termina();
			}
			
		});
		this.setVisible(true);		
	}
	public void termina() {
		this.setVisible(false);		
	}
}
