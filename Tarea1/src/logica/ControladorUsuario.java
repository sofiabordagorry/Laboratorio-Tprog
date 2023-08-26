package logica;

import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.EmpresaSinOfertasException;
import excepciones.EmpresasNoExistenException;
import excepciones.PostulantesNoExistenException;
import excepciones.YaSePostuloException;

public class ControladorUsuario implements IUsuario {
	public ControladorUsuario() {
	}

    public DTEmpresa[] listarEmpresas() throws EmpresasNoExistenException {
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        Empresa[] empresas = mu.getEmpresas();

        if (empresas != null) {
        	DTEmpresa [] de = new DTEmpresa[empresas.length];
            Empresa empresa;

            for (int i = 0; i < empresas.length; i++) {
                empresa = empresas[i];
                de[i] = new DTEmpresa(empresa.getNickname(), empresa.getNombre(), empresa.getApellido(), empresa.getCorreo(), empresa.getNombreEmpresa(), empresa.getDescripcion(), empresa.getLink());
            }
            return de;

        } else throw new EmpresasNoExistenException("No existen empresas");
    }
    
    public DTPostulante[] listarPostulantes() throws PostulantesNoExistenException{
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        Postulante[] postulantes = mu.getPostulantes();

        if (postulantes != null) {
            DTPostulante [] dp = new DTPostulante[postulantes.length];
            Postulante postulante;

            for (int i = 0; i < postulantes.length; i++) {
                postulante = postulantes[i];
                dp[i] = new DTPostulante(postulante.getNickname(), postulante.getNombre(), postulante.getApellido(), postulante.getCorreo(), postulante.getFechaNacimiento(), postulante.getNacionalidad());
            }
            return dp; 
        }  else throw new PostulantesNoExistenException("No existen postulantes");
    }
    
    public  DTOfertaLaboral[] listarOfertasLaboralesVigentes(String empresa) throws EmpresaSinOfertasException{
        ManejadorUsuario mu = ManejadorUsuario.getInstancia();
        Empresa emp = mu.buscarEmpresa(empresa);
        ArrayList<DTOfertaLaboral> ofertas = emp.obtenerOfertasVigentes();
        DTOfertaLaboral[] dtofertas = (DTOfertaLaboral[]) ofertas.toArray();
        if (dtofertas != null) { return  dtofertas;}
        else { throw new EmpresaSinOfertasException("La empresa seleccionada no tiene ofertas vigentes");}
    }
    
    public void ingresarPostulacion(String CVReducido, String motivacion, LocalDate fecha, String empresa, String oferta, String postulante) throws YaSePostuloException {
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
        else  { throw new YaSePostuloException("El postulante seleccionado ya se ha postulado a esta oferta laboral");}
        		
    }
}
