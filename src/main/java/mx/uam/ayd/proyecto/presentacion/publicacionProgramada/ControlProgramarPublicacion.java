package mx.uam.ayd.proyecto.presentacion.publicacionProgramada;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioPublicacion;
import mx.uam.ayd.proyecto.negocio.ServicioRedSocial;
import mx.uam.ayd.proyecto.presentacion.cancelarPublicacion.ControlCancelarPublicacion;
import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;
import mx.uam.ayd.proyecto.presentacion.publicacionProgramada.*;

@Component
public class ControlProgramarPublicacion {
	/*Estableceindo conexiones spring*/
	@Autowired
	private ServicioPublicacion servicioPublicacion;
	
	@Autowired
	private ServicioRedSocial servicioRedSocial;
	
	@Autowired
	private VentanaEleccion ventanaEleccion;
	
	@Autowired
	private ControlPrincipal controlPrincipal;
	@Autowired
	private VentanaForm ventanaForm;
	
	
	@Autowired
	private VentanaListaPublicaciones ventanaListPublicaciones;
	@Autowired
	private ControlCancelarPublicacion controlcancelar;
	
	/*variables auxiliares*/
	private String nombreRed="", titulo="",contenido="";
	private ArrayList<File> imagenes= new ArrayList<>();
	private ArrayList<File>videos= new ArrayList<>();
	
	
	
	/* Mis ventanas de la historia de usuario*/
	public void inicia() {
		ventanaListPublicaciones.sWlistPub(this,servicioPublicacion.recuperaPublicaciones());
	}
	
	public void iniciacancelar() {
		controlcancelar.inicia();
	}
	
	public void nuevaPub() {
		ventanaEleccion.sWselectRedS(this);
	}
	
	
	
	public void cierra() {
		ventanaForm.setVisible(false);		
	}
	
	
	/*metodos para la historia de usuario*/
	public void iValidacio(String nombreRed) {
		if(servicioRedSocial.validaRed(nombreRed)) {
			this.nombreRed= nombreRed;
			ventanaForm.sWformPub(this);
		}else {
			controlPrincipal.recarga();
		}	
	}
	
	public void dPublicacion(String titulo, String contenido, String nombreRed) {
		this.titulo=titulo;
		this.contenido=contenido;
		this.nombreRed= nombreRed;
		ventanaForm.muestraDialogoConMensaje("Listo para publicar");
	}
	
	public void dPublicacion(String titulo, String contenido, String nombreRed,ArrayList<File> imagenes, ArrayList<File> videos) {
		this.titulo=titulo;
		this.contenido=contenido;
		this.nombreRed= nombreRed;
		//ventanaForm.muestraDialogoConMensaje("Listo para publicar");
		
		if(servicioRedSocial.vFimagenYVideo(nombreRed, imagenes, videos)) {
			this.imagenes=imagenes;
			this.videos = videos;
			ventanaForm.muestraDialogoConMensaje("Listo para publicar");
		}else {
			ventanaForm.muestraDialogoConMensaje("Vefique que el peso de cada archivo sea permitido por "+nombreRed);
		}
	}
	public void dPublicacion(String titulo, String contenido, String nombreRed, ArrayList<File> imagenes) {
		this.titulo=titulo;
		this.contenido=contenido;
		this.nombreRed= nombreRed;
		
		/*ventanaForm.muestraDialogoConMensaje("Esto se tiene: "+servicioRedSocial.vFimagen(nombreRed, imagenes));*/
		if(servicioRedSocial.vFimagen(nombreRed, imagenes)) {
			this.imagenes=imagenes;
			ventanaForm.muestraDialogoConMensaje("Listo para publicar");
		}else {
			ventanaForm.muestraDialogoConMensaje("Vefique que el peso y formato de cada imagen sea permitido por "+nombreRed);
		}
	}
	public void dPublicacion(String titulo, String contenido, ArrayList<File> videos, String nombreRed) {
		this.titulo=titulo;
		this.contenido=contenido;
		this.nombreRed= nombreRed;
		if(servicioRedSocial.vFvideo(nombreRed, videos)) {
			this.videos=videos;
			ventanaForm.muestraDialogoConMensaje("Listo para publicar");
		}else {
			ventanaForm.muestraDialogoConMensaje("Vefique que el peso y formato de cada video sea permitido por "+nombreRed);
		}
	}
	
	public void pPublicacion() {
		pPublicacion(LocalDate.now(),LocalTime.now());
	}
	
	public void pPublicacion(LocalDate cuando, LocalTime hora) {
		if((videos==null&&imagenes==null)||(videos.isEmpty() && imagenes.isEmpty())) {
			servicioPublicacion.cPublicacion(titulo, contenido, nombreRed, cuando, hora);
			}else if(imagenes==null || imagenes.isEmpty()) {
			servicioPublicacion.cPublicacion(videos, titulo, contenido, nombreRed, cuando, hora);
			
		}else if(videos==null|| videos.isEmpty()) {
			servicioPublicacion.cPublicacion(titulo, contenido, nombreRed, imagenes, cuando, hora);
		}else {
			servicioPublicacion.cPublicacion(titulo, contenido, nombreRed, imagenes, videos, cuando, hora);
		}
		ventanaForm.muestraDialogoConMensaje("Publicacion creada");
		cierra();
		inicia();
	}
}
