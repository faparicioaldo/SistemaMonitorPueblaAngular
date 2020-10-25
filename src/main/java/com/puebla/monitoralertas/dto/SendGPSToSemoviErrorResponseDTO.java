package com.puebla.monitoralertas.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@JsonIgnoreProperties
	private Map<String,SendGPSToSemoviErrorDTO> errors = new HashMap<>();
	private List<SendGPSToSemoviErrorDTO> errorsList = new ArrayList<>();
}
