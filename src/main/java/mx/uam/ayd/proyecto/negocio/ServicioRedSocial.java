package mx.uam.ayd.proyecto.negocio;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.RedSocialRepository;
import mx.uam.ayd.proyecto.negocio.modelo.RedSocial;

@Slf4j
@Service
public class ServicioRedSocial {
	
	@Autowired 
	private RedSocialRepository redSocialRepository;
	
	
	//variables a usar
	private long tamano;
	private String extension;
	private RedSocial cuentaRed;
	
	//estructura Hashmap
	HashMap <String,Long> formatoPesoImg= new HashMap<String,Long>();
	HashMap <String,Long> formatoPesoVideo= new HashMap<String,Long>();	
	
	/**
	 * Busca que haya una red social activa 
	 * es decir, recupera una red social segun la red social elegida y
	 * asi nos permite decir si hay una session activa.
	 * @param red
	 * @return
	 */
	public boolean validaRed(String nombreRed) {
		System.out.println("Red social repositorio="+redSocialRepository);
		if(nombreRed==null)
			throw new IllegalArgumentException("Ningun argumento puede ser vacio");
		
		
		RedSocial redSocial = redSocialRepository.findByNombreRed(nombreRed);
		System.out.println("Red social en repositorio="+redSocial);
		if(redSocial==null) {
			return false;
		}
		return true;
	}
	
	/**
	 * Se encar de establecer un enlace y almismo tiempo
	 * registra la sesion en la red social
	 * @param usuario
	 * @param password
	 * @return
	 */
	public boolean conectarRedSocial(String usuario, String password) {
		if(usuario== null||password==null) 
			throw new IllegalArgumentException("Ningun argumento puede ser vacio");
		
		log.info("Estableciendo enlace");
		RedSocial generada = new RedSocial();
		cuentaRed = new RedSocial();
		cuentaRed.setUsuario(usuario);
		cuentaRed.setContrasena(password);
		generada=conectaAPI(cuentaRed);
		System.out.println("Red social capturada="+generada);
		if(generada.getNombreRed()==null) {
			return false;
		} 
		cuentaRed.setNombreRed(generada.getNombreRed());
		cuentaRed.setFormatoPesoImagen(generada.getFormatoPesoImagen());
		cuentaRed.setFormatoPesoVideo(generada.getFormatoPesoVideo());
		redSocialRepository.save(cuentaRed);
		
		
		return true;
	}
	
	/**
	 * Se encarga de la implementacion de la API para 
	 * la red social esto es:
	 * seguridad, establecer conexion con el repository de facebook(Simulando)
	 * y asi mismo obtiene-almacena 
	 * algunos atributos del objeto red social
	 * @param enlaceCuenta
	 */
	public RedSocial conectaAPI(RedSocial enlaceCuenta) {
		//implementacion de la api de la red social 
		//variables locales para la conexion con la red social mediante la api, observese que ya estan inicializadas una vez que se configure la api esto cambiara pero lo demas sera igual
		String nombreRed="Facebook";
		HashMap<String,Long> fImagen = new HashMap<String,Long>();
		HashMap<String,Long> fVideo = new HashMap<String,Long>();
		boolean error=false;
		/*
		if(error) {
			return enlaceCuenta;
		}*/
		fImagen.put("jpg", (long) 25);
		fImagen.put("png", (long) 10);
		fImagen.put("jpeg", (long) 15);
		
		fVideo.put("mp4", (long) 205);
		fVideo.put("mwma", (long) 100);
		//lo que debemos sacar y enviar
		enlaceCuenta.setNombreRed(nombreRed);
		enlaceCuenta.setFormatoPesoImagen(fImagen);
		enlaceCuenta.setFormatoPesoVideo(fVideo);
		return enlaceCuenta;
	}
	
