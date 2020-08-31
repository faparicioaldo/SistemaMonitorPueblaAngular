//-----------------------------Chat Infonacot------------------------------------
var ventanaChat = null;
function abrirChat(){
	if(ventanaChat == null || ventanaChat.closed){
	  ventanaChat=window.open("http://chat.fonacotgv.com:9700/SIO_Portales_/SIO/PRT/chatCliente.jsp","Chat","width=510,height=695,menubar=no,toolbar=no,resizable=no,scrollbars=yes,status=yes");
	}else{
		ventanaChat.focus();
	}
}

function trimAccents(source)
{
  var dest = source;
  if(source && source.length > 0)
  {
    for(var i = 0; i < dest.length; i++)
    {
      var c = dest.charAt(i);
      var bSust = false;

      if( c == '�' )
      {
        c = 'a';
        bSust = true;
      }
      else if(c == '�')
      {
        c = 'e';
        bSust = true;
      }
      else if(c == '�')
      {
        c = 'i';
        bSust = true;
      }
      else if(c == '�')
      {
        c = 'o';
        bSust = true;
      }
      else if(c == '�')
      {
        c = 'u';
        bSust = true;
      }
      else if( c == '�' )
      {
        c = 'A';
        bSust = true;
      }
      else if(c == '�')
      {
        c = 'E';
        bSust = true;
      }
      else if(c == '�')
      {
        c = 'I';
        bSust = true;
      }
      else if(c == '�')
      {
        c = 'O';
        bSust = true;
      }
      else if(c == '�')
      {
        c = 'U';
        bSust = true;
      }

      if( bSust )
      {
        var subStr1 = dest.substring(0, i);
        var subStr2 = dest.substring(i+1);
        dest = subStr1 + c + subStr2;
      }
    }
  }

  return dest;
}

//Quita espacios a la izquierda
function ltrim(s){
	// Devuelve una cadena sin los espacios del principio
	var i=0;
	var j=0;

	// Busca el primer caracter <> de un espacio
	for(i=0; i<=s.length-1; i++)
		if(s.substring(i,i+1) != ' '){
			j=i;
			break;
		}
	return s.substring(j, s.length);
}

// Quita espacios a la derecha
function rtrim(s){
	// Quita los espacios en blanco del final de la cadena
	var j=0;

	// Busca el �ltimo caracter <> de un espacio
	for(var i=s.length-1; i>-1; i--)
		if(s.substring(i,i+1) != ' '){
			j=i;
			break;
		}
	return s.substring(0, j+1);
}

function trim(s){
	// Quita los espacios del principio y del final
	return ltrim(rtrim(s));
}

//*** INI 117405 - 24/02/2015 - GCP ***//
function mensajeInformacionRegimen1(){
	$("#mensajeAfiliacion1").modal('toggle');
	
	$("#mensajeAfiliacion1").off('hidden.bs.modal').on('hidden.bs.modal', function (e) {});
}