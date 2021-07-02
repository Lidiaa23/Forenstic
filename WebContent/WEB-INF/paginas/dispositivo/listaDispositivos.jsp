<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<section id="dispositivos">
	<div class="container-fluid" style="max-width:2000px">
		<div class="row">
			<div class="col">
				<div class="card mx-auto">
					<div class="card-header">
						<h4>Lista de dispositivos</h4>
					</div>
					<table class="table table-striped">
						<thead class="thead-dark">
							<tr>
								<th>#</th>
								<th>ID</th>
								<th>Nombre</th>
								<th>Tipo</th>
								<th>Marca y modelo</th>
								<th>Descripción</th>
								<th>Nº evidencias digitales</th>
								<th>Hash</th>
								<th>¿Está cifrado?</th>
								<th>¿Se trata del dispositivo original o una copia?</th>
								<th>ID caso</th>
								<th>ID cadena de custodia</th>
								<th>ID ciclo de vida</th>
								<th>Fecha creación</th>
								<th>Fecha última modificación</th>			
								<!-- <th>Acciones</th>		 -->							
							</tr>
						</thead>
						<tbody>
							<!-- Iteramos cada elemento de la lista de casos -->
							<c:forEach var="dispositivo" items="${listaDispositivos}" varStatus="status">
								<tr>
									<td>${status.count}</td>
									<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarDispositivo&idDispositivo=${dispositivo.idDispositivo}" class="text-dark">${dispositivo.idDispositivo}</a></td>
									<td><a href="${pageContext.request.contextPath}/ServletControlador?accion=consultarDispositivo&idDispositivo=${dispositivo.idDispositivo}" class="text-dark">${dispositivo.nombre}</a></td>

						      		<c:choose>
								      <c:when test="${dispositivo.tipo == '1'}">
								      	<td>CD/DVD</td>
								      </c:when>
								      <c:when test="${dispositivo.tipo == '2'}">
								      	<td>Consola</td>
								      </c:when>
								      <c:when test="${dispositivo.tipo == '3'}">
								      	<td>Cámara de seguridad</td>
								      </c:when>
									  <c:when test="${dispositivo.tipo == '4'}">
								      	<td>Cámara digital</td>
								      </c:when>											      
								      <c:when test="${dispositivo.tipo == '5'}">
								      	<td>Disco duro externo</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '6'}">
								      	<td>Disco duro interno</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '7'}">
								      	<td>Fax</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '8'}">
								      	<td>GPS</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '9'}">
								      	<td>Impresora</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '10'}">
								      	<td>MP3/MP4</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '11'}">
								      	<td>Notebook</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '12'}">
								      	<td>Ordenador personal</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '13'}">
								      	<td>Ordenador portátil</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '14'}">
								      	<td>PDA</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '15'}">
								      	<td>Router</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '16'}">
								      	<td>Servidor</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '17'}">
								      	<td>Smart TV</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '18'}">
								      	<td>Smartwatch</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '19'}">
								      	<td>Tablet</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '20'}">
								      	<td>Tarjeta de memoria</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '21'}">
								      	<td>Teléfono móvil</td>
								      </c:when>												      
								      <c:when test="${dispositivo.tipo == '22'}">
								      	<td>USB</td>
								      </c:when>												      												      											      
							      </c:choose>

									<td>${dispositivo.marca}</td>											
									<td>${dispositivo.descripcion}</td>
									<td>${fn:length(dispositivo.listaEvidenciasDigitales)}</td>
									<td>${dispositivo.hash}</td>

								      <c:choose>
								      	  <c:when test="${dispositivo.estaCifrado == 'S'}">
									      	<td>Sí</td>
									      </c:when>	
								      	  <c:when test="${dispositivo.estaCifrado == 'N'}">
									      	<td>No</td>
									      </c:when>												      
								      </c:choose>									
									
								      <c:choose>
								      	  <c:when test="${dispositivo.esClonadoForense == 'O'}">
									      	<td>Original</td>
									      </c:when>	
								      	  <c:when test="${dispositivo.esClonadoForense == 'C'}">
									      	<td>Clonado forense (copia)</td>
									      </c:when>		
									      <c:when test="${dispositivo.esClonadoForense == 'I'}">
									      	<td>Contiene imagen forense u otras evidencias digitales (copia)</td>
									      </c:when>											      
								      </c:choose>
									<td>[${dispositivo.idCaso}] ${dispositivo.nombreCaso}</td>
									<td>${dispositivo.idCadenaCustodia}</td>
									<td>${dispositivo.idCicloVida}</td>												
									<td><fmt:formatDate value="${dispositivo.fechaCreacionReg}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
									<td><fmt:formatDate value="${dispositivo.fechaUltimaModif}" pattern="dd-MM-yyyy HH:mm:ss" /></td>
							
								</tr>
							</c:forEach>
						</tbody>
					</table>
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
	        <p>¿Estás seguro de querer eliminar este dispositivo?</p>
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