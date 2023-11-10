<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="publicar.DtPaquete"%>
<%@page import="publicar.DtPaqueteTipo"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	    <jsp:include page="../template/head.jsp" />
	    <link rel="stylesheet" href="media/styles/consultaPaquete.css" />
	    <title>Trabajo.uy</title>
	</head>
	<body>
    	<jsp:include page="../template/navbar.jsp" />
	    <div class="container-fluid" id="web-content">
	        <div class="row justify-content-md-center">
	            <jsp:include page="../template/sidebar.jsp" />
				<div class="col col-md-9 col-sm-12" id="main-content">
	                <!--ACÁ VA TODO EL CONTENIDO ESPECÍFICO DE LA PÁGINA 
	                EJEMPLO:-->
	                <div class="container-fluid" id="main-container">
	                	<div class="row">
	                		<%
		                		DtPaquete paq = (DtPaquete) request.getAttribute("dataPaquete");
	                		%>
                    		<div class="col-md-3" id="postulanteConsultaInfo">
                        		<img class="card-img" src="media/imagenes/paquete.jpg" style="max-width: 550px; max-height: 550px;"/>
                            </div>
                            <div class="col">
                                <h2><%= paq.getNombre()%></h2>
                                <p><i class="" style="color: #000000;"></i> <b>Descripción:</b><%=paq.getDescripcion() %></p>
                                <p><i class=""></i> <b>Período:</b><%= paq.getPeriodoDeValidez() %> días</p>
                                <p><i class=""></i> <b>Descuento: </b><%= paq.getDescuento() %>%</p>
                                <p><i class=""></i> <b>Fecha: </b><%= paq.getFechaDeAlta() %></p>
                                <p><i class=""></i> <b>Costo: </b>$<%= paq.getCostoAsociado() %></p>
                                <p><i class=""></i> <b>Tipos de publicación: </b></p>
								<ul>
								    <%
								    List<DtPaqueteTipo> paqTs = paq.getPaquetesTipos();
								    if (paqTs == null) { %>
								        <li class="listarPaq">No se pueden comprar paquetes de ningún tipo</li>
								    <% } else {
								        for (int i = 0; i < paqTs.size(); i++) { %>
								            <li class="listarPaq"><a href="ConsultaTipo?tipo_consultado=<%=paqTs.get(i).getTipo().getNombre()%>"><%= paqTs.get(i).getTipo().getNombre()%>: <%= paqTs.get(i).getCantidad()%></a></li>
								        <% }
								    }
								    %>
								</ul>
								
								<%
								if ((boolean) request.getAttribute("paqComprado")){
									if ((boolean) request.getAttribute("compraExitosa")){%>
										<h5>Su compra fue realizada con exito!</h5>
									<%}else{%>
										<h5>Usted ya ha comprado este paquete.</h5>
									<%}
								}else {
									if ((boolean) request.getAttribute("esEmpresa")){%>
										
										<form action="CompraPaquete" method="POST">
									        <input type="hidden" name="paquete" value="<%=paq.getNombre() %>">
									        <button type="submit" class="btn btn-primary">Comprar</button>
									    </form>
									<%}
								} %>

                            </div>
	                    </div>
	                </div>
	        	</div>
	    	</div>
	    </div>
	    <jsp:include page="../template/footer.jsp" />
	</body>
</html>