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
	                  	<h2>Mis Postulaciones</h2>
	                  	<%
                       		DTOfertaLaboral[] postulaciones = (DTOfertaLaboral[]) request.getAttribute("misPostulaciones");
                     		if(postulaciones.length != 0) {
                       			for(int i = 0; i < postulaciones.length; i++) {
						%>
	                  	
	                    <div class="card mb-3" style="max-width: 650px;">
	                        <div class="row no-gutters">
	                          	<div class="col-md-4">
	                            	<img src="https://kinsta.com/es/wp-content/uploads/sites/8/2021/12/front-end-developer-1024x512.png" class="card-img" alt="...">
	                          	</div>
	                          	<div class="col-md-8">
	                            	<div class="card-body">
	                              		<h5 class="card-title"><%= postulaciones[i].getNombre() %></h5>
	                              		<p class="card-text"><%= postulaciones[i].getDescripcion() %></p>
	                              		<button class="btn"><a href="ConsultaOfertaLaboral?oferta_consultada=<%=postulaciones[i].getNombre()%>">Ver oferta/postulacion</a></button>
	                            	</div>
	                          	</div>
	                        </div>
						</div>
						<%
                       			}
                     		} else {
                       	%>
                       	<h1 class="">No hay niguna postulación.</h1>
                       	<%
                     		}
                       	%>
					</div>
				</div>
	   		</div>
	    </div>
	    <jsp:include page="../template/footer.jsp" />
  	</body>
</html>
