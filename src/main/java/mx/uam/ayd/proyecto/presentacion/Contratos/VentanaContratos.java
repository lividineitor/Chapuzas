package mx.uam.ayd.proyecto.presentacion.Contratos;
import mx.uam.ayd.proyecto.presentacion.Contratos.ControlContratos;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


//Iturbe Pineda

@Component
public class VentanaContratos extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
