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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
@Component
public class VentanaContratos extends JFrame {
	JPanel panel;
	JComboBox listarContratos;
	public static void main(String[] args) {
		VentanaContratos x = new VentanaContratos();
		x.setVisible(true);
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VentanaContratos() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 912, 673);
		getContentPane().setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(750, 567, 89, 23);
		getContentPane().add(btnCancelar);
		
		JButton btnNewButton = new JButton("Ver Peticiones");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deshabilitaListaContratos();
			}
		});
		btnNewButton.setBounds(27, 30, 135, 23);
		getContentPane().add(btnNewButton);
		VentanaContratos a= this;
		JButton btnNewButton_1 = new JButton("Ver Contratos");
	
		btnNewButton_1.setBounds(27, 63, 135, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Subir Contratos");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deshabilitaListaContratos();
			}
		});
		btnNewButton_2.setBounds(27, 93, 135, 23);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Editar Contrato");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.habilitaListaContratos();
			}
		});
		btnNewButton_3.setBounds(27, 127, 135, 23);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Contratos Aceptados");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.deshabilitaListaContratos();
			}
		});
		btnNewButton_4.setBounds(27, 160, 135, 23);
		getContentPane().add(btnNewButton_4);
		
		panel =new  JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(-100);
		flowLayout.setHgap(-100);
		panel.setBounds(270, 11, 464, 542);
		getContentPane().add(panel);
		
		ScrollPane scrollPane = new ScrollPane();
		panel.add(scrollPane);
		
		listarContratos = new JComboBox();
		listarContratos.setEnabled(false);
		listarContratos.setBounds(27, 274, 116, 28);
		getContentPane().add(listarContratos);
		//Listeners
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				a.MostrarContrato("Contrato.pdf");
				a.habilitaListaContratos();
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				termina();
			}
			
		});
		
				
	}
	public void deshabilitaListaContratos() {
		listarContratos.setEnabled(false);
	}
	public void habilitaListaContratos() {
		listarContratos.setEnabled(true);
	}
	public void termina() {
		this.setVisible(false);		
	}
	public void MostrarContrato(String NombreArch) {
		panel.setVisible(false);
		panel = GetPdfPanel(NombreArch);
		
		int x =getX(), y =getY();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(-100);
		flowLayout.setHgap(-100);
	
		panel.setBounds(270, 11, 464, 542);

		panel.setVisible(true);
		panel.revalidate();
		panel.setBorder(new EmptyBorder(100, 100, 100, 100));
		
		getContentPane().add(panel);
		this.pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setBounds(x, y, 912, 673);
		panel.setBounds(270, 11, 464, 542);
		panel.revalidate();
		
	}
	public void verListaContratos() {
		
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
                    renderer.renderPageToGraphics(0, (Graphics2D) g, 1f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        return panel;
    }
}
