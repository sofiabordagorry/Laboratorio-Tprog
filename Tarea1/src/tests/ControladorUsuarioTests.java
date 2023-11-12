package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.EmpresaSinOfertasException;
import excepciones.EmpresasNoExistenException;
import excepciones.ExisteUnUsuarioYaRegistradoException;
import excepciones.NoExistenEmpresasOfertasLaboralesException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.OfertasLaboralesNoExistenException;
import excepciones.PaqueteRepetidoException;
import excepciones.PaqueteYaCompradoException;
import excepciones.TipoRepetidoException;
import excepciones.UsuarioSinPostulacionesException;
import excepciones.YaSePostuloException;
import logica.DTEmpresa;
import logica.DTPostulacion;
import logica.DTKeyword;
import logica.DTOfertaLaboral;
import logica.DTPaquete;
import logica.DTPaqueteTipo;
import logica.DTTipo;
import logica.DTUsuario;
import logica.EstadoOL;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.Keyword;
import logica.ManejadorUsuario;
import logica.Tipo;
import logica.DTPostulante;


import excepciones.PostulantesNoExistenException;

import excepciones.UsuariosNoExistenException;


import logica.ManejadorTipo;
import logica.ManejadorOfertaLaboral;

class ControladorUsuarioTests {
	private static IUsuario cu;
	private static IOfertaLaboral col;
	private ManejadorUsuario mu;
	private ManejadorTipo tp;
	private ManejadorOfertaLaboral mol;
	
	@BeforeAll
	public static void iniciar(){
		Factory fab = Factory.getInstance();
		cu = fab.getIUsuario(); 
		col = fab.getIOfertaLaboral();
	}
	
	@AfterEach
	public void borrarUsuarios() {
		mu = ManejadorUsuario.getInstancia();
		mol = ManejadorOfertaLaboral.getInstance();
		tp = ManejadorTipo.getInstancia();
		mu.borrarUsuarios();
		mol.limpiar();
		tp.limpiar();
	}
	
	@Test
	void testRegistrarPostulanteOK() {
		//Data postulante 
		String nickname = "NicknamePrueba";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba@gmail.com";
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, "contra", "2023-01-01", nacionalidad, null, null, null);
		
		try {
			cu.ingresarDatosPostulante(dataP);
			DTPostulante dataU =(DTPostulante) cu.mostrarInformacionUsuario(nickname);
			
			assertEquals(dataU.getNickname(), nickname);
			assertEquals(dataU.getNombre(), nombre);
			assertEquals(dataU.getApellido(), apellido);
			assertEquals(dataU.getCorreo(), correo);
			assertEquals(dataU.getFechaDeNacimiento(), "2023-01-01");
			assertEquals(dataU.getNacionalidad(), nacionalidad);
		} catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test 
	void testRegistrarPostulanteRepetido() {
		//Data postulante 
		String nickname = "NicknamePrueba2";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba2@gmail.com";
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, "contra", "2023-01-01", nacionalidad, null, null, null);
		
