<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value = "/resources/styles.css"/>">

</head>
<body>
<center>
<h1>MONITOR DE ALARMAS (BOTON DE PANICO)</h1>
</center>
<table class="blueTable">
    <!-- here should go some titles... -->
    <tr>
        <th>#ALARMA</th>
        <th>PLACA</th>
        <th>IMEI</th>
        <th>IP</th>
        <th>EMPRESA</th>
        <th>CONCESIONARIA</th>
        <th>LATITUD</th>
        <th>LONGITUD</th>
    </tr>
    <c:forEach varStatus="loopCounter"
        items="${alarmas}" var="alarma">
    <tr>
        <td>
            <c:out value="${alarma.alarmaid}" />
        </td>
        <td>
            <c:out value="${alarma.placa}" />
        </td>
        <td>
            <c:out value="${alarma.imei}" />
        </td>
        <td>
            <c:out value="${alarma.ip_dispositivo}" />
        </td>
        <td>
            <c:out value="${alarma.empresa}" />
        </td>
        <td>
            <c:out value="${alarma.concesionaria}" />
        </td>
        <td>
            <c:out value="${alarma.latitud}" />
        </td>
        <td>
            <c:out value="${alarma.longitud}" />
        </td>
    </tr>
    </c:forEach>
</table>

</body>
</html>