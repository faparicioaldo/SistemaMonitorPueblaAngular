package com.puebla.monitoralertas.helper;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.puebla.monitoralertas.common.CadenasCommon;
import com.puebla.monitoralertas.config.CargaCeiba2ServerProperties;

import lombok.extern.log4j.Log4j2;

/**
 * Clase para armado de URL's de CEIBA2 para el consumo de su API
 * 
 * @author Aldo Flores Aparicio
 * 
 * */
@Component
@Log4j2
public class Ceiba2ArmaURLHelper {

	@Autowired
	private CargaCeiba2ServerProperties ceiba2ServerProperties;
		
	@Autowired
	private CadenasUrlHelper cadenasUrlHelper;
	
	@Autowired
	private CadenasCommon cadenasHelper;
	
	private String urlDominioCeiba2Api;
	
	private String urlDominioCeiba2Video;
	
	/**
	 * Crea URL base (dominio + contexto) de CEIBA2 utilizados por metodos de esta clase para 
	 * realizar petuciones al API (de datos y video) 
	 * */
	@PostConstruct
	public void PostConstructor() {				
		urlDominioCeiba2Api = 
				cadenasUrlHelper.armaUrl(
						ceiba2ServerProperties.getCeiba2ServiceProtocol(), 
						ceiba2ServerProperties.getCeiba2ServiceIp(), 
						ceiba2ServerProperties.getCeiba2ServicePort(),
						ceiba2ServerProperties.getCeiba2ServiceAppContext());

		urlDominioCeiba2Video = 
				cadenasUrlHelper.armaUrl(
						ceiba2ServerProperties.getCeiba2ServiceProtocol(), 
						ceiba2ServerProperties.getCeiba2ServiceIp(), 
						ceiba2ServerProperties.getCeiba2ServiceVideoPort(),
						ceiba2ServerProperties.getCeiba2ServiceAppContext());
	}
	
	/**
	 * Construye URL para consultar servicio que obtiene token.
	 * 
	 * <br/> Metodo GET 
	 * */
	public String getUrlServiceKey() {
				
		String rutaServicio = cadenasHelper.format(ceiba2ServerProperties.getKey(),new String[]{ceiba2ServerProperties.getCeiba2ServiceUsername(),ceiba2ServerProperties.getCeiba2ServicePassword()});
		String url = urlDominioCeiba2Api + rutaServicio;
		return url;
	}
	
	/**
	 * Construye URL para consultar lista de dispositivos registrados en CEIBA
	 *  
	 * <br/> Metodo GET 
	 * */
	public String getUrlDevices(String key) {
		String rutaServicio = cadenasHelper.format(ceiba2ServerProperties.getDevices(),key);
		String url = urlDominioCeiba2Api + rutaServicio;
		return url;
	}

	/**
	 * Construye URL's para consultar video en streaming de las 4 camaras de cada dispositivo DVR
	 *  
	 * <br/> Metodo GET 
	 * */
	public Map<String,String> getUriliveVideosByDevid(String devid) {
		Map<String, String> videosUri = new HashMap<>();
		
		String pathVideoChannel1 = cadenasHelper.format(ceiba2ServerProperties.getLiveVideoDirectUri(),new String[] {devid, "1"});
		String pathVideoChannel2 = cadenasHelper.format(ceiba2ServerProperties.getLiveVideoDirectUri(),new String[] {devid, "2"});
		String pathVideoChannel3 = cadenasHelper.format(ceiba2ServerProperties.getLiveVideoDirectUri(),new String[] {devid, "3"});
		String pathVideoChannel4 = cadenasHelper.format(ceiba2ServerProperties.getLiveVideoDirectUri(),new String[] {devid, "4"});
		String uriVideo1 = urlDominioCeiba2Video + pathVideoChannel1;
		String uriVideo2 = urlDominioCeiba2Video + pathVideoChannel2;
		String uriVideo3 = urlDominioCeiba2Video + pathVideoChannel3;
		String uriVideo4 = urlDominioCeiba2Video + pathVideoChannel4;
		videosUri.put("video1", uriVideo1);
		videosUri.put("video2", uriVideo2);
		videosUri.put("video3", uriVideo3);
		videosUri.put("video4", uriVideo4);
		return videosUri;
	}
	
	/**
	 * Construye URL para consultar la ultima ubicacion (coordenadas latitud y longitus entre otros datos) 
	 * de los vehiculos registrados en CEIBA
	 *  
	 * <br/> Metodo POST 
	 * */
	public String getUrlDeviceGpsLast() {
		String rutaServicio = ceiba2ServerProperties.getDeviceGpsLast();
		String url = urlDominioCeiba2Api + rutaServicio;
		log.info("Url ceiba DeviceGpsLast: " + url);
		return url;
	}
	
	/**
	 * Construye URL para consultar los dispositivos que estan en linea (encendidos y conectados a internet) 
	 * al momento de esta consulta.
	 *  
	 * <br/> Metodo POST 
	 * */
	public String getUrlDevicesOnlineNow() {
		String rutaServicio = ceiba2ServerProperties.getDeviceOnlineNow();
		String url = urlDominioCeiba2Api + rutaServicio;
		log.info("Url ceiba DeviceOnlineNow: " + url);
		return url;
	}
	
	/**
	 * Construye URL para consultar informacion de la alertas
	 *  
	 * <br/> Metodo POST 
	 * */
	public String getUrlDevicesAlarmInfo() {
		String rutaServicio = ceiba2ServerProperties.getDeviceAlarmInfo();
		String url = urlDominioCeiba2Api + rutaServicio;
		log.info("Url ceiba DeviceAlarmInfo: " + url);
		return url;
	}
}