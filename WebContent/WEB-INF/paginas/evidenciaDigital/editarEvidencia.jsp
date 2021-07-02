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

		<title>Editar evidencia digital</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

		<form action="${pageContext.request.contextPath}/ServletControlador?accion=modificarEvidencia&idEvidencia=${evidencia.idEvidencia}&idCaso=${evidencia.idCaso}&idDispositivo=${evidencia.idDispositivo}" method="POST" class="was-validated" enctype="multipart/form-data">
		
			<!-- Botones de navegación -->
			<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionEdicionEvidencia.jsp" />
			
			<section id="details">
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-header">
									<h4>Editar evidencia digital</h4>
								</div>
								<div class="card-body">
								
								
								
								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="nombre" class="form-label">Nombre
												evidencia digital:</label> <input value="${evidencia.nombre}" type="text" class="form-control"
												id="nombre" name="nombre">
										</div>
									</div>
								</div>

								<br/>

								<div class="row justify-content-md-center">
									<div class="col-7">
										<div class="form-group">
											<label for="descripcion" class="form-label">Descripción:</label>
											<textarea class="form-control" rows="10" id="descripcion"
												name="descripcion">${evidencia.descripcion}</textarea>
										</div>
									</div>
								</div>

								<br />

								<%Object tipoObj = request.getAttribute("tipoAttr");
								String tipoStr = (String) tipoObj; 							
								%>

								<div class="row justify-content-md-center">
									<div class="col-7">
										<label for="#" class="form-label">Tipo de evidencia digital:</label>
										<select class="form-control" aria-label="Default select example" name="tipo" value="${evidencia.tipo}">
											<!-- <option selected>Seleccionar</option> -->
											<option <%	if (tipoStr.equalsIgnoreCase("1")) { %> selected <%}%> value="1">Archivo cifrado</option>
											<option <%	if (tipoStr.equalsIgnoreCase("2")) { %> selected <%}%> value="2">Archivo comprimido</option>
											<option <%	if (tipoStr.equalsIgnoreCase("3")) { %> selected <%}%> value="3">Archivo de texto</option>
											<option <%	if (tipoStr.equalsIgnoreCase("4")) { %> selected <%}%> value="4">Audio</option>
											<option <%	if (tipoStr.equalsIgnoreCase("5")) { %> selected <%}%> value="5">Base de datos</option>
											<option <%	if (tipoStr.equalsIgnoreCase("6")) { %> selected <%}%> value="6">Documento ofimático</option>											
											<option <%	if (tipoStr.equalsIgnoreCase("7")) { %> selected <%}%> value="7">Historial de navegación</option>
											<option <%	if (tipoStr.equalsIgnoreCase("8")) { %> selected <%}%> value="8">Imagen forense</option>
											<option <%	if (tipoStr.equalsIgnoreCase("9")) { %> selected <%}%> value="9">Imagen</option>											
											<option <%	if (tipoStr.equalsIgnoreCase("10")) { %> selected <%}%> value="10">Memoria RAM</option>
											<option <%	if (tipoStr.equalsIgnoreCase("11")) { %> selected <%}%> value="11">Mensaje/s de correo</option>
											<option <%	if (tipoStr.equalsIgnoreCase("12")) { %> selected <%}%> value="12">Metadato/s de archivo/s</option>											
											<option <%	if (tipoStr.equalsIgnoreCase("13")) { %> selected <%}%> value="13">Otros</option>
											<option <%	if (tipoStr.equalsIgnoreCase("14")) { %> selected <%}%> value="14">Redes Wi-Fi detectadas</option>
											<option <%	if (tipoStr.equalsIgnoreCase("15")) { %> selected <%}%> value="15">Registro de llamadas</option>											
											<option <%	if (tipoStr.equalsIgnoreCase("16")) { %> selected <%}%> value="16">Registro/s de Windows</option>
											<option <%	if (tipoStr.equalsIgnoreCase("17")) { %> selected <%}%> value="17">SMS</option>
											<option <%	if (tipoStr.equalsIgnoreCase("18")) { %> selected <%}%> value="18">Tráfico de red</option>											
											<option <%	if (tipoStr.equalsIgnoreCase("19")) { %> selected <%}%> value="19">Vídeo</option>										

										</select>
										
									</div>
								</div>

								<br />						

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