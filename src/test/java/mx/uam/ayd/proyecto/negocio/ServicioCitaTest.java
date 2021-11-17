package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.negocio.ServicioCita;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import mx.uam.ayd.proyecto.datos.CitaRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
class ServicioCitaTest {

 @Mock
  CitaRepository citaRepository ;

    @InjectMocks
     ServicioCita servicioCita ;

       /*
         * Regresa todas las citas en la base de datos
           * @param void
             * @return Lista <Cita>, con 0 o más citas
               * */

                 @Test
                  void testObtenerCitas() {
                    // Caso de prueba 1: Regresa todas las citas cuando la base de datos está vacía

                        List <Cita> citas ;

                            List <Cita> lista = new ArrayList <> () ;

                                when(citaRepository.findAll()).thenReturn(lista);

                                    citas = servicioCita.obtenerCitas () ;

                                        assertEquals (0 , citas.size () , "No hay citas." ) ;

                                            // Caso de prueba 2: Regresa todas las citas cuando la base de datos tiene citas

                                              Cita citaDePrueba1 = new Cita () ;

                                                  citaDePrueba1.setIdUsuario ( 20 ) ;
                                                    citaDePrueba1.setNotas ( "Primer cita" ) ;
                                                      citaDePrueba1.setEstado ( "Pendiente" ) ;
                                                        citaDePrueba1.setFechaCreacion ( LocalDateTime.now () ) ;
                                                          citaDePrueba1.setFechaCita ( LocalDateTime.now ()) ;

                                                            Cita citaDePrueba2 = new Cita () ;

                                                                citaDePrueba2.setIdUsuario ( 20 ) ;
                                                                  citaDePrueba2.setNotas ( "Segunda cita" ) ;
                                                                    citaDePrueba2.setEstado ( "Pendiente" ) ;
                                                                      citaDePrueba2.setFechaCreacion ( LocalDateTime.now () ) ;
                                                                        citaDePrueba2.setFechaCita ( LocalDateTime.now () ) ;

                                                                            lista.add(citaDePrueba1);
                                                                              lista.add(citaDePrueba2);

                                                                                when(citaRepository.findAll()).thenReturn(lista);

                                                                                    citas = servicioCita.obtenerCitas () ;

                                                                                        assertEquals ( 2 , citas.size () , "Si hay citas." ) ;

                                                                                           }

                                                                                            /*
                                                                                              * Regresa todas las citas que coincidan con el usuario y hora en la cita dada.
                                                                                                * @param Cita
                                                                                                  * @return Lista <Cita>
                                                                                                    * */

                                                                                                      @Test
                                                                                                       void testObtenerHorasDisponibles() {

                                                                                                           List <Cita> citas ;

                                                                                                               Cita citaDePrueba1 = new Cita () ;

                                                                                                                   citaDePrueba1.setIdUsuario ( 20 ) ;
                                                                                                                     citaDePrueba1.setNotas ( "Primer cita" ) ;
                                                                                                                       citaDePrueba1.setEstado ( "Pendiente" ) ;
                                                                                                                         citaDePrueba1.setFechaCreacion ( LocalDateTime.now () ) ;
                                                                                                                           citaDePrueba1.setFechaCita ( LocalDateTime.now ().minusDays(2) ) ;

                                                                                                                             Cita citaDePrueba2 = new Cita () ;

                                                                                                                                 citaDePrueba2.setIdUsuario ( 20 ) ;
                                                                                                                                   citaDePrueba2.setNotas ( "Segunda cita" ) ;
                                                                                                                                     citaDePrueba2.setEstado ( "Pendiente" ) ;
                                                                                                                                       citaDePrueba2.setFechaCreacion ( LocalDateTime.now () ) ;
                                                                                                                                         citaDePrueba2.setFechaCita ( LocalDateTime.now ().minusDays(2) ) ;

                                                                                                                                             Cita citaDePrueba3 = new Cita () ;

                                                                                                                                                 citaDePrueba3.setIdUsuario(30);
                                                                                                                                                   citaDePrueba3.setNotas ( "Tercera cita" ) ;
                                                                                                                                                     citaDePrueba3.setEstado ( "Pendiente" ) ;
                                                                                                                                                       citaDePrueba3.setFechaCreacion ( LocalDateTime.now () ) ;
                                                                                                                                                         citaDePrueba3.setFechaCita ( LocalDateTime.now () ) ;

                                                                                                                                                             Cita citaDePrueba4 = new Cita () ;

                                                                                                                                                                 citaDePrueba4.setIdUsuario(20);
                                                                                                                                                                   citaDePrueba4.setNotas ( "citaPruebaHoras" ) ;
                                                                                                                                                                     citaDePrueba4.setEstado ( "Pendiente" ) ;
                                                                                                                                                                       citaDePrueba4.setFechaCreacion ( LocalDateTime.now () ) ; //.minusDays ( 2 ) ) ;
                                                                                                                                                                         citaDePrueba4.setFechaCita ( LocalDateTime.now ().minusDays ( 2 ) ) ;


                                                                                                                                                                               List <Cita> lista = new ArrayList <> () ;

                                                                                                                                                                                   lista.add(citaDePrueba1);
                                                                                                                                                                                     lista.add(citaDePrueba2);
                                                                                                                                                                                       lista.add(citaDePrueba3);
                                                                                                                                                                                         lista.add(citaDePrueba4);

                                                                                                                                                                                             // Caso de prueba 1: Regresa una lista de usuarios que coinciden con el IdUsuario y la fecha dada.

                                                                                                                                                                                                 when(citaRepository.findAllByIdUsuario(any(Long.class))).thenReturn(lista);

                                                                                                                                                                                                     citas = servicioCita.obtenerHorasDisponibles ( citaDePrueba4 ) ;

                                                                                                                                                                                                         assertEquals ( 3 , citas.size () , "Si hay citas." ) ;

                                                                                                                                                                                                             // Caso de prueba 2: Regresa una lista de usuarios en donde la única coincidencia es su propia cita.


                                                                                                                                                                                                                   when(citaRepository.findAllByIdUsuario(any(Long.class))).thenReturn(lista);

                                                                                                                                                                                                                       citas = servicioCita.obtenerHorasDisponibles ( citaDePrueba3 ) ;

                                                                                                                                                                                                                           assertEquals ( 1 , citas.size () , "Si hay citas." ) ;

                                                                                                                                                                                                                              }


