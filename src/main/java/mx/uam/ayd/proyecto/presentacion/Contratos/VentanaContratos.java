package mx.uam.ayd.proyecto.presentacion.Contratos;
import mx.uam.ayd.proyecto.presentacion.Contratos.ControlContratos;
import javax.swing.JFrame;
import org.springframework.stereotype.Component;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;


//Iturbe Pineda

@SuppressWarnings("serial")
@Component
public class VentanaContratos extends JFrame {
	
	public VentanaContratos() {
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 379);
		getContentPane().setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(438, 306, 89, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnNewButton = new JButton("Ver Peticiones");
		btnNewButton.setBounds(27, 30, 135, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Ver Contratos");
		btnNewButton_1.setBounds(27, 63, 135, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Subir Contratos");
		btnNewButton_2.setBounds(27, 93, 135, 23);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Editar Contrato");
		btnNewButton_3.setBounds(27, 127, 135, 23);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Contratos Aceptados");
		btnNewButton_4.setBounds(27, 160, 135, 23);
		getContentPane().add(btnNewButton_4);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(207, 11, 320, 284);
		getContentPane().add(panel);
		
		//Listeners
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				termina();
			}
			
		});
	}
	public void termina() {
		this.setVisible(false);		
	}
}
