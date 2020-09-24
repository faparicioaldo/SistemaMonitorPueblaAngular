app.controller("indexCtrl", ['$location','$scope','$rootScope',function($location,$scope,$rootScope) {
	$rootScope.numberTab = 0;
	$rootScope.mostrarPestaniasAnalista = true;
	$rootScope.mostrarPestaniasCliente = false;
	
	$scope.init = function() {
		console.log('init');
		alert("INIT");
	};

	$scope.mensajeInformacionRegimen1 = function() {
//		console.log('indexView.jsp');
		var respuesta = {};
		respuesta.respuestaCode = -1;
		respuesta.descripcionCode = 'La afiliaci&oacute;n de'+
			'personas bajo el "R&eacute;gimen de Incorporaci&oacute;n Fiscal"'+
			'no podr&aacute; realizar su tr&aacute;mite por este sitio, les'+
			'agradeceremos realizarlo de manera presencial en alguna de las'+
			'sucursales del Instituto FONACOT';
		respuesta.ruta = "reglasFiel";
		$rootScope.validaMensajeFatal(respuesta);
	};
}]);