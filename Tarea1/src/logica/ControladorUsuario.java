package logica;

import java.time.LocalDate;
import java.util.Map;

import excepciones.EmpresasNoExistenException;
import excepciones.NoExistenEmpresasOfertasLaboralesException;
import excepciones.OfertasLaboralesNoExistenException;
import excepciones.UsuariosNoExistenException;

public class ControladorUsuario implements IUsuario {
	public ControladorUsuario() {
	}
	public DTUsuario[] listarUsuarios() throws UsuariosNoExistenException{
		 ManejadorUsuario iMU =  ManejadorUsuario.getInstancia();
		 Empresa[] empresas = iMU.getEmpresas();
		 Postulante[] postulantes = iMU.getPostulantes();
		 if(empresas != null && postulantes != null) {
			 DTUsuario[] arrUsu = new DTUsuario[empresas.length + postulantes.length];
			 
			 for(int i = 0; i < empresas.length; i++) {
				 arrUsu[i] = empresas[i].getDataEmpresa();
			 }
			 
			 for(int j = 0; j < postulantes.length; j++) {
				 arrUsu[empresas.length + j] = postulantes[j].getDataPostulante();
			 }
	
			 return arrUsu;
		 }else{
			 throw new UsuariosNoExistenException("No existen Usuarios registradas");
		 }
	}

	public DTUsuario mostrarInformacionUsuario(String nickname) {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Usuario u = iMU.buscarUsuario(nickname);
		DTUsuario dtU = u.getDataUsuario(); 
		return dtU;
	}
	
	public DTEmpresa[] listarEmpresas() throws EmpresasNoExistenException,NoExistenEmpresasOfertasLaboralesException {

		 ManejadorUsuario iMU =  ManejadorUsuario.getInstancia();
		 Empresa[] empresas = iMU.getEmpresas();
		 boolean hayOL = false;
		 for(int i = 0; i < empresas.length; i++) {
			 hayOL = hayOL || (empresas[i].getOfertasLaborales().size() != 0);
		 }
		 if (empresas != null ) {
			 if(hayOL) {
				 DTEmpresa[] arrEmp = new DTEmpresa[empresas.length];
				 for(int i = 0; i < empresas.length; i++) {
					 arrEmp[i] = empresas[i].getDataEmpresa();
				 }
				return arrEmp;
			 }else {
				 throw new NoExistenEmpresasOfertasLaboralesException("No hay empresas con Ofertas Laborales");
			 }
		 } else {
			 throw new EmpresasNoExistenException("No existen Empresas registradas");
		 }

	}

	public DTOfertaLaboral[] listarOfertasLaborales(String nomEmpresa) throws OfertasLaboralesNoExistenException {
		ManejadorUsuario iMU = ManejadorUsuario.getInstancia();
		Empresa[] empresas = iMU.getEmpresas();
		int i = 0;
		while(empresas[i].getNombreEmpresa() != nomEmpresa) {
			i++;
		}
		Empresa e = empresas[i];
		Map<String, OfertaLaboral> ofLab = e.getOfertasLaborales();
		if(ofLab.size() != 0) {
			DTOfertaLaboral[] ofertasRes = new DTOfertaLaboral[ofLab.size()];
			if(ofLab.size() != 0) {
				int j = 0;
				for(Map.Entry<String, OfertaLaboral> entry : ofLab.entrySet()) {
					ofertasRes[j] = entry.getValue().getDataOfertaLaboral();
					j++;
				}
			}
			return ofertasRes;
		}else {
			throw new OfertasLaboralesNoExistenException("La empresa seleccionada no tiene Ofertas Laborales");
		}
		

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
