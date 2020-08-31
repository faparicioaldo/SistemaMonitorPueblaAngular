package com.puebla.monitoralertas.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaDatosAlertaEnviadasSemoviDTO extends RespuestaJSON{
	
	private List<DatosAlertaEnviadasDTO> listaAlertasEnviadasSemovi;
}
