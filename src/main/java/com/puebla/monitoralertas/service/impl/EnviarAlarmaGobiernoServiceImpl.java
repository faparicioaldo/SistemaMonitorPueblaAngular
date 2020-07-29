package com.puebla.monitoralertas.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.common.FechasCommon;
import com.puebla.monitoralertas.config.GlobalSession;
import com.puebla.monitoralertas.dto.ChatMessage;
import com.puebla.monitoralertas.dto.ChatMessage.MessageType;
import com.puebla.monitoralertas.dto.DatosAlertaSemoviDTO;
import com.puebla.monitoralertas.dto.DatosAlertasTeridsDTO;
import com.puebla.monitoralertas.dto.SemoviRequestDTO;
import com.puebla.monitoralertas.dto.SemoviResponseDTO;
import com.puebla.monitoralertas.dto.SemoviResponseWrapperDTO;
import com.puebla.monitoralertas.entity.AlarmaEntity;
import com.puebla.monitoralertas.entity.AlertaSemoviEntity;
import com.puebla.monitoralertas.helper.GeneraCadenaPeticionHelper;
import com.puebla.monitoralertas.json.pojo.Ceiba2DeviceAlarmResponseDTO;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesAlarmResponseDTO;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2KeyPojo;
import com.puebla.monitoralertas.json.pojo.DataDevicePojo;
import com.puebla.monitoralertas.repository.AlarmaRepository;
import com.puebla.monitoralertas.repository.AlertaSemoviRepository;
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
	private DatosVehiculoRepository datosVehiculoRepository;
	
	@Autowired
	private APISistemaVideoVigilanciaService apiCeiba2;

	@Autowired
	private AlarmaRepository alarmaRepository;
	
	@Autowired
	private FechasCommon fechasCommon;
	
	@Autowired
	private ClienteSemoviPuebla semoviPuebla;
	
	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private AlertaSemoviRepository alertaSemoviRepository;

	private List<String> terids;

	private String key;

	private final boolean IS_ALERTA_PANICO = true;
	
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

		log.info("TOTAL VEHICULOS REGISTRADOS EN CEIBA: " + terids.size());

	}
	
	/**
	 * Envia alertas de boton 
	 * 
	 * */
	/**
	 *
	 */
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

			//Consulta en CEIBA2 si hay alertas de boton de panico:
			//Si se encuentran se envian a SEMOVI.
			//Si estan registradas en la base de datos no las envia a SEMOVI.
			/*ALARMAS*/ Ceiba2DevicesAlarmResponseDTO alertas = apiCeiba2.getDevicesAlarmInfo(key, terids, alarmTypes, starttime, endtime);

			//SE ENCONTRO ALERTAS DE PANICO??
			if(alertas != null && alertas.getData()!=null&&alertas.getData().size()>0) {
				log.info("ALERTAS PANICO ENCONTRADAS: " + alertas.getData().size());
					
				//GENERA LISTA DE ID_DISPOSITIVO (TERIDS) CON ALERTAS DISPONIBLES
				DatosAlertasTeridsDTO datosAlertasTeridsDto = new DatosAlertasTeridsDTO (); 
				List<DatosAlertaSemoviDTO> teridsYDatosAlarmas = new ArrayList<>();
				List<String> teridsAlertas = new ArrayList<>();
				if(alertas!=null && alertas.getData()!=null)
				for(Ceiba2DeviceAlarmResponseDTO alerta : alertas.getData()) {
					DatosAlertaSemoviDTO envaseTerId = new DatosAlertaSemoviDTO();
					envaseTerId.setIdDispositivo(alerta.getTerid());
					envaseTerId.setCeibaAlarmId(alerta.getAlarmid());
					envaseTerId.setCeibaCmdType(alerta.getCmdtype());
					envaseTerId.setCeibaContent(alerta.getContent());
					envaseTerId.setCeibaGpsTime(alerta.getGpstime());
					envaseTerId.setCeibaTime(alerta.getTime());
					envaseTerId.setCeibaType(alerta.getType());
					envaseTerId.setFechaRecepcionAlerta(fechasCommon.dateToString(new Date()));
					
					teridsYDatosAlarmas.add(envaseTerId);
					teridsAlertas.add(alerta.getTerid());
				}
				datosAlertasTeridsDto.setDatosAlertas(teridsYDatosAlarmas);
				datosAlertasTeridsDto.setTeridsList(teridsAlertas);
				
				//SI HAY ALERTAS DISPONIBLES LAS PROCESA PARA ENVIAR A SEMOVI
				if(teridsYDatosAlarmas!=null && teridsYDatosAlarmas.size()>0)
					if(alertas != null && alertas.getData() != null&& alertas.getData().size() > 0) {

						//OBTIENE DATOS NECESARIOS PARA ALERTAS
						generaCadenaPeticion.obtieneDatosNecesarios(datosAlertasTeridsDto, key);
						//GENERA CADENAS DE ALERTAS PARA ENVIAR A SEMOVI
						List<DatosAlertaSemoviDTO> alertasBtnPanico = generaCadenaPeticion.generaCadenasSemovi("true");
										
						//ENVIA CADENAS DE ALERTAS A SEMOVI
						for(DatosAlertaSemoviDTO datosAlerta : alertasBtnPanico){
							hayAlarmas = true;
							SemoviResponseWrapperDTO semoviResponse = semoviPuebla.enviarMensajeSemovi(datosAlerta.getDatosSemovi());
							log.info("-------------------------------");
							log.info("ENVIANDO ALERTA " + datosAlerta.getCeibaAlarmId() + " A SEMOVI... ");
							log.info("RESPUESTA SEMOVI: " + semoviResponse.getSemoviResponse().getMsg());
							log.info("-------------------------------");

							AlarmaEntity alarmaEntity = new AlarmaEntity();
							alarmaEntity.setEstatus(semoviResponse.getSemoviResponse().getMsg());
							alarmaEntity.setEconomico(datosAlerta.getDatosSemovi().getEco());
							alarmaEntity.setImei(datosAlerta.getDatosSemovi().getImei());
							alarmaEntity.setAlarma(datosAlerta.getDatosSemovi().getPanic_button());
							alarmaEntity.setPlaca(datosAlerta.getDatosSemovi().getPlate());
							alarmaEntity.setFecha(fechasCommon.stringToDate(datosAlerta.getCeibaGpsTime()));
							alarmaRepository.save(alarmaEntity);
							
							AlertaSemoviEntity alertaEnviada = new AlertaSemoviEntity();
							alertaEnviada.setIddispositivo(datosAlerta.getIdDispositivo());							
							alertaEnviada.setSemoviestatus(semoviResponse.getSemoviResponse().getStatus());
							alertaEnviada.setSemovimensaje(semoviResponse.getSemoviResponse().getMsg());
							alertaEnviada.setSemovirespuesta(semoviResponse.getSemoviResponseJson());
							alertaEnviada.setLatitud(datosAlerta.getDatosSemovi().getLatitude());
							alertaEnviada.setLongitud(datosAlerta.getDatosSemovi().getLongitude());
							alertaEnviada.setFecharecepcionalerta(fechasCommon.stringToDate(datosAlerta.getFechaRecepcionAlerta()));
							alertaEnviada.setCeibatime(fechasCommon.stringToDate(datosAlerta.getCeibaTime()));
							alertaEnviada.setCeibagpstime(fechasCommon.stringToDate(datosAlerta.getCeibaGpsTime()));
							alertaEnviada.setCeibatype(datosAlerta.getCeibaType());
							alertaEnviada.setCeibacontent(datosAlerta.getCeibaContent());
							alertaEnviada.setCeibacmdtype(datosAlerta.getCeibaCmdType());
							alertaEnviada.setCeibaalarmid(datosAlerta.getCeibaAlarmId());						
							
							alertaSemoviRepository.save(alertaEnviada);
							
							log.info("Alerta de Panico guardada en tabla ALARMA Y tabla ALERTAS_SEMOVI");
						}
					}
				
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

				//GENERA LISTA DE ID_DISPOSITIVO (TERIDS) CON ALERTAS DISPONIBLES
				DatosAlertasTeridsDTO datosAlertasTeridsDto = new DatosAlertasTeridsDTO (); 
				List<DatosAlertaSemoviDTO> teridsAlarmas = new ArrayList<>();

				Date fechaActual = new Date();
				for(String terid : terids) {
					DatosAlertaSemoviDTO envaseTerId = new DatosAlertaSemoviDTO();
					envaseTerId.setIdDispositivo(terid);
					envaseTerId.setCeibaGpsTime(fechasCommon.dateToString(fechaActual));
					envaseTerId.setFechaRecepcionAlerta(fechasCommon.dateToString(new Date()));
					
					teridsAlarmas.add(envaseTerId);
				}
				datosAlertasTeridsDto.setDatosAlertas(teridsAlarmas);
				datosAlertasTeridsDto.setTeridsList(terids);
				
				generaCadenaPeticion.obtieneDatosNecesarios(datosAlertasTeridsDto, key);
				List<DatosAlertaSemoviDTO> cadenasGps = generaCadenaPeticion.generaCadenasSemovi("false");

				if(cadenasGps!=null)
					log.info("VEHICULOS CON DATOS DE GPS CORRECTAMENTE: " + cadenasGps.size());
				
				for(DatosAlertaSemoviDTO alerta : cadenasGps){
					log.info("ENVIANDO GPS A SEMOVI: " + alerta.getDatosSemovi().getImei());
					SemoviResponseWrapperDTO respuesta = semoviPuebla.enviarMensajeSemovi(alerta.getDatosSemovi());
					
					if(session.isMostrarCadenasAlertas()) {
						log.error("Cadena de GPS: ");
						ObjectMapper mapper = new ObjectMapper();
						String jsonAlerta = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(alerta);
						log.error(jsonAlerta);
					}
					
					log.info("RESPUESTA DE SEMOVI AL ENVIAR GPS: " + respuesta.getSemoviResponse().getMsg());
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