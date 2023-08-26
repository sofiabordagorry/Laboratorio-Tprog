package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorUsuario implements IUsuario {
	public ControladorUsuario() {
	}

    public DTEmpresa[] listarEmpresas() {
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        Empresa[] empresas = mu.getEmpresas();
        DTEmpresa[] de  = null;

        if (empresas != null) {
        	de = new DTEmpresa[empresas.length];
            Empresa empresa;

            for (int i = 0; i < empresas.length; i++) {
                empresa = empresas[i];
                de[i] = new DTEmpresa(empresa.getNickname(), empresa.getNombre(), empresa.getApellido(), empresa.getCorreo(), empresa.getNombreEmpresa(), empresa.getDescripcion(), empresa.getLink());
            }
        }
        return de;
    }
    
    public DTPostulante[] listarPostulantes() {
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        Postulante[] postulantes = mu.getPostulantes();
        DTPostulante[] dp = null;

        if (postulantes != null) {
            dp = new DTPostulante[postulantes.length];
            Postulante postulante;

            for (int i = 0; i < postulantes.length; i++) {
                postulante = postulantes[i];
                dp[i] = new DTPostulante(postulante.getNickname(), postulante.getNombre(), postulante.getApellido(), postulante.getCorreo(), postulante.getFechaNacimiento(), postulante.getNacionalidad());
            }

        }
        return dp;

    }
    
    public  DTOfertaLaboral[] listarOfertasLaboralesVigentes(String empresa) {
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        Empresa emp = mu.buscarEmpresa(empresa);
        ArrayList<DTOfertaLaboral> ofertas = emp.obtenerOfertasVigentes();
        return (DTOfertaLaboral[]) ofertas.toArray();
    }
    
    public Boolean ingresarPostulacion(String CVReducido, String motivacion, LocalDate fecha, String empresa, String oferta, String postulante) {
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        Postulante pos = mu.buscarPostulante(postulante);
        Boolean existe =  pos.existePostulacion(oferta);
        Empresa emp = mu.buscarEmpresa(empresa);
        OfertaLaboral of = emp.buscarOferta(oferta);
        
        if (!existe) {
        	Postulacion postu = new Postulacion(fecha, CVReducido, motivacion, pos, of);
        	pos.agregarPostulacion(postu);
        	of.agregarPostulacion(postu);	
        }
    	
        		
        return existe;		
    }
}
