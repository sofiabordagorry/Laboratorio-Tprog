package logica;

public class Usuario {
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	
	public Usuario(String nickname, String nombre, String apellido, String correo) {
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
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
}
