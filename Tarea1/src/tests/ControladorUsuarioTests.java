package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.EmpresaSinOfertasException;
import excepciones.EmpresasNoExistenException;
import excepciones.ExisteUnUsuarioYaRegistradoException;
import excepciones.NoExistenEmpresasOfertasLaboralesException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.OfertasLaboralesNoExistenException;
import excepciones.TipoRepetidoException;
import excepciones.YaSePostuloException;
import logica.DTEmpresa;
import logica.DTPostulacion;
import logica.DTKeyword;
import logica.DTOfertaLaboral;
import logica.DTTipo;
import logica.DTUsuario;
import logica.Empresa;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Tipo;
import logica.DTPostulante;

class ControladorUsuarioTests {
    private static IUsuario contUsuario;
    private static IOfertaLaboral contOL;
    private ManejadorUsuario mu;

    @BeforeAll
    public static void iniciar(){
        Factory fab = Factory.getInstance();
        contUsuario = fab.getIUsuario();
        contOL = fab.getIOfertaLaboral();
    }
    
    @AfterEach
    public void borrarUsuarios() {
        mu = ManejadorUsuario.getInstancia();
        mu.borrarUsuarios();
    }


@Test
void testIngresarPostulacionOK() {

        String CVReducidoTest = "CV TestOK";
        String motivacionTest = "Motivacion TestOK";
        LocalDate fechaTest = LocalDate.now(); 
        
      //Datos Empresa
        String nickname = "NicknamePrueba3";
        String nombre = "NombrePrueba";
        String apellido = "ApellidoPrueba";
        String correo = "correoPrueba3@gmail.com";
        String nombreEmpresa = "NombreEmpresaPrueba";
        String descripcion = "DescripcionPrueba";
        String link = "LinkPrueba";
        DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, nombreEmpresa, descripcion, link);

        //Datos de tipo
        String nombreTipo = "Nombre Tipo Test ListarEmpresasOK";
        String descripcionTipo = "Descripcion Tipo Test ListarEmpresasOK";
        int exposicionTipo = 10;
        int duracionTipo = 100000;
        float costoTipo = 15.0f;
        Date fechaDeAltaTipo = new Date();
           LocalDate localDate = fechaDeAltaTipo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
           DTTipo dtTipo = new DTTipo(nombreTipo, descripcionTipo, exposicionTipo,duracionTipo,costoTipo,localDate);

           //Datos de Keyword 
           String nombreK = "NombreKey";
           DTKeyword dtk = new DTKeyword(nombreK);
           Map<String, DTKeyword> mapdtk = new HashMap<>();
           mapdtk.put(nombreK, dtk);

           //Datos de Oferta Laboral
           String nombreOL = "NombreOL";
           String descripcionOL = "DescripcionOL";
           String ciudadOL = "CiudadOL";
           String departamentoOL = "DepartamentoOL";
           String horario = "HorarioOL";
           float remuneracion = 123;
           LocalDate dateOL = LocalDate.of(2023, 8, 1);
           DTOfertaLaboral dtol = new DTOfertaLaboral(nombreOL, descripcionOL, ciudadOL, departamentoOL, horario, remuneracion, dateOL, mapdtk);
      
           
           //Datos de postulante
           String nickPostu = "NickPostu";
           String nombrePostu = "NombrePostu";
           String apellidoPostu = "ApellidoPostu";
           String correoPostu = "correoPostu";
           LocalDate fechaPostu = LocalDate.of(2023, 8, 1);
           String nacionalidadPostu = "nacionalidadPostu";          
           DTPostulante dtpos = new DTPostulante(nickPostu, nombrePostu, apellidoPostu, correoPostu, fechaPostu, nacionalidadPostu);
           
           
           
           try {
        
        	contOL.ingresarTipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
        	contUsuario.ingresarDatosEmpresa(emp);
        	contUsuario.ingresarDatosPostulante(dtpos);
        	contOL.ingresarDatosOL(nickname, nombreTipo, dtol);;
            contUsuario.ingresarPostulacion(CVReducidoTest, motivacionTest, fechaTest, nickname, nombreOL, nickPostu);
         
            DTOfertaLaboral[] ofertas = null;
			try {
				ofertas = contUsuario.listarOfertasLaborales(nickname);
			} catch (OfertasLaboralesNoExistenException e) {
				// TODO Auto-generated catch block
	            fail(e.getMessage());
				e.printStackTrace();
			}
            List<DTPostulacion> postulaciones = ofertas[0].getPostulaciones();
            DTPostulacion dtpostulacion = postulaciones.get(0);

            assertEquals(dtpostulacion.getCVReducido(), CVReducidoTest);
            assertEquals(dtpostulacion.getMotivacion(), motivacionTest);
            assertEquals(dtpostulacion.getFecha(), fechaTest);
            assertEquals(dtpostulacion.getOferta(), nombreOL);
            assertEquals(dtpostulacion.getPostulante(), nickPostu);      
        }catch(ExisteUnUsuarioYaRegistradoException e) {
            fail(e.getMessage());
            e.printStackTrace();
        }catch(OfertaLaboralRepetidaException e) {
            fail(e.getMessage());
            e.printStackTrace();
        }catch(TipoRepetidoException e) {
            fail(e.getMessage());
            e.printStackTrace();
        }
           catch(YaSePostuloException e) {
            fail(e.getMessage());
            e.printStackTrace();
        };
    }
