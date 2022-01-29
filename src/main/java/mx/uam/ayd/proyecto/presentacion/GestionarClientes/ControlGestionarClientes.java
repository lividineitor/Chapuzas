package mx.uam.ayd.proyecto.presentacion.GestionarClientes;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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
		vistaGestionDeClientes.inicio ( this ) ;
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
		
		clientes = servicioUsuario.recuperaPorRol("Cliente") ;
		
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
	
	/**
	 * Elimina al cliente dado y toda su información
	 * @param Usuario
	 * @return Boolean
	 */
	
	public boolean eliminarCliente (Usuario usuario) {
		return servicioUsuario.eliminarUsuario(usuario);
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
	 * Método para mostrar diálogos de estado
	 * @param Int (valor del estado 0: error, 1: éxito) , String (Mensaje a desplegar)
	 */
	
	public void confirmacion ( int estado , String mensaje ) {
		
		String tipoDeEstado = null ;
		
		if ( estado == 0 )
			tipoDeEstado = "ERROR" ;
		else if ( estado == 1 )
			tipoDeEstado = "ÉXITO" ;
		
		JOptionPane.showMessageDialog(null, tipoDeEstado + "\n" + mensaje );
			
	}
	
	/**
	 * Termina las vistas
	 * 
	 */
	
	public void terminaGestionClientes () {
		vistaGestionDeClientes.setVisible (false) ;
	}
	
}
