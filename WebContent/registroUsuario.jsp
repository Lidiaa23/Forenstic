<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
<!-- Bootstrap CSS -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/c819e18a95.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>

<!-- Bootstrap JavaScript -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-growl/1.0.0/jquery.bootstrap-growl.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<title>Registrarse</title>
</head>
<body>

	<!-- Cabecero -->
	<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

	<!-- Botones de navegación -->
	<!--<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsulta.jsp" />-->
	<br />
	<br />
	<section id="details">
		<div class="container-fluid" style="max-width: 1500px">
			<div class="row">
				<div class="col">	

					<div class="card mx-auto">

						<div class="card-header">
							<h4>
								<i class="fas fa-user-plus"></i> Nuevo usuario
							</h4>
						</div>

						<div class="card-body">

							<form class="needs-validation" id="agregarUsuarioForm" action="${pageContext.request.contextPath}/ServletControlador?accion=insertarUsuario" method="POST" novalidate>						
							
								<!-- NOMBRE USUARIO -->
								<div class="row justify-content-md-center">
									<div class="col-5">
										<div class="form-group input-group">
											<div class="input-group-prepend">
												<span class="input-group-text">
													<i class="fa fa-user-tag"></i>
												</span>
											</div>
											<input name="usuario" class="form-control" placeholder="Nombre usuario" type="text" required>
										    <div class="invalid-feedback">
									          Por favor, indique un nombre de usuario.
									        </div>
										</div>
								
									</div>
								</div>
								
								<!-- NOMBRE COMPLETO -->
								<div class="row justify-content-md-center">
									<div class="col-5">
										<div class="form-group input-group">
											<div class="input-group-prepend">
												<span class="input-group-text"> <i class="fa fa-user"></i>
												</span>
											</div>
											<input name="nombre" class="form-control" placeholder="Nombre completo" type="text" required>
									        <div class="invalid-feedback">
									          Por favor, indique un nombre completo.
									        </div>
										</div>
									</div>
								</div>
								
								<!-- CORREO ELECTRÓNICO -->
								<div class="row justify-content-md-center">
									<div class="col-5">
										<div class="form-group input-group">
											<div class="input-group-prepend">
												<span class="input-group-text"> <i
													class="fa fa-envelope"></i>
												</span>
											</div>
											<input name="correoElectronico" class="form-control" placeholder="Correo electrónico" type="email" required>
											<div class="invalid-feedback">
									          Por favor, indique un correo electrónico.
									        </div>
										</div>
									</div>
								</div>
								
								<!-- TELÉFONO -->
								<div class="row justify-content-md-center">
									<div class="col-5">
										<div class="form-group input-group">
											<div class="input-group-prepend">
												<span class="input-group-text"> <i
													class="fa fa-phone"></i>
												</span>
											</div>
											<input name="telefono" class="form-control" placeholder="Teléfono" type="text" required>
											 <div class="invalid-feedback">
									          Por favor, indique un número de teléfono.
									        </div>
										</div>
									</div>
								</div>
								
								<!-- CONTRASEÑA -->
								<div class="row justify-content-md-center">
									<div class="col-5">
										<div class="form-group input-group">
											<div class="input-group-prepend">
												<span class="input-group-text"> <i class="fa fa-lock"></i>
												</span>
											</div>
											<input name="contrasena1" class="form-control" placeholder="Contraseña" type="password" required>
											<div class="invalid-feedback">
									          Por favor, indique una contraseña.
									        </div>
										</div>
									</div>
								</div>
								
								<!-- REPETIR CONTRASEÑA -->
								<div class="row justify-content-md-center">
									<div class="col-5">
										<div class="form-group input-group">
											<div class="input-group-prepend">
												<span class="input-group-text"> <i class="fa fa-lock"></i>
												</span>
											</div>
											<input name="contrasena2" class="form-control" placeholder="Por favor, repita la contraseña" type="password" required>
											<div class="invalid-feedback">
									          Por favor, repita la contraseña.
									        </div>
										</div>
									</div>
								</div>

								<div class="row justify-content-md-center">
									<div class="col-5">
										<div class="form-group">
											<button type="submit" class="btn btn-primary btn-block">
												Crear cuenta</button>
										</div>
									</div>
								</div>
								
								<div class="row justify-content-md-center">
									<div class="col-5">
										<p class="text-center">
											¿Ya tiene una cuenta? <a href="${pageContext.request.contextPath}/login.jsp">Identificarse</a>
										</p>
									</div>
								</div>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Pie de página -->
	<jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />
	
	<script type="text/javascript">
	
		// Example starter JavaScript for disabling form submissions if there are invalid fields
		(function () {
		  'use strict'
	
		  // Fetch all the forms we want to apply custom Bootstrap validation styles to
		  var forms = document.querySelectorAll('.needs-validation')
	
		  // Loop over them and prevent submission
		  Array.prototype.slice.call(forms)
		    .forEach(function (form) {
		      form.addEventListener('submit', function (event) {
		        if (!form.checkValidity()) {
		          event.preventDefault()
		          event.stopPropagation()
		        }
	
		        form.classList.add('was-validated')
		      }, false)
		    })
		})()
		
	</script>

</body>
</html>