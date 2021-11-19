package mx.uam.ayd.proyecto.presentacion.agendaDeUsuario ;

import java.util.ArrayList;
import java.util.List ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Component ;

import mx.uam.ayd.proyecto.negocio.ServicioCita ;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Cita ;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario ;

/**
 *
 * Módulo de control para la historia de usuario AgendaDeUsuario
 *
 * @autor chapuzascompany
 *
 */

@Component
public class ControlAgendaDeUsuario {

    @Autowired
    private ServicioCita servicioCita ;

    @Autowired
    private VistaAgendaDeUsuario vista ;
    
    @Autowired
    private ServicioUsuario servicioUsuario ;
    
    private Usuario usuarioLogueado ;

    /**
    * Inicia la historia de usuario
    *
    */

    public void inicio ( Usuario usuario ) {

    	this.usuarioLogueado = usuario ;
    	
        vista.inicio ( this ) ;

    }

    public List <Cita> recargarLista ()
    {
        return servicioCita.obtenerCitasDeUsuario( usuarioLogueado.getIdUsuario()) ;
    }
    
    public void borrarCita ( Cita cita )
    {
    	List <Cita> citas = new ArrayList <> () ;
    	citas.add(cita);
    	servicioCita.eliminarCita(citas);
    }
    
    public void termina () {
        vista.setVisible ( false ) ;
    }

}
