package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.time.* ;
import java.util.Optional ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.PreferenciaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Preferencia;


/**
 * Servicio que controla las preferencias del sistema
 * */

@Service
public class ServicioPreferencia {

	@Autowired 
	PreferenciaRepository preferenciaRepository;
	
	/**
	 * crearPreferencia: Permite la creación de una única preferencia, si es true se pudo crear, si es false ya existe
	 * @param
	 * @return boolean
	 * */
	
	public boolean crearPreferencia ()
	{
		if ( preferenciaRepository.count () == 0 )
			
			return false ;
		
		else
		{
			Preferencia preferencia = new Preferencia () ;
			preferenciaRepository.save(preferencia) ;
			return true ;
		}
	}
	
	/**
	 * obtenerPreferencia: Obtiene las preferencias del sistema
	 * @param
	 * @return Preferencia de existir o null de no existir
	 */
	
	public Preferencia obtenerPreferencia ()
	{
		Optional <Preferencia> temporal = preferenciaRepository.findById( ( long ) 1 ) ;
		
		if ( temporal.isPresent() )
			return temporal.get() ;
		else
			return null ;
	}
	
	/**
	 * actualizarPreferencia: Actualiza los cambios en preferencia
	 * @param preferencia
	 * @return Preferencia ya actualizada
	 */
	
	public Preferencia actualizarPreferencia ( Preferencia preferencia )
	{
		return preferenciaRepository.save(preferencia) ;
	}

	// Controla los valores de RN-01
	
	public boolean agregarDiasDescanso ( String [] dias ) {
		
		Preferencia preferencia = obtenerPreferencia () ;
				
		preferencia.setDiasDescanso ( dias ) ;

		preferenciaRepository.save( preferencia ) ;
		
		return true ;
	}

	// Controla los valores de RN-02
	
	public boolean agregarDiasFeriados ( ArrayList <LocalDate> dias ) {

		Preferencia preferencia = obtenerPreferencia () ;
		
		preferencia.setDiasFeriados ( dias ) ;

		preferenciaRepository.save( preferencia ) ;
		
		return true ;
		
	}
	
	public boolean setHorarioLaboral (LocalTime horaApertura , LocalTime horaCierre )
	{
		Preferencia preferencia = obtenerPreferencia () ;

		preferencia.setHoraDeApertura(horaApertura);
		preferencia.setHoraDeCierre(horaCierre);

		preferenciaRepository.save( preferencia ) ;
		
		return true ;
	}
	
	
}
