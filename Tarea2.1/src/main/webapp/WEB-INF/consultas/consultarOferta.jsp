<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="logica.DTOfertaLaboral"%>
<%@ page import="logica.DTKeyword"%>
<%@ page import="logica.Usuario"%>
<%@ page import="logica.DTPostulacion"%>
<%@ page import="logica.Empresa"%>
<%@ page import="logica.Postulante"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%
	DTOfertaLaboral ofertaConsultada = (DTOfertaLaboral) request.getAttribute("oferta_laboral");
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <jsp:include page="../template/head.jsp" />
	    <link rel="stylesheet" href="media/styles/stylesConsultas.css">
	    <link rel="stylesheet" href="media/styles/consultaOfertaLaboral.css">
	    <title>Trabajo.uy</title>
	</head>
	<body>
		<jsp:include page="../template/navbar.jsp" />
			<div class="container-fluid" id="web-content">
	        <div class="row justify-content-md-center">
	          <jsp:include page="../template/sidebar.jsp" />
	          	<div class="col-md-9 col-sm-12" id="main-content">
	              <div class="container-fluid" id="consultaOferta">
	                  <div class="row">
	                      <div class="col-md-8 col-sm-12" id="ofertaInfo">
	                          <div>
	                              <img class="imagenOferta mx-auto d-block p-2" src="https://www.hubspot.com/hubfs/media/estrategianegocio.jpeg" />
	                              <h2 class="text-center p-2"><%= ofertaConsultada.getNombre() %></h2>
	                              <p class="text-center p-2"><%= ofertaConsultada.getDescripcion() %></p>
	                        </div>
	                          <div class="p-4">
	                              <p><i class="fa-solid fa-money-bills" style="color: #000000;"></i> <b>Remuneración:</b> $<%= ofertaConsultada.getRemuneracion() %></p>
	                              <p><i class="fa-solid fa-clock"></i> <b>Horario:</b> <%= ofertaConsultada.getHorario() %> </p>
	                              <p><i class="fa-solid fa-location-crosshairs"></i> <b>Departamento: </b> <%= ofertaConsultada.getDepartamento() %></p>
	                              <p><i class="fa-solid fa-location-crosshairs"></i> <b>Ciudad: </b> <%= ofertaConsultada.getCiudad() %></p>
	                              <p><i class="fa-solid fa-calendar-days"></i> <b>Fecha de alta: </b> <%= ofertaConsultada.getFechaDeAlta() %></p>
	                              <p><i class="fa-solid fa-key"></i> <b>Keywords:</b>
	                              <%
	                              	Map<String, DTKeyword> keywords = ofertaConsultada.getKeywords();
	                              	for (Map.Entry<String, DTKeyword> entry : keywords.entrySet()) {
	                              %>
	                              	<%= entry.getKey() + "\n" %>
	                              <%
	                              	}
	                              %>
	                              </p>
	                          </div>
	                          <div>
	                              <button class="btn mx-auto d-block">
	                                  <a href="PostulacionAOfertaLaboral?oferta=<%= ofertaConsultada.getNombre() %>">Postularse</a>
	                              </button>
	                          </div>
	                      </div>
	                          	<%
	                          		List<DTPostulacion> postulaciones = ofertaConsultada.getPostulaciones();
	                          		Usuario user = (Usuario) session.getAttribute("usuario_logueado");
	                          		if (user != null) {
	                          			if (user instanceof Empresa && user.getNickname() == ofertaConsultada.getDTEmpresa()) {
	                          				for (DTPostulacion elem: postulaciones) {
	                          	%>
	                          	<div class="col col-md-4 col-sm-12" id="postulacionesPaquetesCol">
	                          		<div id="postulaciones">
		                              <h2>Postulaciones</h2>
		                              <div class="postulante">
		                                  <img width="50" height="50" class="img-fluid img-thumbnail" src="https://imgv3.fotor.com/images/gallery/a-woman-linkedin-picture-with-grey-background-made-by-LinkedIn-Profile-Picture-Maker.jpg" />
		                                  <a href="ConsultaPostulacion?postulanteConsultado=<%=elem.getPostulante()%>&nombreOfertaConsultada=<%= ofertaConsultada.getNombre()%>"><%= elem.getPostulante() %></a>
		                              </div>
		                              <h2 class="mt-5">Paquete</h2>
		                              <div class="paquete">
		                                  <img width="50" height="50" class="img-fluid img-thumbnail" src="./public/files/paquete.png" />
		                                  <a href="./ConsultaTipoBasica-ecotech.html"><%= ofertaConsultada.getTipo() %></a>
		                              </div>
		                             </div>
		                          </div>
	                            <%
	                          				}
	                          			} else if (user instanceof Empresa && user.getNickname() != ofertaConsultada.getDTEmpresa()) {
	                            %>
	                            <div id="postulaciones">
	                              <h2 class="mt-5">Paquete</h2>
	                              <div class="paquete">
	                                  <img width="50" height="50" class="img-fluid img-thumbnail" src="./public/files/paquete.png" />
	                                  <a href="./ConsultaTipoBasica-ecotech.html"><%= ofertaConsultada.getTipo() %></a>
	                              </div>
	                            </div>
	                            <%
	                          			} else if (user instanceof Postulante) {
	                          				if (((Postulante) user).existePostulacion(ofertaConsultada.getNombre())) {
	                          					%>
	                          					<div id="postulaciones">
	                                    		<h2>Postulacion</h2>
	                                    			<div class="postulante">
	                                        			<img width="50" height="50" class="img-fluid img-thumbnail" src="https://imgv3.fotor.com/images/gallery/a-woman-linkedin-picture-with-grey-background-made-by-LinkedIn-Profile-Picture-Maker.jpg" />
	                                        			<a href="ConsultaPostulacion?postulanteConsultado=<%=user.getNickname()%>&nombreOfertaConsultada=<%= ofertaConsultada.getNombre()%>">Mi Postulación</a>
	                                    			</div>
	                                			</div>
	                          					<%
	                          				}
	                          			}
	                          		}
	                            %>
	                          </div>
	                      </div>
	                  </div>
	              </div>
	          </div>
	          <jsp:include page="../template/footer.jsp" />
	</body>
</html>