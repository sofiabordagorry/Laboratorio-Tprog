package logica;

import java.util.*;

public class ManejadorUsuario {
	
	private static ManejadorUsuario instance;
	
	private ManejadorUsuario() {
		this.empresas= new HashMap<>();
		this.postulantes = new HashMap<>();
	};
	
	private Map<String, Empresa> empresas;
	private Map<String, Postulante> postulantes;
	
	public static ManejadorUsuario getInstance() {
		if (instance == null) {
			instance = new ManejadorUsuario();
		}
		
		return instance;
	}
	
	public Map<String, Empresa> getEmpresas() {
		return this.empresas;
	}
	
	public Map<String, Postulante> getPostulantes() {
		return this.postulantes;
	}
	
	public Empresa getEmpresa(String nickname) {	
		return this.empresas.get(nickname);
	}
	
	public Postulante getPostulante(String nickname) {	
		return this.postulantes.get(nickname);
	}
	
	public boolean existeEmpresa(String nickname) {
		return this.empresas.containsKey(nickname);
	}
	
	public boolean existePosutlante(String nickname) {
		return this.postulantes.containsKey(nickname);
	}
	
	public void addEmpresa(Empresa emp) {
		this.empresas.put(emp.getNickname(), emp);
	}
	
	public void addPostulante(Postulante post) {
		this.postulantes.put(post.getNickname(), post);
	}
	
	public void borrarEmpresa(String nickname) {
		this.empresas.remove(nickname);
	}
	
	public void borrarPostulante(String nickname) {
		this.postulantes.remove(nickname);
	}
	
	public int getCantidadEmpresas() {
		return this.empresas.size();
	}
	
	public int getCantidadPostulantes() {
		return this.postulantes.size();
	}
}
