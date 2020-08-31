<!DOCTYPE html>
<%@ page import="java.text.SimpleDateFormat,java.util.*,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade" id="mensajePDF" data-backdrop="static" data-keyboard="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Mensaje PDF</h4>
			</div>
			
			<div class="modal-body">
				<div style="text-align: justify;" >
					<p>
						Sus datos han sido registrados correctamente.<br><br> 
						Puede descargar un comprobante de registro al oprimir el boton siguiente
					</p>
					
					<center>
					<a href="data:application/pdf;base64,{{$root.errores.descripcionCode}}" class="btn btn-default" download="Comprobante_preafiliacion_fonacot.pdf">Descargar Comprobante</a>
					</center>				
				</div>
				
			</div>
			<div class="modal-footer">
				<a id="btnModalMensaje" class="btn btn-default" 
					onclick="$('#mensajePDF').modal('hide'); location.reload(true);" data-dismiss="modal">Aceptar</a>
			</div>
		</div>
	</div>
</div>