package mx.uam.ayd.proyecto.negocio.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

import java.time.* ;

/**
 * Entidad de negocio Contrato
 * 
 * @author Capuzas
 */

	@Entity
	@Data
	public class Contrato {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long idContrato ;
		
		private long idUsuario ;
		
		private String rutaContrato ;
		private LocalDateTime fechaDeCreacion ;
		private LocalDateTime fechaDeModificacion ;
	}

