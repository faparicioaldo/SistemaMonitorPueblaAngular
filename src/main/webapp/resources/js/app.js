function cambiarTexto(idArchivo, idCajita){
	var fileInput = document.getElementById(""+idArchivo+"");
	var filename = fileInput.files[0].name;
	$("#"+idCajita+"").val(filename);
}

function completeLeftZeroes(strValue, nNumDigits)
{
  if(nNumDigits <= 0 )
    return strValue;
  
  if(!strValue)
    strValue = "";
    
  var numDigitsLeft = nNumDigits - strValue.length;
  
  for(var i = 0; i < numDigitsLeft; i++)
  {
    strValue = '0' + strValue;
  }
  
  return strValue;
}

function upperCase(value) {
	if (value.length > 0) {
		value = trimAccents(trim(value.toUpperCase()));
	}
	return value;
}

function desactivaAutocompletar() {
	for (i = 0; i < document.forms.length; i++) {
		document.forms[i].setAttribute("AutoComplete", "off");
	}
}

function acceptNum(evt) {
	var nav4 = window.Event.srcElement ? true : false;
	var key = nav4 ? evt.which : evt.keyCode;
	if (key <= 13) {
		return true;
	} else if (key >= 48 && key <= 57) {
		return true;
	}
	return false;
}

function validarAlfa(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8)
		return true;
	patron = /^[a-zA-Z0-9.,& \u00E0-\u00FC]+$/i;
	te = String.fromCharCode(tecla);
	return patron.test(te);
}

function validarAlfaRazonSocial(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8)
		return true;
	patron = /^[a-zA-Z0-9-.,&)( \u00E0-\u00FC]+$/i;
	te = String.fromCharCode(tecla);
	return patron.test(te);
}

function validarNumerico(e) {
	teclan = (document.all) ? e.keyCode : e.which;
	if (teclan == 8)
		return true;
	patronn = /[0-9]/;
	ten = String.fromCharCode(teclan);
	return patronn.test(ten);
}

function validarDir(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8)
		return true;
	patron = /^[a-zA-Z0-9.,#&\- \u00E0-\u00FC]+$/i;
	te = String.fromCharCode(tecla);
	return patron.test(te);
}

function validarAlfaPuro(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8)
		return true;
	patron = /^[a-zA-Z0-9&]/;
	te = String.fromCharCode(tecla);
	return patron.test(te);
}

function validarAlfaLatinoPuro(e) {
	tecla = (document.all) ? e.keyCode : e.which;
	if (tecla == 8)
		return true;
	patron = /^[a-zñA-ZÑ0-9&]/;
	te = String.fromCharCode(tecla);
	return patron.test(te);
}

var app = angular.module("fonacotApp", [ "ngRoute", "ngSanitize", "ngMaterial",
		"ngMessages", "ui.mask", "angularUtils.directives.dirPagination" ]);

