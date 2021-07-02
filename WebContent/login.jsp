<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"
	integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<script src="https://kit.fontawesome.com/c819e18a95.js"
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
	integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
	integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
	crossorigin="anonymous"></script>
<!-- Bootstrap JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-growl/1.0.0/jquery.bootstrap-growl.min.js"></script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<title>Login</title>
</head>
<body>

	<!-- Cabecero -->
	<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

	<!-- Botones de navegación -->
	<!--<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsulta.jsp" />-->
	<br />
	<br />
	<section id="details">
				
		<div class="container-fluid" style="max-width: 500px">
			<div class="row">
				<div class="col">
					<div class="card mx-auto">

						<div class="card-header">
							<h4>
								<i class="fas fa-user-friends"></i> Sign in
							</h4>
						</div>

						<div class="card-body">
						
						
						
							<%
							Object message = request.getAttribute("logoutOK");
							
							if (message != null) {%>					
								<div class="alert alert-primary alert-dismissible fade show" role="alert">
									<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
									Has salido de la sesión correctamente.					
								</div>	
							<% }%>
							
							<%
							Object usuarioRegistradoOK = request.getAttribute("usuarioRegistradoOK");
							String usuarioRegistradoOKstr = (String) usuarioRegistradoOK;
							
							if (usuarioRegistradoOK != null && usuarioRegistradoOKstr.equalsIgnoreCase("usuarioRegistradoOK")) {%>					
								<div class="alert alert-primary alert-dismissible fade show" role="alert">
									<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
									Se ha registrado su usuario correctamente. Ya puede iniciar sesión con su nuevo usuario.				
								</div>	
							<% }%>
							
							<form id="loginForm" action="${pageContext.request.contextPath}/ServletControlador?accion=loginUsuario" method="POST">
								<h1 class="h3 mb-3 font-weight-normal">Indicar usuario</h1>
								<label for="usuario" class="sr-only">Usuario</label>
								 <input
									type="text" id="usuario" name="usuario" class="form-control"
									placeholder="Usuario" required autofocus> 
									<br/>
		
									<label 
									for="contrasena" class="sr-only">Contraseña</label> <input
									type="password" name="contrasena" id="contrasena" class="form-control"
									placeholder="Contraseña" required>
								<div class="checkbox mb-3">
								
									<label> <br/><input type="checkbox" value="remember-me">
										Recuérdame
									</label>
								</div>
								<button class="btn btn-lg btn-secondary btn-block" type="submit">Entrar</button>

								<br/>
							</form>
							
							<a href="${pageContext.request.contextPath}/registroUsuario.jsp" class="link-primary">Registrarse</a>							

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- Pie de página -->
	<jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />

</body>
</html>