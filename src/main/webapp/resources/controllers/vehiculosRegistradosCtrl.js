app.controller("vehiculosRegistradosCtrl", ['$scope','utilityService','FuncionesService', '$location', '$rootScope', function($scope, utilityService, FuncionesService, $location, $rootScope) {
	$rootScope.numberTab = 1;
	
	$scope.listaVehiculosRegistrados = [];

	var vm = this;
  	vm.sendEvent = function(datosVehiculo) {
    	$scope.$parent.$broadcast('msg', datosVehiculo);
  	};

	$scope.init = function() {
		console.log("init vehiculosRegistradosCtrl")
		cargaVehiculosRegistrados();
	}

	$scope.faltanDatos = function(datosVehiculo) {
		var faltanDatos = false;
				
		if(datosVehiculo.empresa == undefined || datosVehiculo.iddispositivo == ''){
			faltanDatos = true;
		}
		if(datosVehiculo.imei == undefined || datosVehiculo.imei == ''){
			faltanDatos = true;
		}
		if(datosVehiculo.plate == undefined || datosVehiculo.plate == ''){
			faltanDatos = true;
		}
		if(datosVehiculo.vin == undefined || datosVehiculo.vin == ''){
			faltanDatos = true;
		}
		if(datosVehiculo.engine == undefined || datosVehiculo.engine == ''){
			faltanDatos = true;
		}
		if(datosVehiculo.year == undefined || datosVehiculo.year == ''){
			faltanDatos = true;
		}
		if(datosVehiculo.color == undefined || datosVehiculo.color == ''){
			faltanDatos = true;
		}
		if(datosVehiculo.route == undefined || datosVehiculo.route == ''){
			faltanDatos = true;
		}
		if(datosVehiculo.rs == undefined || datosVehiculo.rs == ''){
			faltanDatos = true;
		}
		
		//return "faltanDatos";
		return (faltanDatos) ? "faltanDatos" : "";
		
	};				

	function validaVacio(dato){
		var isVacio = false;
		if(dato == undefined || dato == ''){
			isVacio = true;
		}
		return isVacio;
	}

	$scope.editarDatosVehiculo = function(datosVehiculo) {
		console.log("init editarDatosVehiculo")
		
		console.log("datos alertas enviadas semovi respuesta: " + JSON.stringify(datosVehiculo, null, '\t'));
		
		$rootScope.datosVehiculoEditar = datosVehiculo;		
		
		mostrarModalEditarVehiculo();
		
		vm.sendEvent(datosVehiculo);
	}
		
	function cargaVehiculosRegistrados() {
		console.log('Cargando vehiculos registrados....');

        FuncionesService.POST("/MonitorAlertasPuebla/cargaDatosVehiculos").then(
            function(respuesta) {
				//console.log("datos alertas enviadas semovi respuesta: " + JSON.stringify(respuesta, null, '\t'));
				
                if (respuesta) {
                	$scope.listaVehiculosRegistrados = respuesta.listaVehiculosRegistrados;                	
                }
            }
        );
    };
    
	/*
	$scope.enviarAlarmaPOST = function(idAlarma, deviceid){
		var formData = {
	        idAlarma : idAlarma,
	        deviceid : deviceid
	    }
	    alert("Enviando alarma....");
	    $.ajax({
		    type : "POST",
		    contentType : "application/json",
		    url : "/enviarAlarma",
		    data : JSON.stringify(formData),
		    dataType : 'json',
		    success : function(result) {
		    	if(result.idAlarma == -1 )
			        alert('Atencion: No ha capturado datos del vehiculo, presione la opcion Ir a Datos del Vehiculo.');
		    	else
		    		alert('Alarma ' + result.idAlarma + ' enviada. RESPUESTA: ' + result.deviceid);
	    		location.reload();
		    }
	  	});
	}
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