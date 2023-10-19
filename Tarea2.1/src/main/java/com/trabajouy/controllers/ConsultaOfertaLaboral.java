package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import logica.Usuario;
import logica.Compra;
import logica.DTOfertaLaboral;
import logica.Empresa;
import logica.Keyword;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorUsuario;

/**
 * Servlet implementation class consultaOferta
 */
public class ConsultaOfertaLaboral extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultaOfertaLaboral() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	Usuario user = (Usuario) session.getAttribute("usuario_logueado");
    	String ofertaConsultada = (String) request.getParameter("oferta_consultada");
    	DTOfertaLaboral oferta = ManejadorOfertaLaboral.getInstance().buscarOfertaLaboral(ofertaConsultada).getDataOfertaLaboral();
        ManejadorUsuario murs = ManejadorUsuario.getInstancia();
        ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Keyword[] keys = mol.getKeywords();
		request.setAttribute("keywords", keys);
        
        if(user != null) {
	        Empresa emp = murs.buscarEmpresa(user.getNickname());
	        if (emp != null && emp.getNickname() == oferta.getDTEmpresa()) {
	            List<Compra> compras = emp.getCompras();
	
	             if (compras != null) {
	                for (int i = 0; i < compras.size(); i++) {
	                    if (compras.get(i).getOfertas() != null && compras.get(i).getOfertas().get(oferta.getNombre()) != null) {
	                        request.setAttribute("paquete", compras.get(i).getPaqueteComprado().getDataPaquete().getNombre());
	                    }
	                }
	            }
	        }
        }
        
        request.setAttribute("oferta_laboral", oferta);

        // Forward the request to the JSP for rendering.
        request.getRequestDispatcher("/WEB-INF/consultas/consultarOferta.jsp").forward(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}