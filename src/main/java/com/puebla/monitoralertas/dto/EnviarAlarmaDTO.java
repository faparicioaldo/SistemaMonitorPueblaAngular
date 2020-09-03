package com.puebla.monitoralertas.dto;

import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnviarAlarmaDTO {

	private Integer idAlarma;
	private String deviceid;
}
