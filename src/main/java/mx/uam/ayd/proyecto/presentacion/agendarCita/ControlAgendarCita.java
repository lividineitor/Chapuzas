package mx.uam.ayd.proyecto.presentacion.agendarCita ;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List ;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Component ;

import mx.uam.ayd.proyecto.negocio.ServicioCita ;
import mx.uam.ayd.proyecto.negocio.ServicioPreferencia;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Cita ;
import mx.uam.ayd.proyecto.negocio.modelo.Preferencia;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario ;

/**
 *
 * Módulo de control para la historia de usuario AgendarCita
 *
 * @autor chapuzascompany
 *
 */

@Component
public class ControlAgendarCita {

    @Autowired
    private ServicioCita servicioCita ;

    @Autowired
    private VistaAgendarCita vista ;
    
    @Autowired
    private ServicioUsuario servicioUsuario ;
    
    @Autowired
    private ServicioPreferencia servicioPreferencia ;
    
    private Usuario usuarioLogueado ;
    private Preferencia preferencia ;

    /**
    * Inicia la historia de usuario
    *
    */

    public void inicio ( Usuario usuario ) {

    	this.usuarioLogueado = usuario ;
    	
        vista.inicio ( this , obtenerDias () ) ;
       
    }
    
    public List <LocalDate> obtenerDias ()
    {
        List <LocalDate> citas = new ArrayList <> ();
        
        preferencia = servicioPreferencia.obtenerPreferencia() ;
        
        LocalDate fechaActual = LocalDate.now () ;
		LocalDate periodoParaCitas = fechaActual.plusMonths( preferencia.getPeriodoParaCitas() ) ;
				
		for (int i = 0 ; i < fechaActual.until(periodoParaCitas , ChronoUnit.DAYS) ; i ++ )
		{
			boolean estado = false ;

			for ( int j = 0 ; j < preferencia.getDiasDescanso().length ; j ++ )
				if (fechaActual.plusDays(i).getDayOfWeek().getDisplayName( TextStyle.FULL , Locale.ENGLISH ).equals( preferencia.getDiasDescanso()[j] ))
					estado = true ;
			
			if ( estado == false )
				citas.add(fechaActual.plusDays(i));
		}
		
		return citas ;
    }
    


    
    /**
     * Obtiene las horas disponibles a partir de una fecha dada en cita
     * @param Cita
     * @return List <LocalDateTime>
     * */
    
    public List <LocalDateTime> obtenerHorasDisponibles ( Cita cita ) {

    	List <LocalDateTime> horariosDisponibles = new ArrayList <> () ;

    	preferencia = servicioPreferencia.obtenerPreferencia() ;
    	
    	long aumento = 0 ;
    	
    	for ( int i = preferencia.getHoraDeApertura().getHour() ; i < preferencia.getHoraDeCierre().getHour() ; i ++ )
    	{
    		horariosDisponibles.add( LocalDateTime.of ( cita.getFechaCita().toLocalDate () , preferencia.getHoraDeApertura().plusHours ( aumento ++ ) ) ) ;
    	}
    	
        List <Cita> citas = servicioCita.obtenerCitas() ;
        List <Cita> citasValidadas = new ArrayList <> ();
        
        for ( Cita citaDePrueba:citas)
        {
        	if ( citaDePrueba.getFechaCita().toLocalDate() != cita.getFechaCita().toLocalDate() )
        		citasValidadas.add(citaDePrueba) ;
        }
        
        // Genera una lista de las horas disponibles para agendar cita
        
        for (Cita citaDePrueba:citasValidadas)
        {
        	horariosDisponibles.remove( citaDePrueba.getFechaCita()) ;
        }
        
        return horariosDisponibles ;

    }

    /**
     * Muestra la ventana de selección de horario
     * @param Cita
     * */
    
    public void mostrarHorarios ( Cita cita )
    {
    	vista.mostrarHorarios(obtenerHorasDisponibles ( cita ));
    }
    
    
    /**
     * Gestiona la cración de una cita nueva
     * @param LocalDateTime
     * @return
     * */
    
    public void mostrarAgendarCita ( LocalDateTime fecha , String mensaje ) {
    	Cita cita = new Cita () ;
    	
    	cita.setEstado("Pendiente");
    	cita.setFechaCita(fecha);
    	cita.setFechaCreacion(LocalDateTime.now());
    	cita.setNotas(mensaje);

    	List <Cita> citas = servicioCita.obtenerCitas() ;
    	
    	boolean estado = false ;
    	
    	for ( Cita citaDePrueba:citas )
    	{
    	    
    	    if (citaDePrueba.getFechaCita().equals(fecha))
    	    {
    	    	estado = true ;
    	    	mostrarMensajeError () ;
    	    }
    	}
    	
    	if (estado == false )
    	    vista.mostrarMensajeExito( confirmarCita ( usuarioLogueado , cita )); 
    }
    
    /**
     * Confirma una nueva cita
     * @param Usuario , Cita
     * @return
     * */
    
    public Cita confirmarCita ( Usuario usuario , Cita cita ) {

        return servicioCita.confirmarCita ( usuario , cita ) ;

    }
    
    /**
     * Muestra un diálogo de confirmación de cita
     * @param Cita
     * */
    
    public void mostrarMensajeExito ( Cita cita )
    {
    	vista.mostrarMensajeExito(cita);
    }

    public void mostrarMensajeError ()
    {
        vista.mostrarMensajeError () ;
    }
    
    /**
     * Termina la historia de usuario
     *
     */

    public void termina () {
        vista.setVisible ( false ) ;
    }

}
