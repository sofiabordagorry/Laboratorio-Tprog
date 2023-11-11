package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import publicar.DtEmpresa;
import publicar.DtKeywordWS;
import publicar.DtOfertaLaboral;
import publicar.DtPostulante;
import publicar.DtUsuario;
import publicar.YaSePostuloException_Exception;

@WebServlet("/PostularAOfertaLaboral")
public class PostulacionAOfertaLaboral extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PostulacionAOfertaLaboral() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String nomOL = (String) request.getParameter("oferta");
		request.getSession().setAttribute("oferta", nomOL);
    	request.getRequestDispatcher("/WEB-INF/mobile/altas/postulacionAOfertaLaboral.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (ServletException | IOException | YaSePostuloException_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, YaSePostuloException_Exception {
        // Verificar el estado de login
        // Obtén el nickname del usuario en sesión (si hay uno)
        HttpSession session = request.getSession();
    	DtUsuario user = (DtUsuario) session.getAttribute("usuario_logueado");
        
    	publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
        
		DtKeywordWS keys = port.getDTKeyword();
		request.setAttribute("keywords", keys.getKeys());
		
		boolean seCumpleExcepcion = false;

        if (user == null) {
            response.sendError(403);
        } else {
            if (user instanceof DtEmpresa) {
                response.sendError(403);
            } else if (user instanceof DtPostulante) {
                String CVReducido, motivacion, link;
                CVReducido = request.getParameter("CVReducido");
                motivacion = request.getParameter("motivacion");
                link = request.getParameter("link");
                String ofertaL = (String) session.getAttribute("oferta");
                DtOfertaLaboral ofertaBuscada = port.obtenerDTOfertaLaboral(ofertaL);
                String postulante = user.getNickname();
               	LocalDate fecha =  LocalDate.now();
                
        		
        		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        		String fechaString = fecha.format(formatter);
                
    			try {
    				
                    // Ingresar los datos de la postulación
                    port.ingresarPostulacion(CVReducido, motivacion, fechaString, ofertaBuscada.getDataEmpresa(), ofertaL, postulante, link);
                    session.setAttribute("yaSePostulo", true);
                } catch (YaSePostuloException_Exception e) {
                    request.setAttribute("postulacionError", e.getMessage());
    				seCumpleExcepcion = true;
    			}
    		}
    		
    		
    		if(seCumpleExcepcion) {
                request.getRequestDispatcher("/WEB-INF/mobile/errores/postulacionAOfertaLaboralError.jsp").forward(request, response);
    		} else {
				request.setAttribute("postulacionExitosa", true);
                request.getRequestDispatcher("/WEB-INF/mobile/altas/postulacionAOfertaLaboral.jsp").forward(request, response);
    		}

                
        }
    }
}
