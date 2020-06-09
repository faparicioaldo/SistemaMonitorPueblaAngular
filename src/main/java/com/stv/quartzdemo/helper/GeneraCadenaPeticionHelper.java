package com.stv.quartzdemo.helper;

import java.util.List;

import com.stv.quartzdemo.dto.RespuestaGobiernoDTO;
import com.stv.quartzdemo.dto.SemoviRequestDTO;

public interface GeneraCadenaPeticionHelper {

	/**
	 *  GENERA CADENA PARA PETICION AL SOCKET DE GOBIERNO 
	 **/
//	public String generaCadenaPeticion();
//	public RespuestaGobiernoDTO decodificaCadenaResultado(String cadenaRespuesta);
	public List<SemoviRequestDTO> generaCadenaPeticion(List<String> terids, String key, String isAlertPanicButton);

}
