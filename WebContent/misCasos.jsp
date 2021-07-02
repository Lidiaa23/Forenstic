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
		<title>Mis casos</title>
	</head>
	<body>
	
		<!-- Cabecero -->
		<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp" />
	
		<!-- Botones de navegación -->
		<!--<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionConsulta.jsp" />-->
		<br/><br/>
		<section id="details">
			<div class="container-fluid" style="max-width:1650px">
				<div class="row">
					<div class="col">
						<div class="card mx-auto">
						
							<div class="card-header">
								<h4><i class="far fa-folder-open"></i> Mis casos</h4>
							</div>
							
							<div class="card-body">
							
							<%
							Object messageEliminado = request.getAttribute("casoEliminadoOK");
							
							if (messageEliminado != null) {%>
								<div class="alert alert-success alert-dismissible fade show" role="alert">
								<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span></button>
								El caso se ha eliminado correctamente.
							</div>	
							<% }%>
							
								<c:choose>
									<c:when test="${fn:length(listaMisCasos) > 0}">
										<table class="table table-striped">
											<thead class="thead-dark">
												<tr>
													<th>#</th>
													<th>ID</th>
													<th>Nombre</th>
													<th>Descripción</th>
													<th>Nº dispositivos</th>
													<th>Nº evidencias digitales</th>
													<th>Fecha creación</th>
													<th>Fecha última modificación</th>
													<th>Acciones</th>
												</tr>
											</thead>
											<tbody>
												<!-- Iteramos cada elemento de la lista de casos -->
												<c:forEach var="caso" items="${listaMisCasos}" varStatus="status">
													<tr>
														<td>${status.count}</td>
														<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${caso.idCaso}" class="text-dark">${caso.idCaso}</a></td>
														<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${caso.idCaso}" class="text-dark">${caso.nombre}</a></td>
														<td>${caso.descripcion}</td>
														<td>${caso.numDispositivos}</td>
														<td>${caso.numEvidencias}</td>
														<td><fmt:formatDate value="${caso.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
														<td><fmt:formatDate value="${caso.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
														<td>
												      		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownButtonAccionesEvidencias" role="button" data-toggle="dropdown" aria-expanded="false">Seleccionar</button>
												       		 <ul class="dropdown-menu" aria-labelledby="dropdownButtonAccionesEvidencias">												        	
																<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCaso=${caso.idCaso}">Editar</a></li>
																<li><a class="dropdown-item"  href="#" data-href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarCasoDesdeMisCasos&idCaso=${caso.idCaso}" data-toggle="modal" data-target="#exampleModal">Eliminar</a></li>															
															</ul>
								 						</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</c:when>
									<c:otherwise>
										<dd class="col-sm-9">Actualmente aún no dispones de casos asignados.</dd>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
			
			<!-- Modal -->
			<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h5 class="modal-title" id="exampleModalLabel">Ventana de confirmación</h5>
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			          <span aria-hidden="true">&times;</span>
			        </button>
			      </div>
			      <div class="modal-body">
			        <p>¿Estás seguro de querer eliminar este caso?</p>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
			        <a class="btn btn-danger btn-ok">Eliminar</a>
			      </div>
			    </div>
			  </div>
			</div>			
		</div>
		
		<script>
		
		$('#exampleModal').on('show.bs.modal', function(e) {
		    $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
		});

		</script>
		
		</section>
				
		<!-- Pie de página -->
		<jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp" />

	</body>
</html>