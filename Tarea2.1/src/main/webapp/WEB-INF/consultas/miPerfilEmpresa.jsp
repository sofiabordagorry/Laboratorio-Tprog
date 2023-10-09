<%@page import="logica.*"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
	<head>
	    <meta charset="UTF-8">
	   	<jsp:include page="../template/head.jsp" />
	    <link rel="stylesheet" href="media/styles/stylesConsultas.css" />
	    <title>Trabajo.uy</title>
	</head>
	<body>
	    <jsp:include page="../template/navbar.jsp" />
	    <div class="container-fluid" id="web-content">
	        <div class="row justify-content-md-center">
	            <jsp:include page="../template/sidebar.jsp" />
	            <div class="col col-md-9 col-sm-12" id="main-content">
	                <div class="container-fluid" id="main-container">
	                	<div class="row">
		                	<%
		                		DTUsuario userInfo = (DTUsuario) request.getAttribute("userData");
		                		DTEmpresa eInfo = null;
		                		eInfo = (DTEmpresa) userInfo;
		                		Map<String, DTOfertaLaboral> offers = eInfo.getDTOfertasLaborales();
		                	%>
	                    	<div class="col-md-3" id="empresaPefilInfo">
                        		<img class="card-img" src="media/imagenes/NoImageUser.png" style="max-width: 550px; max-height: 550px;"/>
                            </div>
                            <div class="col">
                                <h2><%=eInfo.getNickname() %></h2>
                                <p><i class="" style="color: #000000;"></i> <b>Nombre:</b><%=eInfo.getNombre() %></p>
                                <p><i class=""></i> <b>Apellido:</b><%=eInfo.getApellido() %></p>
                                <p><i class=""></i> <b>Correo: </b><%=eInfo.getCorreo() %></p>
                                <p><i class=""></i> <b>Descripcion: </b><%=eInfo.getDescripcion() %></p>
                                <p><i class=""></i> <b>Link: </b> <a href="#"><%=eInfo.getLink() %>></a></p>
							</div>
	                    </div>
	                    <div class ="row" style="margin-top: 1%;">
							<div class = "col-md-6 text-center">
		                        <h3>Ofertas Laborales</h3>
		                        <ul class="list-group" id="ofertas-laborales">
			                        <%
			                        	for(Map.Entry<String, DTOfertaLaboral> entry : offers.entrySet()){
			                        %>
			                        <li class="list-group-item text-center"><a href="./ConsultaOfeLabDFemp.html"><%=entry.getValue().getNombre() + "(" + entry.getValue().getEstado() + ")" %></a></li>
									<%
										}
									%>
								</ul>
							</div>
							<div class = "col-md-6 text-center">
		                        <h3>Compras de Paquetes</h3>
		                        <table class="table" id="tablaPrincipal">
									<thead>
									    <tr>
									      <th>Nombre de Paquete</th>
									      <th>Precio</th>
									      <th>Fecha de Vencimiento</th>
									    </tr>
								  	</thead>
									<tbody>
										<%
											List<DTCompra> paquetesComprados = eInfo.getPaqComprados();
											for(DTCompra compra : paquetesComprados){
										  	//ACA TENDRIA QUE PONER LA LOGICA DE LOS PAQUETES COMPRADOS
										%>
									    <tr>
									    	<td><a href="#"><%=compra.getPaqueteComprado().getNombre()%></a></td>
											<td><%=compra.getPaqueteComprado().getCostoAsociado()%></td>
											<td><%=compra.getFechaVencimiento() %></td>
									    </tr>
										<%
											}
										%>
									</tbody>
								</table>
								<%
									//POR CADA PAQUETE COMPRADO TENGO QUE ARMAR UNA TABLA DE LOS TIPOS QUE TIENE Y OCULTARLAS TODAS
								%>
		                        <!--<table class="table" id="tablaSecundaria1">
									<thead>
									    <tr>
									      <th>Tipo</th>
									      <th>Cantidad Restante</th>
									    </tr>
								  	</thead>
									<tbody>
										<tr>
											<td><a href="./ConsultaTipoPremium-ecotech.html">Premium</a></td>
									      	<td>1</td>
									    </tr>
									    <tr>
									      	<td><a href="./ConsultaTipoDestacada-ecotech.html">Destacada</a></td>
		  							      	<td>1</td>
									    </tr>
									    <tr>
									      	<td><a href="ConsultaTipoEstandar-ecotech.html">Estándar</a></td>
		  							      	<td>2</td>
									    </tr>
									</tbody>
								</table>-->
								<%
									//ACA TERMINA LA TABLA	
								%>
							</div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    <jsp:include page="../template/footer.jsp" />
		<script src="media/javaScripts/consultaEmpresa.js"></script>
	</body>
</html>