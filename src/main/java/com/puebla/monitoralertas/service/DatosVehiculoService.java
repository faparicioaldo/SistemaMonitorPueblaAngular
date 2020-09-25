package com.puebla.monitoralertas.service;

import java.util.List;

import com.puebla.monitoralertas.entity.DatosVehiculoEntity;

public interface DatosVehiculoService {

	public DatosVehiculoEntity obtenerDatosVehiculo(String idDispositivo);
	public List<DatosVehiculoEntity> obtenerDatosVehiculos();
	public void guardaDatosVehiculo(DatosVehiculoEntity datosVehiculo);
	public void eliminarVehiculo(String iddispositivo);
}
