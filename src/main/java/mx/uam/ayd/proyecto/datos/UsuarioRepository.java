package mx.uam.ayd.proyecto.datos;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

/**
 * 
 * Repositorio para usuarios
 * 
 * @author Chapuzas
 *
 */
public interface UsuarioRepository extends CrudRepository <Usuario, Long> {
	
	public Usuario findByNombreAndApellido(String nombre, String apellido); 
	
	public Usuario findByEmail(String email); //Busqueda por email
	
	public List <Usuario> findByDiaAndMesAndAnnio(int dia,int mes, int annio);
	
	public List <Usuario> findByMesAndAnnio(int mes, int annio);
	
	public List <Usuario> findByAnnio(int annio);
	
	//cambios version
	
	public Usuario findByNombre(String nombre);

}
