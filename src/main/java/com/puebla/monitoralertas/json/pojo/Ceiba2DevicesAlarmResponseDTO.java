package com.puebla.monitoralertas.json.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class Ceiba2DevicesAlarmResponseDTO {

	@JsonProperty("errorcode")
	private Integer errorcode;
	@JsonProperty("data")
	private List<Ceiba2DeviceAlarmResponseDTO> data;

}
