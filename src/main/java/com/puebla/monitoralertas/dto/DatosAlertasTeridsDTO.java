package com.puebla.monitoralertas.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosAlertasTeridsDTO {
	
	//Datos complemento para operaciones y guardado en bitacora (tabla Alertas_Semovi) y para envio a semovi
	private List<DatosAlertaSemoviDTO> datosAlertas;
	
	//Datos para peticiones a api ceiba
	private List<String> teridsList;
		
}
