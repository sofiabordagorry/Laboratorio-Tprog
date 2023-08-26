package logica;

import java.time.LocalDate;
import java.util.Map;

public class ControladorUsuario implements IUsuario {
	public ControladorUsuario() {
	}
	public DTUsuario[] listarUsuarios(){
		 ManejadorUsuario iMU =  ManejadorUsuario.getInstancia();
//		 Empresa u1 = new Empresa("nickname", "nombre", "apellido", "correo", "NombreEmpresa", "descripcion", "link");
//		 LocalDate d = LocalDate.now();
//		 Postulante u2 = new Postulante("nickname2", "nombre2", "apellido2", "correo2", d,"nacionalidad");
//		 iMU.agregarPostulante(u2);
//		 iMU.agregarEmpresa(u1);
		 Empresa[] empresas = iMU.getEmpresas();
		 Postulante[] postulantes = iMU.getPostulantes();
		 DTUsuario[] arrUsu = new DTUsuario[empresas.length + postulantes.length];
		 
		 for(int i = 0; i < empresas.length; i++) {
			 arrUsu[i] = empresas[i].getDataEmpresa();
		 }
		 
		 for(int j = 0; j < postulantes.length; j++) {
			 arrUsu[empresas.length + j] = postulantes[j].getDataPostulante();
		 }

		 return arrUsu;
	}

	public DTUsuario mostrarInformacionUsuario(String nickname) {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Usuario u = iMU.buscarUsuario(nickname);
		DTUsuario dtU = u.getDataUsuario(); 
		return dtU;
	}
	
	public DTEmpresa[] listarEmpresas() {
		 ManejadorUsuario iMU =  ManejadorUsuario.getInstancia();
		 Empresa u1 = new Empresa("nickname", "nombre", "apellido", "correo", "NombreEmpresa", "descripcion", "link");
		 LocalDate d = LocalDate.now();
		 Postulante u2 = new Postulante("nickname2", "nombre2", "apellido2", "correo2", d,"nacionalidad");
		 iMU.agregarPostulante(u2);
		 iMU.agregarEmpresa(u1);
		 Empresa[] empresas = iMU.getEmpresas();
		 DTEmpresa[] arrEmp = new DTEmpresa[empresas.length];
		 for(int i = 0; i < empresas.length; i++) {
			 arrEmp[i] = empresas[i].getDataEmpresa();
		 }
		 return arrEmp;
	}

	public DTOfertaLaboral[] listarOfertasLaborales(String nomEmpresa) {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Empresa[] empresas = iMU.getEmpresas();
		int i = 0;
		while(empresas[i].getNombreEmpresa() != nomEmpresa) {
			i++;
		}
		Empresa e = empresas[i];
		Map<String, OfertaLaboral> ofLab = e.getOfertasLaborales();
		DTOfertaLaboral[] ofertasRes = new DTOfertaLaboral[ofLab.size()];
		if(ofLab.size() != 0) {
			int j = 0;
			for(Map.Entry<String, OfertaLaboral> entry : ofLab.entrySet()) {
				ofertasRes[j] = entry.getValue().getDataOfertaLaboral();
				j++;
			}
		}
		
		return ofertasRes;
	}

	public DTOfertaLaboral mostrarDatosOfertaLaboral(String OfertaLaboral) {
		ManejadorOfertaLaboral contOfertaLaboral = ManejadorOfertaLaboral.getInstance();
		DTOfertaLaboral ofLabRes= contOfertaLaboral.buscarOfertaLaboral(OfertaLaboral).getDataOfertaLaboral();
		return ofLabRes;
	}

	public void modificarEmpresa(String nick, String nom, String ap, String nomE, String desc, String l) {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Empresa e = iMU.buscarEmpresa(nick);
		e.modificarDatos(nom, ap, nomE, desc, l);
	}

	public void modificarPostulante(String nick, String nom, String ap, LocalDate f, String nac) {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Postulante e = iMU.buscarPostulante(nick);
		e.modificarDatos(nom, ap, f, nac);
	}
	
}
