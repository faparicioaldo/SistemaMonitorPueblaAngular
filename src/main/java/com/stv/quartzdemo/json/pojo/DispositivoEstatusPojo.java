package com.stv.quartzdemo.json.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class DispositivoEstatusPojo {
	@JsonProperty("id")
	private String id;
	@JsonProperty("pt")
	private String pt;
	@JsonProperty("vid")
	private String vid;
	@JsonProperty("dt")
	private String dt;
	@JsonProperty("lc")
	private String lc;
	@JsonProperty("sp")
	private String sp;
	@JsonProperty("dn")
	private String dn;
	@JsonProperty("jn")
	private String jn;
	@JsonProperty("lid")
	private String lid;
	@JsonProperty("ft")
	private String ft;
	@JsonProperty("pk")
	private String pk;
	@JsonProperty("ol")
	private String ol;
	@JsonProperty("ps")
	private String ps;
	@JsonProperty("gw")
	private String gw;
	@JsonProperty("t1")
	private String t1;
	@JsonProperty("s3")
	private String s3;
	@JsonProperty("hx")
	private String hx;
	@JsonProperty("t4")
	private String t4;
	@JsonProperty("s1")
	private String s1;
	@JsonProperty("s4")
	private String s4;
	@JsonProperty("t2")
	private String t2;
	@JsonProperty("t3")
	private String t3;
	@JsonProperty("lng")
	private String lng;
	@JsonProperty("net")
	private String net;
	@JsonProperty("lt")
	private String lt;
	@JsonProperty("lat")
	private String lat;
	@JsonProperty("mlng")
	private String mlng;
	@JsonProperty("mlat")
	private String mlat;
	@JsonProperty("s2")
	private String s2;
	@JsonProperty("ojt")
	private String ojt;
	@JsonProperty("yl")
	private String yl;
	@JsonProperty("hv")
	private String hv;
	@JsonProperty("fdt")
	private String fdt;
	@JsonProperty("sfg")
	private String sfg;
	@JsonProperty("ojm")
	private String ojm;
	@JsonProperty("snm")
	private String snm;
	@JsonProperty("os")
	private String os;
	@JsonProperty("p5")
	private String p5;
	@JsonProperty("p8")
	private String p8;
	@JsonProperty("p10")
	private String p10;
	@JsonProperty("ac")
	private String ac;
	@JsonProperty("sv")
	private String sv;
	@JsonProperty("drid")
	private String drid;
	@JsonProperty("p7")
	private String p7;
	@JsonProperty("sst")
	private String sst;
	@JsonProperty("ef")
	private String ef;
	@JsonProperty("p9")
	private String p9;
	@JsonProperty("dct")
	private String dct;
	@JsonProperty("or")
	private String or;
	@JsonProperty("ov")
	private String ov;
	@JsonProperty("p6")
	private String p6;
	@JsonProperty("po")
	private String po;
	@JsonProperty("glat")
	private String glat;
	@JsonProperty("gt")
	private String gt;
	@JsonProperty("ost")
	private String ost;
	@JsonProperty("glng")
	private String glng;
	@JsonProperty("p1")
	private String p1;
	@JsonProperty("p4")
	private String p4;
	@JsonProperty("p2")
	private String p2;
	@JsonProperty("p3")
	private String p3;
	@JsonProperty("imei")
	private String imei;
	@JsonProperty("imsi")
	private String imsi;
}
