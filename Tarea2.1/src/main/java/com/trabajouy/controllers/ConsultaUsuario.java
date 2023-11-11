package com.trabajouy.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import publicar.DtKeywordWS;
import publicar.DtUsuario;
import publicar.DtPostulante;
import publicar.DtPostulacion;
import publicar.DtOfertaLaboral;
import publicar.DtUsuarioWS;
import publicar.EstadoOL;
import publicar.UsuariosNoExistenException_Exception;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class ConsultaUsuario
 */
public class ConsultaUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsultaUsuario() {
    	super();
        // TODO Auto-generated constructor stub
    }
    
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	// Obtén el nickname del usuario que se consulta de alguna manera
    	//esto hay que cambiarlo en todos por el publicador (web services)
    	
    	publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
    	String nicknameConsultado = request.getParameter("usuarioConsultado");
    	
		DtKeywordWS keys = port.getDTKeyword();
		request.setAttribute("keywords", keys.getKeys());
    	
    	// Obtén el nickname del usuario en sesión (si hay uno)
    	HttpSession session = request.getSession();
    	//Usuario user = (Usuario) session.getAttribute("usuario_logueado");
    	DtUsuario user = (DtUsuario) session.getAttribute("usuario_logueado");
    	String nicknameEnSesion = null;
		request.setAttribute("logueado", false);
    	if(user != null) {
    		nicknameEnSesion = user.getNickname();
    		
    	}
    	
    	if (nicknameConsultado == null) {
    	    // No se especificó un nickname para consultar, mostrar la página ListarUsuarios
    		try {
				//DTUsuario[] users = iurs.listarUsuarios();
	    		DtUsuarioWS users = port.listarUsuarios();
    			request.setAttribute("SystemUsers", users.getUsers());
				request.setAttribute("NoUsersInSystem_Error", false);
	    	    request.getRequestDispatcher("/WEB-INF/desktop/listar/listarUsuarios.jsp").forward(request, response);
			} catch (UsuariosNoExistenException_Exception e) {
				request.setAttribute("NoUsersInSystem_Error", true);
	    		request.getRequestDispatcher("/WEB-INF/desktop/listar/listarUsuarios.jsp").forward(request, response);
			}


    	} else {
	        DtUsuario userInfo = port.mostrarInformacionUsuario(nicknameConsultado);
	        request.setAttribute("userData", userInfo);
    	    if (nicknameEnSesion != null && nicknameEnSesion.equals(nicknameConsultado)) {
    	        // El usuario en sesión coincide con el usuario consultado
    	    	String[] seguidores = port.obtenerSeguidores(nicknameEnSesion);
	        	String[] seguidos = port.obtenerSeguidos(nicknameEnSesion);
	        	request.setAttribute("User_Followers", seguidores);
	        	request.setAttribute("User_Following", seguidos);
    	        if (port.existeEmpresa(nicknameConsultado)) {
    	            // Es una empresa, mostrar MiPerfilEmpresa
    	            request.getRequestDispatcher("/WEB-INF/desktop/consultas/miPerfilEmpresa.jsp").forward(request, response);
    	        } else if (port.existePostulante(nicknameConsultado)) {
    	            // Es un postulante, mostrar MiPerfilPostulante
					DtPostulante pInfo = null;
            		pInfo = (DtPostulante) userInfo;
            		List<DtPostulacion> posts = pInfo.getPostulaciones();
            		List<String> ofertasFinalizadas = new ArrayList<>();
            		for(DtPostulacion p : posts) {
            			DtOfertaLaboral ofLab = port.buscarOfertaLaboral(p.getOferta());
            			if(ofLab.getEstado() == EstadoOL.FINALIZADA) {
            				ofertasFinalizadas.add(p.getOferta());
            			}
            		}
            		request.setAttribute("Ofertas_Finalizadas", ofertasFinalizadas);
    	            request.getRequestDispatcher("/WEB-INF/desktop/consultas/miPerfilPostulante.jsp").forward(request, response);
    	        }
    	    } else {
    	    	String[] seguidores = port.obtenerSeguidores(nicknameConsultado);
	        	String[] seguidos = port.obtenerSeguidos(nicknameConsultado);
	        	request.setAttribute("User_Followers", seguidores);
	        	request.setAttribute("User_Following", seguidos);
    	    	if (nicknameEnSesion != null) {
    	    		request.setAttribute("logueado", true);
    	    		boolean sigue = port.estaSiguiendo(nicknameEnSesion, nicknameConsultado);
    	    		request.setAttribute("siguiendo", sigue);
    	    	}
    	        if (port.existeEmpresa(nicknameConsultado)) {
    	            // Es una empresa, mostrar ConsultarEmpresa
    	            request.getRequestDispatcher("/WEB-INF/desktop/consultas/consultarEmpresa.jsp").forward(request, response);
    	        } else if (port.existePostulante(nicknameConsultado)) {
    	            // Es un postulante, mostrar ConsultarPostulante
    	            request.getRequestDispatcher("/WEB-INF/desktop/consultas/consultarPostulante.jsp").forward(request, response);
    	        }
    	    }
    	}
    }
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
    	
		HttpSession session = request.getSession();
    	DtUsuario user = (DtUsuario) session.getAttribute("usuario_logueado");
    	
    	if(user != null) {
    		String nicknameEnSesion = user.getNickname();
    		String nicknameConsultado = request.getParameter("seguidor");
    		if (port.estaSiguiendo(nicknameEnSesion, nicknameConsultado)) {
    			port.dejarSeguimiento(nicknameEnSesion, nicknameConsultado);
    		}else {
    			port.seguirUsuario(nicknameEnSesion, nicknameConsultado);
    		}
    		
    		response.sendRedirect("ConsultaUsuario?usuarioConsultado=" + nicknameConsultado);
    		
    	}else {
    		response.sendError(403);
    	}
	}

}
