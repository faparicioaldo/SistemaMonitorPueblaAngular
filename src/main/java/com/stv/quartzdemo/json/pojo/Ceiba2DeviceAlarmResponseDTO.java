package com.stv.quartzdemo.json.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class Ceiba2DeviceAlarmResponseDTO {

	@JsonProperty("terid")
	private String terid;
	@JsonProperty("gpstime")
	private String gpstime;
	@JsonProperty("altitude")
	private String altitude;
	@JsonProperty("direction")
	private String direction;
	@JsonProperty("gpslat")
	private String gpslat;
	@JsonProperty("gpslng")
	private String gpslng;
	@JsonProperty("speed")
	private String speed;
	@JsonProperty("recordspeed")
	private String recordspeed;
	@JsonProperty("state")
	private String state;
	@JsonProperty("time")
	private String time;
	@JsonProperty("type")
	private String type;
	@JsonProperty("content")
	private String content;
	@JsonProperty("cmdtype")
	private String cmdtype;
	@JsonProperty("alarmid")
	private String alarmid;

}
