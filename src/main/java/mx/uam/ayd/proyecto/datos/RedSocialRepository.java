package mx.uam.ayd.proyecto.datos;

import java.util.HashMap;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.RedSocial;

public interface RedSocialRepository extends CrudRepository <RedSocial, Long> {
	
	/**
	 * Se encarga de revisar que haya un objeto de red social con la red social elegida
	 * es decir para saber si ha iniciado sesion previamente en la red social elegida
	 * @param red
	 * @return
	 */
	public RedSocial findByNombreRed(String nombreRed);
	
	/**
	 * Se encarga de recuperar el HashMap con los formatos y pesos correspondientes a la imagen
	 * segun la red social elegida
	 * @param red
	 * @return
	 */
	public HashMap<String, Long> findByFormatoPesoImagen(String nombreRed);
	
	/**
	 * Se encarga de recuperar el HashMap con los formatos y pesos correspondientes a el video
	 * segun la red social elegida
	 * @param red
	 * @return
	 */
	public HashMap<String, Long> findByFormatoPesoVideo(String nombreRed);
	
	/**
	 * Se encarga de revisar si existe un objeto de redsocial con el idUsuario dado
	 * esta limitado a una sola red social
	 * @param idUsuario
	 * @return
	 */
	public RedSocial findByIdUsuario(long idUsuario);
	
	
	/**
	 * Se encarga de revisar si existe un objeto de redsocial con el idUsuario dado y el nombre de red
	 * este no es util para multiples redes sociales
	 * @param idUsuario
	 * @param nombreRed
	 * @return
	 */
	public RedSocial findByIdUsuarioAndNombreRed(long idUsuario,String nombreRed);
	

}