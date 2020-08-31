

function concatenar() {

    lada=document.getElementById('lada').value;
    telefono=document.getElementById('telfijo').value;

    telefono1=lada+telefono;
    
document.getElementById('telefono1').value=telefono1;

}





function dividirladatelefono() {

     
	   var cadena= document.getElementById('telefono1').value;
	   
	    var num=cadena.substring(0, 2);
	   var subca=cadena.substring(2, 12);
	   
	     
	    if(num == 81||num == 55||num == 33) {
		  
		  document.getElementById('lada').value=num;	
		  document.getElementById('telfijo').value=subca;
		  	 
	  } else {
		  
		  
		  var num2=cadena.substring(0, 3);
		   var subca2=cadena.substring(3, 12);
		   
		   document.getElementById('lada').value=num2;	
			  document.getElementById('telfijo').value=subca2;
		   }
	   		  
}


