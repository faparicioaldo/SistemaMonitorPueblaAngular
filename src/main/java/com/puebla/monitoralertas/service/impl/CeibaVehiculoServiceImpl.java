package com.puebla.monitoralertas.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puebla.monitoralertas.dto.ChatMessage;
import com.puebla.monitoralertas.dto.ChatMessage.MessageType;
import com.puebla.monitoralertas.entity.DatosVehiculoEntity;
import com.puebla.monitoralertas.feign.client.SemoviDelFeignClient;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2KeyPojo;
import com.puebla.monitoralertas.json.pojo.DataDevicePojo;
import com.puebla.monitoralertas.repository.DatosVehiculoRepository;
import com.puebla.monitoralertas.service.APISistemaVideoVigilanciaService;
import com.puebla.monitoralertas.service.CeibaVehiculoService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CeibaVehiculoServiceImpl implements CeibaVehiculoService {

	@Autowired
	private DatosVehiculoRepository datosVehiculoRepository;

	@Autowired
	private APISistemaVideoVigilanciaService apiCeiba2;
	
	@Autowired
	private SemoviDelFeignClient semoviDelFeignClient; 
	
	@Autowired
	private SimpMessagingTemplate template;
	
	@Value("${monitor.video.url}")
	private String videoPath;
	
	/**
	 * Actualiza la lista de vehiculos de CEIBA2 en la base de monitor, Tambien consulta llave necesaria
	 * para consumir el API de CEIBA2
	 */
	@Override
	@Transactional
	public void actualizarVehiculosCeibaInMonitor() {

		// Solicita key necesario para hacer peticiones a CEIBA 2
		Ceiba2KeyPojo keyPojo = null;
		String key = null;
		DatosVehiculoEntity vehiculo = null; 
		List<DatosVehiculoEntity> vehiculosNuevos = null; 
		int newVehiclesCount = 0;
		int oldVehiclesCount = 0;
		
		try {
			
			keyPojo = apiCeiba2.getCeibaToken();
			key = keyPojo.getData().getKey();
			vehiculosNuevos = new ArrayList<>(); 
					
			// Obtiene lista de vehiculos del CEIBA 2
			Ceiba2DevicesPojo vehicles = apiCeiba2.getAllVehicles(key);

			// Actualiza vehiculos en tabla datos_vehiculo
			for (DataDevicePojo vehiculoCeiba : vehicles.getData()) {
				
				String idDispositivoCeiba = vehiculoCeiba.getDeviceid();					
				Optional<DatosVehiculoEntity> vehiculoEncontrado = datosVehiculoRepository.findById(idDispositivoCeiba);

				if(idDispositivoCeiba.equals("0099003604")) {
					log.info("Encontrado: " + vehiculoEncontrado.isPresent());
				}
				
				vehiculo = null;
				boolean vehicleNew = false;
				
				if(vehiculoCeiba.getGroupid().equals("4")) { // SIN ASIGNAR
					
				}
				
				// Si el vehiculo no se encuentra en la base lo crea, de lo contrario lo actualiza
				if(!vehiculoEncontrado.isPresent()) {
					//en caso de q dispositivo no este asignado no lo procesa
//					if(vehiculoCeiba.getGroupid().equals("4")) { // SIN ASIGNAR
//						continue;
//					}
					newVehiclesCount += 1;
					vehicleNew = true; 
					
					vehiculo = new DatosVehiculoEntity();
					//Datos que se agregan solo si es nuevo registro
					vehiculo.setIddispositivo(idDispositivoCeiba);
					vehiculo.setFechacaptura(new Date());
					vehiculo.setEstatus("FALTAN_DATOS");
				}else {
					
					oldVehiclesCount += 1;

					//En caso de que dispositivo ya no este asignado los elimina de semovi y base
//					if(vehiculoCeiba.getGroupid().equals("4")) { // SIN ASIGNAR
//						SemoviDelRequestDTO request = new SemoviDelRequestDTO();
//						request.setId(vehiculoCeiba.getDeviceid());
//						semoviDelFeignClient.del(request);
//						
//						datosVehiculoRepository.deleteById(vehiculoCeiba.getDeviceid());
//						continue;
//					}

					vehiculo = vehiculoEncontrado.get();
				}
				
				// Datos de los vehiculos q se agregan si es nuevo registro o actualizacion
				vehiculo.setFechamodificacion(new Date());
				vehiculo.setUrlcamera(videoPath + idDispositivoCeiba);

				if(vehicleNew)
					vehiculosNuevos.add(vehiculo);
				
				//Inserta o actualiza vehiculo en la base de datos
				datosVehiculoRepository.save(vehiculo);
			}
			
			log.info("Vehiculos ceiba : " + vehicles.getData().size() + " / " + vehicles.getData().get(0).getDeviceid());
			log.info("Vehiculos nuevos : " + newVehiclesCount);
			log.info("Vehiculos viejos : " + oldVehiclesCount);

			if(vehiculosNuevos.size() > 0) {
				log.info("Reportando a websocket: " + vehiculosNuevos.size());
				  ChatMessage mensaje = new ChatMessage();
			      mensaje.setContent("HOLAA");
			      mensaje.setType(MessageType.CHAT);
			      mensaje.setSender("HOLAA");
				template.convertAndSend("/topic/vehicle", mensaje);
			}

		} catch (Exception e) {
			log.error("Ocurrio un problema al actualizar vehiculos de ceiba a monitor", e);
		}
	}
	


}