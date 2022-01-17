package mx.uam.ayd.proyecto.conexion;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioRedSocials;

@Slf4j
@Service
public class SecurityConfig {
	@Autowired 
	private ServicioRedSocials servicioRed;
	
	public void creaScripConexion(String usuario,String contrasenia,String nombreRedSocial){
		CrearArchivoConexion archivo = new CrearArchivoConexion();
		archivo.CreaArchivo(usuario, contrasenia, nombreRedSocial);
	}	
	public Boolean ejecucionScripConexion() {
		try {
			String cmd = "python ScripsPython\\DriverConexionRedSocial.py"; //Comando de apagado en linux
			Runtime.getRuntime().exec(cmd); 
		} catch (IOException ioe) {
			System.out.println (ioe);
		}
		return true;
	}
}
