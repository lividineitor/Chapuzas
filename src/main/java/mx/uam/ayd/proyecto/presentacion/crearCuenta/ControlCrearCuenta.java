package mx.uam.ayd.proyecto.presentacion.crearCuenta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.uam.ayd.proyecto.ManejoDeMensajes.ControlManejoDeMensajes;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;

@Component
public class ControlCrearCuenta {

	@Autowired
	private ServicioUsuario servicioUsuario;

	@Autowired
	private ControlPrincipal controlPrincipal;
	
	@Autowired
	private VentanaFormularioCrearCuenta ventanaFormulario;
	
	@Autowired
	private VentanaNuevoCliente ventanaCliente;
	
	@Autowired
	private VentanaPrevisualizacion ventanaPrev;
	
	//variables de ayuda;
	private String nombres,apellidos,correo,telefono,contrasena,rol;
	
	@Autowired
	private ControlManejoDeMensajes mensajes;
	
	//por default el rol sera de administrador
	public ControlCrearCuenta() {
		this.rol="Administrador";
	}
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	
	
	public void inicia() {
		ventanaFormulario.sWcrearCuenta(this);
	}
	public void inicio() {
		ventanaCliente.sWcrearCuentaCliente(this);
	}
	
	public void dCuenta(String nombres, String apellidos,String correo,String telefono,String contrasena) {
		if(servicioUsuario.VerificaExistencia(correo)) {
			//ventanaFormulario.muestraDialogoConMensaje("Ventana prev");
			ventanaFormulario.setVisible(false);
			this.nombres=nombres;
			this.apellidos=apellidos;
			this.correo=correo;
			this.telefono=telefono;
			this.contrasena=contrasena;
			ventanaPrev.sWprevisualizacion(this, nombres, apellidos, rol, correo, telefono);
		}else {
			mensajes.MuestraMensajeErrorRepetido("Correo existente");
		}
		
		
		/*try {
			servicioUsuario.VerificaExistencia(correo);
			ventanaFormulario.muestraDialogoConMensaje("Ventana prev");
		}catch (Exception ex) {
			ventanaFormulario.muestraDialogoConMensaje("Error al agregar usuario: "+ex.getMessage());
		}*/
	}
	
	public void nuevaCuenta() {
		if(servicioUsuario.creaCuenta(nombres, apellidos, correo, telefono, contrasena, rol)) {
			ventanaPrev.setVisible(false);
			controlPrincipal.inicia();
		}
	}
	
	public void MuestraMensajeErrorVacio(String mensaje)
	{
		mensajes.MuestraMensajeErrorVacio(mensaje);
	}
	public void MuestraMensajeErrorCampo(String mensaje)
	{
		mensajes.MuestraMensajeErrorCampo(mensaje);
	}
}