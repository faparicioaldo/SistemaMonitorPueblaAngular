
    <!-- Contenido -->
    <main class="page">        
        <div class="container" ng-init="init()">  
            <!-- Header -->   
             <div class="container tab-content ">
                     
                 <br/>
                 
                 <div class="tab-pane active" id="Active1">  
                    <!--Titulo principal del formulario-->
                    <h3>VEHICULOS REGISTRADOS</h3>
                    <hr class="red"/>
                                   
					<div>	
						<div class="row">
							<div class="col">
								<span class="float-right">Total Vehiculos: {{infoVehiculos.total}}</span><br/>
								<span class="float-right">Total Vehiculos Falta Datos: {{infoVehiculos.totalFaltaDatos}}</span>
							</div>
						</div>
					</div>
                                   
                    <!--TABLA-->
                    <div class="container">
						<form class="form-inline">
					        <div class="form-group">
					            <label 
					            class="form-control" 
					            >Search</label>
					            <input type="text" ng-model="search" class="form-control" oninput="this.value = this.value.toUpperCase()" placeholder="Search">
					        </div>
					    </form>
					</div>
					
					<br/>

					<div>
						<table class="table table-striped table-hover ">
							<thead>
							  	<tr>
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
							    	
							    	<th></th>
							    	<th></th>
							  	</tr>
						  	</thead>
						  	<tbody>
								<tr ng-class="faltanDatos(x)" dir-paginate="x in listaVehiculosRegistrados | orderBy:sortKey:reverse | filter:search | itemsPerPage:10"
								>
							    	<td>{{ x.iddispositivo }}</td>
				 					<td>{{ x.empresa}}</td> 
							    	<td>{{ x.plate }}</td>
				 					<td>{{ x.route}}</td> 
							    	<td>{{ x.imei }}</td>
							    	<td>{{ x.municipio }}</td>
				 					<td>{{ x.concesion}}</td> 
	
							    	<td>						    
							    		<a href="{{x.urlcamera}}" target="_blank" class="btn btn-primary">CAMARAS</a>
							    	</td>
							    	<td>						    
							    		<input type="button" ng-click="editarDatosVehiculo(x)" value = "EDITAR" class="btn btn-primary"/>
							    	</td>
							  	</tr>
							</tbody>
						</table>
					</div>
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