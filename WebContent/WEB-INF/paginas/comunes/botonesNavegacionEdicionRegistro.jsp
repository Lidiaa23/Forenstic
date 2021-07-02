<section id="actions" class="py-4 mb-4 bg-light">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<a href="#" onclick="history.back()" class="btn btn-light btn-block">
					<i class="fas fa-arrow-left"></i> Volver atrás
				</a>	
			</div>
			<div class="col-md-3">
				<button type="submit" class="btn btn-success btn-block">
					<i class="fas fa-check"></i> Guardar registro
				</button>
			</div>
			<div class="col-md-3">
				<a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarRegistroDesdeConsultarCadena&idCadena=${registroCadena.idCadenaCustodia}&idRegistro=${registroCadena.idRegistroCadenaCustodia}" class="btn btn-danger btn-block">
					<i class="fas fa-trash"></i> Eliminar registro
				</a>
			</div>
		</div>
	</div>
</section>