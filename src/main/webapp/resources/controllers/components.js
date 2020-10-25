var scopeHolder;

app.component("modalEditarVehiculo", {
    templateUrl: "/MonitorAlertasPuebla/components/modalEditarVehiculo.jsp",
    controller: ['$scope','$rootScope','utilityService','FuncionesService', function($scope,$rootScope,utilityService,FuncionesService) {
    	$scope.datosVehiculoSeleccionado = {};
		$scope.municipios = [];
		
		$scope.init = function() {
    		$scope.municipios = utilityService.getCatalogoMunicipios();
    	};
		
		var vm = this;
	  	vm.data = '';
	  	$scope.$on('msg', function(evt, msg){
	    	vm.data = msg;
	    	$scope.datosVehiculoSeleccionado = vm.data;
	    	
	    	//Este es un parche se debe manejar el tipo de dato municipio como numero en la base
	    	$scope.datosVehiculoSeleccionado.municipio = parseInt($scope.datosVehiculoSeleccionado.municipio,10);
	    	//alert(JSON.stringify($scope.datosVehiculoSeleccionado, null, '\t'));
	  	});
		
		$scope.limpiarFormulario = function() {
			console.log("init limpiarFormulario");
			var iddispositivoback = $scope.datosVehiculoSeleccionado.iddispositivo;
			$scope.datosVehiculoSeleccionado = {};
			$scope.datosVehiculoSeleccionado.iddispositivo = iddispositivoback; 
		};

		$scope.guardarDatosVehiculo = function() {
			console.log("guardando datos vehiculo...");
			
			FuncionesService.POST("/MonitorAlertasPuebla/guardarDatosVehiculo", $scope.datosVehiculoSeleccionado).then(
	            function(respuesta) {
					//console.log("Municipios: " + JSON.stringify(respuesta));
	                if (respuesta) {
                		utilityService.mostrarMensaje(respuesta.descripcionCode);
                	}
	            }
		    );
    	};
    	
        $scope.cerrar = function(){
        	$("#modalEditarVehiculo").modal('hide');
        };
    }]
});

app.component("alertError", {
    templateUrl: "/MonitorAlertasPuebla/components/alertError.jsp"
});
app.component("modalMensaje", {
    templateUrl: "/MonitorAlertasPuebla/components/modalMensaje.jsp"
});
app.component("loading", {
    templateUrl: "/MonitorAlertasPuebla/components/loading.jsp"
});
app.component("footerPage", {
    templateUrl: "/MonitorAlertasPuebla/components/footer.jsp"
});