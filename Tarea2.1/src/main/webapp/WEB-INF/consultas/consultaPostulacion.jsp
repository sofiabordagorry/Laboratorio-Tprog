<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.trabajouy.model.*"%>
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
	                        		DTPostulacion dtpostulacion = (DTPostulacion) request.getAttribute("dataPostulacion");
	                        		Postulante post = (Postulante) request.getAttribute("usuarioPostulacion");
	                        	%>
	                          	<div class="col-md-3">
	                            	<img src="https://imgv3.fotor.com/images/gallery/a-woman-linkedin-picture-with-grey-background-made-by-LinkedIn-Profile-Picture-Maker.jpg" class="card-img" alt="..." >
	                          	</div>
	                          	<div class="col-md-9">
		                            <div class="card-body">
										<h4 class="card-title"><%= post.getNombre() %> <%= post.getApellido() %></h4>
			                            <h5 class="card-title">Datos Postulacion:</h5>
			                            <p class="card-text">Se postuló a <%= dtpostulacion.getOferta() %> en la fecha <%= dtpostulacion.getFecha() %>.</p>
			                            <h5 class="card-title">Currículo reducido:</h5>
			                            <p class="card-text"><%= dtpostulacion.getCVReducido() %></p>
			                            <h5 class="card-title">Motivación:</h5>
		                             	<p class="card-text"><%= dtpostulacion.getMotivacion() %>.</p>
		                              	<button class="btn"><a href="./consultaOfeLabDFlgarcia.html">Volver a oferta</a></button>
		                            </div>
								</div>
	                        </div>
						</div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../template/footer.jsp" />
  	</body>
</html>