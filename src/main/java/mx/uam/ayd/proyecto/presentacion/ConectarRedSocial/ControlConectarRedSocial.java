package mx.uam.ayd.proyecto.presentacion.ConectarRedSocial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import mx.uam.ayd.proyecto.negocio.ServicioRedSocial;
import mx.uam.ayd.proyecto.negocio.ServicioRedSocials;
import mx.uam.ayd.proyecto.negocio.modelo.Publicacion;
import mx.uam.ayd.proyecto.presentacion.preferencia.ControlPreferencia;

@Component
public class ControlConectarRedSocial {
	@Autowired
	private ServicioRedSocials servicioRedSocial;
	
	@Autowired
	private VentanaSesionFacebook sesionFacebook;
	
	@Autowired
	private VentanaSesionInstagram sesionInstagram;
	
	@Autowired
	private ControlPreferencia controlPreferencia ;
	
	//atributo de clase que nos permite almacenar el id del usuario actual en el sistema 
	private long idUsuario;
	
	//control de inicio de ejecucion del apartado redsocial
	public void inicia(long idUsuario,String nombreRed) {
		this.idUsuario=idUsuario;
		
		if(nombreRed.equals("Facebook")) {
			if(servicioRedSocial.buscarInicioSesionFacebook(idUsuario)==false)
				sesionFacebook.muestraVentanaSesionFacebook(this);
			else {
				sesionFacebook.muestraDialogoStatus("Reconectando");
				conectarFacebooklAuto();
			}	
		}else {
			if(servicioRedSocial.buscarInicioSesionInstagram(idUsuario)==false)
				sesionInstagram.muestraVentanaSesionInstagram(this);
			else {
				sesionInstagram.muestraDialogoStatus("Reconectando");
				conectarInstagramAuto();
			}	
		}
		
	}
	
	
	//metodos para iniciar el proceso de conexion a la red social elegida
	
	//manera mecanica cuando el usuario del sistema no tiene o nunca ha ingresado su cuenta de la red social 
	public void conectarRedSocial(String nombreRedSocial, String usuario, String contrasenia) {
		switch (nombreRedSocial) {
			case "Facebook":
				if(servicioRedSocial.conectar(nombreRedSocial, usuario, contrasenia, idUsuario)) {
					sesionFacebook.muestraDialogoStatus("Conexion exitosa con Facebook");
					controlPreferencia.datosObtenidosLoginSocial(usuario, contrasenia, nombreRedSocial);
					sesionFacebook.setVisible(false);
				}else
					sesionFacebook.muestraDialogoStatus("Error usuario o contrasenia no validos");
				break;
			case "Instagram":
				if(servicioRedSocial.conectar(nombreRedSocial, usuario, contrasenia, idUsuario)) {
					sesionInstagram.muestraDialogoStatus("Conexion exitosa con instagram");
					controlPreferencia.datosObtenidosLoginSocial(usuario, contrasenia, nombreRedSocial);
					sesionInstagram.setVisible(false);
				}else
					sesionInstagram.muestraDialogoStatus("Error usuario o contrasenia no validos");
				break;
			default:
				System.out.println("Error en controlRedsocial");
		
		}
			
	}
	//manera automatica cuando ya existe una red social registrada con el usuario del sistema es muy limitado este metodo no usar si se implementan varia redes sociales
	public void conectarRedSocialAuto() {
		if(servicioRedSocial.autoConectar(idUsuario))
			sesionFacebook.muestraDialogoStatus("Conexion exitosa");
			//controlPreferencia.datosObtenidosLoginSocial(usuario, contrasenia, nombreRedSocial);
		else
			sesionFacebook.muestraDialogoStatus(" Error usuario o contrasenia no validos");
	}
	
	//manera automatica cuando ya existe una red social registrada con el usuario del sistema, en este caso solo facebook
	public void conectarFacebooklAuto() {
		if(servicioRedSocial.autoConectarFacebook(idUsuario))
			sesionFacebook.muestraDialogoStatus("Conexion exitosa facebook");
		else
			sesionFacebook.muestraDialogoStatus(" Error usuario o contrasenia no validos");
	}

	//manera automatica cuando ya existe una red social registrada con el usuario del sistema, en este caso solo instagram
	public void conectarInstagramAuto() {
		if(servicioRedSocial.autoConectaInstagram(idUsuario))
			sesionFacebook.muestraDialogoStatus("Conexion exitosa instagram");
		else
			sesionFacebook.muestraDialogoStatus(" Error usuario o contrasenia no validos");
	}
	
	public void subePublicacionFacebook(Publicacion publicacion) {
		if (servicioRedSocial.subePublicacionFacebook(publicacion)) 
			sesionFacebook.muestraDialogoStatus("Publicado");
		else
			sesionFacebook.muestraDialogoStatus("Error en la publicacion");
	}
	
	public void subePublicacionInstagram(Publicacion publicacion) {
		if (servicioRedSocial.subePublicacionInstagram(publicacion)) 
			sesionInstagram.muestraDialogoStatus("Publicado");
		else
			sesionInstagram.muestraDialogoStatus("Error en la publicacion");
	}
}
