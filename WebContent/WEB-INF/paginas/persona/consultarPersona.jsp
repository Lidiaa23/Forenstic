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
			.footer {
			  position: relative;
			  margin-top: -150px; /* negative value of footer height */
			  height: 150px;
			  clear:both;
			  padding-top:20px;
			} 
		</style>-->
		<title>Consultar usuario</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />

		<!-- Botones de navegación -->
		<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsultaPersona.jsp" />
		
		<section id="details">
			<div class="container-fluid" style="max-width:1650px">
				<div class="row">
					<div class="col">
						<div class="card mx-auto">
							<div class="card-header">
								<h4>Consultar usuario</h4>
							</div>
							<div class="card-body">
							
								<%
								Object messageEditado = request.getAttribute("personaEditadaOK");
								
								if (messageEditado != null) {%>
									<div class="alert alert-success alert-dismissible fade show" role="alert">
									<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
									El usuario se ha editado correctamente.
								</div>	
								<% }%>								

								<dl class="row">
								  <dt class="col-sm-3">ID:</dt>
								  <dd class="col-sm-9">${usuario.idPersona}</dd>
								
								  <dt class="col-sm-3">Nombre:</dt>
								  <dd class="col-sm-9">${usuario.nombre}</dd>
								
								  <dt class="col-sm-3">Usuario:</dt>
								  <dd class="col-sm-9">${usuario.usuario}</dd>
								  
								  <dt class="col-sm-3">Correo:</dt>
								  <dd class="col-sm-9">${usuario.correo}</dd>
								  
								  <dt class="col-sm-3">Teléfono:</dt>
								  <dd class="col-sm-9">${usuario.telefono}</dd>
								  
								  
								  <dt class="col-sm-3">Casos:</dt>
								  								  
								  <c:choose>
								  	<c:when test="${fn:length(usuario.listaCasos) > 0}">
								 	 <br/>
								 	 <br/>							  
									  <table class="table table-striped table-bordered">
										  <thead>
										    <tr>
										      <th scope="col">#</th>
										      <th scope="col">ID</th>
										      <th scope="col">Nombre</th>
										      <th scope="col">Descripción</th>
										    </tr>
										  </thead>
										  <tbody>										  
										  	<c:forEach var="caso" items="${usuario.listaCasos}" varStatus="status">
											    <tr>
											      <th scope="row">${status.index + 1}</th>
											      <td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${caso.idCaso}" class="text-dark">${caso.idCaso}</a></td>
											      <td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${caso.idCaso}" class="text-dark">${caso.nombre}</a></td>
											      <td>${caso.descripcion}</td>									      
											    </tr>
											</c:forEach>
										  </tbody>
										</table>
										<br/>							  
								  		<br/>
									</c:when>
									<c:otherwise>
										<dd class="col-sm-9">No existen casos.</dd>
									</c:otherwise>
								  </c:choose>							

								</dl>

								<!--  <button class="btn btn-secondary" type="submit"><i class="far fa-folder-open"></i> Asignar caso</button>-->

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