package mx.uam.ayd.proyecto.datos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Contrato;

/**
 * Repositorio para Contratos
 * 
 * @author Chapuzas
 * 
 */

public interface ContratoRepository extends CrudRepository<Contrato,Long> {
	public List <Contrato> findAllByIdUsuario ( long idUsuario ) ;
}
