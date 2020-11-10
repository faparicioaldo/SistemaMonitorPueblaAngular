package com.puebla.monitoralertas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class SemoviSendRequestDTO {

	@JsonProperty("Id")
	private String id;
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
	@JsonProperty("PanicButton")
	private String pannicbutton;
}
