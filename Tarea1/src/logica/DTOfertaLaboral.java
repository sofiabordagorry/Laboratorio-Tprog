package logica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)

public class DTOfertaLaboral {
	
	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horario;
	private float remuneracion;
	private String fechaDeAlta;
	private float costoAsociado;
	private DTTipo dataTipo;
	private EstadoOL estado;
	private DTKeyword[] dataKeywords;
	private List<DTPostulacion> dataPostulaciones;
	private String dataEmpresa;
	private byte[] imagen;
	private boolean rankeada = false;
	//private DTCompra dataCompra;
	private int cantidadFav = 0;
	private String fechaSeleccion = "";
	
	public DTOfertaLaboral() {
		
	}
	
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario, float remuneracion, String fechaDeAlta, String fechaDeSelec, int favoritos, 
							float costoAsociado, DTTipo dataTipo, DTKeyword[] dataKeywords, List<DTPostulacion> dataPostulaciones, String dataEmpresa, EstadoOL estado, boolean rankeada) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaDeAlta = fechaDeAlta;
		this.fechaSeleccion = fechaDeSelec;
		this.cantidadFav = favoritos;
		this.costoAsociado = costoAsociado;
		this.dataTipo = dataTipo;
		this.estado = estado;
		this.dataKeywords = dataKeywords;
		this.dataPostulaciones = dataPostulaciones;
		this.dataEmpresa = dataEmpresa;
		this.rankeada = rankeada;
		//this.dataCompra = dataCompra;
	}
	
	
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario, float remuneracion, String fechaDeAlta, DTKeyword[] keys) {
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
	
	
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario, float remuneracion, String fechaDeAlta) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaDeAlta = fechaDeAlta;
		this.dataTipo = null;
	}
	
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario, float remuneracion, String fechaDeAlta, DTKeyword[] keys, byte[] image) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaDeAlta = fechaDeAlta;
		this.dataTipo = null;
		this.dataKeywords = keys;
		this.imagen = image;
	}
	
	// --- getters ---
	
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
	
	public String getFechaDeAlta() {
		return this.fechaDeAlta;
	}
	
	public float getCostoAsociado() {
		return this.costoAsociado;
	}
	
	public DTTipo getTipo() {
		return this.dataTipo;
	}
	
	public DTKeyword[] getKeywords() {
		return this.dataKeywords;
	}
	
	public List<DTPostulacion> getPostulaciones(){
		return this.dataPostulaciones;
	}
	
	public String getDTEmpresa() {
		return this.dataEmpresa;
	}
	
	public String toString() {
		return this.nombre;
	}


	public EstadoOL getEstado() {
		return estado;
	}


	public byte[] getImagen() {
		return imagen;
	}
	
	public boolean getRankeada() {
		return this.rankeada;
	}
	
	// --- setters ---
	
	public void setNombre(String nombre) {
	    this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
	    this.descripcion = descripcion;
	}

	public void setCiudad(String ciudad) {
	    this.ciudad = ciudad;
	}

	public void setDepartamento(String departamento) {
	    this.departamento = departamento;
	}

	public void setHorario(String horario) {
	    this.horario = horario;
	}

	public void setRemuneracion(float remuneracion) {
	    this.remuneracion = remuneracion;
	}

	public void setFechaDeAlta(String fechaDeAlta) {
	    this.fechaDeAlta = fechaDeAlta;
	}

	public void setCostoAsociado(float costoAsociado) {
	    this.costoAsociado = costoAsociado;
	}

	public void setDataTipo(DTTipo dataTipo) {
	    this.dataTipo = dataTipo;
	}

	public void setEstado(EstadoOL estado) {
	    this.estado = estado;
	}

	public void setDataKeywords(DTKeyword[] dataKeywords) {
	    this.dataKeywords = dataKeywords;
	}

	public void setDataPostulaciones(List<DTPostulacion> dataPostulaciones) {
	    this.dataPostulaciones = dataPostulaciones;
	}

	public void setDataEmpresa(String dataEmpresa) {
	    this.dataEmpresa = dataEmpresa;
	}

	public void setImagen(byte[] imagen) {
	    this.imagen = imagen;
	}
	
	public void setRankeada(boolean rankeada) {
		this.rankeada = rankeada;
	}
	
/*	public boolean estaVigente() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate venc = this.dataTipo.calcularVencimiento(LocalDate.parse(this.fechaDeAlta, formatter));
		return venc.isAfter(LocalDate.now());
	}*/

	public boolean estaVigente() {
		// Crea un objeto DateTimeFormatter con el formato de tu cadena
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Parsea la cadena a LocalDate sin bloque try-catch
        LocalDate date = LocalDate.parse(this.fechaDeAlta, formatter);
		LocalDate venc = this.dataTipo.calcularVencimiento(date);
		return venc.isAfter(LocalDate.now());
	}
	
	
	public void agregarFavorito() {
		this.cantidadFav += 1;
	}
	
	public void quitarFavorito() {
		this.cantidadFav -= 1;
	}
	
	public int getFavoritos() {
		return this.cantidadFav;
	}
	
	public void setFavoritos(int cant) {
		this.cantidadFav = cant;
	}
	
	public void setFechaSeleccion(String fecha) {
		this.fechaSeleccion = fecha;
	}
	
	public String getFechaSeleccion() {
		return this.fechaSeleccion;
	}
	
}
