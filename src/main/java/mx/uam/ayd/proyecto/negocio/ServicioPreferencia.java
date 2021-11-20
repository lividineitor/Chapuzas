package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.time.* ;

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
	 * crearPreferencia: Permite la creación de una única preferencia por usuario, si es true se pudo crear, si es false ya existe
	 * @param Preferencia
	 * @return boolean
	 * */
	
	public boolean crearPreferencia ( Long idUsuario )
	{
		if ( preferenciaRepository.existsByIdUsuario( idUsuario ) )
			
			return false ;
		
		else
		{
			Preferencia preferencia = new Preferencia () ;
			preferencia.setIdUsuario ( idUsuario ) ;
			preferenciaRepository.save(preferencia) ;
			return true ;
		}
	}
	
	public Preferencia obtenerPreferencia ( Long idUsuario )
	{
		return preferenciaRepository.findByIdUsuario( idUsuario ) ;
	}
	
	public Preferencia actualizarPreferencia ( Preferencia preferencia )
	{
		return preferenciaRepository.save(preferencia) ;
	}

	// Controla los valores de RN-01
	
	public boolean agregarDiasDescanso ( String [] dias , Long idUsuario ) {
		
		Preferencia preferencia = obtenerPreferencia ( idUsuario ) ;
				
		preferencia.setDiasDescanso ( dias ) ;

		preferenciaRepository.save( preferencia ) ;
		
		return true ;
	}

	// Controla los valores de RN-02
	
	public boolean agregarDiasFeriados ( ArrayList <LocalDate> dias , Long idUsuario ) {

		Preferencia preferencia = obtenerPreferencia ( idUsuario ) ;
		
		preferencia.setDiasFeriados ( dias ) ;

		preferenciaRepository.save( preferencia ) ;
		
		return true ;
		
	}
	
	public boolean setHorarioLaboral (LocalTime horaApertura , LocalTime horaCierre , Long idUsuario )
	{
		Preferencia preferencia = obtenerPreferencia ( idUsuario ) ;

		preferencia.setHoraDeApertura(horaApertura);
		preferencia.setHoraDeCierre(horaCierre);

		preferenciaRepository.save( preferencia ) ;
		
		return true ;
	}
	
	
}
