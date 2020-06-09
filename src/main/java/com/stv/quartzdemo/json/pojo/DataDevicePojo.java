package com.stv.quartzdemo.json.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class DataDevicePojo {

	@JsonProperty("carlicence")
	private String carlicence;
	@JsonProperty("deviceid")
	private String deviceid;
	@JsonProperty("sim")
	private String sim;
	@JsonProperty("channelcount")
	private String channelcount;
	@JsonProperty("cname")
	private String cname;
	@JsonProperty("platecolor")
	private String platecolor;
	@JsonProperty("groupid")
	private String groupid;
	@JsonProperty("devicetype")
	private String devicetype;
	@JsonProperty("linktype")
	private String linktype;
	@JsonProperty("deviceusername")
	private String deviceusername;
	@JsonProperty("devicepassword")
	private String devicepassword;
	@JsonProperty("registerip")
	private String registerip;
	@JsonProperty("registerport")
	private String registerport;
	@JsonProperty("transmitip")
	private String transmitip;
	@JsonProperty("transmitport")
	private String transmitport;
	@JsonProperty("en")
	private String en;
}
