package com.puebla.monitoralertas.dto;

import java.util.List;

import com.puebla.monitoralertas.entity.DatosVehiculoEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListaDatosVehiculosRegistradosDTO extends RespuestaJSON{
	
	private List<DatosVehiculoEntity> listaVehiculosRegistrados;
}
