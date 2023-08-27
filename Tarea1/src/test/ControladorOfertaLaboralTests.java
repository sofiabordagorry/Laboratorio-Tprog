package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

import excepciones.PaqueteRepetidoException;
import excepciones.TipoRepetidoException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import logica.Factory;
import logica.IOfertaLaboral;
import logica.Keyword;
import logica.Tipo;
import logica.Paquete;
import logica.PaqueteTipo;
import logica.DTPaquete;
import logica.DTPaqueteTipo;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorTipo;

class ControladorOfertaLaboralTests{

	private static IOfertaLaboral controladorOfertaLaboral;
	private ManejadorOfertaLaboral mol;
	private ManejadorTipo mt;

	
	@BeforeAll
	public static void iniciar(){
		Factory fabrica = Factory.getInstance();
		controladorOfertaLaboral = fabrica.getIOfertaLaboral();
	}
	
	@AfterEach
	public void borrar(){
		
	}
	
	@Test
	void testIngresarKeyword(){
		String nombre = "nombreKeyword";
			controladorOfertaLaboral.ingresarKeyword(nombre);
			mol = ManejadorOfertaLaboral.getInstance();
			Keyword k = mol.buscarKeyword(nombre);
			//if (k == null) {
			//	fail("No exciste la keyword");
		//	}
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
		List<PaqueteTipo> paquetesTipos = new ArrayList<>();//lista vacia para comparar
		DTPaquete 	dtp = new DTPaquete(nombre, descripcion, periodoDeValidez, descuento, costoAsociado, dtpaq, fechaDeAlta);
		try {
			controladorOfertaLaboral.ingresarDatosPaquete(dtp);
		}catch(PaqueteRepetidoException	e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		
		assertThrows(PaqueteRepetidoException.class, ()->{controladorOfertaLaboral.ingresarDatosPaquete(dtp);});
		
	}
	
	//@Test
	

}
