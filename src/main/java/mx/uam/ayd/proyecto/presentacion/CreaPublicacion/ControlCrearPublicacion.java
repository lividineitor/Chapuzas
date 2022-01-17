package mx.uam.ayd.proyecto.presentacion.CreaPublicacion;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioPublicacion;
import mx.uam.ayd.proyecto.negocio.ServicioRedSocials;
import mx.uam.ayd.proyecto.negocio.modelo.Publicacion;
import mx.uam.ayd.proyecto.presentacion.ConectarRedSocial.ControlConectarRedSocial;

@Component
public class ControlCrearPublicacion {

	@Autowired
	private ServicioPublicacion servicioPublicacion;

	@Autowired
	private ServicioRedSocials servicioRedsocial;

	@Autowired
	private VentanaFormularioFacebook VentanaFormularioPubFacebook;

	@Autowired
	private VentanaFormularioInstagram ventanaFormularioInstagram;

	@Autowired
	private VentanaPublicaciones ventanaPublicaiones;

	@Autowired
	private VentanaSeleccionRedSocial ventanaSeleccionRedSocial;

	@Autowired
	private ControlConectarRedSocial controlConectarRedSocial;

	/**
	 * atributo de clase para posteriormente enlazar la publicacion con el usuario
	 * que la creo y mostarla en la lista de publicaciones
	 */
	private long id_usuario = 0;
	// atributto para guardar la redsocial elegida para publicar
	private String red = "";
	// atributo para guardar las imagenes que se aceptaron
	ArrayList<File> imagenes = new ArrayList<>();
	// atributo para guardar los videos que se aceptaron
	ArrayList<File> videos = new ArrayList<>();

	// metodo para iniciar la parte de publicaciones
	public void inicia(long id_usuario) {
		this.id_usuario = id_usuario;
		ventanaPublicaiones.muestraPublicaciones(this, servicioPublicacion.BuscaPublicacionesUsuario(id_usuario));

	}

	// metodo para hacer el llamado a la ventana de eleccion de red social
	public void nuevaPublicacion() {
		// llamado a la ventana de seleccion
		ventanaSeleccionRedSocial.muestraVentanaSeleccionRedSocial(this);

	}

	// metodo para validar inicios de sesion previo relacionado al ususario actual
	// del sistema
	public void PublicacionFacebook() {
		this.red = "Facebook";
		controlConectarRedSocial.inicia(id_usuario, "Facebook");
		if (servicioRedsocial.autoConectarFacebook(id_usuario))
			cargaFormularioPublicacionFacebook();
	}

	// metodo para hacer llamado al formulario de publicacion para facebook
	public void cargaFormularioPublicacionFacebook() {
		VentanaFormularioPubFacebook.muestraVentanaFormularioFacebook(this);
	}

	// metodo para validar inicios de sesion previo relacionado al ususario actual
	// del sistema
	public void PublicacionInstagram() {
		this.red = "Instagram";
		controlConectarRedSocial.inicia(id_usuario, "Instagram");
		if (servicioRedsocial.autoConectaInstagram(id_usuario))
			cargaFormularioPublicacionInstagram();
	}

	// metodo para hacer llamado al formulario de publicacion para Instagram
	public void cargaFormularioPublicacionInstagram() {
		ventanaFormularioInstagram.muestraVentanaFormularioInstagram(this);
	}

	// metodo para validar la imagen agregada
	public boolean nuevaImagen(File imagen) {
		boolean validacion = servicioRedsocial.validaPesoFormatoImagen(red, imagen);
		if (validacion) {
			imagenes.add(imagen);
			return true;
		}
		return false;
	}

	// metodo para validar el video agregado agregada
	public boolean nuevoVideo(File video) {
		boolean validacion = servicioRedsocial.validaPesoFormatoVideo(red, video);
		if (validacion) {
			videos.add(video);
			return true;
		}
		return false;
	}

	// metodo para crear la publicacion y subirla a la platafroma de Facebook
	public void creaPublicacionNoProgramadaFacebook(String titulo, String contenido) {
		Publicacion publicacion = servicioPublicacion.creaPublicacionFacebook(id_usuario, titulo, contenido, "Facebook",
				imagenes, videos, LocalDate.now());
		if (publicacion != null) {

			controlConectarRedSocial.subePublicacionFacebook(publicacion);
			VentanaFormularioPubFacebook.setVisible(false);
		} else {
			System.out.println("Hay un error en creaPublicacionNoProgramada");
		}
	}

	// metodo para crear la publicacion y subirla a la platafroma de Facebook
	public void creaPublicacionProgramadaFacebook(String titulo, String contenido, LocalDate fechaProgramada) {
		Publicacion publicacion = servicioPublicacion.creaPublicacionFacebook(id_usuario, titulo, contenido, "Facebook",
				imagenes, videos, fechaProgramada);
		if (publicacion != null) {

			controlConectarRedSocial.subePublicacionFacebook(publicacion);
			VentanaFormularioPubFacebook.setVisible(false);
		} else {
			System.out.println("Hay un error en creaPublicacionNoProgramada");
		}
	}

	// metodo para crear la publicacion y subirla a la platafroma de Facebook
	public void creaPublicacionNoProgramadaInstagram(String titulo, String contenido) {
		Publicacion publicacion = servicioPublicacion.creaPublicacionInstagram(id_usuario, titulo, contenido,
				"Instagram", imagenes, videos, LocalDate.now());
		if (publicacion != null) {
			controlConectarRedSocial.subePublicacionInstagram(publicacion);
			ventanaFormularioInstagram.setVisible(false);
		} else {
			System.out.println("Hay un error en creaPublicacionNoProgramada");
		}
	}

	// metodo para crear la publicacion y subirla a la platafroma de Facebook
	public void creaPublicacionProgramadaInstagram(String titulo, String contenido,LocalDate fechaProgramada) {
		Publicacion publicacion = servicioPublicacion.creaPublicacionInstagram(id_usuario, titulo, contenido,
				"Instagram", imagenes, videos, fechaProgramada);
		if (publicacion != null) {
			controlConectarRedSocial.subePublicacionInstagram(publicacion);
			ventanaFormularioInstagram.setVisible(false);
		} else {
			System.out.println("Hay un error en creaPublicacionNoProgramada");
		}
	}

	public void cerrarFormulario(String nombreRed) {
		if (nombreRed.equals("Facebook"))
			VentanaFormularioPubFacebook.setVisible(false);
		else if (nombreRed.equals("Instagram"))
			ventanaFormularioInstagram.setVisible(false);
	}

}
