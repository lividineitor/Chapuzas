package mx.uam.ayd.proyecto.presentacion.preferencia ;

import org.springframework.beans.factory.annotation.Autowired ;
import org.springframework.stereotype.Component ;

import mx.uam.ayd.proyecto.negocio.ServicioPreferencia;
import mx.uam.ayd.proyecto.negocio.modelo.Preferencia;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario ;

import mx.uam.ayd.proyecto.presentacion.ConectarRedSocial.ControlConectarRedSocial;


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
    

    @Autowired
	private ControlConectarRedSocial controlConectarRedSocial;
    
    private Usuario usuarioLogueado ;
    private Preferencia preferencia ;

    /**
    * Inicia la historia de usuario
    *
    */

    public void inicio ( Usuario usuario ) {

    	this.usuarioLogueado = usuario ;
    	
    	this.preferencia = servicioPreferencia.obtenerPreferencia();
    	
        vistaPreferencia.inicio ( this ) ;
       
    }

    /**
     * Almacena la preferencia en el sistema
     * @param Preferencia
     * @return
     */
    
    public boolean guardarPreferencia (Preferencia preferenciaTemporal) {
    	preferencia.setDiasFeriados(preferenciaTemporal.getDiasFeriados());
    	preferencia.setDiasDescanso(preferenciaTemporal.getDiasDescanso());
    	preferencia.setHoraDeApertura(preferenciaTemporal.getHoraDeApertura());
    	preferencia.setHoraDeCierre(preferenciaTemporal.getHoraDeCierre());
    	preferencia.setPeriodoParaCitas(preferenciaTemporal.getPeriodoParaCitas());

    	preferencia.setRutaLogotipo(preferenciaTemporal.getRutaLogotipo());
    	
    	preferencia.setUsuarioFacebook(preferenciaTemporal.getUsuarioFacebook());
    	preferencia.setPassFacebook(preferenciaTemporal.getPassFacebook());
    	
    	preferencia.setUsuarioInstagram(preferenciaTemporal.getUsuarioInstagram());
    	preferencia.setPassInstagram(preferenciaTemporal.getPassInstagram());
    	

    	servicioPreferencia.actualizarPreferencia(preferencia) ;
    	
    	return true ;
    	
    }
    
    /**
     * Termina la historia de usuario
     *
     */

    
    
    public void terminaPreferencia () {
        vistaPreferencia.setVisible ( false ) ;
    }
    

    public void datosObtenidosLoginSocial(String usuario, String contrasenia, String redSocial) {
    	if (redSocial=="Facebook") {
    		vistaPreferencia.passwordFieldF.setText(contrasenia);
    		vistaPreferencia.textFieldUsuarioF.setText(usuario);
    	}else if(redSocial=="Instagram") {
    		vistaPreferencia.passwordFieldI.setText(contrasenia);
    		
    		vistaPreferencia.textFieldI.setText(usuario);
    	}
    }
    
    public void redSocialFacebook() {
		
		controlConectarRedSocial.inicia(usuarioLogueado.getIdUsuario(),"Facebook");
	}
	public void redSocialInstagram() {
		controlConectarRedSocial.inicia(usuarioLogueado.getIdUsuario(),"Instagram");
	}
    
}


