app.controller("preafiliacionesPorValidarCtrl", ['$scope', 'FuncionesService', '$location', '$rootScope', function($scope, FuncionesService, $location, $rootScope) {
	$rootScope.numberTab = 8;

	$scope.init = function() {
		console.log('init');
        
    	$scope.preafiliacionesAnalista = {};
		var idPreafiliacion = 1;
		cargaPreafiliacionesPorValidar();
	};
	$scope.sortKey = "";
	$scope.reverse = false;
	 $scope.sort = function(keyname){
	        $scope.sortKey = keyname;   //set the sortKey to the param passed
	        $scope.reverse = !$scope.reverse; //if true make it false and vice versa
	    };
	
	$scope.redirect = function(idPreafiliacion) {
		$rootScope.mostrarPestaniasAnalista = false;
		$rootScope.mostrarPestaniasCliente = true;
		$rootScope.preafiliacionSelecionada = idPreafiliacion;
		$location.path('/datosGeneralesTrabajador');
	};

	function cargaPreafiliacionesPorValidar() {
		console.log('Cargando preafiliaciones asignadas a analista....');

        FuncionesService.POST("/PortalAnalistasAfiliacion/consultaPreafiliacionesAnalista").then(
            function(respuesta) {
        		console.log('combos respuesta: ' + respuesta);		

                if (respuesta) {
                	$scope.preafiliacionesAnalista = respuesta.preafiliaciones;
                }
            }
        );
    };
}]);