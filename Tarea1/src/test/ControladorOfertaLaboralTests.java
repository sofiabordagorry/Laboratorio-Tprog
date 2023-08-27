package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

import excepciones.ExisteUnUsuarioYaRegistradoException;
import excepciones.NoHayPaquetesException;
import excepciones.OfertaLaboralRepetidaException;
import excepciones.PaqueteRepetidoException;
import excepciones.TipoRepetidoException;
import excepciones.KeywordsNoExistenException; 
import excepciones.TipoPubNoExistenException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import logica.Factory;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.Keyword;
import logica.Tipo;
import logica.Paquete;
import logica.PaqueteTipo;
import logica.OfertaLaboral;
import logica.DTOfertaLaboral;
import logica.DTPaquete;
import logica.DTPaqueteTipo;
import logica.DTPostulacion;
import logica.DTTipo;
import logica.DTEmpresa;
import logica.DTKeyword;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorTipo;
import logica.ManejadorUsuario;

class ControladorOfertaLaboralTests{

	private static IOfertaLaboral controladorOfertaLaboral;
	private static IUsuario controladorUsuario;
	private ManejadorOfertaLaboral mol;
	private ManejadorTipo mt;
	private ManejadorUsuario mu;

	
	@BeforeAll
	public static void iniciar(){
		Factory fabrica = Factory.getInstance();
		controladorOfertaLaboral = fabrica.getIOfertaLaboral();
		controladorUsuario =fabrica.getIUsuario();
	}
	
	@AfterEach
	public void borrar(){
		mol = ManejadorOfertaLaboral.getInstance();
		mol.limpiar();
		mt = ManejadorTipo.getInstancia();
		mt.limpiar();
	}
	
	@Test
	void testIngresarKeyword(){
		String nombre = "nombreKeyword";
			controladorOfertaLaboral.ingresarKeyword(nombre);
			mol = ManejadorOfertaLaboral.getInstance();
			Keyword k = mol.buscarKeyword(nombre);
			assertEquals(k.getNombre(), nombre);
	}
	
