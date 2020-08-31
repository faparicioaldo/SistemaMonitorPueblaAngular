app.controller("documentosGeneralesTrabajadorCtrl", ['$scope', '$location', '$rootScope', 'FuncionesService', function($scope, $location, $rootScope, FuncionesService) {
	$rootScope.numberTab = 7;
	$rootScope.mostrarPestaniasAnalista = false;
	$rootScope.mostrarPestaniasCliente = true;

	$scope.redirect = function(ruta) {
		console.log(ruta);
		$location.path(ruta);
	}
	
	$scope.terminarValidacionPreafiliacion = function(estatus) {
		console.log(estatus);
		
		var terminar = confirm("Una vez terminada la validacion no podra volver a editar los datos ¿Esta seguro que desea terminar?");

		if(terminar == true){
	        FuncionesService.POST('/PortalAnalistasAfiliacion/terminarValidacionPreafiliacion/'+estatus+'/'+$rootScope.datosDocumentos.idSucursal, $rootScope.datosCentroTrabajo).then(
	            function(respuesta) {
	
	                if (respuesta) {                	
	                	alert("Preafiliacion cerrada exitosamente.");
	                	$scope.redirect("preafiliacionesValidadas");
	                }
	            }
		    );
        }
	}
	
}]);