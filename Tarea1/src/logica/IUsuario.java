package logica;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IUsuario {
    public DTEmpresa[] listarEmpresas();
    public DTPostulante[] listarPostulantes();
    public DTOfertaLaboral[] listarOfertasLaboralesVigentes(String empresa);
    public Boolean ingresarPostulacion(String CVReducido, String motivacion, LocalDate fecha, String empresa, String oferta, String postulante);
}
