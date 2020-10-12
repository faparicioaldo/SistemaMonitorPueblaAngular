package com.puebla.monitoralertas.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendGPSToSemoviErrorDTO {

	private String deviceId;
	private String errorType;
	private String errorMessage;
	private String lastGps;
}
