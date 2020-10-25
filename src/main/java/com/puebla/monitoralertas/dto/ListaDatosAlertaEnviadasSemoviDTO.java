package com.puebla.monitoralertas.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaDatosAlertaEnviadasSemoviDTO extends RespuestaJSON{
	
	private List<IDatosAlertaEnviadasDTO> listaAlertasEnviadasSemovi;
	private Set<String> alertasNoVistas = new HashSet<>();
	private Integer alertasNoVistasCount = 0;

	public Integer getAlertasNoVistasCount() {
		return alertasNoVistas.size();
	}
}
