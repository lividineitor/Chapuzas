package mx.uam.ayd.proyecto.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Preferencia;

/**
 * 
 * Repositorio para las preferencias del sistema
 * 
 * @author chapuzascompany
 *
 */
	public interface PreferenciaRepository extends CrudRepository <Preferencia, Long> {
		boolean existsByIdUsuario ( Long idUsuario ) ;
		Preferencia findByIdUsuario ( Long idUsuario ) ;
	}
