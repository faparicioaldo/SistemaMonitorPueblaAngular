package com.stv.quartzdemo.json.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@ToString
public class UserVehiclePojo {

	@JsonProperty("result")
	private String result;
	@JsonProperty("companys")
	private List<CompanyPojo> companys;
	@JsonProperty("vehicles")
	private List<VehiclePojo> vehicles;
		
}
