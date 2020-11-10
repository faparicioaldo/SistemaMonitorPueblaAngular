<!DOCTYPE html>
<%@ page import="java.text.SimpleDateFormat,java.util.*,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- ventana captcha modal -->
<!-- <form name="captchaForm" autocomplete="off" ng-submit="submit()"> -->
	<div class="modal fade" id="modalEditarVehiculo" ng-init="init()">
		<div class="modal-dialog modal-xl">
			<div class="modal-content" >
				<div class="modal-header">
					<h4 class="modal-title">Editar Vehiculo {{$ctrl.datosVehiculoSeleccionado.iddispositivo }} </h4>
				</div>
				<div class="modal-body">

				<hr>

            
            <div class="panel panel-default">
                <div class="panel-heading"><strong>:: Cat&aacute;logo de Centros de Trabajo ::</strong></div>
                <div class="panel-body"> 
                    
                    <div class="alert alert-info" style="text-align:left;">
                        <strong>¡Sugerencia!</strong>
                        <ul >
                            <li>Debe ingresar todos los datos marcados con asterisco.</li>
                        </ul>                       
                    </div>
                    
                    <form name="datosVehiculoForm" autocomplete="off">
                        
                        <div  class="form-group col-sm-12">
                            <div  class="form-group col-sm-6" >                                
                                <label class="control-label">IDISPOSITIVO (*):</label>                               
                                <input class="form-control " 
                                	   ng-model="datosVehiculoSeleccionado.iddispositivo"
                                       placeholder="Introduzca el id del dispositivo"
                                       type="text" 
                                       name="strIDCliente" 
                                       size="250" 
                                       maxlength="250"
                                       readonly
			               			    
			               		/>
                            </div>
                            <div class="col-sm-6" ng-class="{'has-error':datosVehiculoForm.empresa.$invalid && datosVehiculoForm.empresa.$touched}">
                               <label class="control-label">EMPRESA (*):</label>
                               <input class="form-control " 
                                	  ng-model="datosVehiculoSeleccionado.empresa"
                                      placeholder="Introduzca el nombre de la Empresa."
                                      type="text" 
                                      name="empresa" 
                                      size="200" 
                                      maxlength="200"
                                      required
                                      required
                                                                            
			              		/>
                                <small class="form-text form-text-error" ng-if="datosVehiculoForm.empresa.$invalid && datosVehiculoForm.empresa.$touched">El campo empresa es requerido</small>                              
                            </div>                            
                        </div>                        
                        <div  class="form-group col-sm-12">
                            <div class="col-sm-6" ng-class="{'has-error':datosVehiculoForm.plate.$invalid && datosVehiculoForm.plate.$touched}">
                               <label class="control-label">PLACA (*):</label>
                               <input class="form-control " 
                                	  ng-model="datosVehiculoSeleccionado.plate"
                                      placeholder="Introduzca N&uacute;mero Patronal de la Empresa."
                                      type="text" 
                                      name="plate" 
                                      size="50" 
                                      maxlength="50"
                                      required
			              			   
			              		/>
			                    <small class="form-text form-text-error" ng-if="datosVehiculoForm.plate.$invalid && datosVehiculoForm.plate.$touched">El campo placa es requerido.</small>                              
                            </div>                            
                            <div  class="form-group col-sm-6" ng-class="{'has-error':datosVehiculoForm.vin.$invalid && datosVehiculoForm.vin.$touched}">                                
                                <label class="control-label">SERIE VEHICULAR (*):</label>                               
                                <input class="form-control " 
                                	   ng-model="datosVehiculoSeleccionado.vin"
                                       placeholder="Introduzca el vin."
                                       type="text" 
                                       name="vin" 
                                       size="200" 
                                       maxlength="200"
                                       required
			               			    
			               		/>
                                <small class="form-text form-text-error" ng-if="datosVehiculoForm.vin.$invalid && datosVehiculoForm.vin.$touched">El campo vin es requerido.</small>                              
                            </div>
                        </div>                        
                        <div  class="form-group col-sm-12">
                            <div class="col-sm-6" ng-class="{'has-error':datosVehiculoForm.engine.$invalid && datosVehiculoForm.engine.$touched}">
                               <label class="control-label">SERIE DEL MOTOR (*):</label>
                               <input class="form-control " 
                                	  ng-model="datosVehiculoSeleccionado.engine"
                                      placeholder="Introduzca el engine."
                                      type="text" 
                                      name="engine" 
                                      size="200" 
                                      maxlength="200"
                                      required
			              			   
			              		/>
			                  	<small class="form-text form-text-error" ng-if="datosVehiculoForm.engine.$invalid && datosVehiculoForm.engine.$touched">El campo engine es requerido.</small>                              
                            </div>                            
                            <div  class="form-group col-sm-6" ng-class="{'has-error':datosVehiculoForm.year.$invalid && datosVehiculoForm.year.$touched}">
                                <label class="control-label">AÑO (*):</label>                               
                                <input class="form-control " 
                                	   ng-model="datosVehiculoSeleccionado.year"
                                       placeholder="Introduzca el año del vehiculo"
                                       type="text" 
                                       name="year" 
                                       size="30" 
                                       maxlength="30"
                                       required
			               			    
			               		/>
                                <small class="form-text form-text-error" ng-if="datosVehiculoForm.year.$invalid && datosVehiculoForm.year.$touched">El campo year es requerido.</small>                              
                            </div>
                        </div>                        
                        <div  class="form-group col-sm-12">
                            <div class="col-sm-6" ng-class="{'has-error':datosVehiculoForm.color.$invalid && datosVehiculoForm.color.$touched}">
                               <label class="control-label">COLOR (*):</label>
                               <input class="form-control " 
                                	  ng-model="datosVehiculoSeleccionado.color"
                                      placeholder="Introduzca el color del vehiculo."
                                      type="text" 
                                      name="color" 
                                      size="200" 
                                      maxlength="200"
                                      required
			              			   
			              		/>
			                    <small class="form-text form-text-error" ng-if="datosVehiculoForm.color.$invalid && datosVehiculoForm.color.$touched">El campo color es requerido.</small>                              
                            </div>                            
                            <div  class="form-group col-sm-6" ng-class="{'has-error':datosVehiculoForm.route.$invalid && datosVehiculoForm.route.$touched}">                                
                                <label class="control-label">RUTA (*):</label>                               
                                <input class="form-control " 
                                	   ng-model="datosVehiculoSeleccionado.route"
                                       placeholder="Introduzca N&uacute;mero Fonacot del Centro de Trabajo."
                                       type="text" 
                                       name="route" 
                                       size="250" 
                                       maxlength="250"
                                       required
				               		    
				               	/>
                                <small class="form-text form-text-error" ng-if="datosVehiculoForm.route.$invalid && datosVehiculoForm.route.$touched">El campo route es requerido.</small>                              
                            </div>
                        </div>                        
                        <div  class="form-group col-sm-12">
                            <div class="col-sm-6" ng-class="{'has-error':datosVehiculoForm.rs.$invalid && datosVehiculoForm.rs.$touched}">
                               <label class="control-label">RAZON SOCIAL (*):</label>
                               <input class="form-control " 
                                	  ng-model="datosVehiculoSeleccionado.rs"
                                      placeholder="Introduzca el rs."
                                      type="text" 
                                      name="rs" 
                                      size="500" 
                                      maxlength="500"
                                      required
			              			   
			              		/>
								<small class="form-text form-text-error" ng-if="datosVehiculoForm.rs.$invalid && datosVehiculoForm.rs.$touched">El campo rs es requerido.</small>                              
			              
                            </div>                            
                            <div  class="form-group col-sm-6" ng-class="{'has-error':datosVehiculoForm.eco.$invalid && datosVehiculoForm.eco.$touched}">                                
                                <label class="control-label">ECONOMICO (*):</label>                               
                                <input class="form-control " 
                                	   ng-model="datosVehiculoSeleccionado.eco"
                                       placeholder="Introduzca el economico."
                                       type="text" 
                                       name="eco" 
                                       size="50" 
                                       maxlength="50"
                                       required
			               			    
			               		/>
                                <small class="form-text form-text-error" ng-if="datosVehiculoForm.eco.$invalid && datosVehiculoForm.eco.$touched">El campo economico es requerido.</small>                              
                            </div>
                        </div>                        
                        <div  class="form-group col-sm-12">
                            <div class="col-sm-6" ng-class="{'has-error':datosVehiculoForm.branch.$invalid && datosVehiculoForm.branch.$touched}">
                               <label class="control-label">MARCA (*):</label>
                               <input class="form-control " 
                                	  ng-model="datosVehiculoSeleccionado.branch"
                                      placeholder="Introduzca el branch."
                                      type="text" 
                                      name="branch" 
                                      size="150" 
                                      maxlength="150"
                                      required
			              			   
			              		/>
			                    <small class="form-text form-text-error" ng-if="datosVehiculoForm.branch.$invalid && datosVehiculoForm.branch.$touched">El campo branch es requerido.</small>                              
			              
                            </div>                            
                            <div  class="form-group col-sm-6" ng-class="{'has-error':datosVehiculoForm.subbranch.$invalid && datosVehiculoForm.subbranch.$touched}">                                
                                <label class="control-label">SUBMARCA (*):</label>                               
                                <input class="form-control " 
                                	   ng-model="datosVehiculoSeleccionado.subbranch"
                                       placeholder="Introduzca el subbranch."
                                       type="text" 
                                       name="subbranch" 
                                       size="150" 
                                       maxlength="150"
                                       required
			               			    
								/>
                                <small class="form-text form-text-error" ng-if="datosVehiculoForm.subbranch.$invalid && datosVehiculoForm.subbranch.$touched">El campo subbranch es requerido.</small>                              
                            </div>
                        </div>                        
                        <div  class="form-group col-sm-12">
                            <div class="col-sm-6" ng-class="{'has-error':datosVehiculoForm.municipio.$invalid && datosVehiculoForm.municipio.$touched}">
                               <label class="control-label">MUNICIPIO (*):</label>
