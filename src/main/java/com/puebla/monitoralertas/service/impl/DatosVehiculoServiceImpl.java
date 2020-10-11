package com.puebla.monitoralertas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.puebla.monitoralertas.dto.SemoviDelRequestDTO;
import com.puebla.monitoralertas.dto.SemoviLoadRequestDTO;
import com.puebla.monitoralertas.entity.DatosVehiculoEntity;
import com.puebla.monitoralertas.feign.client.SemoviDelFeignClient;
import com.puebla.monitoralertas.feign.client.SemoviLoadFeignClient;
import com.puebla.monitoralertas.repository.DatosVehiculoRepository;
import com.puebla.monitoralertas.service.DatosVehiculoService;

@Service
public class DatosVehiculoServiceImpl implements DatosVehiculoService {
	
	@Autowired
	private DatosVehiculoRepository datosVehiculoRepository;

	@Autowired
	private SemoviDelFeignClient semoviDelFeignClient; 

	@Autowired
	private SemoviLoadFeignClient semoviLoadFeignClient;

	@Override
	public DatosVehiculoEntity obtenerDatosVehiculo(String idDispositivo) {
		return datosVehiculoRepository.findById(idDispositivo).get();
	}

	@Override
	public List<DatosVehiculoEntity> obtenerDatosVehiculos() {
		return datosVehiculoRepository.findAll(Sort.by("empresa").ascending().and(Sort.by("fechacaptura").descending()));
	}

	@Override
	public void guardaDatosVehiculo(DatosVehiculoEntity datosVehiculo) {
		String semoviReponse = semoviLoadFeignClient.load(new SemoviLoadRequestDTO());
		datosVehiculoRepository.save(datosVehiculo);
	}
	
	@Override
	public void eliminarVehiculo(String iddispositivo) {
		String semoviReponse = semoviDelFeignClient.del(new SemoviDelRequestDTO(iddispositivo));
		
		//TODO Si el vehiculo fue eliminado correctamente de semovi eliminarlo de la base de lo contrario NO
		datosVehiculoRepository.deleteById(iddispositivo);
	}

}
