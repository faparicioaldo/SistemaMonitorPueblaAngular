
    <!-- Contenido -->
    <main class="page">        
        <div class="container" ng-init="init()">  
            <!-- Header -->   
             <div class="container tab-content ">
                     
                 <br/>
                 
                 <div class="tab-pane active" id="Active1">  
                    <!--Titulo principal del formulario-->
                    <h3>MONITOR</h3>
                    <hr class="red"/>
                                   
					<div>	
						<div class="row">
							<div class="col">
								<span class="pull-right">Total GPS Enviados: {{infoGpss.totalEnviadosOK}}</span><br/>
								<span class="pull-right">Total GPS con Error: {{infoGpss.totalEnviadosNOK}}</span>
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
					            <input type="text" ng-model="search" class="form-control" placeholder="Search">
					        </div>
					    </form>
					</div>
					
					<br/>

					<div>
						<table class="table table-striped table-hover ">
							<thead>
							  	<tr>
							    	<th ng-click="sort('deviceId')">
							    		<strong>#DISPOSITIVO</strong>
				    	            	<span class="glyphicon sort-icon" 
				    	            		  ng-show="sortKey=='deviceId'" 
				    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
				    	            	</span>
							    	</th>
							    	<th ng-click="sort('errorMessage')">
							    		<strong>ERROR</strong>
				    	            	<span class="glyphicon sort-icon" 
				    	            		  ng-show="sortKey=='errorMessage'" 
				    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
				    	            	</span>
							    	</th>
							    	<th ng-click="sort('lastGps')">
							    		<strong>ULTIMO GPS</strong>
				    	            	<span class="glyphicon sort-icon" 
				    	            		  ng-show="sortKey=='lastGps'" 
				    	            		  ng-class="{'glyphicon-chevron-up':reverse,'glyphicon-chevron-down':!reverse}">
				    	            	</span>
							    	</th>
							  	</tr>
						  	</thead>
						  	<tbody>
								<tr dir-paginate="x in sendGPSToSemoviErrors | orderBy:sortKey:reverse | filter:search | itemsPerPage:10 track by $index"
								>
							    	<td>{{ x.deviceId }}</td>
				 					<td>{{ x.errorMessage }}</td> 
							    	<td>{{ x.lastGps }}</td>
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