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
		if (instancia == null) {
			instancia = new ManejadorUsuario();
		}
		return instancia;
	}

	public void agregarEmpresa(Empresa emp) {//AGREAR UNA EMPRESA A LA COLECCION
		mapEmpresas.put(emp.getNickname(), emp);
	}
	
	public void agregarPostulante(Postulante post) {//AGREGAR UN POSTULANTE A LA COLECCION
		mapPostulantes.put(post.getNickname(), post);
	}
	
	public Empresa[] getEmpresas(){//OBTENER COLECCIONES DE EMPRESAS
        if (this.mapEmpresas.isEmpty())
            return null;
        else {
            Collection<Empresa> emps = this.mapEmpresas.values();
            Object[] obj = emps.toArray();
            Empresa[] empresas = new Empresa[obj.length];
            for (int i = 0; i < obj.length; i++) {
                empresas[i] = (Empresa) obj[i];
            }

            return empresas;
        }
	}

	public Postulante[] getPostulantes(){//OBTENER COLECCION DE POSTULANTES
        if (this.mapPostulantes.isEmpty())
            return null;
        else {
            Collection<Postulante> post = this.mapPostulantes.values();
            Object[] obj = post.toArray();
            Postulante[] postulantes = new Postulante[obj.length];
            for (int i = 0; i < obj.length; i++) {
                postulantes[i] = (Postulante) obj[i];
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
		Usuario usr = mapEmpresas.get(nickname);
		if (usr != null) {
			return usr;
		}
		usr = mapPostulantes.get(nickname);
		return usr;
	}
	
	public boolean existeEmpresa(String nickname) {//VERIFICAR EXISTENCIA DE EMPRESA
		return mapEmpresas.containsKey(nickname);
	}
	
	public boolean existePostulante(String nickname) {//VERIFICAR EXISTENCIA DE POSTULANTE
		return this.mapPostulantes.containsKey(nickname);
	}
	
	public Postulante buscarPostulante(String nickname) {//BUSCAR POSTULANTE
		Postulante post = mapPostulantes.get(nickname);
		return post;
	}
	
	public Empresa buscarEmpresa(String nickname) {//BUSCAR EMPRESA
		Empresa emp = mapEmpresas.get(nickname);
		return emp;
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
