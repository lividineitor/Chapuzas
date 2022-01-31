package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import mx.uam.ayd.proyecto.datos.ContratoRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Contrato;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

@ExtendWith(MockitoExtension.class)
class ServicioContratoTest {
	
	@Mock
	ContratoRepository contratoRepository ;
	
	@InjectMocks
	ServicioContrato servicioContrato ;

	/**
	 * Regresa todos los contratos de un usuario determinado
	 * @param Usuario
	 * @return Lista <Contrato>, con 0 o más contratos
	 */
	
	@Test
	void testObtenerContrato() {
		
		ArrayList <Contrato> contratos = new ArrayList<>();
		ArrayList <Contrato> contratosDeUsuario ;
		
		Usuario usuario1 = new Usuario () ;
		Usuario usuario2 = new Usuario () ;
		
		Contrato contrato2 = new Contrato () ;
		
		usuario1.setIdUsuario(1);
		usuario2.setIdUsuario(2);
		
		contrato2.setIdUsuario(2);
		
		// Caso de prueba 1:Regresa una lista con cero contratos cuando no hay ninguno asignado al usuario

		when(contratoRepository.findAllByIdUsuario(any(Long.class))).thenReturn(contratos) ;
		
		contratosDeUsuario = servicioContrato.obtenerContrato(usuario1);
		
		assertEquals ( 0 , contratosDeUsuario.size(), "El usuario no tiene contratos") ;
		
		// Caso de prueba 2:Regresa un contrato cuando hay un contrato asignado al usuario
		
		contratos.add(contrato2);
		
		contratosDeUsuario = servicioContrato.obtenerContrato(usuario2);
		
		assertEquals ( 1 , contratosDeUsuario.size () , "El usuario tiene contratos" ) ;
	}

	/**
	 * Elimina un contrato de un usuario si es que éste tiene uno
	 * @param Contrato
	 * @return Boolean, true de haber logrado eliminarlo o no existía contrato o false si no
	 */
	
	@Test
	void testEliminarContrato() {
		
		boolean resultado ;
		
		Contrato contrato = new Contrato ();
	
		contrato.setIdUsuario(1);

		
		// Caso de prueba 1: Elimina un contrato de un usuario que sí tiene contrato o un contrato que no corresponde a ningún usuario

		when(contratoRepository.existsById(any(Long.class))).thenReturn (false) ;
		
		resultado = servicioContrato.eliminarContrato(contrato);
		
		assertEquals ( true , resultado , "Se eliminó el contrato o no existe ningún contrato con dichos datos") ;
		
		
		// Caso de prueba 2: Trata de eliminar un contrato de un usuario y no pudo eliminarlo
		
		when(contratoRepository.existsById(any(Long.class))).thenReturn (true) ;

		resultado = servicioContrato.eliminarContrato(contrato) ;
		
		assertEquals ( false , resultado , "Se eliminó el contrato" ) ;
	}

	/**
	 * Guarda un contrato vinculado a un usuario determinado
	 * @param Usuario
	 * @return Boolean, true si se pudo guardar o false si no se pudo
	 */
	
	@Test
	void testGuardarContrato() {

		Contrato contrato = new Contrato () ;
		
		contrato.setIdUsuario(1);
		
		Contrato contrato2 = contrato ; // Hace que contrato y contrato2 sean iguales
		
		Contrato contrato3 = new Contrato () ;
		
		contrato3.setIdUsuario(2);
		
		boolean resultado ;
		
		// Caso de prueba 1: Se pudo guardar el contrato
		
		when ( contratoRepository.save( any ( Contrato.class ) ) ).thenReturn ( contrato2 ) ;
		
		resultado = servicioContrato.guardarContrato(contrato) ;
		
		assertEquals ( true , resultado , "Se pudo guardar el contrato" ) ;
		
		// Caso de prueba 2: No se pudo guardar el contrato

		when ( contratoRepository.save( any ( Contrato.class ) ) ).thenReturn ( contrato3 ) ;
		
		resultado = servicioContrato.guardarContrato(contrato) ;
		
		assertEquals ( false , resultado , "No se pudo guardar el contrato" ) ;

		
	}

}
