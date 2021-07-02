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
		<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
		<!-- <style>			
			footer {
				position: absolute;
				bottom: 0;
				width: 100%;
			}
		</style>-->
		<title>Consultar evidencia digital</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

		<!-- Botones de navegación -->
		<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsultaEvidencia.jsp" />
		
		<section id="details">
			<div class="container-fluid" style="max-width:1650px"> 
			
			<c:choose>
			
				<c:when test="${evidencia.idDispositivo == '-1'}"> <!-- Sin dispositivo asociado -->
					<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
					  <ol class="breadcrumb">
					    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ServletControlador">Inicio</a></li>
					    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${evidencia.idCaso}">Caso</a></li>
					    <li class="breadcrumb-item active" aria-current="page">Evidencia digital</li>
					  </ol>
					</nav>										
				</c:when>
				
				<c:otherwise> <!-- Con dispositivo asociado -->
					<nav style="--bs-breadcrumb-divider: '>';" aria-label="breadcrumb">
					  <ol class="breadcrumb">
					    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ServletControlador">Inicio</a></li>
					    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${evidencia.idCaso}">Caso</a></li>
					    <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarDispositivo&idDispositivo=${evidencia.idDispositivo}">Dispositivo</a></li>										    
					    <li class="breadcrumb-item active" aria-current="page">Evidencia digital</li>
					  </ol>
					</nav>										
				</c:otherwise>
			
			</c:choose>			
			
				<div class="row">
					<div class="col">
						<div class="card mx-auto">
							<div class="card-header">
								<h4><i class="fas fa-file-video"></i> Consultar evidencia digital</h4>
							</div>
							<div class="card-body">									
					
							<%
							Object message = request.getAttribute("evidenciaAgregadaOK");
							
							if (message != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								La evidencia digital se ha creado correctamente.
							</div>	
							<% }%>
							
							<%
							Object messageEditada = request.getAttribute("evidenciaEditadaOK");
							
							if (messageEditada != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								La evidencia digital se ha modificado correctamente.
							</div>	
							<% }%>

								<dl class="row">
								
								<dt class="col-sm-3">ID:</dt>
								  <dd class="col-sm-9">${evidencia.idEvidencia}</dd>
								  
								  <dt class="col-sm-3">Caso:</dt>
								  <dd class="col-sm-9">[${evidencia.idCaso}] ${evidencia.nombreCaso}</dd>
								  
								  <dt class="col-sm-3">Dispositivo:</dt>
								  <c:choose>
								  	<c:when test="${evidencia.idDispositivo == '-1'}">
								  	  <dd class="col-sm-9">Sin dispositivo asociado</dd>
								  	</c:when>
								  	<c:otherwise>
								  	  <dd class="col-sm-9">[${evidencia.idDispositivo}] ${evidencia.nombreDispositivo}</dd>
								  	</c:otherwise>
								  </c:choose>
								
								  <dt class="col-sm-3">Nombre:</dt>
								  <dd class="col-sm-9">${evidencia.nombre}</dd>
								
								  <dt class="col-sm-3">Descripción:</dt>
								  <dd class="col-sm-9">${evidencia.descripcion}</dd>
								  
								  <dt class="col-sm-3">Tipo:</dt>		
		
								  <c:choose>
								  	<c:when test="${evidencia.tipo == '1'}">
								  		<dd class="col-sm-9">Archivo cifrado</dd>
								  	</c:when>	
								  	<c:when test="${evidencia.tipo == '2'}">
								  		<dd class="col-sm-9">Archivo comprimido</dd>
								  	</c:when>	
								  	<c:when test="${evidencia.tipo == '3'}">
								  		<dd class="col-sm-9">Archivo de texto</dd>
								  	</c:when>	
								  	<c:when test="${evidencia.tipo == '4'}">
								  		<dd class="col-sm-9">Audio</dd>
								  	</c:when>	
								  	<c:when test="${evidencia.tipo == '5'}">
								  		<dd class="col-sm-9">Base de datos</dd>
								  	</c:when>	
								  	<c:when test="${evidencia.tipo == '6'}">
								  		<dd class="col-sm-9">Documento ofimático</dd>
								  	</c:when>	
								  	<c:when test="${evidencia.tipo == '7'}">
								  		<dd class="col-sm-9">Historial de navegación</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '8'}">
								  		<dd class="col-sm-9">Imagen forense</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '9'}">
								  		<dd class="col-sm-9">Imagen</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '10'}">
								  		<dd class="col-sm-9">Memoria RAM</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '11'}">
								  		<dd class="col-sm-9">Mensaje/s de correo</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '12'}">
								  		<dd class="col-sm-9">Metadato/s de archivo/s</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '13'}">
								  		<dd class="col-sm-9">Otros</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '14'}">
								  		<dd class="col-sm-9">Redes Wi-Fi detectadas</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '15'}">
								  		<dd class="col-sm-9">Registro de llamadas</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '16'}">
								  		<dd class="col-sm-9">Registro/s de Windows</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '17'}">
								  		<dd class="col-sm-9">SMS</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '18'}">
								  		<dd class="col-sm-9">Tráfico de red</dd>
								  	</c:when>									  	
								  	<c:when test="${evidencia.tipo == '19'}">
								  		<dd class="col-sm-9">Vídeo</dd>
								  	</c:when>									  	
								  						  
								  </c:choose>
								  
								  <dt class="col-sm-3">Fecha creación registro:</dt>
								  <dd class="col-sm-9">
								  	<fmt:formatDate value="${evidencia.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" />
								  </dd>
								  
								  <dt class="col-sm-3">Fecha última modificación:</dt>
								  <dd class="col-sm-9">
								  	<fmt:formatDate value="${evidencia.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" />
								  </dd>								  

								</dl>

								<br/>

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