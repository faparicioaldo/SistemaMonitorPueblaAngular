app.controller("datosReferencia1Ctrl", ['$scope', '$location', '$rootScope', function($scope, $location, $rootScope) {
	$rootScope.numberTab = 5;
	$rootScope.mostrarPestaniasAnalista = false;
	$rootScope.mostrarPestaniasCliente = true;

	$scope.desicionRedireccionar = function() {
		var tieneConyuge = $rootScope.tieneConyuge();
		
		if(tieneConyuge){
			$scope.redirect('datosConyuge');			
		}else{
			$scope.redirect('otrosDatos');
		}

	}	
	$scope.redirect = function(ruta) {
		console.log(ruta);
		$location.path(ruta);
	}
}]);