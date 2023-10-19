package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;

import excepciones.YaSePostuloException;
import logica.Empresa;
import logica.Factory;
import logica.IUsuario;
import logica.Keyword;
import logica.LoginEstado;
import logica.ManejadorOfertaLaboral;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Postulante;
import logica.Usuario;

@WebServlet("/PostularAOfertaLaboral")
public class PostulacionAOfertaLaboral extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PostulacionAOfertaLaboral() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println((String) request.getParameter("oferta"));
    	String nomOL = (String) request.getParameter("oferta");
		request.getSession().setAttribute("oferta", nomOL);
    	request.getRequestDispatcher("/WEB-INF/altas/postulacionAOfertaLaboral.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Verificar el estado de login
        // Obtén el nickname del usuario en sesión (si hay uno)
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario_logueado");
        ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getInstance();
		Keyword[] keys = mol.getKeywords();
		request.setAttribute("keywords", keys);
		boolean seCumpleExcepcion = false;

        if (user == null) {
            response.sendError(403);
        } else {
            if (user instanceof Empresa) {
                response.sendError(403);
            } else if (user instanceof Postulante) {
                Factory fac = Factory.getInstance();
                IUsuario icu = fac.getIUsuario();
                ManejadorOfertaLaboral manejadorOf = ManejadorOfertaLaboral.getInstance();
                ManejadorUsuario manejadorUsu = ManejadorUsuario.getInstancia();

                String CVReducido, motivacion;
                CVReducido = request.getParameter("CVReducido");
                motivacion = request.getParameter("motivacion");
                String ofertaL = (String) session.getAttribute("oferta");
                System.out.println(ofertaL);
                OfertaLaboral ofertaBuscada = manejadorOf.buscarOfertaLaboral(ofertaL);
                String postulante = user.getNickname();
                
    			try {
                    // Ingresar los datos de la postulación
                    icu.ingresarPostulacion(CVReducido, motivacion, LocalDate.now(), ofertaBuscada.getEmpresaCreadora(), ofertaL, postulante);
    
                    session.setAttribute("yaSePostulo", true);
                } catch (YaSePostuloException e) {
                    request.setAttribute("postulacionError", e.getMessage());
    				seCumpleExcepcion = true;
    			}
    		}
    		
    		
    		if(seCumpleExcepcion) {
                request.getRequestDispatcher("/WEB-INF/errores/postulacionAOfertaLaboralError.jsp").forward(request, response);
    		} else {
                request.getRequestDispatcher("/WEB-INF/altas/postulacionAOfertaLaboral.jsp").forward(request, response);
    		}

                
        }
    }

    public static LoginEstado getLoginEstado(HttpServletRequest request) {
        return (LoginEstado) request.getSession().getAttribute("loginEstado");
    }

    public static Usuario getUsuarioLogueado(HttpServletRequest request) {
        return (Usuario) request.getSession().getAttribute("usuario_logueado");
    }
}

