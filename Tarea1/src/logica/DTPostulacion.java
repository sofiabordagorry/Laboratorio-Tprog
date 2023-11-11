package logica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DTPostulacion {
	private String fecha;
	private String cVReducido;
	private String motivacion;
	private String postulante;
	private String oferta;
	private int rank;
	private String video;

	public DTPostulacion() {
	}
	
	public DTPostulacion(String fecha, String CVReducido, String motivacion, String postulante, String oferta, int rank, String video) {
		this.fecha = fecha;
		this.cVReducido = CVReducido;
		this.motivacion = motivacion;
		this.postulante = postulante;
		this.oferta = oferta;
		this.rank = rank;
		this.video=video;
	}
	
	public String getFecha() {
		return this.fecha;
	}
	
	public String getCVReducido() {
		return this.cVReducido;
	}
	
	public String getMotivacion() {
		return this.motivacion;
	}
	
	public String getPostulante() {
		return this.postulante;
	}
	
	public String getOferta() {
		return this.oferta;
	}
	
	public int getRank() {
		return this.rank;
	}
	
	public void setFecha(String fecha) {
	    this.fecha = fecha;
	}
	
	public String getVideo() {
		return this.video;
	}

	public void setCVReducido(String cVReducido) {
	    this.cVReducido = cVReducido;
	}

	public void setMotivacion(String motivacion) {
	    this.motivacion = motivacion;
	}

	public void setPostulante(String postulante) {
	    this.postulante = postulante;
	}

	public void setOferta(String oferta) {
	    this.oferta = oferta;
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
