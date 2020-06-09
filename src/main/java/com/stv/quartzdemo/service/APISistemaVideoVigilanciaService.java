package com.stv.quartzdemo.service;

import java.util.List;

import com.stv.quartzdemo.json.pojo.Ceiba2DeviceTerIdPojo;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesAlarmResponseDTO;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesGpsLastResponsePojo;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesPojo;
import com.stv.quartzdemo.json.pojo.Ceiba2KeyPojo;

public interface APISistemaVideoVigilanciaService {

	public Ceiba2KeyPojo login();
    public Ceiba2DevicesPojo getAllVehicles(String key);
//    public Ceiba2LiveVideoPojo getLiveVideo(String key, String vehiceid, String channel);
    public String getLiveVideo(String vehiceid);
	public Ceiba2DevicesGpsLastResponsePojo getGPSVehicle(String key, List<String> teridList);
	public Ceiba2DeviceTerIdPojo getEstatusVehicle(String key, List<String> terids);
	public Ceiba2DevicesAlarmResponseDTO getDevicesAlarmInfo(String key, List<String> terids, List<String> types, String starttime, String endtime);

}
