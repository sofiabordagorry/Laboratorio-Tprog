package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import publicar.DtPostulante;
import publicar.DtUsuario;

import java.io.IOException;

/**
 * Servlet implementation class AgregarOfertaAFavorito
 */
public class AgregarOfertaAFavorito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgregarOfertaAFavorito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
    	DtUsuario user = (DtUsuario) session.getAttribute("usuario_logueado");
    	
    	String ofertaConsultada = (String) request.getParameter("oferta");
    	//obtener servicios
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		
		if (user instanceof DtPostulante) {
			if (port.esFavorito(user.getNickname(), ofertaConsultada)) {
				
				port.quitarOfertaFav(user.getNickname(), ofertaConsultada);
			}else {
				port.agregarOfertaFav(user.getNickname(), ofertaConsultada);
			}
			response.sendRedirect("ConsultaOfertaLaboral?oferta_consultada=" + ofertaConsultada);
		}else {
			response.sendError(403);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
