package logica;
import java.util.Map;
import java.util.Collection;
import java.util.HashMap;

public class ManejadorUsuario {
	private static ManejadorUsuario instancia = null;
	private Map<String, Empresa> mapEmpresas;
	private Map<String, Postulante> mapPostulantes;
	
	private ManejadorUsuario() {//CONSTRUCTOR
		mapEmpresas = new HashMap<>();
		mapPostulantes = new HashMap<>();
	}
	
	public static ManejadorUsuario getInstancia() {//OBTENER INSTANCIA
		if(instancia == null) {
			instancia = new ManejadorUsuario();
		}
		return instancia;
	}
	
	public void agregarEmpresa(Empresa e) {//AGREAR UNA EMPRESA A LA COLECCION
		mapEmpresas.put(e.getNickname(), e);
	}
	
	public void agregarPostulante(Postulante p) {//AGREGAR UN POSTULANTE A LA COLECCION
		mapPostulantes.put(p.getNickname(), p);
	}
	
	public Empresa[] getEmpresas(){//OBTENER COLECCIONES DE EMPRESAS
        if (this.mapEmpresas.isEmpty())
            return null;
        else {
            Collection<Empresa> emps = this.mapEmpresas.values();
            Object[] o = emps.toArray();
            Empresa[] empresas = new Empresa[o.length];
            for (int i = 0; i < o.length; i++) {
                empresas[i] = (Empresa) o[i];
            }

            return empresas;
        }
	}

	public Postulante[] getPostulantes(){//OBTENER COLECCION DE POSTULANTES
        if (this.mapPostulantes.isEmpty())
            return null;
        else {
            Collection<Postulante> post = this.mapPostulantes.values();
            Object[] o = post.toArray();
            Postulante[] postulantes = new Postulante[o.length];
            for (int i = 0; i < o.length; i++) {
                postulantes[i] = (Postulante) o[i];
            }

            return postulantes;
        }
	}
	
	public Map<String, Postulante> getMapPostulantes() {
		return this.mapPostulantes;
	}
	
	public Map<String, Empresa> getMapEmpresas() {
		return this.mapEmpresas;
	}

	
	public Usuario buscarUsuario(String nickname) {//BUSCAR UN USUARIO
		Usuario u = mapEmpresas.get(nickname);
		if(u != null) {
			return u;
		}
		u = mapPostulantes.get(nickname);
		return u;
	}
	
	public boolean existeEmpresa(String nickname) {//VERIFICAR EXISTENCIA DE EMPRESA
		return mapEmpresas.containsKey(nickname);
	}
	
	public void existePostulante(String nickname) {//VERIFICAR EXISTENCIA DE POSTULANTE
		mapPostulantes.remove(nickname);
	}
	
	public Postulante buscarPostulante(String nickname) {//BUSCAR POSTULANTE
		Postulante p = mapPostulantes.get(nickname);
		return p;
	}
	
	public Empresa buscarEmpresa(String nickname) {//BUSCAR EMPRESA
		Empresa e = mapEmpresas.get(nickname);
		return e;
	}
	
	public void eliminarEmpresa(String nickname) {//ELIMINAR EMPRESA DE LA COLECION
		mapEmpresas.remove(nickname);
	}
	
	public void eliminarPostulante(String nickname) {//ELIMINAR POSTULANTE DE LA COLECCCION
		mapPostulantes.remove(nickname);
	}

	public Usuario buscarUsuarioPorMail(String login) {
		Usuario usr = null;
		for (Usuario user : mapEmpresas.values()) {
            if (user.getCorreo().equals(login)) {
                usr = user ;
            }
        }
		if (usr == null) {
			for (Usuario user : mapPostulantes.values()) {
	            if (user.getCorreo().equals(login)) {
	                usr = user ;
	            }
	        }
		}
		return usr;
	}
	
	public void borrarUsuarios() {
        this.mapEmpresas.clear();
        this.mapPostulantes.clear();
    }

}
