
<style>
	.sort-icon {
	    font-size: 9px;
	    margin-left: 5px;
	}
	
	th {
	    cursor:pointer;
	}
</style>
	
    <!-- Contenido -->
    <main class="page">        
        <div class="container" ng-init="init()">  
            <!-- Header -->   
             <div class="container tab-content ">
                     
                 <div class="tab-pane active" id="Active1">  
                    <!--Titulo principal del formulario-->
                    <h3>ALERTAS ENVIADAS A CEIBA</h3>
                    <hr class="red"/>
                                   
                    <!--TABLA-->
					 <form class="form-inline">
				        <div class="form-group">
				            <label 
				            class="form-control" 
				            >Search</label>
				            <input type="text" ng-model="search" class="form-control" oninput="this.value = this.value.toUpperCase()" placeholder="Search">
				        </div>
				    </form>

					<table class="table table-striped table-hover ">
						<thead>
						  	<tr class="thead-dark">
						    	<th ng-click="sort('idAlerta')">
						    		<strong>#</strong>
		    	                	<span class="glyphicon sort-icon" 
		    	                		  ng-show="sortKey=='idAlerta'" 
		    	                		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
		    	                	</span>
						    	</th>
<!-- 						    	<th ng-click="sort('ceibaAlarmid')"> -->
<!-- 						    		<strong>ID_CEIBA</strong> -->
<!-- 			    	            	<span class="glyphicon sort-icon"  -->
<!-- 			    	            		  ng-show="sortKey=='ceibaAlarmid'"  -->
<!-- 			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}"> -->
<!-- 			    	            	</span> -->
<!-- 						    	</th> -->
						    	<th ng-click="sort('idDispositivo')">
						    		<strong>#DISPOSITIVO</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='idDispositivo'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('plate')">
						    		<strong>PLACA</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='plate'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('eco')">
						    		<strong>ECO</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='eco'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('ceibaGpsTime')">
						    		<strong>FECHA</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='ceibaGpsTime'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('semoviMensaje')">
						    		<strong>ESTATUS SEMOVI</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='semoviMensaje'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('empresa')">
						    		<strong>EMPRESA</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='empresa'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('route')">
						    		<strong>RUTA</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='route'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('ceibaType')">
						    		<strong>PUERTO</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='ceibaType'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	
						    	<th></th>
						    	<th></th>
						  	</tr>
					  	</thead>
					  	<tbody>
							<tr 
								dir-paginate="x in listaAlertasEnviadasSemovi | orderBy:sortKey:reverse | filter:search | itemsPerPage:10"
							>
						    	<td>{{ x.idAlerta }}</td>
<!-- 						    	<td>{{ x.ceibaAlarmid }}</td> -->
						    	<td>{{ x.idDispositivo }}</td>
						    	<td>{{ x.plate }}</td>
						    	<td>{{ x.eco }}</td>
			 					<td>{{ x.ceibaGpsTime}}</td> 
			 					<td>{{ x.semoviMensaje}}</td> 
			 					<td>{{ x.empresa}}</td> 
			 					<td>{{ x.route}}</td> 
			 					<td>{{ x.ceibaType}}</td>				 

						    	<td>
						    
						    	<input type="button" ng-click="redirect(x.idAlerta)" value = "ENVIAR" class="btn btn-default"/>
						
						    	</td>
						    	<td>
						    
						    	<input type="button" ng-click="redirect(x.idAlerta)" value = "RECHAZAR" class="btn btn-default"/>
						
						    	</td>
						  	</tr>
						</tbody>
					</table>
					<dir-pagination-controls
				        max-size="10"
				        direction-links="true"
				        boundary-links="true" >
				    </dir-pagination-controls>
					
                </div>      
            </div>            
        </div> 
    </main>
    <hr>


<!-- onload="connect();"  -->

<!-- <main> -->
<!-- 	<div class="container" ng-init="init()"> -->
	
<!-- 		<table class="blueTable"> -->
<!-- 			<tr> -->
<!-- 				<th>#ALARMA</th> -->
<!-- 				<th>ID_CEIBA</th> -->
<!-- 				<th>DISPOSITIVO_ID</th> -->
<!-- 				<th>PLACA</th> -->
<!-- 				<th>ECONOMICO</th> -->
<!-- 				<th>FECHA</th> -->
<!-- 				<th>ESTATUS SEMOVI</th> -->
<!-- 				<th>EMPRESA</th> -->
<!-- 				<th>RUTA</th> -->
<!-- 				<th>PUERTO</th> -->
<!-- 				<th></th> -->
<!-- 				<th></th> -->
<!-- 				<th></th> -->
<!-- 			</tr> -->
<%-- 			<c:forEach varStatus="loopCounter" items="${alarmas}" var="alarma"> --%>
<!-- 				<tr> -->
<%-- 					<td><c:out value="${alarma.idAlerta}" /></td> --%>
<%-- 					<td><c:out value="${alarma.ceibaAlarmid}" /></td> --%>
<%-- 					<td><c:out value="${alarma.idDispositivo}" /></td> --%>
<%-- 					<td><c:out value="${alarma.plate}" /></td> --%>
<%-- 					<td><c:out value="${alarma.eco}" /></td> --%>
<%-- 					<td><c:out value="${alarma.ceibaGpsTime}" /></td> --%>
<%-- 					<td><c:out value="${alarma.semoviMensaje}" /></td> --%>
<%-- 					<td><c:out value="${alarma.empresa}" /></td> --%>
<%-- 					<td><c:out value="${alarma.route}" /></td> --%>
<%-- 					<td><c:out value="${alarma.ceibaType}" /></td>				 --%>
<%-- 					<c:if test="${alarma.semoviEstatus == 'Cargada'}"> --%>
<!-- 						<td><input type="button" value="Enviar" id="nuevo" -->
<%-- 							name="nuevo" onclick="enviarAlarmaPOST(${alarma.idAlerta},${alarma.idDispositivo})" /></td> --%>
<!-- 						<td><input type="button" value="Descartar" id="nuevo" -->
<%-- 							name="nuevo" onclick="descartarAlarmaPOST(${alarma.idAlerta},${alarma.idDispositivo})" /> --%>
<!-- 						</td> -->
<!-- 					</c:if> -->
<%-- 					<c:if test="${alarma.semoviEstatus != 'Cargada'}"> --%>
<!-- 						<td></td> -->
<!-- 						<td></td> -->
<!-- 					</c:if> -->
<!-- 					<td> -->
<%-- 						<input type="button" value="Agregar Datos" id="add" name="add" onclick="window.location='/agregarDatosAlarma/'+${alarma.idAlerta}+'/'+${alarma.idDispositivo}"/> --%>
<!-- 					</td> -->
	
<!-- 				</tr> -->
<!-- 			</c:forEach> -->
<!-- 		</table> -->
	
	<!-- 	<div class="centrar"> -->
	<!-- 		<a href="/monitorFoliosAlarmas">Ir a Monitor de Folios</a> -->
		
	<!-- 		<br> -->
		
	<!-- 		<a href="/datosVehiculos">Agregar Vehiculo</a> -->
	<!-- 	</div> -->
	
<!-- 	</div> -->

<!-- </main> -->
