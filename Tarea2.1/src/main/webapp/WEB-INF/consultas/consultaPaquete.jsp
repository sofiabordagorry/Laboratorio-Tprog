<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="logica.DTPaquete"%>
<%@page import="logica.DTPaqueteTipo"%>
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
		                		DTPaquete paq = (DTPaquete) request.getAttribute("dataPaquete");
	                		%>
                    		<div class="col-md-3" id="postulanteConsultaInfo">
                        		<img class="card-img" src="https://imgv3.fotor.com/images/gallery/a-woman-linkedin-picture-with-grey-background-made-by-LinkedIn-Profile-Picture-Maker.jpg" style="max-width: 550px; max-height: 550px;"/>
                            </div>
                            <div class="col">
                                <h2><%= paq.getNombre()%></h2>
                                <p><i class="" style="color: #000000;"></i> <b>Descripción:</b><%=paq.getDescripcion() %></p>
                                <p><i class=""></i> <b>Período:</b><%= paq.getPeriodoDeValidez() %></p>
                                <p><i class=""></i> <b>Descuento: </b><%= paq.getDescuento() %></p>
                                <p><i class=""></i> <b>Fecha: </b><%= paq.getFechaDeAlta() %></p>
                                <p><i class=""></i> <b>Costo: </b><%= paq.getCostoAsociado() %></p>
                                <p><i class=""></i> <b>Tipos de publicación: </b></p>
								<ul>
								    <%
								    DTPaqueteTipo[] paqTs = paq.getPaqueteTipos();
								    if (paqTs == null) { %>
								        <li>No se pueden comprar ofertas de ningún tipo</li>
								    <% } else {
								        for (int i = 0; i < paqTs.length; i++) { %>
								            <li><%= paqTs[i].getTipo().getNombre()%>: <%= paqTs[i].getCantidad()%></li>
								        <% }
								    }
								    %>
								</ul>
								
								<%
								if ((boolean) request.getAttribute("paqComprado")){
									if ((boolean) request.getAttribute("compraExitosa")){%>
										<h5>Su compra fue realizada con exito!</h5>
									<%}else{%>
										<h5>Usted ya había comprado este paquete.</h5>
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