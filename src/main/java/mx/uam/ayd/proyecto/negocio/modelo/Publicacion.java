package mx.uam.ayd.proyecto.negocio.modelo;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


import lombok.Data;

/**
 * Entidad de negocio Publicacion
 * 
 * @author chapuzascompany
 *
 */
@Entity
@Data
public class Publicacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idPublicacion;
	
	
	private long idUsuario;
 	private LocalDate fechaCreacion ;

 	private LocalDate fechaModificacion ;

 	private LocalDate fechaProgramada ;
 	
	private String  nombreRedSocial;
	
	
	private String titulo;
	
	private String contenido;
	
	private ArrayList <File> videos; 

	private ArrayList <File> imagenes; 
}
