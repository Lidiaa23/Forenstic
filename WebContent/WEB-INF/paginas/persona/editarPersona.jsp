<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- Required meta tags -->
   		<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    	<!-- Bootstrap CSS -->
    	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
		<script src="https://kit.fontawesome.com/c819e18a95.js" crossorigin="anonymous"></script>
		<style>			
		</style>
		<title>Editar usuario</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />


		<form class="needs-validation" id="editarUsuarioForm" action="${pageContext.request.contextPath}/ServletControlador?accion=modificarUsuario&idPersona=${usuario.idPersona}" method="POST" novalidate>						
		
			<!-- Botones de navegación -->
			<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionEdicionUsuario.jsp" />
			
			<section id="details">
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-header">
									<h4>Editar usuario</h4>
								</div>
								<div class="card-body">
								
								
								
								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="usuario" class="form-label">Nombre
												usuario</label> <input value="${usuario.usuario}" type="text" class="form-control"
												id="usuario" name="usuario">
										</div>
									</div>
								</div>

								<br/>
										
								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="nombre" class="form-label">Nombre
												completo</label> <input value="${usuario.nombre}" type="text" class="form-control"
												id="nombre" name="nombre">
										</div>
									</div>
								</div>								

								<br />	
										
								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="correo" class="form-label">Correo electrónico</label> <input value="${usuario.correo}" type="text" class="form-control"
												id="correo" name="correo">
										</div>
									</div>
								</div>	
															
								<br />
													
								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="telefono" class="form-label">Teléfono</label> <input value="${usuario.telefono}" type="text" class="form-control"
												id="telefono" name="telefono">
										</div>
									</div>
								</div>	
															
								<br />
	
								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="contrasena1" class="form-label">Contraseña</label> 											
											<input type="password" class="form-control"
												id="contrasena1" name="contrasena1">
												<small id="casoHelp" class="form-text text-muted">Si no desea modificar la contraseña, deje estos campos en blanco.</small>
										</div>
										<div class="form-group">
											<label for="contrasena2" class="form-label">Por favor, repita la contraseña</label> <input type="password" class="form-control"
												id="contrasena2" name="contrasena2">
										</div>
									</div>																													
								</div>
								
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>

		</form>

		<!-- Pie de página -->
		<jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />
		
	    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
	    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
		
	</body>
</html>