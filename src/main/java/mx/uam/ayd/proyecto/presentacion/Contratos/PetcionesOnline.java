package mx.uam.ayd.proyecto.presentacion.Contratos;

public class PetcionesOnline {
	public String MensajeAdional = null;
	String NombreDelCliente;
	String Peticion;
	String Direccion;
	String email;
	String telefono;
	public String Apellido;
	public PetcionesOnline( String NombreDelCliente,String Apellido, String Peticion, String Direccion, String email, String telefono,String MensajeAdional) {
		this.NombreDelCliente= NombreDelCliente;
		this.Peticion= Peticion;
		this.Direccion= Direccion;
		this.email= email;
		this.telefono= telefono;
		this.Apellido = Apellido;
		this.MensajeAdional = MensajeAdional;

	}
	@Override
	public String toString() {
		if(NombreDelCliente.isBlank() || NombreDelCliente =="" )
			return "";
			return "Cliente: "+NombreDelCliente+" "+Apellido;	}
}
