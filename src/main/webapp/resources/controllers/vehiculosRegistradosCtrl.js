app.controller("vehiculosRegistradosCtrl", ['$scope','utilityService','FuncionesService', '$location', '$rootScope', 'MonitorService', function($scope, utilityService, FuncionesService, $location, $rootScope, MonitorService) {
	$rootScope.numberTab = 1;
	
	$scope.listaVehiculosRegistrados = [];
	$scope.infoVehiculos = {};
	$scope.infoVehiculos.total = 0;
	$scope.infoVehiculos.totalFaltaDatos = 0;
	
	var vm = this;
  	vm.sendEvent = function(datosVehiculo) {
    	$scope.$parent.$broadcast('msg', datosVehiculo);
  	};

	MonitorService.receive().then(null, null, function(message) {
  	});
  
	$scope.init = function() {
		console.log("init vehiculosRegistradosCtrl")
		cargaVehiculosRegistrados();		
	}
	
	$scope.faltanDatos = function(datosVehiculo) {
		
		//console.log("datos vehiculo: " + JSON.stringify(datosVehiculo, null, '\t'));

		var faltanDatos = false;
		if(datosVehiculo == undefined){
			faltanDatos = true;			
		}else
		if(datosVehiculo.empresa == undefined || datosVehiculo.iddispositivo == ''){
			faltanDatos = true;
		}else
		if(datosVehiculo.imei == undefined || datosVehiculo.imei == ''){
			faltanDatos = true;
		}else
		if(datosVehiculo.plate == undefined || datosVehiculo.plate == ''){
			faltanDatos = true;
		}else
		if(datosVehiculo.vin == undefined || datosVehiculo.vin == ''){
			faltanDatos = true;
		}else
		if(datosVehiculo.engine == undefined || datosVehiculo.engine == ''){
			faltanDatos = true;
		}else
		if(datosVehiculo.year == undefined || datosVehiculo.year == ''){
			faltanDatos = true;
		}else
		if(datosVehiculo.color == undefined || datosVehiculo.color == ''){
			faltanDatos = true;
		}else
		if(datosVehiculo.route == undefined || datosVehiculo.route == ''){
			faltanDatos = true;
		}else
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
		
		//console.log("datos alertas enviadas semovi respuesta: " + JSON.stringify(datosVehiculo, null, '\t'));
		
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
                	
                	$scope.infoVehiculos.total = $scope.listaVehiculosRegistrados.length;
                	
                	console.log("Total vehiculos: " + $scope.infoVehiculos.total);
                	//console.log("Total vehiculos: " + $scope.infoVehiculos.totalFaltaDatos.length);
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
	
}]);