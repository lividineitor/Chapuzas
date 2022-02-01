package mx.uam.ayd.proyecto.presentacion.Contratos;
 import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.ContratosOnline;
import mx.uam.ayd.proyecto.negocio.modelo.PetcionesOnline;

import java.awt.Graphics;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

@SuppressWarnings("serial")
@Component
public class VentanaContratos extends JFrame {
	JPanel panel;
	JComboBox<ContratosOnline> listarContratos;
	JComboBox<PetcionesOnline> ListarPeticiones;
	JButton botonVerPeticiones ;
	JButton btonModificar;
	JButton botonsubir;
	JScrollPane panelPane;
/*	public static void main(String[] args) {
		VentanaContratos x = new VentanaContratos();
		x.setVisible(true);
		
	}*/
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public VentanaContratos() {
		setTitle("Contratos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 883, 638);
		getContentPane().setLayout(null);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(779, 575, 89, 23);
		getContentPane().add(btnCancelar);
		botonVerPeticiones = new JButton("Ver Peticiones");

		botonVerPeticiones.setBounds(10, 30, 135, 23);
		getContentPane().add(botonVerPeticiones);
		JButton botonverContratos = new JButton("Ver Contratos");
	
		botonverContratos.setBounds(10, 83, 135, 23);
		getContentPane().add(botonverContratos);
		
		btonModificar = new JButton("Modificar contrato");
	
		btonModificar.setBounds(10, 225, 153, 23);
		getContentPane().add(btonModificar);
		btonModificar.setVisible(false);
		
		listarContratos = new JComboBox<ContratosOnline>();
		listarContratos.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				cambioContrato();
			}
			
		});
		listarContratos.setEnabled(true);
		listarContratos.setBounds(225, 27, 244, 28);
		getContentPane().add(listarContratos);
		ListarPeticiones = new JComboBox<>();
		ListarPeticiones.setBounds(225, 27, 244, 28);
		getContentPane().add(ListarPeticiones);
		ListarPeticiones.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				cambioPeticion();
			}
			
		});
		
		JButton botonActualizar = new JButton("Actualizar");
	
		botonActualizar.setBounds(10, 269, 100, 23);
		getContentPane().add(botonActualizar);
		
		botonsubir = new JButton("Subir Contratos");
		botonsubir.setBounds(10, 128, 135, 23);
		getContentPane().add(botonsubir);
		botonsubir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.showOpenDialog(fileChooser);
		        try {
		            String ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
		            File f = new File(ruta);
		            JOptionPane.showMessageDialog(null, "Se Subio Correctamente");
		        } catch (NullPointerException e1) {
		            System.out.println("No se ha seleccionado ningún fichero");
		        } catch (Exception e1) {
		            System.out.println(e1.getMessage());
		        } finally {
		         
		        }	
			}
		});
		//Listeners
		botonActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizar();
			}
		});
		botonVerPeticiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verPetciones();
			}
		});
		btonModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        fileChooser.showOpenDialog(fileChooser);
		        try {
		            String ruta = fileChooser.getSelectedFile().getAbsolutePath();                                        
		            File f = new File(ruta);
		            JOptionPane.showMessageDialog(null, "Se modifico Correctamente");
		        } catch (NullPointerException e1) {
		            System.out.println("No se ha seleccionado ningún fichero");
		        } catch (Exception e1) {
		            System.out.println(e1.getMessage());
		        } finally {
		         
		        }
			}
		});
		
		botonverContratos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verContratos();
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				termina();
			}
			
		});
		
		Invisible_botones();	
	}
	public void Invisible_botones() {
		 btonModificar.setVisible(false);
		 listarContratos.setVisible(false);
		 botonsubir.setVisible(false);
	     ListarPeticiones.setVisible(false);
		 if(panelPane != null) {
			panelPane.setVisible(false);
		    panelPane.revalidate();
		 }
	}
	void cambioContrato() {
		if( panelPane!=null)
		panelPane.setVisible(false);
		if(listarContratos == null)
			return;
		ContratosOnline contrato= listarContratos.getItemAt(listarContratos.getSelectedIndex());
		if(contrato.nombreDelArchivo!="") {
			MostrarContrato(contrato.nombreDelArchivo);
			btonModificar.setEnabled(true);
			panelPane.setVisible(true);
			
		}
		
	}
	void cambioPeticion(){
		if( panelPane!=null)
			panelPane.setVisible(false);
		if(listarContratos == null)
			return;
		PetcionesOnline s= ListarPeticiones.getItemAt(ListarPeticiones.getSelectedIndex());
		
		if(s== null)return;
		if(s.nombreDelCliente!="") {
			botonsubir.setEnabled(true);
			panelPane = new JScrollPane(
					new PanelPeticion(s),
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
				); 
			panelPane.setBounds(270, 80, 464, 542); 
			getContentPane().add(panelPane); 
			panelPane.setVisible(true);

		}
	}
	public void verPetciones() {
		Invisible_botones();
		botonsubir.setVisible(true);
		botonsubir.setEnabled(false);
	    ListarPeticiones.setVisible(true);;
	
	}
	public void verContratos(){
		Invisible_botones();
		 btonModificar.setVisible(true);
		 btonModificar.setEnabled(false);
		 listarContratos.setVisible(true);;
	}
	public void actualizar() {
		ListarPeticiones.removeAllItems();
		ListarPeticiones.removeAllItems();
		httpsRequest("Peticiones");
		
		
	}

	public void termina() {
		this.setVisible(false);		
	}
	public void MostrarContrato(String NombreArch) {
		
		panel = GetPdfPanel(NombreArch);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(-100);
		flowLayout.setHgap(-100);
		 
		CreaPanelDeDatos( panel);

		panel.revalidate();
		
	}
	public JScrollPane CreaPanelDeDatos(JPanel panel) {
		panelPane = new JScrollPane(panel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
			); 
		panelPane.setBounds(270, 80, 464, 542); 
		getContentPane().add(panelPane); 
		return panelPane;
	}
	public void verListaContratos() {
		
	}
	private void httpsRequest(String string) {
		ListarPeticiones.addItem(new PetcionesOnline("","","","","","",""));
		ListarPeticiones.addItem(new PetcionesOnline("Juan","Sanchez","una foto","calle Siempre viva 742 en Springfield","88-8@live.com.mx","55 42995420","Con algo que ver"));
		ListarPeticiones.addItem(new PetcionesOnline("Juan","Pelota","otra foto","calle donde vieve se la creyo","88-8a@live.com.mx","55 42995420", "Es perfecto"));
		listarContratos.addItem(new ContratosOnline("",""));
		listarContratos.addItem(new ContratosOnline("Contrato.pdf","Juan Perez"));
		listarContratos.addItem(new ContratosOnline("Contrato2.pdf","Juan Pelota"));
	}
    private static JPanel GetPdfPanel(String NombreArch) {
        PDDocument doc = null;
        try {
            doc = PDDocument.load(new File(NombreArch));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        PDFRenderer renderer = new PDFRenderer(doc);
        JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			@Override
            protected void paintComponent(Graphics g) {
                try {
                	 super.paintComponent(g);
                	 BufferedImage image = renderer.renderImage(0);
                	 g.drawImage(image, 0, 0, null);
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        
        return panel;
    }
}
