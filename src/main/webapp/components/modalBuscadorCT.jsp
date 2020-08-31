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
					<h4 class="modal-title">Buscar Centro de Trabajo {{testVariable}} </h4>
				</div>
				<div class="modal-body">

				<hr>

            
            <div class="panel panel-default">
                <div class="panel-heading"><strong>:: Cat&aacute;logo de Centros de Trabajo ::</strong></div>
                <div class="panel-body"> 
                    
                    <div class="alert alert-info" style="text-align:left;">
                        <strong>¡Sugerencia!</strong>
                        <ul >
                            <li>Si desea realizar la búsqueda por Nombre del Centro de trabajo deberá capturar el Estado y/o Delegación/Municipio.</li>
                            <li>Si desea realizar la búsqueda por Número del Centro de Trabajo s&oacute;lo debe capturar este campo.</li>
                            <li>Si desea realizar la búsqueda por Número Patronal de la Empresa solo debe capturar este campo.</li>
                            <li>Dar click sobre el Centro de  Trabajo para seleccionar.</li>
                        </ul>                       
                    </div>
                    
                    <form name="ConsultaCTForm" autocomplete="off">
                        
                        <div class="form-group col-sm-12">
                            <div class="form-group col-sm-12" ng-class="{'has-error':ConsultaCTForm.nombreCt.$invalid}">
                                <label class="control-label" for="strNombre">Nombre del Centro de Trabajo:</label>
                                <input class="form-control "
                                	   ng-model="datosBusquedaCt.nombreCt"
                                       placeholder="Introduzca el Nombre del Centro de Trabajo."
                                       type="text" 
                                       name="nombreCt" 
                                       size="60"
                                                                             
									   oninput="this.value = this.value.toUpperCase()"
                                       >
                                <small class="form-text form-text-error"></small>
                                <small class="form-text form-text-error" ng-if="ConsultaCTForm.nombreCt.$invalid">El apellido paterno es obligatorio</small>
                                
                            </div>
                        </div>
                        
                        <div  class="form-group col-sm-12">
                            <div  class="form-group col-sm-6"  ng-class="{'has-error':ConsultaCTForm.estado.$invalid}">                                
                                <label class="control-label" for="lngEstadoSeleccionado">Estado:</label>                               
								<select name="estado" id="comboEstados" class="form-control" ng-model="datosBusquedaCt.claveEstado" 
									ng-change="consultaMunicipios()"									
									>
									<!-- ng-options="estado as estado.nombreEstado for estado in catalogos.estados track by estado.claveEstado"  -->									
									<option value="">Seleccione un Estado</option>
                               		<option ng-value="{{x.claveEstado}}" ng-repeat="x in catalogos.estados">{{x.nombreEstado}}</option>
								</select>
                                <small class="form-text form-text-error"></small>
                            </div>
                            <div class="col-sm-6" ng-class="{'has-error':ConsultaCTForm.municipio.$invalid}">
                               <label class="control-label" for="lngMunicipioSeleccionado">Delegaci&oacute;n / Municipio:</label>
								<select name="municipio" class="form-control" 
									ng-model="datosBusquedaCt.claveMunicipio" 
									>
<!-- 									ng-options="municipio as municipio.nombreMunicipio for municipio in municipios track by municipio.claveMunicipio"									 -->
									<option value="">Seleccione una Delegacion/Municipio</option>
                               		<option ng-value="{{x.claveMunicipio}}" ng-repeat="x in municipios">{{x.nombreMunicipio}}</option>
								</select>
                                <small class="form-text form-text-error"></small>
                            </div>
                        </div>
                                        
                        <div  class="form-group col-sm-12">
                            <div  class="form-group col-sm-6" >                                
                                <label class="control-label" for="strIDCliente">N&uacute;mero Fonacot del Centro de Trabajo:</label>                               
                                <input class="form-control " 
                                	   ng-model="datosBusquedaCt.clienteId"
                                       placeholder="Introduzca N&uacute;mero Fonacot del Centro de Trabajo."
                                       type="text" 
                                       name="strIDCliente" 
                                       size="13" 
                                       maxlength="13"
			               onBlur="if(this.value.length > 0) this.value = trimAccents(trim(this.value.toUpperCase())); return true" 
			               value="">
                                <small class="form-text form-text-error"></small>                              
                            </div>
                            <div class="col-sm-6">
                               <label class="control-label" for="strNoSeguroSocial">N&uacute;mero Patronal de la Empresa:</label>
                               <input class="form-control " 
                                	  ng-model="datosBusquedaCt.numSegSocial"
                                      placeholder="Introduzca N&uacute;mero Patronal de la Empresa."
                                      type="text" 
                                      name="strNoSeguroSocial" 
                                      size="13" 
                                      maxlength="13"
			              onBlur="if(this.value.length > 0) this.value = trimAccents(trim(this.value.toUpperCase())); return true" 
			              value=""></input>
			              
                            </div>                            
                        </div>
                            
                            
                            
                        <div  class="form-group col-sm-12">
                            <p class="pull-right">                                                              
                                
			        <input class="btn btn-primary" 
                                       type="button" 
                                       name="cmd:buscar"
                                       id="cmd:buscar"
                                       ng-disabled="ConsultaCTForm.$invalid"
			               value='Buscar'
			               ng-click="realizarBusqueda()"/>
<%-- 			               onClick=" --%>
<!--                                                 if( this.form.strNoSeguroSocial.value.length <= 0){ -->
<!--                                                      if( this.form.strIDCliente.value.length <= 0){ -->
<!--                                                              if( this.form.lngEstadoSeleccionado.selectedIndex <= 0 ) -->
<!--                                                          { -->
<!--                                                            alert('Error: se debe proporcionar al menos parte del nombre &oacute; el Estado y el municipio/delegaci&oacute;n &oacute; el n&uacute;mero del centro de trabajo'); -->
<!--                                                            return false; -->
<!--                                                          } -->
<!--                                                      if( this.form.strNombre.value.length <= 0 && this.form.lngMunicipioSeleccionado.selectedIndex <= 0 ) -->
<!--                                                          { -->
<!--                                                            alert('Error: se debe proporcionar al menos parte del nombre &oacute; el Estado y el municipio/delegaci&oacute;n &oacute; el n&uacute;mero del centro de trabajo'); -->
<!--                                                            return false; -->
<!--                                                          } -->
<!--                                                      } -->
<!-- 			                        } -->
<%-- 			                           " --%>
			                           
                                &nbsp;&nbsp;
                                <input class="btn btn-default" 
                                       type="button" 
                                       ng-click="limpiar()"
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