package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logica.DTPaquete;
import logica.Factory;
import logica.IOfertaLaboral;
import logica.Keyword;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorTipo;
import logica.Paquete;
import logica.Usuario;

import java.io.IOException;

import excepciones.PaqueteYaCompradoException;



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

    	Factory fac = Factory.getInstance();
    	IOfertaLaboral col = fac.getIOfertaLaboral();
    	ManejadorTipo mtip = ManejadorTipo.getInstancia();
    	ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Keyword[] keys = mol.getKeywords();
		request.setAttribute("keywords", keys);
    	
		HttpSession session = request.getSession();
     	Usuario user = (Usuario) session.getAttribute("usuario_logueado");
     	request.setAttribute("paqComprado", true);
     	String nombrePaq = request.getParameter("paquete");
     	try {
     		col.comprarPaquete(user.getNickname(),nombrePaq);
     		request.getSession().setAttribute("compraExitosa", true);
     	}catch (PaqueteYaCompradoException e) {
     		request.getSession().setAttribute("compraExitosa", false);
     	}
     	
     	Paquete paq = mtip.buscarPaquete(nombrePaq);
        DTPaquete paquete = paq.getDataPaquete();
        request.setAttribute("dataPaquete", paquete);
        request.getRequestDispatcher("/WEB-INF/consultas/consultaPaquete.jsp").forward(request, response);
	}

}