app.config([ '$routeProvider', '$compileProvider',
	function($routeProvider, $compileProvider) {
				$compileProvider.debugInfoEnabled(false);
	
    	$compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|javascript|local|data|chrome-extension):/);
		$compileProvider.debugInfoEnabled(false);
		$compileProvider.commentDirectivesEnabled(false);
		$compileProvider.cssClassDirectivesEnabled(false);
		
			$routeProvider.when("/", {
				templateUrl : "views/indexView.jsp",
				controller : "indexCtrl"
					
			}).when("/datosGeneralesTrabajador", {
				templateUrl : "/PortalAnalistasAfiliacion/views/cliente/datosGeneralesTrabajadorView.jsp",
				controller : "datosGeneralesTrabajadorCtrl"
			}).when("/datosLaborales", {
				templateUrl : "/PortalAnalistasAfiliacion/views/cliente/datosLaboralesView.jsp",
				controller : "datosLaboralesCtrl"
			}).when("/domicilioTrabajador", {
				templateUrl : "/PortalAnalistasAfiliacion/views/cliente/domicilioTrabajadorView.jsp",
				controller : "domicilioTrabajadorCtrl"
			}).when("/otrosDatos", {
				templateUrl : "/PortalAnalistasAfiliacion/views/cliente/otrosDatosView.jsp",
				controller : "otrosDatosCtrl"
			}).when("/datosConyuge", {
				templateUrl : "/PortalAnalistasAfiliacion/views/cliente/datosConyugeView.jsp",
				controller : "datosConyugeCtrl"
			}).when("/datosReferencia1", {
				templateUrl : "/PortalAnalistasAfiliacion/views/cliente/datosReferencia1View.jsp",
				controller : "datosReferencia1Ctrl"
			}).when("/datosReferencia2", {
				templateUrl : "/PortalAnalistasAfiliacion/views/cliente/datosReferencia2View.jsp",
				controller : "datosReferencia2Ctrl"
			}).when("/documentosGeneralesTrabajador", {
				templateUrl : "/PortalAnalistasAfiliacion/views/cliente/documentosGeneralesTrabajadorView.jsp",
				controller : "documentosGeneralesTrabajadorCtrl"

			}).when("/monitorAlarmas", {
				templateUrl : "/MonitorAlertasPuebla/views.spa/monitorAlarmas.jsp",
				controller : "monitorAlarmasCtrl"
			}).when("/vehiculosRegistrados", {
				templateUrl : "/MonitorAlertasPuebla/views.spa/vehiculosRegistrados.jsp",
				controller : "vehiculosRegistradosCtrl"

			}).when("/preafiliacionesPorValidar", {
				templateUrl : "/PortalAnalistasAfiliacion/views/analista/preafiliacionesPorValidarView.jsp",
				controller : "preafiliacionesPorValidarCtrl"
			}).when("/preafiliacionesValidadas", {
				templateUrl : "/PortalAnalistasAfiliacion/views/analista/preafiliacionesValidadasView.jsp",
				controller : "preafiliacionesValidadasCtrl"
			});
	}]);

