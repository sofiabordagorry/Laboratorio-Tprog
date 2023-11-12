package com.trabajouy.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import publicar.DtCompra;
import publicar.DtEmpresa;
import publicar.DtKeyword;
import publicar.DtOfertaLaboral;
import publicar.DtPostulante;
import publicar.DtTipo;
import publicar.DtUsuario;
import publicar.EstadoOL;

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
    	DtUsuario user = (DtUsuario) session.getAttribute("usuario_logueado");
    	String ofertaConsultada = (String) request.getParameter("oferta_consultada");
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	
		publicar.WebServicesService service = new publicar.WebServicesService();
		publicar.WebServices port = service.getWebServicesPort();

        DtOfertaLaboral oferta = port.obtenerDTOfertaLaboral(ofertaConsultada);
        List<DtKeyword> keys = port.getDTKeyword().getKeys();
        request.setAttribute("keywords", keys);
        
        //port.agregarVisualizacion(oferta.getNombre());
        
        if(user != null) {
            DtEmpresa emp = port.buscarEmpresa(user.getNickname());
            if (emp.getNickname() != null && emp.getNickname().equals(oferta.getDataEmpresa())) {

                List<DtCompra> compras = port.getPaqComprados(emp.getNickname()).getCompras();
    
                if (compras != null) {
                    for (int i = 0; i < compras.size(); i++) {
                    	List<DtOfertaLaboral> of = compras.get(i).getOfertasLaborales();
                    	Boolean existeOferta = false;
                    	for (DtOfertaLaboral item: of) {
                    		if (item.getNombre().equals(ofertaConsultada)) {
                    			existeOferta = true;
                    			break;
                    		}
                    	}
                        if (of != null && compras.get(i).getOfertasLaborales() != null && existeOferta) {
                            request.setAttribute("paquete", compras.get(i).getPaqComprado());
                        }
                    }
                }
            }
        }
        DtTipo tipo = oferta.getDataTipo();
        LocalDate fechaAlta = LocalDate.parse(oferta.getFechaDeAlta(), formatter);
        request.setAttribute("vigente", fechaAlta.plusDays(tipo.getDuracion()).isAfter(LocalDate.now()));
        request.setAttribute("oferta_laboral", oferta);
        boolean ofertaVigente = port.estaVigenteOferta(ofertaConsultada);
        request.setAttribute("oferta_vigente", ofertaVigente);
        request.setAttribute("oferta_consultada", oferta);;
        
        if (user instanceof DtPostulante) {
        	request.setAttribute("esfav", port.esFavorito(user.getNickname(), ofertaConsultada));
        	request.setAttribute("existe_post", port.verificacionDePostulantePostulacion(user.getNickname(), oferta.getNombre(), oferta.getDataEmpresa()));
        } else if (user instanceof DtEmpresa) {
        	String seleccionarPostulacion = request.getParameter("seleccionar_postulantes");
        	if (seleccionarPostulacion != null)
        		request.setAttribute("seleccionar_postulantes", true);
        	else 
        		request.setAttribute("seleccionar_postulantes", false);
        }

        // Forward the request to the JSP for rendering.
        request.getRequestDispatcher("/WEB-INF/desktop/consultas/consultarOferta.jsp").forward(request, response);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = (String) request.getParameter("oferta_consultada");
		if(action != null) {
			publicar.WebServicesService service = new publicar.WebServicesService();
			publicar.WebServices port = service.getWebServicesPort();
			port.cambiarEstadoOferta(EstadoOL.FINALIZADA, action);
			//DtOfertaLaboral oferta = port.obtenerDTOfertaLaboral(action);
			doGet(request, response);
		}else {
			doGet(request, response);
		}
	}

}