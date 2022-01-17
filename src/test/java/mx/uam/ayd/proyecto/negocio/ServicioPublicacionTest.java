package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.PublicacionRepository;
import mx.uam.ayd.proyecto.datos.UsuarioRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Publicacion;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

@ExtendWith(MockitoExtension.class)
class ServicioPublicacionTest {
	@Mock
	private PublicacionRepository publicacionRepository;
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private ServicioUsuario servicio;
	
	@InjectMocks
	private ServicioPublicacion servicioPub;
	
	
	Publicacion pub;
	String fechaPr;
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	@Disabled
	@Test
	void testBuscaPublicaciones() {
		Usuario usuario = new Usuario();
		usuario.setIdUsuario(1);
		usuario.setNombre("Link");
		usuario.setApellido("Doma");
		usuario.setEmail("wwwow@gmail.com");
		usuario.setTelefono("553635262");
		usuario.setContraseña("JujojkajAju");
		usuario.setPermisos("admin");
		when(usuarioRepository.save(usuario)).thenReturn(usuario);
		
		//caso 1: El usuario actual no ha creado ninguna publicacion
		ArrayList<Publicacion> publicaciones_en_local = new ArrayList<>();
		publicaciones_en_local = servicioPub.BuscaPublicacionesUsuario(usuario.getIdUsuario());
		System.out.println(publicaciones_en_local);
		assertTrue(publicaciones_en_local.isEmpty());
		//caso 2: El usuario actual ya ha creado varias publicaciones
		Publicacion miPublicacion = new Publicacion();
		miPublicacion.setIdUsuario(usuario.getIdUsuario());
		miPublicacion.setFechaCreacion(LocalDate.now());
		miPublicacion.setFechaModificacion(LocalDate.now());
		miPublicacion.setFechaProgramada(LocalDate.now());
		miPublicacion.setTitulo("Mi titulo de esta publicacion");
		miPublicacion.setContenido("mi contenido ");
		miPublicacion.setNombreRedSocial("Facebook");
		miPublicacion.setImagenes(null);
		miPublicacion.setVideos(null);
		when(publicacionRepository.save(miPublicacion)).thenReturn(miPublicacion);
		when(publicaciones_en_local).thenReturn(publicaciones_en_local);
		assertTrue(publicaciones_en_local.isEmpty());
	}
	
	@Test
	void testCreaPublicacionFacebook() {
		
		
		//caso1: la fecha de publicacion es la actual
		Publicacion miPublicacion = new Publicacion();
		miPublicacion.setIdUsuario(10);
		miPublicacion.setFechaCreacion(LocalDate.now());
		miPublicacion.setFechaModificacion(LocalDate.now());
		miPublicacion.setFechaProgramada(LocalDate.parse("2021-12-24"));
		miPublicacion.setTitulo("Mi titulo de esta publicacion");
		miPublicacion.setContenido("mi contenido ");
		miPublicacion.setNombreRedSocial("Facebook");
		miPublicacion.setImagenes(null);
		miPublicacion.setVideos(null);
		when(publicacionRepository.save(miPublicacion)).thenReturn(miPublicacion);
		
		assertFalse(miPublicacion.getFechaProgramada().isEqual(LocalDate.now()));
		//caso2: la fecha de publicacion en anterior a la actual
		assertFalse(miPublicacion.getFechaProgramada().isBefore(LocalDate.now()));
		//caso3: la fecha de publicacion es posterior a la actual
		assertTrue(miPublicacion.getFechaProgramada().isAfter(LocalDate.now()));
	}

}
