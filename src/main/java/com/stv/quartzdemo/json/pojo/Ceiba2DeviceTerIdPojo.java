package com.stv.quartzdemo.json.pojo;

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
public class Ceiba2DeviceTerIdPojo {
	@JsonProperty("data")
	public List<DataPojo> data;
	@JsonProperty("errorcode")
	public String errorcode;



}
