package logica;

import java.time.LocalDate;
import java.util.Map;

public class DTOfertaLaboral {
	
	private String nombre;
	private String ciudad;
	private String departamento;
	private int horario;
	private float remuneracion;
	private LocalDate fechaDeAlta;
	private float costoAsociado;
	private DTTipo dataTipo;
	private Map<String, DTKeyword> dataKeywords;
	
	public DTOfertaLaboral(String nombre, String ciudad, String departamento, int horario, float remuneracion, LocalDate fechaDeAlta, float costoAsociado, DTTipo dataTipo, Map<String, DTKeyword> dataKeywords) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaDeAlta = fechaDeAlta;
		this.costoAsociado = costoAsociado;
		this.dataTipo = dataTipo;
		this.dataKeywords = dataKeywords;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}

	public String getDepartamente() {
		return this.departamento;
	}
	
	public int getHorario() {
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
}
