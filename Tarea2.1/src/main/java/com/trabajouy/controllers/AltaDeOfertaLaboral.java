package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import publicar.DtKeyword;
import publicar.DtKeywordWS;
import publicar.DtUsuario;
import publicar.DtOfertaLaboral;
import publicar.DtEmpresa;
 
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
		
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		
		DtUsuario usuario = (DtUsuario) session.getAttribute("usuario_logueado");
		if (usuario != null) {
			DtEmpresa empresa = port.buscarEmpresa(usuario.getNickname());
			if (empresa != null) { // es una empresa
				//obtengo los tipos
			
				String[] tipos = port.listarTipoPublicacionOfertaLaboral();
				if (tipos != null) {
				request.setAttribute("tiposOL", tipos);
				request.setAttribute("hayTipos", true);
					
				}else{
					
					request.setAttribute("hayTipos", false);
				}
				
				//obtengo las keywords
				DtKeywordWS keys = port.getDTKeyword();
				List<DtKeyword> key = keys.getKeys();
				request.setAttribute("keywords", key);
				if (key == null || key.size() == 0) {
					request.setAttribute("hayKeys", false);
				}else {
					String[] keywords = new String[key.size()];
					for (int j = 0; j < key.size(); j++) {
						keywords[j] =  key.get(j).getNombre();
		            }
					request.setAttribute("keywordsNom", keywords);
					request.setAttribute("hayKeys", true);

				}
				
				//obtener los paquetes
		
				request.getRequestDispatcher("/WEB-INF/desktop/altas/altaOfertaLaboral.jsp").forward(request, response);
			}else {
				response.sendError(403);
			}
		}else {
			response.sendError(403);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		
		HttpSession session = request.getSession();
		DtUsuario usuario = (DtUsuario) session.getAttribute("usuario_logueado");
	
		String nombre, horario, descripcion, tipo, ciudad, departamento;
		nombre = request.getParameter("nombre");
		horario = request.getParameter("horario");
		descripcion = request.getParameter("descripcion");
		tipo = request.getParameter("tipo");
		float remuneracion = Float.parseFloat(request.getParameter("remuneracion"));
		ciudad = request.getParameter("ciudad");
		departamento = request.getParameter("departamento");
		String[] nombreKey = request.getParameterValues("keywords");
		
		DtKeyword[] keys = new DtKeyword[nombreKey.length];
		for (int i =0; i < nombreKey.length; i++){
			DtKeyword key = new DtKeyword();
			key.setNombre(nombreKey[i]);
			keys[i] = key;
		}
		
		String[] tipos = port.listarTipoPublicacionOfertaLaboral();
		if (tipos != null) {
		request.setAttribute("tiposOL", tipos);
		request.setAttribute("hayTipos", true);
			
		}else{
			
			request.setAttribute("hayTipos", false);
		}
		
	
		
		//obtengo las keywords
		DtKeywordWS keyss = port.getDTKeyword();
		List<DtKeyword> key = keyss.getKeys();
		request.setAttribute("keywords", key);
		if (key == null || key.size() == 0) {
			request.setAttribute("hayKeys", false);
		}else {
			String[] keywords = new String[key.size()];
			for (int j = 0; j < key.size(); j++) {
				keywords[j] =  key.get(j).getNombre();
            }
			request.setAttribute("keywordsNom", keywords);
			request.setAttribute("hayKeys", true);

		}
		
		
		DtOfertaLaboral ofLab = port.crearDTOfertaLaboral(nombre, descripcion, ciudad, departamento, horario, remuneracion, nombreKey);
		
		
		if (port.ingresarDatosOL(usuario.getNickname(), tipo, ofLab)) {
			request.getRequestDispatcher("/WEB-INF/desktop/altas/altaOfertaLaboral.jsp").forward(request, response);
			
		}else{
			request.setAttribute("datosOferta", ofLab);
			request.setAttribute("nombreTipo", tipo);
			request.setAttribute("OLrepetida", "oferta repetida");
			
			request.getRequestDispatcher("/WEB-INF/desktop/errores/altaOfertaLaboralError.jsp").forward(request, response);

		}
	}

}
