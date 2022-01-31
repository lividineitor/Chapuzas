package mx.uam.ayd.proyecto.conexion;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.negocio.ServicioRedSocials;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Publicacion;
import mx.uam.ayd.proyecto.negocio.modelo.RedSocial;

@Slf4j
@Service
public class SecurityConfig {
	@Autowired 
	private ServicioRedSocials servicioRed;
	
	public void creaScripConexion(String usuario,String contrasenia,String nombreRedSocial){
		CrearArchivoConexion archivo = new CrearArchivoConexion();
		archivo.CreaArchivo(usuario, contrasenia, nombreRedSocial);
	}	
	public Boolean ejecucionScripPublicacion() {
		try {
			String cmd = "python ScripsPython\\AlgorimoPublicacion.py"; //Comando de apagado en linux
			Runtime.getRuntime().exec(cmd); 
		} catch (IOException ioe) {
			System.out.println (ioe);
		}
		return true;
	}
	
	public Boolean scripConexionRedSocial(String usuario,String contrasenia,String nombreRedSocial) {
		System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		if (nombreRedSocial=="Facebook") 
			return scripFacebookLogin(usuario, contrasenia, driver);
		else if (nombreRedSocial=="Instagram") {
			return scripInstagramLogin(usuario, contrasenia, driver);
		}
		return false;
	}
	public Boolean scripFacebookLogin(String usuario,String contrasenia,WebDriver driverFacebook) {
		driverFacebook.get("https://www.facebook.com/");
		WebElement userBox = driverFacebook.findElement(By.id("email"));
		userBox.clear();
		userBox.sendKeys(usuario);
		
		WebElement passBox = driverFacebook.findElement(By.id("pass"));
		passBox.clear();
		passBox.sendKeys(contrasenia);
		
		passBox.submit();
		driverFacebook.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Titulo de la pagina: "+driverFacebook.getTitle());
		return driverFacebook.getTitle().equals("Iniciar sesión en Facebook") ?false :true;
	}
	public Boolean scripInstagramLogin(String usuario,String contrasenia,WebDriver driverInstagram) {
		driverInstagram.get("https://www.instagram.com/");
		driverInstagram.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement userBox2 = driverInstagram.findElement(By.xpath("/html/body/div[1]/section/main/article/div[2]/div[1]/div/form/div/div[1]/div/label/input"));
		//WebElement userBox = driverInstagram.findElement(By.name("username"));
		userBox2.clear();
		userBox2.sendKeys(usuario);
		
		WebElement passBox2 = driverInstagram.findElement(By.xpath("/html/body/div[1]/section/main/article/div[2]/div[1]/div/form/div/div[2]/div/label/input"));
		passBox2.clear();
		passBox2.sendKeys(contrasenia);
		
		passBox2.submit();
		driverInstagram.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println("Titulo de la pagina: "+driverInstagram.getTitle());
		return driverInstagram.getTitle().equals("Instagram") ?true :true;
	}
	
	public Boolean scripPublicacionFacebook(Publicacion publicacion) {
		//recupero el usuario del sistema que hizo la publicacion
		long linkUsuarioPublicacion=publicacion.getIdUsuario();
		//segunda validacion para que no ocurra un error al tratar de subir la publicacion
		//esto nos ayuda en las publicaciones programadas
		if(!servicioRed.buscarInicioSesionFacebook(linkUsuarioPublicacion))
			return false;
		//obteniendo las imagenes para la publicacion
		ArrayList<File> imagenes=publicacion.getImagenes();
		//obteniendo los videos para la publicacion
		ArrayList<File> videos=publicacion.getVideos();
		//banderas para saber que se subira en el caso en que no hayan imagenes
		Boolean banderaImagenes = (imagenes.isEmpty() || imagenes==null) ?true: false;
		Boolean banderaVideos = (videos.isEmpty() || videos==null) ?true: false;
		//array para concentrar todos los files a publicar
		ArrayList<File> multimediaSubir = new ArrayList<>();
		
		if (!banderaImagenes) {
			for(File media1: imagenes)
				multimediaSubir.add(media1);
		}else if(!banderaVideos) {
			for(File media2: videos)
				multimediaSubir.add(media2);
		}
			
		
		System.out.println("Imagenes: "+imagenes);
		System.out.println("Videos: "+videos);
		System.out.println("Multimedia a publicar: "+multimediaSubir);
		
		//obteniendo los datos importantes para poder publicar en facebook
		String usuario=servicioRed.usuario;
		String contrasenia=servicioRed.password;
		CrearArchivoConexion archivo = new CrearArchivoConexion();
		archivo.CrearArchivoDatosPublicacion(publicacion.getNombreRedSocial(),publicacion.getContenido() ,usuario, contrasenia, multimediaSubir);
		ejecucionScripPublicacion();
		return true;
	}
	
	public Boolean scripPublicacionInstagram(Publicacion publicacion) {
		//recupero el usuario del sistema que hizo la publicacion
		long linkUsuarioPublicacion=publicacion.getIdUsuario();
		//segunda validacion para que no ocurra un error al tratar de subir la publicacion
		//esto nos ayuda en las publicaciones programadas
		if(!servicioRed.buscarInicioSesionInstagram(linkUsuarioPublicacion))
			return false;
		//obteniendo las imagenes para la publicacion
		ArrayList<File> imagenes=publicacion.getImagenes();
		//obteniendo los videos para la publicacion
		ArrayList<File> videos=publicacion.getVideos();
		//banderas para saber que se subira en el caso en que no hayan imagenes
		Boolean banderaImagenes = (imagenes.isEmpty() || imagenes==null) ?true: false;
		Boolean banderaVideos = (videos.isEmpty() || videos==null) ?true: false;
		//array para concentrar todos los files a publicar
		ArrayList<File> multimediaSubir = new ArrayList<>();
		
		if (!banderaImagenes) {
			for(File media1: imagenes)
				multimediaSubir.add(media1);
		}else if(!banderaVideos) {
			for(File media2: videos)
				multimediaSubir.add(media2);
		}
			
		
		System.out.println("Imagenes: "+imagenes);
		System.out.println("Videos: "+videos);
		System.out.println("Multimedia a publicar: "+multimediaSubir);
		
		//obteniendo los datos importantes para poder publicar en instagram
		String usuario=servicioRed.usuario;
		String contrasenia=servicioRed.password;
		CrearArchivoConexion archivo = new CrearArchivoConexion();
		archivo.CrearArchivoDatosPublicacion(publicacion.getNombreRedSocial(),publicacion.getContenido() ,usuario, contrasenia, multimediaSubir);
		ejecucionScripPublicacion();
		return true;
	}
}