                                                                                                                                                                                                                               /*
                                                                                                                                                                                                                                @Test
                                                                                                                                                                                                                                 void testConfirmarCitaUsuarioCita() {




                                                                                                                                                                                                                                          }
                                                                                                                                                                                                                                          */
                                                                                                                                                                                                                                            /*
                                                                                                                                                                                                                                              * Actualiza el estado de un usuario ya dado. El valor que evalúa el éxito es el campo estado, cuando tanto en el argumento como el recuperado son iguales.
                                                                                                                                                                                                                                                * @param Cita
                                                                                                                                                                                                                                                  * @Return boolean
                                                                                                                                                                                                                                                    * */

                                                                                                                                                                                                                                                     @Test
                                                                                                                                                                                                                                                      void testConfirmarCitaCita() {
                                                                                                                                                                                                                                                        // Caso de prueba 1: Cuando se realiza el cambio de forma exitosa.

                                                                                                                                                                                                                                                              Cita citaDePrueba2 = new Cita () ;

                                                                                                                                                                                                                                                                      citaDePrueba2.setIdUsuario ( 20 ) ;
                                                                                                                                                                                                                                                                          citaDePrueba2.setNotas ( "Segunda cita" ) ;
                                                                                                                                                                                                                                                                              citaDePrueba2.setEstado ( "Pendiente" ) ;
                                                                                                                                                                                                                                                                                  citaDePrueba2.setFechaCreacion ( LocalDateTime.now () ) ;
                                                                                                                                                                                                                                                                                      citaDePrueba2.setFechaCita ( LocalDateTime.now () ) ;

                                                                                                                                                                                                                                                                                              Cita citaDePrueba3 = new Cita () ;

                                                                                                                                                                                                                                                                                                      citaDePrueba3.setIdUsuario ( 20 ) ;
                                                                                                                                                                                                                                                                                                          citaDePrueba3.setNotas ( "Segunda cita" ) ;
                                                                                                                                                                                                                                                                                                              citaDePrueba3.setEstado ( "Confirmado" ) ;
                                                                                                                                                                                                                                                                                                                  citaDePrueba3.setFechaCreacion ( LocalDateTime.now () ) ;
                                                                                                                                                                                                                                                                                                                      citaDePrueba3.setFechaCita ( LocalDateTime.now () ) ;

                                                                                                                                                                                                                                                                                                                              boolean resultado ;

                                                                                                                                                                                                                                                                                                                                      when (citaRepository.save(any(Cita.class))).then(new Answer <Cita> ()
                                                                                                                                                                                                                                                                                                                                            {
                                                                                                                                                                                                                                                                                                                                                   @Override
                                                                                                                                                                                                                                                                                                                                                          public Cita answer(InvocationOnMock invocation) throws Throwable
                                                                                                                                                                                                                                                                                                                                                                 {
                                                                                                                                                                                                                                                                                                                                                                         Cita citaRecibida = (Cita) invocation.getArgument ( 0 ) ;
                                                                                                                                                                                                                                                                                                                                                                                 assertEquals ( citaRecibida.getEstado() , citaDePrueba2.getEstado () ) ;
                                                                                                                                                                                                                                                                                                                                                                                         return citaRecibida ;
                                                                                                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                                                                                                                      }) ;

                                                                                                                                                                                                                                                                                                                                                                                                              resultado = servicioCita.confirmarCita ( citaDePrueba2 ) ;

                                                                                                                                                                                                                                                                                                                                                                                                                      assertTrue ( resultado , "Se actualizó la cita.") ;    

                                                                                                                                                                                                                                                                                                                                                                                                                           }

                                                                                                                                                                                                                                                                                                                                                                                                                           }
                                                                                                                                                                                                                                                                                                                                                                                                                           