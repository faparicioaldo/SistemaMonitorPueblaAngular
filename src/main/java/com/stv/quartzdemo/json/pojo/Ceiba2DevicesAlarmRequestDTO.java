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
public class Ceiba2DevicesAlarmRequestDTO {

	@JsonProperty("key")
	private String key;
	@JsonProperty("terid")
	private List<String> terid;
	@JsonProperty("type")
	private List<String> type;
	@JsonProperty("starttime")
	private String starttime;
	@JsonProperty("endtime")
	private String endtime;

}