		try {
			cu.ingresarDatosPostulante(dataP);
		} catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(ExisteUnUsuarioYaRegistradoException.class, ()->{cu.ingresarDatosPostulante(dataP);});
	}
	
	@Test
	void testRegistrarPostulanteRepetidoMail() {
		//Data postulante 
		//String nickname = "NicknamePrueba2";
		String nickname2 = "NicknamePrueba22";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba2@gmail.com";
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname2, nombre, apellido, correo, "contra", "2023-01-01", nacionalidad, null, null, null);
		
		try {
			cu.ingresarDatosPostulante(dataP);
		} catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(ExisteUnUsuarioYaRegistradoException.class, ()->{cu.ingresarDatosPostulante(dataP);});
}
	
	@Test
	void testListarPostulanteOK() {
		String nickname = "NicknamePrueba3";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba3@gmail.com";
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, "contra", "2023-01-01", nacionalidad, null, null, null);
		try {
			cu.ingresarDatosPostulante(dataP);
			DTPostulante[] listaP = cu.listarPostulantes();
			assertEquals(listaP[0].getNickname(), nickname);
			assertEquals(listaP[0].getNombre(), nombre);
			assertEquals(listaP[0].getApellido(), apellido);
			assertEquals(listaP[0].getCorreo(), correo);
			assertEquals(listaP[0].getFechaDeNacimiento(), "2023-01-01");
			assertEquals(listaP[0].getNacionalidad(), nacionalidad);
		} catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (PostulantesNoExistenException ep) {
			fail(ep.getMessage());
			ep.printStackTrace();
		}
	}
	
	@Test
	void testListarPostulanteNoExisten() {
		assertThrows(PostulantesNoExistenException.class, ()->{cu.listarPostulantes();});
	}
	
	@Test 
	void listarEmpresasAOLOK() {
		String nickname = "NicknamePrueba3";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba3@gmail.com";
		String descripcion = "DescripcionPrueba";
		String link = "LinkPrueba";
		DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, "contra", null, descripcion, link, null, null);
		try {
			cu.ingresarDatosEmpresa(emp);
			DTEmpresa[] listaE = cu.listarEmpresasAOL();
			assertEquals(listaE[0].getNombre(), nombre);
			assertEquals(listaE[0].getNickname(), nickname);
			assertEquals(listaE[0].getApellido(), apellido);
			assertEquals(listaE[0].getCorreo(), correo);
			assertEquals(listaE[0].getDescripcion(), descripcion);
			assertEquals(listaE[0].getLink(), link);
		} catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
		} catch (EmpresasNoExistenException ee) {
			fail(ee.getMessage());
		}
	}
	
	@Test
	void listarEmpresasAOLNoExisten() {
		assertThrows(EmpresasNoExistenException.class, ()->{cu.listarEmpresasAOL();});
	}
	
	@Test 
	void listarOfertasLaboralesViegentesOK() {
		//Datos Empresa
		String nickname = "NicknamePrueba3";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba3@gmail.com";
		String descripcion = "DescripcionPrueba";
		String link = "LinkPrueba";
		DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, "contra", null, descripcion, link, null, null);
		
		//Datos de tipo
		String nombreTipo = "Nombre Tipo Test ListarEmpresasOK";
		String descripcionTipo = "Descripcion Tipo Test ListarEmpresasOK";
        int exposicionTipo = 10;
        int duracionTipo = 100000;
        float costoTipo = 15.0f;
        Date fechaDeAltaTipo = new Date();

       	//Datos de Keyword 
       	String nombreK = "NombreKey";
       	DTKeyword dtk = new DTKeyword(nombreK);
       	DTKeyword[] mapdtk = new DTKeyword[1];
       	mapdtk[0] = dtk;
       	
       	//Datos de Oferta Laboral
       	String nombreOL = "NombreOL";
       	String descripcionOL = "DescripcionOL";
       	String ciudadOL = "CiudadOL";
       	String departamentoOL = "DepartamentoOL";
       	String horario = "HorarioOL";
       	float costoAsociado = 23;
       	float remuneracion = 123;
       	DTOfertaLaboral dtol = new DTOfertaLaboral(nombreOL, descripcionOL, ciudadOL, departamentoOL, horario, remuneracion, "2023-08-01", "2023-08-01", 3, costoAsociado, null, mapdtk, null, nombre, EstadoOL.Confirmada, true);
       	
		try {
			cu.ingresarDatosEmpresa(emp);
			col.ingresarTipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
			col.ingresarDatosOL(emp.getNickname(), nombreTipo, dtol);
			DTOfertaLaboral[] o = cu.listarOfertasLaboralesVigentes(emp.getNickname());
			assertEquals(o[0].getNombre(), nombreOL);
			assertEquals(o[0].getDescripcion(), descripcionOL);
			assertEquals(o[0].getCiudad(), ciudadOL);
			assertEquals(o[0].getDepartamento(), departamentoOL);
			assertEquals(o[0].getHorario(), horario);
			assertEquals(o[0].getRemuneracion(), remuneracion);
			assertEquals(o[0].getFechaDeAlta(), "2023-08-01");
		} catch (EmpresaSinOfertasException e) {
			fail(e.getMessage());
		} catch (ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		} catch (TipoRepetidoException eee) {
			fail(eee.getMessage());
		} catch (OfertaLaboralRepetidaException eeee) {
			fail(eeee.getMessage());
		}
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
		        String descripcion = "DescripcionPrueba";
		        String link = "LinkPrueba";
		        DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, "contra", null, descripcion, link, null, null);

		        //Datos de tipo
		        String nombreTipo = "Nombre Tipo Test ListarEmpresasOK";
		        String descripcionTipo = "Descripcion Tipo Test ListarEmpresasOK";
		        int exposicionTipo = 10;
		        int duracionTipo = 100000;
		        float costoTipo = 15.0f;
		        Date fechaDeAltaTipo = new Date();

           //Datos de Keyword 
           String nombreK = "NombreKey";
           Keyword key = new Keyword(nombreK);
           mol = ManejadorOfertaLaboral.getInstance();
           mol.agregarKeyword(key);
           DTKeyword dtk = new DTKeyword(nombreK);
           DTKeyword[] mapdtk = new DTKeyword[1];
           mapdtk[0] = dtk;

           //Datos de Oferta Laboral
           String nombreOL = "NombreOL";
           String descripcionOL = "DescripcionOL";
           String ciudadOL = "CiudadOL";
           String departamentoOL = "DepartamentoOL";
           String horario = "HorarioOL";
           float remuneracion = 123;
           float costoAsociado = 234;
           DTOfertaLaboral dtol = new DTOfertaLaboral(nombreOL, descripcionOL, ciudadOL, departamentoOL, horario, remuneracion, "2023-08-01", "2023-08-01", 3, costoAsociado, null, mapdtk, null, nombre, EstadoOL.Confirmada, true);
      
           
           //Datos de postulante
           String nickPostu = "NickPostu";
           String nombrePostu = "NombrePostu";
           String apellidoPostu = "ApellidoPostu";
           String correoPostu = "correoPostu";
           String nacionalidadPostu = "nacionalidadPostu";           
           DTPostulante dtpos = new DTPostulante(nickPostu, nombrePostu, apellidoPostu, correoPostu, "contra", "2023-01-01", nacionalidadPostu, null, null, null);
           
           
           
           try {
        
        	col.ingresarTipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
        	cu.ingresarDatosEmpresa(emp);
        	cu.ingresarDatosPostulante(dtpos);
        	col.ingresarDatosOL(nickname, nombreTipo, dtol);;
            cu.ingresarPostulacion(CVReducidoTest, motivacionTest, fechaTest, nickname, nombreOL, nickPostu, "video");
         
            DTOfertaLaboral[] ofertas = null;
			try {
				ofertas = cu.listarOfertasLaborales(nickname);
			} catch (OfertasLaboralesNoExistenException e) {
				// TODO Auto-generated catch block
	            fail(e.getMessage());
				e.printStackTrace();
			}
            List<DTPostulacion> postulaciones = ofertas[0].getPostulaciones();
            DTPostulacion dtpostulacion = postulaciones.get(0);
            String dateString = dtpostulacion.getFecha();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dateLocalDate = LocalDate.parse(dateString, formatter);
            assertEquals(dtpostulacion.getCVReducido(), CVReducidoTest);
            assertEquals(dtpostulacion.getMotivacion(), motivacionTest);
            assertEquals(dateLocalDate, fechaTest);
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
    String descripcion = "DescripcionPrueba";
    String link = "LinkPrueba";
    DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, "contra", null, descripcion, link, null, null);

    //Datos de tipo
    String nombreTipo2 = "Nombre2 Tipo Test ListarEmpresasOK";
    String descripcionTipo = "Descripcion Tipo Test ListarEmpresasOK";
    int exposicionTipo = 10;
    int duracionTipo = 100000;
    float costoTipo = 15.0f;
    Date fechaDeAltaTipo = new Date();

       //Datos de Keyword 
       String nombreK = "NombreKey2";
       Keyword key = new Keyword(nombreK);
       mol = ManejadorOfertaLaboral.getInstance();
       mol.agregarKeyword(key);
       DTKeyword dtk = new DTKeyword(nombreK);
       DTKeyword[] mapdtk = new DTKeyword[1];
       mapdtk[0] = dtk;

       //Datos de Oferta Laboral
       String nombreOL = "NombreOL2";
       String descripcionOL = "DescripcionOL";
       String ciudadOL = "CiudadOL";
       String departamentoOL = "DepartamentoOL";
       String horario = "HorarioOL";
       float remuneracion = 123;
       float costoAsociado = 23;
       DTOfertaLaboral dtol = new DTOfertaLaboral(nombreOL, descripcionOL, ciudadOL, departamentoOL, horario, remuneracion, "2023-08-01", "2023-08-01", 3, costoAsociado, null, mapdtk, null, nombre, EstadoOL.Confirmada, true);
  
       
       //Datos de postulante
       String nickPostu = "NickPostu30";
       String nombrePostu = "NombrePostu";
       String apellidoPostu = "ApellidoPostu";
       String correoPostu = "correoPostu";
       String nacionalidadPostu = "nacionalidadPostu";          
       DTPostulante dtpos = new DTPostulante(nickPostu, nombrePostu, apellidoPostu, correoPostu, "contra", "2023-08-01", nacionalidadPostu, null, null, null);
       
       
       
       try {
    
    	col.ingresarTipo(nombreTipo2, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
    	cu.ingresarDatosEmpresa(emp);
    	cu.ingresarDatosPostulante(dtpos);
    	col.ingresarDatosOL(nickname, nombreTipo2, dtol);;
        cu.ingresarPostulacion(CVReducidoTest, motivacionTest, fechaTest, nickname, nombreOL, nickPostu, "video");
     
        
        
		try {
			cu.listarOfertasLaborales(nickname);
		} catch (OfertasLaboralesNoExistenException e) {
			// TODO Auto-generated catch block
	        fail(e.getMessage());
			e.printStackTrace();
		}

        
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
    assertThrows(YaSePostuloException.class, ()->{cu.ingresarPostulacion(CVReducidoTest, motivacionTest, fechaTest, nickname, nombreOL, nickPostu, "video");});
}

	@Test 
	void listarOfertasLaboralesViegentesNoExisten() {
		//Datos Empresa
		String nickname = "NicknamePrueba3";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba3@gmail.com";
		String descripcion = "DescripcionPrueba";
		String link = "LinkPrueba";
		DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, "contra", null, descripcion, link, null, null);
		
       	try {
			cu.ingresarDatosEmpresa(emp);
		} catch (ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		} 
       	assertThrows(EmpresaSinOfertasException.class, ()->{cu.listarOfertasLaboralesVigentes(emp.getNickname());});
	}
	
	@Test
	void listarUsuariosOKEmpresa() {
		//Datos Empresa
		String nickname = "NicknamePrueba3";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba3@gmail.com";
		String descripcion = "DescripcionPrueba";
		String link = "LinkPrueba";
		DTEmpresa emp = new DTEmpresa(nickname, nombre, apellido, correo, "contra", null, descripcion, link, null, null);
		try {
			cu.ingresarDatosEmpresa(emp);
			DTUsuario[] usu = new DTEmpresa[1];
			usu = cu.listarUsuarios();
			assertEquals(usu[0].getNickname(), nickname);
			assertEquals(usu[0].getNombre(), nombre);
			assertEquals(usu[0].getApellido(), apellido);
			assertEquals(usu[0].getCorreo(), correo);
		} catch (ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		} catch(UsuariosNoExistenException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	void listarUsuariosOKPostulante() {
		//Data postulante 
		String nickname = "NicknamePrueba";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba@gmail.com";
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, "contra", "2023-01-01", nacionalidad, null, null, null);
		try {
			cu.ingresarDatosPostulante(dataP);
			DTUsuario[] usu = cu.listarUsuarios();
			assertEquals(usu[0].getNickname(), nickname);
			assertEquals(usu[0].getNombre(), nombre);
			assertEquals(usu[0].getApellido(), apellido);
			assertEquals(usu[0].getCorreo(), correo);
		} catch (ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		} catch(UsuariosNoExistenException e) {
			fail(e.getMessage());
		}
	}
	
	@Test 
	void listarUsuariosOKPostEmp() {
		//Data postulante 
		String nickname = "NicknamePrueba";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba@gmail.com";
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, "contra", "2023-01-01", nacionalidad, null, null, null);
		
		//Datos Empresa
		String nicknameE = "NicknamePrueba3";
		String nombreE = "NombrePrueba";
		String apellidoE = "ApellidoPrueba";
		String correoE = "correoPrueba3@gmail.com";
		String descripcion = "DescripcionPrueba";
		String link = "LinkPrueba";
		DTEmpresa emp = new DTEmpresa(nicknameE, nombreE, apellidoE, correoE, "contra", null, descripcion, link, null, null);
		try {
			cu.ingresarDatosEmpresa(emp);
			cu.ingresarDatosPostulante(dataP);
			DTUsuario[] usu = cu.listarUsuarios();
			assertEquals(usu[0].getNickname(), nicknameE);
			assertEquals(usu[0].getNombre(), nombreE);
			assertEquals(usu[0].getApellido(), apellidoE);
			assertEquals(usu[0].getCorreo(), correoE);
			assertEquals(usu[1].getNickname(), nickname);
			assertEquals(usu[1].getNombre(), nombre);
			assertEquals(usu[1].getApellido(), apellido);
			assertEquals(usu[1].getCorreo(), correo);
		} catch (ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		} catch (UsuariosNoExistenException e) {
			fail(e.getMessage());
		}
	}
	
	@Test 
	void listarUsuariosNoExistentes() {
		assertThrows(UsuariosNoExistenException.class, ()->{cu.listarUsuarios();});
	}
	
	@Test 
	public void modificarPostulante() {
		//Data postulante 
		String nickname = "NicknamePrueba";
		String nombre = "NombrePrueba";
		String apellido = "ApellidoPrueba";
		String correo = "correoPrueba@gmail.com";
		LocalDate fechaDeNacimiento = LocalDate.of(2023, 1, 1);
		String nacionalidad = "Uruguay";
		DTPostulante dataP = new DTPostulante(nickname, nombre, apellido, correo, "contra", "2023-01-01", nacionalidad, null, null, null);
		try {
			cu.ingresarDatosPostulante(dataP);
		} catch(ExisteUnUsuarioYaRegistradoException ee) {
			fail(ee.getMessage());
		}
		cu.modificarPostulante(nickname, "nom", "ape", fechaDeNacimiento, "China");
		DTPostulante dtp = (DTPostulante) cu.mostrarInformacionUsuario(nickname);
		assertEquals(dtp.getNombre(), "nom");
		assertEquals(dtp.getApellido(), "ape");
		assertEquals(dtp.getFechaDeNacimiento(), "2023-01-01");
		assertEquals(dtp.getNacionalidad(), "China");
	}

	@Test
	void testRegistrarEmpresaOK() {
		String nicknameTest = "Nickname Test RegUsuOK";
		String nombreTest = "Nombre Test RegUsuOK";
		String apellidoTest = "Apellido Test";
		String correoTest = "Correo Test RegUsuOK";
		DTOfertaLaboral[] ofertasLaboralesTest = new DTOfertaLaboral[0];
		String descripcionTest = "Descripcion Test RegUsuOK";
		String linkTest = "Link Test RegUsuOK";
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, "contra", ofertasLaboralesTest, descripcionTest, linkTest, null, null);
		try {
			cu.ingresarDatosEmpresa(uEmp);
			DTUsuario du = cu.mostrarInformacionUsuario(nicknameTest);
			DTEmpresa de = (DTEmpresa) du;
			
			assertEquals(de.getNickname(), nicknameTest);
			assertEquals(de.getNombre(), nombreTest);
			assertEquals(de.getApellido(), apellidoTest);
			assertEquals(de.getCorreo(), correoTest);
			assertEquals(de.getDTOfertasLaborales().length, ofertasLaboralesTest.length);
			assertEquals(de.getDescripcion(), descripcionTest);
			assertEquals(de.getLink(), linkTest);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		};
	}
	
	@Test
	void testRegistrarUsuarioRepetido (){
		String nicknameTest = "Nickname Test RegUsuRepe";
		String nombreTest = "Nombre Test RegUsuRepe";
		String apellidoTest = "Apellido Test RegUsuRepe";
		String correoTest = "Correo Test RegUsuRepe";
		DTOfertaLaboral[] ofertasLaboralesTest = new DTOfertaLaboral[1];
		String descripcionTest = "Descripcion Test RegUsuRepe";
		String linkTest = "Link Test RegUsuRepe";
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, "contra", ofertasLaboralesTest, descripcionTest, linkTest, null, null);
		try {
			cu.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e){
			fail(e.getMessage());
			e.printStackTrace();
		};
		assertThrows(ExisteUnUsuarioYaRegistradoException.class, ()->{cu.ingresarDatosEmpresa(uEmp);});
	}
	
	@Test
	void testListarEmpresasOK() {
		String nicknameTest = "Nickname Test ListarEmpresasOK";
		String nombreTest = "Nombre Test ListarEmpresasOK";
		String apellidoTest = "Apellido Test ListarEmpresasOK";
		String correoTest = "Correo Test ListarEmpresasOK";
		DTOfertaLaboral[] ofertasLaboralesTest = new DTOfertaLaboral[0];
		String descripcionTest = "Descripcion Test ListarEmpresasOK";
		String linkTest = "Link Test ListarEmpresasOK";
		DTEmpresa[] empresas = new DTEmpresa[1];
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, "contra", ofertasLaboralesTest, descripcionTest, linkTest, null, null);
		empresas[0] = uEmp;
		
		String nombreTipo = "Nombre Tipo Test ListarEmpresasOK";
		String descripcionTipo = "Descripcion Tipo Test ListarEmpresasOK";
		int exposicionTipo = 10;
		int duracionTipo = 10;
		float costoTipo = 15.0f;
		Date fechaDeAltaTipo = new Date();
		//LocalDate localDate = fechaDeAltaTipo.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		//DTTipo dtTipo = new DTTipo(nombreTipo, descripcionTipo, exposicionTipo,duracionTipo,costoTipo,localDate);
		
		String nombreOL = "Nombre Oferta Laboral ListarEmpresasOK";
		String descripcionOL = "Descripcion OfertaLaboral ListarEmpresasOK";
		String ciudadOL = "Ciudad Oferta Laboral ListarEmpresasOK";
		String departamentoOL = "Departamento Oferta Laboral ListarEmpresasOK";
		String horarioOL ="Horario Oferta Laboral ListarEmpresasOK";
		float remuneracionOL = 180.7f;
		DTKeyword[] k = new DTKeyword[0];
		float costoOL = 15.0f;
		DTOfertaLaboral olNueva = new DTOfertaLaboral(nombreOL, descripcionOL, ciudadOL, departamentoOL, horarioOL, remuneracionOL, "2023-08-01", "2023-08-01", 3, costoOL, null, k, null, "emp", EstadoOL.Confirmada, true);
		ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		try {
			cu.ingresarDatosEmpresa(uEmp);
			col.ingresarTipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
			col.ingresarDatosOL(nicknameTest,nombreTipo, olNueva);
			int cantEmpresas = mu.getEmpresas().length;
			DTEmpresa[] emps = new DTEmpresa[cantEmpresas];
			emps = cu.listarEmpresas();
			assertEquals(emps[0].getNickname(),empresas[0].getNickname());
			assertEquals(emps[0].getNombre(), empresas[0].getNombre());
			assertEquals(emps[0].getApellido(), empresas[0].getApellido());
			assertEquals(emps[0].getCorreo(), empresas[0].getCorreo());
			
			DTOfertaLaboral dt1 = emps[0].getDTOfertasLaborales()[0];
			
			assertEquals(dt1.getCiudad(), ciudadOL);
			assertEquals(dt1.getCostoAsociado(), costoOL);
			assertEquals(dt1.getDepartamento(), departamentoOL);
			assertEquals(dt1.getDescripcion(), descripcionOL);
			assertEquals(dt1.getFechaDeAlta(), "2023-08-01");
			assertEquals(dt1.getHorario(), horarioOL);
			assertEquals(dt1.getNombre(), nombreOL);
			assertEquals(dt1.getRemuneracion(), remuneracionOL);

			assertEquals(emps[0].getDescripcion(), empresas[0].getDescripcion());
			assertEquals(emps[0].getLink(), empresas[0].getLink());
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch(EmpresasNoExistenException e){
			fail(e.getMessage());
			e.printStackTrace();
		}catch(NoExistenEmpresasOfertasLaboralesException e){
			fail(e.getMessage());
			e.printStackTrace();
		}catch (TipoRepetidoException e){
			fail(e.getMessage());
			e.printStackTrace();
		}catch(OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		};
	}

	@Test
	void TestListarEmpresasNoHayEmpresa() {
		assertThrows(EmpresasNoExistenException.class, ()->{cu.listarEmpresas();});
	}
	
	@Test
	void TestListarEmpresasNoHayOfertasLaborales() {
		String nicknameTest = "Nickname Test ListarEmpresasOK";
		String nombreTest = "Nombre Test ListarEmpresasOK";
		String apellidoTest = "Apellido Test ListarEmpresasOK";
		String correoTest = "Correo Test ListarEmpresasOK";
		DTOfertaLaboral[] ofertasLaboralesTest = new DTOfertaLaboral[0];
		String descripcionTest = "Descripcion Test ListarEmpresasOK";
		String linkTest = "Link Test ListarEmpresasOK";
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, "correo", ofertasLaboralesTest, descripcionTest, linkTest, null, null);
		//ManejadorUsuario mu = ManejadorUsuario.getInstancia();
		try {
			//int cantEmpresas = mu.getEmpresas().length;
			cu.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

			assertThrows(NoExistenEmpresasOfertasLaboralesException.class, ()->{cu.listarEmpresas();});
	}
	
	@Test
	void listarOfertasLaboralesOK() {
		String nicknameTest = "Nickname Test ListarEmpresasOK";
		String nombreTest = "Nombre Test ListarEmpresasOK";
		String apellidoTest = "Apellido Test ListarEmpresasOK";
		String correoTest = "Correo Test ListarEmpresasOK";
		DTOfertaLaboral[] ofertasLaboralesTest = new DTOfertaLaboral[0];
		String descripcionTest = "Descripcion Test ListarEmpresasOK";
		String linkTest = "Link Test ListarEmpresasOK";
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, "contra", ofertasLaboralesTest, descripcionTest, linkTest, null, null);
		
		String nombreTipo = "Nombre Tipo Test ListarEmpresasOK";
		String descripcionTipo = "Descripcion Tipo Test ListarEmpresasOK";
		int exposicionTipo = 10;
		int duracionTipo = 10;
		float costoTipo = 15.0f;
		Date fechaDeAltaTipo = new Date();
		
		String nombreOL = "Nombre Oferta Laboral ListarEmpresasOK";
		String descripcionOL = "Descripcion OfertaLaboral ListarEmpresasOK";
		String ciudadOL = "Ciudad Oferta Laboral ListarEmpresasOK";
		String departamentoOL = "Departamento Oferta Laboral ListarEmpresasOK";
		String horarioOL ="Horario Oferta Laboral ListarEmpresasOK";
		float remuneracionOL = 180.7f;
		float costoOL = 15.0f;
		DTOfertaLaboral olNueva = new DTOfertaLaboral(nombreOL, descripcionOL, ciudadOL, departamentoOL, horarioOL, remuneracionOL, "2023-08-01", "2023-08-01", 3, costoOL, null, new DTKeyword[0], null, "emp", EstadoOL.Confirmada, true);
		
		try {
			cu.ingresarDatosEmpresa(uEmp);
			col.ingresarTipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaDeAltaTipo);
			col.ingresarDatosOL(nicknameTest,nombreTipo, olNueva);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch(OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch(TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		};
		try {
			DTOfertaLaboral[] dtOL = new DTOfertaLaboral[1];
 			dtOL = cu.listarOfertasLaborales(nicknameTest);
 			assertEquals(dtOL[0].getNombre(), nombreOL);
 			assertEquals(dtOL[0].getDescripcion(), descripcionOL);
 			assertEquals(dtOL[0].getCiudad(), ciudadOL);
 			assertEquals(dtOL[0].getDepartamento(), departamentoOL);
 			assertEquals(dtOL[0].getHorario(), horarioOL);
 			assertEquals(dtOL[0].getRemuneracion(),remuneracionOL);
 			assertEquals(dtOL[0].getFechaDeAlta(), "2023-08-01");
 			assertEquals(dtOL[0].getCostoAsociado(), costoOL);
		}catch(OfertasLaboralesNoExistenException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test 
	void listarOfertasLaboralesNoHayOfertas(){
		String nicknameTest = "Nickname Test ListarEmpresasOK";
		String nombreTest = "Nombre Test ListarEmpresasOK";
		String apellidoTest = "Apellido Test ListarEmpresasOK";
		String correoTest = "Correo Test ListarEmpresasOK";
		DTOfertaLaboral[] ofertasLaboralesTest = new DTOfertaLaboral[0];
		String descripcionTest = "Descripcion Test ListarEmpresasOK";
		String linkTest = "Link Test ListarEmpresasOK";
		DTEmpresa uEmp = new DTEmpresa(nicknameTest, nombreTest, apellidoTest, correoTest, "contra", ofertasLaboralesTest, descripcionTest, linkTest, null, null);
		

		try {
			cu.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(OfertasLaboralesNoExistenException.class, ()->{cu.listarOfertasLaborales(nicknameTest);});
	}
	
	@Test
	void modificarEmpresaOK() {
		String nicknameViejo = "NicknameViejo";
		String nombreViejo = "NombreViejo";
		String apellidoViejo = "ApellidoViejo";
		String correoViejo = "CorreoViejo";
		DTOfertaLaboral[] ofertasLaboralesViejo = new DTOfertaLaboral[0];
		String descripcionViejo = "DescripcionVieja";
		String linkViejo = "LinkViejo";
		//DTEmpresa[] empresas = new DTEmpresa[1];
		DTEmpresa uEmp = new DTEmpresa(nicknameViejo, nombreViejo, apellidoViejo, correoViejo, "contra", ofertasLaboralesViejo, descripcionViejo, linkViejo, null, null);
		
		String nombreNuevo = "NombreNuevo";
		String apellidoNuevo = "ApellidoNuevo";
		String descripcionNuevo = "DescripcionNuevo";
		String linkNuevo = "LinkNuevo";
		
		try {
			cu.ingresarDatosEmpresa(uEmp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		cu.modificarEmpresa(nicknameViejo, nombreNuevo, apellidoNuevo, descripcionNuevo, linkNuevo);
		
		DTUsuario du = cu.mostrarInformacionUsuario(nicknameViejo);
		DTEmpresa de = (DTEmpresa) du;
		assertEquals(de.getNombre(), nombreNuevo);
		assertEquals(de.getApellido(), apellidoNuevo);
		assertEquals(de.getDescripcion(), descripcionNuevo);
		assertEquals(de.getLink(), linkNuevo);
	}
	
	@Test
	void listarOfertasPostuladoOK() {
		//POSTULANTE
		String nicknamePostulante = "postulante";
		String nombrePostulante = "nombre";
		String apellidoPostulante = "apellido";
		String correoPostulante = "correo";
		String nacionalidadPostulante = "nacionalidad";
		DTPostulante post = new DTPostulante(nicknamePostulante, nombrePostulante, apellidoPostulante, correoPostulante, "contra", "2023-01-20", nacionalidadPostulante, null, null, null);
		//EMPRESA
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosPostulante(post);
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			e.printStackTrace();		
		}
		
		//TIPO
		String nombreTipo = "nombreTipo";
		int exposicionTipo = 3;
		int duracionTipo = 15;
		Float costoTipo = 330f;
		LocalDate fechaAlta = LocalDate.now();
		Date fechaAltaTipo = Date.from(fechaAlta.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Tipo t = new Tipo(nombreTipo, descripcionEmpresa, exposicionTipo, duracionTipo, costoTipo, fechaAlta);
		try {
			col.ingresarTipo(nombreTipo, descripcionEmpresa, exposicionTipo, duracionTipo, costoTipo, fechaAltaTipo);
		} catch (TipoRepetidoException e) {
			e.printStackTrace();
		}
		
		String nombreOferta = "nomOferta";
		String descripcionOferta = "descOferta";
		String ciudadOferta = "ciudadOferta";
		String departamentoOferta = "departamentoOferta";
		String horarioOferta = "10:00-15:30";
		float remuneracionOferta = 8447f;
		float costoOferta = 330f;
		DTTipo tipoOferta = t.getDataTipo();
		EstadoOL estadoOferta = EstadoOL.Confirmada;
		DTOfertaLaboral ofLab = new DTOfertaLaboral(nombreOferta, descripcionOferta, ciudadOferta, departamentoOferta, horarioOferta, remuneracionOferta, "2023-08-01", "2023-08-01", 3, costoOferta, tipoOferta, new DTKeyword[0], null,nicknameEmpresa, estadoOferta, true);
		try {
			col.ingresarDatosOL(nicknameEmpresa, nombreTipo, ofLab);
		} catch (OfertaLaboralRepetidaException e) {
			e.printStackTrace();
		}
		
		String cvReducido = "CVReducido";
		String motivacion = "Motivacion";
		LocalDate fechaPostulacion = LocalDate.now();
		try {
			cu.ingresarPostulacion(cvReducido, motivacion, fechaPostulacion, nicknameEmpresa, nombreOferta, nicknamePostulante, "video");
		} catch (YaSePostuloException e) {
			e.printStackTrace();
		}

		try {
			DTOfertaLaboral[] offers = cu.listarOfertasPostulado(nicknamePostulante);
			assertEquals(offers[0].getCiudad(), ciudadOferta);
			assertEquals(offers[0].getCostoAsociado(), costoOferta);
			assertEquals(offers[0].getDepartamento(), departamentoOferta);
			assertEquals(offers[0].getDescripcion(), descripcionOferta);
			//assertEquals(offers[0].getEstado(), estadoOferta);
			assertEquals(offers[0].getFechaDeAlta(), "2023-08-01");
			assertEquals(offers[0].getHorario(), horarioOferta);
			assertEquals(offers[0].getNombre(), nombreOferta);
			assertEquals(offers[0].getRemuneracion(), remuneracionOferta);
		} catch (UsuarioSinPostulacionesException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void listarOfertasLaboralesIngresadasOK() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//Tipo
		String nombreTipo = "nombreTipo";
		int exposicionTipo = 3;
		int duracionTipo = 15;
		Float costoTipo = 330f;
		LocalDate fechaAlta = LocalDate.now();
		Date fechaAltaTipo = Date.from(fechaAlta.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Tipo t = new Tipo(nombreTipo, descripcionEmpresa, exposicionTipo, duracionTipo, costoTipo, fechaAlta);
		try {
			col.ingresarTipo(nombreTipo, descripcionEmpresa, exposicionTipo, duracionTipo, costoTipo, fechaAltaTipo);
		} catch (TipoRepetidoException e) {
			e.printStackTrace();
		}
		
		//OfertaLaboral
		String nombreOferta = "nomOferta";
		String descripcionOferta = "descOferta";
		String ciudadOferta = "ciudadOferta";
		String departamentoOferta = "departamentoOferta";
		String horarioOferta = "10:00-15:30";
		float remuneracionOferta = 8447f;
		float costoOferta = 330f;
		DTTipo tipoOferta = t.getDataTipo();
		EstadoOL estadoOferta = EstadoOL.Confirmada;
		DTOfertaLaboral ofLab = new DTOfertaLaboral(nombreOferta, descripcionOferta, ciudadOferta, departamentoOferta, horarioOferta, remuneracionOferta, "2023-08-01", "2023-08-01", 3, costoOferta, tipoOferta, new DTKeyword[0], null,nicknameEmpresa, estadoOferta, true);
		try {
			col.ingresarDatosOL(nicknameEmpresa, nombreTipo, ofLab);
		} catch (OfertaLaboralRepetidaException e) {
			e.printStackTrace();
		}
		
		try {
			cu.listarOfertasLaboralesIngresadas(nicknameEmpresa);
		} catch(EmpresaSinOfertasException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void listarOfertasLaboralesIngresadasNiguna() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		assertThrows(EmpresaSinOfertasException.class, ()->{cu.listarOfertasLaboralesIngresadas(nicknameEmpresa);});
	}
	
	@Test
	void buscarEmpresa() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		cu.buscarEmpresa(nicknameEmpresa);
		cu.buscarEmpresa("hola");
	}
	
	@Test
	void buscarDTUsuario() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//Postulante
		String nicknamePostulante = "postulante";
		String nombrePostulante = "nombre";
		String apellidoPostulante = "apellido";
		String correoPostulante = "correo";
		String nacionalidadPostulante = "nacionalidad";
		DTPostulante post = new DTPostulante(nicknamePostulante, nombrePostulante, apellidoPostulante, correoPostulante, "contra", "2023-01-20", nacionalidadPostulante, null, null, null);
		
		try {
			cu.ingresarDatosPostulante(post);
		} catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		DTUsuario hayEmp = cu.buscarDTUsuario(nicknameEmpresa);
		DTEmpresa empHay = (DTEmpresa) hayEmp;
		assertEquals(nicknameEmpresa, empHay.getNickname());
		assertEquals(nombreEmpresa, empHay.getNombre());
		assertEquals(apellidoEmpresa, empHay.getApellido());
		assertEquals(correoEmpresa, empHay.getCorreo());
		assertEquals(descripcionEmpresa, empHay.getDescripcion());
		assertEquals(linkEmpresa, empHay.getLink());
		DTUsuario hayPost = cu.buscarDTUsuario(nicknamePostulante);
		DTPostulante postHay = (DTPostulante) hayPost;
		assertEquals(nicknamePostulante, postHay.getNickname());
		assertEquals(nombrePostulante, postHay.getNombre());
		assertEquals(apellidoPostulante, postHay.getApellido());
		assertEquals(correoPostulante, postHay.getCorreo());
		assertEquals(nacionalidadPostulante, postHay.getNacionalidad());
		cu.buscarDTUsuario("hola");
	}
	
	@Test 
	void buscarDTUsuarioPorMail() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		DTUsuario hayUser = cu.buscarDTUsuarioPorMail(correoEmpresa);
		DTEmpresa empHay = (DTEmpresa) hayUser;
		assertEquals(nicknameEmpresa, empHay.getNickname());
		assertEquals(nombreEmpresa, empHay.getNombre());
		assertEquals(apellidoEmpresa, empHay.getApellido());
		assertEquals(correoEmpresa, empHay.getCorreo());
		assertEquals(descripcionEmpresa, empHay.getDescripcion());
		assertEquals(linkEmpresa, empHay.getLink());
		cu.buscarDTUsuarioPorMail("hola");
	}

	@Test 
	void verificacionDePostulantePostulacion() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//Postulante
		String nicknamePostulante = "postulante";
		String nombrePostulante = "nombre";
		String apellidoPostulante = "apellido";
		String correoPostulante = "correo";
		String nacionalidadPostulante = "nacionalidad";
		DTPostulante post = new DTPostulante(nicknamePostulante, nombrePostulante, apellidoPostulante, correoPostulante, "contra", "2023-01-20", nacionalidadPostulante, null, null, null);
		
		try {
			cu.ingresarDatosPostulante(post);
		} catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//Tipo
		String nombreTipo = "nombreTipo";
		int exposicionTipo = 3;
		int duracionTipo = 15;
		Float costoTipo = 330f;
		LocalDate fechaAlta = LocalDate.now();
		Date fechaAltaTipo = Date.from(fechaAlta.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Tipo t = new Tipo(nombreTipo, descripcionEmpresa, exposicionTipo, duracionTipo, costoTipo, fechaAlta);
		try {
			col.ingresarTipo(nombreTipo, descripcionEmpresa, exposicionTipo, duracionTipo, costoTipo, fechaAltaTipo);
		} catch (TipoRepetidoException e) {
			e.printStackTrace();
		}
		
		//OfertaLaboral
		String nombreOferta = "nomOferta";
		String descripcionOferta = "descOferta";
		String ciudadOferta = "ciudadOferta";
		String departamentoOferta = "departamentoOferta";
		String horarioOferta = "10:00-15:30";
		float remuneracionOferta = 8447f;
		float costoOferta = 330f;
		DTTipo tipoOferta = t.getDataTipo();
		EstadoOL estadoOferta = EstadoOL.Confirmada;
		DTOfertaLaboral ofLab = new DTOfertaLaboral(nombreOferta, descripcionOferta, ciudadOferta, departamentoOferta, horarioOferta, remuneracionOferta, "2023-08-01", "2023-08-01", 3, costoOferta, tipoOferta, new DTKeyword[0], null,nicknameEmpresa, estadoOferta, true);
		try {
			col.ingresarDatosOL(nicknameEmpresa, nombreTipo, ofLab);
		} catch (OfertaLaboralRepetidaException e) {
			e.printStackTrace();
		}
		
		try {
			cu.ingresarPostulacion("CV", "Motivacion", fechaAlta, nicknameEmpresa, nombreOferta, nicknamePostulante, "video");
		} catch (YaSePostuloException e) {
			e.printStackTrace();
		}
		
		cu.verificacionDePostulantePostulacion(nicknamePostulante, nombreOferta, nicknameEmpresa);
	}
	
	@Test
	void dataPostulante() {
		//Postulante
		String nicknamePostulante = "postulante";
		String nombrePostulante = "nombre";
		String apellidoPostulante = "apellido";
		String correoPostulante = "correo";
		String nacionalidadPostulante = "nacionalidad";
		DTPostulante post = new DTPostulante(nicknamePostulante, nombrePostulante, apellidoPostulante, correoPostulante, "contra", "2023-01-20", nacionalidadPostulante, null, null, null);
		
		try {
			cu.ingresarDatosPostulante(post);
		} catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		DTPostulante dataPost = cu.dataPostulante(nicknamePostulante);
		assertEquals(dataPost.getNickname(), nicknamePostulante);
		assertEquals(dataPost.getNombre(), nombrePostulante);
		assertEquals(dataPost.getApellido(), apellidoPostulante);
		assertEquals(dataPost.getContrasenia(), "contra");
		assertEquals(dataPost.getCorreo(), correoPostulante);
		assertEquals(dataPost.getNacionalidad(), nacionalidadPostulante);
		assertEquals(dataPost.getFechaDeNacimiento(), "2023-01-20");
	}

	@Test 
	void verificacionCompraPaq() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//Tipo
		String nombreTipo = "nombreTipo";
		String descripcionTipo = "descTipo";
		int exposicionTipo = 3;
		int duracionTipo = 15;
		Float costoTipo = 330f;
		LocalDate fechaAlta = LocalDate.now();
		Date fechaAltaTipo = Date.from(fechaAlta.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Tipo t = new Tipo(nombreTipo, descripcionTipo, exposicionTipo, duracionTipo, costoTipo, fechaAlta);
		try {
			col.ingresarTipo(nombreTipo, descripcionEmpresa, exposicionTipo, duracionTipo, costoTipo, fechaAltaTipo);
		} catch (TipoRepetidoException e) {
			e.printStackTrace();
		}
		
		//PaqueteTipo
		int cantidadPaqTip = 3;
		DTPaqueteTipo paqTip = new DTPaqueteTipo(cantidadPaqTip, t.getDataTipo());
		
		//Paquete
		String nombrePaq = "nombrePaq";
		String descripcionPaq = "descripcionPaq";
		int periodoDeValidezPaq = 3;
		float descuentoPaq = 20;
		float costoAsociadoPaq = 20;
		String fechaDeAlta = "20-03-2023";
		DTPaqueteTipo[] paqTipos = new DTPaqueteTipo[1];
		paqTipos[0] = paqTip;
		DTPaquete paquete = new DTPaquete(nombrePaq, descripcionPaq, periodoDeValidezPaq, descuentoPaq, costoAsociadoPaq, paqTipos, fechaDeAlta);;
		
		try {
			col.ingresarDatosPaquete(paquete);
			col.comprarPaquete(nicknameEmpresa, nombrePaq);
		} catch (PaqueteRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (PaqueteYaCompradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		 
		cu.verificacionCompraPaq(nicknameEmpresa, nombrePaq);
	}
	
	@Test 
	void estaSiguiendo() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//Postulante
		String nicknamePostulante = "postulante";
		String nombrePostulante = "nombre";
		String apellidoPostulante = "apellido";
		String correoPostulante = "correo";
		String nacionalidadPostulante = "nacionalidad";
		DTPostulante post = new DTPostulante(nicknamePostulante, nombrePostulante, apellidoPostulante, correoPostulante, "contra", "2023-01-20", nacionalidadPostulante, null, null, null);
		
		try {
			cu.ingresarDatosPostulante(post);
		} catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		cu.seguirUsuario(nicknamePostulante, nicknameEmpresa);
		cu.estaSiguiendo(nicknamePostulante, nicknameEmpresa);
		cu.dejarSeguimiento(nicknamePostulante, nicknameEmpresa);
	}
	
	@Test
	void crearDTPostulante() {
		String nicknamePostulante = "postulante";
		String nombrePostulante = "nombre";
		String apellidoPostulante = "apellido";
		String correoPostulante = "correo";
		String nacionalidadPostulante = "nacionalidad";
		String contraseniaPostulante = "contra";
		String fechaDeNacimiento = "2023-12-12";
		DTPostulante dtPost = cu.crearDTPostulante(nicknamePostulante, nombrePostulante, apellidoPostulante, correoPostulante, contraseniaPostulante, nacionalidadPostulante, fechaDeNacimiento, null);
		
		assertEquals(dtPost.getNickname(), nicknamePostulante);
		assertEquals(dtPost.getNombre(), nombrePostulante);
		assertEquals(dtPost.getApellido(), apellidoPostulante);
		assertEquals(dtPost.getNacionalidad(), nacionalidadPostulante);
		assertEquals(dtPost.getCorreo(), correoPostulante);
		assertEquals(dtPost.getContrasenia(), contraseniaPostulante);
		assertEquals(dtPost.getFechaDeNacimiento(), fechaDeNacimiento);
	}
	
	@Test
	void crearDTEmpresa() {
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		String contraseniaEmpresa = "contra";
		DTEmpresa dtEmp = cu.crearDTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, contraseniaEmpresa, descripcionEmpresa, linkEmpresa, null);
		
		assertEquals(dtEmp.getNickname(), nicknameEmpresa);
		assertEquals(dtEmp.getNombre(), nombreEmpresa);
		assertEquals(dtEmp.getApellido(), apellidoEmpresa);
		assertEquals(dtEmp.getContrasenia(), contraseniaEmpresa);
		assertEquals(dtEmp.getCorreo(), correoEmpresa);
		assertEquals(dtEmp.getDescripcion(), descripcionEmpresa);
		assertEquals(dtEmp.getLink(), linkEmpresa);
	}
	
	@Test 
	void obtenerSeguidoeres() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//Postulante
		String nicknamePostulante = "postulante";
		String nombrePostulante = "nombre";
		String apellidoPostulante = "apellido";
		String correoPostulante = "correo";
		String nacionalidadPostulante = "nacionalidad";
		DTPostulante post = new DTPostulante(nicknamePostulante, nombrePostulante, apellidoPostulante, correoPostulante, "contra", "2023-01-20", nacionalidadPostulante, null, null, null);
		
		try {
			cu.ingresarDatosPostulante(post);
		} catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		cu.seguirUsuario(nicknamePostulante, nicknameEmpresa);
		String[] seguidores = cu.obtenerSeguidores(nicknameEmpresa);
		assertEquals(seguidores[0], nicknamePostulante);
	}
	
	@Test
	void obtenerSeguidos() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//Postulante
		String nicknamePostulante = "postulante";
		String nombrePostulante = "nombre";
		String apellidoPostulante = "apellido";
		String correoPostulante = "correo";
		String nacionalidadPostulante = "nacionalidad";
		DTPostulante post = new DTPostulante(nicknamePostulante, nombrePostulante, apellidoPostulante, correoPostulante, "contra", "2023-01-20", nacionalidadPostulante, null, null, null);
		
		try {
			cu.ingresarDatosPostulante(post);
		} catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		cu.seguirUsuario(nicknamePostulante, nicknameEmpresa);
		String[] seguidos = cu.obtenerSeguidos(nicknamePostulante);
		assertEquals(seguidos[0], nicknameEmpresa);
	}
	
	@Test 
	void agregarOfertaFavorito() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//Postulante
		String nicknamePostulante = "postulante";
		String nombrePostulante = "nombre";
		String apellidoPostulante = "apellido";
		String correoPostulante = "correo";
		String nacionalidadPostulante = "nacionalidad";
		DTPostulante post = new DTPostulante(nicknamePostulante, nombrePostulante, apellidoPostulante, correoPostulante, "contra", "2023-01-20", nacionalidadPostulante, null, null, null);
		
		try {
			cu.ingresarDatosPostulante(post);
		} catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		//Tipo
		String nombreTipo = "nombreTipo";
		int exposicionTipo = 3;
		int duracionTipo = 15;
		Float costoTipo = 330f;
		LocalDate fechaAlta = LocalDate.now();
		Date fechaAltaTipo = Date.from(fechaAlta.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Tipo t = new Tipo(nombreTipo, descripcionEmpresa, exposicionTipo, duracionTipo, costoTipo, fechaAlta);
		try {
			col.ingresarTipo(nombreTipo, descripcionEmpresa, exposicionTipo, duracionTipo, costoTipo, fechaAltaTipo);
		} catch (TipoRepetidoException e) {
			e.printStackTrace();
		}
		
		//OfertaLaboral
		String nombreOferta = "nomOferta";
		String descripcionOferta = "descOferta";
		String ciudadOferta = "ciudadOferta";
		String departamentoOferta = "departamentoOferta";
		String horarioOferta = "10:00-15:30";
		float remuneracionOferta = 8447f;
		float costoOferta = 330f;
		DTTipo tipoOferta = t.getDataTipo();
		EstadoOL estadoOferta = EstadoOL.Confirmada;
		DTOfertaLaboral ofLab = new DTOfertaLaboral(nombreOferta, descripcionOferta, ciudadOferta, departamentoOferta, horarioOferta, remuneracionOferta, "2023-08-01", "2023-08-01", 3, costoOferta, tipoOferta, new DTKeyword[0], null,nicknameEmpresa, estadoOferta, true);
		try {
			col.ingresarDatosOL(nicknameEmpresa, nombreTipo, ofLab);
		} catch (OfertaLaboralRepetidaException e) {
			e.printStackTrace();
		}
		
		cu.agregarOfertaFav(nicknamePostulante, nombreOferta);
		boolean esFav = cu.esFavorito(nicknamePostulante, nombreOferta);
		assertEquals(esFav, true);
		cu.quitarOfertaFav(nicknamePostulante, nombreOferta);
		esFav = cu.esFavorito(nicknamePostulante, nombreOferta);
		assertEquals(esFav, false);
	}
	
	@Test 
	void existePostulante() {
		//Postulante
		String nicknamePostulante = "postulante";
		String nombrePostulante = "nombre";
		String apellidoPostulante = "apellido";
		String correoPostulante = "correo";
		String nacionalidadPostulante = "nacionalidad";
		DTPostulante post = new DTPostulante(nicknamePostulante, nombrePostulante, apellidoPostulante, correoPostulante, "contra", "2023-01-20", nacionalidadPostulante, null, null, null);
		
		try {
			cu.ingresarDatosPostulante(post);
		} catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		boolean existePost = cu.existePostulante(nicknamePostulante);
		assertEquals(existePost, true);
		existePost = cu.existePostulante("holas");
		assertEquals(existePost, false);
	}
	
	@Test 
	void existeEmpresa() {
		//Empresa
		String nicknameEmpresa = "empresa";
		String nombreEmpresa = "nomEmpresa";
		String apellidoEmpresa = "apeEmpresa";
		String correoEmpresa = "correoEmpresa";
		String descripcionEmpresa = "descEmpresa";
		String linkEmpresa = "linkEmpresa";
		DTEmpresa emp = new DTEmpresa(nicknameEmpresa, nombreEmpresa, apellidoEmpresa, correoEmpresa, "contra", null, descripcionEmpresa, linkEmpresa, null, null);
		
		try {
			cu.ingresarDatosEmpresa(emp);
		}catch(ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		boolean existeEmp = cu.existeEmpresa(nicknameEmpresa);
		assertEquals(existeEmp, true);
		existeEmp = cu.existeEmpresa("Hola");
		assertEquals(existeEmp, false);
	}
}
