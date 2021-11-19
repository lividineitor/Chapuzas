package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entidad de negocio Usuario
 * 
 * @author Chapuzas
 *
 */
@Entity
@Data
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idUsuario;

	private String nombre;
	
	private String apellido;
	
	private String telefono;
	
	private String email;
	
	private String permisos;
	
	private String contraseña;
	
	private int dia;
	
	private int mes;
	
	private int annio;
	
	private Date fechaderegistro;
	

}
