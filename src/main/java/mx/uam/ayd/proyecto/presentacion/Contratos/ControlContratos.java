package mx.uam.ayd.proyecto.presentacion.Contratos;

import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

@Component
public class ControlContratos {
	VentanaContratos Ventana; 
	public ControlContratos() {
		Ventana= new VentanaContratos();
	}
	public void inicio(Usuario usuario) {
		Ventana.setVisible(true); 
		
	}

}
