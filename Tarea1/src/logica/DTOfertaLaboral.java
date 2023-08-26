package logica;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;

public class DTOfertaLaboral {
	
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horario;
	private float remuneracion;
	private LocalDate fechaDeAlta;
	private float costoAsociado;
	private DTTipo dataTipo;
	private Map<String, DTKeyword> dataKeywords;
	private List<DTPostulacion> dataPostulaciones;
	private DTEmpresa dataEmpresa;
	private DTCompra dataCompra;
	
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario, float remuneracion, LocalDate fechaDeAlta, float costoAsociado, DTTipo dataTipo, Map<String, DTKeyword> dataKeywords, List<DTPostulacion> dataPostulaciones, DTEmpresa dataEmpresa, DTCompra dataCompra) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaDeAlta = fechaDeAlta;
		this.costoAsociado = costoAsociado;
		this.dataTipo = dataTipo;
		this.dataKeywords = dataKeywords;
		this.dataPostulaciones = dataPostulaciones;
		this.dataEmpresa = dataEmpresa;
		this.dataCompra = dataCompra;
	}
	
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario, float remuneracion, LocalDate fechaDeAlta, Map<String, DTKeyword> keys) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaDeAlta = fechaDeAlta;
		this.dataTipo = null;
		this.dataKeywords = keys;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}

	public String getDepartamente() {
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
	
	public DTTipo getTipo() {
		return this.dataTipo;
	}
	
	public Map<String, DTKeyword> getKeywords() {
		return this.dataKeywords;
	}
	
	public List<DTPostulacion> getPostulaciones(){
		return this.dataPostulaciones;
	}
	
	public DTEmpresa getDTEmpresa() {
		return this.dataEmpresa;
	}
	public DTCompra getDTCompra() {
		return this.dataCompra;
	}
}
