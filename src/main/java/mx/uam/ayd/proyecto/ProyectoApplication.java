package mx.uam.ayd.proyecto;

import java.time.LocalTime;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mx.uam.ayd.proyecto.datos.GrupoRepository;
import mx.uam.ayd.proyecto.datos.UsuarioRepository;
import mx.uam.ayd.proyecto.datos.PreferenciaRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;

import mx.uam.ayd.proyecto.negocio.modelo.Preferencia;


/**
 * 
 * Clase principal que arranca la aplicación 
 * construida usando el principio de 
 * inversión de control
 * 
 * Ejemplo de cambio en Rama
 *
 * @author Chapuzas
 * 
 * 
 * Cesar es bueno
 *
 */
@SpringBootApplication
public class ProyectoApplication {

	@Autowired
	ControlPrincipal controlPrincipal;
	
	@Autowired
	GrupoRepository grupoRepository;

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PreferenciaRepository preferenciaRepository;
	
	/**
	 * 
	 * Método principal
	 * 
	 */
	public static void main(String[] args) {
		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(ProyectoApplication.class);

		builder.headless(false);

		builder.run(args);
	}

	/**
	 * Metodo que arranca la aplicacion
	 * inicializa la bd y arranca el controlador
	 * otro comentario
	 */
	@PostConstruct
	public void inicia() {
		
		inicializaBD();
		
		controlPrincipal.inicia();
	}
	
	
	/**
	 * 
	 * Inicializa la BD con datos
	 * 
	 * 
	 */
	public void inicializaBD() {
		
		// Vamos a crear los dos grupos de usuarios
		
		Grupo grupoAdmin = new Grupo();
		grupoAdmin.setNombre("Administradores");
		grupoRepository.save(grupoAdmin);
		
		Grupo grupoOps = new Grupo();
		grupoOps.setNombre("Operadores");
		grupoRepository.save(grupoOps);
		
		// Se crean temporalmente las preferencias de fechas

		Usuario usuario = new Usuario () ;
		usuario.setNombre( "a" ) ;
		usuario.setApellido("a");
		usuario.setContraseña("a");
		usuario.setEmail("a");

		usuarioRepository.save(usuario);
		
		String [] dias = {"Saturday" , "Sunday" } ;

		
		
		Preferencia preferencias = new Preferencia () ;
		
		preferencias.setIdUsuario(0);
		preferencias.setDiasDescanso(dias);
		preferencias.setHoraDeApertura(LocalTime.of(10, 0));
		preferencias.setHoraDeCierre(LocalTime.of(18, 0));
		preferenciaRepository.save(preferencias) ;
	}
}
