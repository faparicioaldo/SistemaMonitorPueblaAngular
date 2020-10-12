package com.puebla.monitoralertas.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendGPSToSemoviErrorResponseDTO {

	private int numEnviadosOK;
	private int numEnviadosNOK;
	private List<SendGPSToSemoviErrorDTO> errors;
}
