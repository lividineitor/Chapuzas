package mx.uam.ayd.proyecto.negocio;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.ayd.proyecto.datos.RedSocialRepository;
import mx.uam.ayd.proyecto.negocio.modelo.RedSocial;

@Slf4j
@Service
public class ServicioRedSocials {
	@Autowired 
	private RedSocialRepository redSocialRepository;
	
	/**
	 * Este metodo nos ayuda en el caso en que no hay una cuenta de facebook
	 * enlazada para llamr al formualrio de fabook e iniciar sesion
	 * 
	 * Por el contrario en el caso en que haya una cuenta registrada
	 * se autoconecta a la cuenta
	 * @param idUsuario
	 * @return
	 */
	public boolean buscarInicioSesionFacebook(long idUsuario) {
		
		//RedSocial  usuario = redSocialRepository.findByIdUsuario(idUsuario);
		RedSocial  usuario1 = redSocialRepository.findByIdUsuarioAndNombreRed(idUsuario, "Facebook");
		
		System.out.println("Esto se obtiene de findIdUserAndNombreRed--> "+usuario1);
		if (usuario1 ==null)
			return false;
		return true;
	}
	/**
	 * Este metodo nos ayuda en el caso en que no hay una cuenta de instagram
	 * enlazada para llamr al formualrio de fabook e iniciar sesion
	 * 
	 * Por el contrario en el caso en que haya una cuenta registrada
	 * se autoconecta a la cuenta
	 * @param idUsuario
	 * @return
	 */
	public boolean buscarInicioSesionInstagram(long idUsuario) {
		RedSocial  usuario1 = redSocialRepository.findByIdUsuarioAndNombreRed(idUsuario, "Instagram");
		if (usuario1 ==null)
			return false;
		return true;
	}
	/**
	 * Este metodo se encraga de conectar la red social y crear en 
	 * memoria un enlace entre el usuario del sistema y su cuenta de red social
	 * @param nombreRedSocial
	 * @param usuario
	 * @param contrasenia
	 * @param id_user
	 * @return
	 */
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
	 * Este metodo se encarga de trabajar en conjunto con las apis
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
	
	/**
	 * este metodo se encarga de autonectar la red social cuando el usuairo actual del sistema
	 * ha iniciado sesion antes, uso limitado y aun presenta bugs
	 * @param idUsuario
	 * @return
	 */
	public boolean autoConectar(long idUsuario) {
		RedSocial  usuario = redSocialRepository.findByIdUsuario(idUsuario);
		if (usuario ==null)
				return false;
		
		return conectarAPIs(usuario.getUsuario(),usuario.getContrasena(),usuario.getNombreRed());
	}
	
	/**
	 * este metodo se encarga de autonectar la red social facebook cuando el usuario actual del sistema
	 * ha iniciado sesion antes, uso ilimitado y no se han encontrado bugs
	 * @param idUsuario
	 * @return
	 */
	public boolean autoConectarFacebook(long idUsuario) {
		RedSocial  usuario2 = redSocialRepository.findByIdUsuarioAndNombreRed(idUsuario, "Facebook");
		if (usuario2 ==null)
				return false;
		
		return conectarAPIs(usuario2.getUsuario(),usuario2.getContrasena(),usuario2.getNombreRed());
	}
	
	/**
	 * este metodo se encarga de autonectar la red social instagram cuando el usuario actual del sistema
	 * ha iniciado sesion antes, uso ilimitado y no se han encontrado bugs
	 * @param idUsuario
	 * @return
	 */
	public boolean autoConectaInstagram(long idUsuario) {
		RedSocial  usuario1 = redSocialRepository.findByIdUsuarioAndNombreRed(idUsuario, "Instagram");
		if (usuario1 ==null)
				return false;
		
		return conectarAPIs(usuario1.getUsuario(),usuario1.getContrasena(),usuario1.getNombreRed());
	}
}
