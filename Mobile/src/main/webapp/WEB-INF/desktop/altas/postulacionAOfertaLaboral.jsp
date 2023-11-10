<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<head>
		<meta charset="UTF-8">
		<jsp:include page="../template/head.jsp" />
		<link rel="stylesheet" href="media/styles/styles.css">
				<link rel="stylesheet" href="media/styles/postulacion.css">
	
		<title>Trabajo.uy</title>
		<style>
			.large-input {
			  height: 100px; /* Ajusta la altura según tus preferencias */
			  resize: none; /* Evita que el usuario pueda cambiar el tamaño */
			}
			
			label {
			padding: 10px;}
		</style>
	</head>
<body>
	<jsp:include page="../template/navbar.jsp" />
		<div class="container-fluid" id="web-content">
			<div class="row justify-content-md-center">
				<div class="col-xs-12 col-md-10">
					<div id="postulacionContainer" class="d-flex flex-column min-vh-100 justify-content-center">
					
										
						<!-- Formulario -->
						<form action="PostularAOfertaLaboral" method="POST" class="formulario" id="formularioPos">
							<h3>Ingrese sus datos para la postulación</h3>
							<br>
							
							<div class="form-group">
								<div class="row">
								<% 
								boolean yaSePostulo = false; // Valor predeterminado: asumimos que el usuario no se ha postulado
								Object yaSePostuloAttr = request.getAttribute("yaSePostulo");
								if (yaSePostuloAttr != null) {
								    String yaSePostuloStr = yaSePostuloAttr.toString();
								    yaSePostulo = Boolean.parseBoolean(yaSePostuloStr);
								}

										%>

									      <input type="hidden" name="yaSePostulo" value="<%= yaSePostulo %>">
									
									
									<div class="col-md-6 formulario__grupo" id="grupo__CVReducido">
										<label for="CVReducido">CV Reducido</label>
										<div class="input-group">
										  <input type="text" class="form-control large-input" name="CVReducido" id="CVReducido" placeholder="Ingresar CV reducido">
											<div class="input-group-append">
												<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid"></i>
											</div>
										</div>
										<p class="formulario__input-error">Debe ingresar un CV Reducido.</p>
									</div>
									
									<div class="col-md-6 formulario__grupo" id="grupo__motivacion">
										<label for="motivacion">Motivación</label>
										<div class="input-group">
  											<input type="text" class="form-control large-input" name="motivacion" id="motivacion" placeholder="Ingresar motivación">
  											<div class="input-group-append">
												<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid"></i>											
											</div>
										</div>
										<p class="formulario__input-error">Debe ingresar una motivación.</p>
									</div>
									
																
									
									<div class="col-md-5" id="grupo__link">
											<label for="link">Link (opcional)</label>
											<div class="input-group">
												<input type="text" class="form-control" name="link" id="link" placeholder="Ingresar link de youtube">
																							  <div class="input-group-append">
													<i class="formulario__validacion-estado formulario__validacion-estado-input fa-solid"></i>
												</div>
											</div>
											<p class="formulario__input-error">Debe ingresar un link válido.</p>
										</div>
								</div>
							</div>
							<br>
							
							<div class="formulario__grupo formulario__grupo-btn-registrarse">
							    <!-- Cambia el tipo de botón a "button" y agrega un id -->
							    <button type="submit" class="btn" id="botonPostularme">Postularme</button>
								<% if (request.getAttribute("postulacionExitosa") != null && (boolean) request.getAttribute("postulacionExitosa")) { %>
								  <div class="alert alert-success alert-dismissible fade show" role="alert">
								    ¡Postulación realizada con éxito! Se ha postulado correctamente a la oferta laboral.
								    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
								      <span aria-hidden="true">&times;</span>
								    </button>
								  </div>
								<% } %>
							</div>

							
						</form>
		           	</div>				
				</div>
			</div>
		</div>
		<jsp:include page="../template/footer.jsp" /> 
		<script src="media/javaScript/postulacionValidacion.js"></script>
				<script>
		  $(".alert").alert();
		</script>
	</body>
</html>