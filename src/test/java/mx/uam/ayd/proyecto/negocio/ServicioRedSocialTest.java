package mx.uam.ayd.proyecto.negocio;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import mx.uam.ayd.proyecto.datos.RedSocialRepository;
import mx.uam.ayd.proyecto.negocio.modelo.RedSocial;

@ExtendWith(MockitoExtension.class)
class ServicioRedSocialTest {
	
	@Mock
	private RedSocialRepository redSocialRepository;
	
	@InjectMocks
	private ServicioRedSocial servicioRed;
	
	HashMap<String,Long> fImagen;
	HashMap<String,Long> fVideo;
	ArrayList<File> imagenes,videos;
	RedSocial facebook;
	
	@BeforeEach
	void setUp() throws Exception {
		fImagen= new HashMap<String,Long>();
		fVideo= new HashMap<String,Long>();
		imagenes= new ArrayList<>();
		videos= new ArrayList<>();

		//fImagen.put("jpg", (long) 25);
		fImagen.put("png", (long) 10);
		fImagen.put("jpeg", (long) 15);
		
		fVideo.put("mp4", (long) 205);
		fVideo.put("mwma", (long) 100);
		//fVideo.put("swf", (long) 100);
	}
	
	@Disabled
	@Test
	void testValidaRed() {
		//caso1: verificar que si nombreRed es nulo se lanza la excepcion
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			servicioRed.validaRed(null);
		});
		//caso2: verificar que si nombreRed no es nulo pero en el repositorio no aparece un objeto con dicho nombre se regrese un false
		boolean resultado=servicioRed.validaRed("Instagram");
		assertFalse(resultado);
		//caso3:verificar que si nombreRed no es nulo pero en el repositorio si aparece un objeto con dicho nombre se regrese un true
		facebook = new RedSocial();
		facebook.setNombreRed("Facebook");
		facebook.setUsuario("leonardo");
		facebook.setContrasena("FJR547TU4U");
		facebook.setFormatoPesoImagen(fImagen);
		facebook.setFormatoPesoVideo(fVideo);
		redSocialRepository.save(facebook);

		when(redSocialRepository.findByNombreRed("Facebook")).thenReturn(facebook);
		resultado=servicioRed.validaRed("Facebook");
		assertEquals(true,resultado);
	} 
	@Disabled
	@Test
	void testConectarRedSocial() {
		//caso1: verificar que si usuario y password son null se lance la excepcion
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			servicioRed.conectarRedSocial(null, null);
		});
		//caso2: verifica que si usuario o password es null se lanza la excepcion
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			servicioRed.conectarRedSocial("Leonardo", null);
		});
		//caso 3. verificar que si dentro de la conexion a la api no hay un error se retorne y que se regrese true
		boolean salida= servicioRed.conectarRedSocial("Jorge", "12345");
		assertEquals(true,salida);
	}
	@Disabled
	@Test
	void testVFimagenYVideo() {
		facebook = new RedSocial();
		facebook.setNombreRed("Facebook");
		facebook.setUsuario("leonardo");
		facebook.setContrasena("FJR547TU4U");
		facebook.setFormatoPesoImagen(fImagen);
		facebook.setFormatoPesoVideo(fVideo);
		redSocialRepository.save(facebook);
		
		imagenes.add(new File("C:\\Users\\leonh\\Downloads\\1.png"));
		imagenes.add(new File("C:\\Users\\leonh\\Downloads\\2.png"));
		
		videos.add(new File("C:\\Users\\leonh\\Downloads\\02.mp4"));
		videos.add(new File("C:\\Users\\leonh\\Videos\\Screenbits\\ALU.mp4"));
		
		//caso1: se reciben referencias nulas para el nombre de la red , las direcciones de imagenes y videos
				
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			servicioRed.vFimagenYVideo(null, null, null);
		});
		//caso2: se reciben por lo menos una referecnia nula en el nombre de la red , las direcciones de imagenes o videos
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			servicioRed.vFimagenYVideo(null, imagenes, null);
		});
		//caso3: si tenemos una red social ya lista con un nombre y subimos las imagenes y/o videos
		when(redSocialRepository.findByFormatoPesoVideo(facebook.getNombreRed())).thenReturn(fVideo);
		when(redSocialRepository.findByFormatoPesoImagen(facebook.getNombreRed())).thenReturn(fImagen);
		
		boolean resultado=servicioRed.vFimagenYVideo(facebook.getNombreRed(), imagenes, videos);
		assertEquals(true,resultado);
	}
	
	@Test
	void testVFimagen() {
		
		/**
		 * para el error del test solo se debe de comentar lo comentado dentro del metodo vFimagen de servicio usuario y comentar RedSocial us y enviar formatoPesoImg (Lo mismo con vFvideo)
		 * 
		 * /*formatoPesoImg = redSocialRepository.findByFormatoPesoImagen(nombreRed);
			if(formatoPesoImg.isEmpty())
			throw new NullPointerException("No has iniciado sesion o no se han cargado los formatos y pesos establecidos por la red social");
			System.out.println("Red social en repositorio="+formatoPesoImg);
			RedSocial us =redSocialRepository.findByNombreRed(nombreRed);
			boolean resultado=checaFormatoPesoMultimedia(imagenes,us.getFormatoPesoImagen());
		 */
		
		/*caso 1: Dada una lista de referencias de imagenes existentes 
		 * todas y el nombre de la red social se verifica que todos c
		 * umplan los formatos y pesos soportados, por lo que se regresa un true
		 */
		facebook = new RedSocial();
		facebook.setNombreRed("Facebook");
		facebook.setUsuario("leonardo");
		facebook.setContrasena("FJR547TU4U");
		facebook.setFormatoPesoImagen(fImagen);
		facebook.setFormatoPesoVideo(fVideo);
		redSocialRepository.save(facebook);
		
		imagenes.add(new File("C:\\Users\\leonh\\Downloads\\1.png"));
		imagenes.add(new File("C:\\Users\\leonh\\Downloads\\2.png"));
		imagenes.add(new File("C:\\Users\\leonh\\Downloads\\3.png"));
		
		when(redSocialRepository.findByFormatoPesoImagen(facebook.getNombreRed())).thenReturn(fImagen);
		boolean resultado=servicioRed.vFimagen(facebook.getNombreRed(), imagenes);
		assertTrue(resultado,"Debio regresar true");
		
		/*
		  Caso2: SI dada una lista de archivos imagen todos existentes, si por lo menos uno 
		 * posee un formato de archivo no permitido por la red social
		  entonces si se hace la verificacion de los formatos y pesos soportados por la red social elegida
		  pero retornara false por que por lo menos un archivo no es permitido por su formato
		 */
		
		imagenes.add(new File("C:\\Users\\leonh\\Videos\\Screenbits\\ff.jpg"));
		
		resultado=servicioRed.vFimagen(facebook.getNombreRed(), imagenes);
		assertFalse(resultado, "Debio regresar false");
		
		/*
		  Caso3: SI dada una lista de direcciones de archivos imagen por lo menos uno no existe
		  entonces no hace ni la verificacion de los formatos y pesos soportados por la red social elegida
		  retorna false antes de esta verificacion pues no tiene caso proseguir si un archivo no existe
		 */
		
		imagenes.add(new File("C:\\Users\\leonh\\Downloads\\imagenNoExistente.png"));
		
		resultado=servicioRed.vFimagen(facebook.getNombreRed(), imagenes);
		assertFalse(resultado, "Debio regresar false");
		
		
	}
	@Disabled
	@Test
	void testVFvideo() {
		
		/*caso 1: Dada una lista de referencias de videos existentes 
		 * todas y el nombre de la red social se verifica que todos c
		 * umplan los formatos y pesos soportados, por lo que se regresa un true
		 */
		facebook = new RedSocial();
		facebook.setNombreRed("Facebook");
		facebook.setUsuario("leonardo");
		facebook.setContrasena("FJR547TU4U");
		facebook.setFormatoPesoImagen(fImagen);
		facebook.setFormatoPesoVideo(fVideo);
		redSocialRepository.save(facebook);
		
		videos.add(new File("C:\\Users\\leonh\\Downloads\\02.mp4"));
		videos.add(new File("C:\\Users\\leonh\\Videos\\Screenbits\\ALU.mp4"));
		videos.add(new File("C:\\Users\\leonh\\Videos\\Screenbits\\clase1.mp4"));
		
		when(redSocialRepository.findByFormatoPesoVideo(facebook.getNombreRed())).thenReturn(fVideo);
		boolean resultado=servicioRed.vFvideo(facebook.getNombreRed(), videos);
		assertTrue(resultado,"Debio regresar true");
		
		/*
		 * Caso2:  SI dada una lista de direcciones de archivos video todos existentes, si por lo menos uno 
		 * posee un formato de archivo no permitido por la red social
		  entonces si se hace la verificacion de los formatos y pesos soportados por la red social elegida
		  pero retornara false por que por lo menos un archivo no es permitido por su formato
		 */
		videos.add(new File("C:\\Users\\leonh\\Videos\\Screenbits\\video.swf"));
		resultado=servicioRed.vFvideo(facebook.getNombreRed(), videos);
		assertFalse(resultado,"Debio regresar true");
		
		/*
		  Caso3: SI dada una lista de direcciones de archivos video por lo menos uno no existe
		  entonces no hace ni la verificacion de los formatos y pesos soportados por la red social elegida
		  retorna false antes de esta verificacion pues no tiene caso proseguir si un archivo no existe
		 */
		
		videos.add(new File("C:\\Users\\leonh\\Videos\\Screenbits\\cls1.mp4"));
		
		resultado=servicioRed.vFvideo(facebook.getNombreRed(), videos);
		assertFalse(resultado, "Debio regresar false");
		
		
		
	}
}
