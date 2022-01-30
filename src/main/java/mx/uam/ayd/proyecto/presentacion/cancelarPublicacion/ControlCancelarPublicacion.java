package mx.uam.ayd.proyecto.presentacion.cancelarPublicacion;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.ManejoDeMensajes.ControlManejoDeMensajes;
import mx.uam.ayd.proyecto.negocio.ServicioPublicacion;
import mx.uam.ayd.proyecto.negocio.ServicioRedSocial;

@Component
public class ControlCancelarPublicacion {
	/*Estableceindo conexiones spring*/
	@Autowired
	private ServicioPublicacion servicioPublicacion;
	
	@Autowired
	private ServicioRedSocial servicioRedSocial;
	
	@Autowired
	public VistaCancelarPublicacion ventana;
	
	@Autowired
	public VentanaConfirmar ventana1;
	
	@Autowired
	ControlManejoDeMensajes mensajes;
	
	private long id_usuario = 0;

	
	public void inicia() { //Inicia la ventana para comenzar el proceso de cancelacion
		//this.id_usuario = id_usuario;
		ventana.MostrarPublicaciones(this, servicioPublicacion.recuperaPublicaciones());
	}
	
	public void eliminaprev(String id) //abre la ventana para confirmar la cancelacion
	{
		this.id_usuario = id_usuario;
		ventana1.muestra(this,id);
	}
	public void elimina(String id) //elimina la publicacion (id)
	{
		int idr = Integer.parseInt(id);
		Long idreal = new Long (idr);
		servicioPublicacion.eliminaPublicacion(idreal.valueOf(id));
	}
	
	public void salir()
	{
		this.id_usuario = id_usuario;
		ventana.termina();
	}
	
	public void MuestraMensajeErrorBorrar(String mensaje)
	{
		mensajes.MuestraMensajeErrorBorrar(mensaje);
	}
	public void MuestraMensajeExito(String mensaje)
	{
		mensajes.MuestraMensajeExito(mensaje);
	}

}
