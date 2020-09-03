package com.puebla.monitoralertas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class SemoviResponseDTO {

	@JsonProperty("status")
	private Boolean status;
	@JsonProperty("msg")
	private String msg;

}
