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
<link href="tail.select-bootstrap4.css" rel="stylesheet">
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
	
<script src="tail.select-full.min.js"></script>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<title>Agregar cadena de custodia</title>
</head>

		<!--  <style>
		
			html {
				height: 100%;
			}
			
			body {
				min-height: 100%;	
				display: grid;
			}
			
			footer {
				grid-column: 1 / -1;
			}
		
		</style>-->
		
<body>

	<!-- Cabecero -->
	<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

	<!-- Botones de navegación -->
	<!--<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsulta.jsp" />-->
	<br />
	<br />
	<section id="details">
		<div class="container-fluid" style="max-width: 1650px">
			<div class="row">
				<div class="col">
					<div class="card mx-auto">

						<div class="card-header">
							<h4>
								<i class="fas fa-link"></i> Agregar cadena de custodia
							</h4>
						</div>

						<div class="card-body">
						
							<form id="agregarCadenaCustodiaAlDispositivoForm"
								action="${pageContext.request.contextPath}/ServletControlador?accion=insertarCadenaCustodia"
								method="POST" enctype="multipart/form-data">

								<input type="hidden" name="idDispositivo" value="${idDispositivo}">
								
								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="localizacion" class="form-label">Localización</label> <input type="text" class="form-control"
												id="localizacion" name="localizacion">
										</div>
									</div>
								</div>

								<br/>

									<div class="row justify-content-md-center">									
										<div class="col-7">										
											<div class="form-group">
											    <label for="fecha" class="form-label">Fecha</label>
											    <input type="date" class="form-control" id="fecha" name="fecha">
											</div>
										</div>
									</div>
										<br />
										
									<div class="row justify-content-md-center">									
										<div class="col-7">										
											<div class="form-group">
											    <label for="hora" class="form-label">Hora</label><br />
											    <input type="time" class="form-control" id="hora" name="hora">
											</div>
										</div>
									</div>

								<br />
								
								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="razon" class="form-label">Razón</label>
											<input type="text" class="form-control" id="razon" name="razon">
										</div>
									</div>
								</div>

								<br />			
								
								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="agenciaInstitucion" class="form-label">Agencia / Institución</label>
											<input type="text" class="form-control" id="agenciaInstitucion" name="agenciaInstitucion">
										</div>
									</div>
								</div>

								<br />		
								
								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="persona" class="form-label">Persona</label>
											<input type="text" class="form-control" id="persona" name="persona">
										</div>
									</div>
								</div>

								<br />
								<button type="submit" class="btn btn-primary">Agregar</button>

							</form>
						
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