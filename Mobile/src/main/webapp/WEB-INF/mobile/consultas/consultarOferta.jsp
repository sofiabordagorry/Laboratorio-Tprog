<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="publicar.DtCompra" %>
<%@ page import="publicar.DtEmpresa" %>
<%@ page import="publicar.DtKeyword" %>
<%@ page import="publicar.DtOfertaLaboral" %>
<%@ page import="publicar.DtPostulante" %>
<%@ page import="publicar.DtTipo" %>
<%@ page import="publicar.DtPaquete" %>
<%@ page import="publicar.DtUsuario" %>
<%@ page import="publicar.DtPostulacion"%>
<%@ page import="publicar.EstadoOL" %>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%
	DtOfertaLaboral ofertaConsultada = (DtOfertaLaboral) request.getAttribute("oferta_laboral");
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
        <div class="col-md-9 col-sm-12" id="main-content">
            <div class="container-fluid" id="consultaOferta">
                <div class="row">
                    <div class="col-md-8 col-sm-12" id="ofertaInfo">
                        <div>
                            <img class="imagenOferta mx-auto d-block p-2"
                                src="media/imagenes/ofertaSinFoto.jpg" />
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
                                //Map<String, DTKeyword> keywords = ofertaConsultada.getKeywords();
                            	//for (Map.Entry<String, DTKeyword> entry : keywords.entrySet()) {
                            	List<DtKeyword> keywords = ofertaConsultada.getDataKeywords();
								for(int i=0;i <keywords.size();i++){
                            %>
                                <%= keywords.get(i).getNombre() + "\n" %>
                            <%
                                }
                            %>
                            </p>
                        </div>
                        <div>
                            <%	
                            	Boolean vigente = (Boolean) request.getAttribute("vigente");
                                DtUsuario user = (DtUsuario) session.getAttribute("usuario_logueado");
                                Boolean existe_postulacion = (Boolean) request.getAttribute("existe_post");
                                if (user instanceof DtPostulante) {
                                	if(!existe_postulacion && vigente) {
                            %>
                            <button class="btn mx-auto d-block">
                                <a href="PostulacionAOfertaLaboral?oferta=<%= ofertaConsultada.getNombre() %>">Postularse</a>
                            </button>
                            <%
                                	} else if (!vigente) {
                                	%>	
                                		<p class="text-center">Esta oferta ya expiró</p>
                                	<%
                                	}
                              }
                            %>
                        </div>
                    </div>

                    <!-- Moved the postulacionesPaquetesCol div outside the inner if statements -->
                    <div class="col col-md-4 col-sm-12" id="postulacionesPaquetesCol">
                        <div id="postulaciones">
                            <%
                                List<DtPostulacion> postulaciones = ofertaConsultada.getDataPostulaciones();
                                if (user != null) {
                                	
	                             if (user instanceof DtPostulante) {
	                                 if (existe_postulacion) {
                            %>
                            <div class="postulante">
                                <h2>Postulación</h2>
                                <div class="postulante">
                                    <img width="50" height="50" class="img-fluid img-thumbnail"
                                        src="media/imagenes/NoImageUser.png" />
                                    <a href="ConsultaPostulacion?postulanteConsultado=<%=user.getNickname()%>&oferta_consultada=<%=ofertaConsultada.getNombre()%>">Mi Postulación</a>
                                </div>
                            </div>
                            <%
                                        } else {
                            %>
                            <div class="postulante">
                                <h2>Postulacion</h2>
                                <div class="postulante">
                                    <p>Aún no te has postulado a esta oferta laboral</p>
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
    </div>
</div>
<jsp:include page="../template/footer.jsp" />
<script src="media/javaScript/consultaOfertaLaboral.js"></script>
</body>
</html>