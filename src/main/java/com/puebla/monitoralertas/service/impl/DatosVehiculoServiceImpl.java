package com.puebla.monitoralertas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.puebla.monitoralertas.constant.Constants;
import com.puebla.monitoralertas.dto.SemoviDelRequestDTO;
import com.puebla.monitoralertas.dto.SemoviLoadRequestDTO;
import com.puebla.monitoralertas.dto.SemoviResponseDTO;
import com.puebla.monitoralertas.entity.DatosVehiculoEntity;
import com.puebla.monitoralertas.feign.client.SemoviDelFeignClient;
import com.puebla.monitoralertas.feign.client.SemoviLoadFeignClient;
import com.puebla.monitoralertas.mapper.DatosAltaVehicleSemoviMapper;
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

	@Autowired
	private DatosAltaVehicleSemoviMapper datosAltaVehicleSemoviMapper; 
	
	@Override
	public DatosVehiculoEntity obtenerDatosVehiculo(String idDispositivo) {
		return datosVehiculoRepository.findById(idDispositivo).get();
	}

	@Override
	public List<DatosVehiculoEntity> obtenerDatosVehiculos() {
//		return datosVehiculoRepository.findAll(Sort.by("empresa").ascending().and(Sort.by("fechacaptura").descending()));
		return datosVehiculoRepository.findByEstatusIsNotOrderByEmpresaAscFechacapturaDesc(Constants.ESTATUS_VEHICULO_ELIMINADO);
	}

	@Override
	public String guardaDatosVehiculo(DatosVehiculoEntity datosVehiculo) {
		SemoviLoadRequestDTO request = datosAltaVehicleSemoviMapper.convertToDto(datosVehiculo);
		request.setId(datosVehiculo.getIddispositivo());
		request.setMunicipio(request.getMunicipio().equals("0")?"Indefinido":request.getMunicipio());
		String semoviReponse = semoviLoadFeignClient.load(request);

		ObjectMapper mapper = new ObjectMapper();

		SemoviResponseDTO response = null;;
		try {
			response = mapper.readValue(semoviReponse, SemoviResponseDTO.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		if(!response.getStatus()) {
			datosVehiculo.setEstatus("ERROR_ALTA_EN_SEMOVI");
		}
		datosVehiculoRepository.save(datosVehiculo);// new SemoviLoadRequestDTO()
		return response.getMsg();
	}
	
	@Override
	public void eliminarVehiculo(String iddispositivo) {
		String semoviReponse = semoviDelFeignClient.del(new SemoviDelRequestDTO(iddispositivo));
		ObjectMapper mapper = new ObjectMapper();
		SemoviResponseDTO response = null;;

		try {
			response = mapper.readValue(semoviReponse, SemoviResponseDTO.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		if(response.getStatus() || response.getMsg().contains("Equipo Inexistente")) {
			DatosVehiculoEntity vehicleData = datosVehiculoRepository.findById(iddispositivo).get();
			vehicleData.setEstatus(Constants.ESTATUS_VEHICULO_ELIMINADO);
			datosVehiculoRepository.save(vehicleData);
		}
	}

}
