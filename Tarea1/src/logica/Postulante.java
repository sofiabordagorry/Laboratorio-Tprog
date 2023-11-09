package logica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Postulante extends Usuario {
	
	private LocalDate fechaNacimiento;
	private String nacionalidad;
	private List<Postulacion> postulaciones;
	private List<OfertaLaboral> ofertasFavoritas = new LinkedList<>();//nuevo atributo tarea3

	public Postulante(String nickname, String nombre, String apellido, String correo, LocalDate fechaNacimiento, String nacionalidad) {
		super(nickname, nombre, apellido, correo);
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.postulaciones = new LinkedList<>();

	}
	
	public Postulante(String nickname, String nombre, String apellido, String correo, LocalDate fechaNacimiento, String nacionalidad, String contrasenia) {
		super(nickname, nombre, apellido, correo, contrasenia);
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.postulaciones = new LinkedList<>();

	}
	
	public Postulante(String nickname, String nombre, String apellido, String correo, LocalDate fechaNacimiento, String nacionalidad, String contrasenia, byte[] image) {
		super(nickname, nombre, apellido, correo, contrasenia, image);
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.postulaciones = new LinkedList<>();
	}
	
	public LocalDate getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	
	public String getNacionalidad() {
		return this.nacionalidad;
	}
	

	public Boolean existePostulacion(String oferta) {
		List<Postulacion> postulaciones = new LinkedList<Postulacion>();
		postulaciones = this.postulaciones;
		Boolean existe=false;
        for (int i = 0; i < postulaciones.size(); i++) {
        	if (postulaciones.get(i).verificarOfertaLaboral(oferta)) {
        		existe = true;
        	}
        }
        return existe;
	}
	
	public void agregarPostulacion(Postulacion postulacion) {
		this.postulaciones.add(postulacion);
	}

	public List<Postulacion> getPostulaciones(){
		return this.postulaciones;
	}
	
	public void setFechaNacimiento(LocalDate fecha) {
		this.fechaNacimiento = fecha;
	}
	
	public void setNacionalidad(String nac) {
		this.nacionalidad = nac;
	}
	
	public DTPostulante getDataPostulante() {
		DTOfertaLaboral[] dtsOL;
		List<DTPostulacion> postulaciones = new LinkedList<>();
		List<Postulacion> post = new LinkedList<>();
		post = this.getPostulaciones();
		if (post != null) {
			dtsOL = new DTOfertaLaboral[post.size()];
			int i = 0;
			for (Postulacion p : post) {
				postulaciones.add(p.getDataPostulacion());
				dtsOL[i] = p.getOfertaLaboral().getDataOfertaLaboral();
				i++;
			}
		} else
			dtsOL = new DTOfertaLaboral[0];
		LocalDate fechaNacimiento = this.getFechaNacimiento();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaNacimientoString = fechaNacimiento.format(formatter);
		DTPostulante dtP = new DTPostulante(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(), this.getContrasenia(), fechaNacimientoString, this.getNacionalidad(), dtsOL, postulaciones, this.getImage());
		return dtP;
	}
	
	public DTUsuario getDataUsuario() {
		return this.getDataPostulante();

	}
	
	public void modificarDatos(String nombre, String apellido, LocalDate fechaNac, String nacionalidad) {
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setFechaNacimiento(fechaNac);
		this.setNacionalidad(nacionalidad);
	}
	
	public boolean esFavorito(OfertaLaboral oferta) {
		return this.ofertasFavoritas.contains(oferta);
	}
	
	public void agregarOfertaFav(OfertaLaboral oferta) {
		this.ofertasFavoritas.add(oferta);
	}
	
	public void quitarOfertaFav(OfertaLaboral oferta) {
		this.ofertasFavoritas.remove(oferta);
	}
}
