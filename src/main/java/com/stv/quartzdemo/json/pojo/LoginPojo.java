package com.stv.quartzdemo.json.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class LoginPojo {
	@JsonProperty("result")
	private String result;
	@JsonProperty("jsession")
	private String jsession;
	@JsonProperty("account_name")
	private String account_name;
	@JsonProperty("JSESSIONID")
	private String JSESSIONID;
}
