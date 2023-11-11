<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="publicar.DtPostulacion"%>
<%@ page import="publicar.DtPostulante"%>
<%@ page import="publicar.DtOfertaLaboral"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <jsp:include page="../template/head.jsp" />
	    <title>Trabajo.uy</title>
	</head>
	<body>
		<jsp:include page="../template/navbar.jsp" />
	    <div class="container-fluid" id="web-content">
	        <div class="row justify-content-md-center">
	           	<jsp:include page="../template/sidebar.jsp" />
	            <div class="col col-md-9 col-sm-12" id="main-content">
	                <div class="col ofertaLaboral">
	                  	<h2>Postulación</h2>
	                    <div class="card mb-3">
	                        <div class="row no-gutters">
	                        	<%
	                        		DtPostulacion dtpostulacion = (DtPostulacion) request.getAttribute("dataPostulacion");
	                        		DtPostulante post = (DtPostulante) request.getAttribute("usuarioPostulacion");
	                        		String nombreOferta = (String) request.getAttribute("nombreOferta");
	                        		DtOfertaLaboral oferta =(DtOfertaLaboral) request.getAttribute("oferta");
	                        	%>
	                          	<div class="col-md-3">
	                            	<img src="media/imagenes/NoImageUser.png" class="card-img" alt="..." >
	                          	</div>
	                          	<div class="col-md-9">
		                            <div class="card-body">
										<h4 class="card-title"><%= post.getNombre() %> <%= post.getApellido() %></h4>
			                            <h5 class="card-title">Datos Postulacion:</h5>
			                            <p class="card-text">Se postuló a <%= dtpostulacion.getOferta() %> en la fecha <%= dtpostulacion.getFecha() %></p>
			                            <h5 class="card-title">Currículo reducido:</h5>
			                            <p class="card-text"><%= dtpostulacion.getCVReducido() %></p>
			                            <h5 class="card-title">Motivación:</h5>
		                             	<p class="card-text"><%= dtpostulacion.getMotivacion() %></p>
		                             	<%
		                             		if(dtpostulacion.getRank() != -1) {
		                             	%>
		                             	<h5 class="card-title">Puesto: <%= dtpostulacion.getRank() %></h5>
		                             	<%
		                             		}
		                             	%>
										<h5 class="card-title">Video de la postulación:</h5>
										<div class="card-text">
										    <%
										    if (dtpostulacion.getVideo() != "" && !dtpostulacion.getVideo().equals("SV")){
				                        		String videoURL = (String) request.getAttribute("videoURL");
										    
										    	if(videoURL != null && !videoURL.isEmpty()) {
										    %>
										        <iframe width="560" height="315" src="https://www.youtube.com/embed/<%= videoURL %>" frameborder="0" allowfullscreen></iframe>
										    <%
										    	} 
										    }else {
										    %>
										        <p>No hay video disponible para esta postulación.</p>
										    <%
										    }			  
										    %>
										</div>
		                            </div>
								</div>
								  <%		
										    if ( oferta.getEstado().value() != "Finalizada"){
				               
										    %>
											<div class="col-md-8">
											<div class="card-body">
												<button class="btn"><a href="ConsultaOfertaLaboral?oferta_consultada=<%=nombreOferta%>">Ver oferta</a></button>
											</div>
										</div>
										 <%
										  } 
										 %>
	                        </div>
						</div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../template/footer.jsp" />
  	</body>
</html>