	@Test
	void testIngresarTipo(){
		String nombre = "nombreT1";
		String descripcion = "descripcionT1";
		 int exposicion = 1;
		 int duracion = 1;
		 float costo = 1;
		 Date fechaAlta = new Date(1597891200000L); // 20 de agosto 2020
		try {
			controladorOfertaLaboral.ingresarTipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
			mt = ManejadorTipo.getInstancia();
			Tipo t = mt.buscarTipo(nombre); 
			
			assertEquals(t.getNombre(), nombre);
			assertEquals(t.getDescripcion(), descripcion);
			assertEquals(t.getExposicion(), exposicion);
			assertEquals(t.getDuracion(), duracion);
			assertEquals(t.getCosto(), costo);
			assertEquals(t.getFechaDeAlta(), fechaAlta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()); //se pasa la date a localDate para comparar
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testTipoRepetido() {
		String nombre = "nombreT2";
		String descripcion = "descripcionT2";
		 int exposicion = 2;
		 int duracion = 2;
		 float costo = 2;
		 Date fechaAlta = new Date(1597891200000L); // 20 de agosto 2020
		 try {
				controladorOfertaLaboral.ingresarTipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
		 }catch (TipoRepetidoException e) {
				fail(e.getMessage());
				e.printStackTrace();
			}
		 
		 assertThrows(TipoRepetidoException.class, ()->{controladorOfertaLaboral.ingresarTipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);});
	}
	
	@Test
	void testIngresarDatosPaquete() {
		String nombre ="nombreP1";
		String descripcion = "descripcionP1";
		int periodoDeValidez = 1;
		float descuento= 1;
		float costoAsociado = 1;
		DTPaqueteTipo[] dtpaq = new DTPaqueteTipo[1];//arreglo vacio para hacer el DT que no se usa despues
		LocalDate fechaDeAlta  = LocalDate.of(2020, 8, 20);
		List<PaqueteTipo> paquetesTipos = new ArrayList<>();//lista vacia para comparar
			
		DTPaquete 	dtp = new DTPaquete(nombre, descripcion, periodoDeValidez, descuento, costoAsociado, dtpaq, fechaDeAlta);
		
		try {
			controladorOfertaLaboral.ingresarDatosPaquete(dtp);
			mt = ManejadorTipo.getInstancia();
			Paquete p = mt.buscarPaquete(nombre);
			assertEquals(p.getNombre(), nombre);
			assertEquals(p.getDescripcion(), descripcion);
			assertEquals(p.getDescuento(), descuento);
			assertEquals(p.getPaquetesTipos(), paquetesTipos);
			assertEquals(p.getCostoAsociado(), costoAsociado);
			assertEquals(p.getFechaDeAlta(), fechaDeAlta);
			
		}catch(PaqueteRepetidoException	e) {
			fail(e.getMessage());
			e.printStackTrace();
		}

	}
	
	@Test
	void testIngregarPaqueteRepetido() {
		String nombre ="nombreP2";
		String descripcion = "descripcionP2";
		int periodoDeValidez = 2;
		float descuento= 2;
		float costoAsociado = 2;
		DTPaqueteTipo[] dtpaq = new DTPaqueteTipo[1];//arreglo vacio para hacer el DT que no se usa despues
		LocalDate fechaDeAlta  = LocalDate.of(2020, 8, 20);
		DTPaquete 	dtp = new DTPaquete(nombre, descripcion, periodoDeValidez, descuento, costoAsociado, dtpaq, fechaDeAlta);
		try {
			controladorOfertaLaboral.ingresarDatosPaquete(dtp);
		}catch(PaqueteRepetidoException	e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertThrows(PaqueteRepetidoException.class, ()->{controladorOfertaLaboral.ingresarDatosPaquete(dtp);});
		
	}
	///*
	@Test
	void testListarPaquetes() {
		String nombre ="nombreP3";
		String descripcion = "descripcionP3";
		int periodoDeValidez = 3;
		float descuento= 3;
		float costoAsociado = 3;
		DTPaqueteTipo[] dtpaq = new DTPaqueteTipo[1];//arreglo vacio para hacer el DT que no se usa despues
		LocalDate fechaDeAlta  = LocalDate.of(2020, 8, 20);
			
		DTPaquete 	dtp = new DTPaquete(nombre, descripcion, periodoDeValidez, descuento, costoAsociado, dtpaq, fechaDeAlta);
		try {
			controladorOfertaLaboral.ingresarDatosPaquete(dtp);
			DTPaquete[] p = controladorOfertaLaboral.listarPaquetes();
			assertEquals(p[0].getNombre(), nombre);
			assertEquals(p[0].getDescripcion(), descripcion);
			assertEquals(p[0].getDescuento(), descuento);
			//assertEquals(p[0].getPaqueteTipos(), dtpaq);
			assertEquals(p[0].getCostoAsociado(), costoAsociado);
			assertEquals(p[0].getFechaDeAlta(), fechaDeAlta);
		}catch(PaqueteRepetidoException	e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (NoHayPaquetesException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	//*/
	
	@Test
	void testListarPaquetesVacio() {
		assertThrows(NoHayPaquetesException.class, ()->{controladorOfertaLaboral.listarPaquetes();});
	}
	
	@Test
	void testDatosPaqueteAMostrar() {
		String nombre ="nombreP4";
		String descripcion = "descripcionP4";
		int periodoDeValidez = 4;
		float descuento= 4;
		float costoAsociado = 4;
		DTPaqueteTipo[] dtpaq = new DTPaqueteTipo[1];//arreglo vacio para hacer el DT que no se usa despues
		LocalDate fechaDeAlta  = LocalDate.of(2020, 8, 20);
		DTPaquete 	dtp = new DTPaquete(nombre, descripcion, periodoDeValidez, descuento, costoAsociado, dtpaq, fechaDeAlta);
		
		String datos = controladorOfertaLaboral.DatosPaqueteAMostrar(dtp);
		String respuestaOK = "Nombre: " + nombre + "\nDescripcion: " + descripcion + "\nPeriodo de validez: " + periodoDeValidez + " días\nDescuento: " + descuento + "%\nCosto: $" + costoAsociado;
		assertEquals(datos, respuestaOK);
	}
	
	@Test
	void testIngresarDatosOL() {
		String nombreK = "nombreKeyword";
		
		controladorOfertaLaboral.ingresarKeyword(nombreK);
		//datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		 int exposicionT = 3;
		 int duracionT = 300000;
		 float costoT = 3;
		 Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020
		 
		 //datos empresa
		 String nicknameE ="nicke1";
		 String nombreE ="nome1";
		 String apellido= "ape1";
		 String correo= "coe1";
		 Map<String, DTOfertaLaboral> ofertasLaborales = new HashMap<>();
		 String nombreDeEmpresa ="nomdee1";
		 String descripcion = "dese1";
		 String link = "linke1";
		 
		 String empresa = nicknameE;
			String nombreTipo = nombreT;
			//datos oferta
			String nombre= "nombreOL1";
			String descripcionOL= "descOL1";
			String ciudad = "ciudOL1";
			String departamento = "depOL1";
			String horario = "hoeOL1";
			float remuneracion =1;
			LocalDate fechaDeAlta = LocalDate.of(2020, 8, 20);
			float costoAsociado = costoT;
			DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			Map<String, DTKeyword> dataKeywords = new HashMap<>();
			DTKeyword dtk = new DTKeyword(nombreK);
			dataKeywords.put(nombreK,dtk);
			List<DTPostulacion> dataPostulaciones = new ArrayList<>();
		 
		 DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, ofertasLaborales, nombreDeEmpresa, descripcion, link);

		 
		try {
			
			controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
			
			controladorUsuario.ingresarDatosEmpresa(dte); 
			mt = ManejadorTipo.getInstancia();
			Tipo tOL = mt.buscarTipo(nombreTipo);
			DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, fechaDeAlta, costoAsociado, dataTipo, dataKeywords, dataPostulaciones, empresa);
			controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);
			mol = ManejadorOfertaLaboral.getInstance();
			OfertaLaboral Ol = mol.buscarOfertaLaboral(nombre);
			assertEquals(Ol.getNombre(), nombre);
			assertEquals(Ol.getCiudad(), ciudad);
			assertEquals(Ol.getDepartamento(), departamento);
			assertEquals(Ol.getDescripcion(), descripcionOL);
			assertEquals(Ol.getFechaDeAlta(), fechaDeAlta);
			assertEquals(Ol.getCostoAsociado(), costoAsociado);
			assertTrue(Ol.getTipoOL().equals(tOL));
			assertEquals(Ol.getKeywords().keySet().iterator().next(), nombreK);
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testIngresarDatosOLRepetido() {
		String nombreK = "nombreKeyword";
		
		controladorOfertaLaboral.ingresarKeyword(nombreK);
		//datos tipo
		String nombreT = "nombreT3";
		String descripcionT = "descripcionT3";
		 int exposicionT = 3;
		 int duracionT = 300000;
		 float costoT = 3;
		 Date fechaAltaT = new Date(1597891200000L); // 20 de agosto 2020
		 
		 //datos empresa
		 String nicknameE ="nicke2";
		 String nombreE ="nome2";
		 String apellido= "ape1";
		 String correo= "coe1";
		 Map<String, DTOfertaLaboral> ofertasLaborales = new HashMap<>();
		 String nombreDeEmpresa ="nomdee1";
		 String descripcion = "dese1";
		 String link = "linke1";
		 
		 String empresa = nicknameE;
			String nombreTipo = nombreT;
			//datos oferta
			String nombre= "nombreOL1";
			String descripcionOL= "descOL1";
			String ciudad = "ciudOL1";
			String departamento = "depOL1";
			String horario = "hoeOL1";
			float remuneracion =1;
			LocalDate fechaDeAlta = LocalDate.of(2020, 8, 20);
			float costoAsociado = costoT;
			DTTipo dataTipo = new DTTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			Map<String, DTKeyword> dataKeywords = new HashMap<>();
			DTKeyword dtk = new DTKeyword(nombreK);
			dataKeywords.put(nombreK,dtk);
			List<DTPostulacion> dataPostulaciones = new ArrayList<>();
		 
		 DTEmpresa dte = new DTEmpresa(nicknameE, nombreE, apellido, correo, ofertasLaborales, nombreDeEmpresa, descripcion, link);

		try {
			
			controladorOfertaLaboral.ingresarTipo(nombreT, descripcionT, exposicionT, duracionT, costoT, fechaAltaT);
			
			controladorUsuario.ingresarDatosEmpresa(dte); 
			mt = ManejadorTipo.getInstancia();
			Tipo tOL = mt.buscarTipo(nombreTipo);
			DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, fechaDeAlta, costoAsociado, dataTipo, dataKeywords, dataPostulaciones, empresa);
			controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);
			mol = ManejadorOfertaLaboral.getInstance();
			OfertaLaboral Ol = mol.buscarOfertaLaboral(nombre);
			assertEquals(Ol.getNombre(), nombre);
			assertEquals(Ol.getCiudad(), ciudad);
			assertEquals(Ol.getDepartamento(), departamento);
			assertEquals(Ol.getDescripcion(), descripcionOL);
			assertEquals(Ol.getFechaDeAlta(), fechaDeAlta);
			assertEquals(Ol.getCostoAsociado(), costoAsociado);
			assertTrue(Ol.getTipoOL().equals(tOL));
			assertEquals(Ol.getKeywords().keySet().iterator().next(), nombreK);
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (ExisteUnUsuarioYaRegistradoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (OfertaLaboralRepetidaException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		DTOfertaLaboral ol = new DTOfertaLaboral(nombre, descripcionOL, ciudad, departamento, horario, remuneracion, fechaDeAlta, costoAsociado, dataTipo, dataKeywords, dataPostulaciones, empresa);
		assertThrows(OfertaLaboralRepetidaException.class, ()->{controladorOfertaLaboral.ingresarDatosOL(empresa,  nombreTipo, ol);});
	}
	
	@Test
	void testListarKeywords() {
		String nombre = "nombreKeyword2";
		controladorOfertaLaboral.ingresarKeyword(nombre);
		try {
		String[] ke = controladorOfertaLaboral.listarKeywords();
		assertEquals(ke[0], nombre);
		}catch (KeywordsNoExistenException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testVacioListarKeywords() {
		assertThrows(KeywordsNoExistenException.class, ()->{controladorOfertaLaboral.listarKeywords();});
	}
	
	@Test
	void testListarTipoPublicacionOfertaLaboral() {
		String nombre = "nombreT1";
		String descripcion = "descripcionT1";
		 int exposicion = 1;
		 int duracion = 1;
		 float costo = 1;
		 Date fechaAlta = new Date(1597891200000L); // 20 de agosto 2020
		try {
			controladorOfertaLaboral.ingresarTipo(nombre, descripcion, exposicion, duracion, costo, fechaAlta);
			DTTipo[] t = controladorOfertaLaboral.listarTipoPublicacionOfertaLaboral();
			
			assertEquals(t[0].getNombre(), nombre);
			assertEquals(t[0].getDescripcion(), descripcion);
			assertEquals(t[0].getExposicion(), exposicion);
			assertEquals(t[0].getDuracion(), duracion);
			assertEquals(t[0].getCosto(), costo);
			assertEquals(t[0].getFechaDeAlta(), fechaAlta.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()); //se pasa la date a localDate para comparar
			
		}catch (TipoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}catch (TipoPubNoExistenException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@Test
	void testVacioListarTipoPublicacionOfertaLaboral() {
		assertThrows(TipoPubNoExistenException.class, ()->{controladorOfertaLaboral.listarTipoPublicacionOfertaLaboral();});
	}
}
