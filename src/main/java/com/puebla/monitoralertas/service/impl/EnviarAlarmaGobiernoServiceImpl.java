package com.puebla.monitoralertas.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.common.FechasCommon;
import com.puebla.monitoralertas.dto.ChatMessage;
import com.puebla.monitoralertas.dto.ChatMessage.MessageType;
import com.puebla.monitoralertas.dto.SemoviResponseDTO;
import com.puebla.monitoralertas.dto.SemoviSendRequestDTO;
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

			List<String> vehiculosListos = datosVehiculoRepository.consultaListaVehiculosCompletos("DATOS_COMPLETOS");
			
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
			hayAlarmas = false;
		      ChatMessage mensaje = new ChatMessage();
		      mensaje.setContent("HOLAA");
		      mensaje.setType(MessageType.CHAT);
		      mensaje.setSender("HOLAA");

		      template.convertAndSend("/topic/public", mensaje);
		}	
	}
	
	
	public void enviarAlertaSemovi(Integer idAlerta) {
		log.info("-------------------------------");
		log.info("ENVIANDO ALERTA " + "" + " A SEMOVI... ");
		log.info("RESPUESTA SEMOVI: " + "");
		log.info("-------------------------------");

		SemoviSendRequestDTO datosAlertaSemovi = new SemoviSendRequestDTO();

		Optional<AlertaSemoviEntity> alertaDB = alertaSemoviRepository.findById(idAlerta);
		
		if(alertaDB.isPresent()) {
			datosAlertaSemovi.setId(alertaDB.get().getIddispositivo());
			datosAlertaSemovi.setLongitude(alertaDB.get().getLongitud());
			datosAlertaSemovi.setLatitude(alertaDB.get().getLatitud());
			datosAlertaSemovi.setAddress(alertaDB.get().getAddress());
			datosAlertaSemovi.setSpeed(alertaDB.get().getSpeed());
			datosAlertaSemovi.setCourse(alertaDB.get().getCourse());
			datosAlertaSemovi.setDate(fechasCommon.dateToString(alertaDB.get().getCeibagpstime()));
			datosAlertaSemovi.setIgnition(alertaDB.get().getIgnition());
			datosAlertaSemovi.setPannicbutton(alertaDB.get().getPanicbutton());//BOTON DE PANICO			
		}
		
		semoviSendFeignClient.send(datosAlertaSemovi);
	}
	
	public void enviarGPSs() {
		String key;

		try {
			
				/*
				 * Solicita key necesario para hacer peticiones a CEIBA 2
				 * */
				Ceiba2KeyPojo keyPojo = apiCeiba2.getCeibaToken();
				key = keyPojo.getData().getKey();
			
				log.info("INICIO: ENVIO DE GPS's A SEMOVI");

				List<String> vehiculosListos = datosVehiculoRepository.consultaListaVehiculosCompletos("DATOS_COMPLETOS");
				
				Ceiba2DevicesGpsLastResponsePojo gpss = apiCeiba2.getGPSVehicle(key, vehiculosListos);

				if(gpss == null || gpss.getErrorcode() == null || !gpss.getErrorcode().equals("200") || gpss.getData() == null || gpss.getData().size() <=0) {
					throw new Exception("No hay gps que enviar"); 
				}
				
				log.info("VEHICULOS CON DATOS DE GPS CORRECTAMENTE: " + gpss.getData().size());

				for(Ceiba2DeviceGpsLastPojo gps : gpss.getData()) {
					SemoviSendRequestDTO datosGps = new SemoviSendRequestDTO();

					datosGps.setId(gps.getTerid());
					datosGps.setLongitude(gps.getGpslng());
					datosGps.setLatitude(gps.getGpslat());
					datosGps.setAddress("OBTENER DE GOOGLE");
					datosGps.setSpeed(gps.getSpeed());
					datosGps.setCourse(gps.getDirection());
					datosGps.setDate(gps.getGpstime());
					datosGps.setIgnition(gps.getState());//buscaSiEstaOnline(onlineVehicles, datosTerid.getIdDispositivo())?"1":"2");/*1-Encencido; 2-Apagado; 3-No Aplica(Se Desconoce)*/
					datosGps.setPannicbutton("0");//GPS
					
					log.info("ENVIANDO GPS A SEMOVI: " + gps.getTerid());
					String responseSemovi = semoviSendFeignClient.send(datosGps);
					SemoviResponseDTO convertido = objectMapper.readValue(responseSemovi, SemoviResponseDTO.class);
					
					log.info("RESPUESTA DE SEMOVI AL ENVIAR GPS: " + convertido.getMsg());

//					if(session.isMostrarCadenasAlertas()) {
//						log.error("Cadena de GPS: ");
//						String jsonAlerta = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(datosGps);
//						log.error(jsonAlerta);
//					}
				}
				
				log.info("FIN: ENVIO DE GPS's A SEMOVI");			
		} catch (Exception e) {
			log.error("No se pudo enviar GPS's a SEMOVI: ", e);
		}
	}
	
	public void descartarAlertaSemovi(Integer idAlerta) {

		log.info("DESCARTAR ALARMA: " + idAlerta);
		alertaSemoviRepository.updateAlarmaEstatusByAlarmaid(idAlerta,"Descartada");

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