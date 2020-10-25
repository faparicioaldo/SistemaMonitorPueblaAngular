package com.puebla.monitoralertas.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.puebla.monitoralertas.dto.SemoviLoadRequestDTO;
import com.puebla.monitoralertas.entity.DatosVehiculoEntity;

@Component
public class DatosAltaVehicleSemoviMapper {

	@Autowired
	private ModelMapper modelMapper;

	public SemoviLoadRequestDTO convertToDto(DatosVehiculoEntity entity) {
		return modelMapper.map(entity, SemoviLoadRequestDTO.class);
	}

}