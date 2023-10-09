package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.DTKeyword;
import logica.DTOfertaLaboral;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;

import logica.ManejadorOfertaLaboral;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Tipo;
import logica.ManejadorTipo;
import logica.Empresa;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.Keyword;
//import com.trabajouy.model.Usuario;
import logica.Usuario;
import excepciones.OfertaLaboralRepetidaException;
 


/**
 * Servlet implementation class AltaDeOL
 */
public class AltaDeOfertaLaboral extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AltaDeOfertaLaboral() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario_logueado");
		ManejadorTipo mtip = ManejadorTipo.getInstancia();
		if (usuario != null) {
			ManejadorUsuario musr = ManejadorUsuario.getInstancia();
			Map<String, Empresa> emp = musr.getMapEmpresas();
			Empresa empresa = emp.get(usuario.getNickname());
			if (empresa != null) { // es una empresa
				//obtengo los tipos
				//ManejadorTipo mt = ManejadorTipo.getInstancia();
				Tipo[] tipo = mtip.getTipos();
				if (tipo == null) {
					request.setAttribute("hayTipos", false);
				}else {
					String[] tipos = new String[tipo.length];
					for (int i = 0; i < tipo.length; i++) {
		                tipos[i] =  tipo[i].getNombre();
		            }
					request.setAttribute("tiposOL", tipos);
					request.setAttribute("hayTipos", true);

				}
				
				//obtengo las keywords
				ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
				Keyword[] key = mol.getKeywords();
				if (key == null) {
					request.setAttribute("hayKeys", false);
				}else {
					String[] keywords = new String[key.length];
					for (int j = 0; j < key.length; j++) {
						keywords[j] =  key[j].getNombre();
		            }
					request.setAttribute("keywords", keywords);
					request.setAttribute("hayKeys", true);

				}
				
				//obtener los paquetes
				Factory fac = Factory.getInstance();
				IOfertaLaboral icol = fac.getIOfertaLaboral();
				
				request.getRequestDispatcher("/WEB-INF/altas/altaOfertaLaboral.jsp").forward(request, response);
			}else {
				response.sendError(403);
			}
		}else {
			response.sendError(403);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Factory fac = Factory.getInstance();
		IOfertaLaboral icol = fac.getIOfertaLaboral();
		
		HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("usuario_logueado");

		ManejadorTipo mtip = ManejadorTipo.getInstancia();
		
		String nombre, horario, descripcion, tipo, ciudad, departamento;
		nombre = request.getParameter("nombre");
		horario = request.getParameter("horario");
		descripcion = request.getParameter("descripcion");
		tipo = request.getParameter("tipo");
		float remuneracion = Float.parseFloat(request.getParameter("remuneracion"));
		ciudad = request.getParameter("ciudad");
		departamento = request.getParameter("departamento");
		System.out.println(nombre + " " + horario + " " + descripcion + " "  + remuneracion + " " + ciudad + " " + departamento + " " + tipo);
		
		String[] nombreKey = request.getParameterValues("keywords");
		Map<String, DTKeyword> keys = new HashMap<>();
		for (int i =0; i < nombreKey.length; i++){
			DTKeyword key = new DTKeyword(nombreKey[i]);
			keys.put(nombreKey[i], key);
		}
		
		Tipo[] tipoSS = mtip.getTipos();
		if (tipoSS == null) {
			request.setAttribute("hayTipos", false);
		}else {
			String[] tipos = new String[tipoSS.length];
			for (int i = 0; i < tipoSS.length; i++) {
                tipos[i] =  tipoSS[i].getNombre();
            }
			request.setAttribute("tiposOL", tipos);
			request.setAttribute("hayTipos", true);

		}
		
		//obtengo las keywords
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Keyword[] key = mol.getKeywords();
		if (key == null) {
			request.setAttribute("hayKeys", false);
		}else {
			String[] keywords = new String[key.length];
			for (int j = 0; j < key.length; j++) {
				keywords[j] =  key[j].getNombre();
            }
			request.setAttribute("keywords", keywords);
			request.setAttribute("hayKeys", true);

		}
		
		LocalDate fechaDeAlta =  LocalDate.now();
		
		DTOfertaLaboral ofLab = new DTOfertaLaboral(nombre, descripcion, ciudad, departamento, horario, remuneracion, fechaDeAlta, keys);
		
		try {
			icol.ingresarDatosOL(usuario.getNickname(), tipo, ofLab);
			request.getRequestDispatcher("/WEB-INF/altas/altaOfertaLaboral.jsp").forward(request, response);
			
		}catch (OfertaLaboralRepetidaException e) {
			request.setAttribute("datosOferta", ofLab);
			request.setAttribute("nombreTipo", tipo);
			request.setAttribute("OLrepetida", e.getMessage());
			
			request.getRequestDispatcher("/WEB-INF/errores/altaOfertaLaboralError.jsp").forward(request, response);

		}
	}

}