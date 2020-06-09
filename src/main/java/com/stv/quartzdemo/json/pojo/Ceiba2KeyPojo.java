package com.stv.quartzdemo.json.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Ceiba2KeyPojo {
	@JsonProperty("data")
	private Data data;
	@JsonProperty("errorcode")
	private String errorcode;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Getter
	@Setter
	@ToString
	public class Data {
		@JsonProperty("key")
		private String key;		
	}
}
