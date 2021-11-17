package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.* ;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entidad de negocio Cita
  * 
   * @author chapuzascompany
    *
     */
     @Entity
     @Data
     public class Cita {
      @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long idCita;

           private long idUsuario ;

              private LocalDateTime fechaCreacion ;

                private LocalDateTime fechaCita ;

                    private String notas ;

                        private String estado ;


                            }
                            