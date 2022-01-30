package mx.uam.ayd.proyecto.presentacion.principal;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.ManejoDeMensajes.ControlManejoDeMensajes;
import mx.uam.ayd.proyecto.ModificarCuenta.ControlModificarCuenta;
import mx.uam.ayd.proyecto.negocio.ServicioRedSocial;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

import mx.uam.ayd.proyecto.presentacion.ConectarRedSocial.ControlConectarRedSocial;
import mx.uam.ayd.proyecto.presentacion.CreaPublicacion.ControlCrearPublicacion;

import mx.uam.ayd.proyecto.presentacion.MostrarClientes.ControlMostrarClientes;
import mx.uam.ayd.proyecto.presentacion.agenda.ControlAgenda;
import mx.uam.ayd.proyecto.presentacion.agendaDeUsuario.ControlAgendaDeUsuario;
import mx.uam.ayd.proyecto.presentacion.agendarCita.ControlAgendarCita;
//import mx.uam.ayd.proyecto.presentacion.agregarUsuario.ControlAgregarUsuario;
import mx.uam.ayd.proyecto.presentacion.crearCuenta.ControlCrearCuenta;
import mx.uam.ayd.proyecto.presentacion.listarUsuarios.ControlListarUsuarios;
import mx.uam.ayd.proyecto.presentacion.preferencia.ControlPreferencia;


/**
 * Esta clase lleva el flujo de control de la ventana principal
 * 
 * @author chapuzas
 *
 */
@Component
public class ControlPrincipal {

	
	
	@Autowired
	private ControlListarUsuarios controlListarUsuarios;
	
	@Autowired
	private ControlCrearCuenta controlCrearCuenta;
	

	@Autowired
	private ControlCrearPublicacion crearPublicacion;

	@Autowired
	private ControlAgendarCita controlAgendarCita ;
	
	@Autowired
	private ControlAgendaDeUsuario controlAgendaDeUsuario ;
	
	@Autowired
	private ControlAgenda controlAgenda ;

	@Autowired
	private ServicioRedSocial servicioRedSocial;
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	@Autowired
	private ControlMostrarClientes controlmostrarClientes;
	
	@Autowired
	private ControlModificarCuenta controlmodificarCuenta;
	
	@Autowired
	private ControlConectarRedSocial controlConectarRedSocial;
	
	@Autowired
	private ControlPreferencia controlPreferencia ;
	
	@Autowired
	private VentanaPrincipal ventana;
	
	
	@Autowired
	private VentanaSesionSistema ventanaSesionSistema;
	
	@Autowired
	private VentanaSesionRedSocial ventanaSesionRedSocial;
	
	@Autowired
	private ControlManejoDeMensajes mensajes;
	
	private boolean loginConect=false;


	// Campo de usuario que se envía a todos los controladores que lo requieran
	
	private Usuario usuario ;
	
	// Constructor

	public ControlPrincipal() {
		usuario = new Usuario();
		usuario.setPermisos("Administrador");
	}
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia() {

		ventana.muestra(this,loginConect,usuario.getPermisos());
	}
	
	/**
	 * Inicia el flujo de control de la ventana principal
	 * 
	 */
	public void inicia(Usuario usuario) {

		this.usuario = usuario ;
		//ventana.muestra(this);
	}



	public void agendarCita ( Usuario usuario ) {
		controlAgendarCita.inicio( usuario );
	}
	


	public void agendaDeUsuario ( Usuario usuario ) {
		controlAgendaDeUsuario.inicio ( usuario ) ;
	}
	

	public void agenda ( Usuario usuario ) {
		controlAgenda.inicio(usuario);
	}
	public Usuario getUsuario ()
	{
		return usuario;
	}
	
	/**
	 * Método que arranca la historia de usuario "Crear cuenta"
	 * 
	 */
	public void addUser() {
		if(loginConect==true) {
			controlCrearCuenta.inicio();
		}else {
		controlCrearCuenta.inicia();
		}
	}
	
	/**
	 * Método que arranca la historia de usuario "listar usuarios"
	 * 
	 */
	public void listarUsuarios() {
		controlListarUsuarios.inicia();
	}
	
	public void publicacion() {

		crearPublicacion.inicia(usuario.getIdUsuario());

	}
	
	public void recarga() {
		ventanaSesionRedSocial.sWsesionReds(this);
	}
	
	public void redSocial(String usuario, String password) {

		if(servicioRedSocial.conectar("Facebook",usuario, password,this.usuario.getIdUsuario())) {

			ventanaSesionRedSocial.setVisible(false);
			inicia();
		}
	}
	
	public void sesionSistema() {
		ventanaSesionSistema.sWsesionSis(this);
	}
	
	public void validaDatos(String user,String pass) {
		int cont=0;
		if(servicioUsuario.recuperaUsuarios().isEmpty()) {
			mensajes.MuestraMensajeErrorInexistencia("No reconozco la cuenta");
		}
		for(Usuario genr: servicioUsuario.recuperaUsuarios()) {
			if(cont==servicioUsuario.recuperaUsuarios().size()){
				mensajes.MuestraMensajeErrorInexistencia(pass);
			}else if(genr.getEmail().equals(user)&&genr.getContraseña().equals(pass)) {
				mensajes.MuestraMensajeInformativo("Bienvenido "+genr.getNombre());
				usuarioLogueado(genr);
				loginConect=true;
				ventanaSesionSistema.setVisible(false);
				inicia();
				break;
			}else if(genr.getEmail().equals(user)&&!genr.getContraseña().equals(pass)) {
				mensajes.MuestraMensajeErrorCampo("Verifica tus credenciales");
				break;
			}
			cont++;
		}
		
	}


	public void preferencias ( Usuario usuario ) {
		controlPreferencia.inicio( usuario );
	}

	public void logOut() {
		loginConect=false;
		inicia();
	}


	
	public void ModificarCuenta() {
		Usuario usuario = servicioUsuario.agregaUsuario("hola", "jummm", "ya casi", "me toooas?","kwdnaoidwa","si");
		usuario.setNombre("hola");
		controlmodificarCuenta.inicia(usuario);
	}
	
	public void MostrarClientes() {
		controlmostrarClientes.inicia();
	}
	
	///auxliares
	
	private void usuarioLogueado(Usuario usuario) {
		this.usuario=usuario;
	}
	

	//cambios nuevos metodos
	public void redSocialFacebook() {
		if(servicioUsuario.regresaIdUsuario(usuario.getNombre())==usuario.getIdUsuario())
			controlConectarRedSocial.inicia(usuario.getIdUsuario(),"Facebook");
		else
			System.out.println("Error redSocialFacebook");
	}
	public void redSocialInstagram() {
		if(servicioUsuario.regresaIdUsuario(usuario.getNombre())==usuario.getIdUsuario())
			controlConectarRedSocial.inicia(usuario.getIdUsuario(),"Instagram");
		else
			System.out.println("Error redSocialInstagram");
	}
	
	public void MuestraMensajeErrorVacio(String mensaje)
	{
		mensajes.MuestraMensajeErrorVacio(mensaje);
	}
}
