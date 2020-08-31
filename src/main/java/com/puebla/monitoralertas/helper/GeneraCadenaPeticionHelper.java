package com.puebla.monitoralertas.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.puebla.monitoralertas.dto.DatosAlertaSemoviDTO;
import com.puebla.monitoralertas.dto.DatosAlertasTeridsDTO;
import com.puebla.monitoralertas.dto.SemoviRequestDTO;
import com.puebla.monitoralertas.entity.DatosVehiculoEntity;
import com.puebla.monitoralertas.json.pojo.Ceiba2DeviceGpsLastPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2DeviceTerIdPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesGpsLastResponsePojo;
import com.puebla.monitoralertas.json.pojo.DataPojo;
import com.puebla.monitoralertas.service.APISistemaVideoVigilanciaService;
import com.puebla.monitoralertas.service.DatosVehiculoService;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class GeneraCadenaPeticionHelper {

	@Autowired
	private DatosVehiculoService datosVehiculoService;

	@Autowired
	private APISistemaVideoVigilanciaService apiCeiba2;

	private List<DatosAlertaSemoviDTO> datosTeridsCorrectos = null; 

	/*ONLINE?*/ private Ceiba2DeviceTerIdPojo onlineVehicles = null;
	/*GPS's  */ private Map<String, Ceiba2DeviceGpsLastPojo> c2GPSsVehicles = null;
	/*VEHICLE*/ private HashMap<String, DatosVehiculoEntity> complementosDataVehicle = null; 
	/*VIDEO's*/ private HashMap<String, String> liveVideoVehicles = null;

	public void obtieneDatosNecesarios(DatosAlertasTeridsDTO datosAlertasTeridsDto, String key) {
		
		try {
			datosTeridsCorrectos = new ArrayList<>();
			
			/*
			 * RECOLECCION DE DATOS PARA ENVIAR A SEMOVI
			 * */
			onlineVehicles = apiCeiba2.getEstatusVehicle(key, datosAlertasTeridsDto.getTeridsList());
			c2GPSsVehicles = obtenGpsMap(apiCeiba2.getGPSVehicle(key, datosAlertasTeridsDto.getTeridsList()));
			complementosDataVehicle = new HashMap<>(); 
			liveVideoVehicles = new HashMap<>();

			int vehiculosNoRegistradosMonitor = 0;
			
			for(DatosAlertaSemoviDTO datosAlertaTeridDto : datosAlertasTeridsDto.getDatosAlertas()) {
				DatosVehiculoEntity complementoDatosVehiculo =null;
				try {
					complementoDatosVehiculo = datosVehiculoService.obtenerDatosVehiculo(datosAlertaTeridDto.getIdDispositivo());
				}catch(Exception e) {
					log.error("Error al obtener datos de vehiculo para GPS de la base: " + e.getMessage());
				}
				if(complementoDatosVehiculo!=null && !complementoDatosVehiculo.getImei().trim().equals("")) {
					complementosDataVehicle.put(complementoDatosVehiculo.getIddispositivo(), complementoDatosVehiculo);
					String c2LiveVehicle = apiCeiba2.getLiveVideo(datosAlertaTeridDto.getIdDispositivo());
					liveVideoVehicles.put(datosAlertaTeridDto.getIdDispositivo(),c2LiveVehicle);
					datosTeridsCorrectos.add(datosAlertaTeridDto);
				}else {
					vehiculosNoRegistradosMonitor ++;
				}
			}
			log.warn("SE ENCONTRO ==> " + vehiculosNoRegistradosMonitor + " <== REGISTRADOS EN CEIBA PERO AUN NO REGISTRADOS EN MONITOR, FAVOR DE REGISTRARLOS");

		}catch(Exception e) {
			log.error("No se pudo obtener datos de ceiba2 necesarios para enviar a semovi.");
		}
	}
	
	/**
	 *  GENERA CADENA PARA PETICION AL SOCKET DE GOBIERNO SEMOVI
	 **/
	public List<DatosAlertaSemoviDTO> generaCadenasSemovi(String isAlertPanicButton) {
				
		List<DatosAlertaSemoviDTO> datosMensajesSemovi = null;		
		
		try {
			datosMensajesSemovi = new ArrayList<>();
	
			int dispositivosErrorCadena = 0;
			/*
			 * ARMADO DE DATOS PARA ENVIAR A SEMOVI
			 * */
			for(DatosAlertaSemoviDTO datosTerid : datosTeridsCorrectos) {
				try {
					SemoviRequestDTO mensaje = new SemoviRequestDTO();
					mensaje.setImei(complementosDataVehicle.get(datosTerid.getIdDispositivo()).getImei());
					mensaje.setLongitude(c2GPSsVehicles.get(datosTerid.getIdDispositivo()).getGpslng());
					mensaje.setLatitude(c2GPSsVehicles.get(datosTerid.getIdDispositivo()).getGpslat());
					mensaje.setAddress(null);
					mensaje.setSpeed(null);
					mensaje.setCourse(null);
					mensaje.setDate(datosTerid.getCeibaGpsTime());
					mensaje.setIgnition(buscaSiEstaOnline(onlineVehicles, datosTerid.getIdDispositivo())?"1":"2");/*1-Encencido; 2-Apagado; 3-No Aplica(Se Desconoce)*/
					mensaje.setFix("true");
					mensaje.setSat("0");
					mensaje.setPlate(complementosDataVehicle.get(datosTerid.getIdDispositivo()).getPlate());
					mensaje.setVin(complementosDataVehicle.get(datosTerid.getIdDispositivo()).getVin());
					mensaje.setEngine(complementosDataVehicle.get(datosTerid.getIdDispositivo()).getEngine());
					mensaje.setYear(complementosDataVehicle.get(datosTerid.getIdDispositivo()).getYear());
					mensaje.setColor(complementosDataVehicle.get(datosTerid.getIdDispositivo()).getColor());
					mensaje.setRoute(complementosDataVehicle.get(datosTerid.getIdDispositivo()).getRoute());
					mensaje.setRs(complementosDataVehicle.get(datosTerid.getIdDispositivo()).getRs());
					mensaje.setEco(complementosDataVehicle.get(datosTerid.getIdDispositivo()).getEco());
					mensaje.setBranch(complementosDataVehicle.get(datosTerid.getIdDispositivo()).getBranch());
					mensaje.setSubbranch(complementosDataVehicle.get(datosTerid.getIdDispositivo()).getSubbranch());
					mensaje.setUrl_camera(liveVideoVehicles.get(datosTerid.getIdDispositivo()));
					mensaje.setPanic_button(isAlertPanicButton);
					
					datosTerid.setDatosSemovi(mensaje);
					datosMensajesSemovi.add(datosTerid);
				}catch(Exception e) {
					dispositivosErrorCadena ++;
					log.error("Error al construir mensaje de GPS para semovi ID: " + datosTerid.getIdDispositivo() + " , se omite y continua con el siguiente. --> Cause: "+ e.getCause()+ " --> Message: " + e.getMessage());
				}				
			}
			
			log.info("VEHICULOS CON PROBLEMA PARA CONSTRUIR CADENA: " + dispositivosErrorCadena + " --> REVISAR DATOS EN CEIBA, PRINCIPALMENTE GPS (LATITUD Y LONGITUD)");
		}catch(Exception e) {
			log.error("No se pudo crear el mensaje que se envia a semovi: ", e);
		}
        return datosMensajesSemovi;
	}

	private Map<String, Ceiba2DeviceGpsLastPojo> obtenGpsMap(Ceiba2DevicesGpsLastResponsePojo c2GPSVehicle){
		HashMap<String,Ceiba2DeviceGpsLastPojo> gpsMapList = new HashMap<>();
		for(Ceiba2DeviceGpsLastPojo gps : c2GPSVehicle.getData()) {
			gpsMapList.put(gps.getTerid(), gps);
		}
		return gpsMapList;
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
}
