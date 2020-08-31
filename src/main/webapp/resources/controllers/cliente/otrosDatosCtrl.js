app.controller("otrosDatosCtrl", ['$scope', '$location', '$rootScope', function($scope, $location, $rootScope) {
	$rootScope.numberTab = 3;
	$rootScope.mostrarPestaniasAnalista = false;
	$rootScope.mostrarPestaniasCliente = true;

	$scope.desicionRedireccionar = function() {
		var tieneConyuge = $rootScope.tieneConyuge();
		
		if(tieneConyuge){
			$scope.redirect('datosConyuge');			
		}else{
			$scope.redirect('datosReferencia1');
		}

	}
	$scope.redirect = function(ruta) {
		console.log(ruta);
		$location.path(ruta);
	}
}]);