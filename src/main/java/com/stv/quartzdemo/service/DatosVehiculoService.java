package com.stv.quartzdemo.service;

import java.util.List;

import com.stv.quartzdemo.entity.AlarmaEntity;
import com.stv.quartzdemo.entity.DatosVehiculoEntity;

public interface DatosVehiculoService {

	public DatosVehiculoEntity obtenerDatosVehiculo(String idDispositivo);
	public List<DatosVehiculoEntity> obtenerDatosVehiculos();
	public void guardaDatosVehiculo(DatosVehiculoEntity datosVehiculo);
	public void guardaDatosAlarma(AlarmaEntity datosAlarma);

}
