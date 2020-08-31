package com.puebla.monitoralertas.service.impl;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.puebla.monitoralertas.entity.DatosVehiculoEntity;
import com.puebla.monitoralertas.json.pojo.Ceiba2DevicesPojo;
import com.puebla.monitoralertas.json.pojo.Ceiba2KeyPojo;
import com.puebla.monitoralertas.json.pojo.DataDevicePojo;
import com.puebla.monitoralertas.repository.DatosVehiculoRepository;
import com.puebla.monitoralertas.service.APISistemaVideoVigilanciaService;
import com.puebla.monitoralertas.service.CeibaVehiculoService;
import com.puebla.monitoralertas.service.VideoFrameService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class CeibaVehiculoServiceImpl implements CeibaVehiculoService {

	@Autowired
	private DatosVehiculoRepository datosVehiculoRepository;

	@Autowired
	private APISistemaVideoVigilanciaService apiCeiba2;

	@Autowired
	private VideoFrameService videoFrameService;
	
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

		try {
			
			keyPojo = apiCeiba2.getCeibaToken();
			key = keyPojo.getData().getKey();

			// Obtiene lista de vehiculos del CEIBA 2
			Ceiba2DevicesPojo vehicles = apiCeiba2.getAllVehicles(key);

			// Actualiza vehiculos en tabla datos_vehiculo
			for (DataDevicePojo vehiculoCeiba : vehicles.getData()) {
				String idDispositivoCeiba = vehiculoCeiba.getDeviceid();					
				Optional<DatosVehiculoEntity> vehiculoEncontrado = datosVehiculoRepository.findById(idDispositivoCeiba);
				vehiculo = null;
				
				// Si el vehiculo no se encuentra en la base lo crea, de lo contrario lo actualiza
				if(vehiculoEncontrado == null || !vehiculoEncontrado.isPresent() || vehiculoEncontrado.get() == null || vehiculoEncontrado.get().getIddispositivo() == null) {
					vehiculo = new DatosVehiculoEntity();
					//Datos que se agregan solo si es nuevo registro
					vehiculo.setIddispositivo(idDispositivoCeiba);
					vehiculo.setFechacaptura(new Date());
				}else {
					vehiculo = vehiculoEncontrado.get();
				}
				
				// Datos de los vehiculos q se agregan si es nuevo registro o actualizacion
				vehiculo.setFechamodificacion(new Date());
				vehiculo.setUrlcamera(videoFrameService.getUrlFrameLiveVideo(idDispositivoCeiba));

				datosVehiculoRepository.save(vehiculo);
			}
		} catch (Exception e) {
			log.error("Ocurrio un problema al actualizar vehiculos de ceiba a monitor", e);
		}
	}

}