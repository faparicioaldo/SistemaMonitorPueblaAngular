<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value = "/resources/styles.css"/>">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script type="application/javascript">
	function enviarAlarmaPOST(idAlarma) {
		var formData = {
			idAlarma : idAlarma
		}

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/enviarAlarma",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				alert('Alarma ' + result.idAlarma + ' enviada.');
			}
		});
	}
	function descartarAlarmaPOST(idAlarma) {
		var formData = {
			idAlarma : idAlarma
		}

		$.ajax({
			type : "POST",
			contentType : "application/json",
			url : "/descartarAlarma",
			data : JSON.stringify(formData),
			dataType : 'json',
			success : function(result) {
				alert('Alarma ' + result.idAlarma + ' descartada.');
			}
		});
	}
</script>
</head>
<body>
	<center>
		<h1>AGREGAR DATOS DE ALARMA</h1>
	</center>

	<form:form method="POST" action="/guardarDatosAlarma"
		modelAttribute="datosAlarma">
		<table>
			<tr>
				<td><form:label path="alarmaid">#alarmaId</form:label></td>
				<td><form:input path="alarmaid" /></td>
			</tr>
			<tr>
				<td><form:label path="deviceid">#Dispositivo</form:label></td>
				<td><form:input path="deviceid" /></td>
			</tr>

			<tr>
				<td><form:label path="economico">economico</form:label></td>
				<td><form:input path="economico"  /></td>
			</tr>
			<tr>
				<td><form:label path="empresa">empresa</form:label></td>
				<td><form:input path="empresa" /></td> <!-- readonly="true"-->
			</tr>
			<tr>
				<td><form:label path="socio">socio</form:label></td>
				<td><form:input path="socio" readonly="true" /></td>
			</tr>
			<tr>
				<td><form:label path="ruta">*Ruta</form:label></td>
				<td><form:input path="ruta" /></td>
			</tr>
			<tr>
				<td><form:label path="placa">*Placa</form:label></td>
				<td><form:input path="placa" /></td>
			</tr>
			<tr>
				<td><form:label path="imei">*IMEI</form:label></td>
				<td><form:input path="imei" /></td>
			</tr>
			<tr>
				<td><form:label path="ipdispositivo">*IP</form:label></td>
				<td><form:input path="ipdispositivo" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Guardar" /></td>
			</tr>
		</table>
	</form:form>

	<center>
		<a href="/monitorAlarmas">Ir a monitor de alarmas</a>
	</center>

</body>
</html>