<!--                                <input class="form-control "  -->
<!--                                 	  ng-model="datosVehiculoSeleccionado.municipio" -->
<!--                                       placeholder="Introduzca el muicipio." -->
<!--                                       type="text"  -->
<!--                                       name="municipio"  -->
<!--                                       size="200"  -->
<!--                                       maxlength="200" -->
<!--                                       required -->
<!-- 			              			    -->
<!-- 			              		/> -->
			              		
			              		<select name="municipio" class="form-control" 
									ng-model="datosVehiculoSeleccionado.municipio" 
									required>
									<option value="">Seleccione un Municipio</option>									 
                              		<option ng-value="{{x.claveSemovi}}" ng-repeat="x in municipios">{{x.municipio}}</option>
								</select>
			              		
                                <small class="form-text form-text-error" ng-if="datosVehiculoForm.municipio.$invalid && datosVehiculoForm.municipio.$touched">El campo municipio es requerido.</small>                              
			              
                            </div>                            
                            <div  class="form-group col-sm-6" ng-class="{'has-error':datosVehiculoForm.concesion.$invalid && datosVehiculoForm.concesion.$touched}">                                
                                <label class="control-label">CONCESION (*):</label>                               
                                <input class="form-control " 
                                	   ng-model="datosVehiculoSeleccionado.concesion"
                                       placeholder="Introduzca la concesion."
                                       type="text" 
                                       name="concesion" 
                                       size="200" 
                                       maxlength="200"
                                       required
			               			    
			               		/>
                                <small class="form-text form-text-error" ng-if="datosVehiculoForm.concesion.$invalid && datosVehiculoForm.concesion.$touched">El campo concesion es requerido.</small>                              
                            </div>
                        </div>                        
                        <div  class="form-group col-sm-12">
                            <div class="col-sm-6" ng-class="{'has-error':datosVehiculoForm.urlcamera.$invalid && datosVehiculoForm.urlcamera.$touched}">
                               <label class="control-label">URL CAMARAS (*):</label>
                               <input class="form-control " 
                                	  ng-model="datosVehiculoSeleccionado.urlcamera"
                                      placeholder="Introduzca la url de las camaras"
                                      type="text" 
                                      name="urlcamera" 
                                      size="2000" 
                                      maxlength="2000"
                                      required
			              			   
			              		/>
                                <small class="form-text form-text-error" ng-if="datosVehiculoForm.urlcamera.$invalid && datosVehiculoForm.urlcamera.$touched">La url de las camaras es requerida</small>                              
			              
                            </div>                            
                        </div>                       
                            
                        <div  class="form-group col-sm-12">
                            <p class="pull-right">                                                              
                                
						        <input class="btn btn-primary" 
			                           type="button" 
			                           name="cmd:buscar"
			                           ng-disabled="datosVehiculoForm.$invalid"
						               value='Guardar'
						               ng-click="guardarDatosVehiculo()"/>
			                           
                                &nbsp;&nbsp;
                                <input class="btn btn-default" 
                                       type="button" 
                                       ng-click="limpiarFormulario()"
                                       value='Limpiar'/>

                                &nbsp;&nbsp;
                                <input class="btn btn-default" 
                                       type="button" 
                                       value="Cerrar"
                                       ng-click="cerrar()"/>
                            </p>
                        </div>
                        
                        <div class="gro" id="resultadoError" ng-if="busquedaExitosa == 'no'">
	                        <div  class="form-group col-sm-12">
	                            <div class="col-sm-12">
	                                <div class="alert alert-danger">
	                                   <strong>¡Error!</strong>
	                                   No se encontr&oacute; Centro de Trabajo, realice una nueva b&uacute;squeda.                     
	                               </div>        
	                            </div>
	                        </div>
                        </div>
                                                
                        <!--Inicia contruccion de TABLA RESULTADO-->                        
                        <div  class="form-group col-sm-12" ng-if="busquedaExitosa == 'si'">                            
                            <hr class="red">
                            
                            <div class="table-responsive">
                                <table class="table table-striped">
                                  <thead> 
                                    <tr>
                                        <th>No. FONACOT</th>
                                        <th>No. de Seguro<br/>Social</th>
                                        <th>Nombre Comercial</th>
                                        <th>Raz&oacute;n Social / Nombre</th>
                                        <th>Direcci&oacute;n</th>
                                        <th>Tel&eacute;fono</th>
                                        <th>E-Mail</th>
                                    </tr>
                                  </thead>                                  
                                  <tbody ng-repeat="ct in centrosTrabajo">
                                  
                                    <tr>
                                        <td class="tblafiliaciones">
                                            {{ct.clienteId}}
                                          </td>
                                          <td class="tblafiliaciones">
                                            {{ct.numSegSocial}}
                                            
                                          </td>
                                          <td class="tblafiliaciones">
                                            <a ng-click="seleccionarCentroTrabajo(ct.clienteId, ct.numSegSocial, ct.nomCompleto)">
                                               {{ct.nomCorto}}
                                            </a>
                                          </td>
                                          <td class="tblafiliaciones">
                                            <a ng-click="seleccionarCentroTrabajo(ct.clienteId, ct.numSegSocial, ct.nomCompleto)">
                                               {{ct.nomCompleto}}
                                            
                                            </a>
                                          </td>
                                          <td class="tblafiliaciones">
                                               {{ct.direccion}}
                                            
                                          </td>
                                          <td class="tblafiliaciones">
                                               {{ct.telefono}}
                                            
                                          </td>
                                          <td class="tblafiliaciones">
                                               {{ct.email}}
                                            
                                          </td> 
                                    </tr>
                                    
                                    
                                  </tbody>
                                  <tfoot>
                                      <tr>
                                        <td bgcolor="#ffffff" align="center" class="tblafiliaciones" colspan="5">
                                          <center>
                                          
                                          </center>
                                        </td>
                                       </tr>
                                  </tfoot>
                                </table>
                              </div>
                            </div>         
                            <!--Termina contruccion de TABLA RESULTADO-->
                                                                                                   
                    </form>                    
                </div>
            </div>

				<hr>
				<hr>
				<hr>
					
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
<!-- </form> -->