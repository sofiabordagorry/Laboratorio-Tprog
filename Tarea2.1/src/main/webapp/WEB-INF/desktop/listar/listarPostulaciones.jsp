<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="publicar.DtOfertaLaboral"%>
<%@ page import="java.util.List"%>
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
	                  		String nick =  (String) request.getAttribute("nicknameConsultado");
                       		List<DtOfertaLaboral> postulaciones = (List<DtOfertaLaboral>) request.getAttribute("misPostulaciones");
                     		if(postulaciones.size() != 0) {
                       			for(int i = 0; i < postulaciones.size(); i++) {
						%>
	                  	
	                    <div class="card mb-3" style="max-width: 650px;">
	                        <div class="row no-gutters">
	                          	<div class="col-md-4">
	                            	<img src="media/imagenes/ofertaSinFoto.jpg" class="card-img" alt="...">
	                          	</div>
	                          	<div class="col-md-8">
	                            	<div class="card-body">
	                              		<h5 class="card-title"><%= postulaciones.get(i).getNombre() %></h5>
	                              		<p class="card-text"><%= postulaciones.get(i).getDescripcion() %></p>
	                              		<button class="btn"><a href="ConsultaPostulacion?oferta_consultada=<%=postulaciones.get(i).getNombre()%>&postulanteConsultado=<%=nick%>">Ver oferta/postulación</a></button>
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