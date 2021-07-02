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
<script src="https://cdn.jsdelivr.net/timepicker.js/latest/timepicker.min.js"></script>
<link href="https://cdn.jsdelivr.net/timepicker.js/latest/timepicker.min.css" rel="stylesheet"/>

	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<title>Agregar registro en cadena de custodia</title>
</head>

<!-- 		<style>
			.footer {
			  position: relative;
			  margin-top: -150px; /* negative value of footer height */
			  height: 150px;
			  clear:both;
			  padding-top:20px;
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
		<div class="container-fluid" style="max-width:1000px">
			<div class="row">
				<div class="col">
					<div class="card mx-auto">

						<div class="card-header">
							<h4>
								<i class="fas fa-link"></i> Agregar registro en cadena de custodia
							</h4>
						</div>

						<div class="card-body">
						
							<form id="agregarCadenaCustodiaAlDispositivoForm"
								action="${pageContext.request.contextPath}/ServletControlador?accion=insertarRegistroCadenaCustodiaForm"
								method="POST" enctype="multipart/form-data">

								<input type="hidden" name="idCadenaCustodia" value="${idCadenaCustodia}">
								<input type="hidden" name="nombreDispositivo" value="${nombreDispositivo}">

								<div class="row justify-content-md-center">									
									<div class="col">										
										<div class="form-group">
										    <label for="fecha" class="form-label">Fecha</label>
										    <input type="date" class="form-control" id="fecha" name="fecha">
										</div>
									</div>
									
									<div class="col">
										<div class="form-group">
										    <label for="hora" class="form-label">Hora</label><br />
										    <input type="time" class="form-control" id="hora" name="hora">
										</div>
									</div>										
								</div>	
								<br />
		
		
								<div class="row">	
									<div class="col">
										<h6>Entrega</h6>	
									</div>
									<div class="col">
										<h6>Recepción</h6>	
									</div>
								</div>	
								
								
								<div class="row">																		
									<div class="col">										
										<div class="form-group">
										    <label for="personaEntrega" class="form-label">Persona</label>
										    <input type="text" class="form-control" id="personaEntrega" name="personaEntrega">
										</div>
									</div>
									<div class="col">										
										<div class="form-group">
										    <label for="agenciaInstitucionEntrega" class="form-label">Agencia / Institución</label>
										    <input type="text" class="form-control" id="agenciaInstitucionEntrega" name="agenciaInstitucionEntrega">
										</div>
									</div>			

									<div class="col">										
										<div class="form-group">
										    <label for="personaRecibe" class="form-label">Persona</label>
										    <input type="text" class="form-control" id="personaRecibe" name="personaRecibe">
										</div>
									</div>	
									<div class="col">
										<div class="form-group">
										    <label for="agenciaInstitucionRecibe" class="form-label">Agencia / Institución</label>
										    <input type="text" class="form-control" id="agenciaInstitucionRecibe" name="agenciaInstitucionRecibe">
										</div>
									</div>																				
								</div>											

								<br />		
								
								<div class="row">																				

									<div class="col">										
										<div class="form-group">
											<label for="actividadProposito" class="form-label">Actividad / Propósito</label>
											<input type="text" class="form-control" id="actividadProposito" name="actividadProposito">
										</div>
									</div>	
																			
								</div>											

								<br />		
								
								<div class="row">																				

									<div class="col">
										<div class="form-group">
												<label for="observaciones" class="form-label">Observaciones</label>
												<textarea class="form-control" rows="10" id="observaciones"
													name="observaciones"></textarea>
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