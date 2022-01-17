package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Publicacion;

public interface PublicacionRepository extends CrudRepository <Publicacion, Long> {
	
	public List <Publicacion> findByFechaCreacion(String fechaCreacion);
}