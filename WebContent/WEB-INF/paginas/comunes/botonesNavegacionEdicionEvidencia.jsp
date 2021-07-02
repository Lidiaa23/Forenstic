<section id="actions" class="py-4 mb-4 bg-light">
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<a href="#" onclick="history.back()" class="btn btn-light btn-block">
					<i class="fas fa-arrow-left"></i> Volver atr�s
				</a>	
			</div>
			<div class="col-md-3">
				<button type="submit" class="btn btn-success btn-block">
					<i class="fas fa-check"></i> Guardar evidencia digital
				</button>
			</div>
			<div class="col-md-3">
				<a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminarEvidenciaDesdeConsultarCasos&idEvidencia=${evidencia.idEvidencia}&idDispositivo=${evidencia.idDispositivo}&idCaso=${evidencia.idCaso}" class="btn btn-danger btn-block">
					<i class="fas fa-trash"></i> Eliminar evidencia digital
				</a>
			</div>
		</div>
	</div>
</section>