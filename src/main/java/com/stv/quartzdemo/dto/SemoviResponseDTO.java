package com.stv.quartzdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class SemoviResponseDTO {

	@JsonProperty("status")
	private String status;
	@JsonProperty("msg")
	private String msg;

}
