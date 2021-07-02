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
		<title>Editar dispositivo</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

		<form action="${pageContext.request.contextPath}/ServletControlador?accion=modificarDispositivo&idDispositivo=${dispositivo.idDispositivo}&idCaso=${dispositivo.idCaso}" method="POST" class="was-validated" enctype="multipart/form-data">
		
			<!-- Botones de navegación -->
			<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionEdicionDispositivo.jsp" />
			
			<section id="details">
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-header">
									<h4>Editar dispositivo</h4>
								</div>
								<div class="card-body">
								
									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
												<label for="nombre" class="form-label">Nombre dispositivo:</label> 
												<input type="text" required class="form-control" id="nombre" name="nombre" value="${dispositivo.nombre}">
											</div>
										</div>
									</div>
	
									<br/>
	
									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
												<label for="descripcion" class="form-label">Descripción:</label>
												<textarea class="form-control" rows="10" id="descripcion"
													name="descripcion">${dispositivo.descripcion}</textarea>
											</div>
										</div>
									</div>
	
									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
											
												<%Object fotografiaObj = request.getAttribute("fotografiaAttr");
												String fotografiaStr = (String) fotografiaObj; 							
												%>
											
												<label for="fotografia" class="form-label">Si desea editar la 
												fotografía, elija una nueva</label> <input class="form-control" type="file"
												id="fotografia" name="fotografia">										
											
												<!--  <label for="fotografia" class="form-label">Fotografía</label> 
												<input type="text" required class="form-control" id="fotografia" name="fotografia" value="${dispositivo.fotografia}">-->
											</div>
										</div>
									</div>
	
									<br />

									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
												<label for="nombre" class="form-label">Hash:</label> <input
													type="text" class="form-control" value="${dispositivo.hash}" id="hash" name="hash">
											</div>
										</div>
									</div>
									<br />
									
									<%Object estaCifradoObj = request.getAttribute("estaCifradoAttr");
									String estaCifradoStr = (String) estaCifradoObj; 							
									%>
									
									<div class="row justify-content-md-center">
										<div class="col-7">									
										<label for="#" class="form-label">¿Se encuentra cifrado?:</label>					
											<div class="form-check">
											  <input class="form-check-input" value="${dispositivo.estaCifrado}" type="radio" name="cifrado" id="flexRadioDefault3" <%	if (estaCifradoStr.equalsIgnoreCase("S")) { %> checked <%}%>>
											  <label class="form-check-label" for="flexRadioDefault3">
											    Sí
											  </label>
											</div>
											<div class="form-check">
											  <input class="form-check-input" type="radio" value="${dispositivo.estaCifrado}" name="cifrado" id="flexRadioDefault4" <%	if (estaCifradoStr.equalsIgnoreCase("N")) { %> checked <%}%>>
											  <label class="form-check-label" for="flexRadioDefault4">
											    No
											  </label>
											</div>
										</div>
									</div>
									
									<%Object tipoObj = request.getAttribute("tipoAttr");
									String tipoStr = (String) tipoObj; 							
									%>
									
									<br />
									<div class="row justify-content-md-center">
										<div class="col-7">
											<label for="#" class="form-label">Tipo de dispositivo:</label>
											<select class="form-control" aria-label="Default select example" name="tipoDispositivo">
												<!-- <option selected>Seleccionar</option> -->
												<option <%	if (tipoStr.equalsIgnoreCase("1")) { %> selected <%}%> value="1">CD/DVD</option>
												<option <%	if (tipoStr.equalsIgnoreCase("2")) { %> selected <%}%> value="2">Consola</option>
												<option <%	if (tipoStr.equalsIgnoreCase("3")) { %> selected <%}%> value="3">Cámara de seguridad</option>
												<option <%	if (tipoStr.equalsIgnoreCase("4")) { %> selected <%}%> value="4">Cámara digital</option>
												<option <%	if (tipoStr.equalsIgnoreCase("5")) { %> selected <%}%> value="5">Disco duro externo</option>
												<option <%	if (tipoStr.equalsIgnoreCase("6")) { %> selected <%}%> value="6">Disco duro interno</option>												
												<option <%	if (tipoStr.equalsIgnoreCase("7")) { %> selected <%}%> value="7">Fax</option>
												<option <%	if (tipoStr.equalsIgnoreCase("8")) { %> selected <%}%> value="8">GPS</option>
												<option <%	if (tipoStr.equalsIgnoreCase("9")) { %> selected <%}%> value="9">Impresora</option>
												<option <%	if (tipoStr.equalsIgnoreCase("10")) { %> selected <%}%> value="10">MP4/MP4</option>
												<option <%	if (tipoStr.equalsIgnoreCase("11")) { %> selected <%}%> value="11">Notebook</option>
												<option <%	if (tipoStr.equalsIgnoreCase("12")) { %> selected <%}%> value="12">Ordenador personal</option>
												<option <%	if (tipoStr.equalsIgnoreCase("13")) { %> selected <%}%> value="13">Ordenador portátil</option>
												<option <%	if (tipoStr.equalsIgnoreCase("14")) { %> selected <%}%> value="14">PDA</option>
												<option <%	if (tipoStr.equalsIgnoreCase("15")) { %> selected <%}%> value="15">Router</option>												
												<option <%	if (tipoStr.equalsIgnoreCase("16")) { %> selected <%}%> value="16">Servidor</option>
												<option <%	if (tipoStr.equalsIgnoreCase("17")) { %> selected <%}%> value="17">Smart TV</option>
												<option <%	if (tipoStr.equalsIgnoreCase("18")) { %> selected <%}%> value="18">Smartwatch</option>												
												<option <%	if (tipoStr.equalsIgnoreCase("19")) { %> selected <%}%> value="19">Tablet</option>
												<option <%	if (tipoStr.equalsIgnoreCase("20")) { %> selected <%}%> value="20">Tarjeta de memoria</option>
												<option <%	if (tipoStr.equalsIgnoreCase("21")) { %> selected <%}%> value="21">Teléfono móvil</option>
												<option <%	if (tipoStr.equalsIgnoreCase("22")) { %> selected <%}%> value="22">USB</option>												
																								
											</select>
											
										</div>
									</div>
									<br />
									
									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
												<label for="nombre" class="form-label">Marca y/o modelo:</label> <input
													type="text" class="form-control" value="${dispositivo.marca}" id="marca" name="marca">
											</div>
										</div>
									</div>
									<br />
									
									<%Object esClonadoForenseObj = request.getAttribute("esClonadoForenseAttr");
									String esClonadoForenseStr = (String) esClonadoForenseObj; 							
									%>
									
									<div class="row justify-content-md-center">
										<div class="col-7">									
										<label for="#" class="form-label">¿Se trata del dispositivo original o una copia?:</label>					
											<div class="form-check">
											  <input class="form-check-input" type="radio" value="${dispositivo.esClonadoForense}" name="clonadoForense" id="flexRadioDefault5" <%	if (esClonadoForenseStr.equalsIgnoreCase("O")) { %> checked <%}%>>
											  <label class="form-check-label" for="flexRadioDefault5">
											    Original
											  </label>
											</div>
											<div class="form-check">
											  <input class="form-check-input" type="radio" value="${dispositivo.esClonadoForense}" name="clonadoForense" id="flexRadioDefault6" <%	if (esClonadoForenseStr.equalsIgnoreCase("C")) { %> checked <%}%>>
											  <label class="form-check-label" for="flexRadioDefault6">
											    Clonado forense (copia)
											  </label>
											</div>
											<div class="form-check">
											  <input class="form-check-input" type="radio" value="${dispositivo.esClonadoForense}" name="clonadoForense" id="flexRadioDefault7" <%	if (esClonadoForenseStr.equalsIgnoreCase("I")) { %> checked <%}%>>
											  <label class="form-check-label" for="flexRadioDefault7">
											    Contiene imagen forense u otras evidencias digitales (copia)
											  </label>
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