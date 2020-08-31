var scopeHolder;

app.component("modalEditarVehiculo", {
    templateUrl: "/MonitorAlertasPuebla/components/modalEditarVehiculo.jsp",
    controller: ['$scope','$rootScope','utilityService','FuncionesService', function($scope,$rootScope,utilityService,FuncionesService) {
    	$scope.datosVehiculoSeleccionado = {};
		
		var vm = this;
	  	vm.data = '';
	  	$scope.$on('msg', function(evt, msg){
	    	vm.data = msg;
	    	$scope.datosVehiculoSeleccionado = vm.data;
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
	                	utilityService.mostrarMensaje("Guardado Correcto..");
	                }
	            }
		    );
    	};
    	
        $scope.cerrar = function(){
        	$("#modalEditarVehiculo").modal('hide');
        };
    }]
});

app.component("modalBuscadorct", {
    templateUrl: "/MonitorAlertasPuebla/components/modalBuscadorCT.jsp",
    controller: ['$scope','$rootScope','utilityService','FuncionesService', function($scope,$rootScope,utilityService,FuncionesService) {
    	$scope.datosBusquedaCt = {};
    	$scope.catalogos = {};
    	$scope.municipios = {};
    	$scope.centrosTrabajo = {};
    	$scope.busquedaExitosa = "na";

    	$scope.init = function() {
    		$scope.catalogos = utilityService.getCatalogoEstados();
    	};

    	$scope.consultaMunicipios = function() {
    		console.log("Consulta municipios...");
	        FuncionesService.POST("/MonitorAlertasPuebla/consultaMunicipioXEstado", $scope.datosBusquedaCt.claveEstado).then(
	            function(respuesta) {
					console.log("Municipios: " + JSON.stringify(respuesta.municipios));
	                if (respuesta) {
	                	$scope.municipios = respuesta.municipios;
	                }
	            }
		    );
    	};

    	$scope.realizarBusqueda = function() {
			console.log("inicio realizarBusqueda...");
			
			console.log("Busqueda de Centros de Trabajo: " + JSON.stringify($scope.datosBusquedaCt));
    		
	        FuncionesService.POST("/MonitorAlertasPuebla/busquedaCentrosTrabajoXNombreAndEstadoAndMunicipio", $scope.datosBusquedaCt).then(
	            function(respuesta) {
					console.log("Consulta de Centros de trabajo concluida. " + respuesta);

	                if (respuesta) {
						console.log("Centros Trabajo: " + JSON.stringify(respuesta.centrosTrabajo));
	                	console.log(respuesta.respuestaCode);
		                if(respuesta.respuestaCode == 404){
							$scope.busquedaExitosa = "no";
				        	$scope.datosBusquedaCt = {};
		                }else{
							$scope.busquedaExitosa = "si";
		                	$scope.centrosTrabajo = respuesta.centrosTrabajo;		                	
		                }

	                }else{
						console.log("No se encontro centros de trabajo: ");
	                }	                
	            }
		    );
    	};

    	$scope.seleccionarCentroTrabajo = function(clienteId, numSegSocial, nomCompleto) {
    		var ctSeleccionado = {};
    		ctSeleccionado.clienteId = clienteId;
    		ctSeleccionado.numSegSocial = numSegSocial;
    		ctSeleccionado.nomCompleto = nomCompleto;
    		utilityService.asignaDatosCentroTrabajo(ctSeleccionado);
    		this.limpiar();
        	$("#modalBuscadorCT").modal('hide');
    	};

        $scope.limpiar = function(){
        	$scope.datosBusquedaCt = {};
        	$scope.busquedaExitosa = "na";        	
        };

        $scope.cerrar = function(){
    		this.limpiar();
        	$("#modalBuscadorCT").modal('hide');
        };
    }],
    bindings: {
        captcha: "=",
        funcionSubmit: "&"
    }
});
app.component("modalBuscadorcp", {
    templateUrl: "/MonitorAlertasPuebla/components/modalBuscadorCP.jsp",
    controller: ['$scope', '$rootScope','utilityService','FuncionesService', function($scope, $rootScope,utilityService,FuncionesService) {
		console.log("cargando init modalBuscadorCP");
    	$scope.codigoPostalSeleccionado = "";
    	$scope.domicilios = {};
    	$scope.busquedaExitosa = "na";
    	    	
    	$scope.cambio = function() {
    		console.log("cargando cambio");
    	};    	

    	$scope.buscarDomiciliosPorCP = function(cp) {
    		console.log("cargando init modalBuscadorCP");

//    		$scope.codigoPostalSeleccionado = utilityService.getCodigoPostalSeleccionado();
    		if($scope.codigoPostalSeleccionado == undefined){
    			alert("Primero debe ingresar un codigo postal");
    			$('#modalBuscadorCP').modal('hide');
    		}else{
	        	FuncionesService.POST("/MonitorAlertasPuebla/busquedaDatosDireccionXCodigoPostal", $scope.codigoPostalSeleccionado).then(
		            function(respuesta) {
						console.log("Domicilios: " + JSON.stringify(respuesta.domicilios));
						
		                if (respuesta) {
			                if(respuesta.respuestaCode == 404){
								$scope.busquedaExitosa = "no";
			                }else{
								$scope.busquedaExitosa = "si";
			                	$scope.domicilios = respuesta.domicilios;
			                }
			                
		                }
		            }
			    );
    		}
    	};
        scopeHolder = $scope.buscarDomiciliosPorCP;

    	$scope.seleccionarDomicilio = function(nombreColonia, nombreMunicipio, nombreEstado, claveColonia) {
    		console.log("seleccionarDomicilio: " + nombreColonia);
    		var domicilioSeleccionado = {};
    		domicilioSeleccionado.codPostal = $scope.codigoPostalSeleccionado;
    		domicilioSeleccionado.nombreColonia = nombreColonia;
    		domicilioSeleccionado.nombreMunicipio = nombreMunicipio;
    		domicilioSeleccionado.nombreEstado = nombreEstado;
    		domicilioSeleccionado.claveColonia = claveColonia;
    		
    		utilityService.asignaDatosDomicilioCP(domicilioSeleccionado);
			$scope.busquedaExitosa = "na";
			$scope.codigoPostalSeleccionado = "";

        	$("#modalBuscadorCP").modal('hide');
    	};

        $scope.cerrar = function(){
			$scope.busquedaExitosa = "na";
        	$("#modalBuscadorCP").modal('hide');
        };

    }],
    bindings: {
    	cp: "="
    }
});
app.component("modalCaptcha", {
    templateUrl: "/MonitorAlertasPuebla/components/modalCaptcha.jsp",
    controller: ['$scope', function($scope) {
        $scope.disableSubmit = function() {
            return !$scope.$ctrl.captcha;
        };
        $scope.submit = function(){
//        	console.log("submit");
        	$("#captchaModal").modal('hide');
//        	console.log($scope.$ctrl.funcionSubmit);
        	$scope.$ctrl.funcionSubmit();
        };
    }],
    bindings: {
        captcha: "=",
        funcionSubmit: "&"
    }
});