app.run([
	'$rootScope',
	'$q',
	'$interval',
	'$sce',
	'$timeout',
	'$location',
	'FuncionesService',
	function($rootScope, $q, $interval, $sce, $timeout, $location, FuncionesService) {
		$rootScope.fechaActual = new Date();		
		$rootScope.datosGeneralesTrabajador = {};

		$rootScope.datosConyuge = {};

		$rootScope.datosRef1 = {};
		$rootScope.datosRef2 = {};
		$rootScope.datosLaborales = {};
		$rootScope.otrosDatos = {};
		
		$rootScope.datosPreafiliacion = {};
		$rootScope.datosComplemento = {};

		$rootScope.datosDocumentos = {};
		$rootScope.catalogos = {};
		$rootScope.faltaCompletarCampos = true;

		$rootScope.formDatosGenerales = {};
		$rootScope.formDatosGenerales.complete = false;
		$rootScope.formDatosLaborales = {};
		$rootScope.formDatosLaborales.complete = false;
		$rootScope.formDomicilioTrabajador = {};
		$rootScope.formDomicilioTrabajador.complete = false;
		$rootScope.formOtrosDatos = {};
		$rootScope.formOtrosDatos.complete = false;
		$rootScope.formDatosConyuge = {};
		$rootScope.formDatosConyuge.complete = false;
		$rootScope.formDatosRef1 = {};
		$rootScope.formDatosRef1.complete = false;
		$rootScope.formDatosRef2 = {};
		$rootScope.formDatosRef2.complete = false;

        $rootScope.datosComplemento.rutaComprobanteDomicilio = 'NO_CARGADO';
        $rootScope.datosComplemento.rutaIdentificacionOficial = 'NO_CARGADO';
        $rootScope.datosComplemento.rutaReciboNomina1 = 'NO_CARGADO';
        $rootScope.datosComplemento.rutaReciboNomina2 = 'NO_CARGADO';
        $rootScope.datosComplemento.rutaReciboNomina3 = 'NO_CARGADO';

        
		$rootScope.datosVehiculoEditar = [];

			$location.path('/monitorAlarmas');
			
			$rootScope.tabs = [ 
					"Alertas Panico",
					"Vehiculos Registrados",
					"Monitor",
					"Operaciones Semovi"
					];

		$rootScope.isTabActive = function(number) {
			return ($rootScope.numberTab == number) ? "active" : "";
		};				

		$rootScope.disableTab = function(number) {
			if ($rootScope.numberTab == number) {
				return true;			
			}else{
				return false;				
			}
		};				
		
		$rootScope.existJQuery = function() {
			var defered = $q.defer();
			var promise = defered.promise;
	
			var intervalo = $interval(
				function() {
					if (typeof $ !== 'undefined' && typeof $('[data-toggle="tooltip"]').tooltip !== 'undefined') {
						$interval.cancel(intervalo);
						defered.resolve($);
					}
				}, 100
			);
	
			return promise;
		};
		$rootScope.validaMensajeFatal = function(respuesta) {
			if (respuesta.respuestaCode != 0) {
				if (respuesta.descripcionCode.length > 0) {
					$rootScope
						.existJQuery()
						.then(
							function(data) {
								$rootScope.errores = respuesta;
								if (typeof respuesta.ruta != 'undefined') {
									$("#btnModalMensaje").attr("href","#!" + respuesta.ruta)
								} else {
									$("#btnModalMensaje").removeAttr("href");
								}
								$("#mensaje").modal('toggle');
							});
					return false;
				}
			}
			return true;
		};
		
		$rootScope.modalDescargaPDF = function(respuesta) {
			if (respuesta.respuestaCode != 0) {
				if (respuesta.descripcionCode.length > 0) {
					$rootScope
						.existJQuery()
						.then(
							function(data) {
								$rootScope.errores = respuesta;
//								if (typeof respuesta.ruta != 'undefined') {
//									$("#btnModalMensaje").attr("href","#!" + respuesta.ruta)
//								} else {
//									$("#btnModalMensaje").removeAttr("href");
//								}
								$("#mensajePDF").modal('toggle');
							});
					return false;
				}
			}
			return true;
		};
		
		$rootScope.mostrarTerminarPreafiliacion = function() {
					$rootScope
						.existJQuery()
						.then(
							function(data) {
								$("#modalTerminarPreafiliacion").modal('toggle');
							});
		};
		$rootScope.servicioNoDisponible = function(mensaje) {
			var respuesta = {};
			respuesta.respuestaCode = -1;
			respuesta.descripcionCode = typeof mensaje !== 'undefined' ? mensaje : 'Servicio no disponible';
			$rootScope.validaMensajeFatal(respuesta);
		};
		$rootScope.resetTooltip = function() {
			$rootScope.existJQuery().then(function(data) {
				$('[data-toggle="tooltip"]').tooltip();
			})
		};
		var intento = 0;
		$rootScope.consultarOtroCaptcha = function(actualizaObj) {
			$rootScope.existJQuery().then(
				function(data) {
					intento++;
					$("#imagenCaptcha").removeAttr("src");
					$("#imagenCaptcha").attr("src","consultaImagenCaptcha.infonacot?intento="
									+ (actualizaObj ? intento * -1 : intento));
				})
		};
		$rootScope.$on(
			"$locationChangeStart",
			function(event, next, current) {
				var path = next.split("/#!/")[1];
				path = angular.isUndefined(path) ? "" : path;
				$rootScope.errores = null;
				$rootScope.resetScroll(path.length == 0 ? 0 : 190);
				$rootScope.pathUDM = 'https://b.scorecardresearch.com/b?c1=2&c2=17183199&ns_site=gobmx&name=fonacotCTGob.'
						+ (path.length == 0 ? 'index' : path);
				window.removeEventListener(
						'beforeunload',
						$rootScope.eventoBeforeunload);
				if (path != 'infoCentroTrabajo') {
					$timeout(
							function() {
								desactivaAutocompletar();
								$rootScope
										.consultarOtroCaptcha();
								$rootScope
										.resetTooltip();
							}, 2000);
				}
			}
		);
		$rootScope.resetScroll = function(pos) {
			var position = pos ? pos : 0;
			$rootScope.existJQuery().then(function(data) {
				$(document).scrollTop(position);
			});
		};
		$rootScope.guardaDatosPreafiliacion = function() {
			
				$rootScope.preafiliacionDTO = {};
									
				$rootScope.preafiliacionDTO.datosPreafiliacion = $rootScope.datosPreafiliacion; 
				$rootScope.preafiliacionDTO.datosComplemento = $rootScope.datosComplemento;
				
				console.log("Datos enviados: " + JSON.stringify($rootScope.preafiliacionDTO, null, '\t'));
				
				FuncionesService.POST("/PortalTrabajadoresAfiliacion/guardaDatosPreafiliacion", $rootScope.preafiliacionDTO).then(
		            function(respuesta) {
		                if (respuesta) {
		                			                	
		        			var datosModal = {};
		        			datosModal.respuestaCode = -1;
		        			datosModal.descripcionCode = respuesta.cadenaBase64;
		        			
		        			$rootScope.modalDescargaPDF(datosModal);
		                	
		                }
		            }
		        );			
		};
		$rootScope.cargaCombos = function() {
			console.log('Cargando combos....');		
	
	        FuncionesService.POST("/PortalTrabajadoresAfiliacion/consultaDatosCombos", $scope.consulta).then(
	            function(respuesta) {
	        		console.log('combos respuesta: ' + respuesta);		
	
	                if (respuesta) {
	                	$scope.estados = respuesta.estados;
	                }
	            }
	        );
	    };
	}
]);

