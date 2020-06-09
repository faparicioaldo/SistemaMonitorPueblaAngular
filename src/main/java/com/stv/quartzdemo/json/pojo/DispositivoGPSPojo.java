package com.stv.quartzdemo.json.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class DispositivoGPSPojo {
	@JsonProperty("result")
	private String result;
	@JsonProperty("status")
	private List<DispositivoEstatusPojo> status;
}
