package com.stv.quartzdemo.json.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class Ceiba2LiveVideoPojo {
	@JsonProperty("data")
	private Data data;
	@JsonProperty("errorcode")
	private String errorcode;
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	@Getter
	@Setter
	@ToString
	public class Data {
		@JsonProperty("url")
		private String url;		
	}
}
