package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.PublicacionRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Publicacion;

@ExtendWith(MockitoExtension.class)
class ServicioPublicacionTest {
	@Mock
	private PublicacionRepository publicacionRepository;
	
	@InjectMocks
	private ServicioPublicacion servicioPub;
	
	
	Publicacion pub;
	String fechaPr;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}

	@Test
	void testCPublicacionStringStringStringGregorianCalendarGregorianCalendar() {
		
		pub =new Publicacion();
		pub.setTitulo("Mi rpimer publicacion");
		pub.setContenido("loremfhgfgjh"
				+ "+ffjshdfjkahfkja"
				+ "fbdhabjahbfabdavfjda");
		pub.setRedSocial("Facebook");
		pub.setImagenes(null);
		pub.setVideos(null);
		pub.setFechaCreacion(fechaPr);
		pub.setFechaModificacion(fechaPr);
		pub.setFechaProgramada(fechaPr);
		publicacionRepository.save(pub);
	}

	@Test
	void testCPublicacionStringStringStringArrayListOfFileArrayListOfFileGregorianCalendarGregorianCalendar() {
		fail("Not yet implemented");
	}

	@Test
	void testCPublicacionStringStringStringArrayListOfFileGregorianCalendarGregorianCalendar() {
		fail("Not yet implemented");
	}

	@Test
	void testCPublicacionArrayListOfFileStringStringStringGregorianCalendarGregorianCalendar() {
		fail("Not yet implemented");
	}

	@Test
	void testRecuperaPublicaciones() {
		fail("Not yet implemented");
	}

}
