package com.stv.quartzdemo.json.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class CompanyPojo {

	@JsonProperty("id")
	private String id;
	@JsonProperty("nm")
	private String nm;
	@JsonProperty("pId")
	private String pId;
	
}
