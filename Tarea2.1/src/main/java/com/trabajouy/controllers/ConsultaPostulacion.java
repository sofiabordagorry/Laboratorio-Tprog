package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import publicar.UsuarioSinPostulacionesException_Exception;
import publicar.DtEmpresa;
import publicar.DtKeyword;
import publicar.DtKeywordWS;
import publicar.DtOfertaLaboral;
import publicar.DtPostulante;
import publicar.DtUsuario;
import publicar.DtOfertaLaboralMisPostulacionesWS;
import publicar.DtPostulacion;

/**
 * Servlet implementation class ConsultaPostulacion
 */
public class ConsultaPostulacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConsultaPostulacion() {
        super();
        // TODO Auto-generated constructor stub
    }
  
    private void processRequest(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
    	
    	DtUsuario usr = (DtUsuario) request.getSession().getAttribute("usuario_logueado");
    	String nombreOferta = request.getParameter("oferta_consultada");
    	request.setAttribute("nombreOferta", nombreOferta);
    	
//    	Factory fac = Factory.getInstance();
//		IUsuario cusr = fac.getIUsuario();
//		IOfertaLaboral col = fac.getIOfertaLaboral();
    	publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();
		
		//keywords para mostrar en el sidebar
		DtKeywordWS dtkeys = port.getDTKeyword();
		List<DtKeyword> keys = dtkeys.getKeys();
		request.setAttribute("keywords", keys);
    	
		String nicknameConsultado = request.getParameter("postulanteConsultado");

		String nicknameEnSesion = null;
		if (usr != null) {
			nicknameEnSesion = usr.getNickname();
			if (nicknameConsultado == null && usr instanceof DtPostulante) {
				try {
					DtOfertaLaboralMisPostulacionesWS postulacionesWS = port.listarOfertasPostulado(nicknameEnSesion);
					List<DtOfertaLaboral> misPostulaciones = postulacionesWS.getOfertas();
					request.setAttribute("misPostulaciones", misPostulaciones);
					request.setAttribute("nicknameConsultado", nicknameEnSesion);
					request.setAttribute("oferta_consultada",nombreOferta);
				} catch (UsuarioSinPostulacionesException_Exception e) {
					e.printStackTrace();
				}
				request.getRequestDispatcher("/WEB-INF/desktop/listar/listarPostulaciones.jsp").forward(request, response);
				
			} else if(usr instanceof DtPostulante && nicknameConsultado.equals(usr.getNickname()) || 
						usr instanceof DtEmpresa && port.verificacionDePostulantePostulacion(nicknameConsultado, nombreOferta, usr.getNickname())){ 
				DtPostulacion dataPostulacion = port.dataPostulacion(nicknameConsultado, nombreOferta);
				request.setAttribute("oferta", port.buscarOfertaLaboral(nombreOferta));
				request.setAttribute("usuarioPostulacion", port.dataPostulante(nicknameConsultado));
				request.setAttribute("dataPostulacion", dataPostulacion);
				request.setAttribute("videoURL", port.obtenerVideoPostulacion(nicknameConsultado, nombreOferta));
				request.getRequestDispatcher("/WEB-INF/desktop/consultas/consultaPostulacion.jsp").forward(request, response);
			} else {
				response.sendError(403);
			}
		} else {
			response.sendError(403);
		}
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.processRequest(request, response);
	}

}