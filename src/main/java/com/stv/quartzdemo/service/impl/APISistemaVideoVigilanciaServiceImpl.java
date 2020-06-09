package com.stv.quartzdemo.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stv.quartzdemo.config.CargaCeiba2ServerProperties;
import com.stv.quartzdemo.config.GlobanSession;
import com.stv.quartzdemo.entity.AlertaCeibaEntity;
import com.stv.quartzdemo.helper.Ceiba2ArmaURLHelper;
import com.stv.quartzdemo.json.pojo.Ceiba2DeviceAlarmResponseDTO;
import com.stv.quartzdemo.json.pojo.Ceiba2DeviceTerIdPojo;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesAlarmRequestDTO;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesAlarmResponseDTO;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesGpsLastRequestPojo;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesGpsLastResponsePojo;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesPojo;
import com.stv.quartzdemo.json.pojo.Ceiba2KeyPojo;
import com.stv.quartzdemo.json.pojo.Ceiba2LiveVideoPojo;
import com.stv.quartzdemo.repository.AlertaCeibaRepository;
import com.stv.quartzdemo.service.APISistemaVideoVigilanciaService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class APISistemaVideoVigilanciaServiceImpl implements APISistemaVideoVigilanciaService {

	@Autowired
	private GlobanSession session;
	
	@Autowired
	private Ceiba2ArmaURLHelper ceiba2ArmaUrlHelper;

	@Autowired
	private CargaCeiba2ServerProperties ceiba2ServerProperties;

	@Autowired
	private AlertaCeibaRepository alertaRepository;

	@Override
	public Ceiba2KeyPojo login() {
		String url = null;
		Ceiba2KeyPojo key = null;
		String jsonKey = "";
		try {
			if(session.getKeyCeiba2() == null || session.getKeyCeiba2().trim().equals("")) {
				url = ceiba2ArmaUrlHelper.getUrlServiceKey();
				log.info("URL login ceiba: " + url);
				System.out.println("URL login ceiba: " + url);
				jsonKey = peticionHttpGet(url);
				key = new ObjectMapper().readValue(jsonKey, Ceiba2KeyPojo.class);
				log.info("SE SOLICITA KEY A CEIBA 2" + key.getData().getKey());
			}else {
				Ceiba2KeyPojo keyPojo = new Ceiba2KeyPojo();
				keyPojo.getData().setKey(session.getKeyCeiba2());
				key = keyPojo;
				log.info("SE OBTINE KEY DE LA SESSION: " + key.getData().getKey());
			}
		} catch (Exception e) {
			log.error("Error al obtener key ceiba2: " + url , e);
		}

		return key;
	}

	@Override
	public Ceiba2DevicesPojo getAllVehicles(String key) {
		String url = null;
		String jsonDevices = "";
		Ceiba2DevicesPojo devices = null;
		try {
			url = ceiba2ArmaUrlHelper.getUrlDevices(key);
			jsonDevices = peticionHttpGet(url);
			devices = new ObjectMapper().readValue(jsonDevices, Ceiba2DevicesPojo.class);
		} catch (Exception e) {
			log.error("Error: ", e);
		}

		return devices;
	}

//	@Override
//	public Ceiba2LiveVideoPojo getLiveVideo(String key, String vehiceid, String channel) {
//		String url = null;
//		Ceiba2LiveVideoPojo ceiba2LiveVideoPojo = null;
//		String liveVideoUriJson = "";
//		try {
//			url = ceiba2ArmaUrlHelper.getUrlliveVideoUri(key, vehiceid, channel);
//			liveVideoUriJson = peticionHttpGet(url);
//			ceiba2LiveVideoPojo = new ObjectMapper().readValue(liveVideoUriJson, Ceiba2LiveVideoPojo.class);
//		} catch (Exception e) {
//			log.error("Error: ", e);
//		}
//
//		return ceiba2LiveVideoPojo;
//	}

	@Override
	public String getLiveVideo(String vehiceid) {
		String url = null;
		Ceiba2LiveVideoPojo ceiba2LiveVideoPojo = null;
		String liveVideoUriJson = "";
		try {
			url = ceiba2ArmaUrlHelper.getUrlliveVideoUri(vehiceid);
//			liveVideoUriJson = peticionHttpGet(url);
//			ceiba2LiveVideoPojo = new ObjectMapper().readValue(liveVideoUriJson, Ceiba2LiveVideoPojo.class);
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
			deviceGpsLastJson = peticionHttpPost(url, request);

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
			deviceGpsLastJson = peticionHttpPost(url, request);

			response = mapper.readValue(deviceGpsLastJson, Ceiba2DeviceTerIdPojo.class);

		} catch (Exception e) {
			log.error("Error: ", e);
		}

		return response;
	}

	@Override
	public Ceiba2DevicesAlarmResponseDTO getDevicesAlarmInfo(String key, List<String> terids, List<String> types, String starttime, String endtime) {

		String url = null;
		Ceiba2DevicesAlarmRequestDTO request = null;
		Ceiba2DevicesAlarmResponseDTO response = null;
		Ceiba2DevicesAlarmResponseDTO responseDepurado = null;
		String deviceGpsLastJson = "";
		List<AlertaCeibaEntity> alertasGuardadas = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			responseDepurado = new Ceiba2DevicesAlarmResponseDTO();
			url = ceiba2ArmaUrlHelper.getUrlDevicesAlarmInfo();
			request = new Ceiba2DevicesAlarmRequestDTO();
			request.setKey(key);
			request.setTerid(terids);
			request.setType(types);
			request.setStarttime(starttime);
			request.setEndtime(endtime);
			deviceGpsLastJson = peticionHttpPost(url, request);

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
							for(AlertaCeibaEntity alerta : alertasGuardadas) {
								if(alerta.getAlarmid().equals(alarma.getAlarmid())) {
									existeAlerta = true;
									log.warn("ALERTA YA SE ENVIO PREVIAMENTE: " + alerta.getAlarmid());
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

	private static String peticionHttpGet(String urlParaVisitar) throws Exception {
		StringBuilder resultado = new StringBuilder();
		URL url = new URL(urlParaVisitar);
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
		String linea;
		while ((linea = rd.readLine()) != null) {
			resultado.append(linea);
		}
		rd.close();
		return resultado.toString();
	}

	private static String peticionHttpPost(String url, Object objectRequest) {
		RestTemplate restTemplate = new RestTemplate();
	    String  result = restTemplate.postForEntity(url, objectRequest, String.class).getBody();
		return result;
	}

}
