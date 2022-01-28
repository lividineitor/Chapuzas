package mx.uam.ayd.proyecto.presentacion.GestionarClientes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.uam.ayd.proyecto.negocio.ServicioContrato;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import mx.uam.ayd.proyecto.negocio.modelo.Contrato;


/**
 * Módulo que controla la HU-13 Gestión de clientes
 * 
 * @author Chapuzas
 *
 */

@Component
public class ControlGestionarClientes {

	@Autowired
	private ServicioContrato servicioContrato ;
	
	@Autowired
	private ServicioUsuario servicioUsuario ;
	
	@Autowired
	private VistaGestionDeClientes vistaGestionDeClientes ;
	
	/**
	 * Inicia la historia de usuario
	 */
	
	public void inicio () {
		ArrayList <Usuario> clientes = servicioUsuario.recuperaUsuarios();
		vistaGestionDeClientes.inicio ( this , clientes ) ;
	}
	
	/**
	 * Métodos utilizados por las vistas
	 */

	//	Métodos relacionados con los Clientes
	
	/**
	 * Obtiene todos los clientes del sistema
	 * @param void
	 * @return ArrayList <Usuario>
	 */

	public ArrayList <Usuario> obtenerClientes () {
		ArrayList <Usuario> clientes = new ArrayList <>() ; 
		
		clientes = servicioUsuario.recuperaUsuarios() ;
		
		return clientes ;
	}
	
	/**
	 * Actualiza los datos del cliente
	 * 
	 * @param Usuario: El usuario modificado
	 * @return Boolean
	 */
	
	public boolean actualizarDatos ( Usuario usuario ) {
		return servicioUsuario.ModificarCuenta(usuario);
	}
	
	// Métodos relacionados con los Contratos
	
	/**
	 * Obtiene todos los contratos de un cliente
	 * 
	 * @param Usuario
	 * @return ArrayList <Contrato>
	 */
	
	public ArrayList <Contrato> obtenerContrato ( Usuario usuario ) {
		ArrayList <Contrato> contratos = new ArrayList <> () ;
		
		contratos = servicioContrato.obtenerContrato(usuario) ;
		
		return contratos ;
	}
	
	/**
	 * Guarda el contrato nuevo
	 * 
	 * @param Contrato
	 * @return Boolean
	 */
	
	public boolean guardarContrato ( Contrato contrato ) {
		return servicioContrato.guardarContrato(contrato);
	}
	
	/**
	 * Elimina el contrado dado
	 * 
	 * @param Contrato
	 * @return Boolean
	 * 
	 */
	
	public boolean eliminarContrato ( Contrato contrato ) {
		return servicioContrato.eliminarContrato(contrato);
	}
	
	/**
	 * Termina las vistas
	 * 
	 */
	
	public void terminaGestionClientes () {
		vistaGestionDeClientes.setVisible (false) ;
	}
	
}
