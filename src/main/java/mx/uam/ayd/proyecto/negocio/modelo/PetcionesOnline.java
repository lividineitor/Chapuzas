package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;


public class PetcionesOnline {
 public String MensajeAdional;
 public String nombreDelCliente;
 public String Peticion;
 public String Direccion;
 public String email;
 public String telefono;
 public String Apellido;
	public PetcionesOnline( String NombreDelCliente,String Apellido, String Peticion, String Direccion, String email, String telefono,String MensajeAdional) {
		this.nombreDelCliente= NombreDelCliente;
		this.Peticion= Peticion;
		this.Direccion= Direccion;
		this.email= email;
		this.telefono= telefono;
		this.Apellido = Apellido;
		this.MensajeAdional = MensajeAdional;

	}
	@Override
	public String toString() {
		if(nombreDelCliente.isBlank() || nombreDelCliente =="" )
			return "";
			return "Cliente: "+nombreDelCliente+" "+Apellido;	
			}

}                                  
