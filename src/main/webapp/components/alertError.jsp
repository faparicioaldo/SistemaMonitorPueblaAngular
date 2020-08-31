<!DOCTYPE html>
<%@ page import="java.text.SimpleDateFormat,java.util.*,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="alert alert-danger" ng-show="$root.errores && $root.errores.respuestaCode>0">
	<p>
		<b>¡Error al realizar la consulta!</b>
	</p>
	<p ng-bind-html="$root.errores.descripcionCode"></p>
</div>