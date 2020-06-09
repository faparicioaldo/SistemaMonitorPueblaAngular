package com.stv.quartzdemo.helper.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stv.quartzdemo.config.CargaCeiba2ServerProperties;
import com.stv.quartzdemo.helper.Ceiba2ArmaURLHelper;

@Component
public class Ceiba2ArmaURLHelperImpl implements Ceiba2ArmaURLHelper{

	@Autowired
	private CargaCeiba2ServerProperties ceiba2ServerProperties;
	
	@Override
	public String getUrlServiceKey() {
				
		String rutaServicio = format(ceiba2ServerProperties.getKey(),new String[]{ceiba2ServerProperties.getCeiba2ServiceUsername(),ceiba2ServerProperties.getCeiba2ServicePassword()});
		String url = armaAppContext() + rutaServicio;
		return url;
	}
	
	@Override
	public String getUrlDevices(String key) {
		String rutaServicio = format(ceiba2ServerProperties.getDevices(),key);
		String url = armaAppContext() + rutaServicio;
		return url;
	}

//	@Override
//	public String getUrlliveVideoUri(String key, String vehiceid, String channel) {
//		String rutaServicio = format(ceiba2ServerProperties.getLiveVideoUrl(),new String[] {key,vehiceid,channel});
//		String url = armaAppContext() + rutaServicio;
//		return url;
//	}

	@Override
	public String getUrlliveVideoUri(String vehiceid) {
//		String rutaServicio = format(ceiba2ServerProperties.getLiveVideoUrl(),new String[] {key,vehiceid,channel});
		String rutaServicio = "/video/"+vehiceid;
		String url = "http://3.14.24.174:8084" + rutaServicio;
		return url;
	}

	@Override
	public Map<String,String> getUriliveVideosByDevid(String devid) {
		Map<String, String> videosUri = new HashMap<>();
		
		String pathVideoChannel1 = format(ceiba2ServerProperties.getLiveVideoDirectUri(),new String[] {devid, "1"});
		String pathVideoChannel2 = format(ceiba2ServerProperties.getLiveVideoDirectUri(),new String[] {devid, "2"});
		String pathVideoChannel3 = format(ceiba2ServerProperties.getLiveVideoDirectUri(),new String[] {devid, "3"});
		String pathVideoChannel4 = format(ceiba2ServerProperties.getLiveVideoDirectUri(),new String[] {devid, "4"});
		String uriVideo1 = armaVideoContext() + pathVideoChannel1;
		String uriVideo2 = armaVideoContext() + pathVideoChannel2;
		String uriVideo3 = armaVideoContext() + pathVideoChannel3;
		String uriVideo4 = armaVideoContext() + pathVideoChannel4;
		videosUri.put("video1", uriVideo1);
		videosUri.put("video2", uriVideo2);
		videosUri.put("video3", uriVideo3);
		videosUri.put("video4", uriVideo4);
		return videosUri;
	}

	@Override
	public String getUrlDeviceGpsLast() {
		String rutaServicio = ceiba2ServerProperties.getDeviceGpsLast();
		String url = armaAppContext() + rutaServicio;
		System.err.println("Url ceiba DeviceGpsLast: " + url);
		return url;
	}

	@Override
	public String getUrlDevicesOnlineNow() {
		String rutaServicio = ceiba2ServerProperties.getDeviceOnlineNow();
		String url = armaAppContext() + rutaServicio;
		System.err.println("Url ceiba DeviceOnlineNow: " + url);
		return url;
	}

	@Override
	public String getUrlDevicesAlarmInfo() {
		String rutaServicio = ceiba2ServerProperties.getDeviceAlarmInfo();
		String url = armaAppContext() + rutaServicio;
		System.err.println("Url ceiba DeviceAlarmInfo: " + url);
		return url;
	}

	private String armaAppContext() {
		StringBuilder url = new StringBuilder();
		
		url.append(ceiba2ServerProperties.getCeiba2ServiceProtocol())
		   .append("://")
		   		.append(ceiba2ServerProperties.getCeiba2ServiceIp())
		   .append(":")
		   		.append(ceiba2ServerProperties.getCeiba2ServicePort())
		   .append("")
		   		.append(ceiba2ServerProperties.getCeiba2ServiceAppContext().trim().equals("/")?"":ceiba2ServerProperties.getCeiba2ServiceAppContext());
		
		return url.toString();
	}

	private String armaVideoContext() {
		StringBuilder url = new StringBuilder();
		
		url.append(ceiba2ServerProperties.getCeiba2ServiceProtocol())
		   .append("://")
		   		.append(ceiba2ServerProperties.getCeiba2ServiceIp())
		   .append(":")
		   		.append(ceiba2ServerProperties.getCeiba2ServiceVideoPort())
		   .append("")
		   		.append(ceiba2ServerProperties.getCeiba2ServiceAppContext().trim().equals("/")?"":ceiba2ServerProperties.getCeiba2ServiceAppContext());
		
		return url.toString();
	}

	private String format(String cadena,String error) {
		return (MessageFormat.format(cadena.substring(0), error));
	}

	private String format(String cadena, Object[] errores) {
		return (MessageFormat.format(cadena.substring(0), errores));
	}

}
