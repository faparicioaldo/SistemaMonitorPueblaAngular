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
		<h1>AGREGAR VEHICULO</h1>
	</center>


<%-- 		<form action="#" th:action="@{/guardarDatosVehiculo}" th:object="${datosVehiculo}" method="post"> --%>
<!-- 			<p> -->
<!-- 				#Dispositivo: <input type="text" th:field="*{idDispositivo}" /> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				Empresa: <input type="text" th:field="*{empresa}" /> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				Socio: <input type="text" th:field="*{socio}" /> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				Economico: <input type="text" th:field="*{economico}" /> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				Ruta: <input type="text" th:field="*{ruta}" /> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				IMEI: <input type="text" th:field="*{imei}" /> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				Placa: <input type="text" th:field="*{placa}" /> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				IP: <input type="text" th:field="*{ip}" /> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				Fecha Modificacion: <input type="text" th:field="*{fechaModificacion}" /> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				Fecha Captura: <input type="text" th:field="*{fechaCaptura}" /> -->
<!-- 			</p> -->
<!-- 			<p> -->
<!-- 				<input type="submit" value="Guardar" />  -->
<!-- 				<input type="reset"	value="Limipiar" /> -->
<!-- 			</p> -->
<%-- 		</form> --%>


	<form:form method="POST" action="/guardarDatosVehiculo"
 		modelAttribute="datosVehiculo"> 
		<table>
 			<tr> 
 				<td><form:label path="idDispositivo">#Dispositivo</form:label></td> 
 				<td><form:input path="idDispositivo" /></td> 
 			</tr> 
 			<tr> 
 				<td><form:label path="empresa">Empresa</form:label></td> 
 				<td><form:input path="empresa" /></td> 
 			</tr> 
<!--  			<tr>  -->
<%--  				<td><form:label path="socio">Socio</form:label></td>  --%>
<%--  				<td><form:input path="socio" /></td>  --%>
<!--  			</tr>  -->
 			<tr> 
 				<td><form:label path="eco">Economico</form:label></td> 
 				<td><form:input path="eco" /></td> 
 			</tr> 
 			<tr> 
 				<td><form:label path="route">Ruta</form:label></td> 
 				<td><form:input path="route" /></td> 
 			</tr> 
 			<tr> 
 				<td><form:label path="imei">IMEI</form:label></td> 
 				<td><form:input path="imei" /></td> 
 			</tr> 
 			<tr> 
 				<td><form:label path="plate">Placa</form:label></td> 
 				<td><form:input path="plate" /></td> 
 			</tr> 
 			<tr> 
 				<td><form:label path="vin">Serie Vehicular</form:label></td> 
 				<td><form:input path="vin" /></td> 
 			</tr> 
  			<tr> 
 				<td><form:label path="engine">Serie Motor</form:label></td> 
 				<td><form:input path="engine" /></td> 
 			</tr> 
  			<tr> 
 				<td><form:label path="year">Año Vehiculo</form:label></td> 
 				<td><form:input path="year" /></td> 
 			</tr> 
  			<tr> 
 				<td><form:label path="color">Color del Vehiculo</form:label></td> 
 				<td><form:input path="color" /></td> 
 			</tr> 
  			<tr> 
 				<td><form:label path="rs">Razon Social</form:label></td> 
 				<td><form:input path="rs" /></td> 
 			</tr> 
  			<tr> 
 				<td><form:label path="branch">Marca del Vehiculo</form:label></td> 
 				<td><form:input path="branch" /></td> 
 			</tr> 
  			<tr> 
 				<td><form:label path="subbranch">Submarca del Vehiculo</form:label></td> 
 				<td><form:input path="subbranch" /></td> 
 			</tr>  
 			
<!--  			<tr>  -->
<%--  				<td><form:label path="ip">IP</form:label></td>  --%>
<%--  				<td><form:input path="ip" /></td>  --%>
<!--  			</tr>  -->
 			<tr> 
<%--  				<td><form:label path="fechaModificacion">Fecha 1</form:label></td>  --%>
<%--  				<td><form:input path="fechaModificacion" /></td>  --%>
 			</tr> 
 			<tr> 
<%--  				<td><form:label path="fechaCaptura">Fecha 2</form:label></td>  --%>
<%--  				<td><form:input path="fechaCaptura" /></td>  --%>
 			</tr> 

 			<tr> 
 				<td><input type="submit" value="Guardar" /></td> 
 			</tr> 
 		</table> 
 	</form:form> 

	<center>
		<a href="/datosVehiculos">Ir a datos de vehiculo</a>
	</center>

</body>
</html>