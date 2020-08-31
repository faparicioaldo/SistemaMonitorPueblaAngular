
    <!-- Contenido -->
    <main class="page">        
        <div class="container" ng-init="init()">  
            <!-- Header -->   
             <div class="container tab-content ">
                     
                 <div class="tab-pane active" id="Active1">  
                    <!--Titulo principal del formulario-->
                    <h3>VEHICULOS REGISTRADOS</h3>
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
						    	<th ng-click="sort('iddispositivo')">
						    		<strong>#DISPOSITIVO</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='iddispositivo'" 
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
						    	<th ng-click="sort('plate')">
						    		<strong>PLATE</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='plate'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('route')">
						    		<strong>ROUTE</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='route'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('imei')">
						    		<strong>IMEI</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='imei'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('municipio')">
						    		<strong>MUNICIPIO</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='municipio'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('concesion')">
						    		<strong>CONCESION</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='concesion'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	<th ng-click="sort('branch')">
						    		<strong>MARCA</strong>
			    	            	<span class="glyphicon sort-icon" 
			    	            		  ng-show="sortKey=='branch'" 
			    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
			    	            	</span>
						    	</th>
						    	
						    	<th></th>
						  	</tr>
					  	</thead>
					  	<tbody>
							<tr ng-class="faltanDatos(x)"
								dir-paginate="x in listaVehiculosRegistrados | orderBy:sortKey:reverse | filter:search | itemsPerPage:10"
							>
						    	<td>{{ x.iddispositivo }}</td>
			 					<td>{{ x.empresa}}</td> 
						    	<td>{{ x.plate }}</td>
			 					<td>{{ x.route}}</td> 
						    	<td>{{ x.imei }}</td>
						    	<td>{{ x.municipio }}</td>
			 					<td>{{ x.concesion}}</td> 
			 					<td>{{ x.branch}}</td> 

						    	<td>
						    
						    	<input type="button" ng-click="editarDatosVehiculo(x)" value = "EDITAR" class="btn btn-default"/>
						
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
