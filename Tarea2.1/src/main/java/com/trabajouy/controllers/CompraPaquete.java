package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import publicar.DtKeyword;
import publicar.DtKeywordWS;
import publicar.DtUsuario;
import publicar.DtPaquete;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CompraPaquete
 */
public class CompraPaquete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public CompraPaquete() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		
		HttpSession session = request.getSession();
		DtUsuario usuario = (DtUsuario) session.getAttribute("usuario_logueado");
    	
		DtKeywordWS keyss = port.getDTKeyword();
		List<DtKeyword> key = keyss.getKeys();
		request.setAttribute("keywords", key);
    	
     	request.setAttribute("paqComprado", true);
     	String nombrePaq = request.getParameter("paquete");
     
     	request.setAttribute("compraExitosa", port.comprarPaquete(usuario.getNickname(),nombrePaq));
     	
     	
     	DtPaquete paquete = port.buscarPaquete(nombrePaq);
        request.setAttribute("dataPaquete", paquete);
        request.getRequestDispatcher("/WEB-INF/desktop/consultas/consultaPaquete.jsp").forward(request, response);
	}

}
