package mx.uam.ayd.proyecto.negocio.modelo;

import java.util.HashMap;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entidad de negocio RedSocial
 * 
 * @author chapuzascompany
 *
 */
@Entity
@Data
public class RedSocial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idRedSocial;

	private String nombreRed;
	private String usuario;
	
	private String contrasena;

	
	
	private HashMap <String,Long> formatoPesoVideo;
	
	private HashMap <String,Long> formatoPesoImagen;
 	
}
