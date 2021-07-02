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
<title>Agregar evidencia digital</title>
</head>
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
								<i class="fas fa-laptop"></i> Agregar evidencia digital al dispositivo [${idDispositivo}] ${nombreDispositivo}
							</h4>
						</div>
						
						<div class="card-body">

							<div class="alert alert-secondary" role="alert">Por favor,
								indique los siguientes campos de la nueva evidencia digital.
							</div>

							<form id="agregarDispositivoAlCasoForm"
								action="${pageContext.request.contextPath}/ServletControlador?accion=insertarEvidencia"
								method="POST" enctype="multipart/form-data">

								<input type="hidden" name="idDispositivo" value="${idDispositivo}">
								<input type="hidden" name="idCaso" value="${idCaso}">

								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="nombre" class="form-label">Nombre
												evidencia digital</label> <input type="text" class="form-control"
												id="nombre" name="nombre">
										</div>
									</div>
								</div>

								<br/>

								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="descripcion" class="form-label">Descripción</label>
											<textarea class="form-control" rows="10" id="descripcion"
												name="descripcion"></textarea>
										</div>
									</div>
								</div>

								<br />

								<div class="row justify-content-md-center">
									<div class="col-7">
										<label for="#" class="form-label">Tipo de evidencia digital</label>
										<select class="form-control" aria-label="Default select example" name="tipo" >
											<option value="1">Archivo cifrado</option>
											<option value="2">Archivo comprimido</option>
											<option value="3">Archivo de texto</option>
											<option value="4">Audio</option>
											<option value="5">Base de datos</option>
											<option value="6">Documento ofimático</option>											
											<option value="7">Historial de navegación</option>
											<option value="8">Imagen forense</option>
											<option value="9">Imagen</option>											
											<option value="10">Memoria RAM</option>
											<option value="11">Mensaje/s de correo</option>
											<option value="12">Metadato/s de archivo/s</option>											
											<option value="13">Otros</option>
											<option value="14">Redes Wi-Fi detectadas</option>
											<option value="15">Registro de llamadas</option>											
											<option value="16">Registro/s de Windows</option>
											<option value="17">SMS</option>
											<option value="18">Tráfico de red</option>											
											<option value="19">Vídeo</option>	
										</select>
										
									</div>
								</div>
	
								<br />

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