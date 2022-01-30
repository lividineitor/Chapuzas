package mx.uam.ayd.proyecto.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.ContratoRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Contrato;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

import java.util.ArrayList ;
import java.util.List ;

@Service
public class ServicioContrato {

	@Autowired
	ContratoRepository contratoRepository ;
	
	/**
	 * Recupera todos los contratos
	 *
	 * @param Usuario
	 * @return ArrayList <Contrato>
	 *
	 * */
	public ArrayList <Contrato> obtenerContrato ( Usuario usuario ) {
		
		ArrayList <Contrato> contratos = new ArrayList<>() ;
		
		for ( Contrato contrato : contratoRepository.findAllByIdUsuario ( usuario.getIdUsuario() ) ) {
			contratos.add(contrato);
		}
		
		return contratos ;
		
	}
	
	/**
	 * 
	 * Elimina un contrato
	 * 
	 * @param Contrato
	 * @return true o false, dependiendo si se eliminó correctamente o hubo algún error
	 * 
	 * */
	
	public boolean eliminarContrato ( Contrato contrato ) {
		
		long idContrato = contrato.getIdContrato() ;
		
		contratoRepository.delete(contrato);
		
		if ( !contratoRepository.existsById(idContrato))
			return true ;
		else
			return false ;
	}
	
	/**
	 * 
	 * Añade un nuevo contrato
	 * 
	 * @param Contrato
	 * @return boolean
	 */
	
	public boolean guardarContrato ( Contrato contrato ) {
		Contrato temporal ;
		
		temporal = contratoRepository.save( contrato ) ;
		
		if ( temporal.equals(contrato) )
			return true ;
		else
			return false ;
	}
}
