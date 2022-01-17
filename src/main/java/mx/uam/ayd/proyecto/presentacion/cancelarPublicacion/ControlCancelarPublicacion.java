package mx.uam.ayd.proyecto.presentacion.cancelarPublicacion;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioPublicacion;
import mx.uam.ayd.proyecto.negocio.ServicioRedSocial;
//import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;

@Component
public class ControlCancelarPublicacion {
	/*Estableceindo conexiones spring*/
	@Autowired
	private ServicioPublicacion servicioPublicacion;
	
	@Autowired
	private ServicioRedSocial servicioRedSocial;
	
	//@Autowired
	//private ControlPrincipal controlPrincipal;
	
	@Autowired
	public VistaCancelarPublicacion ventana;
	
	@Autowired
	public VentanaConfirmar ventana1;

	//ControlCancelarPublicacion control;
	public void inicia() {
		ventana.MostrarPublicaciones(this, servicioPublicacion.recuperaPublicaciones());
	}
	
	public void eliminaprev(String id)
	{
		ventana1.muestra(this,id);
	}
	public void elimina(String id)
	{
		int idr = Integer.parseInt(id);
		Long idreal = new Long (idr);
		servicioPublicacion.eliminaPublicacion(idreal.valueOf(id));
	}
	
	public void salir()
	{
		ventana.termina();
	}
	

}
