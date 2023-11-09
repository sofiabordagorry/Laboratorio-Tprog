package logica;

import java.util.Map;
import java.util.Collection;
import java.util.HashMap;


public class ManejadorOfertaLaboral {
	
	private Map<String, OfertaLaboral> ofertasLaborales;
	private Map<String, Keyword> keywords;
	
	private static ManejadorOfertaLaboral instance = null;
	
	private ManejadorOfertaLaboral() {
		this.ofertasLaborales = new HashMap<>();
		this.keywords = new HashMap<>();
	}

	public static ManejadorOfertaLaboral getInstance() {
		if (instance == null) {
            instance = new ManejadorOfertaLaboral();
        }
        return instance;
	}
	
	public OfertaLaboral buscarOfertaLaboral(String nombre) {
		return this.ofertasLaborales.get(nombre);
	}
	
	public Keyword buscarKeyword(String nombre) {
		return this.keywords.get(nombre);
	}
	
	public Map<String, OfertaLaboral> getOfertasLaborales() {
		return this.ofertasLaborales;
	}
	
	public Keyword[] getKeywords() {
		if (this.keywords.isEmpty())
            return null;
        else {
            Collection<Keyword> key = this.keywords.values();
            Object[] obj = key.toArray();
            Keyword[] keys = new Keyword[obj.length];
            for (int i = 0; i < obj.length; i++) {
                keys[i] = (Keyword) obj[i];
            }

            return keys;
        }
	}
	
	public Map<String, Keyword> getMapKeywords() {
		return keywords;
	}

	public void agregarOfertaLaboral(OfertaLaboral ofl) {
		this.ofertasLaborales.put(ofl.getNombre(), ofl);
	}
	
	public void agregarKeyword(Keyword key) {
		this.keywords.put(key.getNombre(), key);
	}
	
	public void eliminarOfertaLaboral(String nombre) {
		this.ofertasLaborales.remove(nombre);
	}
	
	public void eliminarKeyword(String nombre) {
		this.keywords.remove(nombre);
	}
	
	public boolean existeOfertaLaboral(String nombre) {
		return this.ofertasLaborales.containsKey(nombre);
	}
	
	public boolean existeKeyword(String nombre) {
		return this.keywords.containsKey(nombre);
	}
	
	
	public void limpiar() {
		ofertasLaborales.clear();
		keywords.clear();
	}
	
}

