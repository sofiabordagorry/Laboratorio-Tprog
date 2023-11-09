package logica;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	private String contrasenia;
	private byte[] image;
	private List<Usuario> usuariosSeguidos = new ArrayList<>();//nuevo atributo tarea3
	private List<Usuario> seguidores = new ArrayList<>();//nuevo atributo tarea3
	
	public Usuario(String nickname, String nombre, String apellido, String correo) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
	}
	
	public Usuario(String nickname, String nombre, String apellido, String correo, String contrasenia) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasenia = contrasenia;
	}
	
	public Usuario(String nickname, String nombre, String apellido, String correo, String contrasenia, byte[] image) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.contrasenia = contrasenia;
		this.image = image;
	}
	
	public String getNickname() {
		return this.nickname;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getApellido() {
		return this.apellido;
	}
	
	public String getCorreo() {
		return this.correo;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	public DTUsuario getDataUsuario() {
		return null;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
	
	// --- seguir ---
	
	public boolean sigue(String nickname) {
		boolean sigue = false;
		if(this.usuariosSeguidos != null) {
			for (int i = 0; i < this.usuariosSeguidos.size(); i++) {
	            if ( this.usuariosSeguidos.get(i).getNickname().equals(nickname)) {
	            	sigue = true;
	            }
	            	
	        }
		}
		return sigue;
	}

	public void seguirUsuario(Usuario usr) {
		this.usuariosSeguidos.add(usr);
		usr.agregarSeguidor(this);
	}
	
	public void agregarSeguidor(Usuario usr) {
		this.seguidores.add(usr);
		
	}
	
	public void dejarSeguir(Usuario usr) {
		this.usuariosSeguidos.remove(usr);
		usr.borrarSeguidor(this);
	}
	
	public void borrarSeguidor(Usuario usr) {
		this.seguidores.remove(usr);
		
	}
	
	public List<Usuario> getSeguidores() {
		return this.seguidores;
	}
	
	public List<Usuario> getUsuariosSeguidos(){
		return this.usuariosSeguidos;
	}
}