	/**
	 * Se encarga de verificar los formatos y pesos de las imagenes-videos 
	 * a subir en la publicacion, esto deacuerdo a lo permitido por la red social
	 * @param red
	 * @param imagenes
	 * @param videos
	 * @return
	 */
	public boolean vFimagenYVideo(String nombreRed, ArrayList<File> imagenes, ArrayList<File> videos) {
		if(nombreRed==null||imagenes==null||videos==null)
			throw new IllegalArgumentException("No pueden haber referencias nulas");
		
		/*
		 * if(!(checaExistenciaImg(imagenes)&&checaExistenciaVid(videos)))
			return false;
		*/
		
		if(!(vFimagen(nombreRed,imagenes)&&vFvideo(nombreRed,videos)))
			return false;
		
		return true;
	}
	
	/**
	 * Se encarga de validar los formatos y pesos de las imagenes soportados por la red social
	 * @param red
	 * @param imagenes
	 * @return
	 */
	public boolean vFimagen(String nombreRed, ArrayList<File> imagenes) {
		if(nombreRed==null||imagenes==null)
			throw new IllegalArgumentException("No pueden haber referencias nulas");
		
		if(!checaExistenciaImg(imagenes))
			return false;

		/*formatoPesoImg = redSocialRepository.findByFormatoPesoImagen(nombreRed);
		if(formatoPesoImg.isEmpty())
			throw new NullPointerException("No has iniciado sesion o no se han cargado los formatos y pesos establecidos por la red social");
		*/System.out.println("Red social en repositorio="+formatoPesoImg);
		RedSocial us =redSocialRepository.findByNombreRed(nombreRed);
		boolean resultado=checaFormatoPesoMultimedia(imagenes,us.getFormatoPesoImagen());
		return resultado;
	}
	
	/**
	 * Se encarga de validar los formatos y pesos de los videos soportados por la red social
	 * @param red
	 * @param videos
	 * @return
	 */
	public boolean vFvideo(String nombreRed, ArrayList<File> videos) {
		if(nombreRed==null||videos==null)
			throw new IllegalArgumentException("No pueden haber referencias nulas");
		
		if(!checaExistenciaVid(videos))
			return false;
		
		/*formatoPesoVideo = redSocialRepository.findByFormatoPesoVideo(nombreRed);
		if(formatoPesoVideo.isEmpty())
			throw new NullPointerException("No has iniciado sesion o no se han cargado los formatos y pesos establecidos por la red social");
		*/
		System.out.println("Red social en repositorio="+formatoPesoVideo);
		RedSocial us =redSocialRepository.findByNombreRed(nombreRed);
		boolean resultado=checaFormatoPesoMultimedia(videos,us.getFormatoPesoVideo());
		return resultado;
	}
	
	////METODOS AUXILIARES////
	
	private boolean checaExistenciaImg(ArrayList<File> imagenes) {
		
		//verificamos que cada archivo imagen exista en la ubicacion dada
		for(File direccion: imagenes)
			if(!direccion.exists()) {return false;}
		return true;
	}
	
	private boolean checaExistenciaVid(ArrayList<File> videos) {
		
		//verificamos que cada archivo imagen exista en la ubicacion dada
		for(File direccion: videos)
			if(!direccion.exists()) {return false;}
		return true;
	}
	/**
	 * Este medodo auxiliar nor permite verificar los formatos y peso 
	 * tanto para imagenes y videos, es decir es un metodo con doble funcion
	 * Nos permite solucionar el siguiente error (bug):
	 * Si separamos este metodo como checaFormatoPesoImagen & checaFormatoPesoVideo 
	 * y hacemos esto 
	 * -> boolean resultado=checaFormatoPesoImagen(videos,formatoPesoVideo);
	 * -> return resultado;
	 * Esto nos crea un bug pues formatoPesoVideo trae la informacion necesaria y al meter en el metodo checaFormatoPesoImagen,
	 * esté interpreta que los que se esta verificando son imagens con sus formatos y pesos de formatoPesoVideo cuando en realidad tenemos 
	 * que son videos los que deben verificarse.
	 * @param multimedia
	 * @param formatoPesoMultimedia
	 * @return
	 */
	private boolean checaFormatoPesoMultimedia(ArrayList<File> multimedia, HashMap <String, Long> formatoPesoMultimedia) {
		
		for(File direccion: multimedia) {
			int i= direccion.getName().lastIndexOf('.');
			extension = direccion.getName().substring(i+1).toLowerCase();
			tamano=direccion.length()/1048576;
			if(formatoPesoMultimedia.containsKey(extension)) { 
				if(tamano>formatoPesoMultimedia.get(extension)) {
					multimedia.remove(direccion);
					continue;
					}
			}else if(!formatoPesoMultimedia.containsKey(extension)) {
				multimedia.remove(direccion);
				return false;
			}
		}
		return true;
	}
	
