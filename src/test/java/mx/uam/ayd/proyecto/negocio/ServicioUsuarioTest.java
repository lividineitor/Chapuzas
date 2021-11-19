package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.String;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//import mx.uam.ayd.proyecto.datos.GrupoRepository;
import mx.uam.ayd.proyecto.datos.UsuarioRepository;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

@ExtendWith(MockitoExtension.class)
class ServicioUsuarioTest {
	
	// Al usar la anotación @Mock, el framework Mockito crea un sustituto
	// de la clase que regresa valores por default
	@Mock
	private UsuarioRepository usuarioRepository;
	
	//@Mock
	//private GrupoRepository grupoRepository;
	
	// Esta anotación hace que se inyecten todos los Mocks al módulo que quiero
	// probar para que no haya nullPointerException por que las dependencias
	// no están satisfechas en tiempo de pruebas
	@InjectMocks
	private ServicioUsuario servicio;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testAgregaUsuario() {
		//Prueba1: Agregar a un usuario no nulo
		ArrayList<Usuario> users = new ArrayList<>();
		Usuario usuario = new Usuario();
		Usuario usuario2 = new Usuario();
		usuario.setNombre("Link");
		usuario.setApellido("Doma");
		usuario.setEmail("wwwow@gmail.com");
		usuario.setTelefono("553635262");
		usuario.setContraseña("JujojkajAju");
		usuario.setPermisos("admin");
		when(usuarioRepository.save(usuario)).thenReturn(usuario); //Esto produce el error en el test.
		usuario2 = servicio.agregaUsuario("Link", "Doma", "wwwow@gmail.com", "553635262", "JujojkajAju", "admin");
		users.add(usuario2);
		assertFalse(users.isEmpty());
		
		//Prueba2: Intento agregar a un usuario nulo
		//los metodos del Repository se encarga de verificar el objeto a ingresar es de tipo Usuario
		//Prueba3: El usuario a registrar ya se encuentra registrado (su email), esto se verifica con el metodo VerificaExistencia()
	}

	@Test
	void testRecuperaUsuarios() {

		
		// Prueba 1: corroborar que regresa una lista vacía si no hay usuarios en la BD
		
		// en este momento, la invocación a usuarioRepository.findAll() regresa una lista vacía
		List <Usuario> usuarios = servicio.recuperaUsuarios();
		
		assertTrue(usuarios.isEmpty());

		// Prueba 2: corroborar que regresa una lista con usuarios
		LinkedList <Usuario> lista = new LinkedList <> ();

		// Tengo que crear un Iterable <Usuario> para que el método 
		// usuarioRepository.findAll() no me regrese una lista vacía
		// cuando lo invoco
		Usuario usuario1 = new Usuario();
		usuario1.setNombre("Juan");
		usuario1.setApellido("Perez");

		Usuario usuario2 = new Usuario();
		usuario2.setNombre("María");
		usuario2.setApellido("Ramírez");
		
		lista.add(usuario1);
		lista.add(usuario2);
		
		// Al usar when, lo que hacemos es que definimos un comportamiento
		// para la invoación del método.
		// A partir de este punto, la invocación a usuarioRepository.findAll() ya
		// no me regresa una lista vacía, si no que me regresa una listaLigada
		// vista como Iterable que tiene dos elementos
		when(usuarioRepository.findAll()).thenReturn(lista);
		
		usuarios = servicio.recuperaUsuarios();
		
		assertEquals(2,usuarios.size()); // Corroboro que tenga dos elementos
		
	}
	
	@Test
	void testVerificaFormato() //caso en que utilizo una contraseña que no cumple los requerimientos.
	{
		//Pruebs 1: Utilizando una contraseña que no es adecuada por la RN
		Usuario usuariov = new Usuario();
		Usuario usuariov2 = new Usuario();
		usuariov.setContraseña("pass");
		int longitud;
		boolean bandera;
		String verificar;
		verificar = usuariov.getContraseña();
		longitud = verificar.length();
		if (longitud > 8)
			bandera = true;
		else
			bandera = false;
		
		assertEquals(false,bandera);
		
		//Prueba 2: Ahora si utilizo la contraseña correcta
		usuariov2.setContraseña("holaholahola");
		boolean bandera2;
		String verificar2;
		verificar2 = usuariov2.getContraseña();
		longitud = verificar2.length();
		if (longitud > 8)
			bandera2 = true;
		else
			bandera2 = false;
		
		assertEquals(true,bandera2);
		
		//Prueba 3: Utilizo un null
		Usuario usuariov3 = new Usuario();
		usuariov3.setContraseña(null);
		try {
			servicio.VerificaFormato(usuariov3);
		} catch (Exception e) {assertEquals("objeto nulo",e.getMessage());}
		
	}
	
	@Test
	void testVerificaExistencia()
	{
		String email = "ayayay@gmail.com";
		//Prueba1 : Verificar que regresa false si el usuario no estaba registrado
		boolean bandera = servicio.VerificaExistencia(email);
		assertEquals(true,bandera);
		
		//Prueba 2: Cuando ya esta alguien registrado
		String email2 = "uyuyuy@gmail.com";
		Usuario user = new Usuario();
		user.setEmail("uyuyuy@gmail.com");
		when(usuarioRepository.findByEmail(email2)).thenReturn(user);
		boolean bandera2 = servicio.VerificaExistencia(email2);
		assertEquals(false,bandera2);
		
		//Prueba 3 objeto nulo.
		try {
			servicio.VerificaExistencia(null);
		} catch (Exception e) {assertEquals("mensaje",e.getMessage());}
		
		/*Usuario user2 = null;
		boolean aver = servicio.VerificaExistencia(null);
		assertEquals(null,aver);*/
		
	}
	
