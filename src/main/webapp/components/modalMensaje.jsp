<!DOCTYPE html>
<%@ page import="java.text.SimpleDateFormat,java.util.*,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="modal fade" id="mensaje">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Mensaje</h4>
			</div>
			<div class="modal-body">
				<p style="text-align: justify;" ng-bind-html="$root.errores.descripcionCode"></p>
			</div>
			<div class="modal-footer">
				<a id="btnModalMensaje" class="btn btn-default" href=""
					onclick="$('#mensaje').modal('hide');" data-dismiss="modal">Aceptar</a>
			</div>
		</div>
	</div>
</div>