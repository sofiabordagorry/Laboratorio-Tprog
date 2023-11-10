<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="publicar.DtOfertaLaboral"%>
<%@page import="publicar.DtKeyword"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <jsp:include page="../template/head.jsp" />
        <link rel="stylesheet" href="media/styles/altaOfertaLaboral.css">
        <title>Trabajo.uy</title>
    </head>
    <body>
        <jsp:include page="../template/navbar.jsp" />
        <div class="container-fluid" id="web-content">
            <div class="row justify-content-md-center">
                <jsp:include page="../template/sidebar.jsp" />
                <div class="col-md-9 col-sm-12" id="main-content">
					<form action="AltaDeOfertaLaboral" method="POST" class="formulario" id="formulario">
						<h3>Alta de Oferta Laboral</h3>
						<br>
						<% 
							DtOfertaLaboral dtOfer = (DtOfertaLaboral) request.getAttribute("datosOferta");
							//Map<String, DTKeyword> keys = dtOfer.getKeywords();
							List<DtKeyword> keys = dtOfer.getDataKeywords();
							String nomT = (String) request.getAttribute("nombreTipo");
							String error = (String) request.getAttribute("OLrepetida");
						%>
						<!-- Tipo de publicacion -->
						<div class="form-group formulario__grupo" id="grupo__tipo">
					    	<label for="tipo" class="form-label">Tipos de publicaciones para Ofertas Laborales</label>
					    	<br>
					    	<div class="input-group">
								<select class="form-select form-select-lg form-control custom-select" name="tipo" id="tipo">
									<option selected disabled value="">Elija alguno de los tipos...</option>
									
									<%
										String[] tipos = (String[]) request.getAttribute("tiposOL");
										for (int iter = 0; iter < tipos.length; iter++) { %>
										   
										    <option value="<%= tipos[iter] %>"><%= tipos[iter] %></option>
										  
									<%	}	%>

										
									<i class="formulario__validacion-estado fa-solid fa-circle-xmark"></i>
								</select>
								<div class="input-group-append">
									<i class="formulario__validacion-estado formulario__validacion-estado-select fa-solid fa-circle-xmark"></i>
								</div>
					    	</div>
							<p class="formulario__input-error">Debe elegir Tipo de Oferta Laboral.	</p>
						</div>
						
						<!-- Nombre de la Oferta -->
						<div class="form-group formulario__grupo" id="grupo__nombre">
					    	<label for="nombre" class="form-label">Nombre de la Oferta Laboral</label>
					    	<div class="input-group">
					    		<input type="text" class="form-control" name="nombre" id="nombre" placeholder="Nombre de la Oferta" value="<%=dtOfer.getNombre()%>"> 
					    		<div class="input-group-append">
									<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
								</div>
					    	</div>
					    	<p class="formulario__input-error">Debe ingresar un nombre.</p>
					    	<p class="formulario__input-error3">Ya existe una Oferta Laboral con este nombre.</p>
					  	</div>
					  	
					  	<div class = "form-group">
						<div class="row">
				  	<!-- Nombre de la ciudad -->
							<div class="col-md-6 formulario__grupo" id="grupo__ciudad">
						    	<label for="ciudad" class="form-label">Ciudad</label>
						    	<div class="input-group">
						    		<input type="text" class="form-control" name="ciudad" id="ciudad" placeholder="Ciudad de la Oferta" value="<%=dtOfer.getCiudad()%>"> 
						    		<div class="input-group-append">
										<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
									</div>
						    	</div>
						    	<p class="formulario__input-error">Debe ingresar una ciudad.</p>
						  	</div>
						  	
						  	<!-- Nombre del Departamento -->
							<div class="col-md-6 formulario__grupo" id="grupo__departamento">
						    	<label for="departamento" class="form-label">Departamento</label>
						    	<div class="input-group">
						    		<input type="text" class="form-control" name="departamento" id="departamento" placeholder="Departamento de la Oferta" value="<%= dtOfer.getDepartamento() %>"> 
						    		<div class="input-group-append">
										<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
									</div>
						    	</div>
						    	<p class="formulario__input-error">Debe ingresar un departamento.</p>
						  	</div>
					  	</div>
					</div>
					  	
					  	<!-- Descripcion de la Oferta y Keywords -->
						<div class="form-group">
							<div class="row">
								
								<!-- Descripcion -->
								<div class="col-md-6 formulario__grupo" id="grupo__descripcion">
									<label for="descripcion" class="form-label">Descripción</label>
									<div class="input-group">
										<textarea class="form-control" name="descripcion" id="descripcion" rows="3" placeholder="Descripción de la Oferta" ><%= dtOfer.getDescripcion()%></textarea>
										<div class="input-group-append">
											<i class="formulario__validacion-estado formulario__validacion-estado-caja fa-solid fa-circle-xmark"></i>
										</div>
									</div>
									<p class="formulario__input-error">Debe ingresar una descripción.</p>
								</div>
								
								<!-- Keywords -->
								<div class="col-md-6 formulario__grupo" id="grupo__keywords">
									<label for="keywords" class="form-label">Keywords</label>
									<br>
									<div class="input-group">
										<select multiple class="form-select form-control custom-select" aria-label="Multiple select example" name="keywords" id="keywords">
										
											<%
											String[] nomKeys = (String[]) request.getAttribute("keywordsNom");
							    			for (int iter = 0; iter < nomKeys.length; iter++) {
											%>
											
											<option value="<%=nomKeys[iter]%>"><%=nomKeys[iter]%></option>
											
											<%}%>
										</select>
										<div class="input-group-append">
											<i class="formulario__validacion-estado formulario__validacion-estado-caja fa-solid fa-circle-xmark"></i>
										</div>
									</div>
									<p class="formulario__input-error">Debe elegir al menos una keyword.</p>
								</div>
							</div>
						</div>
						
						<!-- Horario y remunarcion de la Oferta -->
						<div class = "form-group">
							<div class="row">
								
								<!-- Horario -->
								<div class="col-md-6 formulario__grupo" id="grupo__horario">
									<label for="horario" class = "form-label">Horario</label>
									<div class="input-group">
										<input type="text" class="form-control" name="horario" id="horario" placeholder="Ejemplo: 09:00 - 18:00" value= "<%= dtOfer.getHorario()%>">
										<div class="input-group-append">
											<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
										</div>
									</div>
									<p class="formulario__input-error">Debe ingresar un horario.</p>
									<p class="formulario__input-error2">Debe ingresar un horario correcto como el ejemplo.</p>
								</div>
								
								<!-- Remuneracion -->
								<div class="col-md-6 formulario__grupo" id="grupo__remuneracion">
									<label for ="remuneracion" class = "form-label">Remuneración</label>
									<div class="input-group">
										<input type="number" class="form-control" name="remuneracion" id="remuneracion" placeholder="Solo números" value= "<%= dtOfer.getRemuneracion()%>">
										<div class="input-group-append">
											<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid fa-circle-xmark"></i>
										</div>
									</div>
									<p class="formulario__input-error">Debe ingresar una remuneración.</p>
									<p class="formulario__input-error2">La remuneración debe ser mayor a cero.</p>
								</div>
							</div>
						</div>
						
						<!-- Forma de pago ->
						<div class="form-group formulario__grupo" id="grupo__tipoCompra">
							<label for="tipoCompra" class="form-label">Forma de Pago</label>
							<div class="input-group">
								<select class="form-select form-select-lg form-control custom-select" name="tipoCompra" id="tipoCompra">
									<option selected disabled value="">Elija la forma de Pago...</option>
									<option value="general">General</option>
									<option value="paquete">Por Paquete</option>
								</select>
								<div class="input-group-append">
									<i class="formulario__validacion-estado formulario__validacion-estado-select fa-solid fa-circle-xmark"></i>
								</div>
							</div>
							<p class="formulario__input-error">Debe elegir una forma de pago.</p>
						</div>
							
							<!--Paquetes comprados->
						<div class="formulario__grupo" id="grupo__paquetes">
							<div class="form-group paquetes-estado">
								<label for="paquetes" class="form-label">Paquetes comprados</label>
								<div class="input-group">
									<select class="form-select form-select-lg form-control custom-select" name="paquetes" id="paquetes">
										<option selected disabled value="">Elegir Paquete...</option>
										<option value="paquetePremium">Paquete Básico</option>
									</select>
									<div class="input-group-append">
										<i class="formulario__validacion-estado formulario__validacion-estado-select fa-solid fa-circle-xmark"></i>
									</div>
								</div>
								<p class="formulario__input-error">Debe elegir un Paquete.</p>
							</div>
						</div> -->
						<div class="formulario__grupo formulario__grupo-btn-aceptar-cancelar">
							 <div class="row">
								<div class="col-md-4">
									<button type="submit" class="btn btn-primary formulario__btn">Aceptar</button>
									<p class="formulario__mensaje-exitoso" id="formulario__mensaje-exitoso">Oferta Laboral ingresada correctamente.</p>
								</div>
								<div class="col-md-1">
									<a class="btn btn-secondary" href="./indexEmpresa.html" role="button">Cancelar</a>
								</div>
							</div>
						</div>
					</form>
				</div>
            </div>
        </div>

        <jsp:include page="../template/footer.jsp" />
		<script src="media/javaScript/altaOfertaLaboralValidacion.js"></script>
    </body>
</html>
