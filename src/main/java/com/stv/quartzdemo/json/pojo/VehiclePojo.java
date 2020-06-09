package com.stv.quartzdemo.json.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class VehiclePojo {
	@JsonProperty("id")
	private String id;
	@JsonProperty("st")
	private String st;
	@JsonProperty("dt")
	private String dt;
	@JsonProperty("dn")
	private String dn;
	@JsonProperty("abbr")
	private String abbr;
	@JsonProperty("desc")
	private String desc;
	@JsonProperty("linesOperation")
	private String linesOperation;
	@JsonProperty("pvg")
	private String pvg;
	@JsonProperty("adt")
	private String adt;
	@JsonProperty("dl")
	private List<DispositivoPojo> dl;
	@JsonProperty("djb")
	private String djb;
	@JsonProperty("pnm")
	private String pnm;
	@JsonProperty("ic")
	private String ic;
	@JsonProperty("nm")
	private String nm;
	@JsonProperty("etm")
	private String etm;
	@JsonProperty("vtp")
	private String vtp;
	@JsonProperty("pid")
	private String pid;
	@JsonProperty("phone")
	private String phone;
	
}
