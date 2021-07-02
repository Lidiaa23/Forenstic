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
<title>Agregar dispositivo</title>
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
								<i class="fas fa-laptop"></i> Agregar dispositivo
							</h4>
						</div>

						<div class="card-body">
						
						<c:choose>
							<c:when test="${fn:length(listaMisCasos) > 0}">
								<div class="alert alert-secondary" role="alert">Por favor,
									añada los campos básicos del nuevo dispositivo. Posteriormente
									se podrá proceder a añadir <b>evidencias digitales</b>, <b>cadena de custodia</b> y <b>ciclo de vida</b>.</div>
	
								<form id="agregarDispositivoForm"
									action="${pageContext.request.contextPath}/ServletControlador?accion=insertarDispositivo"
									method="POST" enctype="multipart/form-data">
	
									<div class="row justify-content-md-center">
										<div class="col-7">
											<label for="#" class="form-label">Selecciona el caso al que añadirle este dispositivo</label>   
											<select name="idCaso" class="form-control" data-live-search="true">
												
												<c:forEach var="caso" items="${listaMisCasos}" varStatus="status">										
													<option value="${caso.idCaso}">${caso.idCaso} - ${caso.nombre}</option>										
												</c:forEach>
	
											</select>
											<small id="casoHelp" class="form-text text-muted">Solo se muestran los casos que tienes asignados.</small>							
										</div>
									</div>
									
									<br/>
	
									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
												<label for="nombre" class="form-label">Nombre
													dispositivo</label> <input type="text" class="form-control"
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
	
									<div class="row justify-content-md-center">
										<div class="col-7">
											<label for="fotografia" class="form-label">Subir
												fotografía</label> <input class="form-control" type="file"
												id="fotografia" name="fotografia">
										</div>
									</div>
	
									<br />

									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
												<label for="nombre" class="form-label">Hash</label> <input
													type="text" class="form-control" id="hash" name="hash">
											</div>
										</div>
									</div>
									<br />
									
									
									<div class="row justify-content-md-center">
										<div class="col-7">									
										<label for="#" class="form-label">¿Se encuentra cifrado?</label>					
											<div class="form-check">
											  <input class="form-check-input" type="radio" name="cifrado" id="flexRadioDefault3" value="si" checked>
											  <label class="form-check-label" for="flexRadioDefault3">
											    Sí
											  </label>
											</div>
											<div class="form-check">
											  <input class="form-check-input" type="radio" name="cifrado" id="flexRadioDefault4" value="no">
											  <label class="form-check-label" for="flexRadioDefault4">
											    No
											  </label>
											</div>
										</div>
									</div>
									
									
									<br />
									<div class="row justify-content-md-center">
										<div class="col-7">
											<label for="#" class="form-label">Tipo de dispositivo</label>
											<select class="form-control" aria-label="Default select example" name="tipoDispositivo" >
												<!-- <option selected>Seleccionar</option> -->
												<option value="1">CD/DVD</option>
												<option value="2">Consola</option>
												<option value="3">Cámara de seguridad</option>
												<option value="4">Cámara digital</option>
												<option value="5">Disco duro externo</option>
												<option value="6">Disco duro interno</option>
												<option value="7">Fax</option>
												<option value="8">GPS</option>
												<option value="9">Impresora</option>
												<option value="10">MP3/MP4</option>
												<option value="11">Notebook</option>
												<option value="12">Ordenador personal</option>
												<option value="13">Ordenador portátil</option>
												<option value="14">PDA</option>
												<option value="15">Router</option>
												<option value="16">Servidor</option>
												<option value="17">Smart TV</option>
												<option value="18">Smartwatch</option>
												<option value="19">Tablet</option>
												<option value="20">Tarjeta de memoria</option>
												<option value="21">Teléfono móvil</option>
												<option value="22">USB</option>
											</select>
											
										</div>
									</div>
									<br />
									
									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
												<label for="marca" class="form-label">Marca y modelo</label> <input type="text" class="form-control"
													id="marca" name="marca">
											</div>
										</div>
									</div>
									<br />
									
									<div class="row justify-content-md-center">
										<div class="col-7">									
										<label for="#" class="form-label">¿Se trata del dispositivo original o una copia?</label>					
											<div class="form-check">
											  <input class="form-check-input" type="radio" name="clonadoForense" id="flexRadioDefault5" value="O" checked>
											  <label class="form-check-label" for="flexRadioDefault5">
											    Original
											  </label>
											</div>
											<div class="form-check">
											  <input class="form-check-input" type="radio" name="clonadoForense" id="flexRadioDefault6" value="C">
											  <label class="form-check-label" for="flexRadioDefault6">
											    Clonado forense (copia)
											  </label>
											</div>
											<div class="form-check">
											  <input class="form-check-input" type="radio" name="clonadoForense" id="flexRadioDefault7" value="I">
											  <label class="form-check-label" for="flexRadioDefault7">
											    Contiene imagen forense u otras evidencias digitales (copia)
											  </label>
											</div>
										</div>
									</div>

									<br />
									<button type="submit" class="btn btn-primary">Agregar</button>
	
								</form>
							</c:when>
							<c:otherwise>
								<dd class="col-sm-9">Actualmente aún no dispones de casos asignados, por lo que no es posible añadir dispositivos.</dd>
							</c:otherwise>
						</c:choose>
						



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