package mx.uam.ayd.proyecto.ModificarCuenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import mx.uam.ayd.proyecto.negocio.ServicioGrupo;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

/*
 * Modulo de control para la historia de usuario Modificar Cuenta
 * 
 */
@Component
public class ControlModificarCuenta {
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	//@Autowired
	//private ServicioGrupo servicioGrupo;
	
	@Autowired
	private VentanaModificarCuenta ventana;

	public void inicia(Usuario usuario) {
		
		ventana.muestra(this,usuario);
	}
	
	public void ModificaCuenta(String nombre, String apellido, String email,String telefono, String contraseña)
	{
		try {
			servicioUsuario.ModificaCuenta(nombre,apellido,email,telefono,contraseña);
			//ventana.MuestraDialogoconMensaje("Actualizado exitosamente");
		} catch(Exception e) {
			//ventana.muestraDialogoConMensaje("Error al agregar usuario: "+ex.getMessage());
		}
		termina();
	}
	
	public void termina() {
		ventana.setVisible(false);		
	}
}
