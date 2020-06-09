package com.stv.quartzdemo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stv.quartzdemo.config.GlobanSession;
import com.stv.quartzdemo.dto.ChatMessage;
import com.stv.quartzdemo.dto.ChatMessage.MessageType;
import com.stv.quartzdemo.dto.SemoviRequestDTO;
import com.stv.quartzdemo.dto.SemoviResponseDTO;
import com.stv.quartzdemo.entity.AlarmaEntity;
import com.stv.quartzdemo.helper.ClienteSemoviPuebla;
import com.stv.quartzdemo.helper.GeneraCadenaPeticionHelper;
import com.stv.quartzdemo.json.pojo.Ceiba2DeviceAlarmResponseDTO;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesAlarmResponseDTO;
import com.stv.quartzdemo.json.pojo.Ceiba2DevicesPojo;
import com.stv.quartzdemo.json.pojo.Ceiba2KeyPojo;
import com.stv.quartzdemo.json.pojo.DataDevicePojo;
import com.stv.quartzdemo.repository.AlarmaRepository;
import com.stv.quartzdemo.repository.DatosVehiculoRepository;
import com.stv.quartzdemo.service.APISistemaVideoVigilanciaService;
import com.stv.quartzdemo.service.EnviarAlarmaGobiernoService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class EnviarAlarmaGobiernoServiceImpl implements EnviarAlarmaGobiernoService {

	@Autowired
	private GlobanSession session;

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

	private String formatFechaOnlyDay(Date fecha) {
		String fechaFormated = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		fechaFormated = formatter.format(fecha);
		return fechaFormated;
	}

	@Override
	public void enviarAlarmaGobierno() {

		String key = null;
		List<String> terids = null; 
		boolean hayAlarmas = false;
		
		try {
			/*
			 * Solicita key necesario para hacer peticiones a CEIBA 2
			 * */
			Ceiba2KeyPojo keyPojo = apiCeiba2.login();
			key = keyPojo.getData().getKey();
			
			/*
			 * Obtiene lista de vehiculos del CEIBA 2
			 * */
			Ceiba2DevicesPojo vehicles = apiCeiba2.getAllVehicles(key);
			terids = obtenVehicleList(vehicles);

			log.info("LISTA VEHICULOS CEIBA: " + terids);
			
			List<String> alarmTypes = new ArrayList<>();
			alarmTypes.add("5");//Solo alarmas de Boton de Panico
			alarmTypes.add("13");//Solo alarmas de Boton de Panico

			/*
			 * La busqueda de alertas de panico se realiza desde la ultima alerta registrada
			 * o en caso de ser la primera consulta o las variables en memoria se perdieron 
			 * se realiza desde la fecha actual
			 * */
//			String fechaUltimaConsulta = session.getFechaUltimaConsultaAlarma();
//			if(fechaUltimaConsulta==null || fechaUltimaConsulta.length()<10) {
//				fechaUltimaConsulta = formatFecha(new Date());
//			}			
//			String starttime = fechaUltimaConsulta;
//			String endtime = formatFecha(new Date());
			
			String starttime = formatFechaOnlyDay(new Date()) + " 00:00:00";
			String endtime = formatFechaOnlyDay(new Date()) + " 23:59:59";
						

			System.err.println("CONSULTA ALARMAS FECHA INICIO: " + starttime + " : FECHA FIN" + endtime );
			/*
			 * Cada vez que se ejecute el scheduler se revisa en CEIBA 2 si hay alertas de boton de panico
			 * Si se encuentran se envian a SEMOVI 
			 * */
			/*ALARMAS*/ Ceiba2DevicesAlarmResponseDTO alarmas = apiCeiba2.getDevicesAlarmInfo(key, terids, alarmTypes, starttime, endtime);

			if(alarmas != null && alarmas.getData()!=null&&alarmas.getData().size()>0) {
	//			session.setFechaUltimaConsultaAlarma(formatFecha(new Date()));
	
	//			if(alarmas!=null && alarmas.getData()!=null && alarmas.getData().size()>0 
	//					&& alarmas.getData().get(0)!=null && !alarmas.getData().get(0).getAlarmid().trim().equals("") 
	//					&& alarmas.getData().get(0).getAlarmid() != null 
	//					&& alarmas.getData().get(0).getAlarmid().equals(session.getUltimaAlarmaId())) {
	//				log.info("ALARMAS IGUALES SE INGONAN");
	//				throw new Exception("Se ignora alarma pues esta repetida: alramid: " + session.getUltimaAlarmaId());
	//			}
	
				System.out.println("ALERTAS PANICO: " + alarmas.toString());
				List<String> teridsAlarmas = new ArrayList<>();
				if(alarmas!=null && alarmas.getData()!=null)
				for(Ceiba2DeviceAlarmResponseDTO alertaID : alarmas.getData()) {
					teridsAlarmas.add(alertaID.getTerid());
				}
	//			String fechaUltimaAlerta = alarmas.getData().get(alarmas.getData().size()).getGpstime();
	//			String fechaUltimaAlerta = formatFecha(new Date());
	//			session.setFechaUltimaConsultaAlarma(fechaUltimaAlerta);
				if(teridsAlarmas!=null && teridsAlarmas.size()>0)
					if(alarmas != null && alarmas.getData() != null&& alarmas.getData().size() > 0) {

						List<SemoviRequestDTO> alertasBtnPanico = generaCadenaPeticion.generaCadenaPeticion(teridsAlarmas, key, "true");
										
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
				
	//			if(alarmas!=null && alarmas.getData()!=null && alarmas.getData().size()>0 && alarmas.getData().get(0)!=null && !alarmas.getData().get(0).getAlarmid().trim().equals("") && alarmas.getData().get(0).getAlarmid() != null) {
	//				session.setUltimaAlarmaId(alarmas.getData().get(0).getAlarmid());
	//			}
			}else {
				log.info("No se encontro alarmas");
			}
			
		} catch (Exception e) {
			log.error("Error al enviar alertas de panico ", e);
//			throw new ConsultaBuroException("Error al crear cadena de peticion");
		}
		
		try {
			/*
			 * Cuando se cumplan N (CiclosContadorGPS) veces que se ejecuta el scheduler 
			 * Se realiza en envio de mensajes de todos los vehiculos a SEMOVI 
			 * */
			
			session.setContadorEnviarGPSs(session.getContadorEnviarGPSs() + 1);
			if(session.getContadorEnviarGPSs().equals(session.getCiclosContadorGPS())) {
				log.info("ENVIO DE GPS A SEMOVI");

				session.setContadorEnviarGPSs(0);
				List<SemoviRequestDTO> alertasBtnPanico = generaCadenaPeticion.generaCadenaPeticion(terids, key, "false");
				
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
			}
			
		} catch (Exception e) {
			log.error("Error al enviar geolocacionzaciones ", e);
//			throw new ConsultaBuroException("Error al crear cadena de peticion");
		}
		
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
	
	private List<String> obtenVehicleList(Ceiba2DevicesPojo vehicles){
		List<String> deviceIdList = new ArrayList<>();
		for(DataDevicePojo vehicle : vehicles.getData()) {
			deviceIdList.add(vehicle.getDeviceid());
		}
		return deviceIdList;	
	}
	
	private String formatFecha(Date fecha) {
		String fechaFormated = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		fechaFormated = formatter.format(fecha);
		return fechaFormated;
	}
}
