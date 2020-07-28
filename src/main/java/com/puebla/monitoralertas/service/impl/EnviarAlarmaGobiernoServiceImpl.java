package com.puebla.monitoralertas.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.config.GlobalSession;
import com.puebla.monitoralertas.dto.ChatMessage;
import com.puebla.monitoralertas.dto.ChatMessage.MessageType;
import com.puebla.monitoralertas.dto.SemoviRequestDTO;
import com.puebla.monitoralertas.dto.SemoviResponseDTO;
import com.puebla.monitoralertas.entity.AlarmaEntity;
import com.puebla.monitoralertas.helper.GeneraCadenaPeticionHelper;
import com.puebla.monitoralertas.json.pojo.Ceiba2DeviceAlarmResponseDTO;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesAlarmResponseDTO;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2KeyPojo;
import com.puebla.monitoralertas.json.pojo.DataDevicePojo;
import com.puebla.monitoralertas.repository.AlarmaRepository;
import com.puebla.monitoralertas.repository.DatosVehiculoRepository;
import com.puebla.monitoralertas.rest.client.ClienteSemoviPuebla;
import com.puebla.monitoralertas.service.APISistemaVideoVigilanciaService;
import com.puebla.monitoralertas.service.EnviarAlarmaGobiernoService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EnviarAlarmaGobiernoServiceImpl implements EnviarAlarmaGobiernoService {

	@Autowired
	private GlobalSession session;

	@Autowired
	private GeneraCadenaPeticionHelper generaCadenaPeticion;

	@Autowired
	DatosVehiculoRepository datosVehiculoRepository;
	
	@Autowired
	private APISistemaVideoVigilanciaService apiCeiba2;

	@Autowired
	AlarmaRepository alarmaRepository;
	
	@Autowired
	private ClienteSemoviPuebla semoviPuebla;
	
	@Autowired
	private SimpMessagingTemplate template;

	private List<String> terids;

	private String key;

	/**
	 * Consulta la lista de vehiculos de CEIBA2
	 * Tambien consulta llave necesaria para consumir el API de CEIBA2
	 * */
	public void obtenerListaVehiculosCeiba2() {
		/*
		 * Solicita key necesario para hacer peticiones a CEIBA 2
		 * */
		Ceiba2KeyPojo keyPojo = apiCeiba2.getCeibaToken();
		key = keyPojo.getData().getKey();
		
		/*
		 * Obtiene lista de vehiculos del CEIBA 2
		 * */
		Ceiba2DevicesPojo vehicles = apiCeiba2.getAllVehicles(key);
		terids = obtenVehicleList(vehicles);

		log.info("LISTA VEHICULOS CEIBA: " + terids);

	}
	
	/**
	 * Envia alertas de boton 
	 * 
	 * */
	@Override
	public void enviarAlarmaGobierno() {

		boolean hayAlarmas = false;
		
		try {
			
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

			/*
			 * Consulta en CEIBA2 si hay alertas de boton de panico:
			 * <ul>
			 * 	<li>Si se encuentran se envian a SEMOVI.</li>
			 * 	<li>Si estan registradas en la base de datos no las envia a SEMOVI.</li>
			 * </ul>
			 * */
			/*ALARMAS*/ Ceiba2DevicesAlarmResponseDTO alarmas = apiCeiba2.getDevicesAlarmInfo(key, terids, alarmTypes, starttime, endtime);

			if(alarmas != null && alarmas.getData()!=null&&alarmas.getData().size()>0) {
	
				System.out.println("ALERTAS PANICO: " + alarmas.toString());
				List<String> teridsAlarmas = new ArrayList<>();
				if(alarmas!=null && alarmas.getData()!=null)
				for(Ceiba2DeviceAlarmResponseDTO alertaID : alarmas.getData()) {
					teridsAlarmas.add(alertaID.getTerid());
				}
				if(teridsAlarmas!=null && teridsAlarmas.size()>0)
					if(alarmas != null && alarmas.getData() != null&& alarmas.getData().size() > 0) {

						generaCadenaPeticion.obtieneDatosNecesarios(teridsAlarmas, key);
						List<SemoviRequestDTO> alertasBtnPanico = generaCadenaPeticion.generaCadenaPeticion("true");
										
						for(SemoviRequestDTO alerta : alertasBtnPanico){
							hayAlarmas = true;
							log.info("ALERTAS PANICO ENCONTRADAS: " + alerta.toString());
							SemoviResponseDTO semoviResponse = semoviPuebla.enviarMensajeSemovi(alerta);
							log.info("-------------------------------");
							log.info("ALERTA ENVIADA A SEMOVI: " + alerta.getUrl_camera());
							log.info("RESPUESTA SEMOVI: " + semoviResponse.getMsg());
							log.info("-------------------------------");

							System.err.println("-------------------------------");
							System.err.println("ALERTA ENVIADA A SEMOVI: " + alerta.getUrl_camera());
							System.err.println("RESPUESTA SEMOVI: " + semoviResponse.getMsg());
							System.err.println("-------------------------------");

							AlarmaEntity alarmaEntity = new AlarmaEntity();
							alarmaEntity.setEstatus(semoviResponse.getMsg());
							alarmaEntity.setEconomico(alerta.getEco());
							alarmaEntity.setImei(alerta.getImei());
							alarmaEntity.setAlarma(alerta.getPanic_button());
							alarmaEntity.setPlaca(alerta.getPlate());
							alarmaRepository.save(alarmaEntity);
							log.info("Alerta de Panico guardada en tabla ALARMA");
						}
					}
				
			}else {
				log.info("No se encontro alarmas");
			}
			
		} catch (Exception e) {
			log.error("No se pudo enviar alertas a SEMOVI ", e);
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
		
//		try {
//			/* DECODIFICA CADENA DE GOBIERNO */
//			RespuestaGobiernoDTO respuestaDTO = generaCadenaPeticion.decodificaCadenaResultado(resultado);
//
//			
//			/* ACTUALIZA ESTATUS Y FOLIO EN TABLA ALARMA */
//			if(!session.getCadenaRespuesta().equals("No se obtuvo respuesta de gobierno."))
//			alarmaRepository.updateAlarmaEstatusByAlarmaid(idAlarma.getIdAlarma(), "Enviada");
//
//			/* GUARDA FOLIO OTORGADO POR GOBIERNO */
//			FolioAlarmaEntity folioAlarma = new FolioAlarmaEntity();
//			folioAlarma.setFolio(respuestaDTO.getFolioCAD());
//			folioAlarma.setIpdispositivo(respuestaDTO.getIdentificadorCampoTres());
//			folioAlarma.setFechagenerado(new Date());
//			folioAlarma.setEstatus("Generado");
//			folioAlarmaRepository.save(folioAlarma);
//		} catch (Exception e) {
//			log.error("Error al decodificar respuesta de gobierno o al guardar respuesta de buro! ", e);
//			throw new ConsultaBuroException(
//					"Error al decodificar respuesta de gobierno o al guardar respuesta de buro!");
//		}
	}
	
	public void enviarGPSs() {
		try {
				log.info("INICIO: ENVIO DE GPS's A SEMOVI");

//				List<SemoviRequestDTO> alertasBtnPanico = generaCadenaPeticion.generaCadenaPeticion(terids, key, "false");
				generaCadenaPeticion.obtieneDatosNecesarios(terids, key);
				List<SemoviRequestDTO> alertasBtnPanico = generaCadenaPeticion.generaCadenaPeticion("true");

				if(alertasBtnPanico!=null)
					log.info("#GPS encontrados: " + alertasBtnPanico.size());
				
				for(SemoviRequestDTO alerta : alertasBtnPanico){
					System.err.println("GPS ENVIADA A SEMOVI: " + alerta.toString());
					log.info("Enviando gps semovi: " + alerta.getImei());
					SemoviResponseDTO respuesta = semoviPuebla.enviarMensajeSemovi(alerta);
					
					if(session.isMostrarCadenasAlertas()) {
						log.error("Cadena de GPS: ");
						ObjectMapper mapper = new ObjectMapper();
						String jsonAlerta = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(alerta);
						log.error(jsonAlerta);
					}
					
					log.info("RESPUESTA DE SEMOVI AL ENVIAR GPS: " + respuesta.getMsg());
				}				
				
				log.info("FIN: ENVIO DE GPS's A SEMOVI");			
		} catch (Exception e) {
			log.error("No se pudo enviar GPS's a SEMOVI: ", e);
		}
	}
	
	private String formatFechaOnlyDay(Date fecha) {
		String fechaFormated = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		fechaFormated = formatter.format(fecha);
		return fechaFormated;
	}

	private List<String> obtenVehicleList(Ceiba2DevicesPojo vehicles){
		List<String> deviceIdList = new ArrayList<>();
		for(DataDevicePojo vehicle : vehicles.getData()) {
			deviceIdList.add(vehicle.getDeviceid());
		}
		return deviceIdList;	
	}	
}