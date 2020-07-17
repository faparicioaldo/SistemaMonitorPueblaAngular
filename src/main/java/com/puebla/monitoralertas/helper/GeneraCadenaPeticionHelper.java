package com.puebla.monitoralertas.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	private List<String> teridsCorrectos = null; 

	/*ONLINE?*/ private Ceiba2DeviceTerIdPojo onlineVehicles = null;
	/*GPS's  */ private Map<String, Ceiba2DeviceGpsLastPojo> c2GPSsVehicles = null;
	/*VEHICLE*/ private HashMap<String, DatosVehiculoEntity> complementosDataVehicle = null; 
	/*VIDEO's*/ private HashMap<String, String> liveVideoVehicles = null;

	public void obtieneDatosNecesarios(List<String> terids, String key) {
		
		try {
			teridsCorrectos = new ArrayList<>();
			
			/*
			 * RECOLECCION DE DATOS PARA ENVIAR A SEMOVI
			 * */
			onlineVehicles = apiCeiba2.getEstatusVehicle(key, terids);
			c2GPSsVehicles = obtenGpsMap(apiCeiba2.getGPSVehicle(key, terids));
			complementosDataVehicle = new HashMap<>(); 
			liveVideoVehicles = new HashMap<>();

			for(String terid : terids) {
				DatosVehiculoEntity complementoDatosVehiculo =null;
				try {
					complementoDatosVehiculo = datosVehiculoService.obtenerDatosVehiculo(terid);
				}catch(Exception e) {
					log.error("Error al obtener datos de vehiculo para GPS de la base: " + e.getMessage());
				}
				if(complementoDatosVehiculo!=null && !complementoDatosVehiculo.getImei().trim().equals("")) {
					complementosDataVehicle.put(complementoDatosVehiculo.getIdDispositivo(), complementoDatosVehiculo);
					String c2LiveVehicle = apiCeiba2.getLiveVideo(terid);
					liveVideoVehicles.put(terid,c2LiveVehicle);
					teridsCorrectos.add(terid);
				}else {
					log.warn("NO SE ENCONTRO DATOS DE VEHICULO EN BASE DE DATOS, FAVOR DE REGISTRARLO: " + terid);
				}
			}
		}catch(Exception e) {
			log.error("No se pudo obtener datos de ceiba2 necesarios para enviar a semovi.");
		}
	}
	
	/**
	 *  GENERA CADENA PARA PETICION AL SOCKET DE GOBIERNO 
	 **/
	public List<SemoviRequestDTO> generaCadenaPeticion(String isAlertPanicButton) {
				
		List<SemoviRequestDTO> mensajesSemovi = null;		
		
		try {
			mensajesSemovi = new ArrayList<>();
	
			/*
			 * ARMADO DE DATOS PARA ENVIAR A SEMOVI
			 * */
			for(String terid : teridsCorrectos) {
				try {
					SemoviRequestDTO mensaje = new SemoviRequestDTO();
					mensaje.setImei(complementosDataVehicle.get(terid).getImei());
					mensaje.setLongitude(c2GPSsVehicles.get(terid).getGpslng());
					mensaje.setLatitude(c2GPSsVehicles.get(terid).getGpslat());
					mensaje.setAddress(null);
					mensaje.setSpeed(null);
					mensaje.setCourse(null);
					mensaje.setDate(formatFecha(new Date()));
					mensaje.setIgnition(buscaSiEstaOnline(onlineVehicles, terid)?"1":"2");/*1-Encencido; 2-Apagado; 3-No Aplica(Se Desconoce)*/
					mensaje.setFix("true");
					mensaje.setSat("0");
					mensaje.setPlate(complementosDataVehicle.get(terid).getPlate());
					mensaje.setVin(complementosDataVehicle.get(terid).getVin());
					mensaje.setEngine(complementosDataVehicle.get(terid).getEngine());
					mensaje.setYear(complementosDataVehicle.get(terid).getYear());
					mensaje.setColor(complementosDataVehicle.get(terid).getColor());
					mensaje.setRoute(complementosDataVehicle.get(terid).getRoute());
					mensaje.setRs(complementosDataVehicle.get(terid).getRs());
					mensaje.setEco(complementosDataVehicle.get(terid).getEco());
					mensaje.setBranch(complementosDataVehicle.get(terid).getBranch());
					mensaje.setSubbranch(complementosDataVehicle.get(terid).getSubbranch());
					mensaje.setUrl_camera(liveVideoVehicles.get(terid));
					mensaje.setPanic_button(isAlertPanicButton);
					
					mensajesSemovi.add(mensaje);
				}catch(Exception e) {
					log.error("Error al construir mensaje de GPS para semovi ID: " + terid + " , se omite y continua con el siguiente. --> Cause: "+ e.getCause()+ " --> Message: " + e.getMessage());
				}
			}
		}catch(Exception e) {
			log.error("No se pudo crear el mensaje que se envia a semovi: ", e);
		}
        return mensajesSemovi;
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
	
	private String formatFecha(Date fecha) {
		String fechaFormated = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		fechaFormated = formatter.format(fecha);
		return fechaFormated;
	}
}
