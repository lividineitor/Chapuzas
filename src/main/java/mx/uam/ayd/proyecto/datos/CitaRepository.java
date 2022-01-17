package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Cita;


import java.util.List ;

/**
 * 
 * Repositorio para citas
 * 
 * @author chapuzascompany
 *
 */
public interface CitaRepository extends CrudRepository <Cita, Long> {
	public List <Cita> findAllByIdUsuario ( long idUsuairo ) ;
}


