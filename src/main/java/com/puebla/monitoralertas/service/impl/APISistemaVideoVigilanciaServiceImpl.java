package com.puebla.monitoralertas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.config.GlobalSession;
import com.puebla.monitoralertas.entity.AlertaCeibaEntity;
import com.puebla.monitoralertas.helper.Ceiba2ArmaURLHelper;
import com.puebla.monitoralertas.helper.ClientHttpHelper;
import com.puebla.monitoralertas.json.pojo.Ceiba2DeviceAlarmResponseDTO;
import com.puebla.monitoralertas.json.pojo.Ceiba2DeviceTerIdPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesAlarmRequestDTO;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesAlarmResponseDTO;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesGpsLastRequestPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesGpsLastResponsePojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2KeyPojo;
import com.puebla.monitoralertas.repository.AlertaCeibaRepository;
import com.puebla.monitoralertas.service.APISistemaVideoVigilanciaService;
import com.puebla.monitoralertas.service.VideoFrameService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class APISistemaVideoVigilanciaServiceImpl implements APISistemaVideoVigilanciaService {

	@Autowired
	private GlobalSession session;
	
	@Autowired
	private Ceiba2ArmaURLHelper ceiba2ArmaUrlHelper;

	@Autowired
	private AlertaCeibaRepository alertaRepository;

	@Autowired
	private ClientHttpHelper httpClient;
	
	@Autowired
	private VideoFrameService videoFrameService;
	
	/**
	 * Solicitud al CEIBA API para obtener token y se guarda en session
	 * 
	 * Ejemplo:
	 * 
	 * http://3.12.246.173:12056/api/v1/basic/key?username=administrador&password=M4ng3k10Sh4r1ng4n
	 * 
	 * */
	@Override
	public Ceiba2KeyPojo getCeibaToken() {
		String url = null;
		Ceiba2KeyPojo key = null;
		String jsonKey = "";
		try {
			log.info("Obteniendo token para acceder a API ceiba...");
			//Valida si el token se perdio o caduco
			if(session.getKeyCeiba2() == null || session.getKeyCeiba2().trim().equals("")) {
				log.info("Solicitando token a ceiba... ");
				//Solicita un nuevo token
				url = ceiba2ArmaUrlHelper.getUrlServiceKey();
				log.info("URL de ceiba para obtener token: " + url);
				jsonKey = httpClient.peticionHttpGet(url);
				key = new ObjectMapper().readValue(jsonKey, Ceiba2KeyPojo.class);
				log.info("token obtenido de CEIBA 2: " + key.getData().getKey());
			}else {
				//Utiliza el token de la session
				Ceiba2KeyPojo keyPojo = new Ceiba2KeyPojo();
				keyPojo.getData().setKey(session.getKeyCeiba2());
				key = keyPojo;
				log.info("Se obtuvo token de la session: " + key.getData().getKey());
			}
		} catch (Exception e) {
			log.error("Error al obtener token para el API de ceiba2: " + url , e);
		}

		return key;
	}

	@Override
	public Ceiba2DevicesPojo getAllVehicles(String key) {
		String url = null;
		String jsonDevices = "";
		Ceiba2DevicesPojo devices = null;
		try {
			log.info("Obteniendo lista de vehiculos registrados en CEIBA...");
			url = ceiba2ArmaUrlHelper.getUrlDevices(key);
			jsonDevices = httpClient.peticionHttpGet(url);
			devices = new ObjectMapper().readValue(jsonDevices, Ceiba2DevicesPojo.class);
			log.info("Obteniendo lista de vehiculos registrados en CEIBA...");
		} catch (Exception e) {
			log.error("No se pudo obtener vehiculos registrados en CEIBA", e);
		}

		return devices;
	}

	@Override
	public String getLiveVideo(String vehicleId) {
		String url = null;
		try {
			url = videoFrameService.getUrlFrameLiveVideo(vehicleId);
		} catch (Exception e) {
			log.error("Error: ", e);
		}

		return url;
	}

	@Override
	public Ceiba2DevicesGpsLastResponsePojo getGPSVehicle(String key, List<String> teridList) {
		String url = null;
		Ceiba2DevicesGpsLastRequestPojo request = null;
		Ceiba2DevicesGpsLastResponsePojo response = null;
		String deviceGpsLastJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			url = ceiba2ArmaUrlHelper.getUrlDeviceGpsLast();
			request = new Ceiba2DevicesGpsLastRequestPojo();
			request.setKey(key);
			request.setTerid(teridList);			
			deviceGpsLastJson = httpClient.peticionHttpPost(url, request);

			response = mapper.readValue(deviceGpsLastJson, Ceiba2DevicesGpsLastResponsePojo.class);

		} catch (Exception e) {
			log.error("Error: ", e);
		}

		return response;
	}

	@Override
	public Ceiba2DeviceTerIdPojo getEstatusVehicle(String key, List<String> terids) {

		String url = null;
		Ceiba2DevicesGpsLastRequestPojo request = null;
		Ceiba2DeviceTerIdPojo response = null;
		String deviceGpsLastJson = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			url = ceiba2ArmaUrlHelper.getUrlDevicesOnlineNow();
			request = new Ceiba2DevicesGpsLastRequestPojo();
			request.setKey(key);
			request.setTerid(terids);			
			deviceGpsLastJson = httpClient.peticionHttpPost(url, request);

			response = mapper.readValue(deviceGpsLastJson, Ceiba2DeviceTerIdPojo.class);

		} catch (Exception e) {
			log.error("Error: ", e);
		}

		return response;
	}

	/**
	 * Consulta en CEIBA2 si hay alertas de boton de panico:
	 * <ul>
	 * 	<li>Si se encuentran se envian a SEMOVI.</li>
	 * 	<li>Si estan registradas en la base de datos no las envia a SEMOVI.</li>
	 * </ul>
	 * */
	@Override
	public Ceiba2DevicesAlarmResponseDTO getDevicesAlarmInfo(String key, List<String> terids, List<String> types, String starttime, String endtime) {

		String url = null;
		Ceiba2DevicesAlarmRequestDTO request = null;
		Ceiba2DevicesAlarmResponseDTO response = null;
		Ceiba2DevicesAlarmResponseDTO responseDepurado = null;
		String deviceGpsLastJson = "";
		List<AlertaCeibaEntity> alertasGuardadas = null;
		ObjectMapper mapper = null;
		
		try {
			responseDepurado = new Ceiba2DevicesAlarmResponseDTO();
			mapper = new ObjectMapper();

			url = ceiba2ArmaUrlHelper.getUrlDevicesAlarmInfo();
			request = new Ceiba2DevicesAlarmRequestDTO();
			request.setKey(key);
			request.setTerid(terids);
			request.setType(types);
			request.setStarttime(starttime);
			request.setEndtime(endtime);
			deviceGpsLastJson = httpClient.peticionHttpPost(url, request);

			response = mapper.readValue(deviceGpsLastJson, Ceiba2DevicesAlarmResponseDTO.class);

			if(response != null && response.getData()!=null&& response.getData().size() > 0) {
				AlertaCeibaEntity alertaEntity= new AlertaCeibaEntity();

				try {
					alertasGuardadas = alertaRepository.findAll();
				}catch(Exception e) {
					log.error("Error al obtener aletas de base ALERTAS_CEIBA", e);
				}
				responseDepurado.setData(new ArrayList<Ceiba2DeviceAlarmResponseDTO>());
				for(Ceiba2DeviceAlarmResponseDTO alarma : response.getData()) {
					try {
						/*
						 * Valida si la alerta ya reporto previamente
						 * */
						boolean existeAlerta = false;
						if(alertasGuardadas != null && alertasGuardadas.size() > 0) {
							for(AlertaCeibaEntity alertaEnBase : alertasGuardadas) {
								if(alertaEnBase.getAlarmid().equals(alarma.getAlarmid())) {
									existeAlerta = true;
									log.warn("ALERTA YA SE ENVIO PREVIAMENTE: " + alertaEnBase.getAlarmid());
									break;
								}
							}
							if(existeAlerta)
								continue;
						}

						/*
						 * Si la alerta no se ha reposrtado previamente
						 * Se ingresa a la base
						 * */
						alertaEntity.setAlarmid(alarma.getAlarmid());
						alertaEntity.setTerid(alarma.getTerid());
						alertaEntity.setType(alarma.getType());
						
						alertaRepository.save(alertaEntity);	
						log.info("Se agrega nueva alerta ala base:" + alarma.getAlarmid());
						
						responseDepurado.getData().add(alarma);

					}catch(Exception e) {
						log.error("Error al guardar alerta en base: ", e);
					}
				}
			}
			
		} catch (Exception e) {
			log.error("Error al obetener alarmas de ceiba2, json result: " + deviceGpsLastJson, e);
		}


		return responseDepurado;
	}

}