	public RedSocial formatosPesos(String nombreRed){
		return redSocialRepository.findByNombreRed(nombreRed);
	}
	public HashMap<String,Long> forms(String nombreRed){
		RedSocial us =redSocialRepository.findByNombreRed(nombreRed);
		
		return us.getFormatoPesoImagen();
	}
	
	//cambios
	public boolean conectar(String nombreRedSocial,String usuario,String contrasenia,long id_user) {
		if(usuario.isEmpty() || usuario==null )
			throw new IllegalArgumentException("Ningun argumento puede ser vacio");
		else if(conectarAPIs(usuario,contrasenia,nombreRedSocial)==false)
			return false;
		
		//estructuras 
		HashMap<String, Long> fImagen = new HashMap<String, Long>();
		HashMap<String, Long> fVideo = new HashMap<String, Long>();
		log.info("Estableciendo enlace");
		RedSocial cuentaRed= new RedSocial();
		cuentaRed.setIdUsuario(id_user);
		cuentaRed.setNombreRed(nombreRedSocial);
		cuentaRed.setUsuario(usuario);
		cuentaRed.setContrasena(contrasenia);
		
		if (nombreRedSocial.equals("Facebook")) {
			// conexion facebook
			// banderaError =
			// conectFecebook(enlaceCuenta.getUsuario(),enlaceCuenta.getContrasena())

			// consultado: https://es-la.facebook.com/business/ads-guide/image
			fImagen.put("jpg", (long) 30);
			fImagen.put("png", (long) 30);

			// consultado: https://es-la.facebook.com/business/ads-guide/video
			fVideo.put("mp4", (long) 3000);
			fVideo.put("mov", (long) 3000);
			fVideo.put("gif", (long) 5);
		} else if (nombreRedSocial.equals("Instagram")) {
			// banderaError =
			// conectInstagram(enlaceCuenta.getUsuario(),enlaceCuenta.getContrasena())
			fImagen.put("jpg", (long) 30);
			fImagen.put("png", (long) 30);
			fVideo.put("mp4", (long) 3000);
			fVideo.put("mov", (long) 3000);
			fVideo.put("gif", (long) 5);
		}
		cuentaRed.setFormatoPesoImagen(fImagen);
		cuentaRed.setFormatoPesoVideo(fVideo);
		
		System.out.println("Red social capturada="+cuentaRed);
		redSocialRepository.save(cuentaRed);
		
		return true;
	}
	
	/**
	 * Este metodo se encarag de trabajar en conjunto con las apis
	 * y iniciando sesion en la red social elegida
	 * @param usuario
	 * @param contrasenia
	 * @param nombreRedSocial
	 * @return
	 */
	public boolean conectarAPIs(String usuario,String contrasenia, String nombreRedSocial) {
		
		//bandera que nos ayuda determinar con la API si ocurrio un error
		boolean banderaConexion = true;
		
		/*if(nombreRedSocial.equals("Facebook"))
			banderaConexion=conectFacebook( usuario, contrasenia);
		else if(nombreRedSocial.equals("Instagram"))
			banderaConexion=conectInstagram( usuario, contrasenia);*/
		return banderaConexion;
	}
}

