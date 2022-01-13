package mx.uam.ayd.proyecto.presentacion.preferencia ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Component ;

import mx.uam.ayd.proyecto.negocio.ServicioPreferencia;
import mx.uam.ayd.proyecto.negocio.modelo.Preferencia;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario ;

/**
 *
 * Módulo de control para la historia de usuario Preferencia
 *
 * @autor chapuzascompany
 *
 */

@Component
public class ControlPreferencia {

    @Autowired
    private ServicioPreferencia servicioPreferencia ;
    
    @Autowired
    private VistaPreferencia vistaPreferencia ;
    
    private Usuario usuarioLogueado ;
    private Preferencia preferencia ;

    /**
    * Inicia la historia de usuario
    *
    */

    public void inicio ( Usuario usuario ) {

    	this.usuarioLogueado = usuario ;
        vistaPreferencia.inicio ( this ) ;
       
    }
    
    /**
     * Termina la historia de usuario
     *
     */

    
    
    public void terminaPreferencia () {
        vistaPreferencia.setVisible ( false ) ;
    }
    
}
