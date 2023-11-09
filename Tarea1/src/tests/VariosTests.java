package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.DTOfertaLaboral;
import logica.DTPostulante;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.IUsuario;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorTipo;
import logica.ManejadorUsuario;

class VariosTests {
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
		mu = ManejadorUsuario.getInstancia();
		mu.borrarUsuarios();
	}
	
	/* @Test
	void testConstrucotresDTPostulante() {
		Map<String, DTOfertaLaboral> dtols = new HashMap<>();
		DTPostulante dtpost1 = new DTPostulante("nick", "nom", "apel", "correo", LocalDate.now(), "uru", dtols);
		DTPostulante dtpost2 = new DTPostulante("nick2", "nom", "apel", "correo2", LocalDate.now(), "uru", "contra");
		DTPostulante dtpost3 = new DTPostulante("nick3", "nom", "apel", "correo3", LocalDate.now(), "uru", "contra", new byte[0]);
	} */

}
