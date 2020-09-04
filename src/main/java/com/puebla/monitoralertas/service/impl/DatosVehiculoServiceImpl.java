package com.puebla.monitoralertas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puebla.monitoralertas.entity.DatosVehiculoEntity;
import com.puebla.monitoralertas.repository.DatosVehiculoRepository;
import com.puebla.monitoralertas.service.DatosVehiculoService;

@Service
public class DatosVehiculoServiceImpl implements DatosVehiculoService {
	
	@Autowired
	private DatosVehiculoRepository datosVehiculoRepository;

	@Override
	public DatosVehiculoEntity obtenerDatosVehiculo(String idDispositivo) {
		return datosVehiculoRepository.findById(idDispositivo).get();
	}

	@Override
	public List<DatosVehiculoEntity> obtenerDatosVehiculos() {
		return datosVehiculoRepository.findAll();
	}

	@Override
	public void guardaDatosVehiculo(DatosVehiculoEntity datosVehiculo) {		
		datosVehiculoRepository.save(datosVehiculo);
	}

}
