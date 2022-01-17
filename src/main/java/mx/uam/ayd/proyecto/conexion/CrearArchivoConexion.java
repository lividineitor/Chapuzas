package mx.uam.ayd.proyecto.conexion;

import java.io.*;

import mx.uam.ayd.proyecto.negocio.modelo.RedSocial;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

public class CrearArchivoConexion {

	// atributos de objeto
	private String nombreArchivo;
	private String codificacion;

	public CrearArchivoConexion() {
		super();
		this.codificacion = "UTF-8";
		this.nombreArchivo = "Usr.sgf";
	}

	public void CreaArchivo(String usuario, String contrasenia, String nombreRedSocial) {
		try {
			FileWriter miArchivo = new FileWriter(nombreArchivo);
			
			miArchivo.write("usuario:"+usuario+"\n");
			miArchivo.write("password:"+contrasenia+"\n");
			miArchivo.write("redSocial:"+nombreRedSocial+" \n");
			miArchivo.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

}