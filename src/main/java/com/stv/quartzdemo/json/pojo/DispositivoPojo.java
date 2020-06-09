package com.stv.quartzdemo.json.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class DispositivoPojo {
	@JsonProperty("id")
	private String id;
	@JsonProperty("st")
	private String st;
	@JsonProperty("tc")
	private String tc;
	@JsonProperty("cn")
	private String cn;
	@JsonProperty("nflt")
	private String nflt;
	@JsonProperty("md")
	private String md;
	@JsonProperty("us")
	private String us;
	@JsonProperty("ic")
	private String ic;
	@JsonProperty("isb")
	private String isb;
	@JsonProperty("sim")
	private String sim;
	@JsonProperty("vt")
	private String vt;
	@JsonProperty("dId")
	private String dId;
	@JsonProperty("tn")
	private String tn;
	@JsonProperty("sdc")
	private String sdc;
	@JsonProperty("io")
	private String io;
	@JsonProperty("cc")
	private String cc;
	@JsonProperty("pid")
	private String pid;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSt() {
		return st;
	}
	public void setSt(String st) {
		this.st = st;
	}
	public String getTc() {
		return tc;
	}
	public void setTc(String tc) {
		this.tc = tc;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getNflt() {
		return nflt;
	}
	public void setNflt(String nflt) {
		this.nflt = nflt;
	}
	public String getMd() {
		return md;
	}
	public void setMd(String md) {
		this.md = md;
	}
	public String getUs() {
		return us;
	}
	public void setUs(String us) {
		this.us = us;
	}
	public String getIc() {
		return ic;
	}
	public void setIc(String ic) {
		this.ic = ic;
	}
	public String getIsb() {
		return isb;
	}
	public void setIsb(String isb) {
		this.isb = isb;
	}
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public String getVt() {
		return vt;
	}
	public void setVt(String vt) {
		this.vt = vt;
	}
	public String getdId() {
		return dId;
	}
	public void setdId(String dId) {
		this.dId = dId;
	}
	public String getTn() {
		return tn;
	}
	public void setTn(String tn) {
		this.tn = tn;
	}
	public String getSdc() {
		return sdc;
	}
	public void setSdc(String sdc) {
		this.sdc = sdc;
	}
	public String getIo() {
		return io;
	}
	public void setIo(String io) {
		this.io = io;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

	
}
