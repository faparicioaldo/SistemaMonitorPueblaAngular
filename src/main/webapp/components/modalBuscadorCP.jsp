<!DOCTYPE html>
<%@ page import="java.text.SimpleDateFormat,java.util.*,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


	<div class="modal fade" id="modalBuscadorCP" onShow="scopeHolder()" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Buscar Codigo Postal </h4>
				</div>
				<br>
		        <br>
		        <div class="container">  

	                <div class="form-group col-sm-12">
							<form name="formBuscadorCP" autocomplete="off">
			
				                <label class="col-sm-3 control-label" for="strCodPostal">C&oacute;digo Postal (*):</label>
				                <div class="col-sm-6" ng-class="{'has-error':formBuscadorCP.codPostal.$invalid}">
								<input class="form-control  " 
											     type="number"
				                                 ng-model="codigoPostalSeleccionado"
			                                     placeholder="Introduzca C&oacute;digo Postal."
			                                     name="codPostal" 
			                                     size="10" 
			                                     maxlength="5"
											     ng-pattern="/^\d{5}$/"
											     required
				                              >
								<small class="form-text form-text-error" ng-if="formBuscadorCP.codPostal.$invalid">Ingresar codigo postal a 5 digitos</small>
				                              </div>
				                              <div class="col-sm-3" >
				                <button class=" btn btn-primary" 
				                        type="button" 
				                        value="Buscar" 
				                        name="buscar"
				                        ng-disabled="formBuscadorCP.$invalid" 
				                        onClick="scopeHolder()"
				                        >
				                    Buscar
				                    <span class="glyphicon glyphicon-search"></span>
				                </button> 
		                
			                </form>
		                </div>
					</div>
				</div>
				<div class="modal-body">				 	
					<hr>		
			            <div class="panel panel-default">
			                <div class="panel-heading"><strong>:: Afiliaci&oacute;n de Establecimientos Comerciales ::</strong></div>
			                <div class="panel-body"> 
			                    			                       
			                       <div  class="form-group col-sm-12" ng-if="busquedaExitosa == 'no'">
			                            <div class="col-sm-12">
			                                <div class="alert alert-danger">
			                                   <strong>¡Error!</strong>&nbsp; No hay datos para este código postal.                   
			                                    <script language='JavaScript'>
			                                        alert('No hay datos para este código postal.');
			                                        asignarValores('||||||');
			                                        self.close();                          
			                                    </script>
			                               </div>        
			                            </div>
			                        </div>
			                                              
			                        <div  class="form-group col-sm-12" ng-if="busquedaExitosa == 'si'">                            
			                            <hr class="red">                            
			                            <div class="table-responsive">
			                                <table class="table table-striped">
			                                  <thead>
			                                    <tr>
			                                        <th>Asentamiento</th>
			                                        <th>Colonia</th>
			                                        <th>Delegaci&oacute;n / Municipio</th>
			                                        <th>Estado</th>
			                                        <th>Lada</th>
			                                    </tr>
			                                  </thead> 
			                                  <tbody ng-repeat="dom in domicilios">
			                                    
			                                    <tr>                      
			                                      <td> {{dom.nombreCortoAsentamiento}}</td>

			                                      <td><a ng-click="seleccionarDomicilio(dom.nombreColonia, dom.nombreMunicipio, dom.nombreEstado, dom.claveColonia)">
			                                      		{{dom.nombreColonia}}
			                                      		</a>
			                                      </td>
			                                      <td> {{dom.nombreMunicipio}}</td>
			                                      <td>{{dom.nombreEstado}}</td>
			                                      <td>{{dom.lada}}</td>
			                                     </tr>
			                                    
			                                  </tbody>
			                                </table>
			                            </div>
			                        </div>  
			                        <div  class="form-group col-sm-12">
			                            <p class="pull-right">
			                                <input type="button" 
			                                       class="btn btn-default" 
			                                       value="Cerrar"
			                                       ng-click="cerrar()">
			                            </p>
			                        </div>

			                </div>
			            </div>
					<hr>
				</div>
			</div>
		</div>
	</div>