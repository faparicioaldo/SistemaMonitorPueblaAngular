package com.puebla.monitoralertas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosAlertaSemoviDTO {
	
	//Datos complemento para operaciones y guardado en bitacora (tabla Alertas_Semovi)
	private String idDispositivo;
	private String fechaRecepcionAlerta;
	private String ceibaTime;
	private String ceibaGpsTime;
	private String ceibaType;
	private String ceibaContent;
	private String ceibaCmdType;
	private String ceibaAlarmId;
	
//	private String semoviEstatus;
//	private String semoviMensaje;
	private String semoviRespuesta;
//	private String latitud;
//	private String longitud;
	
	
	//Datos que se enviaran a semovi
	private SemoviRequestDTO datosSemovi;
		
}