app.component("modalTerminarPreafiliacion", {
    templateUrl: "/MonitorAlertasPuebla/components/modalTerminarPreafiliacion.jsp",
    controller: ['$scope','utilityService', function($scope, utilityService) {
        $scope.disableSubmit = function() {
            return !$scope.$ctrl.captcha;
        };
        $scope.submit = function(){
        	$("#modalTerminarPreafiliacion").modal('hide');
        	$scope.$ctrl.funcionSubmit();
        };
        $scope.aceptar = function(){
    		utilityService.terminarPreafiliacion();
        	$("#modalTerminarPreafiliacion").modal('hide');
        	$scope.$ctrl.funcionSubmit();
        };

    }],
    bindings: {
        captcha: "=",
        funcionSubmit: "&"
    }
});
app.component("timer", {
    templateUrl: "/MonitorAlertasPuebla/components/modalTimer.jsp",
    controller: ['$scope', '$interval', 'FuncionesService', '$location', function($scope, $interval, FuncionesService, $location) {
		$scope.init = function(){
			//Se agrega una consulta al servidor para que la cookie no se vea afectada
			FuncionesService.POST("infoCodPostal.infonacot", 12345).then(
					function(respuesta) {
						if(respuesta){
							$scope.tiempo = 6 * 60;							
						} else{
							$scope.finish();
						}
					}
				);
		}
		$scope.finish = function(){
			$("#timerModal").modal('hide');
			$interval.cancel($scope.interval);
			$location.path("/");
		}
        $scope.interval = $interval(function () {
			if(!$scope.$$destroyed){				
				$scope.tiempo--;
//				console.log($scope.tiempo);
//				console.log(Math.floor($scope.tiempo/60) + ":" +$scope.tiempo%60);
				if($scope.tiempo<=0){
					$scope.finish();
				} else if($scope.tiempo==90){
					$("#timerModal").modal('show');
				}
			} else {
				$scope.finish();
			}
        }, 1000);
		$scope.restartInterval = function(){
			$scope.init();
		}			
    }]
});
app.component("alertError", {
    templateUrl: "/MonitorAlertasPuebla/components/alertError.jsp"
});
app.component("modalMensaje", {
    templateUrl: "/MonitorAlertasPuebla/components/modalMensaje.jsp"
});
app.component("modalMensajepdf", {
    templateUrl: "/MonitorAlertasPuebla/components/modalMensajePDF.jsp"
});
app.component("loading", {
    templateUrl: "/MonitorAlertasPuebla/components/loading.jsp"
});
app.component("footerPage", {
    templateUrl: "/MonitorAlertasPuebla/components/footer.jsp"
});