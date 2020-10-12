package com.puebla.monitoralertas.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.common.FechasCommon;
import com.puebla.monitoralertas.config.GlobalSession;
import com.puebla.monitoralertas.dto.ChatMessage;
import com.puebla.monitoralertas.dto.ChatMessage.MessageType;
import com.puebla.monitoralertas.dto.GpsCoordinatesDTO;
import com.puebla.monitoralertas.dto.SemoviResponseDTO;
import com.puebla.monitoralertas.dto.SemoviSendRequestDTO;
import com.puebla.monitoralertas.dto.SendGPSToSemoviErrorDTO;
import com.puebla.monitoralertas.dto.SendGPSToSemoviErrorResponseDTO;
import com.puebla.monitoralertas.entity.AlertaSemoviEntity;
import com.puebla.monitoralertas.feign.client.SemoviSendFeignClient;
import com.puebla.monitoralertas.json.pojo.Ceiba2DeviceAlarmResponseDTO;
import com.puebla.monitoralertas.json.pojo.Ceiba2DeviceGpsLastPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2DeviceTerIdPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesAlarmResponseDTO;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesGpsLastResponsePojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2KeyPojo;
import com.puebla.monitoralertas.json.pojo.DataPojo;
import com.puebla.monitoralertas.repository.AlertaSemoviRepository;
import com.puebla.monitoralertas.repository.DatosVehiculoRepository;
import com.puebla.monitoralertas.service.APISistemaVideoVigilanciaService;
import com.puebla.monitoralertas.service.EnviarAlarmaGobiernoService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EnviarAlarmaGobiernoServiceImpl implements EnviarAlarmaGobiernoService {

	private static final String IGNICION_NO_SE_APLICA_O_SE_DESCONOCE = "3";

	@Autowired
	private GlobalSession session;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private DatosVehiculoRepository datosVehiculoRepository;
	
	@Autowired
	private APISistemaVideoVigilanciaService apiCeiba2;

	@Autowired
	private FechasCommon fechasCommon;
	
	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private AlertaSemoviRepository alertaSemoviRepository;

	@Autowired
	private SemoviSendFeignClient semoviSendFeignClient;
		
	/**
	 * Se actualizan las alertas encontradas en ceiba con la base de datos 
	 * 
	 * */
	@Override
	public void actualizarConAlertasCeiba() {

		
		boolean hayAlarmas = false;
		String key;

		try {
		
			/*
			 * Solicita key necesario para hacer peticiones a CEIBA 2
			 * */
			Ceiba2KeyPojo keyPojo = apiCeiba2.getCeibaToken();
			key = keyPojo.getData().getKey();

			List<String> vehiculosListos = datosVehiculoRepository.consultaListaVehiculosCompletosByStatus("DATOS_COMPLETOS");
			
			List<String> alarmTypes = new ArrayList<>();
			alarmTypes.add("5"); //Considera alertas de Sensor 1
			alarmTypes.add("6"); //Considera alertas de Sensor 2
			alarmTypes.add("7"); //Considera alertas de Sensor 3
			alarmTypes.add("8"); //Considera alertas de Sensor 4
			alarmTypes.add("9"); //Considera alertas de Sensor 5
			alarmTypes.add("10");//Considera alertas de Sensor 6
			alarmTypes.add("11");//Considera alertas de Sensor 7
			alarmTypes.add("12");//Considera alertas de Sensor 8
			alarmTypes.add("13");//Considera alertas de Boton de Panico
			
			String starttime = formatFechaOnlyDay(new Date()) + " 00:00:00";
			String endtime = formatFechaOnlyDay(new Date()) + " 23:59:59";

			System.err.println("CONSULTA ALARMAS FECHA INICIO: " + starttime + " : FECHA FIN" + endtime );

			//Consulta en CEIBA2 si hay alertas de boton de panico:
			/*ALARMAS*/ Ceiba2DevicesAlarmResponseDTO alertas = apiCeiba2.getDevicesAlarmInfo(key, vehiculosListos, alarmTypes, starttime, endtime);

			//SE ENCONTRO ALERTAS DE PANICO??
			if(alertas != null 
					&& alertas.getErrorcode() != null && alertas.getErrorcode().equals(200)
					&& alertas.getData() != null && alertas.getData().size() > 0) {

				log.info("ALERTAS PANICO ENCONTRADAS: " + alertas.getData().size());

				//consulta id de alertas previamente registradas en la base de datos
				List<String> alertasRegistradasBase = alertaSemoviRepository.consultaListaIdAlertasCeiba();
				
				//Procesa alertas obtenidas de ceiba
				for(Ceiba2DeviceAlarmResponseDTO alerta : alertas.getData() ) {
					Boolean alertaYaRegistrada = false;
					
					//Busca si alerta ha sido registrada previamente
					for(String alertaRegistrada : alertasRegistradasBase) {
						if(alerta.getAlarmid().equals(alertaRegistrada)) {
							alertaYaRegistrada = true;
							break;
						}
					}

					//Si la alerta ha sido registrada previamente la ignora
					if(alertaYaRegistrada) {
						continue;
					}

					hayAlarmas = true;
					
					AlertaSemoviEntity alertaEnviada = new AlertaSemoviEntity();
					
					alertaEnviada.setIddispositivo(alerta.getTerid());							
					alertaEnviada.setSemoviestatus("ALERTA EN VALIDACION");
					alertaEnviada.setSemovimensaje("LA ALERTA SE ESTA VALIDANDO ANTES DE ENVIAR A SEMOVI");
					alertaEnviada.setSemovirespuesta("AUN NO SE ENVIA A SEMOVI");
					alertaEnviada.setLatitud(alerta.getGpslat());
					alertaEnviada.setLongitud(alerta.getGpslng());
					alertaEnviada.setAddress("buscar con api google");
					alertaEnviada.setSpeed(alerta.getSpeed());
					alertaEnviada.setCourse(alerta.getDirection());
					alertaEnviada.setFecharecepcionalerta(new Date());
					alertaEnviada.setIgnition(alerta.getState());//buscaSiEstaOnline(onlineVehicles, datosTerid.getIdDispositivo())?"1":"2");/*1-Encencido; 2-Apagado; 3-No Aplica(Se Desconoce)*/
					alertaEnviada.setPanicbutton("1");
					alertaEnviada.setCeibatime(fechasCommon.stringToDate(alerta.getTime()));
					alertaEnviada.setCeibagpstime(fechasCommon.stringToDate(alerta.getGpstime()));
					alertaEnviada.setCeibatype(alerta.getType());
					alertaEnviada.setCeibacontent(alerta.getContent());
					alertaEnviada.setCeibacmdtype(alerta.getCmdtype());
					alertaEnviada.setCeibaalarmid(alerta.getAlarmid());						
					
					alertaSemoviRepository.save(alertaEnviada);
					
				}
							
				log.info("Alerta de Panico guardada en tabla ALARMA Y tabla ALERTAS_SEMOVI");
				
			}else {
				log.info("NO SE ENCONTRO ALERTAS DE PANICO NUEVAS");
			}
			
		} catch (Exception e) {
			log.error("No se pudo enviar alertas DE PANICO A SEMOVI ", e);
		}
		
		/*
		 * Si hay alertas de panico
		 * Se alerta via websocket para mostrar en el monitor de alertas
		 *  
		 * */
		if(hayAlarmas) {
		      ChatMessage mensaje = new ChatMessage();
		      mensaje.setContent("Se encontro alertas nuevas");
		      mensaje.setType(MessageType.CHAT);
		      mensaje.setSender("Alertas");

		      template.convertAndSend("/topic/alert", mensaje);
		}	
	}
	
	
	public SemoviResponseDTO enviarAlertaSemovi(Integer idAlerta) {
		log.info("-------------------------------");
		log.info("ENVIANDO ALERTA " + "" + " A SEMOVI... ");
		log.info("RESPUESTA SEMOVI: " + "");
		log.info("-------------------------------");

		SemoviSendRequestDTO datosAlertaSemovi = new SemoviSendRequestDTO();
		SemoviResponseDTO semoviResponse = new SemoviResponseDTO(); 
		
		Optional<AlertaSemoviEntity> alertaDB = alertaSemoviRepository.findById(idAlerta);
		
		if(!alertaDB.isPresent()) {
			log.warn("Alerta no existe en la base de datos: " + idAlerta);
		}
		AlertaSemoviEntity alertaEncontradaBase = alertaDB.get();
		
		datosAlertaSemovi.setId(alertaEncontradaBase.getIddispositivo());
		datosAlertaSemovi.setLongitude(alertaEncontradaBase.getLongitud());
		datosAlertaSemovi.setLatitude(alertaEncontradaBase.getLatitud());
		datosAlertaSemovi.setAddress(alertaEncontradaBase.getAddress());
		datosAlertaSemovi.setSpeed(alertaEncontradaBase.getSpeed());
		datosAlertaSemovi.setCourse(alertaEncontradaBase.getCourse());
		datosAlertaSemovi.setDate(fechasCommon.dateToString(alertaEncontradaBase.getCeibagpstime()));
		datosAlertaSemovi.setIgnition(alertaEncontradaBase.getIgnition());
		datosAlertaSemovi.setPannicbutton(alertaEncontradaBase.getPanicbutton());//BOTON DE PANICO			
		
		try {
			String response = semoviSendFeignClient.send(datosAlertaSemovi);
			semoviResponse = objectMapper.readValue(response, SemoviResponseDTO.class);
			
			alertaEncontradaBase.setSemoviestatus(semoviResponse.getStatus().toString());
			alertaEncontradaBase.setSemovimensaje(semoviResponse.getMsg());
			alertaEncontradaBase.setSemovirespuesta(objectMapper.writeValueAsString(semoviResponse));
			log.info("Exito al enviar alerta a semovi: " + idAlerta);
		} catch (Exception e) {
			alertaEncontradaBase.setSemoviestatus(semoviResponse.getStatus().toString());
			alertaEncontradaBase.setSemovimensaje(semoviResponse.getMsg());
			
			log.error("Fallo al enviar alerta a semovi: " + idAlerta);
		} finally {
			log.info("Guarda en bitacora envio de alerta a semovi: " + idAlerta);
			alertaSemoviRepository.save(alertaEncontradaBase);	
		}

		return semoviResponse;
	}
	
	public void pruebaGPS() {
		String key = "zT908g2j9ngT5imODE6v4IPitzTOJgrWrqk5PwWPgI4%3D";		
		List<String> vehiculosListos = new ArrayList<>();
		vehiculosListos.add("009900AA83");
		vehiculosListos.add("009900AA0A");
		Ceiba2DevicesGpsLastResponsePojo gpss = apiCeiba2.getGPSVehicle(key, vehiculosListos);
		for(Ceiba2DeviceGpsLastPojo gps : gpss.getData()) {
			log.error("ter: " + gps.getTerid()+" - lat: "+ gps.getGpslat() + " - lng: " + gps.getGpslng());
		}
	}
	
	/**
	 * Envia gps de cada dispositivo registrado en ceiba que cumplan las siguientes caracteristicas:
	 * 
	 * <ul>
	 * 	<li>El operador ha registrado el total de datos requeridos en la base de datos</li>
	 * 	<li>Ceiba esta regresando datos de GPS (en ocaciones esto falla por red en el dispositivo, cobertura, etc.)</li>
	 * 	<li>Las coordenadas de gps del dispositivo son diferentes al envio anterior</li>
	 * 	<li>El sistema de webmaps esta funcionando correctamente</li>
	 * 	<li>Los datos del dispositivo son correctos en base a las validaciones de webmaps </li>
	 * </ul>
	 * 
	 * */
	public void enviarGPSs() {
		String key;
		Set<String> gpsNuevos = new HashSet<>();
		Set<String> gpsCambiaron = new HashSet<>();
		Set<String> gpsNoCambiaron = new HashSet<>();
		Map<String,Set<String>> mapGpsStatus = new HashMap<>();
		List<String> deviceWithoutGpsInCeiba = new ArrayList<>(); 
		List<SendGPSToSemoviErrorDTO> errorsToSendGps = new ArrayList<>();
		SendGPSToSemoviErrorResponseDTO response = new SendGPSToSemoviErrorResponseDTO(); 
		int gpssEnviadosOK = 0;
		int gpssEnviadosNOK = 0;
		
		try {
				/*
				 * Solicita key necesario para hacer peticiones a CEIBA 2
				 * */
				Ceiba2KeyPojo keyPojo = apiCeiba2.getCeibaToken();
				key = keyPojo.getData().getKey();
			
				log.info("INICIO: ENVIO DE GPS's A SEMOVI");

				List<String> vehiculosListos = datosVehiculoRepository.consultaListaVehiculosCompletosByStatus("DATOS_COMPLETOS");

				log.info("Vehiculos en condiciones registrados en base datos: " + vehiculosListos.size());
				
				if (vehiculosListos == null || vehiculosListos.size() <= 0) {
					log.warn("No se encontraron vehiculos en la base que cumplan las condiciones necesarias para enviar a semovi");
					throw new Exception("No se encontraron vehiculos en la base que cumplan las condiciones necesarias para enviar a semovi"); 
				}

				for(String vehiculo : vehiculosListos) {
					log.info("Vehiculos en condiciones: " + vehiculo);
				}

				Ceiba2DevicesGpsLastResponsePojo gpss = apiCeiba2.getGPSVehicle(key, vehiculosListos);

				if(gpss == null || gpss.getErrorcode() == null || gpss.getData() == null ) {
					log.warn("Ocurrio un problema al obtener gps's de ceiba");
				}
				
				if (!gpss.getErrorcode().equals("200")) {
					log.warn("Ocurrio un problema al obtener gps's de ceiba, respuesta ceiba: " + gpss.getErrorcode());
					throw new Exception("Ocurrio un problema al obtener gps's de ceiba, respuesta ceiba: " + gpss.getErrorcode()); 
				}
				
				if (gpss.getData().size() <=0) {
					log.warn("No hay gps que enviar: " + gpss.getData().size());
					throw new Exception("No hay gps que enviar" + gpss.getData().size()); 
				}

				log.info("Dispositivos no se encontro gps en ceiba: " + gpss.getData().size());
				for(Ceiba2DeviceGpsLastPojo gps : gpss.getData()) {
					if(vehiculosListos.contains(gps.getTerid())) {
						log.info(gps.getTerid());
						deviceWithoutGpsInCeiba.add(gps.getTerid());
					}
				}

				log.info("VEHICULOS CON DATOS DE GPS CORRECTAMENTE: " + gpss.getData().size());

				for(Ceiba2DeviceGpsLastPojo gps : gpss.getData()) {
					SemoviSendRequestDTO datosGps = new SemoviSendRequestDTO();
					GpsCoordinatesDTO newCoordinatesAndDate = null;
					
					if(!session.getMapLastGps().containsKey(gps.getTerid())) {
						//crea
						gpsNuevos.add(gps.getTerid());
						newCoordinatesAndDate = new GpsCoordinatesDTO(gps.getGpslat(), gps.getGpslng(), gps.getGpstime());
						session.getMapLastGps().put(gps.getTerid(), newCoordinatesAndDate);
					}else if(session.getMapLastGps().get(gps.getTerid()).getLatitude().equals(gps.getGpslat()) &&
							session.getMapLastGps().get(gps.getTerid()).getLongitude().equals(gps.getGpslng())) {
						//ignora
						gpsNoCambiaron.add(gps.getTerid());
						continue;
					}else{
						//actualiza
						gpsCambiaron.add(gps.getTerid());
						newCoordinatesAndDate = new GpsCoordinatesDTO(gps.getGpslat(), gps.getGpslng(), gps.getGpstime());
						session.getMapLastGps().replace(gps.getTerid(), newCoordinatesAndDate);							
					}										
															
					datosGps.setId(gps.getTerid());
					datosGps.setLongitude(gps.getGpslng());
					datosGps.setLatitude(gps.getGpslat());
					datosGps.setAddress("OBTENER DE GOOGLE");
					datosGps.setSpeed(gps.getSpeed());
					datosGps.setCourse(gps.getDirection());
					datosGps.setDate(gps.getGpstime());
//					datosGps.setIgnition(gps.getState());
					datosGps.setIgnition(IGNICION_NO_SE_APLICA_O_SE_DESCONOCE);//buscaSiEstaOnline(onlineVehicles, datosTerid.getIdDispositivo())?"1":"2");/*1-Encencido; 2-Apagado; 3-No Aplica(Se Desconoce)*/
					datosGps.setPannicbutton("0");//GPS
					
					log.info("ENVIANDO GPS A SEMOVI: " + gps.getTerid());
					String responseSemovi = semoviSendFeignClient.send(datosGps);
					SemoviResponseDTO responseSemoviDTO = objectMapper.readValue(responseSemovi, SemoviResponseDTO.class);
					
					if(responseSemoviDTO.getStatus()) {
						gpssEnviadosOK+=1;
					} else {
						gpssEnviadosNOK+=1;
						errorsToSendGps.add(
								new SendGPSToSemoviErrorDTO(
									gps.getTerid(),
									"Error Respuesta Semovi",
									responseSemoviDTO.getMsg(),
									session.getMapLastGps().get(gps.getTerid()).getLastGpsTime()
								)
						);
					}
					
					log.info("RESPUESTA DE SEMOVI AL ENVIAR GPS: STATUS -> " +responseSemoviDTO.getStatus() + " / MSG-> " + responseSemoviDTO.getMsg());

//					if(session.isMostrarCadenasAlertas()) {
//						log.error("Cadena de GPS: ");
						String jsonAlerta = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(datosGps);
						log.error(jsonAlerta);
//					}
				}
				
				log.info("GPS_NUEVOS: " + gpsNuevos);
				log.info("GPS_CAMBIARON: " + gpsCambiaron);
				log.info("GPS_NO_CAMBIARON: " + gpsNoCambiaron);
				
				mapGpsStatus.put("GPS_NUEVOS", gpsNuevos);
				mapGpsStatus.put("GPS_CAMBIARON", gpsCambiaron);
				mapGpsStatus.put("GPS_NO_CAMBIARON", gpsNoCambiaron);					

				session.setMapGpsStatus(mapGpsStatus);
				
				errorsToSendGps.add(
						new SendGPSToSemoviErrorDTO(
							"terid",
							"Error Respuesta Semovi",
							"test",
							"test"
						)
				);
				response.setErrors(errorsToSendGps);
				response.setNumEnviadosOK(gpssEnviadosOK);
				response.setNumEnviadosNOK(gpssEnviadosNOK);
			    template.convertAndSend("/topic/gps", response);
				
				log.info("FIN: ENVIO DE GPS's A SEMOVI");			
		} catch (Exception e) {
			log.error("No se pudo enviar GPS's a SEMOVI: ", e.getCause() , " - mensaje: " + e.getMessage());
		}
	}
	
	public void descartarAlertaSemovi(Integer idAlerta) {

		log.info("DESCARTAR ALARMA: " + idAlerta);
		try {
			alertaSemoviRepository.updateAlarmaEstatusByAlarmaid(idAlerta,"DESCARTADA","DESCARTADA");
		}catch(Exception e) {
			log.error("Fallo al guardar cambios en la base de datos");
		}

	}
	
	private boolean buscaSiEstaOnline(Ceiba2DeviceTerIdPojo onlineVehicles, String terid){
		boolean online = false;
		for(DataPojo onlineVehicle : onlineVehicles.getData()) {
			if(onlineVehicle.getTerid().equalsIgnoreCase(terid)) {
				online = true;
				break;
			}
		}
		return online;
	}	
	
	private String formatFechaOnlyDay(Date fecha) {
		String fechaFormated = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		fechaFormated = formatter.format(fecha);
		return fechaFormated;
	}

}