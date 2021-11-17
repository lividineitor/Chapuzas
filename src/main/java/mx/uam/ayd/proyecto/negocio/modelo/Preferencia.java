package mx.uam.ayd.proyecto.negocio.modelo;

import java.time.* ;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
* Entidad de negocio que almacena las preferencias
* 
* @author chapuzascompany
*
*/
    @Entity
    @Data
    public class Preferencia {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long idPreferencia;

        private long idUsuario ;
        private LocalDateTime fechaCreacion ;

        // Aquí se definen los valores de RN-01
        
        private ArrayList <LocalDate> diasFeriados = new ArrayList <> () ;
        private String [] diasDescanso ;

        // Aquí se definen los valores de RN-02
        
        private LocalTime horaDeApertura ;
        private LocalTime horaDeCierre ;
        
     }
