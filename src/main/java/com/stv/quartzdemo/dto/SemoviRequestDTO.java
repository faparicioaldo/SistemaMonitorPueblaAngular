package com.stv.quartzdemo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class SemoviRequestDTO {
	
	@JsonProperty("IMEI")
	private String imei;
	@JsonProperty("Longitude")
	private String longitude;
	@JsonProperty("Latitude")
	private String latitude;
	@JsonProperty("Address")
	private String address;
	@JsonProperty("Speed")
	private String speed;
	@JsonProperty("Course")
	private String course;
	@JsonProperty("Date")
	private String date;
	@JsonProperty("Ignition")
	private String ignition;
	@JsonProperty("Fix")
	private String fix;
	@JsonProperty("Sat")
	private String sat;
	@JsonProperty("Plate")
	private String plate;
	@JsonProperty("VIN")
	private String vin;
	@JsonProperty("Engine")
	private String engine;
	@JsonProperty("Year")
	private String year;
	@JsonProperty("Color")
	private String color;
	@JsonProperty("Route")
	private String route;
	@JsonProperty("RS")
	private String rs;
	@JsonProperty("Eco")
	private String eco;
	@JsonProperty("Branch")
	private String branch;
	@JsonProperty("Subbranch")
	private String subbranch;
	@JsonProperty("URLCamera")
	private String url_camera;
	@JsonProperty("PanicButton")
	private String panic_button;

}
