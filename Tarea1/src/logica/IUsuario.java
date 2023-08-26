package logica;

import java.time.LocalDate;
import java.util.ArrayList;

import excepciones.EmpresaSinOfertasException;
import excepciones.EmpresasNoExistenException;
import excepciones.PostulantesNoExistenException;
import excepciones.YaSePostuloException;

public interface IUsuario {
    public DTEmpresa[] listarEmpresas() throws EmpresasNoExistenException;
    public DTPostulante[] listarPostulantes() throws PostulantesNoExistenException;
    public DTOfertaLaboral[] listarOfertasLaboralesVigentes(String empresa) throws EmpresaSinOfertasException;
    public void ingresarPostulacion(String CVReducido, String motivacion, LocalDate fecha, String empresa, String oferta, String postulante) throws YaSePostuloException;
}