app.service('DispacherService',[
'$location',
'FuncionesService',
'$rootScope',
function($location, FuncionesService, $rootScope) {
	var centroTrabajo = {};
	var datos = {};
	this.esConcluido = function(ct) {
		centroTrabajo = ct;
		if (ct.idEstatus == 2) {
			$location.path("/infoCentroTrabajo");
		} else {
			var respuesta = {};
			respuesta.respuestaCode = -1;
			respuesta.descripcionCode = 'Usted ya se encuentra en proceso de validaci&oacute;n. Para mayor informaci&oacute;n comun&iacute;quese al tel. 01 800 FONACOT (3662268) o acuda a nuestras oficinas.';
			$rootScope.validaMensajeFatal(respuesta);
			$location.path("/consultarAfiliacion");
		}
	};
	
	this.insertaValores = function(data) {
		data.rfcCT = centroTrabajo.ctRFC;
		data.registroPatronal = centroTrabajo.registroPatronal;
		centroTrabajo = {};
	};

	this.mantieneDatos = function(registroPatronal) {
		datos.registroPatronal = registroPatronal.toUpperCase();
	};
	this.obtieneDatos = function() {
		return datos;
	};
}]);
		
app.service('FuncionesService',['$http','$q','$rootScope', function($http, $q, $rootScope) {
	// Detecta si el navegador es Internet Explorer
	this.isIE = function() {
		var ua = window.navigator.userAgent;
		var msie = ua.indexOf("MSIE ");
		return msie > 0 || !!ua.match(/Trident.*rv\:11\./);
	};
	// Detecta si el navegador esta en un dispositivo movil
	this.isMobile = function() {
		if (window.navigator.userAgent.match(/Mobile/i)
				|| window.navigator.userAgent
						.match(/Tablet/i)
				|| window.navigator.userAgent
						.match(/iPhone/i)
				|| window.navigator.userAgent
						.match(/iPod/i)
				|| window.navigator.userAgent
						.match(/iPad/i)
				|| window.navigator.userAgent
						.match(/IEMobile/i)
				|| window.navigator.userAgent
						.match(/Windows Phone/i)
				|| window.navigator.userAgent
						.match(/Android/i)
				|| window.navigator.userAgent
						.match(/BlackBerry/i)
				|| window.navigator.userAgent
						.match(/webOS/i)
				|| window.navigator.userAgent
						.match(/Nexus 7/i)
				|| window.navigator.userAgent
						.match(/Nexus 10/i)
				|| window.navigator.userAgent
						.match(/KFAPWI/i)) {
			return true;
		} else {
			return false;
		}
	};
	this.cifrarTexto = function(dato) {
		// var rsa = new RSAKey();
		// rsa.setPublic('A9E55538FF49DB1AA4A1A7B2F0DA09CB87006A004645372A2A33808A5FD37F4211688B189D7529CFBA29B1EB59356137CA9DF6C62E6BF113AE226EC303246B67',
		// '10001');
		// return hex2b64(rsa.encrypt("" + dato));
		return dato;
	};
	this.POST = function(url, datos) {
		var defered = $q.defer();
		var promise = defered.promise;
		$rootScope.loading = true;
		$http.post(url, datos)
		.then(
			function(response) {
				$rootScope.errores = null;
				$rootScope.loading = false;
				var respuesta = response.data;

				console.log("RESPONSE: " + JSON.stringify(response, null, '\t'));

				if ($rootScope.validaMensajeFatal(respuesta)) {
					defered.resolve(respuesta);
				} else {
					if (datos) {
						datos.captcha = null;
					}
					defered.resolve(respuesta.respuestaCode < 0 ? respuesta: null);
				}
			},
			function(error) {
				if (datos) {
					datos.captcha = null;
				}
				$rootScope.loading = false;
				$rootScope.servicioNoDisponible();
				defered.resolve(null);
			}
		);
		return promise;
	}

	this.windowPost = function(url, datos, target,propiedades) {
		var form = document.createElement('form');
		form.setAttribute('method', 'post');
		form.setAttribute('action', url);
		form.setAttribute('target', target);

		Object
			.keys(datos)
			.forEach(
				function(k) {
					var hiddenField = document.createElement("input");
					hiddenField.setAttribute("type", "hidden");
					hiddenField.setAttribute("name", k);
					hiddenField.setAttribute("value", datos[k]);
					form.appendChild(hiddenField);
				}
			);

		document.body.appendChild(form);

		var win = window.open('',target,'width=800,height=650,left=70,top=70,menubar=yes,scrollbars=yes,status=no,location=no,toolbar="0"');
		win.focus();

		form.submit();
	};

	this.tabPost = function(url, datos, target,
			propiedades) {
		var form = document.createElement('form');
		form.setAttribute('method', 'post');
		form.setAttribute('action', url);
		form.setAttribute('target', target);

		Object
				.keys(datos)
				.forEach(
						function(k) {
							var hiddenField = document
									.createElement("input");
							hiddenField.setAttribute(
									"type", "hidden");
							hiddenField.setAttribute(
									"name", k);
							hiddenField.setAttribute(
									"value", datos[k]);
							form
									.appendChild(hiddenField);
						});

		document.body.appendChild(form);

		form.submit();
	};

	this.push = function(array, item, value) {
		if (angular.isUndefined(array[item])) {
			array[item] = [ value ];
		} else {
			array[item].push(value);
		}
	};
} ]);

