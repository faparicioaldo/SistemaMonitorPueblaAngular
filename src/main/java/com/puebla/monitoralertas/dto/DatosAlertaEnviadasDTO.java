package com.puebla.monitoralertas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatosAlertaEnviadasDTO implements IDatosAlertaEnviadasDTO{
	
	private String idAlerta;
	private String ceibaAlarmid;
	private String idDispositivo;
	private String plate;
	private String eco;
	private String ceibaGpsTime;
	private String semoviEstatus;
	private String empresa;
	private String route;
	private String semoviMensaje;
	private String ceibaType;
	private String urlCamera;
}
