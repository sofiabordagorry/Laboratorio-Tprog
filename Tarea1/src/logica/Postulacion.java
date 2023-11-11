package logica;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Postulacion {
	private LocalDate fecha;
	private String cvreducido;
	private String motivacion;
	private Postulante postulante;
	private OfertaLaboral ofertaLaboral;
	private int rank;
	private String video;
	
	public Postulacion(LocalDate fecha, String CVReducido, String motivacion, Postulante postulante, OfertaLaboral ofertaLaboral, String video) {
		this.fecha = fecha;
		this.cvreducido = CVReducido;
		this.motivacion = motivacion;
		this.postulante = postulante;
		this.ofertaLaboral = ofertaLaboral;
		this.rank = -1;
		this.video=video;
	}
	
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	public String getCVReducido() {
		return this.cvreducido;
	}
	
	public String getMotivacion() {
		return this.motivacion;
	}
	
	public Postulante getPostulante() {
		return this.postulante;
	}
	
	public OfertaLaboral getOfertaLaboral() {
		return this.ofertaLaboral;
	}
	
	
	public String getVideo() {
		return this.video;
	}
	
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	public void setCVReducido(String CVReducido) {
		this.cvreducido = CVReducido;
	}

	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;

	}
	
	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}
	
	public void setOfertaLaboral(OfertaLaboral ofertaLaboral) {
		this.ofertaLaboral = ofertaLaboral;
	}
	
	public Boolean verificarOfertaLaboral(String oferta) {
		String nombre = this.ofertaLaboral.getNombre();
		return  oferta.equals(nombre);
	}

	public DTPostulacion getDataPostulacion() {
		LocalDate fechaDeAlta = this.getFecha();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String fechaDeAltaString = fechaDeAlta.format(formatter);
		DTPostulacion dtP = new DTPostulacion(fechaDeAltaString, this.getCVReducido(), this.getMotivacion(), this.getPostulante().getNickname(), this.getOfertaLaboral().getNombre(), this.getRank(), this.getVideo());

		return dtP;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	

	public void setVideo(String video) {
		this.video=video;
		}
	
    public String obtenerIDDeVideo() {
    	String videoURL = this.video;
        String videoID = null;

        if (videoURL != null && videoURL != "SV" && !videoURL.isEmpty()) {
            String regex = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|%2Fvideos%2F|%2Fv%2F|%2Fe%2F|%2F|%3Fv%3D|%26v%3D)[^#\\?\\&\\s]*";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(videoURL);
            
            if (matcher.find()) {
                videoID = matcher.group();
            }
        }

        return videoID;
    }
}