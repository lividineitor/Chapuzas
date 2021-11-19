package mx.uam.ayd.proyecto.presentacion.MostrarClientes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import mx.uam.ayd.proyecto.negocio.ServicioGrupo;
import mx.uam.ayd.proyecto.negocio.ServicioUsuario;
import mx.uam.ayd.proyecto.negocio.modelo.Usuario;
import mx.uam.ayd.proyecto.presentacion.crearCuenta.VentanaFormularioCrearCuenta;

/*
 * Modulo de control para la historia Mostrar Clientes
 */

@Component
public class ControlMostrarClientes {
	
	@Autowired
	private ServicioUsuario servicioUsuario;
	
	//@Autowired
	//private ServicioGrupo servicioGrupo;
	
	@Autowired
	private VentanaMostrarClientes ventana;
	
	@Autowired
	private VentanaMostrarClientes2 ventana2;
	
public void inicia() {
		
		//List <Grupo> grupos = servicioGrupo.recuperaGrupos();
		
		ventana.muestra(this);
		
	}
	
	public ArrayList<Usuario> BuscaClientesDia(Date dia)
	{
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios = servicioUsuario.RecuperaporDia(dia);
		ventana2.muestra(usuarios,this);
		return usuarios;
		
		
	}
	
	public ArrayList<Usuario> BuscaClientesMes(Date mes)
	{
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios = servicioUsuario.RecuperaporMes(mes);
		ventana2.muestra(usuarios,this);
		return usuarios;
		
	}
	
	public ArrayList<Usuario> BuscaClientesAnnio(Date annio)
	{
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios = servicioUsuario.RecuperaporAnnio(annio);
		ventana2.muestra(usuarios,this);
		return usuarios;
		
	}
	
	public ArrayList<Usuario> recuperaUsuarios()
	{
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios = servicioUsuario.recuperaUsuarios();
		ventana2.muestra(usuarios, this);
		return usuarios;
		
	}
	
	public Usuario BuscarporEmail(String email)
	{

		Usuario usuario;
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuario = servicioUsuario.RecuperaporEmail(email);
		usuarios.add(usuario);
		ventana2.muestra(usuarios, this);
		return usuario;
	}
	
	public void termina() {
		ventana.setVisible(false);		
	}
	public void termina2() {
		ventana2.setVisible(false);		
	}

}
