package logica;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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
	
	private Empresa empresaCreadora;//nuevo atributo agregado
	
	
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
		return this.tipoOL.getCosto();
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
	
	public String getEmpresaCreadora(){
		return this.empresaCreadora.getNombreEmpresa();
	}
	
	public DTOfertaLaboral getDataOfertaLaboral() {
		DTTipo dataTipoOL = this.getTipoOL().getDataTipo();
		Map<String, DTKeyword> dataKeyWords = new HashMap<>();
		
		for (Map.Entry<String, Keyword> entry : this.getKeywords().entrySet()) {
			dataKeyWords.put(entry.getKey(), entry.getValue().getDataKeyWord());
		}
		
		List<DTPostulacion> dataPostulaciones = new LinkedList<>();
		for(Postulacion p : this.getPostulaciones()) {
			dataPostulaciones.add(p.getDataPostulacion());
		}
		
		DTOfertaLaboral dtOL = new DTOfertaLaboral(this.getNombre(),this.getDescripcion(),this.getCiudad(),this.getDepartamento(),this.getHorario(),this.getRemuneracion(),this.getFechaDeAlta(),this.getCostoAsociado(),dataTipoOL, dataKeyWords, dataPostulaciones,this.getEmpresaCreadora());
		return dtOL;
	}
}
