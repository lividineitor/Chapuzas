package mx.uam.ayd.proyecto;

import java.time.LocalTime;

import javax.annotation.PostConstruct;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException ;
import javax.swing.plaf.* ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import mx.uam.ayd.proyecto.datos.UsuarioRepository;
import mx.uam.ayd.proyecto.datos.PreferenciaRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

import mx.uam.ayd.proyecto.presentacion.principal.ControlPrincipal;

import mx.uam.ayd.proyecto.negocio.modelo.Preferencia;


/**
 * 
 * Clase principal que arranca la aplicación 
 * construida usando el principio de 
 * inversión de control
 *
 * @author Chapuzas
 */

@SpringBootApplication
public class ProyectoApplication {

	@Autowired
	ControlPrincipal controlPrincipal;
	
	// usuarioRepository y preferenciasRepository son temporales hasta que la clase de configuración esté desarrollada.
	
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
		
		seleccionarGUI () ;

		
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
		
		// Se crean temporalmente las preferencias de fechas

		String [] dias = {"Saturday" , "Sunday" } ;

		Preferencia preferencias = new Preferencia () ;
		
		preferencias.setDiasDescanso(dias);
		preferencias.setHoraDeApertura(LocalTime.of(10, 0));
		preferencias.setHoraDeCierre(LocalTime.of(18, 0));
		preferencias.setPerdiodoParaCitas(3);
		preferenciaRepository.save(preferencias) ;
	}
	
	private static void seleccionarGUI ()
	{
		String temaDelSistema = null ;
		
		String temaWindows = null ;
		String temaMac = null ;
		String temaGTK = null ;
		String temaDefault = null ;
		
		UIManager.LookAndFeelInfo [] lf = UIManager.getInstalledLookAndFeels() ;
		
		for (UIManager.LookAndFeelInfo tema : lf )
		{
			if ( tema.getClassName().equals ( "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" ) )
			{
				temaWindows = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel" ;
			}
/*			
			else if ()
			{
				temaMac = "" ;
			}
*/			
			else if ( tema.getClassName().equals ( "com.sun.java.swing.plaf.gtk.GTKLookAndFeel" ) )
			{
				temaGTK = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel" ;
			}
			
			else
			{
				temaDefault = "javax.swing.plaf.metal.MetalLookAndFeel" ;
			}
		}

		if ( temaGTK != null )
			temaDelSistema = temaGTK ;
		
		else if ( temaWindows != null )
			temaDelSistema = temaWindows ;
		
		else
			temaDelSistema = temaDefault ;
	
		try
		{

			UIManager.setLookAndFeel( temaDelSistema );
		}
		
		catch ( UnsupportedLookAndFeelException e)
		{
			System.out.println ( "Sin soporte." ) ;
		}

		catch ( ClassNotFoundException e )
		{
			System.out.println ( "Clase no encontrada." ) ;
		}

		catch ( InstantiationException e )
		{
			System.out.println ( "Problemas de instanciación." ) ;
		}

		catch ( IllegalAccessException e )
		{
			System.out.println ( "Acceso ilegal." ) ;
		}

	}
	
}
