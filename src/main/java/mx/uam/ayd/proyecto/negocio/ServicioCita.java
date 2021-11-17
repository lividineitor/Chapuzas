package mx.uam.ayd.proyecto.negocio;

import java.util.ArrayList;
import java.util.List;
import java.time.* ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.ayd.proyecto.datos.CitaRepository;
import mx.uam.ayd.proyecto.negocio.modelo.*;


@Service
public class ServicioCita {

	@Autowired 
	CitaRepository citaRepository;

	/**
	 * 
	 * Recupera todos los grupos
	 * 
	 * @return
	 */
	public List <Cita> obtenerCitas() { // Regresa todas las citas que hay, principalmente para poder mostrarlas en el calendario



		List <Cita> citas = new ArrayList<>();

		for(Cita cita:citaRepository.findAll()) {
			citas.add(cita);
		}


		return citas;

	}

	public List <Cita> obtenerHorasDisponibles ( Cita cita ) { // Regresa solo las citas que coinciden con el usuario dado en la fecha solicitada. Sirve principalmente para mostrar los horarios ocupados
		List <Cita> citas = new ArrayList<> () ;
        LocalDateTime fechaCita ;
        LocalDateTime fechaRecuperada = cita.getFechaCita () ;

        for (Cita citaRecuperada:citaRepository.findAllByIdUsuario( cita.getIdUsuario () )) {

        fechaCita = citaRecuperada.getFechaCita () ;

        if ( fechaCita.getYear () == fechaRecuperada.getYear () && fechaCita.getMonth () == fechaRecuperada.getMonth () && fechaCita.getDayOfMonth () == fechaRecuperada.getDayOfMonth () )
        	citas.add( citaRecuperada ) ;
        }

        return citas ;
	}

	public List <Cita> obtenerCitasDeUsuario ( Long idUsuario ) { // Regresa solo las citas que coinciden con el usuario dado en la fecha solicitada. Sirve principalmente para mostrar los horarios ocupados

			return citaRepository.findAllByIdUsuario( idUsuario ) ;
	}

	public Cita confirmarCita ( Usuario usuario , Cita cita ) // Confirma una cita nueva, por ello requiere el usuario y los valores de la cita creada.
	{

		Cita nuevaCita = cita ;
		nuevaCita.setIdUsuario ( usuario.getIdUsuario () ) ;

		return citaRepository.save( nuevaCita ) ;


	}

	public boolean confirmarCita ( Cita cita ) // Actualiza el estado de una cita ya existente, con todos sus valores ya creados.
	{
		Cita citaConfirmada ;

		citaConfirmada = citaRepository.save( cita ) ;

		if ( citaConfirmada.getEstado () == cita.getEstado () )
			return true ;
		else
			return false ;

	}

	public boolean eliminarCita ( List <Cita> citas ) { // Elimina una o más citas ya creadas. Principalmente sirve para la administración de citas.
		citaRepository.deleteAll(citas) ;

		return true ;
	}
	
	public void validarEstado ()
	{
		List <Cita> citas = obtenerCitas() ;
		
		for (Cita cita:citas)
		{
			if (cita.getFechaCita().isBefore(LocalDateTime.now()))
			{
				if (cita.getEstado().equals("Pendiente"))
					cita.setEstado("Expirado");
				else if (cita.getEstado().equals("Confirmado"))
					cita.setEstado("Realizado");

				
				confirmarCita(cita) ;
			}
		}
	
	}

}
