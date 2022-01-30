package mx.uam.ayd.proyecto.negocio;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
//import mx.uam.ayd.proyecto.datos.GrupoRepository;
import mx.uam.ayd.proyecto.datos.UsuarioRepository;
//import mx.uam.ayd.proyecto.negocio.modelo.Grupo;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;

@Slf4j
@Service
public class ServicioUsuario {
	
	@Autowired 
	private UsuarioRepository usuarioRepository;
	
	/**
	 * 
	 * Permite agregar un usuario
	 * 
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param telefono
	 * @param contraseña
	 * @param permisos
	 * @return
	 */
		
	public Usuario agregaUsuario(String nombre, String apellido, String email, String telefono, String contraseña, String permisos) {
			
		boolean existe;
		existe = VerificaExistencia(email);//Verifica regla de negocio: dos usuarios no pueden tener el mismo email.
		if(existe == false)
			{
				throw new IllegalArgumentException("Ese usuario ya existe");
			}
	
		log.info("Agregando usuario nombre: "+nombre+" apellido:"+apellido);
		
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setEmail(email);
		usuario.setTelefono(telefono);
		usuario.setContraseña(contraseña);
		usuario.setPermisos(permisos);
		Date registro = new Date();
		usuario.setFechaderegistro(registro);
		usuario.setDia(registro.getDate());
		usuario.setMes(registro.getMonth()+1);
		usuario.setAnnio(registro.getYear()+1900);
		usuarioRepository.save(usuario);
		
		return usuario;
		

	}
	public boolean creaCuenta(String nombre, String apellido, String email, String telefono, String contraseña, String Permisos) {
		Usuario usuario = new Usuario();
		usuario.setNombre(nombre);
		usuario.setApellido(apellido);
		usuario.setEmail(email);
		usuario.setTelefono(telefono);
		usuario.setContraseña(contraseña);
		usuario.setPermisos(Permisos);
		usuario.setFechaderegistro(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
		usuarioRepository.save(usuario);
		return true;
	}

	public boolean ModificarCuenta ( Usuario usuario ) {
		
		Usuario usuarioModificado ;
		
		usuarioModificado = usuarioRepository.save(usuario) ;
		
		if (usuario.equals(usuarioModificado))
			return true ;
		
		else
			return false ;
	}
	
	public boolean ModificaCuenta(String Nombre, String Apellido,String email, String Telefono,String Contraseña)//Actualiza informacion de la cuenta
	{
		//usuario.setNombre(usuario.getNombre());
		Usuario usuario = usuarioRepository.findByEmail(email);
		usuario.setNombre(Nombre);
		usuario.setApellido(Apellido);
		usuario.setTelefono(Telefono);
		usuario.setContraseña(Contraseña);
		usuarioRepository.save(usuario);
		return true;
	}
	
	public ArrayList<Usuario> RecuperaporDia(Date start) //Recupera usuarios por periodo
	{
		ArrayList <Usuario> usuarios = new ArrayList<>();
		
		for(Usuario usuario:usuarioRepository.findByDiaAndMesAndAnnio(start.getDate(),start.getMonth(),start.getYear())) {
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	public ArrayList<Usuario> RecuperaporMes(Date start) //Recupera usuarios por periodo
	{
		ArrayList <Usuario> usuarios = new ArrayList<>();
		
		for(Usuario usuario:usuarioRepository.findByMesAndAnnio(start.getMonth(),start.getYear())) {
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	public ArrayList<Usuario> RecuperaporAnnio(Date start) //Recupera usuarios por periodo
	{
		ArrayList <Usuario> usuarios = new ArrayList<>();
		
		for(Usuario usuario:usuarioRepository.findByAnnio(start.getYear())) {
			usuarios.add(usuario);
		}
		return usuarios;
	}
	
	public Usuario RecuperaporEmail(String email)
	{
		if(email == null)
		{
			throw new IllegalArgumentException("objeto nulo");
		}
		Usuario usuario;
		usuario = usuarioRepository.findByEmail(email);
		return usuario;
	}
	
	public boolean VerificaFormato(Usuario usuario)
	{
		//Aqui vere si cumple con las reglas de negocio (al menos una contraseña de 8 caracteres)
		if(usuario.getContraseña() == null)
		{
			throw new IllegalArgumentException("objeto nulo");
		}
		int longitud;
		String verificar;
		verificar = usuario.getContraseña();
		longitud = verificar.length();
		if (longitud > 8)
			return true;
		else
			return false;
	}
	
	public boolean VerificaExistencia (String email) //Saber si el usuario no fue previamente registrado
	{
		if(email == null)
		{
			throw new IllegalArgumentException("mensaje");
		}
		Usuario usuario = usuarioRepository.findByEmail(email);
		if(usuario != null) {
			return false; //Este usuario ya existe
		}
		else 
			return true;//no existe, puede crear una cuenta nueva
		
	}


	/**
	 * Recupera todos los usuarios existentes
	 * 
	 * @return Una lista con los usuarios (o lista vacía)
	 */
	public ArrayList <Usuario> recuperaUsuarios() {

		
		System.out.println("usuarioRepository = "+usuarioRepository);
		
		ArrayList <Usuario> usuarios = new ArrayList<>();
		
		for(Usuario usuario:usuarioRepository.findAll()) {
			usuarios.add(usuario);
		}
				
		return usuarios;
	}
	
	public ArrayList <Usuario> recuperaPorRol (String rol) {
		ArrayList <Usuario> clientes = new ArrayList <>();
		
		for ( Usuario usuario : usuarioRepository.findByPermisos(rol))
			clientes.add(usuario);
		
		return clientes ;
	}

	
	public boolean eliminarUsuario (Usuario usuario ) {
		usuarioRepository.delete(usuario);
		
		return !usuarioRepository.existsById(usuario.getIdUsuario()) ;

	}
	
	public long regresaIdUsuario(String nombre) {
		
		Usuario usuario = usuarioRepository.findByNombre(nombre);
		return usuario.getIdUsuario();
	}

}