app.factory('utilityService', ['$http','$q','$rootScope', function($http, $q, $rootScope) {
	  return {
			getCatalogoEstados: function() {
				return $rootScope.catalogos;
			},
			getCodigoPostalSeleccionado: function() {
			  return $rootScope.datosPreafiliacion.codPostal;
			},
			asignaDatosCentroTrabajo: function(ctSeleccionado) {
				$rootScope.datosLaborales.nombreCt = ctSeleccionado.nomCompleto;
				$rootScope.datosLaborales.numSegSocialCt = ctSeleccionado.numSegSocial;			    	
				$rootScope.datosPreafiliacion.idClienteCt = ctSeleccionado.clienteId;
			},
			asignaDatosDomicilioCP: function(domicilioSeleccionado) {
				$rootScope.datosPreafiliacion.codPostal = domicilioSeleccionado.codPostal;
				$rootScope.datosPreafiliacion.nombreColonia = domicilioSeleccionado.nombreColonia;
				$rootScope.datosPreafiliacion.nombreMunicipio = domicilioSeleccionado.nombreMunicipio;			    	
				$rootScope.datosPreafiliacion.nombreEstado = domicilioSeleccionado.nombreEstado;
				$rootScope.datosPreafiliacion.claveColonia = domicilioSeleccionado.claveColonia;
			},
			terminarPreafiliacion: function() {
				$rootScope.guardaDatosPreafiliacion();
			},
			mensaje: 'jeje...',
	  		getIdDispositivo: function() {
				return this.mensaje;
			},
			setIdDispositivo: function(msj) {
				this.mensaje = msj;
			},
			mostrarMensaje: function(msj) {
				$rootScope.errores = {};
				$rootScope.errores.descripcionCode = msj;
				
				$("#mensaje").modal('toggle');

			}
		};
}]);
