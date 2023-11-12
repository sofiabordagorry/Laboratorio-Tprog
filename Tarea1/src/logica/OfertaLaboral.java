package logica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
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
	private EstadoOL estado;
	private byte[] imagen;
	private Empresa empresaCreadora;
	private boolean rankeada = false;
	private int cantidadFav;//nuevo atributo tarea3
	private int cantidadVisitas;
	private LocalDate fechaSeleccion ;//nuevo atributos tarea3
	

	public OfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario,
								float remuneracion, LocalDate fechaDeAlta, float costoAsociado, Tipo tipoOL, Map<String, Keyword> keywords, Empresa empCreadora) {
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
		this.estado = EstadoOL.Ingresada;
		this.empresaCreadora = empCreadora;
		this.cantidadFav = 0;
		this.fechaSeleccion = null;
		this.cantidadVisitas = 0;
	}
	
	public OfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario,
            float remuneracion, LocalDate fechaDeAlta, float costoAsociado, Tipo tipoOL, Map<String, Keyword> keywords, EstadoOL estado, Empresa empCreadora, byte[] image) {
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
        this.estado = estado;
        this.empresaCreadora = empCreadora;
        this.setImagen(image);
		this.cantidadFav = 0;
		this.fechaSeleccion = null;
		this.cantidadVisitas = 0;
    }

	public OfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario,
			float remuneracion, LocalDate fechaDeAlta, float costoAsociado, Tipo tipoOL, Map<String, Keyword> keywords, Empresa empCreadora, byte[] image) {
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
		this.estado = EstadoOL.Ingresada;
		this.empresaCreadora = empCreadora;
		this.setImagen(image);
		this.cantidadFav = 0;
		this.fechaSeleccion = null;
		this.cantidadVisitas = 0;
	}
	
	public OfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario,
			float remuneracion, LocalDate fechaDeAlta, float costoAsociado, Tipo tipoOL, Map<String, Keyword> keywords, Empresa empCreadora, byte[] image, int visitas) {
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
		this.estado = EstadoOL.Ingresada;
		this.empresaCreadora = empCreadora;
		this.setImagen(image);
		this.cantidadFav = 0;
		this.fechaSeleccion = null;
		this.cantidadVisitas = 0;
	}
	
	public OfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario,
			float remuneracion, LocalDate fechaDeAlta, float costoAsociado, Tipo tipoOL, Map<String, Keyword> keywords, EstadoOL estado, Empresa empCreadora, byte[] image, int visitas) {
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
		this.estado = estado;
		this.empresaCreadora = empCreadora;
		this.setImagen(image);
		this.cantidadFav = 0;
		this.fechaSeleccion = null;
		this.cantidadVisitas = visitas;
	}
	
	public void setEstado(EstadoOL est) {
		this.estado = est;
	}
	
	//Getters
	public String getNombre() {
		return this.nombre;
	}
	
	public EstadoOL getEstado() {
		return this.estado;
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

	public boolean getRankeada() {
		return this.rankeada;
	}
	
	public List<Postulacion> getPostulaciones() {
		return this.postulaciones;
	}
	
	public Map<String, Keyword> getKeywords() {
		return this.keywords;
	}
	
	public void setRankeada(boolean rankeada) {
		this.rankeada = rankeada;
	}
	
	public void agregarKeyword(Keyword keyword) {
		this.keywords.put(keyword.getNombre(), keyword);
	}
	
	public void agregarPostulacion(Postulacion postulacion) {
		this.postulaciones.add(postulacion);
	}
	

	public Boolean estaVigente() {
		LocalDate venc = this.tipoOL.calcularVencimiento(this.fechaDeAlta);
		return venc.isAfter(LocalDate.now());
	}

	public String getEmpresaCreadora(){
		return this.empresaCreadora.getNickname();
	}
	
	public DTOfertaLaboral getDataOfertaLaboral() {
		DTTipo dataTipoOL = this.getTipoOL().getDataTipo(); 
		DTKeyword[] dataKeyWords = new DTKeyword[this.keywords.size()];
		
		
		if (!this.getKeywords().isEmpty()) {
			int i = 0;
		for (Map.Entry<String, Keyword> entry : this.getKeywords().entrySet()) {
			dataKeyWords[i] = entry.getValue().getDataKeyWord();
			i++;
		}
		}
		List<DTPostulacion> dataPostulaciones = new LinkedList<>();
		for (Postulacion post : this.getPostulaciones()) {
			dataPostulaciones.add(post.getDataPostulacion());
		}

        // Crea un objeto DateTimeFormatter con el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Convierte el LocalDate a una cadena
        String dateString = this.getFechaDeAlta().format(formatter);
        String dateString2 = "";
        if (this.getFechaSeleccion() != null)
        	dateString2 = this.getFechaSeleccion().format(formatter);
        
		DTOfertaLaboral dtOL = new DTOfertaLaboral(this.getNombre(), this.getDescripcion(), this.getCiudad(), this.getDepartamento(), this.getHorario(), this.getRemuneracion(), dateString, dateString2, this.getFavoritos(), 
												this.getCostoAsociado(), dataTipoOL, dataKeyWords, dataPostulaciones, this.getEmpresaCreadora(), this.getEstado(), this.getRankeada());
		return dtOL;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
	
	public Postulacion getPostulacion(String postulante) {
		for (Postulacion post: this.postulaciones) {
			if (post.getPostulante().getNickname().equals(postulante)) {
				return post;
			}
		}
		return null;
	}
	
	public void realizarSeleccion(String[] rankings) {
		Postulacion post;
		for (int i = 1; i <= rankings.length; i++) {
			post = this.getPostulacion(rankings[i-1]);
			post.setRank(i);
		}
		
		this.setRankeada(true);
		this.fechaSeleccion = LocalDate.now();
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
	
	public void setFechaSeleccion(LocalDate fecha) {
		this.fechaSeleccion = fecha;
	}
	
	public LocalDate getFechaSeleccion() {
		return this.fechaSeleccion;
	}
}
