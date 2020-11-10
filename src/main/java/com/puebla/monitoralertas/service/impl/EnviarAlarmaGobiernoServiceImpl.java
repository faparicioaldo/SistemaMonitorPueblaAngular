package com.puebla.monitoralertas.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.common.FechasCommon;
import com.puebla.monitoralertas.config.GlobalSession;
import com.puebla.monitoralertas.constant.Constants;
import com.puebla.monitoralertas.dto.ChatMessage;
import com.puebla.monitoralertas.dto.ChatMessage.MessageType;
import com.puebla.monitoralertas.dto.SemoviResponseDTO;
import com.puebla.monitoralertas.dto.SemoviSendRequestDTO;
import com.puebla.monitoralertas.entity.AlertaSemoviEntity;
import com.puebla.monitoralertas.entity.DatosVehiculoEntity;
import com.puebla.monitoralertas.feign.client.SemoviSendFeignClient;
import com.puebla.monitoralertas.json.pojo.Ceiba2DeviceAlarmResponseDTO;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesAlarmResponseDTO;
import com.puebla.monitoralertas.json.pojo.Ceiba2KeyPojo;
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
	private ObjectMapper mapper;

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
		Set<String> alertasEncontradas = null;
		
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
				
				alertasEncontradas = new HashSet<String>();//guarda los ceibaId de la consulta de alertas actual 
				
				//Procesa alertas obtenidas de ceiba
				for(Ceiba2DeviceAlarmResponseDTO alerta : alertas.getData() ) {
					Boolean alertaYaRegistrada = false;
					
					//Si viene repetido en esta peticion se ignora
					if(alertasEncontradas.contains(alerta.getAlarmid())) {
						continue;
					}
					alertasEncontradas.add(alerta.getAlarmid());
					
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
					
					//Se utiliza para resaltar alertas no vistas en el front
					session.getAlertasNoVistas().add(alerta.getAlarmid());
					
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
		semoviResponse.setStatus(false);
		Optional<AlertaSemoviEntity> alertaDB = null;
		AlertaSemoviEntity alertaEncontradaBase = null;
		Optional<DatosVehiculoEntity> device = null;
		
		try {

			alertaDB = alertaSemoviRepository.findById(idAlerta);
			
			if(!alertaDB.isPresent()) {
				log.warn("Alerta no existe en la base de datos: " + idAlerta);
				semoviResponse.setMsg("Alerta no existe en base");
				return semoviResponse;
			}
			
			alertaEncontradaBase = alertaDB.get();
	
			device = datosVehiculoRepository.findById(alertaEncontradaBase.getIddispositivo());
			
			if(!device.isPresent()) {
				log.warn("Vehiculo no existe en la base de datos por lo que no se puede enviar alerta: " + idAlerta + " | vehice: " + alertaEncontradaBase.getIddispositivo());
				semoviResponse.setMsg("Vehiculo no existe en la base de datos por lo que no se puede enviar alerta");
				return semoviResponse;
			}
	
			if(device.get().getEstatus().equals(Constants.ESTATUS_VEHICULO_ELIMINADO)) {
				log.warn("Vehiculo estatus eliminado por lo que no se puede enviar alerta: " + idAlerta + " | vehice: " + alertaEncontradaBase.getIddispositivo());
				semoviResponse.setMsg("Vehiculo estatus eliminado por lo que no se puede enviar alerta");
				return semoviResponse;
			}
	
			datosAlertaSemovi.setId(alertaEncontradaBase.getIddispositivo());
			datosAlertaSemovi.setLongitude(alertaEncontradaBase.getLongitud());
			datosAlertaSemovi.setLatitude(alertaEncontradaBase.getLatitud());
			datosAlertaSemovi.setAddress(alertaEncontradaBase.getAddress());
			datosAlertaSemovi.setSpeed(alertaEncontradaBase.getSpeed());
			datosAlertaSemovi.setCourse(alertaEncontradaBase.getCourse());
			datosAlertaSemovi.setDate(fechasCommon.dateToString(alertaEncontradaBase.getCeibagpstime()));
			datosAlertaSemovi.setIgnition(IGNICION_NO_SE_APLICA_O_SE_DESCONOCE);
			datosAlertaSemovi.setPannicbutton(alertaEncontradaBase.getPanicbutton());//BOTON DE PANICO			
			
			String response = semoviSendFeignClient.send(datosAlertaSemovi);
			semoviResponse = mapper.readValue(response, SemoviResponseDTO.class);
			
			alertaEncontradaBase.setSemoviestatus(semoviResponse.getStatus().toString());
			alertaEncontradaBase.setSemovimensaje(semoviResponse.getMsg());
			alertaEncontradaBase.setSemovirespuesta(mapper.writeValueAsString(semoviResponse));
			log.info("Exito al enviar alerta a semovi: " + idAlerta);
		} catch (Exception e) {
			log.error("Fallo al enviar alerta a semovi: " + idAlerta, e);
			alertaEncontradaBase.setSemoviestatus(semoviResponse.getStatus().toString());
			alertaEncontradaBase.setSemovimensaje(semoviResponse.getMsg());			
		} finally {
			log.info("Guarda en bitacora envio de alerta a semovi: " + idAlerta);
			alertaSemoviRepository.save(alertaEncontradaBase);	
		}

		return semoviResponse;
	}
	
	public void descartarAlertaSemovi(Integer idAlerta) {

		log.info("DESCARTAR ALARMA: " + idAlerta);
		try {
			alertaSemoviRepository.updateAlarmaEstatusByAlarmaid(idAlerta,"DESCARTADA","DESCARTADA");
		}catch(Exception e) {
			log.error("Fallo al guardar cambios en la base de datos");
		}

	}
	
	private String formatFechaOnlyDay(Date fecha) {
		String fechaFormated = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		fechaFormated = formatter.format(fecha);
		return fechaFormated;
	}

}