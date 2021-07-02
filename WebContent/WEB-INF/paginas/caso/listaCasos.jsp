<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<section id="casos">
	<div class="container-fluid" style="max-width:1650px">
		<div class="row">
			<div class="col">
				<div class="card mx-auto">
					<div class="card-header">
						<h4>Lista de casos</h4>
					</div>
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
								<th>Fecha última modif.</th>
								<!-- <th>Acciones</th> -->
							</tr>
						</thead>
						<tbody>
							<!-- Iteramos cada elemento de la lista de casos -->
							<c:forEach var="caso" items="${listaCasos}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${caso.idCaso}" class="text-dark">${caso.idCaso}</a></td>
									<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultar&idCaso=${caso.idCaso}" class="text-dark">${caso.nombre}</a></td>
									<td>${caso.descripcion}</td>
									<!-- <td>${fn:length(caso.listaDispositivos)}</td>
									<td>${fn:length(caso.listaEvidencias)}</td> -->
									<td>${caso.numDispositivos}</td>
									<td>${caso.numEvidencias}</td>
									<td><fmt:formatDate value="${caso.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
									<td><fmt:formatDate value="${caso.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
									<!-- <td>
							      		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownButtonAccionesEvidencias" role="button" data-toggle="dropdown" aria-expanded="false">Seleccionar</button>
							       		 <ul class="dropdown-menu" aria-labelledby="dropdownButtonAccionesEvidencias">												        	
											<li><a class="dropdown-item" href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCaso=${caso.idCaso}">Editar</a></li>
											<li><a class="dropdown-item"  href="#" data-href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idCaso=${caso.idCaso}" data-toggle="modal" data-target="#exampleModal">Eliminar</a></li>
											
										</ul>
								 	</td> -->							
								</tr>
							</c:forEach>
						</tbody>
					</table>
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
		
	</div>
</section>