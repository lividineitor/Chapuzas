package mx.uam.ayd.proyecto.negocio;

import mx.uam.ayd.proyecto.negocio.ServicioCita;
import mx.uam.ayd.proyecto.negocio.modelo.Cita;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import mx.uam.ayd.proyecto.presentacion.Contratos.ContratosOnline;
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
class ServicioContratoTest {

	@Mock
	CitaRepository citaRepository;

	@InjectMocks
	ServicioCita servicioCita;

	/* Regresa todos los contratos
	 * 
	 * @param void
	 * 
	 * @return Lista <Cita>, con 0 o más citas
	 */

	@Test
	void testObtenerCitas() {
		// Caso de prueba 1: Regresa todas las citas cuando la base de datos está vacía

	 
		List<ContratosOnline> Contratos1 = new ArrayList<>();
		Contratos1.add(new ContratosOnline("UnArchivo.pdf", "Juanin"));
		Contratos1.add(new ContratosOnline("segundoArchivo.pdf", "Juanin2"));
		assertEquals(2, Contratos1.size(), "Si hay citas.");

	}


}