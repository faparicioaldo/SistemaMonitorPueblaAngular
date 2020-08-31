<!DOCTYPE html>
<%@ page import="java.text.SimpleDateFormat,java.util.*,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- ventana sesion modal -->
<div class="modal fade" data-keyboard="false" data-backdrop="static" id="timerModal" ng-init="init()">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Sesi&oacute;n a punto de expirar</h4>
			</div>
			<div class="modal-body">
				<div>
					<p>Tu sesi&oacute;n expirar&aacute; en <b>{{tiempo}} segundos</b></p>
					<p>Si desea m&aacute;s tiempo de click en aceptar, para que
						pueda continuar con su registro.</p>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-default" ng-click="restartInterval()" data-dismiss="modal">Aceptar</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>