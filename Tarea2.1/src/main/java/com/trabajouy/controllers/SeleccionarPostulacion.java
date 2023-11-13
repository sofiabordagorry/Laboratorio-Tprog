package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import publicar.DtUsuario;
import publicar.NoExistenOfertasSeleccionarPostulanteException_Exception;
import publicar.DtEmpresa;
import publicar.DtKeyword;
import publicar.DtKeywordWS;
import publicar.DtOfertaLaboral;
import publicar.DtOfertaLaboralWS;
import publicar.DtPostulacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class SeleccionarPostulacion
 */
public class SeleccionarPostulacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeleccionarPostulacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
		if(usr instanceof DtEmpresa) {
			publicar.WebServicesService service = new publicar.WebServicesService();
			publicar.WebServices port = service.getWebServicesPort();
			DtOfertaLaboralWS dtsOL;
			
			try {
				dtsOL = port.getOfertasSeleccionarPosutlante(usr.getNickname());
				List<DtOfertaLaboral> dtsOLList = dtsOL.getOfs();
				request.setAttribute("ofertasSeleccionarPostulante", dtsOLList);
			} catch (NoExistenOfertasSeleccionarPostulanteException_Exception e) {
				List<DtOfertaLaboral> dtsOLList = new ArrayList<>();
				request.setAttribute("ofertasSeleccionarPostulante", dtsOLList);
				request.setAttribute("sinOfertasSeleccionarPostulante", e.getMessage());
			}
			
			DtKeywordWS dtk = port.getDTKeyword();
			List<DtKeyword> dtkeys = dtk.getKeys();
			request.setAttribute("keywords", dtkeys);
			request.getRequestDispatcher("/WEB-INF/desktop/listar/listarOfertasSeleccionarPostulacion.jsp").forward(request, response);
		} else 
			response.sendError(403);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
		if (usr instanceof DtEmpresa) {
			publicar.WebServicesService service = new publicar.WebServicesService();
			publicar.WebServices port = service.getWebServicesPort();
			
			String ofertaConsultada = request.getParameter("nombreOferta");
			DtOfertaLaboral oferta = port.buscarOfertaLaboral(ofertaConsultada);
			if (oferta.getDataEmpresa().equals(usr.getNickname())) {
				List<DtPostulacion> postulaciones = oferta.getDataPostulaciones();
				
				String[] rankings = new String[postulaciones.size()];
				for(DtPostulacion elem : postulaciones) {
					String numS = request.getParameter("ranking_" + elem.getPostulante());
					int numero = Integer.parseInt(numS); 
					rankings[numero - 1] = elem.getPostulante();
				}
				
				port.realizarSeleccion(ofertaConsultada, rankings);
				response.sendRedirect("ConsultaOfertaLaboral?oferta_consultada=" + ofertaConsultada);
			} else {
				response.sendError(403);
			}
		} else {
			response.sendError(403);
		}
	}

}
