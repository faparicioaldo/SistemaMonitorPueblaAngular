package com.puebla.monitoralertas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RespuestaGobiernoDTO {
	private String encabezado;
	private String longitudSegMsjHex;
	private Integer longitudSegMsjDec;
	private String identificadorCampoTres;
	private String folioCAD;
}