@Test
    void testIngresarPostulacionRepetida (){
	

    String CVReducidoTest = "CV TestOK";
    String motivacionTest = "Motivacion TestOK";
    LocalDate fechaTest = LocalDate.now(); 
    
  //Datos Empresa
    String nickname = "NicknamePrueba4";
    String nombre = "NombrePrueba";
    String apellido = "ApellidoPrueba";
    String correo = "correoPrueba3@gmail.com";
    String nombreEmpresa = "NombreEmpresaPrueba";
    String descripcion = "DescripcionPrueba";
    String link = "LinkPrueba";
    DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, nombreEmpresa, descripcion, link);

    //Datos de tipo
    String nombreTipo2 = "Nombre2 Tipo Test ListarEmpresasOK";
    String descripcionTipo = "Descripcion Tipo Test ListarEmpresasOK";
    int exposicionTipo = 10;
    int duracionTipo = 100000;
    float costoTipo = 15.0f;
    Date fechaDeAltaTipo = new Date();
       LocalDate localDate = fechaDeAltaTipo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
       DTTipo dtTipo = new DTTipo(nombreTipo2, descripcionTipo, exposicionTipo,duracionTipo,costoTipo,localDate);

       //Datos de Keyword 
       String nombreK = "NombreKey2";
       DTKeyword dtk = new DTKeyword(nombreK);
       Map<String, DTKeyword> mapdtk = new HashMap<>();
       mapdtk.put(nombreK, dtk);

       //Datos de Oferta Laboral
       String nombreOL = "NombreOL2";
       String descripcionOL = "DescripcionOL";
       String ciudadOL = "CiudadOL";
       String departamentoOL = "DepartamentoOL";
       String horario = "HorarioOL";
       float remuneracion = 123;
       LocalDate dateOL = LocalDate.of(2023, 8, 1);
       DTOfertaLaboral dtol = new DTOfertaLaboral(nombreOL, descripcionOL, ciudadOL, departamentoOL, horario, remuneracion, dateOL, mapdtk);
  
       
       //Datos de postulante
       String nickPostu = "NickPostu30";
       String nombrePostu = "NombrePostu";
       String apellidoPostu = "ApellidoPostu";
       String correoPostu = "correoPostu";
       LocalDate fechaPostu = LocalDate.of(2023, 8, 1);
       String nacionalidadPostu = "nacionalidadPostu";          
       DTPostulante dtpos = new DTPostulante(nickPostu, nombrePostu, apellidoPostu, correoPostu, fechaPostu, nacionalidadPostu);
       
       
       
       try {
    
    	contOL.ingresarTipo(nombreTipo2, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
    	contUsuario.ingresarDatosEmpresa(emp);
    	contUsuario.ingresarDatosPostulante(dtpos);
    	contOL.ingresarDatosOL(nickname, nombreTipo2, dtol);;
        contUsuario.ingresarPostulacion(CVReducidoTest, motivacionTest, fechaTest, nickname, nombreOL, nickPostu);
     
        
        
        DTOfertaLaboral[] ofertas = null;
		try {
			ofertas = contUsuario.listarOfertasLaborales(nickname);
		} catch (OfertasLaboralesNoExistenException e) {
			// TODO Auto-generated catch block
	        fail(e.getMessage());
			e.printStackTrace();
		}
        List<DTPostulacion> postulaciones = ofertas[0].getPostulaciones();
        DTPostulacion dtpostulacion = postulaciones.get(0);

        
    }catch(ExisteUnUsuarioYaRegistradoException e) {
        fail(e.getMessage());
        e.printStackTrace();
    }catch(OfertaLaboralRepetidaException e) {
        fail(e.getMessage());
        e.printStackTrace();
    }catch(TipoRepetidoException e) {
        fail(e.getMessage());
        e.printStackTrace();
    }
       catch(YaSePostuloException e) {
        fail(e.getMessage());
        e.printStackTrace();
    };
    assertThrows(YaSePostuloException.class, ()->{contUsuario.ingresarPostulacion(CVReducidoTest, motivacionTest, fechaTest, nickname, nombreOL, nickPostu);});
}

    }