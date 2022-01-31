package mx.uam.ayd.proyecto.presentacion.agenda ;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List ;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Component ;

import mx.uam.ayd.proyecto.ManejoDeMensajes.ControlManejoDeMensajes;
import mx.uam.ayd.proyecto.negocio.ServicioCita ;
import mx.uam.ayd.proyecto.negocio.ServicioPreferencia;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Cita ;
import mx.uam.ayd.proyecto.negocio.modelo.Preferencia;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario ;

/**
 *
 * Módulo de control para la historia de usuario ManejoDeAgenda
 *
 * @autor chapuzascompany
 *
 */

@Component
public class ControlAgenda {

    @Autowired
    private ServicioCita servicioCita ;

    @Autowired
    private VistaAgenda vistaAgenda ;

    @Autowired
    private VistaRegistrarCita vistaRegistrarCita ;

    @Autowired
    private VistaCalendario vistaCalendario ;
    
    @Autowired
    private ServicioUsuario servicioUsuario ;
       
    @Autowired
    private ServicioPreferencia servicioPreferencia ;
    
    @Autowired 
    private ControlManejoDeMensajes mensajes;
    
    private Usuario usuarioLogueado ;
    private Preferencia preferencia ;

    /**
    * Inicia la historia de usuario
    *
    */

    public void inicio ( Usuario usuario ) {

    	this.usuarioLogueado = usuario ;
    	servicioCita.validarEstado();
        vistaAgenda.inicio ( this ) ;
       
    }
    
    /**
     * Muestra la ventana de creación de cita
     * */

    public void inicioRegistrarCita ( LocalDateTime fecha ) {

        vistaRegistrarCita.inicio ( this , usuarioLogueado.getIdUsuario() , fecha ) ;
    }

    public void inicioCalendario () {

        vistaCalendario.inicio ( this , obtenerDias() ) ;
    }
    
    public void mostrarHorarios ( Cita cita )
    {
    	vistaCalendario.mostrarHorarios(obtenerHorasDisponibles ( cita ));
    }
    
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
     * Obtiene todas las citas del sistema 
     * */

    public List <Cita> obtenerCitas ()
    {
    	return servicioCita.obtenerCitas() ;
    }
    
    /**
     * Actualiza el estado de cita
     * @param Cita
     * @return Boolean
     * */
    
    public boolean confirmarCita ( Cita cita )
    {
    	return servicioCita.confirmarCita(cita) ;
    }
    
    public boolean eliminarCita ( Cita cita )
    {
    	List <Cita> citas = new ArrayList <> () ;
    	citas.add(cita);
    	
    	return servicioCita.eliminarCita(citas) ;
    }
    
    public List <LocalDate> obtenerDias ()
    {
        List <LocalDate> citas = new ArrayList <> ();
        
        preferencia = servicioPreferencia.obtenerPreferencia() ;
        
        LocalDate fechaActual = LocalDate.now () ;
		LocalDate periodoParaCitas = fechaActual.plusMonths(3);
				
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
    
    public Usuario crearUsuario ( Usuario usuario )
    {
    	return servicioUsuario.agregaUsuario(usuario.getNombre() , usuario.getApellido() , usuario.getEmail() , usuario.getTelefono() , usuario.getContraseña() , usuario.getPermisos()) ;
    }

    public boolean crearCita ( Cita cita , Usuario usuario )

    {
    	servicioCita.confirmarCita( usuario, cita) ;
    	return true ;
    }
    
    /**
     * Termina la historia de usuario
     *
     */

    
    
    public void terminaAgenda () {
        vistaAgenda.setVisible ( false ) ;
    }
    
    public void terminaRegistrarCita () {
        vistaRegistrarCita.setVisible ( false ) ;
    }

    public void terminaCalendario () {
        vistaCalendario.setVisible ( false ) ;
    }
    
    public void MuestraMensajeErrorInfraccion(String mensaje)
    {
    	mensajes.MuestraMensajeErrorInfraccion(mensaje);
    }
    public void MuestraMensajeExito(String mensaje)
    {
    	mensajes.MuestraMensajeExito(mensaje);
    }
}
