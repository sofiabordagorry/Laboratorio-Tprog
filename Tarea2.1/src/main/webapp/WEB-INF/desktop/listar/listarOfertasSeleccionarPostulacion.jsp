<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="publicar.DtOfertaLaboral"%>
<%@ page import="publicar.EstadoOL"%>
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
	                  	<h2>Ofertas para seleccionar postulación</h2>
	                  	<%
                       		List<DtOfertaLaboral> seleccionarPostulacion = (List<DtOfertaLaboral>) request.getAttribute("ofertasSeleccionarPostulante");
                     		if(seleccionarPostulacion.size() != 0) {
                       			for(int i = 0; i < seleccionarPostulacion.size(); i++) {
                       				if (!seleccionarPostulacion.get(i).isRankeada() && seleccionarPostulacion.get(i).getEstado() != EstadoOL.FINALIZADA){
						%>
	                  	
	                    <div class="card mb-3" style="max-width: 650px;">
	                        <div class="row no-gutters">
	                          	<div class="col-md-4">
	                            	<img src="media/imagenes/ofertaSinFoto.jpg" class="card-img" alt="...">
	                          	</div>
	                          	<div class="col-md-8">
	                            	<div class="card-body">
	                              		<h5 class="card-title"><%= seleccionarPostulacion.get(i).getNombre() %></h5>
	                              		<p class="card-text"><%= seleccionarPostulacion.get(i).getDescripcion() %></p>
	                              		<button class="btn"><a href="ConsultaOfertaLaboral?oferta_consultada=<%=seleccionarPostulacion.get(i).getNombre()%>&seleccionar_postulantes=<%=true%>">Ver oferta</a></button>
	                            	</div>
	                          	</div> 
	                        </div>
						</div>
						<%
                       				}}
                     		} else {
                     			String error = (String) request.getAttribute("sinOfertasSeleccionarPostulante");
                       	%>
                       	<h1><%=error%></h1>
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