	@Test
	void testRecuperaporDia()
	{
		//Prueba 1: Verificar que no regresa ningun usuario si no esta registrado previamente en la BD
		ArrayList<Usuario> usuarios = new ArrayList<>();
		Date fecha1 = new Date();
		Date fecha2 = new Date(121,9,15);
		//LocalDate fecha1 = LocalDate.of(2010, 05, 20);
		//LocalDate fecha2 = LocalDate.of(2021, 10, 15);
		usuarios = servicio.RecuperaporDia(fecha2);
		assertTrue(usuarios.isEmpty());
		
		//Prueba2: Verificar que regrese la lista si si hay usuarios registrados en esa fecha 
		ArrayList<Usuario> usuarios2 = new ArrayList<>();
		ArrayList<Usuario> lista = new ArrayList<>();
		Date fechap1 = new Date(120,11,28);
		Date fechap2 = new Date(120,11,28);
		//LocalDate fechap1 = LocalDate.of(2015, 4, 8);
		//LocalDate fechap2 = LocalDate.of(2020, 12, 28);
		Usuario prueba1 = new Usuario();
		Usuario prueba2 = new Usuario();
		Date fecha3 = new Date(120,11,28);
		//Date fecha4 = new Date(119,4,18);
		//LocalDate fecha3 = LocalDate.of(2018, 4, 8);
		//LocalDate fecha4 = LocalDate.of(2019, 5, 18);
		prueba1.setNombre("Rick");
		prueba1.setApellido("Cuack");
		prueba1.setEmail("iyiyiyiyi");
		prueba1.setTelefono("6473");
		prueba1.setPermisos("1");
		prueba1.setContraseña("micontraseña");
		prueba1.setFechaderegistro(fecha3);
		usuarios2.add(prueba1);
		prueba2.setNombre("Esme");
		prueba2.setApellido("Cueck");
		prueba2.setEmail("oyoyoyyyy");
		prueba2.setTelefono("6473");
		prueba2.setPermisos("1");
		prueba2.setContraseña("micontraseña");
		prueba2.setFechaderegistro(fecha3);
		usuarios2.add(prueba2);
		when(usuarioRepository.findByDiaAndMesAndAnnio(fecha3.getDate(),fecha3.getMonth(),fecha3.getYear())).thenReturn(usuarios2);
		lista = servicio.RecuperaporDia(fecha3);
		//when(usuarioRepository.findAllByEntityNotNullAndFechaderegistroBetween(fechap1,fechap2)).thenReturn(usuarios2);
		//lista = servicio.RecuperaporFecha(fechap1, fechap2);
		assertEquals(2,lista.size());	
		
	}
	
@Test
	void testRecuperaporMes()
	{
		//Prueba 1 cuando no hay usuarios registrados en la BD
		ArrayList<Usuario> usuarios = new ArrayList<>();
		ArrayList<Usuario> listas = new ArrayList<>();
		Date fecha1 = new Date();
		when(usuarioRepository.findByMesAndAnnio(fecha1.getMonth(), fecha1.getYear())).thenReturn(usuarios);
		assertTrue(usuarios.isEmpty());
	
		//Prueba 2 cuando hay un usuario registrado en la BD en el mes seleccionado
		ArrayList<Usuario> user = new ArrayList<>();
		ArrayList<Usuario> lista = new ArrayList<>();
		Date fecha = new Date();
		Usuario usuario = new Usuario();
		usuario.setFechaderegistro(fecha);
		user.add(usuario);
		when(usuarioRepository.findByMesAndAnnio(fecha.getMonth(), fecha.getYear())).thenReturn(user);
		lista = servicio.RecuperaporMes(fecha);
		assertEquals(1,lista.size());
		
	}

@Test
	void testRecuperaporAnnio()
	{
	//Prueba 1 cuando no hay usuarios registrados en la BD
	ArrayList<Usuario> usuarios = new ArrayList<>();
	ArrayList<Usuario> listas = new ArrayList<>();
	Date fecha1 = new Date();
	when(usuarioRepository.findByAnnio(fecha1.getYear())).thenReturn(usuarios);
	listas = servicio.RecuperaporAnnio(fecha1);
	assertTrue(usuarios.isEmpty());

	//Prueba 2 cuando hay un usuario registrado en la BD en el mes seleccionado
	ArrayList<Usuario> user = new ArrayList<>();
	ArrayList<Usuario> lista = new ArrayList<>();
	Date fecha = new Date();
	Date fecha2 = new Date();
	Usuario usuario = new Usuario();
	Usuario usuario2 = new Usuario();
	usuario.setFechaderegistro(fecha);
	usuario2.setFechaderegistro(fecha2);
	user.add(usuario);
	user.add(usuario2);
	when(usuarioRepository.findByMesAndAnnio(fecha.getMonth(), fecha.getYear())).thenReturn(user);
	lista = servicio.RecuperaporMes(fecha);
	assertEquals(2,lista.size());
	
	}

}
