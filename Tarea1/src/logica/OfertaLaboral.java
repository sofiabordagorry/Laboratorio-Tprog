package logica;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class OfertaLaboral {
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horario;
	private float remuneracion;
	private LocalDate fechaDeAlta;
	private float costoAsociado;
	private Tipo tipoOL;
	private List<Postulacion> postulaciones;
	private Map<String, Keyword> keywords;
	
	
	public OfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario,
								float remuneracion, LocalDate fechaDeAlta, float costoAsociado, Tipo tipoOL, Map<String, Keyword> keywords) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaDeAlta = fechaDeAlta;
		this.costoAsociado = tipoOL.getCosto();
		this.tipoOL = tipoOL;
		this.postulaciones = new ArrayList<>();
		this.keywords = keywords;
	}
	
	//Getters
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}
	
	public String getDepartamento() {
		return this.departamento;
	}
	
	public String getHorario() {
		return this.horario;
	}
	
	public float getRemuneracion() {
		return this.remuneracion;
	}
	
	public LocalDate getFechaDeAlta() {
		return this.fechaDeAlta;
	}
	
	public float getCostoAsociado() {
		return this.costoAsociado;
	}
	
	public Tipo getTipoOL() {
		return this.tipoOL;
	}
	
	public List<Postulacion> getPostulaciones() {
		return this.postulaciones;
	}
	
	public Map<String, Keyword> getKeywords() {
		return this.keywords;
	}
	
	public void agregarKeyword(Keyword keyword) {
		this.keywords.put(keyword.getNombre(), keyword);
	}
	
	public void agregarPostulacion(Postulacion postulacion) {
		this.postulaciones.add(postulacion);
	}
	
	public Boolean estaVigente() {
		LocalDate venc = this.tipoOL.calcularVencimiento(this.fechaDeAlta);
		return  (venc.isAfter(LocalDate.now()));
	}
}
