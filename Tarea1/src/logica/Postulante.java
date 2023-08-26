package logica;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Postulante extends Usuario {
	
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private LinkedList<Postulacion> postulaciones;

	public Postulante(String nickname, String nombre, String apellido, String correo, LocalDate fechaNacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, correo);
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		postulaciones = new LinkedList<>();
	}
	
	public LocalDate getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}
	
	public LinkedList<Postulacion> getPostulaciones(){
		return this.postulaciones;
	}
	
	public void setFechaNacimiento(LocalDate fecha) {
		this.fechaNacimiento = fecha;
	}
	
	public void setNacionalidad(String nac) {
		this.nacionalidad = nac;
	}
	
	public DTPostulante getDataPostulante() {
		Map<String, DTOfertaLaboral> ofertasLab = new HashMap<>();
		LinkedList<Postulacion> post = this.getPostulaciones();
		if(post.size() != 0) {
			for(Postulacion p : post) {
				ofertasLab.put(p.getOfertaLaboral().getNombre(), p.getOfertaLaboral().getDataOfertaLaboral());
			}
		}
		DTPostulante dtP = new DTPostulante(this.getNickname(),this.getNombre(),this.getApellido(),this.getCorreo(),ofertasLab,this.getFechaNacimiento(),this.getNacionalidad());
		return dtP;

	}
	
	public DTUsuario getDataUsuario() {
		return this.getDataPostulante();

	}
	
	public void modificarDatos(String nombre, String apellido, LocalDate fechaNac, String nacionalidad) {
		//Usuario u = (Usuario) this;
		this.setNombre(nombre);
		this.setApellido(apellido);
		//u.setNombre(nombre);
		//u.setApellido(apellido);
		this.setFechaNacimiento(fechaNac);
		this.setNacionalidad(nacionalidad);
	}
}
