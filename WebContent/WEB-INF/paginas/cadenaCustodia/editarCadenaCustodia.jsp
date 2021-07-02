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
		<!-- <style>			
			footer {
				position: absolute;
				bottom: 0;
				width: 100%;
			}
		</style>-->
		<title>Editar cadena de custodia</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

		<form action="${pageContext.request.contextPath}/ServletControlador?accion=modificarCadenaCustodia&idCadenaCustodia=${cadenaCustodia.idCadenaCustodia}" method="POST" class="was-validated">
		
			<!-- Botones de navegación -->
			<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionEdicionCadenaCustodia.jsp" />
			
			<section id="details">
				<div class="container">
					<div class="row">
						<div class="col">
							<div class="card">
								<div class="card-header">
									<h4>Editar cadena de custodia</h4>
								</div>
								<div class="card-body">
																	
									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
												<label for="localizacion" class="form-label">Localización</label> <input type="text" class="form-control"
													id="localizacion" name="localizacion" value="${cadenaCustodia.localizacionOrigen}">
											</div>
										</div>
									</div>
									<br />	
									<div class="row justify-content-md-center">						
										<div class="col-7">										
											<div class="form-group">
											    <label for="fecha" class="form-label">Fecha</label>
											    <input type="date" class="form-control" id="fecha" name="fecha" value="${fechaStr}">
											</div>
										</div>
									</div>
									<br />	
									<div class="row justify-content-md-center">	
									
										<div class="col-7">
											<div class="form-group">
											    <label for="hora" class="form-label">Hora</label><br />
											    <input type="time" class="form-control" id="hora" name="hora" value="${horaStr}">
											</div>
										</div>	
									</div>									
								
									<br />
									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
												<label for="razon" class="form-label">Razón</label>
												<input type="text" class="form-control" id="razon" name="razon" value="${cadenaCustodia.razon}">
											</div>
										</div>
									</div>
	
									<br />			
									
									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
												<label for="agenciaInstitucion" class="form-label">Agencia / Institución</label>
												<input type="text" class="form-control" id="agenciaInstitucion" name="agenciaInstitucion" value="${cadenaCustodia.agenciaInstitucion}">
											</div>
										</div>
									</div>
	
									<br />		
									
									<div class="row justify-content-md-center">
										<div class="col-7">
											<div class="form-group">
												<label for="persona" class="form-label">Persona</label>
												<input type="text" class="form-control" id="persona" name="persona" value="${cadenaCustodia.persona}">
											</div>
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