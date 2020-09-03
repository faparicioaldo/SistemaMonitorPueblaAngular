package com.puebla.monitoralertas.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SemoviLoadRequestDTO {

	@JsonProperty("Id")
	private String id;
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
	@JsonProperty("Municipio")
	private String municipio;
	@JsonProperty("Concesion")
	private String concesion;	
	@JsonProperty("URLCamera")
	private String urlcamera;
}
