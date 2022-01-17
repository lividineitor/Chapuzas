package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.PublicacionRepository;
import mx.uam.ayd.proyecto.datos.RedSocialRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.negocio.modelo.Publicacion;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
@Service
public class ServicioPublicacion {	
	
	@Autowired
	private PublicacionRepository publicacionRepository;
	
	private String fechaPr="";
	private Publicacion pub;
	
	/**
	 * Este metodo es el mas sencillo pues 
	 * se hace invoca cuando no se agregan imagenes ni videos
	 * No hay una validacion de las entradas de texto pues esto se hace en la capa de presentacion
	 * @param titulo
	 * @param contenido
	 * @param nombreRed
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public  boolean cPublicacion(String titulo, String contenido, String nombreRed, LocalDate fecha, LocalTime hora) {
		
		
		fechaPr+=fecha+";"+hora;
		log.info("Agregando publicacion, sin nada titulo: "+titulo+" Resumen contenido:"+contenido.substring(0, contenido.length()/2)+ ", en: "+nombreRed+", en la fecha: "+fechaPr);
		pub = new Publicacion();
		pub.setTitulo(titulo);
		pub.setContenido(contenido);
		pub.setRedSocial(nombreRed);
		pub.setImagenes(null);
		pub.setVideos(null);
		pub.setFechaCreacion(""+LocalDate.now()+","+LocalTime.now());
		pub.setFechaModificacion(""+LocalDate.now()+","+LocalTime.now());
		pub.setFechaProgramada(fechaPr);
		publicacionRepository.save(pub);
		
		return true;
	}
	
	/**
	 * Este metodo es el mas completo pues 
	 * este se invoca cuando hay tanto imagens como videos para publicarse
	 * NO hay validacion de las entradas de texto pues estas se hacen en la capa de presentacion
	 * No hay validacion de los archivos imagenes y videos pues esta se lleva acabo en la de redSocial
	 * @param titulo
	 * @param contenido
	 * @param nombreRed
	 * @param imagenes
	 * @param videos
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public boolean cPublicacion(String titulo,String contenido,String nombreRed,ArrayList <File> imagenes,ArrayList <File> videos,LocalDate fecha, LocalTime hora) {
		
		
		fechaPr+=fecha+";"+hora;
		pub = new Publicacion();
		log.info("Agregando publicacion con todo, titulo: "+titulo+" Resumen contenido:"+contenido.substring(0, contenido.length()/2)+ ", en: "+nombreRed+", en la fecha: "+fechaPr);
		pub.setTitulo(titulo);
		pub.setContenido(contenido);
		pub.setRedSocial(nombreRed);
		pub.setImagenes(imagenes);
		pub.setVideos(videos);
		pub.setFechaCreacion(""+LocalDate.now()+","+LocalTime.now());
		pub.setFechaModificacion(""+LocalDate.now()+","+LocalTime.now());
		pub.setFechaProgramada(fechaPr);
		publicacionRepository.save(pub);
		
		return true;
	}
	
	/**
	 * Este metodo es el completo pues 
	 * este se invoca cuando hay solo  imagenes publicarse
	 * NO hay validacion de las entradas de texto pues estas se hacen en la capa de presentacion
	 * No hay validacion de los archivos imagenes, pues esta se lleva acabo en la de redSocial
	 * @param titulo
	 * @param contenido
	 * @param red
	 * @param imagenes
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public boolean cPublicacion(String titulo,String contenido,String red,ArrayList <File> imagenes,LocalDate fecha, LocalTime hora) {
		
		
		fechaPr+=fecha+";"+hora;
		log.info("Agregando publicacion,solo imagenes titulo: "+titulo+" Resumen contenido:"+contenido.substring(0, contenido.length()/2)+ ", en: "+red+", en la fecha: "+fechaPr);
		pub = new Publicacion();
		pub.setTitulo(titulo);
		pub.setContenido(contenido);
		pub.setRedSocial(red);
		pub.setImagenes(imagenes);
		pub.setVideos(null);
		pub.setFechaCreacion(""+LocalDate.now()+","+LocalTime.now());
		pub.setFechaModificacion(""+LocalDate.now()+","+LocalTime.now());
		pub.setFechaProgramada(fechaPr);
		publicacionRepository.save(pub);
		
		return true;
	}
	
	/**
	 * Este metodo es completo pues 
	 * este se invoca cuando hay solo videos para publicarse
	 * NO hay validacion de las entradas de texto pues estas se hacen en la capa de presentacion
	 * No hay validacion de los archivos videos pues esta se lleva acabo en la de redSocial
	 * @param videos
	 * @param titulo
	 * @param contenido
	 * @param nombreRed
	 * @param fecha
	 * @param hora
	 * @return
	 */
	public boolean cPublicacion(ArrayList <File> videos,String titulo,String contenido,String nombreRed,LocalDate fecha, LocalTime hora) {
		
		
		fechaPr+=fecha+";"+hora;
		log.info("Agregando publicacion, solo videos titulo: "+titulo+" Resumen contenido: "+contenido.substring(0, contenido.length()/2)+ " en: "+nombreRed+", en la fecha: "+fechaPr);
		pub = new Publicacion();
		pub.setTitulo(titulo);
		pub.setContenido(contenido);
		pub.setRedSocial(nombreRed);
		pub.setImagenes(null);
		pub.setVideos(videos);
		pub.setFechaCreacion(""+LocalDate.now()+","+LocalTime.now());
		pub.setFechaModificacion(""+LocalDate.now()+","+LocalTime.now());
		pub.setFechaProgramada(fechaPr);
		publicacionRepository.save(pub);
		
		return true;
	}
	
	
	/**
	 * Nos perimte recuperar todas las publicaciones, para posteriormente mostrar un listado de las mismas
	 * @return
	 */
	public List <Publicacion> recuperaPublicaciones() {
		System.out.println("publicacionRepository = "+publicacionRepository);
		
		List <Publicacion> publicaciones = new ArrayList<>();
		
		for(Publicacion pubCreadas:publicacionRepository.findAll()) {
			publicaciones.add(pubCreadas);
		}
				
		return publicaciones;
	}
	
	public void eliminaPublicacion(long id)
	{
		//int identera = Integer.parseInt(id);
	    //long idlong = identera;
		publicacionRepository.deleteById(id);
	}

	
}
