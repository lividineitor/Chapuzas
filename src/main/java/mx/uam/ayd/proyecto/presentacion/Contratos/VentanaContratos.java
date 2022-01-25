package mx.uam.ayd.proyecto.presentacion.Contratos;
import mx.uam.ayd.proyecto.presentacion.Contratos.ControlContratos;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Component;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

@SuppressWarnings("serial")
@Component
public class VentanaContratos extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VentanaContratos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
    private static JPanel GetPdfPanel(String NombreArch) {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(new File(NombreArch));
        } catch (IOException e) {
            e.printStackTrace();
        }
        final PDFRenderer renderer = new PDFRenderer(doc);
        JPanel panel = new JPanel() {
        
			private static final long serialVersionUID = 1L;

			@Override
            protected void paintComponent(Graphics g) {
                try {
                    renderer.renderPageToGraphics(0, (Graphics2D) g, 0.5f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return panel;
    }
}
