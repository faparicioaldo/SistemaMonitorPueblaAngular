<!DOCTYPE html>
<%@ page import="java.text.SimpleDateFormat,java.util.*,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="modal fade" id="modalTerminarPreafiliacion">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Mensaje</h4>
			</div>
			<div class="modal-body">

         <div  class="form-group col-sm-12">
                            <p class="pull-right">                                                              
                                		
                                		Una vez terminada la preafiliacion no podra volver a editar los datos ¿Esta seguro que desea terminar?	                           
                            </p>
                        </div>				

			</div>
			<div class="modal-footer">

              &nbsp;&nbsp;
              <input class="btn btn-default" 
                     type="button" 
                     ng-click="aceptar()"
                     value='Aceptar'/>

              &nbsp;&nbsp;
              <input class="btn btn-default" 
                     type="button" 
                     value="Cerrar"
                     onclick="$('#modalTerminarPreafiliacion').modal('hide');" data-dismiss="modal"/>
					
			</div>
		</div>
	</div>
</div>

