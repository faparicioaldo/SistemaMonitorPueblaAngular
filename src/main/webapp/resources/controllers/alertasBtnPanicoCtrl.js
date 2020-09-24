app.controller("alertasBtnPanicoCtrl", ['$scope','FuncionesService', '$location', '$rootScope', function($scope, FuncionesService, $location, $rootScope) {
	$rootScope.numberTab = 0;
	
	$scope.listaAlertasEnviadasSemovi = [];

	$scope.init = function() {
		console.log("init alertasBtnPanicoCtrl")
		getPannicButtonAlerts();
	}
	
	function getPannicButtonAlerts() {
		console.log('Cargando alertas enviadas a semovi....');

        FuncionesService.POST("/MonitorAlertasPuebla/getPannicButtonAlerts").then(
            function(respuesta) {
				//console.log("datos alertas enviadas semovi respuesta: " + JSON.stringify(respuesta, null, '\t'));
				
                if (respuesta) {
                	$scope.listaAlertasEnviadasSemovi = respuesta.listaAlertasEnviadasSemovi;                	
                }
            }
        );
    };
    
	$scope.enviarAlertaSemovi = function(idAlerta){
		console.log("Enviando alerta: " + idAlerta);
		FuncionesService.POST("/MonitorAlertasPuebla/enviarAlertaSemovi", idAlerta).then(
            function(respuesta) {
				console.log("respuesta envio de alerta: " + JSON.stringify(respuesta, null, '\t'));
				
                if (respuesta) {
                }
            }
        );
	}

	$scope.descartarAlertaCeiba = function(idAlerta){
		console.log("Descartando alerta: " + idAlerta);
		FuncionesService.POST("/MonitorAlertasPuebla/descartarAlarma", idAlerta).then(
            function(respuesta) {
				console.log("respuesta descartar alerta: " + JSON.stringify(respuesta, null, '\t'));
				
                if (respuesta) {
                }
            }
        );
	}
	/*
	
	$scope.descartarAlarmaPOST = function(idAlarma, deviceid){
		var formData = {
		        idAlarma : idAlarma, 
		        deviceid : deviceid
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
	*/
	/*
 * INICIA FUNCIONALIDAD DE CLIENTE WEB SOCKET
 */
	/*
	'use strict';
	
	var stompClient = null;
	
	function connect() {
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
	}
	
	function onConnected() {
	    stompClient.subscribe('/topic/public', onMessageReceived);
	}

	function onError(error) {
		alert("No se ha podido conectart al servidor de websocket. Por favor, recargue la pagina para intentar nuevamente!");
	}

	function onMessageReceived(payload) {
 	    var message = JSON.parse(payload.body);
 	    alert(message.content + " Se encontraron alertas nuevas!!");
	    location.reload();
	    
	}
	*/
	/*
	 * TERMINA FUNCIONALIDAD DE CLIENTE WEB SOCKET
	 */
	
}]);