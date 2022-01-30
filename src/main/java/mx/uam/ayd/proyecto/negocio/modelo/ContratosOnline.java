package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;



public class ContratosOnline {
	public String nombreDelArchivo;
	public String nombreDelCliente;
	public String UrlArchivo;
	public ContratosOnline(String nombreDelArchivo,String nombreDelCliente) {
		this.nombreDelArchivo = nombreDelArchivo;
		this.nombreDelCliente = nombreDelCliente;
	}
	@Override
	public String toString() {
		if(nombreDelCliente.isBlank() || nombreDelCliente =="" )
			return "";
			return "Cliente: "+nombreDelCliente;
	}
	
	
}
