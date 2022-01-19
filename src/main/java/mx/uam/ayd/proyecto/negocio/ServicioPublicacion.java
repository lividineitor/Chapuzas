package mx.uam.ayd.proyecto.negocio;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.PublicacionRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Publicacion;

@Slf4j
@Service
public class ServicioPublicacion {

	@Autowired
	private PublicacionRepository publicacionRepository;

	// metodo para las publicaciones hechas por el usuario actual
	public ArrayList<Publicacion> BuscaPublicacionesUsuario(long id_usuario) {
		ArrayList<Publicacion> misPublicaciones = new ArrayList<>();
		for(Publicacion publicacion : publicacionRepository.findAll()) {
			if(publicacion.getIdUsuario()==id_usuario)
				misPublicaciones.add(publicacion);
		}
		System.out.println(misPublicaciones);
		return misPublicaciones;

	}

	// metodo para crear la publicacion en local y posteriormenete subirla a
	// facebook
	public Publicacion creaPublicacionFacebook(long id_usuario, String titulo, String contenido, String nombreRedSocial,
			ArrayList<File> imagenes, ArrayList<File> videos, LocalDate fechaActual) {
		// variable para la fecha actual
		LocalDate fecha_de_hoy = LocalDate.now();

		Publicacion miPublicacion = new Publicacion();
		miPublicacion.setIdUsuario(id_usuario);
		miPublicacion.setFechaCreacion(fecha_de_hoy);
		miPublicacion.setFechaModificacion(fecha_de_hoy);
		if(fechaActual.isAfter(fecha_de_hoy))
			miPublicacion.setFechaProgramada(fechaActual);
		else
			miPublicacion.setFechaProgramada(fecha_de_hoy);
		miPublicacion.setTitulo(titulo);
		miPublicacion.setContenido(contenido);
		miPublicacion.setNombreRedSocial(nombreRedSocial);
		miPublicacion.setImagenes(imagenes);
		miPublicacion.setVideos(videos);

		publicacionRepository.save(miPublicacion);

		return miPublicacion;
	}

	// metodo para crear la publicacion en local y posteriormenete subirla a
	// facebook
	public Publicacion creaPublicacionInstagram(long id_usuario, String titulo, String contenido,
			String nombreRedSocial, ArrayList<File> imagenes, ArrayList<File> videos, LocalDate fechaActual) {
		// variable para la fecha actual
		LocalDate fecha_de_hoy = LocalDate.now();

		Publicacion miPublicacion = new Publicacion();
		miPublicacion.setIdUsuario(id_usuario);
		miPublicacion.setFechaCreacion(fecha_de_hoy);
		miPublicacion.setFechaModificacion(fecha_de_hoy);
		if(fechaActual.isAfter(fecha_de_hoy))
			miPublicacion.setFechaProgramada(fechaActual);
		else
			miPublicacion.setFechaProgramada(fecha_de_hoy);
		miPublicacion.setTitulo(titulo);
		miPublicacion.setContenido(contenido);
		miPublicacion.setNombreRedSocial(nombreRedSocial);
		miPublicacion.setImagenes(imagenes);
		miPublicacion.setVideos(videos);

		publicacionRepository.save(miPublicacion);

		return miPublicacion;
	}
}
